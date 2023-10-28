package com.kmp.recipes.mobile.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(val image: String, val label: String, val quantity: String)
