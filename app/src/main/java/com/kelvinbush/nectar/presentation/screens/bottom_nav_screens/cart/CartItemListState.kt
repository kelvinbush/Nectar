package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.cart

import com.kelvinbush.nectar.domain.model.CartProduct

data class CartItemListState(
    val isLoading: Boolean = false,
    val items: List<CartProduct> = emptyList(),
    val error: String = "",
)