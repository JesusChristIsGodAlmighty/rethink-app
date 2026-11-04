package com.celzero.bravedns.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performBlocking
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.celzero.bravedns.`data`.AppConnection
import com.celzero.bravedns.`data`.DataUsage
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RethinkLogDao_Impl(
  __db: RoomDatabase,
) : RethinkLogDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRethinkLog: EntityInsertAdapter<RethinkLog>

  private val __deleteAdapterOfRethinkLog: EntityDeleteOrUpdateAdapter<RethinkLog>

  private val __updateAdapterOfRethinkLog: EntityDeleteOrUpdateAdapter<RethinkLog>
  init {
    this.__db = __db
    this.__insertAdapterOfRethinkLog = object : EntityInsertAdapter<RethinkLog>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `RethinkLog` (`id`,`appName`,`uid`,`usrId`,`ipAddress`,`port`,`protocol`,`isBlocked`,`blockedByRule`,`blocklists`,`proxyDetails`,`flag`,`dnsQuery`,`timeStamp`,`connId`,`downloadBytes`,`uploadBytes`,`duration`,`synack`,`rpid`,`message`,`connType`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkLog) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.appName)
        statement.bindLong(3, entity.uid.toLong())
        statement.bindLong(4, entity.usrId.toLong())
        statement.bindText(5, entity.ipAddress)
        statement.bindLong(6, entity.port.toLong())
        statement.bindLong(7, entity.protocol.toLong())
        val _tmp: Int = if (entity.isBlocked) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindText(9, entity.blockedByRule)
        statement.bindText(10, entity.blocklists)
        statement.bindText(11, entity.proxyDetails)
        statement.bindText(12, entity.flag)
        val _tmpDnsQuery: String? = entity.dnsQuery
        if (_tmpDnsQuery == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpDnsQuery)
        }
        statement.bindLong(14, entity.timeStamp)
        statement.bindText(15, entity.connId)
        statement.bindLong(16, entity.downloadBytes)
        statement.bindLong(17, entity.uploadBytes)
        statement.bindLong(18, entity.duration.toLong())
        statement.bindLong(19, entity.synack)
        statement.bindText(20, entity.rpid)
        statement.bindText(21, entity.message)
        statement.bindText(22, entity.connType)
      }
    }
    this.__deleteAdapterOfRethinkLog = object : EntityDeleteOrUpdateAdapter<RethinkLog>() {
      protected override fun createQuery(): String = "DELETE FROM `RethinkLog` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkLog) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfRethinkLog = object : EntityDeleteOrUpdateAdapter<RethinkLog>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `RethinkLog` SET `id` = ?,`appName` = ?,`uid` = ?,`usrId` = ?,`ipAddress` = ?,`port` = ?,`protocol` = ?,`isBlocked` = ?,`blockedByRule` = ?,`blocklists` = ?,`proxyDetails` = ?,`flag` = ?,`dnsQuery` = ?,`timeStamp` = ?,`connId` = ?,`downloadBytes` = ?,`uploadBytes` = ?,`duration` = ?,`synack` = ?,`rpid` = ?,`message` = ?,`connType` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkLog) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.appName)
        statement.bindLong(3, entity.uid.toLong())
        statement.bindLong(4, entity.usrId.toLong())
        statement.bindText(5, entity.ipAddress)
        statement.bindLong(6, entity.port.toLong())
        statement.bindLong(7, entity.protocol.toLong())
        val _tmp: Int = if (entity.isBlocked) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindText(9, entity.blockedByRule)
        statement.bindText(10, entity.blocklists)
        statement.bindText(11, entity.proxyDetails)
        statement.bindText(12, entity.flag)
        val _tmpDnsQuery: String? = entity.dnsQuery
        if (_tmpDnsQuery == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpDnsQuery)
        }
        statement.bindLong(14, entity.timeStamp)
        statement.bindText(15, entity.connId)
        statement.bindLong(16, entity.downloadBytes)
        statement.bindLong(17, entity.uploadBytes)
        statement.bindLong(18, entity.duration.toLong())
        statement.bindLong(19, entity.synack)
        statement.bindText(20, entity.rpid)
        statement.bindText(21, entity.message)
        statement.bindText(22, entity.connType)
        statement.bindLong(23, entity.id.toLong())
      }
    }
  }

  public override fun insert(log: RethinkLog): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfRethinkLog.insert(_connection, log)
  }

  public override fun insertBatch(logs: List<RethinkLog>): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfRethinkLog.insert(_connection, logs)
  }

  public override fun delete(log: RethinkLog): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfRethinkLog.handle(_connection, log)
  }

  public override fun update(log: RethinkLog): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfRethinkLog.handle(_connection, log)
  }

  public override suspend fun getActivityBuckets(
    rangeStart: Long,
    rangeEnd: Long,
    bucketMs: Long,
  ): List<ActivityBucketRow> {
    val _sql: String = "select cast((timeStamp - ?)/? as integer) as bucketIndex, isBlocked as blocked, count(id) as total from RethinkLog where timeStamp >= ? and timeStamp < ? group by bucketIndex, blocked"
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
    val _sql: String = "select cast((timeStamp - ?)/? as integer) as bucketIndex, isBlocked as blocked, count(id) as total from RethinkLog where timeStamp >= ? and timeStamp < ? and proxyDetails like ? group by bucketIndex, blocked"
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
    val _sql: String = "select coalesce(sum(case when isBlocked then 1 else 0 end), 0) as blocked, count(*) as total from RethinkLog where timeStamp >= ? and timeStamp < ?"
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

  public override suspend fun getRethinkLogsInWindow(
    start: Long,
    end: Long,
    limit: Int,
  ): List<RethinkLog> {
    val _sql: String = "select * from RethinkLog where timeStamp >= ? and timeStamp < ? order by id desc limit ?"
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
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
        val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
        val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
        val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
        val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
        val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
        val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
        val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
        val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
        val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
        val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
        val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
        val _result: MutableList<RethinkLog> = mutableListOf()
        while (_stmt.step()) {
          val _item: RethinkLog
          _item = RethinkLog()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
          _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
          _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
          _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
          _item.isBlocked = _tmp != 0
          _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
          _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
          _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
          _item.flag = _stmt.getText(_columnIndexOfFlag)
          if (_stmt.isNull(_columnIndexOfDnsQuery)) {
            _item.dnsQuery = null
          } else {
            _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
          }
          _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
          _item.connId = _stmt.getText(_columnIndexOfConnId)
          _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
          _item.synack = _stmt.getLong(_columnIndexOfSynack)
          _item.rpid = _stmt.getText(_columnIndexOfRpid)
          _item.message = _stmt.getText(_columnIndexOfMessage)
          _item.connType = _stmt.getText(_columnIndexOfConnType)
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
    val _sql: String = "select uid as uid, appName as appName, count(id) as total, sum(case when isBlocked then 1 else 0 end) as blocked from RethinkLog where timeStamp >= ? and timeStamp < ? group by uid, appName order by total desc limit ?"
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

  public override suspend fun getRethinkLogsInWindowForUid(
    start: Long,
    end: Long,
    uid: Int,
    limit: Int,
  ): List<RethinkLog> {
    val _sql: String = "select * from RethinkLog where timeStamp >= ? and timeStamp < ? and uid = ? order by id desc limit ?"
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
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
        val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
        val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
        val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
        val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
        val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
        val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
        val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
        val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
        val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
        val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
        val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
        val _result: MutableList<RethinkLog> = mutableListOf()
        while (_stmt.step()) {
          val _item: RethinkLog
          _item = RethinkLog()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
          _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
          _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
          _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
          _item.isBlocked = _tmp != 0
          _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
          _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
          _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
          _item.flag = _stmt.getText(_columnIndexOfFlag)
          if (_stmt.isNull(_columnIndexOfDnsQuery)) {
            _item.dnsQuery = null
          } else {
            _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
          }
          _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
          _item.connId = _stmt.getText(_columnIndexOfConnId)
          _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
          _item.synack = _stmt.getLong(_columnIndexOfSynack)
          _item.rpid = _stmt.getText(_columnIndexOfRpid)
          _item.message = _stmt.getText(_columnIndexOfMessage)
          _item.connType = _stmt.getText(_columnIndexOfConnType)
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
    val _sql: String = "select coalesce(nullif(dnsQuery, ''), ipAddress) as label, count(id) as total, sum(case when isBlocked then 1 else 0 end) as blocked, max(timeStamp) as lastSeen, substr(max(printf('%016d', timeStamp) || flag), 17) as flag from RethinkLog where timeStamp >= ? and timeStamp < ? and uid = ? group by label order by total desc limit ?"
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

  public override fun getRethinkLogByName(): PagingSource<Int, RethinkLog> {
    val _sql: String = "select * from RethinkLog order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<RethinkLog>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
          val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
          val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
          val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
          val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
          val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
          val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
          val _result: MutableList<RethinkLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkLog
            _item = RethinkLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item.dnsQuery = null
            } else {
              _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item.connId = _stmt.getText(_columnIndexOfConnId)
            _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item.synack = _stmt.getLong(_columnIndexOfSynack)
            _item.rpid = _stmt.getText(_columnIndexOfRpid)
            _item.message = _stmt.getText(_columnIndexOfMessage)
            _item.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getRethinkLogByName(query: String): PagingSource<Int, RethinkLog> {
    val _sql: String = "select * from RethinkLog where (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ?) order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<RethinkLog>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
          val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
          val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
          val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
          val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
          val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
          val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
          val _result: MutableList<RethinkLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkLog
            _item = RethinkLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item.dnsQuery = null
            } else {
              _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item.connId = _stmt.getText(_columnIndexOfConnId)
            _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item.synack = _stmt.getLong(_columnIndexOfSynack)
            _item.rpid = _stmt.getText(_columnIndexOfRpid)
            _item.message = _stmt.getText(_columnIndexOfMessage)
            _item.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getBlockedConnections(): PagingSource<Int, RethinkLog> {
    val _sql: String = "select * from RethinkLog where isBlocked = 1 order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<RethinkLog>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
          val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
          val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
          val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
          val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
          val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
          val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
          val _result: MutableList<RethinkLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkLog
            _item = RethinkLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item.dnsQuery = null
            } else {
              _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item.connId = _stmt.getText(_columnIndexOfConnId)
            _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item.synack = _stmt.getLong(_columnIndexOfSynack)
            _item.rpid = _stmt.getText(_columnIndexOfRpid)
            _item.message = _stmt.getText(_columnIndexOfMessage)
            _item.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getBlockedConnections(query: String): PagingSource<Int, RethinkLog> {
    val _sql: String = "select * from RethinkLog where  (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ?) and isBlocked = 1 order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<RethinkLog>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
          val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
          val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
          val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
          val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
          val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
          val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
          val _result: MutableList<RethinkLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkLog
            _item = RethinkLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item.dnsQuery = null
            } else {
              _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item.connId = _stmt.getText(_columnIndexOfConnId)
            _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item.synack = _stmt.getLong(_columnIndexOfSynack)
            _item.rpid = _stmt.getText(_columnIndexOfRpid)
            _item.message = _stmt.getText(_columnIndexOfMessage)
            _item.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getLogsForApp(uid: Int): PagingSource<Int, AppConnection> {
    val _sql: String = "select uid as uid, ipAddress as ipAddress, port as port, count(ipAddress) as count, flag, 0 as blocked, GROUP_CONCAT(DISTINCT dnsQuery) as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes from RethinkLog where uid = ? group by ipAddress order by count desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getLogsForAppFiltered(uid: Int, ipAddress: String): PagingSource<Int, AppConnection> {
    val _sql: String = "select uid as uid, ipAddress as ipAddress, port as port, count(ipAddress) as count, flag, 0 as blocked, GROUP_CONCAT(DISTINCT dnsQuery) as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes from RethinkLog where uid = ? and ipAddress like ? group by ipAddress order by count desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindText(_argIndex, ipAddress)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getBlockedConnectionsFiltered(): PagingSource<Int, RethinkLog> {
    val _sql: String = "select * from RethinkLog where isBlocked = 1 order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<RethinkLog>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
          val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
          val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
          val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
          val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
          val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
          val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
          val _result: MutableList<RethinkLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkLog
            _item = RethinkLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item.dnsQuery = null
            } else {
              _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item.connId = _stmt.getText(_columnIndexOfConnId)
            _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item.synack = _stmt.getLong(_columnIndexOfSynack)
            _item.rpid = _stmt.getText(_columnIndexOfRpid)
            _item.message = _stmt.getText(_columnIndexOfMessage)
            _item.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getBlockedConnectionsFiltered(query: String): PagingSource<Int, RethinkLog> {
    val _sql: String = "select * from RethinkLog where isBlocked = 1 and (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ?) order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<RethinkLog>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
          val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
          val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
          val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
          val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
          val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
          val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
          val _result: MutableList<RethinkLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkLog
            _item = RethinkLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item.dnsQuery = null
            } else {
              _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item.connId = _stmt.getText(_columnIndexOfConnId)
            _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item.synack = _stmt.getLong(_columnIndexOfSynack)
            _item.rpid = _stmt.getText(_columnIndexOfRpid)
            _item.message = _stmt.getText(_columnIndexOfMessage)
            _item.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllowedConnections(query: String): PagingSource<Int, RethinkLog> {
    val _sql: String = "select * from RethinkLog where isBlocked = 0 and  (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ?) order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<RethinkLog>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
          val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
          val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
          val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
          val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
          val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
          val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
          val _result: MutableList<RethinkLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkLog
            _item = RethinkLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item.dnsQuery = null
            } else {
              _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item.connId = _stmt.getText(_columnIndexOfConnId)
            _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item.synack = _stmt.getLong(_columnIndexOfSynack)
            _item.rpid = _stmt.getText(_columnIndexOfRpid)
            _item.message = _stmt.getText(_columnIndexOfMessage)
            _item.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllowedConnections(): PagingSource<Int, RethinkLog> {
    val _sql: String = "select * from RethinkLog where isBlocked = 0 order by id desc LIMIT 35000"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<RethinkLog>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfUsrId: Int = getColumnIndexOrThrow(_stmt, "usrId")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsBlocked: Int = getColumnIndexOrThrow(_stmt, "isBlocked")
          val _columnIndexOfBlockedByRule: Int = getColumnIndexOrThrow(_stmt, "blockedByRule")
          val _columnIndexOfBlocklists: Int = getColumnIndexOrThrow(_stmt, "blocklists")
          val _columnIndexOfProxyDetails: Int = getColumnIndexOrThrow(_stmt, "proxyDetails")
          val _columnIndexOfFlag: Int = getColumnIndexOrThrow(_stmt, "flag")
          val _columnIndexOfDnsQuery: Int = getColumnIndexOrThrow(_stmt, "dnsQuery")
          val _columnIndexOfTimeStamp: Int = getColumnIndexOrThrow(_stmt, "timeStamp")
          val _columnIndexOfConnId: Int = getColumnIndexOrThrow(_stmt, "connId")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
          val _columnIndexOfSynack: Int = getColumnIndexOrThrow(_stmt, "synack")
          val _columnIndexOfRpid: Int = getColumnIndexOrThrow(_stmt, "rpid")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfConnType: Int = getColumnIndexOrThrow(_stmt, "connType")
          val _result: MutableList<RethinkLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkLog
            _item = RethinkLog()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item.isBlocked = _tmp != 0
            _item.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item.dnsQuery = null
            } else {
              _item.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item.connId = _stmt.getText(_columnIndexOfConnId)
            _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item.synack = _stmt.getLong(_columnIndexOfSynack)
            _item.rpid = _stmt.getText(_columnIndexOfRpid)
            _item.message = _stmt.getText(_columnIndexOfMessage)
            _item.connType = _stmt.getText(_columnIndexOfConnType)
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
    val _sql: String = "select count(id) from RethinkLog"
    return __db.invalidationTracker.createLiveData(arrayOf("RethinkLog"), false) { _connection ->
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
    val _sql: String = "select timeStamp from RethinkLog where id = (select min(id) from RethinkLog)"
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

  public override fun getDataUsage(fromTime: Long, toTime: Long): DataUsage? {
    val _sql: String = "SELECT uid, SUM(uploadBytes) AS uploadBytes, SUM(downloadBytes) AS downloadBytes FROM RethinkLog where timeStamp >= ? and timeStamp <= ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, fromTime)
        _argIndex = 2
        _stmt.bindLong(_argIndex, toTime)
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfUploadBytes: Int = 1
        val _columnIndexOfDownloadBytes: Int = 2
        val _result: DataUsage?
        if (_stmt.step()) {
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpUploadBytes: Long
          _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          val _tmpDownloadBytes: Long
          _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _result = DataUsage(_tmpUid,_tmpUploadBytes,_tmpDownloadBytes)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDomainLogsLimited(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, '' as ipAddress, port, COUNT(dnsQuery) as count, flag as flag, 0 as blocked, dnsQuery as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM RethinkLog WHERE timeStamp > ? and dnsQuery != '' GROUP BY dnsQuery ORDER BY count DESC LIMIT 3"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getBlockedDomainLogsLimited(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, '' as ipAddress, port, COUNT(dnsQuery) as count, flag as flag, 1 as blocked, dnsQuery as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM RethinkLog WHERE timeStamp > ? and dnsQuery != '' and isBlocked = 1 GROUP BY dnsQuery ORDER BY count DESC LIMIT 3"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getIpLogsLimited(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, ipAddress, port, COUNT(ipAddress) as count, flag as flag, 0 as blocked, '' as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM RethinkLog WHERE timeStamp > ? GROUP BY uid, ipAddress, port ORDER BY count DESC LIMIT 3"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getBlockedIpLogsLimited(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, ipAddress, port, COUNT(ipAddress) as count, flag as flag, 1 as blocked, '' as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM RethinkLog WHERE timeStamp > ? and isBlocked = 1 GROUP BY uid, ipAddress, port ORDER BY count DESC LIMIT 3"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getIpLogs(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, ipAddress, port, COUNT(ipAddress) as count, flag as flag, 0 as blocked, GROUP_CONCAT(DISTINCT dnsQuery) as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM RethinkLog WHERE timeStamp > ? GROUP BY uid, ipAddress, port ORDER BY count DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getCountryLogsLimited(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT 0 as uid, '' as ipAddress, 0 as port, COUNT(id) as count, flag as flag, 0 as blocked, '' as appOrDnsName, 0 as downloadBytes, 0 as uploadBytes, 0 as totalBytes FROM RethinkLog WHERE timeStamp > ? AND LENGTH(flag) = 2 AND unicode(flag) BETWEEN 127462 AND 127487 AND flag != '🇿🇿' GROUP BY flag ORDER BY count DESC LIMIT 3"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllCountryLogs(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT 0 as uid, '' as ipAddress, 0 as port, COUNT(id) as count, flag as flag, 0 as blocked, '' as appOrDnsName, 0 as downloadBytes, 0 as uploadBytes, 0 as totalBytes FROM RethinkLog WHERE timeStamp > ? AND LENGTH(flag) = 2 AND unicode(flag) BETWEEN 127462 AND 127487 AND flag != '🇿🇿' GROUP BY flag ORDER BY count DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getIpLogsFiltered(
    to: Long,
    query: String,
    isBlocked: Boolean?,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, ipAddress, port, COUNT(ipAddress) as count, flag as flag, MAX(isBlocked) as blocked, GROUP_CONCAT(DISTINCT dnsQuery) as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM RethinkLog WHERE timeStamp > ? and ipAddress like ? and (? IS NULL OR isBlocked = ?) GROUP BY  uid, ipAddress, port ORDER BY count DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      val _tmp: Int? = isBlocked?.let { if (it) 1 else 0 }
      if (_tmp == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindLong(_argIndex, _tmp.toLong())
      }
      _argIndex = 4
      val _tmp_1: Int? = isBlocked?.let { if (it) 1 else 0 }
      if (_tmp_1 == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindLong(_argIndex, _tmp_1.toLong())
      }
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp_2 != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDomainLogs(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, GROUP_CONCAT(DISTINCT ipAddress) as ipAddress, port, COUNT(dnsQuery) as count, flag as flag, 0 as blocked, dnsQuery as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM RethinkLog WHERE timeStamp > ? and dnsQuery != '' GROUP BY dnsQuery ORDER BY count DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDomainLogsFiltered(
    to: Long,
    query: String,
    isBlocked: Boolean?,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, GROUP_CONCAT(DISTINCT ipAddress) as ipAddress, port, COUNT(dnsQuery) as count, flag as flag, MAX(isBlocked) as blocked, dnsQuery as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM RethinkLog WHERE timeStamp > ? and dnsQuery != '' and dnsQuery like ? and (? IS NULL OR isBlocked = ?) GROUP BY dnsQuery ORDER BY count DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      val _tmp: Int? = isBlocked?.let { if (it) 1 else 0 }
      if (_tmp == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindLong(_argIndex, _tmp.toLong())
      }
      _argIndex = 4
      val _tmp_1: Int? = isBlocked?.let { if (it) 1 else 0 }
      if (_tmp_1 == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindLong(_argIndex, _tmp_1.toLong())
      }
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfFlag: Int = 4
          val _columnIndexOfBlocked: Int = 5
          val _columnIndexOfAppOrDnsName: Int = 6
          val _columnIndexOfDownloadBytes: Int = 7
          val _columnIndexOfUploadBytes: Int = 8
          val _columnIndexOfTotalBytes: Int = 9
          val _result: MutableList<AppConnection> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppConnection
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpBlocked: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp_2 != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpTotalBytes: Long?
            if (_stmt.isNull(_columnIndexOfTotalBytes)) {
              _tmpTotalBytes = null
            } else {
              _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
            }
            _item = AppConnection(_tmpUid,_tmpIpAddress,_tmpPort,_tmpCount,_tmpFlag,_tmpBlocked,_tmpAppOrDnsName,_tmpDownloadBytes,_tmpUploadBytes,_tmpTotalBytes)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun updateSummary(
    connId: String,
    pid: String,
    rpid: String,
    downloadBytes: Long,
    uploadBytes: Long,
    duration: Int,
    synack: Long,
    message: String,
  ) {
    val _sql: String = "update RethinkLog set proxyDetails = ?, rpid = ?, downloadBytes = ?, uploadBytes = ?, duration = ?, synack = ?, message = ? where connId = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, pid)
        _argIndex = 2
        _stmt.bindText(_argIndex, rpid)
        _argIndex = 3
        _stmt.bindLong(_argIndex, downloadBytes)
        _argIndex = 4
        _stmt.bindLong(_argIndex, uploadBytes)
        _argIndex = 5
        _stmt.bindLong(_argIndex, duration.toLong())
        _argIndex = 6
        _stmt.bindLong(_argIndex, synack)
        _argIndex = 7
        _stmt.bindText(_argIndex, message)
        _argIndex = 8
        _stmt.bindText(_argIndex, connId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearAllData() {
    val _sql: String = "delete from RethinkLog"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearLogsByUid(uid: Int) {
    val _sql: String = "delete from RethinkLog where uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearLogsByRule(rule: String) {
    val _sql: String = "delete from RethinkLog where blockedByRule = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, rule)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun purgeLogsByDate(date: Long) {
    val _sql: String = "DELETE FROM RethinkLog WHERE  timeStamp < ?"
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
