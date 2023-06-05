import com.google.devtools.ksp.gradle.KspExtension
import id.logee.configuration.ConventionBundle
import id.logee.configuration.ConventionDependency
import id.logee.configuration.RoomSchemaArgProvider
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.File

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply("com.google.devtools.ksp")

            extensions.configure<KspExtension> {
                arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
            }

            dependencies {
                ConventionBundle.roomDependencies.forEach {
                    add("implementation", it)
                }
                add("ksp", ConventionDependency.androidxRoomCompiler)
            }
        }
    }
}