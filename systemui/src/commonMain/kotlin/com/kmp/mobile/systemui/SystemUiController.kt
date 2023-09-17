package com.kmp.mobile.systemui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
expect fun StatusBarColor(color: Color, isDarkTheme: Boolean)