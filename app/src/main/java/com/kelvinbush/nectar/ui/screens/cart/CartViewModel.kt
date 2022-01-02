package com.kelvinbush.nectar.ui.screens.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.data.remote.FruityService
import com.kelvinbush.nectar.domain.model.CartItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val fruityService: FruityService
) : ViewModel() {

    private var _cart = MutableLiveData<CartItemList>()
    val cart: LiveData<CartItemList>
        get() = _cart

    init {
        getCartItems()
    }

    private fun getIdToken(): String {
        val user = Firebase.auth.currentUser
        var token = ""
        user?.getIdToken(true)?.addOnSuccessListener { id ->
            token = "Bearer ${id.token}"
        }
        return token
    }

    fun refreshCart() = getCartItems()


    private fun getCartItems() = viewModelScope.launch(Dispatchers.IO) {
        _cart.value = fruityService.getCart(getIdToken())
    }
}