package com.celzero.bravedns.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SettingsEthernet
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

data class ConfigureMenuItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val accent: Color,
    val testTag: String,
    val onClick: () -> Unit
)

@Composable
fun ConfigureScreen(
    activeResolver: String,
    proxyModeLabel: String,
    onOpenApps: () -> Unit,
    onOpenDns: () -> Unit,
    onOpenBlocklists: () -> Unit,
    onOpenFirewallRules: () -> Unit,
    onOpenCustomRules: () -> Unit,
    onOpenProxyWg: () -> Unit,
    onOpenLogs: () -> Unit,
    onOpenTunnel: () -> Unit,
    onOpenBackupAppearance: () -> Unit,
    modifier: Modifier = Modifier
) {
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    val itemsList = listOf(
        ConfigureMenuItem(
            title = "Apps Firewall",
            subtitle = "Per-app Allow, Block, Wi-Fi/Mobile & VPN exclusion",
            icon = Icons.Filled.Apps,
            accent = secondary,
            testTag = "config_apps_item",
            onClick = onOpenApps
        ),
        ConfigureMenuItem(
            title = "DNS Resolvers",
            subtitle = "Active: $activeResolver (DoH / DoT / DNSCrypt / ODoH)",
            icon = Icons.Filled.Dns,
            accent = primary,
            testTag = "config_dns_item",
            onClick = onOpenDns
        ),
        ConfigureMenuItem(
            title = "Blocklist Tags",
            subtitle = "Privacy, Security & Parental lists from filetag.json",
            icon = Icons.Filled.FilterAlt,
            accent = tertiary,
            testTag = "config_blocklists_item",
            onClick = onOpenBlocklists
        ),
        ConfigureMenuItem(
            title = "Universal Firewall",
            subtitle = "Lockdown, Screen-off, Background, Metered & Leak rules",
            icon = Icons.Filled.Security,
            accent = MaterialTheme.colorScheme.error,
            testTag = "config_firewall_item",
            onClick = onOpenFirewallRules
        ),
        ConfigureMenuItem(
            title = "Custom Domain & IP Rules",
            subtitle = "User-defined wildcard domain and IP/port rules",
            icon = Icons.Filled.Rule,
            accent = primary,
            testTag = "config_custom_rules_item",
            onClick = onOpenCustomRules
        ),
        ConfigureMenuItem(
            title = "WireGuard & Proxy",
            subtitle = "Mode: $proxyModeLabel • Import .conf or SOCKS5/HTTP",
            icon = Icons.Filled.VpnKey,
            accent = secondary,
            testTag = "config_proxy_wg_item",
            onClick = onOpenProxyWg
        ),
        ConfigureMenuItem(
            title = "Network & DNS Logs",
            subtitle = "Live query feed, latencies & one-tap rule creation",
            icon = Icons.Filled.History,
            accent = tertiary,
            testTag = "config_logs_item",
            onClick = onOpenLogs
        ),
        ConfigureMenuItem(
            title = "Tunnel & Anti-Censorship",
            subtitle = "MTU, IPv4/IPv6 stack, LAN gateway & DPI split strategy",
            icon = Icons.Filled.SettingsEthernet,
            accent = secondary,
            testTag = "config_tunnel_item",
            onClick = onOpenTunnel
        ),
        ConfigureMenuItem(
            title = "Backup, Theme & Storage",
            subtitle = "JSON backup/restore via SAF, Obsidian/Light themes",
            icon = Icons.Filled.Backup,
            accent = primary,
            testTag = "config_backup_item",
            onClick = onOpenBackupAppearance
        )
    )

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 720.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(bottom = 6.dp)) {
                    Text("Configure", style = MaterialTheme.typography.headlineMedium)
                    Text(
                        text = "Controls for DNS, Firewall, Tunnels & Diagnostics",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            items(itemsList, key = { it.title }) { item ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable(onClick = item.onClick)
                        .testTag(item.testTag)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(item.accent.copy(alpha = 0.14f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = null,
                                    tint = item.accent
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(item.title, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    text = item.subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Open ${item.title}",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Rethink+ Multi-Party Relay TODO Placeholder (no fake billing SDK)
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Rethink+ Multi-Party Relay",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Serverless relay subscription billing is stubbed (TODO) in this rewrite. Self-hosted WireGuard & custom DoH endpoints are active.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = "TODO",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
