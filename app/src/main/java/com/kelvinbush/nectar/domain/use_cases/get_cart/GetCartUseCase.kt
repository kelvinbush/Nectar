package com.kelvinbush.nectar.domain.use_cases.get_cart

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.CartItemList
import com.kelvinbush.nectar.domain.model.ShoppingSession
import com.kelvinbush.nectar.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCartUseCase(private val repository: FruityRepository) {
    suspend operator fun invoke(
        authToken: String,
        sessionId: ShoppingSession,
    ): Flow<Resource<CartItemList>> = flow {
        try {
            emit(Resource.Loading())
            val items = repository.getCart(authToken = authToken, sessionId = sessionId)
            emit(Resource.Success(items))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}