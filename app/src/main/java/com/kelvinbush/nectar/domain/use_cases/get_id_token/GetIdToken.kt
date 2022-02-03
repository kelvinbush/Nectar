package com.kelvinbush.nectar.domain.use_cases.get_id_token

import com.kelvinbush.nectar.domain.FruityRepository
import javax.inject.Inject

class GetIdToken @Inject constructor(private val repository: FruityRepository) {
    suspend operator fun invoke(string: String) = repository.getIdToken()
}