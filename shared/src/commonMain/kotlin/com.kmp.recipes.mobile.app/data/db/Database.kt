package com.kmp.recipes.mobile.app.data.db

import com.kmp.recipes.mobile.app.data.model.Recipe
import com.kmp.recipes.mobile.app.db.AppDatabase
import comkmprecipesmobileappdb.Favourites

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun addToFavourites(recipe: Recipe) {
        dbQuery.transaction {
            dbQuery.insertIntoFavourites(
                recipeId = recipe.id,
                recipeTitle = recipe.label
            )
        }
    }

    internal fun removeFromFavourites(id: String) {
        dbQuery.transaction {
            dbQuery.deleteFromFavourites(
                recipeId = id,
            )
        }
    }

    internal fun fetchRecipeById(id: String): List<String> {
        return dbQuery.selectFavouritesById(id).executeAsList().map { it.recipeId }
    }

    internal fun fetchAllFavouriteRecipes(): List<String> {
        return dbQuery.selectAllFavourites().executeAsList().map { it.recipeId }
    }
}