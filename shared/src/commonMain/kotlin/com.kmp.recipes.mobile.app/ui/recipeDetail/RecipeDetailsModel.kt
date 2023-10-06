package com.kmp.recipes.mobile.app.ui.recipeDetail

import cafe.adriel.voyager.core.model.ScreenModel
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository

class RecipeDetailsModel(private val repository: RecipesRepository): ScreenModel {

    fun markAsFavourite(id: String) {
        repository.markRecipeFavourite(id)
    }
}