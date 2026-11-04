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
public class WgConfigFilesDAO_Impl(
  __db: RoomDatabase,
) : WgConfigFilesDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfWgConfigFiles: EntityInsertAdapter<WgConfigFiles>

  private val __deleteAdapterOfWgConfigFiles: EntityDeleteOrUpdateAdapter<WgConfigFiles>

  private val __updateAdapterOfWgConfigFiles: EntityDeleteOrUpdateAdapter<WgConfigFiles>
  init {
    this.__db = __db
    this.__insertAdapterOfWgConfigFiles = object : EntityInsertAdapter<WgConfigFiles>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `WgConfigFiles` (`id`,`name`,`configPath`,`serverResponse`,`isActive`,`isCatchAll`,`isLockdown`,`oneWireGuard`,`useOnlyOnMetered`,`isDeletable`,`ssidEnabled`,`ssids`,`modifiedTs`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: WgConfigFiles) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.configPath)
        statement.bindText(4, entity.serverResponse)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCatchAll) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isLockdown) 1 else 0
        statement.bindLong(7, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.oneWireGuard) 1 else 0
        statement.bindLong(8, _tmp_3.toLong())
        val _tmp_4: Int = if (entity.useOnlyOnMetered) 1 else 0
        statement.bindLong(9, _tmp_4.toLong())
        val _tmp_5: Int = if (entity.isDeletable) 1 else 0
        statement.bindLong(10, _tmp_5.toLong())
        val _tmp_6: Int = if (entity.ssidEnabled) 1 else 0
        statement.bindLong(11, _tmp_6.toLong())
        statement.bindText(12, entity.ssids)
        statement.bindLong(13, entity.modifiedTs)
      }
    }
    this.__deleteAdapterOfWgConfigFiles = object : EntityDeleteOrUpdateAdapter<WgConfigFiles>() {
      protected override fun createQuery(): String = "DELETE FROM `WgConfigFiles` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: WgConfigFiles) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfWgConfigFiles = object : EntityDeleteOrUpdateAdapter<WgConfigFiles>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `WgConfigFiles` SET `id` = ?,`name` = ?,`configPath` = ?,`serverResponse` = ?,`isActive` = ?,`isCatchAll` = ?,`isLockdown` = ?,`oneWireGuard` = ?,`useOnlyOnMetered` = ?,`isDeletable` = ?,`ssidEnabled` = ?,`ssids` = ?,`modifiedTs` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: WgConfigFiles) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.configPath)
        statement.bindText(4, entity.serverResponse)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isCatchAll) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isLockdown) 1 else 0
        statement.bindLong(7, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.oneWireGuard) 1 else 0
        statement.bindLong(8, _tmp_3.toLong())
        val _tmp_4: Int = if (entity.useOnlyOnMetered) 1 else 0
        statement.bindLong(9, _tmp_4.toLong())
        val _tmp_5: Int = if (entity.isDeletable) 1 else 0
        statement.bindLong(10, _tmp_5.toLong())
        val _tmp_6: Int = if (entity.ssidEnabled) 1 else 0
        statement.bindLong(11, _tmp_6.toLong())
        statement.bindText(12, entity.ssids)
        statement.bindLong(13, entity.modifiedTs)
        statement.bindLong(14, entity.id.toLong())
      }
    }
  }

  public override fun insertAll(wgConfigFiles: List<WgConfigFiles>): LongArray = performBlocking(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfWgConfigFiles.insertAndReturnIdsArray(_connection, wgConfigFiles)
    _result
  }

  public override fun insert(wgConfigFiles: WgConfigFiles): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfWgConfigFiles.insertAndReturnId(_connection, wgConfigFiles)
    _result
  }

  public override fun delete(wgConfigFiles: WgConfigFiles): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfWgConfigFiles.handle(_connection, wgConfigFiles)
  }

  public override fun update(wgConfigFiles: WgConfigFiles): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfWgConfigFiles.handle(_connection, wgConfigFiles)
  }

  public override fun getWgConfigsLiveData(): PagingSource<Int, WgConfigFiles> {
    val _sql: String = "select * from WgConfigFiles order by isActive desc, name collate nocase asc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<WgConfigFiles>(_rawQuery, __db, "WgConfigFiles") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<WgConfigFiles> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfConfigPath: Int = getColumnIndexOrThrow(_stmt, "configPath")
          val _columnIndexOfServerResponse: Int = getColumnIndexOrThrow(_stmt, "serverResponse")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _columnIndexOfIsCatchAll: Int = getColumnIndexOrThrow(_stmt, "isCatchAll")
          val _columnIndexOfIsLockdown: Int = getColumnIndexOrThrow(_stmt, "isLockdown")
          val _columnIndexOfOneWireGuard: Int = getColumnIndexOrThrow(_stmt, "oneWireGuard")
          val _columnIndexOfUseOnlyOnMetered: Int = getColumnIndexOrThrow(_stmt, "useOnlyOnMetered")
          val _columnIndexOfIsDeletable: Int = getColumnIndexOrThrow(_stmt, "isDeletable")
          val _columnIndexOfSsidEnabled: Int = getColumnIndexOrThrow(_stmt, "ssidEnabled")
          val _columnIndexOfSsids: Int = getColumnIndexOrThrow(_stmt, "ssids")
          val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
          val _result: MutableList<WgConfigFiles> = mutableListOf()
          while (_stmt.step()) {
            val _item: WgConfigFiles
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpConfigPath: String
            _tmpConfigPath = _stmt.getText(_columnIndexOfConfigPath)
            val _tmpServerResponse: String
            _tmpServerResponse = _stmt.getText(_columnIndexOfServerResponse)
            val _tmpIsActive: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _tmpIsActive = _tmp != 0
            val _tmpIsCatchAll: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfIsCatchAll).toInt()
            _tmpIsCatchAll = _tmp_1 != 0
            val _tmpIsLockdown: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfIsLockdown).toInt()
            _tmpIsLockdown = _tmp_2 != 0
            val _tmpOneWireGuard: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfOneWireGuard).toInt()
            _tmpOneWireGuard = _tmp_3 != 0
            val _tmpUseOnlyOnMetered: Boolean
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfUseOnlyOnMetered).toInt()
            _tmpUseOnlyOnMetered = _tmp_4 != 0
            val _tmpIsDeletable: Boolean
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsDeletable).toInt()
            _tmpIsDeletable = _tmp_5 != 0
            val _tmpSsidEnabled: Boolean
            val _tmp_6: Int
            _tmp_6 = _stmt.getLong(_columnIndexOfSsidEnabled).toInt()
            _tmpSsidEnabled = _tmp_6 != 0
            val _tmpSsids: String
            _tmpSsids = _stmt.getText(_columnIndexOfSsids)
            _item = WgConfigFiles(_tmpName,_tmpConfigPath,_tmpServerResponse,_tmpIsActive,_tmpIsCatchAll,_tmpIsLockdown,_tmpOneWireGuard,_tmpUseOnlyOnMetered,_tmpIsDeletable,_tmpSsidEnabled,_tmpSsids)
            _item.id = _stmt.getLong(_columnIndexOfId).toInt()
            _item.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getWgConfigs(): List<WgConfigFiles> {
    val _sql: String = "select * from WgConfigFiles order by isActive desc, name collate nocase asc"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfConfigPath: Int = getColumnIndexOrThrow(_stmt, "configPath")
        val _columnIndexOfServerResponse: Int = getColumnIndexOrThrow(_stmt, "serverResponse")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsCatchAll: Int = getColumnIndexOrThrow(_stmt, "isCatchAll")
        val _columnIndexOfIsLockdown: Int = getColumnIndexOrThrow(_stmt, "isLockdown")
        val _columnIndexOfOneWireGuard: Int = getColumnIndexOrThrow(_stmt, "oneWireGuard")
        val _columnIndexOfUseOnlyOnMetered: Int = getColumnIndexOrThrow(_stmt, "useOnlyOnMetered")
        val _columnIndexOfIsDeletable: Int = getColumnIndexOrThrow(_stmt, "isDeletable")
        val _columnIndexOfSsidEnabled: Int = getColumnIndexOrThrow(_stmt, "ssidEnabled")
        val _columnIndexOfSsids: Int = getColumnIndexOrThrow(_stmt, "ssids")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _result: MutableList<WgConfigFiles> = mutableListOf()
        while (_stmt.step()) {
          val _item: WgConfigFiles
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpConfigPath: String
          _tmpConfigPath = _stmt.getText(_columnIndexOfConfigPath)
          val _tmpServerResponse: String
          _tmpServerResponse = _stmt.getText(_columnIndexOfServerResponse)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpIsCatchAll: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCatchAll).toInt()
          _tmpIsCatchAll = _tmp_1 != 0
          val _tmpIsLockdown: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsLockdown).toInt()
          _tmpIsLockdown = _tmp_2 != 0
          val _tmpOneWireGuard: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfOneWireGuard).toInt()
          _tmpOneWireGuard = _tmp_3 != 0
          val _tmpUseOnlyOnMetered: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfUseOnlyOnMetered).toInt()
          _tmpUseOnlyOnMetered = _tmp_4 != 0
          val _tmpIsDeletable: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfIsDeletable).toInt()
          _tmpIsDeletable = _tmp_5 != 0
          val _tmpSsidEnabled: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfSsidEnabled).toInt()
          _tmpSsidEnabled = _tmp_6 != 0
          val _tmpSsids: String
          _tmpSsids = _stmt.getText(_columnIndexOfSsids)
          _item = WgConfigFiles(_tmpName,_tmpConfigPath,_tmpServerResponse,_tmpIsActive,_tmpIsCatchAll,_tmpIsLockdown,_tmpOneWireGuard,_tmpUseOnlyOnMetered,_tmpIsDeletable,_tmpSsidEnabled,_tmpSsids)
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getWarpSecWarpConfig(): List<WgConfigFiles> {
    val _sql: String = "select * from WgConfigFiles where id in (0, 1)"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfConfigPath: Int = getColumnIndexOrThrow(_stmt, "configPath")
        val _columnIndexOfServerResponse: Int = getColumnIndexOrThrow(_stmt, "serverResponse")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsCatchAll: Int = getColumnIndexOrThrow(_stmt, "isCatchAll")
        val _columnIndexOfIsLockdown: Int = getColumnIndexOrThrow(_stmt, "isLockdown")
        val _columnIndexOfOneWireGuard: Int = getColumnIndexOrThrow(_stmt, "oneWireGuard")
        val _columnIndexOfUseOnlyOnMetered: Int = getColumnIndexOrThrow(_stmt, "useOnlyOnMetered")
        val _columnIndexOfIsDeletable: Int = getColumnIndexOrThrow(_stmt, "isDeletable")
        val _columnIndexOfSsidEnabled: Int = getColumnIndexOrThrow(_stmt, "ssidEnabled")
        val _columnIndexOfSsids: Int = getColumnIndexOrThrow(_stmt, "ssids")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _result: MutableList<WgConfigFiles> = mutableListOf()
        while (_stmt.step()) {
          val _item: WgConfigFiles
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpConfigPath: String
          _tmpConfigPath = _stmt.getText(_columnIndexOfConfigPath)
          val _tmpServerResponse: String
          _tmpServerResponse = _stmt.getText(_columnIndexOfServerResponse)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpIsCatchAll: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCatchAll).toInt()
          _tmpIsCatchAll = _tmp_1 != 0
          val _tmpIsLockdown: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsLockdown).toInt()
          _tmpIsLockdown = _tmp_2 != 0
          val _tmpOneWireGuard: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfOneWireGuard).toInt()
          _tmpOneWireGuard = _tmp_3 != 0
          val _tmpUseOnlyOnMetered: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfUseOnlyOnMetered).toInt()
          _tmpUseOnlyOnMetered = _tmp_4 != 0
          val _tmpIsDeletable: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfIsDeletable).toInt()
          _tmpIsDeletable = _tmp_5 != 0
          val _tmpSsidEnabled: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfSsidEnabled).toInt()
          _tmpSsidEnabled = _tmp_6 != 0
          val _tmpSsids: String
          _tmpSsids = _stmt.getText(_columnIndexOfSsids)
          _item = WgConfigFiles(_tmpName,_tmpConfigPath,_tmpServerResponse,_tmpIsActive,_tmpIsCatchAll,_tmpIsLockdown,_tmpOneWireGuard,_tmpUseOnlyOnMetered,_tmpIsDeletable,_tmpSsidEnabled,_tmpSsids)
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getLastAddedConfigId(): Int {
    val _sql: String = "select max(id) from WgConfigFiles"
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

  public override fun isConfigAdded(id: Int): WgConfigFiles? {
    val _sql: String = "select * from WgConfigFiles where id = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfConfigPath: Int = getColumnIndexOrThrow(_stmt, "configPath")
        val _columnIndexOfServerResponse: Int = getColumnIndexOrThrow(_stmt, "serverResponse")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsCatchAll: Int = getColumnIndexOrThrow(_stmt, "isCatchAll")
        val _columnIndexOfIsLockdown: Int = getColumnIndexOrThrow(_stmt, "isLockdown")
        val _columnIndexOfOneWireGuard: Int = getColumnIndexOrThrow(_stmt, "oneWireGuard")
        val _columnIndexOfUseOnlyOnMetered: Int = getColumnIndexOrThrow(_stmt, "useOnlyOnMetered")
        val _columnIndexOfIsDeletable: Int = getColumnIndexOrThrow(_stmt, "isDeletable")
        val _columnIndexOfSsidEnabled: Int = getColumnIndexOrThrow(_stmt, "ssidEnabled")
        val _columnIndexOfSsids: Int = getColumnIndexOrThrow(_stmt, "ssids")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _result: WgConfigFiles?
        if (_stmt.step()) {
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpConfigPath: String
          _tmpConfigPath = _stmt.getText(_columnIndexOfConfigPath)
          val _tmpServerResponse: String
          _tmpServerResponse = _stmt.getText(_columnIndexOfServerResponse)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpIsCatchAll: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsCatchAll).toInt()
          _tmpIsCatchAll = _tmp_1 != 0
          val _tmpIsLockdown: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsLockdown).toInt()
          _tmpIsLockdown = _tmp_2 != 0
          val _tmpOneWireGuard: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfOneWireGuard).toInt()
          _tmpOneWireGuard = _tmp_3 != 0
          val _tmpUseOnlyOnMetered: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfUseOnlyOnMetered).toInt()
          _tmpUseOnlyOnMetered = _tmp_4 != 0
          val _tmpIsDeletable: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfIsDeletable).toInt()
          _tmpIsDeletable = _tmp_5 != 0
          val _tmpSsidEnabled: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfSsidEnabled).toInt()
          _tmpSsidEnabled = _tmp_6 != 0
          val _tmpSsids: String
          _tmpSsids = _stmt.getText(_columnIndexOfSsids)
          _result = WgConfigFiles(_tmpName,_tmpConfigPath,_tmpServerResponse,_tmpIsActive,_tmpIsCatchAll,_tmpIsLockdown,_tmpOneWireGuard,_tmpUseOnlyOnMetered,_tmpIsDeletable,_tmpSsidEnabled,_tmpSsids)
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getConfigCount(): LiveData<Int> {
    val _sql: String = "select count(id) from WgConfigFiles"
    return __db.invalidationTracker.createLiveData(arrayOf("WgConfigFiles"), false) { _connection ->
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

  public override fun deleteOnAppRestore(): Int {
    val _sql: String = "delete from WgConfigFiles"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteConfig(id: Int) {
    val _sql: String = "delete from WgConfigFiles where id = ?"
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

  public override fun updateCatchAllConfig(id: Int, isCatchAll: Boolean) {
    val _sql: String = "update WgConfigFiles set isCatchAll = ?, oneWireGuard = 0 where id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (isCatchAll) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateLockdownConfig(id: Int, isLockdown: Boolean) {
    val _sql: String = "update WgConfigFiles set isLockdown = ? where id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (isLockdown) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateMobileConfig(id: Int, isMobile: Boolean) {
    val _sql: String = "update WgConfigFiles set useOnlyOnMetered = ? where id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (isMobile) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateOneWireGuardConfig(id: Int, oneWireGuard: Boolean) {
    val _sql: String = "update WgConfigFiles set oneWireGuard = ? where id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (oneWireGuard) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateSsidEnabled(id: Int, enabled: Boolean) {
    val _sql: String = "update WgConfigFiles set ssidEnabled = ? where id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (enabled) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateSsids(id: Int, ssids: String) {
    val _sql: String = "update WgConfigFiles set ssids = ? where id = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, ssids)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun disableConfig(id: Int) {
    val _sql: String = "update WgConfigFiles set isActive = 0, oneWireGuard = 0 where id = ?"
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
