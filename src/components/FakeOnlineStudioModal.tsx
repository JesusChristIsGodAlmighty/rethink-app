import React, { useState } from 'react';
import { 
  X, 
  Wifi, 
  Disc, 
  Download, 
  Upload, 
  Play, 
  Check, 
  FileCode, 
  ShieldAlert, 
  Layers, 
  Smartphone,
  Sparkles
} from 'lucide-react';

interface AppProfileTemplate {
  appName: string;
  packageName: string;
  category: 'Game' | 'Media' | 'Utility' | 'Education';
  probeFaked: string;
  stubsCount: number;
  testedOfflineDate: string;
  notes: string;
}

const PRESET_TEMPLATES: AppProfileTemplate[] = [
  {
    appName: 'Subway Surfers',
    packageName: 'com.kiloo.subwaysurf',
    category: 'Game',
    probeFaked: 'connectivitycheck.gstatic.com (HTTP 204)',
    stubsCount: 14,
    testedOfflineDate: '2026-11-01',
    notes: 'Bypasses opening "Checking connection" loop. UnityAds and Google Analytics answered with empty JSON.'
  },
  {
    appName: 'Duolingo',
    packageName: 'com.duolingo',
    category: 'Education',
    probeFaked: 'connectivitycheck.android.com (HTTP 204)',
    stubsCount: 8,
    testedOfflineDate: '2026-10-28',
    notes: 'Allows offline lesson review and practice without connection prompt error banner.'
  },
  {
    appName: 'Spotify Music',
    packageName: 'com.spotify.music',
    category: 'Media',
    probeFaked: 'spclient.wg.spotify.com loopback',
    stubsCount: 12,
    testedOfflineDate: '2026-10-30',
    notes: 'Enforces local cached music playback without hanging on background banner auctions.'
  },
  {
    appName: 'Adobe Acrobat Reader',
    packageName: 'com.adobe.reader',
    category: 'Utility',
    probeFaked: 'ims-na1.adobelogin.com cached',
    stubsCount: 6,
    testedOfflineDate: '2026-11-02',
    notes: 'PDF viewer opens instantly without waiting for cloud document telemetry sync.'
  }
];

interface FakeOnlineStudioModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const FakeOnlineStudioModal: React.FC<FakeOnlineStudioModalProps> = ({
  isOpen,
  onClose
}) => {
  if (!isOpen) return null;

  const [activeTab, setActiveTab] = useState<'presets' | 'recorder' | 'scanner'>('presets');
  const [selectedTemplate, setSelectedTemplate] = useState<AppProfileTemplate>(PRESET_TEMPLATES[0]);
  const [isRecording, setIsRecording] = useState(false);
  const [recordedPackets, setRecordedPackets] = useState<number>(0);
  const [recordFeedback, setRecordFeedback] = useState<string | null>(null);

  // F06 APK Static Scan
  const [scanPackage, setScanPackage] = useState('com.kiloo.subwaysurf');
  const [isScanning, setIsScanning] = useState(false);
  const [scanReport, setScanReport] = useState<{
    trackersFound: string[];
    offlineViability: string;
    verdict: string;
  } | null>(null);

  const handleStartRecording = () => {
    setIsRecording(true);
    setRecordFeedback(null);
    setRecordedPackets(0);
    const interval = setInterval(() => {
      setRecordedPackets((p) => {
        if (p >= 12) {
          clearInterval(interval);
          setIsRecording(false);
          setRecordFeedback(`Profile capture complete! Intercepted 12 handshake requests. Saved as local F05 offline template for ${selectedTemplate.appName}.`);
          return p;
        }
        return p + 2;
      });
    }, 400);
  };

  const handleRunStaticScan = () => {
    setIsScanning(true);
    setScanReport(null);
    setTimeout(() => {
      setIsScanning(false);
      setScanReport({
        trackersFound: [
          'UnityAds SDK v4.9 (Advertising)',
          'Google Firebase Analytics (Telemetry)',
          'AppLovin MAX SDK (Bidding Network)',
          'Facebook Core SDK (Graph pixel)'
        ],
        offlineViability: '98% Offline Viable (Core game assets stored locally)',
        verdict: 'App does not require cloud computation. Artificial internet requirement is strictly for advertising. Safe to run in Fake-Online mode.'
      });
    }, 800);
  };

  const handleExportProfile = () => {
    const dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(selectedTemplate, null, 2));
    const a = document.createElement('a');
    a.href = dataStr;
    a.download = `SERF_Profile_${selectedTemplate.appName.replace(/\s+/g, '_')}.json`;
    a.click();
    a.remove();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-4xl h-[85vh] flex flex-col shadow-2xl overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="p-5 border-b border-slate-800 flex items-center justify-between gap-4">
          <div className="flex items-center gap-3">
            <div className="p-2 rounded-lg bg-emerald-500/10 border border-emerald-500/30 text-emerald-400">
              <Wifi className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>Fake-Online Studio &amp; Profile Engine</span>
                <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-emerald-400 font-bold border border-slate-700">
                  F01–F07 Core Engine
                </span>
              </h2>
              <p className="text-xs text-slate-400">
                The "Soul" of SERF: Live capture, template learning, APK static scans, and shareable offline profiles.
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

        {/* Tab switcher */}
        <div className="px-5 py-2.5 bg-slate-950/60 border-b border-slate-800 flex items-center gap-2 text-xs font-medium overflow-x-auto">
          <button
            onClick={() => setActiveTab('presets')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
              activeTab === 'presets' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Layers className="w-3.5 h-3.5" />
            <span>Preset Profiles (F05 / F07)</span>
          </button>

          <button
            onClick={() => setActiveTab('recorder')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
              activeTab === 'recorder' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Disc className="w-3.5 h-3.5" />
            <span>Live Capture Recorder (F02)</span>
          </button>

          <button
            onClick={() => setActiveTab('scanner')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
              activeTab === 'scanner' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <FileCode className="w-3.5 h-3.5" />
            <span>APK Static Scan (F06)</span>
          </button>
        </div>

        {/* Tab Body */}
        <div className="flex-1 p-6 overflow-y-auto space-y-5 text-sm">
          
          {/* TAB 1: Preset Profiles (F05 / F07) */}
          {activeTab === 'presets' && (
            <div className="space-y-5">
              <div className="flex items-center justify-between">
                <div>
                  <h3 className="font-bold text-slate-100 mb-0.5">Verified Offline App Templates</h3>
                  <p className="text-xs text-slate-400">
                    Community-verified handshake stubs allowing apps to run in complete Airplane Mode.
                  </p>
                </div>

                <button
                  onClick={handleExportProfile}
                  className="px-3 py-1.5 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-semibold flex items-center gap-1.5 border border-slate-700 transition-colors"
                >
                  <Download className="w-3.5 h-3.5 text-emerald-400" />
                  <span>Export Profile (F07)</span>
                </button>
              </div>

              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                {PRESET_TEMPLATES.map((tmpl) => {
                  const isSelected = selectedTemplate.packageName === tmpl.packageName;
                  return (
                    <div
                      key={tmpl.packageName}
                      onClick={() => setSelectedTemplate(tmpl)}
                      className={`p-4 rounded-xl border cursor-pointer transition-all flex flex-col justify-between ${
                        isSelected
                          ? 'bg-emerald-500/10 border-emerald-500/40 ring-1 ring-emerald-500/20'
                          : 'bg-slate-950 border-slate-800 hover:border-slate-700'
                      }`}
                    >
                      <div>
                        <div className="flex items-center justify-between text-xs mb-1">
                          <span className="font-bold text-slate-100">{tmpl.appName}</span>
                          <span className="font-mono text-[10px] text-emerald-400">{tmpl.category}</span>
                        </div>
                        <div className="font-mono text-[11px] text-slate-500 mb-2 truncate">
                          {tmpl.packageName}
                        </div>
                        <p className="text-xs text-slate-300 line-clamp-2">{tmpl.notes}</p>
                      </div>

                      <div className="pt-3 border-t border-slate-800/80 mt-3 flex items-center justify-between text-[11px] font-mono text-slate-400">
                        <span>{tmpl.stubsCount} stubs armed</span>
                        <span className="text-emerald-400 font-semibold">Ready Offline</span>
                      </div>
                    </div>
                  );
                })}
              </div>

              {/* Selected Profile Detail */}
              <div className="p-5 bg-slate-950 rounded-xl border border-slate-800 space-y-3">
                <div className="font-bold text-xs uppercase tracking-wider text-slate-400">
                  Profile Configuration: {selectedTemplate.appName}
                </div>
                <div className="grid grid-cols-2 sm:grid-cols-4 gap-3 text-xs">
                  <div className="p-3 bg-slate-900 rounded-lg border border-slate-800">
                    <div className="text-slate-500 text-[10px]">Probe Intercept</div>
                    <div className="font-mono text-slate-200 mt-1 truncate">{selectedTemplate.probeFaked}</div>
                  </div>
                  <div className="p-3 bg-slate-900 rounded-lg border border-slate-800">
                    <div className="text-slate-500 text-[10px]">Synthesized Stubs</div>
                    <div className="font-mono text-slate-200 mt-1">{selectedTemplate.stubsCount} Endpoints</div>
                  </div>
                  <div className="p-3 bg-slate-900 rounded-lg border border-slate-800">
                    <div className="text-slate-500 text-[10px]">Radio Spoofing (F861)</div>
                    <div className="font-mono text-emerald-400 mt-1">Dual-Transport (Wi-Fi + LTE)</div>
                  </div>
                  <div className="p-3 bg-slate-900 rounded-lg border border-slate-800">
                    <div className="text-slate-500 text-[10px]">Fidelity Level (F692)</div>
                    <div className="font-mono text-sky-400 mt-1">Tier 3 (Full Synthetic)</div>
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* TAB 2: Live Capture Recorder (F02) */}
          {activeTab === 'recorder' && (
            <div className="space-y-5 max-w-2xl">
              <div>
                <h3 className="font-bold text-slate-100 mb-0.5">Live Capture / Recording Studio (F02)</h3>
                <p className="text-xs text-slate-400">
                  Record an app’s network requests while connected once. SERF parses the responses into permanent on-device offline templates.
                </p>
              </div>

              <div className="p-6 bg-slate-950 border border-slate-800 rounded-2xl text-center space-y-4">
                <div className="w-16 h-16 rounded-2xl bg-rose-500/10 border border-rose-500/30 text-rose-400 flex items-center justify-center mx-auto">
                  <Disc className={`w-8 h-8 ${isRecording ? 'animate-spin' : ''}`} />
                </div>

                <div>
                  <div className="text-base font-bold text-slate-100">
                    {isRecording ? 'Recording Handshake Buffers...' : 'Ready to Capture New App Handshake'}
                  </div>
                  <p className="text-xs text-slate-400 mt-1">
                    Select target app, hit record, launch the app while online for 5 seconds, then stop recording.
                  </p>
                </div>

                <div className="flex items-center justify-center gap-3">
                  <button
                    onClick={handleStartRecording}
                    disabled={isRecording}
                    className="px-5 py-2.5 rounded-xl bg-rose-500 hover:bg-rose-400 text-slate-950 font-bold text-xs flex items-center gap-2 shadow-lg shadow-rose-500/20 active:scale-98 transition-all"
                  >
                    <Disc className="w-4 h-4" />
                    <span>{isRecording ? `Recording (${recordedPackets} captured)...` : 'Start 1-Tap Handshake Capture'}</span>
                  </button>
                </div>
              </div>

              {recordFeedback && (
                <div className="p-4 bg-emerald-950/40 border border-emerald-500/30 text-emerald-300 rounded-xl text-xs font-mono leading-relaxed animate-in fade-in">
                  {recordFeedback}
                </div>
              )}
            </div>
          )}

          {/* TAB 3: APK Static Scan (F06) */}
          {activeTab === 'scanner' && (
            <div className="space-y-5 max-w-2xl">
              <div>
                <h3 className="font-bold text-slate-100 mb-0.5">APK Static Analyzer (F06)</h3>
                <p className="text-xs text-slate-400">
                  Inspects Android manifests and bytecode to reveal whether an app genuinely needs the cloud or is just gating single-player content for ads.
                </p>
              </div>

              <div className="flex items-center gap-2">
                <input
                  type="text"
                  placeholder="com.example.game or package name"
                  value={scanPackage}
                  onChange={(e) => setScanPackage(e.target.value)}
                  className="flex-1 bg-slate-950 border border-slate-800 rounded-lg px-3 py-2 text-xs text-slate-200 font-mono focus:outline-none focus:border-emerald-500/50"
                />
                <button
                  onClick={handleRunStaticScan}
                  disabled={isScanning}
                  className="px-4 py-2 bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-bold text-xs rounded-lg transition-colors shrink-0"
                >
                  {isScanning ? 'Auditing APK...' : 'Scan APK'}
                </button>
              </div>

              {scanReport && (
                <div className="bg-slate-950 p-5 rounded-xl border border-slate-800 space-y-4 animate-in fade-in">
                  <div className="flex items-center justify-between">
                    <span className="font-bold text-xs uppercase tracking-wider text-slate-300">
                      Static Analysis Verdict
                    </span>
                    <span className="text-xs font-mono text-emerald-400 font-bold">
                      {scanReport.offlineViability}
                    </span>
                  </div>

                  <p className="text-xs text-slate-300 leading-relaxed bg-slate-900/60 p-3 rounded-lg border border-slate-800">
                    {scanReport.verdict}
                  </p>

                  <div className="space-y-2">
                    <div className="text-[11px] font-semibold text-slate-400 uppercase tracking-wider">
                      Detected Tracking &amp; Advertising SDKs in Bytecode:
                    </div>
                    {scanReport.trackersFound.map((t, idx) => (
                      <div key={idx} className="p-2.5 bg-slate-900 rounded-lg border border-slate-800 text-xs text-slate-300 flex items-center gap-2">
                        <ShieldAlert className="w-4 h-4 text-amber-400 shrink-0" />
                        <span>{t}</span>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          )}

        </div>
      </div>
    </div>
  );
};
