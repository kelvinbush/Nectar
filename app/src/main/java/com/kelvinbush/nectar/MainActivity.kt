package com.kelvinbush.nectar

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.NectarScreen.*
import com.kelvinbush.nectar.ui.screens.*
import com.kelvinbush.nectar.ui.theme.NectarTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            NectarTheme {
                NectarApp()
            }
        }
    }
}

@Composable
fun NectarApp() {
    val systemUiController = rememberSystemUiController()
    val allScreens = values().toList()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = NectarScreen.fromRoute(
        backStackEntry.value?.destination?.route
    )
    Scaffold { innerPadding ->
        NavHost(navController = navController, startDestination = Splash.name) {
            composable(Splash.name) { SplashScreen(navController, systemUiController) }
            composable(OnBoarding.name) { OnBoardingScreen(navController) }
            composable(Start.name) { GetStartedScreen(navController, systemUiController) }
            composable(Login.name) { LoginScreen(systemUiController, navController) }
            composable(Signup.name) {
                SignupScreen(
                    systemUiController = systemUiController,
                    navController
                )
            }
        }

    }
}