package com.celzero.bravedns.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performBlocking
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class DnsLogDAO_Impl(
  __db: RoomDatabase,
) : DnsLogDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfDnsLog: EntityInsertAdapter<DnsLog>
  init {
    this.__db = __db
    this.__insertAdapterOfDnsLog = object : EntityInsertAdapter<DnsLog>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `DnsLogs` (`id`,`uid`,`appName`,`packageName`,`queryStr`,`time`,`flag`,`resolver`,`latency`,`ttl`,`typeName`,`isBlocked`,`blockLists`,`serverIP`,`proxyId`,`relayIP`,`responseTime`,`response`,`status`,`dnsType`,`responseIps`,`resolverId`,`msg`,`upstreamBlock`,`region`,`isCached`,`dnssecOk`,`dnssecValid`,`blockedTarget`,`isEch`,`blockedReason`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DnsLog) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.uid.toLong())
        statement.bindText(3, entity.appName)
        statement.bindText(4, entity.packageName)
        statement.bindText(5, entity.queryStr)
        statement.bindLong(6, entity.time)
        statement.bindText(7, entity.flag)
        statement.bindText(8, entity.resolver)
        statement.bindLong(9, entity.latency)
        statement.bindLong(10, entity.ttl)
        statement.bindText(11, entity.typeName)
        val _tmp: Int = if (entity.isBlocked) 1 else 0
        statement.bindLong(12, _tmp.toLong())
        statement.bindText(13, entity.blockLists)
        statement.bindText(14, entity.serverIP)
        statement.bindText(15, entity.proxyId)
        statement.bindText(16, entity.relayIP)
        statement.bindLong(17, entity.responseTime)
        statement.bindText(18, entity.response)
        statement.bindText(19, entity.status)
        statement.bindLong(20, entity.dnsType.toLong())
        statement.bindText(21, entity.responseIps)
        statement.bindText(22, entity.resolverId)
        statement.bindText(23, entity.msg)
        val _tmp_1: Int = if (entity.upstreamBlock) 1 else 0
        statement.bindLong(24, _tmp_1.toLong())
        statement.bindText(25, entity.region)
        val _tmp_2: Int = if (entity.isCached) 1 else 0
        statement.bindLong(26, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.dnssecOk) 1 else 0
        statement.bindLong(27, _tmp_3.toLong())
        val _tmp_4: Int = if (entity.dnssecValid) 1 else 0
        statement.bindLong(28, _tmp_4.toLong())
        statement.bindText(29, entity.blockedTarget)
        val _tmp_5: Int = if (entity.isEch) 1 else 0
        statement.bindLong(30, _tmp_5.toLong())
        statement.bindText(31, entity.blockedReason)
      }
    }
  }

  public override fun insert(dnsLog: DnsLog): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDnsLog.insert(_connection, dnsLog)
  }

  public override fun insertBatch(dnsLogs: List<DnsLog>): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDnsLog.insert(_connection, dnsLogs)
  }

  public override fun getAllDnsLogs(): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllDnsLogs(uid: Int): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where uid = ? order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDnsLogsByName(searchString: String): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where (queryStr like ? or responseIps like ? or appName like ?) order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 2
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 3
      _stmt.bindText(_argIndex, searchString)
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDnsLogsByName(searchString: String, uid: Int): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where uid = ? and (queryStr like ? or responseIps like ? or appName like ?) order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 3
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 4
      _stmt.bindText(_argIndex, searchString)
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDnsLogsForWireGuard(wgDnsId: String): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where proxyId like ? order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, wgDnsId)
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDnsLogsForRpn(rpnDnsId: String): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where proxyId like ? order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, rpnDnsId)
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllowedDnsLogs(): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where isBlocked = 0 and blockLists = '' order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllowedDnsLogsByName(searchString: String): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where (queryStr like ? or responseIps like ? or flag = ? or appName like ?) and isBlocked = 0 and blockLists = '' order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 2
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 3
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 4
      _stmt.bindText(_argIndex, searchString)
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getBlockedDnsLogs(): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where isBlocked = 1 order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getBlockedDnsLogsByName(searchString: String): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where (queryStr like ? or responseIps like ? or flag = ? or appName like ?) and isBlocked = 1 order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 2
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 3
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 4
      _stmt.bindText(_argIndex, searchString)
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMaybeBlockedDnsLogs(): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where isBlocked = 0 and blockLists != '' order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getUnknownRecordDnsLogs(types: Set<String>): PagingSource<Int, DnsLog> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from DNSLogs where typeName not in (")
    val _inputSize: Int = types.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") order by id desc LIMIT 35000")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: String in types) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: DnsLog
            _item_1 = DnsLog()
            _item_1.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item_1.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item_1.appName = _stmt.getText(_columnIndexOfAppName)
            _item_1.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item_1.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item_1.time = _stmt.getLong(_columnIndexOfTime)
            _item_1.flag = _stmt.getText(_columnIndexOfFlag)
            _item_1.resolver = _stmt.getText(_columnIndexOfResolver)
            _item_1.latency = _stmt.getLong(_columnIndexOfLatency)
            _item_1.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item_1.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item_1.isBlocked = _tmp != 0
            _item_1.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item_1.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item_1.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item_1.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item_1.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item_1.response = _stmt.getText(_columnIndexOfResponse)
            _item_1.status = _stmt.getText(_columnIndexOfStatus)
            _item_1.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item_1.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item_1.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item_1.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item_1.upstreamBlock = _tmp_1 != 0
            _item_1.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item_1.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item_1.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item_1.dnssecValid = _tmp_4 != 0
            _item_1.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item_1.isEch = _tmp_5 != 0
            _item_1.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getUnknownRecordDnsLogsByName(searchString: String, types: Set<String>): PagingSource<Int, DnsLog> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from DNSLogs where (queryStr like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or responseIps like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or flag = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and typeName not in (")
    val _inputSize: Int = types.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") order by id desc LIMIT 35000")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 2
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 3
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 4
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 5
      for (_item: String in types) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: DnsLog
            _item_1 = DnsLog()
            _item_1.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item_1.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item_1.appName = _stmt.getText(_columnIndexOfAppName)
            _item_1.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item_1.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item_1.time = _stmt.getLong(_columnIndexOfTime)
            _item_1.flag = _stmt.getText(_columnIndexOfFlag)
            _item_1.resolver = _stmt.getText(_columnIndexOfResolver)
            _item_1.latency = _stmt.getLong(_columnIndexOfLatency)
            _item_1.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item_1.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item_1.isBlocked = _tmp != 0
            _item_1.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item_1.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item_1.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item_1.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item_1.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item_1.response = _stmt.getText(_columnIndexOfResponse)
            _item_1.status = _stmt.getText(_columnIndexOfStatus)
            _item_1.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item_1.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item_1.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item_1.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item_1.upstreamBlock = _tmp_1 != 0
            _item_1.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item_1.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item_1.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item_1.dnssecValid = _tmp_4 != 0
            _item_1.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item_1.isEch = _tmp_5 != 0
            _item_1.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMaybeBlockedDnsLogsByName(searchString: String): PagingSource<Int, DnsLog> {
    val _sql: String = "select * from DNSLogs where (queryStr like ? or responseIps like ? or flag = ? or appName like ?) and isBlocked = 0 and blockLists != '' order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 2
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 3
      _stmt.bindText(_argIndex, searchString)
      _argIndex = 4
      _stmt.bindText(_argIndex, searchString)
    }
    return object : LimitOffsetPagingSource<DnsLog>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
          val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
          val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
          val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
          val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
          val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
          val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
          val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
          val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
          val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
          val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
          val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
          val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
          val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
          val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
          val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
          val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
          val _result: MutableList<DnsLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsLog
            _item = DnsLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
            _item.time = _stmt.getLong(_columnIndexOfTime)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            _item.resolver = _stmt.getText(_columnIndexOfResolver)
            _item.latency = _stmt.getLong(_columnIndexOfLatency)
            _item.ttl = _stmt.getLong(_columnIndexOfTtl)
            _item.typeName = _stmt.getText(_columnIndexOfTypeName)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
            _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
            _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
            _item.response = _stmt.getText(_columnIndexOfResponse)
            _item.status = _stmt.getText(_columnIndexOfStatus)
            _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
            _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
            _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
            _item.msg = _stmt.getText(_columnIndexOfMsg)
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
            _item.upstreamBlock = _tmp_1 != 0
            _item.region = _stmt.getText(_columnIndexOfRegion)
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
            _item.isCached = _tmp_2 != 0
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
            _item.dnssecOk = _tmp_3 != 0
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
            _item.dnssecValid = _tmp_4 != 0
            _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
            _item.isEch = _tmp_5 != 0
            _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun logsCount(): LiveData<Long> {
    val _sql: String = "select count(id) from DNSLogs"
    return __db.invalidationTracker.createLiveData(arrayOf("DNSLogs"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Long?
        if (_stmt.step()) {
          val _tmp: Long?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0)
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getLeastLoggedTime(): Long {
    val _sql: String = "select time from DNSLogs where id = (select min(id) from DNSLogs)"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Long
        if (_stmt.step()) {
          _result = _stmt.getLong(0)
        } else {
          _result = 0L
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRecentlyBlockedDnsApps(time: Long): List<BlockedDnsAppResult> {
    val _sql: String = "SELECT uid AS uid, MAX(time) AS lastBlocked, COUNT(*) AS count FROM DNSLogs WHERE isBlocked = 1 AND time > ? GROUP BY uid ORDER BY lastBlocked DESC LIMIT 10"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, time)
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfLastBlocked: Int = 1
        val _columnIndexOfCount: Int = 2
        val _result: MutableList<BlockedDnsAppResult> = mutableListOf()
        while (_stmt.step()) {
          val _item: BlockedDnsAppResult
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpLastBlocked: Long
          _tmpLastBlocked = _stmt.getLong(_columnIndexOfLastBlocked)
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          _item = BlockedDnsAppResult(_tmpUid,_tmpLastBlocked,_tmpCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRecentlyBlockedDnsAppsPaged(time: Long): PagingSource<Int, BlockedDnsAppResult> {
    val _sql: String = "SELECT uid AS uid, MAX(time) AS lastBlocked, COUNT(*) AS count FROM DNSLogs WHERE isBlocked = 1 AND time > ? GROUP BY uid ORDER BY lastBlocked DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, time)
    }
    return object : LimitOffsetPagingSource<BlockedDnsAppResult>(_rawQuery, __db, "DNSLogs") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<BlockedDnsAppResult> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfLastBlocked: Int = 1
          val _columnIndexOfCount: Int = 2
          val _result: MutableList<BlockedDnsAppResult> = mutableListOf()
          while (_stmt.step()) {
            val _item: BlockedDnsAppResult
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpLastBlocked: Long
            _tmpLastBlocked = _stmt.getLong(_columnIndexOfLastBlocked)
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            _item = BlockedDnsAppResult(_tmpUid,_tmpLastBlocked,_tmpCount)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override suspend fun getActivityBuckets(
    rangeStart: Long,
    rangeEnd: Long,
    bucketMs: Long,
  ): List<ActivityBucketRow> {
    val _sql: String = "select cast((time - ?)/? as integer) as bucketIndex, isBlocked as blocked, count(id) as total from DNSLogs where time >= ? and time < ? group by bucketIndex, blocked"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, rangeStart)
        _argIndex = 2
        _stmt.bindLong(_argIndex, bucketMs)
        _argIndex = 3
        _stmt.bindLong(_argIndex, rangeStart)
        _argIndex = 4
        _stmt.bindLong(_argIndex, rangeEnd)
        val _columnIndexOfBucketIndex: Int = 0
        val _columnIndexOfBlocked: Int = 1
        val _columnIndexOfTotal: Int = 2
        val _result: MutableList<ActivityBucketRow> = mutableListOf()
        while (_stmt.step()) {
          val _item: ActivityBucketRow
          val _tmpBucketIndex: Long
          _tmpBucketIndex = _stmt.getLong(_columnIndexOfBucketIndex)
          val _tmpBlocked: Int
          _tmpBlocked = _stmt.getLong(_columnIndexOfBlocked).toInt()
          val _tmpTotal: Long
          _tmpTotal = _stmt.getLong(_columnIndexOfTotal)
          _item = ActivityBucketRow(_tmpBucketIndex,_tmpBlocked,_tmpTotal)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRpnActivityBuckets(
    proxyIdFilter: String,
    rangeStart: Long,
    rangeEnd: Long,
    bucketMs: Long,
  ): List<ActivityBucketRow> {
    val _sql: String = "select cast((time - ?)/? as integer) as bucketIndex, isBlocked as blocked, count(id) as total from DNSLogs where time >= ? and time < ? and proxyId like ? group by bucketIndex, blocked"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, rangeStart)
        _argIndex = 2
        _stmt.bindLong(_argIndex, bucketMs)
        _argIndex = 3
        _stmt.bindLong(_argIndex, rangeStart)
        _argIndex = 4
        _stmt.bindLong(_argIndex, rangeEnd)
        _argIndex = 5
        _stmt.bindText(_argIndex, proxyIdFilter)
        val _columnIndexOfBucketIndex: Int = 0
        val _columnIndexOfBlocked: Int = 1
        val _columnIndexOfTotal: Int = 2
        val _result: MutableList<ActivityBucketRow> = mutableListOf()
        while (_stmt.step()) {
          val _item: ActivityBucketRow
          val _tmpBucketIndex: Long
          _tmpBucketIndex = _stmt.getLong(_columnIndexOfBucketIndex)
          val _tmpBlocked: Int
          _tmpBlocked = _stmt.getLong(_columnIndexOfBlocked).toInt()
          val _tmpTotal: Long
          _tmpTotal = _stmt.getLong(_columnIndexOfTotal)
          _item = ActivityBucketRow(_tmpBucketIndex,_tmpBlocked,_tmpTotal)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getWindowCounts(start: Long, end: Long): WindowCountRow {
    val _sql: String = "select coalesce(sum(case when isBlocked then 1 else 0 end), 0) as blocked, count(*) as total from DNSLogs where time >= ? and time < ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        val _columnIndexOfBlocked: Int = 0
        val _columnIndexOfTotal: Int = 1
        val _result: WindowCountRow
        if (_stmt.step()) {
          val _tmpBlocked: Long
          _tmpBlocked = _stmt.getLong(_columnIndexOfBlocked)
          val _tmpTotal: Long
          _tmpTotal = _stmt.getLong(_columnIndexOfTotal)
          _result = WindowCountRow(_tmpBlocked,_tmpTotal)
        } else {
          error("The query result was empty, but expected a single row to return a NON-NULL object of type 'com.celzero.bravedns.database.WindowCountRow'.")
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countBlockedDnsSince(since: Long): Int {
    val _sql: String = "select count(id) from DNSLogs where isBlocked = 1 and time > ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, since)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getDnsLogsInWindow(
    start: Long,
    end: Long,
    limit: Int,
  ): List<DnsLog> {
    val _sql: String = "select * from DNSLogs where time >= ? and time < ? order by id desc limit ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
        val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
        val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
        val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
        val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
        val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
        val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
        val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
        val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
        val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
        val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
        val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
        val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
        val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
        val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
        val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
        val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
        val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
        val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
        val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
        val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
        val _result: MutableList<DnsLog> = mutableListOf()
        while (_stmt.step()) {
          val _item: DnsLog
          _item = DnsLog()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.packageName = _stmt.getText(_columnIndexOfPackageName)
          _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
          _item.time = _stmt.getLong(_columnIndexOfTime)
          _item.flag = _stmt.getText(_columnIndexOfFlag)
          _item.resolver = _stmt.getText(_columnIndexOfResolver)
          _item.latency = _stmt.getLong(_columnIndexOfLatency)
          _item.ttl = _stmt.getLong(_columnIndexOfTtl)
          _item.typeName = _stmt.getText(_columnIndexOfTypeName)
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
          _item.isBlocked = _tmp != 0
          _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
          _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
          _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
          _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
          _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
          _item.response = _stmt.getText(_columnIndexOfResponse)
          _item.status = _stmt.getText(_columnIndexOfStatus)
          _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
          _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
          _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
          _item.msg = _stmt.getText(_columnIndexOfMsg)
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
          _item.upstreamBlock = _tmp_1 != 0
          _item.region = _stmt.getText(_columnIndexOfRegion)
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
          _item.isCached = _tmp_2 != 0
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
          _item.dnssecOk = _tmp_3 != 0
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
          _item.dnssecValid = _tmp_4 != 0
          _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
          _item.isEch = _tmp_5 != 0
          _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAppActivity(
    start: Long,
    end: Long,
    limit: Int,
  ): List<AppActivityRow> {
    val _sql: String = "select uid as uid, appName as appName, count(id) as total, sum(case when isBlocked then 1 else 0 end) as blocked from DNSLogs where time >= ? and time < ? group by uid, appName order by total desc limit ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfAppName: Int = 1
        val _columnIndexOfTotal: Int = 2
        val _columnIndexOfBlocked: Int = 3
        val _result: MutableList<AppActivityRow> = mutableListOf()
        while (_stmt.step()) {
          val _item: AppActivityRow
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpTotal: Long
          _tmpTotal = _stmt.getLong(_columnIndexOfTotal)
          val _tmpBlocked: Long
          _tmpBlocked = _stmt.getLong(_columnIndexOfBlocked)
          _item = AppActivityRow(_tmpUid,_tmpAppName,_tmpTotal,_tmpBlocked)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTopBlockedApps(
    start: Long,
    end: Long,
    limit: Int,
  ): List<AppBlockedRow> {
    val _sql: String = "select uid as uid, appName as appName, sum(case when isBlocked then 1 else 0 end) as blocked, max(time) as lastSeen from DNSLogs where time >= ? and time < ? group by uid, appName having blocked > 0 order by blocked desc limit ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfAppName: Int = 1
        val _columnIndexOfBlocked: Int = 2
        val _columnIndexOfLastSeen: Int = 3
        val _result: MutableList<AppBlockedRow> = mutableListOf()
        while (_stmt.step()) {
          val _item: AppBlockedRow
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpBlocked: Long
          _tmpBlocked = _stmt.getLong(_columnIndexOfBlocked)
          val _tmpLastSeen: Long
          _tmpLastSeen = _stmt.getLong(_columnIndexOfLastSeen)
          _item = AppBlockedRow(_tmpUid,_tmpAppName,_tmpBlocked,_tmpLastSeen)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getDnsLogsInWindowForUid(
    start: Long,
    end: Long,
    uid: Int,
    limit: Int,
  ): List<DnsLog> {
    val _sql: String = "select * from DNSLogs where time >= ? and time < ? and uid = ? order by id desc limit ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        _argIndex = 3
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfQueryStr: Int = getColumnIndexOrThrow(_stmt, "queryStr")
        val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
        val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
        val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _columnIndexOfTtl: Int = getColumnIndexOrThrow(_stmt, "ttl")
        val _columnIndexOfTypeName: Int = getColumnIndexOrThrow(_stmt, "typeName")
        val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
        val _columnIndexOfBlockLists: Int = getColumnIndexOrThrow(_stmt, "blockLists")
        val _columnIndexOfServerIP: Int = getColumnIndexOrThrow(_stmt, "serverIP")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfRelayIP: Int = getColumnIndexOrThrow(_stmt, "relayIP")
        val _columnIndexOfResponseTime: Int = getColumnIndexOrThrow(_stmt, "responseTime")
        val _columnIndexOfResponse: Int = getColumnIndexOrThrow(_stmt, "response")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfDnsType: Int = getColumnIndexOrThrow(_stmt, "dnsType")
        val _columnIndexOfResponseIps: Int = getColumnIndexOrThrow(_stmt, "responseIps")
        val _columnIndexOfResolverId: Int = getColumnIndexOrThrow(_stmt, "resolverId")
        val _columnIndexOfMsg: Int = getColumnIndexOrThrow(_stmt, "msg")
        val _columnIndexOfUpstreamBlock: Int = getColumnIndexOrThrow(_stmt, "upstreamBlock")
        val _columnIndexOfRegion: Int = getColumnIndexOrThrow(_stmt, "region")
        val _columnIndexOfIsCached: Int = getColumnIndexOrThrow(_stmt, "isCached")
        val _columnIndexOfDnssecOk: Int = getColumnIndexOrThrow(_stmt, "dnssecOk")
        val _columnIndexOfDnssecValid: Int = getColumnIndexOrThrow(_stmt, "dnssecValid")
        val _columnIndexOfBlockedTarget: Int = getColumnIndexOrThrow(_stmt, "blockedTarget")
        val _columnIndexOfIsEch: Int = getColumnIndexOrThrow(_stmt, "isEch")
        val _columnIndexOfBlockedReason: Int = getColumnIndexOrThrow(_stmt, "blockedReason")
        val _result: MutableList<DnsLog> = mutableListOf()
        while (_stmt.step()) {
          val _item: DnsLog
          _item = DnsLog()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.packageName = _stmt.getText(_columnIndexOfPackageName)
          _item.queryStr = _stmt.getText(_columnIndexOfQueryStr)
          _item.time = _stmt.getLong(_columnIndexOfTime)
          _item.flag = _stmt.getText(_columnIndexOfFlag)
          _item.resolver = _stmt.getText(_columnIndexOfResolver)
          _item.latency = _stmt.getLong(_columnIndexOfLatency)
          _item.ttl = _stmt.getLong(_columnIndexOfTtl)
          _item.typeName = _stmt.getText(_columnIndexOfTypeName)
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
          _item.isBlocked = _tmp != 0
          _item.blockLists = _stmt.getText(_columnIndexOfBlockLists)
          _item.serverIP = _stmt.getText(_columnIndexOfServerIP)
          _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
          _item.relayIP = _stmt.getText(_columnIndexOfRelayIP)
          _item.responseTime = _stmt.getLong(_columnIndexOfResponseTime)
          _item.response = _stmt.getText(_columnIndexOfResponse)
          _item.status = _stmt.getText(_columnIndexOfStatus)
          _item.dnsType = _stmt.getLong(_columnIndexOfDnsType).toInt()
          _item.responseIps = _stmt.getText(_columnIndexOfResponseIps)
          _item.resolverId = _stmt.getText(_columnIndexOfResolverId)
          _item.msg = _stmt.getText(_columnIndexOfMsg)
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfUpstreamBlock).toInt()
          _item.upstreamBlock = _tmp_1 != 0
          _item.region = _stmt.getText(_columnIndexOfRegion)
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsCached).toInt()
          _item.isCached = _tmp_2 != 0
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfDnssecOk).toInt()
          _item.dnssecOk = _tmp_3 != 0
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfDnssecValid).toInt()
          _item.dnssecValid = _tmp_4 != 0
          _item.blockedTarget = _stmt.getText(_columnIndexOfBlockedTarget)
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfIsEch).toInt()
          _item.isEch = _tmp_5 != 0
          _item.blockedReason = _stmt.getText(_columnIndexOfBlockedReason)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getDomainActivityForUid(
    start: Long,
    end: Long,
    uid: Int,
    limit: Int,
  ): List<DomainActivityRow> {
    val _sql: String = "select queryStr as label, count(id) as total, sum(case when isBlocked then 1 else 0 end) as blocked, max(time) as lastSeen, substr(max(printf('%016d', time) || flag), 17) as flag from DNSLogs where time >= ? and time < ? and uid = ? and queryStr != '' group by queryStr order by total desc limit ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        _argIndex = 3
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfLabel: Int = 0
        val _columnIndexOfTotal: Int = 1
        val _columnIndexOfBlocked: Int = 2
        val _columnIndexOfLastSeen: Int = 3
        val _columnIndexOfFlag: Int = 4
        val _result: MutableList<DomainActivityRow> = mutableListOf()
        while (_stmt.step()) {
          val _item: DomainActivityRow
          val _tmpLabel: String
          _tmpLabel = _stmt.getText(_columnIndexOfLabel)
          val _tmpTotal: Long
          _tmpTotal = _stmt.getLong(_columnIndexOfTotal)
          val _tmpBlocked: Long
          _tmpBlocked = _stmt.getLong(_columnIndexOfBlocked)
          val _tmpLastSeen: Long
          _tmpLastSeen = _stmt.getLong(_columnIndexOfLastSeen)
          val _tmpFlag: String
          _tmpFlag = _stmt.getText(_columnIndexOfFlag)
          _item = DomainActivityRow(_tmpLabel,_tmpTotal,_tmpBlocked,_tmpLastSeen,_tmpFlag)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearAllData() {
    val _sql: String = "delete from DNSLogs"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun purgeDnsLogsByDate(date: Long) {
    val _sql: String = "delete from DNSLogs where time < ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, date)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
