package com.kelvinbush.nectar.presentation.screens.bottomNavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kelvinbush.nectar.R

sealed class BottomNavScreen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val drawableId: Int
) {
    object Shop : BottomNavScreen("shop", R.string.shop, R.drawable.shop)
    object Explore : BottomNavScreen("explore", R.string.explore, R.drawable.explore)
    object Cart : BottomNavScreen("cart", R.string.cart, R.drawable.cart)
    object Favourite : BottomNavScreen("favourite", R.string.favourite, R.drawable.outline_favorite_border)
    object Account : BottomNavScreen("account", R.string.account, R.drawable.account)
}