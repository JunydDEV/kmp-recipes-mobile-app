package com.kmp.recipes.mobile.app.ui.main

import cafe.adriel.voyager.core.model.StateScreenModel
import com.kmp.recipes.mobile.app.data.datasource.model.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.datasource.model.RecipesData
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

class MainScreenModel(val repository: RecipesRepository) : StateScreenModel<MainScreenState>(
    MainScreenState.Init) {

    suspend fun fetchRecipesData() {
        repository.getRecipesData()
            .onStart {
                mutableState.value = MainScreenState.Loading
            }
            .catch {
                mutableState.value = MainScreenState.Error(it.message ?: "Unknown error")
            }
            .collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        mapSuccessResultOnUiState(it)
                    }

                    is ApiResultState.OnFailure -> {
                        mutableState.value = MainScreenState.Error(it.errorMessage)
                    }
                }
            }
    }

    private fun mapSuccessResultOnUiState(it: ApiResultState.OnSuccess<*>) {
        val recipesData = it.data as RecipesData
        with(recipesData.sections) {
            mutableState.value = MainScreenState.Content(
                foodQuotes = quotes,
                categories = categories,
                popularRecipes = getPopularRecipes(
                    recipesIds = recipesData.sections.popularRecipesIds,
                    recipes = recipesData.recipesList,
                ),
                recipesList = recipesData.recipesList
            )
        }
    }

    private fun getPopularRecipes(recipesIds: List<String>, recipes: List<Recipe>): List<Recipe> {
        val recipesMap = recipes.associateBy { it.id }
        return recipesIds.map { recipesMap[it]!! }
    }
}
