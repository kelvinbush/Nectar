package com.kelvinbush.nectar.di

import com.kelvinbush.nectar.data.repository.Repository
import com.kelvinbush.nectar.domain.use_cases.UseCases
import com.kelvinbush.nectar.domain.use_cases.get_all_products.GetAllProductsUseCase
import com.kelvinbush.nectar.domain.use_cases.get_cart.GetCartUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getCartUseCase = GetCartUseCase(repository = repository),
            getAllProductsUseCase = GetAllProductsUseCase(repository = repository)
        )
    }
}