package com.kmp.recipes.mobile.app

import android.content.Context
import androidx.compose.ui.unit.dp
import com.kmp.recipes.mobile.app.sharedres.SharedRes

actual val safeAreaPadding = 30.dp

actual fun readFakeRecipesText(context: Any) =
    SharedRes.files.recipes_fake_data.readText(context as Context)