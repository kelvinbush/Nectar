package com.kelvinbush.nectar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CartProduct(
    val cartId: String,
    val name: String,
    val imageUrl: String,
    val quantity: Int,
    val price: Double,
)

@Serializable
data class CartItemList(
    val cartItems: List<CartProduct>? = emptyList(),
)
