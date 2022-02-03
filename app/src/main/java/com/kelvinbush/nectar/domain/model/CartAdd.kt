package com.kelvinbush.nectar.domain.model

data class CartAdd(
    val sessionId: String,
    val productId: String,
    val quantity: Int
)
