package com.kelvinbush.nectar.ui.screens.bottomNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.components.ItemListScreenHolder
import com.kelvinbush.nectar.ui.components.LastColumnCart
import com.kelvinbush.nectar.ui.components.ProductCard

@Composable
fun CartScreen() {
   ItemListScreenHolder(headText = "My Cart", proList = pList, btnText = "Go to Checkout") {
       ProductCard(
           product = it, endCol = {
               LastColumnCart(price = it.price, iconId = R.drawable.close)
           },
           lastColAlignment = Alignment.End,
           lastColArrangement = Arrangement.SpaceBetween
       )
   }

}

data class Product(
    val image: Int,
    val name: String,
    val size: String,
    val price: Double,
    var quantity: Int = 1
)

val pList = listOf(
    Product(R.drawable.pepper, "Bell Pepper Red", "1kg", 4.99),
    Product(R.drawable.chicken, "Egg Chicken Red", "4pcs", 1.99),
    Product(R.drawable.bananas, "Organic Bananas", "12kg", 3.00),
    Product(R.drawable.ginger, "Ginger", "250gms", 2.99),
)