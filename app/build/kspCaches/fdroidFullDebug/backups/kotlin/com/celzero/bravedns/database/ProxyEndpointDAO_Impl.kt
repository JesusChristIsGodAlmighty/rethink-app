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
public class ProxyEndpointDAO_Impl(
  __db: RoomDatabase,
) : ProxyEndpointDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfProxyEndpoint: EntityInsertAdapter<ProxyEndpoint>

  private val __deleteAdapterOfProxyEndpoint: EntityDeleteOrUpdateAdapter<ProxyEndpoint>

  private val __updateAdapterOfProxyEndpoint: EntityDeleteOrUpdateAdapter<ProxyEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfProxyEndpoint = object : EntityInsertAdapter<ProxyEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `ProxyEndpoint` (`id`,`proxyName`,`proxyMode`,`proxyType`,`proxyAppName`,`proxyIP`,`proxyPort`,`userName`,`password`,`isSelected`,`isCustom`,`isUDP`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.proxyName)
        statement.bindLong(3, entity.proxyMode.toLong())
        statement.bindText(4, entity.proxyType)
        val _tmpProxyAppName: String? = entity.proxyAppName
        if (_tmpProxyAppName == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpProxyAppName)
        }
        val _tmpProxyIP: String? = entity.proxyIP
        if (_tmpProxyIP == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpProxyIP)
        }
        statement.bindLong(7, entity.proxyPort.toLong())
        val _tmpUserName: String? = entity.userName
        if (_tmpUserName == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpUserName)
        }
        val _tmpPassword: String? = entity.password
        if (_tmpPassword == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpPassword)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(11, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isUDP) 1 else 0
        statement.bindLong(12, _tmp_2.toLong())
        statement.bindLong(13, entity.modifiedDataTime)
        statement.bindLong(14, entity.latency.toLong())
      }
    }
    this.__deleteAdapterOfProxyEndpoint = object : EntityDeleteOrUpdateAdapter<ProxyEndpoint>() {
      protected override fun createQuery(): String = "DELETE FROM `ProxyEndpoint` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfProxyEndpoint = object : EntityDeleteOrUpdateAdapter<ProxyEndpoint>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `ProxyEndpoint` SET `id` = ?,`proxyName` = ?,`proxyMode` = ?,`proxyType` = ?,`proxyAppName` = ?,`proxyIP` = ?,`proxyPort` = ?,`userName` = ?,`password` = ?,`isSelected` = ?,`isCustom` = ?,`isUDP` = ?,`modifiedDataTime` = ?,`latency` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.proxyName)
        statement.bindLong(3, entity.proxyMode.toLong())
        statement.bindText(4, entity.proxyType)
        val _tmpProxyAppName: String? = entity.proxyAppName
        if (_tmpProxyAppName == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpProxyAppName)
        }
        val _tmpProxyIP: String? = entity.proxyIP
        if (_tmpProxyIP == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpProxyIP)
        }
        statement.bindLong(7, entity.proxyPort.toLong())
        val _tmpUserName: String? = entity.userName
        if (_tmpUserName == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpUserName)
        }
        val _tmpPassword: String? = entity.password
        if (_tmpPassword == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpPassword)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(11, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isUDP) 1 else 0
        statement.bindLong(12, _tmp_2.toLong())
        statement.bindLong(13, entity.modifiedDataTime)
        statement.bindLong(14, entity.latency.toLong())
        statement.bindLong(15, entity.id.toLong())
      }
    }
  }

  public override fun insert(proxyEndpoint: ProxyEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfProxyEndpoint.insert(_connection, proxyEndpoint)
  }

  public override fun delete(proxyEndpoint: ProxyEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfProxyEndpoint.handle(_connection, proxyEndpoint)
  }

  public override fun update(proxyEndpoint: ProxyEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfProxyEndpoint.handle(_connection, proxyEndpoint)
  }

  public override fun getDNSProxyEndpointLiveData(): PagingSource<Int, ProxyEndpoint> {
    val _sql: String = "select * from ProxyEndpoint where proxyMode = 1 order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<ProxyEndpoint>(_rawQuery, __db, "ProxyEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ProxyEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
          val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
          val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
          val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
          val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
          val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
          val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
          val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<ProxyEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: ProxyEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpProxyName: String
            _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
            val _tmpProxyMode: Int
            _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
            val _tmpProxyType: String
            _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
            val _tmpProxyAppName: String?
            if (_stmt.isNull(_columnIndexOfProxyAppName)) {
              _tmpProxyAppName = null
            } else {
              _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
            }
            val _tmpProxyIP: String?
            if (_stmt.isNull(_columnIndexOfProxyIP)) {
              _tmpProxyIP = null
            } else {
              _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
            }
            val _tmpProxyPort: Int
            _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
            val _tmpUserName: String?
            if (_stmt.isNull(_columnIndexOfUserName)) {
              _tmpUserName = null
            } else {
              _tmpUserName = _stmt.getText(_columnIndexOfUserName)
            }
            val _tmpPassword: String?
            if (_stmt.isNull(_columnIndexOfPassword)) {
              _tmpPassword = null
            } else {
              _tmpPassword = _stmt.getText(_columnIndexOfPassword)
            }
            val _tmpIsSelected: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
            _tmpIsSelected = _tmp != 0
            val _tmpIsCustom: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
            _tmpIsCustom = _tmp_1 != 0
            val _tmpIsUDP: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
            _tmpIsUDP = _tmp_2 != 0
            val _tmpModifiedDataTime: Long
            _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
            val _tmpLatency: Int
            _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
            _item = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDNSProxyEndpointLiveDataByType(query: String): PagingSource<Int, ProxyEndpoint> {
    val _sql: String = "select * from ProxyEndpoint where proxyName like ? order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<ProxyEndpoint>(_rawQuery, __db, "ProxyEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ProxyEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
          val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
          val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
          val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
          val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
          val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
          val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
          val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<ProxyEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: ProxyEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpProxyName: String
            _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
            val _tmpProxyMode: Int
            _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
            val _tmpProxyType: String
            _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
            val _tmpProxyAppName: String?
            if (_stmt.isNull(_columnIndexOfProxyAppName)) {
              _tmpProxyAppName = null
            } else {
              _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
            }
            val _tmpProxyIP: String?
            if (_stmt.isNull(_columnIndexOfProxyIP)) {
              _tmpProxyIP = null
            } else {
              _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
            }
            val _tmpProxyPort: Int
            _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
            val _tmpUserName: String?
            if (_stmt.isNull(_columnIndexOfUserName)) {
              _tmpUserName = null
            } else {
              _tmpUserName = _stmt.getText(_columnIndexOfUserName)
            }
            val _tmpPassword: String?
            if (_stmt.isNull(_columnIndexOfPassword)) {
              _tmpPassword = null
            } else {
              _tmpPassword = _stmt.getText(_columnIndexOfPassword)
            }
            val _tmpIsSelected: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
            _tmpIsSelected = _tmp != 0
            val _tmpIsCustom: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
            _tmpIsCustom = _tmp_1 != 0
            val _tmpIsUDP: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
            _tmpIsUDP = _tmp_2 != 0
            val _tmpModifiedDataTime: Long
            _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
            val _tmpLatency: Int
            _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
            _item = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getCount(): Int {
    val _sql: String = "select count(*) from ProxyEndpoint"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          _result = _stmt.getLong(0).toInt()
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getConnectedProxyLiveData(): LiveData<ProxyEndpoint?> {
    val _sql: String = "select * from ProxyEndpoint where isSelected = 1"
    return __db.invalidationTracker.createLiveData(arrayOf("ProxyEndpoint"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: ProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpProxyMode: Int
          _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
          val _tmpProxyType: String
          _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
          val _tmpProxyAppName: String?
          if (_stmt.isNull(_columnIndexOfProxyAppName)) {
            _tmpProxyAppName = null
          } else {
            _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
          }
          val _tmpProxyIP: String?
          if (_stmt.isNull(_columnIndexOfProxyIP)) {
            _tmpProxyIP = null
          } else {
            _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
          }
          val _tmpProxyPort: Int
          _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
          val _tmpUserName: String?
          if (_stmt.isNull(_columnIndexOfUserName)) {
            _tmpUserName = null
          } else {
            _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          }
          val _tmpPassword: String?
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpIsUDP: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
          _tmpIsUDP = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCustomSocks5Endpoint(): ProxyEndpoint? {
    val _sql: String = "select * from ProxyEndpoint where proxyMode = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: ProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpProxyMode: Int
          _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
          val _tmpProxyType: String
          _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
          val _tmpProxyAppName: String?
          if (_stmt.isNull(_columnIndexOfProxyAppName)) {
            _tmpProxyAppName = null
          } else {
            _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
          }
          val _tmpProxyIP: String?
          if (_stmt.isNull(_columnIndexOfProxyIP)) {
            _tmpProxyIP = null
          } else {
            _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
          }
          val _tmpProxyPort: Int
          _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
          val _tmpUserName: String?
          if (_stmt.isNull(_columnIndexOfUserName)) {
            _tmpUserName = null
          } else {
            _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          }
          val _tmpPassword: String?
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpIsUDP: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
          _tmpIsUDP = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getConnectedSocks5Proxy(): ProxyEndpoint? {
    val _sql: String = "select * from ProxyEndpoint where isSelected = 1 and proxyMode = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: ProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpProxyMode: Int
          _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
          val _tmpProxyType: String
          _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
          val _tmpProxyAppName: String?
          if (_stmt.isNull(_columnIndexOfProxyAppName)) {
            _tmpProxyAppName = null
          } else {
            _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
          }
          val _tmpProxyIP: String?
          if (_stmt.isNull(_columnIndexOfProxyIP)) {
            _tmpProxyIP = null
          } else {
            _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
          }
          val _tmpProxyPort: Int
          _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
          val _tmpUserName: String?
          if (_stmt.isNull(_columnIndexOfUserName)) {
            _tmpUserName = null
          } else {
            _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          }
          val _tmpPassword: String?
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpIsUDP: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
          _tmpIsUDP = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getHttpProxyDetails(): ProxyEndpoint? {
    val _sql: String = "select * from ProxyEndpoint where proxyMode = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: ProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpProxyMode: Int
          _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
          val _tmpProxyType: String
          _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
          val _tmpProxyAppName: String?
          if (_stmt.isNull(_columnIndexOfProxyAppName)) {
            _tmpProxyAppName = null
          } else {
            _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
          }
          val _tmpProxyIP: String?
          if (_stmt.isNull(_columnIndexOfProxyIP)) {
            _tmpProxyIP = null
          } else {
            _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
          }
          val _tmpProxyPort: Int
          _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
          val _tmpUserName: String?
          if (_stmt.isNull(_columnIndexOfUserName)) {
            _tmpUserName = null
          } else {
            _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          }
          val _tmpPassword: String?
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpIsUDP: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
          _tmpIsUDP = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getConnectedHttpProxy(): ProxyEndpoint? {
    val _sql: String = "select * from ProxyEndpoint where proxyMode = 1 and isSelected = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: ProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpProxyMode: Int
          _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
          val _tmpProxyType: String
          _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
          val _tmpProxyAppName: String?
          if (_stmt.isNull(_columnIndexOfProxyAppName)) {
            _tmpProxyAppName = null
          } else {
            _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
          }
          val _tmpProxyIP: String?
          if (_stmt.isNull(_columnIndexOfProxyIP)) {
            _tmpProxyIP = null
          } else {
            _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
          }
          val _tmpProxyPort: Int
          _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
          val _tmpUserName: String?
          if (_stmt.isNull(_columnIndexOfUserName)) {
            _tmpUserName = null
          } else {
            _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          }
          val _tmpPassword: String?
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpIsUDP: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
          _tmpIsUDP = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getConnectedOrbotProxy(): ProxyEndpoint? {
    val _sql: String = "select * from ProxyEndpoint where isSelected = 1 and (proxyMode = 2 or proxyMode = 3)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: ProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpProxyMode: Int
          _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
          val _tmpProxyType: String
          _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
          val _tmpProxyAppName: String?
          if (_stmt.isNull(_columnIndexOfProxyAppName)) {
            _tmpProxyAppName = null
          } else {
            _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
          }
          val _tmpProxyIP: String?
          if (_stmt.isNull(_columnIndexOfProxyIP)) {
            _tmpProxyIP = null
          } else {
            _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
          }
          val _tmpProxyPort: Int
          _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
          val _tmpUserName: String?
          if (_stmt.isNull(_columnIndexOfUserName)) {
            _tmpUserName = null
          } else {
            _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          }
          val _tmpPassword: String?
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpIsUDP: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
          _tmpIsUDP = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getOrbotSocks5Endpoint(): ProxyEndpoint? {
    val _sql: String = "select * from ProxyEndpoint where proxyMode = 2"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: ProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpProxyMode: Int
          _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
          val _tmpProxyType: String
          _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
          val _tmpProxyAppName: String?
          if (_stmt.isNull(_columnIndexOfProxyAppName)) {
            _tmpProxyAppName = null
          } else {
            _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
          }
          val _tmpProxyIP: String?
          if (_stmt.isNull(_columnIndexOfProxyIP)) {
            _tmpProxyIP = null
          } else {
            _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
          }
          val _tmpProxyPort: Int
          _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
          val _tmpUserName: String?
          if (_stmt.isNull(_columnIndexOfUserName)) {
            _tmpUserName = null
          } else {
            _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          }
          val _tmpPassword: String?
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpIsUDP: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
          _tmpIsUDP = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getOrbotHttpEndpoint(): ProxyEndpoint? {
    val _sql: String = "select * from ProxyEndpoint where proxyMode = 3"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyMode: Int = getColumnIndexOrThrow(_stmt, "proxyMode")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfUserName: Int = getColumnIndexOrThrow(_stmt, "userName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsUDP: Int = getColumnIndexOrThrow(_stmt, "isUDP")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: ProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpProxyMode: Int
          _tmpProxyMode = _stmt.getLong(_columnIndexOfProxyMode).toInt()
          val _tmpProxyType: String
          _tmpProxyType = _stmt.getText(_columnIndexOfProxyType)
          val _tmpProxyAppName: String?
          if (_stmt.isNull(_columnIndexOfProxyAppName)) {
            _tmpProxyAppName = null
          } else {
            _tmpProxyAppName = _stmt.getText(_columnIndexOfProxyAppName)
          }
          val _tmpProxyIP: String?
          if (_stmt.isNull(_columnIndexOfProxyIP)) {
            _tmpProxyIP = null
          } else {
            _tmpProxyIP = _stmt.getText(_columnIndexOfProxyIP)
          }
          val _tmpProxyPort: Int
          _tmpProxyPort = _stmt.getLong(_columnIndexOfProxyPort).toInt()
          val _tmpUserName: String?
          if (_stmt.isNull(_columnIndexOfUserName)) {
            _tmpUserName = null
          } else {
            _tmpUserName = _stmt.getText(_columnIndexOfUserName)
          }
          val _tmpPassword: String?
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpIsUDP: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUDP).toInt()
          _tmpIsUDP = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = ProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyMode,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpUserName,_tmpPassword,_tmpIsSelected,_tmpIsCustom,_tmpIsUDP,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun removeConnectionStatus() {
    val _sql: String = "update ProxyEndpoint set isSelected = 0 where isSelected = 1"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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
