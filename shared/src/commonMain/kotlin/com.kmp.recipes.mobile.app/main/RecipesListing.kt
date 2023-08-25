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
import com.kmp.recipes.mobile.app.main.model.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun RecipesListing(title: String, recipesList: List<Recipe>) {
    Column(modifier = Modifier.fillMaxWidth()) {
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

        Column(
            modifier = Modifier.padding(
                start = Dimens.defaultSpacing,
                end = Dimens.defaultSpacing
            )
        ) {
            repeat(recipesList.size) {
                Column {
                    Card(
                        Modifier.fillMaxWidth()
                            .height(Dimens.popularRecipeImageHeight)
                            .padding(top = Dimens.smallSpacing)
                            .clipToBounds(),
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(recipesList[it].image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = Dimens.smallSpacing),
                        text = recipesList[it].title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1
                    )

                    Text(
                        text = recipesList[it].description,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 3
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = Dimens.smallSpacing, bottom = Dimens.smallSpacing),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PopularRecipeCookingDuration(recipesList[it])
                        Spacer(Modifier.width(Dimens.defaultSpacing))
                        PopularRecipeDifficultyLevel(recipesList[it])
                    }
                }
            }
        }
    }
}

@Composable
fun PopularRecipeCookingDuration(recipe: Recipe) {
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
fun PopularRecipeDifficultyLevel(recipe: Recipe) {
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