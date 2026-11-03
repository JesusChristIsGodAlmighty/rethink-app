import React, { useState } from 'react';
import { X, Search, Plus, Check, Clock } from 'lucide-react';
import { SerfModule } from '../types/serf';

interface TileGalleryModalProps {
  allModules: SerfModule[];
  activeModuleIds: string[];
  isOpen: boolean;
  onClose: () => void;
  onAddTile: (moduleId: string) => void;
  onInspectModule: (module: SerfModule) => void;
}

export const TileGalleryModal: React.FC<TileGalleryModalProps> = ({
  allModules,
  activeModuleIds,
  isOpen,
  onClose,
  onAddTile,
  onInspectModule
}) => {
  if (!isOpen) return null;

  const [search, setSearch] = useState('');
  const [selectedCategory, setSelectedCategory] = useState<string>('All');

  const categories = ['All', 'Core Engine', 'Firewall & Blocking', 'Routing & Privacy', 'Tools & Diagnostics', 'Mesh & Social', 'Developer & Advanced'];

  const filtered = allModules.filter((m) => {
    if (selectedCategory !== 'All' && m.category !== selectedCategory) return false;
    if (search.trim()) {
      const q = search.toLowerCase();
      return m.name.toLowerCase().includes(q) || m.id.toLowerCase().includes(q) || m.description.toLowerCase().includes(q);
    }
    return true;
  });

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-4xl h-[85vh] flex flex-col shadow-2xl overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="p-5 border-b border-slate-800 flex items-center justify-between gap-4">
          <div>
            <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
              <span>Tile Gallery (Section 4.11)</span>
              <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-emerald-400 font-bold border border-slate-700">
                850+ Catalog
              </span>
            </h2>
            <p className="text-xs text-slate-400">
              Browse foundational modules and F888 Coming Soon architectural components.
            </p>
          </div>

          <button
            onClick={onClose}
            className="p-1.5 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Filter Bar */}
        <div className="px-5 py-3 bg-slate-950/60 border-b border-slate-800 flex flex-wrap items-center justify-between gap-3">
          <div className="flex items-center gap-1.5 overflow-x-auto text-xs">
            {categories.map((cat) => (
              <button
                key={cat}
                onClick={() => setSelectedCategory(cat)}
                className={`px-3 py-1 rounded-md transition-colors whitespace-nowrap ${
                  selectedCategory === cat ? 'bg-emerald-500/20 text-emerald-300 font-medium border border-emerald-500/40' : 'text-slate-400 hover:text-white'
                }`}
              >
                {cat}
              </button>
            ))}
          </div>

          <div className="relative">
            <Search className="w-3.5 h-3.5 absolute left-2.5 top-2.5 text-slate-500" />
            <input
              type="text"
              placeholder="Filter by name, ID, or description..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              className="bg-slate-900 border border-slate-800 rounded-lg pl-8 pr-3 py-1.5 text-xs text-slate-200 placeholder-slate-500 focus:outline-none focus:border-emerald-500/50 w-64"
            />
          </div>
        </div>

        {/* Tiles List */}
        <div className="flex-1 p-6 overflow-y-auto grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
          {filtered.map((m) => {
            const isAdded = activeModuleIds.includes(m.id);
            return (
              <div
                key={m.id}
                className={`p-4 rounded-xl border flex flex-col justify-between transition-colors ${
                  m.isComingSoon
                    ? 'bg-slate-950/40 border-dashed border-slate-800'
                    : isAdded
                    ? 'bg-slate-900/40 border-slate-800'
                    : 'bg-slate-950/70 border-slate-800 hover:border-slate-700'
                }`}
              >
                <div>
                  <div className="flex items-center justify-between text-xs mb-1.5">
                    <span className="font-mono text-emerald-400 font-semibold">{m.id}</span>
                    {m.isComingSoon ? (
                      <span className="text-[10px] text-amber-400/90 flex items-center gap-1 font-medium">
                        <Clock className="w-3 h-3" />
                        <span>Coming Soon</span>
                      </span>
                    ) : (
                      <span className="text-[10px] text-slate-500">{m.category}</span>
                    )}
                  </div>
                  <h4 className="font-semibold text-sm text-slate-200 line-clamp-1">{m.name}</h4>
                  <p className="text-xs text-slate-400 line-clamp-2 mt-1 mb-3">{m.description}</p>
                </div>

                <div className="pt-2 border-t border-slate-800/60 flex items-center justify-between text-xs">
                  <button
                    onClick={() => onInspectModule(m)}
                    className="text-[11px] text-slate-400 hover:text-slate-200 underline decoration-slate-700 underline-offset-2"
                  >
                    View Lenses
                  </button>

                  {m.isComingSoon ? (
                    <span className="text-[11px] text-slate-500 italic">Tier Roadmap</span>
                  ) : isAdded ? (
                    <span className="text-[11px] text-emerald-400 font-medium flex items-center gap-1">
                      <Check className="w-3.5 h-3.5" />
                      <span>On Grid</span>
                    </span>
                  ) : (
                    <button
                      onClick={() => onAddTile(m.id)}
                      className="px-2.5 py-1 rounded-md bg-emerald-500 text-slate-950 font-semibold text-xs hover:bg-emerald-400 flex items-center gap-1 shadow-sm transition-all"
                    >
                      <Plus className="w-3.5 h-3.5" />
                      <span>Add Tile</span>
                    </button>
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
