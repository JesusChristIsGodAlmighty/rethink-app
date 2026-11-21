package com.celzero.bravedns.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celzero.bravedns.data.RethinkRepository
import com.celzero.bravedns.data.db.ConnectionLogEntity
import com.celzero.bravedns.data.db.CustomDomainEntity
import com.celzero.bravedns.data.db.CustomIpEntity
import com.celzero.bravedns.data.db.DnsLogEntity
import com.celzero.bravedns.data.db.RankedStatRow
import com.celzero.bravedns.model.RuleStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class StatsTimeWindow(val label: String, val durationMs: Long) {
    ONE_HOUR("1h", 3_600_000L),
    TWENTY_FOUR_HOURS("24h", 86_400_000L),
    SEVEN_DAYS("7d", 7 * 86_400_000L)
}

enum class StatsCategory(val label: String) {
    ALLOWED_DOMAINS("Top Allowed Domains"),
    BLOCKED_DOMAINS("Top Blocked Domains"),
    ALLOWED_APPS("Most Connected Apps"),
    BLOCKED_APPS("Most Blocked Apps"),
    ALLOWED_IPS("Top Contacted IPs"),
    BLOCKED_IPS("Top Blocked IPs")
}

data class StatsOverview(
    val totalDns: Int = 0,
    val blockedDns: Int = 0,
    val totalConns: Int = 0,
    val blockedConns: Int = 0,
    val avgLatencyMs: Long = 0L,
    val rankedRows: List<RankedStatRow> = emptyList()
)

@OptIn(ExperimentalCoroutinesApi::class)
class LogsStatsViewModel(
    private val repository: RethinkRepository
) : ViewModel() {

    private val _timeWindow = MutableStateFlow(StatsTimeWindow.TWENTY_FOUR_HOURS)
    val timeWindow: StateFlow<StatsTimeWindow> = _timeWindow.asStateFlow()

    private val _category = MutableStateFlow(StatsCategory.ALLOWED_DOMAINS)
    val category: StateFlow<StatsCategory> = _category.asStateFlow()

    val dnsLogs: StateFlow<List<DnsLogEntity>> = repository.dnsLogsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val connectionLogs: StateFlow<List<ConnectionLogEntity>> = repository.connectionLogsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val statsOverview: StateFlow<StatsOverview> = combine(_timeWindow, _category) { window, cat ->
        Pair(System.currentTimeMillis() - window.durationMs, cat)
    }.flatMapLatest { (sinceMs, cat) ->
        val rankedFlow = when (cat) {
            StatsCategory.ALLOWED_DOMAINS -> repository.db.dnsLogDao().observeTopDomains(sinceMs, blocked = false)
            StatsCategory.BLOCKED_DOMAINS -> repository.db.dnsLogDao().observeTopDomains(sinceMs, blocked = true)
            StatsCategory.ALLOWED_APPS -> repository.db.connectionLogDao().observeTopApps(sinceMs, blocked = false)
            StatsCategory.BLOCKED_APPS -> repository.db.connectionLogDao().observeTopApps(sinceMs, blocked = true)
            StatsCategory.ALLOWED_IPS -> repository.db.connectionLogDao().observeTopIps(sinceMs, blocked = false)
            StatsCategory.BLOCKED_IPS -> repository.db.connectionLogDao().observeTopIps(sinceMs, blocked = true)
        }
        combine(
            repository.db.dnsLogDao().observeSince(sinceMs),
            repository.db.connectionLogDao().observeSince(sinceMs),
            rankedFlow
        ) { dLogs, cLogs, ranked ->
            val allowedLatencies = dLogs.filter { !it.isBlocked && it.latencyMs > 0 }.map { it.latencyMs }
            val avgLatency = if (allowedLatencies.isNotEmpty()) allowedLatencies.average().toLong() else 0L
            StatsOverview(
                totalDns = dLogs.size,
                blockedDns = dLogs.count { it.isBlocked },
                totalConns = cLogs.size,
                blockedConns = cLogs.count { it.isBlocked },
                avgLatencyMs = avgLatency,
                rankedRows = ranked
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), StatsOverview())

    fun setTimeWindow(window: StatsTimeWindow) {
        _timeWindow.value = window
    }

    fun setCategory(category: StatsCategory) {
        _category.value = category
    }

    fun setDomainRule(domain: String, status: RuleStatus) {
        val clean = domain.trim().lowercase().trimEnd('.')
        if (clean.isEmpty()) return
        viewModelScope.launch {
            repository.db.customDomainDao().upsert(
                CustomDomainEntity(
                    domain = clean,
                    uid = -1000,
                    status = status.id,
                    isWildcard = false
                )
            )
        }
    }

    fun setIpRule(ip: String, status: RuleStatus) {
        val clean = ip.trim()
        if (clean.isEmpty()) return
        viewModelScope.launch {
            repository.db.customIpDao().upsert(
                CustomIpEntity(
                    uid = -1000,
                    ipAddress = clean,
                    port = 0,
                    protocol = "ANY",
                    status = status.id
                )
            )
        }
    }

    fun clearAllLogs() {
        viewModelScope.launch {
            repository.db.dnsLogDao().clearAll()
            repository.db.connectionLogDao().clearAll()
        }
    }
}
