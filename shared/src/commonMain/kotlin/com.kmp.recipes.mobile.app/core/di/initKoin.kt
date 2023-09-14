package com.kmp.recipes.mobile.app.core.di

import com.kmp.recipes.mobile.app.data.di.dataModule
import mainScreenModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            dataModule,
            mainScreenModule
        )
    }