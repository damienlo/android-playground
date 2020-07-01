val kotlinVersion = "1.3.70"
val buildToolsVersion = "4.0.0"

repositories {
    jcenter()
    google()
}

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}


gradlePlugin {
    plugins {
        register("app-plugin") {
            id = "AppPlugin"
            implementationClass = "AppPlugin"
        }
        register("feature-lib-plugin") {
            id = "FeatureLibPlugin"
            implementationClass = "FeatureLibPlugin"
        }
        register("common-lib-plugin") {
            id = "CommonLibPlugin"
            implementationClass = "CommonLibPlugin"
        }
    }
}


dependencies {
    implementation("com.android.tools.build:gradle:$buildToolsVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
}
