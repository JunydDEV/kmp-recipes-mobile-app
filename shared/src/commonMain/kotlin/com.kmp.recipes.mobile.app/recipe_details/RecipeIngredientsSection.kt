package com.kmp.recipes.mobile.app.recipe_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.recipes_listing.RecipesScreen
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RecipeIngredientsSection(recipe: Recipe, navigator: Navigator) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            val categoriesImages = listOf(
                SharedRes.images.pizza_background_image,
                SharedRes.images.pasta_background_image,
                SharedRes.images.steak_background_image
            )
            val categoriesNames = listOf(
                SharedRes.strings.category_pizza,
                SharedRes.strings.category_pasta,
                SharedRes.strings.category_steak
            )

            repeat(categoriesImages.size) {
                Box(
                    modifier = Modifier.size(
                        width = Dimens.ingredientImageWidth,
                        height = Dimens.ingredientImageHeight
                    ).padding(top = Dimens.smallSpacing),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Image(
                        modifier = Modifier.aspectRatio(Dimens.ingredientImageRatio)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(Dimens.categoryImageRadius)),
                        painter = painterResource(categoriesImages[it]),
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(SharedRes.strings.recipe_category_image_description)
                    )

                    Text(
                        modifier = Modifier.padding(
                            start = Dimens.smallSpacing,
                            bottom = Dimens.smallSpacing
                        ),
                        text = stringResource(categoriesNames[it]),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(Modifier.padding(start = Dimens.smallSpacing))
            }
        }

    }
}