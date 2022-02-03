package com.kelvinbush.nectar


enum class NectarScreen {
    OnBoarding,
    Start,
    Login,
    Splash,
    Signup,
    Pager,
    Shop,
    ProductDetail;

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
                ProductDetail.name -> ProductDetail
                null -> OnBoarding
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}

