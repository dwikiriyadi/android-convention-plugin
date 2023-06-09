import id.logee.configuration.ConventionBundle
import id.logee.configuration.ConventionDependency
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class OkHttpRetrofitConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                add("implementation", platform(ConventionDependency.okhttpBom))
                ConventionBundle.okhttpDependencies.forEach {
                    add("implementation", it)
                }
                ConventionBundle.retrofitDependencies.forEach {
                    add("implementation", it)
                }
            }
        }
    }
}