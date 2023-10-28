package com.kmp.recipes.mobile.app.data.db

import android.content.Context
import com.kmp.recipes.mobile.app.db.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.mp.KoinPlatform.getKoin

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        val context: Context = getKoin().get()
        return AndroidSqliteDriver(AppDatabase.Schema, context, "cookit.db")
    }
}
