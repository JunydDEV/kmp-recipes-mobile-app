package com.kmp.mobile.systemui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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

