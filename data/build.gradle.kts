plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}