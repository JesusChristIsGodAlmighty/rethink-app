package com.celzero.bravedns.database

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.RoomSQLiteQuery
import androidx.room.RoomSQLiteQuery.Companion.acquire
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performBlocking
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
public class CustomDomainDAO_Impl(
  __db: RoomDatabase,
) : CustomDomainDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfCustomDomain: EntityInsertAdapter<CustomDomain>

  private val __deleteAdapterOfCustomDomain: EntityDeleteOrUpdateAdapter<CustomDomain>

  private val __updateAdapterOfCustomDomain: EntityDeleteOrUpdateAdapter<CustomDomain>
  init {
    this.__db = __db
    this.__insertAdapterOfCustomDomain = object : EntityInsertAdapter<CustomDomain>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `CustomDomain` (`domain`,`uid`,`ips`,`status`,`type`,`proxyId`,`proxyCC`,`modifiedTs`,`deletedTs`,`version`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CustomDomain) {
        statement.bindText(1, entity.domain)
        statement.bindLong(2, entity.uid.toLong())
        statement.bindText(3, entity.ips)
        statement.bindLong(4, entity.status.toLong())
        statement.bindLong(5, entity.type.toLong())
        statement.bindText(6, entity.proxyId)
        statement.bindText(7, entity.proxyCC)
        statement.bindLong(8, entity.modifiedTs)
        statement.bindLong(9, entity.deletedTs)
        statement.bindLong(10, entity.version)
      }
    }
    this.__deleteAdapterOfCustomDomain = object : EntityDeleteOrUpdateAdapter<CustomDomain>() {
      protected override fun createQuery(): String = "DELETE FROM `CustomDomain` WHERE `domain` = ? AND `uid` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CustomDomain) {
        statement.bindText(1, entity.domain)
        statement.bindLong(2, entity.uid.toLong())
      }
    }
    this.__updateAdapterOfCustomDomain = object : EntityDeleteOrUpdateAdapter<CustomDomain>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `CustomDomain` SET `domain` = ?,`uid` = ?,`ips` = ?,`status` = ?,`type` = ?,`proxyId` = ?,`proxyCC` = ?,`modifiedTs` = ?,`deletedTs` = ?,`version` = ? WHERE `domain` = ? AND `uid` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CustomDomain) {
        statement.bindText(1, entity.domain)
        statement.bindLong(2, entity.uid.toLong())
        statement.bindText(3, entity.ips)
        statement.bindLong(4, entity.status.toLong())
        statement.bindLong(5, entity.type.toLong())
        statement.bindText(6, entity.proxyId)
        statement.bindText(7, entity.proxyCC)
        statement.bindLong(8, entity.modifiedTs)
        statement.bindLong(9, entity.deletedTs)
        statement.bindLong(10, entity.version)
        statement.bindText(11, entity.domain)
        statement.bindLong(12, entity.uid.toLong())
      }
    }
  }

  public override fun insert(customDomain: CustomDomain): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfCustomDomain.insertAndReturnId(_connection, customDomain)
    _result
  }

  public override fun delete(customDomain: CustomDomain): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfCustomDomain.handle(_connection, customDomain)
  }

  public override fun deleteAll(customDomains: List<CustomDomain>): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfCustomDomain.handleMultiple(_connection, customDomains)
  }

  public override fun update(customDomain: CustomDomain): Int = performBlocking(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfCustomDomain.handle(_connection, customDomain)
    _result
  }

  public override fun updateUid(uid: Int, newUid: Int): Unit = performBlocking(__db, false, true) { _ ->
    super@CustomDomainDAO_Impl.updateUid(uid, newUid)
  }

  public override fun getAllDomains(): List<CustomDomain> {
    val _sql: String = "select * from CustomDomain order by modifiedTs desc"
    return performBlocking(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfDomain: Int = getColumnIndexOrThrow(_stmt, "domain")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIps: Int = getColumnIndexOrThrow(_stmt, "ips")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfDeletedTs: Int = getColumnIndexOrThrow(_stmt, "deletedTs")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _result: MutableList<CustomDomain> = mutableListOf()
        while (_stmt.step()) {
          val _item: CustomDomain
          val _tmpDomain: String
          _tmpDomain = _stmt.getText(_columnIndexOfDomain)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpIps: String
          _tmpIps = _stmt.getText(_columnIndexOfIps)
          val _tmpStatus: Int
          _tmpStatus = _stmt.getLong(_columnIndexOfStatus).toInt()
          val _tmpType: Int
          _tmpType = _stmt.getLong(_columnIndexOfType).toInt()
          val _tmpProxyId: String
          _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
          val _tmpProxyCC: String
          _tmpProxyCC = _stmt.getText(_columnIndexOfProxyCC)
          val _tmpModifiedTs: Long
          _tmpModifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmpDeletedTs: Long
          _tmpDeletedTs = _stmt.getLong(_columnIndexOfDeletedTs)
          val _tmpVersion: Long
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
          _item = CustomDomain(_tmpDomain,_tmpUid,_tmpIps,_tmpType,_tmpStatus,_tmpProxyId,_tmpProxyCC,_tmpModifiedTs,_tmpDeletedTs,_tmpVersion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getDomainsLiveData(uid: Int, query: String): PagingSource<Int, CustomDomain> {
    val _sql: String = "select * from CustomDomain where uid = ? and domain like ? order by modifiedTs desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, uid.toLong())
      _argIndex = 2
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<CustomDomain>(_rawQuery, __db, "CustomDomain") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<CustomDomain> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfDomain: Int = getColumnIndexOrThrow(_stmt, "domain")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIps: Int = getColumnIndexOrThrow(_stmt, "ips")
          val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
          val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
          val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
          val _columnIndexOfDeletedTs: Int = getColumnIndexOrThrow(_stmt, "deletedTs")
          val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
          val _result: MutableList<CustomDomain> = mutableListOf()
          while (_stmt.step()) {
            val _item: CustomDomain
            val _tmpDomain: String
            _tmpDomain = _stmt.getText(_columnIndexOfDomain)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIps: String
            _tmpIps = _stmt.getText(_columnIndexOfIps)
            val _tmpStatus: Int
            _tmpStatus = _stmt.getLong(_columnIndexOfStatus).toInt()
            val _tmpType: Int
            _tmpType = _stmt.getLong(_columnIndexOfType).toInt()
            val _tmpProxyId: String
            _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
            val _tmpProxyCC: String
            _tmpProxyCC = _stmt.getText(_columnIndexOfProxyCC)
            val _tmpModifiedTs: Long
            _tmpModifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            val _tmpDeletedTs: Long
            _tmpDeletedTs = _stmt.getLong(_columnIndexOfDeletedTs)
            val _tmpVersion: Long
            _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
            _item = CustomDomain(_tmpDomain,_tmpUid,_tmpIps,_tmpType,_tmpStatus,_tmpProxyId,_tmpProxyCC,_tmpModifiedTs,_tmpDeletedTs,_tmpVersion)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAppWiseDomainRulesCount(uid: Int): LiveData<Int> {
    val _sql: String = "select count(*) from CustomDomain where uid = ?"
    return __db.invalidationTracker.createLiveData(arrayOf("CustomDomain"), false) { _connection ->
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

  public override fun getDomainsByUID(uid: Int): List<CustomDomain> {
    val _sql: String = "select * from CustomDomain where uid = ? order by modifiedTs desc"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        val _columnIndexOfDomain: Int = getColumnIndexOrThrow(_stmt, "domain")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIps: Int = getColumnIndexOrThrow(_stmt, "ips")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfDeletedTs: Int = getColumnIndexOrThrow(_stmt, "deletedTs")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _result: MutableList<CustomDomain> = mutableListOf()
        while (_stmt.step()) {
          val _item: CustomDomain
          val _tmpDomain: String
          _tmpDomain = _stmt.getText(_columnIndexOfDomain)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpIps: String
          _tmpIps = _stmt.getText(_columnIndexOfIps)
          val _tmpStatus: Int
          _tmpStatus = _stmt.getLong(_columnIndexOfStatus).toInt()
          val _tmpType: Int
          _tmpType = _stmt.getLong(_columnIndexOfType).toInt()
          val _tmpProxyId: String
          _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
          val _tmpProxyCC: String
          _tmpProxyCC = _stmt.getText(_columnIndexOfProxyCC)
          val _tmpModifiedTs: Long
          _tmpModifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmpDeletedTs: Long
          _tmpDeletedTs = _stmt.getLong(_columnIndexOfDeletedTs)
          val _tmpVersion: Long
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
          _item = CustomDomain(_tmpDomain,_tmpUid,_tmpIps,_tmpType,_tmpStatus,_tmpProxyId,_tmpProxyCC,_tmpModifiedTs,_tmpDeletedTs,_tmpVersion)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllDomainRulesCount(): LiveData<Int> {
    val _sql: String = "select count(*) from CustomDomain where uid != -1000"
    return __db.invalidationTracker.createLiveData(arrayOf("CustomDomain"), false) { _connection ->
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

  public override fun getRulesCursor(): Cursor {
    val _sql: String = "select * from CustomDomain where status in (1,2) order by modifiedTs desc"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    val _tmpResult: Cursor = __db.query(_statement)
    return _tmpResult
  }

  public override fun getAllDomainRules(query: String): PagingSource<Int, CustomDomain> {
    val _sql: String = "SELECT `domain`, `uid`, `ips`, `status`, `type`, `proxyId`, `proxyCC`, `modifiedTs`, `deletedTs`, `version` FROM (SELECT * FROM (SELECT *, (SELECT COUNT(*) FROM CustomDomain cd2 WHERE cd2.uid = cd1.uid AND cd2.rowid <= cd1.rowid) row_num FROM CustomDomain cd1 WHERE uid != -1000 AND domain LIKE ?) WHERE row_num <= 5 ORDER BY uid, row_num)"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<CustomDomain>(_rawQuery, __db, "CustomDomain") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<CustomDomain> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfDomain: Int = 0
          val _columnIndexOfUid: Int = 1
          val _columnIndexOfIps: Int = 2
          val _columnIndexOfStatus: Int = 3
          val _columnIndexOfType: Int = 4
          val _columnIndexOfProxyId: Int = 5
          val _columnIndexOfProxyCC: Int = 6
          val _columnIndexOfModifiedTs: Int = 7
          val _columnIndexOfDeletedTs: Int = 8
          val _columnIndexOfVersion: Int = 9
          val _result: MutableList<CustomDomain> = mutableListOf()
          while (_stmt.step()) {
            val _item: CustomDomain
            val _tmpDomain: String
            _tmpDomain = _stmt.getText(_columnIndexOfDomain)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIps: String
            _tmpIps = _stmt.getText(_columnIndexOfIps)
            val _tmpStatus: Int
            _tmpStatus = _stmt.getLong(_columnIndexOfStatus).toInt()
            val _tmpType: Int
            _tmpType = _stmt.getLong(_columnIndexOfType).toInt()
            val _tmpProxyId: String
            _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
            val _tmpProxyCC: String
            _tmpProxyCC = _stmt.getText(_columnIndexOfProxyCC)
            val _tmpModifiedTs: Long
            _tmpModifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            val _tmpDeletedTs: Long
            _tmpDeletedTs = _stmt.getLong(_columnIndexOfDeletedTs)
            val _tmpVersion: Long
            _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
            _item = CustomDomain(_tmpDomain,_tmpUid,_tmpIps,_tmpType,_tmpStatus,_tmpProxyId,_tmpProxyCC,_tmpModifiedTs,_tmpDeletedTs,_tmpVersion)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getCustomDomain(uid: Int, domain: String): CustomDomain? {
    val _sql: String = "SELECT * FROM CustomDomain WHERE uid = ? AND domain = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, domain)
        val _columnIndexOfDomain: Int = getColumnIndexOrThrow(_stmt, "domain")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIps: Int = getColumnIndexOrThrow(_stmt, "ips")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfProxyCC: Int = getColumnIndexOrThrow(_stmt, "proxyCC")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfDeletedTs: Int = getColumnIndexOrThrow(_stmt, "deletedTs")
        val _columnIndexOfVersion: Int = getColumnIndexOrThrow(_stmt, "version")
        val _result: CustomDomain?
        if (_stmt.step()) {
          val _tmpDomain: String
          _tmpDomain = _stmt.getText(_columnIndexOfDomain)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpIps: String
          _tmpIps = _stmt.getText(_columnIndexOfIps)
          val _tmpStatus: Int
          _tmpStatus = _stmt.getLong(_columnIndexOfStatus).toInt()
          val _tmpType: Int
          _tmpType = _stmt.getLong(_columnIndexOfType).toInt()
          val _tmpProxyId: String
          _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
          val _tmpProxyCC: String
          _tmpProxyCC = _stmt.getText(_columnIndexOfProxyCC)
          val _tmpModifiedTs: Long
          _tmpModifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmpDeletedTs: Long
          _tmpDeletedTs = _stmt.getLong(_columnIndexOfDeletedTs)
          val _tmpVersion: Long
          _tmpVersion = _stmt.getLong(_columnIndexOfVersion)
          _result = CustomDomain(_tmpDomain,_tmpUid,_tmpIps,_tmpType,_tmpStatus,_tmpProxyId,_tmpProxyCC,_tmpModifiedTs,_tmpDeletedTs,_tmpVersion)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCustomDomainCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM CustomDomain"
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
    val _sql: String = "SELECT COUNT(*) FROM CustomDomain WHERE proxyCC = ?"
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

  public override fun insertOrReplaceWithNewUid(oldUid: Int, newUid: Int) {
    val _sql: String = """
        |
        |        INSERT OR REPLACE INTO CustomDomain (domain, uid, ips, status, type, proxyId, proxyCC, modifiedTs, deletedTs, version)
        |        SELECT domain, ?, ips, status, type, proxyId, proxyCC, modifiedTs, deletedTs, version 
        |        FROM CustomDomain WHERE uid = ?
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

  public override fun deleteRulesByUid(uid: Int) {
    val _sql: String = "delete from CustomDomain where uid = ?"
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

  public override fun deleteAllRules() {
    val _sql: String = "delete from CustomDomain"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteDomain(domain: String, uid: Int): Int {
    val _sql: String = "delete from CustomDomain where domain = ? and uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, domain)
        _argIndex = 2
        _stmt.bindLong(_argIndex, uid.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun cpUpdate(status: Int, clause: String): Int {
    val _sql: String = "update CustomDomain set status = ? where ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, status.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, clause)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
