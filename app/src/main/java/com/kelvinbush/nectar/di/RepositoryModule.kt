package com.kelvinbush.nectar.di

import com.kelvinbush.nectar.data.remote.FruityApi
import com.kelvinbush.nectar.data.repository.FruityRepositoryImpl
import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.use_cases.UseCases
import com.kelvinbush.nectar.domain.use_cases.add_to_cart.AddToCartUseCase
import com.kelvinbush.nectar.domain.use_cases.get_all_products.GetAllProductsUseCase
import com.kelvinbush.nectar.domain.use_cases.get_cart.GetCartUseCase
import com.kelvinbush.nectar.domain.use_cases.get_id_token.GetIdToken
import com.kelvinbush.nectar.domain.use_cases.login.LoginUseCase
import com.kelvinbush.nectar.domain.use_cases.remove_from_cart.RemoveCartUseCase
import com.kelvinbush.nectar.domain.use_cases.update_cart.UpdateCartUseCase
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
    fun provideFruityRepository(fruityApi: FruityApi): FruityRepository {
        return FruityRepositoryImpl(fruityApi = fruityApi)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: FruityRepository): UseCases =
        UseCases(
            getCartUseCase = GetCartUseCase(repository = repository),
            getAllProductsUseCase = GetAllProductsUseCase(repository = repository),
            loginUseCase = LoginUseCase(repository = repository),
            addToCartUseCase = AddToCartUseCase(repository = repository),
            getIdToken = GetIdToken(repository = repository),
            removeCartUseCase = RemoveCartUseCase(repository = repository),
            updateCartUseCase = UpdateCartUseCase(repository = repository)
        )
}
