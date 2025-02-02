plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.mobilerestaurant"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mobilerestaurant"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.convertergson)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
//    implementation("com.android.support:design:28.0.0") {
//        exclude(group = "com.android.support", module = "support-v4")
//    }
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}