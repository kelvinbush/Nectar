package com.kelvinbush.nectar.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R

@Composable
fun BottomNav() {
    BottomAppBar(
        elevation = 8.dp,
        cutoutShape = RoundedCornerShape(
            topStartPercent = 23,
            topEndPercent = 23,
            bottomEndPercent = 0,
            bottomStartPercent = 0
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(painter = painterResource(id = R.drawable.shop), contentDescription = null)
                Text(text = "Shop")
            }
            Column() {
                Icon(
                    painter = painterResource(id = R.drawable.explore),
                    contentDescription = null
                )
                Text(text = "Explore")
            }
            Column() {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = null
                )
                Text(text = "Cart")
            }
            Column() {
                Icon(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = null
                )
                Text(text = "Favourite")
            }
            Column() {
                Icon(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = null
                )
                Text(text = "Account")
            }
        }
    }
}