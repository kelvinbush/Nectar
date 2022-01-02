package com.kelvinbush.nectar.data.repository

import com.kelvinbush.nectar.data.remote.FruityApi
import com.kelvinbush.nectar.domain.model.CartItemList
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class Repository @Inject constructor(
    private val fruityApi: FruityApi
) {
    suspend fun getCart(authToken: String): Flow<CartItemList> {
        return fruityApi.getCart(authToken = authToken)
    }
}