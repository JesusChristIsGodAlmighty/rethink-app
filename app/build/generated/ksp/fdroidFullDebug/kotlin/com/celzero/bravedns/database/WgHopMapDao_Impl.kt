package com.celzero.bravedns.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performBlocking
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.LongArray
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class WgHopMapDao_Impl(
  __db: RoomDatabase,
) : WgHopMapDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfWgHopMap: EntityInsertAdapter<WgHopMap>

  private val __deleteAdapterOfWgHopMap: EntityDeleteOrUpdateAdapter<WgHopMap>

  private val __updateAdapterOfWgHopMap: EntityDeleteOrUpdateAdapter<WgHopMap>
  init {
    this.__db = __db
    this.__insertAdapterOfWgHopMap = object : EntityInsertAdapter<WgHopMap>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `WgHopMap` (`id`,`src`,`hop`,`isActive`,`status`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: WgHopMap) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.src)
        statement.bindText(3, entity.hop)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindText(5, entity.status)
      }
    }
    this.__deleteAdapterOfWgHopMap = object : EntityDeleteOrUpdateAdapter<WgHopMap>() {
      protected override fun createQuery(): String = "DELETE FROM `WgHopMap` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: WgHopMap) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfWgHopMap = object : EntityDeleteOrUpdateAdapter<WgHopMap>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `WgHopMap` SET `id` = ?,`src` = ?,`hop` = ?,`isActive` = ?,`status` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: WgHopMap) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.src)
        statement.bindText(3, entity.hop)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindText(5, entity.status)
        statement.bindLong(6, entity.id.toLong())
      }
    }
  }

  public override fun insertAll(maps: List<WgHopMap>): LongArray = performBlocking(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfWgHopMap.insertAndReturnIdsArray(_connection, maps)
    _result
  }

  public override fun insert(map: WgHopMap): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfWgHopMap.insertAndReturnId(_connection, map)
    _result
  }

  public override fun delete(map: WgHopMap): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfWgHopMap.handle(_connection, map)
  }

  public override fun update(map: WgHopMap): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfWgHopMap.handle(_connection, map)
  }

  public override fun getBySrc(src: String): WgHopMap? {
    val _sql: String = "select * from WgHopMap where src = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, src)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSrc: Int = getColumnIndexOrThrow(_stmt, "src")
        val _columnIndexOfHop: Int = getColumnIndexOrThrow(_stmt, "hop")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _result: WgHopMap?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpSrc: String
          _tmpSrc = _stmt.getText(_columnIndexOfSrc)
          val _tmpHop: String
          _tmpHop = _stmt.getText(_columnIndexOfHop)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          _result = WgHopMap(_tmpId,_tmpSrc,_tmpHop,_tmpIsActive,_tmpStatus)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllWgs(): List<WgHopMap> {
    val _sql: String = "select * from WgHopMap where src like 'wgyrpn%'"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSrc: Int = getColumnIndexOrThrow(_stmt, "src")
        val _columnIndexOfHop: Int = getColumnIndexOrThrow(_stmt, "hop")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _result: MutableList<WgHopMap> = mutableListOf()
        while (_stmt.step()) {
          val _item: WgHopMap
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpSrc: String
          _tmpSrc = _stmt.getText(_columnIndexOfSrc)
          val _tmpHop: String
          _tmpHop = _stmt.getText(_columnIndexOfHop)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          _item = WgHopMap(_tmpId,_tmpSrc,_tmpHop,_tmpIsActive,_tmpStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllRpn(): List<WgHopMap> {
    val _sql: String = "select * from WgHopMap where src like 'wg%'"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSrc: Int = getColumnIndexOrThrow(_stmt, "src")
        val _columnIndexOfHop: Int = getColumnIndexOrThrow(_stmt, "hop")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _result: MutableList<WgHopMap> = mutableListOf()
        while (_stmt.step()) {
          val _item: WgHopMap
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpSrc: String
          _tmpSrc = _stmt.getText(_columnIndexOfSrc)
          val _tmpHop: String
          _tmpHop = _stmt.getText(_columnIndexOfHop)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          _item = WgHopMap(_tmpId,_tmpSrc,_tmpHop,_tmpIsActive,_tmpStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteById(id: Int) {
    val _sql: String = "delete from WgHopMap where id = ?"
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

  public override fun deleteBySrcAndHop(src: String, hop: String): Int {
    val _sql: String = "delete from WgHopMap where src = ? and hop = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, src)
        _argIndex = 2
        _stmt.bindText(_argIndex, hop)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteAll() {
    val _sql: String = "delete from WgHopMap"
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
