package com.kelvinbush.nectar.ui.screens.bottomNavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.components.ItemListScreenHolder
import com.kelvinbush.nectar.ui.components.ProductCard
import com.kelvinbush.nectar.ui.theme.price2TextStyle
import com.kelvinbush.nectar.ui.theme.priceTextStyle
import com.kelvinbush.nectar.ui.theme.productTextStyle

@Composable
fun FavouriteScreen() {
    ItemListScreenHolder(headText = "Favourite", proList = likedList, btnText = "Add All To Cart") {
        ProductCard(
            product = it,
            arrangement = Arrangement.Center,
            midCol = {})
    }
}

@Composable
fun LikedCard(name: String, size: String, price: Double, image: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(97.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {

        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = image), null,
                contentScale = ContentScale.Inside
            )
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = name, style = productTextStyle)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "$size, Price", style = priceTextStyle)
        }
        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "$$price", style = price2TextStyle)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.next),
                        null,
                        tint = Color.Black
                    )
                }
            }

        }
    }
}


val likedList = listOf(
    Product(R.drawable.sprite, "Sprite Can", "355ml", 1.50),
    Product(R.drawable.coke, "Diet Coke", "355ml", 1.99),
    Product(R.drawable.juice, "Apple & Grape Juice", "2l", 15.50),
    Product(R.drawable.sprite, "Coca Cola Can", "325ml", 4.99),
    Product(R.drawable.pepsi, "Sprite Can", "330ml", 4.99)
)