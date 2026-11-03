import React from 'react';
import { Plus } from 'lucide-react';
import { SerfModule } from '../types/serf';
import { TileCard } from './TileCard';

interface TileGridProps {
  modules: SerfModule[];
  isEditMode: boolean;
  onOpenTileGallery: () => void;
  onOpenFiveLenses: (module: SerfModule) => void;
  onToggleSize: (moduleId: string) => void;
  onToggleEnabled: (moduleId: string) => void;
  onRunSelfTest: (module: SerfModule) => void;
}

export const TileGrid: React.FC<TileGridProps> = ({
  modules,
  isEditMode,
  onOpenTileGallery,
  onOpenFiveLenses,
  onToggleSize,
  onToggleEnabled,
  onRunSelfTest
}) => {
  return (
    <div className="w-full">
      {/* 8-wide logical grid per Section 4.11 spec */}
      <div className="grid grid-cols-8 gap-4 auto-rows-max">
        {modules.map((mod) => (
          <TileCard
            key={mod.id}
            module={mod}
            isEditMode={isEditMode}
            onOpenFiveLenses={onOpenFiveLenses}
            onToggleSize={onToggleSize}
            onToggleEnabled={onToggleEnabled}
            onRunSelfTest={onRunSelfTest}
          />
        ))}

        {/* The '+' Tile from Section 4.11 locked spec to open Tile Gallery */}
        <div
          onClick={onOpenTileGallery}
          className="col-span-4 sm:col-span-4 md:col-span-2 min-h-[180px] rounded-xl border border-dashed border-slate-800 hover:border-emerald-500/50 bg-slate-950/40 hover:bg-slate-900/40 transition-all cursor-pointer flex flex-col items-center justify-center p-6 text-center group"
        >
          <div className="w-12 h-12 rounded-full bg-slate-900 border border-slate-800 flex items-center justify-center text-slate-400 group-hover:text-emerald-400 group-hover:border-emerald-500/40 group-hover:scale-105 transition-all mb-3 shadow-inner">
            <Plus className="w-6 h-6" />
          </div>
          <span className="font-semibold text-sm text-slate-300 group-hover:text-white transition-colors">
            Add Tile from Gallery
          </span>
          <span className="text-xs text-slate-500 mt-1">
            Browse 850+ features &amp; F888 upcoming modules
          </span>
        </div>
      </div>
    </div>
  );
};
