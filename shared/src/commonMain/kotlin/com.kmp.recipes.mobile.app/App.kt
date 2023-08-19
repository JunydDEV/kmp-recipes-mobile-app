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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
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
                        text = "MrBites",
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 28.sp),
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
        }) {
            val mainModifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)

            Column(modifier = mainModifier) {

                Text(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 10.dp),
                    text = "Discover",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Box {
                    Box(contentAlignment = Alignment.TopEnd) {
                        Card(
                            modifier = Modifier.fillMaxWidth().height(250.dp)
                                .padding(start = 16.dp, end = 16.dp, top = 10.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {}

                        Image(
                            modifier = Modifier.size(180.dp),
                            painter = painterResource(SharedRes.images.dumplings),
                            contentDescription = null
                        )
                    }

                    Column(modifier = Modifier.padding(top = 25.dp, start = 30.dp)) {
                        Text(
                            text = "Potstickers\nChinese Dumplings",
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "An authentic potsticker\nrecipe using ground beef\nand ground shrimp",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Row(
                            modifier = Modifier.padding(top = 16.dp).fillMaxSize(fraction = 0.5f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Image(painter = painterResource(SharedRes.images.ic_clock), contentDescription = null)
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = "25min",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }

                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Image(painter = painterResource(SharedRes.images.ic_chef), contentDescription = null)
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = "Easy",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    text = "Categories",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier.padding(top = 0.dp, start = 16.dp, end = 16.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    val categoriesImages = listOf(
                        SharedRes.images.pizza_ibackground_image,
                        SharedRes.images.pasta_background_image,
                        SharedRes.images.steak_background_image
                    )

                    val categoriesNames = listOf("Pizza", "Pasta", "Steak")
                    repeat(3) {
                        Box(
                            modifier = Modifier.size(140.dp, 90.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Image(
                                modifier = Modifier.aspectRatio(16f / 9f)
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(10.dp)),
                                painter = painterResource(categoriesImages[it]),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )

                            Text(
                                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                                text = categoriesNames[it],
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        Spacer(Modifier.padding(start = 10.dp))
                    }
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    text = "Popular Recipes",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    repeat(3) {
                        Column {
                            Card(
                                Modifier.fillMaxWidth().height(150.dp).padding(top = 10.dp)
                                    .clipToBounds(),
                            ) {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    painter = painterResource(SharedRes.images.marinated_steak_cover_image),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
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


                            Row(
                                modifier = Modifier.padding(top = 5.dp, bottom = 16.dp).fillMaxSize(fraction = 0.5f),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row (verticalAlignment = Alignment.CenterVertically) {
                                    Image(painter = painterResource(SharedRes.images.ic_clock), contentDescription = null)
                                    Spacer(Modifier.width(10.dp))
                                    Text(
                                        text = "25min",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }

                                Row (verticalAlignment = Alignment.CenterVertically) {
                                    Image(painter = painterResource(SharedRes.images.ic_chef), contentDescription = null)
                                    Spacer(Modifier.width(10.dp))
                                    Text(
                                        text = "Easy",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

