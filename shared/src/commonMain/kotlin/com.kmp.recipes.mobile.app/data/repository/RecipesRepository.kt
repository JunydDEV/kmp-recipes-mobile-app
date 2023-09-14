package com.kmp.recipes.mobile.app.data.repository

import com.kmp.recipes.mobile.app.data.datasource.ApiResultState
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
}