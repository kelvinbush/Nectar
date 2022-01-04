package com.kelvinbush.nectar.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.domain.model.CartItemList
import com.kelvinbush.nectar.domain.model.CartProduct
import com.kelvinbush.nectar.ui.theme.headerTextStyle
import com.kelvinbush.nectar.ui.theme.price2TextStyle
import com.kelvinbush.nectar.ui.theme.priceTextStyle
import com.kelvinbush.nectar.ui.theme.productTextStyle

@Composable
fun ItemListScreenHolder(
    headText: String,
    proList: CartItemList,
    btnText: String,
    prodCard: @Composable (prod: CartProduct) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.07f))
        Text(text = headText, style = headerTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 1.dp, color = Color(0xffE2E2E2), modifier = Modifier.fillMaxWidth())
        ItemList(myList = proList) {
            prodCard(it)
        }
        Divider(thickness = 1.dp, color = Color(0xffE2E2E2), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(30)
        ) {
            Text(text = btnText, style = MaterialTheme.typography.button)
        }
    }
}


@Composable
fun ItemList(myList: CartItemList, content: @Composable (prod: CartProduct) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        myList.cartItems?.forEach { elem ->
            item {
                content(elem)
                if (myList.cartItems.lastIndex != myList.cartItems.indexOf(elem)) {
                    Divider(
                        thickness = 1.dp,
                        color = Color(0xffE2E2E2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: CartProduct, arrangement: Arrangement.Vertical = Arrangement.Top,
    iconId: Int = R.drawable.next,
    tint: Color = Color.Black,
    midCol: @Composable () -> Unit = {
        MidCol()
    },
    endCol: @Composable () -> Unit = {
        LastColumnFavourite(
            price = product.product.price,
            iconId = iconId,
            tint = tint
        )
    },
    lastColArrangement: Arrangement.Vertical = Arrangement.Center,
    lastColAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(97.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {

        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = rememberImagePainter(product.product.imageUrl), null,
                contentScale = ContentScale.Fit
            )
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            verticalArrangement = arrangement,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = product.product.name, style = productTextStyle)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Kshs, Price", style = priceTextStyle)
            midCol()
        }
        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxHeight(),
            verticalArrangement = lastColArrangement,
            horizontalAlignment = lastColAlignment
        ) {
            endCol()
        }
    }
}

@Composable
fun LastColumnFavourite(price: Double, iconId: Int, tint: Color) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$${price}", style = price2TextStyle)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = iconId),
                null,
                tint = tint
            )
        }
    }
}

@Composable
fun LastColumnCart(price: Double, iconId: Int) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            painter = painterResource(id = iconId),
            null,
            tint = Color(0xffb3b3b3)
        )
    }
    Text(text = "$$price", style = price2TextStyle)
}


@Composable
fun MidCol(quantity: Int = 1) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.65f)
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, Color(0xffe2e2e2)),
                    shape = RoundedCornerShape(33)
                )
                .height(30.dp)
                .width(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                contentDescription = null,
                tint = Color(0xffb3b3b3)
            )
        }
        Text(text = quantity.toString(), style = productTextStyle)
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, Color(0xffe2e2e2)),
                    shape = RoundedCornerShape(33)
                )
                .height(30.dp)
                .width(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
        }
    }
}