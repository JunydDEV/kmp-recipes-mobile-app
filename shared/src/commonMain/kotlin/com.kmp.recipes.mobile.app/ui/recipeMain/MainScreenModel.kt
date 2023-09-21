package com.kmp.recipes.mobile.app.ui.recipeMain

import cafe.adriel.voyager.core.model.StateScreenModel
import com.kmp.recipes.mobile.app.data.datasource.model.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.model.Category
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.datasource.model.RecipesData
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import com.kmp.recipes.mobile.app.ui.recipeMain.stateholders.RecipesDataState
import com.kmp.recipes.mobile.app.ui.recipeMain.stateholders.SearchDataState
import com.kmp.recipes.mobile.app.ui.recipeMain.stateholders.UiState
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
                                    recipes = recipesData.recipesList,
                                ),
                                recipesList = recipesData.recipesList
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
