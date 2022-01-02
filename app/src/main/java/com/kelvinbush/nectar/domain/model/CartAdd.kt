package com.kelvinbush.nectar.domain.model

data class CartAdd(
    val username: String,
    val productId: String,
    val quantity: Int
)
