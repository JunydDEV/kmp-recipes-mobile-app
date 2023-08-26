package com.kmp.recipes.mobile.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.kmp.recipes.mobile.app.main_screen.MainScreen
import com.kmp.recipes.mobile.app.theme.AppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecipesApp() {
    AppTheme {
        Navigator(MainScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}

