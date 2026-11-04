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
import com.celzero.bravedns.`data`.DataUsage
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
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
public class AppInfoDAO_Impl(
  __db: RoomDatabase,
) : AppInfoDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfAppInfo: EntityInsertAdapter<AppInfo>

  private val __deleteAdapterOfAppInfo: EntityDeleteOrUpdateAdapter<AppInfo>

  private val __updateAdapterOfAppInfo: EntityDeleteOrUpdateAdapter<AppInfo>
  init {
    this.__db = __db
    this.__insertAdapterOfAppInfo = object : EntityInsertAdapter<AppInfo>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `AppInfo` (`packageName`,`appName`,`uid`,`isSystemApp`,`firewallStatus`,`appCategory`,`wifiDataUsed`,`mobileDataUsed`,`connectionStatus`,`screenOffAllowed`,`backgroundAllowed`,`uploadBytes`,`downloadBytes`,`isProxyExcluded`,`tombstoneTs`,`modifiedTs`,`tempAllowEnabled`,`tempAllowExpiryTime`,`notes`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AppInfo) {
        statement.bindText(1, entity.packageName)
        statement.bindText(2, entity.appName)
        statement.bindLong(3, entity.uid.toLong())
        val _tmp: Int = if (entity.isSystemApp) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindLong(5, entity.firewallStatus.toLong())
        statement.bindText(6, entity.appCategory)
        statement.bindLong(7, entity.wifiDataUsed)
        statement.bindLong(8, entity.mobileDataUsed)
        statement.bindLong(9, entity.connectionStatus.toLong())
        val _tmp_1: Int = if (entity.screenOffAllowed) 1 else 0
        statement.bindLong(10, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.backgroundAllowed) 1 else 0
        statement.bindLong(11, _tmp_2.toLong())
        statement.bindLong(12, entity.uploadBytes)
        statement.bindLong(13, entity.downloadBytes)
        val _tmp_3: Int = if (entity.isProxyExcluded) 1 else 0
        statement.bindLong(14, _tmp_3.toLong())
        statement.bindLong(15, entity.tombstoneTs)
        statement.bindLong(16, entity.modifiedTs)
        val _tmp_4: Int = if (entity.tempAllowEnabled) 1 else 0
        statement.bindLong(17, _tmp_4.toLong())
        statement.bindLong(18, entity.tempAllowExpiryTime)
        statement.bindText(19, entity.notes)
      }
    }
    this.__deleteAdapterOfAppInfo = object : EntityDeleteOrUpdateAdapter<AppInfo>() {
      protected override fun createQuery(): String = "DELETE FROM `AppInfo` WHERE `uid` = ? AND `packageName` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AppInfo) {
        statement.bindLong(1, entity.uid.toLong())
        statement.bindText(2, entity.packageName)
      }
    }
    this.__updateAdapterOfAppInfo = object : EntityDeleteOrUpdateAdapter<AppInfo>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `AppInfo` SET `packageName` = ?,`appName` = ?,`uid` = ?,`isSystemApp` = ?,`firewallStatus` = ?,`appCategory` = ?,`wifiDataUsed` = ?,`mobileDataUsed` = ?,`connectionStatus` = ?,`screenOffAllowed` = ?,`backgroundAllowed` = ?,`uploadBytes` = ?,`downloadBytes` = ?,`isProxyExcluded` = ?,`tombstoneTs` = ?,`modifiedTs` = ?,`tempAllowEnabled` = ?,`tempAllowExpiryTime` = ?,`notes` = ? WHERE `uid` = ? AND `packageName` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AppInfo) {
        statement.bindText(1, entity.packageName)
        statement.bindText(2, entity.appName)
        statement.bindLong(3, entity.uid.toLong())
        val _tmp: Int = if (entity.isSystemApp) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindLong(5, entity.firewallStatus.toLong())
        statement.bindText(6, entity.appCategory)
        statement.bindLong(7, entity.wifiDataUsed)
        statement.bindLong(8, entity.mobileDataUsed)
        statement.bindLong(9, entity.connectionStatus.toLong())
        val _tmp_1: Int = if (entity.screenOffAllowed) 1 else 0
        statement.bindLong(10, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.backgroundAllowed) 1 else 0
        statement.bindLong(11, _tmp_2.toLong())
        statement.bindLong(12, entity.uploadBytes)
        statement.bindLong(13, entity.downloadBytes)
        val _tmp_3: Int = if (entity.isProxyExcluded) 1 else 0
        statement.bindLong(14, _tmp_3.toLong())
        statement.bindLong(15, entity.tombstoneTs)
        statement.bindLong(16, entity.modifiedTs)
        val _tmp_4: Int = if (entity.tempAllowEnabled) 1 else 0
        statement.bindLong(17, _tmp_4.toLong())
        statement.bindLong(18, entity.tempAllowExpiryTime)
        statement.bindText(19, entity.notes)
        statement.bindLong(20, entity.uid.toLong())
        statement.bindText(21, entity.packageName)
      }
    }
  }

  public override fun insert(appInfo: AppInfo): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfAppInfo.insertAndReturnId(_connection, appInfo)
    _result
  }

  public override fun delete(appInfo: AppInfo): Unit = performBlocking(__db, false, true) { _connection ->
    __deleteAdapterOfAppInfo.handle(_connection, appInfo)
  }

  public override fun update(appInfo: AppInfo): Int = performBlocking(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfAppInfo.handle(_connection, appInfo)
    _result
  }

  public override fun tombstoneAppWithPkg(
    newUid: Int,
    uid: Int,
    packageName: String,
    tombstoneTs: Long,
    modifiedTs: Long,
  ): Unit = performBlocking(__db, false, true) { _ ->
    super@AppInfoDAO_Impl.tombstoneAppWithPkg(newUid, uid, packageName, tombstoneTs, modifiedTs)
  }

  public override fun tombstoneAppByUid(
    oldUid: Int,
    newUid: Int,
    tombstoneTs: Long,
    modifiedTs: Long,
  ): Unit = performBlocking(__db, false, true) { _ ->
    super@AppInfoDAO_Impl.tombstoneAppByUid(oldUid, newUid, tombstoneTs, modifiedTs)
  }

  public override suspend fun isUidPkgExist(uid: Int, pkg: String): AppInfo? {
    val _sql: String = "select * from AppInfo where uid = ? and packageName = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, pkg)
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
        val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
        val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
        val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
        val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
        val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
        val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
        val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
        val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
        val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
        val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
        val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
        val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
        val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
        val _result: AppInfo?
        if (_stmt.step()) {
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpIsSystemApp: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
          _tmpIsSystemApp = _tmp != 0
          val _tmpFirewallStatus: Int
          _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
          val _tmpAppCategory: String
          _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
          val _tmpWifiDataUsed: Long
          _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
          val _tmpMobileDataUsed: Long
          _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
          val _tmpConnectionStatus: Int
          _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
          val _tmpScreenOffAllowed: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
          _tmpScreenOffAllowed = _tmp_1 != 0
          val _tmpBackgroundAllowed: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
          _tmpBackgroundAllowed = _tmp_2 != 0
          val _tmpIsProxyExcluded: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
          _tmpIsProxyExcluded = _tmp_3 != 0
          val _tmpTombstoneTs: Long
          _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
          val _tmpNotes: String
          _tmpNotes = _stmt.getText(_columnIndexOfNotes)
          _result = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
          _result.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _result.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _result.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
          _result.tempAllowEnabled = _tmp_4 != 0
          _result.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAppInfoByUid(uid: Int): AppInfo? {
    val _sql: String = "select * from AppInfo where uid = ? limit 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
        val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
        val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
        val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
        val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
        val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
        val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
        val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
        val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
        val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
        val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
        val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
        val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
        val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
        val _result: AppInfo?
        if (_stmt.step()) {
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpIsSystemApp: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
          _tmpIsSystemApp = _tmp != 0
          val _tmpFirewallStatus: Int
          _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
          val _tmpAppCategory: String
          _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
          val _tmpWifiDataUsed: Long
          _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
          val _tmpMobileDataUsed: Long
          _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
          val _tmpConnectionStatus: Int
          _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
          val _tmpScreenOffAllowed: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
          _tmpScreenOffAllowed = _tmp_1 != 0
          val _tmpBackgroundAllowed: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
          _tmpBackgroundAllowed = _tmp_2 != 0
          val _tmpIsProxyExcluded: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
          _tmpIsProxyExcluded = _tmp_3 != 0
          val _tmpTombstoneTs: Long
          _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
          val _tmpNotes: String
          _tmpNotes = _stmt.getText(_columnIndexOfNotes)
          _result = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
          _result.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _result.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _result.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
          _result.tempAllowEnabled = _tmp_4 != 0
          _result.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllAppDetails(): List<AppInfo> {
    val _sql: String = "select * from AppInfo order by appCategory, uid"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
        val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
        val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
        val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
        val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
        val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
        val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
        val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
        val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
        val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
        val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
        val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
        val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
        val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
        val _result: MutableList<AppInfo> = mutableListOf()
        while (_stmt.step()) {
          val _item: AppInfo
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpIsSystemApp: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
          _tmpIsSystemApp = _tmp != 0
          val _tmpFirewallStatus: Int
          _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
          val _tmpAppCategory: String
          _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
          val _tmpWifiDataUsed: Long
          _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
          val _tmpMobileDataUsed: Long
          _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
          val _tmpConnectionStatus: Int
          _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
          val _tmpScreenOffAllowed: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
          _tmpScreenOffAllowed = _tmp_1 != 0
          val _tmpBackgroundAllowed: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
          _tmpBackgroundAllowed = _tmp_2 != 0
          val _tmpIsProxyExcluded: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
          _tmpIsProxyExcluded = _tmp_3 != 0
          val _tmpTombstoneTs: Long
          _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
          val _tmpNotes: String
          _tmpNotes = _stmt.getText(_columnIndexOfNotes)
          _item = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
          _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _item.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
          _item.tempAllowEnabled = _tmp_4 != 0
          _item.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getSystemApps(
    search: String,
    firewall: Set<Int>,
    connectionStatus: Set<Int>,
    isProxyExcluded: Set<Int>,
    sort: String,
  ): PagingSource<Int, AppInfo> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from AppInfo where isSystemApp = 1 and (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or uid like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or packageName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and (firewallStatus in (")
    val _inputSize: Int = firewall.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") or isProxyExcluded in (")
    val _inputSize_1: Int = isProxyExcluded.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(")) and connectionStatus in (")
    val _inputSize_2: Int = connectionStatus.size
    appendPlaceholders(_stringBuilder, _inputSize_2)
    _stringBuilder.append(") order by case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'name' then lower(appName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'package' then lower(packageName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'uid' then uid end asc, lower(appName) asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, search)
      _argIndex = 2
      _stmt.bindText(_argIndex, search)
      _argIndex = 3
      _stmt.bindText(_argIndex, search)
      _argIndex = 4
      for (_item: Int in firewall) {
        _stmt.bindLong(_argIndex, _item.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize
      for (_item_1: Int in isProxyExcluded) {
        _stmt.bindLong(_argIndex, _item_1.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1
      for (_item_2: Int in connectionStatus) {
        _stmt.bindLong(_argIndex, _item_2.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2
      _stmt.bindText(_argIndex, sort)
      _argIndex = 5 + _inputSize + _inputSize_1 + _inputSize_2
      _stmt.bindText(_argIndex, sort)
      _argIndex = 6 + _inputSize + _inputSize_1 + _inputSize_2
      _stmt.bindText(_argIndex, sort)
    }
    return object : LimitOffsetPagingSource<AppInfo>(_rawQuery, __db, "AppInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppInfo> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
          val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
          val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
          val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
          val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
          val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
          val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
          val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
          val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
          val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
          val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
          val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
          val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
          val _result: MutableList<AppInfo> = mutableListOf()
          while (_stmt.step()) {
            val _item_3: AppInfo
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIsSystemApp: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
            _tmpIsSystemApp = _tmp != 0
            val _tmpFirewallStatus: Int
            _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
            val _tmpAppCategory: String
            _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
            val _tmpWifiDataUsed: Long
            _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
            val _tmpMobileDataUsed: Long
            _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
            val _tmpConnectionStatus: Int
            _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
            val _tmpScreenOffAllowed: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
            _tmpScreenOffAllowed = _tmp_1 != 0
            val _tmpBackgroundAllowed: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
            _tmpBackgroundAllowed = _tmp_2 != 0
            val _tmpIsProxyExcluded: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
            _tmpIsProxyExcluded = _tmp_3 != 0
            val _tmpTombstoneTs: Long
            _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
            val _tmpNotes: String
            _tmpNotes = _stmt.getText(_columnIndexOfNotes)
            _item_3 = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
            _item_3.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_3.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_3.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
            _item_3.tempAllowEnabled = _tmp_4 != 0
            _item_3.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
            _result.add(_item_3)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getSystemApps(
    search: String,
    filter: Set<String>,
    firewall: Set<Int>,
    connectionStatus: Set<Int>,
    isProxyExcluded: Set<Int>,
    sort: String,
  ): PagingSource<Int, AppInfo> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from AppInfo where isSystemApp = 1 and (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or uid like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or packageName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and appCategory in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and (firewallStatus in (")
    val _inputSize_1: Int = firewall.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") or isProxyExcluded in (")
    val _inputSize_2: Int = isProxyExcluded.size
    appendPlaceholders(_stringBuilder, _inputSize_2)
    _stringBuilder.append(")) and connectionStatus in (")
    val _inputSize_3: Int = connectionStatus.size
    appendPlaceholders(_stringBuilder, _inputSize_3)
    _stringBuilder.append(") order by case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'name' then lower(appName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'package' then lower(packageName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'uid' then uid end asc, lower(appName) asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, search)
      _argIndex = 2
      _stmt.bindText(_argIndex, search)
      _argIndex = 3
      _stmt.bindText(_argIndex, search)
      _argIndex = 4
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
      _argIndex = 4 + _inputSize
      for (_item_1: Int in firewall) {
        _stmt.bindLong(_argIndex, _item_1.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1
      for (_item_2: Int in isProxyExcluded) {
        _stmt.bindLong(_argIndex, _item_2.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2
      for (_item_3: Int in connectionStatus) {
        _stmt.bindLong(_argIndex, _item_3.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
      _stmt.bindText(_argIndex, sort)
      _argIndex = 5 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
      _stmt.bindText(_argIndex, sort)
      _argIndex = 6 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
      _stmt.bindText(_argIndex, sort)
    }
    return object : LimitOffsetPagingSource<AppInfo>(_rawQuery, __db, "AppInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppInfo> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
          val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
          val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
          val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
          val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
          val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
          val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
          val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
          val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
          val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
          val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
          val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
          val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
          val _result: MutableList<AppInfo> = mutableListOf()
          while (_stmt.step()) {
            val _item_4: AppInfo
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIsSystemApp: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
            _tmpIsSystemApp = _tmp != 0
            val _tmpFirewallStatus: Int
            _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
            val _tmpAppCategory: String
            _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
            val _tmpWifiDataUsed: Long
            _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
            val _tmpMobileDataUsed: Long
            _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
            val _tmpConnectionStatus: Int
            _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
            val _tmpScreenOffAllowed: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
            _tmpScreenOffAllowed = _tmp_1 != 0
            val _tmpBackgroundAllowed: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
            _tmpBackgroundAllowed = _tmp_2 != 0
            val _tmpIsProxyExcluded: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
            _tmpIsProxyExcluded = _tmp_3 != 0
            val _tmpTombstoneTs: Long
            _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
            val _tmpNotes: String
            _tmpNotes = _stmt.getText(_columnIndexOfNotes)
            _item_4 = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
            _item_4.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_4.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_4.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
            _item_4.tempAllowEnabled = _tmp_4 != 0
            _item_4.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
            _result.add(_item_4)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getInstalledApps(
    search: String,
    firewall: Set<Int>,
    connectionStatus: Set<Int>,
    isProxyExcluded: Set<Int>,
    sort: String,
  ): PagingSource<Int, AppInfo> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from AppInfo where isSystemApp = 0 and (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or uid like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or packageName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and (firewallStatus in (")
    val _inputSize: Int = firewall.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") or isProxyExcluded in (")
    val _inputSize_1: Int = isProxyExcluded.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(")) and connectionStatus in (")
    val _inputSize_2: Int = connectionStatus.size
    appendPlaceholders(_stringBuilder, _inputSize_2)
    _stringBuilder.append(") order by case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'name' then lower(appName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'package' then lower(packageName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'uid' then uid end asc, lower(appName) asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, search)
      _argIndex = 2
      _stmt.bindText(_argIndex, search)
      _argIndex = 3
      _stmt.bindText(_argIndex, search)
      _argIndex = 4
      for (_item: Int in firewall) {
        _stmt.bindLong(_argIndex, _item.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize
      for (_item_1: Int in isProxyExcluded) {
        _stmt.bindLong(_argIndex, _item_1.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1
      for (_item_2: Int in connectionStatus) {
        _stmt.bindLong(_argIndex, _item_2.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2
      _stmt.bindText(_argIndex, sort)
      _argIndex = 5 + _inputSize + _inputSize_1 + _inputSize_2
      _stmt.bindText(_argIndex, sort)
      _argIndex = 6 + _inputSize + _inputSize_1 + _inputSize_2
      _stmt.bindText(_argIndex, sort)
    }
    return object : LimitOffsetPagingSource<AppInfo>(_rawQuery, __db, "AppInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppInfo> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
          val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
          val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
          val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
          val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
          val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
          val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
          val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
          val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
          val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
          val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
          val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
          val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
          val _result: MutableList<AppInfo> = mutableListOf()
          while (_stmt.step()) {
            val _item_3: AppInfo
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIsSystemApp: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
            _tmpIsSystemApp = _tmp != 0
            val _tmpFirewallStatus: Int
            _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
            val _tmpAppCategory: String
            _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
            val _tmpWifiDataUsed: Long
            _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
            val _tmpMobileDataUsed: Long
            _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
            val _tmpConnectionStatus: Int
            _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
            val _tmpScreenOffAllowed: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
            _tmpScreenOffAllowed = _tmp_1 != 0
            val _tmpBackgroundAllowed: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
            _tmpBackgroundAllowed = _tmp_2 != 0
            val _tmpIsProxyExcluded: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
            _tmpIsProxyExcluded = _tmp_3 != 0
            val _tmpTombstoneTs: Long
            _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
            val _tmpNotes: String
            _tmpNotes = _stmt.getText(_columnIndexOfNotes)
            _item_3 = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
            _item_3.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_3.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_3.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
            _item_3.tempAllowEnabled = _tmp_4 != 0
            _item_3.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
            _result.add(_item_3)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getInstalledApps(
    search: String,
    filter: Set<String>,
    firewall: Set<Int>,
    connectionStatus: Set<Int>,
    isProxyExcluded: Set<Int>,
    sort: String,
  ): PagingSource<Int, AppInfo> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from AppInfo where isSystemApp = 0 and (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or uid like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or packageName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and appCategory in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and (firewallStatus in (")
    val _inputSize_1: Int = firewall.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") or isProxyExcluded in (")
    val _inputSize_2: Int = isProxyExcluded.size
    appendPlaceholders(_stringBuilder, _inputSize_2)
    _stringBuilder.append(")) and connectionStatus in (")
    val _inputSize_3: Int = connectionStatus.size
    appendPlaceholders(_stringBuilder, _inputSize_3)
    _stringBuilder.append(") order by case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'name' then lower(appName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'package' then lower(packageName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'uid' then uid end asc, lower(appName) asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, search)
      _argIndex = 2
      _stmt.bindText(_argIndex, search)
      _argIndex = 3
      _stmt.bindText(_argIndex, search)
      _argIndex = 4
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
      _argIndex = 4 + _inputSize
      for (_item_1: Int in firewall) {
        _stmt.bindLong(_argIndex, _item_1.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1
      for (_item_2: Int in isProxyExcluded) {
        _stmt.bindLong(_argIndex, _item_2.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2
      for (_item_3: Int in connectionStatus) {
        _stmt.bindLong(_argIndex, _item_3.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
      _stmt.bindText(_argIndex, sort)
      _argIndex = 5 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
      _stmt.bindText(_argIndex, sort)
      _argIndex = 6 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
      _stmt.bindText(_argIndex, sort)
    }
    return object : LimitOffsetPagingSource<AppInfo>(_rawQuery, __db, "AppInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppInfo> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
          val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
          val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
          val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
          val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
          val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
          val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
          val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
          val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
          val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
          val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
          val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
          val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
          val _result: MutableList<AppInfo> = mutableListOf()
          while (_stmt.step()) {
            val _item_4: AppInfo
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIsSystemApp: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
            _tmpIsSystemApp = _tmp != 0
            val _tmpFirewallStatus: Int
            _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
            val _tmpAppCategory: String
            _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
            val _tmpWifiDataUsed: Long
            _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
            val _tmpMobileDataUsed: Long
            _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
            val _tmpConnectionStatus: Int
            _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
            val _tmpScreenOffAllowed: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
            _tmpScreenOffAllowed = _tmp_1 != 0
            val _tmpBackgroundAllowed: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
            _tmpBackgroundAllowed = _tmp_2 != 0
            val _tmpIsProxyExcluded: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
            _tmpIsProxyExcluded = _tmp_3 != 0
            val _tmpTombstoneTs: Long
            _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
            val _tmpNotes: String
            _tmpNotes = _stmt.getText(_columnIndexOfNotes)
            _item_4 = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
            _item_4.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_4.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_4.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
            _item_4.tempAllowEnabled = _tmp_4 != 0
            _item_4.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
            _result.add(_item_4)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAppInfos(
    search: String,
    firewall: Set<Int>,
    connectionStatus: Set<Int>,
    isProxyExcluded: Set<Int>,
    sort: String,
  ): PagingSource<Int, AppInfo> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from AppInfo where (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or uid like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or packageName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and (firewallStatus in (")
    val _inputSize: Int = firewall.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") or isProxyExcluded in (")
    val _inputSize_1: Int = isProxyExcluded.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(")) and connectionStatus in (")
    val _inputSize_2: Int = connectionStatus.size
    appendPlaceholders(_stringBuilder, _inputSize_2)
    _stringBuilder.append(") order by case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'name' then lower(appName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'package' then lower(packageName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'uid' then uid end asc, lower(appName) asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, search)
      _argIndex = 2
      _stmt.bindText(_argIndex, search)
      _argIndex = 3
      _stmt.bindText(_argIndex, search)
      _argIndex = 4
      for (_item: Int in firewall) {
        _stmt.bindLong(_argIndex, _item.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize
      for (_item_1: Int in isProxyExcluded) {
        _stmt.bindLong(_argIndex, _item_1.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1
      for (_item_2: Int in connectionStatus) {
        _stmt.bindLong(_argIndex, _item_2.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2
      _stmt.bindText(_argIndex, sort)
      _argIndex = 5 + _inputSize + _inputSize_1 + _inputSize_2
      _stmt.bindText(_argIndex, sort)
      _argIndex = 6 + _inputSize + _inputSize_1 + _inputSize_2
      _stmt.bindText(_argIndex, sort)
    }
    return object : LimitOffsetPagingSource<AppInfo>(_rawQuery, __db, "AppInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppInfo> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
          val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
          val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
          val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
          val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
          val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
          val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
          val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
          val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
          val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
          val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
          val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
          val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
          val _result: MutableList<AppInfo> = mutableListOf()
          while (_stmt.step()) {
            val _item_3: AppInfo
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIsSystemApp: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
            _tmpIsSystemApp = _tmp != 0
            val _tmpFirewallStatus: Int
            _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
            val _tmpAppCategory: String
            _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
            val _tmpWifiDataUsed: Long
            _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
            val _tmpMobileDataUsed: Long
            _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
            val _tmpConnectionStatus: Int
            _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
            val _tmpScreenOffAllowed: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
            _tmpScreenOffAllowed = _tmp_1 != 0
            val _tmpBackgroundAllowed: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
            _tmpBackgroundAllowed = _tmp_2 != 0
            val _tmpIsProxyExcluded: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
            _tmpIsProxyExcluded = _tmp_3 != 0
            val _tmpTombstoneTs: Long
            _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
            val _tmpNotes: String
            _tmpNotes = _stmt.getText(_columnIndexOfNotes)
            _item_3 = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
            _item_3.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_3.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_3.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
            _item_3.tempAllowEnabled = _tmp_4 != 0
            _item_3.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
            _result.add(_item_3)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getAppInfos(
    search: String,
    filter: Set<String>,
    firewall: Set<Int>,
    connectionStatus: Set<Int>,
    isProxyExcluded: Set<Int>,
    sort: String,
  ): PagingSource<Int, AppInfo> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from AppInfo where (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or uid like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or packageName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and appCategory in (")
    val _inputSize: Int = filter.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and (firewallStatus in (")
    val _inputSize_1: Int = firewall.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") or isProxyExcluded in (")
    val _inputSize_2: Int = isProxyExcluded.size
    appendPlaceholders(_stringBuilder, _inputSize_2)
    _stringBuilder.append(")) and connectionStatus in (")
    val _inputSize_3: Int = connectionStatus.size
    appendPlaceholders(_stringBuilder, _inputSize_3)
    _stringBuilder.append(") order by case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'name' then lower(appName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'package' then lower(packageName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'uid' then uid end asc, lower(appName) asc")
    val _sql: String = _stringBuilder.toString()
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, search)
      _argIndex = 2
      _stmt.bindText(_argIndex, search)
      _argIndex = 3
      _stmt.bindText(_argIndex, search)
      _argIndex = 4
      for (_item: String in filter) {
        _stmt.bindText(_argIndex, _item)
        _argIndex++
      }
      _argIndex = 4 + _inputSize
      for (_item_1: Int in firewall) {
        _stmt.bindLong(_argIndex, _item_1.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1
      for (_item_2: Int in isProxyExcluded) {
        _stmt.bindLong(_argIndex, _item_2.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2
      for (_item_3: Int in connectionStatus) {
        _stmt.bindLong(_argIndex, _item_3.toLong())
        _argIndex++
      }
      _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
      _stmt.bindText(_argIndex, sort)
      _argIndex = 5 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
      _stmt.bindText(_argIndex, sort)
      _argIndex = 6 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
      _stmt.bindText(_argIndex, sort)
    }
    return object : LimitOffsetPagingSource<AppInfo>(_rawQuery, __db, "AppInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppInfo> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
          val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
          val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
          val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
          val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
          val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
          val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
          val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
          val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
          val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
          val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
          val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
          val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
          val _result: MutableList<AppInfo> = mutableListOf()
          while (_stmt.step()) {
            val _item_4: AppInfo
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIsSystemApp: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
            _tmpIsSystemApp = _tmp != 0
            val _tmpFirewallStatus: Int
            _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
            val _tmpAppCategory: String
            _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
            val _tmpWifiDataUsed: Long
            _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
            val _tmpMobileDataUsed: Long
            _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
            val _tmpConnectionStatus: Int
            _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
            val _tmpScreenOffAllowed: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
            _tmpScreenOffAllowed = _tmp_1 != 0
            val _tmpBackgroundAllowed: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
            _tmpBackgroundAllowed = _tmp_2 != 0
            val _tmpIsProxyExcluded: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
            _tmpIsProxyExcluded = _tmp_3 != 0
            val _tmpTombstoneTs: Long
            _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
            val _tmpNotes: String
            _tmpNotes = _stmt.getText(_columnIndexOfNotes)
            _item_4 = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
            _item_4.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item_4.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item_4.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
            _item_4.tempAllowEnabled = _tmp_4 != 0
            _item_4.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
            _result.add(_item_4)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getFilteredApps(
    search: String,
    cat: Set<String>,
    firewall: Set<Int>,
    appType: Set<Int>,
    connectionStatus: Set<Int>,
    sort: String,
  ): List<AppInfo> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from AppInfo where (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or uid like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or packageName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and appCategory in (")
    val _inputSize: Int = cat.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and isSystemApp in (")
    val _inputSize_1: Int = appType.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") and firewallStatus in (")
    val _inputSize_2: Int = firewall.size
    appendPlaceholders(_stringBuilder, _inputSize_2)
    _stringBuilder.append(") and connectionStatus in (")
    val _inputSize_3: Int = connectionStatus.size
    appendPlaceholders(_stringBuilder, _inputSize_3)
    _stringBuilder.append(") order by case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'name' then lower(appName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'package' then lower(packageName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'uid' then uid end asc, lower(appName) asc")
    val _sql: String = _stringBuilder.toString()
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, search)
        _argIndex = 2
        _stmt.bindText(_argIndex, search)
        _argIndex = 3
        _stmt.bindText(_argIndex, search)
        _argIndex = 4
        for (_item: String in cat) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 4 + _inputSize
        for (_item_1: Int in appType) {
          _stmt.bindLong(_argIndex, _item_1.toLong())
          _argIndex++
        }
        _argIndex = 4 + _inputSize + _inputSize_1
        for (_item_2: Int in firewall) {
          _stmt.bindLong(_argIndex, _item_2.toLong())
          _argIndex++
        }
        _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2
        for (_item_3: Int in connectionStatus) {
          _stmt.bindLong(_argIndex, _item_3.toLong())
          _argIndex++
        }
        _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
        _stmt.bindText(_argIndex, sort)
        _argIndex = 5 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
        _stmt.bindText(_argIndex, sort)
        _argIndex = 6 + _inputSize + _inputSize_1 + _inputSize_2 + _inputSize_3
        _stmt.bindText(_argIndex, sort)
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
        val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
        val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
        val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
        val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
        val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
        val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
        val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
        val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
        val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
        val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
        val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
        val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
        val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
        val _result: MutableList<AppInfo> = mutableListOf()
        while (_stmt.step()) {
          val _item_4: AppInfo
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpIsSystemApp: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
          _tmpIsSystemApp = _tmp != 0
          val _tmpFirewallStatus: Int
          _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
          val _tmpAppCategory: String
          _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
          val _tmpWifiDataUsed: Long
          _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
          val _tmpMobileDataUsed: Long
          _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
          val _tmpConnectionStatus: Int
          _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
          val _tmpScreenOffAllowed: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
          _tmpScreenOffAllowed = _tmp_1 != 0
          val _tmpBackgroundAllowed: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
          _tmpBackgroundAllowed = _tmp_2 != 0
          val _tmpIsProxyExcluded: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
          _tmpIsProxyExcluded = _tmp_3 != 0
          val _tmpTombstoneTs: Long
          _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
          val _tmpNotes: String
          _tmpNotes = _stmt.getText(_columnIndexOfNotes)
          _item_4 = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
          _item_4.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _item_4.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _item_4.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
          _item_4.tempAllowEnabled = _tmp_4 != 0
          _item_4.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
          _result.add(_item_4)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFilteredApps(
    search: String,
    firewall: Set<Int>,
    appType: Set<Int>,
    connectionStatus: Set<Int>,
    sort: String,
  ): List<AppInfo> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("select * from AppInfo where (appName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or uid like ")
    _stringBuilder.append("?")
    _stringBuilder.append(" or packageName like ")
    _stringBuilder.append("?")
    _stringBuilder.append(") and isSystemApp in (")
    val _inputSize: Int = appType.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") and firewallStatus in (")
    val _inputSize_1: Int = firewall.size
    appendPlaceholders(_stringBuilder, _inputSize_1)
    _stringBuilder.append(") and connectionStatus in (")
    val _inputSize_2: Int = connectionStatus.size
    appendPlaceholders(_stringBuilder, _inputSize_2)
    _stringBuilder.append(") order by case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'name' then lower(appName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'package' then lower(packageName) end asc, case when ")
    _stringBuilder.append("?")
    _stringBuilder.append(" = 'uid' then uid end asc, lower(appName) asc")
    val _sql: String = _stringBuilder.toString()
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, search)
        _argIndex = 2
        _stmt.bindText(_argIndex, search)
        _argIndex = 3
        _stmt.bindText(_argIndex, search)
        _argIndex = 4
        for (_item: Int in appType) {
          _stmt.bindLong(_argIndex, _item.toLong())
          _argIndex++
        }
        _argIndex = 4 + _inputSize
        for (_item_1: Int in firewall) {
          _stmt.bindLong(_argIndex, _item_1.toLong())
          _argIndex++
        }
        _argIndex = 4 + _inputSize + _inputSize_1
        for (_item_2: Int in connectionStatus) {
          _stmt.bindLong(_argIndex, _item_2.toLong())
          _argIndex++
        }
        _argIndex = 4 + _inputSize + _inputSize_1 + _inputSize_2
        _stmt.bindText(_argIndex, sort)
        _argIndex = 5 + _inputSize + _inputSize_1 + _inputSize_2
        _stmt.bindText(_argIndex, sort)
        _argIndex = 6 + _inputSize + _inputSize_1 + _inputSize_2
        _stmt.bindText(_argIndex, sort)
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
        val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
        val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
        val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
        val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
        val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
        val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
        val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
        val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
        val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
        val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
        val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
        val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
        val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
        val _result: MutableList<AppInfo> = mutableListOf()
        while (_stmt.step()) {
          val _item_3: AppInfo
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpIsSystemApp: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
          _tmpIsSystemApp = _tmp != 0
          val _tmpFirewallStatus: Int
          _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
          val _tmpAppCategory: String
          _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
          val _tmpWifiDataUsed: Long
          _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
          val _tmpMobileDataUsed: Long
          _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
          val _tmpConnectionStatus: Int
          _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
          val _tmpScreenOffAllowed: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
          _tmpScreenOffAllowed = _tmp_1 != 0
          val _tmpBackgroundAllowed: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
          _tmpBackgroundAllowed = _tmp_2 != 0
          val _tmpIsProxyExcluded: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
          _tmpIsProxyExcluded = _tmp_3 != 0
          val _tmpTombstoneTs: Long
          _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
          val _tmpNotes: String
          _tmpNotes = _stmt.getText(_columnIndexOfNotes)
          _item_3 = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
          _item_3.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _item_3.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _item_3.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
          _item_3.tempAllowEnabled = _tmp_4 != 0
          _item_3.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
          _result.add(_item_3)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllAppDetailsCursor(): Cursor {
    val _sql: String = "select * from AppInfo order by appCategory, uid"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    val _tmpResult: Cursor = __db.query(_statement)
    return _tmpResult
  }

  public override fun getDataUsageByUid(uid: Int): DataUsage? {
    val _sql: String = "select uid as uid, downloadBytes as downloadBytes, uploadBytes as uploadBytes from AppInfo where uid = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfDownloadBytes: Int = 1
        val _columnIndexOfUploadBytes: Int = 2
        val _result: DataUsage?
        if (_stmt.step()) {
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpDownloadBytes: Long
          _tmpDownloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          val _tmpUploadBytes: Long
          _tmpUploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _result = DataUsage(_tmpUid,_tmpUploadBytes,_tmpDownloadBytes)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAppInfoUidForPackageName(packageName: String): Int {
    val _sql: String = "select uid from AppInfo where packageName = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, packageName)
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

  public override suspend fun getTempAllowedApps(): List<AppInfo> {
    val _sql: String = "select * from AppInfo where tempAllowEnabled = 1 and tempAllowExpiryTime > 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
        val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
        val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
        val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
        val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
        val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
        val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
        val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
        val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
        val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
        val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
        val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
        val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
        val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
        val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
        val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
        val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
        val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
        val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
        val _result: MutableList<AppInfo> = mutableListOf()
        while (_stmt.step()) {
          val _item: AppInfo
          val _tmpPackageName: String
          _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
          val _tmpAppName: String
          _tmpAppName = _stmt.getText(_columnIndexOfAppName)
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpIsSystemApp: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
          _tmpIsSystemApp = _tmp != 0
          val _tmpFirewallStatus: Int
          _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
          val _tmpAppCategory: String
          _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
          val _tmpWifiDataUsed: Long
          _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
          val _tmpMobileDataUsed: Long
          _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
          val _tmpConnectionStatus: Int
          _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
          val _tmpScreenOffAllowed: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
          _tmpScreenOffAllowed = _tmp_1 != 0
          val _tmpBackgroundAllowed: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
          _tmpBackgroundAllowed = _tmp_2 != 0
          val _tmpIsProxyExcluded: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
          _tmpIsProxyExcluded = _tmp_3 != 0
          val _tmpTombstoneTs: Long
          _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
          val _tmpNotes: String
          _tmpNotes = _stmt.getText(_columnIndexOfNotes)
          _item = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
          _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
          _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
          _item.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
          val _tmp_4: Int
          _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
          _item.tempAllowEnabled = _tmp_4 != 0
          _item.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTempAllowedAppsPaged(now: Long): PagingSource<Int, AppInfo> {
    val _sql: String = "select * from AppInfo where tempAllowEnabled = 1 and tempAllowExpiryTime > ? ORDER BY tempAllowExpiryTime DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, now)
    }
    return object : LimitOffsetPagingSource<AppInfo>(_rawQuery, __db, "AppInfo") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<AppInfo> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfPackageName: Int = getColumnIndexOrThrow(_stmt, "packageName")
          val _columnIndexOfAppName: Int = getColumnIndexOrThrow(_stmt, "appName")
          val _columnIndexOfUid: Int = getColumnIndexOrThrow(_stmt, "uid")
          val _columnIndexOfIsSystemApp: Int = getColumnIndexOrThrow(_stmt, "isSystemApp")
          val _columnIndexOfFirewallStatus: Int = getColumnIndexOrThrow(_stmt, "firewallStatus")
          val _columnIndexOfAppCategory: Int = getColumnIndexOrThrow(_stmt, "appCategory")
          val _columnIndexOfWifiDataUsed: Int = getColumnIndexOrThrow(_stmt, "wifiDataUsed")
          val _columnIndexOfMobileDataUsed: Int = getColumnIndexOrThrow(_stmt, "mobileDataUsed")
          val _columnIndexOfConnectionStatus: Int = getColumnIndexOrThrow(_stmt, "connectionStatus")
          val _columnIndexOfScreenOffAllowed: Int = getColumnIndexOrThrow(_stmt, "screenOffAllowed")
          val _columnIndexOfBackgroundAllowed: Int = getColumnIndexOrThrow(_stmt, "backgroundAllowed")
          val _columnIndexOfUploadBytes: Int = getColumnIndexOrThrow(_stmt, "uploadBytes")
          val _columnIndexOfDownloadBytes: Int = getColumnIndexOrThrow(_stmt, "downloadBytes")
          val _columnIndexOfIsProxyExcluded: Int = getColumnIndexOrThrow(_stmt, "isProxyExcluded")
          val _columnIndexOfTombstoneTs: Int = getColumnIndexOrThrow(_stmt, "tombstoneTs")
          val _columnIndexOfModifiedTs: Int = getColumnIndexOrThrow(_stmt, "modifiedTs")
          val _columnIndexOfTempAllowEnabled: Int = getColumnIndexOrThrow(_stmt, "tempAllowEnabled")
          val _columnIndexOfTempAllowExpiryTime: Int = getColumnIndexOrThrow(_stmt, "tempAllowExpiryTime")
          val _columnIndexOfNotes: Int = getColumnIndexOrThrow(_stmt, "notes")
          val _result: MutableList<AppInfo> = mutableListOf()
          while (_stmt.step()) {
            val _item: AppInfo
            val _tmpPackageName: String
            _tmpPackageName = _stmt.getText(_columnIndexOfPackageName)
            val _tmpAppName: String
            _tmpAppName = _stmt.getText(_columnIndexOfAppName)
            val _tmpUid: Int
            _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
            val _tmpIsSystemApp: Boolean
            val _tmp: Int
            _tmp = _stmt.getLong(_columnIndexOfIsSystemApp).toInt()
            _tmpIsSystemApp = _tmp != 0
            val _tmpFirewallStatus: Int
            _tmpFirewallStatus = _stmt.getLong(_columnIndexOfFirewallStatus).toInt()
            val _tmpAppCategory: String
            _tmpAppCategory = _stmt.getText(_columnIndexOfAppCategory)
            val _tmpWifiDataUsed: Long
            _tmpWifiDataUsed = _stmt.getLong(_columnIndexOfWifiDataUsed)
            val _tmpMobileDataUsed: Long
            _tmpMobileDataUsed = _stmt.getLong(_columnIndexOfMobileDataUsed)
            val _tmpConnectionStatus: Int
            _tmpConnectionStatus = _stmt.getLong(_columnIndexOfConnectionStatus).toInt()
            val _tmpScreenOffAllowed: Boolean
            val _tmp_1: Int
            _tmp_1 = _stmt.getLong(_columnIndexOfScreenOffAllowed).toInt()
            _tmpScreenOffAllowed = _tmp_1 != 0
            val _tmpBackgroundAllowed: Boolean
            val _tmp_2: Int
            _tmp_2 = _stmt.getLong(_columnIndexOfBackgroundAllowed).toInt()
            _tmpBackgroundAllowed = _tmp_2 != 0
            val _tmpIsProxyExcluded: Boolean
            val _tmp_3: Int
            _tmp_3 = _stmt.getLong(_columnIndexOfIsProxyExcluded).toInt()
            _tmpIsProxyExcluded = _tmp_3 != 0
            val _tmpTombstoneTs: Long
            _tmpTombstoneTs = _stmt.getLong(_columnIndexOfTombstoneTs)
            val _tmpNotes: String
            _tmpNotes = _stmt.getText(_columnIndexOfNotes)
            _item = AppInfo(_tmpPackageName,_tmpAppName,_tmpUid,_tmpIsSystemApp,_tmpFirewallStatus,_tmpAppCategory,_tmpWifiDataUsed,_tmpMobileDataUsed,_tmpConnectionStatus,_tmpIsProxyExcluded,_tmpScreenOffAllowed,_tmpBackgroundAllowed,_tmpTombstoneTs,_tmpNotes)
            _item.uploadBytes = _stmt.getLong(_columnIndexOfUploadBytes)
            _item.downloadBytes = _stmt.getLong(_columnIndexOfDownloadBytes)
            _item.modifiedTs = _stmt.getLong(_columnIndexOfModifiedTs)
            val _tmp_4: Int
            _tmp_4 = _stmt.getLong(_columnIndexOfTempAllowEnabled).toInt()
            _item.tempAllowEnabled = _tmp_4 != 0
            _item.tempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun getNearestTempAllowExpiry(now: Long): Long? {
    val _sql: String = "select MIN(tempAllowExpiryTime) from AppInfo where tempAllowEnabled = 1 and tempAllowExpiryTime > ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, now)
        val _result: Long?
        if (_stmt.step()) {
          val _tmp: Long?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0)
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

  public override fun getExpiredTempAllows(now: Long): List<ExpiredTempAllow> {
    val _sql: String = "select uid, tempAllowExpiryTime from AppInfo where tempAllowEnabled = 1 and tempAllowExpiryTime > 0 and tempAllowExpiryTime <= ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, now)
        val _columnIndexOfUid: Int = 0
        val _columnIndexOfTempAllowExpiryTime: Int = 1
        val _result: MutableList<ExpiredTempAllow> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExpiredTempAllow
          val _tmpUid: Int
          _tmpUid = _stmt.getLong(_columnIndexOfUid).toInt()
          val _tmpTempAllowExpiryTime: Long
          _tmpTempAllowExpiryTime = _stmt.getLong(_columnIndexOfTempAllowExpiryTime)
          _item = ExpiredTempAllow(_tmpUid,_tmpTempAllowExpiryTime)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateNotes(
    uid: Int,
    packageName: String,
    notes: String,
    modifiedTs: Long,
  ): Int {
    val _sql: String = "update AppInfo set notes = ?, modifiedTs = ? where uid = ? and packageName = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, notes)
        _argIndex = 2
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 3
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 4
        _stmt.bindText(_argIndex, packageName)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateFirewallStatusByUid(
    uid: Int,
    firewallStatus: Int,
    connectionStatus: Int,
    modifiedTs: Long,
  ) {
    val _sql: String = "update AppInfo set firewallStatus = ?, connectionStatus = ?, modifiedTs = ? where uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, firewallStatus.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, connectionStatus.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 4
        _stmt.bindLong(_argIndex, uid.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateTempAllowByUid(
    uid: Int,
    enabled: Boolean,
    expiryTime: Long,
    modifiedTs: Long,
  ) {
    val _sql: String = "update AppInfo set tempAllowEnabled = ?, tempAllowExpiryTime = ?, modifiedTs = ? where uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (enabled) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, expiryTime)
        _argIndex = 3
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 4
        _stmt.bindLong(_argIndex, uid.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateUid(
    oldUid: Int,
    pkg: String,
    newUid: Int,
    modifiedTs: Long,
  ): Int {
    val _sql: String = "update AppInfo set uid = ?, tombstoneTs = 0, modifiedTs = ? where uid = ? and packageName = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newUid.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 3
        _stmt.bindLong(_argIndex, oldUid.toLong())
        _argIndex = 4
        _stmt.bindText(_argIndex, pkg)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteByPackageName(packageNames: List<String>) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("delete from AppInfo where packageName in (")
    val _inputSize: Int = packageNames.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: String in packageNames) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deletePackage(uid: Int, packageName: String) {
    val _sql: String = "delete from AppInfo where uid = ? and packageName = ?"
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

  public override fun tombstoneApp(
    newUid: Int,
    uid: Int,
    packageName: String,
    tombstoneTs: Long,
    modifiedTs: Long,
  ) {
    val _sql: String = "update AppInfo set uid = ?, tombstoneTs = ?, modifiedTs = ? where uid = ? and packageName = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newUid.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, tombstoneTs)
        _argIndex = 3
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 4
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 5
        _stmt.bindText(_argIndex, packageName)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun tombstoneApp(
    oldUid: Int,
    newUid: Int,
    tombstoneTs: Long,
    modifiedTs: Long,
  ) {
    val _sql: String = "update AppInfo set uid = ?, tombstoneTs = ?, modifiedTs = ? where uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newUid.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, tombstoneTs)
        _argIndex = 3
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 4
        _stmt.bindLong(_argIndex, oldUid.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun cpUpdate(
    firewall: Int,
    connectionStatus: Int,
    clause: String,
  ): Int {
    val _sql: String = "update AppInfo set firewallStatus = ?, connectionStatus = ? where ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, firewall.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, connectionStatus.toLong())
        _argIndex = 3
        _stmt.bindText(_argIndex, clause)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteByUid(uid: Int): Int {
    val _sql: String = "delete from AppInfo where uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uid.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteAll() {
    val _sql: String = "delete from AppInfo"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateDataUsageByUid(
    uid: Int,
    uploadBytes: Long,
    downloadBytes: Long,
  ) {
    val _sql: String = "update AppInfo set  uploadBytes = ?, downloadBytes = ? where uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, uploadBytes)
        _argIndex = 2
        _stmt.bindLong(_argIndex, downloadBytes)
        _argIndex = 3
        _stmt.bindLong(_argIndex, uid.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun updateProxyExcluded(
    uid: Int,
    isProxyExcluded: Boolean,
    modifiedTs: Long,
  ) {
    val _sql: String = "update AppInfo set isProxyExcluded = ?, modifiedTs = ? where uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (isProxyExcluded) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 3
        _stmt.bindLong(_argIndex, uid.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun exemptRethinkApp(): Int {
    val _sql: String = "update AppInfo set firewallStatus = 7, connectionStatus = 3, isProxyExcluded = 1 where packageName = 'com.celzero.bravedns'"
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

  public override fun clearTempAllowByUid(uid: Int, modifiedTs: Long) {
    val _sql: String = "update AppInfo set tempAllowEnabled = 0, tempAllowExpiryTime = 0, modifiedTs = ? where uid = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 2
        _stmt.bindLong(_argIndex, uid.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearTempAllowByUidIfExpiry(
    uid: Int,
    expectedExpiry: Long,
    modifiedTs: Long,
  ): Int {
    val _sql: String = "update AppInfo set tempAllowEnabled = 0, tempAllowExpiryTime = 0, modifiedTs = ? where uid = ? and tempAllowEnabled = 1 and tempAllowExpiryTime = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 2
        _stmt.bindLong(_argIndex, uid.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, expectedExpiry)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun clearAllExpiredTempAllows(now: Long, modifiedTs: Long): Int {
    val _sql: String = "update AppInfo set tempAllowEnabled = 0, tempAllowExpiryTime = 0, modifiedTs = ? where tempAllowEnabled = 1 and tempAllowExpiryTime > 0 and tempAllowExpiryTime <= ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, modifiedTs)
        _argIndex = 2
        _stmt.bindLong(_argIndex, now)
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
