import React, { useState } from 'react';
import { 
  X, 
  Smartphone, 
  Plane, 
  Wifi, 
  CheckCircle2, 
  AlertOctagon, 
  Play, 
  Sparkles,
  ArrowRight
} from 'lucide-react';
import { simulateOfflineAppLaunch } from '../services/networkSimulator';
import { NetworkPacket } from '../types/serf';

interface OfflineLaunchTesterProps {
  isOpen: boolean;
  onClose: () => void;
  onAddSimulatedPackets: (packets: NetworkPacket[]) => void;
}

export const OfflineLaunchTester: React.FC<OfflineLaunchTesterProps> = ({
  isOpen,
  onClose,
  onAddSimulatedPackets
}) => {
  if (!isOpen) return null;

  const [selectedApp, setSelectedApp] = useState('Subway Surfers');
  const [airplaneMode, setAirplaneMode] = useState(true);
  const [fakeOnlineEnabled, setFakeOnlineEnabled] = useState(true);
  const [isTesting, setIsTesting] = useState(false);
  const [testResult, setTestResult] = useState<{
    success: boolean;
    probeStatus: string;
    telemetryStatus: string;
    uiOutcome: string;
    packetsGenerated: NetworkPacket[];
  } | null>(null);

  const handleRunTest = () => {
    setIsTesting(true);
    setTestResult(null);

    setTimeout(() => {
      setIsTesting(false);
      const result = simulateOfflineAppLaunch(selectedApp, fakeOnlineEnabled);
      setTestResult(result);
      onAddSimulatedPackets(result.packetsGenerated);
    }, 600);
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-2xl flex flex-col shadow-2xl overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="p-5 border-b border-slate-800 flex items-center justify-between gap-4">
          <div className="flex items-center gap-3">
            <div className="p-2 rounded-lg bg-emerald-500/10 border border-emerald-500/30 text-emerald-400">
              <Smartphone className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>v0.1 MVP Offline App Launch Verifier</span>
                <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-emerald-400 font-bold border border-slate-700">
                  F860 / F01
                </span>
              </h2>
              <p className="text-xs text-slate-400">
                Verifies Section 10.1: answering generate_204 with synthetic loopback responses while in Airplane Mode.
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

        {/* Configuration Body */}
        <div className="p-6 space-y-6 text-sm">
          
          {/* Target App Selector */}
          <div>
            <label className="text-xs font-semibold text-slate-300 uppercase tracking-wider block mb-2">
              Select Target Test App
            </label>
            <div className="grid grid-cols-2 sm:grid-cols-4 gap-2">
              {['Subway Surfers', 'Duolingo', 'Spotify', 'Adobe Acrobat'].map((app) => (
                <button
                  key={app}
                  onClick={() => { setSelectedApp(app); setTestResult(null); }}
                  className={`p-3 rounded-xl border text-xs font-semibold text-left transition-all ${
                    selectedApp === app
                      ? 'bg-emerald-500/10 border-emerald-500/40 text-emerald-300'
                      : 'bg-slate-950 border-slate-800 text-slate-400 hover:text-slate-200'
                  }`}
                >
                  {app}
                </button>
              ))}
            </div>
          </div>

          {/* Test Conditions */}
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            
            {/* Airplane Mode Toggle */}
            <div className="p-4 rounded-xl bg-slate-950 border border-slate-800 flex items-center justify-between">
              <div className="flex items-center gap-2.5">
                <Plane className={`w-4 h-4 ${airplaneMode ? 'text-amber-400' : 'text-slate-500'}`} />
                <div>
                  <div className="text-xs font-semibold text-slate-200">Airplane Mode</div>
                  <div className="text-[11px] text-slate-500">Hardware radios shut off</div>
                </div>
              </div>
              <button
                onClick={() => setAirplaneMode(!airplaneMode)}
                className={`px-3 py-1 rounded-md text-xs font-semibold transition-colors ${
                  airplaneMode ? 'bg-amber-500/20 text-amber-300 border border-amber-500/30' : 'bg-slate-800 text-slate-400'
                }`}
              >
                {airplaneMode ? 'Engaged' : 'Off'}
              </button>
            </div>

            {/* Fake-Online Engine Toggle */}
            <div className="p-4 rounded-xl bg-slate-950 border border-slate-800 flex items-center justify-between">
              <div className="flex items-center gap-2.5">
                <Wifi className={`w-4 h-4 ${fakeOnlineEnabled ? 'text-emerald-400' : 'text-slate-500'}`} />
                <div>
                  <div className="text-xs font-semibold text-slate-200">SERF Fake-Online (F01)</div>
                  <div className="text-[11px] text-slate-500">Synthetic probe responder</div>
                </div>
              </div>
              <button
                onClick={() => setFakeOnlineEnabled(!fakeOnlineEnabled)}
                className={`px-3 py-1 rounded-md text-xs font-semibold transition-colors ${
                  fakeOnlineEnabled ? 'bg-emerald-500/20 text-emerald-300 border border-emerald-500/30' : 'bg-slate-800 text-slate-400'
                }`}
              >
                {fakeOnlineEnabled ? 'Active' : 'Disabled'}
              </button>
            </div>

          </div>

          {/* Launch Action */}
          <div className="pt-2 flex justify-end">
            <button
              onClick={handleRunTest}
              disabled={isTesting}
              className="px-5 py-2.5 rounded-xl bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-bold text-xs flex items-center gap-2 shadow-lg shadow-emerald-500/20 active:scale-98 transition-all"
            >
              <Play className="w-4 h-4" />
              <span>{isTesting ? 'Simulating Launch...' : `Launch ${selectedApp} Offline`}</span>
            </button>
          </div>

          {/* Results Panel */}
          {testResult && (
            <div className={`p-4 rounded-xl border animate-in fade-in duration-200 space-y-3 ${
              testResult.success
                ? 'bg-emerald-950/20 border-emerald-500/30 text-emerald-200'
                : 'bg-rose-950/20 border-rose-500/30 text-rose-200'
            }`}>
              <div className="flex items-center gap-2 font-bold text-sm">
                {testResult.success ? (
                  <>
                    <CheckCircle2 className="w-5 h-5 text-emerald-400" />
                    <span>v0.1 Done Test PASSED: Fake-Online Loopback Succeeded!</span>
                  </>
                ) : (
                  <>
                    <AlertOctagon className="w-5 h-5 text-rose-400" />
                    <span>Launch Blocked by Offline Gate (Expected without F01)</span>
                  </>
                )}
              </div>

              <div className="space-y-1.5 font-mono text-xs">
                <div>
                  <span className="opacity-60">1. Captive Probe (/generate_204): </span>
                  <span className="font-semibold">{testResult.probeStatus}</span>
                </div>
                <div>
                  <span className="opacity-60">2. Ad/Telemetry Phone-Home: </span>
                  <span className="font-semibold">{testResult.telemetryStatus}</span>
                </div>
              </div>

              <div className="p-3 bg-slate-950/60 rounded-lg text-xs leading-relaxed border border-slate-800">
                {testResult.uiOutcome}
              </div>
            </div>
          )}

        </div>
      </div>
    </div>
  );
};
