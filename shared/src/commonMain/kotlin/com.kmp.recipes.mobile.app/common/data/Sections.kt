package com.kmp.recipes.mobile.app.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sections(
    @SerialName("discover_recipes") val discover: List<Recipe>,
    @SerialName("categories") val categories: List<Category>,
    @SerialName("popular_recipes") val popularRecipes: PopularRecipesData
)
