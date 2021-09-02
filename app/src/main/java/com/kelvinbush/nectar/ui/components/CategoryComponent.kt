package com.kelvinbush.nectar.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
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
                painter = painterResource(id = R.drawable.strawberries), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.9f)
            )
            Text(
                text = "Red Apple", style = itemNameTextStyle,
                modifier = Modifier.fillMaxWidth(0.9f)
                    .padding(bottom = 2.dp)
            )
            Text(
                text = "1kg, price", style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth(0.9f)
                    .padding(bottom = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(text = "$4.99", style = itemPriceTextStyle)
                Button(
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp),
                    onClick = {},
                    shape = RoundedCornerShape(23),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}