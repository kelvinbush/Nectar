package com.kelvinbush.nectar.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.kelvinbush.nectar.ui.theme.categoryTextStyle

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CategoryCard(bgColor: Color, imageUrl: Int, text: String, border: Color) {
    Card(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(9),
        backgroundColor = bgColor, // Transparency B3(70%)
        modifier = Modifier
            .height(190.dp)
            .padding(start = 8.dp, bottom = 8.dp),
        border = BorderStroke(1.dp, border)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                /*painter = rememberImagePainter(data = imageUrl,
                    builder = {
                        crossfade(true)
                    })*/
                painter = painterResource(id = imageUrl), contentDescription = null,
                modifier = Modifier
                    .height(75.dp)
                    .width(112.dp)


            )
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Text(
                text = text, softWrap = true, style = categoryTextStyle,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}