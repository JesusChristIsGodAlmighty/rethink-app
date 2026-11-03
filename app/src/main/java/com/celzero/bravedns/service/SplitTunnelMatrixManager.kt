/*
 * Copyright 2026 SERF Project (Forked from RethinkDNS / BraveDNS)
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

import android.util.Log
import java.util.concurrent.ConcurrentHashMap

/**
 * F124: Per-App Split Tunneling Matrix Manager
 * Module Contract 4.2 compliant.
 * Manages fine-grained egress routing policies per installed application UID:
 * DIRECT (Bypass VPN), VPN (Default Encrypted Tunnel), TOR (Multi-hop Onion),
 * LOOPBACK_STUB (Fake-Online captive portal stubs), and BLOCK (Drop packet).
 */
object SplitTunnelMatrixManager {
    private const val TAG = "SERF_SplitTunnel"

    enum class RoutingPolicy {
        DIRECT,
        VPN,
        TOR,
        LOOPBACK_STUB,
        BLOCK
    }

    data class AppRule(
        val packageName: String,
        val appName: String,
        var policy: RoutingPolicy,
        var bytesRouted: Long = 0,
        var packetsCount: Long = 0
    )

    private val rules = ConcurrentHashMap<String, AppRule>()

    init {
        // Defaults: Banking direct, Browsers Tor, GMS loopback stub
        setRule("org.mozilla.firefox", "Firefox Browser", RoutingPolicy.TOR)
        setRule("com.chase.sig.android", "Chase Banking", RoutingPolicy.DIRECT)
        setRule("com.google.android.gms", "Google Play Services", RoutingPolicy.LOOPBACK_STUB)
        setRule("org.telegram.messenger", "Telegram", RoutingPolicy.VPN)
    }

    fun setRule(packageName: String, appName: String, policy: RoutingPolicy) {
        val existing = rules[packageName]
        if (existing != null) {
            existing.policy = policy
        } else {
            rules[packageName] = AppRule(packageName, appName, policy)
        }
        Log.i(TAG, "Assigned routing policy for $packageName: $policy")
    }

    fun getPolicyForPackage(packageName: String): RoutingPolicy {
        return rules[packageName]?.policy ?: RoutingPolicy.VPN
    }

    fun recordTraffic(packageName: String, bytes: Long) {
        rules[packageName]?.let {
            it.bytesRouted += bytes
            it.packetsCount += 1
        }
    }

    fun getAllRules(): List<AppRule> = rules.values.toList()
}
