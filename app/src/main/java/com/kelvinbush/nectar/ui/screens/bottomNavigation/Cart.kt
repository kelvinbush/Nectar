package com.kelvinbush.nectar.ui.screens.bottomNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.components.ItemListScreenHolder
import com.kelvinbush.nectar.ui.components.LastColumnCart
import com.kelvinbush.nectar.ui.components.ProductCard
import com.kelvinbush.nectar.viewmodel.LoginScreenViewModel

@Composable
fun CartScreen(viewModel: LoginScreenViewModel) {
    val itemsInCart by viewModel.cart.observeAsState()
    val refreshCart by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = refreshCart) {
        viewModel.getCartItems()
    }

    itemsInCart?.let { cartItemList ->
        ItemListScreenHolder(
            headText = "My Cart", proList = cartItemList, btnText = "Go to Checkout"
        ) {
            ProductCard(
                product = it, endCol = {
                    LastColumnCart(price = it.price, iconId = R.drawable.close)
                },
                lastColAlignment = Alignment.End,
                lastColArrangement = Arrangement.SpaceBetween
            )
        }
    }
}
