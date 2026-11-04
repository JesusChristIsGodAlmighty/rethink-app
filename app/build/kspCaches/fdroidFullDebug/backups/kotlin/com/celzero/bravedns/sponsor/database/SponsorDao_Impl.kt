package com.celzero.bravedns.sponsor.database

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SponsorDao_Impl(
  __db: RoomDatabase,
) : SponsorDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSponsorEntity: EntityInsertAdapter<SponsorEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfSponsorEntity = object : EntityInsertAdapter<SponsorEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `Sponsor` (`id`,`purchase_token`,`product_id`,`purchase_time`,`sponsor_since`,`consumed`,`contribution_count`,`last_contribution_time`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SponsorEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.purchaseToken)
        statement.bindText(3, entity.productId)
        statement.bindLong(4, entity.purchaseTime)
        statement.bindLong(5, entity.sponsorSince)
        val _tmp: Int = if (entity.consumed) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindLong(7, entity.contributionCount.toLong())
        statement.bindLong(8, entity.lastContributionTime)
      }
    }
  }

  public override suspend fun insert(sponsor: SponsorEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSponsorEntity.insertAndReturnId(_connection, sponsor)
    _result
  }

  public override fun observeLatestSponsor(): Flow<SponsorEntity?> {
    val _sql: String = "SELECT * FROM Sponsor ORDER BY id DESC LIMIT 1"
    return createFlow(__db, false, arrayOf("Sponsor")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchase_token")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "product_id")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchase_time")
        val _columnIndexOfSponsorSince: Int = getColumnIndexOrThrow(_stmt, "sponsor_since")
        val _columnIndexOfConsumed: Int = getColumnIndexOrThrow(_stmt, "consumed")
        val _columnIndexOfContributionCount: Int = getColumnIndexOrThrow(_stmt, "contribution_count")
        val _columnIndexOfLastContributionTime: Int = getColumnIndexOrThrow(_stmt, "last_contribution_time")
        val _result: SponsorEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpPurchaseToken: String
          _tmpPurchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          val _tmpProductId: String
          _tmpProductId = _stmt.getText(_columnIndexOfProductId)
          val _tmpPurchaseTime: Long
          _tmpPurchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          val _tmpSponsorSince: Long
          _tmpSponsorSince = _stmt.getLong(_columnIndexOfSponsorSince)
          val _tmpConsumed: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfConsumed).toInt()
          _tmpConsumed = _tmp != 0
          val _tmpContributionCount: Int
          _tmpContributionCount = _stmt.getLong(_columnIndexOfContributionCount).toInt()
          val _tmpLastContributionTime: Long
          _tmpLastContributionTime = _stmt.getLong(_columnIndexOfLastContributionTime)
          _result = SponsorEntity(_tmpId,_tmpPurchaseToken,_tmpProductId,_tmpPurchaseTime,_tmpSponsorSince,_tmpConsumed,_tmpContributionCount,_tmpLastContributionTime)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLatestSponsor(): SponsorEntity? {
    val _sql: String = "SELECT * FROM Sponsor ORDER BY id DESC LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchase_token")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "product_id")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchase_time")
        val _columnIndexOfSponsorSince: Int = getColumnIndexOrThrow(_stmt, "sponsor_since")
        val _columnIndexOfConsumed: Int = getColumnIndexOrThrow(_stmt, "consumed")
        val _columnIndexOfContributionCount: Int = getColumnIndexOrThrow(_stmt, "contribution_count")
        val _columnIndexOfLastContributionTime: Int = getColumnIndexOrThrow(_stmt, "last_contribution_time")
        val _result: SponsorEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpPurchaseToken: String
          _tmpPurchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          val _tmpProductId: String
          _tmpProductId = _stmt.getText(_columnIndexOfProductId)
          val _tmpPurchaseTime: Long
          _tmpPurchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          val _tmpSponsorSince: Long
          _tmpSponsorSince = _stmt.getLong(_columnIndexOfSponsorSince)
          val _tmpConsumed: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfConsumed).toInt()
          _tmpConsumed = _tmp != 0
          val _tmpContributionCount: Int
          _tmpContributionCount = _stmt.getLong(_columnIndexOfContributionCount).toInt()
          val _tmpLastContributionTime: Long
          _tmpLastContributionTime = _stmt.getLong(_columnIndexOfLastContributionTime)
          _result = SponsorEntity(_tmpId,_tmpPurchaseToken,_tmpProductId,_tmpPurchaseTime,_tmpSponsorSince,_tmpConsumed,_tmpContributionCount,_tmpLastContributionTime)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSponsorCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM Sponsor"
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

  public override fun observeAllSponsors(): Flow<List<SponsorEntity>> {
    val _sql: String = "SELECT * FROM Sponsor ORDER BY id DESC"
    return createFlow(__db, false, arrayOf("Sponsor")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfPurchaseToken: Int = getColumnIndexOrThrow(_stmt, "purchase_token")
        val _columnIndexOfProductId: Int = getColumnIndexOrThrow(_stmt, "product_id")
        val _columnIndexOfPurchaseTime: Int = getColumnIndexOrThrow(_stmt, "purchase_time")
        val _columnIndexOfSponsorSince: Int = getColumnIndexOrThrow(_stmt, "sponsor_since")
        val _columnIndexOfConsumed: Int = getColumnIndexOrThrow(_stmt, "consumed")
        val _columnIndexOfContributionCount: Int = getColumnIndexOrThrow(_stmt, "contribution_count")
        val _columnIndexOfLastContributionTime: Int = getColumnIndexOrThrow(_stmt, "last_contribution_time")
        val _result: MutableList<SponsorEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SponsorEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpPurchaseToken: String
          _tmpPurchaseToken = _stmt.getText(_columnIndexOfPurchaseToken)
          val _tmpProductId: String
          _tmpProductId = _stmt.getText(_columnIndexOfProductId)
          val _tmpPurchaseTime: Long
          _tmpPurchaseTime = _stmt.getLong(_columnIndexOfPurchaseTime)
          val _tmpSponsorSince: Long
          _tmpSponsorSince = _stmt.getLong(_columnIndexOfSponsorSince)
          val _tmpConsumed: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfConsumed).toInt()
          _tmpConsumed = _tmp != 0
          val _tmpContributionCount: Int
          _tmpContributionCount = _stmt.getLong(_columnIndexOfContributionCount).toInt()
          val _tmpLastContributionTime: Long
          _tmpLastContributionTime = _stmt.getLong(_columnIndexOfLastContributionTime)
          _item = SponsorEntity(_tmpId,_tmpPurchaseToken,_tmpProductId,_tmpPurchaseTime,_tmpSponsorSince,_tmpConsumed,_tmpContributionCount,_tmpLastContributionTime)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateConsumed(id: Long, consumed: Boolean) {
    val _sql: String = "UPDATE Sponsor SET consumed = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (consumed) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun incrementContribution(id: Long, timestamp: Long) {
    val _sql: String = "UPDATE Sponsor SET contribution_count = contribution_count + 1, last_contribution_time = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM Sponsor"
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
