package com.kelvinbush.nectar.domain.use_cases.get_cart

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.CartItemList
import com.kelvinbush.nectar.domain.model.ShoppingSession

class GetCartUseCase(private val repository: FruityRepository) {
    suspend operator fun invoke(authToken: String, sessionId: ShoppingSession): CartItemList =
        repository.getCart(authToken = authToken, sessionId = sessionId)
}