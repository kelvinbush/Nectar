package com.kelvinbush.nectar.data.remote

import com.kelvinbush.nectar.domain.model.CartItemList
import com.kelvinbush.nectar.domain.FruityUser
import com.kelvinbush.nectar.domain.model.CartAdd
import com.kelvinbush.nectar.domain.model.RemoveProduct
import com.kelvinbush.nectar.domain.model.AllNetworkProducts
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface FruityApi {

    @GET("/api/me")
    suspend fun login(@Header("Authorization") authToken: String): FruityUser

    @GET("/api/product")
    suspend fun getAllProducts(@Header("Authorization") authToken: String): Flow<AllNetworkProducts>

    @GET("/api/updateCart")
    suspend fun getCart(@Header("Authorization") authToken: String): Flow<CartItemList>

    @POST("/api/addCart")
    suspend fun addToCart(@Header("Authorization") authToken: String, @Body item: CartAdd)

    @DELETE("/api/updateCart")
    suspend fun deleteFromCart(@Header("Authorization") authToken: String, @Body item: RemoveProduct)
}