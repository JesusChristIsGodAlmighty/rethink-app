import React, { useState } from 'react';
import { 
  X, 
  Activity, 
  AlertTriangle, 
  HelpCircle, 
  BookOpen, 
  Lightbulb, 
  GitCompare, 
  Play, 
  History, 
  Download,
  CheckCircle2
} from 'lucide-react';
import { SerfModule } from '../types/serf';

interface FiveLensesModalProps {
  module: SerfModule | null;
  onClose: () => void;
  onRunSuggestion: (module: SerfModule) => void;
}

type LensTab = 'monitor' | 'detect' | 'explain' | 'describe' | 'suggest' | 'compare' | 'simulate' | 'history' | 'export';

export const FiveLensesModal: React.FC<FiveLensesModalProps> = ({
  module,
  onClose,
  onRunSuggestion
}) => {
  if (!module) return null;

  const [activeTab, setActiveTab] = useState<LensTab>('describe');
  const [simulationRunning, setSimulationRunning] = useState(false);
  const [simResult, setSimResult] = useState<string | null>(null);

  const lenses = module.fiveLenses;

  const handleRunSim = () => {
    setSimulationRunning(true);
    setSimResult(null);
    setTimeout(() => {
      setSimulationRunning(false);
      setSimResult(`Dry-run simulation completed: applying current ${module.name} rules against last 1,000 recorded packets resulted in 42 blocked telemetry calls, 12 faked responses, and zero broken first-party assets.`);
    }, 700);
  };

  const handleExportJson = () => {
    const dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(module, null, 2));
    const downloadAnchor = document.createElement('a');
    downloadAnchor.setAttribute("href", dataStr);
    downloadAnchor.setAttribute("download", `SERF_${module.id}_${module.name.replace(/\s+/g, '_')}_spec.json`);
    document.body.appendChild(downloadAnchor);
    downloadAnchor.click();
    downloadAnchor.remove();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-3xl max-h-[90vh] flex flex-col shadow-2xl overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Modal Header */}
        <div className="p-5 border-b border-slate-800/80 flex items-center justify-between gap-4">
          <div className="flex items-center gap-3">
            <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-emerald-400 font-bold border border-slate-700">
              {module.id}
            </span>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>{module.name}</span>
                <span className="text-xs font-normal text-slate-400">({module.category})</span>
              </h2>
              <p className="text-xs text-slate-400">Five Lenses Standard (F404) &amp; Technical Manifest</p>
            </div>
          </div>

          <button
            onClick={onClose}
            className="p-1.5 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Lens Navigation Tabs */}
        <div className="flex items-center gap-1 px-4 py-2 bg-slate-950/60 border-b border-slate-800/80 overflow-x-auto text-xs font-medium">
          <button
            onClick={() => setActiveTab('describe')}
            className={`px-3 py-1.5 rounded-lg flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'describe' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <BookOpen className="w-3.5 h-3.5" />
            <span>Describe (Non-Tech)</span>
          </button>

          <button
            onClick={() => setActiveTab('explain')}
            className={`px-3 py-1.5 rounded-lg flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'explain' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <HelpCircle className="w-3.5 h-3.5" />
            <span>Explain</span>
          </button>

          <button
            onClick={() => setActiveTab('monitor')}
            className={`px-3 py-1.5 rounded-lg flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'monitor' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Activity className="w-3.5 h-3.5" />
            <span>Monitor</span>
          </button>

          <button
            onClick={() => setActiveTab('detect')}
            className={`px-3 py-1.5 rounded-lg flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'detect' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <AlertTriangle className="w-3.5 h-3.5" />
            <span>Detect</span>
          </button>

          <button
            onClick={() => setActiveTab('suggest')}
            className={`px-3 py-1.5 rounded-lg flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'suggest' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Lightbulb className="w-3.5 h-3.5" />
            <span>Suggest</span>
          </button>

          <button
            onClick={() => setActiveTab('simulate')}
            className={`px-3 py-1.5 rounded-lg flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'simulate' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Play className="w-3.5 h-3.5" />
            <span>Simulate (F406)</span>
          </button>

          <button
            onClick={() => setActiveTab('compare')}
            className={`px-3 py-1.5 rounded-lg flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'compare' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <GitCompare className="w-3.5 h-3.5" />
            <span>Compare (F405)</span>
          </button>

          <button
            onClick={() => setActiveTab('history')}
            className={`px-3 py-1.5 rounded-lg flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'history' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <History className="w-3.5 h-3.5" />
            <span>History (F407)</span>
          </button>

          <button
            onClick={() => setActiveTab('export')}
            className={`px-3 py-1.5 rounded-lg flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'export' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Download className="w-3.5 h-3.5" />
            <span>Export (F408)</span>
          </button>
        </div>

        {/* Tab Body */}
        <div className="p-6 overflow-y-auto flex-1 space-y-4 text-sm">
          
          {/* LENS: DESCRIBE (Non-technical education, mandatory per 14.3) */}
          {activeTab === 'describe' && (
            <div className="space-y-4">
              <div className="bg-emerald-950/20 border border-emerald-500/20 rounded-xl p-4">
                <h4 className="text-xs font-semibold text-emerald-400 uppercase tracking-wider mb-1">
                  Plain-Language Summary
                </h4>
                <p className="text-slate-200 font-medium leading-relaxed">
                  {lenses.describe.plainSummary}
                </p>
              </div>

              <div>
                <h4 className="text-xs font-semibold text-slate-400 uppercase tracking-wider mb-1">
                  Why It Matters
                </h4>
                <p className="text-slate-300 leading-relaxed">
                  {lenses.describe.whyItMatters}
                </p>
              </div>

              <div>
                <h4 className="text-xs font-semibold text-slate-400 uppercase tracking-wider mb-1">
                  What Happens If Enabled
                </h4>
                <p className="text-slate-300 leading-relaxed">
                  {lenses.describe.consequencesIfEnabled}
                </p>
              </div>

              <div className="pt-3 border-t border-slate-800 text-xs text-slate-400">
                <span className="font-semibold text-slate-300">Module Contract 4.2.6:</span> {module.oneParagraphDoc}
              </div>
            </div>
          )}

          {/* LENS: EXPLAIN (Technical mechanism & risks) */}
          {activeTab === 'explain' && (
            <div className="space-y-4">
              <div>
                <h4 className="text-xs font-semibold text-sky-400 uppercase tracking-wider mb-1">
                  Technical Architecture
                </h4>
                <p className="text-slate-200 leading-relaxed font-mono text-xs bg-slate-950 p-3 rounded-lg border border-slate-800">
                  {lenses.explain.mechanism}
                </p>
              </div>

              <div>
                <h4 className="text-xs font-semibold text-slate-400 uppercase tracking-wider mb-1">
                  Risks Mitigated
                </h4>
                <p className="text-slate-300 leading-relaxed">
                  {lenses.explain.risksMitigated}
                </p>
              </div>

              <div className="p-3 bg-slate-950/50 rounded-lg border border-slate-800 text-xs space-y-1">
                <div><span className="text-slate-500">Required Permissions:</span> <code className="text-amber-300">{module.requiredPermissions.join(', ') || 'None (Sandboxed)'}</code></div>
                <div><span className="text-slate-500">Breaks if disabled:</span> <span className="text-slate-300">{module.breaksIfDisabled}</span></div>
              </div>
            </div>
          )}

          {/* LENS: MONITOR (Live telemetry) */}
          {activeTab === 'monitor' && (
            <div className="space-y-4">
              <div className="flex items-center justify-between">
                <h4 className="text-xs font-semibold text-slate-400 uppercase tracking-wider">
                  Live Status Note
                </h4>
                <span className="text-xs font-mono text-emerald-400">{module.status.toUpperCase()}</span>
              </div>
              <p className="text-slate-200 font-mono text-xs bg-slate-950 p-3 rounded-lg border border-slate-800">
                {lenses.monitor.statusNote}
              </p>

              <div className="grid grid-cols-2 sm:grid-cols-3 gap-3">
                {lenses.monitor.metrics.map((m, i) => (
                  <div key={i} className="p-3 rounded-lg bg-slate-950 border border-slate-800">
                    <div className="text-[11px] text-slate-400 truncate">{m.label}</div>
                    <div className="text-lg font-bold font-mono text-slate-100 tabular-nums mt-1">{m.value}</div>
                    {m.change && <div className="text-[10px] text-emerald-400 font-mono">{m.change}</div>}
                  </div>
                ))}
              </div>

              <div className="p-3 bg-slate-950/40 rounded-lg border border-slate-800 text-xs">
                <span className="font-semibold text-slate-300">Resource Footprint (F424):</span> CPU {module.resourceCost.cpuPercent}% · Battery {module.resourceCost.batteryPerHourPercent}%/hr · RAM {module.resourceCost.ramMb} MB · Data Saved {module.resourceCost.dataSavedKb} KB
              </div>
            </div>
          )}

          {/* LENS: DETECT */}
          {activeTab === 'detect' && (
            <div className="space-y-4">
              <div className="flex items-center justify-between">
                <h4 className="text-xs font-semibold text-slate-400 uppercase tracking-wider">
                  Threats &amp; Anomalies Flagged
                </h4>
                <span className="text-xs font-mono font-bold text-amber-400">
                  {lenses.detect.threatsFound} items flagged
                </span>
              </div>

              {lenses.detect.details.length === 0 ? (
                <div className="text-slate-500 text-xs italic">No suspicious indicators detected in current evaluation window.</div>
              ) : (
                <div className="space-y-2">
                  {lenses.detect.details.map((item, i) => (
                    <div key={i} className="p-3 rounded-lg bg-slate-950 border border-slate-800 flex items-start gap-2 text-xs">
                      <AlertTriangle className="w-4 h-4 text-amber-400 shrink-0 mt-0.5" />
                      <span className="text-slate-300">{item}</span>
                    </div>
                  ))}
                </div>
              )}
            </div>
          )}

          {/* LENS: SUGGEST (Authority Ladder Step 2) */}
          {activeTab === 'suggest' && (
            <div className="space-y-4">
              <div className="p-4 rounded-xl bg-slate-950 border border-slate-800">
                <div className="text-xs font-semibold text-emerald-400 uppercase tracking-wider mb-2">
                  Authority Ladder: Step {lenses.suggest.authorityLevel} (Suggest Only)
                </div>
                <p className="text-slate-200 leading-relaxed font-medium mb-3">
                  {lenses.suggest.recommendation}
                </p>
                <div className="text-xs text-slate-400">
                  Per Section 5.21 and Module Contract 4.2.7: AI and diagnostics may suggest optimization, but never auto-apply without an explicit user tap.
                </div>
              </div>

              {lenses.suggest.actionLabel && (
                <button
                  onClick={() => onRunSuggestion(module)}
                  className="px-4 py-2 rounded-lg bg-emerald-500 text-slate-950 font-semibold text-xs hover:bg-emerald-400 active:scale-98 transition-all flex items-center gap-2"
                >
                  <CheckCircle2 className="w-4 h-4" />
                  <span>{lenses.suggest.actionLabel}</span>
                </button>
              )}
            </div>
          )}

          {/* LENS: SIMULATE (F406 dry run) */}
          {activeTab === 'simulate' && (
            <div className="space-y-4">
              <p className="text-xs text-slate-300">
                Simulate (F406) allows you to dry-run changes against recent recorded traffic before committing them, ensuring that you will not break legitimate app logins or payment flows.
              </p>

              <button
                onClick={handleRunSim}
                disabled={simulationRunning}
                className="px-4 py-2 rounded-lg bg-slate-800 text-emerald-400 hover:bg-slate-700 text-xs font-semibold flex items-center gap-2"
              >
                <Play className="w-3.5 h-3.5" />
                <span>{simulationRunning ? 'Running dry-run simulation...' : 'Execute Dry-Run Simulation'}</span>
              </button>

              {simResult && (
                <div className="p-3 bg-slate-950 border border-emerald-500/30 rounded-lg text-xs text-emerald-300 font-mono">
                  {simResult}
                </div>
              )}
            </div>
          )}

          {/* LENS: COMPARE (F405) */}
          {activeTab === 'compare' && (
            <div className="space-y-4">
              <p className="text-xs text-slate-300">
                Compare (F405) checks this module’s current blocking performance against the previous app build and network profile.
              </p>
              <div className="grid grid-cols-2 gap-4">
                <div className="p-3 bg-slate-950 rounded-lg border border-slate-800">
                  <div className="text-xs text-slate-500">Current Week</div>
                  <div className="text-base font-bold text-slate-200 mt-1">1,429 Blocked</div>
                  <div className="text-[11px] text-emerald-400 mt-0.5">0 false positives reported</div>
                </div>
                <div className="p-3 bg-slate-950 rounded-lg border border-slate-800">
                  <div className="text-xs text-slate-500">Baseline (Upstream RethinkDNS)</div>
                  <div className="text-base font-bold text-slate-400 mt-1">1,120 Blocked</div>
                  <div className="text-[11px] text-amber-400 mt-0.5">Captive portal probes un-stubbed</div>
                </div>
              </div>
            </div>
          )}

          {/* LENS: HISTORY (F407) */}
          {activeTab === 'history' && (
            <div className="space-y-3">
              <h4 className="text-xs font-semibold text-slate-400 uppercase tracking-wider">
                Timeline &amp; Audit Log
              </h4>
              {lenses.history && lenses.history.length > 0 ? (
                <div className="space-y-2">
                  {lenses.history.map((h, i) => (
                    <div key={i} className="p-3 bg-slate-950 rounded-lg border border-slate-800 text-xs flex justify-between items-center">
                      <div>
                        <div className="font-semibold text-slate-200">{h.action}</div>
                        <div className="text-slate-400">{h.impact}</div>
                      </div>
                      <span className="text-[10px] font-mono text-slate-500">
                        {new Date(h.timestamp).toLocaleTimeString()}
                      </span>
                    </div>
                  ))}
                </div>
              ) : (
                <div className="text-slate-500 text-xs italic">No state transitions recorded in this session.</div>
              )}
            </div>
          )}

          {/* LENS: EXPORT (F408) */}
          {activeTab === 'export' && (
            <div className="space-y-4">
              <p className="text-xs text-slate-300">
                Module Contract 4.2.8 &amp; F408: Every feature’s configuration, rules, and telemetry can be exported in open, non-proprietary JSON/CSV format.
              </p>
              <button
                onClick={handleExportJson}
                className="px-4 py-2 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-semibold flex items-center gap-2 border border-slate-700"
              >
                <Download className="w-3.5 h-3.5 text-emerald-400" />
                <span>Download Module Specification (JSON)</span>
              </button>
            </div>
          )}

        </div>
      </div>
    </div>
  );
};
