package com.celzero.bravedns.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.celzero.bravedns.R
import com.celzero.bravedns.data.db.ConnectionLogEntity
import com.celzero.bravedns.data.db.DnsLogEntity
import com.celzero.bravedns.model.RuleStatus
import com.celzero.bravedns.ui.theme.LocalSemanticColors
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private enum class LogStatusFilter(val label: String) {
    ALL("All"),
    ALLOWED("Allowed"),
    BLOCKED("Blocked")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkLogsScreen(
    dnsLogs: List<DnsLogEntity>,
    connectionLogs: List<ConnectionLogEntity>,
    onBack: () -> Unit,
    onSetDomainRule: (String, RuleStatus) -> Unit,
    onSetIpRule: (String, RuleStatus) -> Unit,
    onClearAllLogs: () -> Unit
) {
    val semantic = LocalSemanticColors.current
    var selectedTab by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }
    var statusFilter by remember { mutableStateOf(LogStatusFilter.ALL) }
    var selectedDnsLog by remember { mutableStateOf<DnsLogEntity?>(null) }
    var selectedConnLog by remember { mutableStateOf<ConnectionLogEntity?>(null) }
    var confirmClear by remember { mutableStateOf(false) }

    val timeFormatter = remember { SimpleDateFormat("HH:mm:ss", Locale.getDefault()) }

    val filteredDns = remember(dnsLogs, searchQuery, statusFilter) {
        dnsLogs.filter { log ->
            val matchesQuery = searchQuery.isBlank() ||
                log.queryStr.contains(searchQuery, ignoreCase = true) ||
                log.resolverName.contains(searchQuery, ignoreCase = true) ||
                log.responseIps.contains(searchQuery, ignoreCase = true)
            val matchesStatus = when (statusFilter) {
                LogStatusFilter.ALL -> true
                LogStatusFilter.ALLOWED -> !log.isBlocked
                LogStatusFilter.BLOCKED -> log.isBlocked
            }
            matchesQuery && matchesStatus
        }
    }

    val filteredConns = remember(connectionLogs, searchQuery, statusFilter) {
        connectionLogs.filter { log ->
            val matchesQuery = searchQuery.isBlank() ||
                log.appName.contains(searchQuery, ignoreCase = true) ||
                log.ipAddress.contains(searchQuery, ignoreCase = true) ||
                log.dnsQuery.contains(searchQuery, ignoreCase = true)
            val matchesStatus = when (statusFilter) {
                LogStatusFilter.ALL -> true
                LogStatusFilter.ALLOWED -> !log.isBlocked
                LogStatusFilter.BLOCKED -> log.isBlocked
            }
            matchesQuery && matchesStatus
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Network & DNS Logs") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("logs_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { confirmClear = true },
                        modifier = Modifier.testTag("logs_clear_button")
                    ) {
                        Icon(Icons.Filled.DeleteSweep, contentDescription = "Clear all logs")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 720.dp)
            ) {
                TabRow(selectedTabIndex = selectedTab) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = { Text("DNS (${filteredDns.size})") },
                        modifier = Modifier.testTag("logs_tab_dns")
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = { Text("Connections (${filteredConns.size})") },
                        modifier = Modifier.testTag("logs_tab_connections")
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search domain, IP, resolver or app") },
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("logs_search_input")
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        LogStatusFilter.entries.forEach { filter ->
                            FilterChip(
                                selected = statusFilter == filter,
                                onClick = { statusFilter = filter },
                                label = { Text(filter.label) },
                                modifier = Modifier.testTag("logs_filter_${filter.name.lowercase()}")
                            )
                        }
                    }
                }

                val isEmpty = if (selectedTab == 0) filteredDns.isEmpty() else filteredConns.isEmpty()
                if (isEmpty) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_empty_logs),
                                    contentDescription = "No network logs",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(140.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                )
                                Text("No matching activity", style = MaterialTheme.typography.titleMedium)
                                Text(
                                    text = "Start the VPN tunnel or trigger a DNS probe from the Home screen to see live encrypted queries.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (selectedTab == 0) {
                            items(filteredDns, key = { it.id }) { log ->
                                val badgeColor = if (log.isBlocked) semantic.blocked else semantic.allowed
                                val timeText = timeFormatter.format(Date(log.timeMs))
                                Surface(
                                    shape = MaterialTheme.shapes.medium,
                                    color = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                                        .clickable { selectedDnsLog = log }
                                        .testTag("dns_log_item_${log.id}")
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(end = 10.dp),
                                            verticalArrangement = Arrangement.spacedBy(3.dp)
                                        ) {
                                            Text(
                                                text = log.queryStr,
                                                style = MaterialTheme.typography.titleMedium,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text(
                                                text = "${log.typeName} • ${log.resolverName} • $timeText",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            val detail = if (log.isBlocked) {
                                                log.blockedReason.ifBlank { "Blocked" }
                                            } else {
                                                log.responseIps.ifBlank { log.status }
                                            }
                                            Text(
                                                text = detail,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = badgeColor,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                        Column(horizontalAlignment = Alignment.End) {
                                            Surface(
                                                shape = RoundedCornerShape(8.dp),
                                                color = badgeColor.copy(alpha = 0.16f)
                                            ) {
                                                Text(
                                                    text = if (log.isBlocked) "Blocked" else "${log.latencyMs}ms",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = badgeColor,
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            items(filteredConns, key = { it.id }) { conn ->
                                val badgeColor = if (conn.isBlocked) semantic.blocked else semantic.allowed
                                val timeText = timeFormatter.format(Date(conn.timeStampMs))
                                Surface(
                                    shape = MaterialTheme.shapes.medium,
                                    color = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                                        .clickable { selectedConnLog = conn }
                                        .testTag("conn_log_item_${conn.id}")
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(end = 10.dp),
                                            verticalArrangement = Arrangement.spacedBy(3.dp)
                                        ) {
                                            Text(
                                                text = conn.dnsQuery.ifBlank { conn.ipAddress },
                                                style = MaterialTheme.typography.titleMedium,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text(
                                                text = "${conn.appName} • ${conn.ipAddress}:${conn.port} (${conn.protocol}) • $timeText",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text(
                                                text = conn.blockedByRule.ifBlank { "No Rule" },
                                                style = MaterialTheme.typography.bodySmall,
                                                color = badgeColor,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = badgeColor.copy(alpha = 0.16f)
                                        ) {
                                            Text(
                                                text = if (conn.isBlocked) "Blocked" else "Allowed",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = badgeColor,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    selectedDnsLog?.let { log ->
        AlertDialog(
            onDismissRequest = { selectedDnsLog = null },
            title = { Text(log.queryStr, maxLines = 2, overflow = TextOverflow.Ellipsis) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Record Type: ${log.typeName}", style = MaterialTheme.typography.bodySmall)
                    Text("Resolver: ${log.resolverName}", style = MaterialTheme.typography.bodySmall)
                    Text("Status: ${log.status} (${log.latencyMs} ms)", style = MaterialTheme.typography.bodySmall)
                    if (log.responseIps.isNotBlank()) {
                        Text("Answers: ${log.responseIps}", style = MaterialTheme.typography.bodySmall)
                    }
                    if (log.blockedReason.isNotBlank()) {
                        Text("Rule: ${log.blockedReason}", style = MaterialTheme.typography.bodySmall)
                    }
                    if (log.blocklists.isNotBlank()) {
                        Text("Blocklists: ${log.blocklists}", style = MaterialTheme.typography.bodySmall)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = {
                                onSetDomainRule(log.queryStr, RuleStatus.BLOCK)
                                selectedDnsLog = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.Block, contentDescription = null)
                            Text(" Block")
                        }
                        OutlinedButton(
                            onClick = {
                                onSetDomainRule(log.queryStr, RuleStatus.TRUST)
                                selectedDnsLog = null
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.CheckCircle, contentDescription = null)
                            Text(" Trust")
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { selectedDnsLog = null }) { Text("Close") }
            }
        )
    }

    selectedConnLog?.let { conn ->
        AlertDialog(
            onDismissRequest = { selectedConnLog = null },
            title = { Text(conn.ipAddress) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("App: ${conn.appName}", style = MaterialTheme.typography.bodySmall)
                    Text("Destination: ${conn.ipAddress}:${conn.port} (${conn.protocol})", style = MaterialTheme.typography.bodySmall)
                    if (conn.dnsQuery.isNotBlank()) {
                        Text("Domain: ${conn.dnsQuery}", style = MaterialTheme.typography.bodySmall)
                    }
                    Text("Rule: ${conn.blockedByRule.ifBlank { "No Rule" }}", style = MaterialTheme.typography.bodySmall)
                    Text("Bytes: ↓ ${conn.downloadBytes} B / ↑ ${conn.uploadBytes} B", style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = {
                                onSetIpRule(conn.ipAddress, RuleStatus.BLOCK)
                                selectedConnLog = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.Block, contentDescription = null)
                            Text(" Block IP")
                        }
                        OutlinedButton(
                            onClick = {
                                onSetIpRule(conn.ipAddress, RuleStatus.TRUST)
                                selectedConnLog = null
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.CheckCircle, contentDescription = null)
                            Text(" Trust IP")
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { selectedConnLog = null }) { Text("Close") }
            }
        )
    }

    if (confirmClear) {
        AlertDialog(
            onDismissRequest = { confirmClear = false },
            title = { Text("Clear all logs?") },
            text = { Text("This deletes all recorded DNS and connection history from the local database.") },
            confirmButton = {
                Button(
                    onClick = {
                        onClearAllLogs()
                        confirmClear = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Clear")
                }
            },
            dismissButton = {
                TextButton(onClick = { confirmClear = false }) { Text("Cancel") }
            }
        )
    }
}
