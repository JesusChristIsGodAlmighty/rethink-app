package com.celzero.bravedns.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.celzero.bravedns.BuildConfig
import com.celzero.bravedns.R
import com.celzero.bravedns.data.AppSettings
import com.celzero.bravedns.model.ThemeMode
import com.celzero.bravedns.ui.components.DnsProbeDialog
import com.celzero.bravedns.ui.viewmodel.ProbeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BackupAppearanceScreen(
    settings: AppSettings,
    statusMessage: String?,
    onBack: () -> Unit,
    onSetThemeMode: (ThemeMode) -> Unit,
    onToggleLogsEnabled: (Boolean) -> Unit,
    onSetLogLifespanDays: (Int) -> Unit,
    onExportBackupJson: suspend () -> String,
    onImportBackupJson: (String) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var localNotice by remember { mutableStateOf<String?>(null) }

    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json")
    ) { uri ->
        if (uri != null) {
            scope.launch {
                val json = onExportBackupJson()
                val written = withContext(Dispatchers.IO) {
                    runCatching {
                        context.contentResolver.openOutputStream(uri)?.use { out ->
                            out.write(json.toByteArray(Charsets.UTF_8))
                        }
                    }.isSuccess
                }
                localNotice = if (written) "Backup exported successfully" else "Unable to write backup file"
            }
        }
    }

    val importLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            scope.launch {
                val raw = withContext(Dispatchers.IO) {
                    runCatching {
                        context.contentResolver.openInputStream(uri)?.bufferedReader()?.use { it.readText() }
                    }.getOrNull()
                }
                if (raw != null) {
                    onImportBackupJson(raw)
                } else {
                    localNotice = "Failed to read selected backup file"
                }
            }
        }
    }

    val bannerText = localNotice ?: statusMessage

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Backup, Theme & Storage") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("backup_back_button")) {
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
                if (!bannerText.isNullOrBlank()) {
                    item {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = bannerText,
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
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text("Appearance", style = MaterialTheme.typography.titleMedium)
                            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                ThemeMode.entries.forEach { mode ->
                                    FilterChip(
                                        selected = settings.themeMode == mode,
                                        onClick = { onSetThemeMode(mode) },
                                        label = { Text(mode.label) },
                                        modifier = Modifier.testTag("theme_${mode.name.lowercase()}")
                                    )
                                }
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
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text("Telemetry & Log Retention", style = MaterialTheme.typography.titleMedium)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onToggleLogsEnabled(!settings.logsEnabled) },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Record DNS & Connection Logs", style = MaterialTheme.typography.titleSmall)
                                    Text(
                                        text = "Disable for zero-footprint local querying",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Switch(
                                    checked = settings.logsEnabled,
                                    onCheckedChange = onToggleLogsEnabled
                                )
                            }

                            Text("Retain Logs For", style = MaterialTheme.typography.labelLarge)
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                listOf(1, 7, 30).forEach { days ->
                                    FilterChip(
                                        selected = settings.logLifespanDays == days,
                                        onClick = { onSetLogLifespanDays(days) },
                                        label = { Text("$days d") }
                                    )
                                }
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
                            Text("Configuration Backup (SAF)", style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = "Export or restore firewall toggles, custom domain/IP rules, selected blocklists, and custom DNS resolvers as JSON.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = { exportLauncher.launch("rethink_backup.json") },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("export_backup_button")
                                ) {
                                    Icon(Icons.Filled.CloudUpload, contentDescription = null)
                                    Text(" Export")
                                }
                                OutlinedButton(
                                    onClick = { importLauncher.launch(arrayOf("application/json", "text/plain")) },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("import_backup_button")
                                ) {
                                    Icon(Icons.Filled.CloudDownload, contentDescription = null)
                                    Text(" Restore")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AboutScreen(
    activeResolverName: String,
    activeResolverUrl: String,
    probeState: ProbeUiState,
    onRunProbe: (String) -> Unit,
    onResetProbe: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showProbeDialog by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 720.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.large)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_shield_hero),
                            contentDescription = "Rethink Shield",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .clip(RoundedCornerShape(14.dp))
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Shield,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Column {
                                Text("Rethink DNS + Firewall", style = MaterialTheme.typography.headlineMedium)
                                Text(
                                    text = "Version ${BuildConfig.VERSION_NAME} • Apache-2.0 Open Source",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Text(
                            text = "Modern Jetpack Compose Android rewrite featuring local VpnService DNS interception, encrypted RFC 8484 DNS-over-HTTPS resolution, Room telemetry, per-app firewall rules, and filetag.json blocklists.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
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
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Live Diagnostic Probe", style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = "Resolver: $activeResolverName",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Button(
                            onClick = { showProbeDialog = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("about_run_probe_button")
                        ) {
                            Icon(Icons.Filled.Speed, contentDescription = null)
                            Text(" Test Active DoH Resolver")
                        }
                    }
                }
            }

            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("Architecture Notes", style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = "• Local VPN inspects UDP port 53 packets and resolves queries over OkHttp DoH.\n• Full userspace gVisor/Go TCP socket proxy and Rethink+ relay billing remain stubbed (TODO).",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }

    if (showProbeDialog) {
        DnsProbeDialog(
            activeResolverName = activeResolverName,
            activeResolverUrl = activeResolverUrl,
            probeState = probeState,
            onRunProbe = onRunProbe,
            onDismiss = {
                onResetProbe()
                showProbeDialog = false
            }
        )
    }
}
