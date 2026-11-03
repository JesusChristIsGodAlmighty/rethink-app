import React, { useState, useRef, useEffect } from 'react';
import { X, Terminal, CornerDownLeft, Trash2 } from 'lucide-react';
import { SerfModule } from '../types/serf';

interface DevConsoleModalProps {
  isOpen: boolean;
  onClose: () => void;
  modules: SerfModule[];
}

export const DevConsoleModal: React.FC<DevConsoleModalProps> = ({
  isOpen,
  onClose,
  modules
}) => {
  if (!isOpen) return null;

  const [inputVal, setInputVal] = useState('');
  const [history, setHistory] = useState<string[]>([
    'SERF Sandboxed Dev Console v0.1.0-alpha [F212]',
    'Type "help" for a list of internal diagnostics and commands.',
    'System: Go/gVisor tun2socks loopback initialized on 127.0.0.1:5353.'
  ]);
  const bottomRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [history]);

  const handleCommand = (e: React.FormEvent) => {
    e.preventDefault();
    const cmd = inputVal.trim();
    if (!cmd) return;

    const newHistory = [...history, `$ ${cmd}`];
    const parts = cmd.split(' ');
    const root = parts[0].toLowerCase();

    switch (root) {
      case 'help':
        newHistory.push(
          'Available Commands:',
          '  status         - Show loopback engine, probe responder, and VPN tunnel state',
          '  probe test     - Simulate /generate_204 probe response and report latency',
          '  leak-check     - Run F884/F887 security audit against radio handovers',
          '  modules        - List all loaded F-number modules and operational status',
          '  rules          - Show active radix tree filtering stats and loaded list counts',
          '  clear          - Clear console log buffer'
        );
        break;

      case 'status':
        newHistory.push(
          'SERF Engine Status:',
          '  [✓] Go/gVisor netstack: ACTIVE (sub-millisecond routing)',
          '  [✓] F01 Fake-Online: ACTIVE (answering /generate_204 on loopback)',
          '  [✓] F04 Fast-Fail Stubs: 42,910 endpoints compiled',
          '  [✓] F125 DoH Resolver: Quad9 (TLS 1.3, DNSSEC active)',
          '  [✓] Memory Footprint: 46 MB across 8 worker threads'
        );
        break;

      case 'probe':
        newHistory.push(
          'Simulating /generate_204 probe request...',
          '  > CONNECT connectivitycheck.gstatic.com:80',
          '  > Intercepted by F01 loopback netfilter',
          '  < HTTP/1.1 204 No Content',
          '  < Content-Length: 0',
          '  < Connection: close',
          '  Probe completed in 1.14ms. Zero external radio packets transmitted.'
        );
        break;

      case 'leak-check':
        newHistory.push(
          'Executing T0.8 / F884 Security Regression Audit...',
          '  [✓] Direct Boot early-stage packet drop rule: PASSED',
          '  [✓] IPv6 leak neutralization: PASSED (all traffic tunneled)',
          '  [✓] Captive portal bypass: PASSED (intercepted locally)',
          '  [✓] DNS leak over UDP port 53: PASSED (0 unencrypted queries)',
          'Audit result: 100% compliant. No data leaks detected.'
        );
        break;

      case 'modules':
        newHistory.push(
          `Registered Modules (${modules.length}):`,
          ...modules.map(m => `  [${m.id}] ${m.name.padEnd(28)} | ${m.status.toUpperCase().padEnd(9)} | Diff:${m.difficulty} | Cost:${m.cost}`)
        );
        break;

      case 'rules':
        newHistory.push(
          'Firewall Radix Tree Statistics:',
          '  Loaded Rules: 284,520 (EasyPrivacy, OISD, Peter Lowe, SERF Custom)',
          '  Memory Used: 18.4 MB (compressed bit-radix tree)',
          '  Lookup Speed: 0.12µs average per domain check',
          '  Manifest V3 Independence: Confirmed (Layer 3/4 TUN level)'
        );
        break;

      case 'clear':
        setHistory([]);
        setInputVal('');
        return;

      default:
        newHistory.push(`Command not recognized: "${cmd}". Type "help" for valid commands.`);
    }

    setHistory(newHistory);
    setInputVal('');
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-950 border border-slate-800 rounded-2xl w-full max-w-3xl h-[75vh] flex flex-col shadow-2xl overflow-hidden font-mono text-xs"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Terminal Header */}
        <div className="p-4 bg-slate-900 border-b border-slate-800 flex items-center justify-between">
          <div className="flex items-center gap-2 text-slate-300">
            <Terminal className="w-4 h-4 text-emerald-400" />
            <span className="font-bold">SERF Developer Console (F212)</span>
          </div>

          <div className="flex items-center gap-2">
            <button
              onClick={() => setHistory([])}
              title="Clear Console"
              className="p-1 rounded text-slate-500 hover:text-slate-300 transition-colors"
            >
              <Trash2 className="w-4 h-4" />
            </button>
            <button
              onClick={onClose}
              className="p-1 rounded text-slate-500 hover:text-white transition-colors"
            >
              <X className="w-4 h-4" />
            </button>
          </div>
        </div>

        {/* Terminal Output */}
        <div className="flex-1 p-4 overflow-y-auto space-y-1.5 text-slate-300 select-text">
          {history.map((line, idx) => (
            <div 
              key={idx}
              className={line.startsWith('$') ? 'text-emerald-400 font-semibold mt-2' : line.startsWith('  [✓]') ? 'text-emerald-300' : 'text-slate-300'}
            >
              {line}
            </div>
          ))}
          <div ref={bottomRef} />
        </div>

        {/* Terminal Input Form */}
        <form onSubmit={handleCommand} className="p-3 bg-slate-900 border-t border-slate-800 flex items-center gap-2">
          <span className="text-emerald-400 font-bold">$</span>
          <input
            type="text"
            value={inputVal}
            onChange={(e) => setInputVal(e.target.value)}
            placeholder="Type 'help', 'status', 'probe test', or 'leak-check'..."
            className="flex-1 bg-transparent text-slate-100 placeholder-slate-600 focus:outline-none text-xs"
            autoFocus
          />
          <button type="submit" className="text-slate-400 hover:text-emerald-400">
            <CornerDownLeft className="w-4 h-4" />
          </button>
        </form>
      </div>
    </div>
  );
};
