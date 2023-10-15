package com.kmp.recipes.mobile.app.data.repository

import com.kmp.recipes.mobile.app.cookitApplicationConfig
import com.kmp.recipes.mobile.app.data.datasource.model.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.FakeRecipesDataSource
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RecipesRepositoryImpl(
    private val dataSource: FakeRecipesDataSource
) : RecipesRepository {

    override suspend fun getRecipesData() = flow {
        try {
            val result = dataSource.getRecipesData(cookitApplicationConfig.appContext)
            emit(ApiResultState.OnSuccess(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to retrieve recipes data"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun searchRecipes(query: String) = flow {
        try {
            if (query.length < 3) return@flow
            val result = dataSource.searchRecipes(query)
            if (result.isEmpty()) {
                emit(ApiResultState.OnFailure("Sorry, recipes not found."))
            } else {
                emit(ApiResultState.OnSuccess(result))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to search recipes"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun markRecipeFavourite(recipe: Recipe) {
        dataSource.markRecipeFavourite(recipe)
    }

    override suspend fun fetchRecipeDetailsById(id: String) = flow {
        try {
            val recipe = dataSource.fetchRecipesDetailsById(id)
            emit(ApiResultState.OnSuccess(recipe))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to fetch recipe details"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchRecipesListByCategory(id: String) = flow {
        try {
            val result = dataSource.fetchRecipesListByCategory(id)
            if (result.isNotEmpty()) {
                emit(ApiResultState.OnSuccess(result))
            } else {
                emit(ApiResultState.OnFailure("Sorry, recipes not found."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to fetch recipes by category"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchAllRecipes() = flow {
        try {
            val result = dataSource.fetchAllRecipes()
            if (result.isNotEmpty()) {
                emit(ApiResultState.OnSuccess(result))
            } else {
                emit(ApiResultState.OnFailure("Sorry, recipes not found."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to fetch all recipes list"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchFavouriteRecipes() = flow {
        try {
            val result = dataSource.fetchFavouriteRecipes()
            if (result.isNotEmpty()) {
                emit(ApiResultState.OnSuccess(result))
            } else {
                emit(ApiResultState.OnFailure("Sorry, recipes not found."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to fetch favourite recipes list"))
        }
    }.flowOn(Dispatchers.IO)
}