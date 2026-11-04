/*
 * Copyright 2026 SERF (RethinkDNS fork) and its authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.celzero.bravedns.service

import com.celzero.bravedns.database.CustomDomain
import com.celzero.bravedns.util.Constants
import com.celzero.bravedns.util.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket
import java.net.URLDecoder
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

/**
 * F191 — Block Page (Tier 1 quick win; D3 Fake-Online design, user-facing half).
 *
 * What it does: serves a tiny plain-language HTML page from a loopback-only HTTP
 * server explaining WHY a connection was blocked and offering one-tap unblock links
 * (which reuse the F192 trust-rule ladder via [DomainRulesManager.trustEffectiveRule]
 * and add-a-trust-override fallback). The DNS decision itself is unchanged — blocks
 * still resolve as they did before; this module only adds the human-facing page and
 * the record of recently-blocked domains via [noteBlocked].
 *
 * Scope boundary (section 15.1): the server binds 127.0.0.1 inside the VPN process
 * and only ever answers requests originating from this device's own traffic. It
 * never intercepts, alters, or observes anyone else's traffic.
 *
 * Module Contract (4.2): independent, removable module; off by default (4.2.4, pref
 * f191BlockPageEnabled); no new permissions; attacker-influenced data (the requested
 * host/path) is HTML-escaped before rendering and capped in length (4.2.7); every
 * failure path degrades to the pre-feature behavior rather than breaking DNS.
 *
 * Known limitation (logged in STATE section 10): firestack exposes no way to suggest
 * IPv4 answers (DnsDecision.ipcsv is always ""), so with the current Go core browsers
 * are not automatically redirected here; the page and its unblock links are reachable
 * at http://127.0.0.1:<port>/?host=<domain>. Wiring ipcsv through decideQuery is a
 * follow-up once the backend supports suggested ips (see TunDnsManager.toNsOpts TODO).
 */
object BlockPageManager {

    private const val TAG = "BlockPageManager"
    // loopback port for the mini server; distinct from proxy ports (8118 etc.)
    internal const val PORT = 8194
    private const val MAX_URI_LEN = 2048
    private const val MAX_HOST_LEN = 253

    private val running = AtomicBoolean(false)
    @Volatile private var serverSocket: ServerSocket? = null

    /** Domains the user has viewed a block page for, most-recent first (capped). */
    private val recentBlocked = ArrayDeque<String>()

    fun isRunning(): Boolean = running.get()

    /**
     * Start the loopback block-page server if [enabled]. Idempotent: calling again while
     * running is a no-op; calling with enabled=false stops a running server. Any bind/IO
     * failure is logged and swallowed — DNS keeps working regardless (fail-open design).
     */
    @Synchronized
    fun updateEnabledState(enabled: Boolean) {
        if (enabled && !running.get()) startServer()
        if (!enabled && running.get()) stopServer()
    }

    /** Record a blocked lookup for the page's "recently blocked" list (dedup, cap 20). */
    fun noteBlocked(fqdn: String) {
        if (fqdn.isBlank()) return
        synchronized(recentBlocked) {
            recentBlocked.remove(fqdn)
            recentBlocked.addFirst(fqdn)
            while (recentBlocked.size > 20) recentBlocked.removeLast()
        }
    }

    // returns true when the request line was parsed and a response written
    internal fun serveRequest(socket: Socket): Boolean {
        socket.use { s ->
            s.soTimeout = 3000
            val reader = BufferedReader(InputStreamReader(s.getInputStream(), Charsets.UTF_8))
            val requestLine = reader.readLine() ?: return false
            // drain headers (keep-alive bodies are irrelevant here)
            while (true) {
                val line = reader.readLine() ?: break
                if (line.isEmpty()) break
            }
            // "GET /path?host=x HTTP/1.1"
            val parts = requestLine.split(" ")
            if (parts.size < 2 || parts[0] != "GET") return false
            val target = parts[1].take(MAX_URI_LEN)
            val params = parseQuery(target)
            val host = params["host"]?.take(MAX_HOST_LEN) ?: extractHostFromPath(target)
            val scope = params["scope"]?.take(16)
            val action = params["action"]?.take(16)

            var status = "200 OK"
            if (action == "unblock" && !host.isNullOrEmpty()) {
                val ok = handleUnblock(host, scope)
                status = if (ok) "200 OK" else "404 Not Found"
            }
            val body = renderPage(host)
            val bytes = body.toByteArray(Charsets.UTF_8)
            val out = s.getOutputStream()
            out.write(
                ("HTTP/1.1 $status\r\nContent-Type: text/html; charset=utf-8\r\n" +
                    "Content-Length: ${bytes.size}\r\nConnection: close\r\n\r\n").toByteArray(Charsets.US_ASCII)
            )
            out.write(bytes)
            out.flush()
            return true
        }
    }

    /**
     * One-tap unblock from the block page (reuses the F192 ladder). Runs the DB-touching
     * work on Dispatchers.IO via runBlocking — safe because callers are dedicated daemon
     * request threads, never the main thread. Returns false on any failure (fail-closed:
     * the page just shows an error-free reload instead of corrupting rules).
     */
    internal fun handleUnblock(host: String, scope: String?): Boolean {
        val d = host.lowercase().trim('.')
        if (d.isEmpty() || d.contains("..")) return false
        return try {
            kotlinx.coroutines.runBlocking {
                // "everybody" scope: walk exact -> wildcard -> everybody rule ladder
                if (DomainRulesManager.trustEffectiveRule(d, Constants.UID_EVERYBODY)) return@runBlocking true
                // blocked purely by a user-list (RULE2G) has no BLOCK rule to flip;
                // fall back to adding an explicit everybody-scope trust override,
                // mirroring the connection-log bottom sheet behavior from F192.
                val existing = DomainRulesManager.getObj(Constants.UID_EVERYBODY, d)
                if (existing != null) {
                    if (existing.status == DomainRulesManager.Status.TRUST.id) return@runBlocking false
                    DomainRulesManager.trust(existing)
                    return@runBlocking true
                }
                DomainRulesManager.addDomainRule(
                    d, DomainRulesManager.Status.TRUST,
                    DomainRulesManager.DomainType.DOMAIN, Constants.UID_EVERYBODY
                )
                true
            }
        } catch (e: Exception) {
            Logger.i(TAG, "unblock failed for $d: ${e.message}")
            false
        }
    }

    internal fun parseQuery(target: String): Map<String, String> {
        val q = target.substringAfter('?', "")
        if (q.isEmpty()) return emptyMap()
        return q.split("&").mapNotNull { kv ->
            val k = kv.substringBefore('=').ifEmpty { return@mapNotNull null }
            val v = kv.substringAfter('=', "")
            try {
                URLDecoder.decode(k, "UTF-8") to URLDecoder.decode(v, "UTF-8")
            } catch (_: Exception) {
                null
            }
        }.toMap()
    }

    // fallback: some clients hit "/" without params — treat leading label as the host hint
    private fun extractHostFromPath(target: String): String? {
        val path = target.substringBefore('?').trimStart('/')
        if (path.isEmpty() || path.contains("..")) return null
        return path.takeIf { it.matches(Regex("[a-z0-9.-]{1,$MAX_HOST_LEN}", RegexOption.IGNORE_CASE)) }
    }

    // all user-controlled strings pass through esc() before hitting the html (4.2.7)
    internal fun esc(s: String?): String {
        if (s.isNullOrEmpty()) return ""
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
            .replace("\"", "&quot;").replace("'", "&#39;")
    }

    internal fun renderPage(host: String?): String {
        val h = esc(host)
        val hostLine = if (h.isEmpty()) {
            "A connection on this device was blocked."
        } else {
            "<b>$h</b> was blocked by your firewall."
        }
        val recent = synchronized(recentBlocked) { recentBlocked.toList() }
        val recentHtml = if (recent.isEmpty()) "" else {
            "<p>Recently blocked:</p><ul>" +
                recent.joinToString("") { d -> "<li>${esc(d)}</li>" } + "</ul>"
        }
        val unblockLinks = if (h.isEmpty()) "" else {
            """<p>
            |<a href="/?action=unblock&amp;host=$h&amp;scope=everybody">Unblock everywhere</a> ·
            |<a href="/?action=unblock&amp;host=$h&amp;scope=app">Unblock for this app</a>
            |</p>""".trimMargin()
        }
        // standing education requirement (14.3): zero-jargon wording
        return """<!doctype html>
<html lang="en"><head><meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Blocked — SERF</title>
<style>body{font-family:sans-serif;max-width:30em;margin:3em auto;padding:0 1em;color:#222}
a{color:#0b6efd}small{color:#666}</style></head>
<body>
<h1>⛔ Blocked</h1>
<p>$hostLine</p>
<p>This app (SERF) stopped the connection because a rule you (or a blocklist you chose)
set says this site shouldn't be reached. Nothing was sent to the internet.</p>
$unblockLinks
$recentHtml
<p><small>SERF block page · runs only on this device · <a href="/">reload</a></small></p>
</body></html>"""
    }

    private fun startServer() {
        try {
            val ss = ServerSocket(PORT, 10, java.net.InetAddress.getByName("127.0.0.1"))
            serverSocket = ss
            running.set(true)
            thread(name = "serf-blockpage", isDaemon = true) {
                while (!ss.isClosed) {
                    try {
                        val sock = ss.accept()
                        thread(isDaemon = true) {
                            try {
                                serveRequest(sock)
                            } catch (_: Exception) {
                                // per-request failures must never kill the listener
                            } finally {
                                runCatching { sock.close() }
                            }
                        }
                    } catch (_: Exception) {
                        break // socket closed => shutdown
                    }
                }
                running.set(false)
            }
            Logger.i(TAG, "block page server started on 127.0.0.1:$PORT")
        } catch (e: Exception) {
            running.set(false)
            serverSocket = null
            Logger.e(TAG, "failed to start block page server: ${e.message}")
        }
    }

    private fun stopServer() {
        runCatching { serverSocket?.close() }
        serverSocket = null
        running.set(false)
        Logger.i(TAG, "block page server stopped")
    }
}
