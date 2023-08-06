package com.firebase.kmm.app

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesApp() {
    MaterialTheme {
        Scaffold(topBar = {
            Text(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                text = "Recipes App",
                style = MaterialTheme.typography.titleLarge
            )
        }) {
            val mainModifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)

            Column(modifier = mainModifier) {

                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    text = "Discover",
                    style = MaterialTheme.typography.titleLarge
                )

                Card(modifier = Modifier.fillMaxWidth().height(200.dp).padding(10.dp)) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Recipes Banner")
                    }
                }

                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp),
                    text = "Categories",
                    style = MaterialTheme.typography.titleLarge
                )

                Row(modifier = Modifier.padding(top = 10.dp).horizontalScroll(rememberScrollState())) {
                    repeat(3) {
                        Card(
                            Modifier.size(width = 150.dp, height = 100.dp)
                                .padding(start = 10.dp)
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
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp),
                    text = "Popular Recipes",
                    style = MaterialTheme.typography.titleLarge
                )

                Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
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
                                modifier = Modifier.padding(top = 10.dp),
                                text = "Marsala Marinated Skirt Steak",
                                style = MaterialTheme.typography.titleLarge
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

