package com.kmp.recipes.mobile.app.recipe_details

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.data.Recipe

@Composable
fun RecipeInstructionsSection(recipe: Recipe, navigator: Navigator) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Instructions",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Column(modifier = Modifier.padding(top = Dimens.smallSpacing)) {
            val instructions = recipe.instructions!!
            repeat(instructions.size) {
                Text(
                    modifier = Modifier.padding(bottom = Dimens.smallSpacing),
                    text = instructions[it],
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray))
            }
        }

    }
}