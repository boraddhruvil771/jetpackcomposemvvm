plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.gradle.plugin) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.android.plugin) apply false
    alias(libs.plugins.kotlin.parcelize) apply false

}

allprojects {
    configurations.configureEach {
        resolutionStrategy {
            force("org.objenesis:objenesis:2.6")
        }
    }
}

tasks.register("clean") {
    delete(rootProject.layout.buildDirectory)
}