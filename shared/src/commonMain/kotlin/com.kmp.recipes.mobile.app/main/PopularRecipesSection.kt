package com.kmp.recipes.mobile.app.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.main.model.PopularRecipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PopularRecipes() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(
                top = Dimens.defaultSpacing,
                start = Dimens.defaultSpacing,
                end = Dimens.defaultSpacing
            ),
            text = stringResource(SharedRes.strings.popular_recipes_section_header),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        val popularRecipes = listOf(
            PopularRecipe(
                image = SharedRes.images.marinated_steak_cover_image,
                title = stringResource(SharedRes.strings.popular_recipe_one_title),
                description = stringResource(SharedRes.strings.popular_recipe_one_description),
                duration = stringResource(SharedRes.strings.popular_recipe_one_duration),
                difficultyLevel = stringResource(SharedRes.strings.popular_recipe_one_difficulty_level)
            ),
            PopularRecipe(
                image = SharedRes.images.marinated_steak_cover_image,
                title = stringResource(SharedRes.strings.popular_recipe_two_title),
                description = stringResource(SharedRes.strings.popular_recipe_two_description),
                duration = stringResource(SharedRes.strings.popular_recipe_two_duration),
                difficultyLevel = stringResource(SharedRes.strings.popular_recipe_two_difficulty_level)
            ),
            PopularRecipe(
                image = SharedRes.images.marinated_steak_cover_image,
                title = stringResource(SharedRes.strings.popular_recipe_three_title),
                description = stringResource(SharedRes.strings.popular_recipe_three_description),
                duration = stringResource(SharedRes.strings.popular_recipe_three_duration),
                difficultyLevel = stringResource(SharedRes.strings.popular_recipe_three_difficulty_level)
            )
        )
        Column(
            modifier = Modifier.padding(
                start = Dimens.defaultSpacing,
                end = Dimens.defaultSpacing
            )
        ) {
            repeat(popularRecipes.size) {
                Column {
                    Card(
                        Modifier.fillMaxWidth()
                            .height(Dimens.popularRecipeImageHeight)
                            .padding(top = Dimens.smallSpacing)
                            .clipToBounds(),
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(popularRecipes[it].image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = Dimens.smallSpacing),
                        text = popularRecipes[it].title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = popularRecipes[it].description,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = Dimens.smallSpacing, bottom = Dimens.smallSpacing),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PopularRecipeCookingDuration(popularRecipes[it])
                        Spacer(Modifier.width(Dimens.defaultSpacing))
                        PopularRecipeDifficultyLevel(popularRecipes[it])
                    }
                }
            }
        }
    }
}

@Composable
fun PopularRecipeCookingDuration(recipe: PopularRecipe) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(SharedRes.images.ic_clock),
            contentDescription = stringResource(SharedRes.strings.recipe_cooking_duration_image)
        )
        Spacer(Modifier.width(Dimens.smallSpacing))
        Text(
            text = recipe.duration,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun PopularRecipeDifficultyLevel(recipe: PopularRecipe) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(SharedRes.images.ic_chef),
            contentDescription = stringResource(SharedRes.strings.recipe_difficulty_level_image)
        )
        Spacer(Modifier.width(Dimens.smallSpacing))
        Text(
            text = recipe.difficultyLevel,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}