pluginManagement {
    val dokkaVersion: String by settings
    val kotlinVersion: String by settings

    plugins {
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
        id("org.jetbrains.dokka") version dokkaVersion
    }
}

rootProject.name = "graphql-kotlin"