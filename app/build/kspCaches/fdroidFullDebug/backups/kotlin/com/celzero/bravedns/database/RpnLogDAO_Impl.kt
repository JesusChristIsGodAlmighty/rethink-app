package com.celzero.bravedns.database

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
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
public class RpnLogDAO_Impl(
  __db: RoomDatabase,
) : RpnLogDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfRpnLog: EntityInsertAdapter<RpnLog>

  private val __insertAdapterOfRpnLog_1: EntityInsertAdapter<RpnLog>
  init {
    this.__db = __db
    this.__insertAdapterOfRpnLog = object : EntityInsertAdapter<RpnLog>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `RpnLog` (`id`,`message`,`level`,`timestamp`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RpnLog) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.message)
        statement.bindLong(3, entity.level)
        statement.bindLong(4, entity.timestamp)
      }
    }
    this.__insertAdapterOfRpnLog_1 = object : EntityInsertAdapter<RpnLog>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `RpnLog` (`id`,`message`,`level`,`timestamp`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RpnLog) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.message)
        statement.bindLong(3, entity.level)
        statement.bindLong(4, entity.timestamp)
      }
    }
  }

  public override suspend fun insert(log: RpnLog): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRpnLog.insert(_connection, log)
  }

  public override suspend fun insertBatch(logs: List<RpnLog>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRpnLog_1.insert(_connection, logs)
  }

  public override suspend fun getLogsChunked(
    lastId: Int,
    limit: Int,
    offset: Int,
  ): List<RpnLog> {
    val _sql: String = "SELECT * FROM RpnLog where id > ? LIMIT ? OFFSET ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, lastId.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _result: MutableList<RpnLog> = mutableListOf()
        while (_stmt.step()) {
          val _item: RpnLog
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpMessage: String
          _tmpMessage = _stmt.getText(_columnIndexOfMessage)
          val _tmpLevel: Long
          _tmpLevel = _stmt.getLong(_columnIndexOfLevel)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          _item = RpnLog(_tmpId,_tmpMessage,_tmpLevel,_tmpTimestamp)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLogsForUi(
    input: String,
    minLevel: Int,
    limit: Int,
  ): List<RpnLog> {
    val _sql: String = "SELECT * FROM RpnLog WHERE message LIKE ? AND level >= ? ORDER BY timestamp DESC, id DESC LIMIT ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, input)
        _argIndex = 2
        _stmt.bindLong(_argIndex, minLevel.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _result: MutableList<RpnLog> = mutableListOf()
        while (_stmt.step()) {
          val _item: RpnLog
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpMessage: String
          _tmpMessage = _stmt.getText(_columnIndexOfMessage)
          val _tmpLevel: Long
          _tmpLevel = _stmt.getLong(_columnIndexOfLevel)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          _item = RpnLog(_tmpId,_tmpMessage,_tmpLevel,_tmpTimestamp)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun sinceTime(): Long {
    val _sql: String = "select timestamp from RpnLog order by id limit 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Long
        if (_stmt.step()) {
          _result = _stmt.getLong(0)
        } else {
          _result = 0L
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLogCount(): Int {
    val _sql: String = "select count(*) from RpnLog"
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

  public override suspend fun deleteOldLogs(to: Long) {
    val _sql: String = "DELETE FROM RpnLog WHERE timestamp < ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, to)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllLogs() {
    val _sql: String = "DELETE FROM RpnLog"
    return performSuspending(__db, false, true) { _connection ->
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
