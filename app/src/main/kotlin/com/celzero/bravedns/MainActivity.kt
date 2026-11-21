package com.celzero.bravedns

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.VpnService
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.celzero.bravedns.model.VpnTunnelState
import com.celzero.bravedns.ui.navigation.AboutRoute
import com.celzero.bravedns.ui.navigation.AppsFirewallRoute
import com.celzero.bravedns.ui.navigation.BackupAppearanceRoute
import com.celzero.bravedns.ui.navigation.BlocklistsRoute
import com.celzero.bravedns.ui.navigation.ConfigureRoute
import com.celzero.bravedns.ui.navigation.CustomRulesRoute
import com.celzero.bravedns.ui.navigation.DnsConfigRoute
import com.celzero.bravedns.ui.navigation.FirewallRulesRoute
import com.celzero.bravedns.ui.navigation.HomeRoute
import com.celzero.bravedns.ui.navigation.NetworkLogsRoute
import com.celzero.bravedns.ui.navigation.ProxyWgRoute
import com.celzero.bravedns.ui.navigation.StatsRoute
import com.celzero.bravedns.ui.navigation.TunnelSettingsRoute
import com.celzero.bravedns.ui.screens.AboutScreen
import com.celzero.bravedns.ui.screens.AppsFirewallScreen
import com.celzero.bravedns.ui.screens.BackupAppearanceScreen
import com.celzero.bravedns.ui.screens.BlocklistsScreen
import com.celzero.bravedns.ui.screens.ConfigureScreen
import com.celzero.bravedns.ui.screens.CustomRulesScreen
import com.celzero.bravedns.ui.screens.DnsConfigScreen
import com.celzero.bravedns.ui.screens.FirewallRulesScreen
import com.celzero.bravedns.ui.screens.HomeScreen
import com.celzero.bravedns.ui.screens.NetworkLogsScreen
import com.celzero.bravedns.ui.screens.ProxyWgScreen
import com.celzero.bravedns.ui.screens.StatsScreen
import com.celzero.bravedns.ui.screens.TunnelSettingsScreen
import com.celzero.bravedns.ui.theme.RethinkTheme
import com.celzero.bravedns.ui.viewmodel.ConfigureViewModel
import com.celzero.bravedns.ui.viewmodel.LogsStatsViewModel
import com.celzero.bravedns.ui.viewmodel.MainViewModel
import com.celzero.bravedns.ui.viewmodel.RethinkViewModelFactory
import com.celzero.bravedns.vpn.RethinkVpnService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val app = application as RethinkApp
        val factory = RethinkViewModelFactory(app.repository)

        setContent {
            val mainVm: MainViewModel = viewModel(factory = factory)
            val configureVm: ConfigureViewModel = viewModel(factory = factory)
            val logsStatsVm: LogsStatsViewModel = viewModel(factory = factory)

            val settings by configureVm.settings.collectAsStateWithLifecycle()

            RethinkTheme(themeMode = settings.themeMode) {
                RethinkRootContent(
                    mainVm = mainVm,
                    configureVm = configureVm,
                    logsStatsVm = logsStatsVm
                )
            }
        }
    }
}

private data class TopTabItem(
    val label: String,
    val route: Any,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
)

@Composable
private fun RethinkRootContent(
    mainVm: MainViewModel,
    configureVm: ConfigureViewModel,
    logsStatsVm: LogsStatsViewModel
) {
    val context = LocalContext.current
    val navController = rememberNavController()

    val homeState by mainVm.uiState.collectAsStateWithLifecycle()
    val probeState by mainVm.probeState.collectAsStateWithLifecycle()
    val settings by configureVm.settings.collectAsStateWithLifecycle()
    val apps by configureVm.apps.collectAsStateWithLifecycle()
    val dnsEndpoints by configureVm.dnsEndpoints.collectAsStateWithLifecycle()
    val blocklistTags by configureVm.blocklistTags.collectAsStateWithLifecycle()
    val customDomains by configureVm.customDomains.collectAsStateWithLifecycle()
    val customIps by configureVm.customIps.collectAsStateWithLifecycle()
    val wireGuardConfigs by configureVm.wireGuardConfigs.collectAsStateWithLifecycle()
    val proxyConfigs by configureVm.proxyConfigs.collectAsStateWithLifecycle()
    val configStatusMessage by configureVm.statusMessage.collectAsStateWithLifecycle()

    val statsOverview by logsStatsVm.statsOverview.collectAsStateWithLifecycle()
    val statsWindow by logsStatsVm.timeWindow.collectAsStateWithLifecycle()
    val statsCategory by logsStatsVm.category.collectAsStateWithLifecycle()
    val dnsLogs by logsStatsVm.dnsLogs.collectAsStateWithLifecycle()
    val connLogs by logsStatsVm.connectionLogs.collectAsStateWithLifecycle()

    fun sendVpnAction(action: String) {
        val intent = Intent(context, RethinkVpnService::class.java).apply {
            this.action = action
        }
        ContextCompat.startForegroundService(context, intent)
    }

    val vpnPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            sendVpnAction(RethinkVpnService.ACTION_START)
        }
    }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        val prepareIntent = VpnService.prepare(context)
        if (prepareIntent != null) {
            vpnPermissionLauncher.launch(prepareIntent)
        } else {
            sendVpnAction(RethinkVpnService.ACTION_START)
        }
    }

    fun startVpnWithPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (!granted) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                return
            }
        }
        val prepareIntent = VpnService.prepare(context)
        if (prepareIntent != null) {
            vpnPermissionLauncher.launch(prepareIntent)
        } else {
            sendVpnAction(RethinkVpnService.ACTION_START)
        }
    }

    fun toggleVpn() {
        when (homeState.vpnStatus.state) {
            VpnTunnelState.STOPPED -> startVpnWithPermissions()
            VpnTunnelState.STARTING, VpnTunnelState.ACTIVE, VpnTunnelState.PAUSED -> {
                val stopIntent = Intent(context, RethinkVpnService::class.java).apply {
                    action = RethinkVpnService.ACTION_STOP
                }
                context.startService(stopIntent)
            }
        }
    }

    fun pauseResumeVpn() {
        val action = if (homeState.vpnStatus.state == VpnTunnelState.PAUSED) {
            RethinkVpnService.ACTION_RESUME
        } else {
            RethinkVpnService.ACTION_PAUSE
        }
        val intent = Intent(context, RethinkVpnService::class.java).apply {
            this.action = action
        }
        context.startService(intent)
    }

    val topTabs = listOf(
        TopTabItem("Home", HomeRoute, Icons.Filled.Shield, Icons.Outlined.Shield, "nav_home"),
        TopTabItem("Stats", StatsRoute, Icons.Filled.BarChart, Icons.Outlined.BarChart, "nav_stats"),
        TopTabItem("Configure", ConfigureRoute, Icons.Filled.Tune, Icons.Outlined.Tune, "nav_configure"),
        TopTabItem("About", AboutRoute, Icons.Filled.Info, Icons.Outlined.Info, "nav_about")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDest = navBackStackEntry?.destination
    val isTopLevel = currentDest == null ||
        currentDest.hasRoute<HomeRoute>() ||
        currentDest.hasRoute<StatsRoute>() ||
        currentDest.hasRoute<ConfigureRoute>() ||
        currentDest.hasRoute<AboutRoute>()

    fun navigateTopTab(route: Any) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useRail = maxWidth >= 600.dp

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = {
                if (isTopLevel && !useRail) {
                    NavigationBar(windowInsets = WindowInsets.navigationBars) {
                        topTabs.forEach { tab ->
                            val selected = when (tab.route) {
                                is HomeRoute -> currentDest?.hasRoute<HomeRoute>() == true
                                is StatsRoute -> currentDest?.hasRoute<StatsRoute>() == true
                                is ConfigureRoute -> currentDest?.hasRoute<ConfigureRoute>() == true
                                is AboutRoute -> currentDest?.hasRoute<AboutRoute>() == true
                                else -> false
                            }
                            NavigationBarItem(
                                selected = selected,
                                onClick = { navigateTopTab(tab.route) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                        contentDescription = tab.label
                                    )
                                },
                                label = { Text(tab.label) },
                                modifier = Modifier.testTag(tab.testTag)
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (isTopLevel && useRail) {
                    NavigationRail {
                        topTabs.forEach { tab ->
                            val selected = when (tab.route) {
                                is HomeRoute -> currentDest?.hasRoute<HomeRoute>() == true
                                is StatsRoute -> currentDest?.hasRoute<StatsRoute>() == true
                                is ConfigureRoute -> currentDest?.hasRoute<ConfigureRoute>() == true
                                is AboutRoute -> currentDest?.hasRoute<AboutRoute>() == true
                                else -> false
                            }
                            NavigationRailItem(
                                selected = selected,
                                onClick = { navigateTopTab(tab.route) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                        contentDescription = tab.label
                                    )
                                },
                                label = { Text(tab.label) },
                                modifier = Modifier.testTag("${tab.testTag}_rail")
                            )
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute
                    ) {
                        composable<HomeRoute> {
                            HomeScreen(
                                uiState = homeState,
                                probeState = probeState,
                                onToggleVpn = { toggleVpn() },
                                onPauseResumeVpn = { pauseResumeVpn() },
                                onSelectBraveMode = { mainVm.setBraveMode(it) },
                                onRunProbe = { mainVm.runLiveDnsProbe(it) },
                                onResetProbe = { mainVm.resetProbeState() },
                                onDismissOnboarding = { mainVm.dismissOnboarding() },
                                onOpenApps = { navController.navigate(AppsFirewallRoute) },
                                onOpenDns = { navController.navigate(DnsConfigRoute) },
                                onOpenBlocklists = { navController.navigate(BlocklistsRoute) },
                                onOpenFirewallRules = { navController.navigate(FirewallRulesRoute) },
                                onOpenLogs = { navController.navigate(NetworkLogsRoute) },
                                onOpenProxyWg = { navController.navigate(ProxyWgRoute) }
                            )
                        }

                        composable<StatsRoute> {
                            StatsScreen(
                                overview = statsOverview,
                                selectedWindow = statsWindow,
                                selectedCategory = statsCategory,
                                onSelectWindow = { logsStatsVm.setTimeWindow(it) },
                                onSelectCategory = { logsStatsVm.setCategory(it) },
                                onOpenLogs = { navController.navigate(NetworkLogsRoute) }
                            )
                        }

                        composable<ConfigureRoute> {
                            ConfigureScreen(
                                activeResolver = settings.activeResolverName,
                                proxyModeLabel = settings.proxyMode.label,
                                onOpenApps = { navController.navigate(AppsFirewallRoute) },
                                onOpenDns = { navController.navigate(DnsConfigRoute) },
                                onOpenBlocklists = { navController.navigate(BlocklistsRoute) },
                                onOpenFirewallRules = { navController.navigate(FirewallRulesRoute) },
                                onOpenCustomRules = { navController.navigate(CustomRulesRoute) },
                                onOpenProxyWg = { navController.navigate(ProxyWgRoute) },
                                onOpenLogs = { navController.navigate(NetworkLogsRoute) },
                                onOpenTunnel = { navController.navigate(TunnelSettingsRoute) },
                                onOpenBackupAppearance = { navController.navigate(BackupAppearanceRoute) }
                            )
                        }

                        composable<AboutRoute> {
                            AboutScreen(
                                activeResolverName = settings.activeResolverName,
                                activeResolverUrl = settings.activeResolverUrl,
                                probeState = probeState,
                                onRunProbe = { mainVm.runLiveDnsProbe(it) },
                                onResetProbe = { mainVm.resetProbeState() }
                            )
                        }

                        composable<AppsFirewallRoute> {
                            AppsFirewallScreen(
                                apps = apps,
                                onBack = { navController.popBackStack() },
                                onRefreshApps = { configureVm.refreshInstalledApps() },
                                onUpdateConnectionStatus = { app, st ->
                                    configureVm.updateAppConnectionStatus(app, st)
                                },
                                onUpdateFirewallStatus = { app, fw ->
                                    configureVm.updateAppFirewallStatus(app, fw)
                                }
                            )
                        }

                        composable<DnsConfigRoute> {
                            DnsConfigScreen(
                                endpoints = dnsEndpoints,
                                statusMessage = configStatusMessage,
                                onBack = {
                                    configureVm.clearStatusMessage()
                                    navController.popBackStack()
                                },
                                onSelectEndpoint = { configureVm.selectDnsEndpoint(it) },
                                onProbeEndpoint = { configureVm.probeEndpointLatency(it) },
                                onAddCustomEndpoint = { name, url, proto ->
                                    configureVm.addCustomDnsEndpoint(name, url, proto)
                                },
                                onDeleteCustomEndpoint = { configureVm.deleteCustomDnsEndpoint(it) },
                                onOpenBlocklists = { navController.navigate(BlocklistsRoute) }
                            )
                        }

                        composable<BlocklistsRoute> {
                            BlocklistsScreen(
                                tags = blocklistTags,
                                onBack = { navController.popBackStack() },
                                onToggleTag = { configureVm.toggleBlocklistTag(it) },
                                onClearAll = { configureVm.clearBlocklistSelections() }
                            )
                        }

                        composable<FirewallRulesRoute> {
                            FirewallRulesScreen(
                                settings = settings,
                                onBack = { navController.popBackStack() },
                                onToggleFlag = { key, value -> configureVm.updateFirewallFlag(key, value) },
                                onOpenCustomRules = { navController.navigate(CustomRulesRoute) }
                            )
                        }

                        composable<CustomRulesRoute> {
                            CustomRulesScreen(
                                domains = customDomains,
                                ips = customIps,
                                onBack = { navController.popBackStack() },
                                onAddDomainRule = { domain, status ->
                                    configureVm.addCustomDomainRule(domain, status)
                                },
                                onDeleteDomainRule = { configureVm.deleteCustomDomainRule(it) },
                                onAddIpRule = { ip, port, proto, status ->
                                    configureVm.addCustomIpRule(ip, port, proto, status)
                                },
                                onDeleteIpRule = { configureVm.deleteCustomIpRule(it) }
                            )
                        }

                        composable<ProxyWgRoute> {
                            ProxyWgScreen(
                                settings = settings,
                                wireGuardConfigs = wireGuardConfigs,
                                proxyConfigs = proxyConfigs,
                                statusMessage = configStatusMessage,
                                onBack = {
                                    configureVm.clearStatusMessage()
                                    navController.popBackStack()
                                },
                                onSetProxyMode = { configureVm.setProxyMode(it) },
                                onImportWireGuard = { name, raw ->
                                    configureVm.importWireGuardConfig(name, raw)
                                },
                                onToggleWireGuardActive = { configureVm.toggleWireGuardActive(it) },
                                onDeleteWireGuard = { configureVm.deleteWireGuardConfig(it) },
                                onAddProxy = { name, mode, host, port, user ->
                                    configureVm.addProxyConfig(name, mode, host, port, user)
                                },
                                onToggleProxyActive = { configureVm.toggleProxyActive(it) },
                                onDeleteProxy = { configureVm.deleteProxyConfig(it) }
                            )
                        }

                        composable<NetworkLogsRoute> {
                            NetworkLogsScreen(
                                dnsLogs = dnsLogs,
                                connectionLogs = connLogs,
                                onBack = { navController.popBackStack() },
                                onSetDomainRule = { domain, status ->
                                    logsStatsVm.setDomainRule(domain, status)
                                },
                                onSetIpRule = { ip, status ->
                                    logsStatsVm.setIpRule(ip, status)
                                },
                                onClearAllLogs = { logsStatsVm.clearAllLogs() }
                            )
                        }

                        composable<TunnelSettingsRoute> {
                            TunnelSettingsScreen(
                                settings = settings,
                                statusMessage = configStatusMessage,
                                onBack = {
                                    configureVm.clearStatusMessage()
                                    navController.popBackStack()
                                },
                                onSaveTunnel = { ipMode, mtu, gateway, dns ->
                                    configureVm.setTunnelSettings(ipMode, mtu, gateway, dns)
                                },
                                onToggleFlag = { key, value -> configureVm.updateFirewallFlag(key, value) },
                                onSetAntiCensorship = { strategy, timeout ->
                                    configureVm.setAntiCensorship(strategy, timeout)
                                }
                            )
                        }

                        composable<BackupAppearanceRoute> {
                            BackupAppearanceScreen(
                                settings = settings,
                                statusMessage = configStatusMessage,
                                onBack = {
                                    configureVm.clearStatusMessage()
                                    navController.popBackStack()
                                },
                                onSetThemeMode = { configureVm.setThemeMode(it) },
                                onToggleLogsEnabled = { configureVm.updateFirewallFlag("logsEnabled", it) },
                                onSetLogLifespanDays = { configureVm.setLogLifespanDays(it) },
                                onExportBackupJson = { configureVm.exportBackupJson() },
                                onImportBackupJson = { configureVm.importBackupJson(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}
