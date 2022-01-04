package com.kelvinbush.nectar.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.annotation.ExperimentalCoilApi
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.AccountScreen
import com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop.ShopScreen
import com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.cart.MyCart
import com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.explore.ExploreScreen
import com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.favourite.FavouriteScreen
import com.kelvinbush.nectar.presentation.screens.login.LoginScreen
import com.kelvinbush.nectar.presentation.screens.splash.SplashScreen
import com.kelvinbush.nectar.presentation.screens.welcome.OnBoardingScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    Scaffold(
        bottomBar = { MyBottomNav(navController = navController) }
    ) {
        NavHost(navController = navController, startDestination = Screen.Splash.route) {
            composable(route = Screen.Splash.route) { SplashScreen(navController = navController) }
            composable(route = Screen.Login.route) { LoginScreen(navController = navController) }
            composable(route = Screen.Welcome.route) { OnBoardingScreen(navController = navController) }
            composable(route = BottomNavScreen.Shop.route) { ShopScreen(navController) }
            composable(route = BottomNavScreen.Explore.route) { ExploreScreen() }
            composable(route = BottomNavScreen.Cart.route) { MyCart(navController) }
            composable(route = BottomNavScreen.Favourite.route) { FavouriteScreen() }
            composable(route = BottomNavScreen.Account.route) { AccountScreen(navController) }
        }
    }
}

@Composable
fun MyBottomNav(navController: NavHostController) {
    val bottomItems = listOf(
        BottomNavScreen.Shop,
        BottomNavScreen.Explore,
        BottomNavScreen.Cart,
        BottomNavScreen.Favourite,
        BottomNavScreen.Account
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    if (currentDestination?.route in bottomItems.map { item -> item.route }
    ) {
        BottomNavigation(
            backgroundColor = Color.White,
            elevation = 8.dp,
            modifier = Modifier.height(62.dp)
        ) {

            bottomItems.forEach { screen ->
                if (currentDestination != null) {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(screen.drawableId),
                                contentDescription = stringResource(R.string.screen_label)
                            )
                        },
                        label = {
                            Text(
                                stringResource(screen.resourceId),
                                color = if (currentDestination.hierarchy.any {
                                        it.route == screen.route
                                    })
                                    MaterialTheme.colors.primary else Color.Black,
                                textAlign = TextAlign.Start)
                        },
                        selected = currentDestination.hierarchy.any { it.route == screen.route },
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
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
    } else {
        Spacer(modifier = Modifier.width(0.dp))
    }
}