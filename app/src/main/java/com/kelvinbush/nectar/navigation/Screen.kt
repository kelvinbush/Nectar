package com.kelvinbush.nectar.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("sign_up_screen")
    object Account : Screen("account_screen")
    object Detail : Screen("detail_screen")
    object Explore : Screen("explore_screen")
    object Favourites : Screen("favourites_screen")
    object Home : Screen("home_screen")
    object Cart : Screen("cart_screen")
}