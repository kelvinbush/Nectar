package com.kelvinbush.nectar.presentation.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.NectarScreen
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.presentation.components.Btn
import com.kelvinbush.nectar.presentation.components.Leading
import com.kelvinbush.nectar.presentation.components.fieldColors

private const val TAG = "GetStartedScreen"

@Composable
fun GetStartedScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }

    val systemUiController = rememberSystemUiController()
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
    Column(
        modifier = Modifier
            .background(Color(0xfffcfcfc))
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.43f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_3),
                contentDescription = null,
                modifier = Modifier
                    .rotate(-141.29f)
                    .fillMaxSize()
                    .scale(1.8f)
                    .padding(end = 11.dp, top = 40.dp, start = 30.dp),
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.29f)
                    .fillMaxWidth(0.79f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_min_logo),
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.77f)
        ) {
            Text(
                text = "Get your groceries",
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
            )
            Text(
                text = "with nectar",
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                leadingIcon = { Leading() },
                textStyle = MaterialTheme.typography.h4,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth(0.85f),
                colors = fieldColors(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Or connect with social media", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.height(10.dp))
            Btn(
                id = R.drawable.ic_google,
                text = "Continue with Google",
                clicked = {},
                color = Color(0xff5383ec)
            )
            Btn(
                id = R.drawable.ic_fb,
                text = "Continue with Facebook",
                clicked = {
                    navController.navigate(NectarScreen.Pager.name) {
                        launchSingleTop = true
                    }
                },
                color = Color(0xff4a66ac)
            )
        }
    }
}
