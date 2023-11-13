package com.kmp.recipes.mobile.app.ui.listing

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.kmp.recipes.mobile.app.data.model.ApiResultState
import com.kmp.recipes.mobile.app.data.model.Recipe
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import kotlinx.coroutines.launch

class RecipesScreenModel(private val repository: RecipesRepository) :
    StateScreenModel<RecipesListingScreenState>(RecipesListingScreenState.Init) {

    fun fetchRecipesList(id: String) {
        screenModelScope.launch {
            when (id) {
                "view all" -> {
                    fetchAllRecipes()
                }

                "favourites" -> {
                    fetchFavouriteRecipes()
                }

                else -> {
                    fetchRecipesByCategory(id)
                }
            }
        }
    }

    private suspend fun fetchFavouriteRecipes() {
        repository.fetchFavouriteRecipes().collect {
            mapApiResultOnUiState(it)
        }
    }

    private suspend fun fetchAllRecipes() {
        repository.fetchAllRecipes().collect {
            mapApiResultOnUiState(it)
        }
    }

    private suspend fun fetchRecipesByCategory(id: String) {
        repository.fetchRecipesListByCategory(id).collect {
            mapApiResultOnUiState(it)
        }
    }

    private fun mapApiResultOnUiState(it: ApiResultState) {
        when (it) {
            is ApiResultState.OnFailure -> {
                val failureMessage = it.errorMessage
                mutableState.value = RecipesListingScreenState.Error(failureMessage)
            }

            is ApiResultState.OnSuccess<*> -> {
                val list = it.data as List<Recipe>
                mutableState.value = RecipesListingScreenState.Content(list)
            }
        }
    }
}