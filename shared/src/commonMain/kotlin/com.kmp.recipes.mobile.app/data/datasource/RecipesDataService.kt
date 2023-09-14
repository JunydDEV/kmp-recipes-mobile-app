package com.kmp.recipes.mobile.app.data.datasource

import com.kmp.recipes.mobile.app.data.datasource.model.RecipesData
import kotlinx.serialization.json.Json

class RecipesDataService {

    private val json = Json { ignoreUnknownKeys = true }

    /**
     * Decodes given json text to [RecipesData]
     * */
    fun fetchRecipesData(recipesJson: String): RecipesData {
        return json.decodeFromString(recipesJson)
    }
}