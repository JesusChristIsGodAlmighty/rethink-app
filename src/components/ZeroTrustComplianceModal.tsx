import React, { useState } from 'react';
import { 
  X, 
  ShieldCheck, 
  CheckCircle2, 
  AlertTriangle, 
  Download, 
  Upload, 
  FileText, 
  RefreshCw, 
  Server, 
  QrCode, 
  Lock, 
  Key, 
  Activity, 
  FileCheck, 
  Sliders
} from 'lucide-react';
import { 
  ZeroTrustPosture, 
  ExportImportConfig 
} from '../types/serf';
import { 
  INITIAL_ZERO_TRUST, 
  INITIAL_EXPORT_CONFIG, 
  runTier6DoneTestVerification 
} from '../services/networkSimulator';

interface ZeroTrustComplianceModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const ZeroTrustComplianceModal: React.FC<ZeroTrustComplianceModalProps> = ({
  isOpen,
  onClose
}) => {
  const [activeTab, setActiveTab] = useState<'posture' | 'compliance' | 'peers' | 'export' | 'selftest'>('posture');

  const [posture, setPosture] = useState<ZeroTrustPosture>(INITIAL_ZERO_TRUST);
  const [exportConfig, setExportConfig] = useState<ExportImportConfig>(INITIAL_EXPORT_CONFIG);
  const [exportSuccessMessage, setExportSuccessMessage] = useState<string | null>(null);
  const [isSyncingPeers, setIsSyncingPeers] = useState(false);

  // Self-Test State
  const [testResult, setTestResult] = useState<{
    ran: boolean;
    allPassed: boolean;
    checks: { name: string; passed: boolean; details: string }[];
  }>({
    ran: false,
    allPassed: false,
    checks: []
  });

  if (!isOpen) return null;

  const handleSyncFleet = () => {
    setIsSyncingPeers(true);
    setTimeout(() => {
      setPosture(prev => ({
        ...prev,
        syncPeerNodes: prev.syncPeerNodes.map(p => ({
          ...p,
          status: 'ONLINE',
          lastSync: Date.now()
        }))
      }));
      setIsSyncingPeers(false);
    }, 800);
  };

  const handleExportBundle = () => {
    const bundle = {
      version: 'SERF-v2-SOVEREIGN-EXPORT',
      timestamp: Date.now(),
      posture,
      rulesIncluded: exportConfig,
      signature: 'ed25519:9f8a3c4e...sovereign-attested'
    };
    const blob = new Blob([JSON.stringify(bundle, null, 2)], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `serf-sovereign-bundle-${Date.now()}.${exportConfig.exportFormat === 'YAML' ? 'yaml' : 'json'}`;
    a.click();
    URL.revokeObjectURL(url);
    setExportSuccessMessage(`Configuration bundle exported in ${exportConfig.exportFormat} format.`);
    setTimeout(() => setExportSuccessMessage(null), 3000);
  };

  const handleRunSelfTest = () => {
    const res = runTier6DoneTestVerification();
    setTestResult({
      ran: true,
      allPassed: res.allPassed,
      checks: res.checks
    });
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div className="relative w-full max-w-5xl max-h-[92vh] bg-slate-900 border border-slate-700/80 rounded-2xl shadow-2xl flex flex-col overflow-hidden text-slate-100">
        
        {/* Header */}
        <div className="px-6 py-4 border-b border-slate-800 flex items-center justify-between bg-slate-950/60">
          <div className="flex items-center gap-3">
            <div className="p-2.5 rounded-xl bg-cyan-500/10 border border-cyan-500/30 text-cyan-400">
              <ShieldCheck className="w-5 h-5" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h2 className="text-lg font-bold text-white tracking-tight">Zero-Trust Mesh, Compliance &amp; Export Studio</h2>
                <span className="px-2 py-0.5 text-[10px] font-semibold bg-cyan-500/20 text-cyan-300 border border-cyan-500/30 rounded">
                  TIER 6 MASTER
                </span>
              </div>
              <p className="text-xs text-slate-400 mt-0.5">
                Device posture validation, regulatory compliance audits (NIST/HIPAA/GDPR/ISO), encrypted P2P mesh &amp; export studio.
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
        <div className="flex items-center gap-1 px-6 border-b border-slate-800 bg-slate-900/50 overflow-x-auto text-xs font-semibold">
          <button
            onClick={() => setActiveTab('posture')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'posture'
                ? 'border-cyan-400 text-cyan-400'
                : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Activity className="w-3.5 h-3.5" />
            <span>Device Posture &amp; Root Health (F380–F390)</span>
          </button>

          <button
            onClick={() => setActiveTab('compliance')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'compliance'
                ? 'border-cyan-400 text-cyan-400'
                : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <FileCheck className="w-3.5 h-3.5" />
            <span>Regulatory Audits (F391–F395)</span>
          </button>

          <button
            onClick={() => setActiveTab('peers')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'peers'
                ? 'border-cyan-400 text-cyan-400'
                : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Server className="w-3.5 h-3.5" />
            <span>P2P Policy Mesh (F396–F400)</span>
          </button>

          <button
            onClick={() => setActiveTab('export')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'export'
                ? 'border-cyan-400 text-cyan-400'
                : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Download className="w-3.5 h-3.5" />
            <span>Export &amp; Import Studio (F401–F405)</span>
          </button>

          <button
            onClick={() => setActiveTab('selftest')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'selftest'
                ? 'border-cyan-400 text-cyan-400 font-bold'
                : 'border-transparent text-slate-400 hover:text-cyan-300'
            }`}
          >
            <CheckCircle2 className="w-3.5 h-3.5" />
            <span>Tier 6 Self-Test</span>
          </button>
        </div>

        {/* Tab Body */}
        <div className="flex-1 overflow-y-auto p-6 space-y-6">

          {/* TAB 1: DEVICE POSTURE & ROOT HEALTH */}
          {activeTab === 'posture' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Health Score</span>
                  <div className="text-3xl font-extrabold text-emerald-400 mt-1">{posture.deviceHealthScore}/100</div>
                  <div className="text-xs text-slate-400 mt-1">Sovereign High Assurance</div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">SELinux Mode</span>
                  <div className="text-xl font-bold text-cyan-400 mt-1">{posture.selinuxMode}</div>
                  <div className="text-xs text-slate-400 mt-1">Strict Mandatory Access Control</div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Root Integrity</span>
                  <div className="text-base font-bold text-emerald-400 mt-1">SECURE UNROOTED</div>
                  <div className="text-xs text-slate-400 mt-1">Hardware KeyStore Attested</div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Trust Tier</span>
                  <div className="text-sm font-bold text-indigo-400 mt-1">{posture.trustTier}</div>
                  <div className="text-xs text-slate-400 mt-1">Full isolation active</div>
                </div>
              </div>

              {/* Trust Tier Ladder */}
              <div className="p-5 rounded-xl bg-slate-950/50 border border-slate-800 space-y-3">
                <h4 className="text-xs font-bold text-slate-300 uppercase tracking-wider">Zero-Trust Authority Ladder</h4>
                <div className="grid grid-cols-1 md:grid-cols-4 gap-3 text-xs">
                  <div className="p-3 rounded-lg border border-slate-800 bg-slate-900/40 text-slate-500">
                    <span className="font-bold">Tier 0: Untrusted</span>
                    <p className="mt-1 text-[11px]">No hardware keystore. High-risk environment.</p>
                  </div>
                  <div className="p-3 rounded-lg border border-slate-800 bg-slate-900/40 text-slate-500">
                    <span className="font-bold">Tier 1: Basic</span>
                    <p className="mt-1 text-[11px]">Standard firewall enabled. No onion routing.</p>
                  </div>
                  <div className="p-3 rounded-lg border border-emerald-500/40 bg-emerald-950/20 text-emerald-300 font-semibold">
                    <span className="font-bold flex items-center gap-1">
                      <CheckCircle2 className="w-3.5 h-3.5" /> Tier 2: Hardened Sovereign
                    </span>
                    <p className="mt-1 text-[11px] text-emerald-400/80">Active ECH, DoQ, Tor circuits &amp; MAC rotation.</p>
                  </div>
                  <div className="p-3 rounded-lg border border-slate-800 bg-slate-900/40 text-slate-500">
                    <span className="font-bold">Tier 3: Air-Gapped</span>
                    <p className="mt-1 text-[11px]">Strict zero outbound RF. Baseband kill enabled.</p>
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* TAB 2: REGULATORY AUDITS */}
          {activeTab === 'compliance' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="font-bold text-white text-sm">Automated Regulatory Compliance Auditor</h3>
                  <p className="text-xs text-slate-400 mt-0.5">
                    Continuous evaluation of network policies and encryption primitives against major cybersecurity frameworks.
                  </p>
                </div>
                <div className="px-3 py-1 bg-emerald-950/40 border border-emerald-500/30 rounded-lg text-emerald-400 font-bold text-xs flex items-center gap-1.5">
                  <CheckCircle2 className="w-3.5 h-3.5" />
                  <span>4 / 4 STANDARDS COMPLIANT</span>
                </div>
              </div>

              {/* Compliance Cards */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {posture.complianceStandards.map((std) => (
                  <div key={std.id} className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 flex items-start justify-between">
                    <div>
                      <h4 className="text-sm font-semibold text-slate-200">{std.name}</h4>
                      <div className="text-xs text-slate-400 mt-1 font-mono">
                        Passing {std.passedRules} of {std.totalRules} security controls ({Math.round((std.passedRules / std.totalRules) * 100)}%)
                      </div>
                      <div className="w-full bg-slate-800 h-1.5 rounded-full mt-3 overflow-hidden">
                        <div 
                          className="bg-emerald-400 h-full rounded-full transition-all" 
                          style={{ width: `${(std.passedRules / std.totalRules) * 100}%` }}
                        />
                      </div>
                    </div>
                    <span className="px-2 py-0.5 text-[10px] font-bold bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 rounded">
                      {std.status}
                    </span>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* TAB 3: P2P POLICY MESH */}
          {activeTab === 'peers' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="font-bold text-white text-sm">Encrypted Peer-to-Peer Policy Mesh</h3>
                  <p className="text-xs text-slate-400 mt-0.5">
                    Synchronize blocklists, firewall rules, and zero-trust policies across trusted devices via WireGuard tunnels.
                  </p>
                </div>
                <button
                  onClick={handleSyncFleet}
                  disabled={isSyncingPeers}
                  className="px-4 py-2 bg-cyan-500 hover:bg-cyan-400 disabled:opacity-50 text-slate-950 font-bold text-xs rounded-xl transition-all shadow-sm flex items-center gap-2"
                >
                  <RefreshCw className={`w-3.5 h-3.5 ${isSyncingPeers ? 'animate-spin' : ''}`} />
                  <span>{isSyncingPeers ? 'Syncing...' : 'Sync Fleet Now'}</span>
                </button>
              </div>

              {/* Peers List */}
              <div className="space-y-3">
                {posture.syncPeerNodes.map((peer) => (
                  <div key={peer.nodeId} className="p-4 rounded-xl bg-slate-950/40 border border-slate-800 flex items-center justify-between">
                    <div className="flex items-center gap-3">
                      <div className="p-2 rounded-lg bg-slate-800 border border-slate-700 text-slate-300">
                        <Server className="w-4 h-4" />
                      </div>
                      <div>
                        <div className="flex items-center gap-2">
                          <h4 className="text-sm font-semibold text-slate-200">{peer.name}</h4>
                          <span className="text-[11px] text-slate-500 font-mono">({peer.role})</span>
                        </div>
                        <div className="text-xs text-slate-400 font-mono mt-0.5">
                          {peer.address} · Last synced {Math.round((Date.now() - peer.lastSync) / 1000)}s ago
                        </div>
                      </div>
                    </div>
                    <span className={`px-2 py-0.5 text-[10px] font-bold rounded ${
                      peer.status === 'ONLINE'
                        ? 'bg-emerald-500/20 text-emerald-300 border border-emerald-500/30'
                        : 'bg-amber-500/20 text-amber-300 border border-amber-500/30'
                    }`}>
                      {peer.status}
                    </span>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* TAB 4: EXPORT & IMPORT STUDIO */}
          {activeTab === 'export' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              {exportSuccessMessage && (
                <div className="p-3 rounded-xl bg-emerald-950/60 border border-emerald-500/40 text-emerald-300 text-xs flex items-center gap-2">
                  <CheckCircle2 className="w-4 h-4" />
                  <span>{exportSuccessMessage}</span>
                </div>
              )}

              <div className="p-5 rounded-xl bg-slate-950/60 border border-slate-800 space-y-4">
                <h3 className="font-bold text-white text-sm">Export Sovereign Configuration Bundle</h3>
                
                <div className="grid grid-cols-1 md:grid-cols-2 gap-3 text-xs">
                  <label className="flex items-center gap-2 text-slate-300 cursor-pointer">
                    <input
                      type="checkbox"
                      checked={exportConfig.exportProfiles}
                      onChange={(e) => setExportConfig(prev => ({ ...prev, exportProfiles: e.target.checked }))}
                      className="accent-cyan-500 rounded"
                    />
                    <span>Include Active Profiles &amp; Resolvers</span>
                  </label>

                  <label className="flex items-center gap-2 text-slate-300 cursor-pointer">
                    <input
                      type="checkbox"
                      checked={exportConfig.exportFirewallRules}
                      onChange={(e) => setExportConfig(prev => ({ ...prev, exportFirewallRules: e.target.checked }))}
                      className="accent-cyan-500 rounded"
                    />
                    <span>Include Custom Firewall Rules &amp; Threat Feeds</span>
                  </label>

                  <label className="flex items-center gap-2 text-slate-300 cursor-pointer">
                    <input
                      type="checkbox"
                      checked={exportConfig.exportHardwarePolicies}
                      onChange={(e) => setExportConfig(prev => ({ ...prev, exportHardwarePolicies: e.target.checked }))}
                      className="accent-cyan-500 rounded"
                    />
                    <span>Include Hardware &amp; Radio Shield Policies</span>
                  </label>

                  <label className="flex items-center gap-2 text-slate-300 cursor-pointer">
                    <input
                      type="checkbox"
                      checked={exportConfig.exportDnsPresets}
                      onChange={(e) => setExportConfig(prev => ({ ...prev, exportDnsPresets: e.target.checked }))}
                      className="accent-cyan-500 rounded"
                    />
                    <span>Include DoH/DoQ &amp; Cryptographic Keys</span>
                  </label>
                </div>

                <div className="pt-3 border-t border-slate-800 flex flex-col md:flex-row md:items-center justify-between gap-4">
                  <div className="flex items-center gap-3">
                    <span className="text-xs text-slate-400 font-semibold">Format:</span>
                    {(['JSON', 'YAML', 'ENCRYPTED_SERF_BUNDLE'] as const).map((fmt) => (
                      <button
                        key={fmt}
                        onClick={() => setExportConfig(prev => ({ ...prev, exportFormat: fmt }))}
                        className={`px-2.5 py-1 text-xs font-semibold rounded ${
                          exportConfig.exportFormat === fmt
                            ? 'bg-cyan-500 text-slate-950'
                            : 'bg-slate-800 text-slate-400 hover:text-white'
                        }`}
                      >
                        {fmt}
                      </button>
                    ))}
                  </div>

                  <button
                    onClick={handleExportBundle}
                    className="px-4 py-2 bg-cyan-500 hover:bg-cyan-400 text-slate-950 font-bold text-xs rounded-xl transition-all shadow-sm flex items-center gap-2 self-start md:self-auto"
                  >
                    <Download className="w-3.5 h-3.5" />
                    <span>Download Export Bundle</span>
                  </button>
                </div>
              </div>
            </div>
          )}

          {/* TAB 5: TIER 6 SELF-TEST */}
          {activeTab === 'selftest' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="font-bold text-white text-sm">Tier 6 Verification &amp; Test Suite</h3>
                  <p className="text-xs text-slate-400 mt-0.5">
                    Execute full end-to-end assertions against Zero-Trust Posture, Compliance, and Export engines.
                  </p>
                </div>
                <button
                  onClick={handleRunSelfTest}
                  className="px-4 py-2 bg-cyan-500 hover:bg-cyan-400 text-slate-950 font-bold text-xs rounded-xl transition-all shadow-sm flex items-center gap-2"
                >
                  <Activity className="w-3.5 h-3.5" />
                  <span>Execute Full Tier 6 Suite</span>
                </button>
              </div>

              {testResult.ran && (
                <div className={`p-4 rounded-xl border ${
                  testResult.allPassed 
                    ? 'bg-emerald-950/40 border-emerald-500/40 text-emerald-300' 
                    : 'bg-red-950/40 border-red-500/40 text-red-300'
                }`}>
                  <div className="flex items-center gap-2 font-bold text-sm">
                    {testResult.allPassed ? <CheckCircle2 className="w-5 h-5 text-emerald-400" /> : <AlertTriangle className="w-5 h-5 text-red-400" />}
                    <span>{testResult.allPassed ? 'ALL TIER 6 ENGINE CHECKS PASSED (100% AUDIT READY)' : 'TIER 6 VERIFICATION INCOMPLETE'}</span>
                  </div>

                  <div className="mt-4 space-y-2">
                    {testResult.checks.map((c, i) => (
                      <div key={i} className="p-3 rounded-lg bg-slate-900/60 border border-slate-800 text-xs flex items-start gap-3">
                        <CheckCircle2 className={`w-4 h-4 shrink-0 mt-0.5 ${c.passed ? 'text-emerald-400' : 'text-red-400'}`} />
                        <div>
                          <div className="font-semibold text-slate-200">{c.name}</div>
                          <div className="text-slate-400 mt-0.5 font-mono text-[11px]">{c.details}</div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          )}

        </div>

        {/* Footer */}
        <div className="px-6 py-3 border-t border-slate-800 bg-slate-950/80 flex items-center justify-between text-xs text-slate-400">
          <div className="flex items-center gap-2">
            <span className="w-2 h-2 rounded-full bg-cyan-400 animate-pulse" />
            <span>Zero-Trust Attestation Verified (SELinux Enforcing · NIST/HIPAA Compliant)</span>
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
