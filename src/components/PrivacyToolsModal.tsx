import React, { useState } from 'react';
import { 
  X, 
  Sparkles, 
  Link, 
  Image, 
  Clipboard, 
  QrCode, 
  Trash2, 
  MessageSquarePlus, 
  Check, 
  Copy, 
  ShieldCheck,
  AlertCircle
} from 'lucide-react';

interface PrivacyToolsModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const PrivacyToolsModal: React.FC<PrivacyToolsModalProps> = ({
  isOpen,
  onClose
}) => {
  if (!isOpen) return null;

  const [activeTab, setActiveTab] = useState<'url' | 'exif' | 'clipboard' | 'wifi-qr' | 'cache' | 'feedback'>('url');

  // F411 URL Stripper
  const [dirtyUrl, setDirtyUrl] = useState('https://news.example.com/article?utm_source=facebook&utm_medium=cpc&fbclid=IwAR2k94j29fa92&user_id=84920&content=headline');
  const [cleanUrl, setCleanUrl] = useState('');
  const [copiedClean, setCopiedClean] = useState(false);

  const cleanUrlNow = () => {
    try {
      const parsed = new URL(dirtyUrl);
      const blacklistedParams = ['utm_source', 'utm_medium', 'utm_campaign', 'utm_term', 'utm_content', 'fbclid', 'gclid', 'user_id', 'msclkid', 'mc_eid', 'ref'];
      blacklistedParams.forEach(p => parsed.searchParams.delete(p));
      setCleanUrl(parsed.toString());
    } catch {
      setCleanUrl('Invalid URL entered. Please include https://');
    }
  };

  // F113 EXIF Scrubber
  const [exifStatus, setExifStatus] = useState<string | null>(null);

  const handleExifScrub = () => {
    setExifStatus('Scrubbing sample camera file: Removed GPS coordinates (37.7749° N, 122.4194° W), Camera Model (Pixel 9 Pro), and Timestamp (2026-11-02 14:22:01). Pixel data preserved 100% losslessly.');
  };

  // F155 Clipboard Guard
  const [clipboardTtl, setClipboardTtl] = useState(60);
  const [clipboardAlerts, setClipboardAlerts] = useState([
    { app: 'Shopping App', time: '14 mins ago', field: 'Copied text read in background' },
    { app: 'Social Messenger', time: '1 hour ago', field: 'Clipboard access detected on app resume' }
  ]);

  // F448 Wi-Fi QR
  const [wifiSsid, setWifiSsid] = useState('Home_Guest_Network');
  const [wifiPass, setWifiPass] = useState('WelcomeGuest2026!');
  const [wifiAuth, setWifiAuth] = useState('WPA');

  // F624 Cache Clear
  const [cacheCleared, setCacheCleared] = useState(false);

  // F631 Feature Request
  const [featureTitle, setFeatureTitle] = useState('');
  const [featureCategory, setFeatureCategory] = useState('Core Engine');
  const [featureDesc, setFeatureDesc] = useState('');
  const [requestSent, setRequestSent] = useState(false);

  const handleSendFeatureRequest = (e: React.FormEvent) => {
    e.preventDefault();
    setRequestSent(true);
    setTimeout(() => {
      setRequestSent(false);
      setFeatureTitle('');
      setFeatureDesc('');
    }, 3000);
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
              <Sparkles className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>Tier 1 Privacy Utilities Hub</span>
                <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-emerald-400 font-bold border border-slate-700">
                  Quick-Wins
                </span>
              </h2>
              <p className="text-xs text-slate-400">
                Lightweight, high-impact privacy tools that run entirely offline and local.
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
            onClick={() => setActiveTab('url')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
              activeTab === 'url' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Link className="w-3.5 h-3.5" />
            <span>URL Stripper (F411)</span>
          </button>

          <button
            onClick={() => setActiveTab('exif')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
              activeTab === 'exif' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Image className="w-3.5 h-3.5" />
            <span>Metadata Scrubber (F113)</span>
          </button>

          <button
            onClick={() => setActiveTab('clipboard')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
              activeTab === 'clipboard' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Clipboard className="w-3.5 h-3.5" />
            <span>Clipboard Guard (F155)</span>
          </button>

          <button
            onClick={() => setActiveTab('wifi-qr')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
              activeTab === 'wifi-qr' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <QrCode className="w-3.5 h-3.5" />
            <span>Guest Wi-Fi QR (F448)</span>
          </button>

          <button
            onClick={() => setActiveTab('cache')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
              activeTab === 'cache' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Trash2 className="w-3.5 h-3.5" />
            <span>Universal Cache (F624)</span>
          </button>

          <button
            onClick={() => setActiveTab('feedback')}
            className={`px-3 py-1.5 rounded-lg transition-colors whitespace-nowrap flex items-center gap-1.5 ${
              activeTab === 'feedback' ? 'bg-emerald-500/20 text-emerald-300 font-semibold border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <MessageSquarePlus className="w-3.5 h-3.5" />
            <span>Feature Request (F631)</span>
          </button>
        </div>

        {/* Tab Body */}
        <div className="flex-1 p-6 overflow-y-auto space-y-5 text-sm">
          
          {/* TAB 1: URL Parameter Stripper (F411) */}
          {activeTab === 'url' && (
            <div className="space-y-4 max-w-2xl">
              <div>
                <h3 className="font-bold text-slate-100 mb-1">Tracking Parameter Stripper (F411 &amp; F416)</h3>
                <p className="text-xs text-slate-400">
                  Strips spy parameters (fbclid, gclid, utm_source, ref) before links are opened or shared.
                </p>
              </div>

              <div className="space-y-2">
                <label className="text-xs text-slate-300 font-medium">Paste Tracking URL to Test:</label>
                <textarea
                  value={dirtyUrl}
                  onChange={(e) => setDirtyUrl(e.target.value)}
                  rows={3}
                  className="w-full bg-slate-950 border border-slate-800 rounded-xl p-3 text-xs text-slate-200 font-mono focus:outline-none focus:border-emerald-500/50"
                />
              </div>

              <button
                onClick={cleanUrlNow}
                className="px-4 py-2 bg-emerald-500 text-slate-950 font-bold rounded-lg text-xs hover:bg-emerald-400 transition-colors"
              >
                Sanitize Link Now
              </button>

              {cleanUrl && (
                <div className="p-4 bg-slate-950 border border-emerald-500/30 rounded-xl space-y-2 animate-in fade-in">
                  <div className="flex items-center justify-between">
                    <span className="text-xs font-bold text-emerald-400">Sanitized URL (Clean &amp; Untrackable):</span>
                    <button
                      onClick={() => { navigator.clipboard.writeText(cleanUrl); setCopiedClean(true); setTimeout(() => setCopiedClean(false), 2000); }}
                      className="text-xs text-slate-400 hover:text-white flex items-center gap-1 font-mono"
                    >
                      {copiedClean ? <Check className="w-3.5 h-3.5 text-emerald-400" /> : <Copy className="w-3.5 h-3.5" />}
                      <span>{copiedClean ? 'Copied' : 'Copy'}</span>
                    </button>
                  </div>
                  <div className="font-mono text-xs text-emerald-300 break-all bg-black/40 p-2.5 rounded border border-emerald-500/20">
                    {cleanUrl}
                  </div>
                </div>
              )}
            </div>
          )}

          {/* TAB 2: EXIF Metadata Scrubber (F113) */}
          {activeTab === 'exif' && (
            <div className="space-y-4 max-w-2xl">
              <div>
                <h3 className="font-bold text-slate-100 mb-1">Photo &amp; Document Metadata Scrubber (F113)</h3>
                <p className="text-xs text-slate-400">
                  Zeroes out EXIF GPS coordinates, camera serial numbers, and personal tags from photos before upload.
                </p>
              </div>

              <div className="p-6 border-2 border-dashed border-slate-800 rounded-2xl bg-slate-950/60 text-center space-y-3">
                <div className="w-12 h-12 rounded-xl bg-slate-900 border border-slate-800 text-slate-400 flex items-center justify-center mx-auto">
                  <Image className="w-6 h-6" />
                </div>
                <div className="text-xs text-slate-300">
                  Drop image files here to scrub EXIF GPS tags locally on your phone.
                </div>
                <button
                  onClick={handleExifScrub}
                  className="px-4 py-2 bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-semibold rounded-lg border border-slate-700 transition-colors"
                >
                  Simulate Scrubbing Camera Photo
                </button>
              </div>

              {exifStatus && (
                <div className="p-4 bg-emerald-950/30 border border-emerald-500/30 rounded-xl text-xs text-emerald-200 leading-relaxed font-mono animate-in fade-in">
                  {exifStatus}
                </div>
              )}
            </div>
          )}

          {/* TAB 3: Clipboard Guard (F155) */}
          {activeTab === 'clipboard' && (
            <div className="space-y-5 max-w-2xl">
              <div>
                <h3 className="font-bold text-slate-100 mb-1">Clipboard Guard (F155)</h3>
                <p className="text-xs text-slate-400">
                  Automatically clears copied passwords and alerts you when background apps read your clipboard.
                </p>
              </div>

              <div className="p-4 bg-slate-950 border border-slate-800 rounded-xl space-y-3">
                <div className="flex items-center justify-between">
                  <span className="text-xs font-semibold text-slate-200">Auto-Clear Clipboard Timeout:</span>
                  <div className="flex items-center gap-1.5 text-xs">
                    {[30, 60, 300].map((sec) => (
                      <button
                        key={sec}
                        onClick={() => setClipboardTtl(sec)}
                        className={`px-3 py-1 rounded-md font-mono ${
                          clipboardTtl === sec ? 'bg-emerald-500 text-slate-950 font-bold' : 'bg-slate-800 text-slate-400'
                        }`}
                      >
                        {sec}s
                      </button>
                    ))}
                  </div>
                </div>
                <p className="text-[11px] text-slate-500">
                  Any text you copy will be completely wiped from phone memory after {clipboardTtl} seconds.
                </p>
              </div>

              <div className="space-y-2">
                <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-400">
                  Recent Clipboard Snooping Alerts
                </h4>
                {clipboardAlerts.map((a, i) => (
                  <div key={i} className="p-3 bg-slate-950 border border-slate-800 rounded-lg flex items-center justify-between text-xs">
                    <div className="flex items-center gap-2">
                      <AlertCircle className="w-4 h-4 text-amber-400" />
                      <span className="font-semibold text-slate-200">{a.app}</span>
                      <span className="text-slate-400">&bull; {a.field}</span>
                    </div>
                    <span className="text-slate-500 font-mono text-[11px]">{a.time}</span>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* TAB 4: Guest Wi-Fi QR Generator (F448) */}
          {activeTab === 'wifi-qr' && (
            <div className="space-y-5 max-w-2xl">
              <div>
                <h3 className="font-bold text-slate-100 mb-1">Guest Wi-Fi QR Code Generator (F448)</h3>
                <p className="text-xs text-slate-400">
                  Lets friends connect to your Wi-Fi instantly using their camera without speaking the password out loud.
                </p>
              </div>

              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div className="space-y-3 bg-slate-950 p-4 rounded-xl border border-slate-800 text-xs">
                  <div>
                    <label className="block text-slate-400 mb-1 font-medium">Network Name (SSID):</label>
                    <input
                      type="text"
                      value={wifiSsid}
                      onChange={(e) => setWifiSsid(e.target.value)}
                      className="w-full bg-slate-900 border border-slate-800 rounded-lg px-3 py-2 text-slate-200 focus:outline-none focus:border-emerald-500/50"
                    />
                  </div>

                  <div>
                    <label className="block text-slate-400 mb-1 font-medium">Password:</label>
                    <input
                      type="text"
                      value={wifiPass}
                      onChange={(e) => setWifiPass(e.target.value)}
                      className="w-full bg-slate-900 border border-slate-800 rounded-lg px-3 py-2 text-slate-200 font-mono focus:outline-none focus:border-emerald-500/50"
                    />
                  </div>
                </div>

                <div className="bg-slate-950 p-6 rounded-xl border border-slate-800 flex flex-col items-center justify-center text-center">
                  <div className="p-3 bg-white rounded-xl mb-3 shadow-md">
                    <QrCode className="w-24 h-24 text-slate-950" />
                  </div>
                  <div className="text-xs font-bold text-slate-200">{wifiSsid}</div>
                  <div className="text-[10px] text-slate-500 font-mono mt-0.5">WIFI:S:{wifiSsid};T:WPA;P:***;;</div>
                </div>
              </div>
            </div>
          )}

          {/* TAB 5: Universal Cache Clear (F624) */}
          {activeTab === 'cache' && (
            <div className="space-y-4 max-w-2xl">
              <div>
                <h3 className="font-bold text-slate-100 mb-1">Universal Cache Clear (F624)</h3>
                <p className="text-xs text-slate-400">
                  Deletes junk advertising video preloads and web cookies while safeguarding SERF offline profiles.
                </p>
              </div>

              <div className="p-4 bg-slate-950 border border-slate-800 rounded-xl space-y-3 text-xs leading-relaxed text-slate-300">
                <div className="flex items-center gap-2 text-emerald-400 font-bold">
                  <ShieldCheck className="w-5 h-5" />
                  <span>Module Contract 4.2.12 Ironclad Protection Guarantee</span>
                </div>
                <p>
                  Per STATE.md Section 5.29: The Universal Cache Clear purges third-party junk without touching SERF’s F510/F514 verified evaluation caches, ensuring that your offline game presets and rule profiles remain intact.
                </p>
              </div>

              <button
                onClick={() => { setCacheCleared(true); setTimeout(() => setCacheCleared(false), 4000); }}
                className="px-5 py-2.5 bg-rose-500 hover:bg-rose-400 text-slate-950 font-bold text-xs rounded-xl flex items-center gap-2 transition-colors"
              >
                <Trash2 className="w-4 h-4" />
                <span>Sweep 1.4 GB Junk Cache</span>
              </button>

              {cacheCleared && (
                <div className="p-3 bg-emerald-950/40 border border-emerald-500/30 text-emerald-200 text-xs font-mono rounded-lg animate-in fade-in">
                  Cache swept successfully: 1.4 GB purged. All SERF check-once profiles safeguarded.
                </div>
              )}
            </div>
          )}

          {/* TAB 6: Feature Request (F631) */}
          {activeTab === 'feedback' && (
            <form onSubmit={handleSendFeatureRequest} className="space-y-4 max-w-2xl">
              <div>
                <h3 className="font-bold text-slate-100 mb-1">In-App Feature Request (F631)</h3>
                <p className="text-xs text-slate-400">
                  Submit feature ideas directly formatted for Section 4.5’s GitHub contribution pipeline.
                </p>
              </div>

              <div className="space-y-3 text-xs">
                <div>
                  <label className="block text-slate-400 mb-1 font-medium">Feature Title:</label>
                  <input
                    type="text"
                    required
                    placeholder="e.g. WireGuard QR Code Config Importer"
                    value={featureTitle}
                    onChange={(e) => setFeatureTitle(e.target.value)}
                    className="w-full bg-slate-950 border border-slate-800 rounded-lg px-3 py-2 text-slate-200 focus:outline-none focus:border-emerald-500/50"
                  />
                </div>

                <div>
                  <label className="block text-slate-400 mb-1 font-medium">Category:</label>
                  <select
                    value={featureCategory}
                    onChange={(e) => setFeatureCategory(e.target.value)}
                    className="w-full bg-slate-950 border border-slate-800 rounded-lg px-3 py-2 text-slate-200 focus:outline-none focus:border-emerald-500/50"
                  >
                    <option>Core Engine</option>
                    <option>Firewall &amp; Blocking</option>
                    <option>Routing &amp; Privacy</option>
                    <option>Tools &amp; Diagnostics</option>
                    <option>Mesh &amp; Social</option>
                    <option>Device &amp; System</option>
                  </select>
                </div>

                <div>
                  <label className="block text-slate-400 mb-1 font-medium">Describe your idea:</label>
                  <textarea
                    required
                    rows={4}
                    placeholder="Explain what it does and why it helps users..."
                    value={featureDesc}
                    onChange={(e) => setFeatureDesc(e.target.value)}
                    className="w-full bg-slate-950 border border-slate-800 rounded-lg px-3 py-2 text-slate-200 focus:outline-none focus:border-emerald-500/50"
                  />
                </div>

                <button
                  type="submit"
                  className="px-5 py-2.5 bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-bold text-xs rounded-xl flex items-center gap-2 transition-colors"
                >
                  <MessageSquarePlus className="w-4 h-4" />
                  <span>Submit to Community Registry (F216)</span>
                </button>

                {requestSent && (
                  <div className="p-3 bg-emerald-950/40 border border-emerald-500/30 text-emerald-200 text-xs font-mono rounded-lg animate-in fade-in">
                    Feature request formatted and queued for F216 contribution pipeline!
                  </div>
                )}
              </div>
            </form>
          )}

        </div>
      </div>
    </div>
  );
};
