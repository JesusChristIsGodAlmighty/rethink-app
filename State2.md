# Master Project State: All-in-One Enterprise Admin & Omnipresent Communication Filter
## Project Architecture: From-Scratch Hybrid App (Kotlin + Native C++ JNI)

> **Agent Directive:** You are an autonomous development agent executing task blocks in a cloud environment. Review the **Current Project State**, **Mandatory Rules**, and **Active Milestone** below. Execute the immediate next micro-task. Upon completion or when hitting your API/quota threshold, update this file's progress trackers and commit the code back to the repository.

---

## 📊 Current Project State
* **Current Phase:** Phase 1: Environment Setup & Hybrid Architecture Scaffolding
* **Last Completed Task:** None (Project Initialization)
* **Immediate Next Task:** 1.1 Multi-Module Project Structure Scaffolding
* **System Target:** Android 15+ (API Level 35+)
* **Languages:** Kotlin (Framework/Admin/UI/Hardware Handshakes) & Native C++ (NDK Engine/JNI Bridge/Packet Parsing)

---

## ⚠️ Mandatory External Code Integration Rules
When cloning, extracting, or copying files from external GitHub repositories, every agent **must** strictly enforce these four rules before committing code:

1. **Convert Languages Automatically:** If extracted source files are written in legacy Java, convert them cleanly into modern, idiomatic Kotlin before saving them into the project's `/java` or `/kotlin` directories (leave Native C/C++ files untouched).
2. **Flatten Dependencies:** Audit the external repository's `build.gradle` or `CMakeLists.txt` files for any required third-party libraries. Append those exact dependencies to our project's build configuration so compilation never breaks.
3. **Match Android SDK Versions:** Refactor any deprecated APIs, permissions, or manifest flags from older Android versions to strictly align with our Android 15+ (API 35+) target parameters.
4. **Check Open-Source Licenses:** Inspect the external repository's `LICENSE` file prior to extraction. If it enforces a restrictive copyleft license (such as GPL/AGPL), halt integration, log a warning in the development logs below, and flag it for review or permissive replacement (MIT/Apache 2.0).

---

## 🗺️ Execution Roadmap

### 📦 Phase 1: Environment Setup & Hybrid Architecture
- [ ] **1.1 Multi-Module Project Structure Scaffolding**
  - Create modern Android scratch structure configured for a hybrid project.
  - Establish `/app/src/main/java` (Kotlin) and `/app/src/main/cpp` (C++) directories.
  - Configure root `build.gradle.kts` and app `build.gradle.kts` targeting Android 15+ with CMake/NDK paths enabled.
- [ ] **1.2 JNI Bridge & Memory Buffer Setup**
  - Implement a stable Java Native Interface (JNI) bridge to stream high-velocity data between C++ and Kotlin.
  - Set up lock-free ring buffers in C++ to prevent UI thread lag during deep packet analysis.
- [ ] **1.3 Target Module Isolation (PCAPdroid Base Extraction)**
  - Clone `https://github.com` into a temporary working folder.
  - Enforce the **4 Mandatory External Code Integration Rules** (Note: verify PCAPdroid's GPLv3 license status and log a warning/isolation boundary before copying).
  - Isolate the low-level Native C++ TCP/IP engine code (`zdtun` and packet capture components) and import them into `/app/src/main/cpp`.

### 🎛️ Phase 2: System Admin, Accessibility & Screen Overlays
- [ ] **2.1 Device Policy Manager (System Admin Core)**
  - Scaffold the `DeviceAdminReceiver` and register the app for `Device Owner` status.
  - Implement system lockdown restrictions (blocking factory resets, disabling unsafe settings).
- [ ] **2.2 Accessibility Master Service**
  - Construct an advanced `AccessibilityService` profile to observe on-screen window states, text inputs, and event boundaries.
- [ ] **2.3 Secure System Screen Overlays**
  - Implement a `WindowManager` layout using `TYPE_APPLICATION_OVERLAY`.
  - Design a real-time headless overlay canvas to display live data streams (Wi-Fi, Cellular, Bluetooth) directly over the OS interface.

### 🔍 Phase 3: Omnipresent Data Interception & Decryption
- [ ] **3.1 Multi-Channel Network Capture Engine (C++)**
  - Spin up the Android `VpnService` backend routing raw cellular and Wi-Fi data directly into the C++ native engine.
  - Integrate deep packet parsing via native structures to analyze protocols on the fly.
  - Trap standard DNS resolution pathways (Port 53) to log domain lookups prior to connection handshakes.
- [ ] **3.2 Hardware Peripheral & Short-Range Interceptors (Bluetooth/NFC/USB/Cast/ADB)**
  - Write Bluetooth framework hooks (`BluetoothGatt`, `BluetoothSocket`) and low-level Linux Kernel HCI socket loggers.
  - Implement an `NfcAdapter` monitor capturing contactless transactions and NDEF payloads.
  - Establish `UsbManager` and local port listeners to track USB-host attachments, cast streams (Miracast/Chromecast), and local port `5555` ADB loopback executions.
- [ ] **3.3 SSL/TLS Decryption Hook Integration**
  - Port a lightweight local proxy (`mitmproxy` architecture) to strip encryption layers and reveal plain-text transactional structures.

### 🔄 Phase 4: Internal IPC & Telephony Monitoring
- [ ] **4.1 Binder Subsystem & Inter-App Communication Hooking**
  - Build listeners to map `android.os.Binder` internal transaction vectors.
  - Intercept system intent broadcasts, clipboard buffers, and shared data modifications via global listeners.
- [ ] **4.2 Baseband, SMS, & Telephony Gateway**
  - Monitor `TelephonyManager` cell-tower handshakes, network provisioning strings, and carrier tower transitions.
  - Register low-level `SMS_RECEIVED_ACTION` and `DATA_SMS_RECEIVED_ACTION` gateway filters.

### 🛡️ Phase 5: App-in-App Sandbox Containerization
- [ ] **5.1 Virtual Environment Runtime Interception**
  - Build a private execution context framework inside the app (VirtualApp-style sandbox).
  - Proxy and intercept `ActivityManager`, `PackageManager`, and `ServiceManager` API calls via dynamic proxy reflection hooks.
- [ ] **5.2 Sandboxed Isolation & Alteration Engine**
  - Route all data coming out of sandboxed applications through an alteration layer before it touches the physical Android network hardware.

---

## 📝 Recent Agent Development Logs

### [2026-11-12] - Agent ID: [Governance Update]
* **Action:** Added the 4 Mandatory External Code Integration Rules to enforce automatic Java-to-Kotlin conversion, dependency flattening, SDK 35 alignment, and open-source license auditing.
* **Status:** Awaiting first agent execution cycle for task 1.1.
* **Blocks/Issues encountered:** None.

***
*End of State.md—Do not modify the tracking template structure below this line.*
