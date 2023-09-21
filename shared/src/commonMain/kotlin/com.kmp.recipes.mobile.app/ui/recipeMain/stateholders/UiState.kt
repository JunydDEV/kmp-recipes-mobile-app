package com.kmp.recipes.mobile.app.ui.recipeMain.stateholders

sealed class UiState {
    object Init : UiState()
    object Loading : UiState()
    data class Failure(val errorMessage: String) : UiState()
    data class Success<T>(val data: T) : UiState()
}
