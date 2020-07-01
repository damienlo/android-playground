import com.android.build.gradle.TestedExtension

internal fun TestedExtension.configureAndroidApp() {
    buildToolsVersion(buildToolsVersion)

    defaultConfig {
        applicationId = App.applicationId
    }
}