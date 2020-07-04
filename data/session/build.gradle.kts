plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.Custom.commonLibPlugin)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.daggerHilt)
}

dependencies {
    implementation(Libraries.kotlinStdLib)

    implementation(Injection.daggerHilt)
    kapt(Injection.daggerHiltCompiler)

    implementation(Libraries.moshi)
    kapt(Libraries.moshiCodeGen)

    implementation(project(":domain:common"))

    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockitoInline)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(TestLibraries.coroutinesTest)
}