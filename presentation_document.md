# 🌾 KRISHI DRISHTI (ಕೃಷಿ ದೃಷ್ಟಿ) — Executive Presentation & Technical Pitch Deck

**An Intelligent AI-Powered Crop Health and Early Warning Platform for Smallholder Farmers**

---

## 📌 Slide 1: The Global Agricultural Challenge

### The Problem
* **$220 Billion Annual Loss**: Crop diseases destroy up to **40% of agricultural yields** worldwide each year, severely threatening food security.
* **Delayed Diagnosis**: Farmers in remote villages often recognize crop diseases only after symptoms spread beyond control.
* **Chemical Overuse & Cost Burden**: Without diagnostic tools, farmers overuse expensive chemical pesticides, degrading soil health and ballooning farming expenses.
* **Rural Connectivity Dark Zones**: Most existing AI crop tools require high-speed cloud connections, making them non-functional in remote rural fields.
* **Language & Literacy Barriers**: Complex agricultural advisories are rarely accessible in regional mother tongues like **Kannada** or in spoken audio formats.

---

## 🌿 Slide 2: The Solution — KRISHI DRISHTI (ಕೃಷಿ ದೃಷ್ಟಿ)

**Krishi Drishti** is an end-to-end, voice-enabled, multilingual AI mobile platform that brings enterprise-grade crop pathology directly into the hands of farmers.

### Core Value Pillars
1. **Zero-Latency On-Device AI**: Instant neural net inference (`model.tflite`) running 100% offline in RAM on low-cost Android smartphones.
2. **Weather-Based Early Warning Risk Radar**: Analyzes environmental humidity and temperature to alert farmers *before* fungal spores infect crops.
3. **Voice-First & Multilingual Support**: Full native support in **Kannada (ಕನ್ನಡ)**, English, Hindi, and Marathi, powered by Android Text-To-Speech and Speech Recognition.
4. **Actionable Organic Treatment Guidance**: Recommends biological controls (*Neem oil, Trichoderma, Bacillus subtilis*) and generates downloadable PDF reports.

---

## 🏗️ Slide 3: Technical Architecture & Innovation

```
┌────────────────────────────────────────────────────────────────────────┐
│                        ANDROID MOBILE CLIENT                           │
│  UI: Material 3, Forest Green Theme (#1B4D3E), Vector Brand Emblem     │
│  Voice & Language: TtsManager (4 Languages) + Speech-to-Text Input     │
│  Data Visualization: MPAndroidChart (Scan Distribution Analytics)      │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │ Instant Offline Event Flow
                                    ▼
┌───────────────────────────────────┴────────────────────────────────────┐
│                    ON-DEVICE AI & LOCAL STORAGE                        │
│  TensorFlow Lite Engine: model.tflite (39 Output Classes)             │
│  Image Processing: 224x224 RGB Float32 Normalized Tensor Buffer        │
│  Local Database: SQLite KrishiDrishti.db (Records Table)               │
│  Export: Native A4 PDF Generator (PdfExportHelper)                     │
└────────────────────────────────────────────────────────────────────────┘
```

---

## 🧠 Slide 4: Deep Learning Pipeline & Accuracy

### Model Specifications
* **Architecture**: **ResNet-18** Convolutional Neural Network with ImageNet Transfer Learning.
* **Export Pipeline**: PyTorch (`.pth`) $\rightarrow$ ONNX (`.onnx`) $\rightarrow$ TF SavedModel (NHWC Transpose) $\rightarrow$ TensorFlow Lite (`.tflite`).
* **Input**: `[1, 224, 224, 3]` Float32 Tensor.
* **Output**: `[1, 39]` Class Probability Vector.

### Dataset & Evaluation Benchmark
* **Dataset**: **PlantVillage** (~54,305 images across 38 crop disease/healthy classes + 1 Background Gating Class).
* **Crops Covered**: Tomato, Potato, Apple, Corn/Maize, Grape, Orange, Pepper Bell, Strawberry, Peach, Cherry, Blueberry, Raspberry, Soybean, Squash.

| Evaluation Metric | Score |
| :--- | :--- |
| **Model Accuracy** | **93.14%** |
| **Weighted Precision** | **92.90%** |
| **Weighted Recall** | **93.14%** |
| **Weighted F1-Score** | **92.50%** |

---

## 📱 Slide 5: Core Application Walkthrough

### 1. Main Dashboard (`Home.java`)
* Header bar featuring **KRISHI DRISHTI** brand badge and AI Assistant shortcut.
* Dynamic **Weather & Early Disease Risk Alert** banner (e.g. *82% High Humidity $\rightarrow$ High Late Blight Risk*).
* Quick action cards for **AI Leaf Scan**, **AI Assistant**, and **Scan History**.

### 2. AI Crop Assistant (`CropAssistant.java`)
* Conversational AI interface supporting text and speech input (`startVoiceInput()`).
* Answers crop health, biological fungicide, fertilizer schedule, and weather care questions in Kannada, English, Hindi, and Marathi.
* Reads answers aloud automatically via `TtsManager`.

### 3. AI Diagnosis & PDF Export (`Result.java`)
* Executes TFLite model in background thread.
* Filters out non-leaf background photos using prediction gating policies (`TAU_ITEM = 0.25`).
* Displays confidence percentage meter, reads findings aloud, and exports formal PDF health reports via `PdfExportHelper`.

### 4. Scan History & Visual Analytics (`Reports.java`)
* Plots historical scan disease distributions using an interactive `PieChart` (`MPAndroidChart`) backed by SQLite `KrishiDrishti.db`.

### 5. Farm Profile & Advisory Calendar (`FarmProfileActivity.java`)
* Records farmer land acreage, village location, primary crop, and stage-by-stage advisory calendar.

---

## 📈 Slide 6: Social, Economic & Environmental Impact

* **30% Cost Reduction**: Helps farmers avoid unnecessary chemical sprays through targeted biological guidance.
* **Zero Connectivity Exclusion**: Works seamlessly in deep rural farmland without mobile data.
* **Farmer Empowerment**: Native **Kannada** voice interface allows illiterate and elderly farmers to use AI diagnostics effortlessly.
* **Sustainable Farming**: Promotes bio-fungicides (*Trichoderma*, *Bacillus*) to preserve soil fertility and ecosystem health.

---

## 🚀 Slide 7: Live Execution & Demonstration Commands

### Build & Deploy
```powershell
# Set JDK 21 environment
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-21.0.13.9-hotspot"

# Build Android Debug APK
cd app
.\gradlew.bat assembleDebug

# Install on connected smartphone or emulator
adb install -r "app\build\outputs\apk\debug\app-debug.apk"

# Launch Krishi Drishti
adb shell am start -n com.example.plantai/.MainActivity
```

---

## 🔮 Slide 8: Future Roadmap

* [ ] **IoT Sensor Integration**: Pairing with Bluetooth/LoRa soil moisture and leaf wetness sensors.
* [ ] **NPU Hardware Acceleration**: Leveraging Android NNAPI and GPU delegates for sub-10ms inference.
* [ ] **Regional Language Expansion**: Adding voice models for Tamil, Telugu, Malayalam, and Gujarati.

---

<p align="center">
  <b>KRISHI DRISHTI (ಕೃಷಿ ದೃಷ್ಟಿ)</b> — Empowering Farmers with AI Technology 🌾
</p>
