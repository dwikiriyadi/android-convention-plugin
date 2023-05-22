import com.google.devtools.ksp.gradle.KspExtension
import io.dwikiriyadi.android.configuration.RoomSchemaArgProvider
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.io.File

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            pluginManager.apply(libs.findPlugin("google.ksp").get().get().pluginId)

            extensions.configure<KspExtension> {
                arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
            }

            dependencies {
                add("implementation", libs.findBundle("roomDependencies").get())
                add("ksp", libs.findLibrary("androidx.room.compiler").get())
            }
        }
    }
}