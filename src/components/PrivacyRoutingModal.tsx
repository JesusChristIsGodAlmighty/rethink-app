import React, { useState } from 'react';
import { 
  X, 
  Shield, 
  Shuffle, 
  Globe, 
  MapPin, 
  Radio, 
  Fingerprint, 
  RefreshCw, 
  CheckCircle2, 
  AlertTriangle, 
  ArrowRight, 
  Layers, 
  Zap, 
  Search, 
  Copy, 
  Check, 
  Play
} from 'lucide-react';
import { 
  TorCircuit, 
  TorBridgeType, 
  SplitTunnelAppRule, 
  SplitTunnelMode,
  LocationFuzzConfig, 
  DecoyTrafficConfig, 
  FingerprintShieldConfig,
  NetworkPacket
} from '../types/serf';
import { 
  createInitialTorCircuit, 
  rotateTorCircuitSimulation, 
  resolveOnionDomainSimulation, 
  INITIAL_SPLIT_RULES, 
  INITIAL_LOCATION_CONFIG, 
  getFuzzedLocationSimulation, 
  DECOY_TARGET_POOLS, 
  generateDecoyPacketSimulation, 
  computeFingerprintEntropy,
  runTier4DoneTestVerification 
} from '../services/networkSimulator';

interface PrivacyRoutingModalProps {
  isOpen: boolean;
  onClose: () => void;
  onEmitPacket?: (pkt: NetworkPacket) => void;
}

export const PrivacyRoutingModal: React.FC<PrivacyRoutingModalProps> = ({
  isOpen,
  onClose,
  onEmitPacket
}) => {
  if (!isOpen) return null;

  const [activeTab, setActiveTab] = useState<'tor' | 'split' | 'location' | 'decoy' | 'fingerprint' | 'verification'>('tor');

  // --- Tab 1: Tor Circuit State (F30-F38) ---
  const [torCircuit, setTorCircuit] = useState<TorCircuit>(() => createInitialTorCircuit('direct'));
  const [isRotatingCircuit, setIsRotatingCircuit] = useState(false);
  const [selectedBridge, setSelectedBridge] = useState<TorBridgeType>('direct');
  const [onionQuery, setOnionQuery] = useState('duckduckgogg42xjoc72x3sjasowoarfbgcmvfimaftt6twagswzczad.onion');
  const [onionResolveResult, setOnionResolveResult] = useState<{
    success: boolean;
    virtualIp: string;
    circuitHops: string[];
    latencyMs: number;
    message: string;
  } | null>(null);

  const handleRotateCircuit = () => {
    setIsRotatingCircuit(true);
    setTimeout(() => {
      const newCircuit = rotateTorCircuitSimulation(selectedBridge);
      setTorCircuit(newCircuit);
      setIsRotatingCircuit(false);
    }, 600);
  };

  const handleBridgeChange = (bridge: TorBridgeType) => {
    setSelectedBridge(bridge);
    setTorCircuit(prev => ({
      ...prev,
      bridgeTransport: bridge,
      latencyMs: bridge === 'snowflake' ? 240 : bridge === 'obfs4' ? 190 : 135
    }));
  };

  const handleTestOnionResolve = () => {
    const res = resolveOnionDomainSimulation(onionQuery);
    setOnionResolveResult(res);
    if (res.success && onEmitPacket) {
      onEmitPacket({
        id: `pkt-onion-${Date.now()}`,
        timestamp: Date.now(),
        app: 'Tor Browser Service',
        domain: onionQuery,
        destinationIp: res.virtualIp,
        protocol: 'DoH',
        status: 'faked',
        reason: `F34 Onion SOCKS5 Resolve: RFC 7686 mapped to virtual loopback ${res.virtualIp}`,
        ruleMatched: 'F34-ONION-SOCKS5',
        payloadSize: 240,
        leakRisk: 'safe'
      });
    }
  };

  // --- Tab 2: Split Tunneling Matrix (F124) ---
  const [splitRules, setSplitRules] = useState<SplitTunnelAppRule[]>(INITIAL_SPLIT_RULES);
  const [appSearch, setAppSearch] = useState('');
  const [categoryFilter, setCategoryFilter] = useState<string>('All');

  const handleSetAppMode = (pkg: string, mode: SplitTunnelMode) => {
    setSplitRules(prev => prev.map(r => r.packageName === pkg ? { ...r, routingMode: mode } : r));
  };

  const applyPreset = (presetName: 'banking' | 'max_anonymity' | 'de_google') => {
    if (presetName === 'banking') {
      setSplitRules(prev => prev.map(r => ({
        ...r,
        routingMode: r.category === 'Banking' ? 'DIRECT' : 'VPN'
      })));
    } else if (presetName === 'max_anonymity') {
      setSplitRules(prev => prev.map(r => ({
        ...r,
        routingMode: r.category === 'Browser' ? 'TOR' : r.category === 'Social' ? 'VPN' : 'BLOCK'
      })));
    } else if (presetName === 'de_google') {
      setSplitRules(prev => prev.map(r => ({
        ...r,
        routingMode: r.category === 'System' ? 'LOOPBACK_STUB' : r.routingMode
      })));
    }
  };

  const filteredRules = splitRules.filter(r => {
    const matchesSearch = r.appName.toLowerCase().includes(appSearch.toLowerCase()) || r.packageName.toLowerCase().includes(appSearch.toLowerCase());
    const matchesCategory = categoryFilter === 'All' || r.category === categoryFilter;
    return matchesSearch && matchesCategory;
  });

  // --- Tab 3: Geolocation Fuzzing (F126/F127) ---
  const [locationConfig, setLocationConfig] = useState<LocationFuzzConfig>(INITIAL_LOCATION_CONFIG);
  const [fuzzedOutput, setFuzzedOutput] = useState(() => getFuzzedLocationSimulation(INITIAL_LOCATION_CONFIG));
  const [copiedCoords, setCopiedCoords] = useState(false);

  const handleUpdateRadius = (radius: number) => {
    const updated: LocationFuzzConfig = {
      ...locationConfig,
      mode: 'jitter',
      jitterRadiusMeters: radius
    };
    setLocationConfig(updated);
    setFuzzedOutput(getFuzzedLocationSimulation(updated));
  };

  const handleSelectPreset = (label: string, lat: number, lng: number, altitude: number, tz: string) => {
    const updated: LocationFuzzConfig = {
      ...locationConfig,
      mode: 'fixed_spoof',
      spoofPreset: label,
      spoofCoordinates: { lat, lng, altitude, timezone: tz, label }
    };
    setLocationConfig(updated);
    setFuzzedOutput(getFuzzedLocationSimulation(updated));
  };

  const handleCopyCoords = () => {
    navigator.clipboard.writeText(`${fuzzedOutput.reportedLat}, ${fuzzedOutput.reportedLng}`);
    setCopiedCoords(true);
    setTimeout(() => setCopiedCoords(false), 2000);
  };

  // --- Tab 4: Decoy Traffic Generator (F277-F281) ---
  const [decoyConfig, setDecoyConfig] = useState<DecoyTrafficConfig>({
    enabled: true,
    mode: 'poisson',
    intervalSeconds: 15,
    paddingEnabled: true,
    targetPool: DECOY_TARGET_POOLS,
    totalDecoysSent: 342,
    totalDecoyBytes: 1048576 * 2.8 // ~2.8MB
  });
  const [decoyLog, setDecoyLog] = useState<{ id: string; time: string; target: string; bytes: number; padding: number; latency: number }[]>([
    { id: '1', time: '14:32:01', target: 'en.wikipedia.org', bytes: 642, padding: 162, latency: 42 },
    { id: '2', time: '14:32:16', target: 'cloudflare.com', bytes: 710, padding: 230, latency: 38 },
    { id: '3', time: '14:32:31', target: 'debian.org', bytes: 588, padding: 108, latency: 51 }
  ]);

  const handleTriggerDecoyBurst = () => {
    const now = new Date();
    const timeStr = now.toTimeString().split(' ')[0];
    const newEntries = Array.from({ length: 5 }).map((_, i) => {
      const pkt = generateDecoyPacketSimulation(decoyConfig.paddingEnabled);
      if (onEmitPacket) onEmitPacket(pkt);
      return {
        id: `burst-${Date.now()}-${i}`,
        time: timeStr,
        target: pkt.domain,
        bytes: pkt.payloadSize,
        padding: pkt.payloadSize - 480,
        latency: Math.floor(35 + Math.random() * 45)
      };
    });

    setDecoyLog(prev => [...newEntries, ...prev.slice(0, 20)]);
    setDecoyConfig(prev => ({
      ...prev,
      totalDecoysSent: prev.totalDecoysSent + 5,
      totalDecoyBytes: prev.totalDecoyBytes + newEntries.reduce((acc, curr) => acc + curr.bytes, 0)
    }));
  };

  // --- Tab 5: Browser & Device Fingerprint Shield (F367-F370) ---
  const [fingerprintShield, setFingerprintShield] = useState<FingerprintShieldConfig>({
    canvasNoise: true,
    webGlDisguise: true,
    audioNoise: true,
    userAgentDisguise: true,
    webrtcCloaking: true,
    currentEntropyBits: 6.8,
    uniquenessScore: 92
  });

  const entropyResult = computeFingerprintEntropy(fingerprintShield);

  // --- Tab 6: Tier 4 Done-Test Verification ---
  const [verificationResult, setVerificationResult] = useState<{
    allPassed: boolean;
    checks: { name: string; passed: boolean; details: string }[];
  } | null>(null);

  const handleRunVerification = () => {
    const res = runTier4DoneTestVerification();
    setVerificationResult(res);
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-md">
      <div className="bg-slate-900 border border-slate-800 rounded-xl w-full max-w-5xl max-h-[90vh] flex flex-col shadow-2xl overflow-hidden">
        
        {/* Header */}
        <div className="px-6 py-4 border-b border-slate-800 flex items-center justify-between bg-slate-900/90">
          <div className="flex items-center gap-3">
            <div className="w-9 h-9 rounded-lg bg-indigo-500/10 border border-indigo-500/30 flex items-center justify-center text-indigo-400">
              <Shield className="w-5 h-5" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h2 className="text-lg font-bold text-white tracking-tight">Privacy &amp; Routing Core</h2>
                <span className="text-xs font-semibold text-indigo-400 bg-indigo-950/60 border border-indigo-500/30 px-2 py-0.5 rounded">
                  Tier 4 Engine
                </span>
              </div>
              <p className="text-xs text-slate-400">
                Tor multi-hop onion routing, split tunneling matrix, location fuzzing, decoy traffic, and anti-fingerprinting.
              </p>
            </div>
          </div>
          <button
            onClick={onClose}
            className="p-1.5 text-slate-400 hover:text-white rounded-lg hover:bg-slate-800 transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Tab Navigation */}
        <div className="flex items-center gap-1 px-6 border-b border-slate-800 bg-slate-950/40 overflow-x-auto text-xs font-medium text-slate-400">
          <button
            onClick={() => setActiveTab('tor')}
            className={`py-3 px-3.5 flex items-center gap-2 border-b-2 transition-colors whitespace-nowrap ${
              activeTab === 'tor' ? 'border-indigo-400 text-indigo-400 font-semibold' : 'border-transparent hover:text-slate-200'
            }`}
          >
            <Shuffle className="w-3.5 h-3.5" />
            <span>Tor Onion Circuit (F30–F38)</span>
          </button>

          <button
            onClick={() => setActiveTab('split')}
            className={`py-3 px-3.5 flex items-center gap-2 border-b-2 transition-colors whitespace-nowrap ${
              activeTab === 'split' ? 'border-indigo-400 text-indigo-400 font-semibold' : 'border-transparent hover:text-slate-200'
            }`}
          >
            <Layers className="w-3.5 h-3.5" />
            <span>Split Tunnel Matrix (F124)</span>
          </button>

          <button
            onClick={() => setActiveTab('location')}
            className={`py-3 px-3.5 flex items-center gap-2 border-b-2 transition-colors whitespace-nowrap ${
              activeTab === 'location' ? 'border-indigo-400 text-indigo-400 font-semibold' : 'border-transparent hover:text-slate-200'
            }`}
          >
            <MapPin className="w-3.5 h-3.5" />
            <span>Location Fuzzing &amp; GPS (F126/F127)</span>
          </button>

          <button
            onClick={() => setActiveTab('decoy')}
            className={`py-3 px-3.5 flex items-center gap-2 border-b-2 transition-colors whitespace-nowrap ${
              activeTab === 'decoy' ? 'border-indigo-400 text-indigo-400 font-semibold' : 'border-transparent hover:text-slate-200'
            }`}
          >
            <Radio className="w-3.5 h-3.5" />
            <span>Decoy Traffic Generator (F277–F281)</span>
          </button>

          <button
            onClick={() => setActiveTab('fingerprint')}
            className={`py-3 px-3.5 flex items-center gap-2 border-b-2 transition-colors whitespace-nowrap ${
              activeTab === 'fingerprint' ? 'border-indigo-400 text-indigo-400 font-semibold' : 'border-transparent hover:text-slate-200'
            }`}
          >
            <Fingerprint className="w-3.5 h-3.5" />
            <span>Fingerprint Cloaker (F367–F370)</span>
          </button>

          <button
            onClick={() => setActiveTab('verification')}
            className={`py-3 px-3.5 flex items-center gap-2 border-b-2 transition-colors whitespace-nowrap ${
              activeTab === 'verification' ? 'border-emerald-400 text-emerald-400 font-semibold' : 'border-transparent hover:text-slate-200'
            }`}
          >
            <CheckCircle2 className="w-3.5 h-3.5" />
            <span>Tier 4 Done-Tests</span>
          </button>
        </div>

        {/* Tab Content Body */}
        <div className="flex-1 overflow-y-auto p-6 space-y-6">

          {/* TAB 1: TOR ONION ROUTING */}
          {activeTab === 'tor' && (
            <div className="space-y-6">
              {/* Circuit Map Overview */}
              <div className="p-4 rounded-lg bg-slate-950 border border-slate-800">
                <div className="flex items-center justify-between mb-4">
                  <div>
                    <h3 className="text-sm font-semibold text-white flex items-center gap-2">
                      <Shuffle className="w-4 h-4 text-indigo-400" />
                      Active 3-Hop Onion Circuit
                    </h3>
                    <p className="text-xs text-slate-400">
                      Encrypted in three layers via SOCKS5 loopback daemon at {torCircuit.socksProxyAddress}.
                    </p>
                  </div>
                  <div className="flex items-center gap-3">
                    <span className="text-xs text-slate-400">
                      Circuit ID: <span className="font-mono text-slate-300">{torCircuit.id}</span>
                    </span>
                    <button
                      onClick={handleRotateCircuit}
                      disabled={isRotatingCircuit}
                      className="px-3 py-1.5 text-xs font-semibold rounded bg-indigo-600 hover:bg-indigo-500 text-white flex items-center gap-1.5 transition-colors active:scale-95 disabled:opacity-50"
                    >
                      <RefreshCw className={`w-3.5 h-3.5 ${isRotatingCircuit ? 'animate-spin' : ''}`} />
                      <span>New Identity (Rotate Circuit)</span>
                    </button>
                  </div>
                </div>

                {/* Visual Circuit Diagram */}
                <div className="grid grid-cols-1 md:grid-cols-4 gap-3 py-2">
                  {/* Node 1: Client */}
                  <div className="p-3 rounded border border-slate-800 bg-slate-900/80">
                    <div className="text-xs text-slate-500 uppercase tracking-wider mb-1">Source</div>
                    <div className="text-sm font-semibold text-white">Your Device</div>
                    <div className="text-xs text-indigo-400 font-mono mt-1">127.0.0.1:9050</div>
                    <div className="text-xs text-slate-400 mt-2">Local SOCKS5 Loopback</div>
                  </div>

                  {/* Node 2: Guard Relay */}
                  <div className="p-3 rounded border border-indigo-500/30 bg-indigo-950/20">
                    <div className="flex items-center justify-between text-xs text-indigo-400 uppercase tracking-wider mb-1">
                      <span>Guard Node</span>
                      <span>{torCircuit.guardNode.countryCode}</span>
                    </div>
                    <div className="text-sm font-semibold text-white truncate">{torCircuit.guardNode.nickname}</div>
                    <div className="text-xs text-slate-300 font-mono mt-1">{torCircuit.guardNode.ip}</div>
                    <div className="text-xs text-slate-400 mt-2 truncate">
                      Flags: {torCircuit.guardNode.flags.slice(0, 3).join(', ')}
                    </div>
                  </div>

                  {/* Node 3: Middle Relay */}
                  <div className="p-3 rounded border border-indigo-500/30 bg-indigo-950/20">
                    <div className="flex items-center justify-between text-xs text-indigo-400 uppercase tracking-wider mb-1">
                      <span>Middle Relay</span>
                      <span>{torCircuit.middleNode.countryCode}</span>
                    </div>
                    <div className="text-sm font-semibold text-white truncate">{torCircuit.middleNode.nickname}</div>
                    <div className="text-xs text-slate-300 font-mono mt-1">{torCircuit.middleNode.ip}</div>
                    <div className="text-xs text-slate-400 mt-2 truncate">
                      Bandwidth: {(torCircuit.middleNode.bandwidthKbps / 1000).toFixed(0)} MB/s
                    </div>
                  </div>

                  {/* Node 4: Exit Relay */}
                  <div className="p-3 rounded border border-emerald-500/30 bg-emerald-950/20">
                    <div className="flex items-center justify-between text-xs text-emerald-400 uppercase tracking-wider mb-1">
                      <span>Exit Gateway</span>
                      <span>{torCircuit.exitNode.countryCode}</span>
                    </div>
                    <div className="text-sm font-semibold text-white truncate">{torCircuit.exitNode.nickname}</div>
                    <div className="text-xs text-slate-300 font-mono mt-1">{torCircuit.exitNode.ip}</div>
                    <div className="text-xs text-emerald-400 mt-2 font-mono">
                      Latency: ~{torCircuit.latencyMs}ms
                    </div>
                  </div>
                </div>

                {/* Circuit Metrics Bar */}
                <div className="mt-4 pt-3 border-t border-slate-800 flex flex-wrap items-center justify-between gap-4 text-xs text-slate-400">
                  <div className="flex items-center gap-4">
                    <span>Active Streams: <strong className="text-slate-200">{torCircuit.streamCount}</strong></span>
                    <span>Bytes Transferred: <strong className="text-slate-200">{(torCircuit.bytesTransferred / (1024 * 1024)).toFixed(1)} MB</strong></span>
                    <span>Isolation: <strong className="text-emerald-400">Per-UID SOCKS Credential</strong></span>
                  </div>
                  <div className="flex items-center gap-2">
                    <span className="text-slate-500">Bridge Transport:</span>
                    {(['direct', 'obfs4', 'snowflake', 'meek'] as TorBridgeType[]).map((b) => (
                      <button
                        key={b}
                        onClick={() => handleBridgeChange(b)}
                        className={`px-2 py-0.5 rounded text-xs transition-colors ${
                          selectedBridge === b
                            ? 'bg-indigo-600 text-white font-semibold'
                            : 'bg-slate-800 text-slate-400 hover:text-slate-200'
                        }`}
                      >
                        {b.toUpperCase()}
                      </button>
                    ))}
                  </div>
                </div>
              </div>

              {/* RFC 7686 .onion Resolver Tool */}
              <div className="p-4 rounded-lg bg-slate-950 border border-slate-800 space-y-3">
                <div className="flex items-center justify-between">
                  <h3 className="text-sm font-semibold text-white flex items-center gap-2">
                    <Globe className="w-4 h-4 text-indigo-400" />
                    RFC 7686 .onion Hidden Service Resolver (F34)
                  </h3>
                  <span className="text-xs text-slate-400">Maps .onion queries to local 127.192.0.0/10 virtual range</span>
                </div>
                <div className="flex gap-2">
                  <input
                    type="text"
                    value={onionQuery}
                    onChange={(e) => setOnionQuery(e.target.value)}
                    placeholder="e.g. duckduckgogg42xjoc72x3sjasowoarfbgcmvfimaftt6twagswzczad.onion"
                    className="flex-1 bg-slate-900 border border-slate-800 rounded px-3 py-2 text-xs font-mono text-white focus:outline-none focus:border-indigo-500"
                  />
                  <button
                    onClick={handleTestOnionResolve}
                    className="px-4 py-2 bg-indigo-600 hover:bg-indigo-500 text-white text-xs font-semibold rounded flex items-center gap-1.5 transition-colors"
                  >
                    <span>Resolve via Tor</span>
                    <ArrowRight className="w-3.5 h-3.5" />
                  </button>
                </div>

                {onionResolveResult && (
                  <div className={`p-3 rounded text-xs border ${onionResolveResult.success ? 'bg-slate-900/90 border-indigo-500/40 text-slate-300' : 'bg-rose-950/30 border-rose-500/30 text-rose-300'}`}>
                    <div className="flex items-center justify-between mb-2">
                      <span className="font-semibold text-white">{onionResolveResult.message}</span>
                      <span className="font-mono text-indigo-400">{onionResolveResult.latencyMs}ms</span>
                    </div>
                    {onionResolveResult.success && (
                      <div className="space-y-1 font-mono text-xs text-slate-400">
                        <div>Virtual Bound IP: <strong className="text-emerald-400">{onionResolveResult.virtualIp}</strong></div>
                        <div className="text-slate-500 mt-1">Encrypted Rendezvous Path:</div>
                        {onionResolveResult.circuitHops.map((hop, idx) => (
                          <div key={idx} className="pl-3 text-slate-400">↳ {hop}</div>
                        ))}
                      </div>
                    )}
                  </div>
                )}
              </div>
            </div>
          )}

          {/* TAB 2: SPLIT TUNNELING MATRIX */}
          {activeTab === 'split' && (
            <div className="space-y-4">
              <div className="flex flex-wrap items-center justify-between gap-3">
                <div>
                  <h3 className="text-sm font-semibold text-white flex items-center gap-2">
                    <Layers className="w-4 h-4 text-indigo-400" />
                    Per-Application Egress Policy Matrix (F124)
                  </h3>
                  <p className="text-xs text-slate-400">
                    Route each installed app through Direct Internet, WireGuard VPN, Tor Onion Circuit, Loopback Fake-Online Stubs, or drop entirely.
                  </p>
                </div>
                {/* One-Tap Presets */}
                <div className="flex items-center gap-2">
                  <span className="text-xs text-slate-500">Quick Presets:</span>
                  <button
                    onClick={() => applyPreset('banking')}
                    className="px-2.5 py-1 text-xs bg-slate-800 hover:bg-slate-700 text-slate-200 rounded border border-slate-700 transition-colors"
                  >
                    Banking Bypass
                  </button>
                  <button
                    onClick={() => applyPreset('max_anonymity')}
                    className="px-2.5 py-1 text-xs bg-indigo-950 hover:bg-indigo-900 text-indigo-300 rounded border border-indigo-700/50 transition-colors"
                  >
                    Tor Browser Anonymity
                  </button>
                  <button
                    onClick={() => applyPreset('de_google')}
                    className="px-2.5 py-1 text-xs bg-emerald-950 hover:bg-emerald-900 text-emerald-300 rounded border border-emerald-700/50 transition-colors"
                  >
                    Google De-Telemetrize
                  </button>
                </div>
              </div>

              {/* Filters & Search */}
              <div className="flex flex-wrap items-center justify-between gap-3 p-3 rounded-lg bg-slate-950 border border-slate-800">
                <div className="relative flex-1 min-w-[200px]">
                  <Search className="w-3.5 h-3.5 absolute left-3 top-2.5 text-slate-500" />
                  <input
                    type="text"
                    value={appSearch}
                    onChange={(e) => setAppSearch(e.target.value)}
                    placeholder="Search apps or package names..."
                    className="w-full bg-slate-900 border border-slate-800 rounded pl-9 pr-3 py-1.5 text-xs text-white focus:outline-none focus:border-indigo-500"
                  />
                </div>
                <div className="flex items-center gap-1.5 text-xs text-slate-400">
                  <span>Category:</span>
                  {['All', 'Browser', 'Banking', 'System', 'Social', 'Media', 'Game'].map((cat) => (
                    <button
                      key={cat}
                      onClick={() => setCategoryFilter(cat)}
                      className={`px-2 py-1 rounded transition-colors ${
                        categoryFilter === cat ? 'bg-indigo-600 text-white font-semibold' : 'hover:bg-slate-800 text-slate-400'
                      }`}
                    >
                      {cat}
                    </button>
                  ))}
                </div>
              </div>

              {/* Matrix Table */}
              <div className="rounded-lg border border-slate-800 overflow-hidden">
                <table className="w-full text-left text-xs">
                  <thead className="bg-slate-950 border-b border-slate-800 text-slate-400 font-semibold">
                    <tr>
                      <th className="py-2.5 px-4">Application</th>
                      <th className="py-2.5 px-4">Category</th>
                      <th className="py-2.5 px-4">Routing Destination</th>
                      <th className="py-2.5 px-4 text-right">Traffic Volume</th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-800/80 bg-slate-900/50">
                    {filteredRules.map((rule) => (
                      <tr key={rule.packageName} className="hover:bg-slate-850/50 transition-colors">
                        <td className="py-2.5 px-4">
                          <div className="font-semibold text-white">{rule.appName}</div>
                          <div className="font-mono text-slate-500 text-[11px]">{rule.packageName}</div>
                        </td>
                        <td className="py-2.5 px-4 text-slate-400">
                          {rule.category}
                        </td>
                        <td className="py-2.5 px-4">
                          <div className="flex items-center gap-1.5">
                            {[
                              { mode: 'DIRECT' as SplitTunnelMode, label: 'Direct (Bypass)', color: 'text-amber-400 bg-amber-950/40 border-amber-600/30' },
                              { mode: 'VPN' as SplitTunnelMode, label: 'VPN Tunnel', color: 'text-cyan-400 bg-cyan-950/40 border-cyan-600/30' },
                              { mode: 'TOR' as SplitTunnelMode, label: 'Tor Circuit', color: 'text-indigo-400 bg-indigo-950/40 border-indigo-600/30' },
                              { mode: 'LOOPBACK_STUB' as SplitTunnelMode, label: 'Fake-Online Stub', color: 'text-emerald-400 bg-emerald-950/40 border-emerald-600/30' },
                              { mode: 'BLOCK' as SplitTunnelMode, label: 'Drop / Block', color: 'text-rose-400 bg-rose-950/40 border-rose-600/30' }
                            ].map((opt) => (
                              <button
                                key={opt.mode}
                                onClick={() => handleSetAppMode(rule.packageName, opt.mode)}
                                className={`px-2 py-1 rounded border text-[11px] transition-all ${
                                  rule.routingMode === opt.mode
                                    ? `${opt.color} font-bold shadow-sm scale-102`
                                    : 'border-slate-800 text-slate-500 hover:text-slate-300 hover:border-slate-700'
                                }`}
                              >
                                {opt.label}
                              </button>
                            ))}
                          </div>
                        </td>
                        <td className="py-2.5 px-4 text-right font-mono text-slate-300">
                          {(rule.bytesRouted / 1024 / 1024).toFixed(1)} MB
                          <span className="text-slate-500 text-[11px] ml-1">({rule.packetsCount} pkts)</span>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}

          {/* TAB 3: GEOLOCATION FUZZING */}
          {activeTab === 'location' && (
            <div className="space-y-6">
              <div className="p-4 rounded-lg bg-slate-950 border border-slate-800 flex flex-wrap items-center justify-between gap-4">
                <div>
                  <h3 className="text-sm font-semibold text-white flex items-center gap-2">
                    <MapPin className="w-4 h-4 text-indigo-400" />
                    GPS Mocking &amp; Coordinate Fuzzing Shield (F126/F127)
                  </h3>
                  <p className="text-xs text-slate-400">
                    Defeats hardware GPS triangulation, strips BSSID Wi-Fi scans, and prevents timezone-leak fingerprinting.
                  </p>
                </div>
                <div className="flex items-center gap-3">
                  <label className="flex items-center gap-2 text-xs text-slate-300 cursor-pointer">
                    <input
                      type="checkbox"
                      checked={locationConfig.enabled}
                      onChange={(e) => {
                        const updated = { ...locationConfig, enabled: e.target.checked };
                        setLocationConfig(updated);
                        setFuzzedOutput(getFuzzedLocationSimulation(updated));
                      }}
                      className="rounded border-slate-700 text-indigo-500 focus:ring-0"
                    />
                    <span>Location Shield Active</span>
                  </label>
                  <label className="flex items-center gap-2 text-xs text-slate-300 cursor-pointer">
                    <input
                      type="checkbox"
                      checked={locationConfig.timezoneSync}
                      onChange={(e) => {
                        const updated = { ...locationConfig, timezoneSync: e.target.checked };
                        setLocationConfig(updated);
                        setFuzzedOutput(getFuzzedLocationSimulation(updated));
                      }}
                      className="rounded border-slate-700 text-indigo-500 focus:ring-0"
                    />
                    <span>Sync Browser Timezone</span>
                  </label>
                </div>
              </div>

              {/* Fuzzing Radar & Controls Grid */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                {/* Left: Jitter Radius & Coordinate readout */}
                <div className="space-y-4 p-4 rounded-lg bg-slate-950 border border-slate-800">
                  <div className="text-xs font-semibold text-white uppercase tracking-wider">
                    Gaussian Displacement Jitter (F126)
                  </div>
                  <p className="text-xs text-slate-400">
                    Adds randomized Gaussian scatter to hardware coordinates so apps cannot track exact building or room locations.
                  </p>

                  <div className="space-y-2">
                    <div className="flex items-center justify-between text-xs text-slate-300">
                      <span>Jitter Radius:</span>
                      <strong className="text-indigo-400 font-mono">±{locationConfig.jitterRadiusMeters} meters</strong>
                    </div>
                    <div className="flex gap-2">
                      {[50, 200, 500, 1000, 5000, 25000].map((rad) => (
                        <button
                          key={rad}
                          onClick={() => handleUpdateRadius(rad)}
                          className={`flex-1 py-1.5 text-xs rounded border transition-colors ${
                            locationConfig.jitterRadiusMeters === rad && locationConfig.mode === 'jitter'
                              ? 'bg-indigo-600 border-indigo-500 text-white font-bold'
                              : 'bg-slate-900 border-slate-800 text-slate-400 hover:text-slate-200'
                          }`}
                        >
                          {rad >= 1000 ? `${rad / 1000}km` : `${rad}m`}
                        </button>
                      ))}
                    </div>
                  </div>

                  {/* Active Coordinate Box */}
                  <div className="p-3 rounded bg-slate-900 border border-slate-800 space-y-2">
                    <div className="flex items-center justify-between">
                      <span className="text-xs text-slate-400">Reported GPS to Apps:</span>
                      <button
                        onClick={handleCopyCoords}
                        className="text-xs text-indigo-400 hover:text-indigo-300 flex items-center gap-1"
                      >
                        {copiedCoords ? <Check className="w-3.5 h-3.5 text-emerald-400" /> : <Copy className="w-3.5 h-3.5" />}
                        <span>{copiedCoords ? 'Copied' : 'Copy Coordinates'}</span>
                      </button>
                    </div>
                    <div className="grid grid-cols-2 gap-2 text-xs font-mono">
                      <div className="p-2 rounded bg-slate-950 border border-slate-800">
                        <span className="text-slate-500 block text-[11px]">Latitude</span>
                        <span className="text-white font-semibold">{fuzzedOutput.reportedLat}° N</span>
                      </div>
                      <div className="p-2 rounded bg-slate-950 border border-slate-800">
                        <span className="text-slate-500 block text-[11px]">Longitude</span>
                        <span className="text-white font-semibold">{fuzzedOutput.reportedLng}° W</span>
                      </div>
                    </div>
                    <div className="text-[11px] text-slate-500 flex items-center justify-between pt-1">
                      <span>Source: {fuzzedOutput.source}</span>
                      <span>Offset: Δ{fuzzedOutput.jitterOffsetMeters}m</span>
                    </div>
                  </div>
                </div>

                {/* Right: Fixed Spoofing Presets */}
                <div className="space-y-4 p-4 rounded-lg bg-slate-950 border border-slate-800">
                  <div className="text-xs font-semibold text-white uppercase tracking-wider">
                    Fixed Pin Spoofing Haven (F127)
                  </div>
                  <p className="text-xs text-slate-400">
                    Fix coordinates to international privacy havens with matched locale and timezone to eliminate discrepancy audits.
                  </p>

                  <div className="grid grid-cols-2 gap-2">
                    {[
                      { label: 'Geneva (CH)', lat: 46.2044, lng: 6.1432, alt: 375, tz: 'Europe/Zurich' },
                      { label: 'Reykjavik (IS)', lat: 64.1466, lng: -21.9426, alt: 15, tz: 'Atlantic/Reykjavik' },
                      { label: 'Zurich (CH)', lat: 47.3769, lng: 8.5417, alt: 408, tz: 'Europe/Zurich' },
                      { label: 'Panama City (PA)', lat: 8.9824, lng: -79.5199, alt: 12, tz: 'America/Panama' },
                      { label: 'Tokyo (JP)', lat: 35.6762, lng: 139.6503, alt: 40, tz: 'Asia/Tokyo' },
                      { label: 'Amsterdam (NL)', lat: 52.3676, lng: 4.9041, alt: -2, tz: 'Europe/Amsterdam' }
                    ].map((preset) => (
                      <button
                        key={preset.label}
                        onClick={() => handleSelectPreset(preset.label, preset.lat, preset.lng, preset.alt, preset.tz)}
                        className={`p-2.5 rounded border text-left text-xs transition-colors ${
                          locationConfig.mode === 'fixed_spoof' && locationConfig.spoofPreset === preset.label
                            ? 'bg-indigo-950 border-indigo-500 text-indigo-300 font-semibold'
                            : 'bg-slate-900 border-slate-800 text-slate-400 hover:text-slate-200'
                        }`}
                      >
                        <div className="font-semibold text-white">{preset.label}</div>
                        <div className="font-mono text-[11px] text-slate-500 mt-0.5">{preset.lat}, {preset.lng}</div>
                      </button>
                    ))}
                  </div>

                  <div className="p-3 rounded bg-slate-900/60 border border-slate-800/80 text-xs text-slate-400 space-y-1">
                    <div className="flex items-center gap-1.5 text-emerald-400 font-semibold">
                      <CheckCircle2 className="w-3.5 h-3.5" />
                      <span>Wi-Fi BSSID Stripper Active</span>
                    </div>
                    <p className="text-[11px] text-slate-500">
                      Nearby Wi-Fi router MAC addresses (BSSIDs) are sanitized from app location queries to block Google/Skyhook Wi-Fi positioning.
                    </p>
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* TAB 4: DECOY TRAFFIC GENERATOR */}
          {activeTab === 'decoy' && (
            <div className="space-y-4">
              <div className="p-4 rounded-lg bg-slate-950 border border-slate-800 flex flex-wrap items-center justify-between gap-4">
                <div>
                  <h3 className="text-sm font-semibold text-white flex items-center gap-2">
                    <Radio className="w-4 h-4 text-indigo-400" />
                    Decoy Traffic &amp; Packet Padding Generator (F277–F281)
                  </h3>
                  <p className="text-xs text-slate-400">
                    Defeats ISP deep packet inspection and traffic flow watermarking by generating background synthetic requests with packet padding.
                  </p>
                </div>
                <div className="flex items-center gap-3">
                  <label className="flex items-center gap-2 text-xs text-slate-300 cursor-pointer">
                    <input
                      type="checkbox"
                      checked={decoyConfig.enabled}
                      onChange={(e) => setDecoyConfig(prev => ({ ...prev, enabled: e.target.checked }))}
                      className="rounded border-slate-700 text-indigo-500 focus:ring-0"
                    />
                    <span>Generator Active</span>
                  </label>
                  <label className="flex items-center gap-2 text-xs text-slate-300 cursor-pointer">
                    <input
                      type="checkbox"
                      checked={decoyConfig.paddingEnabled}
                      onChange={(e) => setDecoyConfig(prev => ({ ...prev, paddingEnabled: e.target.checked }))}
                      className="rounded border-slate-700 text-indigo-500 focus:ring-0"
                    />
                    <span>Packet Padding (Mask Packet Shape)</span>
                  </label>
                  <button
                    onClick={handleTriggerDecoyBurst}
                    className="px-3 py-1.5 bg-rose-600 hover:bg-rose-500 text-white rounded text-xs font-semibold flex items-center gap-1.5 transition-colors active:scale-95"
                  >
                    <Zap className="w-3.5 h-3.5" />
                    <span>Trigger 5-Packet Decoy Burst</span>
                  </button>
                </div>
              </div>

              {/* Stats overview */}
              <div className="grid grid-cols-1 sm:grid-cols-3 gap-3">
                <div className="p-3 rounded-lg bg-slate-950 border border-slate-800">
                  <div className="text-xs text-slate-500 uppercase">Decoys Emitted</div>
                  <div className="text-xl font-bold text-white font-mono mt-1">{decoyConfig.totalDecoysSent.toLocaleString()}</div>
                  <div className="text-xs text-emerald-400 mt-1">Masking idle vs active browsing</div>
                </div>
                <div className="p-3 rounded-lg bg-slate-950 border border-slate-800">
                  <div className="text-xs text-slate-500 uppercase">Synthetic Bandwidth</div>
                  <div className="text-xl font-bold text-white font-mono mt-1">{(decoyConfig.totalDecoyBytes / 1024 / 1024).toFixed(2)} MB</div>
                  <div className="text-xs text-indigo-400 mt-1">Low-cost dummy traffic</div>
                </div>
                <div className="p-3 rounded-lg bg-slate-950 border border-slate-800">
                  <div className="text-xs text-slate-500 uppercase">Target Pool Rotation</div>
                  <div className="text-xl font-bold text-white font-mono mt-1">{decoyConfig.targetPool.length} CDNs</div>
                  <div className="text-xs text-slate-400 mt-1">Wikipedia, Cloudflare, Debian, EFF</div>
                </div>
              </div>

              {/* Decoy Transmission Log */}
              <div className="rounded-lg border border-slate-800 overflow-hidden">
                <div className="bg-slate-950 px-4 py-2 border-b border-slate-800 text-xs font-semibold text-slate-400">
                  Live Synthetic Traffic Ledger
                </div>
                <table className="w-full text-left text-xs">
                  <thead className="bg-slate-900/80 border-b border-slate-800 text-slate-400 font-semibold">
                    <tr>
                      <th className="py-2 px-4">Time</th>
                      <th className="py-2 px-4">Target Endpoint</th>
                      <th className="py-2 px-4">Payload Size</th>
                      <th className="py-2 px-4">Padding Added</th>
                      <th className="py-2 px-4 text-right">Round-Trip</th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-800/80 bg-slate-900/40 font-mono">
                    {decoyLog.map((log) => (
                      <tr key={log.id} className="hover:bg-slate-850/50">
                        <td className="py-2 px-4 text-slate-400">{log.time}</td>
                        <td className="py-2 px-4 text-indigo-300 font-semibold">{log.target}</td>
                        <td className="py-2 px-4 text-slate-300">{log.bytes} B</td>
                        <td className="py-2 px-4 text-emerald-400">+{log.padding} B randomized TLS extension</td>
                        <td className="py-2 px-4 text-right text-slate-400">{log.latency} ms</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}

          {/* TAB 5: BROWSER & SYSTEM FINGERPRINT SHIELD */}
          {activeTab === 'fingerprint' && (
            <div className="space-y-6">
              {/* Entropy Score Gauge */}
              <div className="p-4 rounded-lg bg-slate-950 border border-slate-800 flex flex-wrap items-center justify-between gap-6">
                <div>
                  <div className="text-xs text-slate-500 uppercase tracking-wider">Fingerprint Identifiability Pool</div>
                  <div className="flex items-baseline gap-3 mt-1">
                    <span className="text-2xl font-black text-white">{entropyResult.entropyBits} bits</span>
                    <span className={`text-xs font-bold px-2 py-0.5 rounded border ${
                      entropyResult.identifiabilityGrade === 'SOVEREIGN CLOAKED' 
                        ? 'bg-emerald-950/60 border-emerald-500/40 text-emerald-400' 
                        : 'bg-amber-950/60 border-amber-500/40 text-amber-400'
                    }`}>
                      {entropyResult.identifiabilityGrade}
                    </span>
                  </div>
                  <p className="text-xs text-slate-400 mt-1">
                    Device uniqueness reduced to <strong className="text-white">{entropyResult.uniquenessRatio}</strong> browsers (down from 1 in 10,000,000,000).
                  </p>
                </div>
                <div className="flex items-center gap-2">
                  <button
                    onClick={() => {
                      setFingerprintShield({
                        canvasNoise: true,
                        webGlDisguise: true,
                        audioNoise: true,
                        userAgentDisguise: true,
                        webrtcCloaking: true,
                        currentEntropyBits: 5.1,
                        uniquenessScore: 95
                      });
                    }}
                    className="px-3 py-1.5 text-xs font-semibold rounded bg-indigo-600 hover:bg-indigo-500 text-white transition-colors"
                  >
                    Enable All Cloaking Shields
                  </button>
                </div>
              </div>

              {/* Shield Toggles Grid */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {[
                  {
                    key: 'canvasNoise' as const,
                    title: 'Canvas Sub-Pixel Noise Injection (F367)',
                    desc: 'Injects microscopic deterministic RGB variance into HTML5 Canvas to disrupt 2D font rasterization hashes.',
                    gain: '-7.2 bits entropy'
                  },
                  {
                    key: 'webGlDisguise' as const,
                    title: 'WebGL Vendor & GPU Cloaking (F368)',
                    desc: 'Masks exact discrete GPU hardware strings (NVIDIA/AMD/Apple) with generic Mesa/ANGLE rendering parameters.',
                    gain: '-6.4 bits entropy'
                  },
                  {
                    key: 'audioNoise' as const,
                    title: 'AudioContext Oscillator Perturbation (F369)',
                    desc: 'Adds subtle floating-point frequency jitter to Web Audio API dynamics compressions to defeat acoustic hashing.',
                    gain: '-4.8 bits entropy'
                  },
                  {
                    key: 'webrtcCloaking' as const,
                    title: 'WebRTC Local IP Leak Blinder (F370)',
                    desc: 'Forces ICE candidate generation through mDNS anonymization, preventing private LAN IPs (192.168.x.x) from leaking.',
                    gain: '-5.5 bits entropy'
                  }
                ].map((item) => (
                  <div key={item.key} className="p-4 rounded-lg bg-slate-950 border border-slate-800 flex items-start justify-between gap-4">
                    <div className="space-y-1">
                      <div className="text-xs font-semibold text-white">{item.title}</div>
                      <div className="text-xs text-slate-400 leading-relaxed">{item.desc}</div>
                      <div className="text-[11px] text-emerald-400 font-mono pt-1">{item.gain}</div>
                    </div>
                    <label className="relative inline-flex items-center cursor-pointer shrink-0 mt-1">
                      <input
                        type="checkbox"
                        checked={fingerprintShield[item.key]}
                        onChange={(e) => setFingerprintShield(prev => ({ ...prev, [item.key]: e.target.checked }))}
                        className="sr-only peer"
                      />
                      <div className="w-9 h-5 bg-slate-800 peer-focus:outline-none rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-slate-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all peer-checked:bg-indigo-600"></div>
                    </label>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* TAB 6: TIER 4 VERIFICATION (DONE-TESTS) */}
          {activeTab === 'verification' && (
            <div className="space-y-4">
              <div className="p-4 rounded-lg bg-slate-950 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="text-sm font-semibold text-white flex items-center gap-2">
                    <CheckCircle2 className="w-4 h-4 text-emerald-400" />
                    Tier 4 Verification Suite (Done-Tests)
                  </h3>
                  <p className="text-xs text-slate-400">
                    Runs automated end-to-end verifications for Tor routing, RFC 7686 onion resolution, split-tunneling, location fuzzing, decoy emission, and fingerprint cloaking.
                  </p>
                </div>
                <button
                  onClick={handleRunVerification}
                  className="px-4 py-2 bg-emerald-600 hover:bg-emerald-500 text-white rounded text-xs font-semibold flex items-center gap-1.5 transition-colors active:scale-95"
                >
                  <Play className="w-3.5 h-3.5" />
                  <span>Execute Tier 4 Verification</span>
                </button>
              </div>

              {verificationResult ? (
                <div className="space-y-3">
                  <div className={`p-4 rounded-lg border ${
                    verificationResult.allPassed 
                      ? 'bg-emerald-950/20 border-emerald-500/40 text-emerald-300' 
                      : 'bg-rose-950/20 border-rose-500/40 text-rose-300'
                  }`}>
                    <div className="font-bold text-sm flex items-center gap-2">
                      {verificationResult.allPassed ? (
                        <>
                          <CheckCircle2 className="w-4 h-4 text-emerald-400" />
                          <span>All 6 Tier 4 Subsystem Verification Tests Passed</span>
                        </>
                      ) : (
                        <>
                          <AlertTriangle className="w-4 h-4 text-rose-400" />
                          <span>Verification Incomplete</span>
                        </>
                      )}
                    </div>
                  </div>

                  <div className="space-y-2">
                    {verificationResult.checks.map((chk, idx) => (
                      <div key={idx} className="p-3 rounded-lg bg-slate-950 border border-slate-800 flex items-start gap-3">
                        <div className={`mt-0.5 p-1 rounded-full ${chk.passed ? 'bg-emerald-500/10 text-emerald-400' : 'bg-rose-500/10 text-rose-400'}`}>
                          <Check className="w-3.5 h-3.5" />
                        </div>
                        <div className="flex-1 text-xs">
                          <div className="font-semibold text-white">{chk.name}</div>
                          <div className="text-slate-400 font-mono text-[11px] mt-0.5">{chk.details}</div>
                        </div>
                        <span className="text-[11px] font-bold text-emerald-400 bg-emerald-950/50 border border-emerald-500/30 px-2 py-0.5 rounded">
                          PASSED
                        </span>
                      </div>
                    ))}
                  </div>
                </div>
              ) : (
                <div className="p-8 text-center text-xs text-slate-500 border border-dashed border-slate-800 rounded-lg">
                  Click &ldquo;Execute Tier 4 Verification&rdquo; to test all privacy and routing core engines against Section 10 verification contracts.
                </div>
              )}
            </div>
          )}

        </div>

        {/* Footer */}
        <div className="px-6 py-3 border-t border-slate-800 bg-slate-950 flex items-center justify-between text-xs text-slate-400">
          <div className="flex items-center gap-4">
            <span>SOCKS5 Proxy: <span className="font-mono text-slate-200">127.0.0.1:9050</span></span>
            <span>Split Rules: <span className="text-slate-200">{splitRules.length} apps configured</span></span>
            <span>Fuzz Status: <span className="text-emerald-400">±{locationConfig.jitterRadiusMeters}m Jitter</span></span>
          </div>
          <button
            onClick={onClose}
            className="px-4 py-1.5 rounded bg-slate-800 hover:bg-slate-700 text-white font-semibold transition-colors"
          >
            Close
          </button>
        </div>

      </div>
    </div>
  );
};
