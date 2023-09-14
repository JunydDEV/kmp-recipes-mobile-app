package com.kmp.recipes.mobile.app.ui.main_screen.search_recipes_composable

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
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SearchResultsNotFound(errorMessage: String) {
    Column(
        modifier = Modifier.height(Dimens.emptyLayoutHeight).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.secondary)
        )
    }
}