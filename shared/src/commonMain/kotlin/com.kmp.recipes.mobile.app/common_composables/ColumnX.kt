package com.kmp.recipes.mobile.app.common_composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.recipes.mobile.app.Dimens

@Composable
fun ColumnX(
    primaryTitle: String,
    onPrimaryClick: (() -> Unit)? = null,
    secondaryTitle: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> (Unit)
) {
    val mainModifier = Modifier.fillMaxWidth().padding(
        start = Dimens.defaultSpacing,
        end = Dimens.defaultSpacing
    )
    Column(
        modifier = mainModifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.smallSpacing)
    ) {
        TitlesRow(
            primaryTitleContent = {
                Text(
                    modifier = Modifier.clickable { onPrimaryClick?.invoke() },
                    text = primaryTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            },

            secondaryTitleContent = {
                secondaryTitle?.let {
                    Text(
                        modifier = Modifier.clickable { onSecondaryClick?.invoke() },
                        text = secondaryTitle,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
        content()
    }
}

@Composable
private fun TitlesRow(
    primaryTitleContent: @Composable RowScope.() -> Unit,
    secondaryTitleContent: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        primaryTitleContent()
        secondaryTitleContent()
    }
}