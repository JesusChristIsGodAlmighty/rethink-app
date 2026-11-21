package com.celzero.bravedns

import android.app.Application
import com.celzero.bravedns.data.RethinkRepository
import com.celzero.bravedns.data.SettingsDataStore
import com.celzero.bravedns.data.db.RethinkDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RethinkApp : Application() {

    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    lateinit var database: RethinkDatabase
        private set

    lateinit var settingsDataStore: SettingsDataStore
        private set

    lateinit var repository: RethinkRepository
        private set

    override fun onCreate() {
        super.onCreate()
        database = RethinkDatabase.getInstance(this)
        settingsDataStore = SettingsDataStore(this)
        repository = RethinkRepository(this, database, settingsDataStore)
        appScope.launch {
            repository.initializeDefaults()
        }
    }
}
