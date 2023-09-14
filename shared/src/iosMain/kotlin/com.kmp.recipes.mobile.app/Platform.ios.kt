package com.kmp.recipes.mobile.app

import androidx.compose.ui.unit.dp
import com.kmp.recipes.mobile.app.sharedres.SharedRes

actual val safeAreaPadding = 50.dp

actual fun readFakeRecipesText(context: Any) = SharedRes.files.recipes_fake_data.readText()