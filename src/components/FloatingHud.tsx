import React, { useState } from 'react';
import { 
  Shield, 
  Activity, 
  ArrowDown, 
  ArrowUp, 
  EyeOff, 
  Radio, 
  AlertOctagon, 
  ChevronRight, 
  ChevronLeft,
  X,
  Sparkles
} from 'lucide-react';

interface FloatingHudProps {
  isVisible: boolean;
  onToggle: () => void;
  socketCount: number;
  blockedCount: number;
  stealthMode: boolean;
  onToggleStealth: () => void;
  onEmergencyPanic: () => void;
  onOpenFirewallDepth: () => void;
}

export const FloatingHud: React.FC<FloatingHudProps> = ({
  isVisible,
  onToggle,
  socketCount,
  blockedCount,
  stealthMode,
  onToggleStealth,
  onEmergencyPanic,
  onOpenFirewallDepth
}) => {
  const [isExpanded, setIsExpanded] = useState(false);

  if (!isVisible) return null;

  return (
    <div className="fixed bottom-6 right-6 z-40 flex items-end gap-2 pointer-events-auto">
      {/* Expanded Pill HUD */}
      {isExpanded ? (
        <div className="bg-zinc-950/95 border border-cyan-500/40 rounded-2xl p-3.5 shadow-2xl backdrop-blur-md text-zinc-100 w-72 space-y-3 animate-in slide-in-from-right-4 duration-200">
          <div className="flex items-center justify-between border-b border-zinc-800 pb-2">
            <div className="flex items-center gap-2">
              <div className="w-2 h-2 rounded-full bg-emerald-400 animate-ping" />
              <span className="text-xs font-semibold text-zinc-200">SERF Floating HUD (F19)</span>
            </div>
            <div className="flex items-center gap-1">
              <button
                onClick={() => setIsExpanded(false)}
                className="p-1 text-zinc-400 hover:text-zinc-200 rounded hover:bg-zinc-800"
                title="Collapse"
              >
                <ChevronRight className="w-3.5 h-3.5" />
              </button>
              <button
                onClick={onToggle}
                className="p-1 text-zinc-400 hover:text-rose-400 rounded hover:bg-zinc-800"
                title="Close HUD"
              >
                <X className="w-3.5 h-3.5" />
              </button>
            </div>
          </div>

          {/* Real-time stats */}
          <div className="grid grid-cols-2 gap-2 text-xs">
            <div className="bg-zinc-900/80 p-2 rounded-lg border border-zinc-800/80">
              <div className="text-[10px] text-zinc-400">Active Sockets</div>
              <div className="text-sm font-semibold font-mono text-cyan-400 flex items-center gap-1 mt-0.5">
                <Activity className="w-3.5 h-3.5" />
                {socketCount} open
              </div>
            </div>

            <div className="bg-zinc-900/80 p-2 rounded-lg border border-zinc-800/80">
              <div className="text-[10px] text-zinc-400">Blocked Trackers</div>
              <div className="text-sm font-semibold font-mono text-rose-400 flex items-center gap-1 mt-0.5">
                <Shield className="w-3.5 h-3.5" />
                {blockedCount}
              </div>
            </div>
          </div>

          {/* Throughput */}
          <div className="flex items-center justify-between text-[11px] font-mono px-2 py-1.5 bg-zinc-900/50 rounded-lg text-zinc-400 border border-zinc-800/50">
            <span className="flex items-center gap-1 text-emerald-400">
              <ArrowDown className="w-3 h-3" /> 28.4 KB/s
            </span>
            <span className="flex items-center gap-1 text-cyan-400">
              <ArrowUp className="w-3 h-3" /> 2.1 KB/s
            </span>
          </div>

          {/* Quick Controls */}
          <div className="space-y-1.5 pt-1">
            <button
              onClick={onToggleStealth}
              className={`w-full flex items-center justify-between px-2.5 py-1.5 rounded-lg text-xs transition-colors border ${
                stealthMode 
                  ? 'bg-violet-950/60 border-violet-500/40 text-violet-300' 
                  : 'bg-zinc-900 border-zinc-800 text-zinc-400 hover:text-zinc-200'
              }`}
            >
              <span className="flex items-center gap-1.5">
                <EyeOff className="w-3.5 h-3.5" />
                Stealth Mode (F13)
              </span>
              <span className="text-[10px] font-mono uppercase font-bold">
                {stealthMode ? 'ACTIVE' : 'OFF'}
              </span>
            </button>

            <button
              onClick={onOpenFirewallDepth}
              className="w-full flex items-center justify-center gap-1.5 px-2.5 py-1.5 rounded-lg text-xs bg-zinc-900 hover:bg-zinc-800 text-zinc-300 border border-zinc-800 transition-colors"
            >
              <Sparkles className="w-3.5 h-3.5 text-amber-400" />
              Open Firewall Depth Suite
            </button>

            <button
              onClick={onEmergencyPanic}
              className="w-full flex items-center justify-center gap-1.5 px-2.5 py-1.5 rounded-lg text-xs font-semibold bg-rose-950/70 hover:bg-rose-900 border border-rose-500/50 text-rose-200 transition-colors"
            >
              <AlertOctagon className="w-3.5 h-3.5" />
              Panic: Drop All Sockets (F20)
            </button>
          </div>
        </div>
      ) : (
        /* Floating Mini Bubble */
        <button
          onClick={() => setIsExpanded(true)}
          className="group flex items-center gap-2 px-3 py-2 bg-zinc-950/90 hover:bg-zinc-900 border border-cyan-500/50 rounded-full shadow-xl backdrop-blur-md text-zinc-200 transition-all hover:scale-105"
          title="Open SERF Firewall HUD"
        >
          <div className="w-2.5 h-2.5 rounded-full bg-emerald-400 animate-pulse" />
          <Shield className="w-4 h-4 text-cyan-400" />
          <span className="text-xs font-mono font-medium text-cyan-300">{socketCount} conn</span>
          <span className="text-xs font-mono font-medium text-rose-400">| {blockedCount} blk</span>
          <ChevronLeft className="w-3.5 h-3.5 text-zinc-400 group-hover:text-zinc-200" />
        </button>
      )}
    </div>
  );
};
