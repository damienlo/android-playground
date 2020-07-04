plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.Custom.commonLibPlugin)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.daggerHilt)
}

dependencies {
    implementation(Libraries.kotlinStdLib)

    implementation(Libraries.coroutines)

    implementation(Injection.daggerHilt)
    kapt(Injection.daggerHiltCompiler)

    implementation(project(":data:network"))
    implementation(project(":data:session"))
    implementation(project(":domain:common"))

    api(Libraries.moshi)
    implementation(Libraries.moshiConverter)
    kapt(Libraries.moshiCodeGen)
}