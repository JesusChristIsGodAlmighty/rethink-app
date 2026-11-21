package com.celzero.bravedns.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        AppInfoEntity::class,
        DnsLogEntity::class,
        ConnectionLogEntity::class,
        CustomDomainEntity::class,
        CustomIpEntity::class,
        DnsEndpointEntity::class,
        BlocklistTagEntity::class,
        WireGuardConfigEntity::class,
        ProxyConfigEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RethinkDatabase : RoomDatabase() {
    abstract fun appInfoDao(): AppInfoDao
    abstract fun dnsLogDao(): DnsLogDao
    abstract fun connectionLogDao(): ConnectionLogDao
    abstract fun customDomainDao(): CustomDomainDao
    abstract fun customIpDao(): CustomIpDao
    abstract fun dnsEndpointDao(): DnsEndpointDao
    abstract fun blocklistTagDao(): BlocklistTagDao
    abstract fun wireGuardConfigDao(): WireGuardConfigDao
    abstract fun proxyConfigDao(): ProxyConfigDao

    companion object {
        @Volatile
        private var INSTANCE: RethinkDatabase? = null

        fun getInstance(context: Context): RethinkDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RethinkDatabase::class.java,
                    "rethink_compose.db"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
