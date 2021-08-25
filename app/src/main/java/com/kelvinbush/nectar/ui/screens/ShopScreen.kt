package com.kelvinbush.nectar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.components.SearchTextField

@Composable
fun ShopScreen() {
    var searchItem by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_min_carrot),
                contentDescription = null,
                modifier = Modifier
                    .width(27.dp)
                    .height(31.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null
                )
                Text(text = " Nairobi, Kenya")
            }
            Spacer(modifier = Modifier.height(8.dp))
            SearchTextField(searchItem = searchItem, changeEvent = { searchItem = it })
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.carousel_1),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.85f),
            )
        }
    }
}