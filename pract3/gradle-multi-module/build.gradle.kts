plugins {
    id("java")
    id("org.example.code-parser-plugin")
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenLocal()
    gradlePluginPortal()
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}