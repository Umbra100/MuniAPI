// --- Basic Addon Build Settings ---
plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    java
    application
    // Shadow plugin for creating fatjars
    // id 'com.gradleup.shadow' version "${shadowjar_version}"
    // // Adds the runServer task to the project so that you can run paper mc servers inside the IDE
    id ("xyz.jpenilla.run-paper") version "3.1.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()

    // Use the official PaperMC repository for paper dependencies
    maven(url = "https://repo.papermc.io/repository/maven-public/") {
        name = "papermc"
    }
}


dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)

    // Paper API dependency
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
}

// Include resources folder in the final jar file during compilation
sourceSets {
    main {
        resources {
            srcDirs("src/main/resources")
        }
    }
}   


// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

base {
    archivesName.set("paper-embedded")
}

tasks {
    runServer {
        minecraftVersion("26.3")
    }
}

tasks.named<ProcessResources>("processResources"){
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
