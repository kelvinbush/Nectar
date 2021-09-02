package com.kelvinbush.nectar.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.theme.itemCategoryTextStyle
import com.kelvinbush.nectar.ui.theme.itemNameTextStyle
import com.kelvinbush.nectar.ui.theme.itemPriceTextStyle
import com.kelvinbush.nectar.ui.theme.seeAllTextStyle


@Composable
fun CategoryComponent() {
    val itemsList = (0..10).toList()
    Column(modifier = Modifier.fillMaxWidth(0.94f)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Exclusive Offer", style = itemCategoryTextStyle)
            Text(text = "See all", style = seeAllTextStyle)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(itemsList) {
                FoodItemComposable()
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun FoodItemComposable() {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(10),
        border = BorderStroke(1.dp, Color(0xffe2e2e2)),
        modifier = Modifier
            .height(170.dp)
            .width(134.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.apple), contentDescription = null,
                modifier = Modifier.background(Color(0xffffffff))
            )
            Text(
                text = "Red Apple", style = itemNameTextStyle,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Text(
                text = "1kg, price", style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(text = "$4.99", style = itemPriceTextStyle)
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(46.dp)
                        .width(46.dp),
                    shape = RoundedCornerShape(17)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(0.7f)
                    )
                }
            }
        }
    }
}