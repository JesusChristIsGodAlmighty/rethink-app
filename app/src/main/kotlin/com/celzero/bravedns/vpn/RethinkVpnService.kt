package com.celzero.bravedns.vpn

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ServiceInfo
import android.net.VpnService
import android.os.Build
import android.os.ParcelFileDescriptor
import androidx.core.app.NotificationCompat
import com.celzero.bravedns.MainActivity
import com.celzero.bravedns.R
import com.celzero.bravedns.data.SettingsDataStore
import com.celzero.bravedns.data.db.ConnectionLogEntity
import com.celzero.bravedns.data.db.DnsLogEntity
import com.celzero.bravedns.data.db.RethinkDatabase
import com.celzero.bravedns.model.AppFirewallStatus
import com.celzero.bravedns.model.DnsProtocolType
import com.celzero.bravedns.model.RuleStatus
import com.celzero.bravedns.model.VpnTunnelState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.FileOutputStream

class RethinkVpnService : VpnService() {

    companion object {
        const val ACTION_START = "com.celzero.bravedns.action.START_VPN"
        const val ACTION_PAUSE = "com.celzero.bravedns.action.PAUSE_VPN"
        const val ACTION_RESUME = "com.celzero.bravedns.action.RESUME_VPN"
        const val ACTION_STOP = "com.celzero.bravedns.action.STOP_VPN"

        private const val CHANNEL_ID = "rethink_vpn_channel"
        private const val NOTIF_ID = 1001
        private const val TUN_ADDRESS = "10.111.222.1"
        private const val TUN_DNS_SERVER = "10.111.222.3"
    }

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var tunInterface: ParcelFileDescriptor? = null
    private var packetJob: Job? = null
    @Volatile
    private var isPaused: Boolean = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopTunnel()
                return START_NOT_STICKY
            }
            ACTION_PAUSE -> {
                isPaused = true
                VpnStateHolder.setState(VpnTunnelState.PAUSED)
                updateNotification(paused = true)
                return START_STICKY
            }
            ACTION_RESUME -> {
                isPaused = false
                VpnStateHolder.setState(VpnTunnelState.ACTIVE)
                updateNotification(paused = false)
                return START_STICKY
            }
            else -> {
                isPaused = false
                startTunnel()
                return START_STICKY
            }
        }
    }

    private fun startTunnel() {
        createNotificationChannel()
        val notification = buildNotification(paused = false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                NOTIF_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
            )
        } else {
            startForeground(NOTIF_ID, notification)
        }

        VpnStateHolder.setState(VpnTunnelState.STARTING)
        packetJob?.cancel()
        tunInterface?.close()

        serviceScope.launch {
            val settings = SettingsDataStore(applicationContext).current()
            val db = RethinkDatabase.getInstance(applicationContext)
            val excludedApps = db.appInfoDao().getAllOnce()
                .filter { it.firewallStatus == AppFirewallStatus.EXCLUDE.id }
                .map { it.packageName }

            val builder = Builder()
                .setSession(getString(R.string.app_name))
                .setMtu(settings.mtu)
                .addAddress(TUN_ADDRESS, 24)
                .addDnsServer(TUN_DNS_SERVER)
                .addRoute(TUN_DNS_SERVER, 32)

            runCatching { builder.addDisallowedApplication(packageName) }
            excludedApps.forEach { pkg ->
                runCatching { builder.addDisallowedApplication(pkg) }
            }
            if (settings.allowBypass) {
                builder.allowBypass()
            }

            val fd = runCatching { builder.establish() }.getOrNull()
            if (fd == null) {
                VpnStateHolder.setState(VpnTunnelState.STOPPED)
                stopSelf()
                return@launch
            }

            tunInterface = fd
            VpnStateHolder.setState(VpnTunnelState.ACTIVE)
            runPacketLoop(fd, db, settings.activeResolverName, settings.activeResolverUrl)
        }
    }

    private fun runPacketLoop(
        fd: ParcelFileDescriptor,
        db: RethinkDatabase,
        initialResolverName: String,
        initialResolverUrl: String
    ) {
        val settingsStore = SettingsDataStore(applicationContext)
        packetJob = serviceScope.launch {
            val input = FileInputStream(fd.fileDescriptor)
            val output = FileOutputStream(fd.fileDescriptor)
            val buffer = ByteArray(4096)

            while (isActive) {
                val len = try {
                    input.read(buffer)
                } catch (_: Exception) {
                    break
                }
                if (len <= 0) continue

                val udpPacket = DnsPacketParser.parseIpv4UdpPacket(buffer, len) ?: continue
                if (udpPacket.dstPort != 53) continue
                val query = DnsPacketParser.parseDnsQuery(udpPacket.dnsPayload) ?: continue

                val currentSettings = settingsStore.current()
                val customRules = db.customDomainDao().getAllOnce()
                val selectedTags = db.blocklistTagDao().getSelectedOnce()

                val domainLower = query.domain
                val matchedRule = customRules.firstOrNull { rule ->
                    val target = rule.domain.lowercase().removePrefix("*.")
                    domainLower == target || (rule.isWildcard && domainLower.endsWith(".$target"))
                }

                val isBlockedByCustom = matchedRule?.status == RuleStatus.BLOCK.id
                val isTrustedByCustom = matchedRule?.status == RuleStatus.TRUST.id
                val isHeuristicAdDomain = !isTrustedByCustom &&
                    selectedTags.isNotEmpty() &&
                    isPaused.not() &&
                    currentSettings.braveMode.isDnsActive &&
                    isCommonTrackerDomain(domainLower)

                val shouldBlock = !isPaused && currentSettings.braveMode.isDnsActive &&
                    (isBlockedByCustom || isHeuristicAdDomain)

                if (shouldBlock) {
                    val nxResponse = DnsPacketParser.buildNxDomainResponse(udpPacket.dnsPayload)
                    val responsePacket = DnsPacketParser.buildIpv4UdpResponsePacket(udpPacket, nxResponse)
                    runCatching { output.write(responsePacket) }

                    val reason = if (isBlockedByCustom) "Rule #2E (Custom Domain)" else "Rule #2G (Blocklist)"
                    val tagNames = if (isBlockedByCustom) "Custom Block" else selectedTags.take(2).joinToString(", ") { it.vname }
                    VpnStateHolder.recordQuery(domainLower, blocked = true, latencyMs = 0L)

                    if (currentSettings.logsEnabled) {
                        db.dnsLogDao().insert(
                            DnsLogEntity(
                                queryStr = domainLower,
                                typeName = query.queryTypeName,
                                resolverName = currentSettings.activeResolverName,
                                latencyMs = 0L,
                                isBlocked = true,
                                blockedReason = reason,
                                blocklists = tagNames,
                                status = "NXDOMAIN"
                            )
                        )
                        db.connectionLogDao().insert(
                            ConnectionLogEntity(
                                appName = "DNS Resolver",
                                ipAddress = TUN_DNS_SERVER,
                                port = 53,
                                protocol = "UDP",
                                isBlocked = true,
                                blockedByRule = reason,
                                dnsQuery = domainLower
                            )
                        )
                    }
                } else {
                    val resolverUrl = if (currentSettings.dnsType == DnsProtocolType.SYSTEM_DNS) {
                        "https://dns.google/dns-query"
                    } else {
                        currentSettings.activeResolverUrl.ifBlank { initialResolverUrl }
                    }
                    val (rawResp, latency) = DohClient.resolveRawBlocking(resolverUrl, udpPacket.dnsPayload)
                    if (rawResp != null) {
                        val responsePacket = DnsPacketParser.buildIpv4UdpResponsePacket(udpPacket, rawResp)
                        runCatching { output.write(responsePacket) }
                        val ips = DnsPacketParser.extractAnswerIps(rawResp)
                        VpnStateHolder.recordQuery(domainLower, blocked = false, latencyMs = latency)

                        if (currentSettings.logsEnabled) {
                            db.dnsLogDao().insert(
                                DnsLogEntity(
                                    queryStr = domainLower,
                                    typeName = query.queryTypeName,
                                    resolverName = currentSettings.activeResolverName.ifBlank { initialResolverName },
                                    latencyMs = latency,
                                    isBlocked = false,
                                    responseIps = ips.joinToString(", "),
                                    status = "COMPLETE"
                                )
                            )
                            db.connectionLogDao().insert(
                                ConnectionLogEntity(
                                    appName = "DNS Resolver",
                                    ipAddress = ips.firstOrNull() ?: TUN_DNS_SERVER,
                                    port = 443,
                                    protocol = "DoH",
                                    isBlocked = false,
                                    blockedByRule = if (isTrustedByCustom) "Rule #2F (Trusted)" else "No Rule",
                                    dnsQuery = domainLower,
                                    downloadBytes = rawResp.size.toLong(),
                                    uploadBytes = udpPacket.dnsPayload.size.toLong()
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun isCommonTrackerDomain(domain: String): Boolean {
        val trackerMarkers = listOf(
            "doubleclick.net",
            "googlesyndication.com",
            "googleadservices.com",
            "app-measurement.com",
            "ads.",
            "adservice.",
            "telemetry.",
            "analytics.",
            "mixpanel.com",
            "appsflyer.com",
            "adjust.com",
            "facebook.com"
        )
        return trackerMarkers.any { marker ->
            domain == marker || domain.endsWith(".$marker") || domain.startsWith(marker)
        }
    }

    private fun stopTunnel() {
        packetJob?.cancel()
        runCatching { tunInterface?.close() }
        tunInterface = null
        VpnStateHolder.setState(VpnTunnelState.STOPPED)
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun updateNotification(paused: Boolean) {
        val mgr = getSystemService(NotificationManager::class.java)
        mgr.notify(NOTIF_ID, buildNotification(paused))
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mgr = getSystemService(NotificationManager::class.java)
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.vpn_service_notification_channel),
                NotificationManager.IMPORTANCE_LOW
            )
            mgr.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(paused: Boolean): Notification {
        val openAppIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val toggleIntent = PendingIntent.getService(
            this,
            1,
            Intent(this, RethinkVpnService::class.java).apply {
                action = if (paused) ACTION_RESUME else ACTION_PAUSE
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val stopIntent = PendingIntent.getService(
            this,
            2,
            Intent(this, RethinkVpnService::class.java).apply { action = ACTION_STOP },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_lock)
            .setContentTitle(
                getString(if (paused) R.string.vpn_notification_title_paused else R.string.vpn_notification_title_active)
            )
            .setContentText(
                getString(if (paused) R.string.vpn_notification_body_paused else R.string.vpn_notification_body_active)
            )
            .setOngoing(true)
            .setContentIntent(openAppIntent)
            .addAction(
                0,
                getString(if (paused) R.string.action_resume else R.string.action_pause),
                toggleIntent
            )
            .addAction(0, getString(R.string.action_stop), stopIntent)
            .build()
    }

    override fun onDestroy() {
        packetJob?.cancel()
        runCatching { tunInterface?.close() }
        serviceScope.cancel()
        VpnStateHolder.setState(VpnTunnelState.STOPPED)
        super.onDestroy()
    }
}
