plugins {
    id("codingchallengeweather.android.feature")
    id("codingchallengeweather.android.library.compose")
    id("codingchallengeweather.android.library.jacoco")
}

android {
    namespace = "io.thoughtleaps.codingchallengeweather.features.weather"
}

dependencies {
    implementation(libs.androidx.compose.material3.windowSizeClass)


    implementation (platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation ("androidx.activity:activity-compose:1.5.1")
    implementation (platform ("androidx.compose:compose-bom:2022.10.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation (platform ("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")
}