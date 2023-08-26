package com.kmp.recipes.mobile.app.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.main_screen.sections.DiscoverRecipesSection
import com.kmp.recipes.mobile.app.main_screen.sections.MainTopBar
import com.kmp.recipes.mobile.app.main_screen.sections.PopularRecipes
import com.kmp.recipes.mobile.app.main_screen.sections.RecipesCategories
import com.kmp.recipes.mobile.app.main_screen.search_recipes.SearchRecipesList

class MainScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val mainScreenModel = rememberScreenModel { MainScreenModel() }

        val searchFieldState = remember { mutableStateOf("") }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                MainTopBar(searchFieldState.value) {
                    searchFieldState.value = it
                }
            }
        ) {
            val mainModifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(it)

            Column(modifier = mainModifier) {
                if (searchFieldState.value.isNotEmpty() && searchFieldState.value.length > 3) {
                    SearchRecipesList(searchFieldState.value)
                } else {
                    DiscoverRecipesSection()
                    RecipesCategories(navigator)
                    PopularRecipes(navigator)
                }
            }
        }
    }
}