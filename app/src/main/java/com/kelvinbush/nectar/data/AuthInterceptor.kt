package com.kelvinbush.nectar.data

import android.content.Context
import android.util.Log
import com.kelvinbush.nectar.util.Constants.ACCESS_TOKEN
import com.kelvinbush.nectar.util.Constants.REFRESH_TOKEN
import com.kelvinbush.nectar.util.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAccessToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        sessionManager.fetchRefreshToken()?.let {
            requestBuilder.addHeader("x-refresh", it)
        }

        val response = chain.proceed(requestBuilder.build())
        getTokensFromResHeaders(response)

        return response
    }

    private fun getTokensFromResHeaders(response: Response) {
        var accessToken = response.headers().get(ACCESS_TOKEN)
        val refreshToken = response.headers().get(REFRESH_TOKEN)
        val newAccessToken = response.headers().get("x-access-token")
        if (newAccessToken != null) accessToken = newAccessToken

        if (accessToken != null && refreshToken != null) {
            sessionManager.saveAccessToken(accessToken)
            sessionManager.saveRefreshToken(refreshToken)
            Log.d( "getTokensFromResHeaders: ", "access: $accessToken")
        } else {
            Log.d("getTokensFromResponseHeaders: ", "Not token ${response.headers()}")
        }
    }

}