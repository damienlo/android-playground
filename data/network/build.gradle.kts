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
    implementation(Injection.daggerHiltLifecycle)
    kapt(Injection.daggerHiltCompiler)
    kapt(Injection.daggerHiltLifecycleCompiler)

    implementation(Libraries.okHttp)
    implementation(Libraries.okHttpLoggingInterceptor)

    api(Libraries.retrofit)
    implementation(Libraries.retrofitCoroutines)

    api(Libraries.moshi)
    implementation(Libraries.moshiConverter)
    kapt(Libraries.moshiCodeGen)
}