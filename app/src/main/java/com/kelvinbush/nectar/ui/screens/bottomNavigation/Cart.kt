package com.kelvinbush.nectar.ui.screens.bottomNavigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.components.CartProduct
import com.kelvinbush.nectar.ui.components.ItemList
import com.kelvinbush.nectar.ui.theme.headerTextStyle

@Composable
fun CartScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.07f))
        Text(text = "My Cart", style = headerTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 1.dp, color = Color(0xffE2E2E2), modifier = Modifier.fillMaxWidth())
        ItemList(myList = pList) {
            CartProduct(
                image = it.image,
                name = it.name,
                size = it.size,
                quantity = it.quantity,
                price = it.price
            )
        }
        Divider(thickness = 1.dp, color = Color(0xffE2E2E2), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(30)
        ) {
            Text(text = "Go to Checkout", style = MaterialTheme.typography.button)
        }
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