package com.kmp.recipes.mobile.app.data.datasource

import com.kmp.recipes.mobile.app.data.model.RecipesData
import kotlinx.serialization.json.Json

class RecipesDataParser {

    private val json = Json { ignoreUnknownKeys = true }

    /**
     * Decodes given json text to [RecipesData]
     * */
    fun parseJsonResponse(recipesJson: String): RecipesData {
        return json.decodeFromString(recipesJson)
    }
}