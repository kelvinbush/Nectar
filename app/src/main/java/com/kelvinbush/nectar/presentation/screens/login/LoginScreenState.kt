package com.kelvinbush.nectar.presentation.screens.login

data class LoginScreenState(
    val isLoading: Boolean,
    val errorMessage: String,
    val passwordInput: String,
    val emailInput: String,
    val result: String,
    val showPassword: Boolean,
)

data class LoginViewModelState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val passwordInput: String = "",
    val emailInput: String = "",
    val result: String = "",
    val showPassword: Boolean = false,
) {
    fun toUiState(): LoginScreenState =
        LoginScreenState(
            isLoading,
            errorMessage,
            passwordInput,
            emailInput,
            result,
            showPassword
        )
}