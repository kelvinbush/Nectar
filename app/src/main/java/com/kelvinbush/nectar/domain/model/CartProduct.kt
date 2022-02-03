package com.kelvinbush.nectar.domain.model


data class CartProduct(
    val id: String,
    val quantity: Int,
    val product: Product
)

data class Product(
    val name: String,
    val imageUrl: String,
    val price: Double,
)


data class CartItemList(
    val cartItems: List<CartProduct>? = emptyList(),
)
