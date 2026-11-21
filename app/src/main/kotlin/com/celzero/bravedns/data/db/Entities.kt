package com.celzero.bravedns.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.celzero.bravedns.model.AppConnectionStatus
import com.celzero.bravedns.model.AppFirewallStatus
import com.celzero.bravedns.model.DnsProtocolType
import com.celzero.bravedns.model.RuleStatus

@Entity(tableName = "app_info", primaryKeys = ["uid", "packageName"])
data class AppInfoEntity(
    val uid: Int,
    val packageName: String,
    val appName: String,
    val isSystemApp: Boolean = false,
    val appCategory: String = "App",
    val firewallStatus: Int = AppFirewallStatus.NONE.id,
    val connectionStatus: Int = AppConnectionStatus.ALLOW.id,
    val screenOffAllowed: Boolean = true,
    val backgroundAllowed: Boolean = true,
    val wifiDataBytes: Long = 0L,
    val mobileDataBytes: Long = 0L,
    val modifiedTs: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "dns_logs",
    indices = [
        Index(value = ["queryStr"]),
        Index(value = ["timeMs"]),
        Index(value = ["isBlocked"])
    ]
)
data class DnsLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val uid: Int = -1,
    val appName: String = "System",
    val packageName: String = "",
    val queryStr: String,
    val typeName: String = "A",
    val timeMs: Long = System.currentTimeMillis(),
    val resolverName: String,
    val latencyMs: Long = 0L,
    val isBlocked: Boolean = false,
    val blockedReason: String = "",
    val blocklists: String = "",
    val responseIps: String = "",
    val status: String = "COMPLETE",
    val isCached: Boolean = false
)

@Entity(
    tableName = "connection_logs",
    indices = [
        Index(value = ["ipAddress"]),
        Index(value = ["timeStampMs"]),
        Index(value = ["isBlocked"])
    ]
)
data class ConnectionLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val uid: Int = -1,
    val appName: String = "System",
    val packageName: String = "",
    val ipAddress: String,
    val port: Int,
    val protocol: String = "UDP",
    val isBlocked: Boolean = false,
    val blockedByRule: String = "",
    val dnsQuery: String = "",
    val timeStampMs: Long = System.currentTimeMillis(),
    val downloadBytes: Long = 0L,
    val uploadBytes: Long = 0L,
    val connType: String = "Unmetered",
    val flag: String = ""
)

@Entity(tableName = "custom_domains", primaryKeys = ["domain", "uid"])
data class CustomDomainEntity(
    val domain: String,
    val uid: Int = -1000,
    val status: Int = RuleStatus.BLOCK.id,
    val isWildcard: Boolean = false,
    val modifiedTs: Long = System.currentTimeMillis()
)

@Entity(tableName = "custom_ips", primaryKeys = ["uid", "ipAddress", "port", "protocol"])
data class CustomIpEntity(
    val uid: Int = -1000,
    val ipAddress: String,
    val port: Int = 0,
    val protocol: String = "ANY",
    val status: Int = RuleStatus.BLOCK.id,
    val isActive: Boolean = true,
    val modifiedTs: Long = System.currentTimeMillis()
)

@Entity(tableName = "dns_endpoints")
data class DnsEndpointEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val url: String,
    val protocolType: Int = DnsProtocolType.DOH.id,
    val description: String = "",
    val isSelected: Boolean = false,
    val isCustom: Boolean = false,
    val latencyMs: Long = -1L
)

@Entity(tableName = "blocklist_tags")
data class BlocklistTagEntity(
    @PrimaryKey val id: String,
    val uname: String,
    val vname: String,
    val group: String,
    val subgroup: String,
    val url: String,
    val entriesCount: Int,
    val isSelected: Boolean = false
)

@Entity(tableName = "wireguard_configs")
data class WireGuardConfigEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val interfaceAddress: String,
    val privateKeyMasked: String,
    val dnsServers: String,
    val mtu: Int = 1280,
    val peerEndpoint: String,
    val peerPublicKey: String,
    val allowedIps: String,
    val isActive: Boolean = false,
    val rawConfig: String
)

@Entity(tableName = "proxy_configs")
data class ProxyConfigEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val mode: String,
    val host: String,
    val port: Int,
    val username: String = "",
    val isActive: Boolean = false
)
