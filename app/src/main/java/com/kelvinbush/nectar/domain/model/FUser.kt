package com.kelvinbush.nectar.domain.model


data class FUser(
    val user: User,
)

data class ShoppingSession(
    val id: String,
    val total: Int,
)

data class User(
    val username: String? = "",
    val email: String? = "",
    val firstName: String? = "",
    val shoppingSession: ShoppingSession,
)