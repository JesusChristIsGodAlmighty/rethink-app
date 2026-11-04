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

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * F224 Blocklist Manager: a user-managed blocklist entry.
 *
 * Two kinds of entries coexist in one table so the user's list curation
 * lives on ONE screen (STATE_v2 F224):
 *  - REFRESHABLE: an upstream Rethink remote/local blocklist pack that the
 *    app refreshes from json downloads. `value` matches the file-tag id in
 *    RethinkRemoteFileTag / RethinkLocalFileTag; `isSelected` mirrors the
 *    tag's selection state. Refreshes keep these rows' selection intact.
 *  - USER_ADDED: a custom list the user added by name/url (e.g. an OISD or
 *    StevenBlack host file). Not refreshed by the built-in downloaders;
 *    applied via per-domain rules or future stamp injection. `sourceUrl`
 *    is stored sanitized (scheme + host only) — never trusted input.
 *
 * Module Contract 4.3: carries a schema version column from day one so the
 * row format itself can evolve independently of the DB schema.
 */
@Entity(tableName = "CustomBlocklistEntry")
class CustomBlocklistEntry {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    var kind: String = KIND_USER_ADDED // KIND_REFRESHABLE | KIND_USER_ADDED
    var title: String = ""
    var sourceUrl: String = ""
    var author: String = ""
    var description: String = ""
    var value: Int = INVALID_TAG_VALUE // rethink file-tag id for refreshable entries
    var isSelected: Boolean = false
    var sortOrder: Int = DEFAULT_ORDER // user-controlled ordering on the one screen
    var addedAtTs: Long = 0L
    var updatedAtTs: Long = 0L
    var schemaVersion: Int = SCHEMA_VERSION

    override fun equals(other: Any?): Boolean {
        if (other !is CustomBlocklistEntry) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    companion object {
        const val SCHEMA_VERSION = 1
        const val KIND_REFRESHABLE = "refreshable"
        const val KIND_USER_ADDED = "user-added"
        const val INVALID_TAG_VALUE = -1
        const val DEFAULT_ORDER = 0
    }
}
