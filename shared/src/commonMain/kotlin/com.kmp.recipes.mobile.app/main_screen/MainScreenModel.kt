package com.kmp.recipes.mobile.app.main_screen

import cafe.adriel.voyager.core.model.ScreenModel
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.common.data.RecipesData
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.FileResource
import kotlinx.serialization.json.Json

class MainScreenModel: ScreenModel {

    fun getRecipesData(): FileResource {
        return SharedRes.files.recipes_fake_data
    }

    private val json = Json { ignoreUnknownKeys = true }

    fun serializeRecipesTextToRecipesDataModel(value: String?): RecipesData {
        return json.decodeFromString(value?:"{}")
    }

    fun getPopularRecipesList(recipesData: RecipesData): List<Recipe> {
        return recipesData.recipesList.filter { recipesData.recipesList.contains(it) }
    }
}