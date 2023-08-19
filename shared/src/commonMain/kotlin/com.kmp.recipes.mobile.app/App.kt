package com.kmp.recipes.mobile.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.recipes.mobile.app.main.DiscoverRecipesSection
import com.kmp.recipes.mobile.app.main.MainTopBar
import com.kmp.recipes.mobile.app.main.PopularRecipes
import com.kmp.recipes.mobile.app.main.RecipesCategories
import com.kmp.recipes.mobile.app.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesApp() {
    AppTheme {
        Scaffold(
            topBar = { MainTopBar() }
        ) {
            val mainModifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)

            Column(modifier = mainModifier) {
                DiscoverRecipesSection()
                RecipesCategories()
                PopularRecipes()
            }
        }
    }
}

