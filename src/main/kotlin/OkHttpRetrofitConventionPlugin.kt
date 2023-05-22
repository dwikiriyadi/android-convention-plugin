import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class OkhttpRetrofitConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                val okhttpBom = libs.findLibrary("okhttp.bom").get()
                add("implementation", platform(okhttpBom))
                add("implementation", libs.findBundle("okhttpDependencies").get())
                add("implementation", libs.findBundle("retrofitDependencies").get())
            }
        }
    }
}