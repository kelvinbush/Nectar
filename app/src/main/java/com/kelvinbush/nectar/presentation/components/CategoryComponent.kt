package com.kelvinbush.nectar.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.domain.model.NetworkProduct
import com.kelvinbush.nectar.ui.theme.itemCategoryTextStyle
import com.kelvinbush.nectar.ui.theme.itemNameTextStyle
import com.kelvinbush.nectar.ui.theme.itemPriceTextStyle
import com.kelvinbush.nectar.ui.theme.seeAllTextStyle


@ExperimentalCoilApi
@Composable
fun CategoryComponent(
    category: String,
    products: List<NetworkProduct>,
    addItem: (id: String) -> Unit
) {
    val sortedProducts = products.filter {
        it.category.name == category
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = category, style = itemCategoryTextStyle)
            Text(
                text = "See all",
                style = seeAllTextStyle,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(sortedProducts) { product ->
                FoodItemComposable(product, addingToCart = { id -> addItem(id) })
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@ExperimentalCoilApi
@Composable
fun FoodItemComposable(product: NetworkProduct, addingToCart: (id: String) -> Unit) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(10),
        border = BorderStroke(1.dp, Color(0xffe2e2e2)),
        modifier = Modifier
            .height(170.dp)
            .width(134.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberImagePainter(product.imageUrl), contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.9f)
            )
            Text(
                text = product.name, style = itemNameTextStyle,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 2.dp)
            )
            Text(
                text = "Kshs, price", style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(text = product.price.toString(), style = itemPriceTextStyle)
                Button(
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp),
                    onClick = {
                        addingToCart(product.id)
                    },
                    shape = RoundedCornerShape(33),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}