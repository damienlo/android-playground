import com.android.build.gradle.TestedExtension

internal fun TestedExtension.configureAndroidFlavors(isApp: Boolean) {
    flavorDimensions("version")
    productFlavors {
        create("staging") {
            isDefault = true
            dimension = "version"
            if (isApp) {
                applicationIdSuffix = ".staging"
            }
            versionNameSuffix = "-staging"
        }
        create("prep") {
            dimension = "version"
            if (isApp) {
                applicationIdSuffix = ".prep"
            }
        }
        create("prod") {
            dimension = "version"
        }
    }
}