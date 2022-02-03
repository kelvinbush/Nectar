package com.kelvinbush.nectar.data.remote

import com.kelvinbush.nectar.domain.model.*
import retrofit2.http.*

interface FruityApi {

    @GET("/api/me")
    suspend fun login(@Header("Authorization") authToken: String): FUser

    @GET("/api/product")
    suspend fun getAllProducts(@Header("Authorization") authToken: String): AllNetworkProducts

    @POST("/api/getMyCart")
    suspend fun getCart(
        @Header("Authorization") authToken: String,
        @Body sessionId: ShoppingSession,
    ): CartItemList

    @POST("/api/addCart")
    suspend fun addToCart(@Header("Authorization") authToken: String, @Body item: CartAdd)

    @POST("/api/deleteCart")
    suspend fun deleteFromCart(
        @Header("Authorization") authToken: String,
        @Body item: RemoveProduct,
    )
}