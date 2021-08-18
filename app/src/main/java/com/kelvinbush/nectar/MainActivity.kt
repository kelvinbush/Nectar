package com.kelvinbush.nectar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kelvinbush.nectar.NectarScreen.OnBoarding
import com.kelvinbush.nectar.NectarScreen.Splash
import com.kelvinbush.nectar.ui.screens.OnBoardingScreen
import com.kelvinbush.nectar.ui.screens.SplashScreen
import com.kelvinbush.nectar.ui.theme.NectarTheme
import android.view.WindowManager
import android.view.Window


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
    val allScreens = NectarScreen.values().toList()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = NectarScreen.fromRoute(
        backStackEntry.value?.destination?.route
    )
    Scaffold { innerPadding ->
        NavHost(navController = navController, startDestination = Splash.name) {
            composable(Splash.name) { SplashScreen(navController) }
            composable(OnBoarding.name) { OnBoardingScreen() }
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NectarTheme {
        Greeting("Android")
    }
}