package com.celzero.bravedns.ui.screens

import android.text.format.Formatter
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.celzero.bravedns.data.db.AppInfoEntity
import com.celzero.bravedns.model.AppConnectionStatus
import com.celzero.bravedns.model.AppFirewallStatus
import com.celzero.bravedns.ui.theme.LocalSemanticColors

private enum class AppFilterTab(val label: String) {
    ALL("All"),
    USER("User"),
    SYSTEM("System"),
    BLOCKED("Restricted")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppsFirewallScreen(
    apps: List<AppInfoEntity>,
    onBack: () -> Unit,
    onRefreshApps: () -> Unit,
    onUpdateConnectionStatus: (AppInfoEntity, AppConnectionStatus) -> Unit,
    onUpdateFirewallStatus: (AppInfoEntity, AppFirewallStatus) -> Unit
) {
    val context = LocalContext.current
    val semantic = LocalSemanticColors.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(AppFilterTab.ALL) }
    var editingApp by remember { mutableStateOf<AppInfoEntity?>(null) }

    val filteredApps = remember(apps, searchQuery, selectedTab) {
        apps.filter { app ->
            val matchesQuery = searchQuery.isBlank() ||
                app.appName.contains(searchQuery, ignoreCase = true) ||
                app.packageName.contains(searchQuery, ignoreCase = true)
            val connStatus = AppConnectionStatus.fromId(app.connectionStatus)
            val matchesTab = when (selectedTab) {
                AppFilterTab.ALL -> true
                AppFilterTab.USER -> !app.isSystemApp
                AppFilterTab.SYSTEM -> app.isSystemApp
                AppFilterTab.BLOCKED -> connStatus.isBlockedAny || app.firewallStatus != AppFirewallStatus.NONE.id
            }
            matchesQuery && matchesTab
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Apps Firewall (${filteredApps.size})") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("apps_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = onRefreshApps,
                        modifier = Modifier.testTag("apps_refresh_button")
                    ) {
                        Icon(Icons.Filled.Refresh, contentDescription = "Sync installed apps")
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
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search apps or packages") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("apps_search_input")
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AppFilterTab.entries.forEach { tab ->
                        FilterChip(
                            selected = selectedTab == tab,
                            onClick = { selectedTab = tab },
                            label = { Text(tab.label) },
                            modifier = Modifier.testTag("apps_filter_${tab.name.lowercase()}")
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredApps, key = { "${it.uid}_${it.packageName}" }) { app ->
                        val conn = AppConnectionStatus.fromId(app.connectionStatus)
                        val fw = AppFirewallStatus.fromId(app.firewallStatus)
                        val dataSize = Formatter.formatShortFileSize(
                            context,
                            app.wifiDataBytes + app.mobileDataBytes
                        )
                        val statusColor = when (conn) {
                            AppConnectionStatus.ALLOW -> semantic.allowed
                            AppConnectionStatus.BLOCK_BOTH -> semantic.blocked
                            AppConnectionStatus.BLOCK_WIFI, AppConnectionStatus.BLOCK_MOBILE -> semantic.paused
                        }

                        Surface(
                            shape = MaterialTheme.shapes.medium,
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                                .clickable { editingApp = app }
                                .testTag("app_item_${app.packageName}")
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = app.appName,
                                            style = MaterialTheme.typography.titleMedium,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = "${app.packageName} • $dataSize",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = statusColor.copy(alpha = 0.16f)
                                    ) {
                                        Text(
                                            text = if (fw != AppFirewallStatus.NONE) "${conn.label} • ${fw.label}" else conn.label,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = statusColor,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        )
                                    }
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    AppRuleButton(
                                        label = "Allow",
                                        selected = conn == AppConnectionStatus.ALLOW,
                                        icon = Icons.Filled.CheckCircle,
                                        onClick = { onUpdateConnectionStatus(app, AppConnectionStatus.ALLOW) },
                                        modifier = Modifier.weight(1f)
                                    )
                                    AppRuleButton(
                                        label = "Block",
                                        selected = conn == AppConnectionStatus.BLOCK_BOTH,
                                        icon = Icons.Filled.Block,
                                        onClick = { onUpdateConnectionStatus(app, AppConnectionStatus.BLOCK_BOTH) },
                                        modifier = Modifier.weight(1f)
                                    )
                                    AppRuleButton(
                                        label = "No Wi-Fi",
                                        selected = conn == AppConnectionStatus.BLOCK_WIFI,
                                        icon = Icons.Filled.WifiOff,
                                        onClick = { onUpdateConnectionStatus(app, AppConnectionStatus.BLOCK_WIFI) },
                                        modifier = Modifier.weight(1f)
                                    )
                                    AppRuleButton(
                                        label = "No Cell",
                                        selected = conn == AppConnectionStatus.BLOCK_MOBILE,
                                        icon = Icons.Filled.SignalCellularAlt,
                                        onClick = { onUpdateConnectionStatus(app, AppConnectionStatus.BLOCK_MOBILE) },
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    editingApp?.let { app ->
        AlertDialog(
            onDismissRequest = { editingApp = null },
            title = { Text("${app.appName} Policy") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "UID ${app.uid} • ${app.appCategory}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    AppFirewallStatus.entries.forEach { fwOption ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onUpdateFirewallStatus(app, fwOption)
                                    editingApp = null
                                }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = app.firewallStatus == fwOption.id,
                                onClick = {
                                    onUpdateFirewallStatus(app, fwOption)
                                    editingApp = null
                                }
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(fwOption.label, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { editingApp = null }) {
                    Text("Done")
                }
            }
        )
    }
}

@Composable
private fun AppRuleButton(
    label: String,
    selected: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelSmall
            )
        },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(14.dp))
        },
        modifier = modifier
    )
}
