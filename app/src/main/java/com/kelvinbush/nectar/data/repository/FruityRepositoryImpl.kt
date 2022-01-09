package com.kelvinbush.nectar.data.repository

import com.kelvinbush.nectar.data.remote.FruityApi
import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.*
import javax.inject.Inject


class FruityRepositoryImpl @Inject constructor(
    private val fruityApi: FruityApi,
) : FruityRepository {
    override suspend fun getCart(authToken: String, sessionId: ShoppingSession): CartItemList {
        return fruityApi.getCart(authToken = authToken, sessionId = sessionId)
    }

    override suspend fun getAllProducts(authToken: String): AllNetworkProducts {
        return fruityApi.getAllProducts(authToken = authToken)
    }

    override suspend fun login(authToken: String): FUser {
        return fruityApi.login(authToken = authToken)
    }

    override suspend fun addToCart(authToken: String, item: CartAdd) =
        fruityApi.addToCart(authToken, item)
}