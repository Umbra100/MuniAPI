package com.github.umbra.muniapi.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class ExtractEmbeddedJar : DefaultTask() {
    @get:Input
    abstract val jarName: Property<String>
    
    @get:OutputFile
    abstract val outputJar: RegularFileProperty

    @TaskAction
    fun run() {
        val stream = ExtractEmbeddedJar::class.java
            .getResourceAsStream("/embedded/${jarName.get()}.jar")
            ?: error("Embedded jar missing from plugin jar: '${jarName.get()}.jar'")
        
        stream.use { input ->
            outputJar.get().asFile.apply{ parentFile.mkdirs() }
                .outputStream().use { input.copyTo(it) }
        }
    }
}
