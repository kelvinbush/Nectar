package com.kelvinbush.nectar.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kelvinbush.nectar.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int? = R.string.account,
    @DrawableRes val drawableId: Int? = R.drawable.account,
) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("sign_up_screen")
    object Detail : Screen("detail_screen")
    object Order : Screen("order_screen")
    object Start : Screen("get_started_screen")

    object Shop : Screen("shop", R.string.shop, R.drawable.shop)
    object Explore : Screen("explore", R.string.explore, R.drawable.explore)
    object Cart : Screen("cart", R.string.cart, R.drawable.cart)
    object Favourite : Screen("favourite", R.string.favourite, R.drawable.outline_favorite_border)
    object Account : Screen("account", R.string.account, R.drawable.account)

}
