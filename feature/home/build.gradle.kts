plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.safeArgs)
    id(BuildPlugins.daggerHilt)
}

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    testOptions {
        unitTests.apply {
            isReturnDefaultValues = true
        }
    }
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

    implementation(project(":data:network"))

    implementation(Injection.daggerHilt)
    implementation(Injection.daggerHiltLifecycle)
    kapt(Injection.daggerHiltCompiler)
    kapt(Injection.daggerHiltLifecycleCompiler)

    implementation(Libraries.fragment)
    implementation(Libraries.viewModelKtx)

    implementation(Libraries.navigationUiKtx)
    implementation(Libraries.navigationKtx)

    implementation(Libraries.glide)
}