package com.kmp.recipes.mobile.app.ui.recipeMain.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.FailureLabel
import com.kmp.recipes.mobile.app.ui.common.RecipeCard

@Composable
fun SearchRecipesList(
    paddingValue: PaddingValues,
    searchQuery: String,
    navigator: Navigator,
    searchScreenModel: SearchScreenModel
) {
    LaunchedEffect(key1 = searchQuery) {
        searchScreenModel.searchRecipes(searchQuery)
    }

    val searchResultsState = searchScreenModel.state.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(paddingValue)) {
        when (val uiState = searchResultsState.value) {
            is SearchScreenState.Loading, SearchScreenState.Init -> {
                // Ignore these cases.
            }

            is SearchScreenState.Error -> {
                FailureLabel(uiState.message)
            }

            is SearchScreenState.Content -> {
                Text(
                    modifier = Modifier.padding(
                        top = Dimens.defaultSpacing,
                        start = Dimens.defaultSpacing,
                        end = Dimens.defaultSpacing
                    ),
                    text = uiState.searchResultsLabel,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(Dimens.defaultSpacing)
                ) {
                    items(uiState.searchResults.size) { index ->
                        RecipeCard(recipe = uiState.searchResults[index], navigator = navigator)
                    }
                }
            }
        }
    }
}