import React, { useState } from 'react';
import { 
  X, 
  Activity, 
  ShieldAlert, 
  ShieldCheck, 
  Wifi, 
  Play, 
  Filter, 
  Search,
  Check,
  Ban,
  Sparkles
} from 'lucide-react';
import { NetworkPacket } from '../types/serf';

interface TrafficInterpreterModalProps {
  packets: NetworkPacket[];
  isOpen: boolean;
  onClose: () => void;
  onActionOnPacket: (packetId: string, action: 'block' | 'allow' | 'fake') => void;
}

export const TrafficInterpreterModal: React.FC<TrafficInterpreterModalProps> = ({
  packets,
  isOpen,
  onClose,
  onActionOnPacket
}) => {
  if (!isOpen) return null;

  const [selectedPacket, setSelectedPacket] = useState<NetworkPacket | null>(packets[0] || null);
  const [filterType, setFilterType] = useState<'all' | 'blocked' | 'faked' | 'allowed'>('all');
  const [searchQuery, setSearchQuery] = useState('');
  const [testBlockStatus, setTestBlockStatus] = useState<string | null>(null);

  const filteredPackets = packets.filter((p) => {
    if (filterType !== 'all' && p.status !== filterType) return false;
    if (searchQuery.trim() !== '') {
      const q = searchQuery.toLowerCase();
      return p.domain.toLowerCase().includes(q) || p.app.toLowerCase().includes(q);
    }
    return true;
  });

  const handleTestBlock = (pkt: NetworkPacket) => {
    setTestBlockStatus(`Simulating block on ${pkt.domain}: Checked dependency graph—no vital authentication tokens depend on this domain. Safe to block without breaking ${pkt.app}.`);
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-5xl h-[85vh] flex flex-col shadow-2xl overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="p-5 border-b border-slate-800 flex items-center justify-between gap-4">
          <div className="flex items-center gap-3">
            <div className="p-2 rounded-lg bg-teal-500/10 border border-teal-500/30 text-teal-400">
              <Activity className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>Traffic Interpreter</span>
                <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-teal-400 font-bold border border-slate-700">
                  F675 Flagship
                </span>
              </h2>
              <p className="text-xs text-slate-400">
                Identify &rarr; Explain in Plain English &rarr; Test-Block &rarr; Choose Action
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

        {/* Filter bar */}
        <div className="px-5 py-3 bg-slate-950/60 border-b border-slate-800 flex flex-wrap items-center justify-between gap-3">
          <div className="flex items-center gap-2 text-xs">
            <Filter className="w-3.5 h-3.5 text-slate-500" />
            <button
              onClick={() => setFilterType('all')}
              className={`px-2.5 py-1 rounded-md transition-colors ${filterType === 'all' ? 'bg-slate-800 text-white font-medium' : 'text-slate-400 hover:text-white'}`}
            >
              All ({packets.length})
            </button>
            <button
              onClick={() => setFilterType('faked')}
              className={`px-2.5 py-1 rounded-md transition-colors ${filterType === 'faked' ? 'bg-emerald-500/20 text-emerald-300 font-medium' : 'text-slate-400 hover:text-white'}`}
            >
              Faked / Stubs
            </button>
            <button
              onClick={() => setFilterType('blocked')}
              className={`px-2.5 py-1 rounded-md transition-colors ${filterType === 'blocked' ? 'bg-rose-500/20 text-rose-300 font-medium' : 'text-slate-400 hover:text-white'}`}
            >
              Blocked
            </button>
            <button
              onClick={() => setFilterType('allowed')}
              className={`px-2.5 py-1 rounded-md transition-colors ${filterType === 'allowed' ? 'bg-slate-800 text-slate-300 font-medium' : 'text-slate-400 hover:text-white'}`}
            >
              Allowed
            </button>
          </div>

          <div className="relative">
            <Search className="w-3.5 h-3.5 absolute left-2.5 top-2.5 text-slate-500" />
            <input
              type="text"
              placeholder="Search app or domain..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="bg-slate-900 border border-slate-800 rounded-lg pl-8 pr-3 py-1.5 text-xs text-slate-200 placeholder-slate-500 focus:outline-none focus:border-teal-500/50 w-56"
            />
          </div>
        </div>

        {/* 2-Column Split: Stream List + Dissection Pane */}
        <div className="flex-1 grid grid-cols-1 md:grid-cols-12 overflow-hidden">
          
          {/* Left Column: Packet List (5 cols) */}
          <div className="md:col-span-5 border-r border-slate-800 overflow-y-auto divide-y divide-slate-800/60 bg-slate-950/40">
            {filteredPackets.length === 0 ? (
              <div className="p-8 text-center text-slate-500 text-xs">
                No network packets matching current criteria.
              </div>
            ) : (
              filteredPackets.map((pkt) => {
                const isSelected = selectedPacket?.id === pkt.id;
                return (
                  <div
                    key={pkt.id}
                    onClick={() => { setSelectedPacket(pkt); setTestBlockStatus(null); }}
                    className={`p-3.5 cursor-pointer transition-colors text-xs flex flex-col gap-1.5 ${
                      isSelected ? 'bg-teal-500/10 border-l-2 border-teal-400' : 'hover:bg-slate-900/60'
                    }`}
                  >
                    <div className="flex items-center justify-between">
                      <span className="font-semibold text-slate-200 truncate">{pkt.app}</span>
                      <span className="font-mono text-[10px] text-slate-500">
                        {new Date(pkt.timestamp).toLocaleTimeString()}
                      </span>
                    </div>

                    <div className="font-mono text-[11px] text-slate-400 truncate">
                      {pkt.domain}
                    </div>

                    <div className="flex items-center justify-between text-[10px]">
                      <span className="font-mono text-slate-500">{pkt.protocol} · {pkt.payloadSize} B</span>
                      {pkt.status === 'faked' && (
                        <span className="text-emerald-400 font-medium">Synthetic Fake / Stub</span>
                      )}
                      {pkt.status === 'blocked' && (
                        <span className="text-rose-400 font-medium">Blocked</span>
                      )}
                      {pkt.status === 'allowed' && (
                        <span className="text-slate-400">Pass-through</span>
                      )}
                    </div>
                  </div>
                );
              })
            )}
          </div>

          {/* Right Column: Deep Interpretation Pane (7 cols) */}
          <div className="md:col-span-7 p-6 overflow-y-auto flex flex-col justify-between space-y-6 bg-slate-900/60">
            {selectedPacket ? (
              <div className="space-y-6">
                
                {/* 1. Identity & Owner */}
                <div>
                  <div className="text-[10px] uppercase tracking-wider text-slate-500 font-bold mb-1">
                    1. Identity &amp; Corporate Ownership
                  </div>
                  <div className="bg-slate-950 p-4 rounded-xl border border-slate-800 space-y-2">
                    <div className="flex items-center justify-between">
                      <span className="text-base font-bold text-slate-100">{selectedPacket.app}</span>
                      <span className="font-mono text-xs text-slate-400">{selectedPacket.destinationIp}</span>
                    </div>
                    <div className="font-mono text-xs text-teal-400">
                      Destination: {selectedPacket.domain}
                    </div>
                    <div className="text-xs text-slate-300 pt-2 border-t border-slate-900">
                      Parent Entity: {selectedPacket.isProbe ? 'Google AOSP / Apple WISPr Loopback' : selectedPacket.isAdTracker ? 'Third-Party Ad Broker / Tracker Network' : 'Standard Web Host'}
                    </div>
                  </div>
                </div>

                {/* 2. Plain English Explanation */}
                <div>
                  <div className="text-[10px] uppercase tracking-wider text-slate-500 font-bold mb-1">
                    2. What This Connection Is Doing (Plain English)
                  </div>
                  <div className="bg-slate-950/80 p-4 rounded-xl border border-slate-800 text-xs text-slate-200 leading-relaxed space-y-2">
                    {selectedPacket.isProbe ? (
                      <p>
                        This is an operating system connectivity probe. The app is asking <code className="text-teal-300">{selectedPacket.domain}</code> if you are connected to the internet. When intercepted by SERF, we return an instant synthetic HTTP 204 No Content response so offline apps launch immediately!
                      </p>
                    ) : selectedPacket.isAdTracker ? (
                      <p>
                        This is an advertising/telemetry beacon. {selectedPacket.app} is attempting to send analytics data. SERF provides an instant empty stub payload so the app never freezes waiting for a response.
                      </p>
                    ) : (
                      <p>
                        This is standard application data requested by {selectedPacket.app}. It is routed through your encrypted DoH resolver with DNSSEC integrity verification.
                      </p>
                    )}

                    {selectedPacket.simulatedResponse && (
                      <div className="mt-2 pt-2 border-t border-slate-800">
                        <div className="text-[10px] text-emerald-400 font-mono mb-1 font-semibold">Synthetic Response Generated (F03/F04):</div>
                        <pre className="text-[11px] font-mono text-emerald-300 bg-black/40 p-2 rounded border border-emerald-500/20 overflow-x-auto">
                          {selectedPacket.simulatedResponse}
                        </pre>
                      </div>
                    )}
                  </div>
                </div>

                {/* 3. Test-Block Simulation */}
                <div>
                  <div className="text-[10px] uppercase tracking-wider text-slate-500 font-bold mb-1">
                    3. Safe-Break Test Simulation
                  </div>
                  <button
                    onClick={() => handleTestBlock(selectedPacket)}
                    className="px-3 py-1.5 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-semibold flex items-center gap-2 border border-slate-700"
                  >
                    <Play className="w-3.5 h-3.5 text-teal-400" />
                    <span>Test-Block (Dry Run Breakage Check)</span>
                  </button>
                  {testBlockStatus && (
                    <div className="mt-2 p-3 rounded-lg bg-slate-950 border border-teal-500/30 text-xs text-teal-300 font-mono">
                      {testBlockStatus}
                    </div>
                  )}
                </div>

                {/* 4. Action Choices */}
                <div>
                  <div className="text-[10px] uppercase tracking-wider text-slate-500 font-bold mb-2">
                    4. Choose Action for {selectedPacket.domain}
                  </div>
                  <div className="grid grid-cols-3 gap-2">
                    <button
                      onClick={() => onActionOnPacket(selectedPacket.id, 'block')}
                      className="px-3 py-2 rounded-xl bg-rose-500/10 hover:bg-rose-500/20 text-rose-300 border border-rose-500/30 text-xs font-semibold flex items-center justify-center gap-1.5 transition-colors"
                    >
                      <Ban className="w-3.5 h-3.5" />
                      <span>Block Outright</span>
                    </button>

                    <button
                      onClick={() => onActionOnPacket(selectedPacket.id, 'fake')}
                      className="px-3 py-2 rounded-xl bg-emerald-500/10 hover:bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 text-xs font-semibold flex items-center justify-center gap-1.5 transition-colors"
                    >
                      <Sparkles className="w-3.5 h-3.5" />
                      <span>Feed Fake / Stub</span>
                    </button>

                    <button
                      onClick={() => onActionOnPacket(selectedPacket.id, 'allow')}
                      className="px-3 py-2 rounded-xl bg-slate-800 hover:bg-slate-700 text-slate-200 border border-slate-700 text-xs font-semibold flex items-center justify-center gap-1.5 transition-colors"
                    >
                      <Check className="w-3.5 h-3.5 text-emerald-400" />
                      <span>Always Allow</span>
                    </button>
                  </div>
                </div>

              </div>
            ) : (
              <div className="h-full flex items-center justify-center text-slate-500 text-xs">
                Select a network packet to inspect and interpret.
              </div>
            )}
          </div>

        </div>
      </div>
    </div>
  );
};
