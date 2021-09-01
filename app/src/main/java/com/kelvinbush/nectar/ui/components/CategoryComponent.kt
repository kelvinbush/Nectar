package com.kelvinbush.nectar.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.kelvinbush.nectar.ui.theme.itemNameTextStyle
import com.kelvinbush.nectar.ui.theme.itemPriceTextStyle


@Composable
fun CategoryComponent() {

}

@Composable
fun FoodItemComposable() {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(18),
        border = BorderStroke(2.dp, Color(0xffe2e2e2))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.apple), contentDescription = null,
                modifier = Modifier
                    .height(63.dp)
                    .width(104.dp)
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
                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(17)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null
                    )
                }
            }
        }
    }
}