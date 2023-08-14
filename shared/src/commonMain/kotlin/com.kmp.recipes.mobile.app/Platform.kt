package com.kmp.recipes.mobile.app

import androidx.compose.ui.unit.Dp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect val safeAreaPadding: Dp