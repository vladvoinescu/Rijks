plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "top.softmind.data"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}