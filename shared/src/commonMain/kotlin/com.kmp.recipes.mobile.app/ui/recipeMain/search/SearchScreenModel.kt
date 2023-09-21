package com.kmp.recipes.mobile.app.ui.recipeMain.search

import cafe.adriel.voyager.core.model.StateScreenModel
import com.kmp.recipes.mobile.app.data.datasource.model.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import com.kmp.recipes.mobile.app.ui.recipeMain.stateholders.SearchDataState
import com.kmp.recipes.mobile.app.ui.recipeMain.stateholders.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchScreenModel(private val repository: RecipesRepository) :
    StateScreenModel<UiState>(UiState.Init) {

    fun searchRecipes(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.searchRecipes(query)
                .onStart {
                    mutableState.value = UiState.Loading
                }
                .catch {
                    mutableState.value = UiState.Failure(it.message ?: "Unknown error")
                }
                .collect {
                    when (it) {
                        is ApiResultState.OnSuccess<*> -> {
                            val searchUiState =
                                SearchDataState(searchResults = it.data as List<Recipe>)
                            mutableState.value = UiState.Success(searchUiState)
                        }

                        is ApiResultState.OnFailure -> {
                            mutableState.value = UiState.Failure(it.errorMessage)
                        }
                    }
                }
        }
    }

}