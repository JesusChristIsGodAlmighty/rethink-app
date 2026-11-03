import React from 'react';
import { 
  Shield, 
  Sparkles, 
  Sliders, 
  Smartphone, 
  ListFilter, 
  Wrench, 
  ShieldAlert, 
  Wifi, 
  Store, 
  History, 
  HelpCircle,
  Shuffle,
  Radio,
  ShieldCheck,
  Layers,
  Scale
} from 'lucide-react';
import { ActiveProfile } from '../types/serf';

interface HeaderProps {
  activeTab: 'home' | 'traffic' | 'overrides' | 'guides' | 'fork';
  setActiveTab: (tab: 'home' | 'traffic' | 'overrides' | 'guides' | 'fork') => void;
  activeProfile: ActiveProfile;
  onOpenProfiles: () => void;
  onOpenSettings: () => void;
  onOpenOfflineTester: () => void;
  onOpenBlocklists: () => void;
  onOpenPrivacyTools: () => void;
  onOpenBlockPagePreview: () => void;
  onOpenFakeOnlineStudio: () => void;
  onOpenGoogleDefense: () => void;
  onOpenTimeMachine: () => void;
  onOpenOnboarding: () => void;
  onOpenFirewallDepth: () => void;
  onOpenPrivacyRouting: () => void;
  onOpenHardwareRadioShield: () => void;
  onOpenZeroTrustCompliance: () => void;
  onOpenContentDisarm: () => void;
  onOpenLegalDisclaimer: () => void;
  isExpertMode: boolean;
  onToggleExpertMode: () => void;
  isEditMode: boolean;
  onToggleEditMode: () => void;
}

export const Header: React.FC<HeaderProps> = ({
  activeTab,
  setActiveTab,
  activeProfile,
  onOpenProfiles,
  onOpenSettings,
  onOpenOfflineTester,
  onOpenBlocklists,
  onOpenPrivacyTools,
  onOpenBlockPagePreview,
  onOpenFakeOnlineStudio,
  onOpenGoogleDefense,
  onOpenTimeMachine,
  onOpenOnboarding,
  onOpenFirewallDepth,
  onOpenPrivacyRouting,
  onOpenHardwareRadioShield,
  onOpenZeroTrustCompliance,
  onOpenContentDisarm,
  onOpenLegalDisclaimer,
  isExpertMode,
  onToggleExpertMode,
  isEditMode,
  onToggleEditMode
}) => {

  return (
    <header className="border-b border-slate-800/80 bg-slate-950/90 backdrop-blur-md sticky top-0 z-30">
      {/* Zone 1, 2, 3: Top Bar Contract */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 h-16 flex items-center justify-between gap-6 sm:gap-8">
        
        {/* Zone 1: Brand Wordmark (Single text element) */}
        <div className="flex items-center gap-3 shrink-0">
          <a
            href="#home"
            onClick={(e) => { e.preventDefault(); setActiveTab('home'); }}
            className="flex items-center gap-2 group text-white font-extrabold text-xl tracking-tight whitespace-nowrap shrink-0"
          >
            <div className="w-8 h-8 rounded-lg bg-emerald-500/10 border border-emerald-500/30 flex items-center justify-center text-emerald-400 group-hover:bg-emerald-500/20 transition-colors">
              <Shield className="w-4 h-4" />
            </div>
            <span>SERF</span>
          </a>
        </div>

        {/* Zone 2: 4-5 Concise Single-Line Navigation Links */}
        <nav className="hidden md:flex items-center gap-6 text-sm font-medium text-slate-300">
          <button
            onClick={() => setActiveTab('home')}
            className={`transition-colors whitespace-nowrap shrink-0 ${activeTab === 'home' ? 'text-emerald-400 font-semibold' : 'hover:text-white'}`}
          >
            Home Tiles
          </button>
          <button
            onClick={() => setActiveTab('traffic')}
            className={`transition-colors whitespace-nowrap shrink-0 ${activeTab === 'traffic' ? 'text-emerald-400 font-semibold' : 'hover:text-white'}`}
          >
            Traffic Interpreter
          </button>
          <button
            onClick={() => setActiveTab('overrides')}
            className={`transition-colors whitespace-nowrap shrink-0 ${activeTab === 'overrides' ? 'text-emerald-400 font-semibold' : 'hover:text-white'}`}
          >
            Field Overrides
          </button>
          <button
            onClick={() => setActiveTab('guides')}
            className={`transition-colors whitespace-nowrap shrink-0 ${activeTab === 'guides' ? 'text-emerald-400 font-semibold' : 'hover:text-white'}`}
          >
            How-To Guides
          </button>
          <button
            onClick={() => setActiveTab('fork')}
            className={`transition-colors whitespace-nowrap shrink-0 ${activeTab === 'fork' ? 'text-emerald-400 font-semibold' : 'hover:text-white'}`}
          >
            Fork & CI Setup
          </button>
        </nav>

        {/* Zone 3: 1 Primary Action (Test Offline App Launch) */}
        <div className="flex items-center gap-2 shrink-0">
          <button
            onClick={onOpenOfflineTester}
            className="px-3 py-1.5 text-xs font-semibold rounded-lg bg-emerald-500 text-slate-950 hover:bg-emerald-400 active:scale-98 transition-all flex items-center gap-1.5 shadow-sm shadow-emerald-500/20 whitespace-nowrap shrink-0"
          >
            <Smartphone className="w-3.5 h-3.5" />
            <span>Test Offline Launch</span>
          </button>

          <button
            onClick={onOpenOnboarding}
            title="Open Guided Setup Wizard (F186)"
            className="p-1.5 text-slate-400 hover:text-white rounded-lg hover:bg-slate-800 transition-colors"
          >
            <HelpCircle className="w-4 h-4" />
          </button>
        </div>
      </div>

      {/* Sub-toolbar: Quick-Access Tier 1 & Tier 2 Controls */}
      <div className="bg-slate-900/60 border-t border-slate-800/60 px-4 sm:px-6 py-2 text-xs text-slate-400 flex flex-wrap items-center justify-between gap-3">
        <div className="flex items-center gap-2.5 overflow-x-auto">
          <button
            onClick={onOpenProfiles}
            className="flex items-center gap-1.5 hover:text-slate-200 transition-colors shrink-0"
          >
            <span className="text-slate-500">Profile:</span>
            <span className="font-semibold text-slate-200 underline decoration-slate-600 underline-offset-4">{activeProfile.name}</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>
          
          {/* Tier 2 Core Engine Triggers */}
          <button
            onClick={onOpenFakeOnlineStudio}
            className="flex items-center gap-1 hover:text-emerald-300 transition-colors shrink-0"
          >
            <Wifi className="w-3 h-3 text-emerald-400" />
            <span>Fake-Online Studio (F01–F07)</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>

          <button
            onClick={onOpenGoogleDefense}
            className="flex items-center gap-1 hover:text-indigo-300 transition-colors shrink-0"
          >
            <Store className="w-3 h-3 text-indigo-400" />
            <span>Source Doctor &amp; Play Defense (F96/F97)</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>

          <button
            onClick={onOpenTimeMachine}
            className="flex items-center gap-1 hover:text-amber-300 transition-colors shrink-0"
          >
            <History className="w-3 h-3 text-amber-400" />
            <span>Update Time Machine (F228)</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>

          {/* Tier 3 Advanced Firewall & Soul Suite */}
          <button
            onClick={onOpenFirewallDepth}
            className="flex items-center gap-1 text-cyan-400 hover:text-cyan-300 font-semibold transition-colors shrink-0 bg-cyan-950/40 px-2 py-0.5 rounded border border-cyan-500/30"
          >
            <Shield className="w-3 h-3 text-cyan-400" />
            <span>Firewall Depth &amp; Soul (Tier 3)</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>

          {/* Tier 4 Privacy & Routing Core */}
          <button
            onClick={onOpenPrivacyRouting}
            className="flex items-center gap-1 text-indigo-400 hover:text-indigo-300 font-semibold transition-colors shrink-0 bg-indigo-950/40 px-2 py-0.5 rounded border border-indigo-500/30"
          >
            <Shuffle className="w-3 h-3 text-indigo-400" />
            <span>Privacy &amp; Routing (Tier 4)</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>

          {/* Tier 5 Telephony, Radio & Hardware Shields */}
          <button
            onClick={onOpenHardwareRadioShield}
            className="flex items-center gap-1 text-emerald-400 hover:text-emerald-300 font-semibold transition-colors shrink-0 bg-emerald-950/40 px-2 py-0.5 rounded border border-emerald-500/30"
          >
            <Radio className="w-3 h-3 text-emerald-400" />
            <span>Radio &amp; Hardware (Tier 5)</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>

          {/* Tier 6 Zero-Trust, Compliance & Export Studio */}
          <button
            onClick={onOpenZeroTrustCompliance}
            className="flex items-center gap-1 text-cyan-400 hover:text-cyan-300 font-semibold transition-colors shrink-0 bg-cyan-950/40 px-2 py-0.5 rounded border border-cyan-500/30"
          >
            <ShieldCheck className="w-3 h-3 text-cyan-400" />
            <span>Zero Trust &amp; Export (Tier 6)</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>

          {/* F927-F938 Content Disarm & Reconstruction Studio */}
          <button
            onClick={onOpenContentDisarm}
            className="flex items-center gap-1 text-purple-400 hover:text-purple-300 font-semibold transition-colors shrink-0 bg-purple-950/40 px-2 py-0.5 rounded border border-purple-500/30"
          >
            <Layers className="w-3 h-3 text-purple-400" />
            <span>CDR &amp; Media Proxy (F927)</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>

          {/* Quick-Access Tier 1 Modals */}
          <button
            onClick={onOpenBlocklists}
            className="flex items-center gap-1 hover:text-sky-300 transition-colors shrink-0"
          >
            <ListFilter className="w-3 h-3 text-sky-400" />
            <span>Blocklists</span>
          </button>
          <span aria-hidden="true" className="text-slate-700">·</span>

          <button
            onClick={onOpenPrivacyTools}
            className="flex items-center gap-1 hover:text-emerald-300 transition-colors shrink-0"
          >
            <Wrench className="w-3 h-3 text-emerald-400" />
            <span>Privacy Tools</span>
          </button>
        </div>

        <div className="flex items-center gap-3 shrink-0">
          {/* F988 Legal Boundary & Responsibility Notice */}
          <button
            onClick={onOpenLegalDisclaimer}
            className="flex items-center gap-1 text-amber-400 hover:text-amber-300 transition-colors text-xs font-semibold"
            title="Sovereign Responsibility Notice (Section 15 / F988)"
          >
            <Scale className="w-3.5 h-3.5" />
            <span>Notice (F988)</span>
          </button>

          {/* F94 Tiered UI (Newbie vs Expert) */}
          <button
            onClick={onToggleExpertMode}
            className={`flex items-center gap-1.5 px-2.5 py-1 rounded transition-colors ${isExpertMode ? 'bg-slate-800 text-emerald-400 border border-emerald-500/30' : 'text-slate-400 hover:text-slate-200'}`}
          >
            <Sparkles className="w-3.5 h-3.5" />
            <span>{isExpertMode ? 'Expert View' : 'Simple View'}</span>
          </button>

          {/* Customize Grid (Jiggle / Resize Mode) */}
          <button
            onClick={onToggleEditMode}
            className={`flex items-center gap-1.5 px-2.5 py-1 rounded transition-colors ${isEditMode ? 'bg-emerald-500/20 text-emerald-300 border border-emerald-500/40' : 'text-slate-400 hover:text-slate-200'}`}
          >
            <Sliders className="w-3.5 h-3.5" />
            <span>{isEditMode ? 'Lock Grid' : 'Edit Tiles'}</span>
          </button>

          <button
            onClick={onOpenSettings}
            className="hover:text-slate-200 transition-colors"
          >
            Settings
          </button>
        </div>
      </div>
    </header>
  );
};
