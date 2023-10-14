package com.kmp.recipes.mobile.app.ui.di

import com.kmp.recipes.mobile.app.ui.recipeDetail.RecipeDetailsModel
import com.kmp.recipes.mobile.app.ui.recipeListing.RecipesScreenModel
import com.kmp.recipes.mobile.app.ui.recipeMain.MainScreenModel
import com.kmp.recipes.mobile.app.ui.recipeMain.search.SearchScreenModel
import org.koin.dsl.module

val viewModelsModule = module {
    factory { MainScreenModel(repository = get()) }
    factory { SearchScreenModel(repository = get()) }
    factory { RecipeDetailsModel(repository = get()) }
    factory { RecipesScreenModel(repository = get()) }
}