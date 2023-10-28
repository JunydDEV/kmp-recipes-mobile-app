package com.kmp.recipes.mobile.app.data.di

import com.kmp.recipes.mobile.app.data.db.Database
import com.kmp.recipes.mobile.app.data.db.DatabaseDriverFactory
import com.kmp.recipes.mobile.app.data.datasource.FakeRecipesDataSource
import com.kmp.recipes.mobile.app.data.datasource.FakeRecipesDataSourceImpl
import com.kmp.recipes.mobile.app.data.datasource.RecipesDataParser
import com.kmp.recipes.mobile.app.data.datasource.RecipesDataReader
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import com.kmp.recipes.mobile.app.data.repository.RecipesRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single { RecipesDataParser() }
    single { DatabaseDriverFactory() }
    single { Database(databaseDriverFactory = get()) }
    single { RecipesDataReader() }
    single<FakeRecipesDataSource> {
        FakeRecipesDataSourceImpl(
            recipesDataReader = get(),
            recipesDataParser = get(),
            database = get()
        )
    }
    single<RecipesRepository> { RecipesRepositoryImpl(dataSource = get()) }
}