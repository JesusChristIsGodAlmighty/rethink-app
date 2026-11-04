package com.celzero.bravedns.database

import androidx.paging.PagingSource
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.celzero.bravedns.`data`.AppConnection
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class StatsSummaryDao_Impl(
  __db: RoomDatabase,
) : StatsSummaryDao {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override fun getMostConnectedASN(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT -1 as uid, 
        |            '' as ipAddress,
        |            0 as port,
        |            COUNT(*) as count,
        |            0 as blocked, 
        |            ipInfo.countryCode AS flag, 
        |            ipInfo.asName AS appOrDnsName,
        |            Sum(conn.downloadBytes) as downloadBytes,
        |            Sum(conn.uploadBytes) as uploadBytes,
        |            Sum(downloadBytes + uploadBytes) as totalBytes
        |        FROM ConnectionTracker AS conn
        |        INNER JOIN IpInfo AS ipInfo ON conn.ipAddress = ipInfo.ip
        |        WHERE conn.isBlocked = 0
        |            AND ipInfo.asName != ''
        |            AND conn.timeStamp > ?
        |        GROUP BY ipInfo.asName
        |        ORDER BY count DESC
        |        LIMIT 7
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getAllConnectedASN(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT -1 as uid, 
        |            '' as ipAddress,
        |            0 as port,
        |            COUNT(*) as count,
        |            0 as blocked, 
        |            ipInfo.countryCode AS flag, 
        |            ipInfo.asName AS appOrDnsName,
        |            Sum(conn.downloadBytes) as downloadBytes,
        |            Sum(conn.uploadBytes) as uploadBytes,
        |            Sum(downloadBytes + uploadBytes) as totalBytes
        |        FROM ConnectionTracker AS conn
        |        INNER JOIN IpInfo AS ipInfo ON conn.ipAddress = ipInfo.ip
        |        WHERE conn.isBlocked = 0
        |            AND ipInfo.asName != ''
        |            AND conn.timeStamp > ?
        |        GROUP BY ipInfo.asName
        |        ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getAsnDetails(asn: String, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT conn.uid as uid, 
        |            ipInfo.asName as ipAddress,
        |            0 as port,
        |            COUNT(*) as count,
        |            0 as blocked, 
        |            ipInfo.countryCode AS flag,
        |            conn.appName AS appOrDnsName,
        |            Sum(conn.downloadBytes) as downloadBytes,
        |            Sum(conn.uploadBytes) as uploadBytes,
        |            Sum(downloadBytes + uploadBytes) as totalBytes
        |        FROM ConnectionTracker AS conn
        |        INNER JOIN IpInfo AS ipInfo ON conn.ipAddress = ipInfo.ip
        |        WHERE conn.isBlocked = 0
        |            AND ipInfo.asName != ''
        |            AND conn.timeStamp > ?
        |            AND ipInfo.asName = ?
        |        GROUP BY conn.uid
        |        ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindText(_argIndex, asn)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getMostBlockedASN(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT -1 as uid, 
        |            '' as ipAddress,
        |            0 as port,
        |            COUNT(*) as count,
        |            1 as blocked, 
        |            ipInfo.countryCode AS flag,
        |            ipInfo.asName AS appOrDnsName,
        |            0 as downloadBytes,
        |            0 as uploadBytes,
        |            0 as totalBytes
        |        FROM ConnectionTracker AS conn
        |        INNER JOIN IpInfo AS ipInfo ON conn.ipAddress = ipInfo.ip
        |        WHERE conn.isBlocked = 1
        |            AND ipInfo.asName != ''
        |            AND conn.timeStamp > ?
        |        GROUP BY ipInfo.asName
        |        ORDER BY count DESC
        |        LIMIT 7
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getAllBlockedASN(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT -1 as uid, 
        |            '' as ipAddress,
        |            0 as port,
        |            COUNT(*) as count,
        |            1 as blocked, 
        |            ipInfo.countryCode AS flag,
        |            ipInfo.asName AS appOrDnsName,
        |            0 as downloadBytes,
        |            0 as uploadBytes,
        |            0 as totalBytes
        |        FROM ConnectionTracker AS conn
        |        INNER JOIN IpInfo AS ipInfo ON conn.ipAddress = ipInfo.ip
        |        WHERE conn.isBlocked = 1
        |            AND ipInfo.asName != ''
        |            AND conn.timeStamp > ?
        |        GROUP BY ipInfo.asName
        |        ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getAsnBlockedDetails(asn: String, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT conn.uid as uid, 
        |            ipInfo.asName as ipAddress,
        |            0 as port,
        |            COUNT(*) as count,
        |            1 as blocked, 
        |            ipInfo.countryCode AS flag,
        |            conn.appName AS appOrDnsName,
        |            0 as downloadBytes,
        |            0 as uploadBytes,
        |            0 as totalBytes
        |        FROM ConnectionTracker AS conn
        |        INNER JOIN IpInfo AS ipInfo ON conn.ipAddress = ipInfo.ip
        |        WHERE conn.isBlocked = 1
        |            AND ipInfo.asName != ''
        |            AND conn.timeStamp > ?
        |            AND ipInfo.asName = ?
        |        GROUP BY conn.uid
        |        ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindText(_argIndex, asn)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getAsnLogsLimited(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 
        |                conn.uid AS uid, 
        |                GROUP_CONCAT(DISTINCT conn.ipAddress) AS ipAddress,
        |                0 AS port,
        |                COUNT(*) AS count,
        |                0 AS blocked, 
        |                ipInfo.countryCode AS flag,
        |                ipInfo.asName AS appOrDnsName,
        |                0 AS downloadBytes,
        |                0 AS uploadBytes,
        |                0 AS totalBytes
        |            FROM 
        |                ConnectionTracker AS conn
        |            INNER JOIN 
        |                IpInfo AS ipInfo 
        |            ON 
        |                conn.ipAddress = ipInfo.ip
        |            WHERE 
        |                ipInfo.asName != ''
        |                AND conn.timeStamp > ?
        |                AND conn.uid = ?
        |            GROUP BY 
        |                ipInfo.asName
        |            ORDER BY 
        |                count DESC
        |            LIMIT 3
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getAllAsnLogs(
    uid: Int,
    to: Long,
    input: String,
    isBlocked: Boolean?,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 
        |                conn.uid AS uid, 
        |                GROUP_CONCAT(DISTINCT conn.ipAddress) AS ipAddress,
        |                0 AS port,
        |                COUNT(*) AS count,
        |                0 AS blocked, 
        |                ipInfo.countryCode AS flag,
        |                ipInfo.asName AS appOrDnsName,
        |                0 AS downloadBytes,
        |                0 AS uploadBytes,
        |                0 AS totalBytes
        |            FROM 
        |                ConnectionTracker AS conn
        |            INNER JOIN 
        |                IpInfo AS ipInfo 
        |            ON 
        |                conn.ipAddress = ipInfo.ip
        |            WHERE
        |                ipInfo.asName != ''
        |                AND conn.timeStamp > ?
        |                AND conn.uid = ?
        |                AND ipInfo.asName LIKE ?
        |                AND (? IS NULL OR conn.isBlocked = ?)
        |            GROUP BY 
        |                ipInfo.asName
        |            ORDER BY 
        |                count DESC
        |           
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 3
      _stmt.bindText(_argIndex, input)
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
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp_2 != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getTopActiveConns(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |               SELECT uid as uid, 
        |                   '' as ipAddress,
        |                   0 as port,
        |                   COUNT(*) as count,
        |                   0 as blocked, 
        |                   '' as flag,
        |                   appName as appOrDnsName,
        |                   0 as downloadBytes,
        |                   0 as uploadBytes,
        |                   0 as totalBytes
        |               FROM ConnectionTracker
        |               WHERE downloadBytes = 0
        |                   AND uploadBytes = 0
        |                   AND isBlocked = 0
        |                   AND duration = 0
        |                   AND synack = 0
        |                   AND message = ''
        |                   AND timeStamp > ?
        |               GROUP BY appName
        |               ORDER BY count DESC
        |               LIMIT 7
        |               
        """.trimMargin()
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
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getTopActiveConns(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |                   SELECT ct.uid as uid, 
        |                               ct.ipAddress as ipAddress,
        |                               0 as port,
        |                               COUNT(*) as count,
        |                               0 as blocked, 
        |                               ct.flag as flag,
        |                               asn.asName as appOrDnsName,
        |                               0 as downloadBytes,
        |                               0 as uploadBytes,
        |                               0 as totalBytes
        |                           FROM ConnectionTracker as ct
        |                           LEFT JOIN IpInfo as asn ON ct.ipAddress = asn.ip
        |                           WHERE downloadBytes = 0
        |                               AND uploadBytes = 0
        |                               AND isBlocked = 0
        |                               AND duration = 0
        |                               AND synack = 0
        |                               AND message = ''
        |                               AND uid = ?
        |                               AND timeStamp >= ?
        |                           GROUP BY ipAddress
        |                           ORDER BY count DESC
        |                   LIMIT 3
        |               
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getAllActiveConns(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT uid as uid, 
        |                '' as ipAddress,
        |                0 as port,
        |                COUNT(*) as count,
        |                0 as blocked, 
        |                '' as flag,
        |                appName as appOrDnsName,
        |                0 as downloadBytes,
        |                0 as uploadBytes,
        |                0 as totalBytes
        |            FROM ConnectionTracker
        |            WHERE downloadBytes = 0
        |                AND uploadBytes = 0
        |                AND isBlocked = 0
        |                AND duration = 0
        |                AND synack = 0
        |                AND message = ''
        |                AND timeStamp >= ?
        |            GROUP BY appName
        |            ORDER BY count DESC
        |            
        """.trimMargin()
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
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getAllActiveConns(
    uid: Int,
    to: Long,
    query: String,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT ct.uid as uid, 
        |            ct.ipAddress as ipAddress,
        |            0 as port,
        |            COUNT(*) as count,
        |            0 as blocked, 
        |            ct.flag as flag,
        |            asn.asName as appOrDnsName,
        |            0 as downloadBytes,
        |            0 as uploadBytes,
        |            0 as totalBytes
        |        FROM ConnectionTracker as ct
        |        LEFT JOIN IpInfo as asn ON ct.ipAddress = asn.ip
        |        WHERE downloadBytes = 0
        |            AND uploadBytes = 0
        |            AND isBlocked = 0
        |            AND duration = 0
        |            AND synack = 0
        |            AND message = ''
        |            AND uid = ?
        |            AND timeStamp >= ?
        |            AND ct.ipAddress LIKE ?
        |        GROUP BY ipAddress
        |        ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
      _argIndex = 3
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getRethinkTopActiveConns(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |                       SELECT rl.uid as uid, 
        |                                   rl.ipAddress as ipAddress,
        |                                   0 as port,
        |                                   COUNT(*) as count,
        |                                   0 as blocked, 
        |                                   rl.flag as flag,
        |                                   asn.asName as appOrDnsName,
        |                                   0 as downloadBytes,
        |                                   0 as uploadBytes,
        |                                   0 as totalBytes
        |                               FROM RethinkLog as rl
        |                               LEFT JOIN IpInfo as asn ON rl.ipAddress = asn.ip
        |                               WHERE downloadBytes = 0
        |                                   AND uploadBytes = 0
        |                                   AND isBlocked = 0
        |                                   AND duration = 0
        |                                   AND synack = 0
        |                                   AND message = ''
        |                                   AND timeStamp >= ?
        |                               GROUP BY ipAddress
        |                               ORDER BY count DESC
        |                       LIMIT 3
        |                   
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getRethinkAllActiveConns(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |                       SELECT rl.uid as uid, 
        |                                   rl.ipAddress as ipAddress,
        |                                   0 as port,
        |                                   COUNT(*) as count,
        |                                   0 as blocked, 
        |                                   rl.flag as flag,
        |                                   asn.asName as appOrDnsName,
        |                                   0 as downloadBytes,
        |                                   0 as uploadBytes,
        |                                   0 as totalBytes
        |                               FROM RethinkLog as rl
        |                               LEFT JOIN IpInfo as asn ON rl.ipAddress = asn.ip
        |                               WHERE downloadBytes = 0
        |                                   AND uploadBytes = 0
        |                                   AND isBlocked = 0
        |                                   AND duration = 0
        |                                   AND synack = 0
        |                                   AND message = ''
        |                                   AND timeStamp >= ?
        |                               GROUP BY ipAddress
        |                               ORDER BY count DESC
        |                   
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "RethinkLog", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getMostAllowedApps(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT uid AS uid, 
        |              '' AS ipAddress, 
        |              0 AS port, 
        |              SUM(count) AS count, 
        |              '' AS flag, 
        |              0 AS blocked, 
        |              appOrDnsName, 
        |              Sum(downloadBytes) AS downloadBytes, 
        |              Sum(uploadBytes) AS uploadBytes, 
        |              Sum(uploadBytes + downloadBytes) AS totalBytes 
        |            FROM 
        |              (
        |                -- From DnsLogs
        |                SELECT uid AS uid, 
        |                  appName AS appOrDnsName, 
        |                  Count(uid) AS count, 
        |                  0 AS downloadBytes, 
        |                  0 AS uploadBytes
        |                FROM DnsLogs 
        |                WHERE isBlocked = 0
        |                  AND status = 'COMPLETE' 
        |                  AND queryStr != '' 
        |                  AND time > ? 
        |                GROUP BY uid
        |                
        |                UNION ALL 
        |                
        |                -- From ConnectionTracker
        |                SELECT uid AS uid, 
        |                  appName AS appOrDnsName, 
        |                  COUNT(uid) AS count, 
        |                  sum(downloadBytes) as downloadBytes, 
        |                  sum(uploadBytes) as uploadBytes 
        |                FROM ConnectionTracker 
        |                WHERE isBlocked = 0
        |                  AND timeStamp > ?
        |                  AND dnsQuery != '' 
        |                GROUP BY uid
        |              ) AS combined 
        |            GROUP BY uid 
        |            ORDER BY totalBytes DESC 
        |            LIMIT 7
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getAllAllowedApps(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT uid AS uid, 
        |              '' AS ipAddress, 
        |              0 AS port, 
        |              SUM(count) AS count, 
        |              '' as flag, 
        |              0 AS blocked, 
        |              appOrDnsName, 
        |              sum(downloadBytes) as downloadBytes, 
        |              sum(uploadBytes) as uploadBytes, 
        |              sum(uploadBytes + downloadBytes) as totalBytes 
        |            FROM 
        |              (
        |                -- From DnsLogs
        |                SELECT uid as uid, 
        |                  appName AS appOrDnsName, 
        |                  COUNT(uid) AS count, 
        |                  0 as downloadBytes, 
        |                  0 as uploadBytes 
        |                FROM DnsLogs 
        |                WHERE isBlocked = 0
        |                  AND status = 'COMPLETE' 
        |                  AND queryStr != '' 
        |                  AND time > ? 
        |                GROUP BY uid 
        |                  
        |                UNION ALL 
        |                  
        |                  -- From ConnectionTracker
        |                SELECT uid as uid, 
        |                  appName AS appOrDnsName, 
        |                  COUNT(uid) AS count, 
        |                  sum(downloadBytes) as downloadBytes, 
        |                  sum(uploadBytes) as uploadBytes 
        |                FROM ConnectionTracker 
        |                WHERE isBlocked = 0
        |                  AND timeStamp > ? 
        |                  AND dnsQuery != '' 
        |                GROUP BY uid
        |              ) AS combined 
        |            GROUP BY uid 
        |            ORDER BY totalBytes DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getMostBlockedApps(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT uid AS uid, 
        |              '' AS ipAddress, 
        |              0 AS port, 
        |              SUM(count) AS count, 
        |              '' as flag, 
        |              1 AS blocked, 
        |              appOrDnsName,
        |              0 as downloadBytes,
        |              0 as uploadBytes,
        |              0 as totalBytes
        |            FROM 
        |              (
        |                -- From DnsLogs
        |                SELECT uid as uid, 
        |                  appName AS appOrDnsName, 
        |                  COUNT(id) AS count 
        |                FROM DnsLogs 
        |                WHERE isBlocked = 1 
        |                  AND time > ? 
        |                GROUP BY uid
        |                   
        |                UNION ALL 
        |                  
        |                -- From ConnectionTracker
        |                SELECT uid as uid, 
        |                  appName AS appOrDnsName, 
        |                  COUNT(id) AS count 
        |                FROM ConnectionTracker 
        |                WHERE isBlocked = 1 
        |                  AND timeStamp > ? 
        |                GROUP BY uid
        |              ) AS combined 
        |            GROUP BY uid 
        |            ORDER BY count DESC 
        |            LIMIT 7 
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getAllBlockedApps(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 
        |              uid AS uid, 
        |              '' AS ipAddress, 
        |              0 AS port, 
        |              SUM(count) AS count, 
        |              '' as flag, 
        |              1 AS blocked, 
        |              appOrDnsName,
        |              0 as downloadBytes,
        |              0 as uploadBytes,
        |              0 as totalBytes
        |            FROM 
        |              (
        |                -- From DnsLogs
        |                SELECT uid as uid, 
        |                  appName AS appOrDnsName, 
        |                  COUNT(id) AS count 
        |                FROM DnsLogs 
        |                WHERE isBlocked = 1 
        |                  AND time > ? 
        |                GROUP BY uid
        |                   
        |                UNION ALL 
        |                  
        |                  -- From ConnectionTracker
        |                SELECT uid as uid, 
        |                  appName AS appOrDnsName, 
        |                  COUNT(id) AS count 
        |                FROM ConnectionTracker 
        |                WHERE isBlocked = 1 
        |                  AND timeStamp > ? 
        |                GROUP BY uid
        |              ) AS combined 
        |            GROUP BY uid 
        |            ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getMostBlockedDomains(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |          SELECT 0 AS uid, 
        |            '' AS ipAddress, 
        |            0 AS port, 
        |            SUM(count) AS count, 
        |            flag, 
        |            1 AS blocked, 
        |            appOrDnsName,
        |            0 as downloadBytes,
        |            0 as uploadBytes,
        |            0 as totalBytes
        |          FROM 
        |            (
        |              -- From DnsLogs
        |              SELECT RTRIM(queryStr, '.') AS appOrDnsName, 
        |                COUNT(id) AS count, 
        |                flag 
        |              FROM DnsLogs 
        |              WHERE isBlocked = 1 
        |                AND time > ? 
        |                AND queryStr != '' 
        |              GROUP BY RTRIM(queryStr, '.')
        |                 
        |              UNION ALL 
        |                
        |                -- From ConnectionTracker
        |              SELECT dnsQuery AS appOrDnsName, 
        |                COUNT(id) AS count, 
        |                flag 
        |              FROM ConnectionTracker 
        |              WHERE isBlocked = 1 
        |                AND timeStamp > ? 
        |                AND dnsQuery != ''
        |                AND blockedByRule LIKE 'Rule #2G%' 
        |              GROUP BY dnsQuery
        |            ) AS combined 
        |          GROUP BY appOrDnsName 
        |          ORDER BY count DESC 
        |          LIMIT 7
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getAllBlockedDomains(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 0 AS uid, 
        |              '' AS ipAddress, 
        |              0 AS port, 
        |              SUM(count) AS count, 
        |              flag, 
        |              1 AS blocked, 
        |              appOrDnsName,
        |              0 as downloadBytes,
        |              0 as uploadBytes,
        |              0 as totalBytes
        |            FROM 
        |              (
        |                -- From DnsLogs
        |                SELECT RTRIM(queryStr, '.') AS appOrDnsName, 
        |                  COUNT(id) AS count, 
        |                  flag
        |                FROM DnsLogs
        |                WHERE isBlocked = 1 
        |                  AND time > ?
        |                  AND queryStr != '' 
        |                GROUP BY RTRIM(queryStr, '.')
        |                 
        |                UNION ALL
        |                
        |                -- From ConnectionTracker
        |                SELECT dnsQuery AS appOrDnsName, 
        |                  COUNT(id) AS count, 
        |                  flag
        |                FROM ConnectionTracker 
        |                WHERE isBlocked = 1
        |                  AND timeStamp > ? 
        |                  AND dnsQuery != ''
        |                  AND blockedByRule LIKE 'Rule #2G%' 
        |                GROUP BY
        |                  dnsQuery
        |              ) AS combined 
        |            GROUP BY appOrDnsName 
        |            ORDER BY count DESC 
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getAllBlockedDomainsByUid(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT ? AS uid,
        |              '' AS ipAddress,
        |              0 AS port,
        |              SUM(count) AS count,
        |              flag,
        |              1 AS blocked,
        |              appOrDnsName,
        |              0 as downloadBytes,
        |              0 as uploadBytes,
        |              0 as totalBytes
        |            FROM
        |              (
        |                -- From DnsLogs
        |                SELECT RTRIM(queryStr, '.') AS appOrDnsName,
        |                  COUNT(id) AS count,
        |                  flag
        |                FROM DnsLogs
        |                WHERE isBlocked = 1
        |                  AND time > ?
        |                  AND queryStr != ''
        |                  AND uid = ?
        |                GROUP BY RTRIM(queryStr, '.')
        |
        |                UNION ALL
        |
        |                -- From ConnectionTracker
        |                SELECT dnsQuery AS appOrDnsName,
        |                  COUNT(id) AS count,
        |                  flag
        |                FROM ConnectionTracker
        |                WHERE isBlocked = 1
        |                  AND timeStamp > ?
        |                  AND dnsQuery != ''
        |                  AND blockedByRule LIKE 'Rule #2G%'
        |                  AND uid = ?
        |                GROUP BY dnsQuery
        |              ) AS combined
        |            GROUP BY appOrDnsName
        |            ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
      _argIndex = 3
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 4
      _stmt.bindLong(_argIndex, to)
      _argIndex = 5
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getMostContactedDomains(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 0 AS uid, 
        |                '' AS ipAddress, 
        |                0 AS port,  
        |                SUM(count) AS count, 
        |                flag, 
        |                0 AS blocked, 
        |                appOrDnsName,
        |                0 as downloadBytes,
        |                0 as uploadBytes,
        |                0 as totalBytes
        |            FROM (
        |                -- From DnsLogs
        |                SELECT RTRIM(queryStr, '.') AS appOrDnsName, 
        |                    COUNT(id) AS count, 
        |                    flag
        |                FROM DnsLogs
        |                WHERE isBlocked = 0
        |                      AND status = 'COMPLETE'
        |                      AND queryStr != '' 
        |                      AND time > ? 
        |                GROUP BY RTRIM(queryStr, '.')
        |                
        |                UNION ALL 
        |            
        |                -- From ConnectionTracker
        |                SELECT dnsQuery AS appOrDnsName, 
        |                    COUNT(id) AS count, 
        |                    flag
        |                FROM ConnectionTracker 
        |                WHERE isBlocked = 0
        |                      AND timeStamp > ? 
        |                      AND dnsQuery != '' 
        |                GROUP BY dnsQuery 
        |            ) AS combined 
        |            GROUP BY appOrDnsName
        |            ORDER BY count DESC 
        |            LIMIT 7
        |            
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getAllContactedDomains(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 0 AS uid, 
        |                '' AS ipAddress, 
        |                0 AS port,  
        |                SUM(count) AS count, 
        |                flag, 
        |                0 AS blocked, 
        |                appOrDnsName,
        |                0 as downloadBytes,
        |                0 as uploadBytes,
        |                0 as totalBytes
        |            FROM (
        |                -- From DnsLogs
        |                SELECT RTRIM(queryStr, '.') AS appOrDnsName, 
        |                    COUNT(id) AS count, 
        |                    flag
        |                FROM DnsLogs
        |                WHERE isBlocked = 0
        |                      AND status = 'COMPLETE'
        |                      AND queryStr != '' 
        |                      AND time > ?  
        |                GROUP BY RTRIM(queryStr, '.')
        |                
        |                UNION ALL
        |                
        |                -- From ConnectionTracker
        |                SELECT dnsQuery AS appOrDnsName, 
        |                    COUNT(id) AS count, 
        |                    flag
        |                FROM ConnectionTracker 
        |                WHERE isBlocked = 0
        |                    AND timeStamp > ? 
        |                    AND dnsQuery != '' 
        |                GROUP BY dnsQuery
        |            ) AS combined
        |            GROUP BY appOrDnsName 
        |            ORDER BY count DESC
        |            
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getMostContactedCountries(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 0 AS uid, 
        |              '' AS ipAddress, 
        |              0 AS port, 
        |              SUM(count) AS count, 
        |              flag, 
        |              0 AS blocked, 
        |              '' as appOrDnsName,
        |              0 as downloadBytes,
        |              0 as uploadBytes,
        |              0 as totalBytes
        |            FROM 
        |              (
        |                -- From DnsLogs
        |                SELECT COUNT(id) AS count, 
        |              flag 
        |            FROM DnsLogs
        |            WHERE isBlocked = 0
        |              AND status = 'COMPLETE'
        |              AND queryStr != '' 
        |              AND time > ?
        |              AND flag != ''
        |            GROUP BY flag
        |
        |            UNION ALL
        |
        |            -- From ConnectionTracker
        |            SELECT COUNT(id) AS count,
        |              flag
        |            FROM ConnectionTracker
        |            WHERE isBlocked = 0
        |              AND timeStamp > ? 
        |              AND flag != ''
        |            GROUP BY flag
        |          ) AS combined
        |        WHERE LENGTH(flag) = 2
        |            AND unicode(flag) BETWEEN 127462 AND 127487
        |            AND flag NOT LIKE '-%'
        |            AND flag NOT LIKE '🇿🇿'
        |        GROUP BY flag
        |        ORDER BY count DESC
        |        LIMIT 7
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getMostContactedCountriesByUid(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 0 AS uid,
        |              '' AS ipAddress,
        |              0 AS port,
        |              SUM(count) AS count,
        |              flag,
        |              0 AS blocked,
        |              '' as appOrDnsName,
        |              0 as downloadBytes,
        |              0 as uploadBytes,
        |              0 as totalBytes
        |            FROM
        |              (
        |                -- From DnsLogs
        |                SELECT COUNT(id) AS count,
        |                  flag
        |                FROM DnsLogs
        |                WHERE isBlocked = 0
        |                  AND status = 'COMPLETE'
        |                  AND queryStr != ''
        |                  AND time > ?
        |                  AND flag != ''
        |                  AND uid = ?
        |                GROUP BY flag
        |
        |                UNION ALL
        |
        |                -- From ConnectionTracker
        |                SELECT COUNT(id) AS count,
        |                  flag
        |                FROM ConnectionTracker
        |                WHERE isBlocked = 0
        |                  AND timeStamp > ?
        |                  AND flag != ''
        |                  AND uid = ?
        |                GROUP BY flag
        |              ) AS combined
        |            WHERE LENGTH(flag) = 2
        |                AND unicode(flag) BETWEEN 127462 AND 127487
        |                AND flag NOT LIKE '-%'
        |                AND flag NOT LIKE '🇿🇿'
        |            GROUP BY flag
        |            ORDER BY count DESC
        |            LIMIT 3
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 3
      _stmt.bindLong(_argIndex, to)
      _argIndex = 4
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getAllContactedCountriesByUid(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 0 AS uid,
        |              '' AS ipAddress,
        |              0 AS port,
        |              SUM(count) AS count,
        |              flag,
        |              0 AS blocked,
        |              '' as appOrDnsName,
        |              0 as downloadBytes,
        |              0 as uploadBytes,
        |              0 as totalBytes
        |            FROM
        |              (
        |                -- From DnsLogs
        |                SELECT COUNT(id) AS count,
        |                  flag
        |                FROM DnsLogs
        |                WHERE isBlocked = 0
        |                  AND status = 'COMPLETE'
        |                  AND queryStr != ''
        |                  AND time > ?
        |                  AND flag != ''
        |                  AND flag NOT LIKE '-%'
        |                  AND uid = ?
        |                GROUP BY flag
        |
        |                UNION ALL
        |
        |                -- From ConnectionTracker
        |                SELECT COUNT(id) AS count,
        |                  flag
        |                FROM ConnectionTracker
        |                WHERE isBlocked = 0
        |                  AND timeStamp > ?
        |                  AND flag != ''
        |                  AND uid = ?
        |                GROUP BY flag
        |              ) AS combined
        |            WHERE LENGTH(flag) = 2
        |                AND unicode(flag) BETWEEN 127462 AND 127487
        |                AND flag NOT LIKE '-%'
        |                AND flag NOT LIKE '🇿🇿'
        |            GROUP BY flag
        |            ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 3
      _stmt.bindLong(_argIndex, to)
      _argIndex = 4
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getAllContactedCountries(to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT 0 AS uid, 
        |              '' AS ipAddress, 
        |              0 AS port, 
        |              SUM(count) AS count, 
        |              flag, 
        |              0 AS blocked, 
        |              '' as appOrDnsName,
        |              0 as downloadBytes,
        |              0 as uploadBytes,
        |              0 as totalBytes
        |            FROM 
        |              (
        |                -- From DnsLogs
        |                SELECT COUNT(id) AS count, 
        |                  flag
        |                FROM DnsLogs
        |                WHERE isBlocked = 0
        |                  AND status = 'COMPLETE'
        |                  AND queryStr != ''
        |                  AND time > ?
        |                  AND flag != ''
        |                GROUP BY flag
        |                UNION ALL
        |                -- From ConnectionTracker
        |                SELECT COUNT(id) AS count, 
        |                  flag 
        |                FROM ConnectionTracker 
        |                WHERE isBlocked = 0 
        |                  AND timeStamp > ? 
        |                  AND flag != ''
        |                GROUP BY flag
        |              ) AS combined
        |            -- real flag emoji only: exactly 2 regional-indicator code points
        |            -- with the first in U+1F1E6..U+1F1FF. drops empty flags, dash
        |            -- placeholders ('--', '---'), DnsLogs status glyphs ('❔','⚠',
        |            -- '✅','▶') and legacy tofu. ZZ is DB-IP's unknown marker.
        |            WHERE LENGTH(flag) = 2
        |                AND unicode(flag) BETWEEN 127462 AND 127487
        |                AND flag NOT LIKE '-%'
        |                AND flag NOT LIKE '🇿🇿'
        |            GROUP BY flag 
        |            ORDER BY count DESC 
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "DnsLogs", "ConnectionTracker") {
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

  public override fun getDomainDetails(
    query: String,
    to: Long,
    isBlocked: Boolean,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT uid AS uid, 
        |              '' AS ipAddress, 
        |              0 AS port, 
        |              SUM(count) AS count, 
        |              '' AS flag, 
        |              0 AS blocked, 
        |              appOrDnsName, 
        |              SUM(uploadBytes) AS uploadBytes, 
        |              SUM(downloadBytes) AS downloadBytes, 
        |              0 AS totalBytes 
        |            FROM 
        |              (
        |                -- From ConnectionTracker
        |                SELECT uid, 
        |                  appName AS appOrDnsName, 
        |                  COUNT(id) AS count, 
        |                  SUM(uploadBytes) AS uploadBytes, 
        |                  SUM(downloadBytes) AS downloadBytes 
        |                FROM ConnectionTracker 
        |                WHERE timeStamp > ? 
        |                  AND dnsQuery = ? 
        |                  AND isBlocked = ?
        |                GROUP BY uid
        |                   
        |                UNION ALL
        |
        |                -- From DnsLogs
        |                SELECT uid,
        |                  appName AS appOrDnsName, 
        |                  COUNT(id) AS count,
        |                  0 AS uploadBytes,
        |                  0 AS downloadBytes
        |                FROM DnsLogs
        |                WHERE time > ?
        |                  AND queryStr like ?
        |                  AND isBlocked = ? 
        |                  AND status = 'COMPLETE'
        |                  AND queryStr != ''
        |                GROUP BY uid
        |              ) AS combined
        |            GROUP BY uid
        |            ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
      _argIndex = 3
      val _tmp: Int = if (isBlocked) 1 else 0
      _stmt.bindLong(_argIndex, _tmp.toLong())
      _argIndex = 4
      _stmt.bindLong(_argIndex, to)
      _argIndex = 5
      _stmt.bindText(_argIndex, query)
      _argIndex = 6
      val _tmp_1: Int = if (isBlocked) 1 else 0
      _stmt.bindLong(_argIndex, _tmp_1.toLong())
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "DnsLogs") {
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
          val _columnIndexOfUploadBytes: Int = 7
          val _columnIndexOfDownloadBytes: Int = 8
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
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
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

  public override fun getFlagDetails(flag: String, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT uid AS uid, 
        |              '' AS ipAddress, 
        |              0 AS port, 
        |              SUM(count) AS count, 
        |              flag AS flag, 
        |              0 AS blocked, 
        |              appOrDnsName AS appOrDnsName, 
        |              Sum(uploadbytes) AS uploadBytes, 
        |              Sum(downloadbytes) AS downloadBytes, 
        |              Sum(uploadBytes + downloadBytes) AS totalBytes 
        |            FROM 
        |              (
        |                -- From ConnectionTracker
        |                SELECT uid, 
        |                  appName AS appOrDnsName, 
        |                  COUNT(id) AS count, 
        |                  flag as flag, 
        |                  SUM(uploadBytes) AS uploadBytes, 
        |                  SUM(downloadBytes) AS downloadBytes 
        |                FROM ConnectionTracker 
        |                WHERE timeStamp > ? 
        |                  AND flag = ? 
        |                  AND isBlocked = 0 
        |                GROUP BY uid 
        |                
        |                UNION ALL 
        |                
        |                -- From DnsLogs
        |                SELECT uid,
        |                  appName AS appOrDnsName, 
        |                  COUNT(id) AS count, 
        |                  flag as flag,
        |                  0 AS uploadBytes,
        |                  0 AS downloadBytes
        |                FROM DnsLogs
        |                WHERE time > ?
        |                  AND flag = ?
        |                  AND isBlocked = 0
        |                  AND status = 'COMPLETE'
        |                  AND queryStr != '' 
        |                GROUP BY uid
        |              ) AS combined 
        |            GROUP BY uid 
        |            ORDER BY count DESC 
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindText(_argIndex, flag)
      _argIndex = 3
      _stmt.bindLong(_argIndex, to)
      _argIndex = 4
      _stmt.bindText(_argIndex, flag)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "DnsLogs") {
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
          val _columnIndexOfUploadBytes: Int = 7
          val _columnIndexOfDownloadBytes: Int = 8
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
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
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

  public override fun getMostDomainsByUid(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT ? AS uid, 
        |            '' AS ipAddress, 
        |            0 AS port, 
        |            SUM(count) AS count, 
        |            flag AS flag, 
        |            0 AS blocked, 
        |            appOrDnsName, 
        |            0 AS uploadBytes, 
        |            0 AS downloadBytes, 
        |            0 AS totalBytes 
        |        FROM 
        |            (
        |                -- From ConnectionTracker
        |                SELECT dnsQuery AS appOrDnsName, 
        |                    COUNT(dnsQuery) AS count,
        |                    flag as flag
        |                FROM ConnectionTracker 
        |                WHERE dnsQuery != ''  
        |                    AND timeStamp > ?
        |                    AND uid = ?
        |                GROUP BY dnsQuery
        |
        |                UNION ALL
        |        
        |                -- From DnsLogs
        |                SELECT queryStr AS appOrDnsName, 
        |                    COUNT(queryStr) AS count,
        |                    flag as flag
        |                FROM DnsLogs
        |                WHERE uid = ?
        |                    AND time > ?
        |                    AND status = 'COMPLETE'
        |                    AND queryStr != '' 
        |                GROUP BY queryStr
        |            ) AS combined 
        |        GROUP BY appOrDnsName 
        |        ORDER BY count DESC
        |        LIMIT 3
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
      _argIndex = 3
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 4
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 5
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "DnsLogs") {
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
          val _columnIndexOfUploadBytes: Int = 7
          val _columnIndexOfDownloadBytes: Int = 8
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
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
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

  public override fun getBlockedDomainsByUid(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT ? AS uid,
        |            '' AS ipAddress,
        |            0 AS port,
        |            SUM(count) AS count,
        |            flag AS flag,
        |            1 AS blocked,
        |            appOrDnsName,
        |            0 AS uploadBytes,
        |            0 AS downloadBytes,
        |            0 AS totalBytes
        |        FROM
        |            (
        |                -- From ConnectionTracker (DNS-level blocks recorded by the firewall)
        |                SELECT dnsQuery AS appOrDnsName,
        |                    COUNT(dnsQuery) AS count,
        |                    flag as flag
        |                FROM ConnectionTracker
        |                WHERE dnsQuery != ''
        |                    AND timeStamp > ?
        |                    AND uid = ?
        |                    AND isBlocked = 1
        |                    AND blockedByRule LIKE 'Rule #2G%'
        |                GROUP BY dnsQuery
        |
        |                UNION ALL
        |
        |                -- From DnsLogs
        |                SELECT queryStr AS appOrDnsName,
        |                    COUNT(queryStr) AS count,
        |                    flag as flag
        |                FROM DnsLogs
        |                WHERE uid = ?
        |                    AND time > ?
        |                    AND isBlocked = 1
        |                    AND queryStr != ''
        |                GROUP BY queryStr
        |            ) AS combined
        |        GROUP BY appOrDnsName
        |        ORDER BY count DESC
        |        LIMIT 3
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
      _argIndex = 3
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 4
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 5
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "DnsLogs") {
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
          val _columnIndexOfUploadBytes: Int = 7
          val _columnIndexOfDownloadBytes: Int = 8
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
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
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

  public override fun getBlockedAsnByUid(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT
        |                conn.uid AS uid,
        |                GROUP_CONCAT(DISTINCT conn.ipAddress) AS ipAddress,
        |                0 AS port,
        |                COUNT(*) AS count,
        |                1 AS blocked,
        |                ipInfo.countryCode AS flag,
        |                ipInfo.asName AS appOrDnsName,
        |                0 AS downloadBytes,
        |                0 AS uploadBytes,
        |                0 AS totalBytes
        |            FROM
        |                ConnectionTracker AS conn
        |            INNER JOIN
        |                IpInfo AS ipInfo
        |            ON
        |                conn.ipAddress = ipInfo.ip
        |            WHERE
        |                ipInfo.asName != ''
        |                AND conn.timeStamp > ?
        |                AND conn.uid = ?
        |                AND conn.isBlocked = 1
        |            GROUP BY
        |                ipInfo.asName
        |            ORDER BY
        |                count DESC
        |            LIMIT 3
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "IpInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppConnection> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfCount: Int = 3
          val _columnIndexOfBlocked: Int = 4
          val _columnIndexOfFlag: Int = 5
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
            val _tmpBlocked: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp != 0
            val _tmpFlag: String
            _tmpFlag = _stmt.getText(_columnIndexOfFlag)
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

  public override fun getAllDomainsByUid(
    uid: Int,
    to: Long,
    input: String,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT ? AS uid, 
        |            '' AS ipAddress, 
        |            0 AS port, 
        |            SUM(count) AS count, 
        |            flag AS flag, 
        |            0 AS blocked, 
        |            appOrDnsName, 
        |            0 AS uploadBytes, 
        |            0 AS downloadBytes, 
        |            0 AS totalBytes 
        |        FROM 
        |            (
        |                -- From ConnectionTracker
        |                SELECT dnsQuery AS appOrDnsName, 
        |                    COUNT(dnsQuery) AS count,
        |                    flag as flag
        |                FROM ConnectionTracker 
        |                WHERE dnsQuery != ''
        |                    AND timeStamp > ?
        |                    AND uid = ?
        |                    AND dnsQuery LIKE ?
        |                GROUP BY dnsQuery
        |                UNION ALL
        |                -- From DnsLogs
        |                SELECT queryStr AS appOrDnsName, 
        |                    COUNT(queryStr) AS count, 
        |                    flag as flag
        |                FROM DnsLogs
        |                WHERE uid = ?
        |                    AND time > ?
        |                    AND status = 'COMPLETE'
        |                    AND queryStr != '' 
        |                    AND queryStr LIKE ?
        |                GROUP BY queryStr
        |            ) AS combined 
        |        GROUP BY appOrDnsName 
        |        ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
      _argIndex = 3
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 4
      _stmt.bindText(_argIndex, input)
      _argIndex = 5
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 6
      _stmt.bindLong(_argIndex, to)
      _argIndex = 7
      _stmt.bindText(_argIndex, input)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "DnsLogs") {
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
          val _columnIndexOfUploadBytes: Int = 7
          val _columnIndexOfDownloadBytes: Int = 8
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
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
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

  public override fun getAllDomainsByUid(uid: Int, to: Long): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT ? AS uid, 
        |            '' AS ipAddress, 
        |            0 AS port, 
        |            SUM(count) AS count, 
        |            flag AS flag, 
        |            0 AS blocked, 
        |            appOrDnsName, 
        |            0 AS uploadBytes, 
        |            0 AS downloadBytes, 
        |            0 AS totalBytes 
        |        FROM 
        |            (
        |                -- From ConnectionTracker
        |                SELECT dnsQuery AS appOrDnsName, 
        |                    COUNT(dnsQuery) AS count,
        |                    flag as flag
        |                FROM ConnectionTracker 
        |                WHERE dnsQuery != ''  
        |                    AND timeStamp > ?
        |                    AND uid = ?
        |                GROUP BY dnsQuery
        |                UNION ALL
        |                -- From DnsLogs
        |                SELECT queryStr AS appOrDnsName, 
        |                    COUNT(queryStr) AS count, 
        |                    flag as flag
        |                FROM DnsLogs
        |                WHERE uid = ?
        |                    AND time > ?
        |                    AND status = 'COMPLETE'
        |                    AND queryStr != ''
        |                GROUP BY queryStr
        |            ) AS combined 
        |        GROUP BY appOrDnsName 
        |        ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
      _argIndex = 3
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 4
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 5
      _stmt.bindLong(_argIndex, to)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "DnsLogs") {
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
          val _columnIndexOfUploadBytes: Int = 7
          val _columnIndexOfDownloadBytes: Int = 8
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
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
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

  public override fun getAllowedDomainsByUid(
    uid: Int,
    to: Long,
    input: String,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT ? AS uid,
        |            '' AS ipAddress,
        |            0 AS port,
        |            SUM(count) AS count,
        |            flag AS flag,
        |            0 AS blocked,
        |            appOrDnsName,
        |            0 AS uploadBytes,
        |            0 AS downloadBytes,
        |            0 AS totalBytes
        |        FROM
        |            (
        |                -- From ConnectionTracker
        |                SELECT dnsQuery AS appOrDnsName,
        |                    COUNT(dnsQuery) AS count,
        |                    flag as flag
        |                FROM ConnectionTracker
        |                WHERE dnsQuery != ''
        |                    AND timeStamp > ?
        |                    AND uid = ?
        |                    AND isBlocked = 0
        |                    AND dnsQuery LIKE ?
        |                GROUP BY dnsQuery
        |                UNION ALL
        |                -- From DnsLogs
        |                SELECT queryStr AS appOrDnsName,
        |                    COUNT(queryStr) AS count,
        |                    flag as flag
        |                FROM DnsLogs
        |                WHERE uid = ?
        |                    AND time > ?
        |                    AND status = 'COMPLETE'
        |                    AND isBlocked = 0
        |                    AND queryStr != ''
        |                    AND queryStr LIKE ?
        |                GROUP BY queryStr
        |            ) AS combined
        |        GROUP BY appOrDnsName
        |        ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
      _argIndex = 3
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 4
      _stmt.bindText(_argIndex, input)
      _argIndex = 5
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 6
      _stmt.bindLong(_argIndex, to)
      _argIndex = 7
      _stmt.bindText(_argIndex, input)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "DnsLogs") {
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
          val _columnIndexOfUploadBytes: Int = 7
          val _columnIndexOfDownloadBytes: Int = 8
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
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
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

  public override fun getBlockedDomainsByUidFiltered(
    uid: Int,
    to: Long,
    input: String,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |        SELECT ? AS uid,
        |            '' AS ipAddress,
        |            0 AS port,
        |            SUM(count) AS count,
        |            flag AS flag,
        |            1 AS blocked,
        |            appOrDnsName,
        |            0 AS uploadBytes,
        |            0 AS downloadBytes,
        |            0 AS totalBytes
        |        FROM
        |            (
        |                -- From ConnectionTracker
        |                SELECT dnsQuery AS appOrDnsName,
        |                    COUNT(dnsQuery) AS count,
        |                    flag as flag
        |                FROM ConnectionTracker
        |                WHERE dnsQuery != ''
        |                    AND timeStamp > ?
        |                    AND uid = ?
        |                    AND isBlocked = 1
        |                    AND blockedByRule LIKE 'Rule #2G%'
        |                    AND dnsQuery LIKE ?
        |                GROUP BY dnsQuery
        |
        |                UNION ALL
        |
        |                -- From DnsLogs
        |                SELECT queryStr AS appOrDnsName,
        |                    COUNT(queryStr) AS count,
        |                    flag as flag
        |                FROM DnsLogs
        |                WHERE uid = ?
        |                    AND time > ?
        |                    AND isBlocked = 1
        |                    AND queryStr != ''
        |                    AND queryStr LIKE ?
        |                GROUP BY queryStr
        |            ) AS combined
        |        GROUP BY appOrDnsName
        |        ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, to)
      _argIndex = 3
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 4
      _stmt.bindText(_argIndex, input)
      _argIndex = 5
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 6
      _stmt.bindLong(_argIndex, to)
      _argIndex = 7
      _stmt.bindText(_argIndex, input)
    }
    return object : LimitOffsetPagingSource<AppConnection>(_rawQuery, __db, "ConnectionTracker", "DnsLogs") {
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
          val _columnIndexOfUploadBytes: Int = 7
          val _columnIndexOfDownloadBytes: Int = 8
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
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
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

  public override fun getIpDetails(
    ip: String,
    to: Long,
    isBlocked: Boolean,
  ): PagingSource<Int, AppConnection> {
    val _sql: String = """
        |
        |            SELECT uid AS uid, 
        |              ipAddress AS ipAddress, 
        |              0 AS port, 
        |              COUNT(id) AS count, 
        |              flag AS flag, 
        |              isBlocked AS blocked, 
        |              appName AS appOrDnsName, 
        |              SUM(uploadBytes) AS uploadBytes, 
        |              SUM(downloadBytes) AS downloadBytes, 
        |              SUM(downloadBytes + uploadBytes) AS totalBytes 
        |            FROM ConnectionTracker 
        |            WHERE timeStamp > ? 
        |              AND ipAddress = ? 
        |              AND isBlocked = ?
        |            GROUP BY uid 
        |            ORDER BY count DESC
        |        
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, to)
      _argIndex = 2
      _stmt.bindText(_argIndex, ip)
      _argIndex = 3
      val _tmp: Int = if (isBlocked) 1 else 0
      _stmt.bindLong(_argIndex, _tmp.toLong())
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
          val _columnIndexOfUploadBytes: Int = 7
          val _columnIndexOfDownloadBytes: Int = 8
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
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfBlocked).toInt()
            _tmpBlocked = _tmp_1 != 0
            val _tmpAppOrDnsName: String?
            if (_stmt.isNull(_columnIndexOfAppOrDnsName)) {
              _tmpAppOrDnsName = null
            } else {
              _tmpAppOrDnsName = _stmt.getText(_columnIndexOfAppOrDnsName)
            }
            val _tmpUploadBytes: Long?
            if (_stmt.isNull(_columnIndexOfUploadBytes)) {
              _tmpUploadBytes = null
            } else {
              _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            }
            val _tmpDownloadBytes: Long?
            if (_stmt.isNull(_columnIndexOfDownloadBytes)) {
              _tmpDownloadBytes = null
            } else {
              _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
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

  public override suspend fun getBlockedBlocklistCombos(to: Long): List<BlocklistComboCount> {
    val _sql: String = """
        |
        |        SELECT blockLists AS combo, COUNT(id) AS count
        |        FROM DnsLogs
        |        WHERE isBlocked = 1
        |            AND blockLists != ''
        |            AND time > ?
        |        GROUP BY blockLists
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, to)
        val _columnIndexOfCombo: Int = 0
        val _columnIndexOfCount: Int = 1
        val _result: MutableList<BlocklistComboCount> = mutableListOf()
        while (_stmt.step()) {
          val _item: BlocklistComboCount
          val _tmpCombo: String
          _tmpCombo = _stmt.getText(_columnIndexOfCombo)
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          _item = BlocklistComboCount(_tmpCombo,_tmpCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBlockedBlocklistCombosByUid(uid: Int, to: Long): List<BlocklistComboCount> {
    val _sql: String = """
        |
        |        SELECT blockLists AS combo, COUNT(id) AS count
        |        FROM DnsLogs
        |        WHERE isBlocked = 1
        |            AND blockLists != ''
        |            AND time > ?
        |            AND uid = ?
        |        GROUP BY blockLists
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, to)
        _argIndex = 2
        _stmt.bindLong(_argIndex, uid.toLong())
        val _columnIndexOfCombo: Int = 0
        val _columnIndexOfCount: Int = 1
        val _result: MutableList<BlocklistComboCount> = mutableListOf()
        while (_stmt.step()) {
          val _item: BlocklistComboCount
          val _tmpCombo: String
          _tmpCombo = _stmt.getText(_columnIndexOfCombo)
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          _item = BlocklistComboCount(_tmpCombo,_tmpCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBlocklistTagCombos(
    name: String,
    uid: Int?,
    to: Long,
  ): List<String> {
    val _sql: String = """
        |
        |        SELECT DISTINCT blockLists
        |        FROM DnsLogs
        |        WHERE isBlocked = 1
        |            AND blockLists != ''
        |            AND time > ?
        |            AND (? IS NULL OR uid = ?)
        |            AND (',' || blockLists || ',') LIKE '%,' || ? || ':%' ESCAPE '\'
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, to)
        _argIndex = 2
        if (uid == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, uid.toLong())
        }
        _argIndex = 3
        if (uid == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, uid.toLong())
        }
        _argIndex = 4
        _stmt.bindText(_argIndex, name)
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

  public override suspend fun getBlocklistAttributions(
    name: String,
    uid: Int?,
    to: Long,
  ): List<BlocklistAttribution> {
    val _sql: String = """
        |
        |        SELECT uid, appName, queryStr, blockLists
        |        FROM DnsLogs
        |        WHERE isBlocked = 1
        |            AND blockLists != ''
        |            AND time > ?
        |            AND (? IS NULL OR uid = ?)
        |            AND (',' || blockLists || ',') LIKE '%,' || ? || ':%' ESCAPE '\'
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, to)
        _argIndex = 2
        if (uid == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, uid.toLong())
        }
        _argIndex = 3
        if (uid == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, uid.toLong())
        }
        _argIndex = 4
        _stmt.bindText(_argIndex, name)
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfAppName: Int = 1
        val _columnIndexOfQueryStr: Int = 2
        val _columnIndexOfBlockLists: Int = 3
        val _result: MutableList<BlocklistAttribution> = mutableListOf()
        while (_stmt.step()) {
          val _item: BlocklistAttribution
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpQueryStr: String
          _tmpQueryStr = _stmt.getText(_columnIndexOfQueryStr)
          val _tmpBlockLists: String
          _tmpBlockLists = _stmt.getText(_columnIndexOfBlockLists)
          _item = BlocklistAttribution(_tmpUid,_tmpAppName,_tmpQueryStr,_tmpBlockLists)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
