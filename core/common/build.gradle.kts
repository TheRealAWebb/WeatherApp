plugins {
    id("codingchallengeweather.android.library")
    id("codingchallengeweather.android.library.jacoco")
    id("codingchallengeweather.android.hilt")
}

android {
    namespace = "io.thoughtleaps.codingchallengeweather.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(project(":core:testing"))
}