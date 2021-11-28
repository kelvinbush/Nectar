package com.kelvinbush.nectar.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.*
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.domain.FruityUser
import com.kelvinbush.nectar.network.FruityService
import com.kelvinbush.nectar.network.NetworkProduct
import com.kelvinbush.nectar.utils.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG = "LoginScreenViewModel"

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val fruityService: FruityService
) : ViewModel() {

    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private var _idToken = MutableLiveData("")
    val idToken: LiveData<String>
        get() = _idToken

    private var _fUser = MutableLiveData<FruityUser>()
    val fUser: LiveData<FruityUser>
        get() = _fUser

    private var _products = MutableLiveData<List<NetworkProduct>>()
    val products: LiveData<List<NetworkProduct>>
        get() = _products


    init {
        Log.d(TAG, "init: called")
        getAllProducts()
    }

    fun setToken(accessToken: String) {
        Log.d(TAG, "setToken: ")
        _idToken.value = accessToken
    }

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
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

    fun signWithCredential(credential: AuthCredential) = viewModelScope.launch {
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
        viewModelScope.launch {
            val token = "Bearer $accessToken"
            Log.d(TAG, token)
            _fUser.value = fruityService.login(token)
        }
    }

    fun getAllProducts() {

        val user = Firebase.auth.currentUser
        if (user != null) {
            user.getIdToken(true).addOnSuccessListener {
                _idToken.value = it.token
                val token = "Bearer ${_idToken.value}"
                Log.d(TAG, token)
                viewModelScope.launch {
                    _products.value = fruityService.getAllProducts(token).result
                    Log.d(TAG, "getAllProducts: ${it.token}")
                }
            }
        } else {
            Log.d(TAG, "getAllProducts: user not found")
        }

    }
}