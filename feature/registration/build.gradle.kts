plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.Custom.featureLibPlugin)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.safeArgs)
    id(BuildPlugins.daggerHilt)
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.material)

    implementation(project(":core:android"))
    implementation(project(":core:design"))
    implementation(project(":core:navigation"))

    implementation(project(":domain:common"))

    implementation(Injection.daggerHilt)
    implementation(Injection.daggerHiltLifecycle)
    kapt(Injection.daggerHiltCompiler)
    kapt(Injection.daggerHiltLifecycleCompiler)

    implementation(Libraries.fragment)
    implementation(Libraries.viewModelKtx)

    implementation(Libraries.navigationUiKtx)
    implementation(Libraries.navigationKtx)
}