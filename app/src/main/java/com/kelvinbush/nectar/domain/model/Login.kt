package com.kelvinbush.nectar.domain.model

data class Login(
    val accessToken: String,
    val refreshToken: String,
)