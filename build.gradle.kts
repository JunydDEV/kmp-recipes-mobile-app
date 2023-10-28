
plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("multiplatform").version("1.9.0").apply(false)
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    id("org.jetbrains.compose").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.2")
        classpath("dev.icerock.moko:resources-generator:0.23.0")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}