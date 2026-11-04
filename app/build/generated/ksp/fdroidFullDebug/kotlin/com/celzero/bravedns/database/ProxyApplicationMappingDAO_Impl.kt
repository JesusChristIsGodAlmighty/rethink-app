package com.celzero.bravedns.database

import androidx.lifecycle.LiveData
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
import kotlin.LongArray
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ProxyApplicationMappingDAO_Impl(
  __db: RoomDatabase,
) : ProxyApplicationMappingDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfProxyApplicationMapping: EntityInsertAdapter<ProxyApplicationMapping>

  private val __deleteAdapterOfProxyApplicationMapping:
      EntityDeleteOrUpdateAdapter<ProxyApplicationMapping>

  private val __updateAdapterOfProxyApplicationMapping:
      EntityDeleteOrUpdateAdapter<ProxyApplicationMapping>
  init {
    this.__db = __db
    this.__insertAdapterOfProxyApplicationMapping = object : EntityInsertAdapter<ProxyApplicationMapping>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `ProxyApplicationMapping` (`uid`,`packageName`,`proxyId`,`appName`,`proxyName`,`isActive`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ProxyApplicationMapping) {
        statement.bindLong(1, entity.uid.toLong())
        statement.bindText(2, entity.packageName)
        statement.bindText(3, entity.proxyId)
        statement.bindText(4, entity.appName)
        statement.bindText(5, entity.proxyName)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(6, _tmp.toLong())
      }
    }
    this.__deleteAdapterOfProxyApplicationMapping = object : EntityDeleteOrUpdateAdapter<ProxyApplicationMapping>() {
      protected override fun createQuery(): String = "DELETE FROM `ProxyApplicationMapping` WHERE `uid` = ? AND `packageName` = ? AND `proxyId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProxyApplicationMapping) {
        statement.bindLong(1, entity.uid.toLong())
        statement.bindText(2, entity.packageName)
        statement.bindText(3, entity.proxyId)
      }
    }
    this.__updateAdapterOfProxyApplicationMapping = object : EntityDeleteOrUpdateAdapter<ProxyApplicationMapping>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `ProxyApplicationMapping` SET `uid` = ?,`packageName` = ?,`proxyId` = ?,`appName` = ?,`proxyName` = ?,`isActive` = ? WHERE `uid` = ? AND `packageName` = ? AND `proxyId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProxyApplicationMapping) {
        statement.bindLong(1, entity.uid.toLong())
        statement.bindText(2, entity.packageName)
        statement.bindText(3, entity.proxyId)
        statement.bindText(4, entity.appName)
        statement.bindText(5, entity.proxyName)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.uid.toLong())
        statement.bindText(8, entity.packageName)
        statement.bindText(9, entity.proxyId)
      }
    }
  }

  public override fun insert(wgMapping: ProxyApplicationMapping): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfProxyApplicationMapping.insertAndReturnId(_connection, wgMapping)
    _result
  }

  public override fun insertAll(wgMapping: List<ProxyApplicationMapping>): LongArray = performBlocking(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfProxyApplicationMapping.insertAndReturnIdsArray(_connection, wgMapping)
    _result
  }

  public override fun delete(wgMapping: ProxyApplicationMapping): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfProxyApplicationMapping.handle(_connection, wgMapping)
  }

  public override fun update(wgMapping: ProxyApplicationMapping): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfProxyApplicationMapping.handle(_connection, wgMapping)
  }

  public override fun updateUidForApp(
    oldUid: Int,
    newUid: Int,
    packageName: String,
  ): Unit = performBlocking(__db, false, true) { _ ->
    super@ProxyApplicationMappingDAO_Impl.updateUidForApp(oldUid, newUid, packageName)
  }

  public override fun tombstoneApp(oldUid: Int, newUid: Int): Unit = performBlocking(__db, false, true) { _ ->
    super@ProxyApplicationMappingDAO_Impl.tombstoneApp(oldUid, newUid)
  }

  public override fun getWgAppMapping(): List<ProxyApplicationMapping> {
    val _sql: String = "select * from ProxyApplicationMapping"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: MutableList<ProxyApplicationMapping> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProxyApplicationMapping
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpProxyId: String
          _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _item = ProxyApplicationMapping(_tmpUid,_tmpPackageName,_tmpAppName,_tmpProxyName,_tmpIsActive,_tmpProxyId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllAppsMapping(appName: String, proxyId: String): PagingSource<Int, ProxyApplicationMapping> {
    val _sql: String = "SELECT * FROM ProxyApplicationMapping WHERE rowid IN ( SELECT MIN(rowid) FROM ProxyApplicationMapping WHERE appName LIKE ? AND (proxyId = ? or proxyId = '') GROUP BY uid, packageName) ORDER BY lower(appName)"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, appName)
      _argIndex = 2
      _stmt.bindText(_argIndex, proxyId)
    }
    return object : LimitOffsetPagingSource<ProxyApplicationMapping>(_rawQuery, __db, "ProxyApplicationMapping") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ProxyApplicationMapping> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _result: MutableList<ProxyApplicationMapping> = mutableListOf()
          while (_stmt.step()) {
            val _item: ProxyApplicationMapping
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpProxyId: String
            _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpProxyName: String
            _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
            val _tmpIsActive: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _tmpIsActive = _tmp != 0
            _item = ProxyApplicationMapping(_tmpUid,_tmpPackageName,_tmpAppName,_tmpProxyName,_tmpIsActive,_tmpProxyId)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getSelectedAppsMapping(appName: String, proxyId: String): PagingSource<Int, ProxyApplicationMapping> {
    val _sql: String = "SELECT * FROM ProxyApplicationMapping WHERE rowid IN (SELECT MIN(rowid) FROM ProxyApplicationMapping WHERE appName LIKE ? AND proxyId = ? GROUP BY uid, packageName) ORDER BY lower(appName)"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, appName)
      _argIndex = 2
      _stmt.bindText(_argIndex, proxyId)
    }
    return object : LimitOffsetPagingSource<ProxyApplicationMapping>(_rawQuery, __db, "ProxyApplicationMapping") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ProxyApplicationMapping> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _result: MutableList<ProxyApplicationMapping> = mutableListOf()
          while (_stmt.step()) {
            val _item: ProxyApplicationMapping
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpProxyId: String
            _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpProxyName: String
            _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
            val _tmpIsActive: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _tmpIsActive = _tmp != 0
            _item = ProxyApplicationMapping(_tmpUid,_tmpPackageName,_tmpAppName,_tmpProxyName,_tmpIsActive,_tmpProxyId)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getUnSelectedAppsMapping(appName: String, proxyId: String): PagingSource<Int, ProxyApplicationMapping> {
    val _sql: String = "SELECT * FROM ProxyApplicationMapping WHERE rowid IN ( SELECT MIN(rowid) FROM ProxyApplicationMapping WHERE appName LIKE ? AND uid NOT IN (SELECT uid FROM ProxyApplicationMapping WHERE proxyId = ?) GROUP BY uid, packageName) ORDER BY lower(appName)"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, appName)
      _argIndex = 2
      _stmt.bindText(_argIndex, proxyId)
    }
    return object : LimitOffsetPagingSource<ProxyApplicationMapping>(_rawQuery, __db, "ProxyApplicationMapping") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<ProxyApplicationMapping> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _result: MutableList<ProxyApplicationMapping> = mutableListOf()
          while (_stmt.step()) {
            val _item: ProxyApplicationMapping
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpProxyId: String
            _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpProxyName: String
            _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
            val _tmpIsActive: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _tmpIsActive = _tmp != 0
            _item = ProxyApplicationMapping(_tmpUid,_tmpPackageName,_tmpAppName,_tmpProxyName,_tmpIsActive,_tmpProxyId)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAppCountById(id: String): Int {
    val _sql: String = "select count(packageName) from ProxyApplicationMapping where proxyId = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
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

  public override fun getAppCountByIdLiveData(id: String): LiveData<Int> {
    val _sql: String = "select count(packageName) from ProxyApplicationMapping where proxyId = ?"
    return __db.invalidationTracker.createLiveData(arrayOf("ProxyApplicationMapping"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
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

  public override fun getSelectedAppsCountLiveData(id: String): LiveData<Int> {
    val _sql: String = "select count(packageName) from ProxyApplicationMapping where proxyId = ?"
    return __db.invalidationTracker.createLiveData(arrayOf("ProxyApplicationMapping"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
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

  public override fun getUnselectedAppsCountLiveData(id: String): LiveData<Int> {
    val _sql: String = "select count(packageName) from ProxyApplicationMapping where proxyId != ?"
    return __db.invalidationTracker.createLiveData(arrayOf("ProxyApplicationMapping"), false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
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

  public override fun getProxiesForApp(uid: Int, packageName: String): List<ProxyApplicationMapping> {
    val _sql: String = "select * from ProxyApplicationMapping where uid = ? and packageName = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, packageName)
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: MutableList<ProxyApplicationMapping> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProxyApplicationMapping
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpProxyId: String
          _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _item = ProxyApplicationMapping(_tmpUid,_tmpPackageName,_tmpAppName,_tmpProxyName,_tmpIsActive,_tmpProxyId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getProxyIdsForApp(uid: Int, packageName: String): List<String> {
    val _sql: String = "select proxyId from ProxyApplicationMapping where uid = ? and packageName = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, packageName)
        val _result: MutableList<String> = mutableListOf()
        while (_stmt.step()) {
          val _item: String
          _item = _stmt.getText(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAppsForProxy(proxyId: String): List<ProxyApplicationMapping> {
    val _sql: String = "select * from ProxyApplicationMapping where proxyId = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, proxyId)
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfProxyId: Int = getColumnIndexOrThrow(_stmt, "proxyId")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfProxyName: Int = getColumnIndexOrThrow(_stmt, "proxyName")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: MutableList<ProxyApplicationMapping> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProxyApplicationMapping
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpProxyId: String
          _tmpProxyId = _stmt.getText(_columnIndexOfProxyId)
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpProxyName: String
          _tmpProxyName = _stmt.getText(_columnIndexOfProxyName)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _item = ProxyApplicationMapping(_tmpUid,_tmpPackageName,_tmpAppName,_tmpProxyName,_tmpIsActive,_tmpProxyId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteApp(uid: Int, packageName: String) {
    val _sql: String = "delete from ProxyApplicationMapping where uid = ? and packageName = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, packageName)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteAppByPkgName(packageName: String) {
    val _sql: String = "delete from ProxyApplicationMapping where packageName = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, packageName)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteAll() {
    val _sql: String = "delete from ProxyApplicationMapping"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateProxyNameForProxyId(proxyId: String, proxyName: String) {
    val _sql: String = "update ProxyApplicationMapping set proxyName = ? where proxyId = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, proxyName)
        _argIndex = 2
        _stmt.bindText(_argIndex, proxyId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteConflictingMappingsForUidUpdate(
    newUid: Int,
    packageName: String,
    oldUid: Int,
  ) {
    val _sql: String = "delete from ProxyApplicationMapping where uid = ? and packageName = ? and proxyId in (select proxyId from ProxyApplicationMapping where uid = ? and packageName = ?)"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newUid.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, packageName)
        _argIndex = 3
        _stmt.bindLong(_argIndex, oldUid.toLong())
        _argIndex = 4
        _stmt.bindText(_argIndex, packageName)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateUidForAppInternal(
    oldUid: Int,
    newUid: Int,
    packageName: String,
  ) {
    val _sql: String = "update ProxyApplicationMapping set uid = ? where packageName = ? and uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newUid.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, packageName)
        _argIndex = 3
        _stmt.bindLong(_argIndex, oldUid.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteConflictingMappingsForTombstone(newUid: Int, oldUid: Int) {
    val _sql: String = "delete from ProxyApplicationMapping where uid = ? and exists (select 1 from ProxyApplicationMapping as pam2 where pam2.uid = ? and pam2.packageName = ProxyApplicationMapping.packageName and pam2.proxyId = ProxyApplicationMapping.proxyId)"
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

  public override fun tombstoneAppInternal(oldUid: Int, newUid: Int) {
    val _sql: String = "update ProxyApplicationMapping set uid = ? where uid = ?"
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

  public override fun deleteMapping(
    uid: Int,
    packageName: String,
    proxyId: String,
  ) {
    val _sql: String = "delete from ProxyApplicationMapping where uid = ? and packageName = ? and proxyId = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, packageName)
        _argIndex = 3
        _stmt.bindText(_argIndex, proxyId)
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
