package com.kmp.recipes.mobile.app.data.di

import com.kmp.recipes.mobile.app.ui.di.viewModelsModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(dataModule, viewModelsModule)
    }