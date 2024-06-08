# QR Code Generator App

## Overview

This project is a QR Code Generator App that dynamically generates a QR code based on user input and selected payment options. The QR code updates every 5 seconds and includes encrypted user information and payment details.

## Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Architecture](#architecture)
- [Setup](#setup)
- [Usage](#usage)
- [Libraries](#Libraries)

## Features

- User input form to capture user details (Name, Age, Gender, Phone Number)
- Payment options selection
- Dynamic QR code generation every 5 seconds
- Encrypted text in QR code containing user and payment information

## Screenshots


https://github.com/mahmmedn19/QRCode-Generator/assets/100851080/104206f5-7a6b-4279-9607-8ce098774a50

<div align="center">
  <img src="https://github.com/mahmmedn19/QRCode-Generator/assets/100851080/d4a8cec4-ff53-4a1d-8997-006b7c7d547f" alt="image1" width="200" style="border: 1px solid #000">
  <img src="https://github.com/mahmmedn19/QRCode-Generator/assets/100851080/59ec416a-60e6-46f7-aed4-57330c2939cb" alt="image2" width="200" style="border: 1px solid #000">
  <img src="https://github.com/mahmmedn19/QRCode-Generator/assets/100851080/f326d675-89ef-49c4-bb91-3ceb1c081f36" alt="image3" width="200" style="border: 1px solid #000">
  <img src="https://github.com/mahmmedn19/QRCode-Generator/assets/100851080/1ee24d31-f428-4a23-9136-1b36b0a134f1" alt="image4" width="200" style="border: 1px solid #000">
  
</div>


## Architecture

### MVVM (Model-View-ViewModel) Pattern

The project follows the MVVM architecture pattern, which separates the user interface (View) from the business logic (ViewModel) and data (Model). This approach promotes a clear separation of concerns, making the codebase more modular, testable, and maintainable.

- **DataStoreManager:** Interface to manage data persistence.
- **DataStoreManagerImpl:** Implementation of DataStoreManager using Jetpack DataStore for local storage.
- **UiStates:** Data class representing the state of each screen.
- **ViewModel:** The ViewModel manages the UI-related data in a lifecycle-conscious way. It interacts with the Model to fetch data and prepares it for the View.

## Setup

1. **Clone the repository:**

```bash
git clone https://github.com/mahmmedn19/QRCode-Generator.git
cd qrcode-generator-app 
```

Open the project in Android Studio.

Build the project to download all necessary dependencies.

Run the application on an Android device or emulator.

## Usage
Navigate through the app:

Fill in the user details on the User Form screen.
Select a payment option on the Payment Options screen.
View the dynamically updating QR code on the QR Code screen.

## Libraries
- **Jetpack Compose:** Modern UI toolkit for building native Android UI.
- **Jetpack DataStore:** Jetpack library for data storage, used for local storage of user and payment information.
- **Hilt:** Dependency injection library for Android, used for managing dependencies.
- **Airbnb Lottie:** Library for adding animations to Android apps, used for displaying animated QR code.
- **ZXing:** Library for QR code generation, used for dynamically generating QR codes.
