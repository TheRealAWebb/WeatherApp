plugins {
    id("codingchallengeweather.android.library")
    id("codingchallengeweather.android.library.compose")
    id("codingchallengeweather.android.hilt")
}

android {
    namespace = "io.thoughtleaps.codingchallengeweather.core.location"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.google.location)


    implementation(libs.kotlinx.coroutines.play)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
}
