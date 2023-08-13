
plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    id("org.jetbrains.compose").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("dev.icerock.moko:resources-generator:0.23.0")
    }
}