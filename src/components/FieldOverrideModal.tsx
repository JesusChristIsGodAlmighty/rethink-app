import React, { useState } from 'react';
import { 
  X, 
  Sliders, 
  RefreshCw, 
  Check, 
  ShieldCheck, 
  Search, 
  Table, 
  Lock 
} from 'lucide-react';
import { FieldOverride, TransmissionLogEntry } from '../types/serf';

interface FieldOverrideModalProps {
  overrides: FieldOverride[];
  transmissionLogs: TransmissionLogEntry[];
  isOpen: boolean;
  onClose: () => void;
  onToggleOverride: (key: string) => void;
  onUpdateFakeValue: (key: string, newValue: string) => void;
}

export const FieldOverrideModal: React.FC<FieldOverrideModalProps> = ({
  overrides,
  transmissionLogs,
  isOpen,
  onClose,
  onToggleOverride,
  onUpdateFakeValue
}) => {
  if (!isOpen) return null;

  const [activeTab, setActiveTab] = useState<'overrides' | 'ledger'>('overrides');
  const [editingKey, setEditingKey] = useState<string | null>(null);
  const [tempValue, setTempValue] = useState('');

  const startEdit = (override: FieldOverride) => {
    setEditingKey(override.key);
    setTempValue(override.fakeValue);
  };

  const saveEdit = (key: string) => {
    onUpdateFakeValue(key, tempValue);
    setEditingKey(null);
  };

  const randomizeValue = (key: string) => {
    let rand = '';
    if (key === 'mac_address') {
      rand = Array.from({ length: 6 }, () => Math.floor(Math.random() * 256).toString(16).padStart(2, '0').toUpperCase()).join(':');
    } else if (key === 'imei_to_apps') {
      rand = '35' + Array.from({ length: 13 }, () => Math.floor(Math.random() * 10)).join('');
    } else if (key === 'iccid_to_apps') {
      rand = '8901410' + Array.from({ length: 13 }, () => Math.floor(Math.random() * 10)).join('');
    } else if (key === 'android_id' || key === 'gsf_id') {
      rand = Array.from({ length: 16 }, () => Math.floor(Math.random() * 16).toString(16)).join('');
    } else if (key === 'nfc_uid') {
      rand = '04:' + Array.from({ length: 6 }, () => Math.floor(Math.random() * 256).toString(16).padStart(2, '0').toUpperCase()).join(':');
    } else if (key === 'usb_descriptor') {
      const models = ['Generic MTP Phone', 'Android ADB Device', 'Generic Multimedia Host', 'Linux USB Peripheral'];
      rand = models[Math.floor(Math.random() * models.length)] + ' (SN: ' + Math.floor(10000000 + Math.random() * 90000000) + ')';
    } else if (key === 'esim_eid') {
      rand = '890490' + Array.from({ length: 26 }, () => Math.floor(Math.random() * 10)).join('');
    } else if (key === 'bluetooth_name') {
      const names = ['Android Device', 'Pixel Phone', 'Galaxy Model', 'Generic Audio Receiver'];
      rand = names[Math.floor(Math.random() * names.length)];
    } else if (key === 'sensor_calibration') {
      rand = 'Calibrated Gaussian Jitter (σ = ' + (0.001 + Math.random() * 0.003).toFixed(5) + ')';
    } else {
      rand = 'Dynamic Synthetic ' + Math.floor(Math.random() * 1000);
    }
    onUpdateFakeValue(key, rand);
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-950/80 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="bg-slate-900 border border-slate-800 rounded-2xl w-full max-w-4xl h-[85vh] flex flex-col shadow-2xl overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="p-5 border-b border-slate-800 flex items-center justify-between gap-4">
          <div className="flex items-center gap-3">
            <div className="p-2 rounded-lg bg-violet-500/10 border border-violet-500/30 text-violet-400">
              <Sliders className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold text-slate-100 flex items-center gap-2">
                <span>Universal Outbound Field Override Engine</span>
                <span className="font-mono text-xs px-2 py-0.5 rounded bg-slate-800 text-violet-400 font-bold border border-slate-700">
                  F662 / F947
                </span>
              </h2>
              <p className="text-xs text-slate-400">
                Spoofs hardware identifiers and sensors to apps, coupled with F663 Transmission Ledger.
              </p>
            </div>
          </div>

          <button
            onClick={onClose}
            className="p-1.5 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Tab switch */}
        <div className="px-5 py-2.5 bg-slate-950/60 border-b border-slate-800 flex items-center gap-2 text-xs">
          <button
            onClick={() => setActiveTab('overrides')}
            className={`px-3 py-1.5 rounded-lg transition-colors font-semibold ${
              activeTab === 'overrides' ? 'bg-violet-500/20 text-violet-300 border border-violet-500/40' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            Identifier Controls (F662)
          </button>
          <button
            onClick={() => setActiveTab('ledger')}
            className={`px-3 py-1.5 rounded-lg transition-colors font-semibold ${
              activeTab === 'ledger' ? 'bg-violet-500/20 text-violet-300 border border-violet-500/40' : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            Transmission Ledger (F663) ({transmissionLogs.length})
          </button>
        </div>

        {/* Body */}
        <div className="flex-1 p-6 overflow-y-auto space-y-4">
          {activeTab === 'overrides' ? (
            <div className="space-y-4">
              <div className="bg-slate-950 p-4 rounded-xl border border-slate-800 text-xs text-slate-300 leading-relaxed">
                <span className="font-semibold text-slate-200">Architectural Note:</span> F662 supersedes individual toggle switches by providing one unified engine for all outbound fields. Altering what your own apps receive is configuring your personal property (Section 15.1).
              </div>

              <div className="divide-y divide-slate-800/80 rounded-xl border border-slate-800 bg-slate-950/40 overflow-hidden">
                {overrides.map((item) => (
                  <div key={item.key} className="p-4 flex flex-col sm:flex-row sm:items-center justify-between gap-4 hover:bg-slate-900/40 transition-colors">
                    <div className="space-y-1">
                      <div className="flex items-center gap-2">
                        <span className="font-semibold text-sm text-slate-100">{item.label}</span>
                        <span className="text-[10px] px-1.5 py-0.5 rounded bg-slate-800 text-slate-400 font-mono">
                          {item.category}
                        </span>
                        <span className="text-[10px] text-slate-500">
                          {item.queriesCount} queries intercepted
                        </span>
                      </div>
                      <p className="text-xs text-slate-400">{item.description}</p>

                      <div className="flex items-center gap-4 text-xs font-mono pt-1">
                        <span className="text-slate-500">Real: <span className="text-slate-400 line-through">{item.currentRealValue}</span></span>
                        <span className="text-violet-400 font-bold">Reported: <span>{item.fakeValue}</span></span>
                      </div>
                    </div>

                    <div className="flex items-center gap-2 self-end sm:self-center shrink-0">
                      {editingKey === item.key ? (
                        <div className="flex items-center gap-2">
                          <input
                            type="text"
                            value={tempValue}
                            onChange={(e) => setTempValue(e.target.value)}
                            className="bg-slate-900 border border-slate-700 px-2 py-1 rounded text-xs text-slate-200 font-mono w-40"
                          />
                          <button
                            onClick={() => saveEdit(item.key)}
                            className="p-1 rounded bg-emerald-500/20 text-emerald-300 hover:bg-emerald-500/30"
                          >
                            <Check className="w-4 h-4" />
                          </button>
                        </div>
                      ) : (
                        <button
                          onClick={() => startEdit(item)}
                          className="px-2.5 py-1 rounded bg-slate-800 hover:bg-slate-700 text-xs text-slate-300 transition-colors"
                        >
                          Edit
                        </button>
                      )}

                      <button
                        onClick={() => randomizeValue(item.key)}
                        title="Randomize Value"
                        className="p-1.5 rounded bg-slate-800 hover:bg-slate-700 text-slate-400 hover:text-white transition-colors"
                      >
                        <RefreshCw className="w-3.5 h-3.5" />
                      </button>

                      <button
                        onClick={() => onToggleOverride(item.key)}
                        className={`px-3 py-1 rounded-md text-xs font-semibold transition-colors ${
                          item.active
                            ? 'bg-violet-500/20 text-violet-300 border border-violet-500/40'
                            : 'bg-slate-800 text-slate-500'
                        }`}
                      >
                        {item.active ? 'Spoofed' : 'Bypass'}
                      </button>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          ) : (
            <div className="space-y-4">
              <div className="text-xs text-slate-400">
                F663 Transmission Ledger logs every identifier query from apps and the exact synthetic value returned in friendly, plain language.
              </div>

              <div className="rounded-xl border border-slate-800 bg-slate-950 overflow-hidden text-xs">
                <table className="w-full text-left">
                  <thead className="bg-slate-900/80 text-slate-400 border-b border-slate-800 font-medium">
                    <tr>
                      <th className="p-3">Time</th>
                      <th className="p-3">App</th>
                      <th className="p-3">Field Queried</th>
                      <th className="p-3">Value Returned</th>
                      <th className="p-3">Status</th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-800/60 font-mono">
                    {transmissionLogs.length === 0 ? (
                      <tr>
                        <td colSpan={5} className="p-4 text-center text-slate-500 italic">
                          No identifier query events recorded yet.
                        </td>
                      </tr>
                    ) : (
                      transmissionLogs.map((log) => (
                        <tr key={log.id} className="hover:bg-slate-900/40">
                          <td className="p-3 text-slate-500 text-[11px]">
                            {new Date(log.timestamp).toLocaleTimeString()}
                          </td>
                          <td className="p-3 font-sans font-semibold text-slate-200">
                            {log.app}
                          </td>
                          <td className="p-3 text-slate-400">
                            {log.queriedField}
                          </td>
                          <td className="p-3 text-violet-300 truncate max-w-xs">
                            {log.valueReturned}
                          </td>
                          <td className="p-3">
                            {log.wasSpoofed ? (
                              <span className="text-[10px] px-1.5 py-0.5 rounded bg-emerald-500/10 text-emerald-400 border border-emerald-500/30">
                                Spoofed
                              </span>
                            ) : (
                              <span className="text-[10px] text-slate-500">Real</span>
                            )}
                          </td>
                        </tr>
                      ))
                    )}
                  </tbody>
                </table>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
