package com.kmp.recipes.mobile.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

expect val safeAreaPadding: Dp

@Composable
expect fun StatusBarColor(color: Color, isDarkTheme: Boolean)