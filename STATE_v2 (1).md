# STATE.md: SERF (working name; formerly "Google Play Sucks")
Master blueprint. Fully consolidated this session — supersedes all prior STATE.md versions. Feature range: F01–F403+.

---

## 0. HOW TO RESUME (paste this whole file into a NEW chat; nothing else needed)

**Instructions to Claude (follow always, every session):**
1. Read this file fully before doing anything. Do ONLY the task in `NEXT` (section 10). No project recaps.
2. Tone: enthusiastic, can-do, builder mindset. The user is non-technical, building this from passion, not for money. Never dismiss an idea, never lead with reasons something won't work. If something is hard, explain how we'll do it. If a literal version of a request is impossible, deliver the closest working version and say what changed in one line, without being asked twice.
3. Be terse in explanation, generous in actual effort. Output complete files, not snippets.
4. End every working reply with a `CHECKPOINT` (max 10 lines: done, next, new decisions, open problems).
5. When a task's done test (section 9) passes, update section 10 and advance `NEXT`.
6. Never ask "where were we?" The answer is always `NEXT` in section 10.
7. **Nothing is ever dropped.** New ideas append to the Feature Register (section 5) with the next free F-number in the right category. If a new idea sounds like a duplicate, say which F-number and ask only if genuinely ambiguous.
8. Every new module follows the Module Contract (section 4.2) from the first line of code — not optional, not added later.
9. **Fork Decision Protocol (F679, Module Contract 4.2.17, hard rule).** If implementing a feature requires discarding, substantially changing, or making mutually exclusive with another already-registered feature, that is a genuine fork, not a routine design choice. When Claude identifies one, it stops and logs it in section 4.19's "Pending Fork Decisions" table with lettered options (A, B, C...), each stating plainly what is gained and what is discarded or changed. Claude does NOT proceed down any path at that fork until the user replies with a single letter. A reply that is not a bare letter (prose, "yes," "sounds good," a preference described in words) does not count as a decision — Claude asks again for a letter and the fork stays open, un-built, in the meantime. Work on unrelated, non-forked tasks continues normally. Once a letter is given, log the choice and its date/session in the same table and proceed.
10. **Research before building, always — the literal first step, every time (section 4.20, Module Contract 4.2.18).** Before writing any code/config/UI for any part of the app, first search for current advancements, standards, and open-source repos relevant to that specific part, even for features planned long ago, since the plan is a snapshot, not a ceiling. Then search for an existing, actively-maintained, appropriately-licensed open-source project that already does it, or most of it (Module Contract 4.2.16) — check section 4.18's Reusable Components Registry first, add newly-found components to it. Integrate and wrap rather than reimplement, unless no suitable project exists, the license is incompatible, or the project is abandoned.
11. **Section 11 (PARKING LOT) is reference-only.** Nothing in it gets built, scheduled, or assigned to a phase until the user explicitly says to un-park it. Don't suggest starting it; don't fold it into phase planning. It exists so the context and reasoning aren't lost before the user is ready to decide.
12. Reference material: the user has a full Gemini conversation PDF (89 pages) from the original brainstorm. Feature descriptions beyond the one-line summaries here can be requested by F-number when a task starts work on that feature.

**User routine (about 30 seconds per session):** new chat, paste this file, send "Resume." Copy each CHECKPOINT into section 10 of your saved copy. When Claude's limit message appears, note the reset time and return then with a new chat and the latest file. Keep this file in a Claude Project's knowledge so new chats auto-have it; re-upload after edits.

---

## 1. Mission and Vision
Build the most thoughtful, complete, intuitive network-control and privacy app Android has ever had — built from passion and care for the people who'll use it, not to extract money from them. Free. Open source. Powerful enough for experts, simple enough for a grandparent, honest about what it can't do. Everything most apps charge for, we give away; only server-dependent parts need a documented free workaround. Guiding standard for every decision: would a product built by someone who actually cared about the user, instead of a deadline and a paycheck, look like this?

## 2. Strategic Decisions (locked; revisit only with explicit reason)
- **D1 — Fork RethinkDNS.** Kotlin app + Go/gVisor networking core. Inherit a hardened foundation instead of rebuilding it. License believed MPL-2.0 — verify in T0.3. We intentionally diverge from upstream's UI going forward (see D10); our own home screen is the permanent design, not whatever upstream ships next.
- **D2 — Rust for new performance-critical modules**, called from Kotlin. Go core stays as-is (full Rust rewrite considered and rejected — section 12 decision log).
- **D3 — Offline "Fake-Online" works at the application level**, not raw TLS replay (fresh keys each session defeat replay). Layers: fake network presence → probe responder → fail-fast → junk-domain stubs → on-device Works/Stuck presets → recorded/templated profiles → optional interception where certs aren't pinned.
- **D4 — Home screen = scrollable resizable tiles**, Android-native look and feel (see D10 and section 4.11 for the full grid spec).
- **D5 — Build in the cloud.** GitHub repo + GitHub Actions builds the APK; no PC required.
- **D6 — Scanners/auditors scope to networks and devices the user owns or administers** (legal boundary, not a capability limit).
- **D7 — Distribution is F-Droid and direct APK**, not Google Play (several core features will never pass Play review — expected and fine).
- **D8 — Piracy-adjacent features (license/purchase-check removal on others' apps) are permanently out of scope.** Protects the project's integrity and legal footing.
- **D9 — Transmission-capable RF features stay passive/detection-only** (FCC/wiretap boundary). Detect: yes. Jam or intercept others': no.
- **D11 — Personal health data is permanently out of scope.** No feature tracks, stores, or manages personal health/medical/biometric data (examples of what this excludes: period/menstrual tracking, medication reminders, neural/brain data). Previously-registered features in this category were removed; entries show as "(deleted)" in the register rather than being silently renumbered, so F-numbers stay stable. This line holds regardless of future framing or re-requests. Device-level health (e.g., battery health, F596) and generic "health check" as a technical/network term (e.g., F701, F43) are unrelated and unaffected.
- **D10 — Native Android design language.** Material Design components, system font/icon style, standard gestures (long-press to edit, drag to reorder). The app should feel like it belongs on the phone, never like a custom skin bolted on. Applies to every UI feature by default. Full tile-grid spec in section 4.11.

## 3. Non-Negotiable Defaults
- Every feature ships **off by default**.
- Every server-dependent feature has a **documented free, user-controlled path** (own server, Tor, public resolver, etc.).
- Every sensitive action gets a confirmation step; the most sensitive (wipe, lockdown, root toggles) get two-factor confirm (F112).
- All user data stays **local and encrypted by default**. Sync/backup is user-initiated, to storage the user controls.
- No feature requires an account, login, or phone number to use.
- **Legal Boundary Checklist (new, see section 4.12):** every new feature gets checked against FCC/CFAA/DMCA/Wiretap/CALEA boundaries before being added to the register.

---

## 4. Future-Proofing: Editing, Access, Alteration, and Contribution

### 4.1 Plugin/Module Architecture — F213
Every feature is an independent module with a standard manifest (name, version, permissions, tiles provided, settings schema). Core app is a thin shell that loads modules.

### 4.2 Module Contract (every F-number, no exceptions)
1. Manifest: id, name, version, required permissions, declared tile(s), settings schema.
2. Settings in a versioned schema (4.3), never raw key-value pairs.
3. Migration path for every schema version bump.
4. Off by default, own enable/disable switch, same pattern regardless of complexity.
5. Unit tests runnable independently in CI.
6. One-paragraph doc comment: what it does, what permissions it needs and why, what breaks if disabled.
7. **If the module reads externally-sourced or attacker-influenced data** (traffic, domain names, packet contents) **and has any ability to act**, it follows the Input Sandboxing pattern established by F327: read access only, data is always labeled untrusted content, actions are capped by the Authority Ladder (section 5.21), regardless of what the data says.

### 4.3 Versioned Config and Profile Schemas — F214
Every stored structure carries a schema version from day one, with a migration function for every change.

### 4.4 Internal API Stability and Documentation — F215
Module interfaces are documented and semver'd independently of the app's release number; docs auto-generate in CI.

### 4.5 Contribution Pipeline — F216
All community submissions (blocklists, F07 profiles, F170 VoIP directory, F161 picker entries, F190 rule packs, F243/F317/F319 device compatibility and patch data) go through a GitHub PR to a dedicated data repo, validated automatically by a GitHub Action, then merged. Code contributions get human review against the Module Contract.

### 4.6 Feature Flags — F217
Experimental modules ship hidden/beta-gated, enabling small checkpointed development without ever shipping a broken default.

### 4.7 Developer Console — F212
Sandboxed in-app terminal (unlocked like Android's own developer options): read/query access to config, logs, modules; a scripting hook (Lua/JS) for custom rules, Fake-Online templates, packet-inspector filters; direct hand-editing of F07 profiles; command history, export/import feeding back through F216. Full raw root shell is a separate, explicitly-confirmed elevated mode, not the default.

### 4.8 Theming/Localization — F218, F219
F218: skinning system decoupled from logic. F219: externalized, translation-ready strings with a community translation path via F216.

### 4.9 Release Channels — F220
Stable/Beta/Nightly, all from the same CI pipeline on different branches.

### 4.10 Local-First Crash/Error Reporting — F221
Opt-in only, user sees the full report before it's ever sent, no silent telemetry.

### 4.11 Tile Grid and Design System — F95 (expanded)
Locked spec, based on the user-provided screenshot:
- **Grid:** 8-wide logical grid. Sizes: **1x1** (icon + short label, the long-press "shrink" option), **2x2** (icon, label, 1–2 stat lines — the default, matches the screenshot's 2-per-row layout), **4x2** (full-width short strip, status bar/ticker), **4x4/"tall"** (full-width, scrolls its own content internally for live data — traffic graphs, scrolling logs).
- **Long-press** enters jiggle/resize mode, drag a handle to snap sizes, tap elsewhere to lock in — identical interaction pattern to Android's own home-screen widget resize.
- **The `+` tile** (present in the reference screenshot) opens a searchable, categorized tile gallery.
- **F888 Coming Soon Tiles.** Unbuilt features (especially Tier 11's self-hosted-infrastructure-dependent cluster) still appear in the tile gallery, visibly present but marked "Coming Soon," with a one-line description from the Five Lenses' Describe content already written for it. Tapping one shows what it'll do and, where relevant, why it's waiting (needs self-hosted infrastructure, tracked via section 9.0). This keeps the app feeling complete and ambitious from v0.1 onward rather than looking unfinished, without requiring any of that infrastructure to actually exist yet.
- **Root Settings placement:** every category's settings screen ends with a "Root Settings" subsection, always visible (never hidden) but grayed out and non-interactive on an unrooted device, so the capability is always discoverable even before it's usable.
- **Settings tile** does NOT open one giant screen — it opens a category list (Network, Privacy, Firewall, Device, Appearance, Advanced...) exactly like stock Android Settings' drill-down pattern. Every feature's settings live under the right category by default, so new features never need a new navigation idea.
- Not bound by true Android home-screen widget size limits since this grid lives inside our own app — the only real ceiling is screen height, handled by internal scrolling on tall tiles.

### 4.12 Legal Boundary Checklist (new)
Every new feature gets checked against these before being added to the register:
- **FCC:** no actual RF transmission beyond certified hardware limits; no jamming, ever, in any form.
- **CFAA:** scanning/auditing tools default to and are gated on the user's own network/devices (D6).
- **DMCA 1201:** de-Google/driver-extraction tooling stays in "extract and reuse for interoperability" territory (the real legal basis LineageOS itself operates under) — never defeats Verified Boot's signature chain. This boundary is the entire reason section 11 (Parking Lot) exists as a separate, paused section.
- **Wiretap (ECPA):** mesh/chaff/PTT features handle only the user's own traffic; never intercept others' communications without consent.
- **CALEA/telecom law:** mesh/PTT/anonymization features stay device-to-device; no real telecom interconnection without separately addressing this body of law.

### 4.13 AI Assistant Input Sandboxing — F327 (cross-referenced from Module Contract 4.2.7)
The on-device/optional-cloud assistant (F323–F327, section 5.21) only ever gets **read access** to traffic data, always labeled as untrusted content, never as instructions. Its actions are capped strictly by the Authority Ladder regardless of what it reads, specifically because traffic data (domain names, packet contents) is attacker-influenced and a known prompt-injection surface.

### 4.14 The Five Lenses Standard — F404 (Module Contract 4.2.8)
Every feature, no exceptions, is built with five consistent lenses: **Monitor** (live status on the tile), **Detect** (flags something notable now), **Explain** (plain-English "what is this," reusing F181's pattern everywhere), **Describe** (a permanent reference doc, one tap away), **Suggest** (a recommendation, never auto-applied — Suggest is always Authority Ladder step 2, section 5.21). Four more lenses apply where relevant: **Compare** (F405, vs. last week/last version, ties to F228), **Simulate** (F406, dry-run a change before applying), **History/Timeline** (F407, scrollable record of what a feature has done), **Export** (F408, every feature's data leaves in open CSV/JSON, never trapped).

### 4.15 No Hidden Defaults — F423 (Module Contract 4.2.9)
Any underlying setting Android, a library, or RethinkDNS's own core exposes — even ones buried in code with no UI — gets a corresponding visible toggle in SERF's settings hierarchy. Before a feature is "done," every meaningful internal tunable must appear in its settings screen under the right F95 category.

### 4.16 Resource Monitor & Live Status — F424–F426 (Module Contract 4.2.10)
Every feature wires into a central **Resource Monitor Tile** (F424) showing live battery/data/CPU cost per feature. Every feature with an on/off state carries a **Universal Live Status Indicator** (F425: green/active, amber/degraded, red/failed) visible on its tile and settings screen at all times, plus an **"Is It Actually Working" Self-Test** (F426) that actively verifies state rather than just reporting what it assumes.

### 4.20 Mandatory Pre-Build Research Step (Module Contract 4.2.18, hard rule — supersedes 4.18 as the literal first action)
Before writing a single line of code, config, or UI for ANY part of the app — a brand-new F-number, a previously-planned one finally being started, or revisiting/upgrading something already built — the first action, every time, with no exceptions, is: search for current (as-of-today) advancements, standards, and open-source repos relevant to that specific part. This applies even to features planned long ago, since real-world advances keep happening after the plan was written — a feature's original spec is a snapshot, not a ceiling. Only after that search returns (or confirms nothing new exists) does actual building begin. This is not the same step as checking the Reusable Components Registry (4.18, still done too) — it's a live, current check every time, not just a lookup in an already-known list.

### 4.18 Search-First Component Reuse — F584 (Module Contract 4.2.16, hard rule)
Before any module is built, search for an existing open-source project that already does it, or most of it — check section 4.18's Reusable Components Registry (below) first, search GitHub/PyPI/F-Droid if not listed, and only build from scratch if nothing suitable exists, the license doesn't fit (must be compatible with our open-source distribution, D7), or the project is abandoned. Found components get added to the registry below so the search isn't repeated. This is why F575 (OnionShare), F578 (Reticulum), F311/F315 (Magisk/microG), and others integrate rather than reimplement — the pattern applies to every future feature, not just the ones found so far.

### Reusable Components Registry (pre-vetted — check here first)
| Component | Repo | Powers | License note |
|---|---|---|---|
| Reticulum (RNS) | github.com/markqvist/Reticulum | F578 mesh transport core | custom, open — verify compatibility before integration |
| LXMF | github.com/markqvist/lxmf | F578/F579 message delivery over Reticulum | same family as RNS |
| NomadNet | github.com/markqvist/NomadNet | F580 text pages over Reticulum | same family as RNS |
| MVT (Mobile Verification Toolkit) | Amnesty International Security Lab, GitHub | F573 spyware/Pegasus forensics | open source |
| OnionShare | GitHub, OnionShare project | F575 serverless Tor file transfer | open source |
| VeraCrypt | GitHub/IDRIX | F156/F500 encrypted volumes (replaces a DIY version) | open source |
| microG | GitHub, microg project | F315 clean-room Play Services API reimplementation | open source, legally distinct from decompiling GMS |
| Shizuku | GitHub, RikkaApps | F321 persistent ADB-level access, no cable/root | open source |
| Signal (clients + server + protocol) | GitHub, signalapp | F419 Double Ratchet E2E encryption reference | open source |
| phhusson's Treble GSI project | GitHub, phhusson/treble_experimentations | F314/F320 universal GSI baseline + per-device overlays | open source |
| LineageOS extract-files scripts | GitHub, LineageOS | F320 device tree / blob extraction tooling | open source |
| YARA-X | GitHub, VirusTotal/yara-x | F474/F643/F788 malware pattern scanning (supersedes classic YARA) | open source |
| NetGuard (UID-attribution technique) | GitHub, M66B/NetGuard | F428 accessibility-free firewall attribution method | open source, technique reference |
| Magisk / Shamiko | GitHub | F98/F311 systemless root, de-Google modules | open source |
| REA (Reverse Engineer Anything) | GitHub | F212 Developer Console analysis backend | MIT — generic tool, fine to integrate; see section 11 note on NOT using it to decompile GMS specifically |
| Let's Encrypt / ISRG | N/A (CA, not a repo) | F527 nonprofit CA reference for trust-store labeling | nonprofit |
| Headscale | GitHub, juanfont/headscale | F738/F90/F93 self-hosted mesh coordination server | open source, BSD-3-Clause |
| Rosenpass | GitHub, rosenpass/rosenpass | F697/F422 post-quantum WireGuard PSK | open source |
| Saavdhan | GitHub, kalpitt/saavdhan | F743/F06/F61 offline sideload-scam detection patterns | open source |
| rbtlog | GitHub, obfusk/rbtlog | F747/F267 Android reproducible-builds transparency log | open source |
| Sigstore (cosign/fulcio/rekor) | sigstore.dev | F746/F266/F267 keyless signing + transparency log | open source |
| Gemma 4 E2B | Google, LiteRT-LM mobile format | F759/F323 on-device assistant reference model | open weights |
| llama.cpp / MLC-LLM | GitHub, ggerganov/llama.cpp, mlc-ai/mlc-llm | F762/F323 on-device inference engine | open source |
| OvenMediaEngine | GitHub, AirenSoft/OvenMediaEngine | F768/F93 self-hosted low-latency streaming server | open source |
| DEDA | GitHub, dfd-tud/deda | F824/F658 printer tracking dot extraction/decoding/anonymization | open source |
| Play Integrity Fix | GitHub (community module) | F830/F667 reference implementation for Play Integrity spoofing | open source |
| Hide-My-Applist | GitHub, Dr-TSNG/Hide-My-Applist | F863/F98 app-list detection interception | open source |
| FakeLocation-server | GitHub, BIT-BOBH/FakeLocation-server | F864/F126 fake location server reference | open source |
| Virtual-Hosts | GitHub, x-falcon/Virtual-Hosts | F865/F04/F10 VPN-mode hosts-file blocking with wildcard DNS | open source |
| MVPN-Audit | Academic framework (NDSS) | F883 extensible Android VPN app security audit | published/open methodology |
| Image CDR (ICDR) | Academic paper, arXiv 2307.14057 | F927 zero-trust image sanitization method (pixel-extraction-and-rebuild) | published/open methodology |
| Deep CDR (OPSWAT, reference architecture) | OPSWAT (commercial reference, not for direct use — architecture pattern only) | F927/F928 CDR workflow reference (ingest → disarm → reconstruct) | reference pattern, not a dependency |
| AOSP Hidden Framework APIs catalog | GitLab, IndiTheo/aosp-hidden-framework-apis | F993 ongoing @hide API discovery across SDK versions — check before each mandatory pre-build research step | reference catalog |

### 4.17 True Build-Time Removability — F438 (Module Contract 4.2.11, hard rule)
Every module lives in its own package, reachable only through the Module SDK's defined interface (F213/F215) — never directly into another module's internals. A build manifest lets anyone check/uncheck which F-numbers compile into a given build at all; unchecked features are absent from the binary and its permissions entirely, not just disabled. **Cross-module calls through anything other than the defined SDK interface are a build error, not a style violation** — this is what keeps true removability real instead of aspirational as the register grows past 400 features.

### 4.19 Pending Fork Decisions (F679 — see section 0, point 9)
Nothing below is built until the matching letter is given, as a bare letter, by the user. Unrelated work continues normally in the meantime.

| Fork | Option A | Option B | Status |
|---|---|---|---|
| **Fork 1 — Device OS/Root Investment** (section 11) | Keep the no-root path (F310/F321) as the project's focus; leave F311–F320/F322/F583 fully parked, revisit only if asked | Invest real build time in the full root-dependent OS-replacement cluster (F311–F320, F322, F583) alongside everything else | **OPEN** — no letter given yet |
| **Fork 2 — Mesh Transport Core** | Keep the originally-scoped custom relay protocol for F90–93/F292–309, built from scratch as planned | Replace the custom transport with Reticulum/LXMF (F578) as the core underneath F90–93/F292–309 — inherits years of real-world hardening, but changes the original build scope for those features substantially | **OPEN** — no letter given yet |
| **Fork 3 — Chaff/Noise Unification** | Keep F104 and F278 as they were originally scoped, separate, simpler, narrower mechanisms | Fully absorb F104 and F278 into F619 (Wi-Fi/Data Chaff Generator) as one unified engine — more capable and coherent, but F104/F278 stop existing as independent features | **OPEN** — no letter given yet |
| **Fork 4 — Own Chromium-Based Browser** | Keep the current plan: Android WebView (Google-maintained, patched for us) underneath F168's in-app browser component, with SERF's own hardening layered on top | Build and continuously maintain a full forked Chromium browser, automatically merging onto each new upstream release (the real Brave/Vivaldi pattern) — real precedent confirms this takes a dedicated team 2 days to 2+ weeks per release with ~10% of patched files hitting merge conflicts every cycle, and Chrome is moving to a 2-week release cadence starting Sept 2026, meaning this becomes a permanent, specialized, recurring commitment for as long as the project exists, not a one-time build | **OPEN** — no letter given yet |

When ready to decide any of these, reply with just the fork number and letter (e.g., "Fork 2: B").

---

## 5. FEATURE REGISTER (complete — 850+ features)

### Category Index (1–37) — for grouping only, NOT build order
Every feature row below is tagged `[N]` at the start of its ID column, matching the numbers here, so every idea belonging to one category can be found with a single search for `[N]`, regardless of where it sits in the document. Rows also carry a `[GH]` tag when a real, specific open-source project is already identified for them in the 4.18 Reusable Components Registry — everything else gets checked fresh at build time per the mandatory pre-build research step (section 4.20), not pre-guessed here. **These numbers group ideas by theme only; they are not a build sequence** — the actual build order lives in section 9's difficulty/priority tiers.

| # | Category |
|---|---|
| 1 | Infrastructure / Future-Proofing |
| 2 | Core Engine — the "soul" (Fake-Online / Offline Playback) |
| 3 | Google Play / Device-Ownership Defense |
| 4 | Firewall and Blocking |
| 5 | Routing and Privacy |
| 6 | Tools and Diagnostics |
| 7 | Speed |
| 8 | Smart Layer / Diagnostics UX |
| 9 | Device and Root Tier |
| 10 | Covert Comms / Hardening |
| 11 | Flipper Zero-Class Hardware Features |
| 12 | VPN/Firewall/Location/Wi-Fi/Bluetooth Bundle |
| 13 | Router Integration |
| 14 | Platform and Community |
| 15 | AP / Mesh / PTT Infrastructure |
| 16 | Remote Access |
| 17 | Protocol Interoperability |
| 18 | Motion/Input Innovation |
| 19 | Hidden Settings Surface |
| 20 | Gatekeeping/Legal Awareness |
| 21 | "Ultimate App" Philosophy Items |
| 22 | AI Assistant / "Brain" |
| 23 | UX Standards, Encryption, Browser-Extension Ports, RethinkDNS Improvements, P2P Safety, Resource Sharing, Identity Disguise, Everyday Life |
| 24 | Hardware Repurposing, BLE Discovery, Professional/Enterprise Tools |
| 25 | Live-Session Security, Resource-Saving Architecture, Modular Shell & Encrypted Delivery |
| 26 | Certificate Management, Underused AOSP Capabilities, Network Analyzer Parity, Mesh Integration |
| 27 | Parking Lot Additions (reference only — see section 11) |
| 28 | Civic, Community, Device Longevity, Accessibility, Security Round 3 |
| 29 | Smart Device Recovery, TV/Computer Connection, Device-Plugged-In Tools |
| 30 | Noise/Chaff Suite, Tracing Defense, Cache Management |
| 31 | Legacy/Uncommon Protocols, Completeness Promise |
| 32 | Outside-the-Box Privacy (Pattern, Time, Inference, Social) |
| 33 | Legal Hacker Toolkit (D6-scoped) |
| 34 | On-Device Personal Learning (strictly local) |
| 35 | Things Almost Nobody Knows Exist (disclosure/informational) |
| 36 | Full Data Control, Unified Play Spoof System, Traffic Interpreter, Multi-Language |
| 37 | Offline Mesh Social Layer: Directory, Forum, Ride-Matching |

*(Note: section 5.36's research-sweep batch logs and the Gap Sweep are intentionally not numbered categories — they're findings/review logs, not idea groupings, which is why the count lands at exactly 37 rather than 39.)*

**Priority:** P0 = must exist first · P1 = core differentiator ("the soul") · P2 = high-value, broadly used · P3 = privacy/security power features · P4 = tools/diagnostics · P5 = device tier · P6 = platform/radio/mesh (often hardware-dependent) · P7 = UX polish · P8 = frontier/optional hardware
**Diff:** 1 easy → 5 frontier. **Cost:** L = local/free forever · S = server/hardware dependency, free workaround documented.

### 5.0 Infrastructure / Future-Proofing
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [1] [GH] F212 | Developer Console | P2 | 3 | L |
| [1] F213 | Plugin/Module SDK | P0 | 3 | L |
| [1] F214 | Versioned Schemas + Migrations | P0 | 2 | L |
| [1] F215 | Internal API Docs + Versioning | P0 | 2 | L |
| [1] F216 | Contribution Pipeline | P1 | 2 | L |
| [1] F217 | Feature Flags | P1 | 1 | L |
| [1] F218 | Theming Engine | P4 | 2 | L |
| [1] F219 | Localization Framework | P4 | 2 | L |
| [1] F220 | Release Channels | P2 | 2 | L |
| [1] F221 | Local-First Crash Reporting | P2 | 2 | L |

### 5.0a Security Hardening Practice (standing, starts at T0.8, continues every session after)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [1] [GH] F883 | MVPN-Audit Integration (real published framework, run against our fork continuously) | P0 | 3 | L |
| [1] F884 | Upstream RethinkDNS Security Audit (T0.8 — checks the real documented failure categories: unencrypted transmission, tunnel/DNS leakage, weak obfuscation, ID leakage, insecure config) | P0 | 3 | L |
| [1] F885 | Continuous Security Regression Testing (every module security-tested in CI before merge, not just at T0.8) | P0 | 2 | L |
| [1] F886 | Professional Audit Checklist Template (Cure53-style structure; rooted-device exploit vectors as a dedicated focus area) | P1 | 2 | L |
| [1] F887 | Tamper-Resistant Self-Test (hardens F425/F426 against an attacker targeting the verification itself, not just the underlying feature) | P1 | 3 | L |
**Cross-reference, already structurally defended:** malicious code injection via a compromised contribution ("worms," hijacking) is addressed by F216 (validated contribution pipeline), F266/F746 (reproducible builds), and F267 (transparency log) acting together — any tampering becomes independently verifiable rather than silently trusted.

### 5.0b Community Research Findings — Offline/Fake-Online Spoofing (strengthens F01, F98, F04, F10, F126)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [2] F861 | Dual-Transport Capability Spoofing (hook TRANSPORT_CELLULAR + TRANSPORT_WIFI together — confirmed real 2026 technique) | P1 | 2 | L |
| [2] F862 | RootBeer-Specific Counter-Testing (named standard adversary for F98) | P1 | 2 | L |
| [2] [GH] F863 | Hide-My-Applist Integration (4.18 registry — app-list detection interception) | P2 | 2 | L |
| [2] [GH] F864 | FakeLocation-server Reference (4.18 registry — real open-source fake-location server) | P3 | 1 | L |
| [2] [GH] F865 | Virtual-Hosts VPN-Mode Wildcard Blocking (4.18 registry — confirms F04/F10's architecture is proven) | P1 | 1 | L |
| [2] F866 | Frida-to-Xposed Development Workflow (real community dev practice — prototype in Frida, port to LSPosed for production) | — | — | — |
| [2] F867 | *(validation note, not a feature)* — years of community "Fake Wifi Connection" attempts confirm the problem is real and previously unmaintained; SERF's dual-transport, actively-maintained approach is a genuine improvement | — | — | — |

### 5.0j Network-Level Exploit/Worm Pattern Scanning (extends F474/F643/F395)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F923 | Network-Level IDS/IPS Traffic Scanning (YARA-X extended to live traffic, not just static files) | P2 | 4 | L |
| F924 | Fragmented/Staged Payload Assembly Detection (catches multi-stage payloads where no single chunk looks malicious alone) | P2 | 4 | L |
| F925 | Zero-Click Exploit Chain Disruption (directly validated by real documented NSO Group/malvertising cases; extends F395) | P1 | 4 | L |
| F926 | Malvertising-Specific Ad Network Scrutiny (ad-sourced content gets extra sandboxing given its confirmed real-world role as a zero-click delivery vector) | P1 | 3 | L |

### 5.0k Content Disarm and Reconstruction (CDR) — real, named, zero-trust technique (4.18 registry)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F927 | Image CDR (extracts raw pixel data only, discards header/metadata/hidden structure, rebuilds a clean fresh file — zero-trust, effective against zero-days by design, not detection-dependent) | P1 | 3 | L |
| F928 | CDR for Video/Documents/PDFs (same principle extended beyond images) | P2 | 4 | L |
| F929 | CDR Honest Limits (documented: breaks digital signatures on modified files; requires decrypted content to operate on — naturally fine here, CDR runs client-side after decryption anyway) | — | — | — |

### 5.0l Universal Media Quality/Resolution Control (site-wide, any web video/image, not just YouTube)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F930 | Universal Media Quality Control (proxy-level detection of adaptive streaming manifests — HLS/DASH — forces a chosen lower-quality rendition site-wide, any video, not just YouTube) | P2 | 3 | L |
| F931 | Placeholder-Scale-Then-Upscale Loading (loads a smaller version first, scales to fit — bandwidth savings AND a security benefit, ties directly to F927's CDR re-encoding step) | P2 | 2 | L |
| F932 | Wrapper/MIME-Type Normalization (forces consistent, known-safe container formats — part of CDR's reconstruction step, F927/F928) | P3 | 2 | L |

### 5.0m Web-Wide Media Quality Control, Audio-Only Mode, and Video-Stream Telemetry Blocking
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F933 | Non-DRM Adaptive-Stream Quality Override (HLS/DASH manifest rewriting at proxy level — works site-wide automatically, no per-site config needed, extends F930) | P1 | 3 | L |
| F934 | **DRM Honest Limit** — encrypted/licensed streams (Netflix, Disney+, most paid services) cannot have quality controlled at the network level; this is cryptographically closed, not a difficulty tier, and is stated plainly in-app rather than implied as "eventually possible" | — | — | — |
| F935 | Community-Maintained Site-Specific Quality Handlers (confirmed correct instinct — major proprietary-player sites like YouTube need individually contributed handlers, same F216/F07/F161 pattern) | P2 | 3 | L |
| F936 | Universal Quality Preset (144p through highest, applied wherever F933/F935 can technically honor it; graceful "not available for this site" messaging otherwise — Five Lenses Explain) | P1 | 2 | L |
| F937 | Audio-Only Extraction Mode (per-site or universal toggle; skips video segments where the manifest separates audio-only renditions — same DRM wall applies) | P2 | 2 | L |
| F938 | Video-Stream Telemetry/Analytics Beacon Blocker (extends F892/F917/F919 specifically to player heartbeat/QoE/analytics calls, separated cleanly from the actual video delivery traffic) | P1 | 3 | L |

### 5.0n FM Audio Capture & Bluetooth Redirect (formalizes F471's mechanism)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F939 | FM Tuner Audio Capture & Bluetooth Redirect Driver (root-tier; captures FM tuner PCM output at the audio HAL where device architecture allows, duplicates/redirects into Bluetooth A2DP sink — true virtual-audio-cable behavior, device-dependent per F243's compatibility map; hard wall on phones where FM audio is hardwired below the software layer) | P4 | 4 | L |

### 5.0o Deceptive-Capability Reader Mode (CDR applied to web pages, not just images)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F940 | Deceptive-Capability Reader Mode (claims full modern-browser capability via headers/UA to receive the complete server response, then never instantiates a JS engine or executes anything — parses the raw HTML as pure static text/structure only, extracting readable content) | P1 | 3 | L |
| F941 | Zero-Execution Guarantee (the core safety property — nothing is ever run, so there's nothing to exploit, regardless of what the page actually contains) | — | — | — |
| F942 | Community-Submitted Alternate Viewing Templates ("a better viewing experience" options, submitted via F216, same pattern as blocklists/profiles) | P3 | 2 | L |
| F943 | Universal Intuitive Default (confirmed: the base experience needs zero configuration to be good; templates are optional extras, not a requirement) | P1 | 1 | L |

### 5.0p Captive Portal Renewal, Static IP, and Explicit Identifier Override Fields
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F944 | Captive Portal Session Renewal Automation (handles periodic re-click/re-auth some portals require every N minutes, not just first login — extends F130) | P1 | 2 | L |
| F945 | Static IP Auto-Configuration (one-tap fixed IP per network, avoids DHCP churn, extends F128) | P2 | 1 | L |
| F946 | Static Connection Benefits Guide (reliable port-forwarding, faster reconnect, DHCP-pool-range-avoidance tip, pairs with mDNS hostnames — Five Lenses Describe content) | P3 | 1 | L |
| F947 | Universal Hardware/Network Identifier Override Fields (explicit, individually-settable fields on top of F662's general engine: MAC, IMEI, IMSI, serial number, Android ID, Advertising ID, Bluetooth MAC, and any other identifier a network/app queries) | P1 | 3 | L |
| F948 | Plain-Language ID-Request Log (logs each identifier request and the value given back, in friendly plain language, via F663's Transmission Ledger — live popup only if F871's live mode is explicitly enabled, otherwise silent and reviewable) | P2 | 2 | L |

### 5.0q Wi-Fi Connection Stability (fixes multi-saved-network reassociation thrashing)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F949 | Connection-Lock Mode (once connected, suppress background scanning/evaluation of other saved networks until disconnect or manual switch) | P1 | 2 | L |
| F950 | Strongest-Signal Auto-Select-and-Lock (ranks available saved networks by actual signal strength, connects to strongest, immediately engages F949) | P2 | 2 | L |
| F951 | Clustered-Saved-Network Conflict Advisor (detects repeated multi-network competition in the same location, suggests forgetting redundant ones, explains the real reassociation/scan-overhead mechanism — not a misleading "power-splitting" explanation) | P2 | 2 | L |
| F952 | Manual Network Priority Ranking (explicit user-set priority among saved networks, extends F128) | P3 | 1 | L |

### 5.0r Data Management, Network Granularity, and Frontier Items (cross-referenced from external list review)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F953 | Forced Metered Network Toggle (treat any chosen network as capped regardless of what it actually is) | P2 | 1 | L |
| F954 | Pre-Connect Network Speed Rating Indicator | P3 | 2 | L |
| F955 | VoWiFi (Wi-Fi Calling) Awareness/Management Surface | P4 | 1 | L |
| F956 | Hard Data Cutoff Threshold (distinct from F236's throttle — a true cutoff at a chosen limit) | P2 | 1 | L |
| F957 | Billing Cycle Sync for Data Tracking | P3 | 1 | L |
| F958 | Granular MAC Randomization Modes (per-network / per-connection / static choice — extends F561) | P2 | 2 | L |
| F959 | Aggressive RSSI-Threshold Wi-Fi-to-Cellular Handover (extends F158) | P2 | 2 | L |
| F960 | Wi-Fi Band Locking (2.4/5/6GHz — distinct from F150's cellular band lock) | P3 | 1 | L |
| F961 | Throughput/Latency-Based Dual-SIM Auto-Switching (extends F148) | P3 | 2 | L |
| F962 | TTL Normalization (defeats carrier tethering-detection via TTL decrement patterns) | P2 | 2 | L |
| F963 | Per-Interface Data Limits (SIM1/SIM2/Wi-Fi/USB tracked and capped separately) | P2 | 2 | L |
| F964 | LAN-Only Isolation Mode (per-app: block internet, allow local device discovery — ties to F446) | P2 | 2 | L |
| F965 | Hotspot Dynamic Channel Auto-Selection (DFS-aware congestion avoidance, extends F131) | P3 | 3 | L |
| F966 | Per-Client Hotspot Bandwidth Shaping (extends F131, per connected device) | P3 | 2 | L |
| F967 | Multipath TCP (MPTCP) Bonding (aggregates Wi-Fi + cellular simultaneously — real IETF standard) | P4 | 4 | L |
| F968 | Wi-Fi Monitor Mode & Packet Injection (root-tier, D6-scoped to own network only, extends F40/F44) | P4 | 4 | L |
| F969 | Zero-Trust System App Firewalling with Preserved Local IPC (refines F667 — blocks external connections from system apps while keeping local inter-process communication intact) | P3 | 4 | L |
| F970 | Port-Level Per-App Rules (e.g., allow 80/443, block outbound IRC/SSH — finer than domain-level F10-20) | P3 | 3 | L |
| F971 | **Directional Beamforming — HONEST LIMIT.** Not exposed to apps on consumer phone hardware; antenna phase-array control lives in closed baseband firmware. Informational only, not buildable. | — | — | — |
| F972 | Zero-Knowledge Device Attestation (prove device integrity to a remote server without revealing IP/device ID — frontier research, builds on F541/F708's hardware-backed keys) | P6 | 5 | L |

### 5.0s Carrier/RIL-Adjacent Features (corrected scope — see honest limits above)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F973 | Simplified APN Management UI (native Android capability, simplified presentation; honest caveat: tethering-cap bypass via custom APN is less universally effective on modern carriers who tie entitlement to account flags, not just APN) | P4 | 1 | L |
| F974 | Known Carrier Diagnostic/Telemetry App Database (real precedent: the 2011 Carrier IQ case — extends F14's provider profiles and F371's bloatware audit with a maintained list of known carrier diagnostic tools) | P3 | 2 | L |
| F975 | Read-Only RIL Diagnostic Log (device-specific, supported chipsets only — AT command *logging* for transparency, NOT a reject/control capability; ties to F364's honest baseband disclosure) | P5 | 3 | L |
| F976 | TAC (Tracking Area Code) Parameter Addition to F108 (minor spec refinement — adds this parameter to the cell-tower inspection already in F108) | — | — | — |

### 5.0t Concrete AOSP Implementation Paths + Rugged-Phone Hardware (cross-referenced from external review — see corrections above)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F977 | hardened_malloc Integration (4.18 registry — real GrapheneOS component, exploit-resistant memory allocator for our Rust/native core, use directly rather than building our own) | P2 | 2 | L |
| F978 | eBPF TLS Plaintext Inspection for Own-Device Diagnostics (eCapture-style uprobes on OpenSSL/BoringSSL — legitimate, NOT a MITM, inspects the user's own apps' outgoing data at the point of encryption, on their own device, defeating SSL-pinning obstruction of the user's own visibility; extends F40/F664) | P2 | 4 | L |
| F979 | Synthetic NetworkAgent App Isolation (real AOSP technique — a custom NetworkProvider/virtual NetworkAgent routes specific app traffic through an internal pipe without declaring a traditional system-wide VPN; extends F124) | P3 | 4 | L |
| F980 | 802.11 Probe Information-Element Scrubbing (SupplicantStaIfaceHal-level — scrubs chipset/bitrate disclosure from probe frames beyond MAC randomization alone; extends F560/F561) | P2 | 3 | L |
| F981 | Direct eBPF UID Quarantine (netd BPF map-level instant per-app blocking, lower latency than iptables; extends F10/F20) | P2 | 3 | L |
| F982 | Rugged-Phone Hardware Attachment Integration (pogo-pin physical kill-switch trigger, UHF/VHF walkie-talkie digital bridging, thermal-camera surveillance-trigger alert, satellite panic relay — ties to F92/F116-122, device-specific per F243) | P4 | 4 | S |
| F983 | Anti-Probing Kernel Hardening Checklist (concrete sysctl values: rp_filter, tcp_timestamps off, icmp_ignore_bogus_error_responses, dmesg_restrict — extends F70, root-tier) | P4 | 2 | L |

### 5.0u Hidden AOSP Wi-Fi Decision-Making, Exposed (confirms the "symmetry of access" principle directly)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F984 | Expose Adaptive Connectivity ML Scorer Controls (`wifi_scoring_enabled`, `ADAPTIVE_CONNECTIVITY_WIFI_ENABLED` — real settings with zero native UI, confirmed as recently as GrapheneOS's Sept 2026 PR to expose them at all) | P1 | 2 | L |
| F985 | **Custom WifiConnectedNetworkScorer Registration** — registers SERF's own transparent scoring logic via the real `WifiManager.setWifiConnectedNetworkScorer()` API, replacing Android's hidden ML model with user-visible, user-controllable logic. Direct upgrade path for F949-952, where permission level allows | P1 | 4 | L |
| F986 | Wi-Fi Aware (NAN) 2026 API Tracking (confirms F147 is actively evolving — new TXT record handling and channel-on-datapath methods added in 2026) | P4 | 2 | L |
| F987 | Hidden API Access Note (AOSP deliberately `@hide`s many Wi-Fi APIs from normal app visibility by design — the structural reason Shizuku/root-tier access matters for reaching some of this register's deeper features) | — | — | — |

### 5.0v Precise Implementation Details (NetworkSecurityConfig, per-UID query, eBPF maps, hidden API reference)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F989 | NetworkSecurityConfig Runtime Inspection (real API 37.2 — isCleartextTrafficPermitted/isCertificateTransparencyVerificationRequired/getDomainEncryptionMode; strengthens F477/F800) | P3 | 2 | L |
| F990 | APK Network Security Config Patcher (real tool pattern — patches an app's cleartext/pinning config for own-device diagnostic inspection; extends F978) | P3 | 2 | L |
| F991 | Per-UID Active Network Query (real getActiveNetworkForUid API; sharpens F15's attribution) | P3 | 2 | L |
| F992 | Precise eBPF TrafficController Implementation (confirmed real map names — traffic_cookie_tag_map, traffic_uid_counterSet_map — and dumpsys netd diagnostics; concrete grounding for F981) | P2 | 2 | L |
| F993 | AOSP Hidden Framework API Reference (4.18 registry — real, maintained catalog of @hide APIs across SDK versions, valuable for every future session's mandatory pre-build research step) | — | — | — |

### 5.0w Master Identifier/Fingerprint Field Index (consolidates F947/F662 against every known requested parameter)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F994 | **Master Identifier Field Index** — the full consolidated checklist (device hardware, SIM/cellular, network hardware, advertising/app identity, browser/web fingerprinting, carrier-injected data), every category cross-referenced to its handling feature, surfaced as F947's actual field list in-app | P1 | 1 | L |
| F995 | ICCID / MSISDN App-Level Override (SIM serial and phone number as reported to apps, not network registration) | P2 | 2 | L |
| F996 | GSF ID / Firebase Installation ID / FCM Token Rotation | P2 | 2 | L |
| F997 | Bluetooth Device Name Spoofing (distinct from MAC — default names are often personally identifying, e.g. "John's Phone") | P2 | 1 | L |
| F998 | HTTP/2 Header Frame Ordering Normalization (a real, lesser-known fingerprint — client libraries order/capitalize headers distinctly) | P3 | 3 | L |
| F999 | Do Not Track Header Randomization/Normalization (ironically identifying, since almost nobody changes it from default) | P3 | 1 | L |
| F1000 | navigator.deviceMemory API Normalization | P3 | 1 | L |
| F1001 | Storage Quota Fingerprint Normalization | P4 | 1 | L |

### 5.0x Factory Calibration Fingerprinting and Final Identifier Gaps
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F1002 | **Factory Calibration Fingerprint Defense (SensorID-style)** — real, peer-reviewed research confirms accelerometer/gyroscope/magnetometer factory-calibration errors create a globally unique, permission-less, factory-reset-surviving fingerprint; needs calibrated (not naive) noise injection, shaped to mask the signature while preserving legitimate sensor function — strengthens F172, addresses F729's flagged gap directly | P1 | 4 | L |
| F1003 | NFC UID Spoofing (hardware chip UID broadcast in reader/emulation mode) | P3 | 2 | L |
| F1004 | USB Device Descriptor Spoofing (Vendor ID/Product ID/Serial reported to a connected computer) | P3 | 2 | L |
| F1005 | eSIM EID Protection (eUICC Identifier, distinct from ICCID, as reported to apps) | P3 | 2 | L |

### 5.0g Stylometric Writing-Style Defense (advanced, optional layer on F908-912's base keyboard)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F913 | Rule-Based Stylometric Scrambler (hundreds of community-submitted substitution/reordering rules, applied to finalized text, defeats authorship-attribution stylometry) | P3 | 2 | L |
| F914 | Persona Voice Transformer (F323 on-device model rewrites message in a chosen persona's voice) | P3 | 3 | L |
| F915 | Transform-Then-Insert Staging Mode (review before committing — the correct default UX, not silent live substitution) | P2 | 2 | L |
| F916 | Live Inline Mode (opt-in only, skips the review step for trusted rulesets) | P4 | 2 | L |

### 5.0h Disguised-Exfiltration Detection (apps hiding covert traffic inside legitimate-looking, user-triggered requests)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F917 | Action-Correlated Traffic Anomaly Detector (ties a user action to the traffic that follows; flags mismatched destinations or data shape) | P2 | 4 | L |
| F918 | Zero-Justification Traffic Flag (apps with no declared network need per F06, still making calls, flagged outright) | P2 | 2 | L |
| F919 | Upload/Download Ratio Anomaly Detector (passive-consumption actions should be download-heavy; unexpected upload volume is a concrete red flag, builds on F647) | P2 | 3 | L |

### 5.0i Expert Traffic Injection Engine + Clean Navigation
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F920 | Clean Navigation Mode (routes through a neutral intermediate hop before any link's actual destination, stripping referrer/context — extends F416/F898) | P2 | 2 | L |
| F921 | Conditional Traffic Injection Rules Engine (if/when-based, persistent, programmable — "when X detected, inject Y before/after/wrapped in Z" — extends F212/F662/F664) | P3 | 4 | L |
| F922 | Self-Injection Exemption Whitelist (own F921 rules auto-exempted from F907/F917 anomaly detection; explicit toggle to re-enable scanning them for testing) | P2 | 2 | L |

### 5.0e Chrome Integration Clarified, Universal Scope Toggle, Universal Unknown-Data Quarantine
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F904 | VPN-Layer Chrome Traffic Interception (confirmed: already covered by F10, zero additional work — no root, no fork needed) | — | — | — |
| F905 | Root-Tier Chrome Process Hooking (Xposed/Frida-style, controls in-page fingerprint surfaces — Canvas/AudioContext/hardware concurrency — within the existing stock Chrome app; occasional hook fixes when Chrome changes internals, NOT the Fork-4-scale continuous treadmill) | P3 | 3 | L |
| F906 | Universal Scan Scope Toggle (per-feature: "selected apps only" vs. "all apps" — standing pattern for every scanning/fingerprint-defense feature, tradeoff stated at the toggle) | P1 | 1 | L |
| F907 | Universal Unknown-Data Quarantine (generalizes F430 beyond DNS to every traffic type: unrecognized data gets flagged, held for review, or blocked, temporarily or permanently, per user setting) | P1 | 3 | L |

### 5.0f Built-In Keyboard System
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F908 | Full System Default Keyboard (standard Android IME registration — selectable as default, activates on any text field automatically, no root needed) | P2 | 3 | L |
| F909 | Anonymous Keyboard Mode (randomizes keystroke *timing*, not characters — defeats behavioral-biometric/keystroke-dynamics fingerprinting per F370; every typed character stays exactly correct) | P2 | 2 | L |
| F910 | Fully Offline Keyboard (no cloud dictionary sync, no telemetry — extends F553's principle from password-fields-only to the whole keyboard) | P1 | 1 | L |
| F911 | Modular Keyboard Marketplace (community-built, uploadable, switchable keyboard layouts/skins/behaviors — ties to F680's plugin marketplace) | P3 | 3 | L |
| F912 | Simple No-Nonsense Default Layout (clean, minimal baseline keyboard shipped out of the box, not over-designed) | P2 | 1 | L |
**Note:** F553 (hardened password-field keyboard) becomes a specific mode of this system — auto-activates its stricter, more minimal feature set when focus lands in a vault/password field, rather than being a wholly separate keyboard.

### 5.0d Referral Link Stripping and Complete Fingerprint Defense Consolidation
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| F898 | Referral/Affiliate Link Stripper (ref=, aff=, affiliate_id= and similar — distinct from F416's Referer header handling; extends F17/F411) | P2 | 1 | L |
| F899 | AudioContext Fingerprint Defense (Web Audio API processing-output normalization) | P3 | 2 | L |
| F900 | Media Device Enumeration Spoofer (camera/mic device list normalization) | P3 | 2 | L |
| F901 | Hardware Concurrency Normalization (reports a common CPU core count, not the real one) | P3 | 1 | L |
| F902 | Client Hints Normalization (Chrome's newer UA-replacement mechanism, same treatment as traditional UA spoofing) | P3 | 2 | L |
| F903 | **Master Fingerprint Defense Consolidation** — one system-wide umbrella tying together every identified fingerprint vector (F367-370, F699/F726 TLS, F173/F174/F177/F178/F179 device/locale/screen/ID, F727/F728 rendering/budget, F899-F902) into a single control, applied universally via F35/F662's override engine — covers any app's network traffic, not just one browser | P1 | 3 | L |

### 5.0c Variable Change Visualization Engine
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [1] F868 | Variable Change Visualization Engine (umbrella system) | P2 | 3 | L |
| [1] F869 | Four-Tier Recency Color Coding (red ≤1min, yellow ≤5min, green ≤30min, pink ≤60min) | P2 | 2 | L |
| [1] F870 | FROM→TO Change Display (prior value, exact time, new value, hardcoded order, shown as a line above) | P2 | 2 | L |
| [1] F871 | Live vs. Snapshot Mode Toggle (off/snapshot by default per section 3; continuous live is opt-in) | P2 | 2 | L |
| [1] F872 | Per-Variable Log Button (full change history) | P2 | 1 | L |
| [1] F873 | Granular Logging Toggles (live updates, color-coding, logging — independently switchable) | P2 | 1 | L |
| [1] F874 | Accessible, Customizable Color Scheme (red/yellow/green/pink default; colorblind-safe preset available; ties to F218) | P2 | 2 | L |
| [1] F875 | Expert Custom Thresholds (via F212 Developer Console — new bands, colors, or tracked variables) | P3 | 2 | L |
| [1] F876 | Suspicious Change Detector + Suggested Action (anomaly flag, plain explanation, help-guide link; ties to F60/F647) | P2 | 3 | L |
| [1] F877 | Plain-Language Variable Subtitles (every stat gets a one-line non-technical description; extends F404) | P1 | 1 | L |
| [1] F878 | Frequency-Aware Visualization Mode (auto-detects high-frequency variables, switches to a live sparkline instead of discrete color-flagging to avoid meaningless flashing) | P2 | 3 | L |

### 5.1 Core Engine — the "soul" (Fake-Online / Offline Playback)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [2] F01 | Fake-Online Simulator | P1 | 2-3 | L |
| [2] F02 | Live Capture/Recording | P1 | 3 | L |
| [2] F03 | Dynamic Response Synthesis | P1 | 4 | L |
| [2] F04 | Junk-Domain Stub List | P0 | 1 | L |
| [2] F05 | On-Device Preset Learning | P1 | 2 | L |
| [2] [GH] F06 | APK Static Scan | P2 | 3 | L |
| [2] F07 | Shareable Profiles | P1 | 2 | L |
| [2] F228 | App Update Time Machine (diff network behavior across app versions) | P1 | 3 | L |
| [2] F229 | Time Capsule Mode (archive APK + traffic profile together) | P1 | 3 | L |
| [2] F230 | Real Surveillance Graph (cross-app tracker ownership web) | P2 | 3 | L |
| [2] F231 | Crowd-Verified Physical Threat Alerts (regional, anonymous, aggregated) | P4 | 4 | S |
| [2] F232 | Sybil-Resistant Community Profile Trust (web-of-trust for F07/F161/F216) | P1 | 3 | L |
| [2] F233 | One-Tap Evidence-to-Legal-Action (auto-drafted data requests from captured evidence) | P3 | 3 | L |
| [2] F234 | Honest Cost Receipt (per-service battery/data cost, sub-app granularity) | P2 | 3 | L |
| [2] F235 | Version Rollback (archive + restore prior APK versions, ties to F228) | P1 | 2 | L |

### 5.2 Google Play / Device-Ownership Defense
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [3] F96 | Per-App Google Isolation | P1 | 3 | L |
| [3] F97 | Source Doctor | P1 | 3 | L |
| [3] [GH] F98 | Root Shield | P2 | 3 | L |
| [3] F99 | Install Freedom | P3 | 3 | L |
| [3] F100 | Bulk Permission Presets | P4 | 1 | L |
| [3] F101 | My License Checker | P3 | 2 | L |
| [3] F169 | Number-Loss Guard | P2 | 2 | L |
| [3] F170 | VoIP Number Directory | P3 | 2 | L |
| [3] F171 | SMS Forwarding Relay | P3 | 2 | L |
| [3] F310 | ADB Debloat Wizard (no root; covers apps, permissions, hidden settings — see 5.0a) | P1 | 2 | L |
| [3] [GH] F321 | Shizuku Integration Layer (persistent ADB-level access, no cable/root needed) | P1 | 2 | L |

### 5.3 Firewall and Blocking
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [4] F10 | uBlock-grade List Engine | P1 | 2 | L |
| [4] F11 | Geo-IP/Geo-Domain Firewall | P2 | 2 | L |
| [4] F12 | Real-Time Connection Prompts | P2 | 3 | L |
| [4] F13 | Stealth Mode | P3 | 3 | L |
| [4] F14 | Provider Profiles | P2 | 2 | L |
| [4] F15 | Granular Attribution | P2 | 2 | L |
| [4] F16 | Natural-Language Rule Engine | P4 | 4 | L |
| [4] F17 | Ad/Tracker URL Sanitizer | P3 | 3 | L |
| [4] F18 | Per-App Bandwidth Scheduling | P3 | 2 | L |
| [4] F19 | Floating Firewall HUD | P4 | 2 | L |
| [4] F20 | Antitheft Lockdown/Kill Switch | P2 | 3 | L |
| [4] F154 | Notification Firewall | P3 | 2 | L |
| [4] F155 | Clipboard Guard | P3 | 1 | L |
| [4] F162 | Call/SMS Geographic Blocking | P2 | 2 | L |
| [4] F191 | Block Page | P1 | 1 | L |
| [4] F192 | One-Tap Unblock From Log | P1 | 1 | L |
| [4] F193 | ISP DNS Passthrough Mode | P2 | 3 | L |
| [4] F194 | Auth-Flow Safe Mode | P2 | 3 | L |
| [4] F195 | Blocklist Engine Rebuild (exceeds 256-list ceiling) | P1 | 3 | L |
| [4] F222 | Multi-Device Profile Dashboard (NextDNS-style, self-hosted, no account) | P2 | 3 | L |
| [4] F223 | Tailscale/Mesh VPN Interop | P3 | 2 | L |
| [4] F224 | Blocklist Manager (add/remove/reorder, one screen) | P1 | 1 | L |
| [4] F225 | Explicit Update Indicator (visible blocklist update status) | P1 | 1 | L |
| [4] F226 | Multi-Device DNS Profile Lock (no silent unlink across devices) | P2 | 2 | L |
| [4] F227 | VPN+DNS Chain Wizard (auto-correct ordering, no IPv6 tricks required) | P2 | 3 | L |
| [4] F236 | Universal Bandwidth Throttle (per-app, global, per-radio hard caps) | P2 | 2 | L |
| [4] F237 | Adaptive Keep-Alive Interval (manual heartbeat interval, event-triggered override) | P2 | 2 | L |
| [4] F373 | Rogue CA Certificate Detector | P3 | 2 | L |
| [4] F387 | DNS Rebinding Attack Detector | P3 | 3 | L |

### 5.4 Routing and Privacy
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [5] F30 | Multi-Hop Country Chain Routing | P3 | 3 | S |
| [5] F31 | Zero-Knowledge Egress Chaining | P2 | 3 | L/S |
| [5] F32 | Ghost Switch (one-tap Tor) | P2 | 2 | L |
| [5] F33 | ProxyChains-Style Chain Builder | P4 | 3 | S |
| [5] F34 | WebRTC Leak Neutralization | P2 | 2 | L |
| [5] F35 | Fingerprint/UA Spoofer | P3 | 3 | L |
| [5] F36 | Rogue Network Protection | P3 | 4 | L |
| [5] F37 | WireGuard Auto-Recovery | P2 | 2 | L |
| [5] F38 | Location Privacy | P3 | 2 | L |
| [5] F123 | Built-in WireGuard/OpenVPN Client | P1 | 2 | L |
| [5] F124 | Split Tunneling | P2 | 2 | L |
| [5] F125 | DoH/DoT Resolver Switcher | P1 | 1 | L |
| [5] F126 | Fake GPS/Location Spoofer | P3 | 2 | L |
| [5] F127 | Location Fuzzing | P3 | 2 | L |
| [5] F172–F179 | Spoofing Suite (sensor, device model, timezone, battery, privacy-indicator [sandboxed-test-only], locale, resolution, ad-ID rotation) | P4-P5 | 2-3 | L |
| [5] F113 | Metadata Scrubber | P2 | 1 | L |
| [5] F277 | Onion-Routed Everything Toggle (one master anonymity switch) | P2 | 3 | L |
| [5] F278 | Decoy Traffic Generator (browsing chaff) | P4 | 3 | L |
| [5] F279 | Anonymous Identity Per Session (burner fingerprint+DNS+exit bundle) | P3 | 3 | L |
| [5] F280 | Contact-Graph Isolation for Messaging | P3 | 3 | L |
| [5] F281 | Call/Message Caller-ID Anonymization | P4 | 3 | S |
| [5] F367–F370 | Fingerprinting Exposers (canvas/WebGL, battery API, font enumeration, behavioral biometrics) | P3 | 2-3 | L |

### 5.5 Tools and Diagnostics
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [6] F40 | Packet Capture/PCAP | P3 | 3 | L |
| [6] F41 | Network Diagnostics/ISP Audit | P3 | 3 | L |
| [6] F42 | Wi-Fi/Cellular Quality Mapping | P4 | 3 | L |
| [6] F43 | ISP Outage/Throttling Detection | P4 | 3 | L |
| [6] F44 | Subnet/Port Auditor (own network) | P3 | 3 | L |
| [6] F45 | Domain Inspector/OSINT Lookup | P3 | 2 | L |
| [6] F46 | ROI/Savings Dashboard | P6 | 1 | L |
| [6] F47 | Interactive Traffic Visualizer | P4 | 3 | L |
| [6] F48 | Tasker/Macro Integration | P4 | 2 | L |
| [6] F49 | Local Backup/Restore | P1 | 2 | L |
| [6] F108 | IMSI-Catcher Detector | P3 | 4 | L |
| [6] F109 | Bug/Camera RF Sweep | P6 | 4 | S |
| [6] F110 | Tamper Detection | P3 | 3 | L |
| [6] F111 | GPS-Denied Dead Reckoning | P6 | 4 | L |
| [6] F132 | BLE Scanner | P3 | 2 | L |
| [6] F133 | Unknown Tracker Alert | P2 | 3 | L |
| [6] F141 | BLE Signal Mapper | P6 | 2 | L |
| [6] F166 | Wi-Fi Security Auditor (own networks) | P3 | 3 | L |
| [6] F196–F200 | Rogue DHCP/ARP Spoof/Default Credential/CVE Lookup/Open Port Report (own LAN) | P3-P4 | 2-3 | L |
| [6] F181 | "Explain This Connection" | P2 | 2 | L |
| [6] F189 | "Why Is This Slow" Diagnostic | P2 | 2 | L |
| [6] F261 | EMF/RF Exposure Logger | P6 | 2 | L |
| [6] F361 | Carrier Supercookie Detector (injected-header stripper) | P3 | 2 | L |
| [6] F362 | Ultrasonic Beacon Blocker | P3 | 2 | L |
| [6] F363 | Real-Time Bidding Data Leak Monitor | P2 | 3 | L |
| [6] F364 | Baseband Isolation Monitor (informational) | P4 | 2 | L |
| [6] F365 | SS7/Telecom-Layer Honesty Panel (informational) | P4 | 1 | L |
| [6] F366 | Forced-Downgrade (2G) Detector | P3 | 2 | L |
| [6] F371 | Pre-Installed Bloatware Data-Flow Audit | P3 | 3 | L |
| [6] F372 | Third-Party SDK Ownership Unmasker | P2 | 2 | L |
| [6] F374 | Captive Portal Credential Harvest Check | P3 | 2 | L |
| [6] F375 | Push Notification Payload Inspector | P3 | 2 | L |
| [6] F376 | Geofence Warrant Exposure Explainer (informational) | P4 | 1 | L |
| [6] F377 | Data Broker Opt-Out Directory (community-sourced) | P2 | 1 | L |
| [6] F378 | Wi-Fi Sensing/Through-Wall Presence Alert | P4 | 3 | L |
| [6] F379 | Retail Beacon Tracking Detector | P3 | 2 | L |
| [6] F380 | Smart Meter Granularity Privacy Note (informational) | P4 | 1 | L |
| [6] F381 | Vehicle Telematics Data Harvest Explainer (informational) | P4 | 1 | L |
| [6] F382 | Juice-Jacking Protection | P2 | 2 | L |
| [6] F383 | Tapjacking/Overlay Attack Detector | P2 | 2 | L |
| [6] F384 | Accessibility Service Abuse Monitor | P2 | 2 | L |
| [6] F385 | Quishing (QR Phishing) Scanner | P2 | 2 | L |
| [6] F386 | BGP Hijack Awareness Panel (informational) | P4 | 1 | L |
| [6] F388 | SIM Swap Early-Warning | P2 | 3 | L |
| [6] F389 | Always-Listening False-Trigger Auditor | P3 | 3 | L |
| [6] F390 | Smart TV ACR Tracking Explainer (informational) | P4 | 1 | L |
| [6] F391–F398 | Frontier Disclosure Panel: TEMPEST, laser mic, keyboard acoustic emanation, power-line monitoring, zero-click indicator, supply-chain implant awareness, drone rogue-signal detector, air-gap covert channel awareness (all informational/pattern-detection, no operational capability) | P4-P6 | 2-4 | L |

### 5.6 Speed
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [7] F50 | DNS Booster | P2 | 2 | L |
| [7] F51 | Local Caching/Asset Interception | P4 | 3 | L |
| [7] F52 | Cache-Control Header Injection | P5 | 2 | L |
| [7] F53 | Autonomous Bandwidth Slicing | P4 | 3 | L |
| [7] F163 | Volume Booster + Distortion Guard | P4 | 2 | L |

### 5.7 Smart Layer / Diagnostics UX
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [8] F60 | Behavioral Anomaly Detection | P5 | 4 | L |
| [8] [GH] F61 | Stalkerware/Malware Detection | P3 | 4 | L |
| [8] F62 | Self-Updating Rules/Models | P5 | 3 | L |
| [8] F185 | Conflict Detector | P2 | 2 | L |
| [8] F186 | Guided First-Run Setup | P1 | 2 | L |
| [8] F188 | Battery Impact Per Feature | P2 | 2 | L |

### 5.8 Device and Root Tier
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [9] F70 | OS-Level Tuning Suite | P5 | 3 | L |
| [9] F71 | Secure Wipe | P3 | 3 | L |
| [9] F72 | Family Safety/Screen-Time | P5 | 3 | L |
| [9] F73 | Encrypted Vault | P1 | 2 | L |
| [9] F74 | Password-Protect Any App | P2 | 2 | L |
| [9] F75 | Game/Focus Mode | P4 | 1 | L |
| [9] F76 | Change App Name at Install | P5 | 2 | L |
| [9] F77 | Rooted Deep-Control Capabilities | P5 | 3 | L |
| [9] F78 | Hardening Guide | P3 | 2 | L |
| [9] F79 | Kill-Background-App Utilities | P4 | 1 | L |
| [9] F114 | Targeted Secure Delete | P4 | 2 | L |
| [9] [GH] F156 | Hidden Encrypted Volume | P3 | 3 | L |
| [9] F164 | Simple Notepad | P6 | 1 | L |
| [9] F242 | Hidden Settings Hub (surfaces Android's buried + manufacturer-specific settings) | P2 | 2 | L |
| [9] F243 | Community Device Compatibility Map ("worked on my model" crowd data) | P1 | 2 | L |
| [9] F271 | Low-End Device Mode | P2 | 3 | L |
| [9] F272 | Full Offline-First Onboarding | P1 | 2 | L |

### 5.9 Covert Comms / Hardening (passive-only per D9)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [10] F102 | Duress Mode | P5 | 3 | L |
| [10] F103 | Dead Man's Switch | P5 | 2 | L |
| [10] F104 | Traffic Padding/Chaff | P6 | 3 | L |
| [10] F105 | Steganographic Channel | P6 | 3 | L |
| [10] F106 | Air-Gap Transfer (QR/ultrasonic) | P6 | 3 | L |
| [10] F107 | Store-and-Forward Mesh Relay | P6 | 4 | L |
| [10] F112 | Two-Factor Confirm | P2 | 2 | L |
| [10] F115 | Warrant Canary | P6 | 1 | L |
| [10] F262 | Air-Gapped QR Data Diode (one-directional only) | P6 | 2 | L |
| [10] F270 | Digital Legacy Planning | P4 | 2 | L |

### 5.10 Flipper Zero-Class Hardware Features
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [11] F116–F122 | IR Remote, NFC R/W/Emulate, RFID 125kHz, BadUSB, Sub-GHz SDR, GPIO/Serial, iButton | P4-P8 | 2-4 | L/S |
| [11] F142–F147 | LoRa Mesh, APRS/Ham Bridge, Satellite SOS, Emergency Broadcast Monitor, UWB Precision Find, Wi-Fi Aware (NAN) | P6-P8 | 2-4 | S/L |

### 5.11 VPN/Firewall/Location/Wi-Fi/Bluetooth Bundle
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [12] F128–F131 | Wi-Fi Manager+, Wi-Fi Direct Transfer, Captive Portal Auto-Handler (confirmed real pain point), Hotspot Hardening | P1-P3 | 2-3 | L |
| [12] F134–F140 | BLE Mesh Messaging, Presence Switch, Proximity Lock, Beacon Broadcaster, Audio-over-BLE, BT HID, Reverse Tethering | P3-P7 | 2-3 | L |
| [12] F148–F150 | Per-App SIM Routing, eSIM Quick-Switch, Carrier Band Lock | P4-P5 | 1-2 | L |
| [12] F151–F153 | Spam/Scam Call-Block List, SMS Remote Trigger, Encrypted SMS/RCS Backup | P2-P3 | 2 | L |
| [12] F158 | Failover Chain | P2 | 3 | L |
| [12] F238 | Scheduled Blackout Mode + Wake Task List | P3 | 3 | L |
| [12] F239 | Radio Optimization Suite (within hardware limits, honest framing — not a "boost" beyond spec) | P4 | 2 | L |
| [12] F240 | Open Network Auto-Join (paired with F36, required not optional) | P3 | 2 | L |
| [12] F241 | Hidden Network Manager (passive discovery default; active probing off by default, privacy trade-off labeled) | P4 | 2 | L |
| [12] F244–F246 | Offline Maps+Translation, Roaming Cost Guard, Local Emergency Number Auto-Map | P2-P3 | 2-3 | L/S |

### 5.12 Router Integration
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [13] F201–F211 | Port Forwarding Manager, UPnP Toggle, Push Custom DNS, IP Reservations, Guest Network Manager, Client Steering/QoS, Router-Side Device Block, Bridge Mode Awareness, WAN/ISP Panel, Port Status Dashboard | P3-P5 | 1-3 | L/S |

### 5.13 Platform and Community
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [14] [GH] F90–F93 | P2P WireGuard Mesh, PTT/Mesh Messaging (base), Dongle/Flipper Hardware umbrella, Personal Server Mode | P6-P8 | 3-4 | L/S |
| [14] F94 | Tiered UI (Newbie/Expert) | P0 | 2 | L |
| [14] F95 | Tile Home Screen (full spec in 4.11) | P0 | 2 | L |
| [14] F159–F161 | Wi-Fi Password Vault (auto-saved), Mesh Message History (auto-saved), Universal Service Picker | P1-P2 | 2-3 | L |
| [14] F167–F168 | Simple Mode/Caregiver View, Fingerprint-Resistant Browser | P2-P3 | 2-3 | L |
| [14] F180–F190 | Universal Search, Explain This Connection*, Universal Undo, Profiles/Modes, Weekly Report, Conflict Detector*, Settings-Only Sync, Battery Impact*, Why Is This Slow*, Guided Setup*, Community Rule Packs (*cross-listed above) | P1-P2 | 1-2 | L |
| [14] F890 | Four-Slot Profile Switcher (exactly 4 saved profiles, one-tap switch, built on F183 + F666's export format) | P2 | 2 | L |
| [14] F165 | Widget Set | P2 | 2 | L |
| [14] F247–F249 | App Cloning/Dual Instances, Full Profile Switching, Kiosk/Single-App Lockdown | P3-P4 | 2-3 | L |
| [14] F250–F252 | Android Auto Privacy Filter, Smart TV/Cast Firewall, Wearable Data Guard | P3-P4 | 2-3 | L |
| [14] F253–F256 | Keyboard Privacy Audit, Screen Record/Screenshot Detection, Full Accessibility Audit Mode, NFC Door Lock Companion | P2-P4 | 2-3 | L |
| [14] F257–F258 | Mock API Server Mode (dev tool), Enterprise/Self-Host Deployment Guide | P4-P6 | 2-3 | L |
| [14] F259–F260 | Network Printer Firewall, USB-C Accessory Manager | P3-P4 | 2 | L |
| [14] F263–F264 | Consent/Permission Audit Trail, GDPR/CCPA Rights Request Templates | P2-P3 | 1-2 | L |
| [14] [GH] F265–F267 | Censorship-Resistant Distribution, Reproducible Builds, Public Transparency Log | P2-P3 | 3 | L |
| [14] F268–F269 | Interactive Privacy Literacy Mode, Red-Team-Yourself Mode | P3-P4 | 3 | L |
| [14] F273–F276 | Local-Network Fleet View, Router Firmware Companion, Public Roadmap/Vote, Independent Security Audit (published) | P3-P6 | 2-4 | L |
| [14] F282–F283 | Anonymous Donation Methods (Monero/cash/BTC with honest caveats), No-Server Donation Page | P2 | 1 | L |

### 5.14 AP / Mesh / PTT Infrastructure (shared "AP Engine" + "Mesh Comms Engine")
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [15] F284–F286 | USB Tethered Protection Mode, Preset-on-Connect Profile, Auto-Prompt Mode | P2-P3 | 2 | L |
| [15] F287 | Fake-Online Access Point Mode (software-only, no extra hardware) | P1 | 3 | L |
| [15] F288–F291 | Sandboxed Device Network, Travel Shield AP, Offline LAN Party Mode, Captive-Portal-Free Guest Access | P2-P3 | 2-3 | L |
| [15] F292–F296 | Direct Phone-to-Phone Bridge, Relay Chain Mode, Emergency Mesh Fallback, Local Dev/Test Server, Air-Gapped File Beam | P2-P6 | 2-4 | L |
| [15] F297–F309 | Full PTT Suite: Direct PTT, Mesh PTT, Channels, BT Fallback, Audio Compression, Priority Interrupt, Voice-to-Text Log, PTT-Linked Location, Broadcast/Scanner Mode, Store-and-Forward Voice, Cross-Protocol Radio Bridge, Hardware Button Mapping, Noise Gate | P2-P6 | 2-4 | L/S |
| [15] F338–F340 | Wi-Fi Repeater Mode, Mesh Repeater Chain, Cellular-to-Wi-Fi Share Mode (labeled as data-sharing, NOT cellular repeating — see 4.12 FCC boundary) | P2-P4 | 2-3 | L |

### 5.15 Remote Access
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [16] F328–F337 | Remote Desktop Engine, Low-Latency Video Pipeline, Input Injection Bridge, Transport-Agnostic Streaming (reuses AP/VPN/Tor), Integrated File Transfer, Session Selector, QR Pairing (no passwords), No Default Relay Server, Explicit Session Consent, Full Session Audit Log | P3-P4 | 3-4 | L |

### 5.16 Protocol Interoperability
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [17] F341–F344 | MQTT, CoAP, Modbus Reader, Zigbee/Z-Wave Bridge | P4-P6 | 2-3 | L/S |
| [17] F345–F347 | SSH/Telnet Client, WebRTC Data Channel, mDNS/Bonjour Browser | P4 | 2 | L |
| [17] F348 | (deleted) | — | — | — |
| [17] F349 | ASHA Hearing Aid Streaming Support | P4 | 2 | L |
| [17] F350–F352 | IPP Printing, CalDAV/CardDAV Sync, IMAP/SMTP Client Core | P4 | 2 | L |
| [17] F353–F354 | BLE Audio (Auracast), MIDI over BLE | P6-P7 | 2 | L |
| [17] F355–F356 | NMEA GPS Output, Winlink/Digital Radio Modes | P6-P8 | 2-3 | S |
| [17] F357–F358 | Game Streaming (Moonlight/Sunshine-compatible), LAN Game Discovery | P4-P6 | 3 | L |
| [17] F359 | OBD-II Bluetooth Reader | P6 | 2 | L |
| [17] F360 | Lightning Network Support (strengthens F282) | P4 | 3 | L |

### 5.17 Motion/Input Innovation
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [18] F399 | Motion-as-Mouse Input (accelerometer/gyro → BT HID mouse) | P4 | 3 | L |
| [18] F400 | Calibrate-Your-Plane Setup (works lying down, sideways, anywhere) | P4 | 2 | L |
| [18] F401 | Click/Scroll Gesture Set | P4 | 2 | L |
| [18] F402 | Smart TV Remote Profile (D-pad hybrid + free cursor, no IR needed) | P3 | 3 | L |
| [18] F403 | On-Screen Keyboard Overlay (HID keyboard for TV input) | P3 | 2 | L |

### 5.18 Hidden Settings Surface (ties to F242)
Covered under F242 above — Android's buried developer/privacy/display settings plus manufacturer-specific extras (Samsung One UI, etc.), surfaced in one searchable, risk-labeled hub.

### 5.19 Gatekeeping/Legal Awareness
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| (covered) | F376, F380, F381, F386, F390 — see 5.5, all informational/awareness panels, no operational capability | — | — | — |

### 5.20 "Ultimate App" Philosophy Items (project-level, not single toggles)
F265–F267 (self-protection/transparency), F268–F269 (education), F270 (legacy), F271–F272 (low-end/accessibility), F273–F274 (extend past the phone), F275–F276 (provable trustworthiness) — all cross-listed in 5.13, flagged here as the category that separates "very good app" from "ultimate app."

### 5.21 AI Assistant / "Brain"
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [22] [GH] F323 | On-Device Assistant Core (local model, fully offline) | P5 | 4 | L |
| [22] F324 | Optional Cloud Reasoning Tier (opt-in, explicit invoke only) | P5 | 2 | L/S |
| [22] F325 | Unified Decision Engine (ties together F16, F60, F97, F181, F185, F189) | P3 | 3 | L |
| [22] F326 | Proactive Briefing (ambient daily/on-open summary) | P4 | 2 | L |
| [22] F327 | Assistant Input Sandboxing (read-only, untrusted-data labeling — see 4.13) | **required, not optional** | 2 | L |

**Authority Ladder (governs F323–F326, mandatory for any future autonomous-decision feature):**
1. Observe & explain (always on)
2. Suggest (default — shows recommendation, waits for a tap)
3. Act with one-tap confirm (medium-stakes: block a domain, enable a rule)
4. Act autonomously (only fully reversible, low-stakes, user-whitelisted actions)
5. **Never autonomous:** wipe, lockdown, root toggles, vault access, anything on the F112 two-factor list. The assistant may recommend; it may never execute.

---

### 5.22 UX Standards, Encryption, Browser-Extension Ports, RethinkDNS Improvements, P2P Safety, Resource Sharing, Identity Disguise, Everyday Life
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [23] F404–F408 | Five Lenses Standard + Compare/Simulate/History/Export (see 4.14, applies to every feature) | P0 | — | L |
| [23] F409 | Cookie Auto-Delete (per-domain, whitelist-aware) | P2 | 2 | L |
| [23] F410 | Consent-Dialog Auto-Reject | P2 | 2 | L |
| [23] F411 | Tracking Parameter Stripper (extends F17) | P2 | 1 | L |
| [23] F412 | Container/Identity Tabs (in-app browser, ties to F279) | P3 | 2 | L |
| [23] F413 | Local Resource Substitution (serve common JS libs locally, not via tracking CDNs) | P3 | 2 | L |
| [23] [GH] F414 | Header Injection/Modification (dev-console tier, ties to F212) | P4 | 2 | L |
| [23] F415 | Granular Per-Domain Script/Resource Blocking | P3 | 3 | L |
| [23] F416 | Referrer Trimming | P2 | 1 | L |
| [23] F417 | Redirect Skipper (jump past tracking redirect chains) | P2 | 2 | L |
| [23] F418 | At-Rest Encryption Standard (AES-256, stated explicitly per feature) | P0 | 2 | L |
| [23] [GH] F419 | End-to-End Message Encryption, Double Ratchet (underlies F107/F134/F297-309) | P1 | 3 | L |
| [23] F420 | Open File Encryption Support (age/GPG, interoperable) | P3 | 2 | L |
| [23] F421 | Encrypted Backup Format, Documented and Auditable (ties to F276) | P2 | 2 | L |
| [23] [GH] F422 | Post-Quantum Readiness Flag (visible per-connection status, not full impl. yet) | P5 | 2 | L |
| [23] F427 | Predictive Resolver Selection (learns network/time patterns, pre-switches) | P3 | 3 | L |
| [23] [GH] F428 | Accessibility-Free Firewall Mode (avoid the permission F384 itself flags as abused; explain clearly if unavoidable) | P2 | 3 | L |
| [23] F429 | Record-Type DNS Rules Per-App (extends RethinkDNS's global-only version) | P4 | 2 | L |
| [23] F430 | Unknown-Source Quarantine, Not Just Block (visible log instead of silent drop) | P3 | 2 | L |
| [23] F431 | Fully Disclosed Multi-Hop Routing (no "secret" endpoints, ever — transparency rule) | P2 | 1 | L |
| [23] F432 | P2P Trust & Safety Layer (signed peer auth, rate-limiting, F232 reputation score) | P1 | 3 | L |
| [23] F433 | Instant Peer Kill Switch (disconnect + blacklist, logged to F263) | P2 | 1 | L |
| [23] F434 | P2P Swarm Distribution for Updates/Blocklists (BitTorrent/IPFS-style, extends F265) | P2 | 3 | L |
| [23] F435 | Opt-In Science Compute Bridge (user-chosen cause, Wi-Fi+charging only, BOINC-model) | P4 | 2 | L |
| [23] F436 | Federated Learning for the On-Device Assistant (model updates only, never raw data) | P5 | 4 | L |
| [23] F437 | Opt-In Snowflake/Tor Bridge Relay (help others bypass censorship, proven design) | P4 | 2 | L |
| [23] F439 | Custom App Identity (name + icon, ties to F76/F248) | P2 | 1 | L |
| [23] F440 | Decoy Shell Mode (convincingly mundane, fully functional decoy app) | P3 | 3 | L |
| [23] F441 | Hidden Access Gesture (deniable trigger into the real app) | P3 | 2 | L |
| [23] F442 | Uninstall Resistance, Framed Honestly (deniability over forced resistance) | P3 | 2 | L |
| [23] F446 | Smart Home Dashboard, No Cloud Required (Thread/Matter/Zigbee, local control) | P3 | 3 | L |
| [23] F448 | Guest Wi-Fi QR Code Generator (ties to F159/F205) | P2 | 1 | L |
| [23] F449 | Family Photo/Video Local Sync (phone-to-phone/home-server, no cloud subscription) | P2 | 2 | L |
| [23] F450 | Screen Time Negotiation Mode (request/approve flow, softer than F72) | P3 | 2 | L |
| [23] F451 | Lost Phone Finder for Family Devices (local/mesh-based, no shared cloud account) | P3 | 2 | L |
| [23] F453 | (deleted) | — | — | — |
| [23] F455 | Parking Spot Memory (GPS + photo, one tap) | P3 | 1 | L |
| [23] F456 | Commute Pattern Insight, Local-Only | P4 | 2 | L |
| [23] F460 | Event Coordination via Mesh, No Group Chat Noise (ties to F107/F134) | P4 | 2 | L |

---

### 5.23 Hardware Repurposing, BLE Discovery, and Professional/Enterprise Tools
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [24] F461–F463 | Generic BLE Fuzzer/Discovery, Auto-Map to Custom Buttons, Command Library Sharing (F216) | P3-P4 | 3 | L |
| [24] F464–F471 | The "Repurpose" Lens: Seismometer, Barometric Weather Station, Flash-as-Signal-Light, Speaker+Mic Sonar, Screen-as-Light-Reference, Vibration Data Channel, Gyroscope-as-Level, FM-to-Bluetooth Bridge (chipset-dependent, F243 tracked) | P6-P7 | 2-3 | L |
| [24] F472–F492 | Professional/Enterprise Suite: Full Protocol Dissector Stack, Zeek-Style Flow Logging, YARA Scanner, pcap Export, Cert Chain Inspector, CT Log Monitor, DNSSEC Chain Viewer, Topology Map, BGP/ASN Lookup, NetFlow/sFlow Export, Syslog/CEF SIEM Export, MITRE ATT&CK Mapping, SBOM Generator, HIBP-style Breach Check (k-anonymity), Compliance Framework Mapper, MOS/Jitter VoIP Scorer, Golden-Signals Dashboard, eBPF-Style Tracing (root), STIX/TAXII Threat Intel Feed, Protocol Anomaly Scorer, Reproducible Investigation Reports | P3-P4 | 2-4 | L |

### 5.24 Live-Session Security, Resource-Saving Architecture, Modular Shell & Encrypted Delivery
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [25] F493–F499 | Live-Stream/Messaging Protection: Forced TURN Relay, Stream-Specific VPN Routing, DDoS Flood Shield, Session Hijack Detector, Call/Stream Failover, Pre-Connection Leak Test, Real-Time IP Exposure Monitor (cross-ref F34, F124) | P2-P3 | 3 | L |
| [25] [GH] F500–F502 | Encrypted Storage Partition Manager (software container by default; true partition needs root), Portable Vault Partition, Quarantine Partition for Untrusted Files (cross-ref F156, uses VeraCrypt per 4.18) | P2-P3 | 2-3 | L |
| [25] F503–F506 | Screen-State-Conditional Rules, In-Notification Rule Config, Persistent Speed Graph Notification, Mandatory Full IPv6 Support (hard requirement, not optional) | P1-P2 | 1-2 | L |
| [25] F507–F513 | Filter-Level Attribution, Silence/Remove at Filter Level, Breakage-to-Fix Flow, Site/App-Specific Rule Memory, Change-Triggered Re-Check (not timer-based), Incremental Delta Scanning, Profile Staleness Indicator | P1-P2 | 2-3 | L |
| [25] F514–F517 | Universal Check-Once-Remember-Always Pattern (Module Contract 4.2.12), Shared Evaluation Cache (4.2.13), Idle-Aware Scheduling (4.2.14), Dormant Module Zero-Cost Guarantee (4.2.15) | P0 | 2-3 | L |
| [25] F518–F523 | Minimal Shell Install (decoy-sized), Subject-Field Module Packs, Individual Module Browser, Encrypted Module Delivery, Traffic Shape Normalization (±5MB band, padding/chunking), Domain-Fronted Source | P1-P2 | 3 | L |
**Delivery mechanism clarification:** modules are never fetched as live/raw source from GitHub at runtime. GitHub Actions builds and signs each module as a release artifact (source stays on GitHub, the build pipeline lives there); the app downloads and verifies *that signed artifact* via F797's two-layer signature check. GitHub is the source of truth and CI host, not a live code-pull target.
| [25] F889 | Pre-Download/Pre-Install Security Notice (warns before download and before install; one-tap disconnect-everything shortcut via F238/F837 right in the prompt) | P1 | 1 | L |
| [25] F524–F526 | Minimal Resident Footprint, External Scoped Storage for Modules, Installed Size Transparency Note (accurate, not fabricated) | P3 | 2 | L |

### 5.25 Certificate Management, Underused AOSP Capabilities, Network Analyzer Parity, Mesh Integration, Proven-Precedent Integrations
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [26] [GH] F527–F535 | Full Certificate Trust Store Manager: Inspector, Plain-English Explainer, New-Cert Alert, Safe Removal Workflow, Known-Malicious DB (F216), Disable/Delete Toggle, Instant Undo, Install From Curated List, Factory Restore (cross-ref F373) | P1-P2 | 2-3 | L |
| [26] F536–F546 | AOSP Capabilities Round 1: Dynamic System Updates (DSU, low-risk temporary GSI — cross-ref section 11), QuickSettings Tile API, Direct Boot Awareness, Native Multi-User Profiles, Private Space Integration, StrongBox Hardware Keys, Companion Device Manager API, Custom DocumentsProvider for Vault, Screen Pinning, Device Owner Mode (opt-in), App Standby Bucket Management | P2-P5 | 2-3 | L |
| [26] F547–F559 | AOSP Capabilities Round 2: NetworkCapabilities Introspection, Multi-Network Simultaneous Binding, CarrierConfig Access, 5G Network Slicing Hooks, Scoped Storage Media Provider Hooks, StorageStatsManager Breakdown, Hardened InputMethodService Keyboard, Transparent Accessibility Service Use, JobScheduler Constraints, Thermal API Throttling, Precise Foreground Service Types, BiometricPrompt Class 3 Enforcement, Perfetto Tracing Integration | P2-P5 | 2-3 | L |
| [26] F560–F562 | Full Probe Suppression / Silent Scan Mode (passive WifiManager API, no root — confirmed real, not root-dependent), MAC Randomization Enforced+Verified, Connect-Only Mode | P1-P2 | 2-3 | L |
| [26] F563–F566 | External Monitor-Mode Dongle Listener (full pre-connection invisibility, no phone-radio transmission), Passive-Only Scan Mode (root, chipset-dependent), Saved-Network Probe Minimization, "Always Allow Scanning" Leak Closer | P2-P4 | 2-4 | L/S |
| [26] F567–F572 | Wake-on-LAN (local+remote), Traceroute with Geolocation Overlay, Scan History Management, Channel-Width Signal Graph, Detailed Per-Network Radio Info (MIMO/standard/encryption/WPS), Unified Cellular+Wi-Fi Info Panel (cross-ref/expand F41, F42) | P3-P4 | 2-3 | L |
| [26] [GH] F573–F577 | MVT Integration (guided UI wrapper), Accountless Service Profile Template (Mullvad pattern), OnionShare Integration, VeraCrypt-Backed Volumes (replaces DIY), GrapheneOS-Inspired Hardening Suite (auto-reboot-after-idle, charging-only USB, duress wipe PIN — cross-ref section 11), Mandatory Transparency Reporting (cross-ref F267) | P1-P3 | 2-3 | L |
| [26] [GH] F578–F582 | Reticulum Stack Integration (replaces custom mesh transport in F90-93/F292-309), One-Tap Reticulum Onboarding (auto node selection, zero manual config, true "elderly-friendly" test), NomadNet Pages Built In, MicroReticulum Relay Mode (cheap MCU range extension), Self-Hostable Public Transport Node (secure-by-default) | P1-P3 | 3-4 | L/S |

### 5.26 Parking Lot Additions (reference only — see section 11, not scheduled)
F583 Dual-Boot Partition Manager (second-OS, switchable at boot — same bootloader-unlock constraint as F314)

---

### 5.27 Civic, Community, Device Longevity, Accessibility, and Security Round 3
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [28] F590–F594 | Know-Your-Rights Toolkit, Interaction Recording (consent-law aware), Journalist/Source Protection Bundle, Local Community Alert Network (mesh-based), Lost Pet/Item Mesh Broadcast | P2-P3 | 2-3 | L |
| [28] F595–F598 | Right-to-Repair Assistant, Battery Health Honesty Report, E-Waste Disposal Finder, Pet Tracker Privacy Audit | P3-P4 | 1-2 | L |
| [28] F599–F600 | Offline Visual Description (local model), Sign Language Call Optimization | P3 | 3 | L |
| [28] F601–F603 | Guided Legitimate Recovery Assistant, Pre-Lockout Prevention Suite, Proof-of-Ownership Gated Professional Referral (phone/tablet lockout — ownership-verified only, no bypass tool) | P2 | 2-3 | L |
| [28] F604–F608 | Responsible Disclosure Assistant, Personal CTF/Security Sandbox, Password/Passphrase Auditor+Breach Cross-Check, Session Token Hygiene Checker, Dependency Vulnerability Scanner | P3-P4 | 2-3 | L |

### 5.28 Smart Device Recovery, TV/Computer Connection, and Device-Plugged-In Tools
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [29] F609–F611 | IoT Factory Reset Guide Library (F216), Orphaned Device Liberation Assistant (Tasmota/ESPHome/Home Assistant flashing for manufacturer-abandoned devices), IoT Credential/Firmware Recovery via Serial | P2-P3 | 2-3 | L |
| [29] F612–F617 | scrcpy Integration (4.18 registry — strengthens F328), Desktop Mode on External Display, Phone-as-Webcam, Untrusted Computer Consent Clarity, Phone-as-Game-Controller, Secure Encrypted USB Storage Mode | P2-P4 | 2-3 | L |

### 5.29 Noise/Chaff Suite, Tracing Defense, Cache Management
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [30] [GH] F618 | Encrypted Container Metadata Scrubber (cross-ref F500) | P3 | 2 | L |
| [30] F619–F622 | Wi-Fi/Data Chaff Generator, Adaptive Noise Intensity, Wi-Fi Probe Noise Mode, Decoy Device Simulation — *F619 absorption of F104/F278 is Fork 3, section 4.19, pending* | P2-P3 | 3 | L |
| [30] F623 | Identity-Leak Behavior Warning (burner-identity account-crossing alert) | P2 | 2 | L |
| [30] F624 | Universal Cache Clear (all/selected apps — must never touch SERF's own F510/F514 check-once profiles) | P1 | 1 | L |
| [30] F642 | Autonomous Decoy Agent (self-contained, single-device only, non-spreading — the "personality" layer on F619, cross-ref Fork 3) | P3 | 3 | L |

### 5.30 Legacy/Uncommon Protocols, Completeness Promise
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [31] F625–F630 | Gemini Protocol Client, Usenet/NNTP Client, IRC Client, RSS/Atom Reader, ActivityPub/Fediverse Client, Telnet BBS Client | P6-P7 | 1-2 | L |
| [31] F631 | In-App Feature Request Button (wired directly to F216's pipeline) | P1 | 1 | L |

### 5.31 Outside-the-Box Privacy (Pattern, Time, Inference, Social)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [32] F632–F635 | Statistical Innocence Padding, Plausible Alternate History (decoy aging), Herd Privacy Pooling, Protective Data Donation (shared decoy pool, opt-in) | P4-P6 | 3-4 | L |
| [32] F636–F639 | Temporal Obfuscation (randomized scheduling windows), Data Half-Life (granular auto-expiry), Inference Firewall (cross-data correlation warning), Self-Directed Right-to-Be-Forgotten Sweep | P3-P4 | 2-3 | L |
| [32] F640–F641 | Proximity Privacy Zones (location-triggered posture), Social Pressure Deflection (pre-written responses) | P3-P4 | 2 | L |

### 5.32 Legal Hacker Toolkit (D6-scoped: own devices/network/identity only)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [33] [GH] F643–F646 | John the Ripper Integration (own hashes only, 4.18 registry), Metasploit-Style Local Exploit Verification (own network only), SET-Style Awareness Trainer (self/org, with consent), OSINT Aggregator (self-reconnaissance) | P3-P4 | 3 | L |

### 5.33 On-Device Personal Learning (strictly local — see rule below)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [34] F647–F651 | Personal Baseline Engine (sharpens F60/F110/F366/F388), Adaptive Interface Engine, Preference Memory for Presets, Correction-Based Local Reinforcement, Duress-Pattern Deviation Detector (strengthens F102) | P3-P5 | 3-4 | L |
**Hard rule (section 4.13 extension):** this entire category is local-only by default, encrypted at rest (F418), never contributes to F436's shared federated learning without separate explicit opt-in, and is still bound by the Authority Ladder — personalization changes *suggestions*, never grants new autonomous authority.

### 5.34 Things Almost Nobody Knows Exist (disclosure/informational)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [35] F652–F655 | Data Broker Resale Chain Visualizer, Data-Already-Out-There Check, Carrier Pre-Install Silent Updater Audit, eSIM Metadata Exposure Check | P3-P4 | 2-3 | L |
| [35] F656–F657 | Shadow Profile Indicator (informational), Shared Family Plan Visibility Explainer | P4 | 1 | L |
| [35] [GH] F658–F659 | Printer Tracking Dot Decoder, Voice Memo Metadata Stripper | P4 | 2 | L |
| [35] F660–F661 | Smart Speaker Indicator Auditor, HDMI-CEC Command Snooper | P4 | 2-3 | L |

### 5.35 Full Data Control, Unified Play Spoof System, Traffic Interpreter, Multi-Language
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [36] F662–F666 | Universal Outbound Field Override Engine (*supersedes F172-179 individual toggles — see note*), Full Transmission Ledger, Live Intercept and Edit, Inbound Content Sanitizer, Data Sovereignty Export | P1-P2 | 3-4 | L |
| [36] [GH] F667 | Unified Play Services Spoof Layer (formal integration point: F315 microG + F96 isolation + F01 Fake-Online + F97 Source Doctor) | P1 | 3 | L |
| [36] F668–F670 | Semantic Noise Injection, Differential Privacy Toggle (for F231/F436), Time-Delayed Release Queue | P4-P5 | 3 | L |
| [36] F671–F673 | Play Store Metadata Spoof (install-source check), Fake Install Receipt Generator (best-effort, fragile — flagged honestly), Play Billing Sandbox Mode (own apps only, D8) | P2-P3 | 3-4 | L |
| [36] F674 | Value Exchange Transparency (honest "what you give / what you get" for legitimate data trades) | P3 | 2 | L |
| [36] F675 | **Traffic Interpreter** — flagship unifying UI: identify → explain → test-block → choose (block/allow/fake data) → patient analysis when needed. Formal interface layer over F03/F05/F181/F228/F372/F509/F662 | P1 | 3 | L |
| [36] F676–F678 | Install-Time Language Picker (flag/pictograph + dropdown), System-Language Live Translation (on-device ML, no pack needed), Linked System Language Pack Installer (persists after uninstall, OS-level) | P1 | 2 | L |

**Note on F662 vs. F172–F179:** the Universal Override Engine is built to supersede the individual spoofing toggles architecturally (one engine, any field) — this is a natural absorption, not flagged as a Fork, since F172-179 remain fully exposed as pre-configured presets *within* F662 rather than being discarded; no capability is lost.

---

## 6. Difficulty Scale (reference)
1. Easy — UI/settings/lists · 2. Moderate — hooks into existing APIs · 3. Hard — new engine/deep integration · 4. Very hard — reliable detection logic/deep permissions · 5. Frontier — separate sub-project, may need hardware

## 7. Cost Categories (reference)
**L** = fully local/free forever · **S** = server/hardware dependency, free workaround always documented, never a required subscription

---

## 8. Shared Engines (architectural note — build once, many features depend on it)
- **AP Engine:** underlies F287–F296, F338–F340. One shared hotspot-creation/management module, not reimplemented per feature.
- **Mesh Comms Engine:** underlies F91, F107, F134, F292–F309. One shared relay/voice/text transport, not reimplemented per feature.
- **Shizuku/ADB Layer (F321):** underlies F96, F100, F310, and any future no-root feature needing shell-level access.

---

## 9. BUILD ORDER — Difficulty-and-Priority Tiers, Server-Dependency Ordered (reorganized)

### 9.0 Server-Dependency Ordering Principle (applied as an additional, higher-weight sort axis)
Four real levels, ordered No-to-Yes: **(1) No dependency** — fully on-device, works the instant the app installs. **(2) Public free infrastructure, zero setup by us** — the Tor network, public DNS resolvers, public CT logs, GitHub itself (already hosting everything for free). **(3) Self-hosted, even if free** — a coordination server (Headscale), a relay (OvenMediaEngine), something someone has to stand up and keep running. **(4) Costs money** — a required paid service. **Confirmed: nothing in this register was ever designed to require level 4** (section 3's defaults already mandate a free path for every server-dependent feature), so level 4 should end up essentially empty — that's the correct outcome, not a gap to fill. Level 3 items are deliberately pushed to the end of the build order below, regardless of how early their Priority/Difficulty alone would otherwise place them, so a working, feature-rich app ships well before any self-hosted infrastructure is needed.
**Reorganization scope, stated honestly:** the Feature Register (section 5) already groups all 850+ features into 37 relevant thematic sections (5.0–5.37) — that grouping stays as-is; re-sorting it feature-by-feature into new categories would risk losing content for no real gain. What's rebuilt here is the *order of construction*: old alphabetical Phases A–R are replaced with tiers ordered by actual priority-then-difficulty, using each feature's existing Pri and Diff ratings (section 5's tables). **Within any tier, build the Diff-1/2 items in that tier's range before the Diff-4/5 items** — the Diff column already in each table is the tiebreaker; this isn't re-stated per feature below to keep this section readable.

**Why the hardest tier never requires rewriting earlier work (structural guarantee, not a hope):** this follows directly from F213 (Module SDK) and F438 (build-time removability) — every feature is an independent module reachable only through the defined SDK interface (Module Contract 4.2.11). A Tier 9 module can depend on a Tier 1 module's interface, but nothing in an earlier tier is ever modified to accommodate a later one. If this guarantee is ever at risk for a specific feature, that's exactly what Fork Decision Protocol (4.19) exists to catch before building, not after.

**Section 11 (Parking Lot) stays excluded from every tier until explicitly un-parked.** Research-sweep upgrade findings (F688–F830, section 5.36a–ab) are not separate build steps — each applies within its parent feature's own tier, per the "Upgrades" column in those tables.

### Tier 0 — Foundation (must exist before anything; P0, Diff 1–3)
T0.1 Calibrate · T0.3 Fork RethinkDNS, confirm license, CI build · T0.4 Map the codebase · T0.5 F213/F214/F215 SDK skeleton · T0.6 F216 contribution pipeline skeleton · T0.7 F94/F95 tile home screen (4.11 spec) + Settings drill-down · **T0.8 Security-audit the forked RethinkDNS codebase (F884) before building anything on top of it — see 5.0's new security hardening cluster**

### Tier 1 — Easiest Real Wins (P0–P2, Diff 1–2 — ship fast, prove the shell works)
F04, F191, F192, F224, F225, F125, F503–F506, F448, F624, F631, F159, F165, F113, F155, F416, F411, F417

### Tier 2 — The Soul, Core Mechanics (P1, Diff 2–3 — the actual Fake-Online engine)
F01, F02, F05, F06, F07, F96, F97, F130, F195, F186, F228, F229, F235, F243, F272, F310, F321, F123

### Tier 3 — The Soul, Advanced + Firewall Depth (P1–P2, Diff 3–4)
F03, F10–F20, F154, F162, F193, F194, F222, F223, F226, F227, F236, F237, F373, F387, F151

### Tier 4 — Privacy and Routing Core (P2–P3, Diff 2–3)
F30–F38, F124, F126, F127, F172–F179, F277–F281, F367–F370

### Tier 5 — Tools, Diagnostics, Smart Layer Basics (P2–P4, Diff 2–3)
F40–F49, F108–F111, F132, F133, F141, F166, F196–F200, F181, F189, F230–F234, F261, F50–F53, F163

### Tier 6 — Smart Layer Advanced, AI Assistant (P3–P5, Diff 3–4 — Authority Ladder mandatory from day one)
F16, F17, F60–F62, F323–F327

### Tier 7 — UI Polish, Device/Root Basics, Router Integration (P2–P5, Diff 1–3)
F180–F190, F218–F220, F70–F79, F98–F101, F114, F156, F164, F169–F171, F242, F271, F201–F211
*(F311–F320, F322 intentionally excluded — Parking Lot, section 11)*

### Tier 8 — Covert Comms, Local Bluetooth/Protocol Interop, Motion/Input (P2–P6, Diff 2–4; Dependency Level 1-2 only — self-hosted mesh/relay items moved to Tier 11, see 9.0)
F102–F107, F112, F115, F152, F153, F158, F168, F262, F270, F134–F140, F148–F150, F167, F341–F360, F399–F403

### Tier 9 — Ecosystem, Encryption, UX Standards, Everyday Layer (P1–P4, Diff 1–3)
F212, F217, F221, F265–F270, F273–F276, F404–F408, F423–F426, F438, F409–F422, F427–F437, F439–F442, F446, F448–F451, F455, F456, F460

### Tier 10 — Offline Mesh Social Layer (P1–P3, Diff 2–3 — builds on Tier 8's mesh work)
F847–F858 (directory, forum, offline ride-matching — section 5.37)

### Tier 11 — Hardest / Frontier / Self-Hosted-Infrastructure-Dependent (P3–P8, Diff 3–5, Dependency Level 3 — built last specifically because it needs someone to run infrastructure, not just because it's hard)
F461–F492 (hardware repurposing, professional suite), F493–F526 (live-session security, resource architecture, modular shell), F527–F572 (certificate management, AOSP capabilities, network analyzer parity), F573–F582 (7-repo integration, Reticulum mesh core, Headscale coordination), **F90–F93, F284–F309, F328–F340 (AP/Mesh/PTT/Remote Access — moved here from Tier 8 per 9.0: needs Headscale/OvenMediaEngine-style self-hosted coordination or relay infrastructure)**, F116–F122, F142–F147 (Flipper-class hardware), F391–F398 (frontier disclosure)
*(Built via section 4.20/4.18's mandatory search-first step — integrate Reticulum/LXMF/NomadNet, MVT, OnionShare, VeraCrypt, microG, Shizuku, Headscale, Rosenpass, YARA-X rather than rebuilding any of them.)*

---

## 10. LIVE STATE (update after every checkpoint — this is the resumption anchor)

### 10.0 Session Kickoff Checklist (F859 — check before starting any session)
- [x] GitHub repo exists and is reachable (fork: JesusChristIsGodAlmighty/rethink-app, working branch: Qwen)
- [x] CI (GitHub Actions) confirmed building successfully — run 41738504630 GREEN on Qwen (ab6a57c), APK artifact `serf-debug-apk-2` (170 MB) downloadable. Fork auto-trigger still gated until user approves workflows once on the Actions tab; workflow_dispatch via API works meanwhile.
- [ ] Target test app for F01 chosen (see below)
- [x] Last CHECKPOINT from the previous session copied into this section (see 10.4)
- [ ] `TURNS_PER_SESSION` known, or explicitly still calibrating

- **NEXT:** Tier 1 — F04 COMPLETE, F192 COMPLETE (CI green on 8f65840). F191 Block Page: implementation complete locally (BlockPageManager loopback server + one-tap unblock links reusing the F192 ladder; `f191_block_page_enabled` pref off by default; start/stop wired in BraveVPNService; blocked-lookup recording hooked into all five block branches of TunDnsManager.decideQuery with branch-const fix; 8 pure-logic unit tests added) — pushed this turn as part of the two pending snapshot commits, awaiting CI. Known limitation logged in module docs: firestack exposes no ipcsv suggestion path, so browsers aren't auto-redirected to the page yet; reachable at http://127.0.0.1:8194/?host=<domain>. After CI green → F224 Blocklist Manager per section 9; T0.4 CODEMAP refinement folded into feature work
- **DONE:** T0.1 (calibration underway), T0.3 COMPLETE: fork done; license CONFIRMED Apache-2.0 (D1 corrected); CI GREEN on Qwen branch; APK artifact produced and downloadable (install test pending user device). T0.4 first pass: CODEMAP.md committed. F04 module implemented AND CI-green after fixes (JunkDomainStubManager + TunDnsManager.decideQuery hook + off-by-default pref + unit tests) — see checkpoints 2026-11-04e/f. F192 One-Tap Unblock From Log implemented: "Unblock" chip on the connection-log bottom sheet, visible only for blocked connections with a blocking rule in effect; one tap flips the effective BLOCK rule (exact → wildcard → everybody ladder via new DomainRulesManager.trustEffectiveRule) to TRUST, or adds a per-app TRUST override when the block came from a user-list (RULE2G, which has no BLOCK rule to flip); toast + event log + immediate UI re-render; guarded against double-fire while pending — see checkpoint 2026-11-04f.
- **TURNS_PER_SESSION:** still calibrating (this session used ~15 turns incl. token/setup troubleshooting)
- **App name:** SERF (working title)
- **Target app for first F01 real-world test:** not yet chosen
- **Decisions log:** D1–D11 (section 2) + section 13
- **Open problems:** confirm fork builds in CI (first run in progress); choose first test app; **Parking Lot decision pending — see section 11**. RESOLVED: license question — LICENSE file is verbatim Apache License 2.0 (upstream rethink-app relicensed from GPL since 2022). D1's "believed MPL-2.0" was wrong; Apache-2.0 is permissive and fully compatible with our goals (attribution + NOTICE retention required).

### 10.4 CHECKPOINT LOG (newest first)
- **2026-11-04g (Qwen session):** Done: F191 Block Page implementation finished and pushed to Qwen (closing the two unpushed snapshot commits + this turn's fixes). What shipped: (1) `BlockPageManager` — loopback-only (127.0.0.1:8194) mini HTTP server, off by default behind `f191_block_page_enabled`; serves a zero-jargon HTML page (STATE 14.3 education requirement) explaining why a connection was blocked, with "Unblock everywhere / Unblock for this app" links that reuse the F192 `trustEffectiveRule` ladder (falls back to adding an everybody-scope TRUST override for list-blocked domains); all attacker-influenced strings HTML-escaped + length-capped (4.2.7); every failure path degrades to pre-feature behavior (fail-open, Module Contract 4.2). (2) Wiring: start/stop from `BraveVPNService` based on the pref; `maybeNoteBlocked` hooks in all five block branches of `TunDnsManager.decideQuery` feeding a capped recently-blocked list. Fixes found during this turn's review of the previously-unreviewed snapshots: branch tags were passed as string literals ("BRANCH_APP_BLOCKED") instead of the BRANCH_* constants while the when-branches compare constants — recording silently did nothing; corrected all five call sites. Removed unused imports (CustomDomain, Dispatchers/launch/withContext), replaced Logger with plain android.util.Log so unit tests run without koin (Logger.log -> dbWrite -> VpnController crashes with no graph). Added `BlockPageManagerTest` (8 tests: query parsing incl. URL-decode edge cases, esc() neutralizes <script>/quotes, malformed-host fail-closed before DB touch, recently-blocked dedup/cap/most-recent-first, render with escaped host + unblock links, no-host variant, not-running-by-default). Known limitation (documented in module header): firestack DnsDecision has no suggested-ip path (ipcsv always ""), so browsers are NOT auto-redirected yet — page is reachable manually at http://127.0.0.1:8194/?host=<domain>; auto-redirect needs a Go-core follow-up. Next: watch CI on this push; if green → F191 done-test met at build level (install test pending user device, same as F04/F192) → F224 Blocklist Manager. Open problems: none new; sandbox still lacks Android SDK so verification = CI only.
- **2026-11-04f (Qwen session):** Done: F192 One-Tap Unblock From Log finished and committed (local commit on Qwen, pushed this turn): gap analysis from 11-04d resolved — upstream tap-to-cycle covers manual rules but NOT (a) list-blocked (RULE2G) domains or (b) blocks that only resolve via the wildcard/everybody ladder. Implementation: new `DomainRulesManager.trustEffectiveRule(domain, uid)` walks exact→wildcard→everybody and flips the first BLOCK to TRUST at the right scope (returns false if nothing blocked); "Unblock" MaterialButton chip in `bottom_sheet_conn_track.xml` (gone by default), shown by `updateUnblockChip()` only when the logged connection is blocked AND a blocking rule exists beyond the exact scope; `oneTapUnblock()` = trustEffectiveRule, else per-app TRUST override for list blocks, then toast + FW_RULE_MODIFIED event + re-render; disabled while pending (no double-fire). Finishing touches fixed this turn: (1) missing string resources `f192_unblock_action` / `f192_unblocked_toast` added (layout referenced them — would have failed resource linking); (2) click listener was never wired (`setOnClickListener { oneTapUnblock() }` added); (3) `updateUnblockChip` was hard-coded to Status.NONE instead of passing the actual exact rule status — chip stayed visible for already-trusted rows. Next: watch CI on this push; if green, F192 done-test met at build level → F191 Block Page. Open problems: none new; install test still pending user device (same as F04).
- **2026-11-04e (Qwen session):** Done: F04 CI confirmed GREEN after fix push 20a5ba3 (assemble + unit tests incl. the 5 JunkDomainStubManager/TunDnsManager tests) → F04 done-test met at build level. Started F192: implemented trustEffectiveRule ladder helper + unblock chip UI + one-tap handler (committed as task snapshot 961dc31, finishing-touch fixes in 11-04f). Next: see 11-04f.
- **2026-11-04d (Qwen session):** Done: diagnosed CI failure on 296e0be (run 41747024698): three compile errors — (1) `EventLogger.log(...)` called statically in JunkDomainStubManager but log() is an instance method (Koin-injected); (2)+(3) `Backend.Reject` unresolved in TunDnsManager.kt:244 and JunkDomainStubManager.kt:223 — firestack's Backend object has no Reject constant. Applied the pre-agreed fallback from 11-04c's open-problems list: stubbed probes now route to `Backend.BlockAll` (the id every existing firewall-block branch uses = this codebase's local-reject transport), logging switched to `Logger.i(TAG, ...)`, unused EventSource/EventType imports removed, docs/comments/tests updated to match, BRANCH_JUNK_STUB tag kept as "junk-stub-reject" (semantic name preserved). Note: earlier "CI green after fixes" claim was wrong — the green run (41738504630) predates the F04 commits; corrected here. Next: push, watch CI; if green, F04 done-test met at build level (install test still pending user device) → pick F191 Block Page / F192 One-Tap Unblock From Log. Investigation note for F192: ConnTrackerBottomSheet ALREADY has tap-to-cycle rule rows (cycleIpRule/cycleDomainRule: none→block→trust→none) — F192 may be mostly shipped by upstream UI; gap analysis needed (does it cover blocked-by-blocklist RULE2G domains? one-tap unblock of the *blocking rule* vs adding a trust override?). Open problems: none new.
- **2026-11-04c (Qwen session):** Done: F04 Junk-Domain Stub List implemented on Qwen (commit d37004e + fixes): new `JunkDomainStubManager` module (seed list of ~20 connectivity-probe domains, JSON store schemaVersion 1, subdomain matching, off-by-default via `f04_junk_domain_stubs_enabled` pref), wired into `TunDnsManager.decideQuery` behind captive-portal branch with new `junk-stub-reject` branch tag, warmed from `BraveVPNService`, 5 unit tests added to TunDnsManagerTest. Review fixes this turn: stubbed queries now route to `Backend.Reject` (was mistakenly BlockAll in the live path; matches rejectedDecision + doc semantics — synthetic local answer, zero egress), persist() made synchronous inside caller coroutine (was fire-and-forget risking lost writes), memory snapshot updated before disk write. Next: push fixes, dispatch CI run on Qwen, confirm assemble+unit tests green, then pick F191/F192. New decisions: none. Open problems: Backend.Reject constant existence is assumed from upstream rethink usage — if CI compile fails on it, fall back to Backend.BlockAll and update docs/tests accordingly; sandbox still has no Android SDK so verification = CI only.
- **2026-11-04b (Qwen session):** Done: T0.3 COMPLETE — CI run #2 green (fixed OOM: raised Gradle/Kotlin daemon memory 768m→3g/2g in gradle.properties); added APK artifact upload step; 170 MB debug APK downloadable at Actions → run 41738504630 → artifact `serf-debug-apk-2`; began T0.4 codebase map → created CODEMAP.md (package layout, LOC counts, launcher entry points, variant/flavor system, MVP-relevant files). Next: user installs APK on a device (Tier 0 done-test); refine CODEMAP with proxy/tunnel deep-dive for F01 Fake-Online work; pick first Tier 1 quick-win feature to implement. New decisions: none. Open problems: fork pushes need one-time Actions approval by user to auto-run CI; sandbox has no Android SDK so all builds go through CI.- **2026-11-04 (Qwen session):** Done: verified Actions enabled on fork (permissions=all, 10 workflows active); discovered fork pushes do NOT auto-run workflows until user clicks 'I understand, enable workflows' on the Actions tab (GitHub anti-abuse gate for forks) — workaround in place: `workflow_dispatch` trigger on Android CI, fired via API. Added APK artifact upload step to android.yml so a green build yields a downloadable/installable APK (Tier 0 done-test requirement). Pushed Qwen (commit 0dc281e + this). Next: dispatch Android CI run on Qwen, watch build, fix failures (risks: Go toolchain download time, google-services fallback to assembleFdroidFullDebug), then confirm APK installs → T0.3 complete → T0.4 codebase map. New decisions: none. Open problems: user should visit https://github.com/JesusChristIsGodAlmighty/rethink-app/actions once and approve workflows to restore automatic CI on every push.- **2026-11-03 (Qwen session):** Done: connected fork via classic PAT; checked out Qwen branch; STATE_v2 uploaded by user and read fully; verified LICENSE = Apache-2.0; patched `.github/workflows/android.yml` to trigger on Qwen pushes; pushed — first CI build triggered. Next: watch build result, fix any CI failures (expected risks: missing `GOOGLE_SERVICES_TEST_JSON` secret → workflow auto-falls back to `assembleFdroidFullDebug`; Gradle/AGP download times). New decisions: none. Open problems: none new. Note: all work confined to Qwen branch per user's explicit permission boundary.

### 10.1 MVP / v0.1 Definition (F860 — the actual first finish line, nothing more)
Before any tier beyond Tier 2 matters, this is the one thing that must be true: **the forked app builds and installs, the probe responder correctly answers `generate_204` with the device in airplane mode, and one real, chosen app launches successfully that would normally refuse to open offline.** Nothing else is required for v0.1. Every other feature, including the rest of Tier 2, is "more," not "the finish line." This exists so "are we done yet" always has one small, checkable answer, not an 850-feature list to measure against.

### 10.2 Tier Done-Tests (lightweight finish line per tier — restores what the old Phase table had)
- **Tier 0:** APK builds in CI and installs; upstream RethinkDNS UI visible.
- **Tier 1:** Every quick-win toggle in this tier is visible, switchable, and does what it says with no crash.
- **Tier 2:** v0.1 (10.1) passes.
- **Tier 3:** Firewall blocks a known test domain and the block is visible in the log with the correct filter named.
- **Tier 4:** A VPN/Tor connection masks the device's real IP, confirmed by an external check.
- **Tier 5:** At least one diagnostic (F44 or F40) returns real, accurate data about the device's own network.
- **Tier 6:** The on-device assistant answers a real question about current traffic without ever acting beyond the Authority Ladder's "suggest" step.
- **Tier 7:** A full settings category (section 4.11) renders correctly end-to-end, including the router-integration screens where a compatible router is available.
- **Tier 8:** Two devices complete a direct mesh message exchange with no internet and no cell signal.
- **Tier 9:** The full onboarding flow (F186) completes for a new install with zero crashes.
- **Tier 10:** A mesh forum post and an offline ride request both successfully propagate between two test devices.
- **Tier 11:** Each integrated component (Reticulum, VeraCrypt, microG, etc.) passes its own upstream test suite inside our build.
*(Full, granular done-tests for an individual feature are written when that feature's own session begins — these are tier-level checkpoints, not a replacement for that.)*

### 10.3 QA Philosophy
Automated unit tests (Module Contract 4.2.5) catch regressions; they don't prove a feature is good enough for daily use. Before anything leaves Beta (F220) into Stable, it needs real-device testing, not just CI green checks, ideally from more than one device model (F243's compatibility map is exactly the mechanism for gathering this). "Stable" means: used daily, on a real device, for at least one full week, with no crash and no silent failure. Nothing is rushed out of Beta to hit a date.

---

## 11. ⛔ PARKING LOT — NOT FOR IMPLEMENTATION (reference only, revisit when ready) ⛔

**Why this section exists:** during the OS/root/de-Google discussion, a real fork-in-the-road question came up that the user doesn't yet have enough information to decide: how far to invest in the device-unlocking/OS-replacement path (root-dependent, higher effort, higher payoff for power users) versus staying focused on the no-root-friendly path (ADB/Shizuku, works for nearly everyone immediately, lower ceiling). Rather than guessing, everything in this cluster is preserved here, untouched, excluded from all phases, until the user chooses a direction. **Nothing here gets built, scheduled, or referenced in planning until explicitly un-parked.**

### 11.1 The parked features
| ID | Name | What it does |
|---|---|---|
| F311 | Guided Systemless De-Google | Magisk-based, root required, stock drivers untouched |
| F312/F313 | Bootloader Unlock & ROM Feasibility Checker | Checks unlock eligibility, Treble compatibility, known GSI reports for the exact device |
| F314 | Guided GSI Installer | Walks through backup, unlock, vbmeta handling, GSI flash |
| F315 | microG Setup Wizard | Open-source Play Services API reimplementation, no real Google code |
| F316 | GApps Variant Picker | Pico through full, explained in plain language |
| F317 | Obscure-Device Community Bounty Board | Flags devices (like the Doogee V Max) nobody's built a GSI for yet |
| F318 | Vendor Interface Diagnostic | Auto-tests a flashed GSI, reports which hardware (Wi-Fi/RIL/camera/fingerprint) broke |
| F319 | Auto-Patch Contribution Loop | Working device-specific fixes get packaged and submitted back through F216 automatically |
| F583 | Dual-Boot Partition Manager | Second OS on a separate partition, switchable at boot — real technique (old DualBootPatcher-style), same bootloader-unlock constraint as F314, bigger undertaking |
| F320 | Automated Device Tree Extractor + Cloud Build Pipeline | Automates the mechanical parts of LineageOS-style device tree extraction and build kickoff; genuinely can't automate the human part (testing real hardware, hand-fixing HAL mismatches); real resource constraint (~150GB+ storage, hours of compute per build, beyond free CI minutes) |
| F322 | Unlock-Friendly Device Guide | Crowd-sourced ratings of which manufacturers/models support bootloader unlocking, so the *next* phone purchase avoids the Doogee situation |

### 11.2 The technical knowledge to preserve (so nobody re-derives this later)
- **Android Treble architecture:** system partition (OS/framework) and vendor partition (manufacturer drivers) are already separated by design, talking through a standardized interface called **VINTF**. This is *why* GSIs (Generic System Images) work at all — a generic OS image never touches the vendor partition, drivers stay exactly as shipped.
- **Why obscure devices still take forever despite Treble:** not every OEM implements VINTF cleanly — budget manufacturers especially pass Google's certification bar without polishing every driver interface, since no custom-ROM community was ever going to hold them accountable. This is compatibility-testing labor, not driver-writing labor, and it's discovered empirically per device, not derivable by diffing source code (the vendor binaries are closed; there's no AOSP source to diff against).
- **The two real technical blockers**, confirmed from real device-support threads during this discussion:
  1. **VINTF/vendor interface mismatches** — hardware pieces (Wi-Fi, RIL/cellular, camera, fingerprint) come up broken or need hand-written workarounds even on devices where a GSI successfully boots.
  2. **Verified Boot / vbmeta signature chain** — root + unlocked bootloader grant *permission* to flash something different; they do NOT automatically disable the Verified Boot signature check. That's a separate, explicit step (disabling or replacing vbmeta) that has to be done correctly, and getting it wrong is a documented cause of bootloops.
- **LineageOS's actual real-world process** (confirmed correct by the user's own reasoning): pull stock firmware → extract proprietary binary blobs (drivers/firmware, not modified-AOSP source to diff) → write a device tree describing the hardware → compile against those extracted pieces. This is legally grounded in reverse-engineering exemptions for interoperability — fundamentally different, legally, from defeating encryption or copying protected logic.
- **The hard legal/technical line, never to be crossed:** extracting and reusing a manufacturer's binary driver (legal, interoperability) is categorically different from defeating Verified Boot's cryptographic signature chain (illegal under DMCA 1201 in the US and equivalent laws elsewhere, regardless of device ownership). Every feature in this parking lot was scoped to stay on the legal side of that line. A direct request to cross it (described as "cracking Google's protections," reframed even as sci-fi/hypothetical) was declined during this conversation and should continue to be declined if raised again — this isn't a gap in capability, it's a boundary that holds regardless of framing.
- **The Doogee V Max specific situation:** budget/niche devices like this typically ship with locked, non-unlockable bootloaders and no official unlock method — that's the manufacturer's business decision, not a Google restriction, and it's the actual reason this path wasn't available, not lack of user knowledge. One Doogee model (Y8, not V Max) does have some community Treble GSI overlay support, suggesting Doogee as a brand isn't totally absent from this community, a mildly encouraging data point for later.
- **What already ships regardless of this decision:** F310 (ADB debloat, no root) and F321 (Shizuku layer) are **not parked** — they work today, for nearly everyone, with no bootloader unlock, no risk, and stay the flagship first-touch feature of the device-freedom category in Phase B/I.

### 11.4 Watch-and-revisit note: REA and reverse-engineered GMS
The REA (Reverse Engineer Anything) project is a real, legitimate, MIT-licensed general tool (AI-agent-assisted analysis via Ghidra/JADX/Apktool) — fine to integrate into F212's Developer Console as a generic capability. Separately, Google's own GMS license explicitly prohibits reverse engineering GMS itself, so a project that decompiles and redistributes Google's actual service code (as opposed to microG's clean-room reimplementation of documented API behavior) sits on the risky side of the DMCA interoperability line (section 4.12). Nothing concrete has shipped from any "REA community GMS reimplementation" as of this writing — revisit if/when something real and clearly clean-room ships; until then, F315 (microG) remains the legally solid path and is NOT parked.

### 11.7 New context (research sweep, Batch 11): Android OS Build Verification
Android 17 is introducing native OS verification specifically to help users confirm their device runs an official build, explicitly designed to detect "modified Android versions that appear legitimate while secretly compromising security," rolling out first on Pixel. This is a real, new obstacle for the paused custom-GSI/de-Google path (F311-F320): a legitimately de-Googled AOSP build could potentially get flagged by this mechanism depending on how it's implemented. Worth researching the specifics of how this verification works (what it actually checks) before this section is ever un-parked, since it may change the calculus.

### 11.5 Note: DSU may lower this section's risk profile
F536 (Dynamic System Update) lets a supported device *temporarily* boot into a GSI with full, instant, no-flash reversibility — meaningfully lower-risk than the permanent GSI flash this whole section is paused on. Worth testing DSU support specifically when this section is revisited, as it may be a safer first step than anything else listed here.

### 11.8 CORRECTION (research sweep, Batch 23): GrapheneOS is no longer Pixel-only
Earlier in this conversation, GrapheneOS was described as Pixel-only — that was accurate at the time but is now outdated. As of March 2026, GrapheneOS announced a partnership with Motorola Mobility, with Qualcomm's Snapdragon 8 Elite processor introducing hardware security features enabling GrapheneOS's hardened protections on non-Google hardware for the first time. Unofficial, less-complete ports also exist for the OnePlus 11 and Samsung S23. This meaningfully changes the device-choice landscape discussed in sections 11.1-11.3, and also means F804/F810's GrapheneOS-pattern hardening (F576) and age-verification resistance precedent now apply to a wider hardware base than previously understood. Worth re-researching current non-Pixel support status specifically when this section is ever un-parked.

**Also confirmed, directly validating this entire project's origin:** mandatory Android developer verification enforcement begins September 2026 in Brazil, Indonesia, Singapore, and Thailand, with global rollout planned for 2027. A "Keep Android Open" campaign with 60+ organizations from 20+ countries is actively fighting this, naming GrapheneOS and LineageOS specifically as threatened. This is live, ongoing, and exactly the problem this project exists to answer — worth tracking as it develops.

### 11.3 The actual decision still pending
Roughly: *"Do we build the deep, root-dependent, device-specific OS-replacement tooling (F311–F320, F322) as a major investment, potentially diverting effort from the broader feature set — or do we keep the project's center of gravity on the no-root-friendly path (F310/F321) and leave the deeper path for much later, or as a separate companion project?"* No action needed until the user has had time to think this through. When ready, say so explicitly and this section un-parks into the normal phase plan.

---

## 11.6 Noted, not built: Masked Relay Number ("middleman" for PSTN contacts)
A real, understood problem: a VoIP number given out still gets saved with the person's real name in others' synced contacts, defeating privacy at the social layer regardless of the number's technical privacy. A masked/relay number (receive at a server, forward to the real device, rotatable independent of the underlying VoIP account) is the right shape of fix, and commercial versions already exist (Google Voice, MySudo, Hushed). **Not buildable free/local-only** — receiving real PSTN calls/texts fundamentally requires a hosted telephony trunk, the same server-dependency wall as OnionShare/Reticulum's internet on-ramp. Not given an F-number; mentioned in the Guide Library (12.1) as an external option. Our own mesh messaging (F578-582) sidesteps the whole problem structurally — an LXMF address has no phone number to leak into anyone's contacts at all.

## 12. The How-To Guide Library (informational — things our app doesn't build, but users should know exist)
**Purpose and voice:** SERF can't be everything, some genuinely great privacy tools live outside any app's scope entirely (a different phone, a different SIM, a different habit). Rather than staying silent about them, SERF includes a library of short, exciting overviews, not tutorials, that inform and spark curiosity the way a good explainer video does, then point outward. **Standing directive for every future session:** when a topic comes up that's genuinely useful but out of scope for the app itself (can't be free, can't be local-only, needs hardware/a service SERF can't bundle), write it up here in this voice rather than dropping it. Keep each entry to: what it is, why it's exciting, the rough pieces involved, honest cost/limits, in a few short paragraphs, never a full walkthrough.

### 12.1 Guide: The Invisible Phone Stack
Picture a phone that's structurally unable to leak your identity to the network it's running on, no Google account was ever created to leak, no cell radio identifier ever gets matched to a call. That's not science fiction, people are running it today with three off-the-shelf pieces.

**The three pieces:** a de-Googled phone (AOSP-based, running GrapheneOS, CalyxOS, LineageOS, or similar, with microG standing in for Google's push-notification plumbing without ever creating a real Google account), a no-KYC VoIP calling service (your calls route over the internet via SIP rather than a cell tower, so the IMSI/IMEI identifiers that normally ride along with every call never exist for that call), and a data-only eSIM with no phone number attached (internet access with nothing for the PSTN's surveillance machinery to hang metadata on).

**Why it's exciting:** stacked together, a call placed this way never touches the parts of the phone network that are built for mass surveillance, it's not hidden traffic, it's traffic that was never generated in the first place. Two people on the same kind of setup can even call each other by internal extension, completely bypassing the public phone network, free, unlogged, with zero location or identity metadata attached.

**Honest limits:** reliability depends entirely on correct setup (notifications and background permissions have to be configured exactly right or the phone simply won't ring), it costs real money (phone, VoIP line, eSIM, all recurring), and a masked relay number for giving out to contacts (see 11.6) isn't part of this stack, that's still an open problem even here. Some services (Google, Facebook) refuse VoIP numbers for two-factor authentication; many others, including most banks, accept them fine.

**Where to look:** search for "de-Googled phone," "no-KYC VoIP service," and "data-only no-KYC eSIM" — several providers exist, compare based on your own threat model rather than taking one company's word for it.

### 5.36a Research Sweep Progress (F01–F678, 25 at a time — see entries below, continued across sessions)
| Batch | Range | Status |
|---|---|---|
| 1 | F01–F25 | **DONE** — F688-F696 added below |
| 2 | F26–F50 | **DONE** — F697-F701 added below |
| 3 | F51–F75 | **DONE** — F702-F707 added below |
| 4 | F76–F100 | **DONE** — F708-F714 added below |
| 5 | F101–F125 | **DONE** — F715-F720 added below |
| 6 | F126–F150 | **DONE** — F721-F723 added below |
| 7 | F151–F175 | **DONE** — F724-F729 added below |
| 8 | F176–F200 | **DONE** — F730-F734 added below |
| 9 | F201–F225 | **DONE** — F735-F740 added below (Headscale added to 4.18 registry) |
| 10 | F226–F250 | **DONE** — F741-F745 added below (Saavdhan added to 4.18 registry) |
| 11 | F251–F275 | **DONE** — F746-F752 added below (rbtlog added to 4.18 registry; F752 logged in Parking Lot) |
| 12 | F276–F300 | **DONE** — F753-F758 added below |
| 13 | F301–F325 | **DONE** — F759-F764 added below (Gemma 4 E2B, llama.cpp/MLC-LLM added to 4.18 registry) |
| 14 | F326–F350 | **DONE** — F765-F770 added below (OvenMediaEngine added to 4.18 registry) |
| 15 | F351–F375 | **DONE** — F771-F775 added below |
| 16 | F376–F400 | **DONE** — F776-F780 added below |
| 17 | F401–F425 | **DONE** — F781-F783 added below (major: Manifest V3 immunity confirmed as real structural advantage) |
| 18 | F426–F450 | **DONE** — F784-F787 added below |
| 19 | F451–F475 | **DONE** — F788-F792 added below (YARA-X registry update) |
| 20 | F476–F500 | **DONE** — F793-F796 added below |
| 21 | F501–F525 | **DONE** — F797-F799 added below |
| 22 | F526–F550 | **DONE** — F800-F803 added below |
| 23 | F551–F575 | **DONE** — F804-F810 added below (F804/F809 are major — see section 11.8) |
| 24 | F576–F600 | **DONE** — F811-F814 added below |
| 25 | F601–F625 | **DONE** — F815-F818 added below |
| 26 | F626–F650 | **DONE** — F819-F823 added below |
| 27 | F651–F678 | **DONE** — F824-F830 added below |

**FULL SWEEP COMPLETE (F01–F678, all 27 batches).** 143 new research-backed entries added (F688–F830) plus the Parking Lot context updates in 11.7/11.8. Future sessions: treat this as a completed pass; new ideas still append normally per section 0, point 7, but a fresh full-register sweep need not be re-run unless the user asks for one.

### 5.36b Research Sweep Findings — Batch 1 (F01-F25: Core Engine, Firewall start)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F688 | Donor-Pattern Traffic Mimicry | F619, F642 | Cover traffic copied from a real device's own timing/size signature outperforms synthetic padding against modern classifiers |
| F689 | ML-Resistant Traffic Obfuscation | F619, F278 | Real threat model is transformer-based traffic classifiers (90%+ accuracy), not simple statistical analysis |
| F690 | Named WF Defense Algorithm Suite (FRONT/GLUE/TBP) | F32 | Three published, implementable Tor-traffic defense algorithms |
| F691 | Adversarial Perturbation Response Mode | F35, F279 | Traffic crafted specifically to deceive classifier models directly |
| F692 | Fake-Online Fidelity Tier System | F01, F03 | Formal convincingness tiers, cheap-fast vs. maximally-convincing, chosen per app's inspection depth |
| F693 | Response Scrubber for Fake-Online | F01, F03 | Strip tells that would reveal a response is simulated |
| F694 | LLM-Adaptive Decoy Behavior | F642, F323 | On-device assistant drives decoy behavior adaptively instead of fixed patterns |
| F695 | ISP-Level Device Fingerprint Defense | F35, F561 | Defends against ISP-deployed programmable-switch fingerprinting, a layer above home-network defenses |
| F696 | Mutual-Information-Minimizing Chaff Mode | F619 | Rigorous optimization target (FRUGAL) vs. ad hoc padding |

### 5.36c Research Sweep Findings — Batch 2 (F26-F50: Routing/Privacy, Tools start)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F697 | Post-Quantum WireGuard via Rosenpass (4.18 registry) | F123, F422 | Real open-source project supplies PQ-secure PSK without modifying WireGuard; proven production-viable by shipping commercial VPNs |
| F698 | VPN-over-HTTP/3 (MASQUE) Transport | F31, F277 | "Naked" WireGuard UDP is DPI-detectable; wrapping in HTTP/3 defeats modern deep packet inspection |
| F699 | TLS Fingerprint (JA3) Rotation | F35 | Documented real circumvention technique, periodic fingerprint rotation |
| F700 | REALITY-Style Handshake Mimicry | F30, F33 | Copies a real site's TLS handshake without terminating there, reduces detectability |
| F701 | Auto-Transport Failover on Throttle Detection | F158 | Automated health checks detect throttling, switch transport automatically |

### 5.36d Research Sweep Findings — Batch 3 (F51-F75: Speed, Smart Layer, Device/Root start)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F702 | On-Device Power-Draw Anomaly Detector | F60, F61 | Changepoint analysis of power consumption as a malware-detection time series, a new detection vector |
| F703 | Joint Identity-Action Stalkerware Detection | F61 | 2026 feasibility study on joint identity-action detection for IPV infiltration evidence |
| F704 | Byzantine-Resistant Federated Aggregation | F436, F232 | Mahalanobis-distance-based rejection of poisoned model updates |
| F705 | Concept-Drift-Aware Behavioral Models | F60, F647 | Real-time drift detection with 89% lower inference latency at maintained accuracy |
| F706 | Cross-Device Behavioral Fingerprint Defense | F647 | Hardens against demonstrated behavior-based user recognition across devices |
| F707 | Graph-Based Behavioral Modeling (Node2Vec/GraphSAGE) | F60, F647 | 2026 frontier technique, graph-augmented isolation forest vs. flat feature vectors |

### 5.36e Research Sweep Findings — Batch 4 (F76-F100: Device/Root end, Platform/Mesh start, Play defense)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F708 | pKVM Hardware-Isolated Vault/VPN | F73, F123 | Native Android hypervisor VM, guaranteed isolation from main-OS compromise even if rooted |
| F709 | Native Advanced Protection Mode Integration | F183 | Android 16's own single-toggle bundled security controls |
| F710 | Native 2G Disablement Enforcement | F366 | Android 16 ships native hardened connectivity controls, surface/enforce directly |
| F711 | AI Scam Call Detection Integration | F151, F162 | Android 16 native AI scam detection + in-call intervention |
| F712 | CRDT-Based Mesh Consistency Layer | F107, F298 | Real, actively maintained Rust CRDT mesh libraries confirmed |
| F713 | Standard Bluetooth Mesh Node Roles (Friend/LPN/Proxy/Relay) | F134 | Official spec roles, proven interoperable, actively maintained |
| F714 | SELinux-Enforced Module Isolation | F213 | Real OS-level sandboxing backing for build-time removability |

### 5.36f Research Sweep Findings — Batch 5 (F101-F125: Covert Comms, Flipper hardware)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F715 | Provably-Secure Steganography Engine (Meteor/Discop-style) | F105 | Distribution-copy methods with a mathematically provable ~50% (random-guess) detection target |
| F716 | Adaptive GAN-Resistant Embedding (HUGO/UNIWARD-class) | F105 | Embeds in complex regions, resists both statistical and ML-based steganalysis |
| F717 | Post-Quantum Steganography | F105, F697 | Kyber+AES+TransformerGAN combination, near-random detection accuracy, quantum-resistant |
| F718 | Wi-Fi CSI Covert Channel | F105, F106 | Hides data in Wi-Fi Channel State Information itself, no file transfer at all |
| F719 | LLM-Generated Linguistic Steganography | F107, F323 | Disguises mesh messages as natural generated text |
| F720 | Hardware Trojan/Covert Channel Detector | F396, F110 | Defensive AI-based detection of hidden hardware-based covert channels |

### 5.36g Research Sweep Findings — Batch 6 (F126-F150: Location, Wi-Fi/BLE, LoRa, SIM)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F721 | GPS Spoofing Detector (IMU Cross-Validation) | F111, F126 | Kalman-filter cross-check of GPS vs. accelerometer/gyro dead-reckoning, a real published technique; new — detects spoofing done TO the user, distinct from F126's own deliberate spoofing |
| F722 | Signal Quality (C/N0) Spoofing Indicator | F108 | Confirmed feasible on low-cost consumer receivers, not just specialized hardware |
| F723 | Crowd-Sourced GPS Spoofing/Jamming Map | F231 | Established real technique, direct extension of existing crowd-threat pattern |

### 5.36h Research Sweep Findings — Batch 7 (F151-F175: Messaging/SMS, notification/clipboard, browser fingerprinting)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F724 | RCS Fallback-to-SMS Silent Downgrade Detector | F153, F623 | Real 2026 gap — RCS silently falls back to unencrypted SMS on unsupported devices |
| F725 | MLS Protocol for Group Messaging | F419, F107, F134 | Real IETF standard, now used by RCS itself, architected for scalable group E2EE |
| F726 | JA4 Fingerprint Rotation (successor to JA3) | F699 | Confirmed current state-of-the-art TLS fingerprinting, invisible to extensions |
| F727 | GPU-Uniform Rendering Defense (WebGL/Canvas) | F367 | Real published technique, normalizes GLSL execution rather than just blocking |
| F728 | Privacy Budget Enforcement | F168 | Real proposed mechanism, caps cumulative fingerprint surface per site |
| F729 | Sensor Spoofing Noise-Injection Hardening | F172 | HONEST LIMIT: published attacks defeat naive noise injection on mobile sensors; needs stronger approach |

### 5.36i Research Sweep Findings — Batch 8 (F176-F200: Identifier rotation, UX, blocklist engine, network auditing)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F730 | Modified Bloom Filter with Anomaly Learning Mode | F195 | Two-mode (learning/detection) filter learns normal unrecognized-hostname ratio, catches DNS tunneling |
| F731 | Multi-Layered ML ARP Spoofing Detection | F197 | 2025 framework for resource-constrained devices, adapts to changing topology |
| F732 | False-Positive-Aware ARP Alert Tuning | F197 | Confirmed best practice: verify gateway MAC + require repeated changes before alerting |
| F733 | Router-Level DAI/DHCP Snooping Guided Setup | F201, F203 | Proven industry-standard defense sits at the router, scales better than client-only |
| F734 | Client-Side Neural Zero-Day Domain Classifier | F10, F195 | Confirmed real industry shift beyond static blocklist matching |

### 5.36j Research Sweep Findings — Batch 9 (F201-F225: Router/QoS, Infrastructure, Multi-device DNS)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F735 | Outbound-Only Rendezvous for CGNAT Traversal | F90, F292 | Closes a real gap — naive P2P fails on CGNAT/mobile networks; outbound-only connections work everywhere |
| F736 | Relay Fallback for Mesh | F292-F296 | Confirmed real telemetry: ~5% of connections need relay fallback even in mature mesh systems |
| F737 | User-Designated Peer Relay Nodes | F90 | Real feature, ~150ms latency reduction, 12.5x throughput increase when user dedicates their own relay |
| F738 | Headscale Integration (4.18 registry) | F90, F93 | Real, open-source, self-hostable coordination server, fits D7 directly |
| F739 | Coordination Integrity (Tailnet-Lock-style) | F90, F232 | Cryptographic protection — compromised coordination server still can't inject malicious nodes |
| F740 | Mesh-Wide Friendly Naming (MagicDNS-style) | F90, F107 | Automatic meaningful hostnames across the mesh, no manual setup |

### 5.36k Research Sweep Findings — Batch 10 (F226-F250: DNS profiles, Update diffing, Bandwidth, Radio, Travel)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F741 | Method-Level Update Diffing (ApkDiffer-style) | F228 | Real published tool, method-level diffing auto-classifies privacy-sensitive change as legit vs. risky |
| F742 | Supply Chain Compromise Detection Strategy (MITRE DET0628) | F228, F396 | Formal MITRE strategy incl. "AllowedVersionChangeWindow" grace period to cut false positives |
| F743 | Saavdhan-Pattern Offline Scam APK Detection (4.18 registry) | F06, F61 | Real, active, fully-offline open-source app, sideload-lure scam campaign detection |
| F744 | Reachability-Based Vulnerability Scanning | F608 | Verifies vulnerable code is actually reachable/used, not just theoretically present |
| F745 | Hardcoded Secret/Endpoint Exposure Scanner | new audit category | Confirmed real 2026 mobile threat: hardcoded keys, undocumented endpoints in shipped code |

### 5.36l Research Sweep Findings — Batch 11 (F251-F275: Smart TV/Cast, Accessibility abuse, USB, Reproducible builds)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F746 | Sigstore-Based Signing and Transparency Log | F266, F267 | Keyless signing, Merkle-tree transparency log, current industry standard |
| F747 | rbtlog Integration (4.18 registry) | F267 | Real, active, open-source Android-specific transparency log, already proven by IzzyOnDroid |
| F748 | SLSA Compliance Level Target | F266 | Formal, citable build-integrity standard |
| F749 | F-Droid-Pattern Unsigned-Artifact Comparison | F266 | Precise real technique — compare unsigned contents, not signatures |
| F750 | Android Intrusion Logging Integration | F592 | Real Android 16+ feature built with Amnesty International + Reporters Without Borders |
| F751 | Native USB Protection Integration | F260, F382 | Live on Pixel Android 16+, expanding |
| F752 | *(see section 11.7)* Android OS Build Verification | Parking Lot context | Android 17 detects "modified builds that appear legitimate" — new obstacle for custom ROM path |

### 5.36m Research Sweep Findings — Batch 12 (F276-F300: Privacy education, Digital legacy, Low-end/fleet, Donations)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F753 | TEE Inter-Application Isolation (OP-TEE-style) | F73, F541 | Strict inter-TA isolation, secrets don't bleed across trust boundaries even within the secure world |
| F754 | Native Lock-Screen Sensitive Content Hiding Integration | F154 | Android 16 AI auto-hides OTPs/banking alerts on lock screen contextually |
| F755 | Contextual Adaptive Permission Enforcement | F100 | Real native adaptive model vs. static presets only |
| F756 | Gamified Privacy Literacy Formats | F268 | Confirmed academic precedent: escape rooms, card games, location-privacy game tools |
| F757 | Native Temporary Precise Location Sharing Integration | F127, F304 | Android 17 native time-limited precise sharing button |
| F758 | MPC/Threshold Key Splitting for Digital Legacy | F270, F684 | Mature technique, splits inheritance access across multiple trusted people, no single point of failure |

### 5.36n Research Sweep Findings — Batch 13 (F301-F325: PTT end, Parking Lot, AI Assistant start)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F759 | Gemma 4 E2B as Reference Model (4.18 registry) | F323 | Real, currently-shipping (Jun 2026), <1GB mobile format, native tool calling, runs on phones |
| F760 | 4-bit/2-bit Quantization Pipeline (AWQ/SmoothQuant/SpinQuant/AQLM) | F323 | Real named techniques, ~95% quality retention at 4-bit, viable to 2-bit |
| F761 | NPU-Accelerated Inference | F323 | Real 2026 path via XNNPACK/Hexagon NPU, not pure CPU |
| F762 | llama.cpp/MLC-LLM Engine Integration (4.18 registry) | F323 | Real, open-source, actively maintained on-device inference engines |
| F763 | Cloud-Assisted Continual Learning Pattern (Delta-style) | F324 | Real published architecture validating the small-local/large-cloud design |
| F764 | LLM-Powered App Task Automation (AutoDroid/MobileGPT-style) | F325, F326 | Real, named, published research direction for app-level task automation |

### 5.36o Research Sweep Findings — Batch 14 (F326-F350: Remote Access, Wi-Fi Repeater, Protocol Interop, BLE Audio)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F765 | WHIP/WHEP Standard Ingest/Egress | F328, F331 | Real standardized WebRTC ingest/egress, as easy as RTMP |
| F766 | WebCodecs-Based Hardware-Accelerated Pipeline | F329 | Real W3C standard, frame-level encoder/decoder access |
| F767 | Media over QUIC (MoQ), tracked | F357, F93 | Confirmed still 2026+ for production readiness, revisit later |
| F768 | OvenMediaEngine Integration (4.18 registry) | F93 | Real, active, open-source sub-second WebRTC + LL-HLS server |
| F769 | BLE Timing-Offset Low-Latency Audio (CROS/BiCROS-style) | F349 | Real patented technique solving BLE's latency limit for hearing aids |
| F770 | Matter 1.4/Thread with Mandatory Device Attestation | F344, F446 | Dominant modern standard, mandatory DAC cryptographic device proof since 2025 |

### 5.36p Research Sweep Findings — Batch 15 (F351-F375: Calendar/Email, BLE Audio, GPS/Radio, OBD-II, Lightning, Hidden Mechanisms)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F771 | Taproot + PTLC Lightning Channels | F360, F282 | Real, production (2026), channel transactions indistinguishable from ordinary spends; PTLCs fix HTLC's cross-hop correlation leak |
| F772 | BOLT12 Onion-Messaging Offers | F282 | Real LND 0.21 groundwork for privacy-preserving reusable offers |
| F773 | Vehicle OBD-II Security Auditor | F359 | New capability — checks for documented real weakness classes (hardcoded creds, exposed CAN bus, open debug ports) |
| F774 | Connected Car Ad-Tech Exposure (concrete citation) | F381 | Real Oct 2026 study: 19/21 vehicles, Tesla Model 3 contacted 34 tracking domains; opt-out often disables safety features |
| F775 | LINDDUN Privacy Threat Modeling | F638 | Real, validated academic framework for systematic privacy-threat correlation |

### 5.36q Research Sweep Findings — Batch 16 (F376-F400: Hidden Mechanism Disclosure end, Motion-as-Mouse start)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F776 | Far-Field EM "Screaming Channel" Update | F391 | 2026 research eliminates proximity constraint of traditional TEMPEST attacks |
| F777 | Touchscreen EM Handwriting Reconstruction | F391, F393 | Real May 2026 finding, phone-specific, corrects "acoustic ineffective on phones" framing |
| F778 | RF Backscatter Through-Wall Inference (RadKey/RadEar-style) | F398 | Real 2026 LLM-guided through-wall keystroke/voice inference |
| F779 | Acoustic Side-Channel Standards Gap (honest limit) | F393 | Even NSA TEMPEST + FIPS 140-3 don't fully cover this |
| F780 | ML-Based Acoustic Capture Detector (CoAtNet-style) | F393 | Real Jan 2026 architecture, used defensively as an actual detector |

### 5.36r Research Sweep Findings — Batch 17 (F401-F425: Motion-Mouse end, Five Lenses, Browser-extension ports, Encryption)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F781 | Manifest V3 Immunity (major structural validation) | F10, F195, D1 | Confirmed: full uBlock Origin removed from Chrome Web Store Aug 2026; SERF's DNS/VPN-layer filtering is structurally outside Manifest V3's restrictions entirely |
| F782 | Undetectable-by-Design Blocking | F10, F17 | No browser-extension artifact exists for site detection scripts to find |
| F783 | In-App Browser Container Tabs | F412, F168 | Firefox-exclusive feature confirmed unavailable on Chrome/Chromium — real differentiator if SERF builds its own browser |

### 5.36s Research Sweep Findings — Batch 18 (F426-F450: Self-test, Firewall, P2P safety, Decoy/Identity, Smart home)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F784 | Stochastic Trust Tiers (ark-wot-style) | F232, F712 | Real Oct 2026 spec: out-degree normalization, bounded distance, numeric trust tiers, CRDT storage |
| F785 | Hybrid Attribute+Reputation Access Control | F232, F432 | Real 2026 paper, 96% TPR / 97% accuracy against Sybil attacks |
| F786 | Network-Latency Sybil Signal | F232 | Lightweight, real, hard-to-fake consistent latency across fake identities |
| F787 | Decentralized Federated Trust Detection | F436, F232 | Real framework — federated learning polices its own trust graph |

### 5.36t Research Sweep Findings — Batch 19 (F451-F475: Everyday life end, BLE fuzzer, Sensor repurposing, Professional suite start)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F788 | YARA-X Migration (registry update) | F474, F643 | Classic YARA confirmed succeeded by stable YARA-X |
| F789 | (deleted) | — | — |
| F790 | (deleted) | — | — |
| F791 | Precise Geolocation Sale Ban (Oregon) | F127, F687 | Real 2026 state-level ban, concrete legal precedent |
| F792 | (deleted) | — | — |

### 5.36u Research Sweep Findings — Batch 20 (F476-F500: Professional/Enterprise suite tail)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F793 | eBPF Runtime Integrity Attestation (critical fix) | F489 | Real 2026 MITRE framework: eBPF observability itself can be deceived if compromised, needs its own attestation layer |
| F794 | Tetragon-Style Policy-Based eBPF Security | F489 | Real, production-proven implementation reference, CRD-based policy |
| F795 | MITRE ATT&CK v19 / Current-Version Targeting | F483 | Confirmed current version, ongoing update tracking required |
| F796 | 2026 SBOM Minimum Elements Compliance (CISA, RFC 9557) | F484 | Current official standard, new automation support requirements |

### 5.36v Research Sweep Findings — Batch 21 (F501-F525: Encrypted partitions, Firewall UX, Resource-saving, Modular delivery)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F797 | Two-Layer Signed Delta Patch Verification | F519, F520 | Real 2026 pattern: per-chunk + release-level signatures, canary dry-apply verification |
| F798 | Courgette-Style Disassembly-Based Diffing | F519 | Confirmed real algorithm, 0.76% vs 6.7% diff size vs. plain bsdiff |
| F799 | VeraCrypt C/C++ SDK Integration | F500, F573 | Real, active, official SDK for programmatic container creation |

### 5.36w Research Sweep Findings — Batch 22 (F526-F550: Certificate management, AOSP capabilities, Network manager)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F800 | Static CT API Architecture (Sunlight-style) | F477 | Current CT architecture; Let's Encrypt fully transitioned by Feb 2026 |
| F801 | Proactive Lookalike-Domain CT Watch | F385, F45 | Real technique: catches phishing infrastructure before it's used |
| F802 | Automatic Push Alerting | F477 | Confirmed real gap — existing free CT tools are pull-only, no auto-alerting |
| F803 | CAA DNS Record Guidance | F93 | Complementary prevention layer for self-hosted services |

### 5.36x Research Sweep Findings — Batch 23 (F551-F575: Hardened input/biometric, Network diagnostics, 7-repo cluster)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F804 | GrapheneOS Non-Pixel Expansion (CORRECTION — see 11.8) | Parking Lot context | March 2026: Motorola partnership + Snapdragon 8 Elite, ends Pixel-only limitation stated earlier |
| F805 | Two-Factor Biometric+PIN Unlock (GrapheneOS pattern) | F73, F558 | Real pattern: strong passphrase primary, biometric+PIN secondary |
| F806 | 5-Attempt Hard Lockout | F558, F73 | Concrete security parameter, not 20 attempts |
| F807 | Filename-Length Metadata Padding | F618 | Real fix: pad encrypted filenames to reduce length-based leakage |
| F808 | Upstream Coarse-Location Field Leak Awareness | F127, F757 | Real Android flaw — altitude/accuracy leaking without precise permission |
| F809 | Android Developer Verification Confirmed Dates (MAJOR — see 11.8) | Mission context | Sept 2026 enforcement begins (BR/ID/SG/TH), global 2027; Keep Android Open campaign, 60+ orgs |
| F810 | GrapheneOS Age-Verification Resistance Precedent | Mission context | Real 2026 precedent for resisting compliance pressure |

### 5.36y Research Sweep Findings — Batch 24 (F576-F600: Transparency reporting, Dual-boot, Civic/Community, Right-to-repair, Accessibility)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F811 | Region-Aware Right-to-Repair Assistant | F595, F687 | Real, current: 13 US states + EU Directive mandatory since July 2026 |
| F812 | Parts-Pairing Lock Explainer | F595 | Real, current, increasingly-banned repair obstacle |
| F813 | FTC v. Deere Precedent | F595 | Real July 2026 enforcement settlement |
| F814 | Warrant Canary Honest Limit (correction) | F115, F267, F577 | Real legal uncertainty, documented failures (Riseup), outlawed in Australia |

### 5.36z Research Sweep Findings — Batch 25 (F601-F625: Legal hacker toolkit, Responsible disclosure, Dependency scanning)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F815 | ISO 29147 Structured Disclosure Templates | F604 | Real formal international CVD standard |
| F816 | Open Bug Bounty Integration | F604 | Real, free, non-profit, 272,020+ fixed vulns, ISO 29147-based |
| F817 | Passkey-Primary Vault Default | F73, F558 | 15B+ accounts enabled, 93% vs 63% success rate, 81% fewer help-desk incidents |
| F818 | Phishable-Fallback Detector | F606 | Real gap: 57% of orgs still rely on phishable methods despite passkey deployment |

### 5.36aa Research Sweep Findings — Batch 26 (F626-F650: Legacy protocols, Outside-the-box privacy, Legal hacker toolkit, Personal learning)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F819 | OSINT-BIBLE Tool Catalog (web-check, social-analyzer, flowsint, OpenOSINT) | F646 | Real, active 2026 catalog of named, current OSINT tools |
| F820 | Reaper Social | F629, F646 | Real Mastodon/Fediverse-specific investigation tool |
| F821 | ActivityPub 1.1 + Fedify/apkit Targeting | F629 | Real 2026 W3C-standardized version, current implementation libraries |
| F822 | Differential Privacy UX Honest Limit | F669 | Real documented UX problem — epsilon tuning is hard even for experts |
| F823 | DP Implementation Auditing (Re:cord-play-style) | F669 | Real grey-box auditing — DP theory doesn't always match practice |

### 5.36ab Research Sweep Findings — Batch 27 FINAL (F651-F678: Hidden mechanisms end, Full data control, Unified Play spoof, Multi-language)
| ID | Name | Upgrades | Source finding |
|---|---|---|---|
| F824 | DEDA Project Integration (registry) | F658 | Real open-source printer-dot detection/decoding/anonymization tool |
| F825 | Concrete current citation (Jan 2026 FBI case) | F658 | Real, recent printer-dot identification case |
| F826 | Future Printer Tracking Evolution Awareness | F658 | Emerging letter-shape/spacing steganography beyond yellow dots |
| F827 | California DROP Registry Integration | F652, F653, F377 | Real official government data broker registry, mandatory since Jan 2026 |
| F828 | California DELETE Act One-Stop Deletion | F264, F639 | Real official one-stop deletion mechanism, compliance by Aug 2026 |
| F829 | Precise Play Integrity Verdict Targeting | F667 | Confirmed exact verdict structure (accountDetails/appIntegrity/deviceIntegrity) |
| F830 | Play Integrity Fix Module (registry) | F667 | Real existing community tool addressing part of this problem |

### 5.37 Offline Mesh Social Layer: Directory, Forum, Ride-Matching (pure offline, no internet gateway — zero reliance on any single user's data plan)
| ID | Name | Pri | Diff | Cost |
|---|---|---|---|---|
| [37] F847 | Personal Mesh Directory (QR-exchange contacts, store-and-forward delivery regardless of presence) | P2 | 2 | L |
| [37] [GH] F848 | Offline Community Forum (store-and-forward posts, builds on F580/F712) | P2 | 3 | L |
| [37] F849 | Geofenced Category Subscriptions (distance-scoped, per-category on/off) | P2 | 2 | L |
| [37] F850 | Disappearing Post Rules (per-user, per-category: time-after-view, time-after-post, at-event-start) | P2 | 2 | L |
| [37] [GH] F851 | AI-Automated Moderation (local classifier, zero human moderator required, builds on F323/F647) | P2 | 3 | L |
| [37] F852 | Event De-duplication (AI merges duplicate event posts automatically) | P3 | 2 | L |
| [37] F853 | Private Friend-Group Sub-Forums (invite-only, separate from public forum) | P2 | 2 | L |
| [37] F854 | Offline Ride Request Broadcast (mesh-propagated, no internet) | P2 | 3 | L |
| [37] F855 | Driver Availability & ETA Broadcast (offline map routing + mesh position relay) | P2 | 3 | L |
| [37] F856 | Price Negotiation Protocol (structured offer/counter-offer over mesh) | P2 | 2 | L |
| [37] F857 | Verified Profile Linkage for Rides (F159/F847 directory + F232/F784 trust tiers — real-identity safety) | P1 | 2 | L |
| [37] F858 | Offline Trip Safety Record (auto-shares to trusted contact at trip start, ties to F152/F263) | P1 | 2 | L |

**Note on scope:** F844-F846 (routing live internet traffic like ride-hailing-via-gateway or general browsing through other users' mesh connections) were explicitly considered and rejected per the user's own reasoning — relaying one person's internet data for another's real-time use isn't fair to the data-paying user. F854-F858 solve the ride-matching need entirely peer-to-peer instead, no one's data plan involved at any point.

### 5.36 Gap Sweep (found by reviewing the full register for blind spots)
| ID | Name | Gap it closes |
|---|---|---|
| F680 | Third-Party Developer Plugin Marketplace | F213's Module SDK was only ever scoped for *our own* features — nothing lets outside developers build and share new SERF modules the way browser extensions or VSCode plugins work. A real gap given 650+ features already prove the architecture works |
| F681 | Unified Emergency Mode Preset | F238 (blackout), F152 (SMS trigger), F294 (mesh fallback), F244 (offline maps), F103 (dead man's switch) all exist separately — nothing bundles them into one "emergency, activate everything relevant" single action |
| F682 | Voice-Triggered Duress Phrase | F102's duress mode is PIN-based only; a hands-free spoken code phrase covers situations where typing a PIN isn't safe or possible |
| F683 | Kid-as-Primary-User Safe Default Profile | F72/F450 assume a parent configuring a child's phone remotely; nothing covers a simple, safe-by-default profile for a device a child uses directly, age-appropriate without needing a parent's separate setup |
| F684 | Delegated Temporary Access | F270 (Digital Legacy) covers death/incapacitation permanently; nothing covers short-term delegated access (a trusted person temporarily managing the account during a hospital stay, say) with a clean, automatic expiry |
| F685 | Haptic-Only Notification System | F255's accessibility work covers vision/hearing separately; nothing addresses deaf-blind users specifically, who need notification information conveyed through touch patterns alone |
| F686 | Live Cross-Device Policy Sync | F187 syncs settings only, as a one-time export; nothing keeps multiple of a person's own devices continuously in sync on policy changes in real time |
| F687 | Legal-Region Variant Defaults | Consent laws (F591), export rules, and data-retention defaults vary by jurisdiction; nothing auto-adjusts default behavior based on detected region the way a careful legal product would |

---

## 13. Decision Log (why, not just what)
- Rewriting the Go/gVisor core in Rust was considered and rejected: modest gain (no GC pauses), large cost (losing a hardened, battle-tested stack and upstream fixes). Compromise: Go core stays, new modules are Rust (D2).
- Raw TLS replay for offline playback was the user's original framing; rejected as the sole mechanism (fresh keys per session defeat replay), replaced by the layered D3 approach that actually works.
- Piracy-adjacent features were requested and declined (D8), protecting the project's legal standing and its own stated values.
- Root-only "Google isolation" was widened to also support an unrooted path (Shizuku-based, F321), so the feature isn't gated to only the most advanced users.
- A request to describe methods for defeating Verified Boot's signature chain ("cracking," including when reframed as sci-fi/hypothetical) was declined. This is distinct from, and should not be confused with, the legitimate driver-extraction/GSI-building work in section 11, which stays on the legal side of that line by design.
- Crypto mining as a developer-funding mechanism was considered and rejected: phone chips mine at a tiny fraction of dedicated hardware's efficiency, electricity/battery cost almost always exceeds earnings, and "an app that quietly uses phone resources to mine" is the literal definition of cryptojacking — the exact pattern F61 exists to detect elsewhere in this app. F282 (anonymous donations) and F435/F436/F437 (opt-in, user-chosen resource sharing for real causes) are the honest replacements.
- The entire OS-replacement/root cluster (F311–F320, F322) was moved to the Parking Lot (section 11) rather than scheduled into any phase, pending the user's own decision about how much of the project's effort should go toward it versus the broader no-root-friendly feature set.
## 14. PROJECT HANDOFF — Welcome, Future Contributors

This section exists so SERF is never just "here it is, finished, goodbye." It's a standing invitation, written for whoever picks this up next, whether that's the original author months from now, a hobbyist who found the repo, or a security researcher who wants to help harden it.

### 14.1 README.md (public repo, copy this in verbatim when the repo goes live)

> # SERF
> *A free, open-source phone that actually belongs to you.*
>
> **What this does:** makes apps that shouldn't need a connection work offline, strips tracking you never agreed to, de-Googles without needing root, and explains everything it's doing in plain language, never hidden behind jargon. Every feature is off until you switch it on. Nothing here phones home. There's no company behind this trying to profit from your data.
>
> **Why it exists:** this project started from ordinary frustration, apps that wouldn't open without internet, Google Play silently hiding apps and blocking installs, a bank app that stopped working after one update on a phone its owner has every right to control. The honest answer to "why doesn't a good version of this already exist" was: because most software gets built to extract money, not to help. This is the attempt at the other version.
>
> **Where to start:** read `STATE_v2.md`. It's the single living blueprint, every feature planned, every decision made and why, what's built, what's next. Section 0 tells you exactly how to resume work in a single paste. Section 1 is the mission, read it before anything else, it's the part that shouldn't change even as everything else does.
>
> **How to contribute:**
> 1. Read `STATE_v2.md` sections 0, 1, and 10 (Live State) to see exactly where things stand.
> 2. Check section 4.19, Pending Fork Decisions. These are real forks in the road the original project owner chose to decide personally, not something a contributor should resolve unilaterally. If one's still open, leave it open and work on something else.
> 3. Pick a task: section 9's tiers show build order by real difficulty and priority; section 5's 37 numbered categories let you find every idea on a topic you care about with one search.
> 4. Before building anything, search for current advancements and existing open-source projects first (section 4.20). Most of this app is meant to be assembled from excellent existing tools, not reinvented, check section 4.18's registry, then search fresh, every time.
> 5. Follow the Module Contract (section 4.2). It's what keeps 850+ features from turning into unmaintainable spaghetti as more people contribute, every feature is its own independent, removable module.
> 6. Never build anything in section 11 (the Parking Lot) or anything resembling piracy/circumvention tooling (D8, D11's health-data exclusion, and the DMCA boundary in 4.12). These lines are there for real reasons, explained in the decision log (section 13), not arbitrary.
>
> **What kind of help genuinely matters most right now:** real-device testers (especially unusual or budget phones, F243's compatibility map needs you), translators, security reviewers willing to audit the encryption claims, and anyone willing to just pick one single F-number and build it well.
>
> This project is yours to extend. Build something you'd actually want to use.

### 14.2 In-App "About This App" Screen (user-facing, not contributor-facing — plain language)

> **What is this app?**
> This app gives you back control of your own phone. It can make apps work offline that shouldn't need a connection, block tracking you never agreed to, and show you exactly what's happening on your network, explained plainly, not hidden behind technical jargon.
>
> Every feature here is off until you turn it on. Nothing phones home without you knowing. There's no company watching what you do with this, it's free, open source, and built by people who wanted something better than what's in the app stores.
>
> Tap any feature to see what it does and why it exists (look for the "Describe" button). Curious what's coming next, or want to help build it? [link to the public repo].

### 14.3 Standing In-App Education Requirement
This formalizes something already implied by the Five Lenses (4.14) into an explicit rule: **every feature's "Describe" lens must be written for someone with zero technical background**, not just accurately, but genuinely understandably, "what this does, why it matters to you, what happens if you turn it on." A user should never have to leave the app to understand what a feature they're looking at actually does. This is the in-app half of "know what they're downloading"; the How-To Guide Library (section 12) is the out-of-app half, for things beyond what any single feature can explain.

### 14.4 Closing Note
Everything in this document, the mission, the 850+ features, the architecture, the forks still waiting on a decision, was built one honest conversation at a time, starting from real frustration and a wish for something better. It's complete enough to start building. It's also, deliberately, never finished, section 14.1's invitation is as real as anything else in this file. Whoever picks this up next: welcome.

---

## 15. THINGS WE COULD TECHNICALLY DO BUT LEGALLY/ETHICALLY CAN'T (consolidated — never build, regardless of future framing)
Pulled together from across this whole document so the full list lives in one place. Nothing here is a capability gap, each one was specifically considered and declined, for a real, stated reason. If asked again, reframed, or presented hypothetically, the answer stays the same.

| # | Item | Why not | Logged at |
|---|---|---|---|
| 1 | Defeating Verified Boot's cryptographic signature chain ("cracking" root/bootloader locks) | DMCA 1201 circumvention (distinct from legal driver extraction/interoperability, which IS fine) | Section 11.4, 4.12 |
| 2 | License/purchase-check removal on other developers' apps, ad-removal inside paid apps specifically to avoid paying developers | Takes money owed to developers; undercuts the project's own stated values | D8 |
| 3 | Actually operating a cell-site simulator/IMSI catcher (vs. detecting one, which is fine) | Federally restricted equipment, separate from jamming law | Section 4.12 |
| 4 | RF jamming or active transmission beyond certified hardware limits, in any form | FCC violation | D9, 4.12 |
| 5 | Intercepting someone else's communications without consent (vs. protecting your own traffic) | Wiretap Act (ECPA) | 4.12 |
| 6 | Network scanning/auditing tools used against networks the user doesn't own or administer | CFAA | D6, 4.12 |
| 7 | Operating unlicensed money transmission (pooled user funds, cash-out mechanics) | Federal crime (18 U.S.C. § 1960) absent real licensing | Conversation record (MoneyApp discussion) |
| 8 | Reverse-engineering Google's actual GMS code and redistributing it (vs. microG's clean-room reimplementation, which IS fine) | GMS's own license explicitly prohibits this; DMCA interoperability line | Section 11.4 |
| 9 | Tracking/storing personal health, medical, or biometric data | Permanently out of scope by project decision, not a legal requirement — kept here for completeness | D11 |
| 10 | Spoofing IMEI/IMSI to an actual cellular network during registration (vs. spoofing what's reported to apps, which IS fine, F179) | Not cryptographically possible without real stolen/cloned credentials; IMEI spoofing to evade blacklists is illegal in multiple countries | This session |
| 11 | BGP hijacking or any routing-table falsification to bypass internet kill switches | Not technically possible from a phone; a serious federal crime where it is possible | This session, F386 |
| 12 | Forcing globally "unmetered" capability flags to bypass carrier data billing/caps beyond what's paid for | Takes service from the carrier that wasn't paid for — same principle as item 2 | This session |
| 13 | Blocking cellular paging without extreme, carefully-scoped caution | Paging is how incoming calls/texts/emergency alerts reach the device; careless blocking breaks basic phone function | This session |
| 14 | Building or maintaining our own forked Chromium browser product | Not illegal — listed here as a reminder it's a Fork Decision (4.19, Fork 4), not a default yes | Section 4.19 |
| 15 | Altering unencrypted carrier/service protocol data in transit specifically to deceive a carrier's or other third party's billing, provisioning, or identification systems | Unauthorized computer access / service fraud in most jurisdictions — the real dividing line is *whose system gets deceived*, not encryption or jurisdiction; this is NOT the same kind of "varies by country" item as MAC spoofing, fraud/unauthorized-access law exists in some form almost everywhere | This session |

**15.1 Standing architectural boundary (what keeps nearly everything else in this register legitimate by design):** SERF's traffic interception is, and will only ever be, scoped to the device's own traffic. Altering what the device's own apps/network stack sees or sends is configuring your own property. Nothing in this register intercepts, alters, or affects another person's traffic on a shared network, that line is structural, not incidental, and it's the actual reason items like MAC/IMEI-to-apps spoofing (fine) are categorically different from item 15 above (not fine) even though both happen at the same technical layer.

**15.3 The real dividing principle, stated once, for every future session:** general-purpose capability over the user's OWN device and OWN data stream is never restricted, nannied, or technically gated, F664's Live Intercept and Edit and the whole universal override engine (F662/F947) already prove this throughout the register, and that stays exactly as open as it is. What stays declined is narrower than "anything technically possible": only items whose designed function *requires* a non-consenting third party to exist for the tool to work at all (items 3, 4, 5, 8's redistribution half, 2) — a disclaimer between SERF and the user cannot supply that missing third party's consent, so it doesn't change the legal reality, this is a different category from "a general tool someone could misuse," which this project has never restricted. Item 13 (paging block) and item 14 (Chromium fork) were miscategorized in the original list — moved below the table; neither is a legal restriction.

**F988 Explicit Legal Disclaimer / Acknowledgment.** A real, clear, in-app disclaimer covering the genuinely dual-use tools this register is full of: the user is responsible for how they use general-purpose capability over their own device and data; SERF is not responsible for a user's intent, ignorance, or legal consequences from choosing to use that capability. Shown once, plainly, not as a nag.

**Miscategorized items, moved out of the legal-restriction list:**
- Item 13 (blocking cellular paging) is an engineering risk, not a legal one — buildable, with a clear warning that it may break incoming calls/texts/emergency alerts, user's own risk to accept.
- Item 14 (Chromium fork) is a resourcing decision (Fork 4, section 4.19), not a legal restriction.

**15.2 Revisit note:** Item 15 is logged as "avoid for now" rather than permanently ruled out like items 1-9, specifically because the user asked to track it for reconsideration if the legal landscape changes. Unlike items 1-9 (which rest on settled, unlikely-to-change legal principles), item 15's status could shift with future telecom/computer-fraud law changes in specific jurisdictions — worth a fresh check via the mandatory pre-build research step (4.20) before ever revisiting, not assumed still accurate.

---

