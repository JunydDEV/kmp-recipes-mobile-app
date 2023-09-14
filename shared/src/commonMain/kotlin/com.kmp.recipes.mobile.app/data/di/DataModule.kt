package com.kmp.recipes.mobile.app.data.di

import com.kmp.recipes.mobile.app.data.datasource.FakeRecipesDataSource
import com.kmp.recipes.mobile.app.data.datasource.FakeRecipesDataSourceImpl
import com.kmp.recipes.mobile.app.data.datasource.RecipesDataService
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import com.kmp.recipes.mobile.app.data.repository.RecipesRepositoryImpl
import com.kmp.recipes.mobile.app.ui.main_screen.MainScreenModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val dataModule = module {
    single { RecipesDataService() }
    single<FakeRecipesDataSource> {
        FakeRecipesDataSourceImpl(
            recipesDataService = get()
        )
    }
    single<RecipesRepository> { RecipesRepositoryImpl(dataSource = get()) }
    factory { MainScreenModel(repository = get()) }
}