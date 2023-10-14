package com.kmp.recipes.mobile.app.ui.recipeMain.sections

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import com.kmp.recipes.mobile.app.ui.common.ColumnX
import com.kmp.recipes.mobile.app.ui.common.RecipeCard
import com.kmp.recipes.mobile.app.ui.recipeListing.RecipesScreen
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PopularRecipesSection(
    popularRecipesList: List<Recipe>,
    navigator: Navigator
) {
    val primaryTitle = stringResource(SharedRes.strings.popular_recipes_section_header)
    val secondaryTitle = stringResource(SharedRes.strings.title_view_all_recipes)
    val recipesListTitle = stringResource(SharedRes.strings.title_recipes_list)

    ColumnX(
        primaryTitle = primaryTitle,
        secondaryTitle = secondaryTitle,
        onSecondaryClick = {
            navigateToRecipesListing(navigator, recipesListTitle, "view all")
        }
    ) {
        popularRecipesList.forEach { recipe ->
            RecipeCard(
                recipe = recipe,
                navigator = navigator
            )
        }
    }
}

private fun navigateToRecipesListing(
    navigator: Navigator,
    primaryTitle: String,
    categoryId: String
) {
    navigator.push(
        RecipesScreen(
            title = primaryTitle,
            categoryId = categoryId
        )
    )
}
