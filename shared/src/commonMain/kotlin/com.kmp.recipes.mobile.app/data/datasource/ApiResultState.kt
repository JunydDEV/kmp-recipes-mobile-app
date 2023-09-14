package com.kmp.recipes.mobile.app.data.datasource

sealed class ApiResultState {
    class OnSuccess<T>(val data: T) : ApiResultState()
    class OnFailure(val errorMessage: String) : ApiResultState()
}