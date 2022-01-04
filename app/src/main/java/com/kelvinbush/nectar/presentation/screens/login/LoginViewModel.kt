package com.kelvinbush.nectar.presentation.screens.login

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.data.remote.FruityApi
import com.kelvinbush.nectar.domain.FruityUser
import com.kelvinbush.nectar.domain.model.CartItemList
import com.kelvinbush.nectar.domain.model.CartAdd
import com.kelvinbush.nectar.domain.model.NetworkProduct
import com.kelvinbush.nectar.domain.model.RemoveProduct
import com.kelvinbush.nectar.util.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG = "LoginScreenViewModel"

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val fruityApi: FruityApi
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

    private var _cart = MutableLiveData<CartItemList>()
    val cart: LiveData<CartItemList>
        get() = _cart


    fun setToken(accessToken: String) {
        Log.d(TAG, "setToken: ")
        _idToken.value = accessToken
    }

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
            _fUser.value = fruityApi.login(token)
        }
    }


    fun addCart(id: String, quantity: Int = 1) {
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener {
            _idToken.value = it.token
            val token = "Bearer ${_idToken.value}"
            val username = user.uid
            viewModelScope.launch(Dispatchers.IO) {
                fruityApi.addToCart(token, CartAdd(username, id, quantity))
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
                fruityApi.deleteFromCart(token, RemoveProduct(id))
            }
        }
    }
}