package com.kelvinbush.nectar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class NectarScreen {
    OnBoarding,
    Start,
    Login,
    Splash,
    Signup,
    Pager,
    Shop;

    companion object {
        fun fromRoute(route: String?): NectarScreen =
            when (route?.substringBefore("/")) {
                OnBoarding.name -> OnBoarding
                Splash.name -> Splash
                Start.name -> Start
                Login.name -> Login
                Signup.name -> Signup
                Shop.name -> Shop
                Pager.name -> Pager
                null -> OnBoarding
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, @DrawableRes val drawableId: Int) {
    object Shop : Screen("shop", R.string.shop, R.drawable.shop)
    object Login : Screen("login", R.string.login, R.drawable.account)
    object Signup : Screen("signup", R.string.signup, R.drawable.cart)
}