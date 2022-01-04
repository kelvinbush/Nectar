package com.kelvinbush.nectar.viewmodel

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

    init {
        Log.d(TAG, "init: called")
        getAllProducts()
    }

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

    fun getAllProducts() {
        val user = Firebase.auth.currentUser
        if (user != null) {
            user.getIdToken(true).addOnSuccessListener {
                _idToken.value = it.token
                val token = "Bearer ${_idToken.value}"
                viewModelScope.launch {
                    _products.value = fruityApi.getAllProducts(token).result
                }
            }
        } else {
            Log.d(TAG, "getAllProducts: user not found")
        }
    }

    fun getCartItems() {
       /* viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer ${_idToken.value}"
            _cart.value = fruityApi.getCart(token)
        }*/
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