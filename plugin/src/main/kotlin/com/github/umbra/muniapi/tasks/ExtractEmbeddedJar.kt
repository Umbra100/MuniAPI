// Author: Umbra
// Date: Oct 9th 2026

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

/** Extracts an embedded jar resource from the plugin's jar build to a set location. */
abstract class ExtractEmbeddedJar : DefaultTask() {
    /** Target jar file to extract from embedded resources in plugin. Do not include .jar extension. */
    @get:Input
    abstract val jarName: Property<String>
    
    /** Output jar path to extract the resource to. Include the relatived file path from build dir and .jar extension */
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
