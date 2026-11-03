import { NetworkPacket } from '../types/serf';

export interface SimulatorState {
  airplaneMode: boolean;
  fakeOnlineActive: boolean;
  probeResponderActive: boolean;
  activeFilterStrictness: 'Permissive' | 'Balanced' | 'Hardened' | 'Lockdown';
  packets: NetworkPacket[];
  stats: {
    totalPackets: number;
    blockedPackets: number;
    fakedPackets: number;
    probesHandled: number;
    dataSavedKb: number;
  };
}

const APPS_LIST = [
  'Subway Surfers',
  'Duolingo',
  'Spotify',
  'Weather Live',
  'Chrome',
  'System Framework',
  'Instagram',
  'TikTok',
  'Adobe Acrobat',
  'Play Store'
];

const PROBE_ENDPOINTS = [
  { domain: 'connectivitycheck.gstatic.com', path: '/generate_204', desc: 'Android Captive Portal Check' },
  { domain: 'connectivitycheck.android.com', path: '/generate_204', desc: 'AOSP Network Monitor' },
  { domain: 'captive.apple.com', path: '/hotspot-detect.html', desc: 'Apple WISPr Probe' },
  { domain: 'msftconnecttest.com', path: '/connecttest.txt', desc: 'Windows/Cross-Platform Network Test' }
];

const TRACKER_DOMAINS = [
  'app-measurement.com',
  'graph.instagram.com',
  'telemetry.applovin.com',
  'adservice.google.com',
  'pixel.facebook.com',
  'analytics.tiktok.com',
  'crashlyticsreports-pa.googleapis.com',
  'sdk.unityads.unity3d.com',
  'data.flurry.com'
];

const BENIGN_DOMAINS = [
  'api.github.com',
  'en.wikipedia.org',
  'cloudflare-dns.com',
  'dns.quad9.net',
  'weather-data.noaa.gov',
  'f-droid.org'
];

export function createRandomPacket(state: SimulatorState): NetworkPacket {
  const isProbe = Math.random() < 0.25;
  const isTracker = !isProbe && Math.random() < 0.55;
  const id = `pkt-${Date.now()}-${Math.floor(Math.random() * 1000)}`;
  const timestamp = Date.now();

  if (isProbe) {
    const probe = PROBE_ENDPOINTS[Math.floor(Math.random() * PROBE_ENDPOINTS.length)];
    const app = 'System Framework';
    
    // If fake online or probe responder is active, intercept!
    const intercepted = state.fakeOnlineActive || state.probeResponderActive;

    return {
      id,
      timestamp,
      app,
      domain: probe.domain,
      destinationIp: '142.250.190.46',
      protocol: 'HTTP',
      status: intercepted ? 'faked' : state.airplaneMode ? 'blocked' : 'allowed',
      reason: intercepted
        ? 'Intercepted by F01 Fake-Online: Generated synthetic HTTP 204 No Content'
        : state.airplaneMode
        ? 'Dropped: Device in Airplane Mode without synthetic responder'
        : 'Pass-through to upstream',
      ruleMatched: intercepted ? 'F01-PROBE-RESPONDER' : undefined,
      payloadSize: 184,
      isProbe: true,
      simulatedResponse: intercepted ? 'HTTP/1.1 204 No Content\r\nConnection: close' : undefined
    };
  }

  if (isTracker) {
    const domain = TRACKER_DOMAINS[Math.floor(Math.random() * TRACKER_DOMAINS.length)];
    const app = APPS_LIST[Math.floor(Math.random() * (APPS_LIST.length - 2))]; // Non-system
    const fastFail = state.fakeOnlineActive;

    return {
      id,
      timestamp,
      app,
      domain,
      destinationIp: '104.18.32.7',
      protocol: 'HTTPS',
      status: fastFail ? 'faked' : 'blocked',
      reason: fastFail
        ? 'F04 Fast-Fail Stub: Returned instant blank JSON {} to prevent UI hang'
        : 'Blocked by F10 uBlock-Grade Radix Rule (EasyPrivacy / OISD)',
      ruleMatched: fastFail ? 'F04-STUB-SYNTHESIS' : 'F10-BLOCK-RADIX',
      payloadSize: 520,
      isAdTracker: true,
      leakRisk: 'suspicious',
      simulatedResponse: fastFail ? '{"status":"ok","cached":true,"items":[]}' : undefined
    };
  }

  // Benign traffic
  const domain = BENIGN_DOMAINS[Math.floor(Math.random() * BENIGN_DOMAINS.length)];
  const app = APPS_LIST[Math.floor(Math.random() * APPS_LIST.length)];

  return {
    id,
    timestamp,
    app,
    domain,
    destinationIp: '185.199.108.153',
    protocol: 'DoH',
    status: state.airplaneMode && !state.fakeOnlineActive ? 'blocked' : 'allowed',
    reason: 'Encrypted via F125 Quad9 DoH (DNSSEC verified)',
    payloadSize: 420,
    leakRisk: 'safe'
  };
}

/**
 * Simulates launching an app while offline to test F01 Fake-Online & F04 Stubs
 */
export function simulateOfflineAppLaunch(appName: string, fakeOnlineEnabled: boolean): {
  success: boolean;
  probeStatus: string;
  telemetryStatus: string;
  uiOutcome: string;
  packetsGenerated: NetworkPacket[];
} {
  const timestamp = Date.now();
  const packets: NetworkPacket[] = [];

  // Step 1: App checks connectivity via /generate_204
  const probePacket: NetworkPacket = {
    id: `pkt-launch-${timestamp}-1`,
    timestamp: timestamp + 50,
    app: appName,
    domain: 'connectivitycheck.gstatic.com',
    destinationIp: '142.250.190.46',
    protocol: 'HTTP',
    status: fakeOnlineEnabled ? 'faked' : 'blocked',
    reason: fakeOnlineEnabled
      ? 'F01 Fake-Online: Generated synthetic HTTP 204 loopback response'
      : 'Connection timed out (Airplane mode, no radio available)',
    ruleMatched: fakeOnlineEnabled ? 'F01-PROBE-LOOPBACK' : undefined,
    payloadSize: 184,
    isProbe: true,
    simulatedResponse: fakeOnlineEnabled ? 'HTTP/1.1 204 No Content' : undefined
  };
  packets.push(probePacket);

  // Step 2: App phones home to advertising / telemetry
  const telemetryPacket: NetworkPacket = {
    id: `pkt-launch-${timestamp}-2`,
    timestamp: timestamp + 120,
    app: appName,
    domain: 'app-measurement.com',
    destinationIp: '172.217.16.206',
    protocol: 'HTTPS',
    status: fakeOnlineEnabled ? 'faked' : 'blocked',
    reason: fakeOnlineEnabled
      ? 'F04 Fast-Fail Stub: Responded in 0.8ms with empty JSON to avert 30s freeze'
      : 'Connection timed out after 30 seconds',
    ruleMatched: fakeOnlineEnabled ? 'F04-STUB-FASTFAIL' : undefined,
    payloadSize: 640,
    isAdTracker: true,
    simulatedResponse: fakeOnlineEnabled ? '{"status":"ok","ads":[]}' : undefined
  };
  packets.push(telemetryPacket);

  if (fakeOnlineEnabled) {
    return {
      success: true,
      probeStatus: 'HTTP 204 Synthetic Success (1.1ms latency)',
      telemetryStatus: 'Stubbed with empty payload (Zero timeout)',
      uiOutcome: `Success! ${appName} launched in full offline mode without error popups or loading freeze.`,
      packetsGenerated: packets
    };
  } else {
    return {
      success: false,
      probeStatus: 'Timed Out: 0.0.0.0 unreachable in airplane mode',
      telemetryStatus: 'Hanging for 30s waiting for TCP SYN ACK',
      uiOutcome: `Failed: ${appName} blocked the screen with "Check your internet connection" error modal.`,
      packetsGenerated: packets
    };
  }
}
