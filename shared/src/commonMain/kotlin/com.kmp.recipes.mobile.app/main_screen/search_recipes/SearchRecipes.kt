package com.kmp.recipes.mobile.app.main_screen.search_recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.common.RecipesListing
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SearchRecipesList(searchQuery: String) {
    val title = stringResource(SharedRes.strings.search_results_label)
    val recipes = listOf(
        Recipe(
            image = SharedRes.images.marinated_steak_cover_image,
            title = stringResource(SharedRes.strings.popular_recipe_one_title),
            description = stringResource(SharedRes.strings.popular_recipe_one_description),
            duration = stringResource(SharedRes.strings.popular_recipe_one_duration),
            difficultyLevel = stringResource(SharedRes.strings.popular_recipe_one_difficulty_level)
        ),
        Recipe(
            image = SharedRes.images.pasta_cover_image,
            title = stringResource(SharedRes.strings.popular_recipe_two_title),
            description = stringResource(SharedRes.strings.popular_recipe_two_description),
            duration = stringResource(SharedRes.strings.popular_recipe_two_duration),
            difficultyLevel = stringResource(SharedRes.strings.popular_recipe_two_difficulty_level)
        ),
        Recipe(
            image = SharedRes.images.summer_sqash_cordon_cover_image,
            title = stringResource(SharedRes.strings.popular_recipe_three_title),
            description = stringResource(SharedRes.strings.popular_recipe_three_description),
            duration = stringResource(SharedRes.strings.popular_recipe_three_duration),
            difficultyLevel = stringResource(SharedRes.strings.popular_recipe_three_difficulty_level)
        )
    )

    val result = recipes.filter { it.title.contains(searchQuery) }
    if (result.isEmpty()) {
        SearchResultsNotFound()
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
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

            RecipesListing(recipesList = result)
        }
    }
}