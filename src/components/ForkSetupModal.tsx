import React, { useState } from 'react';
import { 
  X, 
  GitFork, 
  CheckCircle2, 
  ShieldCheck, 
  Code, 
  Download, 
  Copy, 
  Check, 
  Terminal,
  ExternalLink,
  Key,
  Globe,
  UploadCloud,
  PlayCircle,
  AlertCircle
} from 'lucide-react';

interface ForkSetupModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const ForkSetupModal: React.FC<ForkSetupModalProps> = ({
  isOpen,
  onClose
}) => {
  if (!isOpen) return null;

  const [copied, setCopied] = useState(false);
  const [activeTab, setActiveTab] = useState<'connect' | 'license' | 'ci' | 'audit' | 'map'>('connect');

  // Interactive GitHub link state
  const [githubToken, setGithubToken] = useState('');
  const [repoName, setRepoName] = useState('serf');
  const [isConnecting, setIsConnecting] = useState(false);
  const [connectionStatus, setConnectionStatus] = useState<{
    success: boolean;
    username?: string;
    avatarUrl?: string;
    message: string;
    repoUrl?: string;
  } | null>(null);

  const [isForking, setIsForking] = useState(false);
  const [forkResult, setForkResult] = useState<string | null>(null);

  const testGitHubConnection = async () => {
    if (!githubToken.trim()) {
      setConnectionStatus({
        success: false,
        message: 'Please paste your GitHub Personal Access Token (PAT) below.'
      });
      return;
    }

    setIsConnecting(true);
    setConnectionStatus(null);

    try {
      const res = await fetch('https://api.github.com/user', {
        headers: {
          'Authorization': `Bearer ${githubToken.trim()}`,
          'Accept': 'application/vnd.github.v3+json'
        }
      });

      if (res.ok) {
        const data = await res.json();
        setConnectionStatus({
          success: true,
          username: data.login,
          avatarUrl: data.avatar_url,
          message: `Connected successfully as @${data.login}!`,
          repoUrl: `https://github.com/${data.login}/${repoName.trim() || 'serf'}`
        });
      } else {
        const errData = await res.json().catch(() => ({}));
        setConnectionStatus({
          success: false,
          message: errData.message || 'GitHub rejected the token. Ensure it has "repo" and "workflow" scopes.'
        });
      }
    } catch (err: any) {
      setConnectionStatus({
        success: false,
        message: `Network error connecting to api.github.com: ${err.message}`
      });
    } finally {
      setIsConnecting(false);
    }
  };

  const handleForkUpstream = async () => {
    if (!connectionStatus?.success) {
      setForkResult('Please test and verify your token first.');
      return;
    }

    setIsForking(true);
    setForkResult(null);

    try {
      const res = await fetch('https://api.github.com/repos/celzero/rethink-app/forks', {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${githubToken.trim()}`,
          'Accept': 'application/vnd.github.v3+json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          name: repoName.trim() || 'serf',
          default_branch_only: true
        })
      });

      if (res.ok || res.status === 202) {
        const forkData = await res.json();
        setForkResult(`Fork initiated successfully! Your new repo is ready at ${forkData.html_url}`);
      } else {
        const errData = await res.json().catch(() => ({}));
        setForkResult(`Fork response: ${errData.message || 'Check if repository name already exists in your account.'}`);
      }
    } catch (err: any) {
      setForkResult(`Forking failed: ${err.message}`);
    } finally {
      setIsForking(false);
    }
  };

  const githubActionsYaml = `name: Build SERF APK
on:
  push:
    branches: [ main, beta ]
  pull_request:
    branches: [ main ]
  workflow_dispatch:

jobs:
  build:
    name: Build Android APK
    runs-on: ubuntu-latest
    steps:
      - name: Checkout Code
        uses: actions/checkout@v4
        with:
          submodules: recursive

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Set up Go (for intra & gVisor netstack)
        uses: actions/setup-go@v5
        with:
          go-version: '1.22'

      - name: Setup Android NDK
        uses: nttld/setup-ndk@v1
        with:
          ndk-version: r26b

      - name: Compile Go Tun2Socks Engine
        run: |
          cd intra
          go build -v -tags "netstack" ./...

      - name: Build APK via Gradle
        run: |
          chmod +x ./gradlew
          ./gradlew assembleDebug --stacktrace

      - name: Upload Artifact
        uses: actions/upload-artifact@v4
        with:
          name: SERF-v0.1-debug.apk
          path: app/build/outputs/apk/debug/app-debug.apk`;

  const handleCopy = () => {
    navigator.clipboard.writeText(githubActionsYaml);
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
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
              <GitFork className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>GitHub Link &amp; Cloud CI Setup</span>
                <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-emerald-400 font-bold border border-slate-700">
                  T0.3 / D5
                </span>
              </h2>
              <p className="text-xs text-slate-400">
                Connect your GitHub account to automatically fork RethinkDNS, push code, and build APKs in the cloud.
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
            onClick={() => setActiveTab('connect')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap ${
              activeTab === 'connect' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            Connect GitHub Account
          </button>
          <button
            onClick={() => setActiveTab('license')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap ${
              activeTab === 'license' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            License &amp; Upstream (T0.3)
          </button>
          <button
            onClick={() => setActiveTab('ci')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap ${
              activeTab === 'ci' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            GitHub Actions CI (D5)
          </button>
          <button
            onClick={() => setActiveTab('audit')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap ${
              activeTab === 'audit' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            Security Audit (T0.8 / F884)
          </button>
          <button
            onClick={() => setActiveTab('map')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap ${
              activeTab === 'map' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            Codebase Architecture (T0.4)
          </button>
        </div>

        {/* Body */}
        <div className="flex-1 p-6 overflow-y-auto space-y-5 text-sm">
          
          {/* TAB 0: Interactive GitHub Connection */}
          {activeTab === 'connect' && (
            <div className="space-y-6">
              
              {/* How it works banner */}
              <div className="bg-emerald-950/20 border border-emerald-500/30 rounded-xl p-4 text-xs text-slate-300 space-y-2">
                <div className="font-bold text-sm text-emerald-300 flex items-center gap-2">
                  <Key className="w-4 h-4 text-emerald-400" />
                  <span>How to Grant Access to Your GitHub</span>
                </div>
                <p>
                  To allow forking repositories, uploading codebase files, and running cloud APK builds directly under your GitHub account, we use a standard <strong>GitHub Personal Access Token (PAT)</strong>.
                </p>
                <div className="grid grid-cols-1 sm:grid-cols-3 gap-3 pt-2">
                  <div className="p-3 bg-slate-950 rounded-lg border border-slate-800">
                    <span className="font-bold text-emerald-400">Step 1</span>
                    <p className="mt-1 text-[11px] text-slate-400">
                      Open <a href="https://github.com/settings/tokens/new" target="_blank" rel="noreferrer" className="text-emerald-400 underline inline-flex items-center gap-0.5">GitHub Token Settings <ExternalLink className="w-2.5 h-2.5" /></a>
                    </p>
                  </div>
                  <div className="p-3 bg-slate-950 rounded-lg border border-slate-800">
                    <span className="font-bold text-emerald-400">Step 2</span>
                    <p className="mt-1 text-[11px] text-slate-400">
                      Check scopes: <code className="text-slate-200">repo</code> (Full repository control) and <code className="text-slate-200">workflow</code> (Actions).
                    </p>
                  </div>
                  <div className="p-3 bg-slate-950 rounded-lg border border-slate-800">
                    <span className="font-bold text-emerald-400">Step 3</span>
                    <p className="mt-1 text-[11px] text-slate-400">
                      Copy the generated <code className="text-emerald-400">ghp_...</code> token and paste it below or in chat!
                    </p>
                  </div>
                </div>
              </div>

              {/* Form Input */}
              <div className="space-y-4 bg-slate-950 p-5 rounded-xl border border-slate-800">
                <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-300">
                  Connect &amp; Authorize
                </h4>

                <div className="space-y-3 text-xs">
                  <div>
                    <label className="block text-slate-400 mb-1 font-medium">GitHub Personal Access Token (PAT):</label>
                    <input
                      type="password"
                      placeholder="ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                      value={githubToken}
                      onChange={(e) => setGithubToken(e.target.value)}
                      className="w-full bg-slate-900 border border-slate-800 rounded-lg px-3 py-2 text-slate-200 font-mono text-xs focus:outline-none focus:border-emerald-500/50"
                    />
                  </div>

                  <div>
                    <label className="block text-slate-400 mb-1 font-medium">Repository Name to Create / Link:</label>
                    <input
                      type="text"
                      placeholder="serf"
                      value={repoName}
                      onChange={(e) => setRepoName(e.target.value)}
                      className="w-full sm:w-64 bg-slate-900 border border-slate-800 rounded-lg px-3 py-2 text-slate-200 font-mono text-xs focus:outline-none focus:border-emerald-500/50"
                    />
                  </div>

                  <div className="pt-2 flex items-center gap-3">
                    <button
                      onClick={testGitHubConnection}
                      disabled={isConnecting}
                      className="px-4 py-2 rounded-lg bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-bold text-xs flex items-center gap-1.5 transition-all active:scale-98"
                    >
                      <Globe className="w-3.5 h-3.5" />
                      <span>{isConnecting ? 'Testing Connection...' : 'Verify Token & Connect'}</span>
                    </button>
                  </div>
                </div>

                {/* Connection Feedback */}
                {connectionStatus && (
                  <div className={`p-4 rounded-xl border text-xs flex items-start gap-3 mt-4 ${
                    connectionStatus.success
                      ? 'bg-emerald-950/30 border-emerald-500/40 text-emerald-200'
                      : 'bg-rose-950/30 border-rose-500/40 text-rose-200'
                  }`}>
                    {connectionStatus.success ? (
                      <CheckCircle2 className="w-5 h-5 text-emerald-400 shrink-0 mt-0.5" />
                    ) : (
                      <AlertCircle className="w-5 h-5 text-rose-400 shrink-0 mt-0.5" />
                    )}
                    <div className="space-y-1">
                      <div className="font-bold">{connectionStatus.message}</div>
                      {connectionStatus.repoUrl && (
                        <div className="text-[11px] font-mono text-slate-300">
                          Target: <a href={connectionStatus.repoUrl} target="_blank" rel="noreferrer" className="text-emerald-400 underline">{connectionStatus.repoUrl}</a>
                        </div>
                      )}
                    </div>
                  </div>
                )}
              </div>

              {/* Action Buttons if connected */}
              {connectionStatus?.success && (
                <div className="p-5 rounded-xl bg-slate-950 border border-slate-800 space-y-4 animate-in fade-in">
                  <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-300">
                    One-Click GitHub Actions
                  </h4>

                  <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
                    <button
                      onClick={handleForkUpstream}
                      disabled={isForking}
                      className="p-4 rounded-xl bg-slate-900 border border-slate-800 hover:border-emerald-500/40 text-left transition-all group flex flex-col justify-between"
                    >
                      <div>
                        <div className="font-bold text-slate-100 group-hover:text-emerald-300 flex items-center gap-2">
                          <GitFork className="w-4 h-4 text-emerald-400" />
                          <span>1. Fork RethinkDNS (T0.3)</span>
                        </div>
                        <p className="text-xs text-slate-400 mt-1">
                          Creates a clean fork of celzero/rethink-app directly into your @{connectionStatus.username} account.
                        </p>
                      </div>
                      <span className="text-[11px] text-emerald-400 font-mono mt-3">
                        {isForking ? 'Creating fork...' : 'Execute Fork API →'}
                      </span>
                    </button>

                    <div className="p-4 rounded-xl bg-slate-900 border border-slate-800 text-left flex flex-col justify-between">
                      <div>
                        <div className="font-bold text-slate-100 flex items-center gap-2">
                          <UploadCloud className="w-4 h-4 text-sky-400" />
                          <span>2. Push Code &amp; Cloud CI Workflow</span>
                        </div>
                        <p className="text-xs text-slate-400 mt-1">
                          Pushes the SERF tile grid, Five Lenses engine, and GitHub Actions build workflow into your repository.
                        </p>
                      </div>
                      <span className="text-[11px] text-sky-400 font-mono mt-3">
                        Ready to sync via git push
                      </span>
                    </div>
                  </div>

                  {forkResult && (
                    <div className="p-3 bg-slate-900 border border-emerald-500/30 rounded-lg text-xs font-mono text-emerald-300">
                      {forkResult}
                    </div>
                  )}
                </div>
              )}

            </div>
          )}

          {/* TAB 1: License & Upstream Verification */}
          {activeTab === 'license' && (
            <div className="space-y-4">
              <div className="p-4 rounded-xl bg-slate-950 border border-slate-800 space-y-3">
                <div className="flex items-center gap-2 text-emerald-400 font-bold text-sm">
                  <CheckCircle2 className="w-5 h-5" />
                  <span>Upstream License Verified: Apache License 2.0</span>
                </div>
                <p className="text-xs text-slate-300 leading-relaxed">
                  RethinkDNS (<code className="text-emerald-300">celzero/rethink-app</code>) is licensed under the permissible <strong>Apache License, Version 2.0</strong>.
                  This grants full legal authorization to fork, modify, rename, and redistribute the codebase via F-Droid and direct APK without commercial restrictions.
                </p>
                <div className="grid grid-cols-1 sm:grid-cols-3 gap-3 pt-2 text-xs">
                  <div className="p-3 bg-slate-900 rounded-lg border border-slate-800">
                    <div className="text-slate-500 text-[11px]">Primary License</div>
                    <div className="font-semibold text-slate-200 mt-0.5">Apache-2.0</div>
                  </div>
                  <div className="p-3 bg-slate-900 rounded-lg border border-slate-800">
                    <div className="text-slate-500 text-[11px]">Sub-components</div>
                    <div className="font-semibold text-slate-200 mt-0.5">BSD-3-Clause / MIT</div>
                  </div>
                  <div className="p-3 bg-slate-900 rounded-lg border border-slate-800">
                    <div className="text-slate-500 text-[11px]">Distribution (D7)</div>
                    <div className="font-semibold text-slate-200 mt-0.5">F-Droid &amp; GitHub Release</div>
                  </div>
                </div>
              </div>

              <div className="space-y-2">
                <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-400">
                  Strategic Decision D1 &amp; D10 Alignment
                </h4>
                <p className="text-xs text-slate-300 leading-relaxed">
                  Per Decision D1, we inherit RethinkDNS’s hardened Go/gVisor networking foundation rather than reinventing the wheel. Under Decision D10, we permanently diverge from upstream’s UI in favor of our own native Android Material tile-grid design.
                </p>
              </div>
            </div>
          )}

          {/* TAB 2: GitHub Actions CI Build Script */}
          {activeTab === 'ci' && (
            <div className="space-y-4">
              <div className="flex items-center justify-between">
                <div>
                  <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-300">
                    Cloud Build Pipeline (.github/workflows/build.yml)
                  </h4>
                  <p className="text-xs text-slate-400">
                    Fulfills Decision D5: Builds the APK in GitHub Actions so no local PC or setup is required.
                  </p>
                </div>

                <button
                  onClick={handleCopy}
                  className="px-3 py-1.5 rounded-lg bg-slate-800 hover:bg-slate-700 text-xs font-medium text-slate-200 flex items-center gap-1.5 border border-slate-700 transition-colors"
                >
                  {copied ? <Check className="w-3.5 h-3.5 text-emerald-400" /> : <Copy className="w-3.5 h-3.5" />}
                  <span>{copied ? 'Copied' : 'Copy Workflow YAML'}</span>
                </button>
              </div>

              <pre className="p-4 rounded-xl bg-slate-950 border border-slate-800 font-mono text-xs text-emerald-300 overflow-x-auto leading-relaxed max-h-96">
                {githubActionsYaml}
              </pre>
            </div>
          )}

          {/* TAB 3: Upstream Security Audit (T0.8 / F884) */}
          {activeTab === 'audit' && (
            <div className="space-y-4">
              <div className="p-4 rounded-xl bg-slate-950 border border-slate-800 space-y-3">
                <div className="flex items-center gap-2 text-emerald-400 font-bold text-sm">
                  <ShieldCheck className="w-5 h-5" />
                  <span>T0.8 RethinkDNS Security Audit Summary</span>
                </div>
                <p className="text-xs text-slate-300 leading-relaxed">
                  Before building new features on the fork, we systematically audited upstream RethinkDNS against the standard VPN/firewall failure categories (F884 / F883):
                </p>
              </div>

              <div className="space-y-2.5 text-xs">
                <div className="p-3 bg-slate-950 rounded-lg border border-slate-800">
                  <div className="font-semibold text-slate-200 mb-1 flex items-center gap-2">
                    <span className="text-emerald-400">1. Captive Portal &amp; OS Probe Bypass:</span>
                    <span className="text-[10px] px-1.5 py-0.5 rounded bg-emerald-500/10 text-emerald-400 font-mono">FIXED in SERF</span>
                  </div>
                  <p className="text-slate-400 leading-relaxed">
                    Android network validation pings <code className="text-slate-300">/generate_204</code> outside normal DNS resolution during Wi-Fi connection. SERF intercepts these locally via F01 Fake-Online loopback, preventing cleartext OS telemetry leaks.
                  </p>
                </div>

                <div className="p-3 bg-slate-950 rounded-lg border border-slate-800">
                  <div className="font-semibold text-slate-200 mb-1 flex items-center gap-2">
                    <span className="text-emerald-400">2. Early Boot / Direct Boot Window Leakage:</span>
                    <span className="text-[10px] px-1.5 py-0.5 rounded bg-emerald-500/10 text-emerald-400 font-mono">FIXED in SERF</span>
                  </div>
                  <p className="text-slate-400 leading-relaxed">
                    Prior to user unlock, apps can send plaintext UDP 53 packets if VPN is not running. SERF introduces an AOSP-compliant early drop-all filter that holds until credentials decrypt the local keystore.
                  </p>
                </div>

                <div className="p-3 bg-slate-950 rounded-lg border border-slate-800">
                  <div className="font-semibold text-slate-200 mb-1 flex items-center gap-2">
                    <span className="text-emerald-400">3. Fast-Fail &amp; Timeout Hang Prevention:</span>
                    <span className="text-[10px] px-1.5 py-0.5 rounded bg-emerald-500/10 text-emerald-400 font-mono">IMPLEMENTED (F04)</span>
                  </div>
                  <p className="text-slate-400 leading-relaxed">
                    Rather than dropping tracker packets into a silent black hole (which causes poorly coded apps to hang for 30s), SERF synthesizes instant empty JSON or TCP RST responses.
                  </p>
                </div>
              </div>
            </div>
          )}

          {/* TAB 4: Codebase Architecture Map (T0.4) */}
          {activeTab === 'map' && (
            <div className="space-y-4">
              <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-400">
                T0.4 Codebase Directory Mapping
              </h4>
              <div className="bg-slate-950 p-4 rounded-xl border border-slate-800 font-mono text-xs text-slate-300 space-y-2">
                <div><span className="text-emerald-400 font-bold">/app/src/main/</span> — Kotlin UI &amp; Android Services</div>
                <div className="pl-4 text-slate-400">&bull; <code className="text-slate-200">service/BraveVPNService.kt</code>: Android VPNService lifecycle &amp; TUN FD management</div>
                <div className="pl-4 text-slate-400">&bull; <code className="text-slate-200">service/VpnController.kt</code>: State machine, start/stop transitions</div>
                <div className="pl-4 text-slate-400">&bull; <code className="text-slate-200">database/</code>: Local Room SQLite database for DNS logs &amp; rules</div>
                <div className="pt-2"><span className="text-sky-400 font-bold">/intra/</span> — Go / gVisor Networking Core</div>
                <div className="pl-4 text-slate-400">&bull; <code className="text-slate-200">tunnel/</code>: tun2socks packet translation between IP packets and user-space sockets</div>
                <div className="pl-4 text-slate-400">&bull; <code className="text-slate-200">doh/</code>: DNS-over-HTTPS client and TLS connection pooling</div>
                <div className="pt-2"><span className="text-violet-400 font-bold">/serf-modules/</span> — SERF Module SDK (F213)</div>
                <div className="pl-4 text-slate-400">&bull; Independent pluggable modules with F438 build-time removability</div>
              </div>
            </div>
          )}

        </div>
      </div>
    </div>
  );
};
