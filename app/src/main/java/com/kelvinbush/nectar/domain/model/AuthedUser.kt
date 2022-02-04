package com.kelvinbush.nectar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthedUser(
    val id: String,
    val imageUrl: String,
    val name: String,
    val email: String,
)
