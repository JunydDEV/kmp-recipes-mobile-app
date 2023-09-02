package com.kmp.recipes.mobile.app.recipes_listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.common.RecipesListing
import com.kmp.recipes.mobile.app.common.SecondaryAppBar
import com.kmp.recipes.mobile.app.common.data.Recipe

class RecipesScreen(private val title: String, private val recipes: List<Recipe>) : Screen {

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
            Column(modifier = Modifier.fillMaxSize().padding(it)) {
                RecipesListing(recipes, navigator)
            }
        }
    }
}