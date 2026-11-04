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
package com.celzero.bravedns.database

import androidx.room.Transaction
import android.util.Log

/**
 * F224 Blocklist Manager repository. Thin wrapper over the DAO plus the
 * pure logic used by the one-screen manager: input sanitization and list
 * reordering. The pure functions live here (not in the ViewModel) so both
 * UI and future modules reuse them and unit tests run without Android deps
 * beyond android.util.Log (stubbed in tests).
 */
class CustomBlocklistEntryRepository(private val customBlocklistEntryDao: CustomBlocklistEntryDao) {

    companion object {
        private const val TAG = "CustomBlocklistRepo"
        const val MAX_TITLE_LENGTH = 100
        const val MAX_URL_LENGTH = 250
        // hostnames are capped well below the RFC 1035 253-octet limit
        const val MAX_HOST_LENGTH = 253

        /**
         * Module Contract 4.2.7: user-entered URLs are attacker-influenced
         * data. Sanitize before storing: trim, length-cap, require an
         * http(s) scheme, and keep only scheme + host (+port) — path/query
         * fragments are dropped so a crafted URL can't smuggle content into
         * the UI. Returns null when the input is not a usable http(s) URL.
         */
        fun sanitizeSourceUrl(raw: String?): String? {
            if (raw.isNullOrBlank()) return null
            val trimmed = raw.trim().take(MAX_URL_LENGTH)
            if (trimmed.length > MAX_URL_LENGTH) return null
            val lower = trimmed.lowercase()
            if (!lower.startsWith("http://") && !lower.startsWith("https://")) return null
            val schemeEnd = trimmed.indexOf("://")
            val rest = trimmed.substring(schemeEnd + 3)
            // strip path/query/fragment/userinfo
            val authority = rest.substringBefore('/').substringBefore('?').substringBefore('#')
            val hostPart = authority.substringAfterLast('@', authority)
            if (hostPart.isBlank()) return null
            val host = hostPart.take(MAX_HOST_LENGTH)
            if (!host.matches(Regex("^[a-z0-9]([a-z0-9.\\-_]*[a-z0-9])?(:[0-9]{1,5})?$")) &&
                !host.matches(Regex("^\\[[0-9a-f:.]+\\](:[0-9]{1,5})?$"))) {
                return null
            }
            val scheme = if (lower.startsWith("https")) "https" else "http"
            return "$scheme://$host"
        }

        /** Trim + length-cap free-text fields; strips control characters. */
        fun sanitizeTitle(raw: String?): String {
            if (raw.isNullOrBlank()) return ""
            val cleaned = raw.trim().replace(Regex("[\\p{Cntrl}]"), "")
            return cleaned.take(MAX_TITLE_LENGTH)
        }

        /**
         * Pure reorder: move the item currently at `from` to position `to`
         * (both clamped into range), shifting the rest. Returns a NEW list;
         * input untouched. Kept pure so CI unit tests cover ordering without
         * a Room DB.
         */
        fun reordered(items: List<CustomBlocklistEntry>, from: Int, to: Int): List<CustomBlocklistEntry> {
            if (items.isEmpty()) return emptyList()
            val src = from.coerceIn(0, items.size - 1)
            val dst = to.coerceIn(0, items.size - 1)
            if (src == dst) return items.toList()
            val mutable = items.toMutableList()
            val moved = mutable.removeAt(src)
            mutable.add(dst, moved)
            return mutable
        }
    }

    @Transaction
    suspend fun fetchAll(): List<CustomBlocklistEntry> {
        return try {
            customBlocklistEntryDao.getAllEntries()
        } catch (e: Exception) {
            Log.w(TAG, "Error fetching blocklist entries", e)
            emptyList()
        }
    }

    @Transaction
    suspend fun fetchSelected(): List<CustomBlocklistEntry> {
        return try {
            customBlocklistEntryDao.getSelectedEntries()
        } catch (e: Exception) {
            Log.w(TAG, "Error fetching selected blocklist entries", e)
            emptyList()
        }
    }

    /** Add a user-supplied list. Invalid url/title -> null (no row written). */
    @Transaction
    suspend fun addUserList(title: String?, url: String?): CustomBlocklistEntry? {
        val cleanTitle = sanitizeTitle(title)
        if (cleanTitle.isEmpty()) return null
        val cleanUrl = sanitizeSourceUrl(url) ?: return null
        val now = System.currentTimeMillis()
        val maxOrder = try {
            customBlocklistEntryDao.getAllEntries().maxOfOrNull { it.sortOrder } ?: 0
        } catch (e: Exception) {
            0
        }
        val entry =
            CustomBlocklistEntry().apply {
                kind = CustomBlocklistEntry.KIND_USER_ADDED
                this.title = cleanTitle
                sourceUrl = cleanUrl
                isSelected = true // adding a list implies wanting it on; per-row switch still visible
                sortOrder = maxOrder + 1
                addedAtTs = now
                updatedAtTs = now
            }
        return try {
            val id = customBlocklistEntryDao.insert(entry)
            if (id <= 0L) null else entry.apply { this.id = id.toInt() }
        } catch (e: Exception) {
            Log.w(TAG, "Error inserting user blocklist", e)
            null
        }
    }

    @Transaction
    suspend fun remove(id: Int): Boolean {
        return try {
            customBlocklistEntryDao.deleteById(id)
            true
        } catch (e: Exception) {
            Log.w(TAG, "Error removing blocklist id=$id", e)
            false
        }
    }

    @Transaction
    suspend fun toggleSelection(id: Int, selected: Boolean) {
        try {
            customBlocklistEntryDao.updateSelection(id, selected, System.currentTimeMillis())
        } catch (e: Exception) {
            Log.w(TAG, "Error toggling blocklist id=$id", e)
        }
    }

    /** Persist a new order given as the id sequence (index = sortOrder). */
    @Transaction
    suspend fun applyOrder(orderedIds: List<Int>) {
        try {
            orderedIds.forEachIndexed { index, id ->
                customBlocklistEntryDao.updateSortOrder(id, index, System.currentTimeMillis())
            }
        } catch (e: Exception) {
            Log.w(TAG, "Error applying blocklist order", e)
        }
    }

    /**
     * Mirror refreshable rethink tags into the manager table so upstream
     * lists appear on the same screen. Only inserts missing rows (selection
     * state of existing rows is preserved across refreshes — that was the
     * bug class readRemoteJson's deleteAll+updateTags dance had to work
     * around; here the manager table never gets wholesale-deleted).
     */
    @Transaction
    suspend fun syncRefreshableFrom(tags: List<RethinkRemoteFileTag>) {
        try {
            val existing = customBlocklistEntryDao.getAllEntries()
            var nextOrder = (existing.maxOfOrNull { it.sortOrder } ?: 0) + 1
            val now = System.currentTimeMillis()
            val missing =
                tags.filter { t ->
                    existing.none { it.kind == CustomBlocklistEntry.KIND_REFRESHABLE && it.value == t.value }
                }
            if (missing.isEmpty()) return
            customBlocklistEntryDao.insertAll(
                missing.map { t ->
                    CustomBlocklistEntry().apply {
                        kind = CustomBlocklistEntry.KIND_REFRESHABLE
                        title = sanitizeTitle(if (t.vname.isNotBlank()) t.vname else t.uname)
                        sourceUrl = sanitizeSourceUrl(t.url.firstOrNull()) ?: ""
                        author = sanitizeTitle(t.group)
                        value = t.value
                        isSelected = t.isSelected
                        sortOrder = nextOrder++
                        addedAtTs = now
                        updatedAtTs = now
                    }
                }
            )
        } catch (e: Exception) {
            Log.w(TAG, "Error syncing refreshable blocklists", e)
        }
    }

    @Transaction
    suspend fun count(): Int {
        return try {
            customBlocklistEntryDao.count()
        } catch (e: Exception) {
            0
        }
    }
}
