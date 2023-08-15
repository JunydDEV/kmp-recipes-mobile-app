package com.kmp.recipes.mobile.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import com.kmp.recipes.mobile.app.theme.AppTheme
import dev.icerock.moko.resources.compose.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesApp() {
    AppTheme {
        Scaffold(topBar = {
            TopAppBar(
                windowInsets = WindowInsets(top = safeAreaPadding),
                title = {
                    Text(
                        textAlign = TextAlign.End,
                        text = "Recipes App",
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 28.sp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Image(painter = painterResource(SharedRes.images.heart), contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Image(painter = painterResource(SharedRes.images.search), contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }) {
            val mainModifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it).padding(all = 16.dp)

            Column(modifier = mainModifier) {

                Text(
                    text = "Discover",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Card(modifier = Modifier.fillMaxWidth().height(200.dp).padding(top = 10.dp)) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Recipes Banner")
                    }
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Categories",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(modifier = Modifier.padding(top = 10.dp).horizontalScroll(rememberScrollState())) {
                    repeat(3) {
                        Card(
                            Modifier.size(width = 150.dp, height = 100.dp).padding(end = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Category Name")
                            }
                        }

                    }
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Popular Recipes",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Column() {
                    repeat(3) {
                        Column {
                            Card(
                                Modifier.fillMaxWidth().height(150.dp).padding(top = 10.dp),
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Recipe Image")
                                }
                            }

                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = "Marsala Marinated Skirt Steak",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = "Skirt steak is always great on the grill and doesn't need much help, but I love how this came out…",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                    }
                }
            }
        }
    }
}

