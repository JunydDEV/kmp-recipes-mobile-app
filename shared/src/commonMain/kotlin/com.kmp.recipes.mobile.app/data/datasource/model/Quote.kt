package com.kmp.recipes.mobile.app.data.datasource.model

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val quote: String,
    val author: String,
    val image: String
)