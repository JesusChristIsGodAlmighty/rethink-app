package com.celzero.bravedns.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.celzero.bravedns.sponsor.database.SponsorDao
import com.celzero.bravedns.sponsor.database.SponsorDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _appInfoDAO: Lazy<AppInfoDAO> = lazy {
    AppInfoDAO_Impl(this)
  }

  private val _doHEndpointDAO: Lazy<DoHEndpointDAO> = lazy {
    DoHEndpointDAO_Impl(this)
  }

  private val _dnsCryptEndpointDAO: Lazy<DnsCryptEndpointDAO> = lazy {
    DnsCryptEndpointDAO_Impl(this)
  }

  private val _dnsCryptRelayEndpointDAO: Lazy<DnsCryptRelayEndpointDAO> = lazy {
    DnsCryptRelayEndpointDAO_Impl(this)
  }

  private val _dnsProxyEndpointDAO: Lazy<DnsProxyEndpointDAO> = lazy {
    DnsProxyEndpointDAO_Impl(this)
  }

  private val _proxyEndpointDAO: Lazy<ProxyEndpointDAO> = lazy {
    ProxyEndpointDAO_Impl(this)
  }

  private val _customDomainDAO: Lazy<CustomDomainDAO> = lazy {
    CustomDomainDAO_Impl(this)
  }

  private val _customIpDao: Lazy<CustomIpDao> = lazy {
    CustomIpDao_Impl(this)
  }

  private val _rethinkDnsEndpointDao: Lazy<RethinkDnsEndpointDao> = lazy {
    RethinkDnsEndpointDao_Impl(this)
  }

  private val _rethinkRemoteFileTagDao: Lazy<RethinkRemoteFileTagDao> = lazy {
    RethinkRemoteFileTagDao_Impl(this)
  }

  private val _rethinkLocalFileTagDao: Lazy<RethinkLocalFileTagDao> = lazy {
    RethinkLocalFileTagDao_Impl(this)
  }

  private val _localBlocklistPacksMapDao: Lazy<LocalBlocklistPacksMapDao> = lazy {
    LocalBlocklistPacksMapDao_Impl(this)
  }

  private val _remoteBlocklistPacksMapDao: Lazy<RemoteBlocklistPacksMapDao> = lazy {
    RemoteBlocklistPacksMapDao_Impl(this)
  }

  private val _appDatabaseRawQueryDao: Lazy<AppDatabaseRawQueryDao> = lazy {
    AppDatabaseRawQueryDao_Impl(this)
  }

  private val _wgConfigFilesDAO: Lazy<WgConfigFilesDAO> = lazy {
    WgConfigFilesDAO_Impl(this)
  }

  private val _proxyApplicationMappingDAO: Lazy<ProxyApplicationMappingDAO> = lazy {
    ProxyApplicationMappingDAO_Impl(this)
  }

  private val _tcpProxyDAO: Lazy<TcpProxyDAO> = lazy {
    TcpProxyDAO_Impl(this)
  }

  private val _doTEndpointDAO: Lazy<DoTEndpointDAO> = lazy {
    DoTEndpointDAO_Impl(this)
  }

  private val _oDoHEndpointDAO: Lazy<ODoHEndpointDAO> = lazy {
    ODoHEndpointDAO_Impl(this)
  }

  private val _rpnProxyDao: Lazy<RpnProxyDao> = lazy {
    RpnProxyDao_Impl(this)
  }

  private val _wgHopMapDao: Lazy<WgHopMapDao> = lazy {
    WgHopMapDao_Impl(this)
  }

  private val _subscriptionStatusDao: Lazy<SubscriptionStatusDao> = lazy {
    SubscriptionStatusDao_Impl(this)
  }

  private val _subscriptionStateHistoryDao: Lazy<SubscriptionStateHistoryDao> = lazy {
    SubscriptionStateHistoryDao_Impl(this)
  }

  private val _sponsorDao: Lazy<SponsorDao> = lazy {
    SponsorDao_Impl(this)
  }

  private val _countryConfigDAO: Lazy<CountryConfigDAO> = lazy {
    CountryConfigDAO_Impl(this)
  }

  private val _smartDnsEndpointDAO: Lazy<SmartDnsEndpointDAO> = lazy {
    SmartDnsEndpointDAO_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(36, "8e9569b41271ff7b0b607d4df19a9f14", "81712b31bc7e140456aa7bcf8f4bfb41") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `AppInfo` (`packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `uid` INTEGER NOT NULL, `isSystemApp` INTEGER NOT NULL, `firewallStatus` INTEGER NOT NULL, `appCategory` TEXT NOT NULL, `wifiDataUsed` INTEGER NOT NULL, `mobileDataUsed` INTEGER NOT NULL, `connectionStatus` INTEGER NOT NULL, `screenOffAllowed` INTEGER NOT NULL, `backgroundAllowed` INTEGER NOT NULL, `uploadBytes` INTEGER NOT NULL, `downloadBytes` INTEGER NOT NULL, `isProxyExcluded` INTEGER NOT NULL, `tombstoneTs` INTEGER NOT NULL, `modifiedTs` INTEGER NOT NULL, `tempAllowEnabled` INTEGER NOT NULL, `tempAllowExpiryTime` INTEGER NOT NULL, `notes` TEXT NOT NULL DEFAULT '', PRIMARY KEY(`uid`, `packageName`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `CustomIp` (`uid` INTEGER NOT NULL, `ipAddress` TEXT NOT NULL, `port` INTEGER NOT NULL, `protocol` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `proxyId` TEXT NOT NULL, `proxyCC` TEXT NOT NULL, `status` INTEGER NOT NULL, `wildcard` INTEGER NOT NULL, `ruleType` INTEGER NOT NULL, `modifiedDateTime` INTEGER NOT NULL, PRIMARY KEY(`uid`, `ipAddress`, `port`, `protocol`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `DoHEndpoint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dohName` TEXT NOT NULL, `dohURL` TEXT NOT NULL, `dohIp` TEXT, `dohExplanation` TEXT, `isSelected` INTEGER NOT NULL, `isCustom` INTEGER NOT NULL, `isSecure` INTEGER NOT NULL, `modifiedDataTime` INTEGER NOT NULL, `latency` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `DNSCryptEndpoint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dnsCryptName` TEXT NOT NULL, `dnsCryptURL` TEXT NOT NULL, `dnsCryptExplanation` TEXT, `isSelected` INTEGER NOT NULL, `isCustom` INTEGER NOT NULL, `modifiedDataTime` INTEGER NOT NULL, `latency` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `DNSProxyEndpoint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `proxyName` TEXT NOT NULL, `proxyType` TEXT NOT NULL, `proxyAppName` TEXT, `proxyIP` TEXT, `proxyPort` INTEGER NOT NULL, `isSelected` INTEGER NOT NULL, `isCustom` INTEGER NOT NULL, `modifiedDataTime` INTEGER NOT NULL, `latency` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `DNSCryptRelayEndpoint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dnsCryptRelayName` TEXT NOT NULL, `dnsCryptRelayURL` TEXT NOT NULL, `dnsCryptRelayExplanation` TEXT, `isSelected` INTEGER NOT NULL, `isCustom` INTEGER NOT NULL, `modifiedDataTime` INTEGER NOT NULL, `latency` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `ProxyEndpoint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `proxyName` TEXT NOT NULL, `proxyMode` INTEGER NOT NULL, `proxyType` TEXT NOT NULL, `proxyAppName` TEXT, `proxyIP` TEXT, `proxyPort` INTEGER NOT NULL, `userName` TEXT, `password` TEXT, `isSelected` INTEGER NOT NULL, `isCustom` INTEGER NOT NULL, `isUDP` INTEGER NOT NULL, `modifiedDataTime` INTEGER NOT NULL, `latency` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `CustomDomain` (`domain` TEXT NOT NULL, `uid` INTEGER NOT NULL, `ips` TEXT NOT NULL, `status` INTEGER NOT NULL, `type` INTEGER NOT NULL, `proxyId` TEXT NOT NULL, `proxyCC` TEXT NOT NULL, `modifiedTs` INTEGER NOT NULL, `deletedTs` INTEGER NOT NULL, `version` INTEGER NOT NULL, PRIMARY KEY(`domain`, `uid`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `RethinkDnsEndpoint` (`name` TEXT NOT NULL, `url` TEXT NOT NULL, `uid` INTEGER NOT NULL, `desc` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `isCustom` INTEGER NOT NULL, `latency` INTEGER NOT NULL, `blocklistCount` INTEGER NOT NULL, `modifiedDataTime` INTEGER NOT NULL, PRIMARY KEY(`name`, `url`, `uid`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `RethinkRemoteFileTag` (`value` INTEGER NOT NULL, `uname` TEXT NOT NULL, `vname` TEXT NOT NULL, `group` TEXT NOT NULL, `subg` TEXT NOT NULL, `url` TEXT NOT NULL, `show` INTEGER NOT NULL, `entries` INTEGER NOT NULL, `pack` TEXT, `level` TEXT, `simpleTagId` INTEGER NOT NULL, `isSelected` INTEGER NOT NULL, PRIMARY KEY(`value`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `RethinkLocalFileTag` (`value` INTEGER NOT NULL, `uname` TEXT NOT NULL, `vname` TEXT NOT NULL, `group` TEXT NOT NULL, `subg` TEXT NOT NULL, `url` TEXT NOT NULL, `show` INTEGER NOT NULL, `entries` INTEGER NOT NULL, `pack` TEXT, `level` TEXT, `simpleTagId` INTEGER NOT NULL, `isSelected` INTEGER NOT NULL, PRIMARY KEY(`value`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `LocalBlocklistPacksMap` (`pack` TEXT NOT NULL, `level` INTEGER NOT NULL, `blocklistIds` TEXT NOT NULL, `group` TEXT NOT NULL, PRIMARY KEY(`pack`, `level`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `RemoteBlocklistPacksMap` (`pack` TEXT NOT NULL, `level` INTEGER NOT NULL, `blocklistIds` TEXT NOT NULL, `group` TEXT NOT NULL, PRIMARY KEY(`pack`, `level`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `WgConfigFiles` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `configPath` TEXT NOT NULL, `serverResponse` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `isCatchAll` INTEGER NOT NULL, `isLockdown` INTEGER NOT NULL, `oneWireGuard` INTEGER NOT NULL, `useOnlyOnMetered` INTEGER NOT NULL, `isDeletable` INTEGER NOT NULL, `ssidEnabled` INTEGER NOT NULL, `ssids` TEXT NOT NULL, `modifiedTs` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `ProxyApplicationMapping` (`uid` INTEGER NOT NULL, `packageName` TEXT NOT NULL, `proxyId` TEXT NOT NULL, `appName` TEXT NOT NULL, `proxyName` TEXT NOT NULL, `isActive` INTEGER NOT NULL, PRIMARY KEY(`uid`, `packageName`, `proxyId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `TcpProxyEndpoint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `token` TEXT NOT NULL, `url` TEXT NOT NULL, `paymentStatus` INTEGER NOT NULL, `isActive` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `DoTEndpoint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `desc` TEXT, `isSelected` INTEGER NOT NULL, `isCustom` INTEGER NOT NULL, `isSecure` INTEGER NOT NULL, `modifiedDataTime` INTEGER NOT NULL, `latency` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `ODoHEndpoint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `proxy` TEXT NOT NULL, `resolver` TEXT NOT NULL, `proxyIps` TEXT NOT NULL, `desc` TEXT, `isSelected` INTEGER NOT NULL, `isCustom` INTEGER NOT NULL, `modifiedDataTime` INTEGER NOT NULL, `latency` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `RpnProxy` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `configPath` TEXT NOT NULL, `serverResPath` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `isLockdown` INTEGER NOT NULL, `createdTs` INTEGER NOT NULL, `modifiedTs` INTEGER NOT NULL, `misc` TEXT NOT NULL, `tunId` TEXT NOT NULL, `latency` INTEGER NOT NULL, `lastRefreshTime` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `WgHopMap` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `src` TEXT NOT NULL, `hop` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `status` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `SubscriptionStatus` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `accountId` TEXT NOT NULL, `deviceId` TEXT NOT NULL, `purchaseToken` TEXT NOT NULL, `orderId` TEXT NOT NULL, `productId` TEXT NOT NULL, `planId` TEXT NOT NULL, `sessionToken` TEXT NOT NULL, `productTitle` TEXT NOT NULL, `state` INTEGER NOT NULL, `purchaseTime` INTEGER NOT NULL, `accountExpiry` INTEGER NOT NULL, `billingExpiry` INTEGER NOT NULL, `developerPayload` TEXT NOT NULL, `status` INTEGER NOT NULL, `windowDays` INTEGER NOT NULL, `lastUpdatedTs` INTEGER NOT NULL, `previousProductId` TEXT NOT NULL, `previousPurchaseToken` TEXT NOT NULL, `replacedAt` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `SubscriptionStateHistory` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `subscriptionId` INTEGER NOT NULL, `fromState` INTEGER NOT NULL, `toState` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `reason` TEXT)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `CountryConfig` (`id` TEXT NOT NULL, `cc` TEXT NOT NULL, `name` TEXT NOT NULL, `address` TEXT NOT NULL, `city` TEXT NOT NULL, `key` TEXT NOT NULL, `load` INTEGER NOT NULL, `link` INTEGER NOT NULL, `count` INTEGER NOT NULL, `premium` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, `isEnabled` INTEGER NOT NULL, `catchAll` INTEGER NOT NULL, `lockdown` INTEGER NOT NULL, `mobileOnly` INTEGER NOT NULL, `ssidBased` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `ssids` TEXT NOT NULL, `lastModified` INTEGER NOT NULL, `selectionCount` INTEGER NOT NULL, `isFavourite` INTEGER NOT NULL, `hopEnabled` INTEGER NOT NULL, `ipv4` INTEGER NOT NULL DEFAULT 0, `ipv6` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_CountryConfig_cc` ON `CountryConfig` (`cc`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_CountryConfig_isActive` ON `CountryConfig` (`isActive`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_CountryConfig_isFavourite` ON `CountryConfig` (`isFavourite`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Sponsor` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `purchase_token` TEXT NOT NULL, `product_id` TEXT NOT NULL, `purchase_time` INTEGER NOT NULL, `sponsor_since` INTEGER NOT NULL, `consumed` INTEGER NOT NULL, `contribution_count` INTEGER NOT NULL, `last_contribution_time` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `SmartDnsEndpoint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dnsName` TEXT NOT NULL, `dnsMode` INTEGER NOT NULL, `dnsExplanation` TEXT NOT NULL, `isSelected` INTEGER NOT NULL, `modifiedDataTime` INTEGER NOT NULL, `latency` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8e9569b41271ff7b0b607d4df19a9f14')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `AppInfo`")
        connection.execSQL("DROP TABLE IF EXISTS `CustomIp`")
        connection.execSQL("DROP TABLE IF EXISTS `DoHEndpoint`")
        connection.execSQL("DROP TABLE IF EXISTS `DNSCryptEndpoint`")
        connection.execSQL("DROP TABLE IF EXISTS `DNSProxyEndpoint`")
        connection.execSQL("DROP TABLE IF EXISTS `DNSCryptRelayEndpoint`")
        connection.execSQL("DROP TABLE IF EXISTS `ProxyEndpoint`")
        connection.execSQL("DROP TABLE IF EXISTS `CustomDomain`")
        connection.execSQL("DROP TABLE IF EXISTS `RethinkDnsEndpoint`")
        connection.execSQL("DROP TABLE IF EXISTS `RethinkRemoteFileTag`")
        connection.execSQL("DROP TABLE IF EXISTS `RethinkLocalFileTag`")
        connection.execSQL("DROP TABLE IF EXISTS `LocalBlocklistPacksMap`")
        connection.execSQL("DROP TABLE IF EXISTS `RemoteBlocklistPacksMap`")
        connection.execSQL("DROP TABLE IF EXISTS `WgConfigFiles`")
        connection.execSQL("DROP TABLE IF EXISTS `ProxyApplicationMapping`")
        connection.execSQL("DROP TABLE IF EXISTS `TcpProxyEndpoint`")
        connection.execSQL("DROP TABLE IF EXISTS `DoTEndpoint`")
        connection.execSQL("DROP TABLE IF EXISTS `ODoHEndpoint`")
        connection.execSQL("DROP TABLE IF EXISTS `RpnProxy`")
        connection.execSQL("DROP TABLE IF EXISTS `WgHopMap`")
        connection.execSQL("DROP TABLE IF EXISTS `SubscriptionStatus`")
        connection.execSQL("DROP TABLE IF EXISTS `SubscriptionStateHistory`")
        connection.execSQL("DROP TABLE IF EXISTS `CountryConfig`")
        connection.execSQL("DROP TABLE IF EXISTS `Sponsor`")
        connection.execSQL("DROP TABLE IF EXISTS `SmartDnsEndpoint`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsAppInfo: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAppInfo.put("packageName", TableInfo.Column("packageName", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("appName", TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("uid", TableInfo.Column("uid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("isSystemApp", TableInfo.Column("isSystemApp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("firewallStatus", TableInfo.Column("firewallStatus", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("appCategory", TableInfo.Column("appCategory", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("wifiDataUsed", TableInfo.Column("wifiDataUsed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("mobileDataUsed", TableInfo.Column("mobileDataUsed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("connectionStatus", TableInfo.Column("connectionStatus", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("screenOffAllowed", TableInfo.Column("screenOffAllowed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("backgroundAllowed", TableInfo.Column("backgroundAllowed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("uploadBytes", TableInfo.Column("uploadBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("downloadBytes", TableInfo.Column("downloadBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("isProxyExcluded", TableInfo.Column("isProxyExcluded", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("tombstoneTs", TableInfo.Column("tombstoneTs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("modifiedTs", TableInfo.Column("modifiedTs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("tempAllowEnabled", TableInfo.Column("tempAllowEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("tempAllowExpiryTime", TableInfo.Column("tempAllowExpiryTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAppInfo.put("notes", TableInfo.Column("notes", "TEXT", true, 0, "''", TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAppInfo: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAppInfo: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAppInfo: TableInfo = TableInfo("AppInfo", _columnsAppInfo, _foreignKeysAppInfo, _indicesAppInfo)
        val _existingAppInfo: TableInfo = read(connection, "AppInfo")
        if (!_infoAppInfo.equals(_existingAppInfo)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |AppInfo(com.celzero.bravedns.database.AppInfo).
              | Expected:
              |""".trimMargin() + _infoAppInfo + """
              |
              | Found:
              |""".trimMargin() + _existingAppInfo)
        }
        val _columnsCustomIp: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCustomIp.put("uid", TableInfo.Column("uid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("ipAddress", TableInfo.Column("ipAddress", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("port", TableInfo.Column("port", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("protocol", TableInfo.Column("protocol", "TEXT", true, 4, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("proxyId", TableInfo.Column("proxyId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("proxyCC", TableInfo.Column("proxyCC", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("status", TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("wildcard", TableInfo.Column("wildcard", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("ruleType", TableInfo.Column("ruleType", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomIp.put("modifiedDateTime", TableInfo.Column("modifiedDateTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCustomIp: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCustomIp: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCustomIp: TableInfo = TableInfo("CustomIp", _columnsCustomIp, _foreignKeysCustomIp, _indicesCustomIp)
        val _existingCustomIp: TableInfo = read(connection, "CustomIp")
        if (!_infoCustomIp.equals(_existingCustomIp)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |CustomIp(com.celzero.bravedns.database.CustomIp).
              | Expected:
              |""".trimMargin() + _infoCustomIp + """
              |
              | Found:
              |""".trimMargin() + _existingCustomIp)
        }
        val _columnsDoHEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDoHEndpoint.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoHEndpoint.put("dohName", TableInfo.Column("dohName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoHEndpoint.put("dohURL", TableInfo.Column("dohURL", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoHEndpoint.put("dohIp", TableInfo.Column("dohIp", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoHEndpoint.put("dohExplanation", TableInfo.Column("dohExplanation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoHEndpoint.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoHEndpoint.put("isCustom", TableInfo.Column("isCustom", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoHEndpoint.put("isSecure", TableInfo.Column("isSecure", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoHEndpoint.put("modifiedDataTime", TableInfo.Column("modifiedDataTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoHEndpoint.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDoHEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDoHEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDoHEndpoint: TableInfo = TableInfo("DoHEndpoint", _columnsDoHEndpoint, _foreignKeysDoHEndpoint, _indicesDoHEndpoint)
        val _existingDoHEndpoint: TableInfo = read(connection, "DoHEndpoint")
        if (!_infoDoHEndpoint.equals(_existingDoHEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |DoHEndpoint(com.celzero.bravedns.database.DoHEndpoint).
              | Expected:
              |""".trimMargin() + _infoDoHEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingDoHEndpoint)
        }
        val _columnsDNSCryptEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDNSCryptEndpoint.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptEndpoint.put("dnsCryptName", TableInfo.Column("dnsCryptName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptEndpoint.put("dnsCryptURL", TableInfo.Column("dnsCryptURL", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptEndpoint.put("dnsCryptExplanation", TableInfo.Column("dnsCryptExplanation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptEndpoint.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptEndpoint.put("isCustom", TableInfo.Column("isCustom", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptEndpoint.put("modifiedDataTime", TableInfo.Column("modifiedDataTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptEndpoint.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDNSCryptEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDNSCryptEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDNSCryptEndpoint: TableInfo = TableInfo("DNSCryptEndpoint", _columnsDNSCryptEndpoint, _foreignKeysDNSCryptEndpoint, _indicesDNSCryptEndpoint)
        val _existingDNSCryptEndpoint: TableInfo = read(connection, "DNSCryptEndpoint")
        if (!_infoDNSCryptEndpoint.equals(_existingDNSCryptEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |DNSCryptEndpoint(com.celzero.bravedns.database.DnsCryptEndpoint).
              | Expected:
              |""".trimMargin() + _infoDNSCryptEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingDNSCryptEndpoint)
        }
        val _columnsDNSProxyEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDNSProxyEndpoint.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSProxyEndpoint.put("proxyName", TableInfo.Column("proxyName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSProxyEndpoint.put("proxyType", TableInfo.Column("proxyType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSProxyEndpoint.put("proxyAppName", TableInfo.Column("proxyAppName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSProxyEndpoint.put("proxyIP", TableInfo.Column("proxyIP", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSProxyEndpoint.put("proxyPort", TableInfo.Column("proxyPort", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSProxyEndpoint.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSProxyEndpoint.put("isCustom", TableInfo.Column("isCustom", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSProxyEndpoint.put("modifiedDataTime", TableInfo.Column("modifiedDataTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSProxyEndpoint.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDNSProxyEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDNSProxyEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDNSProxyEndpoint: TableInfo = TableInfo("DNSProxyEndpoint", _columnsDNSProxyEndpoint, _foreignKeysDNSProxyEndpoint, _indicesDNSProxyEndpoint)
        val _existingDNSProxyEndpoint: TableInfo = read(connection, "DNSProxyEndpoint")
        if (!_infoDNSProxyEndpoint.equals(_existingDNSProxyEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |DNSProxyEndpoint(com.celzero.bravedns.database.DnsProxyEndpoint).
              | Expected:
              |""".trimMargin() + _infoDNSProxyEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingDNSProxyEndpoint)
        }
        val _columnsDNSCryptRelayEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDNSCryptRelayEndpoint.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptRelayEndpoint.put("dnsCryptRelayName", TableInfo.Column("dnsCryptRelayName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptRelayEndpoint.put("dnsCryptRelayURL", TableInfo.Column("dnsCryptRelayURL", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptRelayEndpoint.put("dnsCryptRelayExplanation", TableInfo.Column("dnsCryptRelayExplanation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptRelayEndpoint.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptRelayEndpoint.put("isCustom", TableInfo.Column("isCustom", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptRelayEndpoint.put("modifiedDataTime", TableInfo.Column("modifiedDataTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDNSCryptRelayEndpoint.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDNSCryptRelayEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDNSCryptRelayEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDNSCryptRelayEndpoint: TableInfo = TableInfo("DNSCryptRelayEndpoint", _columnsDNSCryptRelayEndpoint, _foreignKeysDNSCryptRelayEndpoint, _indicesDNSCryptRelayEndpoint)
        val _existingDNSCryptRelayEndpoint: TableInfo = read(connection, "DNSCryptRelayEndpoint")
        if (!_infoDNSCryptRelayEndpoint.equals(_existingDNSCryptRelayEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |DNSCryptRelayEndpoint(com.celzero.bravedns.database.DnsCryptRelayEndpoint).
              | Expected:
              |""".trimMargin() + _infoDNSCryptRelayEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingDNSCryptRelayEndpoint)
        }
        val _columnsProxyEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProxyEndpoint.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("proxyName", TableInfo.Column("proxyName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("proxyMode", TableInfo.Column("proxyMode", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("proxyType", TableInfo.Column("proxyType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("proxyAppName", TableInfo.Column("proxyAppName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("proxyIP", TableInfo.Column("proxyIP", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("proxyPort", TableInfo.Column("proxyPort", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("userName", TableInfo.Column("userName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("password", TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("isCustom", TableInfo.Column("isCustom", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("isUDP", TableInfo.Column("isUDP", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("modifiedDataTime", TableInfo.Column("modifiedDataTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyEndpoint.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProxyEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesProxyEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoProxyEndpoint: TableInfo = TableInfo("ProxyEndpoint", _columnsProxyEndpoint, _foreignKeysProxyEndpoint, _indicesProxyEndpoint)
        val _existingProxyEndpoint: TableInfo = read(connection, "ProxyEndpoint")
        if (!_infoProxyEndpoint.equals(_existingProxyEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |ProxyEndpoint(com.celzero.bravedns.database.ProxyEndpoint).
              | Expected:
              |""".trimMargin() + _infoProxyEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingProxyEndpoint)
        }
        val _columnsCustomDomain: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCustomDomain.put("domain", TableInfo.Column("domain", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomDomain.put("uid", TableInfo.Column("uid", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomDomain.put("ips", TableInfo.Column("ips", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomDomain.put("status", TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomDomain.put("type", TableInfo.Column("type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomDomain.put("proxyId", TableInfo.Column("proxyId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomDomain.put("proxyCC", TableInfo.Column("proxyCC", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomDomain.put("modifiedTs", TableInfo.Column("modifiedTs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomDomain.put("deletedTs", TableInfo.Column("deletedTs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomDomain.put("version", TableInfo.Column("version", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCustomDomain: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCustomDomain: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCustomDomain: TableInfo = TableInfo("CustomDomain", _columnsCustomDomain, _foreignKeysCustomDomain, _indicesCustomDomain)
        val _existingCustomDomain: TableInfo = read(connection, "CustomDomain")
        if (!_infoCustomDomain.equals(_existingCustomDomain)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |CustomDomain(com.celzero.bravedns.database.CustomDomain).
              | Expected:
              |""".trimMargin() + _infoCustomDomain + """
              |
              | Found:
              |""".trimMargin() + _existingCustomDomain)
        }
        val _columnsRethinkDnsEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRethinkDnsEndpoint.put("name", TableInfo.Column("name", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkDnsEndpoint.put("url", TableInfo.Column("url", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkDnsEndpoint.put("uid", TableInfo.Column("uid", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkDnsEndpoint.put("desc", TableInfo.Column("desc", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkDnsEndpoint.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkDnsEndpoint.put("isCustom", TableInfo.Column("isCustom", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkDnsEndpoint.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkDnsEndpoint.put("blocklistCount", TableInfo.Column("blocklistCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkDnsEndpoint.put("modifiedDataTime", TableInfo.Column("modifiedDataTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRethinkDnsEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRethinkDnsEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRethinkDnsEndpoint: TableInfo = TableInfo("RethinkDnsEndpoint", _columnsRethinkDnsEndpoint, _foreignKeysRethinkDnsEndpoint, _indicesRethinkDnsEndpoint)
        val _existingRethinkDnsEndpoint: TableInfo = read(connection, "RethinkDnsEndpoint")
        if (!_infoRethinkDnsEndpoint.equals(_existingRethinkDnsEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |RethinkDnsEndpoint(com.celzero.bravedns.database.RethinkDnsEndpoint).
              | Expected:
              |""".trimMargin() + _infoRethinkDnsEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingRethinkDnsEndpoint)
        }
        val _columnsRethinkRemoteFileTag: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRethinkRemoteFileTag.put("value", TableInfo.Column("value", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("uname", TableInfo.Column("uname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("vname", TableInfo.Column("vname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("group", TableInfo.Column("group", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("subg", TableInfo.Column("subg", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("url", TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("show", TableInfo.Column("show", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("entries", TableInfo.Column("entries", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("pack", TableInfo.Column("pack", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("level", TableInfo.Column("level", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("simpleTagId", TableInfo.Column("simpleTagId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkRemoteFileTag.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRethinkRemoteFileTag: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRethinkRemoteFileTag: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRethinkRemoteFileTag: TableInfo = TableInfo("RethinkRemoteFileTag", _columnsRethinkRemoteFileTag, _foreignKeysRethinkRemoteFileTag, _indicesRethinkRemoteFileTag)
        val _existingRethinkRemoteFileTag: TableInfo = read(connection, "RethinkRemoteFileTag")
        if (!_infoRethinkRemoteFileTag.equals(_existingRethinkRemoteFileTag)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |RethinkRemoteFileTag(com.celzero.bravedns.database.RethinkRemoteFileTag).
              | Expected:
              |""".trimMargin() + _infoRethinkRemoteFileTag + """
              |
              | Found:
              |""".trimMargin() + _existingRethinkRemoteFileTag)
        }
        val _columnsRethinkLocalFileTag: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRethinkLocalFileTag.put("value", TableInfo.Column("value", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("uname", TableInfo.Column("uname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("vname", TableInfo.Column("vname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("group", TableInfo.Column("group", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("subg", TableInfo.Column("subg", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("url", TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("show", TableInfo.Column("show", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("entries", TableInfo.Column("entries", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("pack", TableInfo.Column("pack", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("level", TableInfo.Column("level", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("simpleTagId", TableInfo.Column("simpleTagId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLocalFileTag.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRethinkLocalFileTag: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRethinkLocalFileTag: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRethinkLocalFileTag: TableInfo = TableInfo("RethinkLocalFileTag", _columnsRethinkLocalFileTag, _foreignKeysRethinkLocalFileTag, _indicesRethinkLocalFileTag)
        val _existingRethinkLocalFileTag: TableInfo = read(connection, "RethinkLocalFileTag")
        if (!_infoRethinkLocalFileTag.equals(_existingRethinkLocalFileTag)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |RethinkLocalFileTag(com.celzero.bravedns.database.RethinkLocalFileTag).
              | Expected:
              |""".trimMargin() + _infoRethinkLocalFileTag + """
              |
              | Found:
              |""".trimMargin() + _existingRethinkLocalFileTag)
        }
        val _columnsLocalBlocklistPacksMap: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLocalBlocklistPacksMap.put("pack", TableInfo.Column("pack", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLocalBlocklistPacksMap.put("level", TableInfo.Column("level", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLocalBlocklistPacksMap.put("blocklistIds", TableInfo.Column("blocklistIds", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLocalBlocklistPacksMap.put("group", TableInfo.Column("group", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLocalBlocklistPacksMap: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLocalBlocklistPacksMap: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoLocalBlocklistPacksMap: TableInfo = TableInfo("LocalBlocklistPacksMap", _columnsLocalBlocklistPacksMap, _foreignKeysLocalBlocklistPacksMap, _indicesLocalBlocklistPacksMap)
        val _existingLocalBlocklistPacksMap: TableInfo = read(connection, "LocalBlocklistPacksMap")
        if (!_infoLocalBlocklistPacksMap.equals(_existingLocalBlocklistPacksMap)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |LocalBlocklistPacksMap(com.celzero.bravedns.database.LocalBlocklistPacksMap).
              | Expected:
              |""".trimMargin() + _infoLocalBlocklistPacksMap + """
              |
              | Found:
              |""".trimMargin() + _existingLocalBlocklistPacksMap)
        }
        val _columnsRemoteBlocklistPacksMap: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRemoteBlocklistPacksMap.put("pack", TableInfo.Column("pack", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRemoteBlocklistPacksMap.put("level", TableInfo.Column("level", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRemoteBlocklistPacksMap.put("blocklistIds", TableInfo.Column("blocklistIds", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRemoteBlocklistPacksMap.put("group", TableInfo.Column("group", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRemoteBlocklistPacksMap: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRemoteBlocklistPacksMap: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRemoteBlocklistPacksMap: TableInfo = TableInfo("RemoteBlocklistPacksMap", _columnsRemoteBlocklistPacksMap, _foreignKeysRemoteBlocklistPacksMap, _indicesRemoteBlocklistPacksMap)
        val _existingRemoteBlocklistPacksMap: TableInfo = read(connection, "RemoteBlocklistPacksMap")
        if (!_infoRemoteBlocklistPacksMap.equals(_existingRemoteBlocklistPacksMap)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |RemoteBlocklistPacksMap(com.celzero.bravedns.database.RemoteBlocklistPacksMap).
              | Expected:
              |""".trimMargin() + _infoRemoteBlocklistPacksMap + """
              |
              | Found:
              |""".trimMargin() + _existingRemoteBlocklistPacksMap)
        }
        val _columnsWgConfigFiles: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsWgConfigFiles.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("configPath", TableInfo.Column("configPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("serverResponse", TableInfo.Column("serverResponse", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("isCatchAll", TableInfo.Column("isCatchAll", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("isLockdown", TableInfo.Column("isLockdown", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("oneWireGuard", TableInfo.Column("oneWireGuard", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("useOnlyOnMetered", TableInfo.Column("useOnlyOnMetered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("isDeletable", TableInfo.Column("isDeletable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("ssidEnabled", TableInfo.Column("ssidEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("ssids", TableInfo.Column("ssids", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgConfigFiles.put("modifiedTs", TableInfo.Column("modifiedTs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysWgConfigFiles: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesWgConfigFiles: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoWgConfigFiles: TableInfo = TableInfo("WgConfigFiles", _columnsWgConfigFiles, _foreignKeysWgConfigFiles, _indicesWgConfigFiles)
        val _existingWgConfigFiles: TableInfo = read(connection, "WgConfigFiles")
        if (!_infoWgConfigFiles.equals(_existingWgConfigFiles)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |WgConfigFiles(com.celzero.bravedns.database.WgConfigFiles).
              | Expected:
              |""".trimMargin() + _infoWgConfigFiles + """
              |
              | Found:
              |""".trimMargin() + _existingWgConfigFiles)
        }
        val _columnsProxyApplicationMapping: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProxyApplicationMapping.put("uid", TableInfo.Column("uid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyApplicationMapping.put("packageName", TableInfo.Column("packageName", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyApplicationMapping.put("proxyId", TableInfo.Column("proxyId", "TEXT", true, 3, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyApplicationMapping.put("appName", TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyApplicationMapping.put("proxyName", TableInfo.Column("proxyName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProxyApplicationMapping.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProxyApplicationMapping: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesProxyApplicationMapping: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoProxyApplicationMapping: TableInfo = TableInfo("ProxyApplicationMapping", _columnsProxyApplicationMapping, _foreignKeysProxyApplicationMapping, _indicesProxyApplicationMapping)
        val _existingProxyApplicationMapping: TableInfo = read(connection, "ProxyApplicationMapping")
        if (!_infoProxyApplicationMapping.equals(_existingProxyApplicationMapping)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |ProxyApplicationMapping(com.celzero.bravedns.database.ProxyApplicationMapping).
              | Expected:
              |""".trimMargin() + _infoProxyApplicationMapping + """
              |
              | Found:
              |""".trimMargin() + _existingProxyApplicationMapping)
        }
        val _columnsTcpProxyEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTcpProxyEndpoint.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTcpProxyEndpoint.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTcpProxyEndpoint.put("token", TableInfo.Column("token", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTcpProxyEndpoint.put("url", TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTcpProxyEndpoint.put("paymentStatus", TableInfo.Column("paymentStatus", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTcpProxyEndpoint.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTcpProxyEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesTcpProxyEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoTcpProxyEndpoint: TableInfo = TableInfo("TcpProxyEndpoint", _columnsTcpProxyEndpoint, _foreignKeysTcpProxyEndpoint, _indicesTcpProxyEndpoint)
        val _existingTcpProxyEndpoint: TableInfo = read(connection, "TcpProxyEndpoint")
        if (!_infoTcpProxyEndpoint.equals(_existingTcpProxyEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |TcpProxyEndpoint(com.celzero.bravedns.database.TcpProxyEndpoint).
              | Expected:
              |""".trimMargin() + _infoTcpProxyEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingTcpProxyEndpoint)
        }
        val _columnsDoTEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDoTEndpoint.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoTEndpoint.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoTEndpoint.put("url", TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoTEndpoint.put("desc", TableInfo.Column("desc", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoTEndpoint.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoTEndpoint.put("isCustom", TableInfo.Column("isCustom", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoTEndpoint.put("isSecure", TableInfo.Column("isSecure", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoTEndpoint.put("modifiedDataTime", TableInfo.Column("modifiedDataTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDoTEndpoint.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDoTEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDoTEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDoTEndpoint: TableInfo = TableInfo("DoTEndpoint", _columnsDoTEndpoint, _foreignKeysDoTEndpoint, _indicesDoTEndpoint)
        val _existingDoTEndpoint: TableInfo = read(connection, "DoTEndpoint")
        if (!_infoDoTEndpoint.equals(_existingDoTEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |DoTEndpoint(com.celzero.bravedns.database.DoTEndpoint).
              | Expected:
              |""".trimMargin() + _infoDoTEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingDoTEndpoint)
        }
        val _columnsODoHEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsODoHEndpoint.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsODoHEndpoint.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsODoHEndpoint.put("proxy", TableInfo.Column("proxy", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsODoHEndpoint.put("resolver", TableInfo.Column("resolver", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsODoHEndpoint.put("proxyIps", TableInfo.Column("proxyIps", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsODoHEndpoint.put("desc", TableInfo.Column("desc", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsODoHEndpoint.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsODoHEndpoint.put("isCustom", TableInfo.Column("isCustom", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsODoHEndpoint.put("modifiedDataTime", TableInfo.Column("modifiedDataTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsODoHEndpoint.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysODoHEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesODoHEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoODoHEndpoint: TableInfo = TableInfo("ODoHEndpoint", _columnsODoHEndpoint, _foreignKeysODoHEndpoint, _indicesODoHEndpoint)
        val _existingODoHEndpoint: TableInfo = read(connection, "ODoHEndpoint")
        if (!_infoODoHEndpoint.equals(_existingODoHEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |ODoHEndpoint(com.celzero.bravedns.database.ODoHEndpoint).
              | Expected:
              |""".trimMargin() + _infoODoHEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingODoHEndpoint)
        }
        val _columnsRpnProxy: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRpnProxy.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("configPath", TableInfo.Column("configPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("serverResPath", TableInfo.Column("serverResPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("isLockdown", TableInfo.Column("isLockdown", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("createdTs", TableInfo.Column("createdTs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("modifiedTs", TableInfo.Column("modifiedTs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("misc", TableInfo.Column("misc", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("tunId", TableInfo.Column("tunId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRpnProxy.put("lastRefreshTime", TableInfo.Column("lastRefreshTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRpnProxy: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRpnProxy: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRpnProxy: TableInfo = TableInfo("RpnProxy", _columnsRpnProxy, _foreignKeysRpnProxy, _indicesRpnProxy)
        val _existingRpnProxy: TableInfo = read(connection, "RpnProxy")
        if (!_infoRpnProxy.equals(_existingRpnProxy)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |RpnProxy(com.celzero.bravedns.database.RpnProxy).
              | Expected:
              |""".trimMargin() + _infoRpnProxy + """
              |
              | Found:
              |""".trimMargin() + _existingRpnProxy)
        }
        val _columnsWgHopMap: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsWgHopMap.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgHopMap.put("src", TableInfo.Column("src", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgHopMap.put("hop", TableInfo.Column("hop", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgHopMap.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsWgHopMap.put("status", TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysWgHopMap: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesWgHopMap: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoWgHopMap: TableInfo = TableInfo("WgHopMap", _columnsWgHopMap, _foreignKeysWgHopMap, _indicesWgHopMap)
        val _existingWgHopMap: TableInfo = read(connection, "WgHopMap")
        if (!_infoWgHopMap.equals(_existingWgHopMap)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |WgHopMap(com.celzero.bravedns.database.WgHopMap).
              | Expected:
              |""".trimMargin() + _infoWgHopMap + """
              |
              | Found:
              |""".trimMargin() + _existingWgHopMap)
        }
        val _columnsSubscriptionStatus: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSubscriptionStatus.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("accountId", TableInfo.Column("accountId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("deviceId", TableInfo.Column("deviceId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("purchaseToken", TableInfo.Column("purchaseToken", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("orderId", TableInfo.Column("orderId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("productId", TableInfo.Column("productId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("planId", TableInfo.Column("planId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("sessionToken", TableInfo.Column("sessionToken", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("productTitle", TableInfo.Column("productTitle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("state", TableInfo.Column("state", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("purchaseTime", TableInfo.Column("purchaseTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("accountExpiry", TableInfo.Column("accountExpiry", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("billingExpiry", TableInfo.Column("billingExpiry", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("developerPayload", TableInfo.Column("developerPayload", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("status", TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("windowDays", TableInfo.Column("windowDays", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("lastUpdatedTs", TableInfo.Column("lastUpdatedTs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("previousProductId", TableInfo.Column("previousProductId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("previousPurchaseToken", TableInfo.Column("previousPurchaseToken", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStatus.put("replacedAt", TableInfo.Column("replacedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSubscriptionStatus: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSubscriptionStatus: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSubscriptionStatus: TableInfo = TableInfo("SubscriptionStatus", _columnsSubscriptionStatus, _foreignKeysSubscriptionStatus, _indicesSubscriptionStatus)
        val _existingSubscriptionStatus: TableInfo = read(connection, "SubscriptionStatus")
        if (!_infoSubscriptionStatus.equals(_existingSubscriptionStatus)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |SubscriptionStatus(com.celzero.bravedns.database.SubscriptionStatus).
              | Expected:
              |""".trimMargin() + _infoSubscriptionStatus + """
              |
              | Found:
              |""".trimMargin() + _existingSubscriptionStatus)
        }
        val _columnsSubscriptionStateHistory: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSubscriptionStateHistory.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStateHistory.put("subscriptionId", TableInfo.Column("subscriptionId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStateHistory.put("fromState", TableInfo.Column("fromState", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStateHistory.put("toState", TableInfo.Column("toState", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStateHistory.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSubscriptionStateHistory.put("reason", TableInfo.Column("reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSubscriptionStateHistory: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSubscriptionStateHistory: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSubscriptionStateHistory: TableInfo = TableInfo("SubscriptionStateHistory", _columnsSubscriptionStateHistory, _foreignKeysSubscriptionStateHistory, _indicesSubscriptionStateHistory)
        val _existingSubscriptionStateHistory: TableInfo = read(connection, "SubscriptionStateHistory")
        if (!_infoSubscriptionStateHistory.equals(_existingSubscriptionStateHistory)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |SubscriptionStateHistory(com.celzero.bravedns.database.SubscriptionStateHistory).
              | Expected:
              |""".trimMargin() + _infoSubscriptionStateHistory + """
              |
              | Found:
              |""".trimMargin() + _existingSubscriptionStateHistory)
        }
        val _columnsCountryConfig: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCountryConfig.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("cc", TableInfo.Column("cc", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("address", TableInfo.Column("address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("city", TableInfo.Column("city", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("key", TableInfo.Column("key", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("load", TableInfo.Column("load", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("link", TableInfo.Column("link", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("count", TableInfo.Column("count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("premium", TableInfo.Column("premium", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("isEnabled", TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("catchAll", TableInfo.Column("catchAll", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("lockdown", TableInfo.Column("lockdown", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("mobileOnly", TableInfo.Column("mobileOnly", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("ssidBased", TableInfo.Column("ssidBased", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("priority", TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("ssids", TableInfo.Column("ssids", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("selectionCount", TableInfo.Column("selectionCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("isFavourite", TableInfo.Column("isFavourite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("hopEnabled", TableInfo.Column("hopEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("ipv4", TableInfo.Column("ipv4", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        _columnsCountryConfig.put("ipv6", TableInfo.Column("ipv6", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCountryConfig: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCountryConfig: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesCountryConfig.add(TableInfo.Index("index_CountryConfig_cc", false, listOf("cc"), listOf("ASC")))
        _indicesCountryConfig.add(TableInfo.Index("index_CountryConfig_isActive", false, listOf("isActive"), listOf("ASC")))
        _indicesCountryConfig.add(TableInfo.Index("index_CountryConfig_isFavourite", false, listOf("isFavourite"), listOf("ASC")))
        val _infoCountryConfig: TableInfo = TableInfo("CountryConfig", _columnsCountryConfig, _foreignKeysCountryConfig, _indicesCountryConfig)
        val _existingCountryConfig: TableInfo = read(connection, "CountryConfig")
        if (!_infoCountryConfig.equals(_existingCountryConfig)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |CountryConfig(com.celzero.bravedns.database.CountryConfig).
              | Expected:
              |""".trimMargin() + _infoCountryConfig + """
              |
              | Found:
              |""".trimMargin() + _existingCountryConfig)
        }
        val _columnsSponsor: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSponsor.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSponsor.put("purchase_token", TableInfo.Column("purchase_token", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSponsor.put("product_id", TableInfo.Column("product_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSponsor.put("purchase_time", TableInfo.Column("purchase_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSponsor.put("sponsor_since", TableInfo.Column("sponsor_since", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSponsor.put("consumed", TableInfo.Column("consumed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSponsor.put("contribution_count", TableInfo.Column("contribution_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSponsor.put("last_contribution_time", TableInfo.Column("last_contribution_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSponsor: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSponsor: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSponsor: TableInfo = TableInfo("Sponsor", _columnsSponsor, _foreignKeysSponsor, _indicesSponsor)
        val _existingSponsor: TableInfo = read(connection, "Sponsor")
        if (!_infoSponsor.equals(_existingSponsor)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Sponsor(com.celzero.bravedns.sponsor.database.SponsorEntity).
              | Expected:
              |""".trimMargin() + _infoSponsor + """
              |
              | Found:
              |""".trimMargin() + _existingSponsor)
        }
        val _columnsSmartDnsEndpoint: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSmartDnsEndpoint.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSmartDnsEndpoint.put("dnsName", TableInfo.Column("dnsName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSmartDnsEndpoint.put("dnsMode", TableInfo.Column("dnsMode", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSmartDnsEndpoint.put("dnsExplanation", TableInfo.Column("dnsExplanation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSmartDnsEndpoint.put("isSelected", TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSmartDnsEndpoint.put("modifiedDataTime", TableInfo.Column("modifiedDataTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSmartDnsEndpoint.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSmartDnsEndpoint: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSmartDnsEndpoint: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSmartDnsEndpoint: TableInfo = TableInfo("SmartDnsEndpoint", _columnsSmartDnsEndpoint, _foreignKeysSmartDnsEndpoint, _indicesSmartDnsEndpoint)
        val _existingSmartDnsEndpoint: TableInfo = read(connection, "SmartDnsEndpoint")
        if (!_infoSmartDnsEndpoint.equals(_existingSmartDnsEndpoint)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |SmartDnsEndpoint(com.celzero.bravedns.database.SmartDnsEndpoint).
              | Expected:
              |""".trimMargin() + _infoSmartDnsEndpoint + """
              |
              | Found:
              |""".trimMargin() + _existingSmartDnsEndpoint)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "AppInfo", "CustomIp", "DoHEndpoint", "DNSCryptEndpoint", "DNSProxyEndpoint", "DNSCryptRelayEndpoint", "ProxyEndpoint", "CustomDomain", "RethinkDnsEndpoint", "RethinkRemoteFileTag", "RethinkLocalFileTag", "LocalBlocklistPacksMap", "RemoteBlocklistPacksMap", "WgConfigFiles", "ProxyApplicationMapping", "TcpProxyEndpoint", "DoTEndpoint", "ODoHEndpoint", "RpnProxy", "WgHopMap", "SubscriptionStatus", "SubscriptionStateHistory", "CountryConfig", "Sponsor", "SmartDnsEndpoint")
  }

  public override fun clearAllTables() {
    super.performClear(false, "AppInfo", "CustomIp", "DoHEndpoint", "DNSCryptEndpoint", "DNSProxyEndpoint", "DNSCryptRelayEndpoint", "ProxyEndpoint", "CustomDomain", "RethinkDnsEndpoint", "RethinkRemoteFileTag", "RethinkLocalFileTag", "LocalBlocklistPacksMap", "RemoteBlocklistPacksMap", "WgConfigFiles", "ProxyApplicationMapping", "TcpProxyEndpoint", "DoTEndpoint", "ODoHEndpoint", "RpnProxy", "WgHopMap", "SubscriptionStatus", "SubscriptionStateHistory", "CountryConfig", "Sponsor", "SmartDnsEndpoint")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(AppInfoDAO::class, AppInfoDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(DoHEndpointDAO::class, DoHEndpointDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(DnsCryptEndpointDAO::class, DnsCryptEndpointDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(DnsCryptRelayEndpointDAO::class, DnsCryptRelayEndpointDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(DnsProxyEndpointDAO::class, DnsProxyEndpointDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(ProxyEndpointDAO::class, ProxyEndpointDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(CustomDomainDAO::class, CustomDomainDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(CustomIpDao::class, CustomIpDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RethinkDnsEndpointDao::class, RethinkDnsEndpointDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RethinkRemoteFileTagDao::class, RethinkRemoteFileTagDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RethinkLocalFileTagDao::class, RethinkLocalFileTagDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(LocalBlocklistPacksMapDao::class, LocalBlocklistPacksMapDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RemoteBlocklistPacksMapDao::class, RemoteBlocklistPacksMapDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AppDatabaseRawQueryDao::class, AppDatabaseRawQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(WgConfigFilesDAO::class, WgConfigFilesDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(ProxyApplicationMappingDAO::class, ProxyApplicationMappingDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(TcpProxyDAO::class, TcpProxyDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(DoTEndpointDAO::class, DoTEndpointDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(ODoHEndpointDAO::class, ODoHEndpointDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(RpnProxyDao::class, RpnProxyDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(WgHopMapDao::class, WgHopMapDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SubscriptionStatusDao::class, SubscriptionStatusDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SubscriptionStateHistoryDao::class, SubscriptionStateHistoryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SponsorDao::class, SponsorDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CountryConfigDAO::class, CountryConfigDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(SmartDnsEndpointDAO::class, SmartDnsEndpointDAO_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun appInfoDAO(): AppInfoDAO = _appInfoDAO.value

  public override fun dohEndpointsDAO(): DoHEndpointDAO = _doHEndpointDAO.value

  public override fun dnsCryptEndpointDAO(): DnsCryptEndpointDAO = _dnsCryptEndpointDAO.value

  public override fun dnsCryptRelayEndpointDAO(): DnsCryptRelayEndpointDAO = _dnsCryptRelayEndpointDAO.value

  public override fun dnsProxyEndpointDAO(): DnsProxyEndpointDAO = _dnsProxyEndpointDAO.value

  public override fun proxyEndpointDAO(): ProxyEndpointDAO = _proxyEndpointDAO.value

  public override fun customDomainEndpointDAO(): CustomDomainDAO = _customDomainDAO.value

  public override fun customIpEndpointDao(): CustomIpDao = _customIpDao.value

  public override fun rethinkEndpointDao(): RethinkDnsEndpointDao = _rethinkDnsEndpointDao.value

  public override fun rethinkRemoteFileTagDao(): RethinkRemoteFileTagDao = _rethinkRemoteFileTagDao.value

  public override fun rethinkLocalFileTagDao(): RethinkLocalFileTagDao = _rethinkLocalFileTagDao.value

  public override fun localBlocklistPacksMapDao(): LocalBlocklistPacksMapDao = _localBlocklistPacksMapDao.value

  public override fun remoteBlocklistPacksMapDao(): RemoteBlocklistPacksMapDao = _remoteBlocklistPacksMapDao.value

  public override fun appDatabaseRawQueries(): AppDatabaseRawQueryDao = _appDatabaseRawQueryDao.value

  public override fun wgConfigFilesDAO(): WgConfigFilesDAO = _wgConfigFilesDAO.value

  public override fun wgApplicationMappingDao(): ProxyApplicationMappingDAO = _proxyApplicationMappingDAO.value

  public override fun tcpProxyEndpointDao(): TcpProxyDAO = _tcpProxyDAO.value

  public override fun dotEndpointDao(): DoTEndpointDAO = _doTEndpointDAO.value

  public override fun odohEndpointDao(): ODoHEndpointDAO = _oDoHEndpointDAO.value

  public override fun rpnProxyDao(): RpnProxyDao = _rpnProxyDao.value

  public override fun wgHopMapDao(): WgHopMapDao = _wgHopMapDao.value

  public override fun subscriptionStatusDao(): SubscriptionStatusDao = _subscriptionStatusDao.value

  public override fun subscriptionStateHistoryDao(): SubscriptionStateHistoryDao = _subscriptionStateHistoryDao.value

  public override fun sponsorDao(): SponsorDao = _sponsorDao.value

  public override fun countryConfigDAO(): CountryConfigDAO = _countryConfigDAO.value

  public override fun smartDnsEndpointDao(): SmartDnsEndpointDAO = _smartDnsEndpointDAO.value
}
