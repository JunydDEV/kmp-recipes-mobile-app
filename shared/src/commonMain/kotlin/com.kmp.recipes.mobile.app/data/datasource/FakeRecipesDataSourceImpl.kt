package com.kmp.recipes.mobile.app.data.datasource

import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.datasource.model.RecipesData
import com.kmp.recipes.mobile.app.readFakeRecipesText

class FakeRecipesDataSourceImpl(
    private val recipesDataService: RecipesDataService,
) : FakeRecipesDataSource {

    private lateinit var recipesData: RecipesData
    private val favoriteRecipesList = mutableListOf<Recipe>()

    override suspend fun getRecipesData(context: Any?): RecipesData {
        if (context == null)
            throw RuntimeException("Failed to fetch recipes data, please restart the app")

        if (this::recipesData.isInitialized) return recipesData

        return try {
            val fakeRecipesText = readFakeRecipesText(context)
            recipesDataService.fetchRecipesData(fakeRecipesText).apply {
                updateFavouriteField()
                saveRecipesDataForFutureUse()
            }
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }

    private fun RecipesData.updateFavouriteField() {
        recipesList.map { updateFavouriteField(it) }
    }

    private fun RecipesData.saveRecipesDataForFutureUse() {
        recipesData = this
    }

    private fun updateFavouriteField(it: Recipe) {
        it.isFavourite = favoriteRecipesList.contains(it)
    }

    override fun searchRecipes(query: String): List<Recipe> {
        return recipesData.recipesList.filter {
            it.label.startsWith(query, ignoreCase = true)
        }
    }

    override suspend fun markRecipeFavourite(recipe: Recipe) {
        val recipeExistsInFavorites = favoriteRecipesList.any { it.id == recipe.id }
        if (recipeExistsInFavorites) {
            favoriteRecipesList.remove(recipe)
        } else {
            favoriteRecipesList.add(recipe)
        }
    }

    override suspend fun getFavouriteRecipesList(): List<Recipe> {
        return favoriteRecipesList
    }

    override suspend fun fetchRecipesDetailsById(id: String): Recipe? {
        return recipesData.recipesList.firstOrNull { it.id == id }
    }

    override suspend fun fetchRecipesListByCategory(id: String): List<Recipe> {
        return when (id) {
            "view all" -> { recipesData.recipesList }

            "favourites" -> { favoriteRecipesList }

            else -> {
                val category = recipesData.sections.categories.find { it.id == id }
                val recipesIds = category?.recipes
                val results = recipesData.recipesList.filter {
                    recipesIds?.contains(it.id) == true
                }
                results
            }
        }
    }
}