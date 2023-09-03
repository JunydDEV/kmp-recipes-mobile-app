package com.kmp.recipes.mobile.app.main_screen.sections_composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common_composables.ImageX
import com.kmp.recipes.mobile.app.common_composables.ColumnX
import com.kmp.recipes.mobile.app.data.Category
import com.kmp.recipes.mobile.app.data.Recipe
import com.kmp.recipes.mobile.app.main_screen.MainScreenModel
import com.kmp.recipes.mobile.app.recipe_listing.RecipesScreen
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RecipesCategoriesSection(
    navigator: Navigator,
    categories: List<Category>,
    recipes: List<Recipe>,
    mainScreenModel: MainScreenModel,
) {
    val primaryTitle = stringResource(SharedRes.strings.categories_section_header)

    ColumnX(primaryTitle = primaryTitle) {
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(Dimens.defaultSpacing)
        ) {
            repeat(categories.size) {
                val title = "${categories[it].label} Recipes"
                Card(
                    Modifier.size(
                        width = Dimens.categoryImageWidth,
                        height = Dimens.categoryImageHeight
                    ).clickable {
                        val result =
                            mainScreenModel.findRecipesByCategoryId(categories[it], recipes)
                        navigator.push(RecipesScreen(title, result))
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = Dimens.cardElevation)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        ImageX(
                            modifier = Modifier.fillMaxSize(),
                            url = categories[it].image,
                            showOverlay = true,
                            showProgress = false
                        )

                        Text(
                            modifier = Modifier.padding(
                                start = Dimens.smallSpacing,
                                bottom = Dimens.smallSpacing
                            ),
                            text = categories[it].label,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}