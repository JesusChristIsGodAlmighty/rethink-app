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

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * F224 Blocklist Manager DAO: add / remove / reorder / toggle user's
 * blocklists on one screen. Ordering is explicit (sortOrder column) rather
 * than implicit rowid order, so reordering survives refreshes of the
 * REFRESHABLE rows.
 */
@Dao
interface CustomBlocklistEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(entry: CustomBlocklistEntry): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entries: List<CustomBlocklistEntry>): LongArray

    @Update fun update(entry: CustomBlocklistEntry)

    @Delete fun delete(entry: CustomBlocklistEntry)

    @Query("delete from CustomBlocklistEntry where id = :id") fun deleteById(id: Int)

    @Query("select * from CustomBlocklistEntry order by sortOrder asc, addedAtTs desc")
    fun getAllEntries(): List<CustomBlocklistEntry>

    @Query("select * from CustomBlocklistEntry where kind = :kind order by sortOrder asc")
    fun getEntriesByKind(kind: String): List<CustomBlocklistEntry>

    @Query("select * from CustomBlocklistEntry where isSelected = 1 order by sortOrder asc")
    fun getSelectedEntries(): List<CustomBlocklistEntry>

    @Query("select * from CustomBlocklistEntry where value = :tagValue limit 1")
    fun findByTagValue(tagValue: Int): CustomBlocklistEntry?

    @Query("update CustomBlocklistEntry set isSelected = :selected, updatedAtTs = :ts where id = :id")
    fun updateSelection(id: Int, selected: Boolean, ts: Long)

    @Query("update CustomBlocklistEntry set sortOrder = :order, updatedAtTs = :ts where id = :id")
    fun updateSortOrder(id: Int, order: Int, ts: Long)

    @Query("select count(*) from CustomBlocklistEntry") fun count(): Int

    @Query("delete from CustomBlocklistEntry where kind = :kind") fun deleteByKind(kind: String)
}
