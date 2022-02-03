package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.account

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.navigation.Screen
import com.kelvinbush.nectar.ui.theme.itemNameTextStyle
import com.kelvinbush.nectar.ui.theme.itemPriceTextStyle

@ExperimentalMaterialApi
@Composable
fun AccountScreen(navController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    val currentUser = Firebase.auth.currentUser
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(69.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            if (currentUser != null) {
                Image(
                    modifier = Modifier.size(65.dp),
                    painter = rememberImagePainter(data = currentUser.photoUrl),
                    contentDescription = "user image",
                    contentScale = ContentScale.Fit
                )
            }
            Column {
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(text = currentUser?.displayName ?: "Name", style = itemNameTextStyle)
                    IconButton(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(20.dp),
                        onClick = {
                            Toast
                                .makeText(context, "Editing me", Toast.LENGTH_SHORT)
                                .show()
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(0.67f),
                            painter = painterResource(id = R.drawable.edit_icon),
                            contentDescription = "edit username",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
                Text(text = currentUser?.email ?: "name@gmail.com",
                    style = MaterialTheme.typography.h5)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color(0xFFB3B3B3), modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
        AccountNavItem(icon = R.drawable.orders, name = "Orders")
        AccountNavItem(icon = R.drawable.my_details, name = "My Details")
        AccountNavItem(icon = R.drawable.address, name = "Delivery Address")
        AccountNavItem(icon = R.drawable.payment, name = "Payment methods")
        AccountNavItem(icon = R.drawable.promo, name = "Promo Card")
        AccountNavItem(icon = R.drawable.notifications, name = "Notifications")
        AccountNavItem(icon = R.drawable.help, name = "Help")
        AccountNavItem(icon = R.drawable.about, name = "About")

        Spacer(modifier = Modifier.height(26.dp))

        Button(
            modifier = Modifier
                .height(57.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                Firebase.auth.signOut()
                Toast
                    .makeText(context, "Logging you out", Toast.LENGTH_SHORT)
                    .show()
                navController.navigate(Screen.Start.route) {
                    popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF2F3F2),
                contentColor = Color(0xFF53B175)
            ),
            shape = RoundedCornerShape(19),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp, pressedElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.log_out),
                    contentDescription = "log out",
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier.padding(start = 100.dp),
                    text = "Log Out",
                    style = itemPriceTextStyle,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun AccountNavItem(@DrawableRes icon: Int, name: String) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .fillMaxWidth(), onClick = {
        Toast
            .makeText(context, "$name clicked", Toast.LENGTH_SHORT)
            .show()
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Image(
                    modifier = Modifier.height(20.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = name, style = itemPriceTextStyle)
            }
            Image(
                painter = painterResource(id = R.drawable.arrow_forward),
                contentDescription = "forward"
            )
        }
    }
    Divider(color = Color(0xFFB3B3B3), modifier = Modifier.fillMaxWidth(), thickness = 1.dp)

}