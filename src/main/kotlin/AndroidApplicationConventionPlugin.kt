import com.android.build.api.dsl.ApplicationExtension
import io.dwikiriyadi.android.configuration.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with(pluginManager) {
                apply(libs.findPlugin("android.application").get().get().pluginId)
                apply(libs.findPlugin("kotlin.android").get().get().pluginId)
                apply(libs.findPlugin("kotlin.kapt").get().get().pluginId)
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = libs.findVersion("appCompileSdk").get().requiredVersion.toInt()

                defaultConfig {
                    minSdk = libs.findVersion("appMinSdk").get().requiredVersion.toInt()
                    targetSdk = libs.findVersion("appTargetSdk").get().requiredVersion.toInt()
                    versionCode = libs.generateVersionCode()
                    versionName = libs.generateVersionName()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                configureKotlinJvm()
            }

            extensions.configure<KaptExtension> {
                correctErrorTypes = true
            }
        }
    }

    private fun VersionCatalog.generateVersionName(): String {
        val major = findVersion("appVersionMajor").get().requiredVersion.toInt()
        val minor = findVersion("appVersionMinor").get().requiredVersion.toInt()
        val patch = findVersion("appVersionPatch").get().requiredVersion.toInt()

        return "$major.$minor.$patch"
    }

    private fun VersionCatalog.generateVersionCode(): Int {
        val major = findVersion("appVersionMajor").get().requiredVersion.toInt()
        val minor = findVersion("appVersionMinor").get().requiredVersion.toInt()
        val patch = findVersion("appVersionPatch").get().requiredVersion.toInt()

        // TODO: temporary calculation
        return major * 100000 + minor * 10000 + patch * 1000 + 1
    }
}