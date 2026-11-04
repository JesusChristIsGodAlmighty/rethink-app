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
public class LogDatabase_Impl : LogDatabase() {
  private val _connectionTrackerDAO: Lazy<ConnectionTrackerDAO> = lazy {
    ConnectionTrackerDAO_Impl(this)
  }

  private val _rethinkLogDao: Lazy<RethinkLogDao> = lazy {
    RethinkLogDao_Impl(this)
  }

  private val _dnsLogDAO: Lazy<DnsLogDAO> = lazy {
    DnsLogDAO_Impl(this)
  }

  private val _logDatabaseRawQueryDao: Lazy<LogDatabaseRawQueryDao> = lazy {
    LogDatabaseRawQueryDao_Impl(this)
  }

  private val _statsSummaryDao: Lazy<StatsSummaryDao> = lazy {
    StatsSummaryDao_Impl(this)
  }

  private val _ipInfoDAO: Lazy<IpInfoDAO> = lazy {
    IpInfoDAO_Impl(this)
  }

  private val _eventDao: Lazy<EventDao> = lazy {
    EventDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(16, "e71796eb8e255c50b73df5caff7811a1", "8576e51b4fb9bc916d1125d6396daf5b") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `ConnectionTracker` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `appName` TEXT NOT NULL, `uid` INTEGER NOT NULL, `packageName` TEXT NOT NULL, `usrId` INTEGER NOT NULL, `ipAddress` TEXT NOT NULL, `port` INTEGER NOT NULL, `protocol` INTEGER NOT NULL, `isBlocked` INTEGER NOT NULL, `blockedByRule` TEXT NOT NULL, `blocklists` TEXT NOT NULL, `proxyDetails` TEXT NOT NULL, `flag` TEXT NOT NULL, `dnsQuery` TEXT, `timeStamp` INTEGER NOT NULL, `connId` TEXT NOT NULL, `downloadBytes` INTEGER NOT NULL, `uploadBytes` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `synack` INTEGER NOT NULL, `rpid` TEXT NOT NULL, `message` TEXT NOT NULL, `connType` TEXT NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_ConnectionTracker_ipAddress` ON `ConnectionTracker` (`ipAddress`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_ConnectionTracker_appName` ON `ConnectionTracker` (`appName`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_ConnectionTracker_dnsQuery` ON `ConnectionTracker` (`dnsQuery`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_ConnectionTracker_blockedByRule` ON `ConnectionTracker` (`blockedByRule`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_ConnectionTracker_isBlocked_timeStamp` ON `ConnectionTracker` (`isBlocked`, `timeStamp`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_ConnectionTracker_connId` ON `ConnectionTracker` (`connId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_ConnectionTracker_proxyDetails` ON `ConnectionTracker` (`proxyDetails`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `DnsLogs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uid` INTEGER NOT NULL, `appName` TEXT NOT NULL, `packageName` TEXT NOT NULL, `queryStr` TEXT NOT NULL, `time` INTEGER NOT NULL, `flag` TEXT NOT NULL, `resolver` TEXT NOT NULL, `latency` INTEGER NOT NULL, `ttl` INTEGER NOT NULL, `typeName` TEXT NOT NULL, `isBlocked` INTEGER NOT NULL, `blockLists` TEXT NOT NULL, `serverIP` TEXT NOT NULL, `proxyId` TEXT NOT NULL, `relayIP` TEXT NOT NULL, `responseTime` INTEGER NOT NULL, `response` TEXT NOT NULL, `status` TEXT NOT NULL, `dnsType` INTEGER NOT NULL, `responseIps` TEXT NOT NULL, `resolverId` TEXT NOT NULL, `msg` TEXT NOT NULL, `upstreamBlock` INTEGER NOT NULL, `region` TEXT NOT NULL, `isCached` INTEGER NOT NULL, `dnssecOk` INTEGER NOT NULL, `dnssecValid` INTEGER NOT NULL, `blockedTarget` TEXT NOT NULL, `isEch` INTEGER NOT NULL, `blockedReason` TEXT NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_DnsLogs_queryStr` ON `DnsLogs` (`queryStr`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_DnsLogs_responseIps` ON `DnsLogs` (`responseIps`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_DnsLogs_isBlocked` ON `DnsLogs` (`isBlocked`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_DnsLogs_blockLists` ON `DnsLogs` (`blockLists`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_DnsLogs_time` ON `DnsLogs` (`time`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `RethinkLog` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `appName` TEXT NOT NULL, `uid` INTEGER NOT NULL, `usrId` INTEGER NOT NULL, `ipAddress` TEXT NOT NULL, `port` INTEGER NOT NULL, `protocol` INTEGER NOT NULL, `isBlocked` INTEGER NOT NULL, `blockedByRule` TEXT NOT NULL, `blocklists` TEXT NOT NULL, `proxyDetails` TEXT NOT NULL, `flag` TEXT NOT NULL, `dnsQuery` TEXT, `timeStamp` INTEGER NOT NULL, `connId` TEXT NOT NULL, `downloadBytes` INTEGER NOT NULL, `uploadBytes` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `synack` INTEGER NOT NULL, `rpid` TEXT NOT NULL, `message` TEXT NOT NULL, `connType` TEXT NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_RethinkLog_ipAddress` ON `RethinkLog` (`ipAddress`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_RethinkLog_appName` ON `RethinkLog` (`appName`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_RethinkLog_dnsQuery` ON `RethinkLog` (`dnsQuery`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_RethinkLog_connId` ON `RethinkLog` (`connId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_RethinkLog_timeStamp` ON `RethinkLog` (`timeStamp` DESC)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `IpInfo` (`ip` TEXT NOT NULL, `asn` TEXT NOT NULL, `asName` TEXT NOT NULL, `asDomain` TEXT NOT NULL, `countryCode` TEXT NOT NULL, `country` TEXT NOT NULL, `continentCode` TEXT NOT NULL, `continent` TEXT NOT NULL, `createdTs` INTEGER NOT NULL, PRIMARY KEY(`ip`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `Events` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `timestamp` INTEGER NOT NULL, `eventType` TEXT NOT NULL, `severity` TEXT NOT NULL, `message` TEXT NOT NULL, `details` TEXT, `source` TEXT NOT NULL, `userAction` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_Events_timestamp` ON `Events` (`timestamp`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_Events_eventType` ON `Events` (`eventType`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_Events_severity` ON `Events` (`severity`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_Events_source` ON `Events` (`source`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e71796eb8e255c50b73df5caff7811a1')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `ConnectionTracker`")
        connection.execSQL("DROP TABLE IF EXISTS `DnsLogs`")
        connection.execSQL("DROP TABLE IF EXISTS `RethinkLog`")
        connection.execSQL("DROP TABLE IF EXISTS `IpInfo`")
        connection.execSQL("DROP TABLE IF EXISTS `Events`")
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
        val _columnsConnectionTracker: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsConnectionTracker.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("appName", TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("uid", TableInfo.Column("uid", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("packageName", TableInfo.Column("packageName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("usrId", TableInfo.Column("usrId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("ipAddress", TableInfo.Column("ipAddress", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("port", TableInfo.Column("port", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("protocol", TableInfo.Column("protocol", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("isBlocked", TableInfo.Column("isBlocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("blockedByRule", TableInfo.Column("blockedByRule", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("blocklists", TableInfo.Column("blocklists", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("proxyDetails", TableInfo.Column("proxyDetails", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("flag", TableInfo.Column("flag", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("dnsQuery", TableInfo.Column("dnsQuery", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("timeStamp", TableInfo.Column("timeStamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("connId", TableInfo.Column("connId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("downloadBytes", TableInfo.Column("downloadBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("uploadBytes", TableInfo.Column("uploadBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("duration", TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("synack", TableInfo.Column("synack", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("rpid", TableInfo.Column("rpid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("message", TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConnectionTracker.put("connType", TableInfo.Column("connType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysConnectionTracker: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesConnectionTracker: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesConnectionTracker.add(TableInfo.Index("index_ConnectionTracker_ipAddress", false, listOf("ipAddress"), listOf("ASC")))
        _indicesConnectionTracker.add(TableInfo.Index("index_ConnectionTracker_appName", false, listOf("appName"), listOf("ASC")))
        _indicesConnectionTracker.add(TableInfo.Index("index_ConnectionTracker_dnsQuery", false, listOf("dnsQuery"), listOf("ASC")))
        _indicesConnectionTracker.add(TableInfo.Index("index_ConnectionTracker_blockedByRule", false, listOf("blockedByRule"), listOf("ASC")))
        _indicesConnectionTracker.add(TableInfo.Index("index_ConnectionTracker_isBlocked_timeStamp", false, listOf("isBlocked", "timeStamp"), listOf("ASC", "ASC")))
        _indicesConnectionTracker.add(TableInfo.Index("index_ConnectionTracker_connId", false, listOf("connId"), listOf("ASC")))
        _indicesConnectionTracker.add(TableInfo.Index("index_ConnectionTracker_proxyDetails", false, listOf("proxyDetails"), listOf("ASC")))
        val _infoConnectionTracker: TableInfo = TableInfo("ConnectionTracker", _columnsConnectionTracker, _foreignKeysConnectionTracker, _indicesConnectionTracker)
        val _existingConnectionTracker: TableInfo = read(connection, "ConnectionTracker")
        if (!_infoConnectionTracker.equals(_existingConnectionTracker)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |ConnectionTracker(com.celzero.bravedns.database.ConnectionTracker).
              | Expected:
              |""".trimMargin() + _infoConnectionTracker + """
              |
              | Found:
              |""".trimMargin() + _existingConnectionTracker)
        }
        val _columnsDnsLogs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDnsLogs.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("uid", TableInfo.Column("uid", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("appName", TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("packageName", TableInfo.Column("packageName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("queryStr", TableInfo.Column("queryStr", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("time", TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("flag", TableInfo.Column("flag", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("resolver", TableInfo.Column("resolver", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("latency", TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("ttl", TableInfo.Column("ttl", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("typeName", TableInfo.Column("typeName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("isBlocked", TableInfo.Column("isBlocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("blockLists", TableInfo.Column("blockLists", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("serverIP", TableInfo.Column("serverIP", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("proxyId", TableInfo.Column("proxyId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("relayIP", TableInfo.Column("relayIP", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("responseTime", TableInfo.Column("responseTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("response", TableInfo.Column("response", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("status", TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("dnsType", TableInfo.Column("dnsType", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("responseIps", TableInfo.Column("responseIps", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("resolverId", TableInfo.Column("resolverId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("msg", TableInfo.Column("msg", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("upstreamBlock", TableInfo.Column("upstreamBlock", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("region", TableInfo.Column("region", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("isCached", TableInfo.Column("isCached", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("dnssecOk", TableInfo.Column("dnssecOk", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("dnssecValid", TableInfo.Column("dnssecValid", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("blockedTarget", TableInfo.Column("blockedTarget", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("isEch", TableInfo.Column("isEch", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDnsLogs.put("blockedReason", TableInfo.Column("blockedReason", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDnsLogs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDnsLogs: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesDnsLogs.add(TableInfo.Index("index_DnsLogs_queryStr", false, listOf("queryStr"), listOf("ASC")))
        _indicesDnsLogs.add(TableInfo.Index("index_DnsLogs_responseIps", false, listOf("responseIps"), listOf("ASC")))
        _indicesDnsLogs.add(TableInfo.Index("index_DnsLogs_isBlocked", false, listOf("isBlocked"), listOf("ASC")))
        _indicesDnsLogs.add(TableInfo.Index("index_DnsLogs_blockLists", false, listOf("blockLists"), listOf("ASC")))
        _indicesDnsLogs.add(TableInfo.Index("index_DnsLogs_time", false, listOf("time"), listOf("ASC")))
        val _infoDnsLogs: TableInfo = TableInfo("DnsLogs", _columnsDnsLogs, _foreignKeysDnsLogs, _indicesDnsLogs)
        val _existingDnsLogs: TableInfo = read(connection, "DnsLogs")
        if (!_infoDnsLogs.equals(_existingDnsLogs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |DnsLogs(com.celzero.bravedns.database.DnsLog).
              | Expected:
              |""".trimMargin() + _infoDnsLogs + """
              |
              | Found:
              |""".trimMargin() + _existingDnsLogs)
        }
        val _columnsRethinkLog: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRethinkLog.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("appName", TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("uid", TableInfo.Column("uid", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("usrId", TableInfo.Column("usrId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("ipAddress", TableInfo.Column("ipAddress", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("port", TableInfo.Column("port", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("protocol", TableInfo.Column("protocol", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("isBlocked", TableInfo.Column("isBlocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("blockedByRule", TableInfo.Column("blockedByRule", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("blocklists", TableInfo.Column("blocklists", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("proxyDetails", TableInfo.Column("proxyDetails", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("flag", TableInfo.Column("flag", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("dnsQuery", TableInfo.Column("dnsQuery", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("timeStamp", TableInfo.Column("timeStamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("connId", TableInfo.Column("connId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("downloadBytes", TableInfo.Column("downloadBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("uploadBytes", TableInfo.Column("uploadBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("duration", TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("synack", TableInfo.Column("synack", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("rpid", TableInfo.Column("rpid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("message", TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRethinkLog.put("connType", TableInfo.Column("connType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRethinkLog: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRethinkLog: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesRethinkLog.add(TableInfo.Index("index_RethinkLog_ipAddress", false, listOf("ipAddress"), listOf("ASC")))
        _indicesRethinkLog.add(TableInfo.Index("index_RethinkLog_appName", false, listOf("appName"), listOf("ASC")))
        _indicesRethinkLog.add(TableInfo.Index("index_RethinkLog_dnsQuery", false, listOf("dnsQuery"), listOf("ASC")))
        _indicesRethinkLog.add(TableInfo.Index("index_RethinkLog_connId", false, listOf("connId"), listOf("ASC")))
        _indicesRethinkLog.add(TableInfo.Index("index_RethinkLog_timeStamp", false, listOf("timeStamp"), listOf("DESC")))
        val _infoRethinkLog: TableInfo = TableInfo("RethinkLog", _columnsRethinkLog, _foreignKeysRethinkLog, _indicesRethinkLog)
        val _existingRethinkLog: TableInfo = read(connection, "RethinkLog")
        if (!_infoRethinkLog.equals(_existingRethinkLog)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |RethinkLog(com.celzero.bravedns.database.RethinkLog).
              | Expected:
              |""".trimMargin() + _infoRethinkLog + """
              |
              | Found:
              |""".trimMargin() + _existingRethinkLog)
        }
        val _columnsIpInfo: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsIpInfo.put("ip", TableInfo.Column("ip", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsIpInfo.put("asn", TableInfo.Column("asn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsIpInfo.put("asName", TableInfo.Column("asName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsIpInfo.put("asDomain", TableInfo.Column("asDomain", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsIpInfo.put("countryCode", TableInfo.Column("countryCode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsIpInfo.put("country", TableInfo.Column("country", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsIpInfo.put("continentCode", TableInfo.Column("continentCode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsIpInfo.put("continent", TableInfo.Column("continent", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsIpInfo.put("createdTs", TableInfo.Column("createdTs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysIpInfo: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesIpInfo: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoIpInfo: TableInfo = TableInfo("IpInfo", _columnsIpInfo, _foreignKeysIpInfo, _indicesIpInfo)
        val _existingIpInfo: TableInfo = read(connection, "IpInfo")
        if (!_infoIpInfo.equals(_existingIpInfo)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |IpInfo(com.celzero.bravedns.database.IpInfo).
              | Expected:
              |""".trimMargin() + _infoIpInfo + """
              |
              | Found:
              |""".trimMargin() + _existingIpInfo)
        }
        val _columnsEvents: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEvents.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvents.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvents.put("eventType", TableInfo.Column("eventType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvents.put("severity", TableInfo.Column("severity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvents.put("message", TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvents.put("details", TableInfo.Column("details", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvents.put("source", TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEvents.put("userAction", TableInfo.Column("userAction", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEvents: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesEvents: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesEvents.add(TableInfo.Index("index_Events_timestamp", false, listOf("timestamp"), listOf("ASC")))
        _indicesEvents.add(TableInfo.Index("index_Events_eventType", false, listOf("eventType"), listOf("ASC")))
        _indicesEvents.add(TableInfo.Index("index_Events_severity", false, listOf("severity"), listOf("ASC")))
        _indicesEvents.add(TableInfo.Index("index_Events_source", false, listOf("source"), listOf("ASC")))
        val _infoEvents: TableInfo = TableInfo("Events", _columnsEvents, _foreignKeysEvents, _indicesEvents)
        val _existingEvents: TableInfo = read(connection, "Events")
        if (!_infoEvents.equals(_existingEvents)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |Events(com.celzero.bravedns.database.Event).
              | Expected:
              |""".trimMargin() + _infoEvents + """
              |
              | Found:
              |""".trimMargin() + _existingEvents)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "ConnectionTracker", "DnsLogs", "RethinkLog", "IpInfo", "Events")
  }

  public override fun clearAllTables() {
    super.performClear(false, "ConnectionTracker", "DnsLogs", "RethinkLog", "IpInfo", "Events")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ConnectionTrackerDAO::class, ConnectionTrackerDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(RethinkLogDao::class, RethinkLogDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(DnsLogDAO::class, DnsLogDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(LogDatabaseRawQueryDao::class, LogDatabaseRawQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(StatsSummaryDao::class, StatsSummaryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(IpInfoDAO::class, IpInfoDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(EventDao::class, EventDao_Impl.getRequiredConverters())
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

  public override fun connectionTrackerDAO(): ConnectionTrackerDAO = _connectionTrackerDAO.value

  public override fun rethinkConnectionLogDAO(): RethinkLogDao = _rethinkLogDao.value

  public override fun dnsLogDAO(): DnsLogDAO = _dnsLogDAO.value

  public override fun logsDao(): LogDatabaseRawQueryDao = _logDatabaseRawQueryDao.value

  public override fun statsSummaryDAO(): StatsSummaryDao = _statsSummaryDao.value

  public override fun ipInfoDao(): IpInfoDAO = _ipInfoDAO.value

  public override fun eventDao(): EventDao = _eventDao.value
}
