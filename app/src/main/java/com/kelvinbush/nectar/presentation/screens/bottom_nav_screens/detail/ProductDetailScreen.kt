package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.detail

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.domain.model.ProductDetail
import com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop.ShopViewModel
import com.kelvinbush.nectar.ui.theme.categoryTextStyle
import com.kelvinbush.nectar.ui.theme.price2TextStyle
import com.kelvinbush.nectar.ui.theme.priceTextStyle
import com.kelvinbush.nectar.ui.theme.productTextStyle

@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    product: ProductDetail,
    detailViewModel: DetailViewModel = hiltViewModel(),
    shopViewModel: ShopViewModel = hiltViewModel(),
) {
    var liked by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf(1) }
    val likedIconId = if (liked) R.drawable.favorite else R.drawable.outline_favorite_border

    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color(0xfff2f3f2),
        darkIcons = true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffffffff))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.37f)
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
                    .fillMaxWidth(),
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = rememberImagePainter(data = product.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(330.dp, 165.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .weight(0.53f),
            horizontalAlignment = Alignment.Start,

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
                    Text(text = product.name, style = categoryTextStyle)
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
                        Row(
                            modifier = Modifier.fillMaxWidth(0.68f).height(50.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .size(30.dp)
                                    .border(
                                        shape = RoundedCornerShape(30),
                                        width = 1.dp,
                                        color = Color(0xFFB3B3B3)
                                    ),
                                enabled = amount > 1,
                                onClick = { amount-- }) {
                                Icon(
                                    modifier = Modifier
                                        .width(17.dp)
                                        .align(Alignment.CenterVertically),
                                    painter = painterResource(id = R.drawable.ic_remove),
                                    contentDescription = "Add Icon",
                                    tint = if (amount > 1) Color(0xFF53B175) else Color(0xFFB3B3B3)
                                )
                            }
                            Text(
                                text = "$amount",
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Center
                            )
                            IconButton(
                                modifier = Modifier
                                    .size(30.dp)
                                    .border(
                                        shape = RoundedCornerShape(30),
                                        width = 1.dp,
                                        color = Color(0xFFB3B3B3)
                                    ),
                                onClick = { amount++ }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(17.dp)
                                        .align(Alignment.CenterVertically),
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Icon",
                                    tint = Color(0xFF53B175)
                                )
                            }
                        }
                    }
                    Text(text = "Kshs${product.price * amount}", style = price2TextStyle)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Product Detail", style = productTextStyle)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = product.description,
                    style = priceTextStyle,
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
                    onClick = {
//                        shopViewModel.login(id = product.id, quantity = amount)
                        Toast.makeText(context,
                            "${product.name} was added to cart",
                            Toast.LENGTH_SHORT).show()
                    },
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