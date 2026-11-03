import React from 'react';
import { 
  Wifi, 
  Shield, 
  Terminal, 
  Sliders, 
  Cpu, 
  Activity, 
  Lock, 
  Globe, 
  Radio, 
  Bot, 
  MessageSquare, 
  Maximize2, 
  Minimize2, 
  CheckCircle2, 
  AlertCircle, 
  XCircle, 
  Info,
  Clock
} from 'lucide-react';
import { SerfModule, TileSize } from '../types/serf';

interface TileCardProps {
  module: SerfModule;
  isEditMode: boolean;
  onOpenFiveLenses: (module: SerfModule) => void;
  onToggleSize: (moduleId: string) => void;
  onToggleEnabled: (moduleId: string) => void;
  onRunSelfTest: (module: SerfModule) => void;
}

export const TileCard: React.FC<TileCardProps> = ({
  module,
  isEditMode,
  onOpenFiveLenses,
  onToggleSize,
  onToggleEnabled,
  onRunSelfTest
}) => {
  const size: TileSize = module.currentTileSize || module.defaultTileSize;

  // Icon mapping
  const renderIcon = () => {
    switch (module.id) {
      case 'F01': return <Wifi className="w-5 h-5 text-emerald-400" />;
      case 'F04': return <ZapIcon className="w-5 h-5 text-amber-400" />;
      case 'F10': return <Shield className="w-5 h-5 text-sky-400" />;
      case 'F675': return <Activity className="w-5 h-5 text-teal-400" />;
      case 'F662': return <Sliders className="w-5 h-5 text-violet-400" />;
      case 'F125': return <Globe className="w-5 h-5 text-indigo-400" />;
      case 'F212': return <Terminal className="w-5 h-5 text-emerald-400" />;
      case 'F424': return <Cpu className="w-5 h-5 text-rose-400" />;
      case 'F884': return <Lock className="w-5 h-5 text-emerald-400" />;
      case 'F578': return <Radio className="w-5 h-5 text-slate-400" />;
      case 'F323': return <Bot className="w-5 h-5 text-slate-400" />;
      case 'F848': return <MessageSquare className="w-5 h-5 text-slate-400" />;
      default: return <Shield className="w-5 h-5 text-slate-400" />;
    }
  };

  // Status indicator
  const renderStatus = () => {
    if (module.isComingSoon) {
      return (
        <span className="text-[11px] font-medium text-amber-400/90 flex items-center gap-1">
          <Clock className="w-3 h-3" />
          <span>Coming Soon</span>
        </span>
      );
    }
    if (!module.enabled) {
      return <span className="text-[11px] text-slate-500 font-medium">Off</span>;
    }
    switch (module.status) {
      case 'active':
        return (
          <span className="text-[11px] font-medium text-emerald-400 flex items-center gap-1">
            <CheckCircle2 className="w-3 h-3 text-emerald-400" />
            <span>Active</span>
          </span>
        );
      case 'degraded':
        return (
          <span className="text-[11px] font-medium text-amber-400 flex items-center gap-1">
            <AlertCircle className="w-3 h-3 text-amber-400" />
            <span>Degraded</span>
          </span>
        );
      case 'failed':
        return (
          <span className="text-[11px] font-medium text-rose-400 flex items-center gap-1">
            <XCircle className="w-3 h-3 text-rose-400" />
            <span>Failed</span>
          </span>
        );
      default:
        return null;
    }
  };

  // Size grid classes for 8-column layout
  // 1x1: col-span-2 on mobile, col-span-1 on desktop
  // 2x2: col-span-4 on mobile, col-span-2 on desktop
  // 4x2: col-span-8 on mobile, col-span-4 on desktop
  // 4x4: col-span-8 (full width)
  const getGridSpan = () => {
    switch (size) {
      case '1x1':
        return 'col-span-4 sm:col-span-2 row-span-1 min-h-[100px]';
      case '2x2':
        return 'col-span-8 sm:col-span-4 md:col-span-2 row-span-2 min-h-[180px]';
      case '4x2':
        return 'col-span-8 sm:col-span-8 md:col-span-4 row-span-2 min-h-[180px]';
      case '4x4':
        return 'col-span-8 row-span-4 min-h-[340px]';
    }
  };

  return (
    <div
      className={`relative group rounded-xl border transition-all duration-200 flex flex-col justify-between p-4 ${
        getGridSpan()
      } ${
        module.isComingSoon
          ? 'bg-slate-900/30 border-dashed border-slate-800 opacity-80'
          : module.enabled
          ? 'bg-slate-900/90 border-slate-800 hover:border-slate-700 hover:shadow-lg hover:shadow-black/40'
          : 'bg-slate-950/60 border-slate-900 opacity-60'
      } ${isEditMode ? 'animate-[pulse_3s_ease-in-out_infinite] ring-1 ring-emerald-500/30' : ''}`}
    >
      {/* Top Header */}
      <div>
        <div className="flex items-start justify-between gap-2 mb-2">
          <div className="flex items-center gap-2.5">
            <div className="p-2 rounded-lg bg-slate-800/80 border border-slate-700/60 shrink-0">
              {renderIcon()}
            </div>
            <div>
              <div className="flex items-center gap-2">
                <span className="font-mono text-[10px] text-slate-500 font-semibold">{module.id}</span>
                <span aria-hidden="true" className="text-slate-700 text-xs">·</span>
                {renderStatus()}
              </div>
              <h3 className="font-semibold text-sm text-slate-100 group-hover:text-emerald-300 transition-colors line-clamp-1">
                {module.name}
              </h3>
            </div>
          </div>

          {/* Edit controls if in Edit Mode */}
          {isEditMode ? (
            <div className="flex items-center gap-1">
              <button
                onClick={(e) => { e.stopPropagation(); onToggleSize(module.id); }}
                title="Cycle Tile Size (1x1, 2x2, 4x2, 4x4)"
                className="p-1 rounded bg-slate-800 text-slate-300 hover:text-white"
              >
                {size === '1x1' ? <Maximize2 className="w-3.5 h-3.5" /> : <Minimize2 className="w-3.5 h-3.5" />}
              </button>
            </div>
          ) : (
            <button
              onClick={(e) => { e.stopPropagation(); onOpenFiveLenses(module); }}
              title="Inspect with Five Lenses (F404)"
              className="text-slate-500 hover:text-slate-200 p-1 rounded hover:bg-slate-800/60 transition-colors"
            >
              <Info className="w-4 h-4" />
            </button>
          )}
        </div>

        {/* Content based on size */}
        {size !== '1x1' && (
          <p className="text-xs text-slate-400 line-clamp-2 mt-1 mb-3">
            {module.description}
          </p>
        )}

        {/* 2x2 or 4x2 Metrics */}
        {(size === '2x2' || size === '4x2') && !module.isComingSoon && (
          <div className="grid grid-cols-2 gap-2 mt-2 pt-2 border-t border-slate-800/60">
            {module.fiveLenses.monitor.metrics.slice(0, 2).map((metric, i) => (
              <div key={i} className="flex flex-col">
                <span className="text-[10px] text-slate-500 font-medium truncate">{metric.label}</span>
                <span className="font-mono font-semibold text-xs text-slate-200 tabular-nums">
                  {metric.value}
                </span>
                {metric.change && (
                  <span className="text-[10px] text-emerald-400/80 font-mono">{metric.change}</span>
                )}
              </div>
            ))}
          </div>
        )}

        {/* 4x4 Tall view: Internal scrolling monitor stream */}
        {size === '4x4' && (
          <div className="mt-3 pt-3 border-t border-slate-800/80 flex-1">
            <div className="flex items-center justify-between text-xs text-slate-400 mb-2">
              <span className="font-semibold text-slate-300">Live Engine Diagnostics & Rules</span>
              <span className="font-mono text-[10px] text-emerald-400">Stream Active</span>
            </div>
            <div className="bg-slate-950/80 rounded-lg p-3 font-mono text-xs text-slate-300 space-y-1.5 max-h-48 overflow-y-auto border border-slate-800/60">
              <div className="text-slate-500">// Five Lenses Monitor Output (F404):</div>
              <div className="text-emerald-400">&gt; Status: {module.fiveLenses.monitor.statusNote}</div>
              {module.fiveLenses.detect.details.map((d, idx) => (
                <div key={idx} className="text-slate-300">
                  <span className="text-amber-400/90">&bull;</span> {d}
                </div>
              ))}
              <div className="text-slate-400 pt-1 border-t border-slate-900 text-[11px]">
                Authority Level: {module.fiveLenses.suggest.authorityLevel} (Observe &amp; Suggest)
              </div>
            </div>
          </div>
        )}

        {/* Coming soon note */}
        {module.isComingSoon && (
          <div className="mt-2 p-2 rounded bg-slate-950/50 border border-slate-800/60 text-[11px] text-slate-400">
            <div className="font-medium text-slate-300 mb-0.5">F888 Architectural Blueprint:</div>
            <div>{module.comingSoonReason}</div>
          </div>
        )}
      </div>

      {/* Bottom Actions Bar */}
      <div className="mt-3 pt-2.5 border-t border-slate-800/60 flex items-center justify-between text-xs">
        <div className="flex items-center gap-2">
          {!module.isComingSoon && (
            <button
              onClick={() => onToggleEnabled(module.id)}
              className={`px-2 py-0.5 rounded text-[11px] font-medium transition-colors ${
                module.enabled
                  ? 'bg-emerald-500/10 text-emerald-400 border border-emerald-500/30 hover:bg-emerald-500/20'
                  : 'bg-slate-800 text-slate-400 hover:text-slate-200'
              }`}
            >
              {module.enabled ? 'Enabled' : 'Disabled'}
            </button>
          )}

          {!module.isComingSoon && module.enabled && (
            <button
              onClick={() => onRunSelfTest(module)}
              title="Is It Actually Working (F426) Self-Test"
              className="text-[11px] text-slate-400 hover:text-slate-200 underline decoration-slate-700 underline-offset-2"
            >
              Self-Test
            </button>
          )}
        </div>

        <button
          onClick={() => onOpenFiveLenses(module)}
          className="text-[11px] font-medium text-slate-400 hover:text-emerald-400 transition-colors flex items-center gap-1"
        >
          <span>Five Lenses</span>
          <span aria-hidden="true">&rarr;</span>
        </button>
      </div>
    </div>
  );
};

function ZapIcon(props: React.SVGProps<SVGSVGElement>) {
  return (
    <svg viewBox="0 0 24 24" width="20" height="20" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round" {...props}>
      <polygon points="13 2 3 14 12 14 11 22 21 10 12 10 13 2" />
    </svg>
  );
}
