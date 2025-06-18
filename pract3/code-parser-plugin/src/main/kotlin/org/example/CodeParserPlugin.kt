package org.example

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class CodeParserPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register("concatenate") {
            doLast {
                val outputDir = File(project.projectDir, "code")
                outputDir.mkdirs()

                val outputFile = File(outputDir, "code.txt")
                outputFile.delete()

                concatenateJavaFiles(project, outputFile)
            }
        }
    }

    private fun concatenateJavaFiles(project: Project, outputFile: File) {
        project.allprojects.forEach { subProject ->
            subProject.fileTree(subProject.projectDir).matching {
                include("**/*.java")
            }.forEach { javaFile ->
                val content = javaFile.readText()
                outputFile.appendText("// File: ${javaFile.path}\n")
                outputFile.appendText("$content\n\n")
            }
        }
    }

}