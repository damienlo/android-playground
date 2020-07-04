plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.Custom.appPlugin)
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

    implementation(Libraries.lifecycleProcess)

    implementation(Injection.daggerHilt)
    implementation(Injection.daggerHiltLifecycle)
    kapt(Injection.daggerHiltCompiler)
    kapt(Injection.daggerHiltLifecycleCompiler)

    implementation(project(":core:android"))
    implementation(project(":core:coroutines"))
    implementation(project(":core:design"))
    implementation(project(":core:navigation"))

    implementation(project(":data:authentication"))
    implementation(project(":data:network"))
    implementation(project(":data:session"))

    implementation(project(":domain:common"))

    implementation(project(":feature:home"))
    implementation(project(":feature:registration"))

    testImplementation (TestLibraries.junit4)
    androidTestImplementation (TestLibraries.testRunner)
    androidTestImplementation (TestLibraries.espresso)
}