package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.domain.model.CartAdd
import com.kelvinbush.nectar.domain.use_cases.UseCases
import com.kelvinbush.nectar.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {


    private val viewModelState = MutableStateFlow(ShopViewModelState())
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            useCases.getAllProductsUseCase().onEach { result ->
                viewModelState.update { state ->
                    when (result) {
                        is Resource.Success -> state.copy(products = result.data?.result,
                            isLoading = false,
                            error = "")
                        is Resource.Error -> state.copy(error = result.message
                            ?: "An error occurred", products = emptyList(), isLoading = false)
                        is Resource.Loading -> state.copy(isLoading = true, error = "")
                    }
                }
            }.launchIn(this)
        }
    }

    private fun addToCart(item: CartAdd) {
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