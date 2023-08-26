package com.kmp.recipes.mobile.app.main_screen.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun DiscoverRecipesSection() {
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

        RecipesPager()

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipesPager() {
    val discoverRecipes = getRecipesList()
    val pagerState = rememberPagerState()
    val pageCount = discoverRecipes.size
    HorizontalPager(
        pageCount = pageCount,
        state = pagerState
    ) {
        val modifier = Modifier.fillMaxWidth()
        DiscoverRecipeCard(modifier, discoverRecipes[it])
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
            image = SharedRes.images.discover_recipes_dumplings,
            title = stringResource(SharedRes.strings.discover_recipe_one_title),
            description = stringResource(SharedRes.strings.discover_recipe_one_description),
            duration = stringResource(SharedRes.strings.discover_recipe_one_cooking_duration),
            difficultyLevel = stringResource(SharedRes.strings.discover_recipe_one_cooking_difficulty_level)
        ),
       Recipe(
           image = SharedRes.images.discover_recipes_pasta,
           title = stringResource(SharedRes.strings.discover_recipe_two_title),
           description = stringResource(SharedRes.strings.discover_recipe_two_description),
           duration = stringResource(SharedRes.strings.discover_recipe_two_cooking_duration),
           difficultyLevel = stringResource(SharedRes.strings.discover_recipe_two_cooking_difficulty_level)
       ),
       Recipe(
           image = SharedRes.images.discover_recipes_dumplings,
           title = stringResource(SharedRes.strings.discover_recipe_one_title),
           description = stringResource(SharedRes.strings.discover_recipe_one_description),
           duration = stringResource(SharedRes.strings.discover_recipe_one_cooking_duration),
           difficultyLevel = stringResource(SharedRes.strings.discover_recipe_one_cooking_difficulty_level)
       ),
    )
}

@Composable
private fun DiscoverRecipeCard(modifier: Modifier, recipe: Recipe) {
    Box(modifier = modifier) {
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
                Image(
                    modifier = Modifier.size(Dimens.discoverRecipeImageSize),
                    painter = painterResource(recipe.image),
                    contentDescription = stringResource(SharedRes.strings.recipe_image)
                )
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
            text = recipe.title,
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
            modifier = Modifier
                .padding(top = Dimens.defaultSpacing)
                .fillMaxSize(fraction = Dimens.halfOfScreenWidth),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RecipeCookingDuration(recipe.duration)
            RecipeDifficultyLevel(recipe.difficultyLevel)
        }
    }
}

@Composable
fun RecipeCookingDuration(duration: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(SharedRes.images.ic_clock),
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
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(SharedRes.images.ic_chef),
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
