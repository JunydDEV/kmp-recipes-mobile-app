package com.kmp.recipes.mobile.app.data.datasource.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sections(
    @SerialName("food_quotes") val quotes: List<Quote>,
    @SerialName("categories") val categories: List<Category>,
    @SerialName("popular_recipes") val popularRecipesIds: List<String>,
)
