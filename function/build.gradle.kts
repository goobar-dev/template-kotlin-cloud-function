import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadowJar)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvmVersion.get()))
    }
}

// configures shadowJar creation as per Cloud Function documentation for Gradle projects
tasks.named<ShadowJar>("shadowJar") {
    mergeServiceFiles()
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}

// Create a new build configuration that supports locally running the
// Cloud Function via the Functions Framework.
// The task will set the GCP_PROJECT_ID env variable to LOCAL to indicate running from local environment
// The task requires two Gradle properties be passed to it:
//    - run.functionTarget (name of the class implementing HttpFunction)
//    - run.port (port that the function will be listening on)
val invoker: Configuration by configurations.creating
tasks.register<JavaExec>("runFunction") {
    mainClass = "com.google.cloud.functions.invoker.runner.Invoker"
    classpath = invoker
    inputs.files(configurations.runtimeClasspath.get(), sourceSets.main.get().output)
    args(
        "--target", project.findProperty("run.functionTarget") ?: "",
        "--port", project.findProperty("run.port") ?: 8080
    )
    environment("GCP_PROJECT_ID", "LOCAL")
    doFirst {
        args("--classpath", files(configurations.runtimeClasspath.get(), sourceSets.main.get().output).asPath)
    }
}

dependencies {
    implementation(libs.logback.classic)
    implementation(libs.logback.encoder)

    implementation(libs.function.framework)
    invoker(libs.function.invoker)
}
