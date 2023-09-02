package com.kmp.recipes.mobile.app.main_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.common.data.RecipesData
import com.kmp.recipes.mobile.app.main_screen.search_recipes.SearchRecipesList
import com.kmp.recipes.mobile.app.main_screen.sections.DiscoverRecipesSection
import com.kmp.recipes.mobile.app.main_screen.sections.MainTopBar
import com.kmp.recipes.mobile.app.main_screen.sections.PopularRecipes
import com.kmp.recipes.mobile.app.main_screen.sections.RecipesCategories
import dev.icerock.moko.resources.compose.readTextAsState

class MainScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val mainScreenModel = rememberScreenModel { MainScreenModel() }
        val searchFieldState = rememberSaveable { mutableStateOf("") }
        val recipesDataState = mainScreenModel.getRecipesData().readTextAsState()
        val recipesDataFlow = mainScreenModel.recipesDataFlow.collectAsState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                MainTopBar(searchFieldState.value) {
                    searchFieldState.value = it
                }
            }
        ) { paddingValue ->
            if (recipesDataState.value?.isNotEmpty() == true) {
                LaunchedEffect(Unit) {
                    mainScreenModel.serializeJsonToRecipesData(recipesDataState.value)
                }
                recipesDataFlow.value?.let {
                    if (searchFieldState.value.isNotEmpty()) {
                        SearchRecipesList(searchFieldState.value, navigator)
                    } else {
                        HomeScreenDefaultContent(
                            paddingValues = paddingValue,
                            navigator = navigator,
                            recipesData = it,
                            mainScreenModel = mainScreenModel
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun HomeScreenDefaultContent(
        paddingValues: PaddingValues,
        navigator: Navigator,
        recipesData: RecipesData,
        mainScreenModel: MainScreenModel
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(paddingValues)) {
            item {
                DiscoverRecipesSection(
                    navigator = navigator,
                    recipesList = recipesData.sections.discover)
            }
            item {
                RecipesCategories(
                    navigator = navigator,
                    recipesData = recipesData)
            }
            item {
                PopularRecipes(
                    recipes = mainScreenModel.getPopularRecipesList(recipesData),
                    navigator = navigator
                )
            }
        }
    }
}