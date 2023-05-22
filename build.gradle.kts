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
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradle)
    compileOnly(libs.firebase.perf.gradle)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "dwikiriyadi.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidDynamicFeature") {
            id = "dwikiriyadi.android.dynamic.feature"
            implementationClass = "AndroidDynamicFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "dwikiriyadi.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "dwikiriyadi.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("okhttpRetrofit") {
            id = "dwikiriyadi.okhttp.retrofit"
            implementationClass = "OkhttpRetrofitConventionPlugin"
        }
    }
}