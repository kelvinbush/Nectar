package com.kelvinbush.nectar.data

import android.content.Context
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

        return chain.proceed(requestBuilder.build())
    }
}