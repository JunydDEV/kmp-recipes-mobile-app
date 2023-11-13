package com.kmp.recipes.mobile.app.ui.details

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
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.ImageX
import com.kmp.recipes.mobile.app.data.model.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import com.kmp.recipes.mobile.app.ui.preview.PreviewScreen
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RecipeIngredientsSection(recipe: Recipe, navigator: Navigator) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(SharedRes.strings.title_ingredients),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            val ingredients = recipe.ingredients
            repeat(ingredients.size) {
                Card(
                    modifier = Modifier
                        .size(
                            width = Dimens.ingredientImageWidth,
                            height = Dimens.ingredientImageHeight
                        )
                        .padding(top = Dimens.smallSpacing)
                        .clickable {
                            navigator.push(PreviewScreen(ingredients[it]))
                        },
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        ImageX(
                            modifier = Modifier.aspectRatio(Dimens.ingredientImageRatio)
                                .fillMaxSize()
                                .clip(RoundedCornerShape(Dimens.categoryImageRadius)),
                            url = ingredients[it].image,
                            tag = stringResource(SharedRes.strings.ingredient_image_description),
                            showOverlay = true,
                            showProgress = false
                        )

                        Text(
                            modifier = Modifier.wrapContentSize()
                                .padding(start = Dimens.smallSpacing, end = Dimens.smallSpacing),
                            text = ingredients[it].label,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White
                        )
                    }
                }
                Spacer(Modifier.padding(start = Dimens.smallSpacing))
            }
        }

    }
}