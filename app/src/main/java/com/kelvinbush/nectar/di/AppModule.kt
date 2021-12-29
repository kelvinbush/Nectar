package com.kelvinbush.nectar.di

import com.kelvinbush.nectar.data.remote.FruityService
import com.kelvinbush.nectar.utils.DefaultIfNullFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(DefaultIfNullFactory())
            .addLast(KotlinJsonAdapterFactory())
            .build()


    @Singleton
    @Provides
    fun provideFruityService(moshi: Moshi): FruityService {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:1446/")
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(FruityService::class.java)
    }
}