package com.kelvinbush.nectar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R

@Composable
fun OnBoardingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(230.dp)
                    .width(32.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_carrot),
                contentDescription = "logo",
                modifier = Modifier
                    .width(49.dp)
                    .height(57.dp)
            )
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.h1
            )
            Text(text = "to our store", style = MaterialTheme.typography.h1)
            Text(
                text = "Get your groceries as fast as one hour"
            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Get Started")
            }
        }

    }
}

