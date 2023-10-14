package com.kmp.recipes.mobile.app.ui.recipeMain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
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
import com.kmp.recipes.mobile.app.ui.recipeMain.search.SearchRecipesList
import com.kmp.recipes.mobile.app.ui.recipeMain.search.SearchScreenModel
import com.kmp.recipes.mobile.app.ui.recipeMain.sections.FoodQuotesSection
import com.kmp.recipes.mobile.app.ui.recipeMain.sections.MainTopBar
import com.kmp.recipes.mobile.app.ui.recipeMain.sections.PopularRecipesSection
import com.kmp.recipes.mobile.app.ui.recipeMain.sections.RecipesCategoriesSection
import com.kmp.recipes.mobile.app.ui.recipeMain.stateholders.RecipesDataState
import com.kmp.recipes.mobile.app.ui.recipeMain.stateholders.MainScreenState
import com.kmp.recipes.mobile.app.util.getScreenModel


const val KEY_MAIN_SCREEN = "mainScreenKey"

@OptIn(ExperimentalMaterial3Api::class)
class MainScreen : Screen {

    override val key: ScreenKey
        get() = KEY_MAIN_SCREEN

    @Composable
    override fun Content() {
        val mainScreenModel = getScreenModel<MainScreenModel>()
        val searchScreenModel = getScreenModel<SearchScreenModel>()
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val mainScreenDataState = mainScreenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                MainTopBar(navigator, searchQueryState.value) {
                    searchQueryState.value = it
                }
            }
        ) { paddingValue ->
            when(val state = mainScreenDataState.value) {
                is MainScreenState.Loading, MainScreenState.Init -> {
                    CircularProgressIndicator()
                }

                is MainScreenState.Error -> {
                    FailureLabel(state.message)
                }

                is MainScreenState.Content -> {
                    MapRecipesData(
                        navigator = navigator,
                        searchQuery = searchQueryState,
                        paddingValue = paddingValue,
                        content = state,
                        searchScreenModel = searchScreenModel
                    )
                }
            }
        }

        LaunchedEffect(currentCompositeKeyHash) {
            mainScreenModel.fetchRecipesData()
        }
    }

    @Composable
    private fun MapRecipesData(
        navigator: Navigator,
        searchQuery: MutableState<String>,
        paddingValue: PaddingValues,
        content: MainScreenState.Content,
        searchScreenModel: SearchScreenModel
    ) {

        if (searchQuery.value.isNotEmpty()) {
            SearchRecipesList(
                paddingValue = paddingValue,
                searchQuery = searchQuery.value,
                navigator = navigator,
                searchScreenModel = searchScreenModel
            )
        } else {
            HomeScreenDefaultContent(
                paddingValues = paddingValue,
                navigator = navigator,
                content = content,
            )
        }
    }

    @Composable
    private fun HomeScreenDefaultContent(
        paddingValues: PaddingValues,
        navigator: Navigator,
        content: MainScreenState.Content,
    ) {
        val scrollState = rememberScrollState()
        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(paddingValues)
                .padding(start = Dimens.defaultSpacing, end = Dimens.defaultSpacing)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(Dimens.defaultSpacing)
        ) {
            Spacer(modifier = Modifier.fillMaxWidth().height(Dimens.smallSpacing))
            FoodQuotesSection(
                navigator = navigator,
                quotes = content.foodQuotes
            )
            RecipesCategoriesSection(
                navigator = navigator,
                categories = content.categories,
            )
            PopularRecipesSection(
                navigator = navigator,
                popularRecipesList = content.popularRecipes
            )
        }
    }
}