package com.kmp.recipes.mobile.app.recipe_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.SecondaryAppBar
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

class RecipeDetailsScreen(private val recipe: Recipe) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val recipeDetailsModel = rememberScreenModel { RecipeDetailsModel() }
        Scaffold(
            topBar = {
                Column {
                    SecondaryAppBar(title = "Details", navigator = navigator)
                    Box(
                        modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
//                        Image(
//                            modifier = Modifier.size(300.dp),
//                            painter = painterResource(recipe.image),
//                            contentDescription = null,
//                        )
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            val scrollState = rememberScrollState()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(it)
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(scrollState)
            ) {
                Column(modifier = Modifier.fillMaxSize().padding(Dimens.defaultSpacing)) {
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
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Row(
                        modifier = Modifier.padding(
                            top = Dimens.smallSpacing,
                            bottom = Dimens.smallSpacing
                        ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RecipeCookingDuration(recipe)
                        Spacer(Modifier.width(Dimens.defaultSpacing))
                        RecipeDifficultyLevel(recipe)
                    }

                    Spacer(Modifier.height(Dimens.defaultSpacing))

                    RecipeIngredientsSection(
                        recipe = recipe,
                        navigator = navigator
                    )

                    Spacer(Modifier.height(Dimens.defaultSpacing))

                    RecipeInstructionsSection(
                        recipe = recipe,
                        navigator = navigator
                    )
                }
            }


        }
    }
}

@Composable
fun RecipeCookingDuration(recipe: Recipe) {
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