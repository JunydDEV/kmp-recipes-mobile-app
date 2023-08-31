package com.kmp.recipes.mobile.app.main_screen.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.common.RecipesListing
import com.kmp.recipes.mobile.app.recipes_listing.RecipesScreen
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PopularRecipes(recipes: List<Recipe>, navigator: Navigator) {

    val title = stringResource(SharedRes.strings.popular_recipes_section_header)
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(
                    top = Dimens.defaultSpacing,
                    start = Dimens.defaultSpacing,
                    end = Dimens.defaultSpacing
                ),
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            val title = stringResource(SharedRes.strings.title_recipes_list)
            Text(
                modifier = Modifier.padding(
                    top = Dimens.defaultSpacing,
                    start = Dimens.defaultSpacing,
                    end = Dimens.defaultSpacing
                ).clickable {
                    navigator.push(RecipesScreen(title))
                },
                text = stringResource(SharedRes.strings.title_view_all_recipes),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        RecipesListing(recipesList = recipes, navigator = navigator)
    }
}
