package com.github.umbra.muniapi.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class ExtractRuntimeTask : DefaultTask() {
    @get:OutputFile
    abstract val outputJar: RegularFileProperty

    @TaskAction
    fun extract() {
        val stream = ExtractRuntimeTask::class.java
            .getResourceAsStream("/embedded/sub.jar")
            ?: error("Embedded sub.jar missing from plugin jar")
        stream.use { input ->
            outputJar.get().asFile.apply { parentFile.mkdirs() }
                .outputStream().use { input.copyTo(it) }
        }
    }
}
