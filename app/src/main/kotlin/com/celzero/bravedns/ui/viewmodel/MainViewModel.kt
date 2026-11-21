package com.celzero.bravedns.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celzero.bravedns.data.AppSettings
import com.celzero.bravedns.data.RethinkRepository
import com.celzero.bravedns.data.db.ConnectionLogEntity
import com.celzero.bravedns.data.db.DnsLogEntity
import com.celzero.bravedns.model.BraveMode
import com.celzero.bravedns.vpn.DohClient
import com.celzero.bravedns.vpn.DohProbeResult
import com.celzero.bravedns.vpn.VpnRuntimeStatus
import com.celzero.bravedns.vpn.VpnStateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val settings: AppSettings = AppSettings(),
    val vpnStatus: VpnRuntimeStatus = VpnRuntimeStatus(),
    val blockedAppsCount: Int = 0,
    val allowedAppsCount: Int = 0,
    val customDomainRulesCount: Int = 0,
    val customIpRulesCount: Int = 0,
    val selectedBlocklistsCount: Int = 0,
    val totalDnsQueries: Int = 0,
    val blockedDnsQueries: Int = 0,
    val allowedHeatmap: List<Int> = List(144) { 0 },
    val blockedHeatmap: List<Int> = List(144) { 0 },
    val activeWgCount: Int = 0,
    val activeProxyCount: Int = 0
)

sealed interface ProbeUiState {
    data object Idle : ProbeUiState
    data object Probing : ProbeUiState
    data class Result(val result: DohProbeResult) : ProbeUiState
}

class MainViewModel(
    private val repository: RethinkRepository
) : ViewModel() {

    private val _probeState = MutableStateFlow<ProbeUiState>(ProbeUiState.Idle)
    val probeState: StateFlow<ProbeUiState> = _probeState.asStateFlow()

    val uiState: StateFlow<HomeUiState> = combine(
        repository.settings.settingsFlow,
        VpnStateHolder.status,
        repository.blockedAppCountFlow,
        repository.allowedAppCountFlow,
        repository.customDomainCountFlow,
        repository.customIpCountFlow,
        repository.selectedBlocklistCountFlow,
        repository.dnsLogsFlow,
        repository.wireGuardConfigsFlow,
        repository.proxyConfigsFlow
    ) { values ->
        val settings = values[0] as AppSettings
        val vpnStatus = values[1] as VpnRuntimeStatus
        val blockedApps = values[2] as Int
        val allowedApps = values[3] as Int
        val domainCount = values[4] as Int
        val ipCount = values[5] as Int
        val blocklistCount = values[6] as Int
        @Suppress("UNCHECKED_CAST")
        val dnsLogs = values[7] as List<DnsLogEntity>
        @Suppress("UNCHECKED_CAST")
        val wgConfigs = values[8] as List<com.celzero.bravedns.data.db.WireGuardConfigEntity>
        @Suppress("UNCHECKED_CAST")
        val proxyConfigs = values[9] as List<com.celzero.bravedns.data.db.ProxyConfigEntity>

        val now = System.currentTimeMillis()
        val allowedBuckets = buildHeatmapBuckets(dnsLogs, blocked = false, nowMs = now)
        val blockedBuckets = buildHeatmapBuckets(dnsLogs, blocked = true, nowMs = now)

        HomeUiState(
            settings = settings,
            vpnStatus = vpnStatus,
            blockedAppsCount = blockedApps,
            allowedAppsCount = allowedApps,
            customDomainRulesCount = domainCount,
            customIpRulesCount = ipCount,
            selectedBlocklistsCount = blocklistCount,
            totalDnsQueries = dnsLogs.size,
            blockedDnsQueries = dnsLogs.count { it.isBlocked },
            allowedHeatmap = allowedBuckets,
            blockedHeatmap = blockedBuckets,
            activeWgCount = wgConfigs.count { it.isActive },
            activeProxyCount = proxyConfigs.count { it.isActive }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

    fun setBraveMode(mode: BraveMode) {
        viewModelScope.launch {
            repository.settings.setBraveMode(mode)
        }
    }

    fun dismissOnboarding() {
        viewModelScope.launch {
            repository.settings.setOnboardingSeen(true)
        }
    }

    fun runLiveDnsProbe(domain: String = "rethinkdns.com") {
        viewModelScope.launch {
            _probeState.value = ProbeUiState.Probing
            val settings = repository.settings.current()
            val probeResult = DohClient.probeDomain(settings.activeResolverUrl, domain)
            _probeState.value = ProbeUiState.Result(probeResult)

            if (settings.logsEnabled) {
                repository.db.dnsLogDao().insert(
                    DnsLogEntity(
                        appName = "Rethink Probe",
                        packageName = "com.celzero.bravedns",
                        queryStr = probeResult.domain,
                        typeName = "A",
                        resolverName = settings.activeResolverName,
                        latencyMs = probeResult.latencyMs,
                        isBlocked = !probeResult.success,
                        blockedReason = if (probeResult.success) "" else (probeResult.errorMessage ?: "Probe failed"),
                        responseIps = probeResult.resolvedIps.joinToString(", "),
                        status = if (probeResult.success) "COMPLETE" else "ERROR"
                    )
                )
                repository.db.connectionLogDao().insert(
                    ConnectionLogEntity(
                        appName = "Rethink Probe",
                        packageName = "com.celzero.bravedns",
                        ipAddress = probeResult.resolvedIps.firstOrNull() ?: "1.1.1.1",
                        port = 443,
                        protocol = "DoH",
                        isBlocked = !probeResult.success,
                        blockedByRule = if (probeResult.success) "No Rule" else "Probe Error",
                        dnsQuery = probeResult.domain,
                        downloadBytes = 512L,
                        uploadBytes = 128L
                    )
                )
            }
        }
    }

    fun resetProbeState() {
        _probeState.value = ProbeUiState.Idle
    }

    private fun buildHeatmapBuckets(
        logs: List<DnsLogEntity>,
        blocked: Boolean,
        nowMs: Long
    ): List<Int> {
        val hourMs = 3_600_000L
        val counts = IntArray(144)
        for (log in logs) {
            if (log.isBlocked != blocked) continue
            val hoursAgo = ((nowMs - log.timeMs) / hourMs).toInt()
            if (hoursAgo in 0 until 144) {
                val idx = 143 - hoursAgo
                counts[idx]++
            }
        }
        val maxVal = counts.maxOrNull()?.coerceAtLeast(1) ?: 1
        return counts.map { c ->
            when {
                c <= 0 -> 0
                c * 4 <= maxVal -> 1
                c * 2 <= maxVal -> 2
                c * 4 <= maxVal * 3 -> 3
                else -> 4
            }
        }
    }
}
