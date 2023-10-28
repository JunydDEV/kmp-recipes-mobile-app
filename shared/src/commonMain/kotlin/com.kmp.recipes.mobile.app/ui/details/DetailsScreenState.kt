package com.kmp.recipes.mobile.app.ui.details

import com.kmp.recipes.mobile.app.data.model.Recipe

sealed class DetailsScreenState {
    object Init : DetailsScreenState()
    object Loading : DetailsScreenState()
    data class Error(val message: String) : DetailsScreenState()
    data class Content(val recipe: Recipe) : DetailsScreenState()
}
