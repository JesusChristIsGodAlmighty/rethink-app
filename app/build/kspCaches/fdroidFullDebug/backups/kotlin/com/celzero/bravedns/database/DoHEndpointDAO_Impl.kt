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
public class DoHEndpointDAO_Impl(
  __db: RoomDatabase,
) : DoHEndpointDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfDoHEndpoint: EntityInsertAdapter<DoHEndpoint>

  private val __insertAdapterOfDoHEndpoint_1: EntityInsertAdapter<DoHEndpoint>

  private val __deleteAdapterOfDoHEndpoint: EntityDeleteOrUpdateAdapter<DoHEndpoint>

  private val __updateAdapterOfDoHEndpoint: EntityDeleteOrUpdateAdapter<DoHEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfDoHEndpoint = object : EntityInsertAdapter<DoHEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `DoHEndpoint` (`id`,`dohName`,`dohURL`,`dohIp`,`dohExplanation`,`isSelected`,`isCustom`,`isSecure`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DoHEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.dohName)
        statement.bindText(3, entity.dohURL)
        val _tmpDohIp: String? = entity.dohIp
        if (_tmpDohIp == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDohIp)
        }
        val _tmpDohExplanation: String? = entity.dohExplanation
        if (_tmpDohExplanation == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDohExplanation)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isSecure) 1 else 0
        statement.bindLong(8, _tmp_2.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
        statement.bindLong(10, entity.latency.toLong())
      }
    }
    this.__insertAdapterOfDoHEndpoint_1 = object : EntityInsertAdapter<DoHEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `DoHEndpoint` (`id`,`dohName`,`dohURL`,`dohIp`,`dohExplanation`,`isSelected`,`isCustom`,`isSecure`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DoHEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.dohName)
        statement.bindText(3, entity.dohURL)
        val _tmpDohIp: String? = entity.dohIp
        if (_tmpDohIp == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDohIp)
        }
        val _tmpDohExplanation: String? = entity.dohExplanation
        if (_tmpDohExplanation == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDohExplanation)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isSecure) 1 else 0
        statement.bindLong(8, _tmp_2.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
        statement.bindLong(10, entity.latency.toLong())
      }
    }
    this.__deleteAdapterOfDoHEndpoint = object : EntityDeleteOrUpdateAdapter<DoHEndpoint>() {
      protected override fun createQuery(): String = "DELETE FROM `DoHEndpoint` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DoHEndpoint) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfDoHEndpoint = object : EntityDeleteOrUpdateAdapter<DoHEndpoint>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `DoHEndpoint` SET `id` = ?,`dohName` = ?,`dohURL` = ?,`dohIp` = ?,`dohExplanation` = ?,`isSelected` = ?,`isCustom` = ?,`isSecure` = ?,`modifiedDataTime` = ?,`latency` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DoHEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.dohName)
        statement.bindText(3, entity.dohURL)
        val _tmpDohIp: String? = entity.dohIp
        if (_tmpDohIp == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDohIp)
        }
        val _tmpDohExplanation: String? = entity.dohExplanation
        if (_tmpDohExplanation == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpDohExplanation)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isSecure) 1 else 0
        statement.bindLong(8, _tmp_2.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
        statement.bindLong(10, entity.latency.toLong())
        statement.bindLong(11, entity.id.toLong())
      }
    }
  }

  public override fun insert(doHEndpoint: DoHEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDoHEndpoint.insert(_connection, doHEndpoint)
  }

  public override fun insertReplace(doHEndpoint: DoHEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDoHEndpoint_1.insert(_connection, doHEndpoint)
  }

  public override fun delete(doHEndpoint: DoHEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfDoHEndpoint.handle(_connection, doHEndpoint)
  }

  public override fun update(doHEndpoint: DoHEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfDoHEndpoint.handle(_connection, doHEndpoint)
  }

  public override fun getDoHEndpointLiveData(): PagingSource<Int, DoHEndpoint> {
    val _sql: String = "select * from DoHEndpoint order by isSelected desc, isCustom asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<DoHEndpoint>(_rawQuery, __db, "DoHEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DoHEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfDohName: Int = getColumnIndexOrThrow(_stmt, "dohName")
          val _columnIndexOfDohURL: Int = getColumnIndexOrThrow(_stmt, "dohURL")
          val _columnIndexOfDohIp: Int = getColumnIndexOrThrow(_stmt, "dohIp")
          val _columnIndexOfDohExplanation: Int = getColumnIndexOrThrow(_stmt, "dohExplanation")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DoHEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DoHEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpDohName: String
            _tmpDohName = _stmt.getText(_columnIndexOfDohName)
            val _tmpDohURL: String
            _tmpDohURL = _stmt.getText(_columnIndexOfDohURL)
            val _tmpDohIp: String?
            if (_stmt.isNull(_columnIndexOfDohIp)) {
              _tmpDohIp = null
            } else {
              _tmpDohIp = _stmt.getText(_columnIndexOfDohIp)
            }
            val _tmpDohExplanation: String?
            if (_stmt.isNull(_columnIndexOfDohExplanation)) {
              _tmpDohExplanation = null
            } else {
              _tmpDohExplanation = _stmt.getText(_columnIndexOfDohExplanation)
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
            _item = DoHEndpoint(_tmpId,_tmpDohName,_tmpDohURL,_tmpDohIp,_tmpDohExplanation,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDoHEndpointLiveDataByName(query: String): PagingSource<Int, DoHEndpoint> {
    val _sql: String = "select * from DoHEndpoint where dohURL like ? or dohName like ? order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<DoHEndpoint>(_rawQuery, __db, "DoHEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DoHEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfDohName: Int = getColumnIndexOrThrow(_stmt, "dohName")
          val _columnIndexOfDohURL: Int = getColumnIndexOrThrow(_stmt, "dohURL")
          val _columnIndexOfDohIp: Int = getColumnIndexOrThrow(_stmt, "dohIp")
          val _columnIndexOfDohExplanation: Int = getColumnIndexOrThrow(_stmt, "dohExplanation")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DoHEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DoHEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpDohName: String
            _tmpDohName = _stmt.getText(_columnIndexOfDohName)
            val _tmpDohURL: String
            _tmpDohURL = _stmt.getText(_columnIndexOfDohURL)
            val _tmpDohIp: String?
            if (_stmt.isNull(_columnIndexOfDohIp)) {
              _tmpDohIp = null
            } else {
              _tmpDohIp = _stmt.getText(_columnIndexOfDohIp)
            }
            val _tmpDohExplanation: String?
            if (_stmt.isNull(_columnIndexOfDohExplanation)) {
              _tmpDohExplanation = null
            } else {
              _tmpDohExplanation = _stmt.getText(_columnIndexOfDohExplanation)
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
            _item = DoHEndpoint(_tmpId,_tmpDohName,_tmpDohURL,_tmpDohIp,_tmpDohExplanation,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getConnectedDoH(): DoHEndpoint? {
    val _sql: String = "select * from DoHEndpoint where isSelected = 1"
    return performBlocking(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDohName: Int = getColumnIndexOrThrow(_stmt, "dohName")
        val _columnIndexOfDohURL: Int = getColumnIndexOrThrow(_stmt, "dohURL")
        val _columnIndexOfDohIp: Int = getColumnIndexOrThrow(_stmt, "dohIp")
        val _columnIndexOfDohExplanation: Int = getColumnIndexOrThrow(_stmt, "dohExplanation")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: DoHEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDohName: String
          _tmpDohName = _stmt.getText(_columnIndexOfDohName)
          val _tmpDohURL: String
          _tmpDohURL = _stmt.getText(_columnIndexOfDohURL)
          val _tmpDohIp: String?
          if (_stmt.isNull(_columnIndexOfDohIp)) {
            _tmpDohIp = null
          } else {
            _tmpDohIp = _stmt.getText(_columnIndexOfDohIp)
          }
          val _tmpDohExplanation: String?
          if (_stmt.isNull(_columnIndexOfDohExplanation)) {
            _tmpDohExplanation = null
          } else {
            _tmpDohExplanation = _stmt.getText(_columnIndexOfDohExplanation)
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
          _result = DoHEndpoint(_tmpId,_tmpDohName,_tmpDohURL,_tmpDohIp,_tmpDohExplanation,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllDefaultDoHEndpoints(): List<DoHEndpoint> {
    val _sql: String = "select * from DoHEndpoint where isCustom = 0"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDohName: Int = getColumnIndexOrThrow(_stmt, "dohName")
        val _columnIndexOfDohURL: Int = getColumnIndexOrThrow(_stmt, "dohURL")
        val _columnIndexOfDohIp: Int = getColumnIndexOrThrow(_stmt, "dohIp")
        val _columnIndexOfDohExplanation: Int = getColumnIndexOrThrow(_stmt, "dohExplanation")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<DoHEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: DoHEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDohName: String
          _tmpDohName = _stmt.getText(_columnIndexOfDohName)
          val _tmpDohURL: String
          _tmpDohURL = _stmt.getText(_columnIndexOfDohURL)
          val _tmpDohIp: String?
          if (_stmt.isNull(_columnIndexOfDohIp)) {
            _tmpDohIp = null
          } else {
            _tmpDohIp = _stmt.getText(_columnIndexOfDohIp)
          }
          val _tmpDohExplanation: String?
          if (_stmt.isNull(_columnIndexOfDohExplanation)) {
            _tmpDohExplanation = null
          } else {
            _tmpDohExplanation = _stmt.getText(_columnIndexOfDohExplanation)
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
          _item = DoHEndpoint(_tmpId,_tmpDohName,_tmpDohURL,_tmpDohIp,_tmpDohExplanation,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAll(): List<DoHEndpoint> {
    val _sql: String = "select * from DoHEndpoint order by dohName asc"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDohName: Int = getColumnIndexOrThrow(_stmt, "dohName")
        val _columnIndexOfDohURL: Int = getColumnIndexOrThrow(_stmt, "dohURL")
        val _columnIndexOfDohIp: Int = getColumnIndexOrThrow(_stmt, "dohIp")
        val _columnIndexOfDohExplanation: Int = getColumnIndexOrThrow(_stmt, "dohExplanation")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfIsSecure: Int = getColumnIndexOrThrow(_stmt, "isSecure")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<DoHEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: DoHEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDohName: String
          _tmpDohName = _stmt.getText(_columnIndexOfDohName)
          val _tmpDohURL: String
          _tmpDohURL = _stmt.getText(_columnIndexOfDohURL)
          val _tmpDohIp: String?
          if (_stmt.isNull(_columnIndexOfDohIp)) {
            _tmpDohIp = null
          } else {
            _tmpDohIp = _stmt.getText(_columnIndexOfDohIp)
          }
          val _tmpDohExplanation: String?
          if (_stmt.isNull(_columnIndexOfDohExplanation)) {
            _tmpDohExplanation = null
          } else {
            _tmpDohExplanation = _stmt.getText(_columnIndexOfDohExplanation)
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
          _item = DoHEndpoint(_tmpId,_tmpDohName,_tmpDohURL,_tmpDohIp,_tmpDohExplanation,_tmpIsSelected,_tmpIsCustom,_tmpIsSecure,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCount(): Int {
    val _sql: String = "select count(*) from DoHEndpoint"
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

  public override fun deleteOlderData(date: Long) {
    val _sql: String = "delete from DoHEndpoint where modifiedDataTime < ?"
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
    val _sql: String = "delete from DoHEndpoint"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteDoHEndpoint(id: Int) {
    val _sql: String = "delete from DoHEndpoint where id = ? and isCustom = 1"
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
    val _sql: String = "update DoHEndpoint set isSelected = 0 where isSelected = 1"
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
