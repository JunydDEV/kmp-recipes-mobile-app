package com.kmp.recipes.mobile.app.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.kmp.recipes.mobile.app.theme.AppTheme
import com.kmp.recipes.mobile.app.ui.main.MainScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecipesApp() {
    AppTheme {
        Navigator(
            screen = MainScreen(),
        ) { navigator ->
            SlideTransition(navigator)
        }
    }
}

