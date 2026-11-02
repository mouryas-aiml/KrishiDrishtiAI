# KRISHI DRISHTI — An Intelligent AI-Powered Crop Health & Early Warning Platform

**Krishi Drishti** (ಕೃಷಿ ದೃಷ್ಟಿ) is an AI-powered agricultural mobile application and machine learning platform designed to empower farmers with early disease risk warnings, instant on-device leaf disease detection, smart treatment guidance, and an interactive AI crop health assistant.

<p align="center">
  <img src="asset/app.gif" alt="Krishi Drishti App Demo" width="220">
</p>

---

## 🌟 Key Features

### 1. 🍃 On-Device AI Crop Disease Detection
* **Instant TFLite Inference**: Photographically analyze crop leaves directly on-device with zero internet dependency.
* **38 Disease & Healthy Classes**: Covers major crops including Tomato, Potato, Apple, Corn, Grape, Pepper, Strawberry, Cherry, and Soybean.
* **Confidence & Severity Meter**: Provides AI prediction confidence scores and actionable next steps.

### 2. ⛅ Weather-Based Early Disease Risk Warning
* Combines regional weather conditions (humidity, temperature, moisture risk) to generate early risk warnings for fungal pathogens (e.g. Late Blight, Downy Mildew, Common Rust, Powdery Mildew).

### 3. 🤖 Interactive AI Crop Assistant
* Farmers can ask crop-health questions, fertilizer schedules, or disease management strategies.
* Provides organic, biological, and cultural treatment guidance customized to crops.

### 4. 📊 Crop Health Scan History
* Stores local scan records in an offline SQLite database (`KrishiDrishti.db`).
* Tracks disease trends over time for better farm management.

### 5. 🌐 Farmer-Friendly Multilingual Support
* Multi-language UI support prioritizing **English**, **Kannada (ಕನ್ನಡ)**, **Hindi (हिंदी)**, and **Marathi (मराठी)**.

---

## 🎨 Premium Agricultural UI/UX Design
* **Forest & Leaf Palette**: Deep Forest Green (`#1B4D3E`), Fresh Leaf Green (`#2E7D32`), Warm Off-White (`#F4F6F5`), and Earthy Accents (`#8D6E63`).
* **Visual Identity**: Modernized vector brand logo combining a stylized leaf, AI vision/eye symbol, and technology nodes.

---

## 🚀 Mobile App Build Instructions

### Prerequisites
* JDK 21 (`C:\Program Files\Eclipse Adoptium\jdk-21.0.13.9-hotspot` or equivalent)
* Android SDK Platform 35

### Building & Running
```powershell
# Set Java 21 environment
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-21.0.13.9-hotspot"

# Build debug APK
cd app
.\gradlew.bat assembleDebug

# Install on connected device/emulator
adb install -r "app\build\outputs\apk\debug\app-debug.apk"

# Launch Krishi Drishti
adb shell am start -n com.example.plantai/.MainActivity
```

---

## 🧠 PyTorch Training & Model Export (`model/`)

### Requirements & Installation
```bash
# From repo root using Python 3.11+
pip install -r model/requirements.txt
```

### 1. PyTorch Model Training
Place **PlantVillage** dataset under `model/data/<DATASET_NAME>/` class subfolders.
```bash
cd model
python train.py --config_file config/plantai_resnet_18.yaml
```

### 2. Model Export Pipeline
```bash
cd model

# ONNX Export
python export/export_to_onnx.py --config_file config/plantai_resnet_18.yaml

# TorchScript (Lite Interpreter)
python export/export_to_torchscript.py --config_file config/plantai_resnet_18.yaml

# TensorFlow Lite Export (for Android app)
python export/export_to_tf_light.py --config_file config/plantai_resnet_18.yaml --output model.tflite
```

---

## 📊 Model Evaluation Results

- **Dataset**: PlantVillage (~54,305 leaf images across 38 classes)
- **Accuracy**: **93.14%**
- **Weighted Precision**: **92.90%**
- **Weighted Recall**: **93.14%**
- **Weighted F1-Score**: **92.50%**

---

## 📜 License & Acknowledgments

* **Dataset**: PlantVillage Dataset ([GitHub](https://github.com/spMohanty/PlantVillage-Dataset))
* **License**: MIT License
