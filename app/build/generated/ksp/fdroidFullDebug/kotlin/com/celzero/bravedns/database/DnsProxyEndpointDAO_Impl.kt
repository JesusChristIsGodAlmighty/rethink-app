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
public class DnsProxyEndpointDAO_Impl(
  __db: RoomDatabase,
) : DnsProxyEndpointDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfDnsProxyEndpoint: EntityInsertAdapter<DnsProxyEndpoint>

  private val __insertAdapterOfDnsProxyEndpoint_1: EntityInsertAdapter<DnsProxyEndpoint>

  private val __deleteAdapterOfDnsProxyEndpoint: EntityDeleteOrUpdateAdapter<DnsProxyEndpoint>

  private val __updateAdapterOfDnsProxyEndpoint: EntityDeleteOrUpdateAdapter<DnsProxyEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfDnsProxyEndpoint = object : EntityInsertAdapter<DnsProxyEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `DNSProxyEndpoint` (`id`,`proxyName`,`proxyType`,`proxyAppName`,`proxyIP`,`proxyPort`,`isSelected`,`isCustom`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DnsProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.proxyName)
        statement.bindText(3, entity.proxyType)
        val _tmpProxyAppName: String? = entity.proxyAppName
        if (_tmpProxyAppName == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpProxyAppName)
        }
        val _tmpProxyIP: String? = entity.proxyIP
        if (_tmpProxyIP == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpProxyIP)
        }
        statement.bindLong(6, entity.proxyPort.toLong())
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(8, _tmp_1.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
        statement.bindLong(10, entity.latency.toLong())
      }
    }
    this.__insertAdapterOfDnsProxyEndpoint_1 = object : EntityInsertAdapter<DnsProxyEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `DNSProxyEndpoint` (`id`,`proxyName`,`proxyType`,`proxyAppName`,`proxyIP`,`proxyPort`,`isSelected`,`isCustom`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DnsProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.proxyName)
        statement.bindText(3, entity.proxyType)
        val _tmpProxyAppName: String? = entity.proxyAppName
        if (_tmpProxyAppName == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpProxyAppName)
        }
        val _tmpProxyIP: String? = entity.proxyIP
        if (_tmpProxyIP == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpProxyIP)
        }
        statement.bindLong(6, entity.proxyPort.toLong())
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCustom) 1 else 0
        statement.bindLong(8, _tmp_1.toLong())
        statement.bindLong(9, entity.modifiedDataTime)
        statement.bindLong(10, entity.latency.toLong())
      }
    }
    this.__deleteAdapterOfDnsProxyEndpoint = object : EntityDeleteOrUpdateAdapter<DnsProxyEndpoint>() {
      protected override fun createQuery(): String = "DELETE FROM `DNSProxyEndpoint` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DnsProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfDnsProxyEndpoint = object : EntityDeleteOrUpdateAdapter<DnsProxyEndpoint>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `DNSProxyEndpoint` SET `id` = ?,`proxyName` = ?,`proxyType` = ?,`proxyAppName` = ?,`proxyIP` = ?,`proxyPort` = ?,`isSelected` = ?,`isCustom` = ?,`modifiedDataTime` = ?,`latency` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DnsProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.proxyName)
        statement.bindText(3, entity.proxyType)
        val _tmpProxyAppName: String? = entity.proxyAppName
        if (_tmpProxyAppName == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpProxyAppName)
        }
        val _tmpProxyIP: String? = entity.proxyIP
        if (_tmpProxyIP == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpProxyIP)
        }
        statement.bindLong(6, entity.proxyPort.toLong())
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

  public override fun insert(dnsProxyEndpoint: DnsProxyEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDnsProxyEndpoint.insert(_connection, dnsProxyEndpoint)
  }

  public override fun insertWithReplace(dnsProxyEndpoint: DnsProxyEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfDnsProxyEndpoint_1.insert(_connection, dnsProxyEndpoint)
  }

  public override fun delete(dnsProxyEndpoint: DnsProxyEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfDnsProxyEndpoint.handle(_connection, dnsProxyEndpoint)
  }

  public override fun update(dnsProxyEndpoint: DnsProxyEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfDnsProxyEndpoint.handle(_connection, dnsProxyEndpoint)
  }

  public override fun getDnsProxyEndpointLiveData(): PagingSource<Int, DnsProxyEndpoint> {
    val _sql: String = "select * from DNSProxyEndpoint order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<DnsProxyEndpoint>(_rawQuery, __db, "DNSProxyEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsProxyEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
          val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
          val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
          val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
          val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DnsProxyEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsProxyEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpProxyName: String
            _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
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
            _item = DnsProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getDnsProxyEndpointLiveDataByType(query: String): PagingSource<Int, DnsProxyEndpoint> {
    val _sql: String = "select * from DNSProxyEndpoint where proxyName like ? order by isSelected desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<DnsProxyEndpoint>(_rawQuery, __db, "DNSProxyEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<DnsProxyEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
          val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
          val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
          val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
          val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
          val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
          val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
          val _result: MutableList<DnsProxyEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: DnsProxyEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpProxyName: String
            _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
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
            _item = DnsProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
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
    val _sql: String = "select count(*) from DNSProxyEndpoint"
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

  public override fun getSelectedProxy(): DnsProxyEndpoint? {
    val _sql: String = "select * from DNSProxyEndpoint where isSelected = 1"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: DnsProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
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
          _result = DnsProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getOrbotDnsEndpoint(): DnsProxyEndpoint? {
    val _sql: String = "select * from DNSProxyEndpoint where proxyName = 'Orbot' and isCustom = 0 LIMIT 1"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: DnsProxyEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
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
          _result = DnsProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAll(): List<DnsProxyEndpoint> {
    val _sql: String = "select * from DNSProxyEndpoint order by proxyName asc"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfProxyType: Int = getColumnIndexOrThrow(_stmt, "proxyType")
        val _columnIndexOfProxyAppName: Int = getColumnIndexOrThrow(_stmt, "proxyAppName")
        val _columnIndexOfProxyIP: Int = getColumnIndexOrThrow(_stmt, "proxyIP")
        val _columnIndexOfProxyPort: Int = getColumnIndexOrThrow(_stmt, "proxyPort")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfIsCustom: Int = getColumnIndexOrThrow(_stmt, "isCustom")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<DnsProxyEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: DnsProxyEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
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
          _item = DnsProxyEndpoint(_tmpId,_tmpProxyName,_tmpProxyType,_tmpProxyAppName,_tmpProxyIP,_tmpProxyPort,_tmpIsSelected,_tmpIsCustom,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteOlderData(date: Long) {
    val _sql: String = "delete from DNSProxyEndpoint where modifiedDataTime < ?"
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
    val _sql: String = "delete from DNSProxyEndpoint"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteDnsProxyEndpoint(id: Int) {
    val _sql: String = "delete from DNSProxyEndpoint where id = ? and isSelected = 0"
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
    val _sql: String = "update DNSProxyEndpoint set isSelected = 0 where isSelected = 1"
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
