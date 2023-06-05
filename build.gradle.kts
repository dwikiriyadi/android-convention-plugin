import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "1.1.0"
}

group = "io.dwikiriyadi"
version = "1.0"
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
    website.set("https://github.com/dwikiriyadi/android-convention-plugin")
    vcsUrl.set("https://github.com/dwikiriyadi/android-convention-plugin.git")

    plugins {
        register("androidApplication") {
            id = "io.dwikiriyadi.android.application"
            displayName = "Android Application"
            description = "Android Application Convention Plugin"
            tags.set(listOf("android", "application", "convention", "plugins"))
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "io.dwikiriyadi.android.application.compose"
            displayName = "Android Application Compose"
            description = "Android Application Compose Convention Plugin"
            tags.set(listOf("android", "application", "compose", "convention", "plugins"))
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidDynamicFeature") {
            id = "io.dwikiriyadi.android.dynamic.feature"
            displayName = "Android Dynamic Feature"
            description = "Android Dynamic Feature Convention Plugin"
            tags.set(listOf("android", "dynamic feature", "convention", "plugins"))
            implementationClass = "AndroidDynamicFeatureConventionPlugin"
        }
        register("androidDynamicFeatureCompose") {
            id = "io.dwikiriyadi.android.dynamic.feature.compose"
            displayName = "Android Dynamic Feature Compose"
            description = "Android Dynamic Feature Compose Convention Plugin"
            tags.set(listOf("android", "dynamic feature", "compose", "convention", "plugins"))
            implementationClass = "AndroidDynamicFeatureComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "io.dwikiriyadi.android.library"
            displayName = "Android Library"
            description = "Android Library Convention Plugin"
            tags.set(listOf("android", "library", "convention", "plugins"))
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "io.dwikiriyadi.android.library.compose"
            displayName = "Android Library Compose"
            description = "Android Library Compose Convention Plugin"
            tags.set(listOf("android", "Library", "compose", "convention", "plugins"))
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "io.dwikiriyadi.android.hilt"
            displayName = "Android Hilt"
            description = "Android Hilt Convention Plugin"
            tags.set(listOf("android", "hilt", "di", "convention", "plugins"))
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "io.dwikiriyadi.android.room"
            displayName = "Android Room"
            description = "Android Room Convention Plugin"
            tags.set(listOf("android", "room", "db", "convention", "plugins"))
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("okhttpRetrofit") {
            id = "io.dwikiriyadi.network.okhttp.retrofit"
            displayName = "OkHttp Retrofit"
            description = "OkHttp Retrofit Convention Plugin"
            tags.set(listOf("network", "okhttp", "retrofit", "convention", "plugins"))
            implementationClass = "OkHttpRetrofitConventionPlugin"
        }
        register("publish") {
            id = "io.dwikiriyadi.library.publish"
            displayName = "Publish"
            description = "Publish Convention Plugin"
            tags.set(listOf("maven", "publish", "convention", "plugins"))
            implementationClass = "PublishConventionPlugin"
        }
    }
}