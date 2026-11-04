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
public class DnsCryptRelayEndpointDAO_Impl(
  __db: RoomDatabase,
) : DnsCryptRelayEndpointDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfDnsCryptRelayEndpoint: EntityInsertAdapter<DnsCryptRelayEndpoint>

  private val __deleteAdapterOfDnsCryptRelayEndpoint:
      EntityDeleteOrUpdateAdapter<DnsCryptRelayEndpoint>

  private val __updateAdapterOfDnsCryptRelayEndpoint:
      EntityDeleteOrUpdateAdapter<DnsCryptRelayEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfDnsCryptRelayEndpoint = object : EntityInsertAdapter<DnsCryptRelayEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `DNSCryptRelayEndpoint` (`id`,`dnsCryptRelayName`,`dnsCryptRelayURL`,`dnsCryptRelayExplanation`,`isSelected`,`isCustom`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DnsCryptRelayEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.dnsCryptRelayName)
        statement.bindText(3, entity.dnsCryptRelayURL)
        val _tmpDnsCryptRelayExplanation: String? = entity.dnsCryptRelayExplanation
        if (_tmpDnsCryptRelayExplanation == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDnsCryptRelayExplanation)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        statement.bindLong(7, entity.modifiedDataTime)
        statement.bindLong(8, entity.latency.toLong())
      }
    }
    this.__deleteAdapterOfDnsCryptRelayEndpoint = object : EntityDeleteOrUpdateAdapter<DnsCryptRelayEndpoint>() {
      protected override fun createQuery(): String = "DELETE FROM `DNSCryptRelayEndpoint` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DnsCryptRelayEndpoint) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfDnsCryptRelayEndpoint = object : EntityDeleteOrUpdateAdapter<DnsCryptRelayEndpoint>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `DNSCryptRelayEndpoint` SET `id` = ?,`dnsCryptRelayName` = ?,`dnsCryptRelayURL` = ?,`dnsCryptRelayExplanation` = ?,`isSelected` = ?,`isCustom` = ?,`modifiedDataTime` = ?,`latency` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DnsCryptRelayEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.dnsCryptRelayName)
        statement.bindText(3, entity.dnsCryptRelayURL)
        val _tmpDnsCryptRelayExplanation: String? = entity.dnsCryptRelayExplanation
        if (_tmpDnsCryptRelayExplanation == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDnsCryptRelayExplanation)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        statement.bindLong(7, entity.modifiedDataTime)
        statement.bindLong(8, entity.latency.toLong())
        statement.bindLong(9, entity.id.toLong())
      }
    }
  }

  public override fun insert(dnsCryptRelayEndpoint: DnsCryptRelayEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDnsCryptRelayEndpoint.insert(_connection, dnsCryptRelayEndpoint)
  }

  public override fun delete(dnsCryptRelayEndpoint: DnsCryptRelayEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfDnsCryptRelayEndpoint.handle(_connection, dnsCryptRelayEndpoint)
  }

  public override fun update(dnsCryptRelayEndpoint: DnsCryptRelayEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfDnsCryptRelayEndpoint.handle(_connection, dnsCryptRelayEndpoint)
  }

  public override fun getDnsCryptRelayEndpointLiveData(): PagingSource<Int, DnsCryptRelayEndpoint> {
    val _sql: String = "select * from DNSCryptRelayEndpoint order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<DnsCryptRelayEndpoint>(_rawQuery, __db, "DNSCryptRelayEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsCryptRelayEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfDnsCryptRelayName: Int = getColumnIndexOrThrow(_stmt, "dnsCryptRelayName")
          val _columnIndexOfDnsCryptRelayURL: Int = getColumnIndexOrThrow(_stmt, "dnsCryptRelayURL")
          val _columnIndexOfDnsCryptRelayExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsCryptRelayExplanation")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DnsCryptRelayEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsCryptRelayEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpDnsCryptRelayName: String
            _tmpDnsCryptRelayName = _stmt.getText(_columnIndexOfDnsCryptRelayName)
            val _tmpDnsCryptRelayURL: String
            _tmpDnsCryptRelayURL = _stmt.getText(_columnIndexOfDnsCryptRelayURL)
            val _tmpDnsCryptRelayExplanation: String?
            if (_stmt.isNull(_columnIndexOfDnsCryptRelayExplanation)) {
              _tmpDnsCryptRelayExplanation = null
            } else {
              _tmpDnsCryptRelayExplanation = _stmt.getText(_columnIndexOfDnsCryptRelayExplanation)
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
            _item = DnsCryptRelayEndpoint(_tmpId,_tmpDnsCryptRelayName,_tmpDnsCryptRelayURL,_tmpDnsCryptRelayExplanation,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDnsCryptRelayEndpointLiveDataByName(query: String): PagingSource<Int, DnsCryptRelayEndpoint> {
    val _sql: String = "select * from DNSCryptRelayEndpoint where dnsCryptRelayURL like ? or dnsCryptRelayName like ? order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<DnsCryptRelayEndpoint>(_rawQuery, __db, "DNSCryptRelayEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsCryptRelayEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfDnsCryptRelayName: Int = getColumnIndexOrThrow(_stmt, "dnsCryptRelayName")
          val _columnIndexOfDnsCryptRelayURL: Int = getColumnIndexOrThrow(_stmt, "dnsCryptRelayURL")
          val _columnIndexOfDnsCryptRelayExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsCryptRelayExplanation")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DnsCryptRelayEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsCryptRelayEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpDnsCryptRelayName: String
            _tmpDnsCryptRelayName = _stmt.getText(_columnIndexOfDnsCryptRelayName)
            val _tmpDnsCryptRelayURL: String
            _tmpDnsCryptRelayURL = _stmt.getText(_columnIndexOfDnsCryptRelayURL)
            val _tmpDnsCryptRelayExplanation: String?
            if (_stmt.isNull(_columnIndexOfDnsCryptRelayExplanation)) {
              _tmpDnsCryptRelayExplanation = null
            } else {
              _tmpDnsCryptRelayExplanation = _stmt.getText(_columnIndexOfDnsCryptRelayExplanation)
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
            _item = DnsCryptRelayEndpoint(_tmpId,_tmpDnsCryptRelayName,_tmpDnsCryptRelayURL,_tmpDnsCryptRelayExplanation,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getConnectedRelays(): List<DnsCryptRelayEndpoint> {
    val _sql: String = "select * from DNSCryptRelayEndpoint where isSelected = 1"
    return performBlocking(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDnsCryptRelayName: Int = getColumnIndexOrThrow(_stmt, "dnsCryptRelayName")
        val _columnIndexOfDnsCryptRelayURL: Int = getColumnIndexOrThrow(_stmt, "dnsCryptRelayURL")
        val _columnIndexOfDnsCryptRelayExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsCryptRelayExplanation")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<DnsCryptRelayEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: DnsCryptRelayEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDnsCryptRelayName: String
          _tmpDnsCryptRelayName = _stmt.getText(_columnIndexOfDnsCryptRelayName)
          val _tmpDnsCryptRelayURL: String
          _tmpDnsCryptRelayURL = _stmt.getText(_columnIndexOfDnsCryptRelayURL)
          val _tmpDnsCryptRelayExplanation: String?
          if (_stmt.isNull(_columnIndexOfDnsCryptRelayExplanation)) {
            _tmpDnsCryptRelayExplanation = null
          } else {
            _tmpDnsCryptRelayExplanation = _stmt.getText(_columnIndexOfDnsCryptRelayExplanation)
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
          _item = DnsCryptRelayEndpoint(_tmpId,_tmpDnsCryptRelayName,_tmpDnsCryptRelayURL,_tmpDnsCryptRelayExplanation,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCount(): Int {
    val _sql: String = "select count(*) from DNSCryptRelayEndpoint"
    return performBlocking(__db, true, true) { _connection ->
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
    val _sql: String = "delete from DNSCryptRelayEndpoint where modifiedDataTime < ?"
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
    val _sql: String = "delete from DNSCryptRelayEndpoint"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteDnsCryptRelayEndpoint(id: Int) {
    val _sql: String = "delete from DNSCryptRelayEndpoint where id = ? and isCustom = 1"
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
    val _sql: String = "update DNSCryptRelayEndpoint set isSelected = 0 where isSelected = 1"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun unselectRelay(stamp: String) {
    val _sql: String = "update DNSCryptRelayEndpoint set isSelected = 0 where isSelected = 1 and dnsCryptRelayURL = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, stamp)
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
