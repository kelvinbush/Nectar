package com.kelvinbush.nectar.domain.use_cases.get_all_products

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.AllNetworkProducts
import com.kelvinbush.nectar.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class GetAllProductsUseCase(private val repository: FruityRepository) {
    suspend operator fun invoke() = repository.getAllProducts()
}