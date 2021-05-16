import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.compose") version "0.4.0-build182"
}


repositories {
    mavenCentral()
    jcenter()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

version = "4.5.0"

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("dev.amaro:sonic:0.2.1")
    implementation ("com.google.code.gson:gson:2.8.6")
    implementation("io.insert-koin:koin-core:3.0.1")
    testImplementation("io.kotest:kotest-runner-junit5:$version")
    testImplementation("io.kotest:kotest-assertions-core:$version")
    testImplementation("com.appmattus.fixture:fixture:1.1.0")
    testImplementation("io.mockk:mockk:1.11.0")
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}

tasks.test {
    useJUnitPlatform()
}


tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}