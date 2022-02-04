package com.kelvinbush.nectar.presentation.screens.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinbush.nectar.domain.model.SignupUser
import com.kelvinbush.nectar.domain.use_cases.UseCases
import com.kelvinbush.nectar.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val viewModelState = MutableStateFlow(SignupViewModelState())

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    fun onNameInputChanged(nameInput: String) {
        viewModelState.update {
            it.copy(nameInput = nameInput)
        }
    }

    fun onEmailInputChanged(emailInput: String) {
        viewModelState.update {
            it.copy(emailInput = emailInput)
        }
    }

    fun onPasswordInputChanged(passwordInput: String) {
        viewModelState.update {
            it.copy(passwordInput = passwordInput)
        }
    }

    fun onPasswordConfirmationInputChanged(passwordConfirmationInput: String) {
        viewModelState.update {
            it.copy(passwordConfirmationInput = passwordConfirmationInput)
        }
    }

    fun toggleShowPassword(show: Boolean) {
        viewModelState.update {
            it.copy(showPassword = show)
        }
    }

    fun signup(signup: SignupUser) {
        viewModelScope.launch {
            useCases.signupUseCase(signup = signup).onEach { result ->
                viewModelState.update { state ->
                    when (result) {
                        is Resource.Success -> state.copy(isLoading = false,
                            result = result.data?.name ?: "Some result", errorMessage = "")
                        is Resource.Error -> {
                            state.copy(isLoading = false,
                                errorMessage = result.message ?: "Some error", result = "")
                        }
                        is Resource.Loading -> state.copy(isLoading = true)
                    }
                }
            }.launchIn(this)
        }
    }

}