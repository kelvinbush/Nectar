package com.kelvinbush.nectar.domain.use_cases.user_signup

import com.kelvinbush.nectar.domain.FruityRepository
import com.kelvinbush.nectar.domain.model.Result
import com.kelvinbush.nectar.domain.model.SignupUser
import com.kelvinbush.nectar.util.Resource
import kotlinx.coroutines.flow.Flow

class SignupUseCase(private val repository: FruityRepository) {
    suspend operator fun invoke(signup: SignupUser):Flow<Resource<Result>> = repository.signup(signup)
}