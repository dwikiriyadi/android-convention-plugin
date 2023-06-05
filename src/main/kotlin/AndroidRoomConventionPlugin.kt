import com.google.devtools.ksp.gradle.KspExtension
import io.dwikiriyadi.android.configuration.ConventionBundle
import io.dwikiriyadi.android.configuration.ConventionDependency
import io.dwikiriyadi.android.configuration.RoomSchemaArgProvider
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
                add("implementation", ConventionBundle.roomDependencies)
                add("ksp", ConventionDependency.androidxRoomCompiler)
            }
        }
    }
}