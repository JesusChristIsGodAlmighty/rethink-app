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
        recommendation: 'Track progress in Build Order Tier 10.',
        authorityLevel: 1,
        reversible: true
      }
    }
  },
  // TIER 1 MODULES (Easiest Real Wins per Section 9)
  {
    id: 'F191',
    name: 'Interactive Block Page',
    category: 'Firewall & Blocking',
    priority: 'P1',
    difficulty: 1,
    cost: 'L',
    description: 'Informative, friendly block page explaining why a site was blocked, naming the filter list, and providing one-tap unblocking.',
    oneParagraphDoc: 'Intercepts HTTP/HTTPS blocked requests for browser viewports and serves a lightweight local HTML page explaining why access was prevented with an immediate whitelist bypass option.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Blocked websites will show generic browser "Connection reset" or "Cannot reach site" errors without explanation.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 3, dataSavedKb: 40 },
    fiveLenses: {
      monitor: {
        title: 'Block Page Renderer',
        metrics: [
          { label: 'Pages Served', value: '84' },
          { label: 'Avg Render Time', value: '0.4ms' }
        ],
        statusNote: 'Serving local zero-dependency template on loopback.'
      },
      detect: { threatsFound: 84, details: ['Served block page for 84 malware, phishing, and ad-tech domains'] },
      explain: {
        summary: 'In-Band Explanation Page',
        mechanism: 'Returns a synthetic HTTP 200 containing a local, zero-tracking HTML page when an HTTP/HTTPS domain matches an active blocklist rule.',
        risksMitigated: 'Eliminates confusion when a legitimate service is unexpectedly blocked.'
      },
      describe: {
        plainSummary: 'Shows a friendly explanation instead of an ugly error screen when a bad site is blocked.',
        whyItMatters: 'Normally when an ad blocker stops a site, you just get a broken browser screen saying "Server not found". This page tells you "Blocked by EasyPrivacy because it is a tracker" and lets you unblock it in one tap if you need to.',
        consequencesIfEnabled: 'You always know why a connection was stopped and have immediate control to override it.'
      },
      suggest: {
        recommendation: 'Keep active. Pairs with F192 for immediate temporary whitelisting.',
        authorityLevel: 2,
        actionLabel: 'Preview Block Page',
        reversible: true
      }
    }
  },
  {
    id: 'F192',
    name: 'One-Tap Unblock From Log',
    category: 'Firewall & Blocking',
    priority: 'P1',
    difficulty: 1,
    cost: 'L',
    description: 'Instant unblock button on any blocked connection with 10-minute, 1-hour, or permanent whitelist options.',
    oneParagraphDoc: 'Enables quick override rules from the connection tracker log without manually editing raw text hosts files or re-compiling blocklists.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Unblocking an inadvertently blocked domain requires manually entering complex rule syntax.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 2, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Temporary Whitelist Rules',
        metrics: [
          { label: 'Active Unblocks', value: '2', change: '1 expiring in 4m' },
          { label: 'Permanent Rules', value: '5' }
        ],
        statusNote: 'Timer engine automatically re-engages protection after expiry.'
      },
      detect: { threatsFound: 0, details: ['Zero expired rules left lingering'] },
      explain: {
        summary: 'Dynamic Rule Override Table',
        mechanism: 'Injects high-priority permit exceptions into the front of the radix tree evaluation path with an in-memory TTL timer.',
        risksMitigated: 'Prevents users from permanently disabling their firewall just to get past one stubborn website.'
      },
      describe: {
        plainSummary: 'Fix broken websites with one tap, and automatically turn protection back on later.',
        whyItMatters: 'If a site you need is blocked by mistake, you can unblock it for 10 minutes to finish what you are doing. You do not have to remember to turn the filter back on—it relocks itself automatically.',
        consequencesIfEnabled: 'Zero friction when encountering rare false positives.'
      },
      suggest: {
        recommendation: 'Use 10-minute temporary unblock for single payment checkouts.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F224',
    name: 'Blocklist Manager',
    category: 'Firewall & Blocking',
    priority: 'P1',
    difficulty: 1,
    cost: 'L',
    description: 'Add, remove, search, and toggle trusted community blocklists (OISD, StevenBlack, EasyPrivacy, custom URLs).',
    oneParagraphDoc: 'Single-screen management interface for external filter subscriptions with automatic deduplication and conflict resolution.',
    requiredPermissions: ['android.permission.INTERNET'],
    breaksIfDisabled: 'Blocklist sources cannot be toggled or customized.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.2, batteryPerHourPercent: 0.02, ramMb: 8, dataSavedKb: 3400 },
    fiveLenses: {
      monitor: {
        title: 'List Subscriptions',
        metrics: [
          { label: 'Active Lists', value: '5 / 7' },
          { label: 'Total Rules', value: '284,520' },
          { label: 'Last Synced', value: '2 hours ago' }
        ],
        statusNote: 'All active lists compiled and cached locally.'
      },
      detect: { threatsFound: 0, details: ['Deduplicated 41,200 redundant rules across lists'] },
      explain: {
        summary: 'Multi-Source Filter Aggregator',
        mechanism: 'Parses standard hosts, AdGuard, and uBlock rule formats into an optimized radix tree with zero memory bloat.',
        risksMitigated: 'Protects against stale filter definitions and format incompatibilities.'
      },
      describe: {
        plainSummary: 'Choose which protection lists you want to use from the world’s best privacy researchers.',
        whyItMatters: 'Lets you pick exactly how strict your protection is—from basic malware blocking to strict tracking prevention—with simple on/off switches.',
        consequencesIfEnabled: 'Keeps your phone protected against newly discovered scam domains automatically.'
      },
      suggest: {
        recommendation: 'Keep OISD Full and EasyPrivacy active for balanced daily protection.',
        authorityLevel: 2,
        actionLabel: 'Manage Lists',
        reversible: true
      }
    }
  },
  {
    id: 'F411',
    name: 'Tracking Parameter Stripper',
    category: 'Routing & Privacy',
    priority: 'P2',
    difficulty: 1,
    cost: 'L',
    description: 'Strips tracking identifiers (utm_source, fbclid, gclid, ref) from outgoing URLs to prevent link correlation.',
    oneParagraphDoc: 'Sanitizes HTTP GET parameters and redirect headers, removing cross-site click IDs while preserving essential page routing queries.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'URLs shared between apps or clicked in emails will carry unique advertising IDs.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 2, dataSavedKb: 12 },
    fiveLenses: {
      monitor: {
        title: 'URL Sanitization',
        metrics: [
          { label: 'Parameters Stripped', value: '142', change: '+18 today' },
          { label: 'Common Tokens', value: 'fbclid, utm' }
        ],
        statusNote: 'Active on all outbound HTTP/HTTPS requests.'
      },
      detect: { threatsFound: 142, details: ['Stripped tracking tokens from Instagram, Google, and Amazon links'] },
      explain: {
        summary: 'URL Query Sanitizer',
        mechanism: 'Inspects HTTP Request-URI and strips blacklisted query tokens via regex pattern matching before connection establishment.',
        risksMitigated: 'Prevents companies from linking which friend sent you a link or which email newsletter you opened.'
      },
      describe: {
        plainSummary: 'Cleans ugly tracking junk off web links when you tap them.',
        whyItMatters: 'Ever notice how links on Facebook or Twitter are a mile long? Those extra characters are spy codes that tell advertisers who clicked what. This scrubs them off automatically.',
        consequencesIfEnabled: 'Cleaner web links that protect your privacy when sharing.'
      },
      suggest: {
        recommendation: 'Enable on all apps to stop cross-platform click tracking.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F113',
    name: 'Metadata Scrubber',
    category: 'Tools & Diagnostics',
    priority: 'P2',
    difficulty: 1,
    cost: 'L',
    description: 'Strips EXIF GPS coordinates, camera serial numbers, and device timestamps from photos before upload.',
    oneParagraphDoc: 'Inspects media files passed through system shares and strips metadata headers in local memory with zero external transmission.',
    requiredPermissions: [],
    breaksIfDisabled: 'Photos posted online or sent via messaging apps may disclose your exact home GPS location.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 4, dataSavedKb: 140 },
    fiveLenses: {
      monitor: {
        title: 'EXIF Sanitizer',
        metrics: [
          { label: 'Files Cleaned', value: '28' },
          { label: 'GPS Tags Removed', value: '24' }
        ],
        statusNote: 'Zero-loss lossless image header scrubbing.'
      },
      detect: { threatsFound: 24, details: ['Removed precise GPS latitude/longitude tags from 24 camera photos'] },
      explain: {
        summary: 'Lossless EXIF/XMP Scrubber',
        mechanism: 'Parses JPEG, PNG, and WebP binary headers, zeroing out Exif.GPSInfo and camera serial tags while keeping raw image pixels untouched.',
        risksMitigated: 'Prevents physical location disclosure from uploaded photos.'
      },
      describe: {
        plainSummary: 'Removes your home address and camera serial number from photos you share.',
        whyItMatters: 'Every photo your phone takes includes invisible data showing the exact GPS coordinates where you stood. This removes that information so strangers cannot find where you live.',
        consequencesIfEnabled: 'Peace of mind when uploading pictures to social media or messaging groups.'
      },
      suggest: {
        recommendation: 'Keep active for all camera uploads.',
        authorityLevel: 1,
        reversible: true
      }
    }
  },
  {
    id: 'F155',
    name: 'Clipboard Guard',
    category: 'Tools & Diagnostics',
    priority: 'P3',
    difficulty: 1,
    cost: 'L',
    description: 'Auto-clears sensitive clipboard items after 60 seconds and notifies when background apps read clipboard.',
    oneParagraphDoc: 'Monitors Android ClipboardManager and enforces automatic memory wiping for copied passwords, credit cards, and recovery keys.',
    requiredPermissions: [],
    breaksIfDisabled: 'Copied passwords remain accessible to any third-party app with clipboard permissions indefinitely.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.05, batteryPerHourPercent: 0.01, ramMb: 2, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Clipboard Protection',
        metrics: [
          { label: 'Auto-Clear TTL', value: '60 seconds' },
          { label: 'Snooping Alerts', value: '3' }
        ],
        statusNote: 'Clipboard clear queue armed.'
      },
      detect: { threatsFound: 3, details: ['Shopping app queried clipboard in background while not in foreground'] },
      explain: {
        summary: 'Ephemeral Clipboard Scrubbing',
        mechanism: 'Hooks clipboard state changes and schedules a zero-overwrite job after user-configurable timeout.',
        risksMitigated: 'Neutralizes clipboard-stealing malware and snooping SDKs.'
      },
      describe: {
        plainSummary: 'Erases passwords and copied text after one minute so other apps cannot steal them.',
        whyItMatters: 'When you copy a password, it sits in your phone’s memory forever until you copy something else. Sneaky apps read your clipboard when they open. This wipes it clean after 60 seconds.',
        consequencesIfEnabled: 'Your passwords and private messages cannot be scooped up by background apps.'
      },
      suggest: {
        recommendation: 'Set 60-second auto-clear timer.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F503',
    name: 'Screen-Off Lockdown',
    category: 'Device & System',
    priority: 'P1',
    difficulty: 1,
    cost: 'L',
    description: 'Cuts unnecessary background network traffic the instant the phone screen turns off, saving battery and data.',
    oneParagraphDoc: 'Listens for Intent.ACTION_SCREEN_OFF and temporarily suspends non-whitelisted background app networking until screen unlock.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Background apps continue transmitting telemetry, ads, and location updates when the phone is in your pocket.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: -0.8, ramMb: 2, dataSavedKb: 5200 },
    fiveLenses: {
      monitor: {
        title: 'Screen-Off Energy Saver',
        metrics: [
          { label: 'Battery Saved', value: '~12% / day' },
          { label: 'Silent Pings Blocked', value: '419' }
        ],
        statusNote: 'Whitelisted VoIP (Signal, Phone) allowed through.'
      },
      detect: { threatsFound: 419, details: ['Blocked 419 background analytics pings while screen was locked'] },
      explain: {
        summary: 'Display State Conditional Firewall',
        mechanism: 'Switches netfilter routing to high-strictness whitelist when display turns off, keeping device deep-sleep active.',
        risksMitigated: 'Prevents battery drain and stops apps from snooping while you are not using your phone.'
      },
      describe: {
        plainSummary: 'Puts your apps to sleep when you put your phone in your pocket.',
        whyItMatters: 'Apps continue phoning home to advertisers all night long while your phone is sitting on your nightstand. This closes the network door when your screen is off, saving battery.',
        consequencesIfEnabled: 'Extends your battery life significantly and stops background data waste.'
      },
      suggest: {
        recommendation: 'Enable Screen-Off Lockdown with messaging apps whitelisted.',
        authorityLevel: 2,
        actionLabel: 'Configure Whitelist',
        reversible: true
      }
    }
  },
  {
    id: 'F448',
    name: 'Guest Wi-Fi QR Generator',
    category: 'Tools & Diagnostics',
    priority: 'P2',
    difficulty: 1,
    cost: 'L',
    description: 'Generate instant Wi-Fi connection QR codes for friends without handing out your plaintext password.',
    oneParagraphDoc: 'Creates standardized WIFI:S:ssid;T:WPA;P:password;; QR codes entirely offline with zero cloud transmission.',
    requiredPermissions: [],
    breaksIfDisabled: 'You must manually type complex Wi-Fi passwords on guest devices.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0, batteryPerHourPercent: 0, ramMb: 1, dataSavedKb: 0 },
    fiveLenses: {
      monitor: { title: 'QR Engine', metrics: [{ label: 'Format', value: 'WPA3 / WPA2' }], statusNote: 'Local offline renderer.' },
      detect: { threatsFound: 0, details: [] },
      explain: {
        summary: 'Standard Wi-Fi Protocol QR',
        mechanism: 'Encodes network SSID and pre-shared key into the official standard barcode format recognized by Android and iOS cameras.',
        risksMitigated: 'Eliminates need to write passwords on sticky notes or speak them aloud.'
      },
      describe: {
        plainSummary: 'Let guests connect to your Wi-Fi by scanning your phone screen with their camera.',
        whyItMatters: 'No more reading 20-character passwords out loud or having guests mistype them. They point their camera and are connected in two seconds.',
        consequencesIfEnabled: 'Quick, secure, password-less sharing.'
      },
      suggest: {
        recommendation: 'Generate QR code for your home guest network.',
        authorityLevel: 1,
        actionLabel: 'Create QR Code',
        reversible: true
      }
    }
  },
  {
    id: 'F624',
    name: 'Universal Cache Clear',
    category: 'Tools & Diagnostics',
    priority: 'P1',
    difficulty: 1,
    cost: 'L',
    description: 'One-tap junk cache clearer across apps with an ironclad guarantee never to touch SERF check-once profiles.',
    oneParagraphDoc: 'Clears accumulated temporary app cache files while explicitly protecting SERF’s F510/F514 verified evaluation caches and offline profiles.',
    requiredPermissions: [],
    breaksIfDisabled: 'Temporary tracking caches and cached ad banners accumulate on storage.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 2, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Storage Cleaning',
        metrics: [
          { label: 'Junk Cleared', value: '1.4 GB' },
          { label: 'SERF Profiles', value: 'Protected' }
        ],
        statusNote: 'Check-once evaluation caches safeguarded.'
      },
      detect: { threatsFound: 0, details: ['Cleaned 340 MB of stale ad video preloads'] },
      explain: {
        summary: 'Selective App Cache Scrubber',
        mechanism: 'Iterates application cache directories, purging temporary thumbnails and tracking blobs while preserving essential offline app profiles.',
        risksMitigated: 'Reclaims storage and purges persistent tracking cookies stored in webview caches.'
      },
      describe: {
        plainSummary: 'Frees up phone storage by deleting junk files while keeping your settings safe.',
        whyItMatters: 'Apps store gigabytes of old video ads and tracking files in hidden cache folders. This sweeps them away without logging you out of your favorite apps.',
        consequencesIfEnabled: 'Reclaims storage space and speeds up app launching.'
      },
      suggest: {
        recommendation: 'Run weekly cache sweep.',
        authorityLevel: 2,
        actionLabel: 'Clear Cache Now',
        reversible: false
      }
    }
  },
  // TIER 3: THE SOUL, ADVANCED & FIREWALL DEPTH (P1-P2, Diff 3-4)
  {
    id: 'F03',
    name: 'Dynamic Response Synthesis',
    category: 'Core Engine',
    priority: 'P1',
    difficulty: 3,
    cost: 'L',
    description: 'Generates tailored synthetic JSON, XML, or binary payloads for blocked API calls to prevent UI hangs.',
    oneParagraphDoc: 'Go-tun2socks netstack parses blocked outbound HTTP/HTTPS requests and serves customizable synthetic responses directly on the loopback interface, completely replacing offline network failure dialogs.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Apps with aggressive server checks will crash or hang when blocked domains fail to respond.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.2, batteryPerHourPercent: 0.02, ramMb: 6, dataSavedKb: 380 },
    fiveLenses: {
      monitor: {
        title: 'Synthetic Response Engine',
        metrics: [
          { label: 'Synthetic Packets', value: '412' },
          { label: 'Avg Synthesis Latency', value: '1.4ms' },
          { label: 'Crashes Prevented', value: '38' }
        ],
        statusNote: 'Synthesizing valid 200/204 payloads across 12 app domains.'
      },
      detect: { threatsFound: 38, details: ['Prevented Duolingo, Subway Surfers, and Spotify offline lockouts'] },
      explain: {
        summary: 'In-Memory Payload Synthesis',
        mechanism: 'Intercepts matching URLs on the virtual TUN interface and generates immediate RFC-compliant mock payloads with configurable delay.',
        risksMitigated: 'Prevents telemetry endpoints from knowing they are blocked and protects offline usability.'
      },
      describe: {
        plainSummary: 'Fakes real answers from servers so your apps think they reached the internet even when offline.',
        whyItMatters: 'Some apps freeze or lock your screen if they do not receive a specific code from their analytics company. This sends back a safe blank answer so you can keep working.',
        consequencesIfEnabled: 'Stops apps from complaining about internet connection errors.'
      },
      suggest: {
        recommendation: 'Keep active with F01 Fake-Online.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F10',
    name: 'uBlock-Grade Pattern Engine',
    category: 'Firewall & Blocking',
    priority: 'P1',
    difficulty: 2,
    cost: 'L',
    description: 'Ultra-fast regex, wildcard, and cosmetic list evaluation engine exceeding 250,000 rules.',
    oneParagraphDoc: 'High-performance Aho-Corasick radix tree compiling uBlock Origin, AdGuard, and Steven Black filter syntaxes on-device with sub-millisecond evaluation.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Reverts to standard IP-only blocking without regex pattern matching.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.4, batteryPerHourPercent: 0.04, ramMb: 14, dataSavedKb: 2450 },
    fiveLenses: {
      monitor: {
        title: 'Rule Match Engine',
        metrics: [
          { label: 'Active Rules', value: '142,890' },
          { label: 'Evaluation Time', value: '0.12ms' },
          { label: 'Memory Footprint', value: '14 MB' }
        ],
        statusNote: 'Radix tree compiled and hot in memory.'
      },
      detect: { threatsFound: 1420, details: ['Matched 1,420 tracking and telemetry domain signatures'] },
      explain: {
        summary: 'Radix Pattern Classifier',
        mechanism: 'Maps DNS and SNI hostnames against a memory-efficient radix tree supporting wildcard domain anchors and scriptlet filters.',
        risksMitigated: 'Neutralizes dynamic tracking domains generated by ad networks on the fly.'
      },
      describe: {
        plainSummary: 'A super-fast ad and tracker blocker that understands advanced web filter rules.',
        whyItMatters: 'Simple blockers only check exact names, so advertisers make up new names every day. This engine checks smart patterns to stop them before they load.',
        consequencesIfEnabled: 'Blocks intrusive ads and trackers cleanly across all apps.'
      },
      suggest: {
        recommendation: 'Enabled by default. Keep subscriptions updated weekly.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F11',
    name: 'Geo-IP & Outbound Firewall',
    category: 'Firewall & Blocking',
    priority: 'P2',
    difficulty: 2,
    cost: 'L',
    description: 'Country-level outbound packet filtering powered by an on-device MaxMind GeoLite2 database.',
    oneParagraphDoc: 'Enforces geographic egress boundaries by looking up destination IPv4/IPv6 addresses against an offline database and dropping packets bound for unauthorized nations.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Outbound connections to blocked jurisdictions will be permitted.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.2, batteryPerHourPercent: 0.02, ramMb: 9, dataSavedKb: 890 },
    fiveLenses: {
      monitor: {
        title: 'Geographic Egress Monitor',
        metrics: [
          { label: 'Blocked Countries', value: '5 active' },
          { label: 'Geo-Dropped Sockets', value: '704' },
          { label: 'Lookup Latency', value: '0.08ms' }
        ],
        statusNote: 'Outbound traffic filtered by jurisdiction.'
      },
      detect: { threatsFound: 704, details: ['Dropped 704 outbound sockets destined for servers in blocked regions'] },
      explain: {
        summary: 'Sovereign Egress Boundary',
        mechanism: 'Maps SYN packets to destination ISO country codes and drops unauthorized foreign egress at the network layer.',
        risksMitigated: 'Prevents data export to adversarial jurisdictions and surveillance infrastructure.'
      },
      describe: {
        plainSummary: 'Stops your phone from sending your private data to countries you don’t trust.',
        whyItMatters: 'Many apps secretly upload your contacts, location, and photos to data centers located in foreign countries without telling you.',
        consequencesIfEnabled: 'Blocks apps from phoning home to servers in selected countries.'
      },
      suggest: {
        recommendation: 'Configure blocked nations in Geo-IP settings.',
        authorityLevel: 2,
        actionLabel: 'View Geo Map',
        reversible: true
      }
    }
  },
  {
    id: 'F12',
    name: 'Real-Time Connection Prompts',
    category: 'Firewall & Blocking',
    priority: 'P2',
    difficulty: 3,
    cost: 'L',
    description: 'Interactive system alert asking whether to allow, drop, or synthesize fake responses for new sockets.',
    oneParagraphDoc: 'Holds unclassified outbound connection requests in netstack memory while presenting an interactive HUD prompt with one-tap decisions and granular attribution.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Unknown connections will follow default permissive or blocking firewall rules.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 4, dataSavedKb: 120 },
    fiveLenses: {
      monitor: {
        title: 'Interactive Gatekeeper',
        metrics: [
          { label: 'Prompts Served', value: '19' },
          { label: 'Held Sockets', value: '0 queued' },
          { label: 'User Whitelisted', value: '14' }
        ],
        statusNote: 'Ready for interactive connection intercept.'
      },
      detect: { threatsFound: 5, details: ['User blocked 5 suspicious outbound connections via real-time prompts'] },
      explain: {
        summary: 'Zero-Trust Interactive Gatekeeper',
        mechanism: 'Holds the initial SYN packet in a ring buffer until the user taps Allow, Block, or Fake-Online.',
        risksMitigated: 'Gives absolute manual sovereignty over newly installed or updated applications.'
      },
      describe: {
        plainSummary: 'Asks you "Allow or Block?" whenever an app tries to connect to a new website for the first time.',
        whyItMatters: 'Like a smart doorbell for your phone, it lets you decide if a newly installed app is allowed to call home or not.',
        consequencesIfEnabled: 'Shows a friendly popup when an app connects to a new server.'
      },
      suggest: {
        recommendation: 'Enable for untrusted or newly installed applications.',
        authorityLevel: 3,
        actionLabel: 'Test Connection Prompt',
        reversible: true
      }
    }
  },
  {
    id: 'F13',
    name: 'Stealth Mode (Zero-Signature)',
    category: 'Firewall & Blocking',
    priority: 'P3',
    difficulty: 3,
    cost: 'L',
    description: 'Suppresses ICMP echo replies, unsolicited TCP RSTs, and local mDNS broadcasts.',
    oneParagraphDoc: 'Forces network stack to silently drop probes, making your Android device appear completely non-existent to LAN scanners on public Wi-Fi.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Device will respond to network pings and broadcast discovery on local subnets.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.05, batteryPerHourPercent: 0.01, ramMb: 2, dataSavedKb: 10 },
    fiveLenses: {
      monitor: {
        title: 'RF & Subnet Blackhole',
        metrics: [
          { label: 'ICMP Pings Dropped', value: '89' },
          { label: 'Port Scans Silenced', value: '12' },
          { label: 'mDNS Suppressed', value: '34' }
        ],
        statusNote: 'Operating in pure blackhole mode on local Wi-Fi.'
      },
      detect: { threatsFound: 12, details: ['Silently ignored 12 port scans from other devices on local subnet'] },
      explain: {
        summary: 'Silent Host Architecture',
        mechanism: 'Disables kernel response generation for ICMP type 8, dropping packets with zero response latency.',
        risksMitigated: 'Prevents rogue devices on hotel or airport networks from discovering your phone.'
      },
      describe: {
        plainSummary: 'Makes your phone invisible to other devices connected to the same Wi-Fi network.',
        whyItMatters: 'When you join coffee shop or hotel Wi-Fi, other people can scan the network to see your phone model and find vulnerabilities. This hides your phone like a ghost.',
        consequencesIfEnabled: 'Your phone will not show up on Wi-Fi scanner apps or ping tools.'
      },
      suggest: {
        recommendation: 'Always keep active on public or untrusted Wi-Fi.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F16',
    name: 'Natural-Language Rule Engine',
    category: 'Firewall & Blocking',
    priority: 'P4',
    difficulty: 4,
    cost: 'L',
    description: 'Converts plain English commands into enforceable on-device firewall and throttling rules.',
    oneParagraphDoc: 'On-device rule compiler that parses sentences like "Block TikTok after 10 PM" into structured packet schedules and synthetic responses without cloud servers.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Rules must be entered in raw regex or CIDR format.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 5, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Compiled NL Rules',
        metrics: [
          { label: 'Active NL Rules', value: '3' },
          { label: 'Parsing Time', value: '4.8ms' },
          { label: 'Enforced Today', value: '184 times' }
        ],
        statusNote: '3 natural language policies active.'
      },
      detect: { threatsFound: 184, details: ['Enforced time and app isolation schedules across 3 policies'] },
      explain: {
        summary: 'Plain Language Policy Synthesis',
        mechanism: 'Maps grammar intent to SERF JSON rule syntax using a lightweight local deterministic parser.',
        risksMitigated: 'Eliminates complex networking jargon, making advanced firewall power accessible to everyday users.'
      },
      describe: {
        plainSummary: 'Type what you want in plain words and SERF automatically creates the firewall rule for you.',
        whyItMatters: 'You should not have to be a network engineer to say "Block games during study hours" or "Stop Duolingo from talking to Facebook".',
        consequencesIfEnabled: 'Lets you set up custom app rules by just typing in simple English.'
      },
      suggest: {
        recommendation: 'Use to automate bedtime schedules and isolate noisy apps.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F19',
    name: 'Floating Firewall HUD',
    category: 'Firewall & Blocking',
    priority: 'P4',
    difficulty: 2,
    cost: 'L',
    description: 'Android floating overlay bubble displaying real-time socket counts, throughput, and a 1-tap Panic Button.',
    oneParagraphDoc: 'Renders an unobtrusive system overlay tracking open sockets, dropped telemetry, and offering immediate access to the F20 Panic Kill Switch.',
    requiredPermissions: ['android.permission.SYSTEM_ALERT_WINDOW'],
    breaksIfDisabled: 'Firewall metrics only visible inside the main app dashboard.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.15, batteryPerHourPercent: 0.02, ramMb: 7, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Floating Overlay State',
        metrics: [
          { label: 'Live Sockets', value: '14' },
          { label: 'Throughput', value: '30.5 KB/s' },
          { label: 'HUD Status', value: 'Active' }
        ],
        statusNote: 'Overlay bubble visible on screen.'
      },
      detect: { threatsFound: 0, details: ['Real-time telemetry readout active'] },
      explain: {
        summary: 'Immediate Ambient Awareness',
        mechanism: 'Draws a hardware-accelerated overlay surface with zero-allocation data binding.',
        risksMitigated: 'Ensures users immediately notice unexpected background traffic when testing suspicious apps.'
      },
      describe: {
        plainSummary: 'A tiny floating bubble on your screen showing your live connection speed and a quick panic button.',
        whyItMatters: 'You can immediately see if an app starts secretly downloading or uploading data in the background while using other apps.',
        consequencesIfEnabled: 'Shows a small movable bubble on the edge of your screen.'
      },
      suggest: {
        recommendation: 'Toggle on during testing or when using untrusted apps.',
        authorityLevel: 2,
        actionLabel: 'Toggle Floating HUD',
        reversible: true
      }
    }
  },
  {
    id: 'F20',
    name: 'Antitheft Lockdown & Kill Switch',
    category: 'Firewall & Blocking',
    priority: 'P2',
    difficulty: 3,
    cost: 'L',
    description: 'One-tap emergency panic trigger: drops all sockets, wipes ephemeral keys, and severs all network radios.',
    oneParagraphDoc: 'Emergency security interlock that instantly tears down tun0, zeroes out memory buffers containing WireGuard session keys, and forces radio isolation.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Requires navigating multiple menus to sever connections in an emergency.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.01, batteryPerHourPercent: 0.0, ramMb: 1, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Emergency Interlock',
        metrics: [
          { label: 'Lockdown State', value: 'Armed (Ready)' },
          { label: 'Zero-Fill Latency', value: '0.4ms' },
          { label: 'Key Shredder', value: 'AES-256 Armed' }
        ],
        statusNote: 'Panic trigger armed and ready.'
      },
      detect: { threatsFound: 0, details: ['Emergency interlock ready for activation'] },
      explain: {
        summary: 'Emergency Ephemeral Zeroization',
        mechanism: 'Calls explicit memory zeroing on all cryptographic material and calls VpnService.stopSelf() with immediate interface teardown.',
        risksMitigated: 'Protects device against physical seizure, forensic memory extraction, and rogue network takeover.'
      },
      describe: {
        plainSummary: 'An emergency panic button that instantly cuts all internet and wipes temporary security keys in half a millisecond.',
        whyItMatters: 'If someone steals your phone or you need immediate radio silence, this shuts down all network connections in one tap.',
        consequencesIfEnabled: 'Instantly severs internet connectivity when pressed until you unlock it.'
      },
      suggest: {
        recommendation: 'Available in Floating HUD and top action bar.',
        authorityLevel: 4,
        reversible: true
      }
    }
  },
  {
    id: 'F151',
    name: 'Port Scan Detector & Active Shield',
    category: 'Firewall & Blocking',
    priority: 'P2',
    difficulty: 3,
    cost: 'L',
    description: 'Detects inbound and local LAN SYN/FIN/XMAS port scans, logs attacker IPs, and drops connections.',
    oneParagraphDoc: 'Monitors TCP flag combinations on incoming packets and flags sequential port probes, automatically blacklisting scanning hosts for 1 hour.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Port scans will execute without triggering alarms.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 3, dataSavedKb: 20 },
    fiveLenses: {
      monitor: {
        title: 'LAN Threat Sensor',
        metrics: [
          { label: 'Scans Detected', value: '4' },
          { label: 'Hosts Blacklisted', value: '2' },
          { label: 'Dropped Probes', value: '98' }
        ],
        statusNote: 'Monitoring LAN for reconnaissance activity.'
      },
      detect: { threatsFound: 4, details: ['Detected SYN scan from 192.168.1.104; auto-blacklisted for 60m'] },
      explain: {
        summary: 'Passive Reconnaissance Shield',
        mechanism: 'Calculates rate of distinct destination port hits per source IP over a sliding 5-second window.',
        risksMitigated: 'Neutralizes automated network reconnaissance on public and enterprise Wi-Fi.'
      },
      describe: {
        plainSummary: 'Catches other devices on your Wi-Fi that are scanning your phone for open doors.',
        whyItMatters: 'Hackers scan network ports to find out what software you are running. This alerts you and blocks them immediately.',
        consequencesIfEnabled: 'Notifies you if someone on the same Wi-Fi tries to probe your phone.'
      },
      suggest: {
        recommendation: 'Keep active on all Wi-Fi networks.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F154',
    name: 'Notification Firewall',
    category: 'Firewall & Blocking',
    priority: 'P3',
    difficulty: 2,
    cost: 'L',
    description: 'Inspects push notification payloads, blocking embedded tracking beacons and promotional spam.',
    oneParagraphDoc: 'Local notification listener that parses incoming FCM/push payloads, strips tracking pixels and promotional marketing spam while preserving critical messages.',
    requiredPermissions: ['android.permission.BIND_NOTIFICATION_LISTENER_SERVICE'],
    breaksIfDisabled: 'Push notifications will display all promotional spam and tracking beacons will execute.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.02, ramMb: 4, dataSavedKb: 150 },
    fiveLenses: {
      monitor: {
        title: 'Push Payload Filter',
        metrics: [
          { label: 'Push Scanned', value: '184' },
          { label: 'Spam Blocked', value: '31' },
          { label: 'Beacons Stripped', value: '62' }
        ],
        statusNote: 'Filtering marketing push spam.'
      },
      detect: { threatsFound: 31, details: ['Silenced 31 promotional push alerts from shopping and gaming apps'] },
      explain: {
        summary: 'In-Band Push Inspection',
        mechanism: 'Extracts data bundle keys from push payloads and evaluates against advertising notification signatures.',
        risksMitigated: 'Prevents notification-based tracking and stops apps from spamming your lock screen.'
      },
      describe: {
        plainSummary: 'Silences annoying sales and promo notifications while keeping your real messages safe.',
        whyItMatters: 'Games and shopping apps constantly buzz your pocket with "Limited Time Deal!" popups that also track when you unlock your phone.',
        consequencesIfEnabled: 'Stops spam notifications without missing messages from family and friends.'
      },
      suggest: {
        recommendation: 'Enable to quiet down spammy apps.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F162',
    name: 'Call/SMS Geographic Blocking',
    category: 'Firewall & Blocking',
    priority: 'P2',
    difficulty: 2,
    cost: 'L',
    description: 'Telephony shield dropping calls and SMS from specified international country codes and satellite ranges.',
    oneParagraphDoc: 'Filters incoming caller-ID strings against international fraud databases (e.g. +234, +881 satellite ranges) without requiring access to your contacts list.',
    requiredPermissions: ['android.permission.READ_PHONE_STATE'],
    breaksIfDisabled: 'International robocalls and fraud rings will ring through.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.05, batteryPerHourPercent: 0.01, ramMb: 2, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Telephony Fraud Shield',
        metrics: [
          { label: 'Prefixes Blocked', value: '4 ranges' },
          { label: 'Calls Screened', value: '12' },
          { label: 'Spam Dropped', value: '5' }
        ],
        statusNote: 'Screening incoming telephony by country code.'
      },
      detect: { threatsFound: 5, details: ['Dropped 5 robocalls from high-risk international satellite toll prefixes'] },
      explain: {
        summary: 'Prefix-Based Telephony Filter',
        mechanism: 'Matches incoming phone state events against E.164 country code tables with zero contact-book permissions.',
        risksMitigated: 'Prevents one-ring "Wangiri" callback scams and international toll fraud.'
      },
      describe: {
        plainSummary: 'Blocks spam phone calls and scam text messages from foreign country codes you never call.',
        whyItMatters: 'Scammers use computer systems to dial thousands of people from overseas numbers hoping you call back and get charged massive fees.',
        consequencesIfEnabled: 'Silently silences calls from blocked international prefixes.'
      },
      suggest: {
        recommendation: 'Add prefixes for countries you have no contacts in.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F194',
    name: 'Auth-Flow Safe Mode',
    category: 'Firewall & Blocking',
    priority: 'P2',
    difficulty: 3,
    cost: 'L',
    description: '60-second temporary sandbox pinhole for OAuth/SSO login flows without disabling global protection.',
    oneParagraphDoc: 'Enables a self-expiring 60-second rule exemption specifically for authentication endpoints (Google Sign-In, Apple ID, Okta) that reverts automatically.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Strict firewall rules may block OAuth redirect callbacks in hardened profiles.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.05, batteryPerHourPercent: 0.0, ramMb: 2, dataSavedKb: 15 },
    fiveLenses: {
      monitor: {
        title: 'Authentication Sandbox',
        metrics: [
          { label: 'Safe Pinhole', value: 'Ready' },
          { label: 'Auto-Expiry Window', value: '60s' },
          { label: 'Times Triggered', value: '8' }
        ],
        statusNote: 'Ready for temporary sign-in bypass.'
      },
      detect: { threatsFound: 0, details: ['All temporary pinholes expired cleanly on schedule'] },
      explain: {
        summary: 'Ephemeral Ingress Window',
        mechanism: 'Appends a transient high-priority rule for OAuth callbacks with a countdown timer, flushing the rule upon expiration.',
        risksMitigated: 'Prevents users from permanently disabling their firewall just to log in to an app.'
      },
      describe: {
        plainSummary: 'A 60-second safety window that lets you log in to an app without turning off your firewall.',
        whyItMatters: 'Sometimes strict ad blockers stop "Sign in with Google" or bank logins from loading. Instead of turning off your shield and forgetting, this opens a 60-second window that locks itself back up automatically.',
        consequencesIfEnabled: 'Gives you a one-tap button to log in cleanly whenever a login screen gets stuck.'
      },
      suggest: {
        recommendation: 'Trigger only when a legitimate login page fails to load.',
        authorityLevel: 3,
        reversible: true
      }
    }
  },
  {
    id: 'F227',
    name: 'VPN + DNS Chain Wizard',
    category: 'Routing & Privacy',
    priority: 'P2',
    difficulty: 3,
    cost: 'L',
    description: 'Auto-corrects network tunnel ordering, preventing IPv6 leaks and circular routing deadlocks.',
    oneParagraphDoc: 'Verifies the network pipeline (App -> Virtual TUN -> Intra Netstack -> WireGuard / Tor -> DoH Resolver) guaranteeing that DNS queries never escape unencrypted.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Misconfigured VPN or proxy chains will leak plaintext DNS queries via IPv6.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 4, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Pipeline Verification',
        metrics: [
          { label: 'Pipeline Order', value: 'Verified' },
          { label: 'IPv6 Leak State', value: 'Zero Leaks' },
          { label: 'Encryption', value: 'ChaCha20-Poly1305' }
        ],
        statusNote: 'All stages verified in correct sequence.'
      },
      detect: { threatsFound: 0, details: ['Verified no IPv6 bypasses exist on cellular or Wi-Fi interfaces'] },
      explain: {
        summary: 'Deterministic Chain Validation',
        mechanism: 'Validates route tables and socket bindings using kernel netlink sockets to prove packets follow the cryptographically enforced chain.',
        risksMitigated: 'Eliminates silent ISP snooping through secondary IPv6 gateway leaks.'
      },
      describe: {
        plainSummary: 'Makes sure your VPN and secure DNS are connected in the exact right order so your real IP never leaks.',
        whyItMatters: 'Many VPN apps accidentally leak your internet lookups over modern IPv6 connections without you knowing. This wizard guarantees total airtight protection.',
        consequencesIfEnabled: 'Guarantees zero DNS leaks and keeps your connection completely private.'
      },
      suggest: {
        recommendation: 'Check wizard status when connecting to new VPN providers.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F236',
    name: 'Universal Bandwidth Throttle',
    category: 'Firewall & Blocking',
    priority: 'P2',
    difficulty: 2,
    cost: 'L',
    description: 'Per-app, global, and per-radio hard bandwidth caps to control cellular data usage.',
    oneParagraphDoc: 'Token-bucket rate limiter implemented in the tun netstack that throttles selected apps or the global interface to specified speed ceilings.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Apps will consume maximum available carrier bandwidth without limit.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 3, dataSavedKb: 4500 },
    fiveLenses: {
      monitor: {
        title: 'Bandwidth Governor',
        metrics: [
          { label: 'Global Ceiling', value: 'Unlimited' },
          { label: 'Active Throttles', value: '2 apps' },
          { label: 'Data Conserved', value: '4.5 MB' }
        ],
        statusNote: 'Token bucket governor active.'
      },
      detect: { threatsFound: 0, details: ['Throttling Photos backup on cellular to 50 KB/s'] },
      explain: {
        summary: 'Tun2Socks Token-Bucket Rate Limiter',
        mechanism: 'Delays TCP ACK packets and shapes incoming payload bursts according to configured byte budgets.',
        risksMitigated: 'Prevents rogue apps from burning through mobile data caps and draining battery in background.'
      },
      describe: {
        plainSummary: 'Put speed limits on data-hungry apps so they don’t waste your mobile data plan.',
        whyItMatters: 'Photo backups and video apps can chew through your monthly cellular data in an hour. This lets you put them in the slow lane.',
        consequencesIfEnabled: 'Saves your monthly data allowance and battery life.'
      },
      suggest: {
        recommendation: 'Configure limits for cloud sync apps on cellular.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F373',
    name: 'Rogue CA Certificate Detector',
    category: 'Developer & Advanced',
    priority: 'P3',
    difficulty: 2,
    cost: 'L',
    description: 'Audits Android system and user certificate stores, alerting to rogue enterprise MDM or stalkerware CAs.',
    oneParagraphDoc: 'Scans /system/etc/security/cacerts and the Android KeyStore trust manager, checking public key hashes against a database of known MITM certificates.',
    requiredPermissions: ['android.permission.INTERNET'],
    breaksIfDisabled: 'Hidden root certificates will intercept HTTPS traffic without notification.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.05, batteryPerHourPercent: 0.0, ramMb: 3, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Trust Store Inspector',
        metrics: [
          { label: 'System CAs', value: '142 verified' },
          { label: 'User CAs', value: '0 installed' },
          { label: 'Store Integrity', value: '100% Clean' }
        ],
        statusNote: 'All root certificates verified against AOSP baseline.'
      },
      detect: { threatsFound: 0, details: ['Zero rogue MITM certificates detected in user or system trust stores'] },
      explain: {
        summary: 'Root Trust Store Auditor',
        mechanism: 'Compares SHA-256 fingerprint hashes of all X.509 root certificates against the official Google AOSP trust database.',
        risksMitigated: 'Detects corporate spyware, school monitoring software, and stalkerware attempting HTTPS interception.'
      },
      describe: {
        plainSummary: 'Scans your phone to make sure nobody installed a secret spying certificate to read your encrypted web traffic.',
        whyItMatters: 'If someone gets access to your phone or gives you a managed device, they can install a "root certificate" that lets them read your private messages and bank logins.',
        consequencesIfEnabled: 'Gives you peace of mind that your encrypted connections are truly private.'
      },
      suggest: {
        recommendation: 'Run after system updates or when receiving a pre-configured device.',
        authorityLevel: 2,
        actionLabel: 'Scan Certificates',
        reversible: true
      }
    }
  },
  {
    id: 'F387',
    name: 'DNS Rebinding Attack Detector',
    category: 'Firewall & Blocking',
    priority: 'P3',
    difficulty: 3,
    cost: 'L',
    description: 'Blocks public domain names that return RFC 1918 private IP addresses (192.168.x.x, 10.x.x.x, 127.0.0.1).',
    oneParagraphDoc: 'Inspects all DNS A and AAAA response records, dropping any answer where a public internet hostname points to internal local-network subnets.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'Malicious websites can execute DNS rebinding attacks against home routers and IoT devices.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.05, batteryPerHourPercent: 0.01, ramMb: 2, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Rebinding Shield',
        metrics: [
          { label: 'DNS Queries Inspected', value: '3,892' },
          { label: 'Rebinding Attempts', value: '3 dropped' },
          { label: 'RFC1918 Filter', value: 'Active' }
        ],
        statusNote: 'Protecting home LAN and IoT devices.'
      },
      detect: { threatsFound: 3, details: ['Blocked 3 public domains attempting to resolve to 192.168.1.1 and 127.0.0.1'] },
      explain: {
        summary: 'RFC 1918 Boundary Protection',
        mechanism: 'Parses DNS RDATA answers and validates that non-local domains never contain addresses in 10.0.0.0/8, 172.16.0.0/12, 192.168.0.0/16, or 127.0.0.0/8.',
        risksMitigated: 'Stops browser tabs from accessing router admin consoles or internal smart devices.'
      },
      describe: {
        plainSummary: 'Stops malicious websites from hacking into your home Wi-Fi router or smart home devices.',
        whyItMatters: 'A sneaky website can trick your browser into thinking your home router is part of their website, allowing them to change router passwords or control smart cameras.',
        consequencesIfEnabled: 'Blocks fake lookups before they can reach your internal network.'
      },
      suggest: {
        recommendation: 'Keep active at all times on all profiles.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  // TIER 4: PRIVACY & ROUTING CORE MODULES
  {
    id: 'F30',
    name: 'Tor Onion Circuit Router',
    category: 'Routing & Privacy',
    priority: 'P1',
    difficulty: 3,
    cost: 'L',
    description: 'Routes traffic through a multi-hop cryptographic Onion circuit with SOCKS5 stream isolation and bridge transports.',
    oneParagraphDoc: 'Wraps outbound TCP streams into 3-layer encrypted Tor circuits (Guard, Middle, Exit relays). Isolates application streams by client UID on local SOCKS5 loopback 127.0.0.1:9050 with obfs4 and Snowflake pluggable transports.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE', 'android.permission.INTERNET'],
    breaksIfDisabled: 'Direct egress exposes your true public IP, ISP location, and cellular tower coordinates to all web destinations.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.8, batteryPerHourPercent: 0.15, ramMb: 24, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Tor Circuit Health',
        metrics: [
          { label: 'Circuit State', value: '3 Hops Active' },
          { label: 'Round-Trip Latency', value: '142ms' },
          { label: 'Active Streams', value: '4' }
        ],
        statusNote: 'Circuit routed through Iceland Guard and Netherlands Exit.'
      },
      detect: { threatsFound: 18, details: ['Prevented 18 IP correlation attempts by ad trackers'] },
      explain: {
        summary: 'Layered Onion Encryption',
        mechanism: 'Encapsulates data in three concentric cryptographic layers, stripping each hop at the respective relay so no single node knows both source and destination.',
        risksMitigated: 'Defeats ISP surveillance, nation-state censorship, and geo-profiling.'
      },
      describe: {
        plainSummary: 'Bounces your internet connection through three encrypted relays so websites cannot see who or where you are.',
        whyItMatters: 'Websites and ad trackers record your IP address to build a permanent file on everything you do online. Tor masks your real identity behind a changing global network.',
        consequencesIfEnabled: 'Your web traffic is strongly anonymous. Connections may take a fraction of a second longer to load.'
      },
      suggest: {
        recommendation: 'Pair with F124 Split-Tunneling to route browsers through Tor while keeping bank apps on direct VPN.',
        authorityLevel: 2,
        actionLabel: 'Rotate Circuit Identity',
        reversible: true
      }
    }
  },
  {
    id: 'F34',
    name: 'RFC 7686 .onion Resolver',
    category: 'Routing & Privacy',
    priority: 'P1',
    difficulty: 2,
    cost: 'L',
    description: 'Resolves special-use .onion domain names via local SOCKS5 Tor daemon into loopback 127.192.0.0/10 virtual mappings.',
    oneParagraphDoc: 'Intercepts RFC 7686 pseudo-TLD .onion queries before they reach upstream public DNS servers, assigning ephemeral virtual IPs and establishing encrypted end-to-end Hidden Service rendezvous circuits.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: '.onion hidden service domains fail with NXDOMAIN or leak unencrypted queries to public DNS resolvers.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 4, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: '.onion Virtual Mapping',
        metrics: [
          { label: 'Onion Lookups', value: '14' },
          { label: 'Virtual Subnet', value: '127.192.0.0/10' }
        ],
        statusNote: 'Resolving onion cryptographic descriptors on loopback.'
      },
      detect: { threatsFound: 0, details: ['Zero .onion queries leaked to public DNS servers'] },
      explain: {
        summary: 'RFC 7686 Local Loopback Synthesis',
        mechanism: 'Maps incoming .onion domains to loopback address space and binds TCP connections to SOCKS5 rendezvous tunnels.',
        risksMitigated: 'Prevents catastrophic deanonymization from public DNS server leaks.'
      },
      describe: {
        plainSummary: 'Lets you open .onion privacy websites safely without leaking searches to your internet provider.',
        whyItMatters: 'If your phone sends a .onion lookup to Google DNS or your cable company, they immediately flag your connection. This answers lookups entirely on your own phone.',
        consequencesIfEnabled: '.onion sites open cleanly in your web browser.'
      },
      suggest: {
        recommendation: 'Keep active at all times.',
        authorityLevel: 2,
        reversible: true
      }
    }
  },
  {
    id: 'F124',
    name: 'Per-App Split Tunneling Matrix',
    category: 'Routing & Privacy',
    priority: 'P0',
    difficulty: 2,
    cost: 'L',
    description: 'Fine-grained egress policy matrix assigning apps to Direct internet, WireGuard VPN, Tor Circuit, Fake-Online, or Block.',
    oneParagraphDoc: 'Leverages Linux UID routing and Android VpnService package inclusion/exclusion filters to route each application through custom network paths based on trust levels and functional requirements.',
    requiredPermissions: ['android.permission.BIND_VPN_SERVICE'],
    breaksIfDisabled: 'All applications are forced through a single egress route, causing banking app blocks or leaking untrusted app traffic.',
    enabled: true,
    defaultTileSize: '4x2',
    currentTileSize: '4x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.2, batteryPerHourPercent: 0.02, ramMb: 6, dataSavedKb: 2100 },
    fiveLenses: {
      monitor: {
        title: 'App Routing Policy Matrix',
        metrics: [
          { label: 'Configured Apps', value: '9' },
          { label: 'Bypass (Direct)', value: '2 (Banking)' },
          { label: 'Tor Circuits', value: '2 (Browsers)' },
          { label: 'Fake Stubs', value: '2 (Google GMS)' }
        ],
        statusNote: 'Per-UID routing active across all app sandboxes.'
      },
      detect: { threatsFound: 8, details: ['Isolated Google Play Services telemetry while permitting Chase Banking direct access'] },
      explain: {
        summary: 'Multi-Path Egress Policy Engine',
        mechanism: 'Applies Linux ip-rule and socket fwmark tagging to route traffic based on UID credentials to WireGuard, Tor, or local loopback.',
        risksMitigated: 'Eliminates all-or-nothing VPN limitations.'
      },
      describe: {
        plainSummary: 'Choose exactly how each app connects to the internet.',
        whyItMatters: 'Some apps (like mobile banking) complain if you use a VPN, while others (like web browsers) should always use Tor for privacy. This lets you give each app its own connection rule.',
        consequencesIfEnabled: 'Banking apps work smoothly on direct internet while browsers stay fully encrypted and anonymous.'
      },
      suggest: {
        recommendation: 'Use one-tap "Banking Bypass" preset for zero checkout friction.',
        authorityLevel: 2,
        actionLabel: 'Open Split Matrix',
        reversible: true
      }
    }
  },
  {
    id: 'F126',
    name: 'GPS Gaussian Fuzzing Shield',
    category: 'Routing & Privacy',
    priority: 'P1',
    difficulty: 2,
    cost: 'L',
    description: 'Injects controlled Gaussian coordinate displacement jitter to prevent apps from triangulating room-level locations.',
    oneParagraphDoc: 'Intercepts Android LocationManager coordinates and injects parameterized Gaussian scatter (±50m to ±25km radius) while stripping BSSID Wi-Fi network beacon scanning.',
    requiredPermissions: ['android.permission.ACCESS_FINE_LOCATION'],
    breaksIfDisabled: 'Installed apps can pinpoint your exact street address, apartment unit, and movement history.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 3, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Coordinate Fuzzing State',
        metrics: [
          { label: 'Jitter Radius', value: '±500m' },
          { label: 'Active Offset', value: 'Δ248m' },
          { label: 'BSSID Scans Stripped', value: '42' }
        ],
        statusNote: 'Reporting city-block level coordinates to background apps.'
      },
      detect: { threatsFound: 14, details: ['Neutralized 14 high-precision background location queries'] },
      explain: {
        summary: 'Gaussian Coordinate Perturbation',
        mechanism: 'Applies normal distribution mathematical offsets to latitude and longitude coordinates before returning values to third-party SDKs.',
        risksMitigated: 'Prevents physical location stalking and behavioral habit profiling.'
      },
      describe: {
        plainSummary: 'Fuzzes your location so apps only know your general neighborhood, not your front door.',
        whyItMatters: 'Shopping and social apps constantly poll GPS in the background to build a map of everywhere you walk and sleep. This gives them a fuzzy area so your home remains private.',
        consequencesIfEnabled: 'Weather apps still give you accurate forecasts, but trackers cannot see what building you are inside.'
      },
      suggest: {
        recommendation: 'Use ±500m for daily balance, or ±5km for maximum citywide cloaking.',
        authorityLevel: 2,
        actionLabel: 'Adjust Fuzzing Radius',
        reversible: true
      }
    }
  },
  {
    id: 'F277',
    name: 'Decoy Traffic & Packet Padding Engine',
    category: 'Routing & Privacy',
    priority: 'P1',
    difficulty: 2,
    cost: 'L',
    description: 'Generates synthetic background dummy traffic with randomized TLS padding to defeat ISP packet flow watermarking.',
    oneParagraphDoc: 'Transmits Poisson-distributed synthetic encrypted TLS requests to neutral high-reputation CDN endpoints, appending 128-512 bytes of padding to mask user idle-versus-active browsing patterns.',
    requiredPermissions: ['android.permission.INTERNET'],
    breaksIfDisabled: 'ISPs and cellular carriers can infer what you are doing online by observing packet timing bursts and packet lengths.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.2, batteryPerHourPercent: 0.04, ramMb: 5, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Decoy Traffic Generator',
        metrics: [
          { label: 'Decoys Sent', value: '342' },
          { label: 'Avg Interval', value: '15s Poisson' },
          { label: 'Padding Added', value: '+240B Avg' }
        ],
        statusNote: 'Active dummy flows obscuring device activity state.'
      },
      detect: { threatsFound: 0, details: ['Masked traffic shape against ISP deep packet inspection'] },
      explain: {
        summary: 'Traffic Flow Camouflage',
        mechanism: 'Injects low-frequency HTTP GET requests to Wikipedia and Debian mirrors with variable TLS padding, smoothing the packet timing graph.',
        risksMitigated: 'Defeats machine learning traffic analysis and website fingerprinting attacks.'
      },
      describe: {
        plainSummary: 'Sends tiny fake signals in the background so your internet provider cannot guess what you are reading.',
        whyItMatters: 'Even with encryption, internet companies can tell when you click a link by watching bursts of data. This sends random background whispers so your real activity blends into the background noise.',
        consequencesIfEnabled: 'Zero personal data cost, complete flow-analysis privacy.'
      },
      suggest: {
        recommendation: 'Keep active on public Wi-Fi and hotel connections.',
        authorityLevel: 2,
        actionLabel: 'Trigger Decoy Burst',
        reversible: true
      }
    }
  },
  {
    id: 'F367',
    name: 'Browser & Hardware Fingerprint Shield',
    category: 'Routing & Privacy',
    priority: 'P1',
    difficulty: 2,
    cost: 'L',
    description: 'Injects noise into Canvas, WebGL, AudioContext, and WebRTC local IP APIs to lower browser identifiability entropy.',
    oneParagraphDoc: 'Applies subtle mathematical perturbations to HTML5 Canvas font rendering, WebGL GPU vendor strings, AudioContext compressor dynamics, and mDNS candidate cloaking to reduce entropy from 33.4 bits down to under 8 bits.',
    requiredPermissions: [],
    breaksIfDisabled: 'Websites can uniquely identify and track your browser across incognito sessions without using any cookies.',
    enabled: true,
    defaultTileSize: '2x2',
    currentTileSize: '2x2',
    status: 'active',
    resourceCost: { cpuPercent: 0.1, batteryPerHourPercent: 0.01, ramMb: 2, dataSavedKb: 0 },
    fiveLenses: {
      monitor: {
        title: 'Fingerprint Entropy Cloak',
        metrics: [
          { label: 'Entropy Bits', value: '6.8 bits' },
          { label: 'Uniqueness Pool', value: '1 in 110' },
          { label: 'Status', value: 'SOVEREIGN CLOAKED' }
        ],
        statusNote: 'Canvas, WebGL, and AudioContext noise active.'
      },
      detect: { threatsFound: 32, details: ['Neutralized 32 fingerprinting canvas extraction probes'] },
      explain: {
        summary: 'Synthetic Hardware Noise Layer',
        mechanism: 'Alters the least-significant bits of canvas pixel arrays and web audio buffers, generating a fresh non-trackable fingerprint on every session.',
        risksMitigated: 'Defeats cross-site tracking and cookie-less identity tracking.'
      },
      describe: {
        plainSummary: 'Stops websites from secretly recognizing your device without cookies.',
        whyItMatters: 'Websites inspect tiny quirks in how your computer draws text and shapes to give you a secret tracking barcode. This adds invisible noise so your device looks just like millions of other devices.',
        consequencesIfEnabled: 'You blend in with the crowd. Web pages look and work completely normally.'
      },
      suggest: {
        recommendation: 'Keep active on all browsers.',
        authorityLevel: 2,
        actionLabel: 'Inspect Entropy Score',
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
  },
  {
    key: 'iccid_to_apps',
    label: 'SIM ICCID Serial (App Level)',
    category: 'SIM' as const,
    currentRealValue: '89014103211118510720',
    fakeValue: '89014103998877665544',
    active: true,
    queriesCount: 28,
    description: 'Reported to third-party SDKs; defeats carrier SIM serial tracking (F995).'
  },
  {
    key: 'gsf_id',
    label: 'Google Services Framework (GSF) ID',
    category: 'App Identity' as const,
    currentRealValue: '3d8a4e921b77c082',
    fakeValue: 'Rotated Ephemeral GSF-Token',
    active: true,
    queriesCount: 84,
    description: 'Rotates FCM tokens and GSF identifiers to block cross-app telemetry profiling (F996).'
  },
  {
    key: 'nfc_uid',
    label: 'NFC Emulation Hardware UID',
    category: 'Hardware' as const,
    currentRealValue: '04:7A:B2:1C:88:40',
    fakeValue: '04:XX:XX:XX:XX:XX (Randomized)',
    active: true,
    queriesCount: 6,
    description: 'Randomizes hardware NFC UID broadcast during tap/reader transactions (F1003).'
  },
  {
    key: 'usb_descriptor',
    label: 'USB Host Device Descriptor',
    category: 'Hardware' as const,
    currentRealValue: 'Google Pixel 9 Pro (SN: 34182910)',
    fakeValue: 'Generic MTP Device (SN: 00000000)',
    active: true,
    queriesCount: 4,
    description: 'Masks phone model and serial reported to connected computers via USB (F1004).'
  },
  {
    key: 'esim_eid',
    label: 'eSIM EID Identifier',
    category: 'SIM' as const,
    currentRealValue: '89049032000001000000031892110294',
    fakeValue: '89049032000000000000000000000000',
    active: true,
    queriesCount: 15,
    description: 'Masks embedded UICC 32-digit identifier reported to unprivileged apps (F1005).'
  }
];
