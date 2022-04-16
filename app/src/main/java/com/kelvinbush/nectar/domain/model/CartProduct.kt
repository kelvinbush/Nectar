package com.kelvinbush.nectar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CartProduct(
    val id: String,
    val quantity: Int,
    val product: Product
)

@Serializable
data class Product(
    val name: String,
    val imageUrl: String,
    val price: Double,
)


@Serializable
data class CartItemList(
    val cartItems: List<CartProduct>? = emptyList(),
)
