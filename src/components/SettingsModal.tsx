import React, { useState } from 'react';
import { 
  X, 
  ChevronRight, 
  ArrowLeft, 
  Globe, 
  Shield, 
  Lock, 
  Smartphone, 
  Sliders, 
  Terminal,
  AlertTriangle
} from 'lucide-react';

interface SettingsModalProps {
  isOpen: boolean;
  onClose: () => void;
  isDeviceRooted?: boolean;
}

type SettingsCategory = 
  | 'overview'
  | 'network'
  | 'privacy'
  | 'firewall'
  | 'device'
  | 'appearance'
  | 'advanced';

export const SettingsModal: React.FC<SettingsModalProps> = ({
  isOpen,
  onClose,
  isDeviceRooted = false
}) => {
  if (!isOpen) return null;

  const [currentCategory, setCurrentCategory] = useState<SettingsCategory>('overview');

  // Toggle states
  const [dohStrict, setDohStrict] = useState(true);
  const [fastFailStubs, setFastFailStubs] = useState(true);
  const [fakeOnline, setFakeOnline] = useState(true);
  const [sensorJitter, setSensorJitter] = useState(true);
  const [blockAllIpv6, setBlockAllIpv6] = useState(false);

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-2xl h-[80vh] flex flex-col shadow-2xl overflow-hidden text-sm"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="p-5 border-b border-slate-800 flex items-center justify-between gap-4">
          <div className="flex items-center gap-3">
            {currentCategory !== 'overview' && (
              <button
                onClick={() => setCurrentCategory('overview')}
                className="p-1.5 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 transition-colors mr-1"
              >
                <ArrowLeft className="w-5 h-5" />
              </button>
            )}
            <div>
              <h2 className="text-lg font-bold text-slate-100">
                {currentCategory === 'overview' ? 'SERF Settings' : `${currentCategory.charAt(0).toUpperCase() + currentCategory.slice(1)} Settings`}
              </h2>
              <p className="text-xs text-slate-400">
                {currentCategory === 'overview' ? 'Stock Android drill-down categories (Section 4.11)' : 'Configure toggles, tunables, and root capabilities'}
              </p>
            </div>
          </div>

          <button
            onClick={onClose}
            className="p-1.5 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Category List (Overview Mode) */}
        {currentCategory === 'overview' && (
          <div className="flex-1 overflow-y-auto p-4 space-y-1">
            {[
              { id: 'network', label: 'Network & Encrypted DNS', desc: 'DoH/DoT resolvers, loopback tunnels, and captive portals', icon: Globe },
              { id: 'privacy', label: 'Privacy & Identity Disguise', desc: 'Outbound field spoofing, SensorID jitter, and MAC rotation', icon: Lock },
              { id: 'firewall', label: 'Firewall & Blocking Rules', desc: 'uBlock list engine, fast-fail stubs, and per-app attribution', icon: Shield },
              { id: 'device', label: 'Device & System Hardening', desc: 'Direct-boot protection, battery tuning, and app hibernation', icon: Smartphone },
              { id: 'appearance', label: 'Appearance & Tile Grid', desc: '8-wide grid density, recency color schemes, and theme', icon: Sliders },
              { id: 'advanced', label: 'Advanced & Developer Console', desc: 'In-app sandboxed terminal, Lua scripting, and raw packet dumps', icon: Terminal }
            ].map((cat) => {
              const Icon = cat.icon;
              return (
                <button
                  key={cat.id}
                  onClick={() => setCurrentCategory(cat.id as SettingsCategory)}
                  className="w-full p-4 rounded-xl hover:bg-slate-800/60 transition-colors flex items-center justify-between text-left group"
                >
                  <div className="flex items-center gap-3.5">
                    <div className="p-2.5 rounded-lg bg-slate-800 border border-slate-700/60 text-slate-300 group-hover:text-emerald-400 transition-colors">
                      <Icon className="w-5 h-5" />
                    </div>
                    <div>
                      <div className="font-semibold text-slate-200 group-hover:text-white transition-colors">{cat.label}</div>
                      <div className="text-xs text-slate-400">{cat.desc}</div>
                    </div>
                  </div>
                  <ChevronRight className="w-4 h-4 text-slate-500 group-hover:text-slate-300 transition-colors" />
                </button>
              );
            })}
          </div>
        )}

        {/* Drill-down Sub-Screens with ROOT Settings Subsection */}
        {currentCategory !== 'overview' && (
          <div className="flex-1 overflow-y-auto p-6 space-y-6">
            
            {/* Standard Settings Section */}
            <div className="space-y-4">
              <h3 className="text-xs font-semibold uppercase tracking-wider text-slate-400">
                General Preferences
              </h3>

              <div className="space-y-3">
                <div className="p-3.5 rounded-xl bg-slate-950 border border-slate-800 flex items-center justify-between">
                  <div>
                    <div className="font-medium text-slate-200">Strict DNSSEC Enforcement</div>
                    <div className="text-xs text-slate-500">Drop DNS answers with invalid cryptographic signatures</div>
                  </div>
                  <button
                    onClick={() => setDohStrict(!dohStrict)}
                    className={`px-3 py-1 rounded text-xs font-semibold ${dohStrict ? 'bg-emerald-500/20 text-emerald-300' : 'bg-slate-800 text-slate-500'}`}
                  >
                    {dohStrict ? 'ON' : 'OFF'}
                  </button>
                </div>

                <div className="p-3.5 rounded-xl bg-slate-950 border border-slate-800 flex items-center justify-between">
                  <div>
                    <div className="font-medium text-slate-200">F04 Fast-Fail Stubs</div>
                    <div className="text-xs text-slate-500">Return instant empty JSON instead of timing out on blocked hosts</div>
                  </div>
                  <button
                    onClick={() => setFastFailStubs(!fastFailStubs)}
                    className={`px-3 py-1 rounded text-xs font-semibold ${fastFailStubs ? 'bg-emerald-500/20 text-emerald-300' : 'bg-slate-800 text-slate-500'}`}
                  >
                    {fastFailStubs ? 'ON' : 'OFF'}
                  </button>
                </div>

                <div className="p-3.5 rounded-xl bg-slate-950 border border-slate-800 flex items-center justify-between">
                  <div>
                    <div className="font-medium text-slate-200">F01 Fake-Online Probe Responder</div>
                    <div className="text-xs text-slate-500">Intercept /generate_204 with synthetic HTTP 204 loopback</div>
                  </div>
                  <button
                    onClick={() => setFakeOnline(!fakeOnline)}
                    className={`px-3 py-1 rounded text-xs font-semibold ${fakeOnline ? 'bg-emerald-500/20 text-emerald-300' : 'bg-slate-800 text-slate-500'}`}
                  >
                    {fakeOnline ? 'ON' : 'OFF'}
                  </button>
                </div>
              </div>
            </div>

            {/* MANDATORY ROOT SETTINGS SUBSECTION (Section 4.11 spec) */}
            <div className="pt-4 border-t border-slate-800/80 space-y-3">
              <div className="flex items-center justify-between">
                <div className="flex items-center gap-2">
                  <h3 className="text-xs font-semibold uppercase tracking-wider text-slate-400">
                    Root Settings
                  </h3>
                  <span className="text-[10px] px-1.5 py-0.5 rounded bg-slate-800 text-slate-400 font-mono">
                    {isDeviceRooted ? 'Root Active' : 'Unrooted Device'}
                  </span>
                </div>
              </div>

              <div className="p-3 bg-slate-950/40 rounded-xl border border-slate-800/60 text-xs text-slate-400 leading-relaxed">
                <span className="font-semibold text-slate-300">Section 4.11 Specification:</span> Root settings are always visible to make deep capabilities discoverable, but grayed out and non-interactive without elevated root access.
              </div>

              {/* Grayed-out root controls */}
              <div className="space-y-2.5 opacity-50 pointer-events-none select-none">
                <div className="p-3.5 rounded-xl bg-slate-950 border border-slate-800 flex items-center justify-between">
                  <div>
                    <div className="font-medium text-slate-300">Direct eBPF UID Quarantine (F981)</div>
                    <div className="text-xs text-slate-500">netd BPF map-level instant blocking with zero kernel latency</div>
                  </div>
                  <span className="text-xs text-slate-600 font-mono">ROOT REQUIRED</span>
                </div>

                <div className="p-3.5 rounded-xl bg-slate-950 border border-slate-800 flex items-center justify-between">
                  <div>
                    <div className="font-medium text-slate-300">Wi-Fi Monitor Mode &amp; Raw Frame Injection (F968)</div>
                    <div className="text-xs text-slate-500">Passive radio packet capture on compatible wireless chipsets</div>
                  </div>
                  <span className="text-xs text-slate-600 font-mono">ROOT REQUIRED</span>
                </div>

                <div className="p-3.5 rounded-xl bg-slate-950 border border-slate-800 flex items-center justify-between">
                  <div>
                    <div className="font-medium text-slate-300">FM Tuner PCM Audio Redirect (F939)</div>
                    <div className="text-xs text-slate-500">Capture internal hardware radio output into Bluetooth sink</div>
                  </div>
                  <span className="text-xs text-slate-600 font-mono">ROOT REQUIRED</span>
                </div>
              </div>
            </div>

          </div>
        )}

      </div>
    </div>
  );
};
