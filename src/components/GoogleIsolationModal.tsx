import React, { useState } from 'react';
import { 
  X, 
  Shield, 
  Store, 
  Lock, 
  Trash2, 
  Check, 
  Smartphone, 
  Zap, 
  CheckCircle2, 
  AlertTriangle 
} from 'lucide-react';

interface AppIsolationEntry {
  packageName: string;
  appName: string;
  isolatedFromGms: boolean;
  sourceDoctorActive: boolean;
  debloatFrozen: boolean;
  isSystem: boolean;
}

const DEFAULT_APPS: AppIsolationEntry[] = [
  {
    packageName: 'com.kiloo.subwaysurf',
    appName: 'Subway Surfers',
    isolatedFromGms: true,
    sourceDoctorActive: true,
    debloatFrozen: false,
    isSystem: false
  },
  {
    packageName: 'com.duolingo',
    appName: 'Duolingo',
    isolatedFromGms: true,
    sourceDoctorActive: true,
    debloatFrozen: false,
    isSystem: false
  },
  {
    packageName: 'com.sec.android.app.samsungapps',
    appName: 'Carrier Galaxy Store Telemetry',
    isolatedFromGms: false,
    sourceDoctorActive: false,
    debloatFrozen: true,
    isSystem: true
  },
  {
    packageName: 'com.facebook.system',
    appName: 'Meta App Installer Stub',
    isolatedFromGms: false,
    sourceDoctorActive: false,
    debloatFrozen: true,
    isSystem: true
  },
  {
    packageName: 'com.google.android.gms',
    appName: 'Google Play Services Core',
    isolatedFromGms: false,
    sourceDoctorActive: false,
    debloatFrozen: false,
    isSystem: true
  }
];

interface GoogleIsolationModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const GoogleIsolationModal: React.FC<GoogleIsolationModalProps> = ({
  isOpen,
  onClose
}) => {
  if (!isOpen) return null;

  const [activeTab, setActiveTab] = useState<'source-doctor' | 'isolation' | 'debloat'>('source-doctor');
  const [apps, setApps] = useState<AppIsolationEntry[]>(DEFAULT_APPS);
  const [shizukuConnected, setShizukuConnected] = useState(true);
  const [actionFeedback, setActionFeedback] = useState<string | null>(null);

  const toggleSourceDoctor = (pkg: string) => {
    setApps((prev) =>
      prev.map((a) => (a.packageName === pkg ? { ...a, sourceDoctorActive: !a.sourceDoctorActive } : a))
    );
    setActionFeedback(`F97 Source Doctor: Install source for ${pkg} set to com.android.vending (Google Play Store).`);
    setTimeout(() => setActionFeedback(null), 3000);
  };

  const toggleIsolation = (pkg: string) => {
    setApps((prev) =>
      prev.map((a) => (a.packageName === pkg ? { ...a, isolatedFromGms: !a.isolatedFromGms } : a))
    );
    setActionFeedback(`F96 Isolation: Sandbox state updated for ${pkg}. GMS calls stubbed locally.`);
    setTimeout(() => setActionFeedback(null), 3000);
  };

  const toggleDebloat = (pkg: string) => {
    setApps((prev) =>
      prev.map((a) => (a.packageName === pkg ? { ...a, debloatFrozen: !a.debloatFrozen } : a))
    );
    setActionFeedback(`F310/F321 Shizuku: Package state toggled (pm disable-user / enable).`);
    setTimeout(() => setActionFeedback(null), 3000);
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
            <div className="p-2 rounded-lg bg-indigo-500/10 border border-indigo-500/30 text-indigo-400">
              <Store className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>Google Play &amp; Device Ownership Defense</span>
                <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-indigo-400 font-bold border border-slate-700">
                  F96 / F97 / F310 / F321
                </span>
              </h2>
              <p className="text-xs text-slate-400">
                Source Doctor (install spoofer), Per-App Google Isolation, and Shizuku no-root debloating.
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
        <div className="px-5 py-2.5 bg-slate-950/60 border-b border-slate-800 flex items-center justify-between gap-2 text-xs font-medium">
          <div className="flex items-center gap-2">
            <button
              onClick={() => setActiveTab('source-doctor')}
              className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
                activeTab === 'source-doctor' ? 'bg-indigo-500/20 text-indigo-300 font-semibold border border-indigo-500/30' : 'text-slate-400 hover:text-slate-200'
              }`}
            >
              <Store className="w-3.5 h-3.5" />
              <span>Source Doctor (F97)</span>
            </button>

            <button
              onClick={() => setActiveTab('isolation')}
              className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
                activeTab === 'isolation' ? 'bg-indigo-500/20 text-indigo-300 font-semibold border border-indigo-500/30' : 'text-slate-400 hover:text-slate-200'
              }`}
            >
              <Lock className="w-3.5 h-3.5" />
              <span>Google Isolation (F96)</span>
            </button>

            <button
              onClick={() => setActiveTab('debloat')}
              className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
                activeTab === 'debloat' ? 'bg-indigo-500/20 text-indigo-300 font-semibold border border-indigo-500/30' : 'text-slate-400 hover:text-slate-200'
              }`}
            >
              <Trash2 className="w-3.5 h-3.5" />
              <span>ADB Debloat Wizard (F310)</span>
            </button>
          </div>

          <div className="flex items-center gap-1.5 text-[11px] font-mono text-slate-400">
            <span className="w-2 h-2 rounded-full bg-emerald-400" />
            <span>Shizuku Layer (F321): Active</span>
          </div>
        </div>

        {/* Feedback Alert */}
        {actionFeedback && (
          <div className="p-3 bg-indigo-950/40 border-b border-indigo-500/30 text-indigo-200 text-xs flex items-center gap-2 animate-in fade-in">
            <CheckCircle2 className="w-4 h-4 text-indigo-400" />
            <span>{actionFeedback}</span>
          </div>
        )}

        {/* Tab Body */}
        <div className="flex-1 p-6 overflow-y-auto space-y-4 text-sm">
          
          {/* TAB 1: Source Doctor (F97) */}
          {activeTab === 'source-doctor' && (
            <div className="space-y-4">
              <div className="bg-slate-950 p-4 rounded-xl border border-slate-800 text-xs text-slate-300 leading-relaxed">
                <span className="font-semibold text-slate-100">F97 Source Doctor:</span> Many apps downloaded from F-Droid, GitHub, or alternative stores lock features or display warnings saying "Install this app from Google Play". Source Doctor intercepts Android’s <code className="text-indigo-300">getInstallerPackageName()</code> API and reports <code className="text-emerald-400">com.android.vending</code> so the app runs smoothly without nagging.
              </div>

              <div className="divide-y divide-slate-800 rounded-xl border border-slate-800 bg-slate-950/60 overflow-hidden text-xs">
                {apps.filter(a => !a.isSystem).map((app) => (
                  <div key={app.packageName} className="p-4 flex items-center justify-between gap-4 hover:bg-slate-900/40 transition-colors">
                    <div>
                      <div className="font-bold text-slate-200">{app.appName}</div>
                      <div className="font-mono text-[11px] text-slate-500">{app.packageName}</div>
                      <div className="text-[11px] text-slate-400 mt-1">
                        Reported Installer: <span className="font-mono text-indigo-400 font-semibold">{app.sourceDoctorActive ? 'com.android.vending (Play Store)' : 'Default (Sideload / PackageInstaller)'}</span>
                      </div>
                    </div>

                    <button
                      onClick={() => toggleSourceDoctor(app.packageName)}
                      className={`px-3 py-1.5 rounded-lg font-semibold text-xs transition-colors ${
                        app.sourceDoctorActive
                          ? 'bg-indigo-500/20 text-indigo-300 border border-indigo-500/40'
                          : 'bg-slate-800 text-slate-400'
                      }`}
                    >
                      {app.sourceDoctorActive ? 'Play Store Spoofed' : 'Standard Installer'}
                    </button>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* TAB 2: Google Isolation (F96) */}
          {activeTab === 'isolation' && (
            <div className="space-y-4">
              <div className="bg-slate-950 p-4 rounded-xl border border-slate-800 text-xs text-slate-300 leading-relaxed">
                <span className="font-semibold text-slate-100">F96 Per-App Google Isolation:</span> Prevents third-party apps from querying your Google account identity, GSF ID, and Google Play Services advertising IDs. The app is isolated in a clean local sandbox.
              </div>

              <div className="divide-y divide-slate-800 rounded-xl border border-slate-800 bg-slate-950/60 overflow-hidden text-xs">
                {apps.filter(a => !a.isSystem).map((app) => (
                  <div key={app.packageName} className="p-4 flex items-center justify-between gap-4 hover:bg-slate-900/40 transition-colors">
                    <div>
                      <div className="font-bold text-slate-200">{app.appName}</div>
                      <div className="font-mono text-[11px] text-slate-500">{app.packageName}</div>
                      <div className="text-[11px] text-slate-400 mt-1">
                        Play Services Sandbox: <span className="font-mono text-emerald-400 font-semibold">{app.isolatedFromGms ? 'Isolated (Clean-Room Stubs)' : 'Direct Access'}</span>
                      </div>
                    </div>

                    <button
                      onClick={() => toggleIsolation(app.packageName)}
                      className={`px-3 py-1.5 rounded-lg font-semibold text-xs transition-colors ${
                        app.isolatedFromGms
                          ? 'bg-emerald-500/20 text-emerald-300 border border-emerald-500/40'
                          : 'bg-slate-800 text-slate-400'
                      }`}
                    >
                      {app.isolatedFromGms ? 'GMS Isolated' : 'Direct Play Services'}
                    </button>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* TAB 3: ADB Debloat Wizard (F310 & F321) */}
          {activeTab === 'debloat' && (
            <div className="space-y-4">
              <div className="bg-slate-950 p-4 rounded-xl border border-slate-800 text-xs text-slate-300 leading-relaxed flex items-start gap-3">
                <Zap className="w-5 h-5 text-amber-400 shrink-0 mt-0.5" />
                <div>
                  <span className="font-semibold text-slate-100">F310 ADB Debloat Wizard (Powered by F321 Shizuku):</span>{' '}
                  Safely disable pre-installed carrier bloatware and unneeded telemetry apps without needing root or a PC cable.
                </div>
              </div>

              <div className="divide-y divide-slate-800 rounded-xl border border-slate-800 bg-slate-950/60 overflow-hidden text-xs">
                {apps.map((app) => (
                  <div key={app.packageName} className="p-4 flex items-center justify-between gap-4 hover:bg-slate-900/40 transition-colors">
                    <div>
                      <div className="flex items-center gap-2">
                        <span className="font-bold text-slate-200">{app.appName}</span>
                        {app.isSystem && (
                          <span className="text-[10px] px-1.5 py-0.2 rounded bg-slate-800 text-slate-400 font-mono">
                            System / Pre-Installed
                          </span>
                        )}
                      </div>
                      <div className="font-mono text-[11px] text-slate-500">{app.packageName}</div>
                      <div className="text-[11px] text-slate-400 mt-1">
                        Status: <span className={`font-semibold font-mono ${app.debloatFrozen ? 'text-rose-400' : 'text-emerald-400'}`}>{app.debloatFrozen ? 'Frozen / Disabled' : 'Running'}</span>
                      </div>
                    </div>

                    <button
                      onClick={() => toggleDebloat(app.packageName)}
                      className={`px-3 py-1.5 rounded-lg font-semibold text-xs transition-colors ${
                        app.debloatFrozen
                          ? 'bg-rose-500/20 text-rose-300 border border-rose-500/40'
                          : 'bg-slate-800 text-slate-300 hover:bg-slate-700'
                      }`}
                    >
                      {app.debloatFrozen ? 'Defrost Package' : 'Freeze / Debloat'}
                    </button>
                  </div>
                ))}
              </div>
            </div>
          )}

        </div>
      </div>
    </div>
  );
};
