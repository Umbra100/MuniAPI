package com.github.umbra.muniapi.tasks

import com.github.umbra.muniapi.models.Entrypoint
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class ExtractEmbeddedJar : DefaultTask() {
    @get:Input
    abstract val resource: Property<String>
    
    @get:OutputFile
    abstract val outputJar: RegularFileProperty

    @TaskAction
    fun extract() {
        val stream = ExtractEmbeddedJar::class.java
            .getResourceAsStream(resource.get())
            ?: error("Embedded jar missing from plugin jar: '${resource.get()}'")
            
        stream.use { input ->
            outputJar.get().asFile.apply { parentFile.mkdirs() }
                .outputStream().use { input.copyTo(it) }
        }
    }
}
