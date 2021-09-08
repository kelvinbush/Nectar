package com.kelvinbush.nectar.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R

@Composable
fun BottomNav() {
    BottomNavigation(
        elevation = 12.dp,
        backgroundColor = Color.White,
        modifier = Modifier.height(73.dp)
    ) {
        BottomNavItem(
            text = "Shop",
            modifier = Modifier.padding(start = 16.dp),
            id = R.drawable.shop
        )
        BottomNavItem(text = "Explore", modifier = Modifier, id = R.drawable.explore)
        BottomNavItem(text = "Cart", modifier = Modifier, id = R.drawable.cart)
        BottomNavItem(text = "Favourite", modifier = Modifier, id = R.drawable.favorite)
        BottomNavItem(
            text = "Account",
            modifier = Modifier.padding(end = 16.dp),
            id = R.drawable.account
        )
    }
}

@Composable
fun BottomNavItem(text: String, modifier: Modifier, id: Int) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null
        )
        Text(text = text, textAlign = TextAlign.Center)
    }
}
