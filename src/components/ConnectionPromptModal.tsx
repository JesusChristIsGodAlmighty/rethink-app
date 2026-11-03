import React, { useState } from 'react';
import { 
  ShieldAlert, 
  Check, 
  Clock, 
  Ban, 
  Sparkles, 
  Globe, 
  Cpu, 
  ExternalLink,
  X
} from 'lucide-react';
import { NetworkPacket } from '../types/serf';

interface ConnectionPromptModalProps {
  packet: NetworkPacket | null;
  isOpen: boolean;
  onClose: () => void;
  onResolve: (action: 'allow_always' | 'allow_once' | 'block' | 'fake_synthesis', domain: string) => void;
}

export const ConnectionPromptModal: React.FC<ConnectionPromptModalProps> = ({
  packet,
  isOpen,
  onClose,
  onResolve
}) => {
  if (!isOpen || !packet) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/75 backdrop-blur-sm animate-in fade-in duration-150">
      <div className="w-full max-w-lg bg-zinc-950 border-2 border-amber-500/40 rounded-2xl shadow-2xl overflow-hidden text-zinc-100 animate-in zoom-in-95 duration-150">
        {/* Header with Risk Banner */}
        <div className="bg-gradient-to-r from-amber-950/80 to-zinc-900 border-b border-amber-500/30 p-4">
          <div className="flex items-center justify-between">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-amber-500/20 border border-amber-500/40 flex items-center justify-center text-amber-400">
                <ShieldAlert className="w-5 h-5 animate-pulse" />
              </div>
              <div>
                <div className="flex items-center gap-2">
                  <h3 className="font-semibold text-zinc-100 text-sm">Real-Time Connection Intercept (F12)</h3>
                  <span className="text-[10px] font-mono uppercase bg-amber-500/20 text-amber-300 px-1.5 py-0.5 rounded border border-amber-500/30">
                    Decision Required
                  </span>
                </div>
                <p className="text-xs text-zinc-400">App initiated an unclassified outbound socket connection</p>
              </div>
            </div>
            <button 
              onClick={onClose}
              className="p-1.5 text-zinc-400 hover:text-zinc-200 hover:bg-zinc-800 rounded-lg transition-colors"
            >
              <X className="w-4 h-4" />
            </button>
          </div>
        </div>

        {/* Intercepted Details */}
        <div className="p-5 space-y-4">
          <div className="bg-zinc-900/90 border border-zinc-800 rounded-xl p-3.5 space-y-2.5 text-xs">
            <div className="flex items-center justify-between">
              <span className="text-zinc-400">Requesting App:</span>
              <span className="font-semibold text-zinc-200 flex items-center gap-1.5">
                <Cpu className="w-3.5 h-3.5 text-cyan-400" />
                {packet.app}
              </span>
            </div>
            <div className="flex items-center justify-between">
              <span className="text-zinc-400">Target Endpoint:</span>
              <span className="font-mono font-medium text-amber-300 break-all">{packet.domain}</span>
            </div>
            <div className="flex items-center justify-between">
              <span className="text-zinc-400">Destination IP & Port:</span>
              <span className="font-mono text-zinc-300">{packet.destinationIp}:443 (HTTPS)</span>
            </div>
            <div className="flex items-center justify-between">
              <span className="text-zinc-400">Granular Attribution (F15):</span>
              <span className="font-mono text-[11px] text-zinc-400">
                com.analytics.telemetry.v2 (Embedded SDK)
              </span>
            </div>
            <div className="flex items-center justify-between pt-1 border-t border-zinc-800">
              <span className="text-zinc-400">Traffic Category:</span>
              <span className="text-rose-400 font-medium flex items-center gap-1">
                Ad-Tech & Telemetry Tracking
              </span>
            </div>
          </div>

          <p className="text-xs text-zinc-400 leading-relaxed">
            Choose how SERF should govern this connection. You can block it completely, allow it temporarily, or synthesize a fake response so the app doesn't crash or stall.
          </p>

          {/* Action Grid */}
          <div className="grid grid-cols-2 gap-2.5 pt-1">
            <button
              onClick={() => onResolve('fake_synthesis', packet.domain)}
              className="flex items-start gap-2.5 p-3 rounded-xl bg-violet-950/40 border border-violet-500/40 hover:bg-violet-900/40 hover:border-violet-400 transition-all text-left group"
            >
              <div className="p-2 rounded-lg bg-violet-500/20 text-violet-400 group-hover:scale-105 transition-transform">
                <Sparkles className="w-4 h-4" />
              </div>
              <div>
                <div className="text-xs font-semibold text-violet-200">Fake-Online (F03)</div>
                <div className="text-[10px] text-violet-300/70">Return synthetic 200 OK without transmitting any data</div>
              </div>
            </button>

            <button
              onClick={() => onResolve('block', packet.domain)}
              className="flex items-start gap-2.5 p-3 rounded-xl bg-rose-950/40 border border-rose-500/40 hover:bg-rose-900/40 hover:border-rose-400 transition-all text-left group"
            >
              <div className="p-2 rounded-lg bg-rose-500/20 text-rose-400 group-hover:scale-105 transition-transform">
                <Ban className="w-4 h-4" />
              </div>
              <div>
                <div className="text-xs font-semibold text-rose-200">Drop & Block</div>
                <div className="text-[10px] text-rose-300/70">Instantly drop socket and remember rule for app</div>
              </div>
            </button>

            <button
              onClick={() => onResolve('allow_once', packet.domain)}
              className="flex items-start gap-2.5 p-3 rounded-xl bg-zinc-900 border border-zinc-700 hover:border-zinc-500 transition-all text-left group"
            >
              <div className="p-2 rounded-lg bg-zinc-800 text-zinc-300 group-hover:scale-105 transition-transform">
                <Clock className="w-4 h-4" />
              </div>
              <div>
                <div className="text-xs font-semibold text-zinc-200">Allow 5 Mins</div>
                <div className="text-[10px] text-zinc-400">Temporary pinhole, reverts automatically</div>
              </div>
            </button>

            <button
              onClick={() => onResolve('allow_always', packet.domain)}
              className="flex items-start gap-2.5 p-3 rounded-xl bg-emerald-950/40 border border-emerald-500/40 hover:bg-emerald-900/40 hover:border-emerald-400 transition-all text-left group"
            >
              <div className="p-2 rounded-lg bg-emerald-500/20 text-emerald-400 group-hover:scale-105 transition-transform">
                <Check className="w-4 h-4" />
              </div>
              <div>
                <div className="text-xs font-semibold text-emerald-200">Always Allow</div>
                <div className="text-[10px] text-emerald-300/70">Add domain to trusted whitelist permanently</div>
              </div>
            </button>
          </div>
        </div>

        {/* Footer */}
        <div className="bg-zinc-900/50 border-t border-zinc-800 px-5 py-3 flex items-center justify-between text-[11px] text-zinc-500">
          <span>Authority Ladder: Step 3 (One-Tap Action)</span>
          <span className="font-mono">SERF Tunnel Socket ID: #4819</span>
        </div>
      </div>
    </div>
  );
};
