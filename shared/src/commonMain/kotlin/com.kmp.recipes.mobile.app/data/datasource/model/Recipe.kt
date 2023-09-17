package com.kmp.recipes.mobile.app.data.datasource.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Recipe(
    val id: String,
    val image: String,
    val label: String,
    val description: String,
    val duration: String,
    val level: String,
    val instructions: List<String>,
    val ingredients: List<Ingredient>,
    @Transient val isFavourite: Boolean = false
)
