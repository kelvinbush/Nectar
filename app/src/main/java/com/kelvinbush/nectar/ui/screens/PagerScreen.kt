package com.kelvinbush.nectar.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kelvinbush.nectar.R

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun PagerScreen() {
    val pagerState = rememberPagerState(pageCount = 10)
    Column(modifier = Modifier.fillMaxHeight(0.45f), verticalArrangement = Arrangement.Center) {
        /* HorizontalPager(state = pagerState) { page ->
             Column(
                 modifier = Modifier.fillMaxHeight(0.5f),
                 horizontalAlignment = Alignment.CenterHorizontally
             ) {*/
        AnimatedVisibility(
            visible = true,
            enter = slideInHorizontally(
                // Offsets the content by 1/3 of its width to the left, and slide towards right
                initialOffsetX = { fullWidth -> -fullWidth / 3 },
                // Overwrites the default animation with tween for this slide animation.
                animationSpec = tween(durationMillis = 200)
            ) + fadeIn(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 200)
            ),
            exit = slideOutHorizontally(
                // Overwrites the ending position of the slide-out to 200 (pixels) to the right
                targetOffsetX = { 200 },
                animationSpec = spring(stiffness = Spring.StiffnessHigh)
            ) + fadeOut()
        ) {
            // Content that needs to appear/disappear goes here:
            Box(
                Modifier
                    .fillMaxWidth()
                    .requiredHeight(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.carousel_1),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(text = "Page: $")
            }
        }
    }
}
/*   }
}*/
