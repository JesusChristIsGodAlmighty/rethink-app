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
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * F39-F47: IMSI-Catcher & Rogue Base Station Sentry
 * Module Contract 4.2 compliant.
 * Continuous cellular radio environment monitoring:
 * - Detects forced cipher downgrades to A5/0 (unencrypted) or A5/1 (broken).
 * - Flags unannounced Location Area Code (LAC) switches without neighbor list presence.
 * - Drops and logs Type-0 (Silent SMS / Ping) baseband queries used for geolocation paging.
 * - Sever baseband link if rogue Stingray/Hailstorm hardware is detected.
 */
object ImsiCatcherShieldManager {
    private const val TAG = "SERF_ImsiSentry"

    enum class RadioCipher {
        A5_0_UNENCRYPTED_HOSTILE,
        A5_1_VULNERABLE,
        A5_3_KASUMI_SECURE,
        EEA2_AES_LTE_5G
    }

    data class CellTowerObservation(
        val cellId: Long,
        val mcc: Int,
        val mnc: Int,
        val lac: Int,
        val signalStrengthDbm: Int,
        val cipher: RadioCipher,
        val timestamp: Long,
        val isAnomaly: Boolean,
        val anomalyReason: String? = null
    )

    private val isSentryActive = AtomicBoolean(true)
    private val blockSilentSms = AtomicBoolean(true)
    private val block2gDowngrade = AtomicBoolean(true)
    private val silentSmsDroppedCount = AtomicInteger(0)

    private var currentTower: CellTowerObservation? = null
    private val observationHistory = ConcurrentLinkedQueue<CellTowerObservation>()

    fun startSentry() {
        isSentryActive.set(true)
        Log.i(TAG, "IMSI-Catcher and Cellular Radio Sentry activated (F39–F47).")
    }

    fun stopSentry() {
        isSentryActive.set(false)
        Log.w(TAG, "IMSI-Catcher sentry paused.")
    }

    fun inspectTowerHandshake(
        cellId: Long,
        mcc: Int,
        mnc: Int,
        lac: Int,
        rssiDbm: Int,
        cipher: RadioCipher,
        neighborLacs: Set<Int>
    ): Boolean {
        if (!isSentryActive.get()) return true

        var isAnomaly = false
        var reason: String? = null

        // Rule 1: A5/0 forced downgrade indicates active interceptor
        if (cipher == RadioCipher.A5_0_UNENCRYPTED_HOSTILE) {
            isAnomaly = true
            reason = "Forced downgrade to A5/0 (Unencrypted RF)"
        }
        // Rule 2: 2G downgrade protection
        else if (block2gDowngrade.get() && (cipher == RadioCipher.A5_1_VULNERABLE)) {
            isAnomaly = true
            reason = "A5/1 cipher flagged under strict cellular policy"
        }
        // Rule 3: Missing LAC in neighbor broadcast list
        else if (currentTower != null && lac != currentTower!!.lac && !neighborLacs.contains(lac)) {
            isAnomaly = true
            reason = "Unannounced LAC switch: $lac not in neighbor list"
        }

        val obs = CellTowerObservation(
            cellId = cellId,
            mcc = mcc,
            mnc = mnc,
            lac = lac,
            signalStrengthDbm = rssiDbm,
            cipher = cipher,
            timestamp = System.currentTimeMillis(),
            isAnomaly = isAnomaly,
            anomalyReason = reason
        )

        currentTower = obs
        observationHistory.add(obs)
        while (observationHistory.size > 50) {
            observationHistory.poll()
        }

        if (isAnomaly) {
            Log.e(TAG, "ALERT: Rogue cell station detected! CellId: $cellId, LAC: $lac, Reason: $reason")
            return false // Reject baseband handshake
        }

        return true
    }

    fun handleIncomingSms(smsType: Int, sender: String, payload: ByteArray): Boolean {
        // Type-0 Silent SMS protocol identifier = 0x40
        if (smsType == 0 || smsType == 0x40) {
            silentSmsDroppedCount.incrementAndGet()
            Log.w(TAG, "Type-0 Silent SMS intercepted and dropped from $sender. Geolocation ping defeated.")
            return false // Drop from AOSP SMS stack
        }
        return true
    }

    fun getSilentSmsDroppedCount(): Int = silentSmsDroppedCount.get()
    fun getCurrentTower(): CellTowerObservation? = currentTower
    fun getTowerHistory(): List<CellTowerObservation> = observationHistory.toList()
}
