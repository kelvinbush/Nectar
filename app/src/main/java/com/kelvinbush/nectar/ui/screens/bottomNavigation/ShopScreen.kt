package com.kelvinbush.nectar.ui.screens.bottomNavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.components.CategoryComponent
import com.kelvinbush.nectar.ui.components.SearchTextField
import com.kelvinbush.nectar.ui.components.ShopHeaderComponent
import com.kelvinbush.nectar.viewmodel.LoginScreenViewModel

@Composable
fun ShopScreen(navController1: NavController, viewModel: LoginScreenViewModel) {
    val items = (0..5).toList()
    var searchItem by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ShopHeaderComponent()
        SearchTextField(searchItem = searchItem, changeEvent = { searchItem = it })
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.carousel_1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            items(items) {
                CategoryComponent()
            }
        }
    }
}