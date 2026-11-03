import { SerfModule, ActiveProfile } from '../types/serf';

export const INITIAL_MODULES: SerfModule[] = [
  {
    id: 'F01',
    name: 'Fake-Online Simulator',
    category: 'Core Engine',
    priority: 'P1',
    difficulty: 2,
    cost: 'L',
    description: 'Tricks apps requiring internet into working completely offline via synthetic captive portal and probe responses.',
    oneParagraphDoc: 'Intercepts OS and in-app network availability checks (e.g. generate_204, connectivitycheck.gstatic.com) returning immediate synthetic HTTP 204 status. Breaks single-player games and utilities that refuse to launch offline.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Apps with forced online gates will freeze or display "No Connection" error dialogs.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.3, batteryPerHourPercent: 0.05, ramMb: 8, dataSavedKb: 420 },
    fiveLenses: {
      monitor: {
        title: 'Active Probe Responders',
        metrics: [
          { label: 'Probes Answered', value: '148', change: '+12 last 5m' },
          { label: 'Avg Latency', value: '1.2ms', change: '-0.3ms' },
          { label: 'Synthetic 204s', value: '142' }
        ],
        statusNote: 'Responding to Google, Apple, and captive portal probes on loopback.'
      },
      detect: {
        threatsFound: 7,
        lastDetected: 'Duolingo attempted background analytics ping while in Airplane mode',
        details: [
          'connectivitycheck.gstatic.com: Intercepted & faked HTTP 204',
          'captive.apple.com: Intercepted & faked Success',
          'app-measurement.com: Dropped instantly with TCP RST'
        ]
      },
      explain: {
        summary: 'Synthetic Network Presence Layer',
        mechanism: 'Bypasses hardware radio checks by asserting TRANSPORT_WIFI and TRANSPORT_CELLULAR on the VPN virtual interface, then answering OS HTTP probes locally without touching cellular towers.',
        risksMitigated: 'Prevents artificial software lockouts and stops tracking payloads attempting to leak local telemetry when a connection is restored.'
      },
      describe: {
        plainSummary: 'Makes apps that shouldn’t need internet work when you are offline or in Airplane Mode.',
        whyItMatters: 'Many games and tools lock you out with "No Internet Connection" popups just to show ads. This tricks them into thinking the network is fine so you can keep using what you paid for.',
        consequencesIfEnabled: 'Single-player games, offline note-takers, and e-readers open without connection prompts. True online features (like multiplayer) will gracefully notify you that the game server is unreachable.'
      },
      suggest: {
        recommendation: 'Keep enabled. Pairs with F04 Junk-Domain stubs for immediate launch without timeouts.',
        authorityLevel: 2,
        actionLabel: 'Verify Probe Responder',
        reversible: true
      },
      history: [
        { timestamp: Date.now() - 3600000, action: 'Engine Started', impact: 'Initialized local loopback probe hook' },
        { timestamp: Date.now() - 1200000, action: 'Offline App Launch', impact: 'Simulated HTTP 204 for Subway Surfers' }
      ]
    }
  },
  {
    id: 'F04',
    name: 'Junk-Domain Stub List',
    category: 'Core Engine',
    priority: 'P0',
    difficulty: 1,
    cost: 'L',
    description: 'Fails telemetry requests immediately with synthetic empty responses to eliminate 30-second app freeze timeouts.',
    oneParagraphDoc: 'When an app phones home to an ad network or telemetry endpoint, SERF returns an immediate empty response or TCP RST instead of letting the connection hang waiting for an external server.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Apps might hang on black loading screens for 15-30 seconds waiting for blocked trackers to time out.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.02, ramMb: 4, dataSavedKb: 890 },
    fiveLenses: {
      monitor: {
        title: 'Stub Interception',
        metrics: [
          { label: 'Stubs Active', value: '42,910', change: '+120 community' },
          { label: 'Timeouts Averted', value: '312', change: 'avg 14.8s saved' }
        ],
        statusNote: 'Fast-fail engine responding in under 0.8ms.'
      },
      detect: {
        threatsFound: 24,
        details: ['UnityAds SDK: Handled with empty config JSON', 'Facebook Graph SDK: Returned instant TLS reset']
      },
      explain: {
        summary: 'Instant Response Synthesizer',
        mechanism: 'Maps known ad and tracker endpoints to pre-compiled static empty payloads ({ "status": 200, "ads": [] }) rather than generic packet drops.',
        risksMitigated: 'Eliminates UI freeze cycles and reduces battery drain caused by endless retry loops.'
      },
      describe: {
        plainSummary: 'Stops apps from freezing while they search for ads.',
        whyItMatters: 'When ad-blockers simply drop connections into a black hole, poorly coded apps freeze for 30 seconds waiting for an answer. This gives them an instant blank answer so the app keeps moving.',
        consequencesIfEnabled: 'Apps open up to 4x faster when ad-blocked. No more spin-wheels on startup.'
      },
      suggest: {
        recommendation: 'Enable fast-fail on all background game launchers.',
        authorityLevel: 2,
        actionLabel: 'Update Stub Definitions',
        reversible: true
      }
    }
  },
  {
    id: 'F10',
    name: 'uBlock-Grade Firewall',
    category: 'Firewall & Blocking',
    priority: 'P1',
    difficulty: 2,
    cost: 'L',
    description: 'System-wide network filter using uBlock Origin rule syntax, operating outside Manifest V3 browser constraints.',
    oneParagraphDoc: 'Applies full cosmetic and network-level filtering across all apps and browsers. Evaluates rules locally in Go/gVisor tun2socks with zero cloud dependencies.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Unchecked trackers and third-party advertising SDKs will transmit telemetry freely.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.8, batteryPerHourPercent: 0.1, ramMb: 18, dataSavedKb: 4820 },
    fiveLenses: {
      monitor: {
        title: 'Filtering Engine',
        metrics: [
          { label: 'Rules Loaded', value: '284,520' },
          { label: 'Blocked Outbound', value: '1,429', change: '+84 today' },
          { label: 'Pass-through', value: '8,210' }
        ],
        statusNote: 'Operating via compiled radix tree in sub-millisecond evaluation.'
      },
      detect: {
        threatsFound: 1429,
        lastDetected: 'TikTok SDK pixel beacon from Calculator app',
        details: [
          'graph.instagram.com: Blocked (Rule: EasyPrivacy)',
          'telemetry.applovin.com: Blocked (Rule: Peter Lowe List)',
          'adservice.google.com: Blocked (Rule: OISD Full)'
        ]
      },
      explain: {
        summary: 'Manifest V3-Immune Network Firewall',
        mechanism: 'Operates at OSI Layers 3/4 and Layer 7 DNS. Inspects SNI and DNS records before socket creation. Because it runs at the OS VPN level, browser extension limitations do not apply.',
        risksMitigated: 'Prevents cross-app profiling, fingerprint harvesting, and covert data exfiltration.'
      },
      describe: {
        plainSummary: 'Blocks ads and trackers inside every app on your phone, not just in your browser.',
        whyItMatters: 'Web browsers recently crippled ad blockers with Manifest V3. Because SERF sits at the phone’s master network valve, it blocks trackers inside games, social apps, news apps, and browsers alike.',
        consequencesIfEnabled: 'Saves 30% to 50% mobile data and prolongs battery life by cutting out junk downloads.'
      },
      suggest: {
        recommendation: 'Keep Balanced list bundle active.',
        authorityLevel: 2,
        actionLabel: 'Inspect Blocked Streams',
        reversible: true
      }
    }
  },
  {
    id: 'F675',
    name: 'Traffic Interpreter',
    category: 'Tools & Diagnostics',
    priority: 'P1',
    difficulty: 3,
    cost: 'L',
    description: 'Flagship unifying UI: identify -> explain in plain English -> test-block -> choose (block, allow, or fake data).',
    oneParagraphDoc: 'Translates cryptographic domain names and background network requests into human-readable explanations of which company owns the server and what data is being transmitted.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'You will only see raw cryptic domain names like "e7492.dscg.akamaiedge.net" without context.',
    enabled: true,
    defaultTileSize: '4x4',
    currentTileSize: '4x4',
    status: 'active',
    resourceCost: { cpuPercent: 0.6, batteryPerHourPercent: 0.08, ramMb: 14, dataSavedKb: 120 },
    fiveLenses: {
      monitor: {
        title: 'Live Traffic Flow',
        metrics: [
          { label: 'Live Connections', value: '9', change: '3 encrypted' },
          { label: 'Identified Owners', value: '98.4%' },
          { label: 'Under Review', value: '2' }
        ],
        statusNote: 'Attributing connections to local Android package UIDs.'
      },
      detect: {
        threatsFound: 4,
        details: [
          'Unknown UDP packet stream to 185.220.101.5 on port 8443',
          'Flashlight app attempting outbound socket to overseas telemetry host'
        ]
      },
      explain: {
        summary: 'Reverse Attribution Engine',
        mechanism: 'Combines Linux netlink socket table, procfs UID lookups, WHOIS database, and reverse-DNS to map raw IP traffic to app package names and parent corporate entities.',
        risksMitigated: 'Exposes stealth data leaks disguised under CDN hostnames.'
      },
      describe: {
        plainSummary: 'Tells you what your apps are doing behind your back in plain, friendly English.',
        whyItMatters: 'Normal network tools show confusing technical codes. The Traffic Interpreter tells you "Your weather app just sent your location to an advertising broker in Ohio" and gives you one button to stop it.',
        consequencesIfEnabled: 'You gain complete visibility and control over every single byte entering or leaving your phone.'
      },
      suggest: {
        recommendation: 'Review the 2 flagged connections from background apps.',
        authorityLevel: 2,
        actionLabel: 'Launch Interpreter',
        reversible: true
      }
    }
  },
  {
    id: 'F662',
    name: 'Universal Field Override',
    category: 'Routing & Privacy',
    priority: 'P1',
    difficulty: 3,
    cost: 'L',
    description: 'Universal outbound field override engine: spoofs MAC, IMEI (to apps), Android ID, Ad ID, and sensors.',
    oneParagraphDoc: 'Intercepts system service queries and network payloads, substituting user-defined or randomized values before third-party code receives the response. Powers F663 Transmission Ledger.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Third-party tracking libraries will read your hardware identifiers and permanently link your activity.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.4, batteryPerHourPercent: 0.04, ramMb: 10, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Active Field Spoofs',
        metrics: [
          { label: 'Fields Overridden', value: '6 / 8' },
          { label: 'Queries Sanitized', value: '419', change: '+38 today' },
          { label: 'Active Mode', value: 'Randomized' }
        ],
        statusNote: 'All reported IDs are synthetic and rotate per session.'
      },
      detect: {
        threatsFound: 18,
        details: [
          'Game SDK attempted to query Settings.Secure.ANDROID_ID',
          'Shopping app queried Wi-Fi BSSID to approximate indoor location'
        ]
      },
      explain: {
        summary: 'Hardware & Identity Shield',
        mechanism: 'Substitutes randomized cryptographic identifiers at runtime. Defeats device fingerprinting without breaking standard Android system framework services.',
        risksMitigated: 'Prevents cross-device correlation and permanent advertising identity assignment.'
      },
      describe: {
        plainSummary: 'Feeds fake information to apps that try to track who you are.',
        whyItMatters: 'Apps create permanent profiles on you by reading your phone serial number, advertising ID, and battery level. This feeds them plausible fake numbers that change every time.',
        consequencesIfEnabled: 'Apps think you are a completely new, anonymous phone every session.'
      },
      suggest: {
        recommendation: 'Enable SensorID noise injection (F1002) to mask factory gyroscope calibration curves.',
        authorityLevel: 2,
        actionLabel: 'Configure Overrides',
        reversible: true
      }
    }
  },
  {
    id: 'F125',
    name: 'DoH/DoT Resolver Switcher',
    category: 'Routing & Privacy',
    priority: 'P1',
    difficulty: 1,
    cost: 'L',
    description: 'One-tap switching between encrypted DNS-over-HTTPS, DNS-over-TLS, and private self-hosted resolvers.',
    oneParagraphDoc: 'Encrypts all DNS queries using TLS or HTTP/2, hiding lookup destinations from local Wi-Fi operators and ISPs. Supports Quad9, Mullvad, Cloudflare, RethinkDNS, and custom endpoints.',
    requiredPermissions: ['android.permission.INTERNET', 'android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'DNS queries will be transmitted in unencrypted plaintext on port 53, visible to anyone on your network.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.2, batteryPerHourPercent: 0.03, ramMb: 6, dataSavedKb: 210 },
    fiveLenses: {
      monitor: {
        title: 'Current Resolver',
        metrics: [
          { label: 'Active Protocol', value: 'DNS-over-HTTPS' },
          { label: 'Resolver', value: 'Quad9 (Encrypted)' },
          { label: 'DNSSEC Validated', value: '100%' }
        ],
        statusNote: 'Zero cleartext DNS queries permitted.'
      },
      detect: {
        threatsFound: 0,
        details: ['All 2,891 queries today encrypted over HTTPS port 443']
      },
      explain: {
        summary: 'Encrypted Domain Resolution',
        mechanism: 'Directs all port 53 UDP/TCP traffic into an internal TLS tunnel, verifying SSL certificates against pinned CA authorities before accepting resolution answers.',
        risksMitigated: 'Eliminates ISP DNS snooping, captive network spoofing, and ISP advertising injection.'
      },
      describe: {
        plainSummary: 'Hides the websites you visit from your internet provider and coffee shop Wi-Fi.',
        whyItMatters: 'Normally, whenever your phone looks up a website, your internet provider writes down the domain name. Encrypted DNS wraps every lookup in a private digital envelope.',
        consequencesIfEnabled: 'Your web lookups are completely private and tamper-proof.'
      },
      suggest: {
        recommendation: 'Current Quad9 resolver has 18ms latency and active malware blocking.',
        authorityLevel: 1,
        reversible: true
      }
    }
  },
  {
    id: 'F212',
    name: 'Developer Console',
    category: 'Developer & Advanced',
    priority: 'P2',
    difficulty: 3,
    cost: 'L',
    description: 'Sandboxed in-app terminal: inspect live packet dumps, run diagnostics, and test Lua/JS filter rules.',
    oneParagraphDoc: 'Direct terminal interface with read and query access to config, logs, and module states. Provides scripting hooks for custom rules without requiring an external computer or ADB cable.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Advanced custom scripting and direct system state debugging are unavailable.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 6, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Console Status',
        metrics: [
          { label: 'Sandbox Mode', value: 'Safe Read-Only' },
          { label: 'Commands Run', value: '38' },
          { label: 'Active Hooks', value: '2 Lua' }
        ],
        statusNote: 'Terminal active on internal virtual shell.'
      },
      detect: { threatsFound: 0, details: ['No privilege violations detected in sandbox'] },
      explain: {
        summary: 'In-App Terminal & Scripting Engine',
        mechanism: 'Runs an isolated scripting environment with strict memory and CPU quotas. Allows inspecting live buffers without elevating to raw root permissions.',
        risksMitigated: 'Eliminates need for dangerous third-party debuggers or external USB tethering for diagnostics.'
      },
      describe: {
        plainSummary: 'A command center for power users and curious builders.',
        whyItMatters: 'Lets you look under the hood, test custom rules, and inspect network traffic in real time without plugging your phone into a PC.',
        consequencesIfEnabled: 'Gives power users immediate access to internal diagnostic tools.'
      },
      suggest: {
        recommendation: 'Type "help" in the console to explore available diagnostic tools.',
        authorityLevel: 1,
        actionLabel: 'Open Console',
        reversible: true
      }
    }
  },
  {
    id: 'F424',
    name: 'Resource Monitor',
    category: 'Tools & Diagnostics',
    priority: 'P0',
    difficulty: 2,
    cost: 'L',
    description: 'Live per-feature CPU, battery, and memory usage ledger, ensuring zero background battery drain.',
    oneParagraphDoc: 'Tracks resource consumption per module. Guarantees that dormant modules cost zero CPU and memory, upholding Module Contract 4.2.15.',
    requiredPermissions: [],
    breaksIfDisabled: 'You will not have visibility into battery usage per privacy filter.',
    enabled: true,
    defaultTileSize: '4x2',
    currentTileSize: '4x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 4, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'System Impact',
        metrics: [
          { label: 'Total CPU', value: '2.4%', change: 'Normal' },
          { label: 'Battery Impact', value: '0.3% / hr', change: 'Excellent' },
          { label: 'RAM Allocated', value: '46 MB' }
        ],
        statusNote: 'All active modules operating inside eco-budget.'
      },
      detect: { threatsFound: 0, details: ['Zero memory leaks detected across module workers'] },
      explain: {
        summary: 'Resource Accounting Layer',
        mechanism: 'Reads Android process memory maps and jiffy counters to attribute CPU cycles to specific module threads.',
        risksMitigated: 'Prevents rogue filters from draining the device battery or causing thermal throttling.'
      },
      describe: {
        plainSummary: 'Shows exactly how much battery and phone power SERF is using.',
        whyItMatters: 'Many VPN apps drain your battery in the background without telling you why. SERF accounts for every percent so you know your phone stays cool and lasts all day.',
        consequencesIfEnabled: 'Helps you make informed choices about which deep-scanning features you want to keep on.'
      },
      suggest: {
        recommendation: 'All modules are well below target power limits.',
        authorityLevel: 1,
        reversible: true
      }
    }
  },
  {
    id: 'F884',
    name: 'Fork Audit & Security Shield',
    category: 'Core Engine',
    priority: 'P0',
    difficulty: 3,
    cost: 'L',
    description: 'Continuous validation against upstream RethinkDNS leak vectors: early boot DNS leaks, captive bypass, and tunnel failover.',
    oneParagraphDoc: 'Audits the Go/gVisor network core for known vulnerability classes: unencrypted transmission, tunnel leakage during radio handover, and UID attribution bypass.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Network transitions (Wi-Fi to LTE) may momentarily leak unencrypted packets before the VPN re-latches.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.2, batteryPerHourPercent: 0.02, ramMb: 6, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Audit Health Check',
        metrics: [
          { label: 'Audit Score', value: '100% Passed' },
          { label: 'Direct Boot Guard', value: 'Active' },
          { label: 'Kill Switch', value: 'Locked' }
        ],
        statusNote: 'Zero packet leaks detected across 4 radio handovers.'
      },
      detect: {
        threatsFound: 0,
        details: [
          'Direct-Boot leak test: Passed (Drop-all rule active prior to user unlock)',
          'IPv6 fallback test: Passed (IPv6 routed into tunnel or dropped)',
          'Captive portal bypass: Defeated by local fake probe responder'
        ]
      },
      explain: {
        summary: 'Continuous Security Regression Verifier',
        mechanism: 'Runs periodic loopback packet injections to ensure packets cannot escape to default interface without passing through the VPN netfilter.',
        risksMitigated: 'Prevents silent IP address disclosure when switching between home Wi-Fi and mobile data.'
      },
      describe: {
        plainSummary: 'Constantly checks that no private data accidentally slips out when you switch networks.',
        whyItMatters: 'When your phone moves from Wi-Fi to cellular data, lesser VPNs drop their shield for a split second, exposing your real location. SERF keeps a zero-leak deadbolt engaged at all times.',
        consequencesIfEnabled: 'Guarantees your real IP address is never revealed, even during network hiccups.'
      },
      suggest: {
        recommendation: 'Run an on-demand leak check to verify current tunnel integrity.',
        authorityLevel: 2,
        actionLabel: 'Run Leak Check',
        reversible: true
      }
    }
  },
  // COMING SOON MODULES (F888 Category per Section 4.11)
  {
    id: 'F578',
    name: 'Reticulum Mesh Transport',
    category: 'Mesh & Social',
    priority: 'P1',
    difficulty: 4,
    cost: 'L',
    description: 'Offline peer-to-peer mesh communications over Wi-Fi, Bluetooth, and LoRa without internet or cellular towers.',
    oneParagraphDoc: 'Integrates the proven Reticulum Network Stack (RNS) and LXMF messaging core for zero-infrastructure encrypted comms.',
    requiredPermissions: ['android.permission.BLUETOOTH_ADVERTISE', 'android.permission.NEARBY_WIFI_DEVICES'],
    breaksIfDisabled: 'Mesh communications require this transport core.',
    enabled: false,
    isComingSoon: true,
    comingSoonReason: 'Tier 11 — Scheduled after Core Engine stabilization. Integrates Reticulum/LXMF open-source core.',
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'disabled',
    resourceCost: { cpuPercent: 0, batteryPerHourPercent: 0, ramMb: 0, dataSavedKb: 0 },
    fiveLenses: {
      monitor: { title: 'Mesh Transport', metrics: [{ label: 'Nodes in Range', value: 'Coming Soon' }], statusNote: 'Specification ready.' },
      detect: { threatsFound: 0, details: [] },
      explain: {
        summary: 'Decentralized Cryptographic Mesh',
        mechanism: 'Uses Ed25519 cryptography and multi-hop store-and-forward relaying over any available physical radio medium.',
        risksMitigated: 'Protects communication during blackouts, censorship, or natural disasters.'
      },
      describe: {
        plainSummary: 'Talk to friends nearby even if the entire internet and cell network is shut down.',
        whyItMatters: 'Uses your phone’s built-in Bluetooth and Wi-Fi to hop messages from phone to phone across an entire town without any phone companies involved.',
        consequencesIfEnabled: 'Enables private, un-censorable neighborhood messaging.'
      },
      suggest: {
        recommendation: 'Track progress in Build Order Tier 11.',
        authorityLevel: 1,
        reversible: true
      }
    }
  },
  {
    id: 'F323',
    name: 'On-Device Assistant "Brain"',
    category: 'Developer & Advanced',
    priority: 'P5',
    difficulty: 4,
    cost: 'L',
    description: 'Small quantized local LLM (Gemma 4 E2B / llama.cpp) running strictly on-device to explain traffic and suggest rules.',
    oneParagraphDoc: 'Runs an offline quantized model on phone NPU. Constrained strictly by Section 4.13 Input Sandboxing and the Authority Ladder (read-only traffic, never autonomous on high-stakes actions).',
    requiredPermissions: [],
    breaksIfDisabled: 'Natural language queries about network traffic will fall back to rule-based explainers.',
    enabled: false,
    isComingSoon: true,
    comingSoonReason: 'Tier 6 — Scheduled for integration via llama.cpp mobile engine and LiteRT-LM format.',
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'disabled',
    resourceCost: { cpuPercent: 0, batteryPerHourPercent: 0, ramMb: 0, dataSavedKb: 0 },
    fiveLenses: {
      monitor: { title: 'Local Brain', metrics: [{ label: 'Model', value: 'Gemma 4 E2B' }], statusNote: 'Local weights unpack on demand.' },
      detect: { threatsFound: 0, details: [] },
      explain: {
        summary: 'Sandboxed Natural Language Engine',
        mechanism: 'Executes inference locally on device NPU/CPU without transmitting prompts or telemetry to any external server.',
        risksMitigated: 'Never sends private traffic logs to third-party cloud AI vendors.'
      },
      describe: {
        plainSummary: 'An intelligent helper that lives inside your phone, not in the cloud.',
        whyItMatters: 'Translates confusing technical network logs into simple explanations and helps you decide what to block, without ever sending your data to Google or OpenAI.',
        consequencesIfEnabled: 'Answers questions about what your phone is doing while keeping 100% of your data on your phone.'
      },
      suggest: {
        recommendation: 'Bound by strict Authority Ladder Step 2: suggests actions, never acts autonomously on high-risk settings.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F848',
    name: 'Offline Community Forum',
    category: 'Mesh & Social',
    priority: 'P2',
    difficulty: 3,
    cost: 'L',
    description: 'Neighborhood bulletin board and discussions synchronized purely peer-to-peer over mesh radios.',
    oneParagraphDoc: 'Store-and-forward bulletin system using CRDT synchronization. Users can post, read announcements, and organize community aid without internet.',
    requiredPermissions: ['android.permission.NEARBY_WIFI_DEVICES'],
    breaksIfDisabled: 'Offline community message boards will be unavailable.',
    enabled: false,
    isComingSoon: true,
    comingSoonReason: 'Tier 10 — Built on top of Tier 8 mesh radio infrastructure.',
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'disabled',
    resourceCost: { cpuPercent: 0, batteryPerHourPercent: 0, ramMb: 0, dataSavedKb: 0 },
    fiveLenses: {
      monitor: { title: 'Offline Forum', metrics: [{ label: 'Local Posts', value: 'Coming Soon' }], statusNote: 'Decentralized CRDT schema.' },
      detect: { threatsFound: 0, details: [] },
      explain: {
        summary: 'Local Peer-to-Peer Newsfeed',
        mechanism: 'Syncs signed cryptographically verified posts whenever two devices pass within Wi-Fi or Bluetooth range.',
        risksMitigated: 'Ensures community communication remains resilient against ISP blackouts.'
      },
      describe: {
        plainSummary: 'A local bulletin board for your neighborhood that doesn’t need Wi-Fi or cellular service.',
        whyItMatters: 'During storms or cellular outages, communities need a way to share updates about water, power, and medical help. This turns phones into a community network.',
        consequencesIfEnabled: 'Lets you read and post neighborhood notices without an internet provider.'
      },
      suggest: {
        recommendation: 'View detailed specification in Section 5.37.',
        authorityLevel: 1,
        reversible: true
      }
    }
  }
];

export const INITIAL_PROFILES: ActiveProfile[] = [
  {
    id: 'profile-default',
    name: 'Standard Daily',
    tagline: 'Balanced protection with Fake-Online and uBlock-grade filtering',
    icon: 'Shield',
    dnsResolver: 'Quad9 (DoH)',
    fakeOnlineActive: true,
    firewallStrictness: 'Balanced',
    torRouting: false,
    randomizeIdentities: true
  },
  {
    id: 'profile-stealth',
    name: 'Stealth & Spoof',
    tagline: 'Full identity rotation, sensor noise injection, and aggressive probe suppression',
    icon: 'EyeOff',
    dnsResolver: 'Mullvad (DoH)',
    fakeOnlineActive: true,
    firewallStrictness: 'Hardened',
    torRouting: false,
    randomizeIdentities: true
  },
  {
    id: 'profile-lockdown',
    name: 'Strict Lockdown',
    tagline: 'Zero-trust whitelist: block all unknown background connections and system telemetry',
    icon: 'Lock',
    dnsResolver: 'Cloudflare Zero-Filter',
    fakeOnlineActive: true,
    firewallStrictness: 'Lockdown',
    torRouting: false,
    randomizeIdentities: true
  },
  {
    id: 'profile-mesh',
    name: 'Air-Gap & Mesh',
    tagline: 'Complete radio silence on cellular; local mesh and offline simulation only',
    icon: 'Radio',
    dnsResolver: 'Local Loopback Stub',
    fakeOnlineActive: true,
    firewallStrictness: 'Lockdown',
    torRouting: true,
    randomizeIdentities: true
  }
];

export const INITIAL_FIELD_OVERRIDES = [
  {
    key: 'mac_address',
    label: 'Hardware MAC Address',
    category: 'Hardware' as const,
    currentRealValue: '4A:72:91:BB:03:FE',
    fakeValue: 'E2:84:11:7C:99:A2',
    active: true,
    queriesCount: 84,
    description: 'Randomized per network BSSID connection (F561 / F958).'
  },
  {
    key: 'imei_to_apps',
    label: 'IMEI (Reported to Apps)',
    category: 'Cellular' as const,
    currentRealValue: '864902049102914',
    fakeValue: '358920109482910',
    active: true,
    queriesCount: 19,
    description: 'Reported to third-party SDKs; does not alter carrier tower handshake (F179).'
  },
  {
    key: 'android_id',
    label: 'Android ID (SSAID)',
    category: 'App Identity' as const,
    currentRealValue: '9f8b201a4e218c90',
    fakeValue: 'c4e901aa77b029f1',
    active: true,
    queriesCount: 142,
    description: 'Rotated per app sandbox to defeat cross-app correlation.'
  },
  {
    key: 'advertising_id',
    label: 'Google Advertising ID (GAID)',
    category: 'App Identity' as const,
    currentRealValue: '38400000-8cf0-11bd-b23e-10b96e40000d',
    fakeValue: '00000000-0000-0000-0000-000000000000',
    active: true,
    queriesCount: 231,
    description: 'Zeroed out completely to neutralize ad-tech tracking pixels.'
  },
  {
    key: 'bluetooth_name',
    label: 'Bluetooth Device Name',
    category: 'Hardware' as const,
    currentRealValue: "Alex's Pixel 9 Pro",
    fakeValue: 'Pixel Phone',
    active: true,
    queriesCount: 12,
    description: 'Strips personal names from BLE advertising broadcasts (F997).'
  },
  {
    key: 'sensor_calibration',
    label: 'SensorID Noise Injection',
    category: 'Environment' as const,
    currentRealValue: 'Bias Δ: +0.00284 m/s²',
    fakeValue: 'Calibrated Gaussian Jitter',
    active: true,
    queriesCount: 57,
    description: 'Masks microscopic factory gyroscope silicon flaws (F1002).'
  }
];
