package com.kelvinbush.nectar.network

import com.kelvinbush.nectar.domain.CartItemList
import com.kelvinbush.nectar.domain.FruityUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface FruityService {

    @GET("/api/me")
    suspend fun login(@Header("Authorization") authToken: String): FruityUser

    @GET("/api/product")
    suspend fun getAllProducts(@Header("Authorization") authToken: String): Result

    @GET("/api/updateCart")
    suspend fun getCart(@Header("Authorization") authToken: String): CartItemList

    @POST("/api/addCart")
    suspend fun addToCart(@Header("Authorization") authToken: String, @Body item: CartAdd)
}