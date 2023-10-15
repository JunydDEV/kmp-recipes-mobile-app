package com.kmp.recipes.mobile.app.ui.listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.FailureLabel
import com.kmp.recipes.mobile.app.ui.common.RecipeCard
import com.kmp.recipes.mobile.app.ui.common.SecondaryAppBar
import com.kmp.recipes.mobile.app.util.getScreenModel

private const val KEY_RECIPES_SCREEN = "recipesScreenKey"

data class RecipesScreen(private val title: String, private val categoryId: String) : Screen {

    override val key: ScreenKey = KEY_RECIPES_SCREEN

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val recipesScreenModel = getScreenModel<RecipesScreenModel>()
        val screenState = recipesScreenModel.state.collectAsState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { SecondaryAppBar(title = title, navigator = navigator) }
        ) {
            Box(Modifier.fillMaxSize().padding(it)) {
                when (screenState.value) {
                    is RecipesListingScreenState.Loading, RecipesListingScreenState.Init -> {
                        CircularProgressIndicator()
                    }

                    is RecipesListingScreenState.Error -> {
                        val failure = screenState.value as RecipesListingScreenState.Error
                        val errorMessage = failure.message
                        FailureLabel(errorMessage)
                    }

                    is RecipesListingScreenState.Content -> {
                        val content = screenState.value as RecipesListingScreenState.Content
                        val list = content.recipesList
                        RecipesListingContent(navigator, list)
                    }
                }
            }
        }

        LaunchedEffect(currentCompositeKeyHash) {
            recipesScreenModel.fetchRecipesList(categoryId)
        }
    }

    @Composable
    private fun RecipesListingContent(navigator: Navigator, list: List<Recipe>) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(Dimens.defaultSpacing)
        ) {
            items(list.size) { index ->
                RecipeCard(recipe = list[index], navigator = navigator)
            }
        }
    }
}