package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.cart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.domain.model.CartProduct
import com.kelvinbush.nectar.presentation.screens.splash.SplashViewModel
import com.kelvinbush.nectar.ui.theme.headerTextStyle
import com.kelvinbush.nectar.ui.theme.itemNameTextStyle
import com.kelvinbush.nectar.ui.theme.itemPriceTextStyle

@Composable
fun MyCart(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val state = cartViewModel.state.value
    val user by splashViewModel.fUser.observeAsState()
    user?.user?.shoppingSession?.let { cartViewModel.getCartItems(it) }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "My Cart",
            style = headerTextStyle,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Divider(color = Color(0xFFB3B3B3), modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
        LazyColumn(modifier = Modifier.fillMaxHeight(0.8f)) {
            state.items.forEach { item ->
                item {
                    CartItem(item = item)
                    Divider(
                        color = Color(0xFFB3B3B3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        thickness = 1.dp
                    )
                }
            }
        }
//        EmptyCart()

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .height(57.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                Toast
                    .makeText(context, "Go To Checkout", Toast.LENGTH_SHORT)
                    .show()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF53B175),
                contentColor = Color(0xFFFFFFFF)
            ),
            shape = RoundedCornerShape(19),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp, pressedElevation = 8.dp)
        ) {
            Text(
                text = "Go To Checkout",
                style = itemPriceTextStyle,
                color = Color(0xFFFFFFFF)
            )
        }
    }
}


@Composable
fun CartItem(item: CartProduct) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp)
            .height(77.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(data = item.product.imageUrl),
                contentDescription = "image",
                contentScale = ContentScale.Fit
            )
        }
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(start = 16.dp)
                .fillMaxHeight()
        ) {
            Text(text = item.product.name, style = itemNameTextStyle)
            Text(text = "Kshs, Price", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.68f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .size(30.dp)
                        .border(
                            shape = RoundedCornerShape(30),
                            width = 1.dp,
                            color = Color(0xFFB3B3B3)
                        ),
                    enabled = item.quantity > 1,
                    onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier
                            .width(17.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = "Add Icon",
                        tint = if (item.quantity > 1) Color(0xFF53B175) else Color(0xFFB3B3B3)
                    )
                }
                Text(
                    text = "${item.quantity}",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                IconButton(
                    modifier = Modifier
                        .size(30.dp)
                        .border(
                            shape = RoundedCornerShape(30),
                            width = 1.dp,
                            color = Color(0xFFB3B3B3)
                        ),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(17.dp)
                            .align(Alignment.CenterVertically),
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon",
                        tint = Color(0xFF53B175)
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = "close icon",
                    tint = Color(0xFFB3B3B3)
                )
            }
            Text(
                text = "Kshs ${item.product.price * item.quantity}",
                style = itemPriceTextStyle,
                overflow = TextOverflow.Visible,
                maxLines = 2
            )
        }
    }
}