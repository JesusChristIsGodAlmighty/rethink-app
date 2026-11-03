/**
 * SERF (Sovereign Network Control & Privacy) Type Definitions
 * Based on STATE.md v2 Master Blueprint
 */

export type TileSize = '1x1' | '2x2' | '4x2' | '4x4';

export type ModuleCategory =
  | 'Core Engine'
  | 'Firewall & Blocking'
  | 'Routing & Privacy'
  | 'Tools & Diagnostics'
  | 'Google Play Defense'
  | 'Device & System'
  | 'Mesh & Social'
  | 'Developer & Advanced';

export type LiveStatus = 'active' | 'degraded' | 'failed' | 'disabled';

export interface FiveLensesData {
  monitor: {
    title: string;
    metrics: { label: string; value: string; change?: string; timestamp?: number }[];
    statusNote: string;
  };
  detect: {
    threatsFound: number;
    lastDetected?: string;
    details: string[];
  };
  explain: {
    summary: string;
    mechanism: string;
    risksMitigated: string;
  };
  describe: {
    plainSummary: string; // Written for non-technical users
    whyItMatters: string;
    consequencesIfEnabled: string;
  };
  suggest: {
    recommendation: string;
    authorityLevel: 1 | 2 | 3 | 4 | 5; // Step on Authority Ladder
    actionLabel?: string;
    reversible: boolean;
  };
  history?: { timestamp: number; action: string; impact: string }[];
}

export interface SerfModule {
  id: string; // e.g. "F01"
  name: string;
  category: ModuleCategory;
  priority: 'P0' | 'P1' | 'P2' | 'P3' | 'P4' | 'P5' | 'P6';
  difficulty: 1 | 2 | 3 | 4 | 5;
  cost: 'L' | 'S';
  description: string;
  oneParagraphDoc: string; // Module Contract 4.2.6
  requiredPermissions: string[];
  breaksIfDisabled: string;
  enabled: boolean;
  isComingSoon?: boolean;
  comingSoonReason?: string;
  defaultTileSize: TileSize;
  currentTileSize?: TileSize;
  status: LiveStatus;
  resourceCost: {
    cpuPercent: number;
    batteryPerHourPercent: number;
    ramMb: number;
    dataSavedKb: number;
  };
  fiveLenses: FiveLensesData;
}

export interface NetworkPacket {
  id: string;
  timestamp: number;
  app: string;
  domain: string;
  destinationIp: string;
  protocol: 'DNS' | 'HTTPS' | 'HTTP' | 'DoH' | 'QUIC' | 'TCP' | 'UDP';
  status: 'allowed' | 'blocked' | 'faked' | 'delayed';
  reason?: string;
  ruleMatched?: string;
  payloadSize: number;
  isProbe?: boolean;
  isAdTracker?: boolean;
  leakRisk?: 'safe' | 'suspicious' | 'critical';
  simulatedResponse?: string;
}

export interface FieldOverride {
  key: string;
  label: string;
  category: 'Hardware' | 'Cellular' | 'App Identity' | 'Environment';
  currentRealValue: string;
  fakeValue: string;
  active: boolean;
  queriesCount: number;
  description: string;
}

export interface TransmissionLogEntry {
  id: string;
  timestamp: number;
  app: string;
  queriedField: string;
  valueReturned: string;
  wasSpoofed: boolean;
}

export interface ActiveProfile {
  id: string;
  name: string;
  tagline: string;
  icon: string;
  dnsResolver: string;
  fakeOnlineActive: boolean;
  firewallStrictness: 'Permissive' | 'Balanced' | 'Hardened' | 'Lockdown';
  torRouting: boolean;
  randomizeIdentities: boolean;
}
