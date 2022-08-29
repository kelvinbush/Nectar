package com.kelvinbush.nectar.presentation.screens.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinbush.nectar.domain.model.FUser
import com.kelvinbush.nectar.domain.model.service.AccountService
import com.kelvinbush.nectar.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases,
    private val accountService: AccountService,
) : ViewModel() {

    private var _fUser = MutableLiveData<FUser>()
    val fUser = _fUser


    fun login() {
        viewModelScope.launch {
            _fUser.value = useCases.loginUseCase(authToken = accountService.getIdToken())
        }
    }
}
