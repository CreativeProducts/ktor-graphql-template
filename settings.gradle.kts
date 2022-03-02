pluginManagement {
    val dokkaVersion: String by settings
    val kotlinVersion: String by settings
    val kotlinxBenchmarkVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
        id("org.jetbrains.dokka") version dokkaVersion
        id("org.jetbrains.kotlinx.benchmark") version kotlinxBenchmarkVersion
    }
}

rootProject.name = "graphql-kotlin"