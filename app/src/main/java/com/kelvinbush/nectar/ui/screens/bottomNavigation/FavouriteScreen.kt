package com.kelvinbush.nectar.ui.screens.bottomNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.components.ItemListScreenHolder
import com.kelvinbush.nectar.ui.components.ProductCard

@Composable
fun FavouriteScreen() {
    ItemListScreenHolder(
        headText = "Favourite", proList = likedList, btnText = "Add All To Cart"
    ) {
        ProductCard(
            product = it,
            arrangement = Arrangement.Center,
            midCol = {})
    }
}

val likedList = listOf(
    Product(R.drawable.sprite, "Sprite Can", "355ml", 1.50),
    Product(R.drawable.coke, "Diet Coke", "355ml", 1.99),
    Product(R.drawable.juice, "Apple & Grape Juice", "2l", 15.50),
    Product(R.drawable.sprite, "Coca Cola Can", "325ml", 4.99),
    Product(R.drawable.pepsi, "Sprite Can", "330ml", 4.99)
)