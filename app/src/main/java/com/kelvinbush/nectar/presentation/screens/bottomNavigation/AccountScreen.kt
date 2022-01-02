package com.kelvinbush.nectar.presentation.screens.bottomNavigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.viewmodel.LoginScreenViewModel

@Composable
fun AccountScreen(navController: NavController, loginViewModel: LoginScreenViewModel) {
    val fUser by loginViewModel.fUser.observeAsState()
    val currentUser = Firebase.auth.currentUser
    var token by remember { mutableStateOf("") }

    LaunchedEffect(key1 = 1) {
        currentUser?.getIdToken(true)?.addOnSuccessListener {
            token = it.token.toString()
            Log.d(":gotToken", token)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = fUser.toString())
        Button(onClick = {
            loginViewModel.login(token)
        }) {
            Text(text = "Logout", style = MaterialTheme.typography.button)
        }
    }
}