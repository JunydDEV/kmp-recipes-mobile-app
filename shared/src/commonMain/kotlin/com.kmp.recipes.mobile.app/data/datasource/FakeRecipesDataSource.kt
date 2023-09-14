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
}