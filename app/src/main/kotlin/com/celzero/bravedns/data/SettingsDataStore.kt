package com.celzero.bravedns.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.celzero.bravedns.model.BraveMode
import com.celzero.bravedns.model.DialStrategy
import com.celzero.bravedns.model.DnsProtocolType
import com.celzero.bravedns.model.IpProtocolMode
import com.celzero.bravedns.model.ProxyMode
import com.celzero.bravedns.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "rethink_settings")

data class AppSettings(
    val braveMode: BraveMode = BraveMode.DNS_FIREWALL,
    val dnsType: DnsProtocolType = DnsProtocolType.DOH,
    val activeResolverName: String = "Rethink Sky",
    val activeResolverUrl: String = "https://sky.rethinkdns.com/rs",
    val proxyMode: ProxyMode = ProxyMode.NONE,
    val themeMode: ThemeMode = ThemeMode.DARK,
    val logsEnabled: Boolean = true,
    val logLifespanDays: Int = 7,
    val blockWhenLocked: Boolean = false,
    val blockBackground: Boolean = false,
    val blockMetered: Boolean = false,
    val blockUnknown: Boolean = false,
    val blockUdpExceptDns: Boolean = false,
    val universalLockdown: Boolean = false,
    val blockHttp: Boolean = false,
    val preventDnsLeaks: Boolean = true,
    val ipProtocolMode: IpProtocolMode = IpProtocolMode.IPV4_IPV6,
    val mtu: Int = 1280,
    val allowBypass: Boolean = false,
    val customLanGateway: String = "10.111.222.1/24",
    val customLanDns: String = "10.111.222.3",
    val connectivityChecks: Boolean = true,
    val dialStrategy: DialStrategy = DialStrategy.SPLIT_AUTO,
    val dialTimeoutSec: Int = 10,
    val onboardingSeen: Boolean = false
)

class SettingsDataStore(private val context: Context) {

    private object Keys {
        val BRAVE_MODE = intPreferencesKey("brave_mode")
        val DNS_TYPE = intPreferencesKey("dns_type")
        val RESOLVER_NAME = stringPreferencesKey("resolver_name")
        val RESOLVER_URL = stringPreferencesKey("resolver_url")
        val PROXY_MODE = stringPreferencesKey("proxy_mode")
        val THEME_MODE = intPreferencesKey("theme_mode")
        val LOGS_ENABLED = booleanPreferencesKey("logs_enabled")
        val LOG_LIFESPAN_DAYS = intPreferencesKey("log_lifespan_days")
        val BLOCK_WHEN_LOCKED = booleanPreferencesKey("block_when_locked")
        val BLOCK_BACKGROUND = booleanPreferencesKey("block_background")
        val BLOCK_METERED = booleanPreferencesKey("block_metered")
        val BLOCK_UNKNOWN = booleanPreferencesKey("block_unknown")
        val BLOCK_UDP_EXCEPT_DNS = booleanPreferencesKey("block_udp_except_dns")
        val UNIVERSAL_LOCKDOWN = booleanPreferencesKey("universal_lockdown")
        val BLOCK_HTTP = booleanPreferencesKey("block_http")
        val PREVENT_DNS_LEAKS = booleanPreferencesKey("prevent_dns_leaks")
        val IP_PROTOCOL = intPreferencesKey("ip_protocol")
        val MTU = intPreferencesKey("mtu")
        val ALLOW_BYPASS = booleanPreferencesKey("allow_bypass")
        val CUSTOM_LAN_GATEWAY = stringPreferencesKey("custom_lan_gateway")
        val CUSTOM_LAN_DNS = stringPreferencesKey("custom_lan_dns")
        val CONNECTIVITY_CHECKS = booleanPreferencesKey("connectivity_checks")
        val DIAL_STRATEGY = intPreferencesKey("dial_strategy")
        val DIAL_TIMEOUT_SEC = intPreferencesKey("dial_timeout_sec")
        val ONBOARDING_SEEN = booleanPreferencesKey("onboarding_seen")
    }

    val settingsFlow: Flow<AppSettings> = context.dataStore.data.map { prefs ->
        AppSettings(
            braveMode = BraveMode.fromId(prefs[Keys.BRAVE_MODE] ?: BraveMode.DNS_FIREWALL.id),
            dnsType = DnsProtocolType.fromId(prefs[Keys.DNS_TYPE] ?: DnsProtocolType.DOH.id),
            activeResolverName = prefs[Keys.RESOLVER_NAME] ?: "Rethink Sky",
            activeResolverUrl = prefs[Keys.RESOLVER_URL] ?: "https://sky.rethinkdns.com/rs",
            proxyMode = ProxyMode.fromId(prefs[Keys.PROXY_MODE] ?: ProxyMode.NONE.id),
            themeMode = ThemeMode.fromId(prefs[Keys.THEME_MODE] ?: ThemeMode.DARK.id),
            logsEnabled = prefs[Keys.LOGS_ENABLED] ?: true,
            logLifespanDays = prefs[Keys.LOG_LIFESPAN_DAYS] ?: 7,
            blockWhenLocked = prefs[Keys.BLOCK_WHEN_LOCKED] ?: false,
            blockBackground = prefs[Keys.BLOCK_BACKGROUND] ?: false,
            blockMetered = prefs[Keys.BLOCK_METERED] ?: false,
            blockUnknown = prefs[Keys.BLOCK_UNKNOWN] ?: false,
            blockUdpExceptDns = prefs[Keys.BLOCK_UDP_EXCEPT_DNS] ?: false,
            universalLockdown = prefs[Keys.UNIVERSAL_LOCKDOWN] ?: false,
            blockHttp = prefs[Keys.BLOCK_HTTP] ?: false,
            preventDnsLeaks = prefs[Keys.PREVENT_DNS_LEAKS] ?: true,
            ipProtocolMode = IpProtocolMode.fromId(prefs[Keys.IP_PROTOCOL] ?: IpProtocolMode.IPV4_IPV6.id),
            mtu = prefs[Keys.MTU] ?: 1280,
            allowBypass = prefs[Keys.ALLOW_BYPASS] ?: false,
            customLanGateway = prefs[Keys.CUSTOM_LAN_GATEWAY] ?: "10.111.222.1/24",
            customLanDns = prefs[Keys.CUSTOM_LAN_DNS] ?: "10.111.222.3",
            connectivityChecks = prefs[Keys.CONNECTIVITY_CHECKS] ?: true,
            dialStrategy = DialStrategy.fromId(prefs[Keys.DIAL_STRATEGY] ?: DialStrategy.SPLIT_AUTO.id),
            dialTimeoutSec = prefs[Keys.DIAL_TIMEOUT_SEC] ?: 10,
            onboardingSeen = prefs[Keys.ONBOARDING_SEEN] ?: false
        )
    }

    suspend fun current(): AppSettings = settingsFlow.first()

    suspend fun setBraveMode(mode: BraveMode) {
        context.dataStore.edit { it[Keys.BRAVE_MODE] = mode.id }
    }

    suspend fun setActiveResolver(name: String, url: String, type: DnsProtocolType) {
        context.dataStore.edit {
            it[Keys.RESOLVER_NAME] = name
            it[Keys.RESOLVER_URL] = url
            it[Keys.DNS_TYPE] = type.id
        }
    }

    suspend fun setDnsType(type: DnsProtocolType) {
        context.dataStore.edit { it[Keys.DNS_TYPE] = type.id }
    }

    suspend fun setProxyMode(mode: ProxyMode) {
        context.dataStore.edit { it[Keys.PROXY_MODE] = mode.id }
    }

    suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit { it[Keys.THEME_MODE] = mode.id }
    }

    suspend fun setOnboardingSeen(seen: Boolean) {
        context.dataStore.edit { it[Keys.ONBOARDING_SEEN] = seen }
    }

    suspend fun updateFirewallFlag(keyName: String, value: Boolean) {
        context.dataStore.edit { prefs ->
            when (keyName) {
                "blockWhenLocked" -> prefs[Keys.BLOCK_WHEN_LOCKED] = value
                "blockBackground" -> prefs[Keys.BLOCK_BACKGROUND] = value
                "blockMetered" -> prefs[Keys.BLOCK_METERED] = value
                "blockUnknown" -> prefs[Keys.BLOCK_UNKNOWN] = value
                "blockUdpExceptDns" -> prefs[Keys.BLOCK_UDP_EXCEPT_DNS] = value
                "universalLockdown" -> prefs[Keys.UNIVERSAL_LOCKDOWN] = value
                "blockHttp" -> prefs[Keys.BLOCK_HTTP] = value
                "preventDnsLeaks" -> prefs[Keys.PREVENT_DNS_LEAKS] = value
                "logsEnabled" -> prefs[Keys.LOGS_ENABLED] = value
                "allowBypass" -> prefs[Keys.ALLOW_BYPASS] = value
                "connectivityChecks" -> prefs[Keys.CONNECTIVITY_CHECKS] = value
            }
        }
    }

    suspend fun setTunnelSettings(
        ipMode: IpProtocolMode,
        mtu: Int,
        gateway: String,
        dns: String
    ) {
        context.dataStore.edit {
            it[Keys.IP_PROTOCOL] = ipMode.id
            it[Keys.MTU] = mtu.coerceIn(1200, 1500)
            it[Keys.CUSTOM_LAN_GATEWAY] = gateway
            it[Keys.CUSTOM_LAN_DNS] = dns
        }
    }

    suspend fun setAntiCensorship(strategy: DialStrategy, timeoutSec: Int) {
        context.dataStore.edit {
            it[Keys.DIAL_STRATEGY] = strategy.id
            it[Keys.DIAL_TIMEOUT_SEC] = timeoutSec.coerceIn(3, 60)
        }
    }

    suspend fun setLogLifespanDays(days: Int) {
        context.dataStore.edit { it[Keys.LOG_LIFESPAN_DAYS] = days }
    }
}
