package com.kelvinbush.nectar.ui.screens

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.NectarScreen.OnBoarding
import com.kelvinbush.nectar.NectarScreen.Splash
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.theme.BGreen


@Composable
fun SplashScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = BGreen,
            darkIcons = false
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BGreen),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Column(modifier = Modifier.padding(end = 8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_carrot),
                    contentDescription = "logo",

                    )
            }
            Column {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "logo",
                )
                Text(text = "online groceries", modifier = Modifier.background(Color.White))
            }
        }
    }

    Handler(Looper.getMainLooper()).postDelayed({
        navController.navigate(OnBoarding.name) {
            popUpTo(Splash.name) { inclusive = true }
            launchSingleTop = true
        }
    }, 4000)
}