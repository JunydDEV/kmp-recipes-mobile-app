package com.kmp.recipes.mobile.app.data.db

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory() {
    fun createDriver(): SqlDriver
}