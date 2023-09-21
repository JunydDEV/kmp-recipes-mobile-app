package com.kmp.recipes.mobile.app.ui.recipeMain.stateholders

import com.kmp.recipes.mobile.app.data.datasource.model.Category
import com.kmp.recipes.mobile.app.data.datasource.model.Quote
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe

data class RecipesDataState(
    val foodQuotesSectionTitle: String = "Food Quotes",
    val foodQuotes: List<Quote>,
    val categorySectionTitle: String = "Categories",
    val categories: List<Category>,
    val popularRecipesSectionTitle: String = "Popular Recipes",
    val popularRecipes: List<Recipe>,
    val recipesList: List<Recipe>
)