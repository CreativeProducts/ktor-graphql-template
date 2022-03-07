import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

description = "An Example GraphQL service served by Ktor"

plugins {
    id("application")
    id("com.expediagroup.graphql") version "5.3.2"
    kotlin("jvm") version "1.6.20-RC"
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

val koinVersion: String by project

dependencies {
    implementation(libs.graphqlKotlinServer)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback)
    implementation(libs.kotlinx.coroutines)
    implementation(kotlin("stdlib-jdk8"))

    // Koin Core features
    implementation(libs.koin.core)
    // Koin for Ktor
    implementation(libs.koin.ktor)
    implementation(libs.koin.slf4j)
    testImplementation(libs.koin.test)
}

graphql {
    schema {
        packages = listOf("com.accenture.creativeproducts.examples.server.data")
    }
}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}