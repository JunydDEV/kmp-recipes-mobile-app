package com.kmp.recipes.mobile.app.ui.recipeMain.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.ColumnX
import com.kmp.recipes.mobile.app.ui.common.FailureLabel
import com.kmp.recipes.mobile.app.ui.common.RecipesListing
import com.kmp.recipes.mobile.app.ui.recipeMain.MainScreenModel
import com.kmp.recipes.mobile.app.ui.recipeMain.stateholders.SearchDataState
import com.kmp.recipes.mobile.app.ui.recipeMain.stateholders.UiState

@Composable
fun SearchRecipesList(
    paddingValue: PaddingValues,
    searchQuery: String,
    navigator: Navigator,
    searchScreenModel: SearchScreenModel
) {
    searchScreenModel.searchRecipes(searchQuery)

    val searchResultsState = searchScreenModel.state.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(paddingValue)
            .padding(top = Dimens.defaultSpacing)
            .verticalScroll(scrollState)
    ) {
        when(val uiState = searchResultsState.value) {
            is UiState.Init, UiState.Loading -> {
                // Box { CircularProgressIndicator() }
            }
            is UiState.Failure -> {
                FailureLabel(uiState.errorMessage)
            }
            is UiState.Success<*> -> {
                val searchDataState = uiState.data as SearchDataState
                ColumnX(primaryTitle = searchDataState.searchResultsLabel) {
                    RecipesListing(recipesList = searchDataState.searchResults, navigator = navigator)
                }
            }
        }
    }
}