package com.kmp.recipes.mobile.app.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.data.RecipesData
import com.kmp.recipes.mobile.app.main_screen.search_recipes.SearchRecipesList
import com.kmp.recipes.mobile.app.main_screen.sections.FoodQuotesSection
import com.kmp.recipes.mobile.app.main_screen.sections.MainTopBar
import com.kmp.recipes.mobile.app.main_screen.sections.PopularRecipesSection
import com.kmp.recipes.mobile.app.main_screen.sections.RecipesCategoriesSection
import dev.icerock.moko.resources.compose.readTextAsState

private const val KEY_MAIN_SCREEN = "mainScreenKey"

@OptIn(ExperimentalMaterial3Api::class)
class MainScreen : Screen {

    override val key: ScreenKey
        get() = KEY_MAIN_SCREEN

    @Composable
    override fun Content() {
        val mainScreenModel = rememberScreenModel { MainScreenModel() }
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val fakeRecipesText by mainScreenModel.getRecipesData().readTextAsState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { MainTopBar(searchQueryState.value) { searchQueryState.value = it } }
        ) { paddingValue ->
            fakeRecipesText?.let {
                val recipesData = mainScreenModel.serializeJsonToRecipesData(it)
                MapRecipesData(
                    searchQuery = searchQueryState,
                    paddingValue = paddingValue,
                    recipesData = recipesData,
                    model = mainScreenModel
                )
            }
        }
    }

    @Composable
    private fun MapRecipesData(
        searchQuery: MutableState<String>,
        paddingValue: PaddingValues,
        recipesData: RecipesData,
        model: MainScreenModel
    ) {
        val navigator = LocalNavigator.currentOrThrow

        if (searchQuery.value.isNotEmpty()) {
            SearchRecipesList(
                paddingValue = paddingValue,
                searchQuery = searchQuery.value,
                navigator = navigator,
                recipes = recipesData.recipesList,
                mainScreenModel = model
            )
        } else {
            HomeScreenDefaultContent(
                paddingValues = paddingValue,
                navigator = navigator,
                recipesData = recipesData,
                mainScreenModel = model
            )
        }
    }

    @Composable
    private fun HomeScreenDefaultContent(
        paddingValues: PaddingValues,
        navigator: Navigator,
        recipesData: RecipesData,
        mainScreenModel: MainScreenModel
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(paddingValues)
                .padding(top = Dimens.defaultSpacing),
            verticalArrangement = Arrangement.spacedBy(Dimens.defaultSpacing)
        ) {
            item {
                FoodQuotesSection(
                    navigator = navigator,
                    quotes = recipesData.sections.quotes
                )
            }
            item {
                RecipesCategoriesSection(
                    navigator = navigator,
                    categories = recipesData.sections.categories,
                    recipes = recipesData.recipesList,
                    mainScreenModel = mainScreenModel
                )
            }
            item {
                PopularRecipesSection(
                    navigator = navigator,
                    recipes = mainScreenModel.getPopularRecipesList(recipesData)
                )
            }
        }
    }
}