# Rhymi

## Overview
*An app to track dance progress *

---
### Prerequisites

- **JDK 17** is required for building this project.  
  Make sure your IDE and terminal are using the same JDK version.

## Setup

### Clone the repo
```bash
git clone https://github.com/molly34762/rhymi.git
cd rhymi
```

### Build the project
```bash
./gradlew clean build
```

### Install the project on the emulator
```bash
# Make sure an emulator is running first
./gradlew installDebug && adb shell am start -n com.example.rhymi/.MainActivity
```
### Run unit tests
```bash
./gradlew test
```


