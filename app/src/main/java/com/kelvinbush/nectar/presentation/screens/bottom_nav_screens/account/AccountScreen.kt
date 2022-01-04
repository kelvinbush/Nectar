package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kelvinbush.nectar.navigation.Screen

@Composable
fun AccountScreen(
    navController: NavHostController,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "fUser.toString()")
        Button(onClick = {
            navController.popBackStack()
            navController.navigate(Screen.Login.route) {
                launchSingleTop = true
            }
        }) {
            Text(text = "Logout", style = MaterialTheme.typography.button)
        }
    }
}