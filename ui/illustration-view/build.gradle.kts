@file:Suppress("UnstableApiUsage")

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.Custom.commonLibPlugin)
    id(BuildPlugins.kotlinAndroid)
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.material)
    implementation(Libraries.constraintLayout)

    implementation(project(":core:design"))
}