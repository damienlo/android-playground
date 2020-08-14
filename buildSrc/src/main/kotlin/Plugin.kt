@file:Suppress("unused", "UnstableApiUsage")

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppPlugin : BasePlugin() {
    override fun applyOnApp(appExtension: AppExtension) {
        super.applyOnApp(appExtension)
        with(appExtension) {
            configureAndroidApp()
            configureAndroidFlavors(isApp = true)
            configureAndroidCommonOptions()
        }
    }

    override fun applyOnApplication(application: ApplicationExtension<*, *, *, *, *, *, *, *, *, *, *>) {
        super.applyOnApplication(application)
        application.configureViewBindings()
    }
}

class FeatureLibPlugin : BasePlugin() {

    override fun applyOnLibrary(libraryExtension: LibraryExtension) {
        super.applyOnLibrary(libraryExtension)
        with(libraryExtension) {
            configureAndroidFlavors(isApp = false)
            configureAndroidCommonOptions()
            configureViewBindings()
        }
    }
}

class CommonLibPlugin : BasePlugin() {

    override fun applyOnLibrary(libraryExtension: LibraryExtension) {
        super.applyOnLibrary(libraryExtension)
        with(libraryExtension) {
            configureAndroidCommonOptions()
        }
    }
}

open class BasePlugin : Plugin<Project> {

    final override fun apply(target: Project) {
        target.extensions.findByType(AppExtension::class.java)?.run {
            applyOnApp(this)
        }
        target.extensions.findByType(ApplicationExtension::class.java)?.run {
            applyOnApplication(this)
        }

        target.extensions.findByType(LibraryExtension::class.java)?.run {
            applyOnLibrary(this)
        }
        target.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    open fun applyOnApp(appExtension: AppExtension) {
        // to override
    }

    open fun applyOnApplication(application: ApplicationExtension<*,*,*,*,*,*,*,*,*,*,*>) {
        // to override
    }

    open fun applyOnLibrary(libraryExtension: LibraryExtension) {
        // to override
    }
}