package com.kelvinbush.nectar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kelvinbush.nectar.R

@Composable
fun ProductDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(Color(0xffffffff))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            elevation = 0.dp,
            shape = RoundedCornerShape(
                topStartPercent = 0,
                topEndPercent = 0,
                bottomEndPercent = 17,
                bottomStartPercent = 17
            ), backgroundColor = Color(0xfff2f3f2)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.detail),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}