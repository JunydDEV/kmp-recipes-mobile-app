package com.kmp.recipes.mobile.app.data

import kotlinx.serialization.Serializable

@Serializable
data class PopularRecipesData(
    val id: String,
    val label: String,
    val recipes: List<String>
)
