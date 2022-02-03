package com.kelvinbush.nectar.presentation.screens.signup


data class SignUpState(
    val isLoading: Boolean,
    val errorMessage: String,
    val nameInput: String,
    val passwordInput: String,
    val passwordConfirmationInput: String,
    val emailInput: String,
    val result: String,
    val showPassword: Boolean,
)

data class SignupViewModelState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val nameInput: String = "",
    val passwordInput: String = "",
    val passwordConfirmationInput: String = "",
    val emailInput: String = "",
    val result: String = "",
    val showPassword: Boolean = false,
) {
    fun toUiState(): SignUpState = SignUpState(
        isLoading,
        errorMessage,
        nameInput,
        passwordInput,
        passwordConfirmationInput,
        emailInput,
        result,
        showPassword)
}