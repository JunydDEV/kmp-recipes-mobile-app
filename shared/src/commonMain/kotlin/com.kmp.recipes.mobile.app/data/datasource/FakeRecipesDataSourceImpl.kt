package com.kmp.recipes.mobile.app.data.datasource

import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.datasource.model.RecipesData
import com.kmp.recipes.mobile.app.readFakeRecipesText

class FakeRecipesDataSourceImpl(
    private val recipesDataService: RecipesDataService,
) : FakeRecipesDataSource {

    private var recipesData: RecipesData? = null

    override suspend fun getRecipesData(context: Any?): RecipesData {
        if (context == null) throw RuntimeException("Failed to fetch recipes data, please restart the app")

        return try {
            val fakeRecipesText = readFakeRecipesText(context)
            recipesData = recipesDataService.fetchRecipesData(fakeRecipesText)
            recipesData!!
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }

    override fun searchRecipes(query: String): List<Recipe> {
        if (recipesData == null) {
            throw RuntimeException("Recipes data is not available.")
        }

        return recipesData!!.recipesList.filter {
            it.label.startsWith(query, ignoreCase = true)
        }
    }
}