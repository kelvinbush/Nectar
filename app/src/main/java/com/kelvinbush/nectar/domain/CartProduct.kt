package com.kelvinbush.nectar.domain

data class CartProduct(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val price: Double,
)

data class CartItemList(
    val cartItems:List<CartProduct>
)
