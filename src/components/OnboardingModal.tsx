import React, { useState } from 'react';
import { 
  X, 
  Shield, 
  Sparkles, 
  Check, 
  ArrowRight, 
  Wifi, 
  Smartphone, 
  Lock 
} from 'lucide-react';

interface OnboardingModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSelectPosture: (posture: 'Casual' | 'Balanced' | 'Strict') => void;
  onLaunchOfflineTest: () => void;
}

export const OnboardingModal: React.FC<OnboardingModalProps> = ({
  isOpen,
  onClose,
  onSelectPosture,
  onLaunchOfflineTest
}) => {
  if (!isOpen) return null;

  const [step, setStep] = useState<1 | 2 | 3>(1);
  const [chosenPosture, setChosenPosture] = useState<'Casual' | 'Balanced' | 'Strict'>('Balanced');

  const handleFinish = () => {
    onSelectPosture(chosenPosture);
    onClose();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/85 backdrop-blur-md animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-xl flex flex-col shadow-2xl overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Step indicators */}
        <div className="p-4 bg-slate-950/80 border-b border-slate-800 flex items-center justify-between text-xs">
          <div className="flex items-center gap-2">
            <span className="font-bold text-slate-100">Guided First-Run Setup</span>
            <span className="font-mono text-emerald-400 font-bold">[F186]</span>
          </div>

          <div className="flex items-center gap-1.5 font-mono text-[11px] text-slate-500">
            <span className={step >= 1 ? 'text-emerald-400 font-bold' : ''}>1. Welcome</span>
            <span>&rarr;</span>
            <span className={step >= 2 ? 'text-emerald-400 font-bold' : ''}>2. Posture</span>
            <span>&rarr;</span>
            <span className={step >= 3 ? 'text-emerald-400 font-bold' : ''}>3. Ready</span>
          </div>
        </div>

        {/* Step 1: Welcome & Mission */}
        {step === 1 && (
          <div className="p-8 text-center space-y-5">
            <div className="w-16 h-16 rounded-2xl bg-emerald-500/10 border border-emerald-500/30 text-emerald-400 flex items-center justify-center mx-auto shadow-lg shadow-emerald-500/10">
              <Shield className="w-8 h-8" />
            </div>

            <div className="space-y-2">
              <h3 className="text-2xl font-black text-slate-100 tracking-tight">
                Welcome to SERF
              </h3>
              <p className="text-xs text-emerald-400 font-medium">
                A phone that actually belongs to you.
              </p>
            </div>

            <p className="text-xs text-slate-300 leading-relaxed max-w-md mx-auto bg-slate-950 p-4 rounded-xl border border-slate-800 text-left">
              Built from passion and care for the people who use it, not to extract money from them. Every feature ships <strong>off by default</strong>. All user data stays <strong>local and encrypted</strong>. No accounts, no subscriptions, and no telemetry.
            </p>

            <div className="pt-2 flex justify-end">
              <button
                onClick={() => setStep(2)}
                className="px-5 py-2.5 rounded-xl bg-emerald-500 text-slate-950 font-bold text-xs flex items-center gap-2 hover:bg-emerald-400 transition-colors shadow-md"
              >
                <span>Choose Protection Posture</span>
                <ArrowRight className="w-4 h-4" />
              </button>
            </div>
          </div>
        )}

        {/* Step 2: Choose Posture */}
        {step === 2 && (
          <div className="p-6 space-y-5">
            <div className="space-y-1">
              <h3 className="text-lg font-bold text-slate-100">
                Choose Your Protection Level
              </h3>
              <p className="text-xs text-slate-400">
                You can change this anytime with one tap from the home screen profile switcher.
              </p>
            </div>

            <div className="space-y-3">
              {[
                {
                  id: 'Casual' as const,
                  title: 'Casual Daily',
                  desc: 'Blocks ads and popups; leaves all background telemetry open for maximum compatibility.'
                },
                {
                  id: 'Balanced' as const,
                  title: 'Balanced Sovereign (Recommended)',
                  desc: 'Engages F01 Fake-Online offline play, uBlock list engine, Quad9 encrypted DNS, and fast-fail stubs.'
                },
                {
                  id: 'Strict' as const,
                  title: 'Strict Fortress',
                  desc: 'Randomizes hardware IDs per app session, blocks all unverified trackers, and injects SensorID noise.'
                }
              ].map((p) => (
                <div
                  key={p.id}
                  onClick={() => setChosenPosture(p.id)}
                  className={`p-4 rounded-xl border cursor-pointer transition-all flex items-start justify-between gap-3 ${
                    chosenPosture === p.id
                      ? 'bg-emerald-500/10 border-emerald-500/40 ring-1 ring-emerald-500/20'
                      : 'bg-slate-950 border-slate-800 hover:border-slate-700'
                  }`}
                >
                  <div>
                    <div className="font-bold text-sm text-slate-200">{p.title}</div>
                    <p className="text-xs text-slate-400 mt-1">{p.desc}</p>
                  </div>
                  <div className={`w-5 h-5 rounded-full border flex items-center justify-center shrink-0 mt-0.5 ${
                    chosenPosture === p.id ? 'bg-emerald-500 border-emerald-500 text-slate-950' : 'border-slate-700'
                  }`}>
                    {chosenPosture === p.id && <Check className="w-3.5 h-3.5 stroke-[3]" />}
                  </div>
                </div>
              ))}
            </div>

            <div className="pt-2 flex justify-between items-center">
              <button
                onClick={() => setStep(1)}
                className="text-xs text-slate-400 hover:text-white"
              >
                Back
              </button>
              <button
                onClick={() => setStep(3)}
                className="px-5 py-2.5 rounded-xl bg-emerald-500 text-slate-950 font-bold text-xs flex items-center gap-2 hover:bg-emerald-400 transition-colors shadow-md"
              >
                <span>Continue</span>
                <ArrowRight className="w-4 h-4" />
              </button>
            </div>
          </div>
        )}

        {/* Step 3: Test Fake-Online & Engage */}
        {step === 3 && (
          <div className="p-8 text-center space-y-6">
            <div className="w-16 h-16 rounded-2xl bg-emerald-500/10 border border-emerald-500/30 text-emerald-400 flex items-center justify-center mx-auto shadow-lg shadow-emerald-500/10">
              <Sparkles className="w-8 h-8" />
            </div>

            <div className="space-y-1">
              <h3 className="text-xl font-bold text-slate-100">
                You’re Ready to Go!
              </h3>
              <p className="text-xs text-slate-400">
                Selected Posture: <strong className="text-emerald-400">{chosenPosture}</strong>
              </p>
            </div>

            <div className="p-4 bg-slate-950 border border-slate-800 rounded-xl text-xs text-slate-300 leading-relaxed max-w-md mx-auto text-left space-y-2">
              <div className="font-semibold text-slate-100">Next Recommended Action:</div>
              <p>
                Try putting your phone in Airplane Mode and using the <strong>Offline Launch Verifier</strong> to watch an app launch without internet!
              </p>
            </div>

            <div className="pt-2 flex flex-col sm:flex-row gap-3 justify-center">
              <button
                onClick={() => { onLaunchOfflineTest(); onClose(); }}
                className="px-4 py-2.5 rounded-xl bg-slate-800 hover:bg-slate-700 text-slate-200 font-semibold text-xs border border-slate-700 transition-colors"
              >
                Open Offline Verifier
              </button>

              <button
                onClick={handleFinish}
                className="px-6 py-2.5 rounded-xl bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-bold text-xs transition-colors shadow-md"
              >
                Engage Protection Now
              </button>
            </div>
          </div>
        )}

      </div>
    </div>
  );
};
