plugins {
    id("codingchallengeweather.android.library")
    id("codingchallengeweather.android.library.compose")
    id("codingchallengeweather.android.hilt")
}

android {

    namespace = "io.thoughtleaps.codingchallengeweather.core.testing"
}

dependencies {
    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)

    debugApi(libs.androidx.compose.ui.testManifest)

    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:location"))
    implementation(libs.kotlinx.datetime)
}
