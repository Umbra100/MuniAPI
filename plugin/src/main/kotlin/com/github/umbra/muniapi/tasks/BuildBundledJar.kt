// Author: Umbra
// Date: Oct 10th 2026

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

/** Bundles multiple sourcesets into one fat jar. Used to construct final addon builds. */
abstract class BuildBundledJar : Jar() {
    /** Used to specify what sourcesets to include in the bundled jar.*/
    @get:InputFiles
    abstract val sources: ConfigurableFileCollection
    
    init {
        group = "build"
        description = "Bundles any jars provided in settings with the consumer's project code into one jar."
        
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE // Overwrite old resources
        isPreserveFileTimestamps = false // Reproducible builds
        isReproducibleFileOrder = true
        
        archiveClassifier.convention( "bundled" )
        from(sources)
    }
}
