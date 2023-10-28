package com.kmp.recipes.mobile.app.ui.details

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.kmp.recipes.mobile.app.data.model.ApiResultState
import com.kmp.recipes.mobile.app.data.model.Recipe
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailsModel(private val repository: RecipesRepository) :
    StateScreenModel<DetailsScreenState>(DetailsScreenState.Init) {

    private val _favoriteStateFlow = MutableStateFlow(false)
    val favouriteStateFlow: StateFlow<Boolean> = _favoriteStateFlow

    fun fetchDetails(recipeId: String) {
        coroutineScope.launch {
            repository.fetchRecipeDetailsById(recipeId)
                .collect { response ->
                    mapApiResultOnUiState(response)
                }
        }
    }

    private fun mapApiResultOnUiState(response: ApiResultState) {
        when (response) {
            is ApiResultState.OnFailure -> {
                val errorMessage = response.errorMessage
                mutableState.value = DetailsScreenState.Error(errorMessage)
            }

            is ApiResultState.OnSuccess<*> -> {
                val recipe = response.data as Recipe
                _favoriteStateFlow.value = recipe.isFavourite
                mutableState.value = DetailsScreenState.Content(recipe)
            }
        }
    }

    fun markAsFavourite(recipe: Recipe) {
        coroutineScope.launch(Dispatchers.IO) {
            repository.markRecipeFavourite(recipe).run {
                recipe.isFavourite = recipe.isFavourite.not()
                _favoriteStateFlow.value = recipe.isFavourite
            }
        }
    }
}