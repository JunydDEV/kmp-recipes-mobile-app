package com.kmp.recipes.mobile.app.data.datasource

import com.kmp.recipes.mobile.app.sharedres.SharedRes

actual class RecipesDataReader {

    actual fun getText(): String {
        return SharedRes.files.recipes_fake_data.readText()
    }
}