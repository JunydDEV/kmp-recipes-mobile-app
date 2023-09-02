package com.kmp.recipes.mobile.app.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.asImageBitmap
import com.seiko.imageloader.model.ImageEvent
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.model.ImageResult
import com.seiko.imageloader.rememberImageAction

@Composable
fun ImageX(
    modifier: Modifier,
    url: String,
    showOverlay: Boolean = false,
    overlayColor: Color = MaterialTheme.colorScheme.primary,
    showProgress: Boolean = true
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val imageRequest = ImageRequest { data(url) }
        val imageRequestState = remember { imageRequest }
        val action by rememberImageAction(imageRequestState)
        when (val currentAction = action) {
            is ImageEvent.StartWithFetch -> {
                ShowProgressIndicator(showProgress)
            }

            is ImageEvent.StartWithMemory -> {
                ShowProgressIndicator(showProgress)
            }

            is ImageResult.Bitmap -> {
                Image(
                    modifier = modifier,
                    bitmap = currentAction.bitmap.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                if (showOverlay) {
                    Box(modifier = modifier.background(overlayColor.copy(alpha = 0.5f)))
                }
            }

            else -> {
                print(currentAction)
            }
        }
    }
}

@Composable
private fun ShowProgressIndicator(showProgress: Boolean) {
    if (showProgress) {
        CircularProgressIndicator(
            modifier = Modifier.wrapContentSize(),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}