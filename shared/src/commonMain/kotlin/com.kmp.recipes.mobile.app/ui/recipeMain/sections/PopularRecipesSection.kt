package com.kmp.recipes.mobile.app.ui.recipeMain.sections

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.ui.common.ColumnX
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.ui.recipeListing.RecipesScreen
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.RecipeCard
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PopularRecipesSection(
    allRecipesList: List<Recipe>,
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
            navigateToRecipesListing(navigator, recipesListTitle, allRecipesList)
        }
    ) {
        Spacer(modifier = Modifier.fillMaxWidth().height(Dimens.smallSpacing))
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
    recipes: List<Recipe>
) {
    navigator.push(
        RecipesScreen(
            title = primaryTitle,
            recipes = recipes
        )
    )
}
