plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
    kotlin("plugin.serialization") version "1.9.0"
    id("com.squareup.sqldelight")
}

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resource"] = "'build/cocoapods/framework/shared.framework/*.bundle'"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose
                with(compose) {
                    api(runtime)
                    api(foundation)
                    api(material)
                    api(material3)
                    api(materialIconsExtended)
                }

                // Koin
                with(Deps.Koin) {
                    api(core)
                    api(test)
                }

                // Voyager
                with(Deps.Voyager) {
                    api(navigator)
                    api(transitions)
                    api(koin)
                }

                // Moko
                with(Deps.Moko) {
                    api(resources)
                    api(resourcesCompose)
                }


                api(Deps.Github.imageLoader)
                api(Deps.Io.Ktor.ktorSerializationKotlinxJson)
                api(Deps.SqlDelight.sqlDelight)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api("androidx.activity:activity-compose:1.8.0")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.12.0")
                api("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
                api(Deps.Koin.android)
                api(Deps.SqlDelight.sqlDelightAndroid)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                api(Deps.SqlDelight.sqlDelightIos)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.kmp.recipes.mobile.app.shared"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlin {
        jvmToolchain(18)
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.kmp.recipes.mobile.app.sharedres"
    multiplatformResourcesClassName = "SharedRes"
    disableStaticFrameworkWarning = true
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.kmp.recipes.mobile.app.db"
    }
}