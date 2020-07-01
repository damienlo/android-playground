plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.Custom.commonLibPlugin)
    id(BuildPlugins.kotlinAndroid)
}

dependencies {
    implementation(Libraries.kotlinStdLib)
}