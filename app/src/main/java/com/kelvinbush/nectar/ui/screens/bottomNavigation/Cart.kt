package com.kelvinbush.nectar.ui.screens.bottomNavigation

import androidx.compose.foundation.Image
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
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.theme.headerTextStyle
import com.kelvinbush.nectar.ui.theme.priceTextStyle
import com.kelvinbush.nectar.ui.theme.productTextStyle

@Composable
fun CartScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.07f))
        Text(text = "My Cart", style = headerTextStyle)
        Divider(thickness = 1.dp, color = Color(0xffE2E2E2), modifier = Modifier.fillMaxWidth())
        LazyColumn() {
        }
        Divider(thickness = 1.dp, color = Color(0xffE2E2E2), modifier = Modifier.fillMaxWidth())
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Go to Checkout", style = MaterialTheme.typography.button)
        }
    }
}

@Composable
fun CartProduct(image: Int, name: String, size: String, quantity: Int, price: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(97.dp)
            .padding(horizontal = 16.dp)
    ) {
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(0.25f)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = image), null,
            contentScale = ContentScale.Inside
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = name, style = productTextStyle)
        Text(text = "$size, Price", style = priceTextStyle)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .height(36.dp)
                    .width(36.dp),
                onClick = {},
                shape = RoundedCornerShape(33),
                contentPadding = PaddingValues(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    disabledContentColor = Color(0xffb3b3b3),
                    contentColor = MaterialTheme.colors.primary
                ),
                enabled = true
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                )
            }
            Text(text = quantity.toString(), style = productTextStyle)
            Button(
                modifier = Modifier
                    .height(36.dp)
                    .width(36.dp),
                onClick = {},
                shape = RoundedCornerShape(33),
                contentPadding = PaddingValues(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    disabledContentColor = Color(0xffb3b3b3),
                    contentColor = MaterialTheme.colors.primary
                ),
                enabled = true
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(0.25f)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {
        Image(painter = painterResource(id = R.drawable.close), null)
        Text(text = "$$price")
    }
}

data class Product(
    val image: Int,
    val name: String,
    val size: String,
    val price: Double,
    var quantity: Int = 1
)

val pList = listOf(
    Product(R.drawable.pepper, "Bell Pepper Red", "1kg", 4.99),
    Product(R.drawable.chicken, "Egg Chicken Red", "4pcs", 1.99),
    Product(R.drawable.bananas, "Organic Bananas", "12kg", 3.00),
    Product(R.drawable.ginger, "Ginger", "250gms", 2.99),
)