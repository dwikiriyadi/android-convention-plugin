import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidDynamicFeatureConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with(pluginManager) {
                apply(libs.findPlugin("kotlin.android").get().get().pluginId)
                apply(libs.findPlugin("android.dynamic.feature").get().get().pluginId)
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = libs.findVersion("appCompileSdk").get().requiredVersion.toInt()

                defaultConfig {
                    minSdk = libs.findVersion("appMinSdk").get().requiredVersion.toInt()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            dependencies {
                add("implementation", project(":app"))
                add("implementation", libs.findBundle("androidx.core.ktx").get())
                add("testImplementation", libs.findBundle("testDependencies").get())
                add("androidTestImplementation", libs.findBundle("androidTestDependencies").get())
            }
        }
    }
}