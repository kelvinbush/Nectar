package com.kelvinbush.nectar.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kelvinbush.nectar.data.AuthInterceptor
import com.kelvinbush.nectar.data.remote.FruityApi
import com.kelvinbush.nectar.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context = context))
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }


    @Provides
    @Singleton
    fun provideFruityApi(retrofit: Retrofit): FruityApi = retrofit.create(FruityApi::class.java)
}
