rootProject.name = "gradle-multi-module"
include(":app", ":dictionary", ":parser", ":tokenizer", ":utils")

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        id("org.example.code-parser-plugin") version "1.0.0"
    }
}
