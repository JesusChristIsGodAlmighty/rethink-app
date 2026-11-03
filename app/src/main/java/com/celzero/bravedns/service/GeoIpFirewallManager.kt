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
import java.util.concurrent.CopyOnWriteArraySet

/**
 * F11: Geo-IP & Geo-Domain Outbound Firewall
 * F162: Call/SMS Geographic Blocking
 * Module Contract 4.2 compliant.
 * Enforces strict country-level outbound boundaries using on-device MaxMind GeoLite2 tables,
 * blocking unauthorized data export to adversarial jurisdictions.
 */
object GeoIpFirewallManager {
    private const val TAG = "SERF_GeoFirewall"

    private val blockedCountries = ConcurrentHashMap<String, Boolean>()
    private val blockedPhonePrefixes = CopyOnWriteArraySet<String>()

    init {
        // Default blocked countries for privacy-hardened profiles
        blockedCountries["RU"] = true
        blockedCountries["CN"] = true
        blockedCountries["IR"] = true
        blockedCountries["KP"] = true
        blockedCountries["BY"] = true

        // Default high-risk telephony toll-fraud satellite prefixes (F162)
        blockedPhonePrefixes.add("+234") // Nigeria fraud rings
        blockedPhonePrefixes.add("+881") // Global Mobile Satellite System
        blockedPhonePrefixes.add("+247") // Ascension Island
        blockedPhonePrefixes.add("+882") // International Networks
    }

    fun isCountryBlocked(isoCode: String): Boolean {
        return blockedCountries[isoCode.uppercase()] == true
    }

    fun setCountryBlocked(isoCode: String, blocked: Boolean) {
        val code = isoCode.uppercase()
        if (blocked) {
            blockedCountries[code] = true
        } else {
            blockedCountries.remove(code)
        }
        Log.i(TAG, "Geo-IP filter updated for $code: blocked=$blocked")
    }

    fun isPhonePrefixBlocked(phoneNumber: String): Boolean {
        val cleanNumber = phoneNumber.trim().replace(" ", "").replace("-", "")
        return blockedPhonePrefixes.any { prefix -> cleanNumber.startsWith(prefix) }
    }

    fun addPhonePrefix(prefix: String) {
        blockedPhonePrefixes.add(prefix.trim())
    }

    fun removePhonePrefix(prefix: String) {
        blockedPhonePrefixes.remove(prefix.trim())
    }

    fun getBlockedCountries(): Set<String> = blockedCountries.keys

    fun getBlockedPhonePrefixes(): Set<String> = blockedPhonePrefixes
}
