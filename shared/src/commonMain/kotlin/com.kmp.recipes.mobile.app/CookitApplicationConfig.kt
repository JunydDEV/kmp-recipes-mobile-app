package com.kmp.recipes.mobile.app

data class CookitApplicationConfig(val appContext: Any)

lateinit var cookitApplicationConfig: CookitApplicationConfig

fun initCookitApplication(appContext: Any) {
    cookitApplicationConfig = CookitApplicationConfig(appContext)
}