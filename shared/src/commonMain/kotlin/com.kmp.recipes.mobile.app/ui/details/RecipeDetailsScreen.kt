package com.kmp.recipes.mobile.app.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.FailureLabel
import com.kmp.recipes.mobile.app.ui.common.ImageX
import com.kmp.recipes.mobile.app.util.getScreenModel
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

private const val KEY_RECIPES_DETAILS_SCREEN = "recipesDetailsScreenKey"

class RecipeDetailsScreen(private val id: String) : Screen {

    override val key: ScreenKey = KEY_RECIPES_DETAILS_SCREEN

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val recipeDetailsModel = getScreenModel<RecipeDetailsModel>()
        val recipeImageScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        val screenState = recipeDetailsModel.state.collectAsState()
        val favouriteState = recipeDetailsModel.favouriteStateFlow.collectAsState()

        when (screenState.value) {
            is DetailsScreenState.Init,
            is DetailsScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is DetailsScreenState.Content -> {
                val content = screenState.value as DetailsScreenState.Content
                val recipe = content.recipe
                RecipeDetailsContent(
                    recipeImageScrollBehavior = recipeImageScrollBehavior,
                    navigator = navigator,
                    recipeDetailsModel = recipeDetailsModel,
                    recipe = recipe,
                    favouriteState = favouriteState
                )
            }

            is DetailsScreenState.Error -> {
                val error = screenState.value as DetailsScreenState.Error
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    FailureLabel(error.message)
                }
            }
        }

        LaunchedEffect(currentCompositeKeyHash) {
            recipeDetailsModel.fetchDetails(id)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun RecipeDetailsContent(
        recipeImageScrollBehavior: TopAppBarScrollBehavior,
        navigator: Navigator,
        recipeDetailsModel: RecipeDetailsModel,
        recipe: Recipe,
        favouriteState: State<Boolean>
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(recipeImageScrollBehavior.nestedScrollConnection),
            topBar = { RecipeImage(recipeImageScrollBehavior, navigator, recipe) },
            containerColor = MaterialTheme.colorScheme.primary,
            floatingActionButton = { FavouriteButton(recipeDetailsModel, recipe, favouriteState) }
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
                    RecipeTitleAndDescription(recipe)

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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun RecipeImage(
        recipeImageScrollBehavior: TopAppBarScrollBehavior,
        navigator: Navigator,
        recipe: Recipe
    ) {
        ScrollableImageTopBar(
            imageHeight = Dimens.detailsRecipesImageHeight,
            imageMinHeight = Dimens.detailsRecipesImageMinHeight,
            scrollBehavior = recipeImageScrollBehavior,
            title = {
                Text(
                    textAlign = TextAlign.End,
                    text = stringResource(SharedRes.strings.title_details),
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = Dimens.appTitleSize),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            navigationIcon = {
                IconButton(onClick = { navigator.pop() }) {
                    Image(
                        painter = painterResource(SharedRes.images.ic_back),
                        contentDescription = stringResource(SharedRes.strings.back_button_description)
                    )
                }
            },
            image = {
                ImageX(
                    modifier = Modifier.fillMaxSize(),
                    url = recipe.image,
                    showOverlay = true,
                    tag = stringResource(SharedRes.strings.recipe_image_description)
                )
            }
        )
    }

    @Composable
    private fun FavouriteButton(
        recipeDetailsModel: RecipeDetailsModel,
        recipe: Recipe,
        favouriteState: State<Boolean>
    ) {
        FloatingActionButton(
            onClick = {
                recipeDetailsModel.markAsFavourite(recipe)
            },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            val color = if (favouriteState.value) {
                MaterialTheme.colorScheme.secondary
            } else {
                MaterialTheme.colorScheme.onPrimary
            }
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.Favorite,
                tint = color,
                contentDescription = null
            )
        }
    }

    @Composable
    private fun RecipeTitleAndDescription(recipe: Recipe) {
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