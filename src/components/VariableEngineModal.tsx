import React, { useState, useEffect } from 'react';
import { 
  X, 
  Activity, 
  Sliders, 
  Eye, 
  Sparkles, 
  Clock, 
  History, 
  AlertTriangle, 
  ShieldAlert, 
  CheckCircle2, 
  RefreshCw, 
  ArrowRight, 
  TrendingUp, 
  Layers
} from 'lucide-react';

interface VariableEngineModalProps {
  isOpen: boolean;
  onClose: () => void;
}

interface TrackedVariable {
  id: string;
  name: string;
  subtitle: string;
  currentValue: number | string;
  previousValue: number | string;
  lastUpdated: number;
  unit: string;
  history: Array<{ timestamp: number; value: number | string }>;
  isHighFrequency: boolean;
  sparkline: number[];
  anomaly?: {
    severity: 'warning' | 'critical';
    message: string;
    suggestedAction: string;
  };
}

const INITIAL_VARIABLES: TrackedVariable[] = [
  {
    id: 'trackers_blocked',
    name: 'Third-Party Trackers Blocked',
    subtitle: 'Known telemetry, beacon, and advertising domains dropped at the TUN firewall',
    currentValue: 1420,
    previousValue: 1419,
    lastUpdated: Date.now() - 25000, // 25s ago (red)
    unit: 'domains',
    history: [
      { timestamp: Date.now() - 120000, value: 1416 },
      { timestamp: Date.now() - 85000, value: 1418 },
      { timestamp: Date.now() - 25000, value: 1420 }
    ],
    isHighFrequency: false,
    sparkline: [22, 28, 35, 42, 49, 58, 64]
  },
  {
    id: 'probes_answered',
    name: 'Captive Portal Probes Answered',
    subtitle: 'Synthetic HTTP 204 loopback responses provided to keep offline apps running',
    currentValue: 84,
    previousValue: 83,
    lastUpdated: Date.now() - 95000, // 1.5m ago (yellow)
    unit: 'probes',
    history: [
      { timestamp: Date.now() - 300000, value: 81 },
      { timestamp: Date.now() - 95000, value: 84 }
    ],
    isHighFrequency: false,
    sparkline: [10, 12, 14, 15, 18, 19, 21]
  },
  {
    id: 'doh_latency',
    name: 'DoH Quad9 Resolver Latency',
    subtitle: 'Cryptographic DNS-over-HTTPS round-trip handshake time over TLS 1.3',
    currentValue: 34,
    previousValue: 46,
    lastUpdated: Date.now() - 450000, // 7.5m ago (green)
    unit: 'ms',
    history: [
      { timestamp: Date.now() - 600000, value: 52 },
      { timestamp: Date.now() - 450000, value: 34 }
    ],
    isHighFrequency: false,
    sparkline: [52, 48, 55, 42, 38, 36, 34]
  },
  {
    id: 'mac_rotation',
    name: 'Ephemeral MAC Rotations',
    subtitle: 'Randomized 802.11 unicast addresses generated to blind public Wi-Fi scanners',
    currentValue: 12,
    previousValue: 11,
    lastUpdated: Date.now() - 2100000, // 35m ago (pink)
    unit: 'cycles',
    history: [
      { timestamp: Date.now() - 3600000, value: 10 },
      { timestamp: Date.now() - 2100000, value: 12 }
    ],
    isHighFrequency: false,
    sparkline: [2, 4, 6, 8, 9, 11, 12]
  },
  {
    id: 'high_freq_pps',
    name: 'Kernel TUN Packets Per Second',
    subtitle: 'Real-time throughput processed across Go/gVisor user-space network stack',
    currentValue: 184,
    previousValue: 172,
    lastUpdated: Date.now() - 5000,
    unit: 'pkts/s',
    history: [],
    isHighFrequency: true,
    sparkline: [120, 145, 132, 178, 160, 172, 184]
  },
  {
    id: 'cellular_anomaly',
    name: 'Cellular Base Station Anomaly Score',
    subtitle: 'Radio frequency variance heuristic flagging potential IMSI-catcher or rogue tower presence',
    currentValue: '12%',
    previousValue: '11%',
    lastUpdated: Date.now() - 150000,
    unit: 'variance',
    history: [
      { timestamp: Date.now() - 300000, value: '10%' },
      { timestamp: Date.now() - 150000, value: '12%' }
    ],
    isHighFrequency: false,
    sparkline: [8, 9, 10, 10, 11, 11, 12],
    anomaly: {
      severity: 'warning',
      message: 'Sudden 2% increase in neighborless tower handovers detected in LAC 9401.',
      suggestedAction: 'Engage Forced 4G/5G Lock (F150) to sever unencrypted 2G downgrade risk.'
    }
  }
];

export const VariableEngineModal: React.FC<VariableEngineModalProps> = ({
  isOpen,
  onClose
}) => {
  const [variables, setVariables] = useState<TrackedVariable[]>(INITIAL_VARIABLES);
  const [isLiveMode, setIsLiveMode] = useState<boolean>(true); // F871: Live vs Snapshot
  const [useColorblindSafe, setUseColorblindSafe] = useState<boolean>(false); // F874
  const [selectedVariableHistory, setSelectedVariableHistory] = useState<TrackedVariable | null>(null);

  if (!isOpen) return null;

  // F869 & F874: Color coding logic
  const getRecencyStyle = (lastUpdated: number) => {
    const diffMs = Date.now() - lastUpdated;

    if (useColorblindSafe) {
      if (diffMs <= 60000) return 'text-blue-400 bg-blue-500/10 border-blue-500/30'; // Blue ≤1m
      if (diffMs <= 300000) return 'text-amber-400 bg-amber-500/10 border-amber-500/30'; // Amber ≤5m
      if (diffMs <= 1800000) return 'text-teal-400 bg-teal-500/10 border-teal-500/30'; // Teal ≤30m
      return 'text-purple-400 bg-purple-500/10 border-purple-500/30'; // Purple ≤60m
    }

    // Default 4-Tier: Red <=1m, Yellow <=5m, Green <=30m, Pink <=60m
    if (diffMs <= 60000) return 'text-rose-400 bg-rose-500/10 border-rose-500/30'; // Red ≤1m
    if (diffMs <= 300000) return 'text-amber-400 bg-amber-500/10 border-amber-500/30'; // Yellow ≤5m
    if (diffMs <= 1800000) return 'text-emerald-400 bg-emerald-500/10 border-emerald-500/30'; // Green ≤30m
    return 'text-fuchsia-400 bg-fuchsia-500/10 border-fuchsia-500/30'; // Pink ≤60m
  };

  const getRecencyLabel = (lastUpdated: number) => {
    const diffSec = Math.floor((Date.now() - lastUpdated) / 1000);
    if (diffSec < 60) return `${diffSec}s ago (≤1m)`;
    const diffMin = Math.floor(diffSec / 60);
    if (diffMin < 5) return `${diffMin}m ago (≤5m)`;
    if (diffMin < 30) return `${diffMin}m ago (≤30m)`;
    return `${diffMin}m ago (≤60m)`;
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
            <div className="p-2.5 rounded-xl bg-rose-500/10 border border-rose-500/30 text-rose-400">
              <Activity className="w-5 h-5" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h2 className="text-lg font-bold text-white tracking-tight">Variable Change Visualization Engine</h2>
                <span className="px-2 py-0.5 text-[10px] font-semibold bg-rose-500/20 text-rose-300 border border-rose-500/30 rounded">
                  F868–F878 MASTER
                </span>
              </div>
              <p className="text-xs text-slate-400 mt-0.5">
                Section 5.0c: Four-tier recency color coding, FROM→TO change diffs, and frequency-aware sparklines.
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

        {/* Control Strip */}
        <div className="px-6 py-3 bg-slate-950/50 border-b border-slate-800 flex flex-wrap items-center justify-between gap-3 text-xs">
          
          {/* Legend */}
          <div className="flex items-center gap-2">
            <span className="text-slate-400 font-semibold text-[11px] uppercase mr-1">Recency:</span>
            <span className={`px-2 py-0.5 rounded text-[10px] font-mono border ${useColorblindSafe ? 'text-blue-300 bg-blue-500/20 border-blue-500/30' : 'text-rose-300 bg-rose-500/20 border-rose-500/30'}`}>
              ≤1 min
            </span>
            <span className="px-2 py-0.5 rounded text-[10px] font-mono border text-amber-300 bg-amber-500/20 border-amber-500/30">
              ≤5 min
            </span>
            <span className={`px-2 py-0.5 rounded text-[10px] font-mono border ${useColorblindSafe ? 'text-teal-300 bg-teal-500/20 border-teal-500/30' : 'text-emerald-300 bg-emerald-500/20 border-emerald-500/30'}`}>
              ≤30 min
            </span>
            <span className={`px-2 py-0.5 rounded text-[10px] font-mono border ${useColorblindSafe ? 'text-purple-300 bg-purple-500/20 border-purple-500/30' : 'text-fuchsia-300 bg-fuchsia-500/20 border-fuchsia-500/30'}`}>
              ≤60 min
            </span>
          </div>

          {/* Toggles */}
          <div className="flex items-center gap-3">
            {/* Colorblind Safe (F874) */}
            <button
              onClick={() => setUseColorblindSafe(!useColorblindSafe)}
              className={`px-2.5 py-1 rounded-lg border font-semibold text-xs transition-colors ${
                useColorblindSafe ? 'bg-blue-500/20 text-blue-300 border-blue-500/40' : 'bg-slate-900 border-slate-800 text-slate-400 hover:text-white'
              }`}
            >
              {useColorblindSafe ? 'Colorblind Preset' : 'Standard Colors'}
            </button>

            {/* Live vs Snapshot Mode (F871) */}
            <button
              onClick={() => setIsLiveMode(!isLiveMode)}
              className={`px-2.5 py-1 rounded-lg border font-semibold text-xs transition-colors flex items-center gap-1.5 ${
                isLiveMode ? 'bg-emerald-500/20 text-emerald-300 border-emerald-500/40' : 'bg-slate-900 border-slate-800 text-slate-400'
              }`}
            >
              <RefreshCw className={`w-3 h-3 ${isLiveMode ? 'animate-spin' : ''}`} />
              <span>{isLiveMode ? 'Live Mode Active' : 'Snapshot Mode'}</span>
            </button>
          </div>

        </div>

        {/* Content Body */}
        <div className="flex-1 overflow-y-auto p-6 space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {variables.map((v) => (
              <div 
                key={v.id}
                className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex flex-col justify-between gap-3 hover:border-slate-700 transition-all"
              >
                <div>
                  {/* F870: FROM -> TO Change Display Line */}
                  <div className="flex items-center justify-between text-[11px] font-mono text-slate-400 pb-1.5 border-b border-slate-800/80">
                    <div className="flex items-center gap-1.5 truncate">
                      <span className="text-slate-500">FROM:</span>
                      <span className="text-slate-300 font-semibold">{v.previousValue}</span>
                      <ArrowRight className="w-3 h-3 text-slate-500 shrink-0" />
                      <span className="text-white font-bold">{v.currentValue} {v.unit}</span>
                    </div>

                    {/* Recency Badge (F869) */}
                    <span className={`px-2 py-0.5 rounded text-[10px] font-semibold border ${getRecencyStyle(v.lastUpdated)}`}>
                      {getRecencyLabel(v.lastUpdated)}
                    </span>
                  </div>

                  {/* Title & Subtitle (F877) */}
                  <div className="mt-2.5">
                    <div className="flex items-center justify-between">
                      <h4 className="text-sm font-bold text-white">{v.name}</h4>
                      {v.isHighFrequency && (
                        <span className="px-1.5 py-0.5 rounded text-[9px] font-semibold bg-cyan-950/40 text-cyan-300 border border-cyan-500/30">
                          High-Frequency Sparkline (F878)
                        </span>
                      )}
                    </div>
                    <p className="text-xs text-slate-400 mt-0.5 leading-relaxed">{v.subtitle}</p>
                  </div>
                </div>

                {/* F878: High-Frequency Sparkline vs Discrete Metric */}
                {v.isHighFrequency ? (
                  <div className="mt-1 p-2.5 rounded-lg bg-slate-900/60 border border-slate-800">
                    <div className="flex items-center justify-between text-xs mb-1.5">
                      <span className="text-slate-400 text-[10px] uppercase font-semibold">Live Throughput Sparkline</span>
                      <span className="text-cyan-400 font-mono font-bold">{v.currentValue} {v.unit}</span>
                    </div>
                    {/* SVG Sparkline */}
                    <svg className="w-full h-8 overflow-visible" viewBox="0 0 100 24">
                      <path
                        d="M0,18 L15,12 L30,16 L50,4 L70,8 L85,6 L100,2"
                        fill="none"
                        stroke="#06b6d4"
                        strokeWidth="2"
                        strokeLinecap="round"
                        strokeLinejoin="round"
                      />
                    </svg>
                  </div>
                ) : (
                  <div className="flex items-center justify-between pt-1">
                    <div className="text-2xl font-extrabold text-white font-mono">
                      {v.currentValue} <span className="text-xs text-slate-400 font-normal">{v.unit}</span>
                    </div>

                    <button
                      onClick={() => setSelectedVariableHistory(v)}
                      className="px-2.5 py-1 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-300 text-xs font-semibold transition-colors flex items-center gap-1.5"
                    >
                      <History className="w-3.5 h-3.5 text-slate-400" />
                      <span>History (F872)</span>
                    </button>
                  </div>
                )}

                {/* F876: Suspicious Change Detector */}
                {v.anomaly && (
                  <div className="p-3 rounded-lg bg-amber-950/30 border border-amber-500/40 text-amber-200 text-xs space-y-1.5">
                    <div className="flex items-center gap-1.5 font-bold text-amber-300">
                      <AlertTriangle className="w-3.5 h-3.5 text-amber-400" />
                      <span>Anomaly Detected (F876)</span>
                    </div>
                    <p className="text-[11px] text-amber-200/90 leading-relaxed">{v.anomaly.message}</p>
                    <div className="pt-1 text-[10px] font-mono text-amber-300 font-semibold">
                      Recommendation: {v.anomaly.suggestedAction}
                    </div>
                  </div>
                )}
              </div>
            ))}
          </div>

          {/* F872: History Drawer / Modal */}
          {selectedVariableHistory && (
            <div className="fixed inset-0 z-60 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in">
              <div 
                className="bg-slate-900 border border-slate-700 rounded-2xl w-full max-w-lg p-5 space-y-4 shadow-2xl"
                onClick={(e) => e.stopPropagation()}
              >
                <div className="flex items-center justify-between border-b border-slate-800 pb-3">
                  <div>
                    <h3 className="text-sm font-bold text-white">{selectedVariableHistory.name}</h3>
                    <p className="text-xs text-slate-400">Complete Historical Transition Ledger (F872)</p>
                  </div>
                  <button
                    onClick={() => setSelectedVariableHistory(null)}
                    className="p-1 rounded text-slate-400 hover:text-white"
                  >
                    <X className="w-5 h-5" />
                  </button>
                </div>

                <div className="space-y-2 max-h-60 overflow-y-auto font-mono text-xs text-slate-300">
                  {selectedVariableHistory.history.map((h, idx) => (
                    <div key={idx} className="p-2.5 rounded bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                      <span className="text-slate-400">{new Date(h.timestamp).toLocaleTimeString()}</span>
                      <span className="font-bold text-emerald-400">{h.value} {selectedVariableHistory.unit}</span>
                    </div>
                  ))}
                </div>

                <button
                  onClick={() => setSelectedVariableHistory(null)}
                  className="w-full py-2 bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-bold rounded-xl transition-colors"
                >
                  Close History
                </button>
              </div>
            </div>
          )}

        </div>

        {/* Footer */}
        <div className="px-6 py-3 border-t border-slate-800 bg-slate-950/80 flex items-center justify-between text-xs text-slate-400">
          <div className="flex items-center gap-2">
            <span className="w-2 h-2 rounded-full bg-rose-400 animate-pulse" />
            <span>Variable Engine Active (Four-Tier Recency Colors · High-Frequency Sparklines)</span>
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
