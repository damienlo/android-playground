@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension

internal fun LibraryExtension.configureViewBindings() {
    buildFeatures {
        viewBinding = true
    }
}
internal fun ApplicationExtension<*, *, *, *, *, *, *, *, *, *, *>.configureViewBindings() {
    buildFeatures {
        viewBinding = true
    }
}