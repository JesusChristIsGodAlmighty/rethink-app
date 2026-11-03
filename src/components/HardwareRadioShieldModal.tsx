import React, { useState } from 'react';
import { 
  X, 
  Radio, 
  Wifi, 
  Bluetooth, 
  Mic, 
  MicOff, 
  Camera, 
  VolumeX, 
  ShieldAlert, 
  AlertTriangle, 
  RotateCw, 
  Activity, 
  Lock, 
  CheckCircle2, 
  EyeOff, 
  Cpu, 
  Radar, 
  Signal, 
  Flame, 
  Terminal
} from 'lucide-react';
import { 
  ImsiCatcherDetection, 
  WifiProbeShieldConfig, 
  BleCloakConfig, 
  HardwareSensorsGuard, 
  BasebandRilAudit 
} from '../types/serf';
import { 
  INITIAL_IMSI_DATA, 
  INITIAL_WIFI_SHIELD, 
  INITIAL_BLE_CLOAK, 
  INITIAL_HARDWARE_SENSORS, 
  INITIAL_BASEBAND_AUDIT, 
  rotateWifiMacSimulation, 
  simulateImsiThreatToggle, 
  runTier5DoneTestVerification 
} from '../services/networkSimulator';

interface HardwareRadioShieldModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const HardwareRadioShieldModal: React.FC<HardwareRadioShieldModalProps> = ({
  isOpen,
  onClose
}) => {
  const [activeTab, setActiveTab] = useState<'cellular' | 'wifi' | 'ble' | 'sensors' | 'baseband' | 'selftest'>('cellular');

  // Interactive States
  const [imsiData, setImsiData] = useState<ImsiCatcherDetection>(INITIAL_IMSI_DATA);
  const [wifiShield, setWifiShield] = useState<WifiProbeShieldConfig>(INITIAL_WIFI_SHIELD);
  const [bleCloak, setBleCloak] = useState<BleCloakConfig>(INITIAL_BLE_CLOAK);
  const [sensorsGuard, setSensorsGuard] = useState<HardwareSensorsGuard>(INITIAL_HARDWARE_SENSORS);
  const [basebandAudit, setBasebandAudit] = useState<BasebandRilAudit>(INITIAL_BASEBAND_AUDIT);

  // Self-Test State
  const [testResult, setTestResult] = useState<{
    ran: boolean;
    allPassed: boolean;
    checks: { name: string; passed: boolean; details: string }[];
  }>({
    ran: false,
    allPassed: false,
    checks: []
  });

  if (!isOpen) return null;

  const handleRotateMac = () => {
    setWifiShield(prev => rotateWifiMacSimulation(prev));
  };

  const handleToggleStingraySimulation = () => {
    setImsiData(prev => simulateImsiThreatToggle(prev, !prev.isStingraySuspected));
  };

  const handleToggleRadioKill = () => {
    setBasebandAudit(prev => ({
      ...prev,
      emergencyRadioKillActive: !prev.emergencyRadioKillActive
    }));
  };

  const handleRunSelfTest = () => {
    const res = runTier5DoneTestVerification();
    setTestResult({
      ran: true,
      allPassed: res.allPassed,
      checks: res.checks
    });
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div className="relative w-full max-w-5xl max-h-[92vh] bg-slate-900 border border-slate-700/80 rounded-2xl shadow-2xl flex flex-col overflow-hidden text-slate-100">
        
        {/* Header */}
        <div className="px-6 py-4 border-b border-slate-800 flex items-center justify-between bg-slate-950/60">
          <div className="flex items-center gap-3">
            <div className={`p-2.5 rounded-xl border ${imsiData.isStingraySuspected ? 'bg-red-500/10 border-red-500/40 text-red-400 animate-pulse' : 'bg-emerald-500/10 border-emerald-500/30 text-emerald-400'}`}>
              <Radio className="w-5 h-5" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h2 className="text-lg font-bold text-white tracking-tight">Telephony, Radio &amp; Hardware Shields</h2>
                <span className="px-2 py-0.5 text-[10px] font-semibold bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 rounded">
                  TIER 5 MASTER
                </span>
                {imsiData.isStingraySuspected && (
                  <span className="px-2 py-0.5 text-[10px] font-bold bg-red-500/20 text-red-300 border border-red-500/40 rounded flex items-center gap-1">
                    <AlertTriangle className="w-3 h-3" /> ROGUE TOWER DETECTED
                  </span>
                )}
              </div>
              <p className="text-xs text-slate-400 mt-0.5">
                IMSI-Catcher sentry, Wi-Fi MAC rotation, BLE anti-stalker radar, ultrasonic jamming &amp; baseband RIL monitor.
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
        <div className="flex items-center gap-1 px-6 border-b border-slate-800 bg-slate-900/50 overflow-x-auto text-xs font-semibold">
          <button
            onClick={() => setActiveTab('cellular')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'cellular'
                ? 'border-emerald-400 text-emerald-400'
                : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Signal className="w-3.5 h-3.5" />
            <span>Cellular &amp; IMSI Sentry (F39–F47)</span>
          </button>

          <button
            onClick={() => setActiveTab('wifi')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'wifi'
                ? 'border-emerald-400 text-emerald-400'
                : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Wifi className="w-3.5 h-3.5" />
            <span>Wi-Fi &amp; MAC Blinding (F51–F55)</span>
          </button>

          <button
            onClick={() => setActiveTab('ble')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'ble'
                ? 'border-emerald-400 text-emerald-400'
                : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Bluetooth className="w-3.5 h-3.5" />
            <span>BLE Anti-Stalker (F56–F60)</span>
          </button>

          <button
            onClick={() => setActiveTab('sensors')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'sensors'
                ? 'border-emerald-400 text-emerald-400'
                : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <VolumeX className="w-3.5 h-3.5" />
            <span>Acoustic &amp; Sensors (F48–F50)</span>
          </button>

          <button
            onClick={() => setActiveTab('baseband')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'baseband'
                ? 'border-emerald-400 text-emerald-400'
                : 'border-transparent text-slate-400 hover:text-slate-200'
            }`}
          >
            <Terminal className="w-3.5 h-3.5" />
            <span>Baseband RIL Audit</span>
          </button>

          <button
            onClick={() => setActiveTab('selftest')}
            className={`py-3 px-3.5 border-b-2 flex items-center gap-1.5 transition-colors whitespace-nowrap ${
              activeTab === 'selftest'
                ? 'border-cyan-400 text-cyan-400 font-bold'
                : 'border-transparent text-slate-400 hover:text-cyan-300'
            }`}
          >
            <CheckCircle2 className="w-3.5 h-3.5" />
            <span>Tier 5 Self-Test</span>
          </button>
        </div>

        {/* Tab Body */}
        <div className="flex-1 overflow-y-auto p-6 space-y-6">
          
          {/* TAB 1: CELLULAR & IMSI SENTRY */}
          {activeTab === 'cellular' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              {/* Alert Banner if Threat Active */}
              {imsiData.isStingraySuspected ? (
                <div className="p-4 rounded-xl bg-red-950/60 border border-red-500/50 flex items-start gap-4 text-red-200">
                  <Flame className="w-6 h-6 text-red-400 shrink-0 mt-0.5 animate-pulse" />
                  <div className="flex-1">
                    <h3 className="font-bold text-red-300">Active IMSI-Catcher / Stingray Detected!</h3>
                    <p className="text-xs text-red-300/80 mt-1">
                      Suspicious rogue base station detected on Cell ID {imsiData.cellId} with forced cipher downgrade ({imsiData.encryptionCipher}) and zero neighbor cell announcements. SERF radio firewall has locked baseband handshakes.
                    </p>
                    <button
                      onClick={handleToggleStingraySimulation}
                      className="mt-3 px-3 py-1 bg-red-600 hover:bg-red-500 text-white text-xs font-semibold rounded-lg transition-colors"
                    >
                      Dismiss Threat Simulation
                    </button>
                  </div>
                </div>
              ) : (
                <div className="p-4 rounded-xl bg-emerald-950/40 border border-emerald-500/30 flex items-center justify-between text-emerald-200">
                  <div className="flex items-center gap-3">
                    <ShieldAlert className="w-5 h-5 text-emerald-400" />
                    <div>
                      <span className="font-semibold text-emerald-300 text-sm">Cellular Environment Secure</span>
                      <p className="text-xs text-slate-400">Authentic carrier tower with verified Kasumi A5/3 / AES encryption active.</p>
                    </div>
                  </div>
                  <button
                    onClick={handleToggleStingraySimulation}
                    className="px-3 py-1.5 bg-slate-800 hover:bg-slate-700 text-slate-200 border border-slate-700 text-xs font-medium rounded-lg transition-colors flex items-center gap-1.5"
                  >
                    <AlertTriangle className="w-3.5 h-3.5 text-amber-400" />
                    <span>Simulate Stingray Downgrade</span>
                  </button>
                </div>
              )}

              {/* Tower Telemetry Radar Grid */}
              <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Active Base Station</span>
                  <div className="text-xl font-mono font-bold text-white mt-1">Cell {imsiData.cellId}</div>
                  <div className="text-xs text-slate-400 mt-0.5">MCC {imsiData.mcc} · MNC {imsiData.mnc} · LAC {imsiData.lac}</div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Radio Cipher</span>
                  <div className={`text-base font-bold mt-1 ${imsiData.encryptionCipher.includes('A5/0') ? 'text-red-400' : 'text-emerald-400'}`}>
                    {imsiData.encryptionCipher}
                  </div>
                  <div className="text-xs text-slate-400 mt-0.5">
                    {imsiData.encryptionCipher.includes('A5/0') ? 'Unencrypted payload leak!' : 'Hardware key encrypted'}
                  </div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Signal RSSI</span>
                  <div className="text-xl font-mono font-bold text-cyan-400 mt-1">{imsiData.signalStrengthDbm} dBm</div>
                  <div className="text-xs text-slate-400 mt-0.5">Excellent tower proximity</div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Anomaly Score</span>
                  <div className={`text-xl font-bold mt-1 ${imsiData.anomalyScore > 50 ? 'text-red-400' : 'text-emerald-400'}`}>
                    {imsiData.anomalyScore}%
                  </div>
                  <div className="text-xs text-slate-400 mt-0.5">
                    {imsiData.anomalyScore > 50 ? 'Severe anomaly flagged' : 'Within normal variance'}
                  </div>
                </div>
              </div>

              {/* Tower History & Defense Policies */}
              <div className="space-y-3">
                <h4 className="text-xs font-bold text-slate-300 uppercase tracking-wider">Cell Tower Sentry Ledger</h4>
                <div className="border border-slate-800 rounded-xl overflow-hidden bg-slate-950/40">
                  <table className="w-full text-left text-xs">
                    <thead className="bg-slate-900/80 text-slate-400 border-b border-slate-800">
                      <tr>
                        <th className="py-2.5 px-3">Cell ID</th>
                        <th className="py-2.5 px-3">LAC</th>
                        <th className="py-2.5 px-3">Cipher</th>
                        <th className="py-2.5 px-3">Signal</th>
                        <th className="py-2.5 px-3">Sentry Assessment</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-slate-800/60 font-mono text-slate-300">
                      {imsiData.towerHistory.map((t, idx) => (
                        <tr key={idx} className={t.flag.includes('HOSTILE') ? 'bg-red-950/30 text-red-300' : ''}>
                          <td className="py-2 px-3 font-semibold">{t.cellId}</td>
                          <td className="py-2 px-3 text-slate-400">{t.lac}</td>
                          <td className="py-2 px-3">{t.cipher}</td>
                          <td className="py-2 px-3">{t.signalStrength} dBm</td>
                          <td className="py-2 px-3 font-sans">
                            <span className={`px-2 py-0.5 text-[10px] font-semibold rounded ${
                              t.flag.includes('HOSTILE')
                                ? 'bg-red-500/20 text-red-300 border border-red-500/40'
                                : 'bg-emerald-500/10 text-emerald-400 border border-emerald-500/20'
                            }`}>
                              {t.flag}
                            </span>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          )}

          {/* TAB 2: WI-FI & MAC BLINDING */}
          {activeTab === 'wifi' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex flex-col md:flex-row md:items-center justify-between gap-4">
                <div>
                  <span className="text-[11px] text-slate-500 uppercase font-semibold">Virtual Ephemeral MAC (Active)</span>
                  <div className="text-2xl font-mono font-bold text-emerald-400 tracking-wider mt-1">
                    {wifiShield.currentVirtualMac}
                  </div>
                  <div className="text-xs text-slate-400 mt-1 flex items-center gap-2">
                    <span>Hardware OUI: <span className="font-mono text-slate-500">{wifiShield.realHardwareMac}</span> (Masked)</span>
                    <span>·</span>
                    <span className="text-emerald-400">{wifiShield.probesBlindedCount} Directed Probes Blinded</span>
                  </div>
                </div>

                <div className="flex items-center gap-2">
                  <button
                    onClick={handleRotateMac}
                    className="px-4 py-2 bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-bold text-xs rounded-xl transition-all shadow-sm flex items-center gap-2"
                  >
                    <RotateCw className="w-3.5 h-3.5" />
                    <span>Rotate MAC Now</span>
                  </button>
                </div>
              </div>

              {/* Wi-Fi Privacy Shields Checklist */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 flex items-start gap-3">
                  <input
                    type="checkbox"
                    checked={wifiShield.suppressDirectedProbes}
                    onChange={(e) => setWifiShield(prev => ({ ...prev, suppressDirectedProbes: e.target.checked }))}
                    className="mt-1 accent-emerald-500 rounded cursor-pointer"
                  />
                  <div>
                    <h4 className="text-sm font-semibold text-slate-200">Suppress Directed Probe Requests (F51)</h4>
                    <p className="text-xs text-slate-400 mt-0.5">
                      Prevents your phone from broadcasting saved home/office SSIDs in public, defeating commercial location triangulation.
                    </p>
                  </div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 flex items-start gap-3">
                  <input
                    type="checkbox"
                    checked={wifiShield.randomizeSequenceNumbers}
                    onChange={(e) => setWifiShield(prev => ({ ...prev, randomizeSequenceNumbers: e.target.checked }))}
                    className="mt-1 accent-emerald-500 rounded cursor-pointer"
                  />
                  <div>
                    <h4 className="text-sm font-semibold text-slate-200">Sequence Number Randomization (F52)</h4>
                    <p className="text-xs text-slate-400 mt-0.5">
                      Randomizes 802.11 frame sequence numbers so sniffer hardware cannot link sequential packet batches across MAC changes.
                    </p>
                  </div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 flex items-start gap-3">
                  <input
                    type="checkbox"
                    checked={wifiShield.stripIeFingerprint}
                    onChange={(e) => setWifiShield(prev => ({ ...prev, stripIeFingerprint: e.target.checked }))}
                    className="mt-1 accent-emerald-500 rounded cursor-pointer"
                  />
                  <div>
                    <h4 className="text-sm font-semibold text-slate-200">Information Element (IE) Cloak (F53)</h4>
                    <p className="text-xs text-slate-400 mt-0.5">
                      Strips unique chipset capability flags (HT/VHT rates) from beacon probe frames to defeat radio hardware fingerprinting.
                    </p>
                  </div>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 flex items-start gap-3">
                  <input
                    type="checkbox"
                    checked={true}
                    readOnly
                    className="mt-1 accent-emerald-500 rounded cursor-pointer"
                  />
                  <div>
                    <h4 className="text-sm font-semibold text-slate-200">Karma &amp; Rogue AP Honeypot Trap (F54)</h4>
                    <p className="text-xs text-slate-400 mt-0.5">
                      Automatically drops associations to Wi-Fi Pineapples responding to wildcards or spoofing captive portals.
                    </p>
                  </div>
                </div>
              </div>

              {/* MAC Rotation History */}
              <div>
                <h4 className="text-xs font-bold text-slate-300 uppercase tracking-wider mb-2">Ephemeral Identity Log</h4>
                <div className="space-y-2">
                  {wifiShield.macRotationHistory.map((item, idx) => (
                    <div key={idx} className="p-3 rounded-lg bg-slate-950/40 border border-slate-800/80 flex items-center justify-between text-xs font-mono">
                      <div className="flex items-center gap-3">
                        <span className="text-emerald-400 font-bold">{item.mac}</span>
                        <span className="text-slate-500 font-sans">{item.reason}</span>
                      </div>
                      <span className="text-slate-500 font-sans">{new Date(item.timestamp).toLocaleTimeString()}</span>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          )}

          {/* TAB 3: BLE ANTI-STALKER */}
          {activeTab === 'ble' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="text-sm font-bold text-white flex items-center gap-2">
                    <Radar className="w-4 h-4 text-cyan-400" />
                    <span>Real-Time Bluetooth &amp; Tracker Sweeper (F56–F60)</span>
                  </h3>
                  <p className="text-xs text-slate-400 mt-1">
                    Continuous acoustic and RF sweep detecting Apple AirTags, Samsung SmartTags, and unbonded BLE beacons moving with your coordinates.
                  </p>
                </div>
                <div className="px-3 py-1 bg-cyan-950/40 border border-cyan-500/30 rounded-lg text-cyan-400 font-bold text-xs">
                  {bleCloak.detectedTrackers.length} Beacons In Range
                </div>
              </div>

              {/* Detected Trackers List */}
              <div className="space-y-3">
                {bleCloak.detectedTrackers.map((t) => (
                  <div key={t.id} className={`p-4 rounded-xl border flex flex-col md:flex-row md:items-center justify-between gap-4 ${
                    t.isFollowingUser 
                      ? 'bg-amber-950/20 border-amber-500/40' 
                      : 'bg-slate-950/40 border-slate-800'
                  }`}>
                    <div className="flex items-start gap-3">
                      <div className={`p-2 rounded-lg border ${t.isFollowingUser ? 'bg-amber-500/10 border-amber-500/30 text-amber-400' : 'bg-slate-800 border-slate-700 text-slate-400'}`}>
                        <Bluetooth className="w-4 h-4" />
                      </div>
                      <div>
                        <div className="flex items-center gap-2">
                          <h4 className="text-sm font-semibold text-slate-200">{t.name}</h4>
                          {t.isFollowingUser && (
                            <span className="px-2 py-0.5 text-[10px] font-bold bg-amber-500/20 text-amber-300 border border-amber-500/30 rounded animate-pulse">
                              POTENTIAL STALKER BEACON
                            </span>
                          )}
                        </div>
                        <div className="text-xs text-slate-400 mt-1 flex items-center gap-2 font-mono">
                          <span>MAC: {t.mac}</span>
                          <span>·</span>
                          <span className="text-cyan-400">RSSI: {t.rssi} dBm ({t.proximity})</span>
                        </div>
                      </div>
                    </div>

                    <div className="flex items-center gap-2">
                      <button
                        onClick={() => {
                          setBleCloak(prev => ({
                            ...prev,
                            detectedTrackers: prev.detectedTrackers.filter(d => d.id !== t.id),
                            trackersBlockedCount: prev.trackersBlockedCount + 1
                          }));
                        }}
                        className="px-3 py-1.5 bg-red-600/80 hover:bg-red-500 text-white font-semibold text-xs rounded-lg transition-colors"
                      >
                        Block &amp; Neutralize
                      </button>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* TAB 4: ACOUSTIC & HARDWARE SENSORS */}
          {activeTab === 'sensors' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-3">
                    <div className="p-2.5 rounded-xl bg-indigo-500/10 border border-indigo-500/30 text-indigo-400">
                      <VolumeX className="w-5 h-5" />
                    </div>
                    <div>
                      <h3 className="font-bold text-white text-sm">Ultrasonic Cross-Device Tracking Jammer (F48)</h3>
                      <p className="text-xs text-slate-400 mt-0.5">
                        High-pass filtering and active acoustic perturbation in the 18 kHz – 22 kHz spectrum used by TV ads and storefront beacons.
                      </p>
                    </div>
                  </div>
                  <div className="flex items-center gap-2">
                    <span className="text-xs font-semibold text-emerald-400">
                      {sensorsGuard.ultrasonicBeaconsNeutralized} Beacons Neutralized
                    </span>
                    <button
                      onClick={() => setSensorsGuard(prev => ({ ...prev, ultrasonicShieldActive: !prev.ultrasonicShieldActive }))}
                      className={`px-3 py-1.5 rounded-lg text-xs font-bold transition-colors ${
                        sensorsGuard.ultrasonicShieldActive
                          ? 'bg-emerald-500 text-slate-950 hover:bg-emerald-400'
                          : 'bg-slate-800 text-slate-400 hover:text-white'
                      }`}
                    >
                      {sensorsGuard.ultrasonicShieldActive ? 'SHIELD ACTIVE' : 'DISABLED'}
                    </button>
                  </div>
                </div>
              </div>

              {/* Hardware Sensor Cutoffs */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 flex items-center justify-between">
                  <div className="flex items-center gap-3">
                    {sensorsGuard.micHardwareCutoff ? <MicOff className="w-5 h-5 text-red-400" /> : <Mic className="w-5 h-5 text-slate-400" />}
                    <div>
                      <h4 className="text-sm font-semibold text-slate-200">Microphone Hardware Gate (F49)</h4>
                      <p className="text-xs text-slate-400">Software loopback dead-signal feed to apps</p>
                    </div>
                  </div>
                  <button
                    onClick={() => setSensorsGuard(prev => ({ ...prev, micHardwareCutoff: !prev.micHardwareCutoff }))}
                    className={`px-3 py-1 rounded text-xs font-semibold transition-colors ${
                      sensorsGuard.micHardwareCutoff ? 'bg-red-500/20 text-red-300 border border-red-500/40' : 'bg-slate-800 text-slate-400'
                    }`}
                  >
                    {sensorsGuard.micHardwareCutoff ? 'MUTED' : 'UNMUTED'}
                  </button>
                </div>

                <div className="p-4 rounded-xl bg-slate-950/50 border border-slate-800 flex items-center justify-between">
                  <div className="flex items-center gap-3">
                    <Camera className={`w-5 h-5 ${sensorsGuard.cameraHardwareLock ? 'text-red-400' : 'text-slate-400'}`} />
                    <div>
                      <h4 className="text-sm font-semibold text-slate-200">Camera Shutter Lock (F50)</h4>
                      <p className="text-xs text-slate-400">Intercept HAL camera service requests</p>
                    </div>
                  </div>
                  <button
                    onClick={() => setSensorsGuard(prev => ({ ...prev, cameraHardwareLock: !prev.cameraHardwareLock }))}
                    className={`px-3 py-1 rounded text-xs font-semibold transition-colors ${
                      sensorsGuard.cameraHardwareLock ? 'bg-red-500/20 text-red-300 border border-red-500/40' : 'bg-slate-800 text-slate-400'
                    }`}
                  >
                    {sensorsGuard.cameraHardwareLock ? 'LOCKED' : 'OPEN'}
                  </button>
                </div>
              </div>
            </div>
          )}

          {/* TAB 5: BASEBAND RIL AUDIT */}
          {activeTab === 'baseband' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div className="flex items-center gap-3">
                  <Cpu className="w-5 h-5 text-cyan-400" />
                  <div>
                    <h3 className="font-bold text-white text-sm">Radio Interface Layer (RIL) AT Command Audit</h3>
                    <p className="text-xs text-slate-400 mt-0.5 font-mono">
                      Firmware Hash: <span className="text-slate-300">{basebandAudit.firmwareChecksum}</span> ({basebandAudit.firmwareHashStatus})
                    </p>
                  </div>
                </div>

                <button
                  onClick={handleToggleRadioKill}
                  className={`px-4 py-2 font-bold text-xs rounded-xl transition-all shadow-sm flex items-center gap-1.5 ${
                    basebandAudit.emergencyRadioKillActive
                      ? 'bg-red-500 text-white animate-pulse'
                      : 'bg-slate-800 hover:bg-slate-700 text-red-400 border border-red-500/30'
                  }`}
                >
                  <AlertTriangle className="w-4 h-4" />
                  <span>{basebandAudit.emergencyRadioKillActive ? 'RADIO KILLSWITCH ACTIVE' : 'EMERGENCY RADIO KILL'}</span>
                </button>
              </div>

              {/* AT Command Feed */}
              <div className="space-y-2">
                <h4 className="text-xs font-bold text-slate-300 uppercase tracking-wider">Intercepted AT Commands</h4>
                <div className="border border-slate-800 rounded-xl overflow-hidden bg-slate-950/50">
                  <table className="w-full text-left text-xs font-mono">
                    <thead className="bg-slate-900/80 text-slate-400 border-b border-slate-800 font-sans">
                      <tr>
                        <th className="py-2.5 px-3">Time</th>
                        <th className="py-2.5 px-3">AT Command</th>
                        <th className="py-2.5 px-3">Response</th>
                        <th className="py-2.5 px-3">Verdict</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-slate-800/60 text-slate-300">
                      {basebandAudit.atCommandLog.map((cmd) => (
                        <tr key={cmd.id} className={cmd.riskLevel === 'suspicious' ? 'bg-amber-950/20 text-amber-300' : ''}>
                          <td className="py-2 px-3 text-slate-500">{new Date(cmd.timestamp).toLocaleTimeString()}</td>
                          <td className="py-2 px-3 font-bold text-cyan-300">{cmd.command}</td>
                          <td className="py-2 px-3">{cmd.response}</td>
                          <td className="py-2 px-3 font-sans">
                            <span className={`px-2 py-0.5 text-[10px] font-semibold rounded ${
                              cmd.riskLevel === 'suspicious'
                                ? 'bg-amber-500/20 text-amber-300 border border-amber-500/30'
                                : 'bg-emerald-500/10 text-emerald-400'
                            }`}>
                              {cmd.riskLevel.toUpperCase()}
                            </span>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          )}

          {/* TAB 6: TIER 5 SELF-TEST */}
          {activeTab === 'selftest' && (
            <div className="space-y-6 animate-in fade-in duration-150">
              <div className="p-4 rounded-xl bg-slate-950/60 border border-slate-800 flex items-center justify-between">
                <div>
                  <h3 className="font-bold text-white text-sm">Tier 5 Verification &amp; Test Suite</h3>
                  <p className="text-xs text-slate-400 mt-0.5">
                    Execute full end-to-end assertions against all 5 Telephony, Radio &amp; Hardware Shield sub-engines.
                  </p>
                </div>
                <button
                  onClick={handleRunSelfTest}
                  className="px-4 py-2 bg-cyan-500 hover:bg-cyan-400 text-slate-950 font-bold text-xs rounded-xl transition-all shadow-sm flex items-center gap-2"
                >
                  <Activity className="w-3.5 h-3.5" />
                  <span>Execute Full Tier 5 Suite</span>
                </button>
              </div>

              {testResult.ran && (
                <div className={`p-4 rounded-xl border ${
                  testResult.allPassed 
                    ? 'bg-emerald-950/40 border-emerald-500/40 text-emerald-300' 
                    : 'bg-red-950/40 border-red-500/40 text-red-300'
                }`}>
                  <div className="flex items-center gap-2 font-bold text-sm">
                    {testResult.allPassed ? <CheckCircle2 className="w-5 h-5 text-emerald-400" /> : <AlertTriangle className="w-5 h-5 text-red-400" />}
                    <span>{testResult.allPassed ? 'ALL TIER 5 ENGINE CHECKS PASSED (100% SOVEREIGN)' : 'TIER 5 VERIFICATION INCOMPLETE'}</span>
                  </div>

                  <div className="mt-4 space-y-2">
                    {testResult.checks.map((c, i) => (
                      <div key={i} className="p-3 rounded-lg bg-slate-900/60 border border-slate-800 text-xs flex items-start gap-3">
                        <CheckCircle2 className={`w-4 h-4 shrink-0 mt-0.5 ${c.passed ? 'text-emerald-400' : 'text-red-400'}`} />
                        <div>
                          <div className="font-semibold text-slate-200">{c.name}</div>
                          <div className="text-slate-400 mt-0.5 font-mono text-[11px]">{c.details}</div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          )}

        </div>

        {/* Footer */}
        <div className="px-6 py-3 border-t border-slate-800 bg-slate-950/80 flex items-center justify-between text-xs text-slate-400">
          <div className="flex items-center gap-2">
            <span className="w-2 h-2 rounded-full bg-emerald-400 animate-pulse" />
            <span>Hardware Shield Active (RIL Sentry + RF Blind + Ultrasonic Guard)</span>
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
