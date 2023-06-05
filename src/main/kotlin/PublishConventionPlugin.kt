import io.dwikiriyadi.android.configuration.PublishConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
class PublishConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply("maven-publish")

            val configuration = extensions.create("publishConfiguration", PublishConfiguration::class.java)


            afterEvaluate {
                extensions.configure<PublishingExtension> {
                    publications {
                        val configGroupId = configuration.groupId
                        val configArtifactId = configuration.artifactId
                        val configVersion = configuration.version

                        create("library", MavenPublication::class.java) {
                            artifact(tasks["bundleReleaseAar"])
                            groupId = configGroupId
                            artifactId = configArtifactId
                            version = configVersion

                            pom.withXml {
                                val dependenciesNode = asNode().appendNode("dependencies")

                                configurations["api"].allDependencies.forEach {
                                    val dependencyNode = dependenciesNode.appendNode("dependency")
                                    dependencyNode.appendNode("groupId", it.group)
                                    dependencyNode.appendNode("artifactId", it.name)
                                    dependencyNode.appendNode("version", it.version)
                                }
                            }
                        }
                    }

                    // TODO: change this configuration
                    repositories {
                        maven {
                            name = "nexus"
                            url = uri("https://nexus.playcourt.id/repository/logee")
                            credentials {
                                username = "logee"
                                password = "mobileteam123"
                            }
                        }
                    }
                }
            }
        }
    }
}