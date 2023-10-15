package com.kmp.recipes.mobile.app.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kmp.recipes.mobile.app.safeAreaPadding
import com.kmp.recipes.mobile.app.ui.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrollableImageTopBar(
    modifier: Modifier = Modifier,
    imageHeight: Dp,
    imageMinHeight: Dp,
    scrollBehavior: TopAppBarScrollBehavior,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    image: @Composable () -> Unit = {},
) {
    val heightOffsetLimit =
        with(LocalDensity.current) { -(imageHeight.toPx() - imageMinHeight.toPx()) }

    SideEffect {
        if (scrollBehavior.state.heightOffsetLimit != heightOffsetLimit) {
            scrollBehavior.state.heightOffsetLimit = heightOffsetLimit
        }
    }
    val height = LocalDensity.current.run {
        Dimens.detailsRecipesImageHeight.toPx() + scrollBehavior.state.heightOffset
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(LocalDensity.current.run { height.toDp() })
    ) {

        image()

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = safeAreaPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.padding(TopAppBarHorizontalPadding)) {
                    navigationIcon()
                }
                Box(Modifier.padding(TopAppBarHorizontalPadding)) {
                    title()
                }
            }

            val actionsRow = @Composable {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }

            Box(Modifier.padding(TopAppBarHorizontalPadding)) {
                actionsRow()
            }
        }
    }
}

private val TopAppBarHorizontalPadding = 4.dp
