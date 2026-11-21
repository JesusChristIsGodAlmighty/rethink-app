package com.celzero.bravedns.model

import kotlinx.serialization.Serializable

enum class BraveMode(val id: Int, val label: String, val subtitle: String) {
    DNS_FIREWALL(2, "DNS + Firewall", "Encrypted DNS filtering & per-app firewall"),
    DNS(0, "DNS Only", "Encrypted DNS resolver without app firewall"),
    FIREWALL(1, "Firewall Only", "Per-app & IP firewall with system DNS");

    val isDnsActive: Boolean get() = this == DNS_FIREWALL || this == DNS
    val isFirewallActive: Boolean get() = this == DNS_FIREWALL || this == FIREWALL

    companion object {
        fun fromId(id: Int): BraveMode = entries.firstOrNull { it.id == id } ?: DNS_FIREWALL
    }
}

enum class DnsProtocolType(val id: Int, val label: String, val shortLabel: String) {
    DOH(1, "DNS over HTTPS", "DoH"),
    DOT(6, "DNS over TLS", "DoT"),
    DNSCRYPT(2, "DNSCrypt", "DNSCrypt"),
    ODOH(7, "Oblivious DoH", "ODoH"),
    SYSTEM_DNS(5, "System DNS", "System");

    companion object {
        fun fromId(id: Int): DnsProtocolType = entries.firstOrNull { it.id == id } ?: DOH
    }
}

enum class ProxyMode(val id: String, val label: String) {
    NONE("NONE", "Direct (No Proxy)"),
    WIREGUARD("WIREGUARD", "WireGuard Tunnel"),
    SOCKS5("SOCKS5", "SOCKS5 Proxy"),
    HTTP("HTTP", "HTTP CONNECT"),
    ORBOT("ORBOT", "Orbot (Tor)");

    companion object {
        fun fromId(id: String): ProxyMode = entries.firstOrNull { it.id == id } ?: NONE
    }
}

enum class AppFirewallStatus(val id: Int, val label: String) {
    NONE(5, "Default"),
    ISOLATE(4, "Isolate"),
    BYPASS_DNS_FIREWALL(7, "Bypass DNS & Firewall"),
    BYPASS_UNIVERSAL(2, "Bypass Universal"),
    EXCLUDE(3, "Exclude from VPN");

    companion object {
        fun fromId(id: Int): AppFirewallStatus = entries.firstOrNull { it.id == id } ?: NONE
    }
}

enum class AppConnectionStatus(val id: Int, val label: String) {
    ALLOW(3, "Allowed"),
    BLOCK_BOTH(0, "Blocked (All)"),
    BLOCK_WIFI(1, "Wi-Fi Blocked"),
    BLOCK_MOBILE(2, "Mobile Blocked");

    val isBlockedAny: Boolean get() = this != ALLOW

    companion object {
        fun fromId(id: Int): AppConnectionStatus = entries.firstOrNull { it.id == id } ?: ALLOW
    }
}

enum class RuleStatus(val id: Int, val label: String) {
    BLOCK(0, "Block"),
    TRUST(1, "Trust"),
    NONE(2, "None");

    companion object {
        fun fromId(id: Int): RuleStatus = entries.firstOrNull { it.id == id } ?: NONE
    }
}

enum class ThemeMode(val id: Int, val label: String) {
    SYSTEM(0, "System Default"),
    DARK(1, "Obsidian Dark"),
    LIGHT(2, "Daylight Light");

    companion object {
        fun fromId(id: Int): ThemeMode = entries.firstOrNull { it.id == id } ?: SYSTEM
    }
}

enum class IpProtocolMode(val id: Int, val label: String) {
    IPV4(0, "IPv4"),
    IPV6(1, "IPv6"),
    IPV4_IPV6(2, "IPv4 + IPv6");

    companion object {
        fun fromId(id: Int): IpProtocolMode = entries.firstOrNull { it.id == id } ?: IPV4_IPV6
    }
}

enum class DialStrategy(val id: Int, val label: String, val desc: String) {
    SPLIT_AUTO(0, "Split Auto", "Splits TLS ClientHello automatically on DPI interference"),
    SPLIT_SNI(1, "Split SNI", "Splits packet right before Server Name Indication"),
    DESYNC(2, "TCP Desync", "Out-of-order segment timing to bypass middleboxes"),
    NONE(3, "Standard", "Direct socket connection without packet splitting");

    companion object {
        fun fromId(id: Int): DialStrategy = entries.firstOrNull { it.id == id } ?: SPLIT_AUTO
    }
}

enum class VpnTunnelState {
    STOPPED,
    STARTING,
    ACTIVE,
    PAUSED
}

@Serializable
data class BackupPayload(
    val schemaVersion: Int = 1,
    val exportedAtMs: Long,
    val braveMode: Int,
    val dnsType: Int,
    val activeResolverName: String,
    val activeResolverUrl: String,
    val universalLockdown: Boolean,
    val blockWhenLocked: Boolean,
    val blockBackground: Boolean,
    val blockMetered: Boolean,
    val blockUnknown: Boolean,
    val blockUdpExceptDns: Boolean,
    val blockHttp: Boolean,
    val preventDnsLeaks: Boolean,
    val customDomains: List<BackupDomainRule>,
    val customIps: List<BackupIpRule>,
    val customDnsEndpoints: List<BackupDnsEndpoint>,
    val selectedBlocklistIds: List<String>
)

@Serializable
data class BackupDomainRule(
    val domain: String,
    val uid: Int,
    val status: Int
)

@Serializable
data class BackupIpRule(
    val ipAddress: String,
    val port: Int,
    val protocol: String,
    val uid: Int,
    val status: Int
)

@Serializable
data class BackupDnsEndpoint(
    val name: String,
    val url: String,
    val protocolType: Int,
    val description: String
)
