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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.ImageX
import com.kmp.recipes.mobile.app.data.Recipe
import com.kmp.recipes.mobile.app.recipes_listing.RecipesScreen
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import com.seiko.imageloader.rememberAsyncImagePainter
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
            val ingredients = recipe.ingredients
            repeat(ingredients.size) {
                Card (
                    modifier = Modifier.size(
                        width = Dimens.ingredientImageWidth,
                        height = Dimens.ingredientImageHeight
                    ).padding(top = Dimens.smallSpacing).clipToBounds(),
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        ImageX(
                            modifier = Modifier.aspectRatio(Dimens.ingredientImageRatio)
                                .fillMaxSize()
                                .clip(RoundedCornerShape(Dimens.categoryImageRadius)),
                            url = ingredients[it].image,
                            showOverlay = true,
                            showProgress = false
                        )

                        Text(
                            modifier = Modifier.wrapContentSize()
                                .padding(start = Dimens.smallSpacing, end = Dimens.smallSpacing),
                            text = ingredients[it].label,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                Spacer(Modifier.padding(start = Dimens.smallSpacing))
            }
        }

    }
}