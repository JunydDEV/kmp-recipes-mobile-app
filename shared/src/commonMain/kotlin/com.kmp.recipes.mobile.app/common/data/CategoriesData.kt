package com.kmp.recipes.mobile.app.common.data

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: String,
    val image: String,
    val label: String,
    val recipes: List<String>
)
