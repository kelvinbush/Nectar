package com.kelvinbush.nectar.domain

import com.kelvinbush.nectar.domain.model.*

interface FruityRepository {
    suspend fun getCart(authToken: String, sessionId: ShoppingSession): CartItemList

    suspend fun getAllProducts(authToken: String): AllNetworkProducts

    suspend fun login(authToken: String): FUser

    suspend fun addToCart(authToken: String, item: CartAdd)
}