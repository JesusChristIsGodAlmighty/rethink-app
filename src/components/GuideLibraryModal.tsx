import React, { useState } from 'react';
import { X, BookOpen, Shield, ChevronRight, Info } from 'lucide-react';
import { SERF_GUIDES, IN_APP_ABOUT, GuideItem } from '../data/guidesData';

interface GuideLibraryModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const GuideLibraryModal: React.FC<GuideLibraryModalProps> = ({
  isOpen,
  onClose
}) => {
  if (!isOpen) return null;

  const [selectedGuide, setSelectedGuide] = useState<GuideItem | null>(SERF_GUIDES[0]);
  const [showAbout, setShowAbout] = useState(false);

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
              <BookOpen className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>Section 12 How-To Guide Library</span>
              </h2>
              <p className="text-xs text-slate-400">
                Informational overviews of tools and architectures that extend beyond an individual app.
              </p>
            </div>
          </div>

          <div className="flex items-center gap-2">
            <button
              onClick={() => setShowAbout(!showAbout)}
              className="px-3 py-1.5 rounded-lg text-xs font-semibold bg-slate-800 hover:bg-slate-700 text-slate-200 transition-colors flex items-center gap-1.5"
            >
              <Info className="w-3.5 h-3.5 text-emerald-400" />
              <span>{showAbout ? 'View Guides' : 'About SERF'}</span>
            </button>

            <button
              onClick={onClose}
              className="p-1.5 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 transition-colors"
            >
              <X className="w-5 h-5" />
            </button>
          </div>
        </div>

        {/* Content Body */}
        {showAbout ? (
          <div className="p-8 overflow-y-auto space-y-6 text-sm max-w-2xl mx-auto">
            <div className="space-y-2 text-center">
              <div className="w-16 h-16 rounded-2xl bg-emerald-500/10 border border-emerald-500/30 text-emerald-400 flex items-center justify-center mx-auto mb-3 shadow-lg">
                <Shield className="w-8 h-8" />
              </div>
              <h3 className="text-2xl font-extrabold text-slate-100">{IN_APP_ABOUT.appName}</h3>
              <p className="text-sm font-medium text-emerald-400">{IN_APP_ABOUT.tagline}</p>
            </div>

            <div className="bg-slate-950 p-5 rounded-xl border border-slate-800 text-xs text-slate-300 leading-relaxed space-y-3">
              <p>{IN_APP_ABOUT.description}</p>
              <p>{IN_APP_ABOUT.philosophy}</p>
            </div>

            {/* F988 Legal Disclaimer */}
            <div className="p-4 rounded-xl bg-amber-950/20 border border-amber-500/30 text-xs text-amber-200 leading-relaxed">
              <div className="font-bold text-amber-400 uppercase tracking-wider text-[10px] mb-1">
                F988 Explicit Legal Disclaimer &amp; Acknowledgment
              </div>
              <p>{IN_APP_ABOUT.legalDisclaimer}</p>
            </div>
          </div>
        ) : (
          <div className="flex-1 grid grid-cols-1 md:grid-cols-12 overflow-hidden">
            {/* Guide List (4 cols) */}
            <div className="md:col-span-4 border-r border-slate-800 overflow-y-auto bg-slate-950/40 p-3 space-y-2">
              {SERF_GUIDES.map((guide) => {
                const isSelected = selectedGuide?.id === guide.id;
                return (
                  <div
                    key={guide.id}
                    onClick={() => setSelectedGuide(guide)}
                    className={`p-3.5 rounded-xl cursor-pointer transition-all border ${
                      isSelected
                        ? 'bg-emerald-500/10 border-emerald-500/30'
                        : 'bg-slate-900/60 border-slate-800 hover:border-slate-700'
                    }`}
                  >
                    <div className="flex items-center justify-between text-[10px] font-mono text-slate-500 mb-1">
                      <span>{guide.category}</span>
                      <span>{guide.readTime}</span>
                    </div>
                    <h4 className="font-bold text-xs text-slate-200 mb-1 line-clamp-1">{guide.title}</h4>
                    <p className="text-[11px] text-slate-400 line-clamp-2">{guide.subtitle}</p>
                  </div>
                );
              })}
            </div>

            {/* Guide Reader (8 cols) */}
            <div className="md:col-span-8 p-6 overflow-y-auto space-y-5 bg-slate-900/40">
              {selectedGuide ? (
                <div className="space-y-5">
                  <div>
                    <span className="text-[10px] font-mono px-2 py-0.5 rounded bg-slate-800 text-emerald-400 font-semibold">
                      {selectedGuide.category} · {selectedGuide.readTime}
                    </span>
                    <h3 className="text-xl font-bold text-slate-100 mt-2">{selectedGuide.title}</h3>
                    <p className="text-xs text-slate-400 mt-1">{selectedGuide.subtitle}</p>
                  </div>

                  <div className="prose prose-invert text-xs text-slate-300 leading-relaxed whitespace-pre-line bg-slate-950 p-5 rounded-xl border border-slate-800">
                    {selectedGuide.content}
                  </div>

                  <div>
                    <h4 className="text-xs font-semibold uppercase tracking-wider text-slate-400 mb-2">
                      Key Takeaways
                    </h4>
                    <ul className="space-y-1.5 text-xs text-slate-300">
                      {selectedGuide.keyTakeaways.map((k, i) => (
                        <li key={i} className="flex items-start gap-2">
                          <span className="text-emerald-400 font-bold">&bull;</span>
                          <span>{k}</span>
                        </li>
                      ))}
                    </ul>
                  </div>

                  <div className="p-3 bg-slate-950/40 rounded-lg border border-slate-800 text-[11px] text-slate-400">
                    <span className="font-semibold text-slate-300">Curiosity Search Queries:</span>{' '}
                    {selectedGuide.externalKeywords.join(', ')}
                  </div>
                </div>
              ) : null}
            </div>
          </div>
        )}
      </div>
    </div>
  );
};
