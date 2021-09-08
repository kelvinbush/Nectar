package com.kelvinbush.nectar.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.Screen
import com.kelvinbush.nectar.ui.screens.*

@Composable
fun BottomNav() {
    BottomNavigation(
        elevation = 12.dp,
        backgroundColor = Color.White,
        modifier = Modifier.height(73.dp)
    ) {
        BottomNavItem("Shop", Modifier.padding(start = 16.dp), R.drawable.shop)
        BottomNavItem("Explore", Modifier, R.drawable.explore)
        BottomNavItem("Cart", Modifier, R.drawable.cart)
        BottomNavItem("Favourite", Modifier, R.drawable.favorite)
        BottomNavItem("Account", Modifier.padding(end = 16.dp), R.drawable.account)
    }
}

@Composable
fun BottomNavItem(text: String, modifier: Modifier, id: Int) {
    /* Column(
         modifier = modifier.fillMaxHeight(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Icon(
             painter = painterResource(id = id),
             contentDescription = null
         )
         Text(text = text, textAlign = TextAlign.Center)
     }*/

}
