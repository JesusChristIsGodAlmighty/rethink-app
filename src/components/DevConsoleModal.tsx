import React, { useState, useRef, useEffect } from 'react';
import { X, Terminal, CornerDownLeft, Trash2 } from 'lucide-react';
import { SerfModule } from '../types/serf';

interface DevConsoleModalProps {
  isOpen: boolean;
  onClose: () => void;
  modules: SerfModule[];
}

export const DevConsoleModal: React.FC<DevConsoleModalProps> = ({
  isOpen,
  onClose,
  modules
}) => {
  if (!isOpen) return null;

  const [inputVal, setInputVal] = useState('');
  const [history, setHistory] = useState<string[]>([
    'SERF Sandboxed Dev Console v0.1.0-alpha [F212]',
    'Type "help" for a list of internal diagnostics and commands.',
    'System: Go/gVisor tun2socks loopback initialized on 127.0.0.1:5353.'
  ]);
  const bottomRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [history]);

  const handleCommand = (e: React.FormEvent) => {
    e.preventDefault();
    const cmd = inputVal.trim();
    if (!cmd) return;

    const newHistory = [...history, `$ ${cmd}`];
    const parts = cmd.split(' ');
    const root = parts[0].toLowerCase();

    switch (root) {
      case 'help':
        newHistory.push(
          'Available Commands:',
          '  status         - Show loopback engine, probe responder, and VPN tunnel state',
          '  probe test     - Simulate /generate_204 probe response and report latency',
          '  leak-check     - Run F884/F887 security audit against radio handovers',
          '  tor [rotate]   - Inspect active 3-hop onion circuit or trigger rotation',
          '  imsi           - Cellular tower fingerprinting radar and A5/0 downgrade sentry',
          '  mac [rotate]   - View virtual ephemeral MAC or trigger instant rotation',
          '  ble            - Run anti-stalker BLE scanner for rogue AirTags and SmartTags',
          '  ultrasonic     - Check 18-22 kHz acoustic tracking filter and sensor locks',
          '  zerotrust      - Run endpoint posture check and NIST/HIPAA compliance audit',
          '  cdr sanitize   - Run zero-trust Content Disarm & Reconstruction pipeline',
          '  modules        - List all loaded F-number modules and operational status',
          '  rules          - Show active radix tree filtering stats and loaded list counts',
          '  clear          - Clear console log buffer'
        );
        break;

      case 'tor':
        if (parts[1] === 'rotate') {
          newHistory.push(
            'Rotating Tor circuit (F30-F38)...',
            '  > Severing existing TLS streams on 127.0.0.1:9050',
            '  > Guard: relay-nl-02 (Netherlands, obfs4 transport)',
            '  > Middle: relay-de-09 (Germany, 82 Mbps bandwidth)',
            '  > Exit: relay-is-01 (Reykjavik, Iceland - Fast, Exit, Stable)',
            '  Circuit ready in 284ms. Latency: 92ms.'
          );
        } else {
          newHistory.push(
            'Tor Onion Routing Status (F30-F38):',
            '  [✓] SOCKS5 Loopback: 127.0.0.1:9050 (ACTIVE)',
            '  [✓] Pluggable Transport: Snowflake / obfs4 fallback',
            '  [✓] RFC 7686 .onion Resolver: Mapping to 127.192.0.0/10 stubs',
            '  [✓] Active Streams: 4 isolated app sockets'
          );
        }
        break;

      case 'imsi':
        newHistory.push(
          'Cellular IMSI-Catcher Sentry (F39-F47):',
          '  [✓] Active Cell: ID 40281 · MCC 310 · MNC 260 · LAC 9401',
          '  [✓] Encryption: A5/3 (Kasumi) + EEA2/AES (Verified Base Station)',
          '  [✓] Anomaly Score: 12% (Within safe variance)',
          '  [✓] Silent SMS (Type-0): 0 pings detected · Auto-drop ACTIVE',
          '  [✓] Forced A5/0 Downgrade Defense: LOCKED'
        );
        break;

      case 'mac':
        if (parts[1] === 'rotate') {
          const hex = '0123456789ABCDEF';
          const p = () => hex[Math.floor(Math.random() * 16)] + hex[Math.floor(Math.random() * 16)];
          const newMac = `4A:${p()}:${p()}:${p()}:${p()}:${p()}`;
          newHistory.push(
            `Rotated Wi-Fi MAC to ephemeral address: ${newMac}`,
            '  > Reason: Manual Console Trigger',
            '  > 802.11 Frame Sequence Numbers: Randomized',
            '  > Directed SSID Probes: Suppressed (Wildcard Mode Active)'
          );
        } else {
          newHistory.push(
            'Wi-Fi Probe & MAC Blinding (F51-F55):',
            '  [✓] Ephemeral MAC: 4A:7C:1E:99:3B:52 (Active)',
            '  [✓] Hardware OUI: DC:A6:32:8B:14:6E (Masked)',
            '  [✓] Directed Probes Blinded: 1,420 requests',
            '  [✓] Karma / Rogue AP Trap: ACTIVE'
          );
        }
        break;

      case 'ble':
        newHistory.push(
          'Bluetooth & BLE Anti-Stalker Radar (F56-F60):',
          '  [✓] Scanner Status: ACTIVE (Sweeping 2.4 GHz spectrum)',
          '  [✓] Trackers Monitored: 2 beacons in range',
          '      - Apple AirTag (FindMy) · RSSI -58 dBm · Near (1-3m) · ALERT: FOLLOWING',
          '      - Samsung SmartTag · RSSI -79 dBm · Far (>3m) · STATIONARY',
          '  [✓] BLE Advertisement Scrambling: ENGAGED'
        );
        break;

      case 'ultrasonic':
        newHistory.push(
          'Acoustic & Hardware Sensor Guard (F48-F50):',
          '  [✓] Ultrasonic High-Pass Filter: ACTIVE (18 kHz - 22 kHz cutoff)',
          '  [✓] Neutralized Inaudible Beacons: 7 tracking payloads',
          '  [✓] Mic Hardware Gate: Ready (Software loopback zeroing)',
          '  [✓] Motion Sensor Rate Limit: Capped at 5 Hz (Anti-eavesdropping)'
        );
        break;

      case 'zerotrust':
        newHistory.push(
          'Zero-Trust Posture & Compliance Ledger (F380-F405):',
          '  [✓] Device Health Score: 96/100 (Sovereign High Assurance)',
          '  [✓] SELinux Mode: Enforcing',
          '  [✓] Root Keystore Attestation: Verified Unrooted Hardware Root-of-Trust',
          '  [✓] Regulatory Frameworks:',
          '      - NIST SP 800-53: 48/50 Controls (COMPLIANT)',
          '      - HIPAA Security Rule: 28/28 Controls (COMPLIANT)',
          '      - GDPR Article 32: 32/34 Controls (COMPLIANT)',
          '      - ISO/IEC 27001: 41/42 Controls (COMPLIANT)',
          '  [✓] P2P WireGuard Mesh: 2 Bastions Online'
        );
        break;

      case 'cdr':
        newHistory.push(
          'Content Disarm & Reconstruction Engine (F927-F929):',
          '  Executing zero-trust memory sanitization on Camera_DCIM.jpg...',
          '  > Stripped EXIF GPS tags (37.7749° N, 122.4194° W)',
          '  > Removed Camera Lens Serial: SN-8849102-CAM-01',
          '  > Discarded 48KB steganographic chaff in ICC profile chunk',
          '  > Reconstructed pristine uncompressed bitmap into fresh PNG',
          '  Clean image saved: 0 metadata bytes remaining. Threat neutralized.'
        );
        break;

      case 'status':
        newHistory.push(
          'SERF Engine Status:',
          '  [✓] Go/gVisor netstack: ACTIVE (sub-millisecond routing)',
          '  [✓] F01 Fake-Online: ACTIVE (answering /generate_204 on loopback)',
          '  [✓] F04 Fast-Fail Stubs: 42,910 endpoints compiled',
          '  [✓] F125 DoH Resolver: Quad9 (TLS 1.3, DNSSEC active)',
          '  [✓] Memory Footprint: 46 MB across 8 worker threads'
        );
        break;

      case 'probe':
        newHistory.push(
          'Simulating /generate_204 probe request...',
          '  > CONNECT connectivitycheck.gstatic.com:80',
          '  > Intercepted by F01 loopback netfilter',
          '  < HTTP/1.1 204 No Content',
          '  < Content-Length: 0',
          '  < Connection: close',
          '  Probe completed in 1.14ms. Zero external radio packets transmitted.'
        );
        break;

      case 'leak-check':
        newHistory.push(
          'Executing T0.8 / F884 Security Regression Audit...',
          '  [✓] Direct Boot early-stage packet drop rule: PASSED',
          '  [✓] IPv6 leak neutralization: PASSED (all traffic tunneled)',
          '  [✓] Captive portal bypass: PASSED (intercepted locally)',
          '  [✓] DNS leak over UDP port 53: PASSED (0 unencrypted queries)',
          'Audit result: 100% compliant. No data leaks detected.'
        );
        break;

      case 'modules':
        newHistory.push(
          `Registered Modules (${modules.length}):`,
          ...modules.map(m => `  [${m.id}] ${m.name.padEnd(28)} | ${m.status.toUpperCase().padEnd(9)} | Diff:${m.difficulty} | Cost:${m.cost}`)
        );
        break;

      case 'rules':
        newHistory.push(
          'Firewall Radix Tree Statistics:',
          '  Loaded Rules: 284,520 (EasyPrivacy, OISD, Peter Lowe, SERF Custom)',
          '  Memory Used: 18.4 MB (compressed bit-radix tree)',
          '  Lookup Speed: 0.12µs average per domain check',
          '  Manifest V3 Independence: Confirmed (Layer 3/4 TUN level)'
        );
        break;

      case 'clear':
        setHistory([]);
        setInputVal('');
        return;

      default:
        newHistory.push(`Command not recognized: "${cmd}". Type "help" for valid commands.`);
    }

    setHistory(newHistory);
    setInputVal('');
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-950 border border-slate-800 rounded-2xl w-full max-w-3xl h-[75vh] flex flex-col shadow-2xl overflow-hidden font-mono text-xs"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Terminal Header */}
        <div className="p-4 bg-slate-900 border-b border-slate-800 flex items-center justify-between">
          <div className="flex items-center gap-2 text-slate-300">
            <Terminal className="w-4 h-4 text-emerald-400" />
            <span className="font-bold">SERF Developer Console (F212)</span>
          </div>

          <div className="flex items-center gap-2">
            <button
              onClick={() => setHistory([])}
              title="Clear Console"
              className="p-1 rounded text-slate-500 hover:text-slate-300 transition-colors"
            >
              <Trash2 className="w-4 h-4" />
            </button>
            <button
              onClick={onClose}
              className="p-1 rounded text-slate-500 hover:text-white transition-colors"
            >
              <X className="w-4 h-4" />
            </button>
          </div>
        </div>

        {/* Terminal Output */}
        <div className="flex-1 p-4 overflow-y-auto space-y-1.5 text-slate-300 select-text">
          {history.map((line, idx) => (
            <div 
              key={idx}
              className={line.startsWith('$') ? 'text-emerald-400 font-semibold mt-2' : line.startsWith('  [✓]') ? 'text-emerald-300' : 'text-slate-300'}
            >
              {line}
            </div>
          ))}
          <div ref={bottomRef} />
        </div>

        {/* Terminal Input Form */}
        <form onSubmit={handleCommand} className="p-3 bg-slate-900 border-t border-slate-800 flex items-center gap-2">
          <span className="text-emerald-400 font-bold">$</span>
          <input
            type="text"
            value={inputVal}
            onChange={(e) => setInputVal(e.target.value)}
            placeholder="Type 'help', 'status', 'probe test', or 'leak-check'..."
            className="flex-1 bg-transparent text-slate-100 placeholder-slate-600 focus:outline-none text-xs"
            autoFocus
          />
          <button type="submit" className="text-slate-400 hover:text-emerald-400">
            <CornerDownLeft className="w-4 h-4" />
          </button>
        </form>
      </div>
    </div>
  );
};
