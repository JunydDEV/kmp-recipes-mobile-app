package com.kmp.recipes.mobile.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform