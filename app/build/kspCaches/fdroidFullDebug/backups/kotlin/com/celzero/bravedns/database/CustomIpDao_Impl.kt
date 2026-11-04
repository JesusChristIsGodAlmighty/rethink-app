package com.celzero.bravedns.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performBlocking
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CustomIpDao_Impl(
  __db: RoomDatabase,
) : CustomIpDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCustomIp: EntityInsertAdapter<CustomIp>

  private val __deleteAdapterOfCustomIp: EntityDeleteOrUpdateAdapter<CustomIp>

  private val __updateAdapterOfCustomIp: EntityDeleteOrUpdateAdapter<CustomIp>
  init {
    this.__db = __db
    this.__insertAdapterOfCustomIp = object : EntityInsertAdapter<CustomIp>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `CustomIp` (`uid`,`ipAddress`,`port`,`protocol`,`isActive`,`proxyId`,`proxyCC`,`status`,`wildcard`,`ruleType`,`modifiedDateTime`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CustomIp) {
        statement.bindLong(1, entity.uid.toLong())
        statement.bindText(2, entity.ipAddress)
        statement.bindLong(3, entity.port.toLong())
        statement.bindText(4, entity.protocol)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        statement.bindText(6, entity.proxyId)
        statement.bindText(7, entity.proxyCC)
        statement.bindLong(8, entity.status.toLong())
        val _tmp_1: Int = if (entity.wildcard) 1 else 0
        statement.bindLong(9, _tmp_1.toLong())
        statement.bindLong(10, entity.ruleType.toLong())
        statement.bindLong(11, entity.modifiedDateTime)
      }
    }
    this.__deleteAdapterOfCustomIp = object : EntityDeleteOrUpdateAdapter<CustomIp>() {
      protected override fun createQuery(): String = "DELETE FROM `CustomIp` WHERE `uid` = ? AND `ipAddress` = ? AND `port` = ? AND `protocol` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CustomIp) {
        statement.bindLong(1, entity.uid.toLong())
        statement.bindText(2, entity.ipAddress)
        statement.bindLong(3, entity.port.toLong())
        statement.bindText(4, entity.protocol)
      }
    }
    this.__updateAdapterOfCustomIp = object : EntityDeleteOrUpdateAdapter<CustomIp>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `CustomIp` SET `uid` = ?,`ipAddress` = ?,`port` = ?,`protocol` = ?,`isActive` = ?,`proxyId` = ?,`proxyCC` = ?,`status` = ?,`wildcard` = ?,`ruleType` = ?,`modifiedDateTime` = ? WHERE `uid` = ? AND `ipAddress` = ? AND `port` = ? AND `protocol` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CustomIp) {
        statement.bindLong(1, entity.uid.toLong())
        statement.bindText(2, entity.ipAddress)
        statement.bindLong(3, entity.port.toLong())
        statement.bindText(4, entity.protocol)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        statement.bindText(6, entity.proxyId)
        statement.bindText(7, entity.proxyCC)
        statement.bindLong(8, entity.status.toLong())
        val _tmp_1: Int = if (entity.wildcard) 1 else 0
        statement.bindLong(9, _tmp_1.toLong())
        statement.bindLong(10, entity.ruleType.toLong())
        statement.bindLong(11, entity.modifiedDateTime)
        statement.bindLong(12, entity.uid.toLong())
        statement.bindText(13, entity.ipAddress)
        statement.bindLong(14, entity.port.toLong())
        statement.bindText(15, entity.protocol)
      }
    }
  }

  public override fun insert(customIp: CustomIp): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfCustomIp.insert(_connection, customIp)
  }

  public override fun delete(customIp: CustomIp): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfCustomIp.handle(_connection, customIp)
  }

  public override fun deleteAll(customIp: List<CustomIp>): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfCustomIp.handleMultiple(_connection, customIp)
  }

  public override fun update(customIp: CustomIp): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfCustomIp.handle(_connection, customIp)
  }

  public override fun updateUid(uid: Int, newUid: Int): Unit = performBlocking(__db, false, true) { _ ->
    super@CustomIpDao_Impl.updateUid(uid, newUid)
  }

  public override fun tombstoneRulesByUid(oldUid: Int, newUid: Int): Unit = performBlocking(__db, false, true) { _ ->
    super@CustomIpDao_Impl.tombstoneRulesByUid(oldUid, newUid)
  }

  public override fun getCustomIpRules(): List<CustomIp> {
    val _sql: String = "select * from CustomIp order by uid"
    return performBlocking(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWildcard: Int = getColumnIndexOrThrow(_stmt, "wildcard")
        val _columnIndexOfRuleType: Int = getColumnIndexOrThrow(_stmt, "ruleType")
        val _columnIndexOfModifiedDateTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDateTime")
        val _result: MutableList<CustomIp> = mutableListOf()
        while (_stmt.step()) {
          val _item: CustomIp
          _item = CustomIp()
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
          _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
          _item.protocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _item.isActive = _tmp != 0
          _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
          _item.proxyCC = _stmt.getText(_columnIndexOfProxyCC)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWildcard).toInt()
          _item.wildcard = _tmp_1 != 0
          _item.ruleType = _stmt.getLong(_columnIndexOfRuleType).toInt()
          _item.modifiedDateTime = _stmt.getLong(_columnIndexOfModifiedDateTime)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCustomIpDetail(
    uid: Int,
    ipAddress: String,
    port: Int,
  ): CustomIp? {
    val _sql: String = "select * from CustomIp where ipAddress = ? and uid = ? and port = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, ipAddress)
        _argIndex = 2
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, port.toLong())
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWildcard: Int = getColumnIndexOrThrow(_stmt, "wildcard")
        val _columnIndexOfRuleType: Int = getColumnIndexOrThrow(_stmt, "ruleType")
        val _columnIndexOfModifiedDateTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDateTime")
        val _result: CustomIp?
        if (_stmt.step()) {
          _result = CustomIp()
          _result.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _result.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
          _result.port = _stmt.getLong(_columnIndexOfPort).toInt()
          _result.protocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _result.isActive = _tmp != 0
          _result.proxyId = _stmt.getText(_columnIndexOfProxyId)
          _result.proxyCC = _stmt.getText(_columnIndexOfProxyCC)
          _result.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWildcard).toInt()
          _result.wildcard = _tmp_1 != 0
          _result.ruleType = _stmt.getLong(_columnIndexOfRuleType).toInt()
          _result.modifiedDateTime = _stmt.getLong(_columnIndexOfModifiedDateTime)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getBlockedConnectionsByUID(uid: Int): List<CustomIp> {
    val _sql: String = "select uid,* from CustomIp where uid = ? and isActive = 1"
    return performBlocking(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWildcard: Int = getColumnIndexOrThrow(_stmt, "wildcard")
        val _columnIndexOfRuleType: Int = getColumnIndexOrThrow(_stmt, "ruleType")
        val _columnIndexOfModifiedDateTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDateTime")
        val _result: MutableList<CustomIp> = mutableListOf()
        while (_stmt.step()) {
          val _item: CustomIp
          _item = CustomIp()
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
          _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
          _item.protocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _item.isActive = _tmp != 0
          _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
          _item.proxyCC = _stmt.getText(_columnIndexOfProxyCC)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWildcard).toInt()
          _item.wildcard = _tmp_1 != 0
          _item.ruleType = _stmt.getLong(_columnIndexOfRuleType).toInt()
          _item.modifiedDateTime = _stmt.getLong(_columnIndexOfModifiedDateTime)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getUnivBlockedConnectionsLiveData(): PagingSource<Int, CustomIp> {
    val _sql: String = "select * from CustomIp where isActive = 1 and uid = -1000 order by modifiedDateTime desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<CustomIp>(_rawQuery, __db, "CustomIp") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<CustomIp> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfWildcard: Int = getColumnIndexOrThrow(_stmt, "wildcard")
          val _columnIndexOfRuleType: Int = getColumnIndexOrThrow(_stmt, "ruleType")
          val _columnIndexOfModifiedDateTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDateTime")
          val _result: MutableList<CustomIp> = mutableListOf()
          while (_stmt.step()) {
            val _item: CustomIp
            _item = CustomIp()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getText(_columnIndexOfProtocol)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _item.isActive = _tmp != 0
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.proxyCC = _stmt.getText(_columnIndexOfProxyCC)
            _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfWildcard).toInt()
            _item.wildcard = _tmp_1 != 0
            _item.ruleType = _stmt.getLong(_columnIndexOfRuleType).toInt()
            _item.modifiedDateTime = _stmt.getLong(_columnIndexOfModifiedDateTime)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getUnivBlockedConnectionsByIP(query: String): PagingSource<Int, CustomIp> {
    val _sql: String = "select * from CustomIp where ipAddress like ? and uid = -1000 and  isActive = 1 order by modifiedDateTime desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<CustomIp>(_rawQuery, __db, "CustomIp") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<CustomIp> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfWildcard: Int = getColumnIndexOrThrow(_stmt, "wildcard")
          val _columnIndexOfRuleType: Int = getColumnIndexOrThrow(_stmt, "ruleType")
          val _columnIndexOfModifiedDateTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDateTime")
          val _result: MutableList<CustomIp> = mutableListOf()
          while (_stmt.step()) {
            val _item: CustomIp
            _item = CustomIp()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getText(_columnIndexOfProtocol)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _item.isActive = _tmp != 0
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.proxyCC = _stmt.getText(_columnIndexOfProxyCC)
            _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfWildcard).toInt()
            _item.wildcard = _tmp_1 != 0
            _item.ruleType = _stmt.getLong(_columnIndexOfRuleType).toInt()
            _item.modifiedDateTime = _stmt.getLong(_columnIndexOfModifiedDateTime)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getRulesByUid(uid: Int): List<CustomIp> {
    val _sql: String = "select * from CustomIp where uid = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
        val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
        val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWildcard: Int = getColumnIndexOrThrow(_stmt, "wildcard")
        val _columnIndexOfRuleType: Int = getColumnIndexOrThrow(_stmt, "ruleType")
        val _columnIndexOfModifiedDateTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDateTime")
        val _result: MutableList<CustomIp> = mutableListOf()
        while (_stmt.step()) {
          val _item: CustomIp
          _item = CustomIp()
          _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
          _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
          _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
          _item.protocol = _stmt.getText(_columnIndexOfProtocol)
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _item.isActive = _tmp != 0
          _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
          _item.proxyCC = _stmt.getText(_columnIndexOfProxyCC)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfWildcard).toInt()
          _item.wildcard = _tmp_1 != 0
          _item.ruleType = _stmt.getLong(_columnIndexOfRuleType).toInt()
          _item.modifiedDateTime = _stmt.getLong(_columnIndexOfModifiedDateTime)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getBlockedConnectionsCount(): Int {
    val _sql: String = "select count(*) from CustomIp where uid = -1000 and isActive = 1"
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

  public override fun getCustomIpsLiveData(): LiveData<Int> {
    val _sql: String = "select count(*) from CustomIp where uid = -1000 and isActive = 1"
    return __db.invalidationTracker.createLiveData(arrayOf("CustomIp"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int?
        if (_stmt.step()) {
          val _tmp: Int?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0).toInt()
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAppWiseIpRulesCount(uid: Int): LiveData<Int> {
    val _sql: String = "select count(*) from CustomIp where uid = ? and isActive = 1"
    return __db.invalidationTracker.createLiveData(arrayOf("CustomIp"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        val _result: Int?
        if (_stmt.step()) {
          val _tmp: Int?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0).toInt()
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getIpRulesCountInt(): LiveData<Int> {
    val _sql: String = "select count(*) from CustomIp where isActive = 1 and uid != -1000"
    return __db.invalidationTracker.createLiveData(arrayOf("CustomIp"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int?
        if (_stmt.step()) {
          val _tmp: Int?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0).toInt()
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAppWiseCustomIp(uid: Int): PagingSource<Int, CustomIp> {
    val _sql: String = "select * from CustomIp where uid = ? and isActive = 1 order by modifiedDateTime desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<CustomIp>(_rawQuery, __db, "CustomIp") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<CustomIp> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfWildcard: Int = getColumnIndexOrThrow(_stmt, "wildcard")
          val _columnIndexOfRuleType: Int = getColumnIndexOrThrow(_stmt, "ruleType")
          val _columnIndexOfModifiedDateTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDateTime")
          val _result: MutableList<CustomIp> = mutableListOf()
          while (_stmt.step()) {
            val _item: CustomIp
            _item = CustomIp()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getText(_columnIndexOfProtocol)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _item.isActive = _tmp != 0
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.proxyCC = _stmt.getText(_columnIndexOfProxyCC)
            _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfWildcard).toInt()
            _item.wildcard = _tmp_1 != 0
            _item.ruleType = _stmt.getLong(_columnIndexOfRuleType).toInt()
            _item.modifiedDateTime = _stmt.getLong(_columnIndexOfModifiedDateTime)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAppWiseCustomIp(query: String, uid: Int): PagingSource<Int, CustomIp> {
    val _sql: String = "select * from CustomIp where ipAddress like ? and uid = ? and  isActive = 1 order by modifiedDateTime desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2
      _stmt.bindLong(_argIndex, uid.toLong())
    }
    return object : LimitOffsetPagingSource<CustomIp>(_rawQuery, __db, "CustomIp") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<CustomIp> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIpAddress: Int = getColumnIndexOrThrow(_stmt, "ipAddress")
          val _columnIndexOfPort: Int = getColumnIndexOrThrow(_stmt, "port")
          val _columnIndexOfProtocol: Int = getColumnIndexOrThrow(_stmt, "protocol")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfWildcard: Int = getColumnIndexOrThrow(_stmt, "wildcard")
          val _columnIndexOfRuleType: Int = getColumnIndexOrThrow(_stmt, "ruleType")
          val _columnIndexOfModifiedDateTime: Int = getColumnIndexOrThrow(_stmt, "modifiedDateTime")
          val _result: MutableList<CustomIp> = mutableListOf()
          while (_stmt.step()) {
            val _item: CustomIp
            _item = CustomIp()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getText(_columnIndexOfProtocol)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _item.isActive = _tmp != 0
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.proxyCC = _stmt.getText(_columnIndexOfProxyCC)
            _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfWildcard).toInt()
            _item.wildcard = _tmp_1 != 0
            _item.ruleType = _stmt.getLong(_columnIndexOfRuleType).toInt()
            _item.modifiedDateTime = _stmt.getLong(_columnIndexOfModifiedDateTime)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAllCustomIpRules(query: String): PagingSource<Int, CustomIp> {
    val _sql: String = "SELECT `uid`, `ipAddress`, `port`, `protocol`, `isActive`, `proxyId`, `proxyCC`, `status`, `wildcard`, `ruleType`, `modifiedDateTime` FROM (SELECT * FROM (SELECT *, (SELECT COUNT(*) FROM CustomIp ci2 WHERE ci2.uid = ci1.uid AND ci2.rowid <= ci1.rowid) row_num FROM CustomIp ci1 WHERE ipAddress LIKE ? AND isActive = 1 AND uid != -1000) WHERE row_num <= 5 ORDER BY uid, row_num)"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<CustomIp>(_rawQuery, __db, "CustomIp") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<CustomIp> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = 0
          val _columnIndexOfIpAddress: Int = 1
          val _columnIndexOfPort: Int = 2
          val _columnIndexOfProtocol: Int = 3
          val _columnIndexOfIsActive: Int = 4
          val _columnIndexOfProxyId: Int = 5
          val _columnIndexOfProxyCC: Int = 6
          val _columnIndexOfStatus: Int = 7
          val _columnIndexOfWildcard: Int = 8
          val _columnIndexOfRuleType: Int = 9
          val _columnIndexOfModifiedDateTime: Int = 10
          val _result: MutableList<CustomIp> = mutableListOf()
          while (_stmt.step()) {
            val _item: CustomIp
            _item = CustomIp()
            _item.uid = _stmt.getLong(_columnIndexOfUid).toInt()
            _item.ipAddress = _stmt.getText(_columnIndexOfIpAddress)
            _item.port = _stmt.getLong(_columnIndexOfPort).toInt()
            _item.protocol = _stmt.getText(_columnIndexOfProtocol)
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _item.isActive = _tmp != 0
            _item.proxyId = _stmt.getText(_columnIndexOfProxyId)
            _item.proxyCC = _stmt.getText(_columnIndexOfProxyCC)
            _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfWildcard).toInt()
            _item.wildcard = _tmp_1 != 0
            _item.ruleType = _stmt.getLong(_columnIndexOfRuleType).toInt()
            _item.modifiedDateTime = _stmt.getLong(_columnIndexOfModifiedDateTime)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getRulesCount(): Int {
    val _sql: String = "select count(*) from CustomIp"
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

  public override fun getRulesCountByCC(cc: String): Int {
    val _sql: String = "select count(*) from CustomIp where proxyCC = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, cc)
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

  public override fun clearIpRuleByUid(uid: Int) {
    val _sql: String = "delete from CustomIp where uid = ?"
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

  public override fun deleteIPRulesUniversal(ipAddress: String, port: Int) {
    val _sql: String = "delete from CustomIp where ipAddress = ? and uid = -1000 and port = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, ipAddress)
        _argIndex = 2
        _stmt.bindLong(_argIndex, port.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteRule(
    uid: Int,
    ipAddress: String,
    port: Int,
  ): Int {
    val _sql: String = "delete from CustomIp where ipAddress = ? and uid = ? and port = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, ipAddress)
        _argIndex = 2
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, port.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteRulesByUid(uid: Int) {
    val _sql: String = "delete from CustomIp where uid = ?"
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

  public override fun deleteAllIPRulesUniversal() {
    val _sql: String = "delete from CustomIp where uid = -1000"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun insertOrReplaceWithNewUid(oldUid: Int, newUid: Int) {
    val _sql: String = """
        |
        |        INSERT OR REPLACE INTO CustomIp (uid, ipAddress, port, protocol, isActive, status, ruleType, wildcard, proxyId, proxyCC, modifiedDateTime)
        |        SELECT ?, ipAddress, port, protocol, isActive, status, ruleType, wildcard, proxyId, proxyCC, modifiedDateTime 
        |        FROM CustomIp WHERE uid = ?
        |    
        """.trimMargin()
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newUid.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, oldUid.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteAllAppsRules() {
    val _sql: String = "delete from CustomIp where uid != -1000"
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
