package com.kmp.recipes.mobile.app.ui.main_screen

import cafe.adriel.voyager.core.model.StateScreenModel
import com.kmp.recipes.mobile.app.data.datasource.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.model.Category
import com.kmp.recipes.mobile.app.data.datasource.model.Quote
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.datasource.model.RecipesData
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

class MainScreenModel(val repository: RecipesRepository) : StateScreenModel<UiState>(UiState.Init) {

    private val _searchRecipesStateFlow = MutableStateFlow<UiState>(UiState.Init)
    val searchRecipesStateFlow: StateFlow<UiState> = _searchRecipesStateFlow

    suspend fun fetchRecipesData() {
        repository.getRecipesData()
            .onStart {
                mutableState.value = UiState.Loading
            }
            .catch {
                mutableState.value = UiState.Failure(it.message ?: "Unknown error")
            }
            .collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        val recipesData = it.data as RecipesData
                        with(recipesData.sections) {
                            val recipesDataState = RecipesDataState(
                                foodQuotes = quotes,
                                categories = categories,
                                popularRecipes = getPopularRecipes(
                                    recipesIds = recipesData.sections.popularRecipesIds,
                                    recipes = recipesData.recipesList
                                )
                            )
                            mutableState.value = UiState.Success(recipesDataState)
                        }
                    }

                    is ApiResultState.OnFailure -> {
                        mutableState.value = UiState.Failure(it.errorMessage)
                    }
                }
            }
    }

    suspend fun searchRecipes(query: String) {
        repository.searchRecipes(query)
            .onStart {
                _searchRecipesStateFlow.value = UiState.Loading
            }
            .catch {
                _searchRecipesStateFlow.value = UiState.Failure(it.message ?: "Unknown error")
            }
            .collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        val searchUiState = SearchDataState(searchResults = it.data as List<Recipe>)
                        _searchRecipesStateFlow.value = UiState.Success(searchUiState)
                    }

                    is ApiResultState.OnFailure -> {
                        _searchRecipesStateFlow.value = UiState.Failure(it.errorMessage)
                    }
                }
            }
    }

    private fun getPopularRecipes(recipesIds: List<String>, recipes: List<Recipe>): List<Recipe> {
        val recipesMap = recipes.associateBy { it.id }
        return recipesIds.map { recipesMap[it]!! }
    }

    fun findRecipesByCategoryId(category: Category, recipesList: List<Recipe>): List<Recipe> {
        return recipesList.filter { category.recipes.contains(it.id) }
    }

    fun searchRecipesBy(recipes: List<Recipe>, searchQuery: String): List<Recipe> {
        return recipes.filter { it.label.contains(searchQuery, ignoreCase = true) }
    }

}

sealed class UiState {
    object Init : UiState()
    object Loading : UiState()
    data class Failure(val errorMessage: String) : UiState()
    data class Success<T>(val data: T) : UiState()
}

data class RecipesDataState(
    val foodQuotesSectionTitle: String = "Food Quotes",
    val foodQuotes: List<Quote>,
    val categorySectionTitle: String = "Categories",
    val categories: List<Category>,
    val popularRecipesSectionTitle: String = "Popular Recipes",
    val popularRecipes: List<Recipe>
)

data class SearchDataState(
    val searchResultsLabel: String = "Search Results",
    val searchResults: List<Recipe>
)
