import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "io.dwikiriyadi.android.convention"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.0.0")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    compileOnly("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.8.20-1.0.11")
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "dwkryd.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "dwkryd.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidDynamicFeature") {
            id = "dwkryd.android.dynamic.feature"
            implementationClass = "AndroidDynamicFeatureConventionPlugin"
        }
        register("androidDynamicFeatureCompose") {
            id = "dwkryd.android.dynamic.feature.compose"
            implementationClass = "AndroidDynamicFeatureComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "dwkryd.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "dwkryd.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "dwkryd.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "dwkryd.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("okhttpRetrofit") {
            id = "dwkryd.network.okhttp.retrofit"
            implementationClass = "OkhttpRetrofitConventionPlugin"
        }
        register("publish") {
            id = "dwkryd.library.publish"
            implementationClass = "PublishConventionPlugin"
        }
    }
}