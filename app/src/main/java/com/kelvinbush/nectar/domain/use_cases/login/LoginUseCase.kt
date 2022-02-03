package com.kelvinbush.nectar.domain.use_cases.login

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.FUser

class LoginUseCase(private val repository: FruityRepository) {

    suspend operator fun invoke(authToken: String): FUser {
        return repository.login(authToken = authToken)
    }
}