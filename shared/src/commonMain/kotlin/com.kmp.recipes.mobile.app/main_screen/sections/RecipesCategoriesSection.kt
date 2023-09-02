package com.kmp.recipes.mobile.app.main_screen.sections

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
import com.kmp.recipes.mobile.app.common.ImageX
import com.kmp.recipes.mobile.app.common.data.Category
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.common.data.RecipesData
import com.kmp.recipes.mobile.app.recipes_listing.RecipesScreen
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RecipesCategories(navigator: Navigator, recipesData: RecipesData) {
    val categories = recipesData.sections.categories
    val recipes = recipesData.recipesList

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.padding(
                top = Dimens.defaultSpacing,
                start = Dimens.defaultSpacing,
                end = Dimens.defaultSpacing
            ),
            text = stringResource(SharedRes.strings.categories_section_header),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier
                .padding(start = Dimens.defaultSpacing, end = Dimens.defaultSpacing)
                .horizontalScroll(rememberScrollState())
        ) {
            repeat(categories.size) {
                val title = "${categories[it].label} Recipes"
                Box(
                    modifier = Modifier.size(
                        width = Dimens.categoryImageWidth,
                        height = Dimens.categoryImageHeight
                    ).clickable {
                        navigator.push(RecipesScreen(title, recipes))
                    },
                    contentAlignment = Alignment.BottomStart
                ) {
                    ImageX(
                        modifier = Modifier.aspectRatio(Dimens.categoryImageRatio)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(Dimens.categoryImageRadius)),
                        url = categories[it].image,
                        showOverlay = true
                    )

                    Text(
                        modifier = Modifier.padding(
                            start = Dimens.smallSpacing,
                            bottom = Dimens.smallSpacing
                        ),
                        text = categories[it].label,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(Modifier.padding(start = Dimens.smallSpacing))
            }
        }

    }
}