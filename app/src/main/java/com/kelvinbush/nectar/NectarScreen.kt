package com.kelvinbush.nectar

enum class NectarScreen {
    OnBoarding,
    Splash;

    companion object {
        fun fromRoute(route: String?): NectarScreen =
            when (route?.substringBefore("/")) {
                OnBoarding.name -> OnBoarding
                Splash.name -> Splash
                null -> OnBoarding
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}