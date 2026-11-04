/*
 * Copyright 2026 SERF (RethinkDNS fork) and its authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.celzero.bravedns.service

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * F191 unit tests — pure-logic parts of BlockPageManager (no sockets, no koin).
 * Covers: query parsing, HTML escaping (4.2.7 attacker-controlled data), input
 * validation on the unblock path, recently-blocked bookkeeping, and page rendering.
 */
class BlockPageManagerTest {

    @Test
    fun testParseQuery_basic() {
        val q = BlockPageManager.parseQuery("/?action=unblock&host=example.com&scope=everybody")
        assertEquals("unblock", q["action"])
        assertEquals("example.com", q["host"])
        assertEquals("everybody", q["scope"])
    }

    @Test
    fun testParseQuery_urlDecodedAndEdgeCases() {
        val q = BlockPageManager.parseQuery("/x?host=a%2Eb.com&empty=&noequals")
        assertEquals("a.b.com", q["host"])
        assertEquals("", q["empty"])
        assertEquals("", q["noequals"])
        assertTrue(BlockPageManager.parseQuery("/noquestionmark").isEmpty())
    }

    @Test
    fun testEsc_htmlSpecialCharsAreNeutralized() {
        val evil = "<script>alert('x\"y')</script>&z"
        val safe = BlockPageManager.esc(evil)
        assertFalse(safe.contains("<script>"))
        assertTrue(safe.contains("&lt;script&gt;"))
        assertTrue(safe.contains("&#39;"))
        assertTrue(safe.contains("&quot;"))
        assertTrue(safe.contains("&amp;z"))
    }

    @Test
    fun testHandleUnblock_rejectsMalformedHostsWithoutTouchingDb() {
        // blank / dot-traversal inputs must fail closed before any DB work; these
        // paths never reach koin so the test runs without a standing-up app graph.
        assertFalse(BlockPageManager.handleUnblock("", null))
        assertFalse(BlockPageManager.handleUnblock("...", null))
        assertFalse(BlockPageManager.handleUnblock("..a..b", null))
        assertFalse(BlockPageManager.handleUnblock("   ", null))
    }

    @Test
    fun testNoteBlocked_dedupMostRecentFirstAndCap() {
        for (i in 1..25) BlockPageManager.noteBlocked("d$i.test")
        val page = BlockPageManager.renderPage(null)
        // capped at 20: oldest five evicted
        assertFalse(page.contains("d1.test"))
        assertFalse(page.contains("d5.test"))
        assertTrue(page.contains("d6.test"))
        assertTrue(page.contains("d25.test"))
        // dedup: re-noting d25 must not grow the list / drop newer entries
        BlockPageManager.noteBlocked("d25.test")
        val again = BlockPageManager.renderPage(null)
        assertTrue(again.contains("d25.test"))
        assertTrue(again.contains("d24.test"))
        assertFalse(again.contains("d6.test")) // one more slot freed, oldest evicted
    }

    @Test
    fun testRenderPage_containsHostEscapedAndUnblockLinks() {
        val html = BlockPageManager.renderPage("evil<iframe>.com")
        assertTrue(html.contains("&lt;iframe&gt;"))
        assertFalse(html.contains("<iframe"))
        assertTrue(html.contains("action=unblock"))
        assertTrue(html.contains("Unblock everywhere"))
        // zero-jargon education requirement (STATE 14.3)
        assertTrue(html.contains("Nothing was sent to the internet."))
    }

    @Test
    fun testRenderPage_noHost_hasNoUnblockLinks() {
        val html = BlockPageManager.renderPage(null)
        assertFalse(html.contains("action=unblock"))
        assertTrue(html.contains("A connection on this device was blocked."))
    }

    @Test
    fun testServerOffByDefault_isNotRunning() {
        // Module Contract 4.2.4: nothing binds until explicitly enabled
        assertFalse(BlockPageManager.isRunning())
    }
}
