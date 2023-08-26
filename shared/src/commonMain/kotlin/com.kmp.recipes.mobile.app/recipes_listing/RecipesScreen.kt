package com.kmp.recipes.mobile.app.recipes_listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kmp.recipes.mobile.app.common.RecipesListing
import com.kmp.recipes.mobile.app.common.SecondaryAppBar
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.stringResource

class RecipesScreen(private val title: String) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val recipes = listOf(
            Recipe(
                image = SharedRes.images.marinated_steak_cover_image,
                title = stringResource(SharedRes.strings.popular_recipe_one_title),
                description = stringResource(SharedRes.strings.popular_recipe_one_description),
                duration = stringResource(SharedRes.strings.popular_recipe_one_duration),
                difficultyLevel = stringResource(SharedRes.strings.popular_recipe_one_difficulty_level)
            ),
            Recipe(
                image = SharedRes.images.pasta_cover_image,
                title = stringResource(SharedRes.strings.popular_recipe_two_title),
                description = stringResource(SharedRes.strings.popular_recipe_two_description),
                duration = stringResource(SharedRes.strings.popular_recipe_two_duration),
                difficultyLevel = stringResource(SharedRes.strings.popular_recipe_two_difficulty_level)
            ),
            Recipe(
                image = SharedRes.images.summer_sqash_cordon_cover_image,
                title = stringResource(SharedRes.strings.popular_recipe_three_title),
                description = stringResource(SharedRes.strings.popular_recipe_three_description),
                duration = stringResource(SharedRes.strings.popular_recipe_three_duration),
                difficultyLevel = stringResource(SharedRes.strings.popular_recipe_three_difficulty_level)
            )
        )
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

            Column(modifier = Modifier.fillMaxSize().padding(it).verticalScroll(scrollState)) {
                RecipesListing(recipes)
            }
        }
    }
}