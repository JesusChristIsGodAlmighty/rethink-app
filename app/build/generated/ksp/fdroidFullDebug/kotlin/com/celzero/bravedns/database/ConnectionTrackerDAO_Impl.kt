package com.celzero.bravedns.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performBlocking
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.celzero.bravedns.`data`.AppConnection
import com.celzero.bravedns.`data`.DataUsage
import com.celzero.bravedns.`data`.DataUsageSummary
import com.celzero.bravedns.`data`.RecentRoutedAppRow
import com.celzero.bravedns.`data`.RpnConnStatsSummary
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class ConnectionTrackerDAO_Impl(
  __db: RoomDatabase,
) : ConnectionTrackerDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfConnectionTracker: EntityInsertAdapter<ConnectionTracker>

  private val __deleteAdapterOfConnectionTracker: EntityDeleteOrUpdateAdapter<ConnectionTracker>

  private val __updateAdapterOfConnectionTracker: EntityDeleteOrUpdateAdapter<ConnectionTracker>
  init {
    this.__db = __db
    this.__insertAdapterOfConnectionTracker = object : EntityInsertAdapter<ConnectionTracker>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `ConnectionTracker` (`id`,`appName`,`uid`,`packageName`,`usrId`,`ipAddress`,`port`,`protocol`,`isBlocked`,`blockedByRule`,`blocklists`,`proxyDetails`,`flag`,`dnsQuery`,`timeStamp`,`connId`,`downloadBytes`,`uploadBytes`,`duration`,`synack`,`rpid`,`message`,`connType`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ConnectionTracker) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.appName)
        statement.bindLong(3, entity.uid.toLong())
        statement.bindText(4, entity.packageName)
        statement.bindLong(5, entity.usrId.toLong())
        statement.bindText(6, entity.ipAddress)
        statement.bindLong(7, entity.port.toLong())
        statement.bindLong(8, entity.protocol.toLong())
        val _tmp: Int = if (entity.isBlocked) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        statement.bindText(10, entity.blockedByRule)
        statement.bindText(11, entity.blocklists)
        statement.bindText(12, entity.proxyDetails)
        statement.bindText(13, entity.flag)
        val _tmpDnsQuery: String? = entity.dnsQuery
        if (_tmpDnsQuery == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpDnsQuery)
        }
        statement.bindLong(15, entity.timeStamp)
        statement.bindText(16, entity.connId)
        statement.bindLong(17, entity.downloadBytes)
        statement.bindLong(18, entity.uploadBytes)
        statement.bindLong(19, entity.duration.toLong())
        statement.bindLong(20, entity.synack)
        statement.bindText(21, entity.rpid)
        statement.bindText(22, entity.message)
        statement.bindText(23, entity.connType)
      }
    }
    this.__deleteAdapterOfConnectionTracker = object : EntityDeleteOrUpdateAdapter<ConnectionTracker>() {
      protected override fun createQuery(): String = "DELETE FROM `ConnectionTracker` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ConnectionTracker) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfConnectionTracker = object : EntityDeleteOrUpdateAdapter<ConnectionTracker>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `ConnectionTracker` SET `id` = ?,`appName` = ?,`uid` = ?,`packageName` = ?,`usrId` = ?,`ipAddress` = ?,`port` = ?,`protocol` = ?,`isBlocked` = ?,`blockedByRule` = ?,`blocklists` = ?,`proxyDetails` = ?,`flag` = ?,`dnsQuery` = ?,`timeStamp` = ?,`connId` = ?,`downloadBytes` = ?,`uploadBytes` = ?,`duration` = ?,`synack` = ?,`rpid` = ?,`message` = ?,`connType` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ConnectionTracker) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.appName)
        statement.bindLong(3, entity.uid.toLong())
        statement.bindText(4, entity.packageName)
        statement.bindLong(5, entity.usrId.toLong())
        statement.bindText(6, entity.ipAddress)
        statement.bindLong(7, entity.port.toLong())
        statement.bindLong(8, entity.protocol.toLong())
        val _tmp: Int = if (entity.isBlocked) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        statement.bindText(10, entity.blockedByRule)
        statement.bindText(11, entity.blocklists)
        statement.bindText(12, entity.proxyDetails)
        statement.bindText(13, entity.flag)
        val _tmpDnsQuery: String? = entity.dnsQuery
        if (_tmpDnsQuery == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpDnsQuery)
        }
        statement.bindLong(15, entity.timeStamp)
        statement.bindText(16, entity.connId)
        statement.bindLong(17, entity.downloadBytes)
        statement.bindLong(18, entity.uploadBytes)
        statement.bindLong(19, entity.duration.toLong())
        statement.bindLong(20, entity.synack)
        statement.bindText(21, entity.rpid)
        statement.bindText(22, entity.message)
        statement.bindText(23, entity.connType)
        statement.bindLong(24, entity.id.toLong())
      }
    }
  }

  public override fun insert(connectionTracker: ConnectionTracker): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfConnectionTracker.insert(_connection, connectionTracker)
  }

  public override fun insertBatch(connTrackerList: List<ConnectionTracker>): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfConnectionTracker.insert(_connection, connTrackerList)
  }

  public override fun delete(connectionTracker: ConnectionTracker): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfConnectionTracker.handle(_connection, connectionTracker)
  }

  public override fun update(connectionTracker: ConnectionTracker): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfConnectionTracker.handle(_connection, connectionTracker)
  }

  public override suspend fun getActivityBuckets(
    rangeStart: Long,
    rangeEnd: Long,
    bucketMs: Long,
  ): List<ActivityBucketRow> {
    val _sql: String = "select cast((timeStamp - ?)/? as integer) as bucketIndex, isBlocked as blocked, count(id) as total from ConnectionTracker where timeStamp >= ? and timeStamp < ? group by bucketIndex, blocked"
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

  public override suspend fun getWindowCounts(start: Long, end: Long): WindowCountRow {
    val _sql: String = "select coalesce(sum(case when isBlocked then 1 else 0 end), 0) as blocked, count(*) as total from ConnectionTracker where timeStamp >= ? and timeStamp < ?"
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

  public override suspend fun getConnectionsInWindow(
    start: Long,
    end: Long,
    limit: Int,
  ): List<ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where timeStamp >= ? and timeStamp < ? order by id desc limit ?"
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
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
        val _result: MutableList<ConnectionTracker> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConnectionTracker
          _item = ConnectionTracker()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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
    val _sql: String = "select uid as uid, appName as appName, count(id) as total, sum(case when isBlocked then 1 else 0 end) as blocked from ConnectionTracker where timeStamp >= ? and timeStamp < ? group by uid, appName order by total desc limit ?"
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

  public override suspend fun getTopAppsByUsage(
    start: Long,
    end: Long,
    limit: Int,
  ): List<AppUsageRow> {
    val _sql: String = "select uid as uid, appName as appName, sum(case when connType = 'Metered' then uploadBytes else 0 end) as meteredUploadBytes, sum(case when connType = 'Metered' then downloadBytes else 0 end) as meteredDownloadBytes, sum(case when connType = 'Unmetered' then uploadBytes else 0 end) as unmeteredUploadBytes, sum(case when connType = 'Unmetered' then downloadBytes else 0 end) as unmeteredDownloadBytes, max(timeStamp) as lastSeen from ConnectionTracker where timeStamp >= ? and timeStamp < ? group by uid, appName order by (sum(uploadBytes) + sum(downloadBytes)) desc limit ?"
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
        val _columnIndexOfMeteredUploadBytes: Int = 2
        val _columnIndexOfMeteredDownloadBytes: Int = 3
        val _columnIndexOfUnmeteredUploadBytes: Int = 4
        val _columnIndexOfUnmeteredDownloadBytes: Int = 5
        val _columnIndexOfLastSeen: Int = 6
        val _result: MutableList<AppUsageRow> = mutableListOf()
        while (_stmt.step()) {
          val _item: AppUsageRow
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpMeteredUploadBytes: Long
          _tmpMeteredUploadBytes = _stmt.getLong(_columnIndexOfMeteredUploadBytes)
          val _tmpMeteredDownloadBytes: Long
          _tmpMeteredDownloadBytes = _stmt.getLong(_columnIndexOfMeteredDownloadBytes)
          val _tmpUnmeteredUploadBytes: Long
          _tmpUnmeteredUploadBytes = _stmt.getLong(_columnIndexOfUnmeteredUploadBytes)
          val _tmpUnmeteredDownloadBytes: Long
          _tmpUnmeteredDownloadBytes = _stmt.getLong(_columnIndexOfUnmeteredDownloadBytes)
          val _tmpLastSeen: Long
          _tmpLastSeen = _stmt.getLong(_columnIndexOfLastSeen)
          _item = AppUsageRow(_tmpUid,_tmpAppName,_tmpMeteredUploadBytes,_tmpMeteredDownloadBytes,_tmpUnmeteredUploadBytes,_tmpUnmeteredDownloadBytes,_tmpLastSeen)
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
    val _sql: String = "select uid as uid, appName as appName, sum(case when isBlocked then 1 else 0 end) as blocked, max(timeStamp) as lastSeen from ConnectionTracker where timeStamp >= ? and timeStamp < ? group by uid, appName having blocked > 0 order by blocked desc limit ?"
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

  public override suspend fun getConnectionsInWindowForUid(
    start: Long,
    end: Long,
    uid: Int,
    limit: Int,
  ): List<ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where timeStamp >= ? and timeStamp < ? and uid = ? order by id desc limit ?"
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
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
        val _result: MutableList<ConnectionTracker> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConnectionTracker
          _item = ConnectionTracker()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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
    val _sql: String = "select coalesce(nullif(dnsQuery, ''), ipAddress) as label, count(id) as total, sum(case when isBlocked then 1 else 0 end) as blocked, max(timeStamp) as lastSeen, substr(max(printf('%016d', timeStamp) || flag), 17) as flag from ConnectionTracker where timeStamp >= ? and timeStamp < ? and uid = ? group by label order by total desc limit ?"
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

  public override fun getConnectionTrackerByName(): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getConnectionTrackerByName(uid: Int): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where uid = ? order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getConnectionTrackerByName(query: String): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getConnectionTrackerByName(query: String, uid: Int): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where uid = ? and (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      _stmt.bindText(_argIndex, query)
      _argIndex = 7
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getBlockedConnections(): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where isBlocked = 1 order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getBlockedConnections(uid: Int): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where uid = ? and isBlocked = 1 order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getBlockedConnections(query: String): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where  (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) and isBlocked = 1 order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getBlockedConnections(query: String, uid: Int): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where uid = ? and (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) and isBlocked = 1 order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      _stmt.bindText(_argIndex, query)
      _argIndex = 7
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getAppIpLogs(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, ipAddress, port, COUNT(ipAddress) as count, flag as flag, 0 as blocked, GROUP_CONCAT(DISTINCT dnsQuery) as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM ConnectionTracker WHERE uid = ? and timeStamp > ? GROUP BY uid, ipAddress, port ORDER BY count DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker") {
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

  public override fun getAppIpLogsLimited(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, ipAddress, port, COUNT(ipAddress) as count, flag as flag, 0 as blocked, '' as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM ConnectionTracker WHERE uid = ? and timeStamp > ? GROUP BY uid, ipAddress, port ORDER BY count DESC LIMIT 3"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker") {
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

  public override fun getBlockedAppIpLogsLimited(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, ipAddress, port, COUNT(ipAddress) as count, flag as flag, 1 as blocked, '' as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM ConnectionTracker WHERE uid = ? and timeStamp > ? and isBlocked = 1 GROUP BY uid, ipAddress, port ORDER BY count DESC LIMIT 3"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker") {
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

  public override fun getAppIpLogsFiltered(
    uid: Int,
    to: Long,
    query: String,
    isBlocked: Boolean?,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid, ipAddress, port, COUNT(ipAddress) as count, flag as flag, MAX(isBlocked) as blocked, GROUP_CONCAT(DISTINCT dnsQuery) as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes FROM ConnectionTracker WHERE uid = ? and timeStamp > ? and ipAddress like ? and (? IS NULL OR isBlocked = ?) GROUP BY  uid, ipAddress, port ORDER BY count DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      val _tmp: Int? = isBlocked?.let { if (it) 1 else 0 }
      if (_tmp == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindLong(_argIndex, _tmp.toLong())
      }
      _argIndex = 5
      val _tmp_1: Int? = isBlocked?.let { if (it) 1 else 0 }
      if (_tmp_1 == null) {
        _stmt.bindNull(_argIndex)
      } else {
        _stmt.bindLong(_argIndex, _tmp_1.toLong())
      }
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker") {
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

  public override fun getBlockedConnectionsFiltered(filter: Set<String>): PagingSource<Int, ConnectionTracker> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from ConnectionTracker where blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and isBlocked = 1 order by id desc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: ConnectionTracker
            _item_1 = ConnectionTracker()
            _item_1.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item_1.appName = _stmt.getText(_columnIndexOfAppName)
            _item_1.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item_1.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item_1.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item_1.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item_1.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item_1.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item_1.isBlocked = _tmp != 0
            _item_1.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item_1.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item_1.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item_1.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item_1.dnsQuery = null
            } else {
              _item_1.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item_1.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item_1.connId = _stmt.getText(_columnIndexOfConnId)
            _item_1.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_1.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_1.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item_1.synack = _stmt.getLong(_columnIndexOfSynack)
            _item_1.rpid = _stmt.getText(_columnIndexOfRpid)
            _item_1.message = _stmt.getText(_columnIndexOfMessage)
            _item_1.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getBlockedConnectionsFiltered(query: String, filter: Set<String>): PagingSource<Int, ConnectionTracker> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from ConnectionTracker where blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and isBlocked = 1 and (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or ipAddress like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or dnsQuery like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or flag like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or proxyDetails like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or connId like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") order by id desc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 2 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 3 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 4 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 5 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 6 + _inputSize
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: ConnectionTracker
            _item_1 = ConnectionTracker()
            _item_1.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item_1.appName = _stmt.getText(_columnIndexOfAppName)
            _item_1.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item_1.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item_1.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item_1.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item_1.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item_1.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item_1.isBlocked = _tmp != 0
            _item_1.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item_1.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item_1.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item_1.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item_1.dnsQuery = null
            } else {
              _item_1.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item_1.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item_1.connId = _stmt.getText(_columnIndexOfConnId)
            _item_1.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_1.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_1.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item_1.synack = _stmt.getLong(_columnIndexOfSynack)
            _item_1.rpid = _stmt.getText(_columnIndexOfRpid)
            _item_1.message = _stmt.getText(_columnIndexOfMessage)
            _item_1.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getProtocolFilteredConnections(protocol: String): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where protocol = ? order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, protocol)
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getProtocolFilteredConnections(protocol: String, filter: Set<String>): PagingSource<Int, ConnectionTracker> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from ConnectionTracker where protocol = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" and blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") order by id desc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, protocol)
      _argIndex = 2
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: ConnectionTracker
            _item_1 = ConnectionTracker()
            _item_1.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item_1.appName = _stmt.getText(_columnIndexOfAppName)
            _item_1.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item_1.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item_1.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item_1.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item_1.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item_1.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item_1.isBlocked = _tmp != 0
            _item_1.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item_1.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item_1.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item_1.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item_1.dnsQuery = null
            } else {
              _item_1.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item_1.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item_1.connId = _stmt.getText(_columnIndexOfConnId)
            _item_1.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_1.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_1.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item_1.synack = _stmt.getLong(_columnIndexOfSynack)
            _item_1.rpid = _stmt.getText(_columnIndexOfRpid)
            _item_1.message = _stmt.getText(_columnIndexOfMessage)
            _item_1.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedBlockedConnections(): PagingSource<Int, MergedConnectionLog> {
    val _sql: String = "select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where isBlocked = 1 order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where isBlocked = 1 order by id desc limit 2000) order by timeStamp desc, id desc, source asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedBlockedConnections(query: String): PagingSource<Int, MergedConnectionLog> {
    val _sql: String = "select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where isBlocked = 1 and (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where isBlocked = 1 and (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) order by id desc limit 2000) order by timeStamp desc, id desc, source asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      _stmt.bindText(_argIndex, query)
      _argIndex = 7
      _stmt.bindText(_argIndex, query)
      _argIndex = 8
      _stmt.bindText(_argIndex, query)
      _argIndex = 9
      _stmt.bindText(_argIndex, query)
      _argIndex = 10
      _stmt.bindText(_argIndex, query)
      _argIndex = 11
      _stmt.bindText(_argIndex, query)
      _argIndex = 12
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedAllowedConnections(): PagingSource<Int, MergedConnectionLog> {
    val _sql: String = "select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where isBlocked = 0 order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where isBlocked = 0 order by id desc limit 2000) order by timeStamp desc, id desc, source asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedAllowedConnections(query: String): PagingSource<Int, MergedConnectionLog> {
    val _sql: String = "select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where isBlocked = 0 and (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where isBlocked = 0 and (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) order by id desc limit 2000) order by timeStamp desc, id desc, source asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      _stmt.bindText(_argIndex, query)
      _argIndex = 7
      _stmt.bindText(_argIndex, query)
      _argIndex = 8
      _stmt.bindText(_argIndex, query)
      _argIndex = 9
      _stmt.bindText(_argIndex, query)
      _argIndex = 10
      _stmt.bindText(_argIndex, query)
      _argIndex = 11
      _stmt.bindText(_argIndex, query)
      _argIndex = 12
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedBlockedConnectionsFiltered(filter: Set<String>): PagingSource<Int, MergedConnectionLog> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and isBlocked = 1 order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where blockedByRule in (")
    val _inputSize_1: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") and isBlocked = 1 order by id desc limit 2000) order by timeStamp desc, id desc, source asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      for (_item_1: String in filter) {
        _stmt.bindText(_argIndex, _item_1)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item_2: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item_2 = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item_2)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedBlockedConnectionsFiltered(query: String, filter: Set<String>): PagingSource<Int, MergedConnectionLog> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and isBlocked = 1 and (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or ipAddress like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or dnsQuery like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or flag like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or proxyDetails like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or connId like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where blockedByRule in (")
    val _inputSize_1: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") and isBlocked = 1 and (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or ipAddress like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or dnsQuery like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or flag like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or proxyDetails like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or connId like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") order by id desc limit 2000) order by timeStamp desc, id desc, source asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 2 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 3 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 4 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 5 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 6 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 7 + _inputSize
      for (_item_1: String in filter) {
        _stmt.bindText(_argIndex, _item_1)
        _argIndex++
      }
      _argIndex = 7 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 8 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 9 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 10 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 11 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 12 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item_2: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item_2 = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item_2)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedAllowedConnectionsFiltered(filter: Set<String>): PagingSource<Int, MergedConnectionLog> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and isBlocked = 0 order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where blockedByRule in (")
    val _inputSize_1: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") and isBlocked = 0 order by id desc limit 2000) order by timeStamp desc, id desc, source asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      for (_item_1: String in filter) {
        _stmt.bindText(_argIndex, _item_1)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item_2: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item_2 = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item_2)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedAllowedConnectionsFiltered(query: String, filter: Set<String>): PagingSource<Int, MergedConnectionLog> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and isBlocked = 0 and (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or ipAddress like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or dnsQuery like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or flag like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or proxyDetails like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or connId like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where blockedByRule in (")
    val _inputSize_1: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") and isBlocked = 0 and (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or ipAddress like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or dnsQuery like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or flag like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or proxyDetails like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or connId like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") order by id desc limit 2000) order by timeStamp desc, id desc, source asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 2 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 3 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 4 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 5 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 6 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 7 + _inputSize
      for (_item_1: String in filter) {
        _stmt.bindText(_argIndex, _item_1)
        _argIndex++
      }
      _argIndex = 7 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 8 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 9 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 10 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 11 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 12 + _inputSize + _inputSize
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item_2: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item_2 = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item_2)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedProtocolFilteredConnections(protocol: String): PagingSource<Int, MergedConnectionLog> {
    val _sql: String = "select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where protocol = ? order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where protocol = ? order by id desc limit 2000) order by timeStamp desc, id desc, source asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, protocol)
      _argIndex = 2
      _stmt.bindText(_argIndex, protocol)
    }
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedProtocolFilteredConnections(protocol: String, filter: Set<String>): PagingSource<Int, MergedConnectionLog> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where protocol = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" and blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where protocol = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" and blockedByRule in (")
    val _inputSize_1: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") order by id desc limit 2000) order by timeStamp desc, id desc, source asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, protocol)
      _argIndex = 2
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
      _argIndex = 2 + _inputSize
      _stmt.bindText(_argIndex, protocol)
      _argIndex = 3 + _inputSize
      for (_item_1: String in filter) {
        _stmt.bindText(_argIndex, _item_1)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item_2: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item_2 = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item_2)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedConnectionTrackerByName(): PagingSource<Int, MergedConnectionLog> {
    val _sql: String = "select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog order by id desc limit 2000) order by timeStamp desc, id desc, source asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getMergedConnectionTrackerByName(query: String): PagingSource<Int, MergedConnectionLog> {
    val _sql: String = "select * from (select id, 'ct' as source, appName, uid, packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from ConnectionTracker where (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) order by id desc limit 2000) union all select * from (select id, 'rethink' as source, appName, uid, 'com.celzero.bravedns' as packageName, usrId, ipAddress, port, protocol, isBlocked, blockedByRule, blocklists, proxyDetails, flag, dnsQuery, timeStamp, connId, downloadBytes, uploadBytes, duration, synack, rpid, message, connType from RethinkLog where (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) order by id desc limit 2000) order by timeStamp desc, id desc, source asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      _stmt.bindText(_argIndex, query)
      _argIndex = 7
      _stmt.bindText(_argIndex, query)
      _argIndex = 8
      _stmt.bindText(_argIndex, query)
      _argIndex = 9
      _stmt.bindText(_argIndex, query)
      _argIndex = 10
      _stmt.bindText(_argIndex, query)
      _argIndex = 11
      _stmt.bindText(_argIndex, query)
      _argIndex = 12
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<MergedConnectionLog>(_rawQuery, __db, "ConnectionTracker", "RethinkLog") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<MergedConnectionLog> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<MergedConnectionLog> = mutableListOf()
          while (_stmt.step()) {
            val _item: MergedConnectionLog
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSource: String
            _tmpSource = _stmt.getText(_columnIndexOfSource)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String?
            if (_stmt.isNull(_columnIndexOfPackageName)) {
              _tmpPackageName = null
            } else {
              _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            }
            val _tmpUsrId: Int
            _tmpUsrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            val _tmpIpAddress: String
            _tmpIpAddress = _stmt.getText(_columnIndexOfIpAddress)
            val _tmpPort: Int
            _tmpPort = _stmt.getLong(_columnIndexOfPort).toInt()
            val _tmpProtocol: Int
            _tmpProtocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmpIsBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _tmpIsBlocked = _tmp != 0
            val _tmpBlockedByRule: String
            _tmpBlockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            val _tmpBlocklists: String
            _tmpBlocklists = _stmt.getText(_columnIndexOfBlocklists)
            val _tmpProxyDetails: String
            _tmpProxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
            val _tmpDnsQuery: String?
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _tmpDnsQuery = null
            } else {
              _tmpDnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            val _tmpTimeStamp: Long
            _tmpTimeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            val _tmpConnId: String
            _tmpConnId = _stmt.getText(_columnIndexOfConnId)
            val _tmpDownloadBytes: Long
            _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            val _tmpUploadBytes: Long
            _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            val _tmpDuration: Int
            _tmpDuration = _stmt.getLong(_columnIndexOfDuration).toInt()
            val _tmpSynack: Long
            _tmpSynack = _stmt.getLong(_columnIndexOfSynack)
            val _tmpRpid: String
            _tmpRpid = _stmt.getText(_columnIndexOfRpid)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpConnType: String
            _tmpConnType = _stmt.getText(_columnIndexOfConnType)
            _item = MergedConnectionLog(_tmpId,_tmpSource,_tmpAppName,_tmpUid,_tmpPackageName,_tmpUsrId,_tmpIpAddress,_tmpPort,_tmpProtocol,_tmpIsBlocked,_tmpBlockedByRule,_tmpBlocklists,_tmpProxyDetails,_tmpFlag,_tmpDnsQuery,_tmpTimeStamp,_tmpConnId,_tmpDownloadBytes,_tmpUploadBytes,_tmpDuration,_tmpSynack,_tmpRpid,_tmpMessage,_tmpConnType)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllowedConnections(query: String): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where isBlocked = 0 and  (appName like ? or ipAddress like ? or dnsQuery like ? or flag like ? or proxyDetails like ? or connId like ?) order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getAllowedConnections(): PagingSource<Int, ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where isBlocked = 0 order by id desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item: ConnectionTracker
            _item = ConnectionTracker()
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.appName = _stmt.getText(_columnIndexOfAppName)
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getAllowedConnectionsFiltered(filter: Set<String>): PagingSource<Int, ConnectionTracker> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from ConnectionTracker where isBlocked = 0 and blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") order by id desc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: ConnectionTracker
            _item_1 = ConnectionTracker()
            _item_1.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item_1.appName = _stmt.getText(_columnIndexOfAppName)
            _item_1.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item_1.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item_1.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item_1.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item_1.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item_1.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item_1.isBlocked = _tmp != 0
            _item_1.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item_1.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item_1.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item_1.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item_1.dnsQuery = null
            } else {
              _item_1.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item_1.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item_1.connId = _stmt.getText(_columnIndexOfConnId)
            _item_1.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_1.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_1.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item_1.synack = _stmt.getLong(_columnIndexOfSynack)
            _item_1.rpid = _stmt.getText(_columnIndexOfRpid)
            _item_1.message = _stmt.getText(_columnIndexOfMessage)
            _item_1.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllowedConnectionsFiltered(query: String, filter: Set<String>): PagingSource<Int, ConnectionTracker> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from ConnectionTracker where isBlocked = 0 and  (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or ipAddress like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or dnsQuery like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or flag like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or proxyDetails like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or connId like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and blockedByRule in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") order by id desc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
      _argIndex = 4
      _stmt.bindText(_argIndex, query)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      _stmt.bindText(_argIndex, query)
      _argIndex = 7
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<ConnectionTracker>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ConnectionTracker> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
          val _result: MutableList<ConnectionTracker> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: ConnectionTracker
            _item_1 = ConnectionTracker()
            _item_1.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item_1.appName = _stmt.getText(_columnIndexOfAppName)
            _item_1.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item_1.packageName = _stmt.getText(_columnIndexOfPackageName)
            _item_1.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
            _item_1.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item_1.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item_1.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
            _item_1.isBlocked = _tmp != 0
            _item_1.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
            _item_1.blocklists = _stmt.getText(_columnIndexOfBlocklists)
            _item_1.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
            _item_1.flag = _stmt.getText(_columnIndexOfFlag)
            if (_stmt.isNull(_columnIndexOfDnsQuery)) {
              _item_1.dnsQuery = null
            } else {
              _item_1.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
            }
            _item_1.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
            _item_1.connId = _stmt.getText(_columnIndexOfConnId)
            _item_1.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_1.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_1.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
            _item_1.synack = _stmt.getLong(_columnIndexOfSynack)
            _item_1.rpid = _stmt.getText(_columnIndexOfRpid)
            _item_1.message = _stmt.getText(_columnIndexOfMessage)
            _item_1.connType = _stmt.getText(_columnIndexOfConnType)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllContactedIps(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "select 0 as uid, ipAddress as ipAddress, port as port, count(id) as count, flag, 0 as blocked, '' as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes from ConnectionTracker where isBlocked = 0 and timeStamp > ? and ipAddress != '' group by ipAddress order by count desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker") {
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

  public override fun getMostContactedIps(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "select 0 as uid, ipAddress as ipAddress, port as port, count(id) as count, flag, 0 as blocked, '' as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes from ConnectionTracker where  isBlocked = 0 and timeStamp > ? and ipAddress != '' group by ipAddress order by count desc LIMIT 7"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker") {
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

  public override fun getMostBlockedIps(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "select 0 as uid, ipAddress as ipAddress, port as port, count(id) as count, flag, 1 as blocked, '' as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes from ConnectionTracker where isBlocked = 1 and timeStamp > ? and ipAddress != '' group by ipAddress order by count desc LIMIT 7"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker") {
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

  public override fun getAllBlockedIps(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "select 0 as uid, ipAddress as ipAddress, port as port, count(id) as count, flag, 1 as blocked, '' as appOrDnsName, SUM(downloadBytes) as downloadBytes, SUM(uploadBytes) as uploadBytes, SUM(downloadBytes + uploadBytes) as totalBytes from ConnectionTracker where isBlocked = 1 and timeStamp > ? and ipAddress != '' group by ipAddress order by count desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker") {
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

  public override fun logsCount(): LiveData<Long> {
    val _sql: String = "select count(id) from ConnectionTracker"
    return __db.invalidationTracker.createLiveData(arrayOf("ConnectionTracker"), false) { _connection ->
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
    val _sql: String = "select timeStamp from ConnectionTracker where id = (select min(id) from ConnectionTracker)"
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

  public override fun getConnIdByUidIpAddress(
    uid: Int,
    ipAddress: String,
    to: Long,
  ): List<String> {
    val _sql: String = "select connId from ConnectionTracker where uid = ? and ipAddress = ? and timeStamp >= ? and message = '' and uploadBytes = 0 and downloadBytes = 0 and synack = 0"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, ipAddress)
        _argIndex = 3
        _stmt.bindLong(_argIndex, to)
        val _result: MutableList<String> = mutableListOf()
        while (_stmt.step()) {
          val _item: String
          _item = _stmt.getText(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDataUsage(from: Long, to: Long): List<DataUsage> {
    val _sql: String = "SELECT uid, SUM(uploadBytes) AS uploadBytes, SUM(downloadBytes) AS downloadBytes FROM ConnectionTracker where timeStamp >= ? and timeStamp <= ? GROUP BY uid"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, from)
        _argIndex = 2
        _stmt.bindLong(_argIndex, to)
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfUploadBytes: Int = 1
        val _columnIndexOfDownloadBytes: Int = 2
        val _result: MutableList<DataUsage> = mutableListOf()
        while (_stmt.step()) {
          val _item: DataUsage
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpUploadBytes: Long
          _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          val _tmpDownloadBytes: Long
          _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _item = DataUsage(_tmpUid,_tmpUploadBytes,_tmpDownloadBytes)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTotalUsages(to: Long, meteredTxt: String): DataUsageSummary {
    val _sql: String = "select sum(downloadBytes) as totalDownload, sum(uploadBytes) as totalUpload, count(id) as connectionsCount, (select sum(downloadBytes + uploadBytes) from ConnectionTracker where connType = ? and timeStamp > ?) as meteredDataUsage from ConnectionTracker as ct where ct.timeStamp > ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, meteredTxt)
        _argIndex = 2
        _stmt.bindLong(_argIndex, to)
        _argIndex = 3
        _stmt.bindLong(_argIndex, to)
        val _columnIndexOfTotalDownload: Int = 0
        val _columnIndexOfTotalUpload: Int = 1
        val _columnIndexOfConnectionsCount: Int = 2
        val _columnIndexOfMeteredDataUsage: Int = 3
        val _result: DataUsageSummary
        if (_stmt.step()) {
          val _tmpTotalDownload: Long
          _tmpTotalDownload = _stmt.getLong(_columnIndexOfTotalDownload)
          val _tmpTotalUpload: Long
          _tmpTotalUpload = _stmt.getLong(_columnIndexOfTotalUpload)
          val _tmpConnectionsCount: Int
          _tmpConnectionsCount = _stmt.getLong(_columnIndexOfConnectionsCount).toInt()
          val _tmpMeteredDataUsage: Long
          _tmpMeteredDataUsage = _stmt.getLong(_columnIndexOfMeteredDataUsage)
          _result = DataUsageSummary(_tmpTotalDownload,_tmpTotalUpload,_tmpConnectionsCount,_tmpMeteredDataUsage)
        } else {
          error("The query result was empty, but expected a single row to return a NON-NULL object of type 'com.celzero.bravedns.`data`.DataUsageSummary'.")
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getBlockedUniversalRulesCount(): List<String> {
    val _sql: String = "select blockedByRule from ConnectionTracker where blockedByRule in ('Rule #1B', 'Rule #1F', 'Rule #3', 'Rule #4', 'Rule #5', 'Rule #6', 'Rule #7', 'Http block', 'Universal Lockdown')"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: MutableList<String> = mutableListOf()
        while (_stmt.step()) {
          val _item: String
          _item = _stmt.getText(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getWgAppNetworkActivity(wgId: String, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = "SELECT uid AS uid, '' AS ipAddress, 0 AS port, COUNT(id) AS count, flag AS flag, 0 AS blocked, appName AS appOrDnsName, SUM(downloadBytes) AS downloadBytes, SUM(uploadBytes) AS uploadBytes, SUM(uploadBytes + downloadBytes) AS totalBytes FROM ConnectionTracker WHERE proxyDetails like ? AND timeStamp > ? GROUP BY appName ORDER BY totalBytes DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, wgId)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker") {
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

  public override suspend fun getLastRoutedConnectionForProxy(proxyId: String): ConnectionTracker? {
    val _sql: String = "select * from ConnectionTracker where proxyDetails like '%' || ? || '%' and isBlocked = 0 and appName not like '%Unknown%' order by timeStamp desc limit 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, proxyId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
        val _result: ConnectionTracker?
        if (_stmt.step()) {
          _result = ConnectionTracker()
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.appName = _stmt.getText(_columnIndexOfAppName)
          _result.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _result.packageName = _stmt.getText(_columnIndexOfPackageName)
          _result.usrId = _stmt.getLong(_columnIndexOfUsrId).toInt()
          _result.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
          _result.port = _stmt.getLong(_columnIndexOfPort).toInt()
          _result.protocol = _stmt.getLong(_columnIndexOfProtocol).toInt()
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsBlocked).toInt()
          _result.isBlocked = _tmp != 0
          _result.blockedByRule = _stmt.getText(_columnIndexOfBlockedByRule)
          _result.blocklists = _stmt.getText(_columnIndexOfBlocklists)
          _result.proxyDetails = _stmt.getText(_columnIndexOfProxyDetails)
          _result.flag = _stmt.getText(_columnIndexOfFlag)
          if (_stmt.isNull(_columnIndexOfDnsQuery)) {
            _result.dnsQuery = null
          } else {
            _result.dnsQuery = _stmt.getText(_columnIndexOfDnsQuery)
          }
          _result.timeStamp = _stmt.getLong(_columnIndexOfTimeStamp)
          _result.connId = _stmt.getText(_columnIndexOfConnId)
          _result.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _result.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _result.duration = _stmt.getLong(_columnIndexOfDuration).toInt()
          _result.synack = _stmt.getLong(_columnIndexOfSynack)
          _result.rpid = _stmt.getText(_columnIndexOfRpid)
          _result.message = _stmt.getText(_columnIndexOfMessage)
          _result.connType = _stmt.getText(_columnIndexOfConnType)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRecentRoutedConnectionsForProxy(proxyId: String): List<ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where proxyDetails like '%' || ? || '%' and isBlocked = 0 and appName not like '%Unknown%' order by timeStamp desc limit 24"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, proxyId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
        val _result: MutableList<ConnectionTracker> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConnectionTracker
          _item = ConnectionTracker()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override suspend fun getRecentRoutedAppsWithCount(proxyId: String, limit: Int): List<RecentRoutedAppRow> {
    val _sql: String = "select uid as uid, appName as appName, packageName as packageName, count(*) as count from ConnectionTracker where proxyDetails like '%' || ? || '%' and isBlocked = 0 and appName not like '%Unknown%' group by uid order by max(timeStamp) desc limit ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, proxyId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfAppName: Int = 1
        val _columnIndexOfPackageName: Int = 2
        val _columnIndexOfCount: Int = 3
        val _result: MutableList<RecentRoutedAppRow> = mutableListOf()
        while (_stmt.step()) {
          val _item: RecentRoutedAppRow
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          _item = RecentRoutedAppRow(_tmpUid,_tmpAppName,_tmpPackageName,_tmpCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRecentConnectionsByProxyPrefix(prefix: String, limit: Int): List<ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where proxyDetails like ? || '%' and appName not like '%Unknown%' order by timeStamp desc limit ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, prefix)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
        val _result: MutableList<ConnectionTracker> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConnectionTracker
          _item = ConnectionTracker()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override suspend fun countConnectionsByProxyPrefix(prefix: String, since: Long): Int {
    val _sql: String = "select count(*) from ConnectionTracker where proxyDetails like ? || '%' and timeStamp > ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, prefix)
        _argIndex = 2
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

  public override suspend fun sumBytesByProxyPrefix(prefix: String, since: Long): Long {
    val _sql: String = "select ifnull(sum(downloadBytes + uploadBytes), 0) from ConnectionTracker where proxyDetails like ? || '%' and timeStamp > ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, prefix)
        _argIndex = 2
        _stmt.bindLong(_argIndex, since)
        val _result: Long
        if (_stmt.step()) {
          val _tmp: Long
          _tmp = _stmt.getLong(0)
          _result = _tmp
        } else {
          _result = 0L
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countDistinctAppsByProxyPrefix(prefix: String, since: Long): Int {
    val _sql: String = "select count(distinct packageName) from ConnectionTracker where proxyDetails like ? || '%' and timeStamp > ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, prefix)
        _argIndex = 2
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

  public override suspend fun countBlockedConnectionsSince(since: Long): Int {
    val _sql: String = "select count(*) from ConnectionTracker where isBlocked = 1 and timeStamp > ?"
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

  public override fun getTotalUsagesByWgId(
    to: Long,
    meteredTxt: String,
    wgId: String,
  ): DataUsageSummary {
    val _sql: String = "select sum(downloadBytes) as totalDownload, sum(uploadBytes) as totalUpload, count(id) as connectionsCount, ict.meteredDataUsage as meteredDataUsage from ConnectionTracker as ct join (select sum(downloadBytes + uploadBytes) as meteredDataUsage from ConnectionTracker where connType like ? and timeStamp > ?) as ict where timeStamp > ? and proxyDetails = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, meteredTxt)
        _argIndex = 2
        _stmt.bindLong(_argIndex, to)
        _argIndex = 3
        _stmt.bindLong(_argIndex, to)
        _argIndex = 4
        _stmt.bindText(_argIndex, wgId)
        val _columnIndexOfTotalDownload: Int = 0
        val _columnIndexOfTotalUpload: Int = 1
        val _columnIndexOfConnectionsCount: Int = 2
        val _columnIndexOfMeteredDataUsage: Int = 3
        val _result: DataUsageSummary
        if (_stmt.step()) {
          val _tmpTotalDownload: Long
          _tmpTotalDownload = _stmt.getLong(_columnIndexOfTotalDownload)
          val _tmpTotalUpload: Long
          _tmpTotalUpload = _stmt.getLong(_columnIndexOfTotalUpload)
          val _tmpConnectionsCount: Int
          _tmpConnectionsCount = _stmt.getLong(_columnIndexOfConnectionsCount).toInt()
          val _tmpMeteredDataUsage: Long
          _tmpMeteredDataUsage = _stmt.getLong(_columnIndexOfMeteredDataUsage)
          _result = DataUsageSummary(_tmpTotalDownload,_tmpTotalUpload,_tmpConnectionsCount,_tmpMeteredDataUsage)
        } else {
          error("The query result was empty, but expected a single row to return a NON-NULL object of type 'com.celzero.bravedns.`data`.DataUsageSummary'.")
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRpnConnStats(proxyId: String, to: Long): RpnConnStatsSummary {
    val _sql: String = "select count(id) as connectionsCount, coalesce(sum(downloadBytes), 0) as totalDownload, coalesce(sum(uploadBytes), 0) as totalUpload, coalesce(sum(isBlocked), 0) as blockedCount, count(distinct appName) as appCount from ConnectionTracker where proxyDetails like '%' || ? || '%' and timeStamp > ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, proxyId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, to)
        val _columnIndexOfConnectionsCount: Int = 0
        val _columnIndexOfTotalDownload: Int = 1
        val _columnIndexOfTotalUpload: Int = 2
        val _columnIndexOfBlockedCount: Int = 3
        val _columnIndexOfAppCount: Int = 4
        val _result: RpnConnStatsSummary
        if (_stmt.step()) {
          val _tmpConnectionsCount: Int
          _tmpConnectionsCount = _stmt.getLong(_columnIndexOfConnectionsCount).toInt()
          val _tmpTotalDownload: Long
          _tmpTotalDownload = _stmt.getLong(_columnIndexOfTotalDownload)
          val _tmpTotalUpload: Long
          _tmpTotalUpload = _stmt.getLong(_columnIndexOfTotalUpload)
          val _tmpBlockedCount: Int
          _tmpBlockedCount = _stmt.getLong(_columnIndexOfBlockedCount).toInt()
          val _tmpAppCount: Int
          _tmpAppCount = _stmt.getLong(_columnIndexOfAppCount).toInt()
          _result = RpnConnStatsSummary(_tmpConnectionsCount,_tmpTotalDownload,_tmpTotalUpload,_tmpBlockedCount,_tmpAppCount)
        } else {
          error("The query result was empty, but expected a single row to return a NON-NULL object of type 'com.celzero.bravedns.`data`.RpnConnStatsSummary'.")
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRpnTopAppsForProxy(
    proxyId: String,
    to: Long,
    limit: Int,
  ): List<AppConnection> {
    val _sql: String = "select uid as uid, '' as ipAddress, 0 as port, count(id) as count, '' as flag, 0 as blocked, appName as appOrDnsName, sum(downloadBytes) as downloadBytes, sum(uploadBytes) as uploadBytes, sum(downloadBytes + uploadBytes) as totalBytes from ConnectionTracker where proxyDetails like '%' || ? || '%' and isBlocked = 0 and timeStamp > ? group by uid, appName order by totalBytes desc limit ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, proxyId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, to)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
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

  public override suspend fun getRpnActivityBuckets(
    proxyIdFilter: String,
    rangeStart: Long,
    rangeEnd: Long,
    bucketMs: Long,
  ): List<ActivityBucketRow> {
    val _sql: String = "select cast((timeStamp - ?)/? as integer) as bucketIndex, isBlocked as blocked, count(id) as total from ConnectionTracker where timeStamp >= ? and timeStamp < ? and proxyDetails like ? group by bucketIndex, blocked"
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

  public override suspend fun getRpnWindowCounts(
    proxyIdFilter: String,
    start: Long,
    end: Long,
  ): WindowCountRow {
    val _sql: String = "select coalesce(sum(case when isBlocked then 1 else 0 end), 0) as blocked, count(*) as total from ConnectionTracker where timeStamp >= ? and timeStamp < ? and proxyDetails like ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        _argIndex = 3
        _stmt.bindText(_argIndex, proxyIdFilter)
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

  public override suspend fun getRpnAppActivity(
    proxyIdFilter: String,
    start: Long,
    end: Long,
    limit: Int,
  ): List<AppActivityRow> {
    val _sql: String = "select uid as uid, appName as appName, count(id) as total, sum(case when isBlocked then 1 else 0 end) as blocked from ConnectionTracker where timeStamp >= ? and timeStamp < ? and proxyDetails like ? group by uid, appName order by total desc limit ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, start)
        _argIndex = 2
        _stmt.bindLong(_argIndex, end)
        _argIndex = 3
        _stmt.bindText(_argIndex, proxyIdFilter)
        _argIndex = 4
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

  public override suspend fun getRpnConnectionsInWindowForUid(
    proxyIdFilter: String,
    start: Long,
    end: Long,
    uid: Int,
    limit: Int,
  ): List<ConnectionTracker> {
    val _sql: String = "select * from ConnectionTracker where timeStamp >= ? and timeStamp < ? and uid = ? and proxyDetails like ? order by id desc limit ?"
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
        _stmt.bindText(_argIndex, proxyIdFilter)
        _argIndex = 5
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
        val _result: MutableList<ConnectionTracker> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConnectionTracker
          _item = ConnectionTracker()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override suspend fun getRpnDomainActivityForUid(
    proxyIdFilter: String,
    start: Long,
    end: Long,
    uid: Int,
    limit: Int,
  ): List<DomainActivityRow> {
    val _sql: String = "select coalesce(nullif(dnsQuery, ''), ipAddress) as label, count(id) as total, sum(case when isBlocked then 1 else 0 end) as blocked, max(timeStamp) as lastSeen, substr(max(printf('%016d', timeStamp) || flag), 17) as flag from ConnectionTracker where timeStamp >= ? and timeStamp < ? and uid = ? and proxyDetails like ? group by label order by total desc limit ?"
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
        _stmt.bindText(_argIndex, proxyIdFilter)
        _argIndex = 5
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

  public override suspend fun getRecentlyBlockedApps(time: Long): List<BlockedAppResult> {
    val _sql: String = "SELECT uid, MAX(timeStamp) AS lastBlocked, COUNT(*) AS count FROM ConnectionTracker WHERE isBlocked = 1 and timeStamp > ? GROUP BY uid ORDER BY lastBlocked DESC LIMIT 10"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, time)
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfLastBlocked: Int = 1
        val _columnIndexOfCount: Int = 2
        val _result: MutableList<BlockedAppResult> = mutableListOf()
        while (_stmt.step()) {
          val _item: BlockedAppResult
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpLastBlocked: Long
          _tmpLastBlocked = _stmt.getLong(_columnIndexOfLastBlocked)
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          _item = BlockedAppResult(_tmpUid,_tmpLastBlocked,_tmpCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRecentlyBlockedAppsPaged(time: Long): PagingSource<Int, BlockedAppResult> {
    val _sql: String = "SELECT uid, MAX(timeStamp) AS lastBlocked, COUNT(*) AS count FROM ConnectionTracker WHERE isBlocked = 1 and timeStamp > ? GROUP BY uid ORDER BY lastBlocked DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, time)
    }
    return object : LimitOffsetPagingSource<BlockedAppResult>(_rawQuery, __db, "ConnectionTracker") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<BlockedAppResult> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfLastBlocked: Int = 1
          val _columnIndexOfCount: Int = 2
          val _result: MutableList<BlockedAppResult> = mutableListOf()
          while (_stmt.step()) {
            val _item: BlockedAppResult
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpLastBlocked: Long
            _tmpLastBlocked = _stmt.getLong(_columnIndexOfLastBlocked)
            val _tmpCount: Int
            _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
            _item = BlockedAppResult(_tmpUid,_tmpLastBlocked,_tmpCount)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override suspend fun getBlockedConnectionsSince(since: Long): List<ConnectionTracker> {
    val _sql: String = "SELECT * FROM ConnectionTracker WHERE isBlocked = 1 AND timeStamp >= ? ORDER BY timeStamp DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, since)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
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
        val _result: MutableList<ConnectionTracker> = mutableListOf()
        while (_stmt.step()) {
          val _item: ConnectionTracker
          _item = ConnectionTracker()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.appName = _stmt.getText(_columnIndexOfAppName)
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.packageName = _stmt.getText(_columnIndexOfPackageName)
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

  public override fun getBlockedConnectionsCountLiveData(since: Long): LiveData<Int> {
    val _sql: String = "SELECT COUNT(*) FROM ConnectionTracker WHERE isBlocked = 1 AND timeStamp >= ?"
    return __db.invalidationTracker.createLiveData(arrayOf("ConnectionTracker"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, since)
        val _result: Int?
        if (_stmt.step()) {
          val _tmp: Int?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0).toInt()
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
    val _sql: String = "update ConnectionTracker set proxyDetails = ?, rpid = ?, downloadBytes = ?, uploadBytes = ?, duration = ?, synack = ?, message = ? where connId = ?"
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

  public override fun updateSummary(
    connId: String,
    pid: String,
    rpid: String,
    downloadBytes: Long,
    uploadBytes: Long,
    duration: Int,
    synack: Long,
    message: String,
    ipAddress: String,
    flag: String,
  ) {
    val _sql: String = "update ConnectionTracker set proxyDetails = ?, rpid = ?, downloadBytes = ?, uploadBytes = ?, duration = ?, synack = ?, message = ?, ipAddress = ?, flag = ? where connId = ?"
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
        _stmt.bindText(_argIndex, ipAddress)
        _argIndex = 9
        _stmt.bindText(_argIndex, flag)
        _argIndex = 10
        _stmt.bindText(_argIndex, connId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearAllData() {
    val _sql: String = "delete from ConnectionTracker"
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
    val _sql: String = "delete from ConnectionTracker where uid = ?"
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
    val _sql: String = "delete from ConnectionTracker where blockedByRule = ?"
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

  public override fun clearLogsByTime(uid: Int, time: Long) {
    val _sql: String = "delete from ConnectionTracker where uid = ? and timeStamp > ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, time)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun purgeLogsByDate(date: Long) {
    val _sql: String = "DELETE FROM ConnectionTracker WHERE  timeStamp < ?"
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

  public override fun closeConnections(connIds: List<String>, reason: String) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("update ConnectionTracker set message = ")
    _stringBuilder.append("?")
    _stringBuilder.append(", duration = 0 where connId in (")
    val _inputSize: Int = connIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and message = '' and uploadBytes = 0 and downloadBytes = 0 and synack = 0")
    val _sql: String = _stringBuilder.toString()
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, reason)
        _argIndex = 2
        for (_item: String in connIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun closeConnectionForUids(uids: List<Int>, reason: String) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("update ConnectionTracker set message = ")
    _stringBuilder.append("?")
    _stringBuilder.append(", duration = 0 where uid in (")
    val _inputSize: Int = uids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and message = '' and uploadBytes = 0 and downloadBytes = 0 and synack = 0")
    val _sql: String = _stringBuilder.toString()
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, reason)
        _argIndex = 2
        for (_item: Int in uids) {
          _stmt.bindLong(_argIndex, _item.toLong())
          _argIndex++
        }
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
