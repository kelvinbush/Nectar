package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinbush.nectar.domain.model.CartAdd
import com.kelvinbush.nectar.domain.model.FUser
import com.kelvinbush.nectar.domain.model.service.AccountService
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
    private val accountService: AccountService,
) : ViewModel() {

    private val _state = mutableStateOf(ProductListState())
    val state = _state

    private var _fUser = MutableLiveData<FUser>()
    val fUser = _fUser


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllProducts()
        }
    }

    private suspend fun getAllProducts() {
        useCases.getAllProductsUseCase(authToken = accountService.getIdToken()).onEach { result ->
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
    }


    fun addToCart(id: String, quantity: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.addToCartUseCase(authToken = accountService.getIdToken(),
                item = CartAdd(productId = id, quantity))
        }
    }
}