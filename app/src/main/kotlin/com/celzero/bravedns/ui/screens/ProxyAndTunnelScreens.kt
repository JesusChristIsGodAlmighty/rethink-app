package com.celzero.bravedns.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.celzero.bravedns.data.AppSettings
import com.celzero.bravedns.data.db.ProxyConfigEntity
import com.celzero.bravedns.data.db.WireGuardConfigEntity
import com.celzero.bravedns.model.DialStrategy
import com.celzero.bravedns.model.IpProtocolMode
import com.celzero.bravedns.model.ProxyMode
import com.celzero.bravedns.ui.theme.LocalSemanticColors
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ProxyWgScreen(
    settings: AppSettings,
    wireGuardConfigs: List<WireGuardConfigEntity>,
    proxyConfigs: List<ProxyConfigEntity>,
    statusMessage: String?,
    onBack: () -> Unit,
    onSetProxyMode: (ProxyMode) -> Unit,
    onImportWireGuard: (String, String) -> Unit,
    onToggleWireGuardActive: (WireGuardConfigEntity) -> Unit,
    onDeleteWireGuard: (Int) -> Unit,
    onAddProxy: (String, ProxyMode, String, Int, String) -> Unit,
    onToggleProxyActive: (ProxyConfigEntity) -> Unit,
    onDeleteProxy: (Int) -> Unit
) {
    val semantic = LocalSemanticColors.current
    var selectedTab by remember { mutableIntStateOf(0) }
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("WireGuard & Proxy") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("proxy_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                modifier = Modifier.testTag("proxy_add_fab")
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add tunnel or proxy")
            }
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
                if (!statusMessage.isNullOrBlank()) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = statusMessage,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    ProxyMode.entries.forEach { mode ->
                        FilterChip(
                            selected = settings.proxyMode == mode,
                            onClick = { onSetProxyMode(mode) },
                            label = { Text(mode.label) },
                            modifier = Modifier.testTag("proxy_mode_${mode.name.lowercase()}")
                        )
                    }
                }

                TabRow(selectedTabIndex = selectedTab) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = { Text("WireGuard (${wireGuardConfigs.size})") }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = { Text("SOCKS5 / HTTP (${proxyConfigs.size})") }
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (selectedTab == 0) {
                        if (wireGuardConfigs.isEmpty()) {
                            item {
                                Text(
                                    text = "No WireGuard configurations imported. Tap + to paste a standard WireGuard .conf file.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        } else {
                            items(wireGuardConfigs, key = { it.id }) { wg ->
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(
                                            width = if (wg.isActive) 1.5.dp else 1.dp,
                                            color = if (wg.isActive) semantic.allowed else MaterialTheme.colorScheme.outline,
                                            shape = MaterialTheme.shapes.medium
                                        )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(wg.name, style = MaterialTheme.typography.titleMedium)
                                            Text(
                                                text = "Endpoint: ${wg.peerEndpoint.ifBlank { "Unspecified" }} • MTU ${wg.mtu}",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            Text(
                                                text = "Interface: ${wg.interfaceAddress.ifBlank { "Auto" }} • Key: ${wg.privateKeyMasked}",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                        Switch(
                                            checked = wg.isActive,
                                            onCheckedChange = { onToggleWireGuardActive(wg) }
                                        )
                                        IconButton(onClick = { onDeleteWireGuard(wg.id) }) {
                                            Icon(Icons.Filled.Delete, contentDescription = "Delete WireGuard tunnel")
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (proxyConfigs.isEmpty()) {
                            item {
                                Text(
                                    text = "No SOCKS5/HTTP proxies configured. Tap + to add an upstream proxy.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        } else {
                            items(proxyConfigs, key = { it.id }) { proxy ->
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(
                                            width = if (proxy.isActive) 1.5.dp else 1.dp,
                                            color = if (proxy.isActive) semantic.allowed else MaterialTheme.colorScheme.outline,
                                            shape = MaterialTheme.shapes.medium
                                        )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(proxy.name, style = MaterialTheme.typography.titleMedium)
                                            Text(
                                                text = "${proxy.mode} • ${proxy.host}:${proxy.port}",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        Switch(
                                            checked = proxy.isActive,
                                            onCheckedChange = { onToggleProxyActive(proxy) }
                                        )
                                        IconButton(onClick = { onDeleteProxy(proxy.id) }) {
                                            Icon(Icons.Filled.Delete, contentDescription = "Delete proxy")
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

    if (showAddDialog) {
        if (selectedTab == 0) {
            var name by remember { mutableStateOf("") }
            var rawConf by remember { mutableStateOf("") }
            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                title = { Text("Import WireGuard .conf") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Tunnel Name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = rawConf,
                            onValueChange = { rawConf = it },
                            label = { Text("[Interface] / [Peer] Config") },
                            minLines = 5,
                            maxLines = 8,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        onImportWireGuard(name, rawConf)
                        showAddDialog = false
                    }) {
                        Text("Import")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) { Text("Cancel") }
                }
            )
        } else {
            var name by remember { mutableStateOf("") }
            var host by remember { mutableStateOf("127.0.0.1") }
            var port by remember { mutableStateOf("1080") }
            var username by remember { mutableStateOf("") }
            var mode by remember { mutableStateOf(ProxyMode.SOCKS5) }

            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                title = { Text("Add Proxy Server") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(ProxyMode.SOCKS5, ProxyMode.HTTP, ProxyMode.ORBOT).forEach { m ->
                                FilterChip(
                                    selected = mode == m,
                                    onClick = { mode = m },
                                    label = { Text(m.name) }
                                )
                            }
                        }
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Label (optional)") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = host,
                            onValueChange = { host = it },
                            label = { Text("Host") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = port,
                            onValueChange = { port = it.filter { c -> c.isDigit() } },
                            label = { Text("Port") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            label = { Text("Username (optional)") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        onAddProxy(name, mode, host, port.toIntOrNull() ?: 1080, username)
                        showAddDialog = false
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) { Text("Cancel") }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TunnelSettingsScreen(
    settings: AppSettings,
    statusMessage: String?,
    onBack: () -> Unit,
    onSaveTunnel: (IpProtocolMode, Int, String, String) -> Unit,
    onToggleFlag: (String, Boolean) -> Unit,
    onSetAntiCensorship: (DialStrategy, Int) -> Unit
) {
    var ipMode by remember(settings.ipProtocolMode) { mutableStateOf(settings.ipProtocolMode) }
    var mtu by remember(settings.mtu) { mutableFloatStateOf(settings.mtu.toFloat()) }
    var gateway by remember(settings.customLanGateway) { mutableStateOf(settings.customLanGateway) }
    var lanDns by remember(settings.customLanDns) { mutableStateOf(settings.customLanDns) }
    var timeoutFloat by remember(settings.dialTimeoutSec) { mutableFloatStateOf(settings.dialTimeoutSec.toFloat()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tunnel & Anti-Censorship") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("tunnel_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 720.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                if (!statusMessage.isNullOrBlank()) {
                    item {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = statusMessage,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }

                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text("VPN Stack & MTU", style = MaterialTheme.typography.titleMedium)

                            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                IpProtocolMode.entries.forEach { mode ->
                                    FilterChip(
                                        selected = ipMode == mode,
                                        onClick = { ipMode = mode },
                                        label = { Text(mode.label) }
                                    )
                                }
                            }

                            Text("Tunnel MTU: ${mtu.roundToInt()}", style = MaterialTheme.typography.labelLarge)
                            Slider(
                                value = mtu,
                                onValueChange = { mtu = it },
                                valueRange = 1200f..1500f
                            )

                            OutlinedTextField(
                                value = gateway,
                                onValueChange = { gateway = it },
                                label = { Text("TUN Gateway CIDR") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = lanDns,
                                onValueChange = { lanDns = it },
                                label = { Text("Internal Tunnel DNS IP") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Button(
                                onClick = { onSaveTunnel(ipMode, mtu.roundToInt(), gateway, lanDns) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("save_tunnel_settings_button")
                            ) {
                                Text("Save Tunnel Parameters")
                            }
                        }
                    }
                }

                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text("Tunnel Policies", style = MaterialTheme.typography.titleMedium)
                            TunnelToggleRow(
                                title = "Allow VPN Bypass",
                                subtitle = "Allow apps to request direct bypass sockets",
                                checked = settings.allowBypass,
                                onCheckedChange = { onToggleFlag("allowBypass", it) }
                            )
                            TunnelToggleRow(
                                title = "Connectivity Checks",
                                subtitle = "Verify active upstream reachability on network switches",
                                checked = settings.connectivityChecks,
                                onCheckedChange = { onToggleFlag("connectivityChecks", it) }
                            )
                        }
                    }
                }

                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text("Anti-Censorship Dial Strategy", style = MaterialTheme.typography.titleMedium)
                            DialStrategy.entries.forEach { strategy ->
                                val selected = settings.dialStrategy == strategy
                                Surface(
                                    shape = MaterialTheme.shapes.small,
                                    color = if (selected) {
                                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                                    } else {
                                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onSetAntiCensorship(strategy, timeoutFloat.roundToInt()) }
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Text(strategy.label, style = MaterialTheme.typography.titleSmall)
                                        Text(
                                            text = strategy.desc,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Dial Timeout: ${timeoutFloat.roundToInt()}s",
                                style = MaterialTheme.typography.labelLarge
                            )
                            Slider(
                                value = timeoutFloat,
                                onValueChange = { timeoutFloat = it },
                                onValueChangeFinished = {
                                    onSetAntiCensorship(settings.dialStrategy, timeoutFloat.roundToInt())
                                },
                                valueRange = 3f..60f
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TunnelToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f).padding(end = 12.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall)
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
