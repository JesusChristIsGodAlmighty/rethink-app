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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Speed
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.celzero.bravedns.data.db.BlocklistTagEntity
import com.celzero.bravedns.data.db.DnsEndpointEntity
import com.celzero.bravedns.model.DnsProtocolType
import com.celzero.bravedns.ui.theme.LocalSemanticColors

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DnsConfigScreen(
    endpoints: List<DnsEndpointEntity>,
    statusMessage: String?,
    onBack: () -> Unit,
    onSelectEndpoint: (DnsEndpointEntity) -> Unit,
    onProbeEndpoint: (DnsEndpointEntity) -> Unit,
    onAddCustomEndpoint: (String, String, DnsProtocolType) -> Unit,
    onDeleteCustomEndpoint: (Int) -> Unit,
    onOpenBlocklists: () -> Unit
) {
    val semantic = LocalSemanticColors.current
    var selectedType by remember { mutableStateOf<DnsProtocolType?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }

    val filtered = remember(endpoints, selectedType) {
        if (selectedType == null) endpoints
        else endpoints.filter { it.protocolType == selectedType!!.id }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DNS Resolvers") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("dns_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = onOpenBlocklists, modifier = Modifier.testTag("dns_blocklists_action")) {
                        Text("Blocklists")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                modifier = Modifier.testTag("add_custom_dns_fab")
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add custom DNS resolver")
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
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (!statusMessage.isNullOrBlank()) {
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

                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = selectedType == null,
                        onClick = { selectedType = null },
                        label = { Text("All") }
                    )
                    DnsProtocolType.entries.filter { it != DnsProtocolType.SYSTEM_DNS }.forEach { proto ->
                        FilterChip(
                            selected = selectedType == proto,
                            onClick = { selectedType = proto },
                            label = { Text(proto.shortLabel) }
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 84.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filtered, key = { it.id }) { ep ->
                        val proto = DnsProtocolType.fromId(ep.protocolType)
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = if (ep.isSelected) {
                                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                                } else {
                                    MaterialTheme.colorScheme.surface
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = if (ep.isSelected) 1.5.dp else 1.dp,
                                    color = if (ep.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .clickable { onSelectEndpoint(ep) }
                                .testTag("dns_endpoint_${ep.id}")
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Icon(
                                    imageVector = if (ep.isSelected) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                                    contentDescription = if (ep.isSelected) "Selected" else "Select",
                                    tint = if (ep.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text(ep.name, style = MaterialTheme.typography.titleMedium)
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = MaterialTheme.colorScheme.surfaceVariant
                                        ) {
                                            Text(
                                                text = proto.shortLabel,
                                                style = MaterialTheme.typography.labelSmall,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                        if (ep.latencyMs > 0) {
                                            Text(
                                                text = "${ep.latencyMs}ms",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = semantic.allowed
                                            )
                                        }
                                    }
                                    Text(
                                        text = ep.url,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    if (ep.description.isNotBlank()) {
                                        Text(
                                            text = ep.description,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                if (proto == DnsProtocolType.DOH) {
                                    IconButton(
                                        onClick = { onProbeEndpoint(ep) },
                                        modifier = Modifier.testTag("probe_endpoint_${ep.id}")
                                    ) {
                                        Icon(Icons.Filled.Speed, contentDescription = "Measure latency")
                                    }
                                }
                                if (ep.isCustom) {
                                    IconButton(onClick = { onDeleteCustomEndpoint(ep.id) }) {
                                        Icon(
                                            Icons.Filled.Delete,
                                            contentDescription = "Delete custom resolver",
                                            tint = MaterialTheme.colorScheme.error
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

    if (showAddDialog) {
        var name by remember { mutableStateOf("") }
        var url by remember { mutableStateOf("https://") }
        var proto by remember { mutableStateOf(DnsProtocolType.DOH) }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Add Custom DNS Resolver") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf(DnsProtocolType.DOH, DnsProtocolType.DOT, DnsProtocolType.ODOH).forEach { p ->
                            FilterChip(
                                selected = proto == p,
                                onClick = { proto = p },
                                label = { Text(p.shortLabel) }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Resolver Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = url,
                        onValueChange = { url = it },
                        label = { Text("Endpoint URL") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    onAddCustomEndpoint(name, url, proto)
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BlocklistsScreen(
    tags: List<BlocklistTagEntity>,
    onBack: () -> Unit,
    onToggleTag: (BlocklistTagEntity) -> Unit,
    onClearAll: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    var groupFilter by remember { mutableStateOf("all") }

    val filtered = remember(tags, query, groupFilter) {
        tags.filter { t ->
            val matchText = query.isBlank() ||
                t.vname.contains(query, ignoreCase = true) ||
                t.subgroup.contains(query, ignoreCase = true)
            val matchGroup = when (groupFilter) {
                "all" -> true
                "selected" -> t.isSelected
                else -> t.group.equals(groupFilter, ignoreCase = true)
            }
            matchText && matchGroup
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Blocklist Tags (${tags.count { it.isSelected }} active)") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("blocklists_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = onClearAll, modifier = Modifier.testTag("blocklists_clear_button")) {
                        Text("Clear")
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
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Filter ${tags.size} RethinkDNS blocklists") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("blocklists_search_input")
                )

                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(
                        "all" to "All",
                        "selected" to "Active",
                        "privacy" to "Privacy",
                        "security" to "Security",
                        "parentalcontrol" to "Parental"
                    ).forEach { (key, label) ->
                        FilterChip(
                            selected = groupFilter == key,
                            onClick = { groupFilter = key },
                            label = { Text(label) }
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filtered, key = { it.id }) { tag ->
                        Surface(
                            shape = MaterialTheme.shapes.medium,
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
                                .clickable { onToggleTag(tag) }
                                .testTag("blocklist_tag_${tag.id}")
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(tag.vname, style = MaterialTheme.typography.titleMedium)
                                    Text(
                                        text = "${tag.group.uppercase()} • ${tag.entriesCount} domains",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Switch(
                                    checked = tag.isSelected,
                                    onCheckedChange = { onToggleTag(tag) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
