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
import kotlin.Int
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
public class RemoteBlocklistPacksMapDao_Impl(
  __db: RoomDatabase,
) : RemoteBlocklistPacksMapDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRemoteBlocklistPacksMap: EntityInsertAdapter<RemoteBlocklistPacksMap>

  private val __converters: Converters = Converters()

  private val __insertAdapterOfRemoteBlocklistPacksMap_1:
      EntityInsertAdapter<RemoteBlocklistPacksMap>

  private val __updateAdapterOfRemoteBlocklistPacksMap:
      EntityDeleteOrUpdateAdapter<RemoteBlocklistPacksMap>
  init {
    this.__db = __db
    this.__insertAdapterOfRemoteBlocklistPacksMap = object : EntityInsertAdapter<RemoteBlocklistPacksMap>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `RemoteBlocklistPacksMap` (`pack`,`level`,`blocklistIds`,`group`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RemoteBlocklistPacksMap) {
        statement.bindText(1, entity.pack)
        statement.bindLong(2, entity.level.toLong())
        val _tmp: String = __converters.listToInt(entity.blocklistIds)
        statement.bindText(3, _tmp)
        statement.bindText(4, entity.group)
      }
    }
    this.__insertAdapterOfRemoteBlocklistPacksMap_1 = object : EntityInsertAdapter<RemoteBlocklistPacksMap>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `RemoteBlocklistPacksMap` (`pack`,`level`,`blocklistIds`,`group`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RemoteBlocklistPacksMap) {
        statement.bindText(1, entity.pack)
        statement.bindLong(2, entity.level.toLong())
        val _tmp: String = __converters.listToInt(entity.blocklistIds)
        statement.bindText(3, _tmp)
        statement.bindText(4, entity.group)
      }
    }
    this.__updateAdapterOfRemoteBlocklistPacksMap = object : EntityDeleteOrUpdateAdapter<RemoteBlocklistPacksMap>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `RemoteBlocklistPacksMap` SET `pack` = ?,`level` = ?,`blocklistIds` = ?,`group` = ? WHERE `pack` = ? AND `level` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RemoteBlocklistPacksMap) {
        statement.bindText(1, entity.pack)
        statement.bindLong(2, entity.level.toLong())
        val _tmp: String = __converters.listToInt(entity.blocklistIds)
        statement.bindText(3, _tmp)
        statement.bindText(4, entity.group)
        statement.bindText(5, entity.pack)
        statement.bindLong(6, entity.level.toLong())
      }
    }
  }

  public override fun insert(map: RemoteBlocklistPacksMap): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfRemoteBlocklistPacksMap.insert(_connection, map)
  }

  public override fun insertReplace(map: RemoteBlocklistPacksMap): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfRemoteBlocklistPacksMap_1.insert(_connection, map)
  }

  public override fun insertAll(maps: List<RemoteBlocklistPacksMap>): LongArray = performBlocking(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfRemoteBlocklistPacksMap_1.insertAndReturnIdsArray(_connection, maps)
    _result
  }

  public override fun update(map: RemoteBlocklistPacksMap): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfRemoteBlocklistPacksMap.handle(_connection, map)
  }

  public override fun getTags(): PagingSource<Int, RemoteBlocklistPacksMap> {
    val _sql: String = "select * from RemoteBlocklistPacksMap l INNER JOIN (SELECT pack, MIN(level) level FROM RemoteBlocklistPacksMap where pack not in ('dead','ignore') GROUP BY pack) l1 ON l1.pack = l.pack Where l1.level = l.level ORDER BY l.`group` DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<RemoteBlocklistPacksMap>(_rawQuery, __db, "RemoteBlocklistPacksMap") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RemoteBlocklistPacksMap> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfPack: Int = getColumnIndexOrThrow(_stmt, "pack")
          val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
          val _columnIndexOfBlocklistIds: Int = getColumnIndexOrThrow(_stmt, "blocklistIds")
          val _columnIndexOfGroup: Int = getColumnIndexOrThrow(_stmt, "group")
          val _result: MutableList<RemoteBlocklistPacksMap> = mutableListOf()
          while (_stmt.step()) {
            val _item: RemoteBlocklistPacksMap
            val _tmpPack: String
            _tmpPack = _stmt.getText(_columnIndexOfPack)
            val _tmpLevel: Int
            _tmpLevel = _stmt.getLong(_columnIndexOfLevel).toInt()
            val _tmpBlocklistIds: List<Int>
            val _tmp: String
            _tmp = _stmt.getText(_columnIndexOfBlocklistIds)
            _tmpBlocklistIds = __converters.intToList(_tmp)
            val _tmpGroup: String
            _tmpGroup = _stmt.getText(_columnIndexOfGroup)
            _item = RemoteBlocklistPacksMap(_tmpPack,_tmpLevel,_tmpBlocklistIds,_tmpGroup)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun deleteAll() {
    val _sql: String = "DELETE FROM RemoteBlocklistPacksMap"
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
