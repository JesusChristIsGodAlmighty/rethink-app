import React, { useState } from 'react';
import { 
  X, 
  History, 
  AlertTriangle, 
  CheckCircle2, 
  RotateCcw, 
  Archive, 
  ArrowRight,
  ShieldAlert
} from 'lucide-react';

interface VersionDiff {
  appName: string;
  packageName: string;
  oldVersion: string;
  newVersion: string;
  addedTrackers: string[];
  removedTrackers: string[];
  dataPayloadChange: string;
  verdict: 'risky' | 'clean';
}

const SAMPLE_DIFFS: VersionDiff[] = [
  {
    appName: 'Weather Live Forecast',
    packageName: 'com.weather.live',
    oldVersion: 'v3.1.2',
    newVersion: 'v3.2.0 (Latest)',
    addedTrackers: [
      'graph.facebook.com/v18.0 (Facebook Pixel)',
      'inmobi.com SDK (Location-based bidding)',
      'criteo.com retargeting beacon'
    ],
    removedTrackers: [],
    dataPayloadChange: '+340 KB background upload per launch',
    verdict: 'risky'
  },
  {
    appName: 'Subway Surfers',
    packageName: 'com.kiloo.subwaysurf',
    oldVersion: 'v2.40.1',
    newVersion: 'v2.41.0 (Latest)',
    addedTrackers: [],
    removedTrackers: ['flurry.com telemetry'],
    dataPayloadChange: '-12 KB per session',
    verdict: 'clean'
  }
];

interface UpdateTimeMachineModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const UpdateTimeMachineModal: React.FC<UpdateTimeMachineModalProps> = ({
  isOpen,
  onClose
}) => {
  if (!isOpen) return null;

  const [selectedDiff, setSelectedDiff] = useState<VersionDiff>(SAMPLE_DIFFS[0]);
  const [rollbackStatus, setRollbackStatus] = useState<string | null>(null);

  const handleRollback = () => {
    setRollbackStatus(`F235 Version Rollback: Restored ${selectedDiff.oldVersion} verified clean network profile for ${selectedDiff.appName}. Blocked new Facebook and InMobi trackers.`);
    setTimeout(() => setRollbackStatus(null), 4000);
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-4xl h-[85vh] flex flex-col shadow-2xl overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="p-5 border-b border-slate-800 flex items-center justify-between gap-4">
          <div className="flex items-center gap-3">
            <div className="p-2 rounded-lg bg-amber-500/10 border border-amber-500/30 text-amber-400">
              <History className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>App Update Time Machine</span>
                <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-amber-400 font-bold border border-slate-700">
                  F228 / F229 / F235
                </span>
              </h2>
              <p className="text-xs text-slate-400">
                Diffs network behavior across app updates to catch new surveillance SDKs sneaking into trusted apps.
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

        {/* Content Body */}
        <div className="flex-1 p-6 overflow-y-auto space-y-6 text-sm">
          
          <div className="bg-slate-950 p-4 rounded-xl border border-slate-800 text-xs text-slate-300 leading-relaxed">
            <span className="font-semibold text-slate-100">Why F228 exists:</span> An app you trust often works great until an update quietly adds invasive tracking SDKs or breaks offline functionality. The Time Machine compares network traffic before and after every update and alerts you.
          </div>

          {/* Diffs List */}
          <div className="space-y-3">
            <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-400">
              Recent App Updates Monitored
            </h4>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              {SAMPLE_DIFFS.map((diff) => {
                const isSelected = selectedDiff.packageName === diff.packageName;
                return (
                  <div
                    key={diff.packageName}
                    onClick={() => setSelectedDiff(diff)}
                    className={`p-4 rounded-xl border cursor-pointer transition-all flex flex-col justify-between ${
                      isSelected
                        ? 'bg-amber-500/10 border-amber-500/40 ring-1 ring-amber-500/20'
                        : 'bg-slate-950 border-slate-800 hover:border-slate-700'
                    }`}
                  >
                    <div>
                      <div className="flex items-center justify-between text-xs mb-1">
                        <span className="font-bold text-slate-100">{diff.appName}</span>
                        {diff.verdict === 'risky' ? (
                          <span className="text-[10px] px-2 py-0.5 rounded-full bg-rose-500/20 text-rose-300 font-bold border border-rose-500/30">
                            +3 New Trackers
                          </span>
                        ) : (
                          <span className="text-[10px] px-2 py-0.5 rounded-full bg-emerald-500/20 text-emerald-300 font-bold border border-emerald-500/30">
                            Clean Diff
                          </span>
                        )}
                      </div>
                      <div className="font-mono text-[11px] text-slate-500 mb-2 truncate">
                        {diff.packageName}
                      </div>
                      <div className="flex items-center gap-2 text-xs font-mono text-slate-300">
                        <span>{diff.oldVersion}</span>
                        <ArrowRight className="w-3.5 h-3.5 text-slate-500" />
                        <span className="text-amber-400 font-bold">{diff.newVersion}</span>
                      </div>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>

          {/* Detailed Diff View */}
          <div className="p-5 bg-slate-950 rounded-xl border border-slate-800 space-y-4">
            <div className="flex items-center justify-between">
              <div>
                <h4 className="font-bold text-sm text-slate-200">
                  Update Analysis: {selectedDiff.appName} ({selectedDiff.oldVersion} &rarr; {selectedDiff.newVersion})
                </h4>
                <div className="text-xs text-slate-400 font-mono mt-0.5">
                  Payload Delta: {selectedDiff.dataPayloadChange}
                </div>
              </div>

              {selectedDiff.verdict === 'risky' && (
                <button
                  onClick={handleRollback}
                  className="px-3.5 py-1.5 rounded-lg bg-amber-500 text-slate-950 font-bold text-xs hover:bg-amber-400 flex items-center gap-1.5 shadow-sm transition-all"
                >
                  <RotateCcw className="w-3.5 h-3.5" />
                  <span>Rollback Network Profile (F235)</span>
                </button>
              )}
            </div>

            {selectedDiff.addedTrackers.length > 0 ? (
              <div className="space-y-2">
                <div className="text-xs font-semibold text-rose-400 uppercase tracking-wider flex items-center gap-1.5">
                  <ShieldAlert className="w-4 h-4" />
                  <span>Newly Injected Trackers in {selectedDiff.newVersion}:</span>
                </div>
                <div className="space-y-1.5">
                  {selectedDiff.addedTrackers.map((t, idx) => (
                    <div key={idx} className="p-2.5 rounded-lg bg-rose-950/20 border border-rose-500/30 text-rose-200 text-xs font-mono">
                      + {t}
                    </div>
                  ))}
                </div>
              </div>
            ) : (
              <div className="p-3 bg-emerald-950/20 border border-emerald-500/30 text-emerald-200 text-xs font-mono rounded-lg flex items-center gap-2">
                <CheckCircle2 className="w-4 h-4 text-emerald-400" />
                <span>Zero new trackers added in this update. Safe to keep running!</span>
              </div>
            )}

            {rollbackStatus && (
              <div className="p-3 bg-emerald-950/40 border border-emerald-500/30 text-emerald-200 text-xs font-mono rounded-lg animate-in fade-in">
                {rollbackStatus}
              </div>
            )}
          </div>

        </div>
      </div>
    </div>
  );
};
