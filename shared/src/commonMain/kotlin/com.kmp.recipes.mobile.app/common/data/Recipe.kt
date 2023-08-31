package com.kmp.recipes.mobile.app.common.data

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: String? = null,
    val image: String,
    val label: String,
    val description: String,
    val duration: String,
    val level: String
)
