package com.kelvinbush.nectar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResult(
    val accessToken: String,
    val refreshToken: String,
    val user: AuthedUser
)