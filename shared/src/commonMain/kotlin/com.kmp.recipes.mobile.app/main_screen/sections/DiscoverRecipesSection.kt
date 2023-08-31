package com.kmp.recipes.mobile.app.main_screen.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.recipe_details.RecipeDetailsScreen
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun DiscoverRecipesSection(navigator: Navigator) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.padding(
                start = Dimens.defaultSpacing,
                top = Dimens.smallSpacing
            ),
            text = stringResource(SharedRes.strings.discover_section_header),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        RecipesPager(navigator)

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipesPager(navigator: Navigator) {
    val discoverRecipes = getRecipesList()
    val pagerState = rememberPagerState()
    val pageCount = discoverRecipes.size
    HorizontalPager(
        pageCount = pageCount,
        state = pagerState
    ) {
        val modifier = Modifier.fillMaxWidth()
        DiscoverRecipeCard(modifier, discoverRecipes[it], navigator)
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = Dimens.smallSpacing),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) {
            val color =
                if (it == pagerState.currentPage) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            Box(
                Modifier.size(Dimens.smallSpacing).background(
                    color = color,
                    shape = CircleShape
                )
            )
            Spacer(Modifier.width(Dimens.smallSpacing))
        }
    }
}

@Composable
fun getRecipesList(): List<Recipe> {
    return listOf(
        Recipe(
            image = "",
            label = stringResource(SharedRes.strings.discover_recipe_one_title),
            description = stringResource(SharedRes.strings.discover_recipe_one_description),
            duration = stringResource(SharedRes.strings.discover_recipe_one_cooking_duration),
            level = stringResource(SharedRes.strings.discover_recipe_one_cooking_difficulty_level)
        ),
        Recipe(
            image = "",
            label = stringResource(SharedRes.strings.discover_recipe_two_title),
            description = stringResource(SharedRes.strings.discover_recipe_two_description),
            duration = stringResource(SharedRes.strings.discover_recipe_two_cooking_duration),
            level = stringResource(SharedRes.strings.discover_recipe_two_cooking_difficulty_level)
        ),
        Recipe(
            image = "",
            label = stringResource(SharedRes.strings.discover_recipe_one_title),
            description = stringResource(SharedRes.strings.discover_recipe_one_description),
            duration = stringResource(SharedRes.strings.discover_recipe_one_cooking_duration),
            level = stringResource(SharedRes.strings.discover_recipe_one_cooking_difficulty_level)
        ),
    )
}

@Composable
private fun DiscoverRecipeCard(modifier: Modifier, recipe: Recipe, navigator: Navigator) {
    Box(modifier = modifier.clickable { navigator.push(RecipeDetailsScreen(recipe)) }) {
        Box(modifier = Modifier.fillMaxWidth()) {
            val cardModifier = Modifier.fillMaxWidth()
                .padding(
                    start = Dimens.defaultSpacing,
                    end = Dimens.defaultSpacing,
                    top = Dimens.smallSpacing,
                )
            Card(
                modifier = cardModifier,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                DiscoverRecipeContent(recipe)
            }

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
//                Image(
//                    modifier = Modifier.size(Dimens.discoverRecipeImageSize),
//                    painter = painterResource(recipe.image),
//                    contentDescription = stringResource(SharedRes.strings.recipe_image)
//                )
            }
        }
    }
}

@Composable
fun DiscoverRecipeContent(recipe: Recipe) {
    Column(
        modifier = Modifier.padding(
            top = Dimens.defaultSpacing,
            start = Dimens.defaultSpacing,
            bottom = Dimens.defaultSpacing
        )
    ) {
        Text(
            modifier = Modifier.width(200.dp),
            text = recipe.label,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 2
        )
        Spacer(Modifier.height(Dimens.smallSpacing))
        Text(
            modifier = Modifier.width(200.dp),
            text = recipe.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 3
        )
        Row(
            modifier = Modifier.padding(top = Dimens.defaultSpacing).fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            RecipeCookingDuration(recipe.duration)
            RecipeDifficultyLevel(recipe.level)
        }
    }
}

@Composable
fun RecipeCookingDuration(duration: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(Dimens.iconSizeSmall),
            painter = painterResource(SharedRes.images.ic_time),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            contentDescription = stringResource(SharedRes.strings.recipe_cooking_duration_image)
        )
        Spacer(Modifier.width(Dimens.smallSpacing))
        Text(
            text = duration,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun RecipeDifficultyLevel(difficultyLevel: String) {
    Row(
        modifier = Modifier.padding(start = Dimens.defaultSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(Dimens.iconSizeSmall),
            painter = painterResource(SharedRes.images.ic_recipe),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            contentDescription = stringResource(SharedRes.strings.recipe_difficulty_level_image)
        )
        Spacer(Modifier.width(Dimens.smallSpacing))
        Text(
            text = difficultyLevel,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
