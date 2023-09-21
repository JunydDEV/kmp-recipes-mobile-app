package com.kmp.recipes.mobile.app.ui.recipeMain.stateholders

import com.kmp.recipes.mobile.app.data.datasource.model.Recipe

data class SearchDataState(
    val searchResultsLabel: String = "Search Results",
    val searchResults: List<Recipe>
)