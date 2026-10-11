package com.github.umbra.muniapi.tasks

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar

abstract class BuildBundledJar : Jar() {
    @get:InputFiles
    abstract val sources: ConfigurableFileCollection
    
    init {
        group = "build"
        description = "Bundles any jars provided in settings with the consumer's project code into one jar."
        
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
        
        archiveClassifier.convention( "bundled" )
        from(sources)
    }
}
