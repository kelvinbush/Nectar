package com.kelvinbush.nectar.presentation.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinbush.nectar.domain.model.service.AccountService
import com.kelvinbush.nectar.presentation.common.ext.isValidEmail
import com.kelvinbush.nectar.util.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.kelvinbush.nectar.R.string as AppText

private const val TAG = "LoginScreenViewModel"

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isAuthenticated: Boolean = false,
    val hasError: Boolean = false,
    val error: String = "",
)

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val accountService: AccountService,
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick() {
        if (!email.isValidEmail()) {
            uiState.value =
                uiState.value.copy(hasError = true, error = "Please enter a valid email")
            return
        }

        if (password.isBlank()) {
            uiState.value =
                uiState.value.copy(hasError = true, error = "Password cannot be blank")
            return
        }
        viewModelScope.launch() {
            loadingState.emit(LoadingState.LOADING)
            accountService.authenticate(email, password) { error ->
                if (error == null) {
                    uiState.value = uiState.value.copy(isAuthenticated = true, hasError = false)
                } else {
                    if (error.message.toString().contains("The password is invalid")) {
                        uiState.value = uiState.value.copy(hasError = true,
                            error = "Invalid Username or Password")
                    } else {
                        Log.d(TAG, "onSignInClick: $error")
                        uiState.value = uiState.value.copy(hasError = true,
                            error = error.message.toString())
                    }
                }
            }
            loadingState.emit(LoadingState.LOADED)
        }
    }
}