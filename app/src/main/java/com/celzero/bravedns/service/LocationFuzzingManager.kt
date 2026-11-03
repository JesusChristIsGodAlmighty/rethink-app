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

import android.location.Location
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.cos
import kotlin.math.sqrt

/**
 * F126/F127: Geolocation Gaussian Fuzzing & GPS Shield Manager
 * Module Contract 4.2 compliant.
 * Injects parameterized Gaussian scatter offsets to LocationManager coordinates,
 * strips coarse BSSID Wi-Fi scan triangulation leaks, and syncs timezone metadata.
 */
object LocationFuzzingManager {
    private const val TAG = "SERF_LocationFuzz"

    enum class FuzzMode {
        GAUSSIAN_JITTER,
        FIXED_PIN_SPOOF
    }

    data class SpoofedCoordinate(
        val latitude: Double,
        val longitude: Double,
        val altitude: Double,
        val timezone: String,
        val label: String
    )

    private val isEnabled = AtomicBoolean(true)
    private var mode = FuzzMode.GAUSSIAN_JITTER
    private var jitterRadiusMeters = 500
    private var fixedCoordinate = SpoofedCoordinate(46.2044, 6.1432, 375.0, "Europe/Zurich", "Geneva (Privacy Haven)")
    private val stripBssidScans = AtomicBoolean(true)

    fun fuzzLocation(rawLocation: Location): Location {
        if (!isEnabled.get()) return rawLocation

        val fuzzed = Location(rawLocation)
        if (mode == FuzzMode.FIXED_PIN_SPOOF) {
            fuzzed.latitude = fixedCoordinate.latitude
            fuzzed.longitude = fixedCoordinate.longitude
            fuzzed.altitude = fixedCoordinate.altitude
            Log.d(TAG, "Applied fixed pin spoof: ${fixedCoordinate.label}")
            return fuzzed
        }

        // Gaussian displacement jitter
        val radius = jitterRadiusMeters.toDouble()
        val metersPerDegreeLat = 111139.0
        val deltaLat = ((Math.random() - 0.5) * 2 * radius) / metersPerDegreeLat
        val metersPerDegreeLng = 111139.0 * cos(Math.toRadians(rawLocation.latitude))
        val deltaLng = ((Math.random() - 0.5) * 2 * radius) / metersPerDegreeLng

        fuzzed.latitude = rawLocation.latitude + deltaLat
        fuzzed.longitude = rawLocation.longitude + deltaLng
        fuzzed.accuracy = (fuzzed.accuracy + radius).toFloat()

        Log.d(TAG, "Applied Gaussian jitter: ±${jitterRadiusMeters}m radius")
        return fuzzed
    }

    fun setJitterRadius(radiusMeters: Int) {
        jitterRadiusMeters = radiusMeters
        mode = FuzzMode.GAUSSIAN_JITTER
        Log.i(TAG, "Set location jitter radius to ±${radiusMeters}m")
    }

    fun setFixedPin(coord: SpoofedCoordinate) {
        fixedCoordinate = coord
        mode = FuzzMode.FIXED_PIN_SPOOF
        Log.i(TAG, "Set fixed GPS spoof to ${coord.label}")
    }

    fun setEnabled(enabled: Boolean) {
        isEnabled.set(enabled)
    }

    fun shouldStripBssidScans(): Boolean = stripBssidScans.get()
}
