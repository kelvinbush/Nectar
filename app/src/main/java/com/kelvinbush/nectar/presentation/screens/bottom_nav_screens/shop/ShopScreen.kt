package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.presentation.components.CategoryComponent
import com.kelvinbush.nectar.presentation.components.SearchTextField
import com.kelvinbush.nectar.presentation.components.ShopHeaderComponent

@ExperimentalCoilApi
@Composable
fun ShopScreen(
    navController: NavHostController,
    viewModel: ShopViewModel = hiltViewModel(),
) {
    val products by viewModel.products.collectAsState()
    val categories = ArrayList<String>()
    products.result?.forEach { item ->
        categories.add(item.category.name)
    }
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
            categories.toSet().forEach { category ->
                item {
                    products.result?.let {
                        CategoryComponent(
                            category = category,
                            products = it,
                            addItem = { })
                    }
                }
            }
        }
    }
}