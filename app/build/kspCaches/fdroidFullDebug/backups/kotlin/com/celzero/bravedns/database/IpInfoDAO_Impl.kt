package com.celzero.bravedns.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performBlocking
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class IpInfoDAO_Impl(
  __db: RoomDatabase,
) : IpInfoDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfIpInfo: EntityInsertAdapter<IpInfo>

  private val __insertAdapterOfIpInfo_1: EntityInsertAdapter<IpInfo>

  private val __deleteAdapterOfIpInfo: EntityDeleteOrUpdateAdapter<IpInfo>

  private val __updateAdapterOfIpInfo: EntityDeleteOrUpdateAdapter<IpInfo>
  init {
    this.__db = __db
    this.__insertAdapterOfIpInfo = object : EntityInsertAdapter<IpInfo>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `IpInfo` (`ip`,`asn`,`asName`,`asDomain`,`countryCode`,`country`,`continentCode`,`continent`,`createdTs`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: IpInfo) {
        statement.bindText(1, entity.ip)
        statement.bindText(2, entity.asn)
        statement.bindText(3, entity.asName)
        statement.bindText(4, entity.asDomain)
        statement.bindText(5, entity.countryCode)
        statement.bindText(6, entity.country)
        statement.bindText(7, entity.continentCode)
        statement.bindText(8, entity.continent)
        statement.bindLong(9, entity.createdTs)
      }
    }
    this.__insertAdapterOfIpInfo_1 = object : EntityInsertAdapter<IpInfo>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `IpInfo` (`ip`,`asn`,`asName`,`asDomain`,`countryCode`,`country`,`continentCode`,`continent`,`createdTs`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: IpInfo) {
        statement.bindText(1, entity.ip)
        statement.bindText(2, entity.asn)
        statement.bindText(3, entity.asName)
        statement.bindText(4, entity.asDomain)
        statement.bindText(5, entity.countryCode)
        statement.bindText(6, entity.country)
        statement.bindText(7, entity.continentCode)
        statement.bindText(8, entity.continent)
        statement.bindLong(9, entity.createdTs)
      }
    }
    this.__deleteAdapterOfIpInfo = object : EntityDeleteOrUpdateAdapter<IpInfo>() {
      protected override fun createQuery(): String = "DELETE FROM `IpInfo` WHERE `ip` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: IpInfo) {
        statement.bindText(1, entity.ip)
      }
    }
    this.__updateAdapterOfIpInfo = object : EntityDeleteOrUpdateAdapter<IpInfo>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `IpInfo` SET `ip` = ?,`asn` = ?,`asName` = ?,`asDomain` = ?,`countryCode` = ?,`country` = ?,`continentCode` = ?,`continent` = ?,`createdTs` = ? WHERE `ip` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: IpInfo) {
        statement.bindText(1, entity.ip)
        statement.bindText(2, entity.asn)
        statement.bindText(3, entity.asName)
        statement.bindText(4, entity.asDomain)
        statement.bindText(5, entity.countryCode)
        statement.bindText(6, entity.country)
        statement.bindText(7, entity.continentCode)
        statement.bindText(8, entity.continent)
        statement.bindLong(9, entity.createdTs)
        statement.bindText(10, entity.ip)
      }
    }
  }

  public override fun insert(ipInfo: IpInfo): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfIpInfo.insert(_connection, ipInfo)
  }

  public override fun insertReplace(ipInfo: IpInfo): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfIpInfo_1.insert(_connection, ipInfo)
  }

  public override fun delete(ipInfo: IpInfo): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfIpInfo.handle(_connection, ipInfo)
  }

  public override fun update(ipInfo: IpInfo): Unit = performBlocking(__db, false, true) { _connection ->
    __updateAdapterOfIpInfo.handle(_connection, ipInfo)
  }

  public override fun getIpInfo(ipStr: String): IpInfo? {
    val _sql: String = "SELECT * FROM IpInfo WHERE ip = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, ipStr)
        val _columnIndexOfIp: Int = getColumnIndexOrThrow(_stmt, "ip")
        val _columnIndexOfAsn: Int = getColumnIndexOrThrow(_stmt, "asn")
        val _columnIndexOfAsName: Int = getColumnIndexOrThrow(_stmt, "asName")
        val _columnIndexOfAsDomain: Int = getColumnIndexOrThrow(_stmt, "asDomain")
        val _columnIndexOfCountryCode: Int = getColumnIndexOrThrow(_stmt, "countryCode")
        val _columnIndexOfCountry: Int = getColumnIndexOrThrow(_stmt, "country")
        val _columnIndexOfContinentCode: Int = getColumnIndexOrThrow(_stmt, "continentCode")
        val _columnIndexOfContinent: Int = getColumnIndexOrThrow(_stmt, "continent")
        val _columnIndexOfCreatedTs: Int = getColumnIndexOrThrow(_stmt, "createdTs")
        val _result: IpInfo?
        if (_stmt.step()) {
          _result = IpInfo()
          _result.ip = _stmt.getText(_columnIndexOfIp)
          _result.asn = _stmt.getText(_columnIndexOfAsn)
          _result.asName = _stmt.getText(_columnIndexOfAsName)
          _result.asDomain = _stmt.getText(_columnIndexOfAsDomain)
          _result.countryCode = _stmt.getText(_columnIndexOfCountryCode)
          _result.country = _stmt.getText(_columnIndexOfCountry)
          _result.continentCode = _stmt.getText(_columnIndexOfContinentCode)
          _result.continent = _stmt.getText(_columnIndexOfContinent)
          _result.createdTs = _stmt.getLong(_columnIndexOfCreatedTs)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
