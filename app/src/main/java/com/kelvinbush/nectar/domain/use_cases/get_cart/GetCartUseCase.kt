package com.kelvinbush.nectar.domain.use_cases.get_cart

import com.kelvinbush.nectar.data.repository.Repository
import com.kelvinbush.nectar.domain.model.CartItemList
import kotlinx.coroutines.flow.Flow

class GetCartUseCase(private val repository: Repository) {
    suspend operator fun invoke(authToken: String): Flow<CartItemList> =
        repository.getCart(authToken = authToken)
}