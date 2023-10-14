package com.kmp.recipes.mobile.app.data.datasource

import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.datasource.model.RecipesData

interface FakeRecipesDataSource {

    /**
     * Retrieves recipes data including categories, food quotes, and popular recipes
     *
     * @return [RecipesData] result object.
     * */
    suspend fun getRecipesData(context: Any?): RecipesData

    /**
     * Searches for recipes data.
     *
     * @param query applies on the titles of recipes.
     * @return list of recipes.
     * */
    fun searchRecipes(query: String): List<Recipe>

    /**
     * Adds recipe to the favourites.
     *
     * @param recipe object.
     * */
    suspend fun markRecipeFavourite(recipe: Recipe)

    /**
     * Retrieves favourite recipes list.
     *
     * @return list of recipes
     * */
    suspend fun getFavouriteRecipesList(): List<Recipe>

    /**
     * Fetches recipe based on recipe ID.
     *
     * @return recipe object
     * */
    suspend fun fetchRecipesDetailsById(id: String): Recipe?

    /**
     * Fetches recipes list based on category ID.
     *
     * @return list of recipes
     * */
    suspend fun fetchRecipesListByCategory(id: String): List<Recipe>
}