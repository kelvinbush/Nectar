package com.kelvinbush.nectar.ui.screens.bottomNavigation

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
import com.kelvinbush.nectar.ui.components.CategoryCard
import com.kelvinbush.nectar.ui.components.SearchTextField
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
                        imageUrl = R.drawable.veges,
                        text = elem.text,
                        border = elem.border
                    )
                }
            }
        }
    }
}

data class Category(
    val url: String,
    val color: Color,
    val text: String,
    val border: Color
)

val list = listOf(
    Category(
        "https://images.unsplash.com/photo-1627998393358-06afa811bf77?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjV8fGZydWl0cyUyMGFuZCUyMHZlZ2V0YWJsZXN8ZW58MHwyfDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
        Color(0xff53b175).copy(alpha = 0.1f), "Fresh Fruits & Vegetables",
        Color(0xff53b175).copy(alpha = 0.7f)
    ),
    Category(
        "https://copia.co.ke/wp-content/uploads/2020/07/KF403_630.png",
        Color(0xffF8A44C).copy(.1f), "Cooking Oil & Ghee",
        Color(0xffF8A44C).copy(.7f)
    ),
    Category(
        "https://www.gfs.com/sites/default/files/styles/open_graph_image/public/MeatPoultryFish_ICHeader.jpg?itok=6c5beb2j",
        Color(0xffF7A593).copy(.25f),
        "Meat & Fish",
        Color(0xffF7A593).copy(1f)
    ),
    Category(
        "https://c8.alamy.com/comp/C3CB1R/bakery-snacks-pastry-isolated-on-white-background-C3CB1R.jpg",
        Color(0xffD3B0E0).copy(.25f),
        "Bakery & Snacks",
        Color(0xffD3B0E0).copy(1f)
    ),
    Category(
        "https://image.shutterstock.com/image-photo/glass-jug-milk-two-eggs-260nw-69657883.jpg",
        Color(0xffFDE598).copy(.25f),
        "Dairy & Eggs",
        Color(0xffFDE598).copy(1f)
    ),
    Category(
        "https://media.nutrition.org/wp-content/uploads/2019/06/beverages-carbonated-drink-cold-drink-1571849.jpg",
        Color(0xffB7DFF5).copy(.25f),
        "Beverages",
        Color(0xffB7DFF5).copy(1f)
    ),
    Category(
        "https://images.unsplash.com/photo-1627998393358-06afa811bf77?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjV8fGZydWl0cyUyMGFuZCUyMHZlZ2V0YWJsZXN8ZW58MHwyfDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
        Color(0xff53b175).copy(alpha = 0.1f), "Fresh Fruits & Vegetables",
        Color(0xff53b175).copy(alpha = 0.7f)
    ),
    Category(
        "https://copia.co.ke/wp-content/uploads/2020/07/KF403_630.png",
        Color(0xffF8A44C).copy(.1f), "Cooking Oil & Ghee",
        Color(0xffF8A44C).copy(.7f)
    ),
    Category(
        "https://www.gfs.com/sites/default/files/styles/open_graph_image/public/MeatPoultryFish_ICHeader.jpg?itok=6c5beb2j",
        Color(0xffF7A593).copy(.25f),
        "Meat & Fish",
        Color(0xffF7A593).copy(1f)
    )
)
