package com.celzero.bravedns.database

import androidx.paging.PagingSource
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SubscriptionStateHistoryDao_Impl(
  __db: RoomDatabase,
) : SubscriptionStateHistoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSubscriptionStateHistory:
      EntityInsertAdapter<SubscriptionStateHistory>
  init {
    this.__db = __db
    this.__insertAdapterOfSubscriptionStateHistory = object : EntityInsertAdapter<SubscriptionStateHistory>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `SubscriptionStateHistory` (`id`,`subscriptionId`,`fromState`,`toState`,`timestamp`,`reason`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SubscriptionStateHistory) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.subscriptionId.toLong())
        statement.bindLong(3, entity.fromState.toLong())
        statement.bindLong(4, entity.toState.toLong())
        statement.bindLong(5, entity.timestamp)
        val _tmpReason: String? = entity.reason
        if (_tmpReason == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpReason)
        }
      }
    }
  }

  public override suspend fun insert(history: SubscriptionStateHistory): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSubscriptionStateHistory.insertAndReturnId(_connection, history)
    _result
  }

  public override suspend fun getHistoryForSubscription(subscriptionId: Int): List<SubscriptionStateHistory> {
    val _sql: String = "SELECT * FROM SubscriptionStateHistory WHERE subscriptionId = ? ORDER BY timestamp DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, subscriptionId.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSubscriptionId: Int = getColumnIndexOrThrow(_stmt, "subscriptionId")
        val _columnIndexOfFromState: Int = getColumnIndexOrThrow(_stmt, "fromState")
        val _columnIndexOfToState: Int = getColumnIndexOrThrow(_stmt, "toState")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
        val _result: MutableList<SubscriptionStateHistory> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStateHistory
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpSubscriptionId: Int
          _tmpSubscriptionId = _stmt.getLong(_columnIndexOfSubscriptionId).toInt()
          val _tmpFromState: Int
          _tmpFromState = _stmt.getLong(_columnIndexOfFromState).toInt()
          val _tmpToState: Int
          _tmpToState = _stmt.getLong(_columnIndexOfToState).toInt()
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpReason: String?
          if (_stmt.isNull(_columnIndexOfReason)) {
            _tmpReason = null
          } else {
            _tmpReason = _stmt.getText(_columnIndexOfReason)
          }
          _item = SubscriptionStateHistory(_tmpId,_tmpSubscriptionId,_tmpFromState,_tmpToState,_tmpTimestamp,_tmpReason)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRecentHistory(limit: Int): List<SubscriptionStateHistory> {
    val _sql: String = "SELECT * FROM SubscriptionStateHistory ORDER BY timestamp DESC LIMIT ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSubscriptionId: Int = getColumnIndexOrThrow(_stmt, "subscriptionId")
        val _columnIndexOfFromState: Int = getColumnIndexOrThrow(_stmt, "fromState")
        val _columnIndexOfToState: Int = getColumnIndexOrThrow(_stmt, "toState")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
        val _result: MutableList<SubscriptionStateHistory> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStateHistory
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpSubscriptionId: Int
          _tmpSubscriptionId = _stmt.getLong(_columnIndexOfSubscriptionId).toInt()
          val _tmpFromState: Int
          _tmpFromState = _stmt.getLong(_columnIndexOfFromState).toInt()
          val _tmpToState: Int
          _tmpToState = _stmt.getLong(_columnIndexOfToState).toInt()
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpReason: String?
          if (_stmt.isNull(_columnIndexOfReason)) {
            _tmpReason = null
          } else {
            _tmpReason = _stmt.getText(_columnIndexOfReason)
          }
          _item = SubscriptionStateHistory(_tmpId,_tmpSubscriptionId,_tmpFromState,_tmpToState,_tmpTimestamp,_tmpReason)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeHistoryPaged(): PagingSource<Int, SubscriptionStateHistory> {
    val _sql: String = """
        |
        |        SELECT * FROM SubscriptionStateHistory
        |        WHERE fromState != toState
        |          AND fromState NOT IN (-1, 4)
        |          AND toState   NOT IN (0, 4)
        |        ORDER BY timestamp DESC
        |    
        """.trimMargin()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<SubscriptionStateHistory>(_rawQuery, __db, "SubscriptionStateHistory") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<SubscriptionStateHistory> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfSubscriptionId: Int = getColumnIndexOrThrow(_stmt, "subscriptionId")
          val _columnIndexOfFromState: Int = getColumnIndexOrThrow(_stmt, "fromState")
          val _columnIndexOfToState: Int = getColumnIndexOrThrow(_stmt, "toState")
          val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
          val _columnIndexOfReason: Int = getColumnIndexOrThrow(_stmt, "reason")
          val _result: MutableList<SubscriptionStateHistory> = mutableListOf()
          while (_stmt.step()) {
            val _item: SubscriptionStateHistory
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpSubscriptionId: Int
            _tmpSubscriptionId = _stmt.getLong(_columnIndexOfSubscriptionId).toInt()
            val _tmpFromState: Int
            _tmpFromState = _stmt.getLong(_columnIndexOfFromState).toInt()
            val _tmpToState: Int
            _tmpToState = _stmt.getLong(_columnIndexOfToState).toInt()
            val _tmpTimestamp: Long
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
            val _tmpReason: String?
            if (_stmt.isNull(_columnIndexOfReason)) {
              _tmpReason = null
            } else {
              _tmpReason = _stmt.getText(_columnIndexOfReason)
            }
            _item = SubscriptionStateHistory(_tmpId,_tmpSubscriptionId,_tmpFromState,_tmpToState,_tmpTimestamp,_tmpReason)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override suspend fun getMeaningfulCount(): Int {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM SubscriptionStateHistory
        |        WHERE fromState != toState
        |          AND fromState NOT IN (-1, 4)
        |          AND toState   NOT IN (0, 4)
        |    
        """.trimMargin()
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

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM SubscriptionStateHistory"
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
