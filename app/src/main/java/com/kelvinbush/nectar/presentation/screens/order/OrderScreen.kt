package com.kelvinbush.nectar.presentation.screens.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.navigation.Screen
import com.kelvinbush.nectar.ui.theme.itemPriceTextStyle
import com.kelvinbush.nectar.ui.theme.orderText
import com.kelvinbush.nectar.ui.theme.priceTextStyle

@Composable
fun OrderScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier
            .fillMaxHeight(0.66f)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.complete_order),
                contentDescription = null)
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Your Order has been", style = orderText)
            Text(text = "accepted", style = orderText)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Your order has been placed and is on", style = priceTextStyle)
            Text(text = "its way to being processed", style = priceTextStyle)
        }
        Button(
            modifier = Modifier
                .height(57.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                navController.navigate(Screen.Shop.route)
                { launchSingleTop = true }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF53B175),
                contentColor = Color(0xFFFFFFFF)
            ),
            shape = RoundedCornerShape(19),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp,
                pressedElevation = 8.dp)
        ) {
            Text(
                text = "Back To Shop",
                style = itemPriceTextStyle,
                color = Color(0xFFFFFFFF)
            )
        }
    }
}