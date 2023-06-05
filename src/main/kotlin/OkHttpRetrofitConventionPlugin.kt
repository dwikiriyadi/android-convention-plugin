import io.dwikiriyadi.android.configuration.ConventionBundle
import io.dwikiriyadi.android.configuration.ConventionDependency
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class OkHttpRetrofitConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                add("implementation", platform(ConventionDependency.okhttpBom))
                add("implementation", ConventionBundle.okhttpDependencies)
                add("implementation", ConventionBundle.retrofitDependencies)
            }
        }
    }
}