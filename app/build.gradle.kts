plugins {
    id("codingchallengeweather.android.application")
    id("codingchallengeweather.android.application.compose")
    id("codingchallengeweather.android.hilt")
}

android {
    namespace = "io.thoughtleaps.codingchallengeweather"
    defaultConfig {
        applicationId = "io.thoughtleaps.codingchallengeweather"
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":features:weather"))
    implementation(project(":features:search"))

    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    androidTestImplementation(kotlin("test"))
    androidTestImplementation(project(":core:network"))
    debugImplementation(libs.androidx.compose.ui.testManifest)
}