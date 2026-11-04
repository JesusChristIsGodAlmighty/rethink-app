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
public class RethinkDnsEndpointDao_Impl(
  __db: RoomDatabase,
) : RethinkDnsEndpointDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRethinkDnsEndpoint: EntityInsertAdapter<RethinkDnsEndpoint>

  private val __insertAdapterOfRethinkDnsEndpoint_1: EntityInsertAdapter<RethinkDnsEndpoint>

  private val __deleteAdapterOfRethinkDnsEndpoint: EntityDeleteOrUpdateAdapter<RethinkDnsEndpoint>

  private val __updateAdapterOfRethinkDnsEndpoint: EntityDeleteOrUpdateAdapter<RethinkDnsEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfRethinkDnsEndpoint = object : EntityInsertAdapter<RethinkDnsEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `RethinkDnsEndpoint` (`name`,`url`,`uid`,`desc`,`isActive`,`isCustom`,`latency`,`blocklistCount`,`modifiedDataTime`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkDnsEndpoint) {
        statement.bindText(1, entity.name)
        statement.bindText(2, entity.url)
        statement.bindLong(3, entity.uid.toLong())
        statement.bindText(4, entity.desc)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        statement.bindLong(7, entity.latency.toLong())
        statement.bindLong(8, entity.blocklistCount.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
      }
    }
    this.__insertAdapterOfRethinkDnsEndpoint_1 = object : EntityInsertAdapter<RethinkDnsEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `RethinkDnsEndpoint` (`name`,`url`,`uid`,`desc`,`isActive`,`isCustom`,`latency`,`blocklistCount`,`modifiedDataTime`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkDnsEndpoint) {
        statement.bindText(1, entity.name)
        statement.bindText(2, entity.url)
        statement.bindLong(3, entity.uid.toLong())
        statement.bindText(4, entity.desc)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        statement.bindLong(7, entity.latency.toLong())
        statement.bindLong(8, entity.blocklistCount.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
      }
    }
    this.__deleteAdapterOfRethinkDnsEndpoint = object : EntityDeleteOrUpdateAdapter<RethinkDnsEndpoint>() {
      protected override fun createQuery(): String = "DELETE FROM `RethinkDnsEndpoint` WHERE `name` = ? AND `url` = ? AND `uid` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkDnsEndpoint) {
        statement.bindText(1, entity.name)
        statement.bindText(2, entity.url)
        statement.bindLong(3, entity.uid.toLong())
      }
    }
    this.__updateAdapterOfRethinkDnsEndpoint = object : EntityDeleteOrUpdateAdapter<RethinkDnsEndpoint>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `RethinkDnsEndpoint` SET `name` = ?,`url` = ?,`uid` = ?,`desc` = ?,`isActive` = ?,`isCustom` = ?,`latency` = ?,`blocklistCount` = ?,`modifiedDataTime` = ? WHERE `name` = ? AND `url` = ? AND `uid` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkDnsEndpoint) {
        statement.bindText(1, entity.name)
        statement.bindText(2, entity.url)
        statement.bindLong(3, entity.uid.toLong())
        statement.bindText(4, entity.desc)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        statement.bindLong(7, entity.latency.toLong())
        statement.bindLong(8, entity.blocklistCount.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
        statement.bindText(10, entity.name)
        statement.bindText(11, entity.url)
        statement.bindLong(12, entity.uid.toLong())
      }
    }
  }

  public override fun insert(rethinkDnsEndpoint: RethinkDnsEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfRethinkDnsEndpoint.insert(_connection, rethinkDnsEndpoint)
  }

  public override fun insertReplace(rethinkDnsEndpoint: RethinkDnsEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfRethinkDnsEndpoint_1.insert(_connection, rethinkDnsEndpoint)
  }

  public override fun delete(rethinkDnsEndpoint: RethinkDnsEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfRethinkDnsEndpoint.handle(_connection, rethinkDnsEndpoint)
  }

  public override fun update(rethinkDnsEndpoint: RethinkDnsEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfRethinkDnsEndpoint.handle(_connection, rethinkDnsEndpoint)
  }

  public override fun getRethinkEndpoints(): PagingSource<Int, RethinkDnsEndpoint> {
    val _sql: String = "select * from RethinkDnsEndpoint where uid =  -2000 order by isActive desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<RethinkDnsEndpoint>(_rawQuery, __db, "RethinkDnsEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkDnsEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfBlocklistCount: Int = getColumnIndexOrThrow(_stmt, "blocklistCount")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _result: MutableList<RethinkDnsEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkDnsEndpoint
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpUrl: String
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpDesc: String
            _tmpDesc = _stmt.getText(_columnIndexOfDesc)
            val _tmpIsActive: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _tmpIsActive = _tmp != 0
            val _tmpIsCustom: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
            _tmpIsCustom = _tmp_1 != 0
            val _tmpLatency: Int
            _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
            val _tmpBlocklistCount: Int
            _tmpBlocklistCount = _stmt.getLong(_columnIndexOfBlocklistCount).toInt()
            val _tmpModifiedDataTime: Long
            _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
            _item = RethinkDnsEndpoint(_tmpName,_tmpUrl,_tmpUid,_tmpDesc,_tmpIsActive,_tmpIsCustom,_tmpLatency,_tmpBlocklistCount,_tmpModifiedDataTime)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllRethinkEndpoints(): PagingSource<Int, RethinkDnsEndpoint> {
    val _sql: String = "select * from RethinkDnsEndpoint order by isActive desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<RethinkDnsEndpoint>(_rawQuery, __db, "RethinkDnsEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkDnsEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfBlocklistCount: Int = getColumnIndexOrThrow(_stmt, "blocklistCount")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _result: MutableList<RethinkDnsEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkDnsEndpoint
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpUrl: String
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpDesc: String
            _tmpDesc = _stmt.getText(_columnIndexOfDesc)
            val _tmpIsActive: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _tmpIsActive = _tmp != 0
            val _tmpIsCustom: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
            _tmpIsCustom = _tmp_1 != 0
            val _tmpLatency: Int
            _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
            val _tmpBlocklistCount: Int
            _tmpBlocklistCount = _stmt.getLong(_columnIndexOfBlocklistCount).toInt()
            val _tmpModifiedDataTime: Long
            _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
            _item = RethinkDnsEndpoint(_tmpName,_tmpUrl,_tmpUid,_tmpDesc,_tmpIsActive,_tmpIsCustom,_tmpLatency,_tmpBlocklistCount,_tmpModifiedDataTime)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun isAppWiseDnsEnabled(uid: Int): Boolean? {
    val _sql: String = "select isActive from RethinkDnsEndpoint where uid = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        val _result: Boolean?
        if (_stmt.step()) {
          val _tmp: Int?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0).toInt()
          }
          _result = _tmp?.let { it != 0 }
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRethinkEndpointsByName(query: String): PagingSource<Int, RethinkDnsEndpoint> {
    val _sql: String = "select * from RethinkDnsEndpoint where name like ? or url like ? and uid = -2000 order by isActive desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<RethinkDnsEndpoint>(_rawQuery, __db, "RethinkDnsEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkDnsEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _columnIndexOfBlocklistCount: Int = getColumnIndexOrThrow(_stmt, "blocklistCount")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _result: MutableList<RethinkDnsEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkDnsEndpoint
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpUrl: String
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpDesc: String
            _tmpDesc = _stmt.getText(_columnIndexOfDesc)
            val _tmpIsActive: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _tmpIsActive = _tmp != 0
            val _tmpIsCustom: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
            _tmpIsCustom = _tmp_1 != 0
            val _tmpLatency: Int
            _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
            val _tmpBlocklistCount: Int
            _tmpBlocklistCount = _stmt.getLong(_columnIndexOfBlocklistCount).toInt()
            val _tmpModifiedDataTime: Long
            _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
            _item = RethinkDnsEndpoint(_tmpName,_tmpUrl,_tmpUid,_tmpDesc,_tmpIsActive,_tmpIsCustom,_tmpLatency,_tmpBlocklistCount,_tmpModifiedDataTime)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getConnectedEndpoint(): RethinkDnsEndpoint? {
    val _sql: String = "select * from RethinkDnsEndpoint where isActive = 1 and uid = -2000 LIMIT 1"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _columnIndexOfBlocklistCount: Int = getColumnIndexOrThrow(_stmt, "blocklistCount")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _result: RethinkDnsEndpoint?
        if (_stmt.step()) {
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpDesc: String
          _tmpDesc = _stmt.getText(_columnIndexOfDesc)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          val _tmpBlocklistCount: Int
          _tmpBlocklistCount = _stmt.getLong(_columnIndexOfBlocklistCount).toInt()
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          _result = RethinkDnsEndpoint(_tmpName,_tmpUrl,_tmpUid,_tmpDesc,_tmpIsActive,_tmpIsCustom,_tmpLatency,_tmpBlocklistCount,_tmpModifiedDataTime)
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
    val _sql: String = "select count(*) from RethinkDnsEndpoint"
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

  public override fun getRethinkPlusEndpoint(plus: String): RethinkDnsEndpoint? {
    val _sql: String = "select * from RethinkDnsEndpoint where name = ? and uid = -2000"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, plus)
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _columnIndexOfBlocklistCount: Int = getColumnIndexOrThrow(_stmt, "blocklistCount")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _result: RethinkDnsEndpoint?
        if (_stmt.step()) {
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpDesc: String
          _tmpDesc = _stmt.getText(_columnIndexOfDesc)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          val _tmpBlocklistCount: Int
          _tmpBlocklistCount = _stmt.getLong(_columnIndexOfBlocklistCount).toInt()
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          _result = RethinkDnsEndpoint(_tmpName,_tmpUrl,_tmpUid,_tmpDesc,_tmpIsActive,_tmpIsCustom,_tmpLatency,_tmpBlocklistCount,_tmpModifiedDataTime)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDefaultRethinkEndpoint(): RethinkDnsEndpoint? {
    val _sql: String = "select * from RethinkDnsEndpoint where name = 'RDNS Default' and isCustom = 0 LIMIT 1"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _columnIndexOfBlocklistCount: Int = getColumnIndexOrThrow(_stmt, "blocklistCount")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _result: RethinkDnsEndpoint?
        if (_stmt.step()) {
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpDesc: String
          _tmpDesc = _stmt.getText(_columnIndexOfDesc)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          val _tmpBlocklistCount: Int
          _tmpBlocklistCount = _stmt.getLong(_columnIndexOfBlocklistCount).toInt()
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          _result = RethinkDnsEndpoint(_tmpName,_tmpUrl,_tmpUid,_tmpDesc,_tmpIsActive,_tmpIsCustom,_tmpLatency,_tmpBlocklistCount,_tmpModifiedDataTime)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllForBlockFree(): List<RethinkDnsEndpoint> {
    val _sql: String = "select * from RethinkDnsEndpoint where uid = -2000 order by name asc"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfDesc: Int = getColumnIndexOrThrow(_stmt, "desc")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _columnIndexOfBlocklistCount: Int = getColumnIndexOrThrow(_stmt, "blocklistCount")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _result: MutableList<RethinkDnsEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: RethinkDnsEndpoint
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpDesc: String
          _tmpDesc = _stmt.getText(_columnIndexOfDesc)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpIsCustom: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCustom).toInt()
          _tmpIsCustom = _tmp_1 != 0
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          val _tmpBlocklistCount: Int
          _tmpBlocklistCount = _stmt.getLong(_columnIndexOfBlocklistCount).toInt()
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          _item = RethinkDnsEndpoint(_tmpName,_tmpUrl,_tmpUid,_tmpDesc,_tmpIsActive,_tmpIsCustom,_tmpLatency,_tmpBlocklistCount,_tmpModifiedDataTime)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateEndpoint(
    name: String,
    url: String,
    count: Int,
  ) {
    val _sql: String = "Update RethinkDnsEndpoint set url = ?, blocklistCount = ? where name = ? and uid = -2000"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, url)
        _argIndex = 2
        _stmt.bindLong(_argIndex, count.toLong())
        _argIndex = 3
        _stmt.bindText(_argIndex, name)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun removeConnectionStatus() {
    val _sql: String = "update RethinkDnsEndpoint set isActive = 0 where isActive = 1 and uid = -2000"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun removeAppWiseDns(uid: Int) {
    val _sql: String = "update RethinkDnsEndpoint set isActive = 0 where uid = ?"
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

  public override fun updateConnectionDefault(conn: String) {
    val _sql: String = "update RethinkDnsEndpoint set isActive = 1 where uid = -2000 and name = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, conn)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun setRethinkPlus(plus: String) {
    val _sql: String = "update RethinkDnsEndpoint set isActive = 1 where uid = -2000 and name = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, plus)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updatePlusBlocklistCount(count: Int, plus: String) {
    val _sql: String = "update RethinkDnsEndpoint set blocklistCount = ? where uid = -2000 and name = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, count.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, plus)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun switchToMax() {
    val _sql: String = "update RethinkDnsEndpoint set url = REPLACE(url, 'sky', 'max')"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun switchToSky() {
    val _sql: String = "update RethinkDnsEndpoint set url = REPLACE(url, 'max', 'sky')"
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
