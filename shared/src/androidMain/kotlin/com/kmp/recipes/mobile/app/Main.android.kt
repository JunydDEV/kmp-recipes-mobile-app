package com.kmp.recipes.mobile.app

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.option.androidContext
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.cache.memory.maxSizePercent
import okio.Path.Companion.toOkioPath

@Composable
fun MainView() {
    val context = LocalContext.current
    CompositionLocalProvider(
        LocalImageLoader provides remember { generateImageLoader(context) },
    ) {
        RecipesApp()
    }
}

fun generateImageLoader(context: Context): ImageLoader {
    return ImageLoader {
        options {
            androidContext(context)
        }
        components {
            setupDefaultComponents()
        }
        interceptor {
            memoryCacheConfig {
                // Set the max size to 25% of the app's available memory.
                maxSizePercent(context, 0.25)
            }
            diskCacheConfig {
                directory(context.cacheDir.resolve("image_cache").toOkioPath())
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}