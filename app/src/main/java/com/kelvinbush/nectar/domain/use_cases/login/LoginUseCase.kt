package com.kelvinbush.nectar.domain.use_cases.login

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.LoginCredentials

class LoginUseCase(private val repository: FruityRepository) {

    suspend operator fun invoke(loginCredentials: LoginCredentials) =
        repository.loginUser(loginCredentials)
}