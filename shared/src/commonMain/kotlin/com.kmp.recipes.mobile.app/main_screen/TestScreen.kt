package com.kmp.recipes.mobile.app.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.readTextAsState

@Composable
fun TestScreen() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        val fileText = SharedRes.files.recipes_fake_data.readTextAsState()
        Text(fileText.value ?: "")
    }
}