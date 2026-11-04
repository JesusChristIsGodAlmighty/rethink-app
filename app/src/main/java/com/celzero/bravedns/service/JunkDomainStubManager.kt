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

import android.content.Context
import com.celzero.bravedns.database.EventSource
import com.celzero.bravedns.util.Logger
import com.celzero.bravedns.database.EventType
import com.celzero.firestack.backend.Backend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * F04 — Junk-Domain Stub List (Tier 1 quick win; D3 layer 4 of the Fake-Online design).
 *
 * What it does: maintains a list of domains that apps probe purely to check whether the
 * network is "really up" (connectivity checks, captive-portal probes, SDK presence pings).
 * When [isStubbed] answers true, the DNS pipeline (TunDnsManager.decideQuery) routes the
 * query to Backend.Reject so the Go core synthesizes a local NXDOMAIN immediately instead
 * of leaking the probe to the internet. Apps that consult these domains see "offline"
 * without the user's real traffic ever touching those hosts. This is the foundational,
 * no-server-required building block for F01 (Fake-Online Simulator): stubbing junk probes
 * first means fewer dead-signal requests racing the probe responder later.
 *
 * Permissions needed: none beyond what the VPN service already has; storage uses the app's
 * private files dir (data stays local, per section 3 defaults).
 *
 * What breaks if disabled: nothing. Off by default; behavior is byte-for-byte upstream when
 * persistentState.f04JunkDomainStubs is false.
 *
 * Module Contract notes: 4.2.1 manifest = this doc + registration in ServiceModule where
 * needed (none yet — object singleton like its siblings); 4.2.4 off-by-default via
 * PersistentState booleanPref; 4.3 versioned schema: the JSON store carries schemaVersion 1;
 * 4.2.7 attacker-influenced data: domain lists are untrusted content — read-only here,
 * actions capped (DNS branch choice only, never launches or mutates rules).
 */
object JunkDomainStubManager {

    private const val TAG = "JunkDomainStubManager"
    // current on-disk schema version (Module Contract 4.3 — bump + migrate() on change)
    internal const val SCHEMA_VERSION = 1
    private const val FILE_NAME = "junk_domain_stubs_v1.json"

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    // normalized (lowercase, trailing-dot-stripped, no wildcards) stub domains for exact match
    @Volatile
    private var stubs: Set<String> = emptySet()

    /**
     * The built-in seed list: well-known connectivity/probe endpoints Android and common
     * SDKs hit to decide "am I online". All entries are exact hosts; subdomain matching is
     * handled by [isStubbed]. Extend via import/user additions, not by editing this list
     * ad hoc (contributions flow through F216 once the data repo exists).
     */
    internal val SEED_DOMAINS: Set<String> = setOf(
        // Android / Google connectivity checks
        "clients3.google.com",           // generate_204
        "clientservices.googleapis.com",
        "connectivitycheck.android.com",
        "connectivitycheck.gstatic.com",
        "play.googleapis.com",
        // Chromium / Chrome
        "chrome-connectivitycheck.gstatic.com",
        "chrome-connectivitycheck2.gstatic.com",
        "dns.google",
        // Samsung
        "connectivitycheck.samsung.com",
        "www.samsung.com/galaxy-sans-captive-test",
        // Microsoft
        "www.msftconnecttest.com",
        "www.msftncsi.com",
        // Apple
        "captive.apple.com",
        // Firefox / Mozilla
        "detectportal.firefox.com",
        "connectivitycheck.mozilla.org",
        // Canonical / Ubuntu-based
        "connectivity-check.ubuntu.com",
        // Amazon / Alexa-adjacent
        "client-drop.amazon.com",
        // Common captive-portal probe hosts (hotels/hotspots)
        "speedtest.xfinitymobile.com",
        "hotspot-check.google.com"
    )

    private fun normalize(d: String): String {
        return d.trim().lowercase().removeSuffix(".")
    }

    /**
     * Load the persisted stub list (creating it from the seed list on first run), then keep
     * the in-memory snapshot hot. Called from BraveVPNService startup alongside the rest of
     * the engine; safe to call more than once.
     */
    fun initialize(context: Context) {
        scope.launch {
            try {
                val f = File(context.filesDir, FILE_NAME)
                if (!f.exists()) {
                    f.writeText(serialize(SCHEMA_VERSION, SEED_DOMAINS.toList()))
                }
                val json = JSONObject(f.readText())
                val migrated = migrate(json)
                stubs = migrated.optJSONArray("domains")?.let { arr ->
                    buildSet(arr.length()) {
                        for (i in 0 until arr.length()) {
                            val d = normalize(arr.optString(i))
                            if (d.isNotEmpty()) add(d)
                        }
                    }
                } ?: SEED_DOMAINS
                EventLogger.log(EventType.SYSTEM_EVENT, message = "F04: loaded ${stubs.size} junk-domain stubs", source = EventSource.SERVICE)
            } catch (e: Exception) {
                // fall back to the seed list; never let a corrupt file disable the engine
                stubs = SEED_DOMAINS
                Logger.e(TAG, "stub list load failed, using seeds: ${e.message}", e)
            }
        }
    }

    // test/synchronous hook: swap the in-memory snapshot directly (unit tests, dev console)
    internal fun setStubsForTest(domains: Set<String>) {
        stubs = domains.map { normalize(it) }.toSet()
    }

    fun getStubs(): Set<String> = stubs

    /**
     * True when [fqdn] matches a stub exactly or as a subdomain (e.g. a.b.gstatic.com-style
     * probe variants under a stubbed apex). Case-insensitive; blank-safe.
     */
    fun isStubbed(fqdn: String): Boolean {
        val d = normalize(fqdn)
        if (d.isEmpty()) return false
        if (stubs.contains(d)) return true
        // subdomain match against any stub apex
        var idx = d.indexOf('.')
        while (idx >= 0 && idx < d.length - 1) {
            if (stubs.contains(d.substring(idx + 1))) return true
            idx = d.indexOf('.', idx + 1)
        }
        return false
    }

    /** Add a domain to the persisted list. Returns true if the set changed. */
    suspend fun addStub(context: Context, domain: String): Boolean {
        val d = normalize(domain)
        if (d.isEmpty() || stubs.contains(d)) return false
        val next = stubs + d
        // update the hot snapshot first so decisions reflect the change immediately,
        // then persist synchronously within the caller's coroutine (no fire-and-forget)
        stubs = next
        persist(context, next)
        return true
    }

    /** Remove a domain from the persisted list. Returns true if the set changed. */
    suspend fun removeStub(context: Context, domain: String): Boolean {
        val d = normalize(domain)
        if (!stubs.contains(d)) return false
        val next = stubs - d
        stubs = next
        persist(context, next)
        return true
    }

    /** Restore the built-in seed list, discarding user additions/removals. */
    suspend fun resetToSeeds(context: Context) {
        stubs = SEED_DOMAINS
        persist(context, SEED_DOMAINS)
    }

    private suspend fun persist(context: Context, domains: Set<String>) {
        try {
            File(context.filesDir, FILE_NAME).writeText(serialize(SCHEMA_VERSION, domains.toList()))
        } catch (e: Exception) {
            Logger.e(TAG, "stub list persist failed: ${e.message}", e)
        }
    }

    private fun serialize(version: Int, domains: List<String>): String {
        val root = JSONObject()
        root.put("schemaVersion", version)
        root.put("domains", JSONArray(domains.sorted()))
        return root.toString(2)
    }

    /**
     * Schema migration seam (Module Contract 4.3). v1 == passthrough. Future versions bump
     * SCHEMA_VERSION, rename FILE_NAME, and transform here from the previous shape.
     */
    private fun migrate(json: JSONObject): JSONObject = json

    // convenience for callers deciding transport ids (kept here so the DNS layer reads one API)
    const val STUB_BRANCH = "junk-stub-reject"

    /**
     * The transport decision applied when a query is stubbed: Reject makes the Go core
     * answer locally (synthetic NXDOMAIN) with zero network egress. Exposed as a tiny pure
     * function so both the live path and the connection simulator share identical semantics.
     */
    fun rejectedDecision(uid: Int, fqdn: String, isIfaceCellular: Boolean, ssid: String?) =
        com.celzero.bravedns.tunnel.TunDnsManager.DnsDecision(
            uid, Pair(Backend.Reject, ""), fqdn, false, isIfaceCellular, ssid, STUB_BRANCH
        )
}
