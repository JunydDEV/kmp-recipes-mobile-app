package com.kmp.recipes.mobile.app.main_screen

import cafe.adriel.voyager.core.model.ScreenModel
import com.kmp.recipes.mobile.app.data.Category
import com.kmp.recipes.mobile.app.data.Recipe
import com.kmp.recipes.mobile.app.data.RecipesData
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.FileResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json

class MainScreenModel: ScreenModel {

    fun getRecipesData(): FileResource {
        return SharedRes.files.recipes_fake_data
    }

    private val json = Json { ignoreUnknownKeys = true }

    fun serializeJsonToRecipesData(value: String?): RecipesData {
        return json.decodeFromString(value ?: EMPTY_JSON)
    }

    fun getPopularRecipesList(recipesData: RecipesData): List<Recipe> {
        val popularRecipesIds = recipesData.sections.popularRecipes.recipes
        return recipesData.recipesList.filter {
            popularRecipesIds.contains(it.id)
        }
    }

    fun findRecipesByCategoryId(category: Category, recipesList: List<Recipe>): List<Recipe> {
        return recipesList.filter { category.recipes.contains(it.id) }
    }

    fun searchRecipesBy(recipes: List<Recipe>, searchQuery: String): List<Recipe> {
        return recipes.filter { it.label.contains(searchQuery, ignoreCase = true) }
    }

    companion object {
        private const val EMPTY_JSON = "{}"
    }
}