package com.kelvinbush.nectar.presentation.screens.welcome

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.navigation.BottomNavScreen
import com.kelvinbush.nectar.presentation.screens.splash.SplashViewModel
import com.kelvinbush.nectar.ui.theme.BGreen


@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val user by splashViewModel.fUser.observeAsState()

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = BGreen,
        darkIcons = false
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.veg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_carrot),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier
                    .width(49.dp)
                    .height(57.dp)
                    .padding(bottom = 6.dp)
            )
            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.h1
            )
            Text(text = stringResource(R.string.to_our_store),
                style = MaterialTheme.typography.h1)
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = stringResource(R.string.get_groceries),
                style = MaterialTheme.typography.h3
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.06f)
            )
            Button(
                onClick = {
                    navController.navigate(BottomNavScreen.Shop.route)
                    { launchSingleTop = true }
                    splashViewModel.login()
                    Log.d("SplashScreen: ", user.toString())
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(60.dp),
                shape = RoundedCornerShape(30)
            ) {
                Text(
                    text = stringResource(R.string.get_started),
                    style = MaterialTheme.typography.button
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.15f)
            )
        }
    }
}

