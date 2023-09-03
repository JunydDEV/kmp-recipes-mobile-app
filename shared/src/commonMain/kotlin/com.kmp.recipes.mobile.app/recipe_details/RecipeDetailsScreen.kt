package com.kmp.recipes.mobile.app.recipe_details

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.ImageX
import com.kmp.recipes.mobile.app.common.SecondaryAppBar
import com.kmp.recipes.mobile.app.data.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

private const val KEY_RECIPES_DETAILS_SCREEN = "recipesDetailsScreenKey"

class RecipeDetailsScreen(private val recipe: Recipe) : Screen {

    override val key: ScreenKey
        get() = KEY_RECIPES_DETAILS_SCREEN

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val recipeDetailsModel = rememberScreenModel { RecipeDetailsModel() }
        Scaffold(
            topBar = {
                Column {
                    SecondaryAppBar(
                        title = stringResource(SharedRes.strings.title_details),
                        navigator = navigator
                    )
                    Box(
                        modifier = Modifier.height(Dimens.detailsRecipesImageHeight).fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        ImageX(modifier = Modifier.fillMaxSize(), url = recipe.image)
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            val scrollState = rememberScrollState()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(start = Dimens.defaultSpacing, end = Dimens.defaultSpacing),
                    verticalArrangement = Arrangement.spacedBy(Dimens.smallSpacing)
                ) {

                    RecipeTitleAndDescription()

                    RecipeIngredientsSection(
                        recipe = recipe,
                        navigator = navigator
                    )

                    RecipeInstructionsSection(
                        recipe = recipe,
                        navigator = navigator
                    )
                }
            }


        }
    }

    @Composable
    private fun RecipeTitleAndDescription() {
        Column(modifier = Modifier.fillMaxWidth().padding(top = Dimens.smallSpacing)) {
            Text(
                text = recipe.label,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1
            )

            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )

            Row(
                modifier = Modifier.padding(
                    top = Dimens.smallSpacing,
                    bottom = Dimens.smallSpacing
                ),
                horizontalArrangement = Arrangement.spacedBy(Dimens.defaultSpacing)
            ) {
                RecipeCookingDuration(recipe)
                RecipeDifficultyLevel(recipe)
            }
        }
    }
}

@Composable
fun RecipeCookingDuration(recipe: Recipe) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.smallSpacing)
    ) {
        Icon(
            modifier = Modifier.size(Dimens.iconSizeMedium),
            painter = painterResource(SharedRes.images.ic_time),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            contentDescription = stringResource(SharedRes.strings.recipe_cooking_duration_image)
        )
        Text(
            text = recipe.duration,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun RecipeDifficultyLevel(recipe: Recipe) {
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