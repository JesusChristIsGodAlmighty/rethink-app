package com.celzero.bravedns.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.celzero.bravedns.model.VpnTunnelState
import com.celzero.bravedns.ui.theme.LocalSemanticColors
import com.celzero.bravedns.ui.viewmodel.ProbeUiState

@Composable
fun ProtectionOrbControl(
    state: VpnTunnelState,
    onStartStopClick: () -> Unit,
    onPauseResumeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val semantic = LocalSemanticColors.current
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary

    val accentColor = when (state) {
        VpnTunnelState.ACTIVE -> semantic.allowed
        VpnTunnelState.PAUSED -> semantic.paused
        VpnTunnelState.STARTING -> primary
        VpnTunnelState.STOPPED -> primary
    }

    val infiniteTransition = rememberInfiniteTransition(label = "orb_rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "orb_angle"
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onStartStopClick)
                    .testTag("vpn_orb_button"),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokePx = 4.dp.toPx()
                    val pad = strokePx / 2f
                    drawArc(
                        color = accentColor.copy(alpha = 0.22f),
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        topLeft = Offset(pad, pad),
                        size = Size(size.width - strokePx, size.height - strokePx),
                        style = Stroke(width = strokePx)
                    )
                    if (state != VpnTunnelState.PAUSED) {
                        rotate(degrees = angle) {
                            drawArc(
                                brush = Brush.sweepGradient(
                                    listOf(
                                        accentColor.copy(alpha = 0.05f),
                                        accentColor,
                                        secondary
                                    )
                                ),
                                startAngle = 0f,
                                sweepAngle = if (state == VpnTunnelState.ACTIVE) 240f else 110f,
                                useCenter = false,
                                topLeft = Offset(pad, pad),
                                size = Size(size.width - strokePx, size.height - strokePx),
                                style = Stroke(width = strokePx, cap = StrokeCap.Round)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(accentColor.copy(alpha = 0.16f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (state == VpnTunnelState.STOPPED) {
                            Icons.Filled.PowerSettingsNew
                        } else {
                            Icons.Filled.Shield
                        },
                        contentDescription = "Toggle VPN protection",
                        tint = accentColor,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = when (state) {
                        VpnTunnelState.ACTIVE -> "Protected"
                        VpnTunnelState.PAUSED -> "Paused"
                        VpnTunnelState.STARTING -> "Starting..."
                        VpnTunnelState.STOPPED -> "Tap to Start"
                    },
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = when (state) {
                        VpnTunnelState.ACTIVE -> "Local DNS & Firewall tunnel running"
                        VpnTunnelState.PAUSED -> "Filtering temporarily bypassed"
                        VpnTunnelState.STARTING -> "Establishing TUN interface"
                        VpnTunnelState.STOPPED -> "No-root DNS & per-app firewall"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.76f)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = onStartStopClick,
                modifier = Modifier.testTag("vpn_primary_action_button")
            ) {
                Text(
                    text = if (state == VpnTunnelState.STOPPED) "Start" else "Stop"
                )
            }
            if (state == VpnTunnelState.ACTIVE || state == VpnTunnelState.PAUSED) {
                FilledTonalButton(
                    onClick = onPauseResumeClick,
                    modifier = Modifier.testTag("vpn_pause_resume_button")
                ) {
                    Icon(
                        imageVector = if (state == VpnTunnelState.PAUSED) {
                            Icons.Filled.PlayArrow
                        } else {
                            Icons.Filled.Pause
                        },
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (state == VpnTunnelState.PAUSED) "Resume" else "Pause")
                }
            }
        }
    }
}

@Composable
fun ActivityHeatmapGrid(
    buckets: List<Int>,
    isBlockedMode: Boolean,
    onCellTap: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val semantic = LocalSemanticColors.current
    val baseColor = if (isBlockedMode) semantic.blocked else semantic.allowed
    val emptyColor = MaterialTheme.colorScheme.surfaceContainerHighest

    // 6 rows (days) x 24 columns (hours) = 144 cells
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (row in 0 until 6) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                for (col in 0 until 24) {
                    val idx = row * 24 + col
                    val level = buckets.getOrElse(idx) { 0 }
                    val cellColor = when (level) {
                        0 -> emptyColor
                        1 -> baseColor.copy(alpha = 0.25f)
                        2 -> baseColor.copy(alpha = 0.48f)
                        3 -> baseColor.copy(alpha = 0.74f)
                        else -> baseColor
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(cellColor)
                            .clickable { onCellTap(idx) }
                    )
                }
            }
        }
    }
}

@Composable
fun StatsDonutChart(
    allowedCount: Int,
    blockedCount: Int,
    centerTitle: String,
    centerSubtitle: String,
    modifier: Modifier = Modifier
) {
    val semantic = LocalSemanticColors.current
    val trackColor = MaterialTheme.colorScheme.surfaceContainerHighest
    val total = (allowedCount + blockedCount).coerceAtLeast(0)
    val allowedSweep = if (total == 0) 0f else (allowedCount.toFloat() / total) * 360f
    val blockedSweep = if (total == 0) 0f else 360f - allowedSweep

    Box(
        modifier = modifier.size(168.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 18.dp.toPx()
            val pad = strokeWidth / 2f
            val arcSize = Size(size.width - strokeWidth, size.height - strokeWidth)
            val topLeft = Offset(pad, pad)

            if (total == 0) {
                drawArc(
                    color = trackColor,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth)
                )
            } else {
                if (allowedSweep > 0f) {
                    drawArc(
                        color = semantic.allowed,
                        startAngle = -90f,
                        sweepAngle = allowedSweep,
                        useCenter = false,
                        topLeft = topLeft,
                        size = arcSize,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                    )
                }
                if (blockedSweep > 0f) {
                    drawArc(
                        color = semantic.blocked,
                        startAngle = -90f + allowedSweep,
                        sweepAngle = blockedSweep,
                        useCenter = false,
                        topLeft = topLeft,
                        size = arcSize,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                    )
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = centerTitle,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = centerSubtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun DnsProbeDialog(
    activeResolverName: String,
    activeResolverUrl: String,
    probeState: ProbeUiState,
    onRunProbe: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var domainInput by remember { mutableStateOf("rethinkdns.com") }
    val semantic = LocalSemanticColors.current

    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Filled.Speed,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        title = {
            Text("Live DoH Resolver Probe")
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Resolver: $activeResolverName",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = activeResolverUrl,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                OutlinedTextField(
                    value = domainInput,
                    onValueChange = { domainInput = it },
                    label = { Text("Domain to resolve") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("probe_domain_input")
                )

                when (probeState) {
                    ProbeUiState.Idle -> {
                        Text(
                            text = "Sends an RFC 8484 DNS-over-HTTPS query and logs the live RTT latency.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    ProbeUiState.Probing -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                            Text("Querying DoH upstream...", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                    is ProbeUiState.Result -> {
                        val r = probeState.result
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (r.success) semantic.allowedContainer else semantic.blockedContainer,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = if (r.success) {
                                        "Resolved in ${r.latencyMs} ms"
                                    } else {
                                        "Probe failed (${r.latencyMs} ms)"
                                    },
                                    style = MaterialTheme.typography.titleMedium,
                                    color = if (r.success) semantic.allowed else semantic.blocked
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (r.success) {
                                        if (r.resolvedIps.isEmpty()) "No A records returned"
                                        else "IPs: ${r.resolvedIps.joinToString(", ")}"
                                    } else {
                                        r.errorMessage ?: "Unknown error"
                                    },
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onRunProbe(domainInput) },
                modifier = Modifier.testTag("run_probe_confirm_button")
            ) {
                Text("Run Probe")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}
