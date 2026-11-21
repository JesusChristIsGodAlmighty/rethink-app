package com.celzero.bravedns.data

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.TrafficStats
import android.os.Build
import com.celzero.bravedns.data.db.AppInfoEntity
import com.celzero.bravedns.data.db.BlocklistTagEntity
import com.celzero.bravedns.data.db.CustomDomainEntity
import com.celzero.bravedns.data.db.CustomIpEntity
import com.celzero.bravedns.data.db.DnsEndpointEntity
import com.celzero.bravedns.data.db.ProxyConfigEntity
import com.celzero.bravedns.data.db.RethinkDatabase
import com.celzero.bravedns.data.db.WireGuardConfigEntity
import com.celzero.bravedns.model.BackupDnsEndpoint
import com.celzero.bravedns.model.BackupDomainRule
import com.celzero.bravedns.model.BackupIpRule
import com.celzero.bravedns.model.BackupPayload
import com.celzero.bravedns.model.BraveMode
import com.celzero.bravedns.model.DnsProtocolType
import com.celzero.bravedns.vpn.DohClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class RethinkRepository(
    private val context: Context,
    val db: RethinkDatabase,
    val settings: SettingsDataStore
) {
    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    val appsFlow: Flow<List<AppInfoEntity>> = db.appInfoDao().observeAll()
    val blockedAppCountFlow: Flow<Int> = db.appInfoDao().observeBlockedAppCount()
    val allowedAppCountFlow: Flow<Int> = db.appInfoDao().observeAllowedAppCount()
    val dnsLogsFlow = db.dnsLogDao().observeRecent()
    val dnsBlockedCountFlow = db.dnsLogDao().observeBlockedCount()
    val dnsTotalCountFlow = db.dnsLogDao().observeTotalCount()
    val connectionLogsFlow = db.connectionLogDao().observeRecent()
    val customDomainsFlow = db.customDomainDao().observeAll()
    val customDomainCountFlow = db.customDomainDao().observeCount()
    val customIpsFlow = db.customIpDao().observeAll()
    val customIpCountFlow = db.customIpDao().observeCount()
    val dnsEndpointsFlow = db.dnsEndpointDao().observeAll()
    val blocklistTagsFlow = db.blocklistTagDao().observeAll()
    val selectedBlocklistCountFlow = db.blocklistTagDao().observeSelectedCount()
    val wireGuardConfigsFlow = db.wireGuardConfigDao().observeAll()
    val proxyConfigsFlow = db.proxyConfigDao().observeAll()

    suspend fun initializeDefaults() = withContext(Dispatchers.IO) {
        seedDnsEndpointsIfEmpty()
        seedBlocklistTagsIfEmpty()
        syncInstalledApps()
    }

    private suspend fun seedDnsEndpointsIfEmpty() {
        if (db.dnsEndpointDao().getAllOnce().isNotEmpty()) return
        val defaults = listOf(
            DnsEndpointEntity(
                name = "Rethink Sky",
                url = "https://sky.rethinkdns.com/rs",
                protocolType = DnsProtocolType.DOH.id,
                description = "Default RethinkDNS resolver with Cloudflare Workers edge",
                isSelected = true
            ),
            DnsEndpointEntity(
                name = "Rethink Max",
                url = "https://max.rethinkdns.com/rs",
                protocolType = DnsProtocolType.DOH.id,
                description = "RethinkDNS Fly.io edge with malware & tracker filters"
            ),
            DnsEndpointEntity(
                name = "Cloudflare",
                url = "https://cloudflare-dns.com/dns-query",
                protocolType = DnsProtocolType.DOH.id,
                description = "Fast privacy-first 1.1.1.1 resolver"
            ),
            DnsEndpointEntity(
                name = "Quad9",
                url = "https://dns.quad9.net/dns-query",
                protocolType = DnsProtocolType.DOH.id,
                description = "Security-focused threat blocking & DNSSEC"
            ),
            DnsEndpointEntity(
                name = "AdGuard DNS",
                url = "https://dns.adguard-dns.com/dns-query",
                protocolType = DnsProtocolType.DOH.id,
                description = "Blocks ads, trackers, and analytics domains"
            ),
            DnsEndpointEntity(
                name = "Mullvad",
                url = "https://dns.mullvad.net/dns-query",
                protocolType = DnsProtocolType.DOH.id,
                description = "No-logging encrypted DNS by Mullvad"
            ),
            DnsEndpointEntity(
                name = "Google DoH",
                url = "https://dns.google/dns-query",
                protocolType = DnsProtocolType.DOH.id,
                description = "Global anycast DNS over HTTPS"
            ),
            DnsEndpointEntity(
                name = "Cloudflare DoT",
                url = "tls://one.one.one.one",
                protocolType = DnsProtocolType.DOT.id,
                description = "DNS over TLS on port 853"
            ),
            DnsEndpointEntity(
                name = "Quad9 DoT",
                url = "tls://dns.quad9.net",
                protocolType = DnsProtocolType.DOT.id,
                description = "DNS over TLS with malware filtering"
            ),
            DnsEndpointEntity(
                name = "Quad9 DNSCrypt",
                url = "sdns://AQMAAAAAAAAAEzE0OS4xMTIuMTEyLjExMjo4NDQz",
                protocolType = DnsProtocolType.DNSCRYPT.id,
                description = "DNSCrypt v2 authenticated resolver"
            ),
            DnsEndpointEntity(
                name = "Cloudflare ODoH",
                url = "https://odoh.cloudflare-dns.com/dns-query",
                protocolType = DnsProtocolType.ODOH.id,
                description = "Oblivious DoH decoupling client IP from queries"
            )
        )
        db.dnsEndpointDao().insertAll(defaults)
    }

    private suspend fun seedBlocklistTagsIfEmpty() {
        if (db.blocklistTagDao().countOnce() > 0) return
        val rawJson = runCatching {
            context.assets.open("filetag.json").bufferedReader().use { it.readText() }
        }.getOrNull() ?: return

        val tags = mutableListOf<BlocklistTagEntity>()
        runCatching {
            val rootObj = json.parseToJsonElement(rawJson).jsonObject
            for ((key, value) in rootObj.entries) {
                val obj = value.jsonObject
                val vname = obj["vname"]?.jsonPrimitive?.content.orEmpty()
                val uname = obj["uname"]?.jsonPrimitive?.content ?: key
                val group = obj["group"]?.jsonPrimitive?.content.orEmpty().ifBlank { "other" }
                val subg = obj["subg"]?.jsonPrimitive?.content.orEmpty()
                val url = obj["url"]?.jsonPrimitive?.content.orEmpty()
                val entries = obj["entries"]?.jsonPrimitive?.intOrNull ?: 0
                if (vname.isNotBlank()) {
                    tags.add(
                        BlocklistTagEntity(
                            id = key,
                            uname = uname,
                            vname = vname,
                            group = group,
                            subgroup = subg,
                            url = url,
                            entriesCount = entries,
                            isSelected = key in setOf("OHE", "THG", "IKY")
                        )
                    )
                }
            }
        }
        if (tags.isNotEmpty()) {
            db.blocklistTagDao().insertAll(tags)
        }
    }

    suspend fun syncInstalledApps() = withContext(Dispatchers.IO) {
        val pm = context.packageManager
        val existing = db.appInfoDao().getAllOnce().associateBy { it.uid to it.packageName }
        val launcherIntent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfos = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pm.queryIntentActivities(launcherIntent, PackageManager.ResolveInfoFlags.of(0L))
        } else {
            @Suppress("DEPRECATION")
            pm.queryIntentActivities(launcherIntent, 0)
        }

        val appsToUpsert = resolveInfos
            .mapNotNull { it.activityInfo?.applicationInfo }
            .distinctBy { it.uid to it.packageName }
            .map { info ->
                val prev = existing[info.uid to info.packageName]
                val label = pm.getApplicationLabel(info).toString()
                val isSys = (info.flags and ApplicationInfo.FLAG_SYSTEM) != 0
                val category = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ApplicationInfo.getCategoryTitle(context, info.category)?.toString() ?: "Utility"
                } else {
                    "Utility"
                }
                val rx = TrafficStats.getUidRxBytes(info.uid).coerceAtLeast(0L)
                val tx = TrafficStats.getUidTxBytes(info.uid).coerceAtLeast(0L)
                val totalBytes = rx + tx

                AppInfoEntity(
                    uid = info.uid,
                    packageName = info.packageName,
                    appName = label,
                    isSystemApp = isSys,
                    appCategory = category,
                    firewallStatus = prev?.firewallStatus ?: com.celzero.bravedns.model.AppFirewallStatus.NONE.id,
                    connectionStatus = prev?.connectionStatus ?: com.celzero.bravedns.model.AppConnectionStatus.ALLOW.id,
                    screenOffAllowed = prev?.screenOffAllowed ?: true,
                    backgroundAllowed = prev?.backgroundAllowed ?: true,
                    wifiDataBytes = if (totalBytes > 0) totalBytes else (prev?.wifiDataBytes ?: 0L),
                    mobileDataBytes = prev?.mobileDataBytes ?: 0L
                )
            }

        if (appsToUpsert.isNotEmpty()) {
            db.appInfoDao().upsertAll(appsToUpsert)
        }
    }

    suspend fun selectDnsEndpoint(endpoint: DnsEndpointEntity) = withContext(Dispatchers.IO) {
        db.dnsEndpointDao().clearSelection()
        db.dnsEndpointDao().selectById(endpoint.id)
        settings.setActiveResolver(
            name = endpoint.name,
            url = endpoint.url,
            type = DnsProtocolType.fromId(endpoint.protocolType)
        )
    }

    suspend fun probeAndSaveEndpointLatency(endpoint: DnsEndpointEntity): Long {
        if (endpoint.protocolType != DnsProtocolType.DOH.id) return -1L
        val result = DohClient.probeDomain(endpoint.url, "example.com")
        val latency = if (result.success) result.latencyMs else -1L
        db.dnsEndpointDao().updateLatency(endpoint.id, latency)
        return latency
    }

    suspend fun addCustomDnsEndpoint(name: String, url: String, type: DnsProtocolType) {
        val endpoint = DnsEndpointEntity(
            name = name.trim(),
            url = url.trim(),
            protocolType = type.id,
            description = "Custom ${type.shortLabel} endpoint",
            isCustom = true
        )
        db.dnsEndpointDao().insert(endpoint)
    }

    suspend fun exportBackupJson(): String = withContext(Dispatchers.IO) {
        val s = settings.current()
        val domains = db.customDomainDao().getAllOnce().map {
            BackupDomainRule(it.domain, it.uid, it.status)
        }
        val ips = db.customIpDao().getAllOnce().map {
            BackupIpRule(it.ipAddress, it.port, it.protocol, it.uid, it.status)
        }
        val customEndpoints = db.dnsEndpointDao().getAllOnce()
            .filter { it.isCustom }
            .map { BackupDnsEndpoint(it.name, it.url, it.protocolType, it.description) }
        val selectedTags = db.blocklistTagDao().getSelectedOnce().map { it.id }

        val payload = BackupPayload(
            exportedAtMs = System.currentTimeMillis(),
            braveMode = s.braveMode.id,
            dnsType = s.dnsType.id,
            activeResolverName = s.activeResolverName,
            activeResolverUrl = s.activeResolverUrl,
            universalLockdown = s.universalLockdown,
            blockWhenLocked = s.blockWhenLocked,
            blockBackground = s.blockBackground,
            blockMetered = s.blockMetered,
            blockUnknown = s.blockUnknown,
            blockUdpExceptDns = s.blockUdpExceptDns,
            blockHttp = s.blockHttp,
            preventDnsLeaks = s.preventDnsLeaks,
            customDomains = domains,
            customIps = ips,
            customDnsEndpoints = customEndpoints,
            selectedBlocklistIds = selectedTags
        )
        json.encodeToString(payload)
    }

    suspend fun importBackupJson(rawJson: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val payload = json.decodeFromString<BackupPayload>(rawJson)
            settings.setBraveMode(BraveMode.fromId(payload.braveMode))
            settings.setActiveResolver(
                payload.activeResolverName,
                payload.activeResolverUrl,
                DnsProtocolType.fromId(payload.dnsType)
            )
            settings.updateFirewallFlag("universalLockdown", payload.universalLockdown)
            settings.updateFirewallFlag("blockWhenLocked", payload.blockWhenLocked)
            settings.updateFirewallFlag("blockBackground", payload.blockBackground)
            settings.updateFirewallFlag("blockMetered", payload.blockMetered)
            settings.updateFirewallFlag("blockUnknown", payload.blockUnknown)
            settings.updateFirewallFlag("blockUdpExceptDns", payload.blockUdpExceptDns)
            settings.updateFirewallFlag("blockHttp", payload.blockHttp)
            settings.updateFirewallFlag("preventDnsLeaks", payload.preventDnsLeaks)

            if (payload.customDomains.isNotEmpty()) {
                db.customDomainDao().upsertAll(
                    payload.customDomains.map {
                        CustomDomainEntity(
                            domain = it.domain,
                            uid = it.uid,
                            status = it.status,
                            isWildcard = it.domain.startsWith("*.")
                        )
                    }
                )
            }
            if (payload.customIps.isNotEmpty()) {
                db.customIpDao().upsertAll(
                    payload.customIps.map {
                        CustomIpEntity(
                            uid = it.uid,
                            ipAddress = it.ipAddress,
                            port = it.port,
                            protocol = it.protocol,
                            status = it.status
                        )
                    }
                )
            }
            payload.customDnsEndpoints.forEach {
                db.dnsEndpointDao().insert(
                    DnsEndpointEntity(
                        name = it.name,
                        url = it.url,
                        protocolType = it.protocolType,
                        description = it.description,
                        isCustom = true
                    )
                )
            }
            db.blocklistTagDao().clearAllSelections()
            payload.selectedBlocklistIds.forEach { id ->
                db.blocklistTagDao().setSelected(id, true)
            }
        }
    }

    suspend fun insertWireGuardConfig(config: WireGuardConfigEntity) {
        db.wireGuardConfigDao().insert(config)
    }

    suspend fun insertProxyConfig(config: ProxyConfigEntity) {
        db.proxyConfigDao().insert(config)
    }
}
