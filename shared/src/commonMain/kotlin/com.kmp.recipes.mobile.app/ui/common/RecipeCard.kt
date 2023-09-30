package com.kmp.recipes.mobile.app.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.recipeDetail.RecipeDetailsScreen
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RecipeCard(recipe: Recipe, navigator: Navigator) {
    Column(modifier = Modifier.clickable {
        navigateToRecipeDetails(
            recipe = recipe,
            navigator = navigator
        )
    }) {
        Card(
            modifier = Modifier.fillMaxWidth().height(Dimens.popularRecipeImageHeight),
            shape = RoundedCornerShape(Dimens.normalRadius),
        ) {
            ImageX(
                modifier = Modifier.fillMaxSize(),
                url = recipe.image,
                tag = stringResource(SharedRes.strings.recipe_category_image_description),
                showOverlay = true
            )
        }
        Text(
            modifier = Modifier.padding(top = Dimens.smallSpacing),
            text = recipe.label,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1
        )

        Text(
            text = recipe.description,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 3
        )

        Row(
            modifier = Modifier.padding(
                top = Dimens.smallSpacing,
                bottom = Dimens.smallSpacing
            ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PopularRecipeCookingDuration(recipe)
            Spacer(Modifier.width(Dimens.defaultSpacing))
            PopularRecipeDifficultyLevel(recipe)
        }
    }
}

private fun navigateToRecipeDetails(
    recipe: Recipe,
    navigator: Navigator
) {
    navigator.push(
        RecipeDetailsScreen(recipe)
    )
}

@Composable
fun PopularRecipeCookingDuration(recipe: Recipe) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(Dimens.iconSizeMedium),
            painter = painterResource(SharedRes.images.ic_time),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
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
        Icon(
            modifier = Modifier.size(Dimens.iconSizeMedium),
            painter = painterResource(SharedRes.images.ic_recipe),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            contentDescription = stringResource(SharedRes.strings.recipe_difficulty_level_image)
        )
        Spacer(Modifier.width(Dimens.smallSpacing))
        Text(
            text = recipe.level,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
