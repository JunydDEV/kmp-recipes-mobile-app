package com.kmp.recipes.mobile.app.main.model

import dev.icerock.moko.resources.ImageResource

data class PopularRecipe(
    val image: ImageResource,
    val title: String,
    val description: String,
    val duration: String,
    val difficultyLevel: String
)
