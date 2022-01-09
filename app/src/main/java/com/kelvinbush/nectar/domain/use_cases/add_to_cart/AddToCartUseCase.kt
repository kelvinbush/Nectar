package com.kelvinbush.nectar.domain.use_cases.add_to_cart

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.CartAdd

class AddToCartUseCase(private val repository: FruityRepository) {
    suspend operator fun invoke(authToken: String, item: CartAdd) =
        repository.addToCart(authToken = authToken, item = item)
}