plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serilazation)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.monaser.qrcodegen"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.monaser.qrcodegen"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    //appcompat
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.androidx.core.splashscreen)
    //navigation

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.kolinx.serialization.json)
    implementation("com.airbnb.android:lottie-compose:6.0.1")




    //QR code scanner
    implementation("com.google.mlkit:barcode-scanning:17.1.0")
    //Data store
    implementation ("androidx.datastore:datastore-preferences:1.1.1")
    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp("com.google.dagger:hilt-compiler:2.48")
}