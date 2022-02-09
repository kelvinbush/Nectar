package com.kelvinbush.nectar.data.repository

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.data.remote.FruityApi
import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.*
import com.kelvinbush.nectar.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class FruityRepositoryImpl @Inject constructor(
    private val fruityApi: FruityApi,
) : FruityRepository {
    override suspend fun getCart(authToken: String, sessionId: ShoppingSession): CartItemList {
        return fruityApi.getCart(authToken = authToken, sessionId = sessionId)
    }

    override suspend fun getAllProducts() = flow {
        emit(Resource.Loading())
        try {
            val response = fruityApi.getAllProducts()
            emit(Resource.Success(data = response))

        } catch (e: HttpException) {
            emit(Resource.Error(
                message = e.message.toString(),
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = e.message.toString(),
            ))
        }
    }

    override suspend fun login(authToken: String): FUser {
        return fruityApi.login(authToken = authToken)
    }

    override suspend fun addToCart(authToken: String, item: CartAdd) =
        fruityApi.addToCart(authToken, item)

    override suspend fun getIdToken(): String = withContext(Dispatchers.Unconfined) {
        Log.d("getIdTokenReWith: ", "Token")
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener {
            Log.d("getIdToken: ", "User${user.displayName}")
        }.let { withContext(Dispatchers.Unconfined) { it?.result?.token.toString() } }
    }

    override suspend fun removeFromCart(authToken: String, item: RemoveProduct) {
        fruityApi.deleteFromCart(authToken = authToken, item = item)
    }

    override suspend fun signup(signup: SignupUser) = flow {
        emit(Resource.Loading())
        try {
            val response = fruityApi.signup(signup)
            emit(Resource.Success(data = response))

        } catch (e: HttpException) {
            emit(Resource.Error(
                message = e.message.toString(),
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = e.message.toString(),
            ))
        }
    }

    override suspend fun loginUser(loginCredentials: LoginCredentials): Flow<Resource<LoginResult>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = fruityApi.loginUser(loginCredentials)
                emit(Resource.Success(data = response))
            } catch (e: HttpException) {
                emit(Resource.Error(
                    message = e.message.toString(),
                ))
            } catch (e: IOException) {
                emit(Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                ))
            }
        }
}