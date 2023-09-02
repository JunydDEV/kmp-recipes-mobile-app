package com.kmp.recipes.mobile.app.main_screen

import cafe.adriel.voyager.core.model.ScreenModel
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.common.data.RecipesData
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.FileResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json

class MainScreenModel: ScreenModel {

    private val _recipesDataFlow = MutableStateFlow<RecipesData?>(null)

    fun getRecipesData(): FileResource {
        return SharedRes.files.recipes_fake_data
    }

    private val json = Json { ignoreUnknownKeys = true }

    fun serializeJsonToRecipesData(value: String?): RecipesData {
        return json.decodeFromString(value ?: EMPTY_JSON)
    }

    fun getPopularRecipesList(recipesData: RecipesData): List<Recipe> {
        return recipesData.recipesList.filter { recipesData.recipesList.contains(it) }
    }

    companion object {
        private const val EMPTY_JSON = "{}"
    }
}