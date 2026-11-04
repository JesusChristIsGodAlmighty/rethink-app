package com.celzero.bravedns.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SubscriptionStatusDao_Impl(
  __db: RoomDatabase,
) : SubscriptionStatusDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSubscriptionStatus: EntityInsertAdapter<SubscriptionStatus>

  private val __insertAdapterOfSubscriptionStatus_1: EntityInsertAdapter<SubscriptionStatus>

  private val __deleteAdapterOfSubscriptionStatus: EntityDeleteOrUpdateAdapter<SubscriptionStatus>

  private val __updateAdapterOfSubscriptionStatus: EntityDeleteOrUpdateAdapter<SubscriptionStatus>
  init {
    this.__db = __db
    this.__insertAdapterOfSubscriptionStatus = object : EntityInsertAdapter<SubscriptionStatus>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `SubscriptionStatus` (`id`,`accountId`,`deviceId`,`purchaseToken`,`orderId`,`productId`,`planId`,`sessionToken`,`productTitle`,`state`,`purchaseTime`,`accountExpiry`,`billingExpiry`,`developerPayload`,`status`,`windowDays`,`lastUpdatedTs`,`previousProductId`,`previousPurchaseToken`,`replacedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SubscriptionStatus) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.accountId)
        statement.bindText(3, entity.deviceId)
        statement.bindText(4, entity.purchaseToken)
        statement.bindText(5, entity.orderId)
        statement.bindText(6, entity.productId)
        statement.bindText(7, entity.planId)
        statement.bindText(8, entity.sessionToken)
        statement.bindText(9, entity.productTitle)
        statement.bindLong(10, entity.state.toLong())
        statement.bindLong(11, entity.purchaseTime)
        statement.bindLong(12, entity.accountExpiry)
        statement.bindLong(13, entity.billingExpiry)
        statement.bindText(14, entity.developerPayload)
        statement.bindLong(15, entity.status.toLong())
        statement.bindLong(16, entity.windowDays.toLong())
        statement.bindLong(17, entity.lastUpdatedTs)
        statement.bindText(18, entity.previousProductId)
        statement.bindText(19, entity.previousPurchaseToken)
        statement.bindLong(20, entity.replacedAt)
      }
    }
    this.__insertAdapterOfSubscriptionStatus_1 = object : EntityInsertAdapter<SubscriptionStatus>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `SubscriptionStatus` (`id`,`accountId`,`deviceId`,`purchaseToken`,`orderId`,`productId`,`planId`,`sessionToken`,`productTitle`,`state`,`purchaseTime`,`accountExpiry`,`billingExpiry`,`developerPayload`,`status`,`windowDays`,`lastUpdatedTs`,`previousProductId`,`previousPurchaseToken`,`replacedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SubscriptionStatus) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.accountId)
        statement.bindText(3, entity.deviceId)
        statement.bindText(4, entity.purchaseToken)
        statement.bindText(5, entity.orderId)
        statement.bindText(6, entity.productId)
        statement.bindText(7, entity.planId)
        statement.bindText(8, entity.sessionToken)
        statement.bindText(9, entity.productTitle)
        statement.bindLong(10, entity.state.toLong())
        statement.bindLong(11, entity.purchaseTime)
        statement.bindLong(12, entity.accountExpiry)
        statement.bindLong(13, entity.billingExpiry)
        statement.bindText(14, entity.developerPayload)
        statement.bindLong(15, entity.status.toLong())
        statement.bindLong(16, entity.windowDays.toLong())
        statement.bindLong(17, entity.lastUpdatedTs)
        statement.bindText(18, entity.previousProductId)
        statement.bindText(19, entity.previousPurchaseToken)
        statement.bindLong(20, entity.replacedAt)
      }
    }
    this.__deleteAdapterOfSubscriptionStatus = object : EntityDeleteOrUpdateAdapter<SubscriptionStatus>() {
      protected override fun createQuery(): String = "DELETE FROM `SubscriptionStatus` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SubscriptionStatus) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfSubscriptionStatus = object : EntityDeleteOrUpdateAdapter<SubscriptionStatus>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `SubscriptionStatus` SET `id` = ?,`accountId` = ?,`deviceId` = ?,`purchaseToken` = ?,`orderId` = ?,`productId` = ?,`planId` = ?,`sessionToken` = ?,`productTitle` = ?,`state` = ?,`purchaseTime` = ?,`accountExpiry` = ?,`billingExpiry` = ?,`developerPayload` = ?,`status` = ?,`windowDays` = ?,`lastUpdatedTs` = ?,`previousProductId` = ?,`previousPurchaseToken` = ?,`replacedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SubscriptionStatus) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.accountId)
        statement.bindText(3, entity.deviceId)
        statement.bindText(4, entity.purchaseToken)
        statement.bindText(5, entity.orderId)
        statement.bindText(6, entity.productId)
        statement.bindText(7, entity.planId)
        statement.bindText(8, entity.sessionToken)
        statement.bindText(9, entity.productTitle)
        statement.bindLong(10, entity.state.toLong())
        statement.bindLong(11, entity.purchaseTime)
        statement.bindLong(12, entity.accountExpiry)
        statement.bindLong(13, entity.billingExpiry)
        statement.bindText(14, entity.developerPayload)
        statement.bindLong(15, entity.status.toLong())
        statement.bindLong(16, entity.windowDays.toLong())
        statement.bindLong(17, entity.lastUpdatedTs)
        statement.bindText(18, entity.previousProductId)
        statement.bindText(19, entity.previousPurchaseToken)
        statement.bindLong(20, entity.replacedAt)
        statement.bindLong(21, entity.id.toLong())
      }
    }
  }

  public override suspend fun upsert(subscription: SubscriptionStatus): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSubscriptionStatus.insertAndReturnId(_connection, subscription)
    _result
  }

  public override suspend fun insert(subscriptionStatus: SubscriptionStatus): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSubscriptionStatus_1.insertAndReturnId(_connection, subscriptionStatus)
    _result
  }

  public override suspend fun delete(subscriptionStatus: SubscriptionStatus): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfSubscriptionStatus.handle(_connection, subscriptionStatus)
    _result
  }

  public override suspend fun update(subscriptionStatus: SubscriptionStatus): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfSubscriptionStatus.handle(_connection, subscriptionStatus)
    _result
  }

  public override suspend fun getByAccountId(accountId: String): SubscriptionStatus? {
    val _sql: String = "SELECT * FROM SubscriptionStatus WHERE accountId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, accountId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: SubscriptionStatus?
        if (_stmt.step()) {
          _result = SubscriptionStatus()
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.accountId = _stmt.getText(_columnIndexOfAccountId)
          _result.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _result.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _result.orderId = _stmt.getText(_columnIndexOfOrderId)
          _result.productId = _stmt.getText(_columnIndexOfProductId)
          _result.planId = _stmt.getText(_columnIndexOfPlanId)
          _result.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _result.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _result.state = _stmt.getLong(_columnIndexOfState).toInt()
          _result.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _result.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _result.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _result.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _result.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _result.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _result.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _result.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _result.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _result.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCurrent(): SubscriptionStatus? {
    val _sql: String = "SELECT * FROM SubscriptionStatus order by billingExpiry desc LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: SubscriptionStatus?
        if (_stmt.step()) {
          _result = SubscriptionStatus()
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.accountId = _stmt.getText(_columnIndexOfAccountId)
          _result.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _result.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _result.orderId = _stmt.getText(_columnIndexOfOrderId)
          _result.productId = _stmt.getText(_columnIndexOfProductId)
          _result.planId = _stmt.getText(_columnIndexOfPlanId)
          _result.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _result.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _result.state = _stmt.getLong(_columnIndexOfState).toInt()
          _result.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _result.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _result.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _result.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _result.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _result.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _result.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _result.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _result.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _result.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubscriptionById(id: Int): SubscriptionStatus? {
    val _sql: String = "SELECT * FROM SubscriptionStatus WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: SubscriptionStatus?
        if (_stmt.step()) {
          _result = SubscriptionStatus()
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.accountId = _stmt.getText(_columnIndexOfAccountId)
          _result.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _result.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _result.orderId = _stmt.getText(_columnIndexOfOrderId)
          _result.productId = _stmt.getText(_columnIndexOfProductId)
          _result.planId = _stmt.getText(_columnIndexOfPlanId)
          _result.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _result.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _result.state = _stmt.getLong(_columnIndexOfState).toInt()
          _result.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _result.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _result.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _result.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _result.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _result.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _result.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _result.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _result.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _result.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubscriptionByToken(token: String): SubscriptionStatus? {
    val _sql: String = "SELECT * FROM SubscriptionStatus WHERE purchaseToken = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, token)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: SubscriptionStatus?
        if (_stmt.step()) {
          _result = SubscriptionStatus()
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.accountId = _stmt.getText(_columnIndexOfAccountId)
          _result.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _result.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _result.orderId = _stmt.getText(_columnIndexOfOrderId)
          _result.productId = _stmt.getText(_columnIndexOfProductId)
          _result.planId = _stmt.getText(_columnIndexOfPlanId)
          _result.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _result.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _result.state = _stmt.getLong(_columnIndexOfState).toInt()
          _result.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _result.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _result.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _result.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _result.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _result.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _result.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _result.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _result.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _result.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubscriptionByProductId(productId: String): SubscriptionStatus? {
    val _sql: String = "SELECT * FROM SubscriptionStatus WHERE productId = ? ORDER BY lastUpdatedTs DESC LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, productId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: SubscriptionStatus?
        if (_stmt.step()) {
          _result = SubscriptionStatus()
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.accountId = _stmt.getText(_columnIndexOfAccountId)
          _result.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _result.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _result.orderId = _stmt.getText(_columnIndexOfOrderId)
          _result.productId = _stmt.getText(_columnIndexOfProductId)
          _result.planId = _stmt.getText(_columnIndexOfPlanId)
          _result.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _result.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _result.state = _stmt.getLong(_columnIndexOfState).toInt()
          _result.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _result.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _result.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _result.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _result.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _result.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _result.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _result.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _result.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _result.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubscriptionsByAccountId(accountId: String): List<SubscriptionStatus> {
    val _sql: String = "SELECT * FROM SubscriptionStatus WHERE accountId = ? ORDER BY lastUpdatedTs DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, accountId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCurrentSubscription(): SubscriptionStatus? {
    val _sql: String = "SELECT * FROM SubscriptionStatus ORDER BY lastUpdatedTs DESC LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: SubscriptionStatus?
        if (_stmt.step()) {
          _result = SubscriptionStatus()
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.accountId = _stmt.getText(_columnIndexOfAccountId)
          _result.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _result.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _result.orderId = _stmt.getText(_columnIndexOfOrderId)
          _result.productId = _stmt.getText(_columnIndexOfProductId)
          _result.planId = _stmt.getText(_columnIndexOfPlanId)
          _result.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _result.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _result.state = _stmt.getLong(_columnIndexOfState).toInt()
          _result.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _result.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _result.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _result.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _result.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _result.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _result.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _result.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _result.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _result.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCurrentValidSubscription(): SubscriptionStatus? {
    val _sql: String = """
        |
        |        SELECT * FROM SubscriptionStatus
        |        WHERE status IN (1, 2, 5, 6, 9, 10, 11)
        |        ORDER BY CASE WHEN status = 1 THEN 0 ELSE 1 END, lastUpdatedTs DESC
        |        LIMIT 1
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: SubscriptionStatus?
        if (_stmt.step()) {
          _result = SubscriptionStatus()
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.accountId = _stmt.getText(_columnIndexOfAccountId)
          _result.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _result.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _result.orderId = _stmt.getText(_columnIndexOfOrderId)
          _result.productId = _stmt.getText(_columnIndexOfProductId)
          _result.planId = _stmt.getText(_columnIndexOfPlanId)
          _result.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _result.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _result.state = _stmt.getLong(_columnIndexOfState).toInt()
          _result.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _result.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _result.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _result.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _result.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _result.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _result.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _result.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _result.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _result.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSubscriptions(): List<SubscriptionStatus> {
    val _sql: String = "SELECT * FROM SubscriptionStatus ORDER BY lastUpdatedTs DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubscriptionsByStatus(status: Int): List<SubscriptionStatus> {
    val _sql: String = "SELECT * FROM SubscriptionStatus WHERE status = ? ORDER BY lastUpdatedTs DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, status.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubscriptionsByStates(statuses: List<Int>): List<SubscriptionStatus> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM SubscriptionStatus WHERE status IN (")
    val _inputSize: Int = statuses.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") ORDER BY lastUpdatedTs DESC")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Int in statuses) {
          _stmt.bindLong(_argIndex, _item.toLong())
          _argIndex++
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: SubscriptionStatus
          _item_1 = SubscriptionStatus()
          _item_1.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item_1.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item_1.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item_1.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item_1.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item_1.productId = _stmt.getText(_columnIndexOfProductId)
          _item_1.planId = _stmt.getText(_columnIndexOfPlanId)
          _item_1.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item_1.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item_1.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item_1.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item_1.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item_1.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item_1.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item_1.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item_1.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item_1.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item_1.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item_1.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item_1.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTotalCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM SubscriptionStatus"
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

  public override suspend fun getCountByStatus(status: Int): Int {
    val _sql: String = "SELECT COUNT(*) FROM SubscriptionStatus WHERE status = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, status.toLong())
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

  public override suspend fun getCountByStates(statuses: List<Int>): Int {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT COUNT(*) FROM SubscriptionStatus WHERE status IN (")
    val _inputSize: Int = statuses.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Int in statuses) {
          _stmt.bindLong(_argIndex, _item.toLong())
          _argIndex++
        }
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

  public override fun observeCurrentSubscription(): Flow<SubscriptionStatus?> {
    val _sql: String = """
        |
        |        SELECT * FROM SubscriptionStatus
        |        ORDER BY CASE WHEN status = 1 THEN 0 ELSE 1 END, lastUpdatedTs DESC
        |        LIMIT 1
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("SubscriptionStatus")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: SubscriptionStatus?
        if (_stmt.step()) {
          _result = SubscriptionStatus()
          _result.id = _stmt.getLong(_columnIndexOfId).toInt()
          _result.accountId = _stmt.getText(_columnIndexOfAccountId)
          _result.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _result.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _result.orderId = _stmt.getText(_columnIndexOfOrderId)
          _result.productId = _stmt.getText(_columnIndexOfProductId)
          _result.planId = _stmt.getText(_columnIndexOfPlanId)
          _result.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _result.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _result.state = _stmt.getLong(_columnIndexOfState).toInt()
          _result.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _result.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _result.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _result.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _result.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _result.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _result.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _result.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _result.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _result.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeAllSubscriptions(): Flow<List<SubscriptionStatus>> {
    val _sql: String = "SELECT * FROM SubscriptionStatus ORDER BY lastUpdatedTs DESC"
    return createFlow(__db, false, arrayOf("SubscriptionStatus")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeSubscriptionsByStatus(status: Int): Flow<List<SubscriptionStatus>> {
    val _sql: String = "SELECT * FROM SubscriptionStatus WHERE status = ? ORDER BY lastUpdatedTs DESC"
    return createFlow(__db, false, arrayOf("SubscriptionStatus")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, status.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeSubscriptionsByAccount(accountId: String): Flow<List<SubscriptionStatus>> {
    val _sql: String = "SELECT * FROM SubscriptionStatus WHERE accountId = ? ORDER BY lastUpdatedTs DESC"
    return createFlow(__db, false, arrayOf("SubscriptionStatus")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, accountId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getStatusDistribution(): List<StatusCount> {
    val _sql: String = """
        |
        |        SELECT status, COUNT(*) as count 
        |        FROM SubscriptionStatus 
        |        GROUP BY status 
        |        ORDER BY count DESC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfStatus: Int = 0
        val _columnIndexOfCount: Int = 1
        val _result: MutableList<StatusCount> = mutableListOf()
        while (_stmt.step()) {
          val _item: StatusCount
          val _tmpStatus: Int
          _tmpStatus = _stmt.getLong(_columnIndexOfStatus).toInt()
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          _item = StatusCount(_tmpStatus,_tmpCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getProductDistribution(): List<ProductCount> {
    val _sql: String = """
        |
        |        SELECT productId, COUNT(*) as count 
        |        FROM SubscriptionStatus 
        |        GROUP BY productId 
        |        ORDER BY count DESC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfProductId: Int = 0
        val _columnIndexOfCount: Int = 1
        val _result: MutableList<ProductCount> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProductCount
          val _tmpProductId: String
          _tmpProductId = _stmt.getText(_columnIndexOfProductId)
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          _item = ProductCount(_tmpProductId,_tmpCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubscriptionsInTimeRange(startTime: Long, endTime: Long): List<SubscriptionStatus> {
    val _sql: String = """
        |
        |        SELECT * FROM SubscriptionStatus 
        |        WHERE lastUpdatedTs BETWEEN ? AND ? 
        |        ORDER BY lastUpdatedTs DESC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endTime)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSubscriptionsExpiringInRange(startTime: Long, endTime: Long): List<SubscriptionStatus> {
    val _sql: String = """
        |
        |        SELECT * FROM SubscriptionStatus 
        |        WHERE billingExpiry > 0 AND billingExpiry BETWEEN ? AND ? 
        |        ORDER BY billingExpiry ASC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 2
        _stmt.bindLong(_argIndex, endTime)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getExpiredGracePeriodSubscriptions(currentTime: Long): List<SubscriptionStatus> {
    val _sql: String = """
        |
        |        SELECT * FROM SubscriptionStatus 
        |        WHERE status = 4
        |        AND billingExpiry < ?
        |        ORDER BY billingExpiry ASC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, currentTime)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getValidSubscriptions(): List<SubscriptionStatus> {
    val _sql: String = """
        |
        |        SELECT * FROM SubscriptionStatus 
        |        WHERE status IN (1, 4, 5, 6)
        |        ORDER BY lastUpdatedTs DESC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getInvalidSubscriptions(): List<SubscriptionStatus> {
    val _sql: String = """
        |
        |        SELECT * FROM SubscriptionStatus 
        |        WHERE status IN (3, 10)
        |        ORDER BY lastUpdatedTs DESC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfDeviceId: Int = getColumnIndexOrThrow(_stmt, "deviceId")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchaseToken")
        val _columnIndexOfOrderId: Int = getColumnIndexOrThrow(_stmt, "orderId")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "productId")
        val _columnIndexOfPlanId: Int = getColumnIndexOrThrow(_stmt, "planId")
        val _columnIndexOfSessionToken: Int = getColumnIndexOrThrow(_stmt, "sessionToken")
        val _columnIndexOfProductTitle: Int = getColumnIndexOrThrow(_stmt, "productTitle")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchaseTime")
        val _columnIndexOfAccountExpiry: Int = getColumnIndexOrThrow(_stmt, "accountExpiry")
        val _columnIndexOfBillingExpiry: Int = getColumnIndexOrThrow(_stmt, "billingExpiry")
        val _columnIndexOfDeveloperPayload: Int = getColumnIndexOrThrow(_stmt, "developerPayload")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfWindowDays: Int = getColumnIndexOrThrow(_stmt, "windowDays")
        val _columnIndexOfLastUpdatedTs: Int = getColumnIndexOrThrow(_stmt, "lastUpdatedTs")
        val _columnIndexOfPreviousProductId: Int = getColumnIndexOrThrow(_stmt, "previousProductId")
        val _columnIndexOfPreviousPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "previousPurchaseToken")
        val _columnIndexOfReplacedAt: Int = getColumnIndexOrThrow(_stmt, "replacedAt")
        val _result: MutableList<SubscriptionStatus> = mutableListOf()
        while (_stmt.step()) {
          val _item: SubscriptionStatus
          _item = SubscriptionStatus()
          _item.id = _stmt.getLong(_columnIndexOfId).toInt()
          _item.accountId = _stmt.getText(_columnIndexOfAccountId)
          _item.deviceId = _stmt.getText(_columnIndexOfDeviceId)
          _item.purchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          _item.orderId = _stmt.getText(_columnIndexOfOrderId)
          _item.productId = _stmt.getText(_columnIndexOfProductId)
          _item.planId = _stmt.getText(_columnIndexOfPlanId)
          _item.sessionToken = _stmt.getText(_columnIndexOfSessionToken)
          _item.productTitle = _stmt.getText(_columnIndexOfProductTitle)
          _item.state = _stmt.getLong(_columnIndexOfState).toInt()
          _item.purchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          _item.accountExpiry = _stmt.getLong(_columnIndexOfAccountExpiry)
          _item.billingExpiry = _stmt.getLong(_columnIndexOfBillingExpiry)
          _item.developerPayload = _stmt.getText(_columnIndexOfDeveloperPayload)
          _item.status = _stmt.getLong(_columnIndexOfStatus).toInt()
          _item.windowDays = _stmt.getLong(_columnIndexOfWindowDays).toInt()
          _item.lastUpdatedTs = _stmt.getLong(_columnIndexOfLastUpdatedTs)
          _item.previousProductId = _stmt.getText(_columnIndexOfPreviousProductId)
          _item.previousPurchaseToken = _stmt.getText(_columnIndexOfPreviousPurchaseToken)
          _item.replacedAt = _stmt.getLong(_columnIndexOfReplacedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun findDuplicateTokens(): List<TokenCount> {
    val _sql: String = """
        |
        |        SELECT purchaseToken, COUNT(*) as count 
        |        FROM SubscriptionStatus 
        |        GROUP BY purchaseToken 
        |        HAVING count > 1
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfPurchaseToken: Int = 0
        val _columnIndexOfCount: Int = 1
        val _result: MutableList<TokenCount> = mutableListOf()
        while (_stmt.step()) {
          val _item: TokenCount
          val _tmpPurchaseToken: String
          _tmpPurchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          val _tmpCount: Int
          _tmpCount = _stmt.getLong(_columnIndexOfCount).toInt()
          _item = TokenCount(_tmpPurchaseToken,_tmpCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM SubscriptionStatus"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateSubscriptionState(
    accountId: String,
    purchaseToken: String,
    state: Int,
  ): Int {
    val _sql: String = "Update SubscriptionStatus SET state = ? WHERE accountId = ? AND purchaseToken = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, state.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, accountId)
        _argIndex = 3
        _stmt.bindText(_argIndex, purchaseToken)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Int): Int {
    val _sql: String = "DELETE FROM SubscriptionStatus WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
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

  public override suspend fun updateStatus(
    id: Int,
    newStatus: Int,
    timestamp: Long,
  ): Int {
    val _sql: String = "UPDATE SubscriptionStatus SET status = ?, lastUpdatedTs = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newStatus.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 3
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateStatusByToken(
    token: String,
    newStatus: Int,
    timestamp: Long,
  ): Int {
    val _sql: String = "UPDATE SubscriptionStatus SET status = ?, lastUpdatedTs = ? WHERE purchaseToken = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newStatus.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 3
        _stmt.bindText(_argIndex, token)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateStatusByAccountId(
    accountId: String,
    newStatus: Int,
    timestamp: Long,
  ): Int {
    val _sql: String = "UPDATE SubscriptionStatus SET status = ?, lastUpdatedTs = ? WHERE accountId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newStatus.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 3
        _stmt.bindText(_argIndex, accountId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateBillingExpiry(
    id: Int,
    expiryTime: Long,
    timestamp: Long,
  ): Int {
    val _sql: String = "UPDATE SubscriptionStatus SET billingExpiry = ?, lastUpdatedTs = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, expiryTime)
        _argIndex = 2
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 3
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateAccountExpiry(
    id: Int,
    expiryTime: Long,
    timestamp: Long,
  ): Int {
    val _sql: String = "UPDATE SubscriptionStatus SET accountExpiry = ?, lastUpdatedTs = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, expiryTime)
        _argIndex = 2
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 3
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateDeveloperPayload(
    id: Int,
    payload: String,
    timestamp: Long,
  ): Int {
    val _sql: String = "UPDATE SubscriptionStatus SET developerPayload = ?, lastUpdatedTs = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, payload)
        _argIndex = 2
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 3
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markExpiredSubscriptions(currentTime: Long): Int {
    val _sql: String = """
        |
        |        UPDATE SubscriptionStatus 
        |        SET status = 3, lastUpdatedTs = ? 
        |        WHERE billingExpiry > 0 AND billingExpiry < ? 
        |        AND status NOT IN (3, 10)
        |        AND (productId LIKE '%onetime%' OR productId LIKE '%inapp%' OR productId = 'test_product')
        |    
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, currentTime)
        _argIndex = 2
        _stmt.bindLong(_argIndex, currentTime)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun bulkStatusTransition(
    oldStatus: Int,
    newStatus: Int,
    timestamp: Long,
  ): Int {
    val _sql: String = """
        |
        |        UPDATE SubscriptionStatus 
        |        SET status = ?, lastUpdatedTs = ? 
        |        WHERE status = ?
        |    
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newStatus.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 3
        _stmt.bindLong(_argIndex, oldStatus.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeDuplicateTokens(): Int {
    val _sql: String = """
        |
        |        DELETE FROM SubscriptionStatus 
        |        WHERE id NOT IN (
        |            SELECT MIN(id) 
        |            FROM SubscriptionStatus 
        |            GROUP BY purchaseToken
        |        )
        |    
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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
