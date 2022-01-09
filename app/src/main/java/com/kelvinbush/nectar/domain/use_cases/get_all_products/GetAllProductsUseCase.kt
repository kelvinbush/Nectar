package com.kelvinbush.nectar.domain.use_cases.get_all_products

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.AllNetworkProducts
import com.kelvinbush.nectar.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class GetAllProductsUseCase(private val repository: FruityRepository) {
    operator fun invoke(authToken: String): Flow<Resource<AllNetworkProducts>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getAllProducts(authToken = authToken)
            emit(Resource.Success(products))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}