package com.kmp.recipes.mobile.app.data.repository

import com.kmp.recipes.mobile.app.cookitApplicationConfig
import com.kmp.recipes.mobile.app.data.datasource.model.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.FakeRecipesDataSource
import kotlinx.coroutines.flow.flow

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
    }

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
    }
}