package com.celzero.bravedns.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CountryConfigDAO_Impl(
  __db: RoomDatabase,
) : CountryConfigDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfCountryConfig: EntityInsertAdapter<CountryConfig>

  private val __deleteAdapterOfCountryConfig: EntityDeleteOrUpdateAdapter<CountryConfig>

  private val __updateAdapterOfCountryConfig: EntityDeleteOrUpdateAdapter<CountryConfig>
  init {
    this.__db = __db
    this.__insertAdapterOfCountryConfig = object : EntityInsertAdapter<CountryConfig>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `CountryConfig` (`id`,`cc`,`name`,`address`,`city`,`key`,`load`,`link`,`count`,`premium`,`isActive`,`isEnabled`,`catchAll`,`lockdown`,`mobileOnly`,`ssidBased`,`priority`,`ssids`,`lastModified`,`selectionCount`,`isFavourite`,`hopEnabled`,`ipv4`,`ipv6`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CountryConfig) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.cc)
        statement.bindText(3, entity.name)
        statement.bindText(4, entity.address)
        statement.bindText(5, entity.city)
        statement.bindText(6, entity.key)
        statement.bindLong(7, entity.load.toLong())
        statement.bindLong(8, entity.link.toLong())
        statement.bindLong(9, entity.count.toLong())
        val _tmp: Int = if (entity.premium) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        val _tmp_1: Int = if (entity.isActive) 1 else 0
        statement.bindLong(11, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isEnabled) 1 else 0
        statement.bindLong(12, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.catchAll) 1 else 0
        statement.bindLong(13, _tmp_3.toLong())
        val _tmp_4: Int = if (entity.lockdown) 1 else 0
        statement.bindLong(14, _tmp_4.toLong())
        val _tmp_5: Int = if (entity.mobileOnly) 1 else 0
        statement.bindLong(15, _tmp_5.toLong())
        val _tmp_6: Int = if (entity.ssidBased) 1 else 0
        statement.bindLong(16, _tmp_6.toLong())
        statement.bindLong(17, entity.priority.toLong())
        statement.bindText(18, entity.ssids)
        statement.bindLong(19, entity.lastModified)
        statement.bindLong(20, entity.selectionCount.toLong())
        val _tmp_7: Int = if (entity.isFavourite) 1 else 0
        statement.bindLong(21, _tmp_7.toLong())
        val _tmp_8: Int = if (entity.hopEnabled) 1 else 0
        statement.bindLong(22, _tmp_8.toLong())
        val _tmp_9: Int = if (entity.ipv4) 1 else 0
        statement.bindLong(23, _tmp_9.toLong())
        val _tmp_10: Int = if (entity.ipv6) 1 else 0
        statement.bindLong(24, _tmp_10.toLong())
      }
    }
    this.__deleteAdapterOfCountryConfig = object : EntityDeleteOrUpdateAdapter<CountryConfig>() {
      protected override fun createQuery(): String = "DELETE FROM `CountryConfig` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CountryConfig) {
        statement.bindText(1, entity.id)
      }
    }
    this.__updateAdapterOfCountryConfig = object : EntityDeleteOrUpdateAdapter<CountryConfig>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `CountryConfig` SET `id` = ?,`cc` = ?,`name` = ?,`address` = ?,`city` = ?,`key` = ?,`load` = ?,`link` = ?,`count` = ?,`premium` = ?,`isActive` = ?,`isEnabled` = ?,`catchAll` = ?,`lockdown` = ?,`mobileOnly` = ?,`ssidBased` = ?,`priority` = ?,`ssids` = ?,`lastModified` = ?,`selectionCount` = ?,`isFavourite` = ?,`hopEnabled` = ?,`ipv4` = ?,`ipv6` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CountryConfig) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.cc)
        statement.bindText(3, entity.name)
        statement.bindText(4, entity.address)
        statement.bindText(5, entity.city)
        statement.bindText(6, entity.key)
        statement.bindLong(7, entity.load.toLong())
        statement.bindLong(8, entity.link.toLong())
        statement.bindLong(9, entity.count.toLong())
        val _tmp: Int = if (entity.premium) 1 else 0
        statement.bindLong(10, _tmp.toLong())
        val _tmp_1: Int = if (entity.isActive) 1 else 0
        statement.bindLong(11, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isEnabled) 1 else 0
        statement.bindLong(12, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.catchAll) 1 else 0
        statement.bindLong(13, _tmp_3.toLong())
        val _tmp_4: Int = if (entity.lockdown) 1 else 0
        statement.bindLong(14, _tmp_4.toLong())
        val _tmp_5: Int = if (entity.mobileOnly) 1 else 0
        statement.bindLong(15, _tmp_5.toLong())
        val _tmp_6: Int = if (entity.ssidBased) 1 else 0
        statement.bindLong(16, _tmp_6.toLong())
        statement.bindLong(17, entity.priority.toLong())
        statement.bindText(18, entity.ssids)
        statement.bindLong(19, entity.lastModified)
        statement.bindLong(20, entity.selectionCount.toLong())
        val _tmp_7: Int = if (entity.isFavourite) 1 else 0
        statement.bindLong(21, _tmp_7.toLong())
        val _tmp_8: Int = if (entity.hopEnabled) 1 else 0
        statement.bindLong(22, _tmp_8.toLong())
        val _tmp_9: Int = if (entity.ipv4) 1 else 0
        statement.bindLong(23, _tmp_9.toLong())
        val _tmp_10: Int = if (entity.ipv6) 1 else 0
        statement.bindLong(24, _tmp_10.toLong())
        statement.bindText(25, entity.id)
      }
    }
  }

  public override suspend fun insert(config: CountryConfig): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCountryConfig.insert(_connection, config)
  }

  public override suspend fun insertAll(configs: List<CountryConfig>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCountryConfig.insert(_connection, configs)
  }

  public override suspend fun delete(config: CountryConfig): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfCountryConfig.handle(_connection, config)
  }

  public override suspend fun update(config: CountryConfig): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfCountryConfig.handle(_connection, config)
  }

  public override suspend fun getAllConfigs(): List<CountryConfig> {
    val _sql: String = "SELECT * FROM CountryConfig"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCc: Int = getColumnIndexOrThrow(_stmt, "cc")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfCity: Int = getColumnIndexOrThrow(_stmt, "city")
        val _columnIndexOfKey: Int = getColumnIndexOrThrow(_stmt, "key")
        val _columnIndexOfLoad: Int = getColumnIndexOrThrow(_stmt, "load")
        val _columnIndexOfLink: Int = getColumnIndexOrThrow(_stmt, "link")
        val _columnIndexOfCount: Int = getColumnIndexOrThrow(_stmt, "count")
        val _columnIndexOfPremium: Int = getColumnIndexOrThrow(_stmt, "premium")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsEnabled: Int = getColumnIndexOrThrow(_stmt, "isEnabled")
        val _columnIndexOfCatchAll: Int = getColumnIndexOrThrow(_stmt, "catchAll")
        val _columnIndexOfLockdown: Int = getColumnIndexOrThrow(_stmt, "lockdown")
        val _columnIndexOfMobileOnly: Int = getColumnIndexOrThrow(_stmt, "mobileOnly")
        val _columnIndexOfSsidBased: Int = getColumnIndexOrThrow(_stmt, "ssidBased")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfSsids: Int = getColumnIndexOrThrow(_stmt, "ssids")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSelectionCount: Int = getColumnIndexOrThrow(_stmt, "selectionCount")
        val _columnIndexOfIsFavourite: Int = getColumnIndexOrThrow(_stmt, "isFavourite")
        val _columnIndexOfHopEnabled: Int = getColumnIndexOrThrow(_stmt, "hopEnabled")
        val _columnIndexOfIpv4: Int = getColumnIndexOrThrow(_stmt, "ipv4")
        val _columnIndexOfIpv6: Int = getColumnIndexOrThrow(_stmt, "ipv6")
        val _result: MutableList<CountryConfig> = mutableListOf()
        while (_stmt.step()) {
          val _item: CountryConfig
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCc: String
          _tmpCc = _stmt.getText(_columnIndexOfCc)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpAddress: String
          _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          val _tmpCity: String
          _tmpCity = _stmt.getText(_columnIndexOfCity)
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfKey)
          val _tmpLoad: Int
          _tmpLoad = _stmt.getLong(_columnIndexOfLoad).toInt()
          val _tmpLink: Int
          _tmpLink = _stmt.getLong(_columnIndexOfLink).toInt()
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          val _tmpPremium: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfPremium).toInt()
          _tmpPremium = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpIsEnabled: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsEnabled).toInt()
          _tmpIsEnabled = _tmp_2 != 0
          val _tmpCatchAll: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfCatchAll).toInt()
          _tmpCatchAll = _tmp_3 != 0
          val _tmpLockdown: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfLockdown).toInt()
          _tmpLockdown = _tmp_4 != 0
          val _tmpMobileOnly: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfMobileOnly).toInt()
          _tmpMobileOnly = _tmp_5 != 0
          val _tmpSsidBased: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfSsidBased).toInt()
          _tmpSsidBased = _tmp_6 != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpSsids: String
          _tmpSsids = _stmt.getText(_columnIndexOfSsids)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSelectionCount: Int
          _tmpSelectionCount = _stmt.getLong(_columnIndexOfSelectionCount).toInt()
          val _tmpIsFavourite: Boolean
          val _tmp_7: Int
          _tmp_7 = _stmt.getLong(_columnIndexOfIsFavourite).toInt()
          _tmpIsFavourite = _tmp_7 != 0
          val _tmpHopEnabled: Boolean
          val _tmp_8: Int
          _tmp_8 = _stmt.getLong(_columnIndexOfHopEnabled).toInt()
          _tmpHopEnabled = _tmp_8 != 0
          val _tmpIpv4: Boolean
          val _tmp_9: Int
          _tmp_9 = _stmt.getLong(_columnIndexOfIpv4).toInt()
          _tmpIpv4 = _tmp_9 != 0
          val _tmpIpv6: Boolean
          val _tmp_10: Int
          _tmp_10 = _stmt.getLong(_columnIndexOfIpv6).toInt()
          _tmpIpv6 = _tmp_10 != 0
          _item = CountryConfig(_tmpId,_tmpCc,_tmpName,_tmpAddress,_tmpCity,_tmpKey,_tmpLoad,_tmpLink,_tmpCount,_tmpPremium,_tmpIsActive,_tmpIsEnabled,_tmpCatchAll,_tmpLockdown,_tmpMobileOnly,_tmpSsidBased,_tmpPriority,_tmpSsids,_tmpLastModified,_tmpSelectionCount,_tmpIsFavourite,_tmpHopEnabled,_tmpIpv4,_tmpIpv6)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM CountryConfig"
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

  public override suspend fun getById(id: String): CountryConfig? {
    val _sql: String = "SELECT * FROM CountryConfig WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCc: Int = getColumnIndexOrThrow(_stmt, "cc")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfCity: Int = getColumnIndexOrThrow(_stmt, "city")
        val _columnIndexOfKey: Int = getColumnIndexOrThrow(_stmt, "key")
        val _columnIndexOfLoad: Int = getColumnIndexOrThrow(_stmt, "load")
        val _columnIndexOfLink: Int = getColumnIndexOrThrow(_stmt, "link")
        val _columnIndexOfCount: Int = getColumnIndexOrThrow(_stmt, "count")
        val _columnIndexOfPremium: Int = getColumnIndexOrThrow(_stmt, "premium")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsEnabled: Int = getColumnIndexOrThrow(_stmt, "isEnabled")
        val _columnIndexOfCatchAll: Int = getColumnIndexOrThrow(_stmt, "catchAll")
        val _columnIndexOfLockdown: Int = getColumnIndexOrThrow(_stmt, "lockdown")
        val _columnIndexOfMobileOnly: Int = getColumnIndexOrThrow(_stmt, "mobileOnly")
        val _columnIndexOfSsidBased: Int = getColumnIndexOrThrow(_stmt, "ssidBased")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfSsids: Int = getColumnIndexOrThrow(_stmt, "ssids")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSelectionCount: Int = getColumnIndexOrThrow(_stmt, "selectionCount")
        val _columnIndexOfIsFavourite: Int = getColumnIndexOrThrow(_stmt, "isFavourite")
        val _columnIndexOfHopEnabled: Int = getColumnIndexOrThrow(_stmt, "hopEnabled")
        val _columnIndexOfIpv4: Int = getColumnIndexOrThrow(_stmt, "ipv4")
        val _columnIndexOfIpv6: Int = getColumnIndexOrThrow(_stmt, "ipv6")
        val _result: CountryConfig?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCc: String
          _tmpCc = _stmt.getText(_columnIndexOfCc)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpAddress: String
          _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          val _tmpCity: String
          _tmpCity = _stmt.getText(_columnIndexOfCity)
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfKey)
          val _tmpLoad: Int
          _tmpLoad = _stmt.getLong(_columnIndexOfLoad).toInt()
          val _tmpLink: Int
          _tmpLink = _stmt.getLong(_columnIndexOfLink).toInt()
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          val _tmpPremium: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfPremium).toInt()
          _tmpPremium = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpIsEnabled: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsEnabled).toInt()
          _tmpIsEnabled = _tmp_2 != 0
          val _tmpCatchAll: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfCatchAll).toInt()
          _tmpCatchAll = _tmp_3 != 0
          val _tmpLockdown: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfLockdown).toInt()
          _tmpLockdown = _tmp_4 != 0
          val _tmpMobileOnly: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfMobileOnly).toInt()
          _tmpMobileOnly = _tmp_5 != 0
          val _tmpSsidBased: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfSsidBased).toInt()
          _tmpSsidBased = _tmp_6 != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpSsids: String
          _tmpSsids = _stmt.getText(_columnIndexOfSsids)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSelectionCount: Int
          _tmpSelectionCount = _stmt.getLong(_columnIndexOfSelectionCount).toInt()
          val _tmpIsFavourite: Boolean
          val _tmp_7: Int
          _tmp_7 = _stmt.getLong(_columnIndexOfIsFavourite).toInt()
          _tmpIsFavourite = _tmp_7 != 0
          val _tmpHopEnabled: Boolean
          val _tmp_8: Int
          _tmp_8 = _stmt.getLong(_columnIndexOfHopEnabled).toInt()
          _tmpHopEnabled = _tmp_8 != 0
          val _tmpIpv4: Boolean
          val _tmp_9: Int
          _tmp_9 = _stmt.getLong(_columnIndexOfIpv4).toInt()
          _tmpIpv4 = _tmp_9 != 0
          val _tmpIpv6: Boolean
          val _tmp_10: Int
          _tmp_10 = _stmt.getLong(_columnIndexOfIpv6).toInt()
          _tmpIpv6 = _tmp_10 != 0
          _result = CountryConfig(_tmpId,_tmpCc,_tmpName,_tmpAddress,_tmpCity,_tmpKey,_tmpLoad,_tmpLink,_tmpCount,_tmpPremium,_tmpIsActive,_tmpIsEnabled,_tmpCatchAll,_tmpLockdown,_tmpMobileOnly,_tmpSsidBased,_tmpPriority,_tmpSsids,_tmpLastModified,_tmpSelectionCount,_tmpIsFavourite,_tmpHopEnabled,_tmpIpv4,_tmpIpv6)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getFavouriteConfigs(): List<CountryConfig> {
    val _sql: String = "SELECT * FROM CountryConfig WHERE isFavourite = 1 AND isActive = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCc: Int = getColumnIndexOrThrow(_stmt, "cc")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfAddress: Int = getColumnIndexOrThrow(_stmt, "address")
        val _columnIndexOfCity: Int = getColumnIndexOrThrow(_stmt, "city")
        val _columnIndexOfKey: Int = getColumnIndexOrThrow(_stmt, "key")
        val _columnIndexOfLoad: Int = getColumnIndexOrThrow(_stmt, "load")
        val _columnIndexOfLink: Int = getColumnIndexOrThrow(_stmt, "link")
        val _columnIndexOfCount: Int = getColumnIndexOrThrow(_stmt, "count")
        val _columnIndexOfPremium: Int = getColumnIndexOrThrow(_stmt, "premium")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _columnIndexOfIsEnabled: Int = getColumnIndexOrThrow(_stmt, "isEnabled")
        val _columnIndexOfCatchAll: Int = getColumnIndexOrThrow(_stmt, "catchAll")
        val _columnIndexOfLockdown: Int = getColumnIndexOrThrow(_stmt, "lockdown")
        val _columnIndexOfMobileOnly: Int = getColumnIndexOrThrow(_stmt, "mobileOnly")
        val _columnIndexOfSsidBased: Int = getColumnIndexOrThrow(_stmt, "ssidBased")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfSsids: Int = getColumnIndexOrThrow(_stmt, "ssids")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _columnIndexOfSelectionCount: Int = getColumnIndexOrThrow(_stmt, "selectionCount")
        val _columnIndexOfIsFavourite: Int = getColumnIndexOrThrow(_stmt, "isFavourite")
        val _columnIndexOfHopEnabled: Int = getColumnIndexOrThrow(_stmt, "hopEnabled")
        val _columnIndexOfIpv4: Int = getColumnIndexOrThrow(_stmt, "ipv4")
        val _columnIndexOfIpv6: Int = getColumnIndexOrThrow(_stmt, "ipv6")
        val _result: MutableList<CountryConfig> = mutableListOf()
        while (_stmt.step()) {
          val _item: CountryConfig
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCc: String
          _tmpCc = _stmt.getText(_columnIndexOfCc)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpAddress: String
          _tmpAddress = _stmt.getText(_columnIndexOfAddress)
          val _tmpCity: String
          _tmpCity = _stmt.getText(_columnIndexOfCity)
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfKey)
          val _tmpLoad: Int
          _tmpLoad = _stmt.getLong(_columnIndexOfLoad).toInt()
          val _tmpLink: Int
          _tmpLink = _stmt.getLong(_columnIndexOfLink).toInt()
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          val _tmpPremium: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfPremium).toInt()
          _tmpPremium = _tmp != 0
          val _tmpIsActive: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_1 != 0
          val _tmpIsEnabled: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsEnabled).toInt()
          _tmpIsEnabled = _tmp_2 != 0
          val _tmpCatchAll: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfCatchAll).toInt()
          _tmpCatchAll = _tmp_3 != 0
          val _tmpLockdown: Boolean
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfLockdown).toInt()
          _tmpLockdown = _tmp_4 != 0
          val _tmpMobileOnly: Boolean
          val _tmp_5: Int
          _tmp_5 = _stmt.getLong(_columnIndexOfMobileOnly).toInt()
          _tmpMobileOnly = _tmp_5 != 0
          val _tmpSsidBased: Boolean
          val _tmp_6: Int
          _tmp_6 = _stmt.getLong(_columnIndexOfSsidBased).toInt()
          _tmpSsidBased = _tmp_6 != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpSsids: String
          _tmpSsids = _stmt.getText(_columnIndexOfSsids)
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpSelectionCount: Int
          _tmpSelectionCount = _stmt.getLong(_columnIndexOfSelectionCount).toInt()
          val _tmpIsFavourite: Boolean
          val _tmp_7: Int
          _tmp_7 = _stmt.getLong(_columnIndexOfIsFavourite).toInt()
          _tmpIsFavourite = _tmp_7 != 0
          val _tmpHopEnabled: Boolean
          val _tmp_8: Int
          _tmp_8 = _stmt.getLong(_columnIndexOfHopEnabled).toInt()
          _tmpHopEnabled = _tmp_8 != 0
          val _tmpIpv4: Boolean
          val _tmp_9: Int
          _tmp_9 = _stmt.getLong(_columnIndexOfIpv4).toInt()
          _tmpIpv4 = _tmp_9 != 0
          val _tmpIpv6: Boolean
          val _tmp_10: Int
          _tmp_10 = _stmt.getLong(_columnIndexOfIpv6).toInt()
          _tmpIpv6 = _tmp_10 != 0
          _item = CountryConfig(_tmpId,_tmpCc,_tmpName,_tmpAddress,_tmpCity,_tmpKey,_tmpLoad,_tmpLink,_tmpCount,_tmpPremium,_tmpIsActive,_tmpIsEnabled,_tmpCatchAll,_tmpLockdown,_tmpMobileOnly,_tmpSsidBased,_tmpPriority,_tmpSsids,_tmpLastModified,_tmpSelectionCount,_tmpIsFavourite,_tmpHopEnabled,_tmpIpv4,_tmpIpv6)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTopFrequentCcs(limit: Int): List<String> {
    val _sql: String = "SELECT cc FROM CountryConfig WHERE id != 'AUTO' AND isActive = 1 AND selectionCount > 0 GROUP BY cc ORDER BY MAX(selectionCount) DESC LIMIT ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, limit.toLong())
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

  public override suspend fun updateSsidBased(
    key: String,
    `value`: Boolean,
    timestamp: Long,
  ) {
    val _sql: String = "UPDATE CountryConfig SET ssidBased = ?, lastModified = ? WHERE `key` = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (value) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 3
        _stmt.bindText(_argIndex, key)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateServer(
    id: String,
    name: String,
    address: String,
    city: String,
    key: String,
    load: Int,
    link: Int,
    count: Int,
    isActive: Boolean,
    ipv4: Boolean,
    ipv6: Boolean,
    timestamp: Long,
  ) {
    val _sql: String = "UPDATE CountryConfig SET name = ?, address = ?, city = ?, `key` = ?, load = ?, link = ?, count = ?, isActive = ?, ipv4 = ?, ipv6 = ?, lastModified = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, name)
        _argIndex = 2
        _stmt.bindText(_argIndex, address)
        _argIndex = 3
        _stmt.bindText(_argIndex, city)
        _argIndex = 4
        _stmt.bindText(_argIndex, key)
        _argIndex = 5
        _stmt.bindLong(_argIndex, load.toLong())
        _argIndex = 6
        _stmt.bindLong(_argIndex, link.toLong())
        _argIndex = 7
        _stmt.bindLong(_argIndex, count.toLong())
        _argIndex = 8
        val _tmp: Int = if (isActive) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 9
        val _tmp_1: Int = if (ipv4) 1 else 0
        _stmt.bindLong(_argIndex, _tmp_1.toLong())
        _argIndex = 10
        val _tmp_2: Int = if (ipv6) 1 else 0
        _stmt.bindLong(_argIndex, _tmp_2.toLong())
        _argIndex = 11
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 12
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateSsids(
    key: String,
    ssids: String,
    timestamp: Long,
  ) {
    val _sql: String = "UPDATE CountryConfig SET ssids = ?, lastModified = ? WHERE `key` = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, ssids)
        _argIndex = 2
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 3
        _stmt.bindText(_argIndex, key)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun incrementSelectionCount(key: String) {
    val _sql: String = "UPDATE CountryConfig SET selectionCount = selectionCount + 1 WHERE `key` = ? AND id != 'AUTO'"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, key)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateFavouriteByCountryCode(cc: String, `value`: Boolean) {
    val _sql: String = "UPDATE CountryConfig SET isFavourite = ? WHERE cc = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (value) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, cc)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun resetAllIsEnabled() {
    val _sql: String = "UPDATE CountryConfig SET isEnabled = 0 WHERE id != 'AUTO'"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun resetAllIsFavourite() {
    val _sql: String = "UPDATE CountryConfig SET isFavourite = 0"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun resetAllSelectionCounts() {
    val _sql: String = "UPDATE CountryConfig SET selectionCount = 0 WHERE id != 'AUTO'"
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
