package com.kelvinbush.nectar.presentation.screens.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.domain.model.FUser
import com.kelvinbush.nectar.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private var _fUser = MutableLiveData<FUser>()
    val fUser = _fUser

    init {
//        login()
    }

    fun login() {
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnSuccessListener {
            viewModelScope.launch {
//                _fUser.value = useCases.loginUseCase(authToken = it.token.toString())
            }
        }
    }
}