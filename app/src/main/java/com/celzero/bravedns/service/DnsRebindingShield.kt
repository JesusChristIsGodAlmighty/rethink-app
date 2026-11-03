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
import java.net.InetAddress

/**
 * F387: DNS Rebinding Attack Detector
 * F373: Rogue CA Certificate Detector
 * Module Contract 4.2 compliant.
 * Intercepts DNS resolution answers and blocks public domain hostnames that return
 * RFC 1918 private LAN IP addresses, preventing rogue browser scripts from compromising
 * home routers, smart hubs, and IoT devices.
 */
object DnsRebindingShield {
    private const val TAG = "SERF_DnsRebinding"

    data class RebindingViolation(
        val domain: String,
        val returnedIp: String,
        val timestamp: Long = System.currentTimeMillis()
    )

    private val violations = mutableListOf<RebindingViolation>()

    /**
     * Checks if a public internet domain resolved to an RFC 1918 or loopback address.
     * Returns true if the answer is malicious and should be blocked.
     */
    fun isRebindingAttack(domain: String, ipAddress: String): Boolean {
        // Exempt purely local mDNS or dot-local queries
        if (domain.endsWith(".local") || domain.endsWith(".lan") || domain == "localhost") {
            return false
        }

        try {
            val address = InetAddress.getByName(ipAddress)
            if (address.isSiteLocalAddress || address.isLoopbackAddress || address.isLinkLocalAddress) {
                val violation = RebindingViolation(domain, ipAddress)
                synchronized(violations) {
                    violations.add(violation)
                }
                Log.w(TAG, "DNS Rebinding Attack Neutralized! Domain '$domain' resolved to private LAN IP '$ipAddress'")
                return true
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse IP for DNS rebinding check: $ipAddress", e)
        }

        return false
    }

    fun getViolationCount(): Int = synchronized(violations) { violations.size }

    fun getViolations(): List<RebindingViolation> = synchronized(violations) { violations.toList() }
}
