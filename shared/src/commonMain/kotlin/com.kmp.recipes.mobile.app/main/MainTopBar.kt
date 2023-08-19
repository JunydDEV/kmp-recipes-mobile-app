package com.kmp.recipes.mobile.app.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.safeAreaPadding
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
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
                Image(
                    painter = painterResource(SharedRes.images.heart),
                    contentDescription = null
                )
            }
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(SharedRes.images.search),
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}