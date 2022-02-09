package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop

import com.kelvinbush.nectar.domain.model.NetworkProduct

data class ShopScreenState(
    val isLoading: Boolean,
    val products: List<NetworkProduct>?,
    val error: String,
)

data class ShopViewModelState(
    val isLoading: Boolean = false,
    val products: List<NetworkProduct>? = emptyList(),
    val error: String = "",
) {
    fun toUiState() = ShopScreenState(
        isLoading, products, error
    )
}