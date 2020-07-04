const val kotlinVersion = "1.3.70"
const val buildToolsVersion = "4.0.0"
const val safeArgsVersion = "2.2.1"
const val daggerHiltVersion = "2.28.1-alpha"

object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:$buildToolsVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val safeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$safeArgsVersion"
    const val daggerHiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$daggerHiltVersion"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
    const val daggerHilt = "dagger.hilt.android.plugin"

    object Custom {
        const val appPlugin = "AppPlugin"
        const val featureLibPlugin = "FeatureLibPlugin"
        const val commonLibPlugin = "CommonLibPlugin"
    }
}


object AndroidSdk {
    const val minSdkVersion = 21
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
}

object App {
    const val applicationId = "com.vito.cornelius.archer"
    const val versionCode = 1
    const val versionName = "1.0"
}

object Libraries {
    private object Versions {
        const val appCompat = "1.1.0"
        const val material = "1.3.0-alpha01"
        const val constraintLayout = "1.1.3"
        const val coreKtx = "1.2.0"

        const val fragment = "1.2.5"
        const val navigation = "2.3.0"
        const val viewModel = "2.2.0"
        const val livedata = "2.2.0"
        const val lifecycle = "2.2.0"

        const val coroutinesAndroidVersion = "1.3.7"

        const val glide = "4.11.0"

        const val moshi = "1.9.3"
        const val moshiConverterVersion = "2.8.1"
        const val retrofitVersion = "2.9.0"
        const val retrofitCoroutinesVersion = "0.9.2"
        const val okhttpVersion = "4.7.2"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val navigation = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.viewModel}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata:${Versions.livedata}"
    const val liveDatalKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata}"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

    const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.githubbumptech.glide:compiler:${Versions.glide}"

    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverterVersion}"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitCoroutines =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutinesVersion}"
}

object Injection {
    private object Versions {
        const val daggerHilLifecycleVersion = "1.0.0-alpha01"
    }

    const val daggerHilt = "com.google.dagger:hilt-android:$daggerHiltVersion"
    const val daggerHiltTest = "com.google.dagger:hilt-android-testing:$daggerHiltVersion"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:$daggerHiltVersion"

    const val daggerHiltLifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerHilLifecycleVersion}"
    const val daggerHiltLifecycleCompiler = "androidx.hilt:hilt-compiler:${Versions.daggerHilLifecycleVersion}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13"
        const val testRunner = "1.2.0"
        const val espresso = "3.2.0"
        const val mockito = "3.3.3  "
        const val mockitoInline = "3.3.3"
        const val mockitoKotlin = "2.2.0"
        const val androidxCore = "2.1.0"
        const val coroutinesTest = "1.3.7"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"

    const val androidXCore = "androidx.arch.core:core-testing:${Versions.androidxCore}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
}