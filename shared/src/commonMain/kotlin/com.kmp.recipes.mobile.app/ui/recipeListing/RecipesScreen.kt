package com.kmp.recipes.mobile.app.ui.recipeListing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.RecipeCard
import com.kmp.recipes.mobile.app.ui.common.SecondaryAppBar

private const val KEY_RECIPES_SCREEN = "recipesScreenKey"

class RecipesScreen(private val title: String, private val recipes: List<Recipe>) : Screen {

    override val key: ScreenKey = KEY_RECIPES_SCREEN

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { SecondaryAppBar(title = title, navigator = navigator) }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(it),
                contentPadding = PaddingValues(Dimens.defaultSpacing)
            ) {
                items(recipes.size) { index ->
                    RecipeCard(recipe = recipes[index], navigator = navigator)
                }
            }
        }
    }
}