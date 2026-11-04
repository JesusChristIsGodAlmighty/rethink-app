import React, { useState } from 'react';
import { 
  X, 
  Network, 
  Wifi, 
  Radio, 
  Lock, 
  ShieldCheck, 
  Eye, 
  BookOpen, 
  Sliders, 
  CheckCircle2, 
  Layers, 
  Laptop, 
  Smartphone, 
  Server, 
  Cpu, 
  Zap, 
  Shuffle, 
  Terminal
} from 'lucide-react';

interface AdvancedNetworkModalProps {
  isOpen: boolean;
  onClose: () => void;
}

interface LanIsolatedApp {
  packageName: string;
  appName: string;
  category: 'IoT / Smart Home' | 'Media' | 'Printer' | 'Local Utility';
  lanOnlyActive: boolean;
  localPacketsCount: number;
  wanBlockedCount: number;
}

const DEFAULT_LAN_APPS: LanIsolatedApp[] = [
  {
    packageName: 'com.philips.lighting.hue2',
    appName: 'Philips Hue Bridge Controller',
    category: 'IoT / Smart Home',
    lanOnlyActive: true,
    localPacketsCount: 1420,
    wanBlockedCount: 890
  },
  {
    packageName: 'com.hp.printer.smart',
    appName: 'HP Smart Print Service',
    category: 'Printer',
    lanOnlyActive: true,
    localPacketsCount: 310,
    wanBlockedCount: 640
  },
  {
    packageName: 'com.sonos.acr2',
    appName: 'Sonos Sound System',
    category: 'Media',
    lanOnlyActive: true,
    localPacketsCount: 520,
    wanBlockedCount: 120
  },
  {
    packageName: 'org.videolan.vlc',
    appName: 'VLC Local DLNA Streamer',
    category: 'Local Utility',
    lanOnlyActive: false,
    localPacketsCount: 2840,
    wanBlockedCount: 0
  }
];

export const AdvancedNetworkModal: React.FC<AdvancedNetworkModalProps> = ({
  isOpen,
  onClose
}) => {
  const [activeTab, setActiveTab] = useState<'ttl' | 'lan_only' | 'reader' | 'wifi_lock' | 'mptcp'>('ttl');

  // F962: TTL Normalization State
  const [ttlValue, setTtlValue] = useState<number>(64);
  const [ttlNormalizedActive, setTtlNormalizedActive] = useState<boolean>(true);
  const [tetheredClientsMasked, setTetheredClientsMasked] = useState<number>(3);

  // F964: LAN-Only Matrix State
  const [lanApps, setLanApps] = useState<LanIsolatedApp[]>(DEFAULT_LAN_APPS);

  // F940: Deceptive-Capability Reader State
  const [readerUrl, setReaderUrl] = useState<string>('https://news.ycombinator.com/item?id=41920192');
  const [readerProcessed, setReaderProcessed] = useState<boolean>(false);
  const [isReaderLoading, setIsReaderLoading] = useState<boolean>(false);

  // F949: Wi-Fi Connection Lock
  const [connectionLockActive, setConnectionLockActive] = useState<boolean>(true);
  const [currentBssid, setCurrentBssid] = useState<string>('Sovereign_Haven_5GHz (RSSI: -54 dBm)');
  const [roamingThrashPrevented, setRoamingThrashPrevented] = useState<number>(47);

  // F967: Multipath TCP / Dual Transport
  const [mptcpActive, setMptcpActive] = useState<boolean>(true);
  const [cellThroughput, setCellThroughput] = useState<string>('24.2 Mbps');
  const [wifiThroughput, setWifiThroughput] = useState<string>('88.4 Mbps');

  if (!isOpen) return null;

  const handleToggleLanOnly = (packageName: string) => {
    setLanApps(prev => prev.map(a => a.packageName === packageName ? { ...a, lanOnlyActive: !a.lanOnlyActive } : a));
  };

  const handleRunReaderDisarm = () => {
    setIsReaderLoading(true);
    setTimeout(() => {
      setIsReaderLoading(false);
      setReaderProcessed(true);
    }, 700);
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-700/80 rounded-2xl w-full max-w-4xl max-h-[90vh] flex flex-col shadow-2xl overflow-hidden text-slate-100"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="px-6 py-4 border-b border-slate-800 flex items-center justify-between bg-slate-950/60">
          <div className="flex items-center gap-3">
            <div className="p-2.5 rounded-xl bg-cyan-500/10 border border-cyan-500/30 text-cyan-400">
              <Network className="w-5 h-5" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h2 className="text-lg font-bold text-white tracking-tight">Advanced Network &amp; Transport Engine</h2>
                <span className="px-2 py-0.5 text-[10px] font-semibold bg-cyan-500/20 text-cyan-300 border border-cyan-500/30 rounded">
                  F940–F970 MASTER
                </span>
              </div>
              <p className="text-xs text-slate-400 mt-0.5">
                TTL tethering normalization, LAN-only app isolation, zero-execution reader mode &amp; Wi-Fi connection-lock.
              </p>
            </div>
          </div>

          <button
            onClick={onClose}
            className="p-2 text-slate-400 hover:text-white rounded-lg hover:bg-slate-800 transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Tab Navigation */}
        <div className="flex items-center gap-1 px-6 border-b border-slate-800 bg-slate-900/50 text-xs font-semibold overflow-x-auto">
          <button
            onClick={() => setActiveTab('ttl')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'ttl' ? 'border-cyan-400 text-cyan-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Laptop className="w-3.5 h-3.5" />
            <span>TTL Normalization (F962)</span>
          </button>

          <button
            onClick={() => setActiveTab('lan_only')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'lan_only' ? 'border-cyan-400 text-cyan-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Server className="w-3.5 h-3.5" />
            <span>LAN-Only App Isolation (F964)</span>
          </button>

          <button
            onClick={() => setActiveTab('reader')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'reader' ? 'border-cyan-400 text-cyan-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <BookOpen className="w-3.5 h-3.5" />
            <span>Zero-Execution Reader (F940)</span>
          </button>

          <button
            onClick={() => setActiveTab('wifi_lock')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'wifi_lock' ? 'border-cyan-400 text-cyan-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Wifi className="w-3.5 h-3.5" />
            <span>Wi-Fi Connection-Lock (F949)</span>
          </button>

          <button
            onClick={() => setActiveTab('mptcp')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'mptcp' ? 'border-cyan-400 text-cyan-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Zap className="w-3.5 h-3.5" />
            <span>Dual-Transport Bonding (F967)</span>
          </button>
        </div>

        {/* Tab Body */}
        <div className="flex-1 overflow-y-auto p-6 space-y-6">

          {/* TAB 1: TTL NORMALIZATION */}
          {activeTab === 'ttl' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="text-sm font-bold text-white flex items-center gap-2">
                    <Laptop className="w-4 h-4 text-cyan-400" />
                    <span>IPv4 TTL &amp; IPv6 Hop-Limit Normalizer (F962)</span>
                  </h3>
                  <p className="text-xs text-slate-400 mt-1">
                    Conceals tethered laptops and tablets from carrier Deep Packet Inspection (DPI) by standardizing outbound TTL to {ttlValue}.
                  </p>
                </div>

                <div className="flex items-center gap-2">
                  <span className="text-xs font-semibold text-emerald-400">
                    {tetheredClientsMasked} Tethered Devices Cloaked
                  </span>
                  <button
                    onClick={() => setTtlNormalizedActive(!ttlNormalizedActive)}
                    className={`px-3 py-1.5 rounded-lg text-xs font-bold transition-colors ${
                      ttlNormalizedActive ? 'bg-emerald-500 text-slate-950 hover:bg-emerald-400' : 'bg-slate-800 text-slate-400'
                    }`}
                  >
                    {ttlNormalizedActive ? 'TTL NORMALIZED' : 'BYPASS'}
                  </button>
                </div>
              </div>

              {/* TTL Selector Grid */}
              <div className="grid grid-cols-1 md:grid-cols-3 gap-3">
                {[
                  { val: 64, label: 'TTL = 64 (Linux / Android Standard)', desc: 'Matches native Android stack. Completely masks tethered laptop traffic.' },
                  { val: 65, label: 'TTL = 65 (Carrier Bypass Preset)', desc: 'Accounts for upstream router hop decrementing packet to 64.' },
                  { val: 128, label: 'TTL = 128 (Windows Desktop Mode)', desc: 'Conceals phone identity as a native Windows desktop workstation.' }
                ].map(opt => (
                  <button
                    key={opt.val}
                    onClick={() => setTtlValue(opt.val)}
                    className={`p-4 rounded-xl border text-left transition-all ${
                      ttlValue === opt.val
                        ? 'bg-cyan-950/30 border-cyan-500/50 text-white'
                        : 'bg-slate-950/40 border-slate-800 text-slate-400 hover:text-slate-200'
                    }`}
                  >
                    <div className="font-bold text-xs text-cyan-300">{opt.label}</div>
                    <div className="text-[11px] text-slate-400 mt-1">{opt.desc}</div>
                  </button>
                ))}
              </div>

              {/* How it works */}
              <div className="p-4 rounded-xl bg-slate-950/40 border border-slate-800/80 text-xs text-slate-300 space-y-2">
                <span className="font-bold text-slate-200 uppercase tracking-wider text-[10px]">The Mechanism:</span>
                <p className="text-slate-400 leading-relaxed">
                  When a tethered laptop sends a packet through your phone's hotspot, the IP packet's Time-To-Live (TTL) is decremented by 1 (e.g. 128 becomes 127). Carriers inspect this decrement to flag hotspot data and enforce bandwidth caps. SERF's netfilter loopback rewrites all outbound packets to a fixed TTL of 64 at Layer 3, rendering hotspot traffic indistinguishable from native on-device apps.
                </p>
              </div>
            </div>
          )}

          {/* TAB 2: LAN-ONLY APP ISOLATION */}
          {activeTab === 'lan_only' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="text-sm font-bold text-white flex items-center gap-2">
                    <Server className="w-4 h-4 text-emerald-400" />
                    <span>LAN-Only App Isolation Matrix (F964)</span>
                  </h3>
                  <p className="text-xs text-slate-400 mt-1">
                    Allow smart home controllers, printers, and media players to access local IP subnets (192.168.x.x) while severing all outbound WAN/Internet traffic.
                  </p>
                </div>
              </div>

              {/* App List */}
              <div className="space-y-2.5">
                {lanApps.map(app => (
                  <div 
                    key={app.packageName}
                    className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 flex flex-col md:flex-row md:items-center justify-between gap-4"
                  >
                    <div className="flex items-start gap-3">
                      <div className="p-2 rounded-lg bg-slate-800 border border-slate-700 text-slate-300 mt-0.5">
                        <Smartphone className="w-4 h-4" />
                      </div>
                      <div>
                        <div className="flex items-center gap-2">
                          <h4 className="text-sm font-semibold text-slate-100">{app.appName}</h4>
                          <span className="text-[10px] px-2 py-0.5 rounded bg-slate-800 text-slate-400 font-mono">
                            {app.category}
                          </span>
                        </div>
                        <div className="text-xs text-slate-400 font-mono mt-0.5">
                          {app.packageName} · Local: {app.localPacketsCount} pkts · WAN Dropped: {app.wanBlockedCount} pkts
                        </div>
                      </div>
                    </div>

                    <div className="flex items-center gap-3">
                      <button
                        onClick={() => handleToggleLanOnly(app.packageName)}
                        className={`px-3 py-1.5 rounded-lg text-xs font-bold transition-colors ${
                          app.lanOnlyActive 
                            ? 'bg-emerald-500/20 text-emerald-300 border border-emerald-500/40' 
                            : 'bg-slate-800 text-slate-400 hover:text-white'
                        }`}
                      >
                        {app.lanOnlyActive ? 'LAN ONLY (WAN BLOCKED)' : 'FULL INTERNET ALLOWED'}
                      </button>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* TAB 3: ZERO-EXECUTION READER */}
          {activeTab === 'reader' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                <h3 className="text-sm font-bold text-white flex items-center gap-2">
                  <BookOpen className="w-4 h-4 text-purple-400" />
                  <span>Deceptive-Capability Reader Mode (F940–F943)</span>
                </h3>
                <p className="text-xs text-slate-400 mt-1">
                  Claims full desktop Chrome capabilities via User-Agent and headers to receive the complete server document, then never instantiates a JavaScript engine. Parses raw HTML as pure static text with a Zero-Execution Guarantee (F941).
                </p>

                <div className="mt-4 flex gap-2">
                  <input
                    type="text"
                    value={readerUrl}
                    onChange={(e) => setReaderUrl(e.target.value)}
                    placeholder="Enter article or web URL..."
                    className="flex-1 bg-slate-900 border border-slate-700/80 rounded-xl px-3.5 py-2 text-xs text-slate-100 focus:outline-none focus:border-purple-500"
                  />
                  <button
                    onClick={handleRunReaderDisarm}
                    disabled={isReaderLoading}
                    className="px-4 py-2 bg-purple-600 hover:bg-purple-500 disabled:opacity-50 text-white font-bold text-xs rounded-xl transition-all shadow-sm flex items-center gap-1.5"
                  >
                    <Zap className={`w-3.5 h-3.5 ${isReaderLoading ? 'animate-spin' : ''}`} />
                    <span>{isReaderLoading ? 'Sanitizing...' : 'Disarm Web Page'}</span>
                  </button>
                </div>
              </div>

              {readerProcessed && (
                <div className="p-5 rounded-xl bg-slate-950/60 border border-purple-500/40 space-y-3 animate-in fade-in">
                  <div className="flex items-center justify-between text-xs">
                    <span className="font-bold text-purple-300 flex items-center gap-1.5">
                      <CheckCircle2 className="w-4 h-4 text-emerald-400" />
                      <span>Zero-Execution Guarantee Enforced</span>
                    </span>
                    <span className="font-mono text-slate-500">0 Scripts Executed · 28 Trackers Neutralized</span>
                  </div>

                  <div className="p-4 bg-slate-900/80 rounded-xl border border-slate-800 font-sans text-xs text-slate-300 leading-relaxed space-y-2">
                    <h4 className="font-bold text-white text-sm">Disarmed Static Reader Stream: Hacker News Dispatch</h4>
                    <p className="text-slate-400">
                      "Sovereignty over user hardware is the defining civil liberties battle of the 2020s. When mobile operating systems convert user phones into telemetry appliances, client-side filtering and netfilter loopbacks represent the natural restoration of personal ownership."
                    </p>
                    <div className="pt-2 border-t border-slate-800 flex items-center gap-3 font-mono text-[10px] text-emerald-400">
                      <span>✓ Google Analytics 4 Stripped</span>
                      <span>✓ Facebook Pixel Dropped</span>
                      <span>✓ FingerprintJS Blocked</span>
                    </div>
                  </div>
                </div>
              )}
            </div>
          )}

          {/* TAB 4: WI-FI CONNECTION LOCK */}
          {activeTab === 'wifi_lock' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="text-sm font-bold text-white flex items-center gap-2">
                    <Wifi className="w-4 h-4 text-cyan-400" />
                    <span>Saved-Network Connection-Lock &amp; Thrash Shield (F949–F952)</span>
                  </h3>
                  <p className="text-xs text-slate-400 mt-1">
                    Prevents Android from thrashing between multiple saved Wi-Fi networks in crowded areas, saving battery and eliminating packet drops.
                  </p>
                </div>
                <button
                  onClick={() => setConnectionLockActive(!connectionLockActive)}
                  className={`px-3 py-1.5 rounded-lg text-xs font-bold transition-colors ${
                    connectionLockActive ? 'bg-cyan-500 text-slate-950 hover:bg-cyan-400' : 'bg-slate-800 text-slate-400'
                  }`}
                >
                  {connectionLockActive ? 'CONNECTION LOCKED' : 'AUTO ROAMING'}
                </button>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 space-y-2">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Locked Access Point</span>
                  <div className="text-base font-bold text-emerald-400 mt-1">{currentBssid}</div>
                  <div className="text-xs text-slate-400">Background reassociation scanning held in idle state</div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 space-y-2">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Thrash Cycles Prevented</span>
                  <div className="text-2xl font-bold text-cyan-400 mt-1">{roamingThrashPrevented}</div>
                  <div className="text-xs text-slate-400">Conserves ~8% battery per hour in dense RF zones</div>
                </div>
              </div>
            </div>
          )}

          {/* TAB 5: DUAL TRANSPORT MPTCP */}
          {activeTab === 'mptcp' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="text-sm font-bold text-white flex items-center gap-2">
                    <Zap className="w-4 h-4 text-emerald-400" />
                    <span>Dual-Transport Capability &amp; MPTCP Bonding (F861 / F967)</span>
                  </h3>
                  <p className="text-xs text-slate-400 mt-1">
                    Hooks TRANSPORT_CELLULAR + TRANSPORT_WIFI simultaneously. Seamless failover and aggregate packet bonding.
                  </p>
                </div>
                <button
                  onClick={() => setMptcpActive(!mptcpActive)}
                  className={`px-3 py-1.5 rounded-lg text-xs font-bold transition-colors ${
                    mptcpActive ? 'bg-emerald-500 text-slate-950 hover:bg-emerald-400' : 'bg-slate-800 text-slate-400'
                  }`}
                >
                  {mptcpActive ? 'BONDING ACTIVE' : 'SINGLE INTERFACE'}
                </button>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800">
                  <div className="flex items-center justify-between">
                    <span className="text-xs font-bold text-slate-300">Wi-Fi Interface (wlan0)</span>
                    <span className="text-xs font-mono text-emerald-400 font-bold">{wifiThroughput}</span>
                  </div>
                  <div className="w-full bg-slate-800 h-1.5 rounded-full mt-3 overflow-hidden">
                    <div className="bg-emerald-400 h-full rounded-full" style={{ width: '78%' }} />
                  </div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800">
                  <div className="flex items-center justify-between">
                    <span className="text-xs font-bold text-slate-300">Cellular Interface (rmnet0)</span>
                    <span className="text-xs font-mono text-cyan-400 font-bold">{cellThroughput}</span>
                  </div>
                  <div className="w-full bg-slate-800 h-1.5 rounded-full mt-3 overflow-hidden">
                    <div className="bg-cyan-400 h-full rounded-full" style={{ width: '22%' }} />
                  </div>
                </div>
              </div>
            </div>
          )}

        </div>

        {/* Footer */}
        <div className="px-6 py-3 border-t border-slate-800 bg-slate-950/80 flex items-center justify-between text-xs text-slate-400">
          <div className="flex items-center gap-2">
            <span className="w-2 h-2 rounded-full bg-cyan-400 animate-pulse" />
            <span>Advanced Network Stack Active (TTL 64 Normalization · LAN-Only Matrix)</span>
          </div>
          <button
            onClick={onClose}
            className="px-4 py-1.5 bg-slate-800 hover:bg-slate-700 text-slate-200 rounded-lg font-semibold transition-colors"
          >
            Close
          </button>
        </div>

      </div>
    </div>
  );
};
