package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop

import com.kelvinbush.nectar.domain.model.NetworkProduct

data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<NetworkProduct> = emptyList(),
    val error: String = "",
)