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

val kotlinCoroutinesVersion: String by project
val ktorVersion: String by project
val logbackVersion: String by project
val koinVersion: String by project

dependencies {
    implementation("com.expediagroup:graphql-kotlin-server:5.3.2")
    implementation("io.ktor", "ktor-server-core", ktorVersion)
    implementation("io.ktor", "ktor-server-netty", ktorVersion)
    implementation("ch.qos.logback", "logback-classic", logbackVersion)
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", kotlinCoroutinesVersion)
    implementation(kotlin("stdlib-jdk8"))

    // Koin Core features
    implementation("io.insert-koin:koin-core:$koinVersion")
    // Koin for Ktor
    implementation("io.insert-koin:koin-ktor:$koinVersion")
    // SLF4J Logger
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")
    // Koin Test features
    testImplementation("io.insert-koin:koin-test:$koinVersion")
}

graphql {
    schema {
        packages = listOf("com.expediagroup.graphql.examples.server.ktor")
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