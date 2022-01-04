package com.kelvinbush.nectar.domain.use_cases.get_all_products

import com.kelvinbush.nectar.data.repository.Repository
import com.kelvinbush.nectar.domain.model.AllNetworkProducts
import kotlinx.coroutines.flow.Flow


class GetAllProductsUseCase(private val repository: Repository) {
    suspend operator fun invoke(authToken: String): Flow<AllNetworkProducts> {
        return repository.getAllProducts(authToken)
    }
}