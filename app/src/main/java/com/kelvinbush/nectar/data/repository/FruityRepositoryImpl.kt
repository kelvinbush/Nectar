package com.kelvinbush.nectar.data.repository

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.data.remote.FruityApi
import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class FruityRepositoryImpl @Inject constructor(
    private val fruityApi: FruityApi,
) : FruityRepository {
    override suspend fun getCart(authToken: String): CartItemList {
        return fruityApi.getCart(authToken = authToken)
    }

    override suspend fun getAllProducts(authToken: String): AllNetworkProducts {
        return fruityApi.getAllProducts(authToken = authToken)
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
}