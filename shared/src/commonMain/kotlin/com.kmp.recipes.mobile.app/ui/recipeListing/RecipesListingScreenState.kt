package com.kmp.recipes.mobile.app.ui.recipeListing

import com.kmp.recipes.mobile.app.data.datasource.model.Recipe

sealed class RecipesListingScreenState {
    object Init : RecipesListingScreenState()
    object Loading : RecipesListingScreenState()
    data class Error(val message: String) : RecipesListingScreenState()
    data class Content(val recipesList: List<Recipe>) : RecipesListingScreenState()
}
