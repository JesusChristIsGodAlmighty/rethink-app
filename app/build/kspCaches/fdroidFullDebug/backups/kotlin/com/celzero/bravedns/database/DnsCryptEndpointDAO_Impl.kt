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
public class DnsCryptEndpointDAO_Impl(
  __db: RoomDatabase,
) : DnsCryptEndpointDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfDnsCryptEndpoint: EntityInsertAdapter<DnsCryptEndpoint>

  private val __deleteAdapterOfDnsCryptEndpoint: EntityDeleteOrUpdateAdapter<DnsCryptEndpoint>

  private val __updateAdapterOfDnsCryptEndpoint: EntityDeleteOrUpdateAdapter<DnsCryptEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfDnsCryptEndpoint = object : EntityInsertAdapter<DnsCryptEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `DNSCryptEndpoint` (`id`,`dnsCryptName`,`dnsCryptURL`,`dnsCryptExplanation`,`isSelected`,`isCustom`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DnsCryptEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.dnsCryptName)
        statement.bindText(3, entity.dnsCryptURL)
        val _tmpDnsCryptExplanation: String? = entity.dnsCryptExplanation
        if (_tmpDnsCryptExplanation == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDnsCryptExplanation)
        }
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        statement.bindLong(7, entity.modifiedDataTime)
        statement.bindLong(8, entity.latency.toLong())
      }
    }
    this.__deleteAdapterOfDnsCryptEndpoint = object : EntityDeleteOrUpdateAdapter<DnsCryptEndpoint>() {
      protected override fun createQuery(): String = "DELETE FROM `DNSCryptEndpoint` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DnsCryptEndpoint) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfDnsCryptEndpoint = object : EntityDeleteOrUpdateAdapter<DnsCryptEndpoint>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `DNSCryptEndpoint` SET `id` = ?,`dnsCryptName` = ?,`dnsCryptURL` = ?,`dnsCryptExplanation` = ?,`isSelected` = ?,`isCustom` = ?,`modifiedDataTime` = ?,`latency` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DnsCryptEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.dnsCryptName)
        statement.bindText(3, entity.dnsCryptURL)
        val _tmpDnsCryptExplanation: String? = entity.dnsCryptExplanation
        if (_tmpDnsCryptExplanation == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpDnsCryptExplanation)
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

  public override fun insert(dnsCryptEndpoint: DnsCryptEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDnsCryptEndpoint.insert(_connection, dnsCryptEndpoint)
  }

  public override fun delete(dnsCryptEndpoint: DnsCryptEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfDnsCryptEndpoint.handle(_connection, dnsCryptEndpoint)
  }

  public override fun update(dnsCryptEndpoint: DnsCryptEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfDnsCryptEndpoint.handle(_connection, dnsCryptEndpoint)
  }

  public override fun getDNSCryptEndpointLiveData(): PagingSource<Int, DnsCryptEndpoint> {
    val _sql: String = "select * from DNSCryptEndpoint order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<DnsCryptEndpoint>(_rawQuery, __db, "DNSCryptEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsCryptEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfDnsCryptName: Int = getColumnIndexOrThrow(_stmt, "dnsCryptName")
          val _columnIndexOfDnsCryptURL: Int = getColumnIndexOrThrow(_stmt, "dnsCryptURL")
          val _columnIndexOfDnsCryptExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsCryptExplanation")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DnsCryptEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsCryptEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpDnsCryptName: String
            _tmpDnsCryptName = _stmt.getText(_columnIndexOfDnsCryptName)
            val _tmpDnsCryptURL: String
            _tmpDnsCryptURL = _stmt.getText(_columnIndexOfDnsCryptURL)
            val _tmpDnsCryptExplanation: String?
            if (_stmt.isNull(_columnIndexOfDnsCryptExplanation)) {
              _tmpDnsCryptExplanation = null
            } else {
              _tmpDnsCryptExplanation = _stmt.getText(_columnIndexOfDnsCryptExplanation)
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
            _item = DnsCryptEndpoint(_tmpId,_tmpDnsCryptName,_tmpDnsCryptURL,_tmpDnsCryptExplanation,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDNSCryptEndpointLiveDataByName(query: String): PagingSource<Int, DnsCryptEndpoint> {
    val _sql: String = "select * from DNSCryptEndpoint where dnsCryptURL like ? or dnsCryptName like ? order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<DnsCryptEndpoint>(_rawQuery, __db, "DNSCryptEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsCryptEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfDnsCryptName: Int = getColumnIndexOrThrow(_stmt, "dnsCryptName")
          val _columnIndexOfDnsCryptURL: Int = getColumnIndexOrThrow(_stmt, "dnsCryptURL")
          val _columnIndexOfDnsCryptExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsCryptExplanation")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DnsCryptEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsCryptEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpDnsCryptName: String
            _tmpDnsCryptName = _stmt.getText(_columnIndexOfDnsCryptName)
            val _tmpDnsCryptURL: String
            _tmpDnsCryptURL = _stmt.getText(_columnIndexOfDnsCryptURL)
            val _tmpDnsCryptExplanation: String?
            if (_stmt.isNull(_columnIndexOfDnsCryptExplanation)) {
              _tmpDnsCryptExplanation = null
            } else {
              _tmpDnsCryptExplanation = _stmt.getText(_columnIndexOfDnsCryptExplanation)
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
            _item = DnsCryptEndpoint(_tmpId,_tmpDnsCryptName,_tmpDnsCryptURL,_tmpDnsCryptExplanation,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getConnectedDNSCrypt(): DnsCryptEndpoint? {
    val _sql: String = "select * from DNSCryptEndpoint where isSelected = 1"
    return performBlocking(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDnsCryptName: Int = getColumnIndexOrThrow(_stmt, "dnsCryptName")
        val _columnIndexOfDnsCryptURL: Int = getColumnIndexOrThrow(_stmt, "dnsCryptURL")
        val _columnIndexOfDnsCryptExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsCryptExplanation")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: DnsCryptEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDnsCryptName: String
          _tmpDnsCryptName = _stmt.getText(_columnIndexOfDnsCryptName)
          val _tmpDnsCryptURL: String
          _tmpDnsCryptURL = _stmt.getText(_columnIndexOfDnsCryptURL)
          val _tmpDnsCryptExplanation: String?
          if (_stmt.isNull(_columnIndexOfDnsCryptExplanation)) {
            _tmpDnsCryptExplanation = null
          } else {
            _tmpDnsCryptExplanation = _stmt.getText(_columnIndexOfDnsCryptExplanation)
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
          _result = DnsCryptEndpoint(_tmpId,_tmpDnsCryptName,_tmpDnsCryptURL,_tmpDnsCryptExplanation,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getConnectedCount(): Int {
    val _sql: String = "select count(*) from DNSCryptEndpoint where isSelected = 1"
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

  public override fun getCount(): Int {
    val _sql: String = "select count(*) from DNSCryptEndpoint"
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

  public override fun getAll(): List<DnsCryptEndpoint> {
    val _sql: String = "select * from DNSCryptEndpoint order by dnsCryptName asc"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDnsCryptName: Int = getColumnIndexOrThrow(_stmt, "dnsCryptName")
        val _columnIndexOfDnsCryptURL: Int = getColumnIndexOrThrow(_stmt, "dnsCryptURL")
        val _columnIndexOfDnsCryptExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsCryptExplanation")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<DnsCryptEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: DnsCryptEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDnsCryptName: String
          _tmpDnsCryptName = _stmt.getText(_columnIndexOfDnsCryptName)
          val _tmpDnsCryptURL: String
          _tmpDnsCryptURL = _stmt.getText(_columnIndexOfDnsCryptURL)
          val _tmpDnsCryptExplanation: String?
          if (_stmt.isNull(_columnIndexOfDnsCryptExplanation)) {
            _tmpDnsCryptExplanation = null
          } else {
            _tmpDnsCryptExplanation = _stmt.getText(_columnIndexOfDnsCryptExplanation)
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
          _item = DnsCryptEndpoint(_tmpId,_tmpDnsCryptName,_tmpDnsCryptURL,_tmpDnsCryptExplanation,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteOlderData(date: Long) {
    val _sql: String = "delete from DNSCryptEndpoint where modifiedDataTime < ?"
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
    val _sql: String = "delete from DNSCryptEndpoint"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteDNSCryptEndpoint(id: Int) {
    val _sql: String = "delete from DNSCryptEndpoint where id = ? and isCustom = 1"
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
    val _sql: String = "update DNSCryptEndpoint set isSelected = 0 where isSelected = 1"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateConnectionStatus(liveServerID: Int) {
    val _sql: String = "update DNSCryptEndpoint set isSelected = 1 where id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, liveServerID.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateFailingConnections() {
    val _sql: String = "update DNSCryptEndpoint set isSelected=0"
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
