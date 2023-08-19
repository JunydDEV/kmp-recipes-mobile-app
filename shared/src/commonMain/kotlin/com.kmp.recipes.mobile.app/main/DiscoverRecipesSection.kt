package com.kmp.recipes.mobile.app.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun DiscoverRecipesSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.padding(
                start = Dimens.defaultSpacing,
                top = Dimens.smallSpacing
            ),
            text = stringResource(SharedRes.strings.discover_section_header),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                val cardModifier = Modifier.fillMaxWidth()
                    .height(Dimens.discoverRecipeHeight)
                    .padding(
                        start = Dimens.defaultSpacing,
                        end = Dimens.defaultSpacing,
                        top = Dimens.smallSpacing
                    )
                Card(
                    modifier = cardModifier,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    DiscoverRecipeContent()
                }

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Image(
                        modifier = Modifier.size(Dimens.discoverRecipeImageSize),
                        painter = painterResource(SharedRes.images.dumplings),
                        contentDescription = stringResource(SharedRes.strings.recipe_image)
                    )
                }
            }
        }
    }
}

@Composable
fun DiscoverRecipeContent() {
    Column(
        modifier = Modifier.padding(
            top = Dimens.largeSpacing,
            start = Dimens.largeSpacing
        )
    ) {
        Text(
            modifier = Modifier.width(200.dp),
            text = stringResource(SharedRes.strings.recipe_one_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(Modifier.height(Dimens.smallSpacing))
        Text(
            modifier = Modifier.width(200.dp),
            text = stringResource(SharedRes.strings.recipe_one_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row(
            modifier = Modifier
                .padding(top = Dimens.defaultSpacing)
                .fillMaxSize(fraction = Dimens.halfOfScreenWidth),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RecipeCookingDuration()
            RecipeDifficultyLevel()
        }
    }
}

@Composable
fun RecipeCookingDuration() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(SharedRes.images.ic_clock),
            contentDescription = stringResource(SharedRes.strings.recipe_cooking_duration_image)
        )
        Spacer(Modifier.width(Dimens.smallSpacing))
        Text(
            text = stringResource(SharedRes.strings.recipe_one_cooking_duration),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun RecipeDifficultyLevel() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(SharedRes.images.ic_chef),
            contentDescription = stringResource(SharedRes.strings.recipe_difficulty_level_image)
        )
        Spacer(Modifier.width(Dimens.smallSpacing))
        Text(
            text = stringResource(SharedRes.strings.recipe_one_cooking_difficulty_level),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
