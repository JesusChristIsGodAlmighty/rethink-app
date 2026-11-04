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
public class TcpProxyDAO_Impl(
  __db: RoomDatabase,
) : TcpProxyDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfTcpProxyEndpoint: EntityInsertAdapter<TcpProxyEndpoint>

  private val __deleteAdapterOfTcpProxyEndpoint: EntityDeleteOrUpdateAdapter<TcpProxyEndpoint>

  private val __updateAdapterOfTcpProxyEndpoint: EntityDeleteOrUpdateAdapter<TcpProxyEndpoint>
  init {
    this.__db = __db
    this.__insertAdapterOfTcpProxyEndpoint = object : EntityInsertAdapter<TcpProxyEndpoint>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `TcpProxyEndpoint` (`id`,`name`,`token`,`url`,`paymentStatus`,`isActive`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: TcpProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.token)
        statement.bindText(4, entity.url)
        statement.bindLong(5, entity.paymentStatus.toLong())
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(6, _tmp.toLong())
      }
    }
    this.__deleteAdapterOfTcpProxyEndpoint = object : EntityDeleteOrUpdateAdapter<TcpProxyEndpoint>() {
      protected override fun createQuery(): String = "DELETE FROM `TcpProxyEndpoint` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: TcpProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfTcpProxyEndpoint = object : EntityDeleteOrUpdateAdapter<TcpProxyEndpoint>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `TcpProxyEndpoint` SET `id` = ?,`name` = ?,`token` = ?,`url` = ?,`paymentStatus` = ?,`isActive` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: TcpProxyEndpoint) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.token)
        statement.bindText(4, entity.url)
        statement.bindLong(5, entity.paymentStatus.toLong())
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.id.toLong())
      }
    }
  }

  public override fun insertAll(tcpProxyEndpoints: List<TcpProxyEndpoint>): LongArray = performBlocking(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfTcpProxyEndpoint.insertAndReturnIdsArray(_connection, tcpProxyEndpoints)
    _result
  }

  public override fun insert(tcpProxyEndpoints: TcpProxyEndpoint): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfTcpProxyEndpoint.insertAndReturnId(_connection, tcpProxyEndpoints)
    _result
  }

  public override fun delete(tcpProxy: TcpProxyEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfTcpProxyEndpoint.handle(_connection, tcpProxy)
  }

  public override fun update(tcpProxy: TcpProxyEndpoint): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfTcpProxyEndpoint.handle(_connection, tcpProxy)
  }

  public override fun getTcpProxiesLiveData(): PagingSource<Int, TcpProxyEndpoint> {
    val _sql: String = "select * from TcpProxyEndpoint"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<TcpProxyEndpoint>(_rawQuery, __db, "TcpProxyEndpoint") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<TcpProxyEndpoint> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfToken: Int = getColumnIndexOrThrow(_stmt, "token")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfPaymentStatus: Int = getColumnIndexOrThrow(_stmt, "paymentStatus")
          val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
          val _result: MutableList<TcpProxyEndpoint> = mutableListOf()
          while (_stmt.step()) {
            val _item: TcpProxyEndpoint
            val _tmpId: Int
            _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpToken: String
            _tmpToken = _stmt.getText(_columnIndexOfToken)
            val _tmpUrl: String
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
            val _tmpPaymentStatus: Int
            _tmpPaymentStatus = _stmt.getLong(_columnIndexOfPaymentStatus).toInt()
            val _tmpIsActive: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
            _tmpIsActive = _tmp != 0
            _item = TcpProxyEndpoint(_tmpId,_tmpName,_tmpToken,_tmpUrl,_tmpPaymentStatus,_tmpIsActive)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getTcpProxies(): List<TcpProxyEndpoint> {
    val _sql: String = "select * from TcpProxyEndpoint"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfToken: Int = getColumnIndexOrThrow(_stmt, "token")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfPaymentStatus: Int = getColumnIndexOrThrow(_stmt, "paymentStatus")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: MutableList<TcpProxyEndpoint> = mutableListOf()
        while (_stmt.step()) {
          val _item: TcpProxyEndpoint
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpToken: String
          _tmpToken = _stmt.getText(_columnIndexOfToken)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpPaymentStatus: Int
          _tmpPaymentStatus = _stmt.getLong(_columnIndexOfPaymentStatus).toInt()
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _item = TcpProxyEndpoint(_tmpId,_tmpName,_tmpToken,_tmpUrl,_tmpPaymentStatus,_tmpIsActive)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteById(id: Int) {
    val _sql: String = "delete from TcpProxyEndpoint where id = ?"
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
