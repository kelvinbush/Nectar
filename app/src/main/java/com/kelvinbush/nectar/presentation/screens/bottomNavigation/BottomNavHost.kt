package com.kelvinbush.nectar.presentation.screens.bottomNavigation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.presentation.screens.cart.MyCart
import com.kelvinbush.nectar.viewmodel.LoginScreenViewModel

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavHost(viewModel: LoginScreenViewModel) {
    val currentUser = Firebase.auth.currentUser
    val navController = rememberNavController()
    val bottomItems = listOf(
        Screen.Shop,
        Screen.Explore,
        Screen.Cart,
        Screen.Favourite,
        Screen.Account
    )
    val token by viewModel.idToken.observeAsState()
    val products by viewModel.products.observeAsState()
    Log.d("BottomNavHost: ", products.toString())
    LaunchedEffect(key1 = 1) {
        viewModel.getAllProducts()
    }
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                elevation = 8.dp,
                modifier = Modifier.height(62.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(screen.drawableId), null) },
                        label = {
                            Text(stringResource(screen.resourceId),
                                color = if (currentDestination?.hierarchy?.any {
                                        it.route == screen.route
                                    } == true)
                                    MaterialTheme.colors.primary else Color.Black,
                                textAlign = TextAlign.Start)
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // re-selecting the same item
                                launchSingleTop = true
                                // Restore state when re-selecting a previously selected item
                                restoreState = true
                            }
                        },
                        alwaysShowLabel = true,
                        selectedContentColor = MaterialTheme.colors.primary,
                        unselectedContentColor = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Shop.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Shop.route) { ShopScreen(navController, viewModel) }
            composable(Screen.Explore.route) { ExploreScreen() }
            composable(Screen.Cart.route) { MyCart(navController) }
            composable(Screen.Favourite.route) { FavouriteScreen() }
            composable(Screen.Account.route) { AccountScreen(navController, viewModel) }
        }
    }
}