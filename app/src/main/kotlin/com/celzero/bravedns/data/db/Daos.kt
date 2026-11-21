package com.celzero.bravedns.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

data class RankedStatRow(
    val label: String,
    val subLabel: String,
    val totalCount: Int,
    val totalBytes: Long
)

@Dao
interface AppInfoDao {
    @Query("SELECT * FROM app_info ORDER BY isSystemApp ASC, appName COLLATE NOCASE ASC")
    fun observeAll(): Flow<List<AppInfoEntity>>

    @Query("SELECT * FROM app_info")
    suspend fun getAllOnce(): List<AppInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<AppInfoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: AppInfoEntity)

    @Query("UPDATE app_info SET connectionStatus = :status, modifiedTs = :ts WHERE uid = :uid AND packageName = :pkg")
    suspend fun updateConnectionStatus(uid: Int, pkg: String, status: Int, ts: Long = System.currentTimeMillis())

    @Query("UPDATE app_info SET firewallStatus = :status, modifiedTs = :ts WHERE uid = :uid AND packageName = :pkg")
    suspend fun updateFirewallStatus(uid: Int, pkg: String, status: Int, ts: Long = System.currentTimeMillis())

    @Query("SELECT COUNT(*) FROM app_info WHERE connectionStatus != 3")
    fun observeBlockedAppCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM app_info WHERE connectionStatus = 3")
    fun observeAllowedAppCount(): Flow<Int>
}

@Dao
interface DnsLogDao {
    @Query("SELECT * FROM dns_logs ORDER BY timeMs DESC LIMIT 300")
    fun observeRecent(): Flow<List<DnsLogEntity>>

    @Query("SELECT * FROM dns_logs WHERE timeMs >= :sinceMs ORDER BY timeMs DESC")
    fun observeSince(sinceMs: Long): Flow<List<DnsLogEntity>>

    @Insert
    suspend fun insert(log: DnsLogEntity)

    @Query("DELETE FROM dns_logs WHERE timeMs < :olderThanMs")
    suspend fun purgeOlderThan(olderThanMs: Long)

    @Query("DELETE FROM dns_logs")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM dns_logs WHERE isBlocked = 1")
    fun observeBlockedCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM dns_logs")
    fun observeTotalCount(): Flow<Int>

    @Query(
        """
        SELECT queryStr AS label, MAX(resolverName) AS subLabel, COUNT(*) AS totalCount, 0 AS totalBytes
        FROM dns_logs
        WHERE timeMs >= :sinceMs AND isBlocked = :blocked
        GROUP BY queryStr
        ORDER BY totalCount DESC
        LIMIT 12
        """
    )
    fun observeTopDomains(sinceMs: Long, blocked: Boolean): Flow<List<RankedStatRow>>
}

@Dao
interface ConnectionLogDao {
    @Query("SELECT * FROM connection_logs ORDER BY timeStampMs DESC LIMIT 300")
    fun observeRecent(): Flow<List<ConnectionLogEntity>>

    @Query("SELECT * FROM connection_logs WHERE timeStampMs >= :sinceMs ORDER BY timeStampMs DESC")
    fun observeSince(sinceMs: Long): Flow<List<ConnectionLogEntity>>

    @Insert
    suspend fun insert(log: ConnectionLogEntity)

    @Query("DELETE FROM connection_logs WHERE timeStampMs < :olderThanMs")
    suspend fun purgeOlderThan(olderThanMs: Long)

    @Query("DELETE FROM connection_logs")
    suspend fun clearAll()

    @Query(
        """
        SELECT appName AS label, MAX(packageName) AS subLabel, COUNT(*) AS totalCount, SUM(downloadBytes + uploadBytes) AS totalBytes
        FROM connection_logs
        WHERE timeStampMs >= :sinceMs AND isBlocked = :blocked
        GROUP BY appName
        ORDER BY totalCount DESC
        LIMIT 12
        """
    )
    fun observeTopApps(sinceMs: Long, blocked: Boolean): Flow<List<RankedStatRow>>

    @Query(
        """
        SELECT ipAddress AS label, MAX(dnsQuery) AS subLabel, COUNT(*) AS totalCount, SUM(downloadBytes + uploadBytes) AS totalBytes
        FROM connection_logs
        WHERE timeStampMs >= :sinceMs AND isBlocked = :blocked
        GROUP BY ipAddress
        ORDER BY totalCount DESC
        LIMIT 12
        """
    )
    fun observeTopIps(sinceMs: Long, blocked: Boolean): Flow<List<RankedStatRow>>
}

@Dao
interface CustomDomainDao {
    @Query("SELECT * FROM custom_domains ORDER BY modifiedTs DESC")
    fun observeAll(): Flow<List<CustomDomainEntity>>

    @Query("SELECT * FROM custom_domains")
    suspend fun getAllOnce(): List<CustomDomainEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(rule: CustomDomainEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(rules: List<CustomDomainEntity>)

    @Query("DELETE FROM custom_domains WHERE domain = :domain AND uid = :uid")
    suspend fun delete(domain: String, uid: Int)

    @Query("SELECT COUNT(*) FROM custom_domains")
    fun observeCount(): Flow<Int>
}

@Dao
interface CustomIpDao {
    @Query("SELECT * FROM custom_ips ORDER BY modifiedTs DESC")
    fun observeAll(): Flow<List<CustomIpEntity>>

    @Query("SELECT * FROM custom_ips")
    suspend fun getAllOnce(): List<CustomIpEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(rule: CustomIpEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(rules: List<CustomIpEntity>)

    @Query("DELETE FROM custom_ips WHERE uid = :uid AND ipAddress = :ip AND port = :port AND protocol = :protocol")
    suspend fun delete(uid: Int, ip: String, port: Int, protocol: String)

    @Query("SELECT COUNT(*) FROM custom_ips")
    fun observeCount(): Flow<Int>
}

@Dao
interface DnsEndpointDao {
    @Query("SELECT * FROM dns_endpoints ORDER BY isSelected DESC, isCustom DESC, id ASC")
    fun observeAll(): Flow<List<DnsEndpointEntity>>

    @Query("SELECT * FROM dns_endpoints")
    suspend fun getAllOnce(): List<DnsEndpointEntity>

    @Query("SELECT * FROM dns_endpoints WHERE isSelected = 1 LIMIT 1")
    suspend fun getSelectedOnce(): DnsEndpointEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(endpoints: List<DnsEndpointEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(endpoint: DnsEndpointEntity)

    @Query("UPDATE dns_endpoints SET isSelected = 0")
    suspend fun clearSelection()

    @Query("UPDATE dns_endpoints SET isSelected = 1 WHERE id = :id")
    suspend fun selectById(id: Int)

    @Query("UPDATE dns_endpoints SET latencyMs = :latencyMs WHERE id = :id")
    suspend fun updateLatency(id: Int, latencyMs: Long)

    @Query("DELETE FROM dns_endpoints WHERE id = :id AND isCustom = 1")
    suspend fun deleteCustom(id: Int)
}

@Dao
interface BlocklistTagDao {
    @Query("SELECT * FROM blocklist_tags ORDER BY isSelected DESC, entriesCount DESC")
    fun observeAll(): Flow<List<BlocklistTagEntity>>

    @Query("SELECT * FROM blocklist_tags WHERE isSelected = 1")
    suspend fun getSelectedOnce(): List<BlocklistTagEntity>

    @Query("SELECT COUNT(*) FROM blocklist_tags")
    suspend fun countOnce(): Int

    @Query("SELECT COUNT(*) FROM blocklist_tags WHERE isSelected = 1")
    fun observeSelectedCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(tags: List<BlocklistTagEntity>)

    @Query("UPDATE blocklist_tags SET isSelected = :selected WHERE id = :id")
    suspend fun setSelected(id: String, selected: Boolean)

    @Query("UPDATE blocklist_tags SET isSelected = 0")
    suspend fun clearAllSelections()
}

@Dao
interface WireGuardConfigDao {
    @Query("SELECT * FROM wireguard_configs ORDER BY isActive DESC, id DESC")
    fun observeAll(): Flow<List<WireGuardConfigEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: WireGuardConfigEntity)

    @Query("UPDATE wireguard_configs SET isActive = CASE WHEN id = :id THEN :active ELSE 0 END")
    suspend fun setActive(id: Int, active: Boolean)

    @Query("DELETE FROM wireguard_configs WHERE id = :id")
    suspend fun delete(id: Int)
}

@Dao
interface ProxyConfigDao {
    @Query("SELECT * FROM proxy_configs ORDER BY isActive DESC, id DESC")
    fun observeAll(): Flow<List<ProxyConfigEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: ProxyConfigEntity)

    @Query("UPDATE proxy_configs SET isActive = CASE WHEN id = :id THEN :active ELSE 0 END")
    suspend fun setActive(id: Int, active: Boolean)

    @Query("DELETE FROM proxy_configs WHERE id = :id")
    suspend fun delete(id: Int)
}
