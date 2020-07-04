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

    implementation(Libraries.okHttp)
    implementation(Libraries.okHttpLoggingInterceptor)

    api(Libraries.retrofit)
    implementation(Libraries.retrofitCoroutines)

    implementation(Libraries.moshi)
    implementation(Libraries.moshiConverter)
}