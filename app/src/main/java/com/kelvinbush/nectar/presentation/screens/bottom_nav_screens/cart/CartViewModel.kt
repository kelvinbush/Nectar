package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.cart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinbush.nectar.domain.model.FUser
import com.kelvinbush.nectar.domain.model.RemoveProduct
import com.kelvinbush.nectar.domain.model.service.AccountService
import com.kelvinbush.nectar.domain.use_cases.UseCases
import com.kelvinbush.nectar.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCases: UseCases,
    private val accountService: AccountService,
) : ViewModel() {

    private val _state = mutableStateOf(CartItemListState())
    val state = _state

    private var _fUser = MutableLiveData<FUser>()
    val fUser = _fUser

    init {
        viewModelScope.launch {
            getCartItems()
        }
    }


    private suspend fun getCartItems() {
        _state.value = CartItemListState(isLoading = true)
        useCases.getCartUseCase(authToken = accountService.getIdToken())
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
    }


    fun removeFromCart(item: RemoveProduct) {
        viewModelScope.launch {
            useCases.removeCartUseCase(accountService.getIdToken(), item = item)
            getCartItems()
        }

    }
}
