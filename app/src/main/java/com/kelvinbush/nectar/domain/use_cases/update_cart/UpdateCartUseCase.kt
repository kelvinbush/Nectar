package com.kelvinbush.nectar.domain.use_cases.update_cart

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.UpdateCart

class UpdateCartUseCase(private val repository: FruityRepository) {
    suspend operator fun invoke(authToken: String, item: UpdateCart) =
        repository.updateCart(authToken = authToken, item = item)
}