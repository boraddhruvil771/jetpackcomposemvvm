plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.gradle.plugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
    alias (libs.plugins.hilt.android.plugin)
}

android {
    compileSdk = 34
    namespace = "com.example.jetpackcomposebase"

    defaultConfig {
        applicationId = "com.example.jetpackcomposebase"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.appname.structure.user.utils.HiltTestRunner"
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }


    flavorDimensions += "environment"

    productFlavors {
        create("dev") {
            dimension = "environment"
        }
        create("stage") {
            dimension = "environment"
        }
        create("prod") {
            dimension = "environment"
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
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
        buildConfig = true
        viewBinding = true
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Navigation component
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)

    // Data binding compiler
    ksp(libs.compiler)
    // Crypto for encrypted shared preferences
    implementation(libs.androidx.security.crypto)
    implementation(libs.accompanist.pager)

    // Jackson parser
    implementation(libs.jackson.databind)
    implementation(libs.jackson.core)
    implementation(libs.jackson.annotations)
    implementation(libs.jackson.module.kotlin)

    // SDP and SSP
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    // Retrofit, OkHttp, Jackson interceptor
    implementation(platform(libs.okhttp.bom))
    implementation(libs.converter.jackson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.converter.scalars)

    // Image downloading
    implementation(libs.glide)
    ksp(libs.glide.compiler)

    // Android 12 Splash Screen
    implementation(libs.androidx.core.splashscreen)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // DataStore
    implementation(libs.androidx.datastore.preferences)


    implementation(platform(libs.androidx.compose.bom))

    // Material Design
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material)

    // Compose UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material3.window.size)
    implementation(libs.accompanist.pager)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.runtime.rxjava2)

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Integration with observables
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose image loading
    implementation(libs.coil.compose)
    implementation(libs.accompanist.glide)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Tab pager Compose
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    // Swipe refresh
    implementation(libs.accompanist.swiperefresh)

    implementation(libs.sdp.ssp.compose.multiplatform)

    // Firebase
    implementation(libs.play.services.base)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.firebase.config)

    // Rive animation
    implementation(libs.rive.android)
    implementation(libs.androidx.startup.runtime)

    // OkHttp Profiler
    implementation(libs.okhttpprofiler)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Constraint Layout Compose
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")


    // Sentry
    implementation(libs.sentry.android)
    implementation(libs.sentry.compose.android)

    // Webview
    implementation(libs.androidx.webkit)

}