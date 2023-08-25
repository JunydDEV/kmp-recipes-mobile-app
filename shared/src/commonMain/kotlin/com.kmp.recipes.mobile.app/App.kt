package com.kmp.recipes.mobile.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kmp.recipes.mobile.app.main.DiscoverRecipesSection
import com.kmp.recipes.mobile.app.main.MainTopBar
import com.kmp.recipes.mobile.app.main.PopularRecipes
import com.kmp.recipes.mobile.app.main.RecipesCategories
import com.kmp.recipes.mobile.app.main.SearchRecipesList
import com.kmp.recipes.mobile.app.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesApp() {
    val searchFieldState = remember { mutableStateOf("") }

    AppTheme {
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
                    RecipesCategories()
                    PopularRecipes()
                }
            }
        }
    }
}

