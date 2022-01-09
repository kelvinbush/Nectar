package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.domain.model.CartAdd
import com.kelvinbush.nectar.domain.use_cases.UseCases
import com.kelvinbush.nectar.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(ProductListState())
    val state = _state


    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        _state.value = ProductListState(isLoading = true)
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener {
            useCases.getAllProductsUseCase(authToken = it.token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value =
                            ProductListState(products = result.data?.result ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = ProductListState(error = result.message
                            ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        _state.value = ProductListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }?.addOnFailureListener {
            _state.value = ProductListState(error = it.message
                ?: "An unexpected error occurred")
        }
    }

    fun addToCart(item: CartAdd) {
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener { id ->
            viewModelScope.launch(Dispatchers.IO) {
                id.token?.let { token ->
                    useCases.addToCartUseCase(authToken = token, item = item)
                }
            }
        }
    }
}