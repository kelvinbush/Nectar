package com.kelvinbush.nectar.network

import com.kelvinbush.nectar.domain.FruityUser
import retrofit2.http.GET
import retrofit2.http.Header

interface FruityService {

    @GET("/api/me")
    suspend fun login(@Header("authorization") authToken: String): FruityUser

    @GET("/api/product")
    suspend fun getAllProducts(@Header("authorization") authToken: String): List<NetworkProduct>
}