package com.celzero.bravedns.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.celzero.bravedns.data.RethinkRepository

class RethinkViewModelFactory(
    private val repository: RethinkRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository) as T
            modelClass.isAssignableFrom(ConfigureViewModel::class.java) -> ConfigureViewModel(repository) as T
            modelClass.isAssignableFrom(LogsStatsViewModel::class.java) -> LogsStatsViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
