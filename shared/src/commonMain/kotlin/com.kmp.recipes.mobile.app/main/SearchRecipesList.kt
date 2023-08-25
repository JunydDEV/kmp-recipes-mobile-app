package com.kmp.recipes.mobile.app.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import com.kmp.recipes.mobile.app.main.model.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SearchRecipesList(searchQuery: String) {
    val title = stringResource(SharedRes.strings.search_recipes_label)
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
        RecipesListing(
            title = title,
            recipesList = result
        )
    }
}