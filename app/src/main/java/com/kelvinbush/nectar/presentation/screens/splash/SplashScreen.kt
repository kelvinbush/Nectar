package com.kelvinbush.nectar.presentation.screens.splash


import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.navigation.Screen
import com.kelvinbush.nectar.ui.theme.BGreen


@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val currentUser = Firebase.auth.currentUser
    val user by splashViewModel.fUser.observeAsState()

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = BGreen,
        darkIcons = false
    )
    val context = LocalContext.current

    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1800,
                delayMillis = 200
            )
        )
        Log.d("SplashScreen: ", user.toString())

        navController.popBackStack()
        if (currentUser != null) {
            navController.navigate(Screen.Welcome.route)
        } else {
            navController.navigate(Screen.Login.route)
        }
    }



    Scaffold {
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
                    Text(
                        text = "online  groceries", style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            lineHeight = 18.sp,
                            letterSpacing = 6.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.gilroymedium,
                                    weight = FontWeight.Medium
                                )
                            )
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}