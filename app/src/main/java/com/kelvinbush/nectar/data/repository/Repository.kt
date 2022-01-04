package com.kelvinbush.nectar.data.repository

import com.kelvinbush.nectar.data.remote.FruityApi
import com.kelvinbush.nectar.domain.model.AllNetworkProducts
import com.kelvinbush.nectar.domain.model.CartItemList
import com.kelvinbush.nectar.domain.model.FUser
import com.kelvinbush.nectar.domain.model.ShoppingSession
import javax.inject.Inject


class Repository @Inject constructor(
    private val fruityApi: FruityApi,
) {
    suspend fun getCart(authToken: String, sessionId: ShoppingSession): CartItemList {
        return fruityApi.getCart(authToken = authToken, sessionId = sessionId)
    }

    suspend fun getAllProducts(authToken: String): AllNetworkProducts {
        return fruityApi.getAllProducts(authToken = authToken)
    }

    suspend fun login(authToken: String): FUser {
        return fruityApi.login(authToken = authToken)
    }

}