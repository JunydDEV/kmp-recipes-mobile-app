package com.kmp.recipes.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kmp.recipes.mobile.app.MainView
import com.kmp.recipes.mobile.app.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin {
            androidLogger()
            androidContext(applicationContext)
        }

        setContent {
            MainView()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}

