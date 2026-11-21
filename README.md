# Rethink DNS + Firewall (Jetpack Compose Android Rewrite)

A modern **Kotlin + Jetpack Compose + Material 3** Android rewrite of Rethink DNS + Firewall, featuring encrypted DNS-over-HTTPS (DoH) resolution, local `VpnService` packet interception, Room-backed telemetry, and Play-compliant per-app firewall configuration.

## Implemented Features

- **Home Protection Dashboard**: Local Android `VpnService` toggle (Start, Pause, Resume, Stop), live uptime & blocked/total counters, BraveMode switcher (`DNS + Firewall`, `DNS Only`, `Firewall Only`), 144-cell (6d × 24h) interactive network heatmap, and an in-app live DoH DNS Probe diagnostic dialog.
- **Summary Statistics (`StatsScreen`)**: Insights Donut Chart and ranked breakdown lists across `1h`, `24h`, and `7d` windows for Allowed/Blocked Domains, Apps, and IPs.
- **Apps Firewall (`AppsFirewallScreen`)**: Scoped installed-app discovery via `<queries>` (`MAIN`/`LAUNCHER`, without `QUERY_ALL_PACKAGES`), per-app connection modes (`Allow`, `Block`, `No Wi-Fi`, `No Cell`), and policy overrides (`Default`, `Isolate`, `Bypass DNS & Firewall`, `Bypass Universal`, `Exclude from VPN`).
- **DNS Resolvers & Blocklists (`DnsConfigScreen`, `BlocklistsScreen`)**: Pre-populated and custom DoH/DoT/DNSCrypt/ODoH resolver endpoints with live RFC 8484 OkHttp latency probing, plus searchable blocklists parsed from `app/src/main/assets/filetag.json`.
- **Universal Firewall & Custom Rules (`FirewallRulesScreen`, `CustomRulesScreen`)**: 8 global firewall toggles (Lockdown, Screen-off, Background, Metered, Unknown, UDP-except-DNS, Cleartext HTTP, DNS Leak prevention) and wildcard/exact Domain & IP rule management.
- **Network & DNS Logs (`NetworkLogsScreen`)**: Searchable and filterable DNS query & connection history with tap-to-inspect dialogs and one-tap Block/Trust rule creation.
- **WireGuard, Proxy & Tunnel Settings (`ProxyWgScreen`, `TunnelSettingsScreen`)**: WireGuard `.conf` parser/import, SOCKS5/HTTP/Orbot proxy endpoint CRUD, IPv4/IPv6 stack selection, MTU slider, and anti-censorship dial strategies.
- **Backup & Appearance (`BackupAppearanceScreen`)**: JSON configuration export/restore via Android Storage Access Framework (`CreateDocument` / `OpenDocument`), `System`/`Obsidian Dark`/`Daylight Light` themes, and log retention settings.

## Architecture & Persistence

- **UI**: Jetpack Compose + Material 3 (`RethinkTheme` with Space Grotesk, Plus Jakarta Sans, and JetBrains Mono typography) + type-safe `@Serializable` Navigation Compose (`NavigationBar` / `NavigationRail`).
- **State & Storage**: MVVM (`MainViewModel`, `ConfigureViewModel`, `LogsStatsViewModel`), `Room` (`RethinkDatabase`) for logs, rules, endpoints, apps, blocklist tags, WireGuard, and proxy configs, plus `DataStore` (`SettingsDataStore`) for settings.
- **Networking**: Local `RethinkVpnService` captures UDP port 53 DNS packets from the TUN interface, applies custom domain rules and blocklist markers, and resolves allowed queries via RFC 8484 wire-format DoH (`DohClient` backed by OkHttp).

## Stubbed / Future Work (`TODO`)

- **Rethink+ Multi-Party Relay Billing**: Stubbed as a UI placeholder; proprietary Play/Stripe billing backends were omitted.
- **Full Userspace TCP/UDP Socket Engine**: Packet forwarding beyond UDP/53 DoH DNS interception (e.g., gVisor/tun2socks userspace TCP proxy & kernel WireGuard packet encapsulation) is architectural metadata / `TODO` outside pure JVM Kotlin.
