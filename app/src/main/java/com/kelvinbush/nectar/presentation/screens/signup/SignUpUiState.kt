package com.kelvinbush.nectar.presentation.screens.signup

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)