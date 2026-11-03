/**
 * Section 12 Guide Library & Section 14 About Content
 * Preserves the exact voice and requirements from STATE.md
 */

export interface GuideItem {
  id: string;
  title: string;
  subtitle: string;
  category: 'Phone Freedom' | 'Networking' | 'Emergency & Mesh' | 'Legal Boundaries';
  readTime: string;
  content: string;
  keyTakeaways: string[];
  externalKeywords: string[];
}

export const SERF_GUIDES: GuideItem[] = [
  {
    id: 'invisible-phone-stack',
    title: 'The Invisible Phone Stack',
    subtitle: 'A phone structurally unable to leak your identity to the network it runs on',
    category: 'Phone Freedom',
    readTime: '3 min read',
    content: `Picture a phone that's structurally unable to leak your identity to the network it's running on: no Google account was ever created to leak, and no cell radio identifier ever gets matched to a phone call. That's not science fiction; people are running it today with three off-the-shelf pieces.

The three pieces:
1. A de-Googled phone: AOSP-based, running GrapheneOS, CalyxOS, or LineageOS, with microG standing in for Google's push-notification plumbing without ever creating a real Google account.
2. A no-KYC VoIP calling service: your calls route over the internet via SIP rather than a cellular voice trunk, so the IMSI/IMEI identifiers that normally ride along with every call never exist for that call.
3. A data-only eSIM with no phone number attached: pure internet access with nothing for the PSTN's legacy surveillance machinery to hang metadata on.

Why it's exciting: stacked together, a call placed this way never touches the parts of the phone network built for mass surveillance. It's not hidden traffic—it's traffic that was never generated in the first place. Two people on the same kind of setup can even call each other by internal extension, completely bypassing the public phone network, free, unlogged, with zero location or identity metadata attached.

Honest limits: reliability depends entirely on correct setup (notifications and background permissions have to be configured exactly right or the phone won't ring), it costs real money (phone, VoIP line, eSIM, recurring), and a masked relay number for giving out to contacts is still an open problem. Some services refuse VoIP numbers for 2FA, though many banks accept them fine.`,
    keyTakeaways: [
      'Zero identity-linked call records generated',
      'No PSTN tower metadata rides with SIP packets',
      'Requires intentional push-notification & battery permission setup'
    ],
    externalKeywords: ['de-Googled phone', 'no-KYC VoIP SIP service', 'data-only eSIM travel']
  },
  {
    id: 'fake-online-mechanics',
    title: 'How Fake-Online Actually Works',
    subtitle: 'Why raw TLS replay fails and how application-level synthesis keeps offline apps working',
    category: 'Networking',
    readTime: '4 min read',
    content: `Many offline games, media tools, and utilities lock their screens with a popup saying "Internet connection required". When you test them, they don't actually need the cloud—they only want to phone home to fetch ads or verify an analytics heartbeat.

Why raw TLS replay fails:
Every new HTTPS/TLS 1.3 handshake uses ephemeral Diffie-Hellman keys. You cannot simply record a previous server response and replay the raw packets, because cryptographic keys change every session.

The SERF Layered Solution (Strategic Decision D3):
- Layer 1: Fake Network Presence. The VPN loopback claims active TRANSPORT_WIFI and TRANSPORT_CELLULAR flags simultaneously.
- Layer 2: Probe Responder. When Android pings /generate_204, captive.apple.com, or connectivitycheck.gstatic.com, SERF intercepts and returns a clean HTTP 204 No Content immediately.
- Layer 3: Fail-Fast & Stubbing. Known tracking/telemetry hosts are answered with immediate TCP RST or dummy JSON stubs ({ "status": "ok", "cached": true }) rather than letting the app hang for 30 seconds.
- Layer 4: On-Device Presets. Recorded responses for popular apps allow the UI to load seamlessly while completely in Airplane Mode.`,
    keyTakeaways: [
      'Defeats artificial internet-requirement popups',
      'Intercepts captive portal probes on loopback',
      'Never modifies other apps’ cryptographic binaries'
    ],
    externalKeywords: ['Android captive portal generate_204', 'tun2socks loopback', 'DNS stub responder']
  },
  {
    id: 'manifest-v3-immunity',
    title: 'Manifest V3 Immunity: Why DNS & VPN Layer Wins',
    subtitle: 'Why browser extension limits cannot touch network-level firewalling',
    category: 'Networking',
    readTime: '3 min read',
    content: `In August 2026, browser vendors finalized the deprecation of Manifest V2, removing full declarative request modification from tools like uBlock Origin on major browsers. Many users found their favorite ad blockers downgraded to static, capped rule counts.

SERF operates at the Android VPN and TUN network layer (Layer 3/4) and DNS resolver layer (Layer 7).

Because SERF filters all outbound system packets before they leave the virtual interface:
1. Zero browser dependency: Works across Chrome, WebView, Edge, Firefox, and every single third-party native app.
2. Undetectable by anti-adblock scripts: Web pages cannot inspect the VPN interface or query extension IDs.
3. Unlimited rule capacity: Not bound by browser-enforced 30,000 rule ceilings. SERF handles millions of domains with compressed bloom filters and radix trees.`,
    keyTakeaways: [
      'Immune to Chrome Web Store and Manifest V3 restrictions',
      'Protects in-app webviews and background SDK telemetry',
      'No extension artifacts left for fingerprinting scripts'
    ],
    externalKeywords: ['Manifest V3 declarativeNetRequest limits', 'Android VPNService architecture', 'DNS sinkholing']
  },
  {
    id: 'legal-ethical-boundaries',
    title: 'The Legal Boundary: Dual-Use vs. Forbidden',
    subtitle: 'FCC, CFAA, DMCA, and Wiretap boundaries that keep SERF safe and honest',
    category: 'Legal Boundaries',
    readTime: '4 min read',
    content: `SERF is built on an unwavering principle: general-purpose capability over your OWN device and your OWN data stream is never restricted or nannied. Altering what your own phone’s apps see or send is configuring your own personal property.

What we build:
- Intercepting, auditing, and spoofing data sent BY your own phone.
- Passive detection of Wi-Fi probes, BLE beacons, and IMSI catchers.
- Scanners and auditors scoped strictly to networks you own or administer (Decision D6).
- Defending against invasive tracking and forced online requirements.

What we permanently decline:
- Defeating Verified Boot's signature chain or cracking third-party DRM (DMCA 1201).
- License/purchase bypass on paid apps (Decision D8).
- RF jamming or unauthorized radio transmission (FCC).
- Intercepting communications of other parties on a shared network (Wiretap Act / ECPA).
- Storing or tracking personal health/biometric data (Decision D11).`,
    keyTakeaways: [
      'Complete sovereignty over your own device traffic',
      'Strict adherence to passive detection and non-interference',
      'No piracy, no jamming, no unconsenting interception'
    ],
    externalKeywords: ['DMCA 1201 interoperability exemption', 'CFAA network auditing boundary', 'ECPA wiretap']
  }
];

export const IN_APP_ABOUT = {
  appName: 'SERF',
  tagline: 'Sovereign Network Control & Privacy for Android',
  description: 'Built from passion and care for the people who use it, not to extract money from them. Free, open source, and completely local.',
  philosophy: 'Every feature ships off by default. All data stays local and encrypted. No accounts, no phone numbers, no tracking.',
  legalDisclaimer: 'SERF provides general-purpose network diagnostic, firewall, and privacy tools for your personal hardware and authorized networks. The user is solely responsible for lawful use under applicable local and federal regulations (FCC, CFAA, DMCA).'
};
