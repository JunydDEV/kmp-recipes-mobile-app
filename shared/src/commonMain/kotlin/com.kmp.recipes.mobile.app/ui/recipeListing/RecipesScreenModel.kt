package com.kmp.recipes.mobile.app.ui.recipeListing

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.kmp.recipes.mobile.app.data.datasource.model.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import kotlinx.coroutines.launch

class RecipesScreenModel(private val repository: RecipesRepository) :
    StateScreenModel<RecipesListingScreenState>(RecipesListingScreenState.Init) {

    fun fetchRecipesByCategory(id: String) {
        coroutineScope.launch {
            repository.fetchRecipesListByCategory(id)
                .collect {
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
    }
}