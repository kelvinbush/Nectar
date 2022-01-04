package com.kelvinbush.nectar.domain.use_cases

import com.kelvinbush.nectar.domain.use_cases.get_all_products.GetAllProductsUseCase
import com.kelvinbush.nectar.domain.use_cases.get_cart.GetCartUseCase

data class UseCases(
    val getCartUseCase: GetCartUseCase,
    val getAllProductsUseCase: GetAllProductsUseCase
)