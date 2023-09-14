package com.kmp.recipes.mobile.app.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.recipes.mobile.app.ui.Dimens

@Composable
fun FailureLabel(message: String) {
    Column(
        modifier = Modifier.height(Dimens.emptyLayoutHeight).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.secondary)
        )
    }
}