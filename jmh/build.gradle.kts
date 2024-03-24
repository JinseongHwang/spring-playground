plugins {
    kotlin("jvm") version "1.9.0"
    id("me.champeau.jmh") version "0.7.2" apply true
    application
}

group = "me.study"
version = "1.0-SNAPSHOT"

val jmhVersion = 0.9

repositories {
    mavenCentral()
}

dependencies {
    // jmh
    jmh("org.openjdk.jmh:jmh-core:$jmhVersion")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:$jmhVersion")

    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}