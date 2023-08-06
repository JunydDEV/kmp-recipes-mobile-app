package com.firebase.kmm.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform