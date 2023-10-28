package com.kmp.recipes.mobile.app.data.datasource

import com.kmp.recipes.mobile.app.data.db.Database
import com.kmp.recipes.mobile.app.data.model.Recipe
import com.kmp.recipes.mobile.app.data.model.RecipesData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class FakeRecipesDataSourceImpl(
    recipesDataReader: RecipesDataReader,
    recipesDataParser: RecipesDataParser,
    private val database: Database,
) : FakeRecipesDataSource {
    private var recipesData: RecipesData

    init {
        try {
            val recipesDataJsonResponse = recipesDataReader.getText()
            recipesData = recipesDataParser.parseJsonResponse(recipesDataJsonResponse)
            updateFavouriteRecipesInTheList()
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
    }

    private fun updateFavouriteRecipesInTheList() {
        CoroutineScope(Dispatchers.IO).launch {
            val recipesList = recipesData.recipesList
            val favouriteRecipesList = database.fetchAllFavouriteRecipes()
            recipesList.forEach {
                it.isFavourite = favouriteRecipesList.contains(it.id)
            }
        }
    }

    override fun getRecipesData(): RecipesData {
        return recipesData
    }

    override fun searchRecipes(query: String): List<Recipe> {
        val searchQueryInLowercase = query.lowercase()

        return recipesData.recipesList.filter { recipe->
            recipe.label.lowercase().contains(searchQueryInLowercase)
        }
    }

    override fun markRecipeFavourite(recipe: Recipe) {
        val result = database.fetchRecipeById(recipe.id)
        if (result.isEmpty()) {
            database.addToFavourites(recipe)
        } else {
            database.removeFromFavourites(recipe.id)
        }
    }

    override fun getFavouriteRecipesList(): List<Recipe?> {
        val favourites = database.fetchAllFavouriteRecipes()
        val allRecipesList = recipesData.recipesList
        val recipesListMap = allRecipesList.associateBy { it.id }
        return favourites.map { recipesListMap[it] }
    }

    override fun fetchRecipesDetailsById(id: String): Recipe? {
        return recipesData.recipesList.firstOrNull { it.id == id }
    }

    override fun fetchRecipesListByCategory(id: String): List<Recipe> {
        val category = recipesData.sections.categories.find { it.id == id }
        val recipesIds = category?.recipes
        val results = recipesData.recipesList.filter {
            recipesIds?.contains(it.id) == true
        }
        return results
    }

    override fun fetchAllRecipes(): List<Recipe> {
        return recipesData.recipesList
    }
}