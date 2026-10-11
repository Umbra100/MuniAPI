package com.github.umbra.muniapi.tasks.build

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar

abstract class BuildFinalJar : Jar() {
    @get:Input
    abstract val jarName: Property<String>
    
    @get:InputFiles
    abstract val includeJars: ConfigurableFileCollection
    
    init {
        group = "build"
        
    }
    
    @TaskAction
    fun buildEntrypoint(){
        val main = project.extensions
            .getByType(SourceSetContainer::class.java)
            .getByName("main")
        
        archiveClassifier.set("bundled")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        
        from(main.output)
        from(includeJars)
    }
    
    fun getTaskName(): String {
        return "bundle${jarName.get().replaceFirstChar { c -> c.uppercase() }}"
    }
}
