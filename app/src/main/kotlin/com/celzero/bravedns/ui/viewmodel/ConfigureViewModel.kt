package com.celzero.bravedns.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celzero.bravedns.data.AppSettings
import com.celzero.bravedns.data.RethinkRepository
import com.celzero.bravedns.data.db.AppInfoEntity
import com.celzero.bravedns.data.db.BlocklistTagEntity
import com.celzero.bravedns.data.db.CustomDomainEntity
import com.celzero.bravedns.data.db.CustomIpEntity
import com.celzero.bravedns.data.db.DnsEndpointEntity
import com.celzero.bravedns.data.db.ProxyConfigEntity
import com.celzero.bravedns.data.db.WireGuardConfigEntity
import com.celzero.bravedns.model.AppConnectionStatus
import com.celzero.bravedns.model.AppFirewallStatus
import com.celzero.bravedns.model.DialStrategy
import com.celzero.bravedns.model.DnsProtocolType
import com.celzero.bravedns.model.IpProtocolMode
import com.celzero.bravedns.model.ProxyMode
import com.celzero.bravedns.model.RuleStatus
import com.celzero.bravedns.model.ThemeMode
import com.celzero.bravedns.vpn.WireGuardParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConfigureViewModel(
    private val repository: RethinkRepository
) : ViewModel() {

    val settings: StateFlow<AppSettings> = repository.settings.settingsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppSettings())

    val apps: StateFlow<List<AppInfoEntity>> = repository.appsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val dnsEndpoints: StateFlow<List<DnsEndpointEntity>> = repository.dnsEndpointsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val blocklistTags: StateFlow<List<BlocklistTagEntity>> = repository.blocklistTagsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val customDomains: StateFlow<List<CustomDomainEntity>> = repository.customDomainsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val customIps: StateFlow<List<CustomIpEntity>> = repository.customIpsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val wireGuardConfigs: StateFlow<List<WireGuardConfigEntity>> = repository.wireGuardConfigsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val proxyConfigs: StateFlow<List<ProxyConfigEntity>> = repository.proxyConfigsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _statusMessage = MutableStateFlow<String?>(null)
    val statusMessage: StateFlow<String?> = _statusMessage.asStateFlow()

    fun clearStatusMessage() {
        _statusMessage.value = null
    }

    fun refreshInstalledApps() {
        viewModelScope.launch {
            repository.syncInstalledApps()
        }
    }

    fun updateAppConnectionStatus(app: AppInfoEntity, status: AppConnectionStatus) {
        viewModelScope.launch {
            repository.db.appInfoDao().updateConnectionStatus(app.uid, app.packageName, status.id)
        }
    }

    fun updateAppFirewallStatus(app: AppInfoEntity, status: AppFirewallStatus) {
        viewModelScope.launch {
            repository.db.appInfoDao().updateFirewallStatus(app.uid, app.packageName, status.id)
        }
    }

    fun selectDnsEndpoint(endpoint: DnsEndpointEntity) {
        viewModelScope.launch {
            repository.selectDnsEndpoint(endpoint)
        }
    }

    fun probeEndpointLatency(endpoint: DnsEndpointEntity) {
        viewModelScope.launch {
            val ms = repository.probeAndSaveEndpointLatency(endpoint)
            _statusMessage.value = if (ms >= 0) {
                "${endpoint.name} responded in ${ms}ms"
            } else {
                "Probe unavailable for ${endpoint.name}"
            }
        }
    }

    fun addCustomDnsEndpoint(name: String, url: String, type: DnsProtocolType) {
        if (name.isBlank() || url.isBlank()) return
        viewModelScope.launch {
            repository.addCustomDnsEndpoint(name, url, type)
            _statusMessage.value = "Added $name"
        }
    }

    fun deleteCustomDnsEndpoint(id: Int) {
        viewModelScope.launch {
            repository.db.dnsEndpointDao().deleteCustom(id)
        }
    }

    fun toggleBlocklistTag(tag: BlocklistTagEntity) {
        viewModelScope.launch {
            repository.db.blocklistTagDao().setSelected(tag.id, !tag.isSelected)
        }
    }

    fun clearBlocklistSelections() {
        viewModelScope.launch {
            repository.db.blocklistTagDao().clearAllSelections()
        }
    }

    fun addCustomDomainRule(domain: String, status: RuleStatus) {
        val clean = domain.trim().lowercase().trimEnd('.')
        if (clean.isEmpty()) return
        viewModelScope.launch {
            repository.db.customDomainDao().upsert(
                CustomDomainEntity(
                    domain = clean,
                    uid = -1000,
                    status = status.id,
                    isWildcard = clean.startsWith("*.")
                )
            )
            _statusMessage.value = "${status.label}: $clean"
        }
    }

    fun deleteCustomDomainRule(rule: CustomDomainEntity) {
        viewModelScope.launch {
            repository.db.customDomainDao().delete(rule.domain, rule.uid)
        }
    }

    fun addCustomIpRule(ip: String, port: Int, protocol: String, status: RuleStatus) {
        val clean = ip.trim()
        if (clean.isEmpty()) return
        viewModelScope.launch {
            repository.db.customIpDao().upsert(
                CustomIpEntity(
                    uid = -1000,
                    ipAddress = clean,
                    port = port.coerceIn(0, 65535),
                    protocol = protocol,
                    status = status.id
                )
            )
            _statusMessage.value = "${status.label}: $clean"
        }
    }

    fun deleteCustomIpRule(rule: CustomIpEntity) {
        viewModelScope.launch {
            repository.db.customIpDao().delete(rule.uid, rule.ipAddress, rule.port, rule.protocol)
        }
    }

    fun updateFirewallFlag(flagKey: String, value: Boolean) {
        viewModelScope.launch {
            repository.settings.updateFirewallFlag(flagKey, value)
        }
    }

    fun importWireGuardConfig(name: String, rawConf: String) {
        viewModelScope.launch {
            val parsed = WireGuardParser.parse(name, rawConf)
            parsed.onSuccess { entity ->
                repository.insertWireGuardConfig(entity)
                _statusMessage.value = "Imported WireGuard tunnel: ${entity.name}"
            }.onFailure { err ->
                _statusMessage.value = err.message ?: "Invalid WireGuard config"
            }
        }
    }

    fun toggleWireGuardActive(config: WireGuardConfigEntity) {
        viewModelScope.launch {
            val nextActive = !config.isActive
            repository.db.wireGuardConfigDao().setActive(config.id, nextActive)
            repository.settings.setProxyMode(if (nextActive) ProxyMode.WIREGUARD else ProxyMode.NONE)
        }
    }

    fun deleteWireGuardConfig(id: Int) {
        viewModelScope.launch {
            repository.db.wireGuardConfigDao().delete(id)
        }
    }

    fun addProxyConfig(name: String, mode: ProxyMode, host: String, port: Int, username: String) {
        if (host.isBlank()) return
        viewModelScope.launch {
            repository.insertProxyConfig(
                ProxyConfigEntity(
                    name = name.ifBlank { "${mode.label} Proxy" },
                    mode = mode.id,
                    host = host.trim(),
                    port = port.coerceIn(1, 65535),
                    username = username.trim(),
                    isActive = false
                )
            )
            _statusMessage.value = "Saved proxy ${host.trim()}:$port"
        }
    }

    fun toggleProxyActive(proxy: ProxyConfigEntity) {
        viewModelScope.launch {
            val nextActive = !proxy.isActive
            repository.db.proxyConfigDao().setActive(proxy.id, nextActive)
            repository.settings.setProxyMode(
                if (nextActive) ProxyMode.fromId(proxy.mode) else ProxyMode.NONE
            )
        }
    }

    fun deleteProxyConfig(id: Int) {
        viewModelScope.launch {
            repository.db.proxyConfigDao().delete(id)
        }
    }

    fun setProxyMode(mode: ProxyMode) {
        viewModelScope.launch {
            repository.settings.setProxyMode(mode)
        }
    }

    fun setTunnelSettings(ipMode: IpProtocolMode, mtu: Int, gateway: String, dns: String) {
        viewModelScope.launch {
            repository.settings.setTunnelSettings(ipMode, mtu, gateway, dns)
            _statusMessage.value = "Tunnel settings saved"
        }
    }

    fun setAntiCensorship(strategy: DialStrategy, timeoutSec: Int) {
        viewModelScope.launch {
            repository.settings.setAntiCensorship(strategy, timeoutSec)
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            repository.settings.setThemeMode(mode)
        }
    }

    fun setLogLifespanDays(days: Int) {
        viewModelScope.launch {
            repository.settings.setLogLifespanDays(days)
        }
    }

    suspend fun exportBackupJson(): String = repository.exportBackupJson()

    fun importBackupJson(rawJson: String) {
        viewModelScope.launch {
            repository.importBackupJson(rawJson)
                .onSuccess { _statusMessage.value = "Backup restored successfully" }
                .onFailure { _statusMessage.value = "Invalid backup file: ${it.message}" }
        }
    }
}
