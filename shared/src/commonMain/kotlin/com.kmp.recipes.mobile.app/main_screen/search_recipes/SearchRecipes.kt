package com.kmp.recipes.mobile.app.main_screen.search_recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.ColumnX
import com.kmp.recipes.mobile.app.common.RecipesListing
import com.kmp.recipes.mobile.app.data.Recipe
import com.kmp.recipes.mobile.app.main_screen.MainScreenModel
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SearchRecipesList(
    paddingValue: PaddingValues,
    searchQuery: String,
    navigator: Navigator,
    recipes: List<Recipe>,
    mainScreenModel: MainScreenModel
) {
    val result = mainScreenModel.searchRecipesBy(recipes, searchQuery)
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(paddingValue)
            .padding(top = Dimens.defaultSpacing)
            .verticalScroll(scrollState)
    ) {
        if (result.isEmpty()) {
            SearchResultsNotFound()
        } else {
            ColumnX(
                primaryTitle = stringResource(SharedRes.strings.search_results_label)
            ) {
                RecipesListing(recipesList = result, navigator = navigator)
            }
        }
    }
}