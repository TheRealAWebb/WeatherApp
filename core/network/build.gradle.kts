plugins {
    id("codingchallengeweather.android.library")
    id("codingchallengeweather.android.library.jacoco")
    id("codingchallengeweather.android.hilt")
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "io.thoughtleaps.codingchallengeweather.core.network"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(project(":core:common"))
    testImplementation(project(":core:testing"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.okhttp.logging)

    testImplementation(project(":core:testing"))
}