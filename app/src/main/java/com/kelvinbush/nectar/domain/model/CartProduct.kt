package com.kelvinbush.nectar.domain.model

data class CartProduct(
    val cartId: String,
    val name: String,
    val imageUrl: String,
    val quantity: Int,
    val price: Double,
)

data class CartItemList(
    val cartItems:List<CartProduct>
)
