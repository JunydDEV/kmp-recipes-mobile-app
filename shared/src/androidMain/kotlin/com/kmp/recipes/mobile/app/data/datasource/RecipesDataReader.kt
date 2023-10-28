package com.kmp.recipes.mobile.app.data.datasource

import android.content.Context
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import org.koin.mp.KoinPlatform.getKoin

actual class RecipesDataReader actual constructor() {

    actual fun getText(): String {
        val context: Context = getKoin().get()
        return SharedRes.files.recipes_fake_data.readText(context)
    }
}