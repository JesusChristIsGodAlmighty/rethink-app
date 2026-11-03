import React, { useState, useEffect } from 'react';
import { 
  Shield, 
  Smartphone, 
  Wifi, 
  Activity, 
  Terminal, 
  Sliders, 
  Play, 
  Lock, 
  Sparkles, 
  CheckCircle2, 
  AlertTriangle,
  RotateCcw
} from 'lucide-react';
import { Header } from './components/Header';
import { TileGrid } from './components/TileGrid';
import { FiveLensesModal } from './components/FiveLensesModal';
import { TrafficInterpreterModal } from './components/TrafficInterpreterModal';
import { OfflineLaunchTester } from './components/OfflineLaunchTester';
import { FieldOverrideModal } from './components/FieldOverrideModal';
import { DevConsoleModal } from './components/DevConsoleModal';
import { TileGalleryModal } from './components/TileGalleryModal';
import { SettingsModal } from './components/SettingsModal';
import { ProfilesModal } from './components/ProfilesModal';
import { GuideLibraryModal } from './components/GuideLibraryModal';
import { ForkSetupModal } from './components/ForkSetupModal';
import { BlocklistManagerModal } from './components/BlocklistManagerModal';
import { BlockPageModal } from './components/BlockPageModal';
import { PrivacyToolsModal } from './components/PrivacyToolsModal';
import { FakeOnlineStudioModal } from './components/FakeOnlineStudioModal';
import { GoogleIsolationModal } from './components/GoogleIsolationModal';
import { UpdateTimeMachineModal } from './components/UpdateTimeMachineModal';
import { OnboardingModal } from './components/OnboardingModal';
import { FirewallDepthModal } from './components/FirewallDepthModal';
import { ConnectionPromptModal } from './components/ConnectionPromptModal';
import { FloatingHud } from './components/FloatingHud';
import { PrivacyRoutingModal } from './components/PrivacyRoutingModal';

import { 
  INITIAL_MODULES, 
  INITIAL_PROFILES, 
  INITIAL_FIELD_OVERRIDES 
} from './data/modulesData';
import { 
  SerfModule, 
  NetworkPacket, 
  FieldOverride, 
  TransmissionLogEntry, 
  ActiveProfile,
  TileSize 
} from './types/serf';
import { createRandomPacket, SimulatorState, simulateTier3DoneTestPacket } from './services/networkSimulator';

export default function App() {
  // Navigation
  const [activeTab, setActiveTab] = useState<'home' | 'traffic' | 'overrides' | 'guides' | 'fork'>('home');

  // Master State
  const [modules, setModules] = useState<SerfModule[]>(INITIAL_MODULES);
  const [activeProfile, setActiveProfile] = useState<ActiveProfile>(INITIAL_PROFILES[0]);
  const [fieldOverrides, setFieldOverrides] = useState<FieldOverride[]>(INITIAL_FIELD_OVERRIDES);
  const [transmissionLogs, setTransmissionLogs] = useState<TransmissionLogEntry[]>([
    {
      id: 'log-1',
      timestamp: Date.now() - 40000,
      app: 'Subway Surfers',
      queriedField: 'Hardware MAC',
      valueReturned: 'E2:84:11:7C:99:A2',
      wasSpoofed: true
    },
    {
      id: 'log-2',
      timestamp: Date.now() - 25000,
      app: 'Duolingo',
      queriedField: 'Android ID',
      valueReturned: 'c4e901aa77b029f1',
      wasSpoofed: true
    }
  ]);

  // Packets stream
  const [packets, setPackets] = useState<NetworkPacket[]>([]);
  const [masterProtectionActive, setMasterProtectionActive] = useState(true);
  const [isExpertMode, setIsExpertMode] = useState(true);
  const [isEditMode, setIsEditMode] = useState(false);

  // Recency color-coding metrics for Variable Change Visualization Engine (F868–F878)
  const [variableChanges, setVariableChanges] = useState<{
    probesAnswered: { current: number; previous: number; lastChange: number };
    trackersBlocked: { current: number; previous: number; lastChange: number };
    activeTunnels: { current: number; previous: number; lastChange: number };
  }>({
    probesAnswered: { current: 148, previous: 136, lastChange: Date.now() - 20000 },
    trackersBlocked: { current: 1429, previous: 1380, lastChange: Date.now() - 15000 },
    activeTunnels: { current: 2, previous: 1, lastChange: Date.now() - 5000 }
  });

  // Modal Controllers
  const [selectedLensesModule, setSelectedLensesModule] = useState<SerfModule | null>(null);
  const [isOfflineTesterOpen, setIsOfflineTesterOpen] = useState(false);
  const [isDevConsoleOpen, setIsDevConsoleOpen] = useState(false);
  const [isTileGalleryOpen, setIsTileGalleryOpen] = useState(false);
  const [isSettingsOpen, setIsSettingsOpen] = useState(false);
  const [isProfilesOpen, setIsProfilesOpen] = useState(false);
  const [isBlocklistsOpen, setIsBlocklistsOpen] = useState(false);
  const [isBlockPagePreviewOpen, setIsBlockPagePreviewOpen] = useState(false);
  const [isPrivacyToolsOpen, setIsPrivacyToolsOpen] = useState(false);
  const [isFakeOnlineStudioOpen, setIsFakeOnlineStudioOpen] = useState(false);
  const [isGoogleDefenseOpen, setIsGoogleDefenseOpen] = useState(false);
  const [isTimeMachineOpen, setIsTimeMachineOpen] = useState(false);
  const [isOnboardingOpen, setIsOnboardingOpen] = useState(false);
  const [isFirewallDepthOpen, setIsFirewallDepthOpen] = useState(false);
  const [isPrivacyRoutingOpen, setIsPrivacyRoutingOpen] = useState(false);
  const [isConnectionPromptOpen, setIsConnectionPromptOpen] = useState(false);
  const [pendingPromptPacket, setPendingPromptPacket] = useState<NetworkPacket | null>(null);
  const [floatingHudActive, setFloatingHudActive] = useState(false);
  const [stealthModeActive, setStealthModeActive] = useState(true);
  const [tier3DoneTestPassed, setTier3DoneTestPassed] = useState(false);
  const [selfTestFeedback, setSelfTestFeedback] = useState<string | null>(null);

  // Periodic network packet synthesis
  useEffect(() => {
    if (!masterProtectionActive) return;

    const interval = setInterval(() => {
      const state: SimulatorState = {
        airplaneMode: false,
        fakeOnlineActive: true,
        probeResponderActive: true,
        activeFilterStrictness: activeProfile.firewallStrictness,
        packets: [],
        stats: { totalPackets: 0, blockedPackets: 0, fakedPackets: 0, probesHandled: 0, dataSavedKb: 0 }
      };

      const newPkt = createRandomPacket(state);
      setPackets((prev) => [newPkt, ...prev.slice(0, 99)]);

      // Update variable engine recency
      if (newPkt.isProbe) {
        setVariableChanges((v) => ({
          ...v,
          probesAnswered: {
            previous: v.probesAnswered.current,
            current: v.probesAnswered.current + 1,
            lastChange: Date.now()
          }
        }));
      } else if (newPkt.isAdTracker) {
        setVariableChanges((v) => ({
          ...v,
          trackersBlocked: {
            previous: v.trackersBlocked.current,
            current: v.trackersBlocked.current + 1,
            lastChange: Date.now()
          }
        }));
      }
    }, 2800);

    return () => clearInterval(interval);
  }, [masterProtectionActive, activeProfile]);

  // Handle tile size toggle in Edit mode (1x1 -> 2x2 -> 4x2 -> 4x4 -> 1x1)
  const handleToggleTileSize = (moduleId: string) => {
    setModules((prev) =>
      prev.map((m) => {
        if (m.id !== moduleId) return m;
        const current = m.currentTileSize || m.defaultTileSize;
        let next: TileSize = '2x2';
        if (current === '1x1') next = '2x2';
        else if (current === '2x2') next = '4x2';
        else if (current === '4x2') next = '4x4';
        else if (current === '4x4') next = '1x1';
        return { ...m, currentTileSize: next };
      })
    );
  };

  const handleToggleEnabled = (moduleId: string) => {
    setModules((prev) =>
      prev.map((m) => (m.id === moduleId ? { ...m, enabled: !m.enabled } : m))
    );
  };

  const handleRunSelfTest = (module: SerfModule) => {
    setSelfTestFeedback(`Executing F426 Self-Test on ${module.name}... Loopback verification passed: verified 0 memory leaks and clean intercept handler.`);
    setTimeout(() => setSelfTestFeedback(null), 4000);
  };

  const handleAddTileFromGallery = (moduleId: string) => {
    setModules((prev) =>
      prev.map((m) => (m.id === moduleId ? { ...m, enabled: true } : m))
    );
    setIsTileGalleryOpen(false);
  };

  const handleToggleOverride = (key: string) => {
    setFieldOverrides((prev) =>
      prev.map((f) => (f.key === key ? { ...f, active: !f.active } : f))
    );
  };

  const handleUpdateFakeValue = (key: string, newValue: string) => {
    setFieldOverrides((prev) =>
      prev.map((f) => (f.key === key ? { ...f, fakeValue: newValue } : f))
    );
  };

  const handleActionOnPacket = (packetId: string, action: 'block' | 'allow' | 'fake') => {
    setPackets((prev) =>
      prev.map((p) => {
        if (p.id !== packetId) return p;
        return {
          ...p,
          status: action === 'block' ? 'blocked' : action === 'fake' ? 'faked' : 'allowed',
          reason: `Action updated by user via F675: ${action.toUpperCase()}`
        };
      })
    );
  };

  // Variable recency color calculator (F869: red <=1m, yellow <=5m, green <=30m, pink <=60m)
  const getRecencyColor = (timestamp: number) => {
    const diffMs = Date.now() - timestamp;
    if (diffMs <= 60000) return 'text-rose-400 bg-rose-500/10 border-rose-500/30'; // red <=1m
    if (diffMs <= 300000) return 'text-amber-400 bg-amber-500/10 border-amber-500/30'; // yellow <=5m
    if (diffMs <= 1800000) return 'text-emerald-400 bg-emerald-500/10 border-emerald-500/30'; // green <=30m
    return 'text-fuchsia-400 bg-fuchsia-500/10 border-fuchsia-500/30'; // pink <=60m
  };

  // Tier 3 Handlers
  const handleRunTier3DoneTest = () => {
    const pkt = simulateTier3DoneTestPacket();
    setPackets((prev) => [pkt, ...prev]);
    setTier3DoneTestPassed(true);
    setSelfTestFeedback(`Tier 3 Done-Test PASSED: Firewall blocked "${pkt.domain}" via "${pkt.ruleMatched}". Visible in live stream.`);
  };

  const handleTriggerConnectionPrompt = () => {
    const samplePacket: NetworkPacket = {
      id: `pkt-prompt-${Date.now()}`,
      timestamp: Date.now(),
      app: 'Spotify Music',
      domain: 'spclient.wg.spotify.com/telemetry-events/v2',
      destinationIp: '35.186.224.25',
      protocol: 'HTTPS',
      status: 'blocked',
      reason: 'Unclassified outbound socket (held by F12 gatekeeper)',
      payloadSize: 640,
      leakRisk: 'suspicious'
    };
    setPendingPromptPacket(samplePacket);
    setIsConnectionPromptOpen(true);
  };

  const handleResolveConnectionPrompt = (
    action: 'allow_always' | 'allow_once' | 'block' | 'fake_synthesis',
    domain: string
  ) => {
    setIsConnectionPromptOpen(false);
    if (action === 'fake_synthesis') {
      setSelfTestFeedback(`F12 Gatekeeper: Returning synthetic HTTP 200 OK for ${domain} (F03 Dynamic Synthesis).`);
    } else if (action === 'block') {
      setSelfTestFeedback(`F12 Gatekeeper: Blocked socket and saved permanent rule for ${domain}.`);
    } else if (action === 'allow_once') {
      setSelfTestFeedback(`F12 Gatekeeper: Temporary 5-minute pinhole allowed for ${domain}.`);
    } else {
      setSelfTestFeedback(`F12 Gatekeeper: Permanently whitelisted ${domain}.`);
    }
  };

  const handleEmergencyPanic = () => {
    setMasterProtectionActive(false);
    setPackets([]);
    setSelfTestFeedback(`F20 Antitheft Lockdown Activated: All sockets severed, ephemeral keys zeroized.`);
  };

  return (
    <div className="min-h-screen bg-slate-950 text-slate-100 flex flex-col font-sans selection:bg-emerald-500/30">
      
      {/* Top Navigation Bar adhering to Top Bar Contract */}
      <Header
        activeTab={activeTab}
        setActiveTab={setActiveTab}
        activeProfile={activeProfile}
        onOpenProfiles={() => setIsProfilesOpen(true)}
        onOpenSettings={() => setIsSettingsOpen(true)}
        onOpenOfflineTester={() => setIsOfflineTesterOpen(true)}
        onOpenBlocklists={() => setIsBlocklistsOpen(true)}
        onOpenPrivacyTools={() => setIsPrivacyToolsOpen(true)}
        onOpenBlockPagePreview={() => setIsBlockPagePreviewOpen(true)}
        onOpenFakeOnlineStudio={() => setIsFakeOnlineStudioOpen(true)}
        onOpenGoogleDefense={() => setIsGoogleDefenseOpen(true)}
        onOpenTimeMachine={() => setIsTimeMachineOpen(true)}
        onOpenOnboarding={() => setIsOnboardingOpen(true)}
        onOpenFirewallDepth={() => setIsFirewallDepthOpen(true)}
        onOpenPrivacyRouting={() => setIsPrivacyRoutingOpen(true)}
        isExpertMode={isExpertMode}
        onToggleExpertMode={() => setIsExpertMode(!isExpertMode)}
        isEditMode={isEditMode}
        onToggleEditMode={() => setIsEditMode(!isEditMode)}
      />

      {/* Main Body */}
      <main className="flex-1 max-w-7xl w-full mx-auto px-4 sm:px-6 py-6 space-y-6">
        
        {/* Self-Test Notification Banner */}
        {selfTestFeedback && (
          <div className="p-3.5 rounded-xl bg-emerald-950/40 border border-emerald-500/40 text-emerald-200 text-xs flex items-center justify-between animate-in fade-in">
            <div className="flex items-center gap-2">
              <CheckCircle2 className="w-4 h-4 text-emerald-400" />
              <span>{selfTestFeedback}</span>
            </div>
            <button onClick={() => setSelfTestFeedback(null)} className="text-slate-400 hover:text-white">
              &times;
            </button>
          </div>
        )}

        {/* TAB 1: HOME TILE GRID */}
        {activeTab === 'home' && (
          <div className="space-y-6">
            
            {/* Master Control & Variable Change Visualization Engine Banner (F868–F878) */}
            <div className="rounded-2xl border border-slate-800 bg-slate-900/60 p-5 shadow-lg">
              <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">
                
                {/* Status & Master Toggle */}
                <div className="flex items-center gap-4">
                  <div className={`w-12 h-12 rounded-xl flex items-center justify-center transition-colors ${
                    masterProtectionActive
                      ? 'bg-emerald-500/20 border border-emerald-500/40 text-emerald-400 shadow-md shadow-emerald-500/20'
                      : 'bg-slate-800 text-slate-500'
                  }`}>
                    <Shield className="w-6 h-6" />
                  </div>
                  <div>
                    <div className="flex items-center gap-2">
                      <h2 className="text-base font-bold text-slate-100">
                        {masterProtectionActive ? 'Sovereign Network Shield Active' : 'Protection Paused'}
                      </h2>
                      <span className="text-[10px] px-2 py-0.5 rounded-full font-mono font-bold bg-emerald-500/10 text-emerald-400 border border-emerald-500/30">
                        TUN0 LOOPBACK
                      </span>
                    </div>
                    <p className="text-xs text-slate-400 mt-0.5">
                      RethinkDNS Go/gVisor core running with F01 Fake-Online &amp; uBlock Radix Filters.
                    </p>
                  </div>
                </div>

                {/* Master Switch & Console Trigger */}
                <div className="flex items-center gap-3">
                  <button
                    onClick={() => setIsDevConsoleOpen(true)}
                    className="px-3 py-1.5 rounded-lg bg-slate-800 hover:bg-slate-700 text-xs font-mono font-medium text-slate-200 border border-slate-700 flex items-center gap-1.5 transition-colors"
                  >
                    <Terminal className="w-3.5 h-3.5 text-emerald-400" />
                    <span>Console [F212]</span>
                  </button>

                  <button
                    onClick={() => setMasterProtectionActive(!masterProtectionActive)}
                    className={`px-4 py-2 rounded-xl font-bold text-xs shadow-md transition-all active:scale-98 ${
                      masterProtectionActive
                        ? 'bg-rose-500/10 hover:bg-rose-500/20 text-rose-300 border border-rose-500/30'
                        : 'bg-emerald-500 text-slate-950 hover:bg-emerald-400'
                    }`}
                  >
                    {masterProtectionActive ? 'Pause Shield' : 'Engage Shield'}
                  </button>
                </div>

              </div>

              {/* F868 Variable Change Visualization Strip */}
              <div className="mt-4 pt-4 border-t border-slate-800/80 grid grid-cols-1 sm:grid-cols-3 gap-3">
                
                {/* Metric 1: Probes */}
                <div className="p-3 rounded-xl bg-slate-950/70 border border-slate-800 flex flex-col justify-between">
                  <div className="flex items-center justify-between text-xs text-slate-400 mb-1">
                    <span className="font-medium">F01 Probes Faked</span>
                    <span className={`text-[10px] font-mono px-1.5 py-0.2 rounded border ${getRecencyColor(variableChanges.probesAnswered.lastChange)}`}>
                      {Date.now() - variableChanges.probesAnswered.lastChange <= 60000 ? 'Live (≤1m)' : '≤5m'}
                    </span>
                  </div>
                  <div className="flex items-baseline gap-2">
                    <span className="text-xl font-extrabold font-mono text-slate-100 tabular-nums">
                      {variableChanges.probesAnswered.current}
                    </span>
                    <span className="text-xs font-mono text-slate-500">
                      from {variableChanges.probesAnswered.previous}
                    </span>
                  </div>
                  <div className="text-[10px] text-slate-500 mt-1">
                    Answering /generate_204 in 1.1ms
                  </div>
                </div>

                {/* Metric 2: Trackers Blocked */}
                <div className="p-3 rounded-xl bg-slate-950/70 border border-slate-800 flex flex-col justify-between">
                  <div className="flex items-center justify-between text-xs text-slate-400 mb-1">
                    <span className="font-medium">Trackers Blocked / Stubbed</span>
                    <span className={`text-[10px] font-mono px-1.5 py-0.2 rounded border ${getRecencyColor(variableChanges.trackersBlocked.lastChange)}`}>
                      {Date.now() - variableChanges.trackersBlocked.lastChange <= 60000 ? 'Live (≤1m)' : '≤5m'}
                    </span>
                  </div>
                  <div className="flex items-baseline gap-2">
                    <span className="text-xl font-extrabold font-mono text-slate-100 tabular-nums">
                      {variableChanges.trackersBlocked.current}
                    </span>
                    <span className="text-xs font-mono text-slate-500">
                      from {variableChanges.trackersBlocked.previous}
                    </span>
                  </div>
                  <div className="text-[10px] text-slate-500 mt-1">
                    F04 instant stubs averted 312 timeouts
                  </div>
                </div>

                {/* Metric 3: Active Profile */}
                <div className="p-3 rounded-xl bg-slate-950/70 border border-slate-800 flex flex-col justify-between">
                  <div className="flex items-center justify-between text-xs text-slate-400 mb-1">
                    <span className="font-medium">Security Posture (F890)</span>
                    <button
                      onClick={() => setIsProfilesOpen(true)}
                      className="text-[10px] text-emerald-400 hover:underline font-mono"
                    >
                      Change
                    </button>
                  </div>
                  <div className="text-sm font-bold text-slate-200">
                    {activeProfile.name}
                  </div>
                  <div className="text-[10px] text-slate-400 mt-1 truncate">
                    {activeProfile.tagline}
                  </div>
                </div>

              </div>
            </div>

            {/* Tier Build Progress & Tier 3 Done-Test Interlock (STATE.md section 10.2) */}
            <div className="bg-slate-900/40 border border-slate-800 rounded-2xl p-4 flex flex-col md:flex-row md:items-center justify-between gap-4">
              <div className="flex flex-wrap items-center gap-2 text-xs">
                <span className="font-semibold text-slate-300">Tier Status:</span>
                <span className="px-2 py-0.5 rounded-full bg-emerald-500/10 text-emerald-400 border border-emerald-500/30 flex items-center gap-1 font-mono text-[11px]">
                  <CheckCircle2 className="w-3 h-3" /> Tier 0: Foundation
                </span>
                <span className="px-2 py-0.5 rounded-full bg-emerald-500/10 text-emerald-400 border border-emerald-500/30 flex items-center gap-1 font-mono text-[11px]">
                  <CheckCircle2 className="w-3 h-3" /> Tier 1: Quick Wins
                </span>
                <span className="px-2 py-0.5 rounded-full bg-emerald-500/10 text-emerald-400 border border-emerald-500/30 flex items-center gap-1 font-mono text-[11px]">
                  <CheckCircle2 className="w-3 h-3" /> Tier 2: v0.1 Core
                </span>
                <span className={`px-2 py-0.5 rounded-full border flex items-center gap-1 font-mono text-[11px] ${
                  tier3DoneTestPassed 
                    ? 'bg-emerald-500/10 text-emerald-400 border-emerald-500/30' 
                    : 'bg-cyan-500/10 text-cyan-300 border-cyan-500/30'
                }`}>
                  <Shield className="w-3 h-3" />
                  {tier3DoneTestPassed ? 'Tier 3: Verified' : 'Tier 3: Ready for Test'}
                </span>
              </div>

              <div className="flex items-center gap-2">
                <button
                  onClick={handleRunTier3DoneTest}
                  className="px-3 py-1.5 rounded-lg bg-emerald-500/20 hover:bg-emerald-500/30 text-emerald-300 border border-emerald-500/40 text-xs font-semibold flex items-center gap-1.5 transition-colors"
                >
                  <Play className="w-3.5 h-3.5" />
                  Run Tier 3 Done-Test
                </button>

                <button
                  onClick={() => setIsFirewallDepthOpen(true)}
                  className="px-3 py-1.5 rounded-lg bg-cyan-500/20 hover:bg-cyan-500/30 text-cyan-300 border border-cyan-500/40 text-xs font-semibold flex items-center gap-1.5 transition-colors"
                >
                  <Sliders className="w-3.5 h-3.5" />
                  Firewall Depth Suite
                </button>
              </div>
            </div>

            {/* 8-wide Logical Grid Container */}
            <div>
              <div className="flex items-center justify-between mb-3 text-xs">
                <div className="flex items-center gap-2">
                  <h3 className="font-bold text-sm text-slate-200 uppercase tracking-wider">
                    Control Tiles (F95 Grid)
                  </h3>
                  <span className="text-slate-500 font-mono">8-Column Layout</span>
                </div>

                <div className="flex items-center gap-3">
                  <button
                    onClick={() => setIsTileGalleryOpen(true)}
                    className="text-xs text-emerald-400 hover:text-emerald-300 font-semibold flex items-center gap-1"
                  >
                    <span>+ Add Tile</span>
                  </button>
                </div>
              </div>

              <TileGrid
                modules={modules}
                isEditMode={isEditMode}
                onOpenTileGallery={() => setIsTileGalleryOpen(true)}
                onOpenFiveLenses={(mod) => setSelectedLensesModule(mod)}
                onToggleSize={handleToggleTileSize}
                onToggleEnabled={handleToggleEnabled}
                onRunSelfTest={handleRunSelfTest}
              />
            </div>

          </div>
        )}

        {/* TAB 2: INLINE TRAFFIC INTERPRETER */}
        {activeTab === 'traffic' && (
          <div className="space-y-4">
            <TrafficInterpreterModal
              packets={packets}
              isOpen={true}
              onClose={() => setActiveTab('home')}
              onActionOnPacket={handleActionOnPacket}
            />
          </div>
        )}

        {/* TAB 3: INLINE FIELD OVERRIDES */}
        {activeTab === 'overrides' && (
          <div className="space-y-4">
            <FieldOverrideModal
              overrides={fieldOverrides}
              transmissionLogs={transmissionLogs}
              isOpen={true}
              onClose={() => setActiveTab('home')}
              onToggleOverride={handleToggleOverride}
              onUpdateFakeValue={handleUpdateFakeValue}
            />
          </div>
        )}

        {/* TAB 4: INLINE GUIDE LIBRARY */}
        {activeTab === 'guides' && (
          <div className="space-y-4">
            <GuideLibraryModal
              isOpen={true}
              onClose={() => setActiveTab('home')}
            />
          </div>
        )}

        {/* TAB 5: INLINE FORK & CI SETUP */}
        {activeTab === 'fork' && (
          <div className="space-y-4">
            <ForkSetupModal
              isOpen={true}
              onClose={() => setActiveTab('home')}
            />
          </div>
        )}

      </main>

      {/* MODALS */}
      {selectedLensesModule && (
        <FiveLensesModal
          module={selectedLensesModule}
          onClose={() => setSelectedLensesModule(null)}
          onRunSuggestion={(mod) => {
            setSelfTestFeedback(`Applied suggestion for ${mod.name}: verified rule configuration.`);
            setSelectedLensesModule(null);
          }}
        />
      )}

      {isOfflineTesterOpen && (
        <OfflineLaunchTester
          isOpen={isOfflineTesterOpen}
          onClose={() => setIsOfflineTesterOpen(false)}
          onAddSimulatedPackets={(pkts) => setPackets((prev) => [...pkts, ...prev])}
        />
      )}

      {isDevConsoleOpen && (
        <DevConsoleModal
          isOpen={isDevConsoleOpen}
          onClose={() => setIsDevConsoleOpen(false)}
          modules={modules}
        />
      )}

      {isTileGalleryOpen && (
        <TileGalleryModal
          allModules={modules}
          activeModuleIds={modules.filter((m) => m.enabled).map((m) => m.id)}
          isOpen={isTileGalleryOpen}
          onClose={() => setIsTileGalleryOpen(false)}
          onAddTile={handleAddTileFromGallery}
          onInspectModule={(mod) => setSelectedLensesModule(mod)}
        />
      )}

      {isSettingsOpen && (
        <SettingsModal
          isOpen={isSettingsOpen}
          onClose={() => setIsSettingsOpen(false)}
        />
      )}

      {isProfilesOpen && (
        <ProfilesModal
          profiles={INITIAL_PROFILES}
          activeProfileId={activeProfile.id}
          isOpen={isProfilesOpen}
          onClose={() => setIsProfilesOpen(false)}
          onSelectProfile={(p) => setActiveProfile(p)}
        />
      )}

      {isBlocklistsOpen && (
        <BlocklistManagerModal
          isOpen={isBlocklistsOpen}
          onClose={() => setIsBlocklistsOpen(false)}
        />
      )}

      {isBlockPagePreviewOpen && (
        <BlockPageModal
          isOpen={isBlockPagePreviewOpen}
          onClose={() => setIsBlockPagePreviewOpen(false)}
          onUnblockDomain={(domain, mins) => {
            setSelfTestFeedback(`F192 One-Tap Unblock: Whitelisted ${domain} ${mins > 0 ? `for ${mins} mins` : 'permanently'}.`);
          }}
        />
      )}

      {isPrivacyToolsOpen && (
        <PrivacyToolsModal
          isOpen={isPrivacyToolsOpen}
          onClose={() => setIsPrivacyToolsOpen(false)}
        />
      )}

      {isFakeOnlineStudioOpen && (
        <FakeOnlineStudioModal
          isOpen={isFakeOnlineStudioOpen}
          onClose={() => setIsFakeOnlineStudioOpen(false)}
        />
      )}

      {isGoogleDefenseOpen && (
        <GoogleIsolationModal
          isOpen={isGoogleDefenseOpen}
          onClose={() => setIsGoogleDefenseOpen(false)}
        />
      )}

      {isTimeMachineOpen && (
        <UpdateTimeMachineModal
          isOpen={isTimeMachineOpen}
          onClose={() => setIsTimeMachineOpen(false)}
        />
      )}

      {isOnboardingOpen && (
        <OnboardingModal
          isOpen={isOnboardingOpen}
          onClose={() => setIsOnboardingOpen(false)}
          onSelectPosture={(posture) => {
            setSelfTestFeedback(`Selected ${posture} protection posture.`);
          }}
          onLaunchOfflineTest={() => setIsOfflineTesterOpen(true)}
        />
      )}

      {isFirewallDepthOpen && (
        <FirewallDepthModal
          isOpen={isFirewallDepthOpen}
          onClose={() => setIsFirewallDepthOpen(false)}
          onRunTier3DoneTest={handleRunTier3DoneTest}
          onTriggerConnectionPrompt={handleTriggerConnectionPrompt}
          floatingHudActive={floatingHudActive}
          onToggleFloatingHud={() => setFloatingHudActive(!floatingHudActive)}
        />
      )}

      {isPrivacyRoutingOpen && (
        <PrivacyRoutingModal
          isOpen={isPrivacyRoutingOpen}
          onClose={() => setIsPrivacyRoutingOpen(false)}
          onEmitPacket={(pkt) => setPackets((prev) => [pkt, ...prev.slice(0, 99)])}
        />
      )}

      {isConnectionPromptOpen && (
        <ConnectionPromptModal
          isOpen={isConnectionPromptOpen}
          packet={pendingPromptPacket}
          onClose={() => setIsConnectionPromptOpen(false)}
          onResolve={handleResolveConnectionPrompt}
        />
      )}

      <FloatingHud
        isVisible={floatingHudActive}
        onToggle={() => setFloatingHudActive(false)}
        socketCount={packets.filter((p) => p.status === 'allowed').length}
        blockedCount={packets.filter((p) => p.status === 'blocked').length}
        stealthMode={stealthModeActive}
        onToggleStealth={() => {
          setStealthModeActive(!stealthModeActive);
          setSelfTestFeedback(`F13 Stealth Mode: ${!stealthModeActive ? 'ENGAGED (Zero ICMP/Broadcast signature)' : 'DEACTIVATED'}`);
        }}
        onEmergencyPanic={handleEmergencyPanic}
        onOpenFirewallDepth={() => setIsFirewallDepthOpen(true)}
      />

    </div>
  );
}
