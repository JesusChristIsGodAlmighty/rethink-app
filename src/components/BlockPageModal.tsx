import React, { useState } from 'react';
import { 
  X, 
  ShieldAlert, 
  Clock, 
  Check, 
  AlertTriangle, 
  ArrowLeft, 
  Unlock, 
  ExternalLink,
  Info
} from 'lucide-react';

interface BlockPageModalProps {
  isOpen: boolean;
  onClose: () => void;
  blockedDomain?: string;
  blockedApp?: string;
  ruleMatched?: string;
  onUnblockDomain: (domain: string, durationMinutes: number) => void;
}

export const BlockPageModal: React.FC<BlockPageModalProps> = ({
  isOpen,
  onClose,
  blockedDomain = 'telemetry.applovin.com',
  blockedApp = 'Weather Live',
  ruleMatched = 'OISD Full / EasyPrivacy (Rule #18420)',
  onUnblockDomain
}) => {
  if (!isOpen) return null;

  const [unblockedFeedback, setUnblockedFeedback] = useState<string | null>(null);

  const handleUnblock = (minutes: number) => {
    onUnblockDomain(blockedDomain, minutes);
    if (minutes === 0) {
      setUnblockedFeedback(`Domain ${blockedDomain} has been added to permanent whitelist.`);
    } else {
      setUnblockedFeedback(`Domain ${blockedDomain} unblocked for ${minutes} minutes. Protection will re-engage automatically.`);
    }
    setTimeout(() => {
      setUnblockedFeedback(null);
      onClose();
    }, 2000);
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-2xl flex flex-col shadow-2xl overflow-hidden text-sm"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header Bar */}
        <div className="p-4 bg-slate-950 border-b border-slate-800 flex items-center justify-between">
          <div className="flex items-center gap-2 text-xs font-mono text-slate-400">
            <span className="w-2.5 h-2.5 rounded-full bg-rose-500" />
            <span>SERF Local Intercept Engine · F191 Block Page</span>
          </div>

          <button
            onClick={onClose}
            className="p-1 rounded text-slate-400 hover:text-white transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Realistic Block Page Body */}
        <div className="p-8 text-center space-y-6">
          <div className="w-16 h-16 rounded-2xl bg-rose-500/10 border border-rose-500/30 text-rose-400 flex items-center justify-center mx-auto shadow-lg shadow-rose-500/10">
            <ShieldAlert className="w-8 h-8" />
          </div>

          <div className="space-y-2">
            <h2 className="text-2xl font-black text-slate-100 tracking-tight">
              Connection Blocked by SERF
            </h2>
            <p className="text-xs text-slate-400 max-w-md mx-auto">
              Your device prevented an outgoing request to an advertising, tracking, or telemetry endpoint.
            </p>
          </div>

          {/* Details Card */}
          <div className="bg-slate-950 p-5 rounded-xl border border-slate-800 text-left space-y-2.5 max-w-lg mx-auto text-xs">
            <div className="flex justify-between items-center">
              <span className="text-slate-500 font-medium">Blocked Host:</span>
              <span className="font-mono font-bold text-rose-300">{blockedDomain}</span>
            </div>
            <div className="flex justify-between items-center">
              <span className="text-slate-500 font-medium">Originating App:</span>
              <span className="font-semibold text-slate-200">{blockedApp}</span>
            </div>
            <div className="flex justify-between items-center">
              <span className="text-slate-500 font-medium">Rulepack List:</span>
              <span className="font-mono text-slate-400">{ruleMatched}</span>
            </div>
            <div className="pt-2 border-t border-slate-800/80 flex justify-between items-center text-[11px] text-slate-400">
              <span>Risk Category: Profiling / Tracker</span>
              <span className="text-emerald-400 font-mono">0 bytes leaked</span>
            </div>
          </div>

          {/* Unblock Action Options (F192 One-Tap Unblock) */}
          <div className="pt-2 space-y-3 max-w-lg mx-auto">
            <div className="text-xs font-semibold uppercase tracking-wider text-slate-400">
              One-Tap Unblock (F192)
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-3 gap-2">
              <button
                onClick={() => handleUnblock(10)}
                className="px-3 py-2 rounded-xl bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-semibold flex items-center justify-center gap-1.5 transition-all border border-slate-700"
              >
                <Clock className="w-3.5 h-3.5 text-amber-400" />
                <span>10 Minutes</span>
              </button>

              <button
                onClick={() => handleUnblock(60)}
                className="px-3 py-2 rounded-xl bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-semibold flex items-center justify-center gap-1.5 transition-all border border-slate-700"
              >
                <Clock className="w-3.5 h-3.5 text-sky-400" />
                <span>1 Hour</span>
              </button>

              <button
                onClick={() => handleUnblock(0)}
                className="px-3 py-2 rounded-xl bg-emerald-500/10 hover:bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 text-xs font-semibold flex items-center justify-center gap-1.5 transition-all"
              >
                <Unlock className="w-3.5 h-3.5 text-emerald-400" />
                <span>Always Whitelist</span>
              </button>
            </div>

            {unblockedFeedback && (
              <div className="p-3 bg-emerald-950/40 border border-emerald-500/30 text-emerald-300 rounded-lg text-xs font-mono animate-in fade-in">
                {unblockedFeedback}
              </div>
            )}
          </div>

          <div className="text-[11px] text-slate-500 pt-4 border-t border-slate-800/60 flex items-center justify-center gap-2">
            <Info className="w-3.5 h-3.5 text-slate-600" />
            <span>F191 block pages run locally on your phone; no telemetry is sent to any external server.</span>
          </div>

        </div>
      </div>
    </div>
  );
};
