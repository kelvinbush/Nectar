package com.kelvinbush.nectar.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R

@Composable
fun ShopHeaderComponent() {
    Image(
        painter = painterResource(id = R.drawable.ic_min_carrot),
        contentDescription = null,
        modifier = Modifier
            .width(27.dp)
            .height(31.dp)
    )
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth(0.94f)
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null
        )
        Text(text = " Nairobi, Kenya")
    }
    Spacer(modifier = Modifier.height(5.dp))
}