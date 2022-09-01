package com.kelvinbush.nectar.presentation.screens.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinbush.nectar.domain.model.service.AccountService
import com.kelvinbush.nectar.presentation.common.ext.isValidEmail
import com.kelvinbush.nectar.presentation.common.ext.isValidPassword
import com.kelvinbush.nectar.presentation.common.ext.passwordMatches
import com.kelvinbush.nectar.presentation.common.snackbar.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.kelvinbush.nectar.R.string as AppText

private const val TAG = "SignUpViewModel"

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
) : ViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick() {
        if (!email.isValidEmail()) {
            uiState.value =
                uiState.value.copy(hasError = true, error = "Please enter a valid email")
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            uiState.value =
                uiState.value.copy(hasError = true,
                    error = "Your password should have at least six digits and include one digit, one lower case letter and one upper case letter.")
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            uiState.value =
                uiState.value.copy(hasError = true, error = "Password do not match")
        }
        viewModelScope.launch() {
            accountService.createAccount(email, password) { error ->
                if (error == null) {
                    uiState.value = uiState.value.copy(isAuthenticated = true, hasError = false)
                } else {
                    if (error.message.toString().contains("The password is invalid")) {
                        uiState.value = uiState.value.copy(hasError = true,
                            error = "Invalid Username or Password")
                    } else {
                        uiState.value = uiState.value.copy(hasError = true,
                            error = error.message.toString())
                    }
                }
            }
        }
    }
}