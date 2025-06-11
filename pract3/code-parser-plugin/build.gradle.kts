plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    id("maven-publish")
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation(kotlin("stdlib"))
}

gradlePlugin {
    plugins {
        create("code-parser-plugin") {
            id = "org.example.code-parser-plugin"
            implementationClass = "org.example.CodeParserPlugin"
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}