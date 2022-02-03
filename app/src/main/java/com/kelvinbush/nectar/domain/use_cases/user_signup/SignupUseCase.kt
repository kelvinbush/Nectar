package com.kelvinbush.nectar.domain.use_cases.user_signup

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.UserSignup
import com.kelvinbush.nectar.util.Resource
import kotlinx.coroutines.flow.Flow

class SignupUseCase(private val repository: FruityRepository) {
    suspend operator fun invoke(signup: UserSignup):Flow<Resource<String>> = repository.signup(signup)
}