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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * F224 Blocklist Manager unit tests: URL/title sanitization (attacker
 * influenced input, Module Contract 4.2.7), pure reordering, and repository
 * calls against a mocked DAO. No Android runtime needed beyond Log, which
 * is stubbed by the existing test setup (unmockk'd via unitTests.returnDefaultValues).
 */
class CustomBlocklistEntryRepositoryTest {

    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: CustomBlocklistEntryRepository
    private val dao: CustomBlocklistEntryDao = mockk(relaxed = true)

    @Before
    fun setup() {
        repository = CustomBlocklistEntryRepository(dao)
    }

    // ---- sanitizeSourceUrl ----

    @Test
    fun `sanitizes https url keeping scheme and host only`() {
        val s = CustomBlocklistEntryRepository.sanitizeSourceUrl("https://lists.oisd.nl/new/all?x=1#frag")
        assertEquals("https://lists.oisd.nl", s)
    }

    @Test
    fun `sanitizes http url with port`() {
        val s = CustomBlocklistEntryRepository.sanitizeSourceUrl("http://example.com:8080/path/to/file.txt")
        assertEquals("http://example.com:8080", s)
    }

    @Test
    fun `rejects non-http schemes including javascript and file`() {
        assertNull(CustomBlocklistEntryRepository.sanitizeSourceUrl("javascript:alert(1)"))
        assertNull(CustomBlocklistEntryRepository.sanitizeSourceUrl("file:///etc/passwd"))
        assertNull(CustomBlocklistEntryRepository.sanitizeSourceUrl("ftp://example.com/pub/hosts.txt"))
    }

    @Test
    fun `rejects blank null and userinfo smuggling`() {
        assertNull(CustomBlocklistEntryRepository.sanitizeSourceUrl(null))
        assertNull(CustomBlocklistEntryRepository.sanitizeSourceUrl("   "))
        // userinfo stripped -> host preserved; but empty authority rejected
        assertNull(CustomBlocklistEntryRepository.sanitizeSourceUrl("https://@/evil"))
    }

    @Test
    fun `rejects urls over max length`() {
        val long = "https://" + "a".repeat(300) + ".com"
        assertNull(CustomBlocklistEntryRepository.sanitizeSourceUrl(long))
    }

    @Test
    fun `normalizes scheme case and accepts ipv6 host`() {
        assertEquals("https://example.com", CustomBlocklistEntryRepository.sanitizeSourceUrl("HTTPS://example.com/x"))
        assertEquals("https://[::1]:8443", CustomBlocklistEntryRepository.sanitizeSourceUrl("https://[::1]:8443/p"))
    }

    // ---- sanitizeTitle ----

    @Test
    fun `trims caps and strips control chars from title`() {
        val t = CustomBlocklistEntryRepository.sanitizeTitle("  OISD\nBig\u0000Base  ")
        assertEquals("OISDBigBase", t.replace("\n", ""))
        val capped = CustomBlocklistEntryRepository.sanitizeTitle("x".repeat(500))
        assertEquals(CustomBlocklistEntryRepository.MAX_TITLE_LENGTH, capped.length)
        assertEquals("", CustomBlocklistEntryRepository.sanitizeTitle(null))
        assertEquals("", CustomBlocklistEntryRepository.sanitizeTitle("   "))
    }

    // ---- reordered (pure) ----

    private fun entry(id: Int, order: Int) =
        CustomBlocklistEntry().apply {
            this.id = id
            sortOrder = order
            title = "list-$id"
        }

    @Test
    fun `reorder moves item and shifts rest without mutating input`() {
        val items = listOf(entry(1, 0), entry(2, 1), entry(3, 2))
        val moved = CustomBlocklistEntryRepository.reordered(items, 2, 0)
        assertEquals(listOf(3, 1, 2), moved.map { it.id })
        // original untouched
        assertEquals(listOf(1, 2, 3), items.map { it.id })
    }

    @Test
    fun `reorder clamps out-of-range indices and no-ops same position`() {
        val items = listOf(entry(1, 0), entry(2, 1))
        assertEquals(listOf(2, 1), CustomBlocklistEntryRepository.reordered(items, -5, 99).map { it.id })
        assertEquals(listOf(1, 2), CustomBlocklistEntryRepository.reordered(items, 1, 1).map { it.id })
        assertTrue(CustomBlocklistEntryRepository.reordered(emptyList(), 0, 1).isEmpty())
    }

    // ---- repository / DAO interactions ----

    @Test
    fun `addUserList rejects invalid url or title and persists valid entry`() = runBlocking {
        assertNull(repository.addUserList("My list", "not-a-url"))
        assertNull(repository.addUserList("", "https://good.example"))
        coVerify(exactly = 0) { dao.insert(any()) }

        every { dao.getAllEntries() } returns listOf(entry(1, 5))
        coEvery { dao.insert(any()) } returns 42L
        val added = repository.addUserList("  My List  ", "https://blocks.example/hosts.txt?a=1")
        assertNotNull(added)
        assertEquals("My List", added!!.title)
        assertEquals("https://blocks.example", added.sourceUrl)
        assertEquals(CustomBlocklistEntry.KIND_USER_ADDED, added.kind)
        assertTrue(added.isSelected)
        assertEquals(6, added.sortOrder) // max existing (5) + 1
    }

    @Test
    fun `toggleSelection and remove delegate to dao`() = runBlocking {
        repository.toggleSelection(7, false)
        coVerify { dao.updateSelection(7, false, any()) }
        assertTrue(repository.remove(7))
        coVerify { dao.deleteById(7) }
    }

    @Test
    fun `applyOrder writes index as sortOrder for each id`() = runBlocking {
        repository.applyOrder(listOf(3, 1, 2))
        coVerify { dao.updateSortOrder(3, 0, any()) }
        coVerify { dao.updateSortOrder(1, 1, any()) }
        coVerify { dao.updateSortOrder(2, 2, any()) }
    }

    @Test
    fun `syncRefreshableFrom only inserts missing tags preserving existing selection`() = runBlocking {
        val existing = CustomBlocklistEntry().apply {
            kind = CustomBlocklistEntry.KIND_REFRESHABLE
            value = 10
            isSelected = false
            sortOrder = 1
        }
        every { dao.getAllEntries() } returns listOf(existing)
        val tag10 = RethinkRemoteFileTag().apply { value = 10; vname = "Existing"; isSelected = true }
        val tag11 = RethinkRemoteFileTag().apply { value = 11; vname = "New Pack"; isSelected = true }
        var inserted: List<CustomBlocklistEntry>? = null
        coEvery { dao.insertAll(any()) } answers {
            inserted = firstArg()
            LongArray(inserted!!.size) { it.toLong() }
        }
        repository.syncRefreshableFrom(listOf(tag10, tag11))
        assertNotNull(inserted)
        assertEquals(1, inserted!!.size)
        assertEquals(11, inserted!![0].value) // tag 10 already mirrored -> not re-inserted
        assertEquals(CustomBlocklistEntry.KIND_REFRESHABLE, inserted!![0].kind)
    }

    // ---- F225 Explicit Update Indicator: updateFreshness pure logic ----

    @Test
    fun `updateFreshness never when no successful refresh recorded`() {
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.NEVER,
            CustomBlocklistEntryRepository.updateFreshness(0L, 1_700_000_000_000L))
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.NEVER,
            CustomBlocklistEntryRepository.updateFreshness(-5L, 1_700_000_000_000L))
    }

    @Test
    fun `updateFreshness fresh within one hour`() {
        val now = 1_700_000_000_000L
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.FRESH,
            CustomBlocklistEntryRepository.updateFreshness(now - 30L * 60_000L, now))
        // boundary: exactly one hour old is still FRESH (<=)
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.FRESH,
            CustomBlocklistEntryRepository.updateFreshness(now - CustomBlocklistEntryRepository.ONE_HOUR_MS, now))
    }

    @Test
    fun `updateFreshness today between one hour and one day`() {
        val now = 1_700_000_000_000L
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.TODAY,
            CustomBlocklistEntryRepository.updateFreshness(now - (CustomBlocklistEntryRepository.ONE_HOUR_MS + 1L), now))
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.TODAY,
            CustomBlocklistEntryRepository.updateFreshness(now - CustomBlocklistEntryRepository.ONE_DAY_MS, now))
    }

    @Test
    fun `updateFreshness this week between one and seven days`() {
        val now = 1_700_000_000_000L
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.THIS_WEEK,
            CustomBlocklistEntryRepository.updateFreshness(now - (CustomBlocklistEntryRepository.ONE_DAY_MS + 1L), now))
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.THIS_WEEK,
            CustomBlocklistEntryRepository.updateFreshness(now - CustomBlocklistEntryRepository.SEVEN_DAYS_MS, now))
    }

    @Test
    fun `updateFreshness stale beyond seven days`() {
        val now = 1_700_000_000_000L
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.STALE,
            CustomBlocklistEntryRepository.updateFreshness(now - (CustomBlocklistEntryRepository.SEVEN_DAYS_MS + 1L), now))
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.STALE,
            CustomBlocklistEntryRepository.updateFreshness(1L, now))
    }

    @Test
    fun `updateFreshness future-dated ts counts as fresh not error`() {
        val now = 1_700_000_000_000L
        // clock skew: refresh ts in the future -> negative age -> FRESH
        assertEquals(CustomBlocklistEntryRepository.UpdateFreshness.FRESH,
            CustomBlocklistEntryRepository.updateFreshness(now + 60L * 60_000L, now))
    }
}
