package com.celzero.bravedns.database

import androidx.lifecycle.LiveData
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
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
public class SmartDnsEndpointDAO_Impl(
  __db: RoomDatabase,
) : SmartDnsEndpointDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfSmartDnsEndpoint: EntityInsertAdapter<SmartDnsEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfSmartDnsEndpoint = object : EntityInsertAdapter<SmartDnsEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `SmartDnsEndpoint` (`id`,`dnsName`,`dnsMode`,`dnsExplanation`,`isSelected`,`modifiedDataTime`,`latency`) VALUES (nullif(?, 0),?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SmartDnsEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.dnsName)
        statement.bindLong(3, entity.dnsMode.toLong())
        statement.bindText(4, entity.dnsExplanation)
        val _tmp: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        statement.bindLong(6, entity.modifiedDataTime)
        statement.bindLong(7, entity.latency.toLong())
      }
    }
  }

  public override suspend fun insert(endpoint: SmartDnsEndpoint): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfSmartDnsEndpoint.insert(_connection, endpoint)
  }

  public override suspend fun getSmartDnsEndpoints(): List<SmartDnsEndpoint> {
    val _sql: String = "SELECT * FROM SmartDnsEndpoint order by isSelected desc"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDnsName: Int = getColumnIndexOrThrow(_stmt, "dnsName")
        val _columnIndexOfDnsMode: Int = getColumnIndexOrThrow(_stmt, "dnsMode")
        val _columnIndexOfDnsExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsExplanation")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<SmartDnsEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: SmartDnsEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDnsName: String
          _tmpDnsName = _stmt.getText(_columnIndexOfDnsName)
          val _tmpDnsMode: Int
          _tmpDnsMode = _stmt.getLong(_columnIndexOfDnsMode).toInt()
          val _tmpDnsExplanation: String
          _tmpDnsExplanation = _stmt.getText(_columnIndexOfDnsExplanation)
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _item = SmartDnsEndpoint(_tmpId,_tmpDnsName,_tmpDnsMode,_tmpDnsExplanation,_tmpIsSelected,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getSmartDnsEndpointsLiveData(): LiveData<List<SmartDnsEndpoint>> {
    val _sql: String = "SELECT * FROM SmartDnsEndpoint order by isSelected desc"
    return __db.invalidationTracker.createLiveData(arrayOf("SmartDnsEndpoint"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDnsName: Int = getColumnIndexOrThrow(_stmt, "dnsName")
        val _columnIndexOfDnsMode: Int = getColumnIndexOrThrow(_stmt, "dnsMode")
        val _columnIndexOfDnsExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsExplanation")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: MutableList<SmartDnsEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: SmartDnsEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDnsName: String
          _tmpDnsName = _stmt.getText(_columnIndexOfDnsName)
          val _tmpDnsMode: Int
          _tmpDnsMode = _stmt.getLong(_columnIndexOfDnsMode).toInt()
          val _tmpDnsExplanation: String
          _tmpDnsExplanation = _stmt.getText(_columnIndexOfDnsExplanation)
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _item = SmartDnsEndpoint(_tmpId,_tmpDnsName,_tmpDnsMode,_tmpDnsExplanation,_tmpIsSelected,_tmpModifiedDataTime,_tmpLatency)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSelectedEndpoint(): SmartDnsEndpoint? {
    val _sql: String = "SELECT * FROM SmartDnsEndpoint WHERE isSelected = 1 LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDnsName: Int = getColumnIndexOrThrow(_stmt, "dnsName")
        val _columnIndexOfDnsMode: Int = getColumnIndexOrThrow(_stmt, "dnsMode")
        val _columnIndexOfDnsExplanation: Int = getColumnIndexOrThrow(_stmt, "dnsExplanation")
        val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
        val _columnIndexOfModifiedDataTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDataTime")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _result: SmartDnsEndpoint?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDnsName: String
          _tmpDnsName = _stmt.getText(_columnIndexOfDnsName)
          val _tmpDnsMode: Int
          _tmpDnsMode = _stmt.getLong(_columnIndexOfDnsMode).toInt()
          val _tmpDnsExplanation: String
          _tmpDnsExplanation = _stmt.getText(_columnIndexOfDnsExplanation)
          val _tmpIsSelected: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp != 0
          val _tmpModifiedDataTime: Long
          _tmpModifiedDataTime = _stmt.getLong(_columnIndexOfModifiedDataTime)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          _result = SmartDnsEndpoint(_tmpId,_tmpDnsName,_tmpDnsMode,_tmpDnsExplanation,_tmpIsSelected,_tmpModifiedDataTime,_tmpLatency)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM SmartDnsEndpoint"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeConnectionStatus() {
    val _sql: String = "UPDATE SmartDnsEndpoint SET isSelected = 0 WHERE isSelected = 1"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun setConnectionStatus(id: Int) {
    val _sql: String = "UPDATE SmartDnsEndpoint SET isSelected = 1 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
