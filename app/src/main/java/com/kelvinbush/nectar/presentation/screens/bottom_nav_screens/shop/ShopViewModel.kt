package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.domain.model.AllNetworkProducts
import com.kelvinbush.nectar.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {
    private val _products = MutableStateFlow(AllNetworkProducts(emptyList()))
    val products = _products

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener { id ->
            viewModelScope.launch(Dispatchers.IO) {
                id.token?.let { token ->
                    useCases.getAllProductsUseCase(authToken = token)
                        .collect { items -> _products.value = items }
                }
            }
        }
    }
}