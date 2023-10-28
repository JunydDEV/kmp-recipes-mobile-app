package com.kmp.recipes.mobile.app.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.safeAreaPadding
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryAppBar(
    title: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    navigator: Navigator,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        windowInsets = WindowInsets(top = safeAreaPadding),
        title = {
            Text(
                textAlign = TextAlign.End,
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = Dimens.appTitleSize),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigator.pop() }) {
                Image(
                    painter = painterResource(SharedRes.images.ic_back),
                    contentDescription = stringResource(SharedRes.strings.back_button_description)
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = backgroundColor),
        scrollBehavior = scrollBehavior
    )
}