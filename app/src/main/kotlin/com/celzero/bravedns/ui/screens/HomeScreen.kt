package com.celzero.bravedns.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.celzero.bravedns.R
import com.celzero.bravedns.model.BraveMode
import com.celzero.bravedns.model.VpnTunnelState
import com.celzero.bravedns.ui.components.ActivityHeatmapGrid
import com.celzero.bravedns.ui.components.DnsProbeDialog
import com.celzero.bravedns.ui.components.ProtectionOrbControl
import com.celzero.bravedns.ui.theme.LocalSemanticColors
import com.celzero.bravedns.ui.viewmodel.HomeUiState
import com.celzero.bravedns.ui.viewmodel.ProbeUiState
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    probeState: ProbeUiState,
    onToggleVpn: () -> Unit,
    onPauseResumeVpn: () -> Unit,
    onSelectBraveMode: (BraveMode) -> Unit,
    onRunProbe: (String) -> Unit,
    onResetProbe: () -> Unit,
    onDismissOnboarding: () -> Unit,
    onOpenApps: () -> Unit,
    onOpenDns: () -> Unit,
    onOpenBlocklists: () -> Unit,
    onOpenFirewallRules: () -> Unit,
    onOpenLogs: () -> Unit,
    onOpenProxyWg: () -> Unit,
    modifier: Modifier = Modifier
) {
    val semantic = LocalSemanticColors.current
    var showProbeDialog by remember { mutableStateOf(false) }
    var heatmapBlockedMode by remember { mutableStateOf(false) }
    var uptimeSeconds by remember { mutableLongStateOf(0L) }

    LaunchedEffect(uiState.vpnStatus.state, uiState.vpnStatus.startedAtMs) {
        while (uiState.vpnStatus.state == VpnTunnelState.ACTIVE && uiState.vpnStatus.startedAtMs > 0L) {
            uptimeSeconds = (System.currentTimeMillis() - uiState.vpnStatus.startedAtMs) / 1000L
            delay(1_000L)
        }
        if (uiState.vpnStatus.state == VpnTunnelState.STOPPED) {
            uptimeSeconds = 0L
        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 720.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "RETHINK",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${uiState.settings.braveMode.label} • ${uiState.settings.dnsType.shortLabel}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    AssistChip(
                        onClick = { showProbeDialog = true },
                        label = { Text("Probe DNS") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Speed,
                                contentDescription = "Probe DNS resolver",
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        modifier = Modifier.testTag("home_probe_dns_chip")
                    )
                }
            }

            // First-launch onboarding banner
            item {
                AnimatedVisibility(visible = !uiState.settings.onboardingSeen) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "No-Root Encrypted DNS & Firewall",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Rethink uses a local Android VPN tunnel to filter domains against blocklists, route DNS over HTTPS, and enforce per-app rules.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(
                                    onClick = onDismissOnboarding,
                                    modifier = Modifier.testTag("dismiss_onboarding_button")
                                ) {
                                    Text("Got it")
                                }
                            }
                        }
                    }
                }
            }

            // Hero Protection Card with generated shield art
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.large)
                        .border(1.dp, semantic.surfaceBorder, MaterialTheme.shapes.large)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_shield_hero),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        semantic.heroDarkSurface.copy(alpha = 0.78f),
                                        semantic.heroDarkSurface.copy(alpha = 0.94f)
                                    )
                                )
                            )
                    )
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        ProtectionOrbControl(
                            state = uiState.vpnStatus.state,
                            onStartStopClick = onToggleVpn,
                            onPauseResumeClick = onPauseResumeVpn
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MetricPill(
                                label = "UPTIME",
                                value = formatUptime(uptimeSeconds),
                                modifier = Modifier.weight(1f)
                            )
                            MetricPill(
                                label = "QUERIES",
                                value = uiState.totalDnsQueries.toString(),
                                modifier = Modifier.weight(1f)
                            )
                            MetricPill(
                                label = "BLOCKED",
                                value = uiState.blockedDnsQueries.toString(),
                                valueColor = semantic.blocked,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Brave Mode Selector
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BraveMode.entries.forEach { mode ->
                        FilterChip(
                            selected = uiState.settings.braveMode == mode,
                            onClick = { onSelectBraveMode(mode) },
                            label = {
                                Text(
                                    text = mode.label,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("brave_mode_${mode.name.lowercase()}")
                        )
                    }
                }
            }

            // DNS & Blocklists Card
            item {
                DashboardActionCard(
                    title = "DNS & Blocklists",
                    subtitle = "${uiState.settings.activeResolverName} (${uiState.settings.dnsType.shortLabel})",
                    icon = Icons.Filled.Dns,
                    accent = MaterialTheme.colorScheme.primary,
                    badges = listOf(
                        "${uiState.selectedBlocklistsCount} blocklist packs",
                        "${uiState.customDomainRulesCount} domain rules"
                    ),
                    onClick = onOpenDns,
                    secondaryLabel = "Blocklists",
                    onSecondaryClick = onOpenBlocklists,
                    testTag = "home_dns_card"
                )
            }

            // Firewall & Apps Card
            item {
                DashboardActionCard(
                    title = "Firewall & Apps",
                    subtitle = "${uiState.allowedAppsCount} allowed • ${uiState.blockedAppsCount} restricted",
                    icon = Icons.Filled.Apps,
                    accent = MaterialTheme.colorScheme.secondary,
                    badges = listOf(
                        "${uiState.customIpRulesCount} IP rules",
                        if (uiState.settings.universalLockdown) "Lockdown ON" else "Standard Rules"
                    ),
                    onClick = onOpenApps,
                    secondaryLabel = "Rules",
                    onSecondaryClick = onOpenFirewallRules,
                    testTag = "home_firewall_card"
                )
            }

            // Live Activity Heatmap Card
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.History,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column {
                                    Text(
                                        text = "Network Activity (6d × 24h)",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = if (uiState.totalDnsQueries == 0) {
                                            "Start VPN or Probe DNS to record live telemetry"
                                        } else {
                                            "${uiState.totalDnsQueries} logged queries • Tap to inspect"
                                        },
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            TextButton(
                                onClick = onOpenLogs,
                                modifier = Modifier.testTag("home_open_logs_button")
                            ) {
                                Text("Logs")
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FilterChip(
                                selected = !heatmapBlockedMode,
                                onClick = { heatmapBlockedMode = false },
                                label = { Text("Allowed") },
                                modifier = Modifier.testTag("heatmap_allowed_chip")
                            )
                            FilterChip(
                                selected = heatmapBlockedMode,
                                onClick = { heatmapBlockedMode = true },
                                label = { Text("Blocked") },
                                modifier = Modifier.testTag("heatmap_blocked_chip")
                            )
                        }

                        ActivityHeatmapGrid(
                            buckets = if (heatmapBlockedMode) uiState.blockedHeatmap else uiState.allowedHeatmap,
                            isBlockedMode = heatmapBlockedMode,
                            onCellTap = { onOpenLogs() }
                        )
                    }
                }
            }

            // Proxy & WireGuard Card
            item {
                DashboardActionCard(
                    title = "WireGuard & Proxy",
                    subtitle = "Mode: ${uiState.settings.proxyMode.label}",
                    icon = Icons.Filled.VpnKey,
                    accent = semantic.allowed,
                    badges = listOf(
                        "${uiState.activeWgCount} active WireGuard",
                        "${uiState.activeProxyCount} active proxy"
                    ),
                    onClick = onOpenProxyWg,
                    testTag = "home_proxy_card"
                )
            }
        }
    }

    if (showProbeDialog) {
        DnsProbeDialog(
            activeResolverName = uiState.settings.activeResolverName,
            activeResolverUrl = uiState.settings.activeResolverUrl,
            probeState = probeState,
            onRunProbe = onRunProbe,
            onDismiss = {
                onResetProbe()
                showProbeDialog = false
            }
        )
    }
}

@Composable
private fun MetricPill(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = Color.White
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White.copy(alpha = 0.08f),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.65f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = valueColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun DashboardActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accent: Color,
    badges: List<String>,
    onClick: () -> Unit,
    testTag: String,
    secondaryLabel: String? = null,
    onSecondaryClick: (() -> Unit)? = null
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .testTag(testTag)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                            .background(accent.copy(alpha = 0.14f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = accent
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = title, style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                if (secondaryLabel != null && onSecondaryClick != null) {
                    TextButton(onClick = onSecondaryClick) {
                        Text(secondaryLabel)
                    }
                } else {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Open $title",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                badges.forEach { badge ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = badge,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun formatUptime(seconds: Long): String {
    if (seconds <= 0L) return "00:00"
    val hrs = seconds / 3600
    val mins = (seconds % 3600) / 60
    val secs = seconds % 60
    return if (hrs > 0) {
        "%d:%02d:%02d".format(hrs, mins, secs)
    } else {
        "%02d:%02d".format(mins, secs)
    }
}
