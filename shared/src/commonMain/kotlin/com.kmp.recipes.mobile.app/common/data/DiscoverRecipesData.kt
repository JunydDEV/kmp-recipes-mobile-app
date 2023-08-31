package com.kmp.recipes.mobile.app.common.data

import kotlinx.serialization.Serializable

@Serializable
data class DiscoverRecipesData(
    val id: String,
    val name: String,
    val recipesList: List<Recipe>
)
