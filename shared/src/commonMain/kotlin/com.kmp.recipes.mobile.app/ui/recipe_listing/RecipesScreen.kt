package com.kmp.recipes.mobile.app.ui.recipe_listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.RecipesListing
import com.kmp.recipes.mobile.app.ui.common.SecondaryAppBar
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe

private const val KEY_RECIPES_SCREEN = "recipesScreenKey"

class RecipesScreen(private val title: String, private val recipes: List<Recipe>) : Screen {

    override val key: ScreenKey
        get() = KEY_RECIPES_SCREEN

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SecondaryAppBar(
                    title = title,
                    navigator = navigator
                )
            }
        ) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(it)
                    .padding(Dimens.defaultSpacing)
                    .verticalScroll(scrollState)
            ) {
                RecipesListing(recipes, navigator)
            }
        }
    }
}