package com.kelvinbush.nectar.network

import com.kelvinbush.nectar.domain.CartItemList
import com.kelvinbush.nectar.domain.FruityUser
import retrofit2.http.GET
import retrofit2.http.Header

interface FruityService {

    @GET("/api/me")
    suspend fun login(@Header("Authorization") authToken: String): FruityUser

    @GET("/api/product")
    suspend fun getAllProducts(@Header("Authorization") authToken: String): Result

    @GET("/api/updateCart")
    suspend fun getCart(@Header("Authorization")authToken: String):CartItemList
}