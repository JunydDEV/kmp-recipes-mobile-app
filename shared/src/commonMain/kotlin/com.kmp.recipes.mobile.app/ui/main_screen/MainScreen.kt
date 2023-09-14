package com.kmp.recipes.mobile.app.ui.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.FailureLabel
import com.kmp.recipes.mobile.app.ui.main_screen.search_recipes_composable.SearchRecipesList
import com.kmp.recipes.mobile.app.ui.main_screen.sections_composables.FoodQuotesSection
import com.kmp.recipes.mobile.app.ui.main_screen.sections_composables.MainTopBar
import com.kmp.recipes.mobile.app.ui.main_screen.sections_composables.PopularRecipesSection
import com.kmp.recipes.mobile.app.ui.main_screen.sections_composables.RecipesCategoriesSection
import com.kmp.recipes.mobile.app.util.getScreenModel


private const val KEY_MAIN_SCREEN = "mainScreenKey"

@OptIn(ExperimentalMaterial3Api::class)
class MainScreen : Screen {

    override val key: ScreenKey = KEY_MAIN_SCREEN

    @Composable
    override fun Content() {
        val mainScreenModel = getScreenModel<MainScreenModel>()

        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val mainScreenDataState = mainScreenModel.state.collectAsState()

        LaunchedEffect(Unit) {
            mainScreenModel.fetchRecipesData()
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { MainTopBar(searchQueryState.value) { searchQueryState.value = it } }
        ) { paddingValue ->
            when(val state = mainScreenDataState.value) {
                is UiState.Loading, UiState.Init -> {
                    CircularProgressIndicator()
                }

                is UiState.Failure -> {
                    FailureLabel(state.errorMessage)
                }

                is UiState.Success<*> -> {
                    val recipesDataState = state.data as RecipesDataState
                    MapRecipesData(
                        searchQuery = searchQueryState,
                        paddingValue = paddingValue,
                        recipesDataState = recipesDataState,
                        model = mainScreenModel
                    )
                }
            }
        }
    }

    @Composable
    private fun MapRecipesData(
        searchQuery: MutableState<String>,
        paddingValue: PaddingValues,
        recipesDataState: RecipesDataState,
        model: MainScreenModel
    ) {
        val navigator = LocalNavigator.currentOrThrow

        if (searchQuery.value.isNotEmpty()) {
            SearchRecipesList(
                paddingValue = paddingValue,
                searchQuery = searchQuery.value,
                navigator = navigator,
                mainScreenModel = model
            )
        } else {
            HomeScreenDefaultContent(
                paddingValues = paddingValue,
                navigator = navigator,
                recipesDataState = recipesDataState,
                mainScreenModel = model
            )
        }
    }

    @Composable
    private fun HomeScreenDefaultContent(
        paddingValues: PaddingValues,
        navigator: Navigator,
        recipesDataState: RecipesDataState,
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
                    quotes = recipesDataState.foodQuotes
                )
            }
            item {
                RecipesCategoriesSection(
                    navigator = navigator,
                    categories = recipesDataState.categories,
                    recipes = recipesDataState.popularRecipes,
                    mainScreenModel = mainScreenModel
                )
            }
            item {
                PopularRecipesSection(
                    navigator = navigator,
                    recipes = recipesDataState.popularRecipes
                )
            }
        }
    }
}