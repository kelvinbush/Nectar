package com.kelvinbush.nectar.presentation.screens.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.domain.model.CartProduct
import com.kelvinbush.nectar.ui.theme.productTextStyle

@Composable
fun MyCart(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val itemsInCart by cartViewModel.cart.collectAsState()
    LazyColumn() {
        itemsInCart.cartItems.forEach {
            item {
                CartItem(cartItem = it)
            }
        }
    }
}


@Composable
fun CartItem(cartItem: CartProduct) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = rememberImagePainter(data = cartItem.imageUrl),
                contentDescription = stringResource(R.string.cart_item),
                contentScale = ContentScale.Fit
            )
        }
        Column {
            Text(text = cartItem.name)
            Text(text = "Quantity Price")
            QuantityToggle(
                minusOne = { /*TODO*/ },
                plusOne = {},
                quantity = cartItem.quantity
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.CLOSE_ICON)
                )
            }
            Text(text = "Kshs${cartItem.price}")
        }
    }
}

@Composable
fun QuantityToggle(
    quantity: Int = 1,
    minusOne: () -> Unit,
    plusOne: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.65f)
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { minusOne() },
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, Color(0xffe2e2e2)),
                    shape = RoundedCornerShape(33)
                )
                .size(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                contentDescription = null,
                tint = Color(0xffb3b3b3)
            )
        }
        Text(text = quantity.toString(), style = productTextStyle)
        IconButton(
            onClick = { plusOne() },
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, Color(0xffe2e2e2)),
                    shape = RoundedCornerShape(33)
                )
                .size(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
        }
    }
}