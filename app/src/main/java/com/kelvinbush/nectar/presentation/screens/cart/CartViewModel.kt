package com.kelvinbush.nectar.presentation.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.domain.model.CartItemList
import com.kelvinbush.nectar.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private var _cart = MutableStateFlow(CartItemList(emptyList()))
    val cart = _cart

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
        useCases.getCartUseCase(authToken = getIdToken()).collect {
            _cart.value = it
        }
    }
}