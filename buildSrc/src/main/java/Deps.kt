object Deps {
    object Org {
        object JetBrains {
            object Kotlinx {
                const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
                const val kotlinxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
            }

            object KotlinWrappers {
                const val kotlinWrappersBom = "org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:${Versions.kotlinWrappersBom}"
                const val kotlinStyled = "org.jetbrains.kotlin-wrappers:kotlin-styled"
            }
        }
    }

    object Io {
        object Ktor {
            const val ktorClientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
            const val ktorSerializationKotlinxJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
            const val ktorClientContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
            const val ktorClientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        }
    }
    object Logback {
        const val logbackClassic = "ch.qos.logback:logback-classic:${Versions.logbackClassic}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object Github {
        const val imageLoader = "io.github.qdsfdhvh:image-loader:${Versions.imageLoader}"
    }

    object Voyager {
        const val navigator = "cafe.adriel.voyager:voyager-navigator:${Versions.voyagerVersion}"
        const val transitions = "cafe.adriel.voyager:voyager-transitions:${Versions.voyagerVersion}"
        const val koin = "cafe.adriel.voyager:voyager-koin:${Versions.voyagerVersion}"
    }

    object Moko {
        const val resources = "dev.icerock.moko:resources:${Versions.mokoVersion}"
        const val resourcesCompose = "dev.icerock.moko:resources-compose:${Versions.mokoVersion}"
    }

    object Square {
        const val okio = "com.squareup.okio:okio:${Versions.okioVersion}"
    }

    object SqlDelight {
        const val sqlDelight = "com.squareup.sqldelight:runtime:${Versions.sqlDelightVersion}"
        const val sqlDelightAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelightVersion}"
        const val sqlDelightIos = "com.squareup.sqldelight:native-driver:${Versions.sqlDelightVersion}"
    }
}