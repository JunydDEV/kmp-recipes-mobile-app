package com.kmp.recipes.mobile.app

import androidx.compose.ui.unit.Dp

expect val safeAreaPadding: Dp

expect fun readFakeRecipesText(context: Any): String