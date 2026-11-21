package com.celzero.bravedns.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.celzero.bravedns.R
import com.celzero.bravedns.ui.components.StatsDonutChart
import com.celzero.bravedns.ui.theme.LocalSemanticColors
import com.celzero.bravedns.ui.viewmodel.StatsCategory
import com.celzero.bravedns.ui.viewmodel.StatsOverview
import com.celzero.bravedns.ui.viewmodel.StatsTimeWindow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StatsScreen(
    overview: StatsOverview,
    selectedWindow: StatsTimeWindow,
    selectedCategory: StatsCategory,
    onSelectWindow: (StatsTimeWindow) -> Unit,
    onSelectCategory: (StatsCategory) -> Unit,
    onOpenLogs: () -> Unit,
    modifier: Modifier = Modifier
) {
    val semantic = LocalSemanticColors.current
    var insightsMode by remember { mutableStateOf(true) }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 720.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Network Insights", style = MaterialTheme.typography.headlineMedium)
                        Text(
                            text = "Real-time query & connection telemetry",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    TextButton(
                        onClick = onOpenLogs,
                        modifier = Modifier.testTag("stats_open_logs_button")
                    ) {
                        Text("Live Logs")
                    }
                }
            }

            // View Mode + Time Window
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        StatsTimeWindow.entries.forEach { window ->
                            FilterChip(
                                selected = selectedWindow == window,
                                onClick = { onSelectWindow(window) },
                                label = { Text(window.label) },
                                modifier = Modifier.testTag("stats_window_${window.label}")
                            )
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        FilterChip(
                            selected = insightsMode,
                            onClick = { insightsMode = true },
                            label = {
                                Icon(Icons.Filled.BarChart, contentDescription = "Insights view", modifier = Modifier.size(18.dp))
                            },
                            modifier = Modifier.testTag("stats_mode_insights")
                        )
                        FilterChip(
                            selected = !insightsMode,
                            onClick = { insightsMode = false },
                            label = {
                                Icon(Icons.Filled.FormatListBulleted, contentDescription = "List view", modifier = Modifier.size(18.dp))
                            },
                            modifier = Modifier.testTag("stats_mode_list")
                        )
                    }
                }
            }

            // Insights Summary Card with Donut Chart
            if (insightsMode) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StatsDonutChart(
                                allowedCount = (overview.totalDns - overview.blockedDns).coerceAtLeast(0),
                                blockedCount = overview.blockedDns,
                                centerTitle = "${overview.totalDns}",
                                centerSubtitle = "DNS QUERIES"
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                StatSummaryRow(
                                    label = "Allowed",
                                    value = "${(overview.totalDns - overview.blockedDns).coerceAtLeast(0)}",
                                    color = semantic.allowed
                                )
                                StatSummaryRow(
                                    label = "Blocked",
                                    value = "${overview.blockedDns}",
                                    color = semantic.blocked
                                )
                                StatSummaryRow(
                                    label = "Connections",
                                    value = "${overview.totalConns}",
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                StatSummaryRow(
                                    label = "Avg Latency",
                                    value = if (overview.avgLatencyMs > 0) "${overview.avgLatencyMs} ms" else "—",
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }

            // Category Filter Chips
            item {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    StatsCategory.entries.forEach { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { onSelectCategory(cat) },
                            label = { Text(cat.label) },
                            modifier = Modifier.testTag("stats_cat_${cat.name.lowercase()}")
                        )
                    }
                }
            }

            if (overview.rankedRows.isEmpty()) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_empty_logs),
                                contentDescription = "No statistics recorded yet",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(140.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )
                            Text(
                                text = "No ${selectedCategory.label.lowercase()} yet",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Start the VPN tunnel or run a live DNS probe from Home to see ranked telemetry.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Button(onClick = onOpenLogs) {
                                Text("Open Logs")
                            }
                        }
                    }
                }
            } else {
                val maxCount = overview.rankedRows.maxOf { it.totalCount }.coerceAtLeast(1)
                val isBlockedCat = selectedCategory.name.contains("BLOCKED")
                val barColor = if (isBlockedCat) semantic.blocked else semantic.allowed

                itemsIndexed(overview.rankedRows) { index, row ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.small)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${index + 1}. ${row.label.ifBlank { "Unknown" }}",
                                    style = MaterialTheme.typography.titleMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${row.totalCount} hits",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = barColor
                                )
                            }
                            if (row.subLabel.isNotBlank()) {
                                Text(
                                    text = row.subLabel,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            LinearProgressIndicator(
                                progress = { row.totalCount.toFloat() / maxCount.toFloat() },
                                color = barColor,
                                trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatSummaryRow(
    label: String,
    value: String,
    color: androidx.compose.ui.graphics.Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = color,
                modifier = Modifier.size(10.dp)
            ) {}
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
        }
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
