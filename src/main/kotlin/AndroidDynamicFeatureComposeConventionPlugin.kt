import com.android.build.api.dsl.DynamicFeatureExtension
import id.logee.configuration.ConventionVersion
import id.logee.configuration.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidDynamicFeatureComposeConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("com.android.dynamic-feature")
            }

            extensions.configure<DynamicFeatureExtension> {
                compileSdk = ConventionVersion.compileSdk.toInt()

                defaultConfig {
                    minSdk = ConventionVersion.minSdk.toInt()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                configureAndroidCompose(this)
            }

            dependencies {
                add("implementation", project(":app"))
            }
        }
    }
}