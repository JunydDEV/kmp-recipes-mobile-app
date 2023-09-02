package com.kmp.recipes.mobile.app.data

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val quote: String,
    val author: String,
    val image: String
)