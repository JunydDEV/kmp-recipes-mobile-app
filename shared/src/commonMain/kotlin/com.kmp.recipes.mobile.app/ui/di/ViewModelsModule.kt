package com.kmp.recipes.mobile.app.ui.di

import com.kmp.recipes.mobile.app.ui.details.RecipeDetailsModel
import com.kmp.recipes.mobile.app.ui.listing.RecipesScreenModel
import com.kmp.recipes.mobile.app.ui.main.MainScreenModel
import com.kmp.recipes.mobile.app.ui.main.search.SearchScreenModel
import org.koin.dsl.module

val viewModelsModule = module {
    factory { MainScreenModel(repository = get()) }
    factory { SearchScreenModel(repository = get()) }
    factory { RecipeDetailsModel(repository = get()) }
    factory { RecipesScreenModel(repository = get()) }
}