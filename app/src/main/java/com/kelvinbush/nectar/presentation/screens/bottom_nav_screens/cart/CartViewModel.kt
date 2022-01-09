package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.cart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.domain.model.ShoppingSession
import com.kelvinbush.nectar.domain.use_cases.UseCases
import com.kelvinbush.nectar.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(CartItemListState())
    val state = _state


//    fun refreshCart() = getCartItems()

    fun getCartItems(sessionId: ShoppingSession) {
        _state.value = CartItemListState(isLoading = true)
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener {
            useCases.getCartUseCase(authToken = it.token.toString(), sessionId = sessionId)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value =
                                CartItemListState(items = result.data?.cartItems ?: emptyList())
                        }
                        is Resource.Error -> {
                            _state.value = CartItemListState(error = result.message
                                ?: "An unexpected error occurred")
                        }
                        is Resource.Loading -> {
                            _state.value = CartItemListState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }?.addOnFailureListener {
            _state.value = CartItemListState(error = it.message
                ?: "An unexpected error occurred")
        }
    }
}
