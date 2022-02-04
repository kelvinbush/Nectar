package com.kelvinbush.nectar.domain

import com.kelvinbush.nectar.domain.model.*
import com.kelvinbush.nectar.util.Resource
import kotlinx.coroutines.flow.Flow

interface FruityRepository {
    suspend fun getCart(authToken: String, sessionId: ShoppingSession): CartItemList

    suspend fun getAllProducts(authToken: String): AllNetworkProducts

    suspend fun login(authToken: String): FUser

    suspend fun addToCart(authToken: String, item: CartAdd)

    suspend fun getIdToken(): String

    suspend fun removeFromCart(authToken: String, item: RemoveProduct)

    suspend fun signup(signup: SignupUser): Flow<Resource<Result>>
}