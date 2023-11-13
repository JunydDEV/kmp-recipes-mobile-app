package com.kmp.recipes.mobile.app.ui.preview

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.data.model.Ingredient
import com.kmp.recipes.mobile.app.safeAreaPadding
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.ImageX
import dev.icerock.moko.resources.compose.painterResource

data class PreviewScreen(private val ingredient: Ingredient): Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    windowInsets = WindowInsets(top = safeAreaPadding),
                    title = {
                        Text(
                            textAlign = TextAlign.End,
                            text = ingredient.label,
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = Dimens.appTitleSize),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent),
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                              navigator.pop()
                    },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(SharedRes.images.ic_cancel),
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {
            ImageX(
                modifier = Modifier.fillMaxSize(),
                url = ingredient.image,
                tag = ingredient.label
            )
        }
    }
}