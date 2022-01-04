package com.kelvinbush.nectar.domain.use_cases.login

import com.kelvinbush.nectar.data.repository.Repository
import com.kelvinbush.nectar.domain.model.FUser

class LoginUseCase(private val repository: Repository) {

    suspend operator fun invoke(authToken: String): FUser {
        return repository.login(authToken = authToken)
    }
}