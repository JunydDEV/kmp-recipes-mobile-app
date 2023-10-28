package com.kmp.recipes.mobile.app.ui.main

import com.kmp.recipes.mobile.app.data.model.Category
import com.kmp.recipes.mobile.app.data.model.Quote
import com.kmp.recipes.mobile.app.data.model.Recipe

sealed class MainScreenState {
    object Init : MainScreenState()
    object Loading : MainScreenState()
    data class Error(val message: String) : MainScreenState()
    data class Content(
        val foodQuotesSectionTitle: String = "Food Quotes",
        val foodQuotes: List<Quote>,
        val categorySectionTitle: String = "Categories",
        val categories: List<Category>,
        val popularRecipesSectionTitle: String = "Popular Recipes",
        val popularRecipes: List<Recipe>,
        val recipesList: List<Recipe>
    ) : MainScreenState()
}
