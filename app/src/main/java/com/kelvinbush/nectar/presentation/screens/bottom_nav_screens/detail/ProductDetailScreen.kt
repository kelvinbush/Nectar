package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.presentation.components.MidCol
import com.kelvinbush.nectar.ui.theme.categoryTextStyle
import com.kelvinbush.nectar.ui.theme.price2TextStyle
import com.kelvinbush.nectar.ui.theme.productTextStyle

@Composable
fun ProductDetailScreen(navController: NavController) {
    var liked by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf(1) }
    val likedIconId = if (liked) R.drawable.favorite else R.drawable.outline_favorite_border
    val loremIpsum = "I would like say that after conquered React " +
            "also" +
            " maintain consistency for your entire developer journey. Learn new " +
            "things" +
            " and keep up to date yourself with current industry " +
            "tech" +
            " stacks."
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffffffff))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.37f)
                .padding(vertical = 20.dp)
                .background(
                    Color(0xfff2f3f2),
                    shape = RoundedCornerShape(
                        topEndPercent = 0,
                        topStartPercent = 0,
                        bottomStartPercent = 17,
                        bottomEndPercent = 17
                    )
                ),
        ) {
            Spacer(modifier = Modifier.height(26.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                /*.padding(horizontal = 16.dp)*/,
                horizontalArrangement = Arrangement.Start
            ) {
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
                    .height(200.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.detail),
                    contentDescription = null,
                    modifier = Modifier
                        .size(330.dp, 165.dp),
//                    contentScale = ContentScale.FillBounds,
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .weight(0.53f),
            horizontalAlignment = Alignment.Start,
//            verticalArrangement = Arrangement.SpaceAround

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Natural Red Apple", style = categoryTextStyle)
                    Image(
                        painter = painterResource(id = likedIconId),
                        contentDescription = null,
                        modifier = Modifier.clickable { liked = !liked }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                        MidCol(quantity = amount)
                    }
                    Text(text = "$4.99", style = price2TextStyle)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Product Detail", style = productTextStyle)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = loremIpsum,
                    style = productTextStyle,
                    letterSpacing = 0.1.sp,
                    overflow = TextOverflow.Visible,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(30)
                ) {
                    Text(text = "Add To Basket", style = MaterialTheme.typography.button)
                }
            }

        }
    }
}