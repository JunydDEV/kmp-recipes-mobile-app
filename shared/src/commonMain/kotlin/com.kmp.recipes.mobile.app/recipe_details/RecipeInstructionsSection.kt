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

        val instructions = listOf(
            "Place the shrimp in the work bowl of a food processor",
            "Process until the shrimp are finely ground",
            "Set aside in a large bowl",
            "Working in batches, process the ground beef to a fine grind, and set aside with the shrimp",
            "Combine the shrimp and ground beef with ginger, shallot, green onions, napa cabbage, soy sauce, sesame oil, salt and pepper, and white sugar, and mix the ingredients until thoroughly combined",
        )

        Column(modifier = Modifier.padding(top = Dimens.smallSpacing)) {

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