package com.kelvinbush.nectar.presentation.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinbush.nectar.domain.model.service.AccountService
import com.kelvinbush.nectar.presentation.common.ext.isValidEmail
import com.kelvinbush.nectar.presentation.common.snackbar.SnackbarManager
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

    fun onSignInClick(): Boolean {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return false
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return false
        }
        var check = ""
        viewModelScope.launch() {
            loadingState.emit(LoadingState.LOADING)
            accountService.authenticate(email, password) { error ->
                if (error == null) {
                    check = "Check"
                } else {
                    Log.d(TAG, "onSignInClick: $error")
                }
            }
            loadingState.emit(LoadingState.LOADED)
        }
        return check.isNotEmpty()
    }

    /*private var _idToken = MutableLiveData("")
    val idToken: LiveData<String>
        get() = _idToken

    private var _fUser = MutableLiveData<FUser>()
    val fUser = _fUser

    fun signInWithEmailAndPassword(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loadingState.emit(LoadingState.LOADING)
                val user = Firebase.auth.signInWithEmailAndPassword(email, password).await().user
                user?.getIdToken(true)?.addOnSuccessListener {
                    _idToken.value = it.token
                }
                loadingState.emit(LoadingState.LOADED)
            } catch (e: Exception) {
                loadingState.emit(LoadingState.error(e.localizedMessage))
            }
        }

    fun signWithCredential(credential: AuthCredential) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "signWithCredential: called")
        try {
            loadingState.emit(LoadingState.LOADING)
            val user = Firebase.auth.signInWithCredential(credential).await().user
            user?.getIdToken(true)?.addOnSuccessListener {
                _idToken.value = it.token
            }
            loadingState.emit(LoadingState.LOADED)
        } catch (e: Exception) {
            loadingState.emit(LoadingState.error(e.localizedMessage))
        }
    }

    fun login(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer $accessToken"
            Log.d(TAG, token)
            _fUser.value = useCases.loginUseCase(authToken = token)
        }
    }
    fun addCart(id: String, quantity: Int = 1) {
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener {
            _idToken.value = it.token
            val token = "Bearer ${_idToken.value}"
            val username = user.uid
            viewModelScope.launch(Dispatchers.IO) {
//                fruityApi.addToCart(token, CartAdd(username, id, quantity))
            }
            Log.d(TAG, "addCart: $id")
        }
    }

    fun removeFromCart(id: String) {
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener {
            _idToken.value = it.token
            val token = "Bearer ${_idToken.value}"
            viewModelScope.launch(Dispatchers.IO) {
//                fruityApi.deleteFromCart(token, RemoveProduct(id))
            }
        }
    }*/
}