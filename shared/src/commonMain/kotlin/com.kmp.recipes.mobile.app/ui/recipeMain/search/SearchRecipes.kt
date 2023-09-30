package com.kmp.recipes.mobile.app.ui.recipeMain.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.ColumnX
import com.kmp.recipes.mobile.app.ui.common.FailureLabel
import com.kmp.recipes.mobile.app.ui.common.RecipeCard
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

    when (val uiState = searchResultsState.value) {
        is UiState.Init, UiState.Loading -> {
            // Box { CircularProgressIndicator() }
        }

        is UiState.Failure -> {
            FailureLabel(uiState.errorMessage)
        }

        is UiState.Success<*> -> {
            val searchDataState = uiState.data as SearchDataState
            Column(modifier = Modifier.fillMaxSize().padding(paddingValue)) {
                Text(
                    modifier = Modifier.clickable { },
                    text = searchDataState.searchResultsLabel,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(Dimens.defaultSpacing)
                ) {
                    items(searchDataState.searchResults.size) { index ->
                        RecipeCard(recipe = searchDataState.searchResults[index], navigator = navigator)
                    }
                }
            }
        }
    }

}