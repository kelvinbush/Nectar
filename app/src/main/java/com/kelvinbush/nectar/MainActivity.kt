package com.kelvinbush.nectar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.kelvinbush.nectar.NectarScreen.*
import com.kelvinbush.nectar.presentation.screens.GetStartedScreen
import com.kelvinbush.nectar.presentation.screens.LoginScreen
import com.kelvinbush.nectar.presentation.screens.SplashScreen
import com.kelvinbush.nectar.presentation.screens.bottomNavigation.BottomNavHost
import com.kelvinbush.nectar.ui.theme.NectarTheme
import com.kelvinbush.nectar.viewmodel.LoginScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            NectarTheme {
                NectarApp()
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NectarApp() {
    val navController = rememberNavController()
    val loginScreenViewModel = hiltViewModel<LoginScreenViewModel>()
    Scaffold {
        NavHost(navController = navController, startDestination = Splash.name) {
            composable(Splash.name) { SplashScreen(navController) }
            composable(Start.name) { GetStartedScreen(navController) }
            composable(Login.name) { LoginScreen(navController, loginScreenViewModel) }
            composable(Pager.name) { BottomNavHost(loginScreenViewModel) }
        }
    }
}