package com.kmp.recipes.mobile.app.main_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.safeAreaPadding
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.readTextAsState
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(value: String, onChangeValue: (String) -> Unit) {

    val searchBarVisibilityState = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary)) {
        TopAppBar(
            windowInsets = WindowInsets(top = safeAreaPadding),
            title = {
                Text(
                    textAlign = TextAlign.End,
                    text = stringResource(SharedRes.strings.app_name),
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = Dimens.appTitleSize),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Rounded.Favorite,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null
                    )
                }
                IconButton(onClick = {
                    searchBarVisibilityState.value = searchBarVisibilityState.value.not()
                }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Rounded.Search,
                        tint = if (searchBarVisibilityState.value) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null
                    )
                }
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
        )

        AnimatedVisibility(visible = searchBarVisibilityState.value) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Dimens.defaultSpacing,
                        end = Dimens.defaultSpacing,
                        bottom = Dimens.defaultSpacing
                    ),
                value = value,
                onValueChange = { onChangeValue.invoke(it) },
                label = { Text(stringResource(SharedRes.strings.search_recipes_label)) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(Dimens.normalRadius),
                singleLine = true
            )
        }
    }
}