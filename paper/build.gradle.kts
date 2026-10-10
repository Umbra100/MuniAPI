// --- Basic Addon Build Settings ---
plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    // Shadow plugin for creating fatjars
    // id 'com.gradleup.shadow' version "${shadowjar_version}"
    // // Adds the runServer task to the project so that you can run paper mc servers inside the IDE
    // id "xyz.jpenilla.run-paper" version "3.0.2"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()

    // Use the official PaperMC repository for paper dependencies
    // maven {
    //     name = "papermc"
    //     url = uri("https://repo.papermc.io/repository/maven-public/")
    // }
}


dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)

    // Paper API dependency for addon adapter
    // compileOnly("io.papermc.paper:paper-api:${minecraft_version}.build.+")
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
        languageVersion = JavaLanguageVersion.of(26)
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
