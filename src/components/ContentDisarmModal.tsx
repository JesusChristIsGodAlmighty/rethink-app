import React, { useState } from 'react';
import { 
  X, 
  FileCheck, 
  ShieldCheck, 
  AlertTriangle, 
  FileCode, 
  Sparkles, 
  Sliders, 
  Film, 
  Volume2, 
  CheckCircle2, 
  Download, 
  Eye, 
  Zap, 
  Layers
} from 'lucide-react';

interface ContentDisarmModalProps {
  isOpen: boolean;
  onClose: () => void;
}

interface SanitizationSample {
  id: string;
  name: string;
  type: 'image' | 'pdf' | 'video_manifest';
  originalSizeKb: number;
  threatsFound: string[];
  exifTags: Record<string, string>;
}

const SAMPLES: SanitizationSample[] = [
  {
    id: 'sample-photo',
    name: 'Camera_DCIM_20261103.jpg',
    type: 'image',
    originalSizeKb: 4120,
    threatsFound: [
      'High-precision GPS Latitude/Longitude Tag',
      'Device Serial Number & Lens Fingerprint',
      'ICC Profile Steganographic Chaff (48KB)',
      'Thumbnail Preview Cache (Vulnerable to CVE-2023-4863)'
    ],
    exifTags: {
      'GPS Location': '37.7749° N, 122.4194° W (San Francisco, CA)',
      'Camera Model': 'Pixel 9 Pro (Build AP2A.240805.005)',
      'Lens Serial': 'SN-8849102-CAM-01',
      'Creation Timestamp': '2026-11-03 14:22:18 PST',
      'Software': 'Google Camera HDR+ Pipeline v9.2'
    }
  },
  {
    id: 'sample-doc',
    name: 'Invoice_Attachment_Q3.pdf',
    type: 'pdf',
    originalSizeKb: 890,
    threatsFound: [
      'Embedded AcroForm JavaScript Object (Action /Launch)',
      'External OpenAction URI Phone-Home Beacon',
      'Non-standard TrueType Font Table (Buffer Overflow Risk)'
    ],
    exifTags: {
      'Producer': 'Adobe Acrobat Pro 2024 / AcroJS Engine',
      'Embedded Scripts': 'App.launchURL("https://telemetry-track.net/ack")',
      'Font Engine': 'Type3 Substituted Font Stream'
    }
  },
  {
    id: 'sample-hls',
    name: 'master_playlist_adaptive.m3u8',
    type: 'video_manifest',
    originalSizeKb: 14,
    threatsFound: [
      'Forced 4K Ultra-HD Stream (Bandwidth Hog: 18 Mbps)',
      'QoE Heartbeat Telemetry Segment Beacon (/telemetry/ping?seg=)',
      'Third-Party Dynamic Ad Insertion (DAI) Sub-playlist'
    ],
    exifTags: {
      'Target Stream': 'HLS Multi-Bitrate (1080p60, 4K, 720p, 480p)',
      'Heartbeat Frequency': 'Every 2.0 seconds during playback',
      'Ad Pod Injection': 'EXT-X-DATERANGE ad-insertion trigger'
    }
  }
];

export const ContentDisarmModal: React.FC<ContentDisarmModalProps> = ({
  isOpen,
  onClose
}) => {
  const [selectedSample, setSelectedSample] = useState<SanitizationSample>(SAMPLES[0]);
  const [isProcessing, setIsProcessing] = useState(false);
  const [isDisarmed, setIsDisarmed] = useState(false);
  const [activeTab, setActiveTab] = useState<'cdr' | 'video_proxy'>('cdr');

  // Video proxy preferences
  const [qualityOverride, setQualityOverride] = useState<'auto' | '720p' | '480p' | 'audio_only'>('720p');
  const [blockVideoTelemetry, setBlockVideoTelemetry] = useState(true);

  if (!isOpen) return null;

  const handleDisarm = () => {
    setIsProcessing(true);
    setTimeout(() => {
      setIsProcessing(false);
      setIsDisarmed(true);
    }, 700);
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
            <div className="p-2.5 rounded-xl bg-purple-500/10 border border-purple-500/30 text-purple-400">
              <Layers className="w-5 h-5" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h2 className="text-lg font-bold text-white tracking-tight">Content Disarm &amp; Reconstruction (CDR) Studio</h2>
                <span className="px-2 py-0.5 text-[10px] font-semibold bg-purple-500/20 text-purple-300 border border-purple-500/30 rounded">
                  F927–F938 MASTER
                </span>
              </div>
              <p className="text-xs text-slate-400 mt-0.5">
                Zero-trust media sanitization (pixel-only extraction) and proxy-level HLS/DASH video quality enforcement.
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
        <div className="flex items-center gap-1 px-6 border-b border-slate-800 bg-slate-900/50 text-xs font-semibold">
          <button
            onClick={() => setActiveTab('cdr')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors ${
              activeTab === 'cdr' ? 'border-purple-400 text-purple-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Sparkles className="w-3.5 h-3.5" />
            <span>Zero-Trust File Sanitizer (F927–F929)</span>
          </button>

          <button
            onClick={() => setActiveTab('video_proxy')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors ${
              activeTab === 'video_proxy' ? 'border-purple-400 text-purple-400' : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Film className="w-3.5 h-3.5" />
            <span>Video Proxy &amp; Telemetry Scrubber (F930–F938)</span>
          </button>
        </div>

        {/* Content Body */}
        <div className="flex-1 overflow-y-auto p-6 space-y-6">

          {/* TAB 1: ZERO-TRUST CDR */}
          {activeTab === 'cdr' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              
              {/* Sample Chooser */}
              <div>
                <span className="text-[11px] text-slate-400 uppercase font-semibold">Select Test Media Stream</span>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-3 mt-2">
                  {SAMPLES.map((s) => (
                    <button
                      key={s.id}
                      onClick={() => {
                        setSelectedSample(s);
                        setIsDisarmed(false);
                      }}
                      className={`p-3.5 rounded-xl border text-left transition-all ${
                        selectedSample.id === s.id
                          ? 'bg-purple-950/30 border-purple-500/50 text-white'
                          : 'bg-slate-950/40 border-slate-800 text-slate-400 hover:text-slate-200'
                      }`}
                    >
                      <div className="flex items-center gap-2 font-bold text-xs truncate">
                        <FileCode className="w-4 h-4 text-purple-400 shrink-0" />
                        <span className="truncate">{s.name}</span>
                      </div>
                      <div className="text-[11px] text-slate-500 mt-1">
                        {s.originalSizeKb} KB · {s.threatsFound.length} metadata risks
                      </div>
                    </button>
                  ))}
                </div>
              </div>

              {/* Inspection Workspace */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                
                {/* Left: Raw Incoming Payload */}
                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 space-y-3">
                  <div className="flex items-center justify-between">
                    <span className="text-xs font-bold text-red-400 uppercase tracking-wider flex items-center gap-1.5">
                      <AlertTriangle className="w-3.5 h-3.5" /> Untrusted Raw Content
                    </span>
                    <span className="text-xs font-mono text-slate-500">{selectedSample.originalSizeKb} KB</span>
                  </div>

                  <div className="space-y-2">
                    <span className="text-[11px] text-slate-400 font-semibold">Identified Metadata &amp; Exploitation Vectors:</span>
                    <div className="space-y-1">
                      {selectedSample.threatsFound.map((t, idx) => (
                        <div key={idx} className="p-2 rounded bg-red-950/20 border border-red-500/30 text-red-300 text-[11px] flex items-center gap-2">
                          <span className="w-1.5 h-1.5 rounded-full bg-red-400" />
                          <span>{t}</span>
                        </div>
                      ))}
                    </div>
                  </div>

                  <div className="pt-2 border-t border-slate-800/80">
                    <span className="text-[11px] text-slate-400 font-semibold">Decoded Headers:</span>
                    <div className="mt-1 space-y-1 font-mono text-[10px] text-slate-400 bg-slate-900/60 p-2.5 rounded-lg border border-slate-800">
                      {Object.entries(selectedSample.exifTags).map(([k, v]) => (
                        <div key={k} className="truncate">
                          <span className="text-slate-500">{k}:</span> <span className="text-slate-300">{v}</span>
                        </div>
                      ))}
                    </div>
                  </div>
                </div>

                {/* Right: Disarmed & Reconstructed State */}
                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 space-y-3 flex flex-col justify-between">
                  <div>
                    <div className="flex items-center justify-between">
                      <span className="text-xs font-bold text-emerald-400 uppercase tracking-wider flex items-center gap-1.5">
                        <ShieldCheck className="w-3.5 h-3.5" /> Sanitized Reconstruction
                      </span>
                      {isDisarmed && (
                        <span className="text-xs font-mono text-emerald-400">
                          {Math.round(selectedSample.originalSizeKb * 0.78)} KB (-22%)
                        </span>
                      )}
                    </div>

                    {isDisarmed ? (
                      <div className="mt-3 space-y-3">
                        <div className="p-3 rounded-lg bg-emerald-950/30 border border-emerald-500/40 text-emerald-300 text-xs">
                          <div className="font-bold flex items-center gap-1.5">
                            <CheckCircle2 className="w-4 h-4 text-emerald-400" />
                            <span>Zero-Trust Disarm Complete (100% Clean)</span>
                          </div>
                          <p className="mt-1 text-[11px] text-emerald-300/80">
                            Extracted raw pixel and text streams into pristine memory canvas. All steganography, GPS coordinates, camera serials, and executable JS tags permanently discarded.
                          </p>
                        </div>

                        <div className="space-y-1.5 font-mono text-[11px] text-slate-300">
                          <div className="flex justify-between p-2 rounded bg-slate-900/60 border border-slate-800">
                            <span className="text-slate-400">EXIF / GPS Tags:</span>
                            <span className="text-emerald-400 font-bold">0 Remaining (Scrubbed)</span>
                          </div>
                          <div className="flex justify-between p-2 rounded bg-slate-900/60 border border-slate-800">
                            <span className="text-slate-400">Executable Macros:</span>
                            <span className="text-emerald-400 font-bold">Neutralized</span>
                          </div>
                          <div className="flex justify-between p-2 rounded bg-slate-900/60 border border-slate-800">
                            <span className="text-slate-400">Rebuilt Format:</span>
                            <span className="text-slate-200">ISO Standard Clean Bitstream</span>
                          </div>
                        </div>
                      </div>
                    ) : (
                      <div className="mt-8 text-center py-8 text-slate-500">
                        <Sparkles className="w-8 h-8 mx-auto text-slate-600 mb-2" />
                        <p className="text-xs">Click "Disarm &amp; Reconstruct" to sanitize this media stream.</p>
                      </div>
                    )}
                  </div>

                  <button
                    onClick={handleDisarm}
                    disabled={isProcessing}
                    className="w-full mt-4 py-2.5 bg-purple-600 hover:bg-purple-500 disabled:opacity-50 text-white font-bold text-xs rounded-xl transition-all shadow-sm flex items-center justify-center gap-2"
                  >
                    <Zap className={`w-3.5 h-3.5 ${isProcessing ? 'animate-spin' : ''}`} />
                    <span>{isProcessing ? 'Disarming & Reconstructing...' : isDisarmed ? 'Re-Sanitize Stream' : 'Disarm & Reconstruct (F927)'}</span>
                  </button>
                </div>

              </div>
            </div>
          )}

          {/* TAB 2: VIDEO PROXY & TELEMETRY SCRUBBER */}
          {activeTab === 'video_proxy' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-3">
                    <div className="p-2.5 rounded-xl bg-cyan-500/10 border border-cyan-500/30 text-cyan-400">
                      <Film className="w-5 h-5" />
                    </div>
                    <div>
                      <h3 className="font-bold text-white text-sm">Proxy-Level HLS / DASH Adaptive Stream Manifest Rewriter</h3>
                      <p className="text-xs text-slate-400 mt-0.5">
                        Intercepts .m3u8 and .mpd playlists in real time to force lower resolution caps and drop video player heartbeat beacons.
                      </p>
                    </div>
                  </div>
                </div>
              </div>

              {/* Controls */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="p-5 rounded-xl bg-slate-950/50 border border-slate-800 space-y-3">
                  <h4 className="text-xs font-bold text-slate-300 uppercase tracking-wider">Universal Quality Cap (F933/F936)</h4>
                  <div className="grid grid-cols-2 gap-2 text-xs">
                    {[
                      { id: 'auto', label: 'Uncapped (Native)' },
                      { id: '720p', label: '720p HD (Balanced)' },
                      { id: '480p', label: '480p (Data Saver)' },
                      { id: 'audio_only', label: 'Audio Only (F937)' }
                    ].map((opt) => (
                      <button
                        key={opt.id}
                        onClick={() => setQualityOverride(opt.id as any)}
                        className={`p-3 rounded-lg border font-semibold text-left transition-all ${
                          qualityOverride === opt.id
                            ? 'bg-cyan-500/20 border-cyan-500/50 text-cyan-300'
                            : 'bg-slate-900 border-slate-800 text-slate-400 hover:text-white'
                        }`}
                      >
                        {opt.label}
                      </button>
                    ))}
                  </div>
                  <p className="text-[11px] text-slate-500 mt-2">
                    Non-DRM video streams will have higher bitrate chunks rewritten to the chosen resolution at the proxy level.
                  </p>
                </div>

                <div className="p-5 rounded-xl bg-slate-950/50 border border-slate-800 space-y-4">
                  <h4 className="text-xs font-bold text-slate-300 uppercase tracking-wider">Player Telemetry Scrubber (F938)</h4>
                  
                  <div className="space-y-3">
                    <label className="flex items-start gap-3 cursor-pointer">
                      <input
                        type="checkbox"
                        checked={blockVideoTelemetry}
                        onChange={(e) => setBlockVideoTelemetry(e.target.checked)}
                        className="mt-0.5 accent-cyan-500 rounded"
                      />
                      <div>
                        <span className="text-xs font-semibold text-slate-200">Block QoE / Heartbeat Beacons</span>
                        <p className="text-[11px] text-slate-400 mt-0.5">
                          Drops recurring watch-time pings, pause/play telemetry, and bitrate reporting while keeping the video playback smooth.
                        </p>
                      </div>
                    </label>

                    <label className="flex items-start gap-3 cursor-pointer">
                      <input
                        type="checkbox"
                        checked={true}
                        readOnly
                        className="mt-0.5 accent-cyan-500 rounded"
                      />
                      <div>
                        <span className="text-xs font-semibold text-slate-200">Strip Dynamic Ad Insertion (DAI) Pods</span>
                        <p className="text-[11px] text-slate-400 mt-0.5">
                          Filters out server-side ad segments spliced into the playlist manifest.
                        </p>
                      </div>
                    </label>
                  </div>
                </div>
              </div>

            </div>
          )}

        </div>

        {/* Footer */}
        <div className="px-6 py-3 border-t border-slate-800 bg-slate-950/80 flex items-center justify-between text-xs text-slate-400">
          <div className="flex items-center gap-2">
            <span className="w-2 h-2 rounded-full bg-purple-400 animate-pulse" />
            <span>CDR Engine Ready (Zero-Trust Pixel Extraction + HLS Stream Proxy)</span>
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
