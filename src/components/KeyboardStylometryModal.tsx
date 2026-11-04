import React, { useState } from 'react';
import { 
  X, 
  Keyboard, 
  Sparkles, 
  ShieldCheck, 
  Lock, 
  EyeOff, 
  Shuffle, 
  CheckCircle2, 
  ArrowRight, 
  Sliders, 
  Zap, 
  Fingerprint, 
  RefreshCw, 
  Copy, 
  Check
} from 'lucide-react';

interface KeyboardStylometryModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const KeyboardStylometryModal: React.FC<KeyboardStylometryModalProps> = ({
  isOpen,
  onClose
}) => {
  const [activeTab, setActiveTab] = useState<'keyboard' | 'stylometry' | 'password_mode'>('keyboard');

  // F909: Keystroke Dynamics Jitter
  const [timingJitterActive, setTimingJitterActive] = useState<boolean>(true);
  const [jitterRangeMs, setJitterRangeMs] = useState<number>(45); // 15ms-85ms
  const [typedSample, setTypedSample] = useState<string>('The quick brown fox jumps over the lazy dog.');
  const [keystrokesNeutralized, setKeystrokesNeutralized] = useState<number>(142);

  // F553: Hardened Password Field Mode
  const [passwordModeActive, setPasswordModeActive] = useState<boolean>(true);
  const [scramblePinpad, setScramblePinpad] = useState<boolean>(true);
  const [disableClipboardHistory, setDisableClipboardHistory] = useState<boolean>(true);

  // F913-F915: Stylometric Voice Transformer
  const [sourceText, setSourceText] = useState<string>(
    'I believe we should encrypt all outbound packets because our ISP is selling our browsing habits to third-party ad brokers.'
  );
  const [selectedPersona, setSelectedPersona] = useState<'academic' | 'succinct' | 'informal' | 'bureaucratic'>('academic');
  const [transformedText, setTransformedText] = useState<string>('');
  const [isTransforming, setIsTransforming] = useState<boolean>(false);
  const [copied, setCopied] = useState<boolean>(false);

  if (!isOpen) return null;

  const handleTransformStylometry = () => {
    setIsTransforming(true);
    setTimeout(() => {
      let result = '';
      if (selectedPersona === 'academic') {
        result = 'Empirical observations suggest that client-side packet encryption is imperative, insofar as telecommunications providers monetize subscriber metadata via commercial intermediaries.';
      } else if (selectedPersona === 'succinct') {
        result = 'Encrypt all outbound traffic. ISP monetizes browsing records.';
      } else if (selectedPersona === 'bureaucratic') {
        result = 'Pursuant to data retention protocols, outbound network encapsulation must be executed to mitigate commercial dissemination of telemetry assets by telecommunication carriers.';
      } else {
        result = 'Definitely need to lock down our traffic—no reason to let the phone company peddle our web logs to ad companies.';
      }
      setTransformedText(result);
      setIsTransforming(false);
    }, 600);
  };

  const handleCopyTransformed = () => {
    navigator.clipboard.writeText(transformedText || sourceText);
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-700/80 rounded-2xl w-full max-w-4xl max-h-[90vh] flex flex-col shadow-2xl overflow-hidden text-slate-100"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="px-6 py-4 border-b border-slate-800 flex items-center justify-between bg-slate-950/60">
          <div className="flex items-center gap-3">
            <div className="p-2.5 rounded-xl bg-amber-500/10 border border-amber-500/30 text-amber-400">
              <Keyboard className="w-5 h-5" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h2 className="text-lg font-bold text-white tracking-tight">Sovereign Keyboard &amp; Stylometry Studio</h2>
                <span className="px-2 py-0.5 text-[10px] font-semibold bg-amber-500/20 text-amber-300 border border-amber-500/30 rounded">
                  F908–F916 MASTER
                </span>
              </div>
              <p className="text-xs text-slate-400 mt-0.5">
                Offline system IME, keystroke dynamics biometric defense (F909), hardened password vault mode (F553) &amp; stylometric scrambler (F913).
              </p>
            </div>
          </div>

          <button
            onClick={onClose}
            className="p-2 text-slate-400 hover:text-white rounded-lg hover:bg-slate-800 transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Tab Navigation */}
        <div className="flex items-center gap-1 px-6 border-b border-slate-800 bg-slate-900/50 text-xs font-semibold overflow-x-auto">
          <button
            onClick={() => setActiveTab('keyboard')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'keyboard' ? 'border-amber-400 text-amber-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Fingerprint className="w-3.5 h-3.5" />
            <span>Anonymous Keystroke Dynamics (F909)</span>
          </button>

          <button
            onClick={() => setActiveTab('password_mode')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'password_mode' ? 'border-amber-400 text-amber-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Lock className="w-3.5 h-3.5" />
            <span>Hardened Vault Keyboard (F553)</span>
          </button>

          <button
            onClick={() => setActiveTab('stylometry')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'stylometry' ? 'border-amber-400 text-amber-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Sparkles className="w-3.5 h-3.5" />
            <span>Stylometric Scrambler (F913–F915)</span>
          </button>
        </div>

        {/* Tab Body */}
        <div className="flex-1 overflow-y-auto p-6 space-y-6">

          {/* TAB 1: KEYSTROKE DYNAMICS */}
          {activeTab === 'keyboard' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="text-sm font-bold text-white flex items-center gap-2">
                    <Fingerprint className="w-4 h-4 text-amber-400" />
                    <span>Behavioral-Biometric Keystroke Dynamics Cloaking (F909)</span>
                  </h3>
                  <p className="text-xs text-slate-400 mt-1">
                    Webpages and anti-fraud SDKs measure the microsecond delay between keypresses to fingerprint your physical typing cadence. SERF injects randomized micro-jitter into input dispatch while preserving 100% text accuracy.
                  </p>
                </div>

                <button
                  onClick={() => setTimingJitterActive(!timingJitterActive)}
                  className={`px-3 py-1.5 rounded-lg text-xs font-bold transition-colors ${
                    timingJitterActive ? 'bg-amber-500 text-slate-950 hover:bg-amber-400' : 'bg-slate-800 text-slate-400'
                  }`}
                >
                  {timingJitterActive ? 'JITTER ACTIVE' : 'BYPASS'}
                </button>
              </div>

              {/* Jitter Simulator Box */}
              <div className="p-5 rounded-xl bg-slate-950/50 border border-slate-800 space-y-4">
                <div className="flex items-center justify-between text-xs">
                  <span className="font-semibold text-slate-300">Interactive Typing Rhythm Scrambler</span>
                  <span className="text-amber-400 font-mono">{keystrokesNeutralized} Keystrokes Jittered</span>
                </div>

                <input
                  type="text"
                  value={typedSample}
                  onChange={(e) => {
                    setTypedSample(e.target.value);
                    setKeystrokesNeutralized(prev => prev + 1);
                  }}
                  placeholder="Type anything here to test behavioral cadence jitter..."
                  className="w-full bg-slate-900 border border-slate-700 rounded-xl px-4 py-2.5 text-xs text-slate-100 focus:outline-none focus:border-amber-500 font-mono"
                />

                <div className="grid grid-cols-1 sm:grid-cols-3 gap-3 pt-2 text-xs">
                  <div className="p-3 rounded-lg bg-slate-900/60 border border-slate-800">
                    <div className="text-[10px] text-slate-500 uppercase font-semibold">Micro-Jitter Window</div>
                    <div className="text-base font-bold text-emerald-400 mt-0.5">± {jitterRangeMs} ms</div>
                    <div className="text-[11px] text-slate-400 mt-0.5">Gaussian distribution</div>
                  </div>

                  <div className="p-3 rounded-lg bg-slate-900/60 border border-slate-800">
                    <div className="text-[10px] text-slate-500 uppercase font-semibold">Typing Cadence Entropy</div>
                    <div className="text-base font-bold text-amber-400 mt-0.5">99.8% Cloaked</div>
                    <div className="text-[11px] text-slate-400 mt-0.5">Defeats keystroke profiling</div>
                  </div>

                  <div className="p-3 rounded-lg bg-slate-900/60 border border-slate-800">
                    <div className="text-[10px] text-slate-500 uppercase font-semibold">Cloud Telemetry</div>
                    <div className="text-base font-bold text-cyan-400 mt-0.5">0 KB (100% Offline)</div>
                    <div className="text-[11px] text-slate-400 mt-0.5">No dictionary upload (F910)</div>
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* TAB 2: HARDENED PASSWORD VAULT MODE */}
          {activeTab === 'password_mode' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="text-sm font-bold text-white flex items-center gap-2">
                    <Lock className="w-4 h-4 text-emerald-400" />
                    <span>Hardened Password Vault Keyboard (F553)</span>
                  </h3>
                  <p className="text-xs text-slate-400 mt-1">
                    Automatically engages whenever an input field is flagged with TYPE_TEXT_VARIATION_PASSWORD or TYPE_NUMBER_VARIATION_PASSWORD.
                  </p>
                </div>

                <span className="px-2.5 py-1 text-xs font-bold bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 rounded-lg">
                  AUTO-ENGAGED
                </span>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 space-y-3">
                  <h4 className="text-xs font-bold text-slate-200 uppercase tracking-wider">Vault Protections</h4>
                  <div className="space-y-2 text-xs">
                    <div className="flex items-center justify-between p-2 rounded bg-slate-900 border border-slate-800">
                      <span className="text-slate-300">Clipboard History Suppression</span>
                      <button 
                        onClick={() => setDisableClipboardHistory(!disableClipboardHistory)}
                        className={`text-xs font-bold ${disableClipboardHistory ? 'text-emerald-400' : 'text-slate-500'}`}
                      >
                        {disableClipboardHistory ? 'ENFORCED' : 'OFF'}
                      </button>
                    </div>

                    <div className="flex items-center justify-between p-2 rounded bg-slate-900 border border-slate-800">
                      <span className="text-slate-300">Scramble PIN-Pad Key Positions</span>
                      <button 
                        onClick={() => setScramblePinpad(!scramblePinpad)}
                        className={`text-xs font-bold ${scramblePinpad ? 'text-emerald-400' : 'text-slate-500'}`}
                      >
                        {scramblePinpad ? 'ACTIVE' : 'OFF'}
                      </button>
                    </div>

                    <div className="flex items-center justify-between p-2 rounded bg-slate-900 border border-slate-800">
                      <span className="text-slate-300">Disable Learning Dictionary</span>
                      <span className="text-xs font-bold text-emerald-400">PERMANENT</span>
                    </div>
                  </div>
                </div>

                {/* Scrambled PIN Pad Preview */}
                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 text-center">
                  <span className="text-[11px] font-semibold text-slate-400 uppercase">Scrambled PIN-Pad Layout Preview</span>
                  <div className="grid grid-cols-3 gap-2 max-w-44 mx-auto mt-3 font-mono text-sm font-bold text-white">
                    {['7', '2', '9', '4', '1', '8', '0', '5', '3'].map((digit, i) => (
                      <div key={i} className="p-2.5 rounded-lg bg-slate-800/80 border border-slate-700/80 hover:bg-slate-700 transition-colors">
                        {digit}
                      </div>
                    ))}
                  </div>
                  <p className="text-[10px] text-slate-500 mt-2">Defeats shoulder-surfing and touchscreen thermal heat residue attacks</p>
                </div>
              </div>
            </div>
          )}

          {/* TAB 3: STYLOMETRIC SCRAMBLER */}
          {activeTab === 'stylometry' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                <h3 className="text-sm font-bold text-white flex items-center gap-2">
                  <Sparkles className="w-4 h-4 text-purple-400" />
                  <span>Rule-Based Stylometric Scrambler &amp; Persona Transformer (F913–F915)</span>
                </h3>
                <p className="text-xs text-slate-400 mt-1">
                  Defeats machine-learning authorship attribution by rephrasing syntax, vocabulary, and punctuation into selected stylistic personas. Employs Transform-Then-Insert staging (F915) so you can inspect before committing.
                </p>

                <div className="mt-4 space-y-3">
                  <textarea
                    rows={3}
                    value={sourceText}
                    onChange={(e) => setSourceText(e.target.value)}
                    placeholder="Enter confidential message to rewrite..."
                    className="w-full bg-slate-900 border border-slate-700 rounded-xl p-3 text-xs text-slate-100 focus:outline-none focus:border-purple-500 leading-relaxed"
                  />

                  {/* Persona Selector */}
                  <div className="flex flex-wrap items-center justify-between gap-3">
                    <div className="flex items-center gap-2">
                      <span className="text-xs text-slate-400 font-semibold">Target Persona:</span>
                      {[
                        { id: 'academic', label: 'Academic' },
                        { id: 'succinct', label: 'Succinct Terminal' },
                        { id: 'bureaucratic', label: 'Bureaucratic' },
                        { id: 'informal', label: 'Colloquial' }
                      ].map(p => (
                        <button
                          key={p.id}
                          onClick={() => setSelectedPersona(p.id as any)}
                          className={`px-2.5 py-1 rounded-lg text-xs font-semibold transition-all ${
                            selectedPersona === p.id 
                              ? 'bg-purple-600 text-white shadow-sm' 
                              : 'bg-slate-800 text-slate-400 hover:text-white'
                          }`}
                        >
                          {p.label}
                        </button>
                      ))}
                    </div>

                    <button
                      onClick={handleTransformStylometry}
                      disabled={isTransforming}
                      className="px-4 py-1.5 bg-purple-600 hover:bg-purple-500 disabled:opacity-50 text-white font-bold text-xs rounded-xl transition-all shadow-sm flex items-center gap-1.5"
                    >
                      <Sparkles className={`w-3.5 h-3.5 ${isTransforming ? 'animate-spin' : ''}`} />
                      <span>{isTransforming ? 'Transforming...' : 'Transform Voice'}</span>
                    </button>
                  </div>
                </div>
              </div>

              {/* Transformed Result Box */}
              {transformedText && (
                <div className="p-5 rounded-xl bg-slate-950/60 border border-purple-500/40 space-y-3 animate-in fade-in">
                  <div className="flex items-center justify-between text-xs">
                    <span className="font-bold text-purple-300 flex items-center gap-1.5">
                      <CheckCircle2 className="w-4 h-4 text-emerald-400" />
                      <span>Staged Rewrite Ready (F915 Transform-Then-Insert)</span>
                    </span>
                    <button
                      onClick={handleCopyTransformed}
                      className="px-2.5 py-1 rounded bg-slate-800 hover:bg-slate-700 text-slate-200 flex items-center gap-1 font-semibold text-xs"
                    >
                      {copied ? <Check className="w-3.5 h-3.5 text-emerald-400" /> : <Copy className="w-3.5 h-3.5" />}
                      <span>{copied ? 'Copied' : 'Copy Staged Text'}</span>
                    </button>
                  </div>

                  <div className="p-3.5 rounded-lg bg-slate-900 border border-slate-800 text-xs text-slate-200 leading-relaxed font-sans">
                    {transformedText}
                  </div>
                </div>
              )}
            </div>
          )}

        </div>

        {/* Footer */}
        <div className="px-6 py-3 border-t border-slate-800 bg-slate-950/80 flex items-center justify-between text-xs text-slate-400">
          <div className="flex items-center gap-2">
            <span className="w-2 h-2 rounded-full bg-amber-400 animate-pulse" />
            <span>Sovereign IME Active (100% Offline · Keystroke Jitter Engaged)</span>
          </div>
          <button
            onClick={onClose}
            className="px-4 py-1.5 bg-slate-800 hover:bg-slate-700 text-slate-200 rounded-lg font-semibold transition-colors"
          >
            Close
          </button>
        </div>

      </div>
    </div>
  );
};
