package com.kmp.recipes.mobile.app.data

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: String,
    val image: String,
    val label: String,
    val description: String,
    val duration: String,
    val level: String,
    val instructions:List<String>,
    val ingredients:List<Ingredient>
)
