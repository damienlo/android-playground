plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.Custom.commonLibPlugin)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.daggerHilt)
}

dependencies {
    implementation(Libraries.kotlinStdLib)

    api(Libraries.coroutines)

    implementation(Injection.daggerHilt)
    kapt(Injection.daggerHiltCompiler)
}