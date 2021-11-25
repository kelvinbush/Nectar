package com.kelvinbush.nectar

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.NectarScreen.*
import com.kelvinbush.nectar.ui.screens.GetStartedScreen
import com.kelvinbush.nectar.ui.screens.ProductDetailScreen
import com.kelvinbush.nectar.ui.screens.SplashScreen
import com.kelvinbush.nectar.ui.screens.bottomNavigation.BottomNavHost
import com.kelvinbush.nectar.ui.theme.NectarTheme


class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NectarTheme {
                NectarApp()
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NectarApp() {
    val systemUiController = rememberSystemUiController()
    val allScreens = values().toList()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = NectarScreen.fromRoute(
        backStackEntry.value?.destination?.route
    )
    Scaffold {
        NavHost(navController = navController, startDestination = ProductDetail.name) {
            composable(Splash.name) { SplashScreen(navController, systemUiController) }
            /*composable(OnBoarding.name) { OnBoardingScreen(navController) }*/
            composable(Start.name) { GetStartedScreen(navController/*, systemUiController*/) }
            /* composable(Login.name) { LoginScreen(systemUiController, navController) }*/
            /*composable(Signup.name) {
                SignupScreen(
                    systemUiController = systemUiController,
                    navController
                )
            }*/
//            composable(Shop.name) { ShopScreen(navController) }
            composable(Pager.name) { BottomNavHost() }
            composable(ProductDetail.name) { ProductDetailScreen(navController) }
        }

    }
}