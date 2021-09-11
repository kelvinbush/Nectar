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
import com.kelvinbush.nectar.ui.theme.headerTextStyle

@Composable
fun FavouriteScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.07f))
        Text(text = "Favourite", style = headerTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 1.dp, color = Color(0xffE2E2E2), modifier = Modifier.fillMaxWidth())
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
            Text(text = "Add All to Cart", style = MaterialTheme.typography.button)
        }
    }
}

data class Liked(
    val image: Int,
    val name: String,
    val size: String,
    val price: Double,
)
val likedList = listOf(
    Liked(R.drawable.sprite, "Sprite Can","355ml", 1.50),
    Liked(R.drawable.coke, "Diet Coke","355ml", 1.99),
    Liked(R.drawable.juice, "Apple & Grape Juice","2l", 15.50),
    Liked(R.drawable.sprite, "Coca Cola Can","325ml", 4.99),
    Liked(R.drawable.pepsi, "Sprite Can","355ml", 1.50)
)