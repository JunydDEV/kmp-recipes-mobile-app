package com.kmp.recipes.mobile.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kmp.recipes.mobile.app.sharedres.SharedRes

actual val safeAreaPadding = 50.dp

@Composable
actual fun StatusBarColor(color: Color, isDarkTheme: Boolean) {
}