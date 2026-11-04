package com.celzero.bravedns.database

import androidx.paging.PagingSource
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
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
import kotlin.text.StringBuilder

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class EventDao_Impl(
  __db: RoomDatabase,
) : EventDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfEvent: EntityInsertAdapter<Event>

  private val __converters: Converters = Converters()
  init {
    this.__db = __db
    this.__insertAdapterOfEvent = object : EntityInsertAdapter<Event>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `Events` (`id`,`timestamp`,`eventType`,`severity`,`message`,`details`,`source`,`userAction`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Event) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.timestamp)
        val _tmp: String = __converters.fromEventType(entity.eventType)
        statement.bindText(3, _tmp)
        val _tmp_1: String = __converters.fromSeverity(entity.severity)
        statement.bindText(4, _tmp_1)
        statement.bindText(5, entity.message)
        val _tmpDetails: String? = entity.details
        if (_tmpDetails == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpDetails)
        }
        val _tmp_2: String = __converters.fromEventSource(entity.source)
        statement.bindText(7, _tmp_2)
        val _tmp_3: Int = if (entity.userAction) 1 else 0
        statement.bindLong(8, _tmp_3.toLong())
      }
    }
  }

  public override suspend fun insert(event: Event): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfEvent.insert(_connection, event)
  }

  public override suspend fun getLatest(limit: Int): List<Event> {
    val _sql: String = "SELECT * FROM Events ORDER BY timestamp DESC LIMIT ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
        val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
        val _result: MutableList<Event> = mutableListOf()
        while (_stmt.step()) {
          val _item: Event
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpEventType: EventType
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfEventType)
          _tmpEventType = __converters.toEventType(_tmp)
          val _tmpSeverity: Severity
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfSeverity)
          _tmpSeverity = __converters.toSeverity(_tmp_1)
          val _tmpMessage: String
          _tmpMessage = _stmt.getText(_columnIndexOfMessage)
          val _tmpDetails: String?
          if (_stmt.isNull(_columnIndexOfDetails)) {
            _tmpDetails = null
          } else {
            _tmpDetails = _stmt.getText(_columnIndexOfDetails)
          }
          val _tmpSource: EventSource
          val _tmp_2: String
          _tmp_2 = _stmt.getText(_columnIndexOfSource)
          _tmpSource = __converters.toEventSource(_tmp_2)
          val _tmpUserAction: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfUserAction).toInt()
          _tmpUserAction = _tmp_3 != 0
          _item = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByType(eventType: EventType, limit: Int): List<Event> {
    val _sql: String = "SELECT * FROM Events WHERE eventType = ? ORDER BY timestamp DESC LIMIT ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: String = __converters.fromEventType(eventType)
        _stmt.bindText(_argIndex, _tmp)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
        val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
        val _result: MutableList<Event> = mutableListOf()
        while (_stmt.step()) {
          val _item: Event
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpEventType: EventType
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfEventType)
          _tmpEventType = __converters.toEventType(_tmp_1)
          val _tmpSeverity: Severity
          val _tmp_2: String
          _tmp_2 = _stmt.getText(_columnIndexOfSeverity)
          _tmpSeverity = __converters.toSeverity(_tmp_2)
          val _tmpMessage: String
          _tmpMessage = _stmt.getText(_columnIndexOfMessage)
          val _tmpDetails: String?
          if (_stmt.isNull(_columnIndexOfDetails)) {
            _tmpDetails = null
          } else {
            _tmpDetails = _stmt.getText(_columnIndexOfDetails)
          }
          val _tmpSource: EventSource
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfSource)
          _tmpSource = __converters.toEventSource(_tmp_3)
          val _tmpUserAction: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfUserAction).toInt()
          _tmpUserAction = _tmp_4 != 0
          _item = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBySeverity(severity: Severity, limit: Int): List<Event> {
    val _sql: String = "SELECT * FROM Events WHERE severity = ? ORDER BY timestamp DESC LIMIT ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: String = __converters.fromSeverity(severity)
        _stmt.bindText(_argIndex, _tmp)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
        val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
        val _result: MutableList<Event> = mutableListOf()
        while (_stmt.step()) {
          val _item: Event
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpEventType: EventType
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfEventType)
          _tmpEventType = __converters.toEventType(_tmp_1)
          val _tmpSeverity: Severity
          val _tmp_2: String
          _tmp_2 = _stmt.getText(_columnIndexOfSeverity)
          _tmpSeverity = __converters.toSeverity(_tmp_2)
          val _tmpMessage: String
          _tmpMessage = _stmt.getText(_columnIndexOfMessage)
          val _tmpDetails: String?
          if (_stmt.isNull(_columnIndexOfDetails)) {
            _tmpDetails = null
          } else {
            _tmpDetails = _stmt.getText(_columnIndexOfDetails)
          }
          val _tmpSource: EventSource
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfSource)
          _tmpSource = __converters.toEventSource(_tmp_3)
          val _tmpUserAction: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfUserAction).toInt()
          _tmpUserAction = _tmp_4 != 0
          _item = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBySource(source: EventSource, limit: Int): List<Event> {
    val _sql: String = "SELECT * FROM Events WHERE source = ? ORDER BY timestamp DESC LIMIT ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: String = __converters.fromEventSource(source)
        _stmt.bindText(_argIndex, _tmp)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
        val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
        val _result: MutableList<Event> = mutableListOf()
        while (_stmt.step()) {
          val _item: Event
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpEventType: EventType
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfEventType)
          _tmpEventType = __converters.toEventType(_tmp_1)
          val _tmpSeverity: Severity
          val _tmp_2: String
          _tmp_2 = _stmt.getText(_columnIndexOfSeverity)
          _tmpSeverity = __converters.toSeverity(_tmp_2)
          val _tmpMessage: String
          _tmpMessage = _stmt.getText(_columnIndexOfMessage)
          val _tmpDetails: String?
          if (_stmt.isNull(_columnIndexOfDetails)) {
            _tmpDetails = null
          } else {
            _tmpDetails = _stmt.getText(_columnIndexOfDetails)
          }
          val _tmpSource: EventSource
          val _tmp_3: String
          _tmp_3 = _stmt.getText(_columnIndexOfSource)
          _tmpSource = __converters.toEventSource(_tmp_3)
          val _tmpUserAction: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfUserAction).toInt()
          _tmpUserAction = _tmp_4 != 0
          _item = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByTimeRange(startTime: Long, endTime: Long): List<Event> {
    val _sql: String = "SELECT * FROM Events WHERE timestamp BETWEEN ? AND ? ORDER BY timestamp DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endTime)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
        val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
        val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
        val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
        val _result: MutableList<Event> = mutableListOf()
        while (_stmt.step()) {
          val _item: Event
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpEventType: EventType
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfEventType)
          _tmpEventType = __converters.toEventType(_tmp)
          val _tmpSeverity: Severity
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfSeverity)
          _tmpSeverity = __converters.toSeverity(_tmp_1)
          val _tmpMessage: String
          _tmpMessage = _stmt.getText(_columnIndexOfMessage)
          val _tmpDetails: String?
          if (_stmt.isNull(_columnIndexOfDetails)) {
            _tmpDetails = null
          } else {
            _tmpDetails = _stmt.getText(_columnIndexOfDetails)
          }
          val _tmpSource: EventSource
          val _tmp_2: String
          _tmp_2 = _stmt.getText(_columnIndexOfSource)
          _tmpSource = __converters.toEventSource(_tmp_2)
          val _tmpUserAction: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfUserAction).toInt()
          _tmpUserAction = _tmp_3 != 0
          _item = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM Events"
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

  public override fun getAllEventsPaged(): PagingSource<Int, Event> {
    val _sql: String = "SELECT * FROM Events ORDER BY timestamp DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<Event>(_rawQuery, __db, "Events") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<Event> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
          val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
          val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
          val _result: MutableList<Event> = mutableListOf()
          while (_stmt.step()) {
            val _item: Event
            val _tmpId: Long
            _tmpId = _stmt.getLong(_columnIndexOfId)
            val _tmpTimestamp: Long
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
            val _tmpEventType: EventType
            val _tmp: String
            _tmp = _stmt.getText(_columnIndexOfEventType)
            _tmpEventType = __converters.toEventType(_tmp)
            val _tmpSeverity: Severity
            val _tmp_1: String
            _tmp_1 = _stmt.getText(_columnIndexOfSeverity)
            _tmpSeverity = __converters.toSeverity(_tmp_1)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpDetails: String?
            if (_stmt.isNull(_columnIndexOfDetails)) {
              _tmpDetails = null
            } else {
              _tmpDetails = _stmt.getText(_columnIndexOfDetails)
            }
            val _tmpSource: EventSource
            val _tmp_2: String
            _tmp_2 = _stmt.getText(_columnIndexOfSource)
            _tmpSource = __converters.toEventSource(_tmp_2)
            val _tmpUserAction: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfUserAction).toInt()
            _tmpUserAction = _tmp_3 != 0
            _item = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getEventsBySearchPaged(searchQuery: String): PagingSource<Int, Event> {
    val _sql: String = "SELECT * FROM Events WHERE LOWER(message) LIKE LOWER(?) OR LOWER(details) LIKE LOWER(?) OR LOWER(eventType) LIKE LOWER(?) OR LOWER(source) LIKE LOWER(?) ORDER BY timestamp DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, searchQuery)
      _argIndex = 2
      _stmt.bindText(_argIndex, searchQuery)
      _argIndex = 3
      _stmt.bindText(_argIndex, searchQuery)
      _argIndex = 4
      _stmt.bindText(_argIndex, searchQuery)
    }
    return object : LimitOffsetPagingSource<Event>(_rawQuery, __db, "Events") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<Event> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
          val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
          val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
          val _result: MutableList<Event> = mutableListOf()
          while (_stmt.step()) {
            val _item: Event
            val _tmpId: Long
            _tmpId = _stmt.getLong(_columnIndexOfId)
            val _tmpTimestamp: Long
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
            val _tmpEventType: EventType
            val _tmp: String
            _tmp = _stmt.getText(_columnIndexOfEventType)
            _tmpEventType = __converters.toEventType(_tmp)
            val _tmpSeverity: Severity
            val _tmp_1: String
            _tmp_1 = _stmt.getText(_columnIndexOfSeverity)
            _tmpSeverity = __converters.toSeverity(_tmp_1)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpDetails: String?
            if (_stmt.isNull(_columnIndexOfDetails)) {
              _tmpDetails = null
            } else {
              _tmpDetails = _stmt.getText(_columnIndexOfDetails)
            }
            val _tmpSource: EventSource
            val _tmp_2: String
            _tmp_2 = _stmt.getText(_columnIndexOfSource)
            _tmpSource = __converters.toEventSource(_tmp_2)
            val _tmpUserAction: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfUserAction).toInt()
            _tmpUserAction = _tmp_3 != 0
            _item = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getEventsBySeverityPaged(severity: Severity): PagingSource<Int, Event> {
    val _sql: String = "SELECT * FROM Events WHERE severity = ? ORDER BY timestamp DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      val _tmp: String = __converters.fromSeverity(severity)
      _stmt.bindText(_argIndex, _tmp)
    }
    return object : LimitOffsetPagingSource<Event>(_rawQuery, __db, "Events") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<Event> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
          val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
          val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
          val _result: MutableList<Event> = mutableListOf()
          while (_stmt.step()) {
            val _item: Event
            val _tmpId: Long
            _tmpId = _stmt.getLong(_columnIndexOfId)
            val _tmpTimestamp: Long
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
            val _tmpEventType: EventType
            val _tmp_1: String
            _tmp_1 = _stmt.getText(_columnIndexOfEventType)
            _tmpEventType = __converters.toEventType(_tmp_1)
            val _tmpSeverity: Severity
            val _tmp_2: String
            _tmp_2 = _stmt.getText(_columnIndexOfSeverity)
            _tmpSeverity = __converters.toSeverity(_tmp_2)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpDetails: String?
            if (_stmt.isNull(_columnIndexOfDetails)) {
              _tmpDetails = null
            } else {
              _tmpDetails = _stmt.getText(_columnIndexOfDetails)
            }
            val _tmpSource: EventSource
            val _tmp_3: String
            _tmp_3 = _stmt.getText(_columnIndexOfSource)
            _tmpSource = __converters.toEventSource(_tmp_3)
            val _tmpUserAction: Boolean
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfUserAction).toInt()
            _tmpUserAction = _tmp_4 != 0
            _item = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getEventsBySeverityAndSearchPaged(severity: Severity, searchQuery: String): PagingSource<Int, Event> {
    val _sql: String = "SELECT * FROM Events WHERE severity = ? AND (LOWER(message) LIKE LOWER(?) OR LOWER(details) LIKE LOWER(?) OR LOWER(eventType) LIKE LOWER(?) OR LOWER(source) LIKE LOWER(?)) ORDER BY timestamp DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      val _tmp: String = __converters.fromSeverity(severity)
      _stmt.bindText(_argIndex, _tmp)
      _argIndex = 2
      _stmt.bindText(_argIndex, searchQuery)
      _argIndex = 3
      _stmt.bindText(_argIndex, searchQuery)
      _argIndex = 4
      _stmt.bindText(_argIndex, searchQuery)
      _argIndex = 5
      _stmt.bindText(_argIndex, searchQuery)
    }
    return object : LimitOffsetPagingSource<Event>(_rawQuery, __db, "Events") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<Event> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
          val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
          val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
          val _result: MutableList<Event> = mutableListOf()
          while (_stmt.step()) {
            val _item: Event
            val _tmpId: Long
            _tmpId = _stmt.getLong(_columnIndexOfId)
            val _tmpTimestamp: Long
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
            val _tmpEventType: EventType
            val _tmp_1: String
            _tmp_1 = _stmt.getText(_columnIndexOfEventType)
            _tmpEventType = __converters.toEventType(_tmp_1)
            val _tmpSeverity: Severity
            val _tmp_2: String
            _tmp_2 = _stmt.getText(_columnIndexOfSeverity)
            _tmpSeverity = __converters.toSeverity(_tmp_2)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpDetails: String?
            if (_stmt.isNull(_columnIndexOfDetails)) {
              _tmpDetails = null
            } else {
              _tmpDetails = _stmt.getText(_columnIndexOfDetails)
            }
            val _tmpSource: EventSource
            val _tmp_3: String
            _tmp_3 = _stmt.getText(_columnIndexOfSource)
            _tmpSource = __converters.toEventSource(_tmp_3)
            val _tmpUserAction: Boolean
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfUserAction).toInt()
            _tmpUserAction = _tmp_4 != 0
            _item = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getEventsBySourcesPaged(sources: List<EventSource>): PagingSource<Int, Event> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM Events WHERE source IN (")
    val _inputSize: Int = sources.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") ORDER BY timestamp DESC")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: EventSource in sources) {
        val _tmp: String = __converters.fromEventSource(_item)
        _stmt.bindText(_argIndex, _tmp)
        _argIndex++
      }
    }
    return object : LimitOffsetPagingSource<Event>(_rawQuery, __db, "Events") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<Event> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
          val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
          val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
          val _result: MutableList<Event> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: Event
            val _tmpId: Long
            _tmpId = _stmt.getLong(_columnIndexOfId)
            val _tmpTimestamp: Long
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
            val _tmpEventType: EventType
            val _tmp_1: String
            _tmp_1 = _stmt.getText(_columnIndexOfEventType)
            _tmpEventType = __converters.toEventType(_tmp_1)
            val _tmpSeverity: Severity
            val _tmp_2: String
            _tmp_2 = _stmt.getText(_columnIndexOfSeverity)
            _tmpSeverity = __converters.toSeverity(_tmp_2)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpDetails: String?
            if (_stmt.isNull(_columnIndexOfDetails)) {
              _tmpDetails = null
            } else {
              _tmpDetails = _stmt.getText(_columnIndexOfDetails)
            }
            val _tmpSource: EventSource
            val _tmp_3: String
            _tmp_3 = _stmt.getText(_columnIndexOfSource)
            _tmpSource = __converters.toEventSource(_tmp_3)
            val _tmpUserAction: Boolean
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfUserAction).toInt()
            _tmpUserAction = _tmp_4 != 0
            _item_1 = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getEventsBySourcesAndSearchPaged(sources: List<EventSource>, searchQuery: String): PagingSource<Int, Event> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM Events WHERE source IN (")
    val _inputSize: Int = sources.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") AND (LOWER(message) LIKE LOWER(")
    _stringBuilder.append("?")
    _stringBuilder.append(") OR LOWER(details) LIKE LOWER(")
    _stringBuilder.append("?")
    _stringBuilder.append(") OR LOWER(eventType) LIKE LOWER(")
    _stringBuilder.append("?")
    _stringBuilder.append(") OR LOWER(source) LIKE LOWER(")
    _stringBuilder.append("?")
    _stringBuilder.append(")) ORDER BY timestamp DESC")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: EventSource in sources) {
        val _tmp: String = __converters.fromEventSource(_item)
        _stmt.bindText(_argIndex, _tmp)
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      _stmt.bindText(_argIndex, searchQuery)
      _argIndex = 2 + _inputSize
      _stmt.bindText(_argIndex, searchQuery)
      _argIndex = 3 + _inputSize
      _stmt.bindText(_argIndex, searchQuery)
      _argIndex = 4 + _inputSize
      _stmt.bindText(_argIndex, searchQuery)
    }
    return object : LimitOffsetPagingSource<Event>(_rawQuery, __db, "Events") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<Event> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
          val _columnIndexOfEventType: Int = getColumnIndexOrThrow(_stmt, "eventType")
          val _columnIndexOfSeverity: Int = getColumnIndexOrThrow(_stmt, "severity")
          val _columnIndexOfMessage: Int = getColumnIndexOrThrow(_stmt, "message")
          val _columnIndexOfDetails: Int = getColumnIndexOrThrow(_stmt, "details")
          val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
          val _columnIndexOfUserAction: Int = getColumnIndexOrThrow(_stmt, "userAction")
          val _result: MutableList<Event> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: Event
            val _tmpId: Long
            _tmpId = _stmt.getLong(_columnIndexOfId)
            val _tmpTimestamp: Long
            _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
            val _tmpEventType: EventType
            val _tmp_1: String
            _tmp_1 = _stmt.getText(_columnIndexOfEventType)
            _tmpEventType = __converters.toEventType(_tmp_1)
            val _tmpSeverity: Severity
            val _tmp_2: String
            _tmp_2 = _stmt.getText(_columnIndexOfSeverity)
            _tmpSeverity = __converters.toSeverity(_tmp_2)
            val _tmpMessage: String
            _tmpMessage = _stmt.getText(_columnIndexOfMessage)
            val _tmpDetails: String?
            if (_stmt.isNull(_columnIndexOfDetails)) {
              _tmpDetails = null
            } else {
              _tmpDetails = _stmt.getText(_columnIndexOfDetails)
            }
            val _tmpSource: EventSource
            val _tmp_3: String
            _tmp_3 = _stmt.getText(_columnIndexOfSource)
            _tmpSource = __converters.toEventSource(_tmp_3)
            val _tmpUserAction: Boolean
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfUserAction).toInt()
            _tmpUserAction = _tmp_4 != 0
            _item_1 = Event(_tmpId,_tmpTimestamp,_tmpEventType,_tmpSeverity,_tmpMessage,_tmpDetails,_tmpSource,_tmpUserAction)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override suspend fun deleteOlderThan(cutoff: Long): Int {
    val _sql: String = "DELETE FROM Events WHERE timestamp < ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, cutoff)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM Events"
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
