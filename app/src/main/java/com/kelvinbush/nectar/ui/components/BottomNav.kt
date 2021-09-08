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
        elevation = 8.dp,
        backgroundColor = Color.White,
        modifier = Modifier.height(73.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = painterResource(id = R.drawable.shop), contentDescription = null)
            Text(text = "Shop", textAlign = TextAlign.Center)
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.explore),
                contentDescription = null
            )
            Text(text = "Explore", textAlign = TextAlign.Center)
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cart),
                contentDescription = null
            )
            Text(text = "Cart", textAlign = TextAlign.Center)
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.favorite),
                contentDescription = null
            )
            Text(text = "Favourite", textAlign = TextAlign.Center)
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.account),
                contentDescription = null
            )
            Text(text = "Account", textAlign = TextAlign.Center)
        }
    }
}
