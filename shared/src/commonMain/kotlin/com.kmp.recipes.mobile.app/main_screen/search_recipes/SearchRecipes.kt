package com.kmp.recipes.mobile.app.main_screen.search_recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.common.RecipesListing
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SearchRecipesList(searchQuery: String, navigator: Navigator) {
    val title = stringResource(SharedRes.strings.search_results_label)
//
//    val result = recipes.filter { it.label.contains(searchQuery, ignoreCase = true) }
//    if (result.isEmpty()) {
//        SearchResultsNotFound()
//    } else {
//        Column(modifier = Modifier.fillMaxSize()) {
//            Text(
//                modifier = Modifier.padding(
//                    top = Dimens.defaultSpacing,
//                    start = Dimens.defaultSpacing,
//                    end = Dimens.defaultSpacing
//                ),
//                text = title,
//                style = MaterialTheme.typography.titleLarge,
//                color = MaterialTheme.colorScheme.primary
//            )
//
//            RecipesListing(recipesList = result, navigator = navigator)
//        }
//    }
}