package com.kelvinbush.nectar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.kelvinbush.nectar.R

private const val TAG = "GetStartedScreen"

@Composable
fun GetStartedScreen(navController: NavController, systemUiController: SystemUiController) {
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }
    Column(
        modifier = Modifier
            .background(Color.White)
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
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.5f)
        ) {
            Text(
                text = "Get your groceries",
                style = MaterialTheme.typography.h1,
                color = Color(0xff030303),
                textAlign = TextAlign.Start
            )
            Text(
                text = "with nectar",
                style = MaterialTheme.typography.h1,
                color = Color(0xff030303),
                textAlign = TextAlign.Start
            )
        }

    }
}