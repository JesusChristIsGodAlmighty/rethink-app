import React from 'react';
import { X, Shield, EyeOff, Lock, Radio, Check } from 'lucide-react';
import { ActiveProfile } from '../types/serf';

interface ProfilesModalProps {
  profiles: ActiveProfile[];
  activeProfileId: string;
  isOpen: boolean;
  onClose: () => void;
  onSelectProfile: (profile: ActiveProfile) => void;
}

export const ProfilesModal: React.FC<ProfilesModalProps> = ({
  profiles,
  activeProfileId,
  isOpen,
  onClose,
  onSelectProfile
}) => {
  if (!isOpen) return null;

  const getIcon = (iconName: string) => {
    switch (iconName) {
      case 'Shield': return <Shield className="w-5 h-5 text-emerald-400" />;
      case 'EyeOff': return <EyeOff className="w-5 h-5 text-violet-400" />;
      case 'Lock': return <Lock className="w-5 h-5 text-rose-400" />;
      case 'Radio': return <Radio className="w-5 h-5 text-sky-400" />;
      default: return <Shield className="w-5 h-5 text-emerald-400" />;
    }
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-xl flex flex-col shadow-2xl overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        <div className="p-5 border-b border-slate-800 flex items-center justify-between">
          <div>
            <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
              <span>Four-Slot Profile Switcher</span>
              <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-emerald-400 font-bold border border-slate-700">
                F890
              </span>
            </h2>
            <p className="text-xs text-slate-400">
              One-tap switching between exactly 4 pre-configured security postures.
            </p>
          </div>

          <button
            onClick={onClose}
            className="p-1.5 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        <div className="p-5 space-y-3">
          {profiles.map((p) => {
            const isActive = p.id === activeProfileId;
            return (
              <div
                key={p.id}
                onClick={() => { onSelectProfile(p); onClose(); }}
                className={`p-4 rounded-xl border cursor-pointer transition-all flex items-start justify-between gap-4 ${
                  isActive
                    ? 'bg-emerald-500/10 border-emerald-500/40 ring-1 ring-emerald-500/20'
                    : 'bg-slate-950 border-slate-800 hover:border-slate-700'
                }`}
              >
                <div className="flex items-start gap-3.5">
                  <div className="p-2.5 rounded-xl bg-slate-900 border border-slate-800 shrink-0">
                    {getIcon(p.icon)}
                  </div>
                  <div>
                    <div className="flex items-center gap-2">
                      <span className="font-bold text-sm text-slate-100">{p.name}</span>
                      {isActive && (
                        <span className="text-[10px] px-2 py-0.5 rounded-full bg-emerald-500 text-slate-950 font-bold">
                          CURRENT
                        </span>
                      )}
                    </div>
                    <p className="text-xs text-slate-400 mt-0.5">{p.tagline}</p>
                    <div className="flex items-center gap-3 text-[11px] font-mono text-slate-500 mt-2">
                      <span>DNS: {p.dnsResolver}</span>
                      <span>·</span>
                      <span>Firewall: {p.firewallStrictness}</span>
                    </div>
                  </div>
                </div>

                <div className="self-center">
                  {isActive ? (
                    <div className="w-6 h-6 rounded-full bg-emerald-500 text-slate-950 flex items-center justify-center">
                      <Check className="w-3.5 h-3.5 stroke-[3]" />
                    </div>
                  ) : (
                    <div className="w-6 h-6 rounded-full border border-slate-700" />
                  )}
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};
