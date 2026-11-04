/*
Copyright 2026 RethinkDNS and its authors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.celzero.bravedns.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celzero.bravedns.database.CustomBlocklistEntry
import com.celzero.bravedns.database.CustomBlocklistEntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * F224 Blocklist Manager: backs the one-screen list curator (add / remove /
 * reorder / toggle). All mutations go through the repository which
 * sanitizes user input (Module Contract 4.2.7) before touching the DB.
 * The list is refreshed after each successful mutation; UI observes
 * `entries`.
 */
class CustomBlocklistViewModel(private val customBlocklistRepository: CustomBlocklistEntryRepository) :
    ViewModel() {

    val entries: MutableLiveData<List<CustomBlocklistEntry>> = MutableLiveData()
    // one-shot event channel for user feedback (invalid url, add failure...)
    val lastError: MutableLiveData<String?> = MutableLiveData(null)

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val all = withContext(Dispatchers.IO) { customBlocklistRepository.fetchAll() }
            entries.value = all
        }
    }

    fun addUserList(title: String, url: String) {
        viewModelScope.launch {
            val added = withContext(Dispatchers.IO) { customBlocklistRepository.addUserList(title, url) }
            if (added == null) {
                lastError.value = "invalid_title_or_url"
            } else {
                lastError.value = null
                refresh()
            }
        }
    }

    fun toggle(entry: CustomBlocklistEntry, selected: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { customBlocklistRepository.toggleSelection(entry.id, selected) }
            refresh()
        }
    }

    fun remove(entry: CustomBlocklistEntry) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { customBlocklistRepository.remove(entry.id) }
            refresh()
        }
    }

    /** Move item from position [from] to [to]; persists the new order. */
    fun move(from: Int, to: Int) {
        val current = entries.value ?: return
        val reordered = CustomBlocklistEntryRepository.reordered(current, from, to)
        entries.value = reordered // optimistic
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                customBlocklistRepository.applyOrder(reordered.map { it.id })
            }
            refresh()
        }
    }
}
