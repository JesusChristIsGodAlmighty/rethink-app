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

export type TorBridgeType = 'direct' | 'obfs4' | 'snowflake' | 'meek';

export interface TorRelayNode {
  nickname: string;
  ip: string;
  countryCode: string;
  countryName: string;
  fingerprint: string;
  flags: string[];
  bandwidthKbps: number;
}

export interface TorCircuit {
  id: string;
  status: 'building' | 'ready' | 'rotating' | 'failed';
  guardNode: TorRelayNode;
  middleNode: TorRelayNode;
  exitNode: TorRelayNode;
  latencyMs: number;
  streamCount: number;
  bytesTransferred: number;
  bridgeTransport: TorBridgeType;
  socksProxyAddress: string;
  controlPort: number;
}

export type SplitTunnelMode = 'DIRECT' | 'VPN' | 'TOR' | 'LOOPBACK_STUB' | 'BLOCK';

export interface SplitTunnelAppRule {
  packageName: string;
  appName: string;
  icon: string;
  category: 'Browser' | 'Social' | 'Banking' | 'System' | 'Game' | 'Media';
  routingMode: SplitTunnelMode;
  bytesRouted: number;
  packetsCount: number;
}

export interface LocationFuzzConfig {
  enabled: boolean;
  mode: 'jitter' | 'fixed_spoof';
  jitterRadiusMeters: number;
  spoofPreset: string;
  spoofCoordinates: {
    lat: number;
    lng: number;
    altitude: number;
    timezone: string;
    label: string;
  };
  timezoneSync: boolean;
  stripBssidTriangulation: boolean;
}

export interface DecoyTrafficConfig {
  enabled: boolean;
  mode: 'poisson' | 'constant_jitter' | 'burst';
  intervalSeconds: number;
  paddingEnabled: boolean;
  targetPool: string[];
  totalDecoysSent: number;
  totalDecoyBytes: number;
}

export interface FingerprintShieldConfig {
  canvasNoise: boolean;
  webGlDisguise: boolean;
  audioNoise: boolean;
  userAgentDisguise: boolean;
  webrtcCloaking: boolean;
  currentEntropyBits: number;
  uniquenessScore: number;
}

// ==========================================
// TIER 5: TELEPHONY, RADIO & HARDWARE SHIELDS
// ==========================================

export interface ImsiCatcherDetection {
  cellId: number;
  mcc: number;
  mnc: number;
  lac: number;
  signalStrengthDbm: number;
  anomalyScore: number; // 0 - 100
  encryptionCipher: 'A5/0 (Unencrypted/Hostile)' | 'A5/1 (Weak/Vulnerable)' | 'A5/3 (Kasumi/Secure)' | 'EEA2/AES (LTE/5G)';
  silentSmsCount: number;
  isStingraySuspected: boolean;
  neighborCellMismatch: boolean;
  unsolicitedPagingActive: boolean;
  lastThreatTimestamp?: number;
  towerHistory: Array<{
    cellId: number;
    lac: number;
    signalStrength: number;
    cipher: string;
    flag: string;
    timestamp: number;
  }>;
}

export interface WifiProbeShieldConfig {
  enabled: boolean;
  macRotationIntervalMin: number;
  suppressDirectedProbes: boolean;
  randomizeSequenceNumbers: boolean;
  stripIeFingerprint: boolean;
  currentVirtualMac: string;
  realHardwareMac: string;
  probesBlindedCount: number;
  macRotationHistory: Array<{
    mac: string;
    timestamp: number;
    reason: string;
  }>;
}

export interface BleTrackerDevice {
  id: string;
  name: string;
  mac: string;
  rssi: number;
  proximity: 'Immediate (<1m)' | 'Near (1-3m)' | 'Far (>3m)';
  firstSeen: number;
  lastSeen: number;
  isFollowingUser: boolean;
  threatScore: number;
}

export interface BleCloakConfig {
  enabled: boolean;
  randomizeBleMac: boolean;
  antiTrackerSweeperEnabled: boolean;
  suppressBeaconBroadcast: boolean;
  detectedTrackers: BleTrackerDevice[];
  trackersBlockedCount: number;
}

export interface HardwareSensorsGuard {
  micHardwareCutoff: boolean;
  cameraHardwareLock: boolean;
  ultrasonicShieldActive: boolean;
  ultrasonicBeaconsNeutralized: number;
  motionSensorRateLimit: boolean;
  lightSensorBlinding: boolean;
}

export interface BasebandRilAudit {
  emergencyRadioKillActive: boolean;
  firmwareHashStatus: 'VERIFIED' | 'TAMPERED' | 'CHECKING';
  firmwareChecksum: string;
  atCommandLog: Array<{
    id: string;
    timestamp: number;
    command: string;
    response: string;
    riskLevel: 'benign' | 'suspicious' | 'hostile';
    details: string;
  }>;
  silentSmsLog: Array<{
    id: string;
    timestamp: number;
    sender: string;
    smsType: 'Type-0 (Silent Ping)' | 'MWI (Message Waiting)' | 'Flash SMS';
    payloadHex: string;
    actionTaken: 'DROPPED_AND_LOGGED' | 'QUARANTINED';
  }>;
}

// ==========================================
// TIER 6: ZERO TRUST, COMPLIANCE & EXPORT
// ==========================================

export interface ZeroTrustComplianceStandard {
  id: string;
  name: string;
  passedRules: number;
  totalRules: number;
  status: 'COMPLIANT' | 'WARNING' | 'FAIL';
}

export interface ZeroTrustPeerNode {
  nodeId: string;
  name: string;
  role: 'Fleet Controller' | 'Peer Sentinel' | 'Backup Bastion';
  address: string;
  status: 'ONLINE' | 'OFFLINE' | 'SYNCING';
  lastSync: number;
}

export interface ZeroTrustPosture {
  deviceHealthScore: number;
  rootIntegrity: 'SECURE_UNROOTED' | 'ROOT_DETECTED' | 'CUSTOM_ROM_ATTESTED';
  selinuxMode: 'Enforcing' | 'Permissive' | 'Disabled';
  patchLevel: string;
  trustTier: 'Tier 0: Untrusted' | 'Tier 1: Basic' | 'Tier 2: Hardened Sovereign' | 'Tier 3: Air-Gapped High Assurance';
  complianceStandards: ZeroTrustComplianceStandard[];
  syncPeerNodes: ZeroTrustPeerNode[];
}

export interface ExportImportConfig {
  exportProfiles: boolean;
  exportFirewallRules: boolean;
  exportDnsPresets: boolean;
  exportHardwarePolicies: boolean;
  encryptionPassphrase: string;
  exportFormat: 'JSON' | 'YAML' | 'ENCRYPTED_SERF_BUNDLE' | 'SECURE_QR';
}

