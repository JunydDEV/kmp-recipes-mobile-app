package com.kmp.recipes.mobile.app.data.di

import com.kmp.recipes.mobile.app.data.datasource.FakeRecipesDataSource
import com.kmp.recipes.mobile.app.data.datasource.FakeRecipesDataSourceImpl
import com.kmp.recipes.mobile.app.data.datasource.RecipesDataService
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import com.kmp.recipes.mobile.app.data.repository.RecipesRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single { RecipesDataService() }
    single<FakeRecipesDataSource> { FakeRecipesDataSourceImpl(recipesDataService = get()) }
    single<RecipesRepository> { RecipesRepositoryImpl(dataSource = get()) }
}