package com.kelvinbush.nectar.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.ui.screens.bottomNavigation.Product

@Composable
fun ItemList(myList: List<Product>, content: @Composable (prod: Product) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        myList.forEach { elem ->
            item {
                content(elem)
                if (myList.lastIndex != myList.indexOf(elem)) {
                    Divider(
                        thickness = 1.dp,
                        color = Color(0xffE2E2E2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }

        }
    }

}


