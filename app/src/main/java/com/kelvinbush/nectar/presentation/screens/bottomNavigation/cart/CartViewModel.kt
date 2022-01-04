package com.kelvinbush.nectar.presentation.screens.bottomNavigation.cart

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

    fun refreshCart() = getCartItems()

    private fun getCartItems() {
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener { id ->
            viewModelScope.launch(Dispatchers.IO) {
                id.token?.let { token ->
                    useCases.getCartUseCase(authToken = token).collect {
                        _cart.value = it
                    }
                }
            }
        }
    }
}
