package com.kmp.recipes.mobile.app.ui.main.search

import cafe.adriel.voyager.core.model.StateScreenModel
import com.kmp.recipes.mobile.app.data.datasource.model.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchScreenModel(private val repository: RecipesRepository) :
    StateScreenModel<SearchScreenState>(SearchScreenState.Init) {

    fun searchRecipes(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.searchRecipes(query)
                .onStart {
                    mutableState.value = SearchScreenState.Loading
                }
                .catch {
                    mutableState.value = SearchScreenState.Error(it.message ?: "Unknown error")
                }
                .collect {
                    when (it) {
                        is ApiResultState.OnSuccess<*> -> {
                            mutableState.value =
                                SearchScreenState.Content(searchResults = it.data as List<Recipe>)
                        }

                        is ApiResultState.OnFailure -> {
                            mutableState.value = SearchScreenState.Error(it.errorMessage)
                        }
                    }
                }
        }
    }

}