package com.kmp.recipes.mobile.app.common.data

import dev.icerock.moko.resources.ImageResource

data class Recipe(
    val image: ImageResource,
    val title: String,
    val description: String,
    val duration: String,
    val difficultyLevel: String
)
