package com.kelvinbush.nectar.domain.use_cases.remove_from_cart

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.RemoveProduct

class RemoveCartUseCase(private val repository: FruityRepository) {
    suspend operator fun invoke(authToken: String, item: RemoveProduct) =
        repository.removeFromCart(authToken = authToken, item = item)
}