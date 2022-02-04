package com.kelvinbush.nectar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginCredentials(
    val email: String,
    val password: String,
)
