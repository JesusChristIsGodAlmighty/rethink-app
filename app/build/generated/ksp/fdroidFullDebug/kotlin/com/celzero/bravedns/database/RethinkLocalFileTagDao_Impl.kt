package com.celzero.bravedns.database

import android.database.Cursor
import androidx.paging.PagingSource
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.RoomSQLiteQuery
import androidx.room.RoomSQLiteQuery.Companion.acquire
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performBlocking
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.celzero.bravedns.`data`.FileTag
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
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RethinkLocalFileTagDao_Impl(
  __db: RoomDatabase,
) : RethinkLocalFileTagDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRethinkLocalFileTag: EntityInsertAdapter<RethinkLocalFileTag>

  private val __converters: Converters = Converters()

  private val __insertAdapterOfRethinkLocalFileTag_1: EntityInsertAdapter<RethinkLocalFileTag>

  private val __deleteAdapterOfRethinkLocalFileTag: EntityDeleteOrUpdateAdapter<RethinkLocalFileTag>

  private val __updateAdapterOfRethinkLocalFileTag: EntityDeleteOrUpdateAdapter<RethinkLocalFileTag>
  init {
    this.__db = __db
    this.__insertAdapterOfRethinkLocalFileTag = object : EntityInsertAdapter<RethinkLocalFileTag>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `RethinkLocalFileTag` (`value`,`uname`,`vname`,`group`,`subg`,`url`,`show`,`entries`,`pack`,`level`,`simpleTagId`,`isSelected`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkLocalFileTag) {
        statement.bindLong(1, entity.value.toLong())
        statement.bindText(2, entity.uname)
        statement.bindText(3, entity.vname)
        statement.bindText(4, entity.group)
        statement.bindText(5, entity.subg)
        val _tmp: String = __converters.listToString(entity.url)
        statement.bindText(6, _tmp)
        statement.bindLong(7, entity.show.toLong())
        statement.bindLong(8, entity.entries.toLong())
        val _tmpPack: List<String>? = entity.pack
        val _tmp_1: String = __converters.listToString(_tmpPack)
        if (_tmp_1 == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmp_1)
        }
        val _tmpLevel: List<Int>? = entity.level
        val _tmp_2: String = __converters.listToInt(_tmpLevel)
        if (_tmp_2 == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmp_2)
        }
        statement.bindLong(11, entity.simpleTagId.toLong())
        val _tmp_3: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(12, _tmp_3.toLong())
      }
    }
    this.__insertAdapterOfRethinkLocalFileTag_1 = object : EntityInsertAdapter<RethinkLocalFileTag>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `RethinkLocalFileTag` (`value`,`uname`,`vname`,`group`,`subg`,`url`,`show`,`entries`,`pack`,`level`,`simpleTagId`,`isSelected`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkLocalFileTag) {
        statement.bindLong(1, entity.value.toLong())
        statement.bindText(2, entity.uname)
        statement.bindText(3, entity.vname)
        statement.bindText(4, entity.group)
        statement.bindText(5, entity.subg)
        val _tmp: String = __converters.listToString(entity.url)
        statement.bindText(6, _tmp)
        statement.bindLong(7, entity.show.toLong())
        statement.bindLong(8, entity.entries.toLong())
        val _tmpPack: List<String>? = entity.pack
        val _tmp_1: String = __converters.listToString(_tmpPack)
        if (_tmp_1 == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmp_1)
        }
        val _tmpLevel: List<Int>? = entity.level
        val _tmp_2: String = __converters.listToInt(_tmpLevel)
        if (_tmp_2 == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmp_2)
        }
        statement.bindLong(11, entity.simpleTagId.toLong())
        val _tmp_3: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(12, _tmp_3.toLong())
      }
    }
    this.__deleteAdapterOfRethinkLocalFileTag = object : EntityDeleteOrUpdateAdapter<RethinkLocalFileTag>() {
      protected override fun createQuery(): String = "DELETE FROM `RethinkLocalFileTag` WHERE `value` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkLocalFileTag) {
        statement.bindLong(1, entity.value.toLong())
      }
    }
    this.__updateAdapterOfRethinkLocalFileTag = object : EntityDeleteOrUpdateAdapter<RethinkLocalFileTag>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `RethinkLocalFileTag` SET `value` = ?,`uname` = ?,`vname` = ?,`group` = ?,`subg` = ?,`url` = ?,`show` = ?,`entries` = ?,`pack` = ?,`level` = ?,`simpleTagId` = ?,`isSelected` = ? WHERE `value` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RethinkLocalFileTag) {
        statement.bindLong(1, entity.value.toLong())
        statement.bindText(2, entity.uname)
        statement.bindText(3, entity.vname)
        statement.bindText(4, entity.group)
        statement.bindText(5, entity.subg)
        val _tmp: String = __converters.listToString(entity.url)
        statement.bindText(6, _tmp)
        statement.bindLong(7, entity.show.toLong())
        statement.bindLong(8, entity.entries.toLong())
        val _tmpPack: List<String>? = entity.pack
        val _tmp_1: String = __converters.listToString(_tmpPack)
        if (_tmp_1 == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmp_1)
        }
        val _tmpLevel: List<Int>? = entity.level
        val _tmp_2: String = __converters.listToInt(_tmpLevel)
        if (_tmp_2 == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmp_2)
        }
        statement.bindLong(11, entity.simpleTagId.toLong())
        val _tmp_3: Int = if (entity.isSelected) 1 else 0
        statement.bindLong(12, _tmp_3.toLong())
        statement.bindLong(13, entity.value.toLong())
      }
    }
  }

  public override fun insert(fileTag: RethinkLocalFileTag): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfRethinkLocalFileTag.insertAndReturnId(_connection, fileTag)
    _result
  }

  public override fun insertReplace(fileTag: RethinkLocalFileTag): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfRethinkLocalFileTag_1.insert(_connection, fileTag)
  }

  public override fun insertAll(fileTags: List<RethinkLocalFileTag>): LongArray = performBlocking(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfRethinkLocalFileTag_1.insertAndReturnIdsArray(_connection, fileTags)
    _result
  }

  public override fun delete(fileTag: RethinkLocalFileTag): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfRethinkLocalFileTag.handle(_connection, fileTag)
  }

  public override fun update(fileTag: RethinkLocalFileTag): Int = performBlocking(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfRethinkLocalFileTag.handle(_connection, fileTag)
    _result
  }

  public override fun updateAll(fileTags: List<RethinkLocalFileTag>): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfRethinkLocalFileTag.handleMultiple(_connection, fileTags)
  }

  public override fun getAllTags(): List<FileTag> {
    val _sql: String = "select value, uname, vname, `group`, subg, url as urls, show, entries, pack, level, simpleTagId, isSelected  from RethinkLocalFileTag"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfValue: Int = 0
        val _columnIndexOfUname: Int = 1
        val _columnIndexOfVname: Int = 2
        val _columnIndexOfGroup: Int = 3
        val _columnIndexOfSubg: Int = 4
        val _columnIndexOfUrls: Int = 5
        val _columnIndexOfShow: Int = 6
        val _columnIndexOfEntries: Int = 7
        val _columnIndexOfPack: Int = 8
        val _columnIndexOfLevel: Int = 9
        val _columnIndexOfSimpleTagId: Int = 10
        val _columnIndexOfIsSelected: Int = 11
        val _result: MutableList<FileTag> = mutableListOf()
        while (_stmt.step()) {
          val _item: FileTag
          val _tmpValue: Int
          _tmpValue = _stmt.getLong(_columnIndexOfValue).toInt()
          val _tmpUname: String
          _tmpUname = _stmt.getText(_columnIndexOfUname)
          val _tmpVname: String
          _tmpVname = _stmt.getText(_columnIndexOfVname)
          val _tmpGroup: String
          _tmpGroup = _stmt.getText(_columnIndexOfGroup)
          val _tmpSubg: String
          _tmpSubg = _stmt.getText(_columnIndexOfSubg)
          val _tmpUrls: List<String>
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfUrls)
          _tmpUrls = __converters.stringToList(_tmp)
          val _tmpShow: Int
          _tmpShow = _stmt.getLong(_columnIndexOfShow).toInt()
          val _tmpEntries: Int
          _tmpEntries = _stmt.getLong(_columnIndexOfEntries).toInt()
          val _tmpPack: List<String>
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfPack)
          _tmpPack = __converters.stringToList(_tmp_1)
          val _tmpLevel: List<Int>?
          val _tmp_2: String?
          if (_stmt.isNull(_columnIndexOfLevel)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfLevel)
          }
          val _tmp_3: List<Int> = __converters.intToList(_tmp_2)
          _tmpLevel = _tmp_3
          val _tmpSimpleTagId: Int
          _tmpSimpleTagId = _stmt.getLong(_columnIndexOfSimpleTagId).toInt()
          val _tmpIsSelected: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp_4 != 0
          _item = FileTag(_tmpValue,_tmpUname,_tmpVname,_tmpGroup,_tmpSubg,_tmpUrls,_tmpShow,_tmpPack,_tmpLevel,_tmpEntries,_tmpSimpleTagId,_tmpIsSelected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getLocalFileTags(): PagingSource<Int, RethinkLocalFileTag> {
    val _sql: String = "select * from RethinkLocalFileTag where case when isSelected = 1 then pack like '%%' else (pack not like '%dead%' and pack not like '%ignore%') end order by `group`  desc"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<RethinkLocalFileTag>(_rawQuery, __db, "RethinkLocalFileTag") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLocalFileTag> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfValue: Int = getColumnIndexOrThrow(_stmt, "value")
          val _columnIndexOfUname: Int = getColumnIndexOrThrow(_stmt, "uname")
          val _columnIndexOfVname: Int = getColumnIndexOrThrow(_stmt, "vname")
          val _columnIndexOfGroup: Int = getColumnIndexOrThrow(_stmt, "group")
          val _columnIndexOfSubg: Int = getColumnIndexOrThrow(_stmt, "subg")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfShow: Int = getColumnIndexOrThrow(_stmt, "show")
          val _columnIndexOfEntries: Int = getColumnIndexOrThrow(_stmt, "entries")
          val _columnIndexOfPack: Int = getColumnIndexOrThrow(_stmt, "pack")
          val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
          val _columnIndexOfSimpleTagId: Int = getColumnIndexOrThrow(_stmt, "simpleTagId")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _result: MutableList<RethinkLocalFileTag> = mutableListOf()
          while (_stmt.step()) {
            val _item: RethinkLocalFileTag
            val _tmpValue: Int
            _tmpValue = _stmt.getLong(_columnIndexOfValue).toInt()
            val _tmpUname: String
            _tmpUname = _stmt.getText(_columnIndexOfUname)
            val _tmpVname: String
            _tmpVname = _stmt.getText(_columnIndexOfVname)
            val _tmpGroup: String
            _tmpGroup = _stmt.getText(_columnIndexOfGroup)
            val _tmpSubg: String
            _tmpSubg = _stmt.getText(_columnIndexOfSubg)
            val _tmpUrl: List<String>
            val _tmp: String
            _tmp = _stmt.getText(_columnIndexOfUrl)
            _tmpUrl = __converters.stringToList(_tmp)
            val _tmpShow: Int
            _tmpShow = _stmt.getLong(_columnIndexOfShow).toInt()
            val _tmpEntries: Int
            _tmpEntries = _stmt.getLong(_columnIndexOfEntries).toInt()
            val _tmpPack: List<String>?
            val _tmp_1: String?
            if (_stmt.isNull(_columnIndexOfPack)) {
              _tmp_1 = null
            } else {
              _tmp_1 = _stmt.getText(_columnIndexOfPack)
            }
            val _tmp_2: List<String> = __converters.stringToList(_tmp_1)
            _tmpPack = _tmp_2
            val _tmpLevel: List<Int>?
            val _tmp_3: String?
            if (_stmt.isNull(_columnIndexOfLevel)) {
              _tmp_3 = null
            } else {
              _tmp_3 = _stmt.getText(_columnIndexOfLevel)
            }
            val _tmp_4: List<Int> = __converters.intToList(_tmp_3)
            _tmpLevel = _tmp_4
            val _tmpSimpleTagId: Int
            _tmpSimpleTagId = _stmt.getLong(_columnIndexOfSimpleTagId).toInt()
            val _tmpIsSelected: Boolean
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsSelected).toInt()
            _tmpIsSelected = _tmp_5 != 0
            _item = RethinkLocalFileTag(_tmpValue,_tmpUname,_tmpVname,_tmpGroup,_tmpSubg,_tmpPack,_tmpLevel,_tmpUrl,_tmpShow,_tmpEntries,_tmpSimpleTagId,_tmpIsSelected)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getLocalFileTags(
    query: String,
    selected: Set<Int>,
    subg: Set<String>,
  ): PagingSource<Int, RethinkLocalFileTag> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from RethinkLocalFileTag where isSelected in (")
    val _inputSize: Int = selected.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and subg in (")
    val _inputSize_1: Int = subg.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") and (vname like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or `group` like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or subg like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and (pack not like '%dead%' and pack not like '%ignore%') order by `group`  desc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: Int in selected) {
        _stmt.bindLong(_argIndex, _item.toLong())
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      for (_item_1: String in subg) {
        _stmt.bindText(_argIndex, _item_1)
        _argIndex++
      }
      _argIndex = 1 + _inputSize + _inputSize_1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2 + _inputSize + _inputSize_1
      _stmt.bindText(_argIndex, query)
      _argIndex = 3 + _inputSize + _inputSize_1
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<RethinkLocalFileTag>(_rawQuery, __db, "RethinkLocalFileTag") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLocalFileTag> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfValue: Int = getColumnIndexOrThrow(_stmt, "value")
          val _columnIndexOfUname: Int = getColumnIndexOrThrow(_stmt, "uname")
          val _columnIndexOfVname: Int = getColumnIndexOrThrow(_stmt, "vname")
          val _columnIndexOfGroup: Int = getColumnIndexOrThrow(_stmt, "group")
          val _columnIndexOfSubg: Int = getColumnIndexOrThrow(_stmt, "subg")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfShow: Int = getColumnIndexOrThrow(_stmt, "show")
          val _columnIndexOfEntries: Int = getColumnIndexOrThrow(_stmt, "entries")
          val _columnIndexOfPack: Int = getColumnIndexOrThrow(_stmt, "pack")
          val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
          val _columnIndexOfSimpleTagId: Int = getColumnIndexOrThrow(_stmt, "simpleTagId")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _result: MutableList<RethinkLocalFileTag> = mutableListOf()
          while (_stmt.step()) {
            val _item_2: RethinkLocalFileTag
            val _tmpValue: Int
            _tmpValue = _stmt.getLong(_columnIndexOfValue).toInt()
            val _tmpUname: String
            _tmpUname = _stmt.getText(_columnIndexOfUname)
            val _tmpVname: String
            _tmpVname = _stmt.getText(_columnIndexOfVname)
            val _tmpGroup: String
            _tmpGroup = _stmt.getText(_columnIndexOfGroup)
            val _tmpSubg: String
            _tmpSubg = _stmt.getText(_columnIndexOfSubg)
            val _tmpUrl: List<String>
            val _tmp: String
            _tmp = _stmt.getText(_columnIndexOfUrl)
            _tmpUrl = __converters.stringToList(_tmp)
            val _tmpShow: Int
            _tmpShow = _stmt.getLong(_columnIndexOfShow).toInt()
            val _tmpEntries: Int
            _tmpEntries = _stmt.getLong(_columnIndexOfEntries).toInt()
            val _tmpPack: List<String>?
            val _tmp_1: String?
            if (_stmt.isNull(_columnIndexOfPack)) {
              _tmp_1 = null
            } else {
              _tmp_1 = _stmt.getText(_columnIndexOfPack)
            }
            val _tmp_2: List<String> = __converters.stringToList(_tmp_1)
            _tmpPack = _tmp_2
            val _tmpLevel: List<Int>?
            val _tmp_3: String?
            if (_stmt.isNull(_columnIndexOfLevel)) {
              _tmp_3 = null
            } else {
              _tmp_3 = _stmt.getText(_columnIndexOfLevel)
            }
            val _tmp_4: List<Int> = __converters.intToList(_tmp_3)
            _tmpLevel = _tmp_4
            val _tmpSimpleTagId: Int
            _tmpSimpleTagId = _stmt.getLong(_columnIndexOfSimpleTagId).toInt()
            val _tmpIsSelected: Boolean
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsSelected).toInt()
            _tmpIsSelected = _tmp_5 != 0
            _item_2 = RethinkLocalFileTag(_tmpValue,_tmpUname,_tmpVname,_tmpGroup,_tmpSubg,_tmpPack,_tmpLevel,_tmpUrl,_tmpShow,_tmpEntries,_tmpSimpleTagId,_tmpIsSelected)
            _result.add(_item_2)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getLocalFileTagsGroup(query: String, selected: Set<Int>): PagingSource<Int, RethinkLocalFileTag> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from RethinkLocalFileTag where isSelected in (")
    val _inputSize: Int = selected.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and (vname like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or `group` like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or subg like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and (pack not like '%dead%' and pack not like '%ignore%') order by `group`  desc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: Int in selected) {
        _stmt.bindLong(_argIndex, _item.toLong())
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 2 + _inputSize
      _stmt.bindText(_argIndex, query)
      _argIndex = 3 + _inputSize
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<RethinkLocalFileTag>(_rawQuery, __db, "RethinkLocalFileTag") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLocalFileTag> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfValue: Int = getColumnIndexOrThrow(_stmt, "value")
          val _columnIndexOfUname: Int = getColumnIndexOrThrow(_stmt, "uname")
          val _columnIndexOfVname: Int = getColumnIndexOrThrow(_stmt, "vname")
          val _columnIndexOfGroup: Int = getColumnIndexOrThrow(_stmt, "group")
          val _columnIndexOfSubg: Int = getColumnIndexOrThrow(_stmt, "subg")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfShow: Int = getColumnIndexOrThrow(_stmt, "show")
          val _columnIndexOfEntries: Int = getColumnIndexOrThrow(_stmt, "entries")
          val _columnIndexOfPack: Int = getColumnIndexOrThrow(_stmt, "pack")
          val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
          val _columnIndexOfSimpleTagId: Int = getColumnIndexOrThrow(_stmt, "simpleTagId")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _result: MutableList<RethinkLocalFileTag> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: RethinkLocalFileTag
            val _tmpValue: Int
            _tmpValue = _stmt.getLong(_columnIndexOfValue).toInt()
            val _tmpUname: String
            _tmpUname = _stmt.getText(_columnIndexOfUname)
            val _tmpVname: String
            _tmpVname = _stmt.getText(_columnIndexOfVname)
            val _tmpGroup: String
            _tmpGroup = _stmt.getText(_columnIndexOfGroup)
            val _tmpSubg: String
            _tmpSubg = _stmt.getText(_columnIndexOfSubg)
            val _tmpUrl: List<String>
            val _tmp: String
            _tmp = _stmt.getText(_columnIndexOfUrl)
            _tmpUrl = __converters.stringToList(_tmp)
            val _tmpShow: Int
            _tmpShow = _stmt.getLong(_columnIndexOfShow).toInt()
            val _tmpEntries: Int
            _tmpEntries = _stmt.getLong(_columnIndexOfEntries).toInt()
            val _tmpPack: List<String>?
            val _tmp_1: String?
            if (_stmt.isNull(_columnIndexOfPack)) {
              _tmp_1 = null
            } else {
              _tmp_1 = _stmt.getText(_columnIndexOfPack)
            }
            val _tmp_2: List<String> = __converters.stringToList(_tmp_1)
            _tmpPack = _tmp_2
            val _tmpLevel: List<Int>?
            val _tmp_3: String?
            if (_stmt.isNull(_columnIndexOfLevel)) {
              _tmp_3 = null
            } else {
              _tmp_3 = _stmt.getText(_columnIndexOfLevel)
            }
            val _tmp_4: List<Int> = __converters.intToList(_tmp_3)
            _tmpLevel = _tmp_4
            val _tmpSimpleTagId: Int
            _tmpSimpleTagId = _stmt.getLong(_columnIndexOfSimpleTagId).toInt()
            val _tmpIsSelected: Boolean
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsSelected).toInt()
            _tmpIsSelected = _tmp_5 != 0
            _item_1 = RethinkLocalFileTag(_tmpValue,_tmpUname,_tmpVname,_tmpGroup,_tmpSubg,_tmpPack,_tmpLevel,_tmpUrl,_tmpShow,_tmpEntries,_tmpSimpleTagId,_tmpIsSelected)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getLocalFileTagsSubg(
    query: String,
    selected: Set<Int>,
    subg: Set<String>,
  ): PagingSource<Int, RethinkLocalFileTag> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from RethinkLocalFileTag where isSelected in (")
    val _inputSize: Int = selected.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and subg in (")
    val _inputSize_1: Int = subg.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") and (vname like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or `group` like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or subg like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and case when isSelected = 1 then pack like '%%' else (pack not like '%dead%' and pack not like '%ignore%') end order by `group` desc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: Int in selected) {
        _stmt.bindLong(_argIndex, _item.toLong())
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      for (_item_1: String in subg) {
        _stmt.bindText(_argIndex, _item_1)
        _argIndex++
      }
      _argIndex = 1 + _inputSize + _inputSize_1
      _stmt.bindText(_argIndex, query)
      _argIndex = 2 + _inputSize + _inputSize_1
      _stmt.bindText(_argIndex, query)
      _argIndex = 3 + _inputSize + _inputSize_1
      _stmt.bindText(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<RethinkLocalFileTag>(_rawQuery, __db, "RethinkLocalFileTag") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLocalFileTag> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfValue: Int = getColumnIndexOrThrow(_stmt, "value")
          val _columnIndexOfUname: Int = getColumnIndexOrThrow(_stmt, "uname")
          val _columnIndexOfVname: Int = getColumnIndexOrThrow(_stmt, "vname")
          val _columnIndexOfGroup: Int = getColumnIndexOrThrow(_stmt, "group")
          val _columnIndexOfSubg: Int = getColumnIndexOrThrow(_stmt, "subg")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfShow: Int = getColumnIndexOrThrow(_stmt, "show")
          val _columnIndexOfEntries: Int = getColumnIndexOrThrow(_stmt, "entries")
          val _columnIndexOfPack: Int = getColumnIndexOrThrow(_stmt, "pack")
          val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
          val _columnIndexOfSimpleTagId: Int = getColumnIndexOrThrow(_stmt, "simpleTagId")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _result: MutableList<RethinkLocalFileTag> = mutableListOf()
          while (_stmt.step()) {
            val _item_2: RethinkLocalFileTag
            val _tmpValue: Int
            _tmpValue = _stmt.getLong(_columnIndexOfValue).toInt()
            val _tmpUname: String
            _tmpUname = _stmt.getText(_columnIndexOfUname)
            val _tmpVname: String
            _tmpVname = _stmt.getText(_columnIndexOfVname)
            val _tmpGroup: String
            _tmpGroup = _stmt.getText(_columnIndexOfGroup)
            val _tmpSubg: String
            _tmpSubg = _stmt.getText(_columnIndexOfSubg)
            val _tmpUrl: List<String>
            val _tmp: String
            _tmp = _stmt.getText(_columnIndexOfUrl)
            _tmpUrl = __converters.stringToList(_tmp)
            val _tmpShow: Int
            _tmpShow = _stmt.getLong(_columnIndexOfShow).toInt()
            val _tmpEntries: Int
            _tmpEntries = _stmt.getLong(_columnIndexOfEntries).toInt()
            val _tmpPack: List<String>?
            val _tmp_1: String?
            if (_stmt.isNull(_columnIndexOfPack)) {
              _tmp_1 = null
            } else {
              _tmp_1 = _stmt.getText(_columnIndexOfPack)
            }
            val _tmp_2: List<String> = __converters.stringToList(_tmp_1)
            _tmpPack = _tmp_2
            val _tmpLevel: List<Int>?
            val _tmp_3: String?
            if (_stmt.isNull(_columnIndexOfLevel)) {
              _tmp_3 = null
            } else {
              _tmp_3 = _stmt.getText(_columnIndexOfLevel)
            }
            val _tmp_4: List<Int> = __converters.intToList(_tmp_3)
            _tmpLevel = _tmp_4
            val _tmpSimpleTagId: Int
            _tmpSimpleTagId = _stmt.getLong(_columnIndexOfSimpleTagId).toInt()
            val _tmpIsSelected: Boolean
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsSelected).toInt()
            _tmpIsSelected = _tmp_5 != 0
            _item_2 = RethinkLocalFileTag(_tmpValue,_tmpUname,_tmpVname,_tmpGroup,_tmpSubg,_tmpPack,_tmpLevel,_tmpUrl,_tmpShow,_tmpEntries,_tmpSimpleTagId,_tmpIsSelected)
            _result.add(_item_2)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getLocalFileTagsWithFilter(input: String, selected: Set<Int>): PagingSource<Int, RethinkLocalFileTag> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from RethinkLocalFileTag where isSelected in (")
    val _inputSize: Int = selected.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and (vname like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or `group` like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or subg like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and case when isSelected = 1 then pack like '%%' else (pack not like '%dead%' and pack not like '%ignore%') end order by `group` desc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      for (_item: Int in selected) {
        _stmt.bindLong(_argIndex, _item.toLong())
        _argIndex++
      }
      _argIndex = 1 + _inputSize
      _stmt.bindText(_argIndex, input)
      _argIndex = 2 + _inputSize
      _stmt.bindText(_argIndex, input)
      _argIndex = 3 + _inputSize
      _stmt.bindText(_argIndex, input)
    }
    return object : LimitOffsetPagingSource<RethinkLocalFileTag>(_rawQuery, __db, "RethinkLocalFileTag") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<RethinkLocalFileTag> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfValue: Int = getColumnIndexOrThrow(_stmt, "value")
          val _columnIndexOfUname: Int = getColumnIndexOrThrow(_stmt, "uname")
          val _columnIndexOfVname: Int = getColumnIndexOrThrow(_stmt, "vname")
          val _columnIndexOfGroup: Int = getColumnIndexOrThrow(_stmt, "group")
          val _columnIndexOfSubg: Int = getColumnIndexOrThrow(_stmt, "subg")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfShow: Int = getColumnIndexOrThrow(_stmt, "show")
          val _columnIndexOfEntries: Int = getColumnIndexOrThrow(_stmt, "entries")
          val _columnIndexOfPack: Int = getColumnIndexOrThrow(_stmt, "pack")
          val _columnIndexOfLevel: Int = getColumnIndexOrThrow(_stmt, "level")
          val _columnIndexOfSimpleTagId: Int = getColumnIndexOrThrow(_stmt, "simpleTagId")
          val _columnIndexOfIsSelected: Int = getColumnIndexOrThrow(_stmt, "isSelected")
          val _result: MutableList<RethinkLocalFileTag> = mutableListOf()
          while (_stmt.step()) {
            val _item_1: RethinkLocalFileTag
            val _tmpValue: Int
            _tmpValue = _stmt.getLong(_columnIndexOfValue).toInt()
            val _tmpUname: String
            _tmpUname = _stmt.getText(_columnIndexOfUname)
            val _tmpVname: String
            _tmpVname = _stmt.getText(_columnIndexOfVname)
            val _tmpGroup: String
            _tmpGroup = _stmt.getText(_columnIndexOfGroup)
            val _tmpSubg: String
            _tmpSubg = _stmt.getText(_columnIndexOfSubg)
            val _tmpUrl: List<String>
            val _tmp: String
            _tmp = _stmt.getText(_columnIndexOfUrl)
            _tmpUrl = __converters.stringToList(_tmp)
            val _tmpShow: Int
            _tmpShow = _stmt.getLong(_columnIndexOfShow).toInt()
            val _tmpEntries: Int
            _tmpEntries = _stmt.getLong(_columnIndexOfEntries).toInt()
            val _tmpPack: List<String>?
            val _tmp_1: String?
            if (_stmt.isNull(_columnIndexOfPack)) {
              _tmp_1 = null
            } else {
              _tmp_1 = _stmt.getText(_columnIndexOfPack)
            }
            val _tmp_2: List<String> = __converters.stringToList(_tmp_1)
            _tmpPack = _tmp_2
            val _tmpLevel: List<Int>?
            val _tmp_3: String?
            if (_stmt.isNull(_columnIndexOfLevel)) {
              _tmp_3 = null
            } else {
              _tmp_3 = _stmt.getText(_columnIndexOfLevel)
            }
            val _tmp_4: List<Int> = __converters.intToList(_tmp_3)
            _tmpLevel = _tmp_4
            val _tmpSimpleTagId: Int
            _tmpSimpleTagId = _stmt.getLong(_columnIndexOfSimpleTagId).toInt()
            val _tmpIsSelected: Boolean
            val _tmp_5: Int
            _tmp_5 = _stmt.getLong(_columnIndexOfIsSelected).toInt()
            _tmpIsSelected = _tmp_5 != 0
            _item_1 = RethinkLocalFileTag(_tmpValue,_tmpUname,_tmpVname,_tmpGroup,_tmpSubg,_tmpPack,_tmpLevel,_tmpUrl,_tmpShow,_tmpEntries,_tmpSimpleTagId,_tmpIsSelected)
            _result.add(_item_1)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun fileTags(): List<FileTag> {
    val _sql: String = "select  value, uname, vname, `group`, subg, url as urls, show, entries, pack, level, simpleTagId, isSelected from RethinkLocalFileTag"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfValue: Int = 0
        val _columnIndexOfUname: Int = 1
        val _columnIndexOfVname: Int = 2
        val _columnIndexOfGroup: Int = 3
        val _columnIndexOfSubg: Int = 4
        val _columnIndexOfUrls: Int = 5
        val _columnIndexOfShow: Int = 6
        val _columnIndexOfEntries: Int = 7
        val _columnIndexOfPack: Int = 8
        val _columnIndexOfLevel: Int = 9
        val _columnIndexOfSimpleTagId: Int = 10
        val _columnIndexOfIsSelected: Int = 11
        val _result: MutableList<FileTag> = mutableListOf()
        while (_stmt.step()) {
          val _item: FileTag
          val _tmpValue: Int
          _tmpValue = _stmt.getLong(_columnIndexOfValue).toInt()
          val _tmpUname: String
          _tmpUname = _stmt.getText(_columnIndexOfUname)
          val _tmpVname: String
          _tmpVname = _stmt.getText(_columnIndexOfVname)
          val _tmpGroup: String
          _tmpGroup = _stmt.getText(_columnIndexOfGroup)
          val _tmpSubg: String
          _tmpSubg = _stmt.getText(_columnIndexOfSubg)
          val _tmpUrls: List<String>
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfUrls)
          _tmpUrls = __converters.stringToList(_tmp)
          val _tmpShow: Int
          _tmpShow = _stmt.getLong(_columnIndexOfShow).toInt()
          val _tmpEntries: Int
          _tmpEntries = _stmt.getLong(_columnIndexOfEntries).toInt()
          val _tmpPack: List<String>
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfPack)
          _tmpPack = __converters.stringToList(_tmp_1)
          val _tmpLevel: List<Int>?
          val _tmp_2: String?
          if (_stmt.isNull(_columnIndexOfLevel)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfLevel)
          }
          val _tmp_3: List<Int> = __converters.intToList(_tmp_2)
          _tmpLevel = _tmp_3
          val _tmpSimpleTagId: Int
          _tmpSimpleTagId = _stmt.getLong(_columnIndexOfSimpleTagId).toInt()
          val _tmpIsSelected: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfIsSelected).toInt()
          _tmpIsSelected = _tmp_4 != 0
          _item = FileTag(_tmpValue,_tmpUname,_tmpVname,_tmpGroup,_tmpSubg,_tmpUrls,_tmpShow,_tmpPack,_tmpLevel,_tmpEntries,_tmpSimpleTagId,_tmpIsSelected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getSelectedTags(): List<Int> {
    val _sql: String = "select value from RethinkLocalFileTag where isSelected = 1"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: MutableList<Int> = mutableListOf()
        while (_stmt.step()) {
          val _item: Int
          _item = _stmt.getLong(0).toInt()
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFileTags(): Cursor {
    val _sql: String = "select * from RethinkLocalFileTag order by `group`"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    val _tmpResult: Cursor = __db.query(_statement)
    return _tmpResult
  }

  public override fun contentDelete(id: Int): Int {
    val _sql: String = "delete from RethinkLocalFileTag where value = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateTags(list: Set<Int>, isSelected: Int) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("Update RethinkLocalFileTag set isSelected = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" where value in (")
    val _inputSize: Int = list.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") ")
    val _sql: String = _stringBuilder.toString()
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, isSelected.toLong())
        _argIndex = 2
        for (_item: Int in list) {
          _stmt.bindLong(_argIndex, _item.toLong())
          _argIndex++
        }
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateSelectedTag(`value`: Int, isSelected: Int) {
    val _sql: String = "Update RethinkLocalFileTag set isSelected = ? where value = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, isSelected.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, value.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearSelectedTags() {
    val _sql: String = "Update RethinkLocalFileTag set isSelected = 0"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteAll() {
    val _sql: String = "delete from RethinkLocalFileTag"
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
