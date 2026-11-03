import React from 'react';
import { X, ShieldAlert, CheckCircle, Scale, AlertCircle, FileText } from 'lucide-react';

interface LegalDisclaimerModalProps {
  isOpen: boolean;
  onClose: () => void;
  onAcknowledge?: () => void;
}

export const LegalDisclaimerModal: React.FC<LegalDisclaimerModalProps> = ({
  isOpen,
  onClose,
  onAcknowledge
}) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-2xl max-h-[85vh] flex flex-col shadow-2xl overflow-hidden text-sm text-slate-100"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="p-5 border-b border-slate-800 flex items-center justify-between bg-slate-950/60">
          <div className="flex items-center gap-3">
            <div className="p-2.5 rounded-xl bg-amber-500/10 border border-amber-500/30 text-amber-400">
              <Scale className="w-5 h-5" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h2 className="text-base font-bold text-white tracking-tight">Sovereign Boundary &amp; Responsibility Notice</h2>
                <span className="px-2 py-0.5 text-[10px] font-semibold bg-amber-500/20 text-amber-300 border border-amber-500/30 rounded">
                  F988 SPECIFICATION
                </span>
              </div>
              <p className="text-xs text-slate-400 mt-0.5">
                Section 15 Master Blueprint: Transparent boundaries for dual-use endpoint security tools.
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
        <div className="flex-1 overflow-y-auto p-6 space-y-5 text-xs text-slate-300 leading-relaxed">
          
          <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-start gap-3">
            <ShieldAlert className="w-5 h-5 text-emerald-400 shrink-0 mt-0.5" />
            <div>
              <h3 className="font-bold text-slate-200 text-sm">General-Purpose Ownership of Your Own Hardware</h3>
              <p className="mt-1 text-slate-400">
                SERF operates strictly as an on-device endpoint filter, local firewall, and cryptographic client. Modifying, inspecting, fuzzing, or filtering what your own applications send or receive is your fundamental right as an owner configuring your personal property. Nothing in SERF connects to third-party devices or intercepts non-consenting traffic.
              </p>
            </div>
          </div>

          <div className="space-y-3">
            <h4 className="font-bold text-slate-200 uppercase tracking-wider text-[11px] flex items-center gap-2">
              <FileText className="w-4 h-4 text-cyan-400" />
              <span>Section 15 Boundary Commitments</span>
            </h4>

            <div className="space-y-2 font-sans">
              <div className="p-3 rounded-lg bg-slate-950/40 border border-slate-800/80 flex items-start gap-2.5">
                <CheckCircle className="w-4 h-4 text-emerald-400 shrink-0 mt-0.5" />
                <div>
                  <span className="font-semibold text-slate-200">Device-Scoped Defense Only:</span>
                  <span className="text-slate-400 ml-1">
                    Traffic interception is strictly scoped to this device. No wireless de-authentication, ARP poisoning, or third-party packet sniffing is included or permitted.
                  </span>
                </div>
              </div>

              <div className="p-3 rounded-lg bg-slate-950/40 border border-slate-800/80 flex items-start gap-2.5">
                <CheckCircle className="w-4 h-4 text-emerald-400 shrink-0 mt-0.5" />
                <div>
                  <span className="font-semibold text-slate-200">No Carrier Network Fraud:</span>
                  <span className="text-slate-400 ml-1">
                    SERF will not alter unencrypted carrier protocol data in transit to deceive carrier billing systems, provision unmetered flags, or clone cellular subscriber credentials.
                  </span>
                </div>
              </div>

              <div className="p-3 rounded-lg bg-slate-950/40 border border-slate-800/80 flex items-start gap-2.5">
                <CheckCircle className="w-4 h-4 text-emerald-400 shrink-0 mt-0.5" />
                <div>
                  <span className="font-semibold text-slate-200">Zero Telemetry &amp; Local Execution:</span>
                  <span className="text-slate-400 ml-1">
                    Every rule, cryptographic key, and filter runs 100% on-device. Zero telemetry, crash logs, or analytics are ever phoned home to any central server.
                  </span>
                </div>
              </div>
            </div>
          </div>

          <div className="p-4 rounded-xl bg-amber-950/20 border border-amber-500/30 text-amber-200/90 flex items-start gap-3">
            <AlertCircle className="w-5 h-5 text-amber-400 shrink-0 mt-0.5" />
            <div>
              <span className="font-bold text-amber-300">User Sovereignty &amp; Responsibility</span>
              <p className="mt-1 text-[11px] text-amber-200/80">
                You are responsible for how you deploy general-purpose capabilities over your device and data. SERF is open-source software distributed under the MPL-2.0 license, provided without warranty of any kind.
              </p>
            </div>
          </div>

        </div>

        {/* Footer */}
        <div className="p-4 border-t border-slate-800 bg-slate-950/80 flex items-center justify-between">
          <span className="text-xs text-slate-500 font-mono">F988 Sovereign Acknowledgment</span>
          <button
            onClick={() => {
              if (onAcknowledge) onAcknowledge();
              onClose();
            }}
            className="px-5 py-2 bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-bold text-xs rounded-xl transition-all shadow-sm flex items-center gap-2"
          >
            <CheckCircle className="w-4 h-4" />
            <span>I Acknowledge &amp; Understand</span>
          </button>
        </div>

      </div>
    </div>
  );
};
