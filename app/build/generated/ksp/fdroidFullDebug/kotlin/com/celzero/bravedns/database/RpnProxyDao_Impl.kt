package com.celzero.bravedns.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performBlocking
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
public class RpnProxyDao_Impl(
  __db: RoomDatabase,
) : RpnProxyDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRpnProxy: EntityInsertAdapter<RpnProxy>

  private val __deleteAdapterOfRpnProxy: EntityDeleteOrUpdateAdapter<RpnProxy>

  private val __updateAdapterOfRpnProxy: EntityDeleteOrUpdateAdapter<RpnProxy>
  init {
    this.__db = __db
    this.__insertAdapterOfRpnProxy = object : EntityInsertAdapter<RpnProxy>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `RpnProxy` (`id`,`name`,`configPath`,`serverResPath`,`isActive`,`isLockdown`,`createdTs`,`modifiedTs`,`misc`,`tunId`,`latency`,`lastRefreshTime`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RpnProxy) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.configPath)
        statement.bindText(4, entity.serverResPath)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isLockdown) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        statement.bindLong(7, entity.createdTs)
        statement.bindLong(8, entity.modifiedTs)
        statement.bindText(9, entity.misc)
        statement.bindText(10, entity.tunId)
        statement.bindLong(11, entity.latency.toLong())
        statement.bindLong(12, entity.lastRefreshTime)
      }
    }
    this.__deleteAdapterOfRpnProxy = object : EntityDeleteOrUpdateAdapter<RpnProxy>() {
      protected override fun createQuery(): String = "DELETE FROM `RpnProxy` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RpnProxy) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfRpnProxy = object : EntityDeleteOrUpdateAdapter<RpnProxy>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `RpnProxy` SET `id` = ?,`name` = ?,`configPath` = ?,`serverResPath` = ?,`isActive` = ?,`isLockdown` = ?,`createdTs` = ?,`modifiedTs` = ?,`misc` = ?,`tunId` = ?,`latency` = ?,`lastRefreshTime` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RpnProxy) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.configPath)
        statement.bindText(4, entity.serverResPath)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmp_1: Int = if (entity.isLockdown) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
        statement.bindLong(7, entity.createdTs)
        statement.bindLong(8, entity.modifiedTs)
        statement.bindText(9, entity.misc)
        statement.bindText(10, entity.tunId)
        statement.bindLong(11, entity.latency.toLong())
        statement.bindLong(12, entity.lastRefreshTime)
        statement.bindLong(13, entity.id.toLong())
      }
    }
  }

  public override fun insertAll(rpnProxies: List<RpnProxy>): LongArray = performBlocking(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfRpnProxy.insertAndReturnIdsArray(_connection, rpnProxies)
    _result
  }

  public override fun insert(rpnProxy: RpnProxy): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfRpnProxy.insertAndReturnId(_connection, rpnProxy)
    _result
  }

  public override fun delete(proxy: RpnProxy): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfRpnProxy.handle(_connection, proxy)
  }

  public override fun update(rpnProxy: RpnProxy): Int = performBlocking(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfRpnProxy.handle(_connection, rpnProxy)
    _result
  }

  public override fun getProxyById(id: Int): RpnProxy? {
    val _sql: String = "select * from RpnProxy where id = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfConfigPath: Int = getColumnIndexOrThrow(_stmt, "configPath")
        val _columnIndexOfServerResPath: Int = getColumnIndexOrThrow(_stmt, "serverResPath")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsLockdown: Int = getColumnIndexOrThrow(_stmt, "isLockdown")
        val _columnIndexOfCreatedTs: Int = getColumnIndexOrThrow(_stmt, "createdTs")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfMisc: Int = getColumnIndexOrThrow(_stmt, "misc")
        val _columnIndexOfTunId: Int = getColumnIndexOrThrow(_stmt, "tunId")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _columnIndexOfLastRefreshTime: Int = getColumnIndexOrThrow(_stmt, "lastRefreshTime")
        val _result: RpnProxy?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpConfigPath: String
          _tmpConfigPath = _stmt.getText(_columnIndexOfConfigPath)
          val _tmpServerResPath: String
          _tmpServerResPath = _stmt.getText(_columnIndexOfServerResPath)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpIsLockdown: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsLockdown).toInt()
          _tmpIsLockdown = _tmp_1 != 0
          val _tmpCreatedTs: Long
          _tmpCreatedTs = _stmt.getLong(_columnIndexOfCreatedTs)
          val _tmpModifiedTs: Long
          _tmpModifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmpMisc: String
          _tmpMisc = _stmt.getText(_columnIndexOfMisc)
          val _tmpTunId: String
          _tmpTunId = _stmt.getText(_columnIndexOfTunId)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          val _tmpLastRefreshTime: Long
          _tmpLastRefreshTime = _stmt.getLong(_columnIndexOfLastRefreshTime)
          _result = RpnProxy(_tmpId,_tmpName,_tmpConfigPath,_tmpServerResPath,_tmpIsActive,_tmpIsLockdown,_tmpCreatedTs,_tmpModifiedTs,_tmpMisc,_tmpTunId,_tmpLatency,_tmpLastRefreshTime)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllProxies(): List<RpnProxy> {
    val _sql: String = "select * from RpnProxy"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfConfigPath: Int = getColumnIndexOrThrow(_stmt, "configPath")
        val _columnIndexOfServerResPath: Int = getColumnIndexOrThrow(_stmt, "serverResPath")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsLockdown: Int = getColumnIndexOrThrow(_stmt, "isLockdown")
        val _columnIndexOfCreatedTs: Int = getColumnIndexOrThrow(_stmt, "createdTs")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfMisc: Int = getColumnIndexOrThrow(_stmt, "misc")
        val _columnIndexOfTunId: Int = getColumnIndexOrThrow(_stmt, "tunId")
        val _columnIndexOfLatency: Int = getColumnIndexOrThrow(_stmt, "latency")
        val _columnIndexOfLastRefreshTime: Int = getColumnIndexOrThrow(_stmt, "lastRefreshTime")
        val _result: MutableList<RpnProxy> = mutableListOf()
        while (_stmt.step()) {
          val _item: RpnProxy
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpConfigPath: String
          _tmpConfigPath = _stmt.getText(_columnIndexOfConfigPath)
          val _tmpServerResPath: String
          _tmpServerResPath = _stmt.getText(_columnIndexOfServerResPath)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          val _tmpIsLockdown: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsLockdown).toInt()
          _tmpIsLockdown = _tmp_1 != 0
          val _tmpCreatedTs: Long
          _tmpCreatedTs = _stmt.getLong(_columnIndexOfCreatedTs)
          val _tmpModifiedTs: Long
          _tmpModifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmpMisc: String
          _tmpMisc = _stmt.getText(_columnIndexOfMisc)
          val _tmpTunId: String
          _tmpTunId = _stmt.getText(_columnIndexOfTunId)
          val _tmpLatency: Int
          _tmpLatency = _stmt.getLong(_columnIndexOfLatency).toInt()
          val _tmpLastRefreshTime: Long
          _tmpLastRefreshTime = _stmt.getLong(_columnIndexOfLastRefreshTime)
          _item = RpnProxy(_tmpId,_tmpName,_tmpConfigPath,_tmpServerResPath,_tmpIsActive,_tmpIsLockdown,_tmpCreatedTs,_tmpModifiedTs,_tmpMisc,_tmpTunId,_tmpLatency,_tmpLastRefreshTime)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteById(id: Int) {
    val _sql: String = "delete from RpnProxy where id = ?"
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
