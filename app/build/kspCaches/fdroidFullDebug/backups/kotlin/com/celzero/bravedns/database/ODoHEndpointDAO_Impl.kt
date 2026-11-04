package com.celzero.bravedns.database

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
public class ODoHEndpointDAO_Impl(
  __db: RoomDatabase,
) : ODoHEndpointDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfODoHEndpoint: EntityInsertAdapter<ODoHEndpoint>

  private val __insertAdapterOfODoHEndpoint_1: EntityInsertAdapter<ODoHEndpoint>

  private val __deleteAdapterOfODoHEndpoint: EntityDeleteOrUpdateAdapter<ODoHEndpoint>

  private val __updateAdapterOfODoHEndpoint: EntityDeleteOrUpdateAdapter<ODoHEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfODoHEndpoint = object : EntityInsertAdapter<ODoHEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `ODoHEndpoint` (`id`,`name`,`proxy`,`resolver`,`proxyIps`,`desc`,`isSelected`,`isCustom`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ODoHEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.proxy)
        statement.bindText(4, entity.resolver)
        statement.bindText(5, entity.proxyIps)
        val _tmpDesc: String? = entity.desc
        if (_tmpDesc == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpDesc)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(8, _tmp_1.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
        statement.bindLong(10, entity.latency.toLong())
      }
    }
    this.__insertAdapterOfODoHEndpoint_1 = object : EntityInsertAdapter<ODoHEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `ODoHEndpoint` (`id`,`name`,`proxy`,`resolver`,`proxyIps`,`desc`,`isSelected`,`isCustom`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ODoHEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.proxy)
        statement.bindText(4, entity.resolver)
        statement.bindText(5, entity.proxyIps)
        val _tmpDesc: String? = entity.desc
        if (_tmpDesc == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpDesc)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(8, _tmp_1.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
        statement.bindLong(10, entity.latency.toLong())
      }
    }
    this.__deleteAdapterOfODoHEndpoint = object : EntityDeleteOrUpdateAdapter<ODoHEndpoint>() {
      protected override fun createQuery(): String = "DELETE FROM `ODoHEndpoint` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ODoHEndpoint) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfODoHEndpoint = object : EntityDeleteOrUpdateAdapter<ODoHEndpoint>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `ODoHEndpoint` SET `id` = ?,`name` = ?,`proxy` = ?,`resolver` = ?,`proxyIps` = ?,`desc` = ?,`isSelected` = ?,`isCustom` = ?,`modifiedDataTime` = ?,`latency` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ODoHEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.proxy)
        statement.bindText(4, entity.resolver)
        statement.bindText(5, entity.proxyIps)
        val _tmpDesc: String? = entity.desc
        if (_tmpDesc == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpDesc)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(8, _tmp_1.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
        statement.bindLong(10, entity.latency.toLong())
        statement.bindLong(11, entity.id.toLong())
      }
    }
  }

  public override fun insert(endpoint: ODoHEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfODoHEndpoint.insert(_connection, endpoint)
  }

  public override fun insertReplace(endpoint: ODoHEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfODoHEndpoint_1.insert(_connection, endpoint)
  }

  public override fun delete(endpoint: ODoHEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfODoHEndpoint.handle(_connection, endpoint)
  }

  public override fun update(endpoint: ODoHEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfODoHEndpoint.handle(_connection, endpoint)
  }

  public override fun getODoHEndpointLiveData(): PagingSource<Int, ODoHEndpoint> {
    val _sql: String = "select * from ODoHEndpoint order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<ODoHEndpoint>(_rawQuery, __db, "ODoHEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ODoHEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfProxy: Int = getColumnIndexOrThrow(_stmt, "proxy")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfProxyIps: Int = getColumnIndexOrThrow(_stmt, "proxyIps")
          val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<ODoHEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: ODoHEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpProxy: String
            _tmpProxy = _stmt.getText(_columnIndexOfProxy)
            val _tmpResolver: String
            _tmpResolver = _stmt.getText(_columnIndexOfResolver)
            val _tmpProxyIps: String
            _tmpProxyIps = _stmt.getText(_columnIndexOfProxyIps)
            val _tmpDesc: String?
            if (_stmt.isNull(_columnIndexOfDesc)) {
              _tmpDesc = null
            } else {
              _tmpDesc = _stmt.getText(_columnIndexOfDesc)
            }
            val _tmpIsSelected: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
            _tmpIsSelected = _tmp != 0
            val _tmpIsCustom: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
            _tmpIsCustom = _tmp_1 != 0
            val _tmpModifiedDataTime: Long
            _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
            val _tmpLatency: Int
            _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
            _item = ODoHEndpoint(_tmpId,_tmpName,_tmpProxy,_tmpResolver,_tmpProxyIps,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override suspend fun getAllAsList(): List<ODoHEndpoint> {
    val _sql: String = "select * from ODoHEndpoint order by isSelected desc"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfProxy: Int = getColumnIndexOrThrow(_stmt, "proxy")
        val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
        val _columnIndexOfProxyIps: Int = getColumnIndexOrThrow(_stmt, "proxyIps")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<ODoHEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: ODoHEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpProxy: String
          _tmpProxy = _stmt.getText(_columnIndexOfProxy)
          val _tmpResolver: String
          _tmpResolver = _stmt.getText(_columnIndexOfResolver)
          val _tmpProxyIps: String
          _tmpProxyIps = _stmt.getText(_columnIndexOfProxyIps)
          val _tmpDesc: String?
          if (_stmt.isNull(_columnIndexOfDesc)) {
            _tmpDesc = null
          } else {
            _tmpDesc = _stmt.getText(_columnIndexOfDesc)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _item = ODoHEndpoint(_tmpId,_tmpName,_tmpProxy,_tmpResolver,_tmpProxyIps,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getODoHEndpointLiveDataByName(query: String): PagingSource<Int, ODoHEndpoint> {
    val _sql: String = "select * from ODoHEndpoint where resolver like ? or name like ? order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<ODoHEndpoint>(_rawQuery, __db, "ODoHEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ODoHEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfProxy: Int = getColumnIndexOrThrow(_stmt, "proxy")
          val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
          val _columnIndexOfProxyIps: Int = getColumnIndexOrThrow(_stmt, "proxyIps")
          val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<ODoHEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: ODoHEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpProxy: String
            _tmpProxy = _stmt.getText(_columnIndexOfProxy)
            val _tmpResolver: String
            _tmpResolver = _stmt.getText(_columnIndexOfResolver)
            val _tmpProxyIps: String
            _tmpProxyIps = _stmt.getText(_columnIndexOfProxyIps)
            val _tmpDesc: String?
            if (_stmt.isNull(_columnIndexOfDesc)) {
              _tmpDesc = null
            } else {
              _tmpDesc = _stmt.getText(_columnIndexOfDesc)
            }
            val _tmpIsSelected: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
            _tmpIsSelected = _tmp != 0
            val _tmpIsCustom: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
            _tmpIsCustom = _tmp_1 != 0
            val _tmpModifiedDataTime: Long
            _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
            val _tmpLatency: Int
            _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
            _item = ODoHEndpoint(_tmpId,_tmpName,_tmpProxy,_tmpResolver,_tmpProxyIps,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getConnectedODoH(): ODoHEndpoint? {
    val _sql: String = "select * from ODoHEndpoint where isSelected = 1"
    return performBlocking(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfProxy: Int = getColumnIndexOrThrow(_stmt, "proxy")
        val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
        val _columnIndexOfProxyIps: Int = getColumnIndexOrThrow(_stmt, "proxyIps")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: ODoHEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpProxy: String
          _tmpProxy = _stmt.getText(_columnIndexOfProxy)
          val _tmpResolver: String
          _tmpResolver = _stmt.getText(_columnIndexOfResolver)
          val _tmpProxyIps: String
          _tmpProxyIps = _stmt.getText(_columnIndexOfProxyIps)
          val _tmpDesc: String?
          if (_stmt.isNull(_columnIndexOfDesc)) {
            _tmpDesc = null
          } else {
            _tmpDesc = _stmt.getText(_columnIndexOfDesc)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = ODoHEndpoint(_tmpId,_tmpName,_tmpProxy,_tmpResolver,_tmpProxyIps,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCount(): Int {
    val _sql: String = "select count(*) from ODoHEndpoint"
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

  public override fun getAll(): List<ODoHEndpoint> {
    val _sql: String = "select * from ODoHEndpoint order by name asc"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfProxy: Int = getColumnIndexOrThrow(_stmt, "proxy")
        val _columnIndexOfResolver: Int = getColumnIndexOrThrow(_stmt, "resolver")
        val _columnIndexOfProxyIps: Int = getColumnIndexOrThrow(_stmt, "proxyIps")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<ODoHEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: ODoHEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpProxy: String
          _tmpProxy = _stmt.getText(_columnIndexOfProxy)
          val _tmpResolver: String
          _tmpResolver = _stmt.getText(_columnIndexOfResolver)
          val _tmpProxyIps: String
          _tmpProxyIps = _stmt.getText(_columnIndexOfProxyIps)
          val _tmpDesc: String?
          if (_stmt.isNull(_columnIndexOfDesc)) {
            _tmpDesc = null
          } else {
            _tmpDesc = _stmt.getText(_columnIndexOfDesc)
          }
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _item = ODoHEndpoint(_tmpId,_tmpName,_tmpProxy,_tmpResolver,_tmpProxyIps,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteOlderData(date: Long) {
    val _sql: String = "delete from ODoHEndpoint where modifiedDataTime < ?"
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

  public override fun clearAllData() {
    val _sql: String = "delete from ODoHEndpoint"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteODoHEndpoint(id: Int) {
    val _sql: String = "delete from ODoHEndpoint where id = ? and isCustom = 1"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun removeConnectionStatus() {
    val _sql: String = "update ODoHEndpoint set isSelected = 0 where isSelected = 1"
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
