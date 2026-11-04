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
public class DoTEndpointDAO_Impl(
  __db: RoomDatabase,
) : DoTEndpointDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfDoTEndpoint: EntityInsertAdapter<DoTEndpoint>

  private val __insertAdapterOfDoTEndpoint_1: EntityInsertAdapter<DoTEndpoint>

  private val __deleteAdapterOfDoTEndpoint: EntityDeleteOrUpdateAdapter<DoTEndpoint>

  private val __updateAdapterOfDoTEndpoint: EntityDeleteOrUpdateAdapter<DoTEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfDoTEndpoint = object : EntityInsertAdapter<DoTEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `DoTEndpoint` (`id`,`name`,`url`,`desc`,`isSelected`,`isCustom`,`isSecure`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DoTEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.url)
        val _tmpDesc: String? = entity.desc
        if (_tmpDesc == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDesc)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isSecure) 1 else 0
        statement.bindLong(7, _tmp_2.toLong())
        statement.bindLong(8, entity.modifiedDataTime)
        statement.bindLong(9, entity.latency.toLong())
      }
    }
    this.__insertAdapterOfDoTEndpoint_1 = object : EntityInsertAdapter<DoTEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `DoTEndpoint` (`id`,`name`,`url`,`desc`,`isSelected`,`isCustom`,`isSecure`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DoTEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.url)
        val _tmpDesc: String? = entity.desc
        if (_tmpDesc == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDesc)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isSecure) 1 else 0
        statement.bindLong(7, _tmp_2.toLong())
        statement.bindLong(8, entity.modifiedDataTime)
        statement.bindLong(9, entity.latency.toLong())
      }
    }
    this.__deleteAdapterOfDoTEndpoint = object : EntityDeleteOrUpdateAdapter<DoTEndpoint>() {
      protected override fun createQuery(): String = "DELETE FROM `DoTEndpoint` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DoTEndpoint) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfDoTEndpoint = object : EntityDeleteOrUpdateAdapter<DoTEndpoint>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `DoTEndpoint` SET `id` = ?,`name` = ?,`url` = ?,`desc` = ?,`isSelected` = ?,`isCustom` = ?,`isSecure` = ?,`modifiedDataTime` = ?,`latency` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DoTEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.url)
        val _tmpDesc: String? = entity.desc
        if (_tmpDesc == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDesc)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isSecure) 1 else 0
        statement.bindLong(7, _tmp_2.toLong())
        statement.bindLong(8, entity.modifiedDataTime)
        statement.bindLong(9, entity.latency.toLong())
        statement.bindLong(10, entity.id.toLong())
      }
    }
  }

  public override fun insert(endpoint: DoTEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDoTEndpoint.insert(_connection, endpoint)
  }

  public override fun insertReplace(endpoint: DoTEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDoTEndpoint_1.insert(_connection, endpoint)
  }

  public override fun delete(endpoint: DoTEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfDoTEndpoint.handle(_connection, endpoint)
  }

  public override fun update(endpoint: DoTEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfDoTEndpoint.handle(_connection, endpoint)
  }

  public override fun getDoTEndpointLiveData(): PagingSource<Int, DoTEndpoint> {
    val _sql: String = "select * from DoTEndpoint order by isSelected desc, isCustom asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<DoTEndpoint>(_rawQuery, __db, "DoTEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DoTEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DoTEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DoTEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpUrl: String
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
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
            val _tmpIsSecure: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsSecure).toInt()
            _tmpIsSecure = _tmp_2 != 0
            val _tmpModifiedDataTime: Long
            _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
            val _tmpLatency: Int
            _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
            _item = DoTEndpoint(_tmpId,_tmpName,_tmpUrl,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDoTEndpointLiveDataByName(query: String): PagingSource<Int, DoTEndpoint> {
    val _sql: String = "select * from DoTEndpoint where url like ? or name like ? order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<DoTEndpoint>(_rawQuery, __db, "DoTEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DoTEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DoTEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DoTEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpUrl: String
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
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
            val _tmpIsSecure: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsSecure).toInt()
            _tmpIsSecure = _tmp_2 != 0
            val _tmpModifiedDataTime: Long
            _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
            val _tmpLatency: Int
            _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
            _item = DoTEndpoint(_tmpId,_tmpName,_tmpUrl,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getConnectedDoT(): DoTEndpoint? {
    val _sql: String = "select * from DoTEndpoint where isSelected = 1"
    return performBlocking(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: DoTEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
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
          val _tmpIsSecure: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsSecure).toInt()
          _tmpIsSecure = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = DoTEndpoint(_tmpId,_tmpName,_tmpUrl,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
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
    val _sql: String = "select count(*) from DoTEndpoint"
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

  public override fun getAllDefaultDoTEndpoints(): List<DoTEndpoint> {
    val _sql: String = "select * from DoTEndpoint where isCustom = 0"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<DoTEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: DoTEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
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
          val _tmpIsSecure: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsSecure).toInt()
          _tmpIsSecure = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _item = DoTEndpoint(_tmpId,_tmpName,_tmpUrl,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAll(): List<DoTEndpoint> {
    val _sql: String = "select * from DoTEndpoint order by name asc"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<DoTEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: DoTEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
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
          val _tmpIsSecure: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsSecure).toInt()
          _tmpIsSecure = _tmp_2 != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _item = DoTEndpoint(_tmpId,_tmpName,_tmpUrl,_tmpDesc,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteOlderData(date: Long) {
    val _sql: String = "delete from DoTEndpoint where modifiedDataTime < ?"
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
    val _sql: String = "delete from DoTEndpoint"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteDoTEndpoint(id: Int) {
    val _sql: String = "delete from DoTEndpoint where id = ? and isCustom = 1"
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
    val _sql: String = "update DoTEndpoint set isSelected = 0 where isSelected = 1"
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
