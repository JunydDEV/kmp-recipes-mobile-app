package com.kmp.recipes.mobile.app

data class CookitApplicationConfig(val appContext: Any)

fun initCookitApplication(appContext: Any) {
    cookitApplicationConfig = CookitApplicationConfig(appContext)
}

lateinit var cookitApplicationConfig: CookitApplicationConfig

