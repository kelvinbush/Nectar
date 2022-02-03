package com.kelvinbush.nectar.domain.model

data class UserSignup(
    val name: String,
    val password: String,
    val passwordConfirmation: String,
    val email: String,
)
