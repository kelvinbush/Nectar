package com.kelvinbush.nectar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SignupUser(
    val name: String,
    val password: String,
    val passwordConfirmation: String,
    val email: String,
)

@Serializable
data class Result(
    val name: String
)
