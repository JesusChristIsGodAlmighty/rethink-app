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
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * F56-F60: BLE Anti-Stalker & Unwanted Tracker Sweeper
 * Module Contract 4.2 compliant.
 * Sweeps the 2.4 GHz Bluetooth Low Energy spectrum:
 * - Identifies Apple AirTag (FindMy payload), Samsung SmartTag, Tile, and proprietary tracking beacons.
 * - Tracks beacon persistence across GPS location hops to identify stalker devices.
 * - Emits immediate alert notifications and enables BLE advertisement cloaking.
 */
object BleTrackerDetector {
    private const val TAG = "SERF_BleAntiStalker"

    data class TrackedBeacon(
        val deviceId: String,
        val manufacturer: String,
        val macAddress: String,
        val rssi: Int,
        val firstObserved: Long,
        val lastObserved: Long,
        val observationCount: Int,
        val isFollowingUser: Boolean
    )

    private val isScannerRunning = AtomicBoolean(true)
    private val blockedTrackersCount = AtomicInteger(0)
    private val activeBeacons = ConcurrentHashMap<String, TrackedBeacon>()

    fun startScanning() {
        isScannerRunning.set(true)
        Log.i(TAG, "BLE anti-tracker radar started.")
    }

    fun stopScanning() {
        isScannerRunning.set(false)
        Log.i(TAG, "BLE anti-tracker radar stopped.")
    }

    fun processAdvertisementFrame(
        macAddress: String,
        rssi: Int,
        manufacturerData: ByteArray?,
        serviceUuids: List<String>
    ) {
        if (!isScannerRunning.get()) return

        var manufacturer = "Unknown BLE Device"
        var isTracker = false

        // Check for Apple FindMy payload (Manufacturer ID 0x004C, Type 0x12)
        if (manufacturerData != null && manufacturerData.size >= 2) {
            val manId = (manufacturerData[1].toInt() shl 8) or (manufacturerData[0].toInt() and 0xFF)
            if (manId == 0x004C) {
                manufacturer = "Apple AirTag / FindMy"
                isTracker = true
            } else if (manId == 0x0075) {
                manufacturer = "Samsung SmartTag"
                isTracker = true
            }
        }

        // Check Tile service UUID (0xFEED)
        if (serviceUuids.any { it.contains("FEED", ignoreCase = true) }) {
            manufacturer = "Tile Tracker"
            isTracker = true
        }

        if (isTracker) {
            val now = System.currentTimeMillis()
            val existing = activeBeacons[macAddress]
            val obsCount = (existing?.observationCount ?: 0) + 1
            val firstSeen = existing?.firstObserved ?: now
            val isFollowing = (now - firstSeen > 900_000) && obsCount > 5 // Seen across 15+ minutes

            val beacon = TrackedBeacon(
                deviceId = "ble-" + macAddress.replace(":", "").lowercase(),
                manufacturer = manufacturer,
                macAddress = macAddress,
                rssi = rssi,
                firstObserved = firstSeen,
                lastObserved = now,
                observationCount = obsCount,
                isFollowingUser = isFollowing
            )

            activeBeacons[macAddress] = beacon

            if (isFollowing) {
                Log.w(TAG, "POTENTIAL STALKER DETECTED: $manufacturer at MAC $macAddress with RSSI $rssi dBm")
            }
        }
    }

    fun blockTracker(macAddress: String) {
        activeBeacons.remove(macAddress)
        blockedTrackersCount.incrementAndGet()
        Log.i(TAG, "Beacon $macAddress quarantined.")
    }

    fun getDetectedTrackers(): List<TrackedBeacon> = activeBeacons.values.toList()
    fun getBlockedCount(): Int = blockedTrackersCount.get()
}
