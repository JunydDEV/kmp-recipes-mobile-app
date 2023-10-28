package com.kmp.recipes.mobile.app.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.data.model.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RecipeInstructionsSection(recipe: Recipe, navigator: Navigator) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = Dimens.smallSpacing),
    ) {
        Text(
            text = stringResource(SharedRes.strings.title_instructions),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Column(modifier = Modifier.padding(top = Dimens.smallSpacing)) {
            val instructions = recipe.instructions

            repeat(instructions.size) {
                Row(horizontalArrangement = Arrangement.spacedBy(Dimens.smallSpacing)) {
                    Text(
                        modifier = Modifier.padding(bottom = Dimens.smallSpacing),
                        text = "${it + 1}.",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        modifier = Modifier.padding(bottom = Dimens.smallSpacing),
                        text = instructions[it],
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

    }
}