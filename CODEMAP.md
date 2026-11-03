# CODEMAP — T0.4 working document (Qwen branch / SERF fork)
First-pass map of the RethinkDNS codebase, per STATE_v2 NEXT=T0.4. Update as we go; every entry verified against actual files (2026-11-04).

## 1. Big picture
- **Two-language architecture:** Kotlin app layer (`app/`, ~162k LOC in `app/src/main/java`) + Go networking core (bundled via `external/` → compiled into a native lib, uses gVisor/netstack — verify exact path when we touch it).
- Single Gradle module `:app` plus library deps (WireGuard tunnel lib, material, room, etc.). No multi-module plugin system yet — F213's module contract is our job to introduce.

## 2. Package layout (`app/src/main/java/com/celzero/bravedns/`)
| Package | Files | LOC | Role |
|---|---|---|---|
| `ui` | 142 | 65k | All Activities/fragments. Home screen = `ui/HomeScreenActivity.kt`. `ui/activity/*` = settings screens |
| `service` | 42 | 21.9k | The engine: `BraveVPNService.kt` (VpnService host), `TunDnsManager.kt` (tun interface + Go bridge), `ConnectionMonitor.kt`, `FirewallRuleset.kt`, `DomainRulesManager.kt`, `ConnectionSimulator.kt` (offline mode!), proxy handlers |
| `database` | 109 | 14.4k | Room entities/DAOs: apps, connections, dns logs, firewall rules, proxy apps |
| `adapter` | 47 | 14.4k | RecyclerView adapters (legacy UI plumbing) |
| `util` | 44 | 10.8k | Helpers, shared prefs (`PreferenceUtil.kt` = giant key-value hub; watch Module Contract 4.3 here) |
| `rpnproxy` | 7 | 9.1k | Remote proxy (Tor/rpn) integration |
| `viewmodel` | 39 | 5.2k | MVVM view models |
| `net` | 4 | 4.4k | OkHttp/DNS-over-HTTPS client glue |
| `scheduler` | 15 | 2.8k | WorkManager jobs (blocklist updates etc.) |
| `wireguard` | 12 | 2.3k | WG tunnel config/UI |
| `data` | 15 | 2.2k | Model classes |
| others | — | — | `backup`, `download`, `receiver`, `iab` (purchases — candidate for removal for free-everything goal), `sponsor`, `glide`, `animation`, `tunnel/TunDnsManager` |

## 3. Build variants (flavors × buildTypes) — from `app/build.gradle(.kts)`
- **Flavors:** `fdroid` (no Google libs), `website` (self-hosted update check), `play` (IAP/billing), `alpha`.
- **Dimensions:** `full` vs `tv` (Android TV variant). CI builds `assembleFdroidFullDebug` (fallback used because no google-services secret) → APK artifact `serf-debug-apk-*`.
- Manifest merger: `src/main/AndroidManifest.xml` has NO activities; activities declared in flavor manifests (`src/full/AndroidManifest.xml` has launcher aliases incl. `.ui.HomeScreenActivity` behind `LauncherAliasHome`, currently `enabled="false"` — AppLock alias is the active launcher). Relevant for D10 home-screen rework.

## 4. MVP-critical hooks (v0.1 / F01 Fake-Online)
- `service/ConnectionSimulator.kt` — upstream already simulates connectivity state; inspect for generate_204 handling.
- Probe responder targets: Android connectivity checks hit `clients3.google.com/generate_204` + captive-portal URLs; find where DNS answers are synthesized in TunDnsManager/Go core (`devd`/`dns` packages on the Go side — next deep-dive).
- Custom rules/local DNS: `database/` has custom-ip/custom-if rules tables usable for junk-domain stubs (D3 layer 4).

## 5. Known open questions (fill in during refinement)
- [ ] Exact Go-core entry points for synthetic DNS responses (probe responder insertion point).
- [ ] Where `generate_204`/captive-portal HTTP HEAD requests terminate (VPN tun → Go netstack → ?).
- [ ] `PreferenceUtil` key inventory before schema versioning (F214).
- [ ] Play-store-only code paths to strip (`iab`, `sponsor`, firebase) for fdroid-clean SERF build.

## 6. CI facts
- Workflow: `.github/workflows/android.yml` ("🫣 Android CI"), triggers on push/PR to main+Qwen and `workflow_dispatch`; uploads APK artifact (added 2026-11-04).
- First green run: #2, id 41738504630, commit ab6a57c, ~9 min. Fix that made it pass: Gradle/Kotlin daemon memory raised (upstream 768m OOM'd on the runner).
- Fork caveat: pushes don't auto-run until one-time approval on the Actions tab; dispatch via API works regardless.
