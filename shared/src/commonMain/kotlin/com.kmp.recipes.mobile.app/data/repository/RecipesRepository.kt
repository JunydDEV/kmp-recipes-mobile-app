package com.kmp.recipes.mobile.app.data.repository

import com.kmp.recipes.mobile.app.data.datasource.model.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    /**
     * Retrieves recipes data including categories, food quotes, and popular recipes
     *
     * @return flow of [ApiResultState] result object.
     * */
    suspend fun getRecipesData(): Flow<ApiResultState>

    /**
     * Searches for recipes data.
     *
     * @param query applies on the titles of recipes.
     * @return flow of [ApiResultState] result object.
     * */
    suspend fun searchRecipes(query: String): Flow<ApiResultState>

    /**
     * Adds recipe to the favourites.
     *
     * @param recipe object.
     * */
    suspend fun markRecipeFavourite(recipe: Recipe)

    /**
     * Fetches recipe based on recipe ID.
     *
     * @return flow of [ApiResultState] result object.
     * */
    suspend fun fetchRecipeDetailsById(id: String): Flow<ApiResultState>

    /**
     * Fetches recipes list based on category ID.
     *
     * @return flow of [ApiResultState] result object.
     * */
    suspend fun fetchRecipesListByCategory(id: String): Flow<ApiResultState>
}