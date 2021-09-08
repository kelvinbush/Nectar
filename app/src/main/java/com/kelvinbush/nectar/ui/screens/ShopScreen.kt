package com.kelvinbush.nectar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.Screen
import com.kelvinbush.nectar.ui.components.CategoryComponent
import com.kelvinbush.nectar.ui.components.SearchTextField

@Composable
fun ShopScreen(navController1: NavController) {
    val items = (0..5).toList()
    var searchItem by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.fillMaxSize(),
       ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Image(
                painter = painterResource(id = R.drawable.ic_min_carrot),
                contentDescription = null,
                modifier = Modifier
                    .width(27.dp)
                    .height(31.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.94f)
                    .padding(vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null
                )
                Text(text = " Nairobi, Kenya")
            }
            Spacer(modifier = Modifier.height(8.dp))
            SearchTextField(searchItem = searchItem, changeEvent = { searchItem = it })
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth(0.9f)) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.carousel_1),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(0.94f),
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(items) {
                    CategoryComponent()
                }
            }
        }
    }
}