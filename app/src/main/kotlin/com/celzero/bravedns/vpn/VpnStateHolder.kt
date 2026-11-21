package com.celzero.bravedns.vpn

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.celzero.bravedns.model.VpnTunnelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class VpnRuntimeStatus(
    val state: VpnTunnelState = VpnTunnelState.STOPPED,
    val startedAtMs: Long = 0L,
    val sessionAllowedQueries: Int = 0,
    val sessionBlockedQueries: Int = 0,
    val lastQueryDomain: String = "",
    val lastLatencyMs: Long = 0L
)

object VpnStateHolder {
    private val _status = MutableStateFlow(VpnRuntimeStatus())
    val status: StateFlow<VpnRuntimeStatus> = _status.asStateFlow()

    fun setState(newState: VpnTunnelState) {
        _status.update { current ->
            val started = when (newState) {
                VpnTunnelState.ACTIVE -> if (current.startedAtMs == 0L) System.currentTimeMillis() else current.startedAtMs
                VpnTunnelState.STOPPED -> 0L
                else -> current.startedAtMs
            }
            current.copy(
                state = newState,
                startedAtMs = started,
                sessionAllowedQueries = if (newState == VpnTunnelState.STOPPED) 0 else current.sessionAllowedQueries,
                sessionBlockedQueries = if (newState == VpnTunnelState.STOPPED) 0 else current.sessionBlockedQueries
            )
        }
    }

    fun recordQuery(domain: String, blocked: Boolean, latencyMs: Long) {
        _status.update { current ->
            current.copy(
                sessionAllowedQueries = current.sessionAllowedQueries + if (blocked) 0 else 1,
                sessionBlockedQueries = current.sessionBlockedQueries + if (blocked) 1 else 0,
                lastQueryDomain = domain,
                lastLatencyMs = latencyMs
            )
        }
    }

    fun startVpn(context: Context) {
        val intent = Intent(context, RethinkVpnService::class.java).apply {
            action = RethinkVpnService.ACTION_START
        }
        ContextCompat.startForegroundService(context, intent)
    }

    fun pauseVpn(context: Context) {
        val intent = Intent(context, RethinkVpnService::class.java).apply {
            action = RethinkVpnService.ACTION_PAUSE
        }
        context.startService(intent)
    }

    fun resumeVpn(context: Context) {
        val intent = Intent(context, RethinkVpnService::class.java).apply {
            action = RethinkVpnService.ACTION_RESUME
        }
        context.startService(intent)
    }

    fun stopVpn(context: Context) {
        val intent = Intent(context, RethinkVpnService::class.java).apply {
            action = RethinkVpnService.ACTION_STOP
        }
        context.startService(intent)
    }
}
