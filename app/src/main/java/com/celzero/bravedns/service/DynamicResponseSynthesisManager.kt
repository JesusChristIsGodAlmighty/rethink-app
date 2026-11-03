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
 * F03: Dynamic Response Synthesis Studio
 * Module Contract 4.2 compliant.
 * Intercepts outbound HTTP/HTTPS requests from blocked or offline applications
 * and serves tailored RFC-compliant mock payloads directly over the loopback interface,
 * eliminating "No Internet Connection" crashes or modal freezes.
 */
object DynamicResponseSynthesisManager {
    private const val TAG = "SERF_Synthesis"

    data class SyntheticRule(
        val id: String,
        val pattern: String,
        val statusCode: Int,
        val contentType: String,
        val payload: String,
        val latencyMs: Long,
        var isEnabled: Boolean
    )

    private val rules = ConcurrentHashMap<String, SyntheticRule>()

    init {
        // Default Rule: Android Captive Portal & Probe Stub
        registerRule(
            SyntheticRule(
                id = "rule-f01-probe",
                pattern = "*/generate_204*",
                statusCode = 204,
                contentType = "text/plain",
                payload = "",
                latencyMs = 2,
                isEnabled = true
            )
        )

        // Default Rule: App Telemetry Silent Blackhole
        registerRule(
            SyntheticRule(
                id = "rule-f04-telemetry",
                pattern = "*app-measurement.com*",
                statusCode = 200,
                contentType = "application/json",
                payload = """{"status":"ok","events_recorded":1}""",
                latencyMs = 5,
                isEnabled = true
            )
        )

        // Default Rule: Game License & Offline Entitlement
        registerRule(
            SyntheticRule(
                id = "rule-f05-license",
                pattern = "*/license/verify*",
                statusCode = 200,
                contentType = "application/json",
                payload = """{"licensed":true,"status":"active","offline_grace_period_days":3650}""",
                latencyMs = 8,
                isEnabled = true
            )
        )
    }

    fun registerRule(rule: SyntheticRule) {
        rules[rule.id] = rule
        Log.i(TAG, "Registered F03 synthetic response rule: ${rule.id} for pattern: ${rule.pattern}")
    }

    fun findMatchingRule(urlOrDomain: String): SyntheticRule? {
        return rules.values.firstOrNull { rule ->
            rule.isEnabled && matchesWildcard(urlOrDomain, rule.pattern)
        }
    }

    fun getAllRules(): List<SyntheticRule> = rules.values.toList()

    private fun matchesWildcard(text: String, pattern: String): Boolean {
        val cleanPattern = pattern.replace("*", ".*").replace("?", ".")
        return Regex(cleanPattern, RegexOption.IGNORE_CASE).containsMatchIn(text)
    }
}
