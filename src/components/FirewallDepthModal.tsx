import React, { useState } from 'react';
import { 
  Shield, 
  Globe, 
  Sparkles, 
  EyeOff, 
  Lock, 
  AlertTriangle, 
  Sliders, 
  Terminal, 
  Network, 
  ArrowRight, 
  CheckCircle2, 
  Ban, 
  RefreshCw, 
  Plus, 
  Play, 
  FileCode, 
  Wifi, 
  Gauge, 
  Radio, 
  PhoneOff, 
  Clock, 
  X,
  Search,
  ExternalLink,
  Zap,
  Cpu
} from 'lucide-react';
import { SerfModule } from '../types/serf';

interface FirewallDepthModalProps {
  isOpen: boolean;
  onClose: () => void;
  onRunTier3DoneTest: () => void;
  onTriggerConnectionPrompt: () => void;
  floatingHudActive: boolean;
  onToggleFloatingHud: () => void;
}

export const FirewallDepthModal: React.FC<FirewallDepthModalProps> = ({
  isOpen,
  onClose,
  onRunTier3DoneTest,
  onTriggerConnectionPrompt,
  floatingHudActive,
  onToggleFloatingHud
}) => {
  const [activeTab, setActiveTab] = useState<'rules' | 'geoip' | 'synthesis' | 'security' | 'mesh' | 'stealth'>('rules');

  // F16: Natural Language Rule Parser State
  const [nlInput, setNlInput] = useState('');
  const [parsedRules, setParsedRules] = useState<Array<{ id: string; original: string; ruleType: string; target: string; action: string; active: boolean }>>([
    {
      id: 'nl-1',
      original: 'Block all social media domains after 10:00 PM',
      ruleType: 'Time-Scheduled Filter (F16 / F18)',
      target: '*.tiktok.com, *.facebook.com, *.instagram.com',
      action: 'Drop packets (22:00 - 07:00)',
      active: true
    },
    {
      id: 'nl-2',
      original: 'Isolate Duolingo and fake online responses',
      ruleType: 'Synthetic Jail (F16 / F96 / F03)',
      target: 'com.duolingo / *.duolingo.com/api/*',
      action: 'Return synthetic HTTP 200 { "streak_saved": true }',
      active: true
    },
    {
      id: 'nl-3',
      original: 'Throttle background cloud backups to 50 KB/s on mobile data',
      ruleType: 'Bandwidth Quota (F18 / F236)',
      target: 'com.google.android.apps.photos',
      action: 'Throttle to 50 KB/s on TRANSPORT_CELLULAR',
      active: true
    }
  ]);

  // F11: Geo-IP Firewall State
  const [blockedCountries, setBlockedCountries] = useState<Record<string, boolean>>({
    'RU': true,
    'CN': true,
    'IR': true,
    'KP': true,
    'BY': true
  });

  const [countryStats] = useState([
    { code: 'US', name: 'United States', flag: '🇺🇸', sockets: 42, blockedCount: 120, latency: '34ms' },
    { code: 'EU', name: 'European Union (Frankfurt/Amsterdam)', flag: '🇪🇺', sockets: 18, blockedCount: 14, latency: '82ms' },
    { code: 'CN', name: 'China (Mainland)', flag: '🇨🇳', sockets: 0, blockedCount: 489, latency: 'Blocked (F11)' },
    { code: 'RU', name: 'Russian Federation', flag: '🇷🇺', sockets: 0, blockedCount: 215, latency: 'Blocked (F11)' },
    { code: 'CH', name: 'Switzerland (Proton/CERN)', flag: '🇨🇭', sockets: 6, blockedCount: 0, latency: '95ms' },
    { code: 'SG', name: 'Singapore / APAC', flag: '🇸🇬', sockets: 3, blockedCount: 5, latency: '160ms' }
  ]);

  // F162: Call & SMS Geographic Blocking
  const [telecomBlockedCodes, setTelecomBlockedCodes] = useState<string[]>([
    '+234 (Nigeria)', 
    '+881 (Global Mobile Satellite System)', 
    '+247 (Ascension Island)', 
    '+882 (International Networks)'
  ]);
  const [newPrefix, setNewPrefix] = useState('');

  // F03: Dynamic Response Synthesis Templates
  const [synthesisTemplates, setSynthesisTemplates] = useState([
    {
      id: 'syn-1',
      name: 'Universal HTTP 204 No Content',
      matchPattern: '*/generate_204*, */connectivitycheck*, */hotspot-detect*',
      statusCode: 204,
      contentType: 'text/plain',
      body: '',
      delayMs: 2,
      active: true
    },
    {
      id: 'syn-2',
      name: 'App Analytics Silent Blackhole',
      matchPattern: '*telemetry*, *analytics*, *app-measurement.com*',
      statusCode: 200,
      contentType: 'application/json',
      body: '{\n  "status": "ok",\n  "events_recorded": 1,\n  "next_batch_ms": 86400000\n}',
      delayMs: 5,
      active: true
    },
    {
      id: 'syn-3',
      name: 'Game License / Offline Entitlement Stub',
      matchPattern: '*/license/verify*, */entitlements/v1*',
      statusCode: 200,
      contentType: 'application/json',
      body: '{\n  "licensed": true,\n  "status": "active",\n  "offline_grace_period_days": 3650,\n  "tier": "unlimited"\n}',
      delayMs: 8,
      active: true
    }
  ]);
  const [editingTemplate, setEditingTemplate] = useState<string | null>('syn-2');
  const [synthesisTestResult, setSynthesisTestResult] = useState<string | null>(null);

  // F373: Rogue CA Trust Store
  const [scannedCerts, setScannedCerts] = useState([
    { id: 'ca-1', issuer: 'ISRG Root X1 (Let\'s Encrypt)', type: 'System', trusted: true, fingerprint: '96BCEC06...2681', dateAdded: 'OS Build' },
    { id: 'ca-2', issuer: 'DigiCert Global Root G2', type: 'System', trusted: true, fingerprint: 'CB3CC16D...7290', dateAdded: 'OS Build' },
    { id: 'ca-3', issuer: 'Google Trust Services LLC', type: 'System', trusted: true, fingerprint: '8F69F8B5...1A94', dateAdded: 'OS Build' }
  ]);
  const [isCertScanning, setIsCertScanning] = useState(false);

  // F387: DNS Rebinding Attack Detector
  const [dnsRebindingCount, setDnsRebindingCount] = useState(3);
  const [rebindingTestLog, setRebindingTestLog] = useState<string | null>(null);

  // F13: Stealth Mode
  const [stealthActive, setStealthActive] = useState(true);

  // F194: Auth-Flow Safe Mode (60s pinhole)
  const [authSafeModeActive, setAuthSafeModeActive] = useState(false);
  const [authSafeModeSeconds, setAuthSafeModeSeconds] = useState(60);

  // F227: VPN+DNS Chain Wizard
  const [tunnelChain] = useState([
    { step: 1, name: 'Local App Socket', target: 'tun0 (Virtual Network Interface)', status: 'Active' },
    { step: 2, name: 'Intra Netstack Filter', target: 'uBlock Regex Engine + SERF Stubs (F10)', status: 'Filtering' },
    { step: 3, name: 'VPN WireGuard Tunnel', target: 'ChaCha20-Poly1305 (No IPv6 Leaks)', status: 'Encrypted' },
    { step: 4, name: 'Encrypted DNS Egress', target: 'Cloudflare DNS-over-HTTPS (DoH)', status: 'Verified' }
  ]);

  // Handlers
  const handleAddNlRule = (e: React.FormEvent) => {
    e.preventDefault();
    if (!nlInput.trim()) return;

    let target = 'Wildcard domain / all apps';
    let action = 'Enforce policy';
    const lower = nlInput.toLowerCase();

    if (lower.includes('block') || lower.includes('drop')) {
      action = 'Drop connection immediately (TCP RST)';
    } else if (lower.includes('fake') || lower.includes('spoof')) {
      action = 'Return synthetic HTTP 200 payload (F03)';
    } else if (lower.includes('throttle') || lower.includes('limit')) {
      action = 'Apply hard bandwidth ceiling 64 KB/s';
    }

    if (lower.includes('tiktok')) target = '*.tiktok.com, *.byteoversea.com';
    else if (lower.includes('instagram') || lower.includes('meta')) target = '*.instagram.com, *.fbcdn.net';
    else if (lower.includes('duolingo')) target = 'com.duolingo / *.duolingo.com';
    else if (lower.includes('google')) target = '*.google-analytics.com, *.g.doubleclick.net';

    const newRule = {
      id: `nl-${Date.now()}`,
      original: nlInput,
      ruleType: 'SERF Natural-Language Policy (F16)',
      target,
      action,
      active: true
    };

    setParsedRules([newRule, ...parsedRules]);
    setNlInput('');
  };

  const toggleCountry = (code: string) => {
    setBlockedCountries(prev => ({ ...prev, [code]: !prev[code] }));
  };

  const addTelecomCode = () => {
    if (!newPrefix.trim()) return;
    setTelecomBlockedCodes([...telecomBlockedCodes, newPrefix.trim()]);
    setNewPrefix('');
  };

  const handleTestSynthesis = () => {
    const cur = synthesisTemplates.find(t => t.id === editingTemplate);
    if (!cur) return;
    setSynthesisTestResult(`Simulated call to ${cur.matchPattern.split(',')[0]}:\nHTTP/1.1 ${cur.statusCode} OK\nContent-Type: ${cur.contentType}\nX-SERF-Synthesis: F03-Synthetic-Engine\nLatency: ${cur.delayMs}ms\n\n${cur.body || '[Empty Body 204 No Content]'}`);
  };

  const handleTestRebinding = () => {
    setRebindingTestLog(
      'SIMULATED ATTACK DETECTED:\nDomain: malicious-iot-exploit.com\nDNS Answer Returned: 192.168.1.1 (RFC 1918 Private LAN)\nACTION: Blocked by SERF DNS Rebinding Shield (F387)!\nSocket dropped to prevent unauthorized LAN device exploitation.'
    );
    setDnsRebindingCount(c => c + 1);
  };

  const handleScanCerts = () => {
    setIsCertScanning(true);
    setTimeout(() => {
      setIsCertScanning(false);
      setScannedCerts([
        ...scannedCerts,
        { id: `ca-${Date.now()}`, issuer: 'SERF Verified CA Trust Verifier (F373)', type: 'Audit Passed', trusted: true, fingerprint: 'E7B31A88...91C2', dateAdded: 'Just now' }
      ]);
    }, 700);
  };

  const handleTriggerAuthSafeMode = () => {
    setAuthSafeModeActive(true);
    setAuthSafeModeSeconds(60);
    const interval = setInterval(() => {
      setAuthSafeModeSeconds(prev => {
        if (prev <= 1) {
          clearInterval(interval);
          setAuthSafeModeActive(false);
          return 60;
        }
        return prev - 1;
      });
    }, 1000);
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div className="w-full max-w-5xl h-[88vh] bg-zinc-950 border border-zinc-800 rounded-2xl shadow-2xl flex flex-col text-zinc-100 overflow-hidden">
        {/* Top Header */}
        <div className="bg-gradient-to-r from-zinc-900 via-zinc-900 to-zinc-950 border-b border-zinc-800 px-6 py-4 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-cyan-500/10 border border-cyan-500/30 flex items-center justify-center text-cyan-400">
              <Shield className="w-5 h-5" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h2 className="text-lg font-bold text-zinc-100">Tier 3: The Soul & Firewall Depth</h2>
                <span className="text-[11px] font-mono px-2 py-0.5 rounded-full bg-cyan-500/15 text-cyan-300 border border-cyan-500/30">
                  P1-P2 Depth Suite
                </span>
              </div>
              <p className="text-xs text-zinc-400">
                Advanced filtering engines, Geo-IP shields, synthetic response studio, and attack mitigation
              </p>
            </div>
          </div>

          <div className="flex items-center gap-2.5">
            {/* Quick action to run Tier 3 Done-Test */}
            <button
              onClick={onRunTier3DoneTest}
              className="px-3 py-1.5 rounded-lg bg-emerald-500/20 hover:bg-emerald-500/30 border border-emerald-500/40 text-emerald-300 text-xs font-semibold flex items-center gap-1.5 transition-colors"
            >
              <Zap className="w-3.5 h-3.5" />
              Run Tier 3 Done-Test
            </button>

            <button
              onClick={onClose}
              className="p-1.5 text-zinc-400 hover:text-zinc-200 hover:bg-zinc-800 rounded-lg transition-colors"
            >
              <X className="w-5 h-5" />
            </button>
          </div>
        </div>

        {/* Tab Navigation */}
        <div className="flex items-center px-6 border-b border-zinc-800 bg-zinc-900/50 gap-2 overflow-x-auto text-xs py-2 scrollbar-none">
          <button
            onClick={() => setActiveTab('rules')}
            className={`px-3 py-1.5 rounded-lg font-medium transition-colors flex items-center gap-1.5 ${
              activeTab === 'rules'
                ? 'bg-cyan-500/20 text-cyan-300 border border-cyan-500/30'
                : 'text-zinc-400 hover:text-zinc-200 hover:bg-zinc-800/60'
            }`}
          >
            <Sliders className="w-3.5 h-3.5" />
            Natural Rules & uBlock (F10/F16)
          </button>

          <button
            onClick={() => setActiveTab('geoip')}
            className={`px-3 py-1.5 rounded-lg font-medium transition-colors flex items-center gap-1.5 ${
              activeTab === 'geoip'
                ? 'bg-cyan-500/20 text-cyan-300 border border-cyan-500/30'
                : 'text-zinc-400 hover:text-zinc-200 hover:bg-zinc-800/60'
            }`}
          >
            <Globe className="w-3.5 h-3.5" />
            Geo-IP & Telecom (F11/F162)
          </button>

          <button
            onClick={() => setActiveTab('synthesis')}
            className={`px-3 py-1.5 rounded-lg font-medium transition-colors flex items-center gap-1.5 ${
              activeTab === 'synthesis'
                ? 'bg-cyan-500/20 text-cyan-300 border border-cyan-500/30'
                : 'text-zinc-400 hover:text-zinc-200 hover:bg-zinc-800/60'
            }`}
          >
            <Sparkles className="w-3.5 h-3.5 text-amber-400" />
            Dynamic Synthesis Studio (F03)
          </button>

          <button
            onClick={() => setActiveTab('security')}
            className={`px-3 py-1.5 rounded-lg font-medium transition-colors flex items-center gap-1.5 ${
              activeTab === 'security'
                ? 'bg-cyan-500/20 text-cyan-300 border border-cyan-500/30'
                : 'text-zinc-400 hover:text-zinc-200 hover:bg-zinc-800/60'
            }`}
          >
            <Lock className="w-3.5 h-3.5 text-rose-400" />
            Rogue CAs & Rebinding (F373/F387)
          </button>

          <button
            onClick={() => setActiveTab('mesh')}
            className={`px-3 py-1.5 rounded-lg font-medium transition-colors flex items-center gap-1.5 ${
              activeTab === 'mesh'
                ? 'bg-cyan-500/20 text-cyan-300 border border-cyan-500/30'
                : 'text-zinc-400 hover:text-zinc-200 hover:bg-zinc-800/60'
            }`}
          >
            <Network className="w-3.5 h-3.5" />
            VPN+DNS Chain & Mesh (F227/F223)
          </button>

          <button
            onClick={() => setActiveTab('stealth')}
            className={`px-3 py-1.5 rounded-lg font-medium transition-colors flex items-center gap-1.5 ${
              activeTab === 'stealth'
                ? 'bg-cyan-500/20 text-cyan-300 border border-cyan-500/30'
                : 'text-zinc-400 hover:text-zinc-200 hover:bg-zinc-800/60'
            }`}
          >
            <EyeOff className="w-3.5 h-3.5 text-violet-400" />
            Stealth & Live Prompts (F12/F13/F19)
          </button>
        </div>

        {/* Tab Body */}
        <div className="flex-1 overflow-y-auto p-6 space-y-6">
          {/* TAB 1: Natural Language Rules & uBlock */}
          {activeTab === 'rules' && (
            <div className="space-y-6">
              {/* Natural Language Prompt Bar (F16) */}
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <Terminal className="w-4 h-4 text-cyan-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Natural-Language Rule Engine (F16)</h3>
                  </div>
                  <span className="text-[11px] text-zinc-400">Plain English → Enforceable Packet Rules</span>
                </div>

                <form onSubmit={handleAddNlRule} className="flex gap-2">
                  <input
                    type="text"
                    value={nlInput}
                    onChange={(e) => setNlInput(e.target.value)}
                    placeholder="e.g., 'Block TikTok and Instagram after 10 PM' or 'Fake online responses for Adobe Reader'"
                    className="flex-1 bg-zinc-950 border border-zinc-700 rounded-lg px-3.5 py-2 text-xs text-zinc-100 placeholder-zinc-500 focus:outline-none focus:border-cyan-500"
                  />
                  <button
                    type="submit"
                    className="px-4 py-2 bg-cyan-600 hover:bg-cyan-500 text-white font-medium text-xs rounded-lg transition-colors flex items-center gap-1.5"
                  >
                    <Plus className="w-3.5 h-3.5" />
                    Compile Rule
                  </button>
                </form>

                <div className="flex flex-wrap gap-1.5 pt-1 text-[11px] text-zinc-500">
                  <span>Quick templates:</span>
                  <button
                    onClick={() => setNlInput('Isolate Duolingo and return fake 200 OK')}
                    className="hover:text-cyan-400 underline underline-offset-2"
                  >
                    "Isolate Duolingo"
                  </button>
                  <span>•</span>
                  <button
                    onClick={() => setNlInput('Block all tracking cookies and strip fbclid parameters')}
                    className="hover:text-cyan-400 underline underline-offset-2"
                  >
                    "Strip fbclid parameters"
                  </button>
                  <span>•</span>
                  <button
                    onClick={() => setNlInput('Allow banking apps 60s auth bypass')}
                    className="hover:text-cyan-400 underline underline-offset-2"
                  >
                    "Auth-Flow 60s pinhole"
                  </button>
                </div>
              </div>

              {/* Active Compiled Rules List */}
              <div className="space-y-3">
                <div className="flex items-center justify-between">
                  <h4 className="text-xs font-semibold text-zinc-300 uppercase tracking-wider">
                    Active Firewall Rules ({parsedRules.length})
                  </h4>
                  <div className="flex items-center gap-2">
                    {/* F194 Auth-Flow Safe Mode */}
                    <button
                      onClick={handleTriggerAuthSafeMode}
                      className={`text-xs px-2.5 py-1 rounded-lg border flex items-center gap-1.5 transition-colors ${
                        authSafeModeActive 
                          ? 'bg-amber-500/20 border-amber-500/40 text-amber-300 animate-pulse'
                          : 'bg-zinc-900 border-zinc-800 text-zinc-300 hover:border-zinc-700'
                      }`}
                    >
                      <Clock className="w-3.5 h-3.5" />
                      {authSafeModeActive 
                        ? `Auth Safe Mode: ${authSafeModeSeconds}s remaining` 
                        : 'Trigger 60s Auth Safe Mode (F194)'}
                    </button>
                  </div>
                </div>

                <div className="grid grid-cols-1 gap-2.5">
                  {parsedRules.map((rule) => (
                    <div
                      key={rule.id}
                      className="p-3.5 bg-zinc-900/80 border border-zinc-800 rounded-xl flex items-start justify-between gap-4"
                    >
                      <div className="space-y-1">
                        <div className="flex items-center gap-2">
                          <span className="text-xs font-medium text-zinc-200">"{rule.original}"</span>
                          <span className="text-[10px] font-mono px-1.5 py-0.5 rounded bg-zinc-800 text-cyan-300 border border-zinc-700">
                            {rule.ruleType}
                          </span>
                        </div>
                        <div className="text-[11px] font-mono text-zinc-400">
                          Target: <span className="text-zinc-300">{rule.target}</span>
                        </div>
                        <div className="text-[11px] text-emerald-400 flex items-center gap-1">
                          <CheckCircle2 className="w-3 h-3" />
                          Action: {rule.action}
                        </div>
                      </div>

                      <div className="flex items-center gap-2">
                        <input
                          type="checkbox"
                          checked={rule.active}
                          onChange={() => {
                            setParsedRules(rules =>
                              rules.map(r => r.id === rule.id ? { ...r, active: !r.active } : r)
                            );
                          }}
                          className="w-4 h-4 rounded border-zinc-700 bg-zinc-800 text-cyan-500 focus:ring-0 cursor-pointer"
                        />
                      </div>
                    </div>
                  ))}
                </div>
              </div>

              {/* F10: uBlock Pattern Engine Specs */}
              <div className="bg-zinc-900/50 border border-zinc-800 rounded-xl p-4 space-y-2 text-xs">
                <div className="flex items-center gap-2 text-zinc-300 font-semibold">
                  <FileCode className="w-4 h-4 text-cyan-400" />
                  uBlock-Grade Pattern Engine & Cosmetic Filter (F10)
                </div>
                <p className="text-zinc-400">
                  Full compatibility with Adblock Plus and uBlock Origin syntax: supports <code>||ad.doubleclick.net^</code>, regex domain filters <code>/^ad[0-9]+\.com/</code>, and cosmetic element scriptlet blocks across all sandboxed apps without accessibility service permissions.
                </p>
                <div className="flex items-center gap-4 pt-1 font-mono text-[11px] text-zinc-400">
                  <span>Engine: Ultra-Fast Aho-Corasick Multi-Pattern</span>
                  <span>Memory: 4.2 MB RAM</span>
                  <span>Rules Active: 142,890 entries</span>
                </div>
              </div>
            </div>
          )}

          {/* TAB 2: Geo-IP & Telecom */}
          {activeTab === 'geoip' && (
            <div className="space-y-6">
              {/* Country Blocking Matrix (F11) */}
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-4">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <Globe className="w-4 h-4 text-cyan-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Geo-IP & Outbound Egress Firewall (F11)</h3>
                  </div>
                  <span className="text-[11px] font-mono text-zinc-400">MaxMind GeoLite2 On-Device Database</span>
                </div>
                <p className="text-xs text-zinc-400 leading-relaxed">
                  Block outbound socket traffic destined for servers in specific jurisdictions before packets leave your radio. Apps attempting connection to blocked regions receive synthetic TCP RST.
                </p>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-3">
                  {countryStats.map(c => {
                    const isBlocked = blockedCountries[c.code];
                    return (
                      <div
                        key={c.code}
                        className={`p-3.5 rounded-xl border transition-all flex items-center justify-between ${
                          isBlocked
                            ? 'bg-rose-950/20 border-rose-500/40'
                            : 'bg-zinc-900/80 border-zinc-800 hover:border-zinc-700'
                        }`}
                      >
                        <div className="flex items-center gap-3">
                          <span className="text-2xl">{c.flag}</span>
                          <div>
                            <div className="text-xs font-semibold text-zinc-200">{c.name}</div>
                            <div className="text-[11px] text-zinc-400 font-mono">
                              {c.sockets} active sockets • {c.latency}
                            </div>
                          </div>
                        </div>

                        <button
                          onClick={() => toggleCountry(c.code)}
                          className={`px-3 py-1 rounded-lg text-xs font-semibold border transition-colors ${
                            isBlocked
                              ? 'bg-rose-500/30 text-rose-300 border-rose-500/50'
                              : 'bg-zinc-800 text-zinc-300 border-zinc-700 hover:bg-zinc-750'
                          }`}
                        >
                          {isBlocked ? 'BLOCKED' : 'ALLOWED'}
                        </button>
                      </div>
                    );
                  })}
                </div>
              </div>

              {/* F162: Call & SMS Geographic Blocking */}
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <PhoneOff className="w-4 h-4 text-amber-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Call & SMS Geographic Filtering (F162)</h3>
                  </div>
                  <span className="text-[11px] text-zinc-400">Telephony Layer Protection</span>
                </div>
                <p className="text-xs text-zinc-400">
                  Block inbound spam rings and satellite toll-fraud numbers by international dialing prefix without granting contacts permission.
                </p>

                <div className="flex gap-2">
                  <input
                    type="text"
                    value={newPrefix}
                    onChange={(e) => setNewPrefix(e.target.value)}
                    placeholder="Enter prefix code, e.g. +234 or +882"
                    className="flex-1 bg-zinc-950 border border-zinc-700 rounded-lg px-3 py-1.5 text-xs text-zinc-100 placeholder-zinc-500 focus:outline-none focus:border-amber-500"
                  />
                  <button
                    onClick={addTelecomCode}
                    className="px-3 py-1.5 bg-amber-600 hover:bg-amber-500 text-white font-medium text-xs rounded-lg transition-colors flex items-center gap-1"
                  >
                    <Plus className="w-3.5 h-3.5" />
                    Add Prefix
                  </button>
                </div>

                <div className="flex flex-wrap gap-2 pt-1">
                  {telecomBlockedCodes.map(code => (
                    <span
                      key={code}
                      className="px-2.5 py-1 rounded-lg bg-zinc-800 text-zinc-300 text-xs font-mono border border-zinc-700 flex items-center gap-1.5"
                    >
                      <Ban className="w-3 h-3 text-rose-400" />
                      {code}
                    </span>
                  ))}
                </div>
              </div>
            </div>
          )}

          {/* TAB 3: Dynamic Response Synthesis Studio (F03) */}
          {activeTab === 'synthesis' && (
            <div className="space-y-6">
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-4">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <Sparkles className="w-4 h-4 text-amber-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Dynamic Response Synthesis Studio (F03)</h3>
                  </div>
                  <span className="text-[11px] font-mono text-amber-300 bg-amber-500/10 px-2 py-0.5 rounded border border-amber-500/30">
                    Synthetic Payload Engine
                  </span>
                </div>
                <p className="text-xs text-zinc-400">
                  Instead of dropping packets and causing apps to crash or show "Error connecting to server", SERF can return synthetic HTTP responses (200, 204, or tailored JSON/XML payloads) directly to the app loopback.
                </p>

                {/* Templates Selector */}
                <div className="grid grid-cols-1 md:grid-cols-3 gap-2.5">
                  {synthesisTemplates.map(tmpl => (
                    <button
                      key={tmpl.id}
                      onClick={() => {
                        setEditingTemplate(tmpl.id);
                        setSynthesisTestResult(null);
                      }}
                      className={`p-3 rounded-xl border text-left transition-all ${
                        editingTemplate === tmpl.id
                          ? 'bg-amber-950/30 border-amber-500/50 shadow-md'
                          : 'bg-zinc-950 border-zinc-800 hover:border-zinc-700'
                      }`}
                    >
                      <div className="text-xs font-semibold text-zinc-200">{tmpl.name}</div>
                      <div className="text-[10px] font-mono text-amber-400 mt-1">HTTP {tmpl.statusCode} • {tmpl.contentType}</div>
                      <div className="text-[10px] text-zinc-400 truncate mt-0.5">{tmpl.matchPattern}</div>
                    </button>
                  ))}
                </div>

                {/* Template Editor */}
                {editingTemplate && (() => {
                  const current = synthesisTemplates.find(t => t.id === editingTemplate);
                  if (!current) return null;
                  return (
                    <div className="bg-zinc-950 border border-zinc-800 rounded-xl p-4 space-y-3">
                      <div className="grid grid-cols-2 md:grid-cols-4 gap-3 text-xs">
                        <div>
                          <label className="text-[10px] text-zinc-400 block mb-1">Status Code</label>
                          <input
                            type="number"
                            value={current.statusCode}
                            onChange={(e) => {
                              const val = parseInt(e.target.value) || 200;
                              setSynthesisTemplates(tmpls =>
                                tmpls.map(t => t.id === current.id ? { ...t, statusCode: val } : t)
                              );
                            }}
                            className="w-full bg-zinc-900 border border-zinc-700 rounded px-2.5 py-1.5 text-zinc-100 font-mono text-xs"
                          />
                        </div>

                        <div>
                          <label className="text-[10px] text-zinc-400 block mb-1">Content-Type</label>
                          <input
                            type="text"
                            value={current.contentType}
                            onChange={(e) => {
                              const val = e.target.value;
                              setSynthesisTemplates(tmpls =>
                                tmpls.map(t => t.id === current.id ? { ...t, contentType: val } : t)
                              );
                            }}
                            className="w-full bg-zinc-900 border border-zinc-700 rounded px-2.5 py-1.5 text-zinc-100 font-mono text-xs"
                          />
                        </div>

                        <div>
                          <label className="text-[10px] text-zinc-400 block mb-1">Simulated Latency (ms)</label>
                          <input
                            type="number"
                            value={current.delayMs}
                            onChange={(e) => {
                              const val = parseInt(e.target.value) || 0;
                              setSynthesisTemplates(tmpls =>
                                tmpls.map(t => t.id === current.id ? { ...t, delayMs: val } : t)
                              );
                            }}
                            className="w-full bg-zinc-900 border border-zinc-700 rounded px-2.5 py-1.5 text-zinc-100 font-mono text-xs"
                          />
                        </div>

                        <div>
                          <label className="text-[10px] text-zinc-400 block mb-1">Rule State</label>
                          <div className="pt-1 flex items-center gap-2">
                            <input
                              type="checkbox"
                              checked={current.active}
                              onChange={() => {
                                setSynthesisTemplates(tmpls =>
                                  tmpls.map(t => t.id === current.id ? { ...t, active: !t.active } : t)
                                );
                              }}
                              className="w-4 h-4 rounded text-amber-500"
                            />
                            <span className="text-xs text-zinc-300">Active</span>
                          </div>
                        </div>
                      </div>

                      <div>
                        <label className="text-[10px] text-zinc-400 block mb-1">Match Pattern (URL or Domain wildcard)</label>
                        <input
                          type="text"
                          value={current.matchPattern}
                          onChange={(e) => {
                            const val = e.target.value;
                            setSynthesisTemplates(tmpls =>
                              tmpls.map(t => t.id === current.id ? { ...t, matchPattern: val } : t)
                            );
                          }}
                          className="w-full bg-zinc-900 border border-zinc-700 rounded px-2.5 py-1.5 text-zinc-100 font-mono text-xs"
                        />
                      </div>

                      <div>
                        <label className="text-[10px] text-zinc-400 block mb-1">Synthetic Response Body (JSON/XML/Text)</label>
                        <textarea
                          rows={4}
                          value={current.body}
                          onChange={(e) => {
                            const val = e.target.value;
                            setSynthesisTemplates(tmpls =>
                              tmpls.map(t => t.id === current.id ? { ...t, body: val } : t)
                            );
                          }}
                          className="w-full bg-zinc-900 border border-zinc-700 rounded p-2.5 text-zinc-100 font-mono text-xs focus:outline-none focus:border-amber-500"
                        />
                      </div>

                      <div className="flex items-center justify-between pt-2">
                        <button
                          onClick={handleTestSynthesis}
                          className="px-3.5 py-1.5 bg-amber-600 hover:bg-amber-500 text-white font-medium text-xs rounded-lg transition-colors flex items-center gap-1.5"
                        >
                          <Play className="w-3.5 h-3.5" />
                          Test Synthetic Response in Sandbox
                        </button>
                        <span className="text-[11px] text-zinc-500">Served via loopback tun2socks proxy</span>
                      </div>

                      {synthesisTestResult && (
                        <div className="mt-3 p-3 bg-zinc-900 border border-amber-500/40 rounded-lg font-mono text-[11px] text-amber-200 whitespace-pre-wrap">
                          {synthesisTestResult}
                        </div>
                      )}
                    </div>
                  );
                })()}
              </div>
            </div>
          )}

          {/* TAB 4: Rogue CAs & DNS Rebinding (F373/F387/F151) */}
          {activeTab === 'security' && (
            <div className="space-y-6">
              {/* DNS Rebinding Shield (F387) */}
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <Shield className="w-4 h-4 text-rose-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">DNS Rebinding Attack Detector (F387)</h3>
                  </div>
                  <span className="text-[11px] font-mono text-rose-400 bg-rose-500/10 px-2 py-0.5 rounded border border-rose-500/30">
                    {dnsRebindingCount} Attacks Neutralized
                  </span>
                </div>
                <p className="text-xs text-zinc-400 leading-relaxed">
                  Prevents malicious websites or advertising iframes from resolving public domain names to internal RFC 1918 private subnets (<code>192.168.x.x</code>, <code>10.x.x.x</code>, <code>127.0.0.1</code>) to exploit smart home routers, IoT cameras, or printer interfaces.
                </p>

                <div className="flex items-center gap-3">
                  <button
                    onClick={handleTestRebinding}
                    className="px-3.5 py-1.5 bg-rose-950 hover:bg-rose-900 border border-rose-500/50 text-rose-200 text-xs font-semibold rounded-lg transition-colors flex items-center gap-1.5"
                  >
                    <Zap className="w-3.5 h-3.5" />
                    Simulate Public Domain Rebinding to 192.168.1.1
                  </button>
                  <span className="text-[11px] text-zinc-500">RFC 1918 filtering active across IPv4 and IPv6 ULA</span>
                </div>

                {rebindingTestLog && (
                  <div className="p-3 bg-zinc-950 border border-rose-500/40 rounded-lg font-mono text-[11px] text-rose-300 whitespace-pre-wrap">
                    {rebindingTestLog}
                  </div>
                )}
              </div>

              {/* Rogue CA Certificate Detector (F373) */}
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <Lock className="w-4 h-4 text-cyan-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Rogue CA Certificate Detector (F373)</h3>
                  </div>
                  <button
                    onClick={handleScanCerts}
                    disabled={isCertScanning}
                    className="px-3 py-1 bg-zinc-800 hover:bg-zinc-750 text-xs text-zinc-200 border border-zinc-700 rounded-lg flex items-center gap-1.5 transition-colors"
                  >
                    <RefreshCw className={`w-3.5 h-3.5 ${isCertScanning ? 'animate-spin text-cyan-400' : ''}`} />
                    {isCertScanning ? 'Scanning Trust Stores...' : 'Scan /system and User CAs'}
                  </button>
                </div>
                <p className="text-xs text-zinc-400">
                  Monitors Android certificate storage for unauthorized enterprise MDM roots, carrier interception certs, or stalkerware proxies.
                </p>

                <div className="space-y-2">
                  {scannedCerts.map(cert => (
                    <div
                      key={cert.id}
                      className="p-3 bg-zinc-950 border border-zinc-800 rounded-lg flex items-center justify-between text-xs"
                    >
                      <div className="space-y-0.5">
                        <div className="font-semibold text-zinc-200">{cert.issuer}</div>
                        <div className="font-mono text-[10px] text-zinc-400">
                          Fingerprint: {cert.fingerprint} • Type: {cert.type}
                        </div>
                      </div>
                      <span className="text-emerald-400 flex items-center gap-1 font-medium">
                        <CheckCircle2 className="w-3.5 h-3.5" />
                        Valid
                      </span>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          )}

          {/* TAB 5: Tunnel & Mesh Orchestration */}
          {activeTab === 'mesh' && (
            <div className="space-y-6">
              {/* F227: VPN+DNS Chain Wizard */}
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <Network className="w-4 h-4 text-cyan-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">VPN + DNS Chain Wizard (F227)</h3>
                  </div>
                  <span className="text-[11px] text-emerald-400 font-medium flex items-center gap-1">
                    <CheckCircle2 className="w-3.5 h-3.5" /> Zero IPv6 Leaks
                  </span>
                </div>
                <p className="text-xs text-zinc-400 leading-relaxed">
                  Auto-corrects network tunnel ordering so DNS resolution never bypasses your VPN tunnel or leaks via IPv6 fallback.
                </p>

                <div className="grid grid-cols-1 md:grid-cols-4 gap-2 pt-1">
                  {tunnelChain.map((step, idx) => (
                    <div
                      key={step.step}
                      className="p-3 rounded-xl bg-zinc-950 border border-zinc-800 relative space-y-1"
                    >
                      <div className="flex items-center justify-between text-[10px] font-mono text-cyan-400">
                        <span>STAGE {step.step}</span>
                        <span>{step.status}</span>
                      </div>
                      <div className="text-xs font-semibold text-zinc-200">{step.name}</div>
                      <div className="text-[10px] text-zinc-400 leading-tight">{step.target}</div>
                    </div>
                  ))}
                </div>
              </div>

              {/* F223: Tailscale / Mesh Interop & F236 Bandwidth Throttle */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                  <div className="flex items-center gap-2">
                    <Radio className="w-4 h-4 text-violet-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Tailscale & Mesh Interop (F223)</h3>
                  </div>
                  <p className="text-xs text-zinc-400">
                    Routes 100.64.0.0/10 Carrier-Grade NAT and *.ts.net MagicDNS domains to local WireGuard mesh nodes while maintaining SERF firewall rules.
                  </p>
                  <div className="flex items-center justify-between p-2.5 bg-zinc-950 rounded-lg text-xs">
                    <span className="text-zinc-300">MagicDNS Direct Bind</span>
                    <span className="font-mono text-emerald-400">CONNECTED</span>
                  </div>
                </div>

                <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                  <div className="flex items-center gap-2">
                    <Gauge className="w-4 h-4 text-amber-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Universal Bandwidth Throttle (F236)</h3>
                  </div>
                  <p className="text-xs text-zinc-400">
                    Apply hard bandwidth caps on background sync or simulate 2G/3G low-bandwidth conditions for testing offline resilience.
                  </p>
                  <div className="flex items-center justify-between p-2.5 bg-zinc-950 rounded-lg text-xs">
                    <span className="text-zinc-300">Global Egress Cap</span>
                    <span className="font-mono text-zinc-400">UNLIMITED (Default)</span>
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* TAB 6: Stealth & Live Prompts */}
          {activeTab === 'stealth' && (
            <div className="space-y-6">
              {/* F13: Stealth Mode */}
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <EyeOff className="w-4 h-4 text-violet-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Stealth Mode: ICMP & Broadcast Silence (F13)</h3>
                  </div>
                  <button
                    onClick={() => setStealthActive(!stealthActive)}
                    className={`px-3 py-1 rounded-lg text-xs font-semibold border transition-colors ${
                      stealthActive
                        ? 'bg-violet-500/20 text-violet-300 border-violet-500/40'
                        : 'bg-zinc-800 text-zinc-400 border-zinc-700'
                    }`}
                  >
                    {stealthActive ? 'STEALTH ACTIVE' : 'STEALTH OFF'}
                  </button>
                </div>
                <p className="text-xs text-zinc-400 leading-relaxed">
                  Suppresses all ICMP echo replies (ping), unsolicited TCP RST packets, and mDNS/LLMNR broadcasts. Your phone appears as an unassigned, empty IP address on public Wi-Fi networks (hotels, airports, coffee shops).
                </p>
                <div className="grid grid-cols-3 gap-2 text-[11px] font-mono text-zinc-400">
                  <div className="bg-zinc-950 p-2 rounded border border-zinc-800">
                    Ping (ICMP): <span className="text-emerald-400">Silent Drop</span>
                  </div>
                  <div className="bg-zinc-950 p-2 rounded border border-zinc-800">
                    Port Scans: <span className="text-emerald-400">No RST Sent</span>
                  </div>
                  <div className="bg-zinc-950 p-2 rounded border border-zinc-800">
                    mDNS Leaks: <span className="text-emerald-400">Suppressed</span>
                  </div>
                </div>
              </div>

              {/* F12: Real-Time Connection Prompt Simulator */}
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <Cpu className="w-4 h-4 text-amber-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Real-Time Connection Prompts (F12)</h3>
                  </div>
                  <button
                    onClick={onTriggerConnectionPrompt}
                    className="px-3.5 py-1.5 bg-amber-600 hover:bg-amber-500 text-white font-medium text-xs rounded-lg transition-colors flex items-center gap-1.5"
                  >
                    <Play className="w-3.5 h-3.5" />
                    Trigger Sample Connection Prompt
                  </button>
                </div>
                <p className="text-xs text-zinc-400">
                  Interactively asks whether to allow once, permanently allow, drop, or synthesize fake responses whenever an unknown socket attempts egress.
                </p>
              </div>

              {/* F19: Floating HUD Simulator */}
              <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-4 space-y-3">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <Zap className="w-4 h-4 text-cyan-400" />
                    <h3 className="text-sm font-semibold text-zinc-100">Floating Firewall HUD (F19)</h3>
                  </div>
                  <button
                    onClick={onToggleFloatingHud}
                    className={`px-3 py-1 rounded-lg text-xs font-semibold border transition-colors ${
                      floatingHudActive
                        ? 'bg-cyan-500/20 text-cyan-300 border-cyan-500/40'
                        : 'bg-zinc-800 text-zinc-400 border-zinc-700'
                    }`}
                  >
                    {floatingHudActive ? 'HUD DISPLAYED' : 'ENABLE HUD'}
                  </button>
                </div>
                <p className="text-xs text-zinc-400">
                  An Android system overlay bubble that tracks live sockets, blocked trackers, throughput speeds, and provides a 1-tap Panic Kill Switch (F20) from any screen.
                </p>
              </div>
            </div>
          )}
        </div>

        {/* Modal Footer */}
        <div className="bg-zinc-900/60 border-t border-zinc-800 px-6 py-3.5 flex items-center justify-between text-xs text-zinc-400">
          <div className="flex items-center gap-3">
            <span>Tier 3 Modules: F03, F10, F11, F12, F13, F16, F19, F154, F162, F227, F373, F387</span>
            <span>•</span>
            <span className="text-emerald-400">Compliant with Module Contract 4.2</span>
          </div>
          <button
            onClick={onClose}
            className="px-4 py-1.5 bg-zinc-800 hover:bg-zinc-700 text-zinc-200 rounded-lg font-medium transition-colors"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
};
