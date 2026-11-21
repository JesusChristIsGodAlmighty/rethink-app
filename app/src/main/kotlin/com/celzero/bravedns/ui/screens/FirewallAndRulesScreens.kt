package com.celzero.bravedns.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.celzero.bravedns.data.AppSettings
import com.celzero.bravedns.data.db.CustomDomainEntity
import com.celzero.bravedns.data.db.CustomIpEntity
import com.celzero.bravedns.model.RuleStatus
import com.celzero.bravedns.ui.theme.LocalSemanticColors

private data class FirewallToggleSpec(
    val key: String,
    val title: String,
    val desc: String,
    val ruleCode: String,
    val checked: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirewallRulesScreen(
    settings: AppSettings,
    onBack: () -> Unit,
    onToggleFlag: (String, Boolean) -> Unit,
    onOpenCustomRules: () -> Unit
) {
    val toggles = listOf(
        FirewallToggleSpec(
            key = "universalLockdown",
            title = "Universal Lockdown",
            desc = "Block all app connections unless explicitly whitelisted",
            ruleCode = "Rule #11",
            checked = settings.universalLockdown
        ),
        FirewallToggleSpec(
            key = "blockWhenLocked",
            title = "Block When Device Locked",
            desc = "Drop connections from non-exempt apps while screen is locked",
            ruleCode = "Rule #3",
            checked = settings.blockWhenLocked
        ),
        FirewallToggleSpec(
            key = "blockBackground",
            title = "Block Background Apps",
            desc = "Allow network access only while an app is in foreground",
            ruleCode = "Rule #4",
            checked = settings.blockBackground
        ),
        FirewallToggleSpec(
            key = "blockMetered",
            title = "Block All Metered Data",
            desc = "Prevent apps from consuming cellular or metered Wi-Fi",
            ruleCode = "Rule #1F",
            checked = settings.blockMetered
        ),
        FirewallToggleSpec(
            key = "blockUnknown",
            title = "Block Unknown Connections",
            desc = "Drop packets whose originating UID cannot be resolved",
            ruleCode = "Rule #5",
            checked = settings.blockUnknown
        ),
        FirewallToggleSpec(
            key = "blockUdpExceptDns",
            title = "Block UDP (Except DNS)",
            desc = "Blocks non-DNS UDP flows such as QUIC/NTP",
            ruleCode = "Rule #6",
            checked = settings.blockUdpExceptDns
        ),
        FirewallToggleSpec(
            key = "blockHttp",
            title = "Block Cleartext HTTP",
            desc = "Block unencrypted port 80 connections",
            ruleCode = "Rule #10",
            checked = settings.blockHttp
        ),
        FirewallToggleSpec(
            key = "preventDnsLeaks",
            title = "Prevent DNS Leaks",
            desc = "Block apps trying to reach port 53 outside the tunnel",
            ruleCode = "Rule #7",
            checked = settings.preventDnsLeaks
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Universal Firewall") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("firewall_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = onOpenCustomRules, modifier = Modifier.testTag("firewall_custom_rules_action")) {
                        Text("Custom Rules")
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
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(toggles, key = { it.key }) { item ->
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                            .clickable { onToggleFlag(item.key, !item.checked) }
                            .testTag("fw_toggle_${item.key}")
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
                                    .padding(end = 12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(item.title, style = MaterialTheme.typography.titleMedium)
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant
                                    ) {
                                        Text(
                                            text = item.ruleCode,
                                            style = MaterialTheme.typography.labelSmall,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = item.desc,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Switch(
                                checked = item.checked,
                                onCheckedChange = { onToggleFlag(item.key, it) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomRulesScreen(
    domains: List<CustomDomainEntity>,
    ips: List<CustomIpEntity>,
    onBack: () -> Unit,
    onAddDomainRule: (String, RuleStatus) -> Unit,
    onDeleteDomainRule: (CustomDomainEntity) -> Unit,
    onAddIpRule: (String, Int, String, RuleStatus) -> Unit,
    onDeleteIpRule: (CustomIpEntity) -> Unit
) {
    val semantic = LocalSemanticColors.current
    var selectedTab by remember { mutableIntStateOf(0) }
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Custom Rules") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("custom_rules_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                modifier = Modifier.testTag("add_custom_rule_fab")
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add custom rule")
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
                TabRow(selectedTabIndex = selectedTab) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = { Text("Domains (${domains.size})") }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = { Text("IPs (${ips.size})") }
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (selectedTab == 0) {
                        if (domains.isEmpty()) {
                            item {
                                Text(
                                    text = "No custom domain rules yet. Tap + to block or trust a domain (e.g. *.tracker.example).",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        } else {
                            items(domains, key = { "${it.domain}_${it.uid}" }) { rule ->
                                val status = RuleStatus.fromId(rule.status)
                                val badgeColor = if (status == RuleStatus.BLOCK) semantic.blocked else semantic.allowed
                                Surface(
                                    shape = MaterialTheme.shapes.medium,
                                    color = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(rule.domain, style = MaterialTheme.typography.titleMedium)
                                            Text(
                                                text = if (rule.isWildcard) "Wildcard • Global" else "Exact • Global",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = badgeColor.copy(alpha = 0.16f)
                                        ) {
                                            Text(
                                                text = status.label,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = badgeColor,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                        IconButton(onClick = { onDeleteDomainRule(rule) }) {
                                            Icon(Icons.Filled.Delete, contentDescription = "Remove rule")
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (ips.isEmpty()) {
                            item {
                                Text(
                                    text = "No custom IP rules yet. Tap + to block or trust an IP address.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        } else {
                            items(ips, key = { "${it.uid}_${it.ipAddress}_${it.port}_${it.protocol}" }) { rule ->
                                val status = RuleStatus.fromId(rule.status)
                                val badgeColor = if (status == RuleStatus.BLOCK) semantic.blocked else semantic.allowed
                                Surface(
                                    shape = MaterialTheme.shapes.medium,
                                    color = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(rule.ipAddress, style = MaterialTheme.typography.titleMedium)
                                            Text(
                                                text = "${rule.protocol} • Port ${if (rule.port == 0) "Any" else rule.port}",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = badgeColor.copy(alpha = 0.16f)
                                        ) {
                                            Text(
                                                text = status.label,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = badgeColor,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                        IconButton(onClick = { onDeleteIpRule(rule) }) {
                                            Icon(Icons.Filled.Delete, contentDescription = "Remove IP rule")
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
        var valueInput by remember { mutableStateOf("") }
        var portInput by remember { mutableStateOf("0") }
        var status by remember { mutableStateOf(RuleStatus.BLOCK) }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text(if (selectedTab == 0) "Add Domain Rule" else "Add IP Rule") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(RuleStatus.BLOCK, RuleStatus.TRUST).forEach { st ->
                            FilterChip(
                                selected = status == st,
                                onClick = { status = st },
                                label = { Text(st.label) }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = valueInput,
                        onValueChange = { valueInput = it },
                        label = { Text(if (selectedTab == 0) "Domain or *.wildcard" else "IP Address / CIDR") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (selectedTab == 1) {
                        OutlinedTextField(
                            value = portInput,
                            onValueChange = { portInput = it.filter { ch -> ch.isDigit() } },
                            label = { Text("Port (0 = Any)") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (selectedTab == 0) {
                        onAddDomainRule(valueInput, status)
                    } else {
                        onAddIpRule(valueInput, portInput.toIntOrNull() ?: 0, "ANY", status)
                    }
                    showAddDialog = false
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
