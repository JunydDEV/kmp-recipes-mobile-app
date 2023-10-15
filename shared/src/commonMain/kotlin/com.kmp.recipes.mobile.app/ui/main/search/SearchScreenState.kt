package com.kmp.recipes.mobile.app.ui.main.search

import com.kmp.recipes.mobile.app.data.datasource.model.Recipe

sealed class SearchScreenState {
    object Init : SearchScreenState()
    object Loading : SearchScreenState()
    data class Error(val message: String) : SearchScreenState()
    data class Content(
        val searchResultsLabel: String = "Search Results",
        val searchResults: List<Recipe>
    ) : SearchScreenState()
}
