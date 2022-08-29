package com.kelvinbush.nectar.domain.model.service.module

import com.kelvinbush.nectar.domain.model.service.AccountService
import com.kelvinbush.nectar.domain.model.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
}