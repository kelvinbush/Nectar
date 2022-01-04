package com.kelvinbush.nectar.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.kelvinbush.nectar.presentation.screens.LoginScreen
import com.kelvinbush.nectar.presentation.screens.SplashScreen
import com.kelvinbush.nectar.presentation.screens.bottomNavigation.*
import com.kelvinbush.nectar.presentation.screens.cart.MyCart

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) { SplashScreen(navController = navController)}
        composable(route = Screen.Login.route) { LoginScreen(navController = navController)}
        composable(route = Screen.Favourites.route) { FavouriteScreen()}
        composable(route = BottomNavScreen.Shop.route) { ShopScreen(navController) }
        composable(route = BottomNavScreen.Explore.route) { ExploreScreen() }
        composable(route = BottomNavScreen.Cart.route) { MyCart(navController) }
        composable(route = BottomNavScreen.Favourite.route) { FavouriteScreen() }
        composable(route = BottomNavScreen.Account.route) { AccountScreen(navController) }
    }
}