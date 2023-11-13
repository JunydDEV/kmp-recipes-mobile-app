package com.kmp.recipes.mobile.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

actual val safeAreaPadding = 30.dp

@Composable
actual fun StatusBarColor(color: Color, isDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
}