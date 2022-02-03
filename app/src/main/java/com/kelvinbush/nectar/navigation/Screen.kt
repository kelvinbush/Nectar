package com.kelvinbush.nectar.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("sign_up_screen")
    object Detail : Screen("detail_screen")
    object Order : Screen("order_screen")
    object Start:Screen("get_started_screen")
}
