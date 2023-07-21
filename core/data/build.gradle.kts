plugins {
    id("codingchallengeweather.android.library")
    id("codingchallengeweather.android.library.jacoco")
    id("codingchallengeweather.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "io.thoughtleaps.codingchallengeweather.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:datastore"))
    implementation(project(":core:location"))
    implementation(project(":core:network"))
    implementation(project(":core:model"))
    
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
}
