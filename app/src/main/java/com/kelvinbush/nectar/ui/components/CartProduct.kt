package com.kelvinbush.nectar.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import com.kelvinbush.nectar.ui.theme.price2TextStyle
import com.kelvinbush.nectar.ui.theme.priceTextStyle
import com.kelvinbush.nectar.ui.theme.productTextStyle

@Composable
fun CartProduct(image: Int, name: String, size: String, quantity: Int, price: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(97.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {

        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = image), null,
                contentScale = ContentScale.Inside
            )
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = name, style = productTextStyle)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "$size, Price", style = priceTextStyle)
            Row(
                modifier = Modifier
                    .fillMaxWidth(.65f)
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, Color(0xffe2e2e2)),
                            shape = RoundedCornerShape(33)
                        )
                        .height(30.dp)
                        .width(30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                        contentDescription = null,
                        tint = Color(0xffb3b3b3)
                    )
                }
                Text(text = quantity.toString(), style = productTextStyle)
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, Color(0xffe2e2e2)),
                            shape = RoundedCornerShape(33)
                        )
                        .height(30.dp)
                        .width(30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.close),
                    null,
                    tint = Color(0xffb3b3b3)
                )
            }
            Text(text = "$$price", style = price2TextStyle)
        }
    }
}