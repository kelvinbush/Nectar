package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.presentation.components.CategoryCard
import com.kelvinbush.nectar.presentation.components.SearchTextField
import com.kelvinbush.nectar.ui.theme.headerTextStyle


@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun ExploreScreen() {
    val itemsCategory = ArrayList<Category>()
    itemsCategory.addAll(list)
    var search by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.07f))
        Text(text = "Find Products", style = headerTextStyle)
        SearchTextField(searchItem = search, changeEvent = { search = it })
        Spacer(modifier = Modifier.fillMaxHeight(0.04f))
        LazyVerticalGrid(
            cells = GridCells.Fixed(2), state = rememberLazyListState(),
            contentPadding = PaddingValues(end = 16.dp, start = 8.dp)
        ) {
            itemsCategory.forEach { elem ->
                item {
                    CategoryCard(
                        bgColor = elem.color,
                        imageUrl = elem.url,
                        text = elem.text,
                        border = elem.border
                    )
                }
            }
        }
    }
}

data class Category(
    val url: Int,
    val color: Color,
    val text: String,
    val border: Color
)

val list = listOf(
    Category(
        R.drawable.veges,
        Color(0xff53b175).copy(alpha = 0.1f), "Fresh Fruits & Vegetables",
        Color(0xff53b175).copy(alpha = 0.7f)
    ),
    Category(
        R.drawable.oil,
        Color(0xffF8A44C).copy(.1f), "Cooking Oil & Ghee",
        Color(0xffF8A44C).copy(.7f)
    ),
    Category(
        R.drawable.fish,
        Color(0xffF7A593).copy(.25f),
        "Meat & Fish",
        Color(0xffF7A593).copy(1f)
    ),
    Category(
        R.drawable.snacks,
        Color(0xffD3B0E0).copy(.25f),
        "Bakery & Snacks",
        Color(0xffD3B0E0).copy(1f)
    ),
    Category(
        R.drawable.eggs,
        Color(0xffFDE598).copy(.25f),
        "Dairy & Eggs",
        Color(0xffFDE598).copy(1f)
    ),
    Category(
        R.drawable.beverages,
        Color(0xffB7DFF5).copy(.25f),
        "Beverages",
        Color(0xffB7DFF5).copy(1f)
    ),
    Category(
        R.drawable.veges,
        Color(0xff53b175).copy(alpha = 0.1f), "Fresh Fruits & Vegetables",
        Color(0xff53b175).copy(alpha = 0.7f)
    ),
    Category(
        R.drawable.oil,
        Color(0xffF8A44C).copy(.1f), "Cooking Oil & Ghee",
        Color(0xffF8A44C).copy(.7f)
    ),
    Category(
        R.drawable.fish,
        Color(0xffF7A593).copy(.25f),
        "Meat & Fish",
        Color(0xffF7A593).copy(1f)
    )
)
