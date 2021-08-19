package com.kelvinbush.nectar

enum class NectarScreen {
    OnBoarding,
    Start,
    Login,
    Splash;

    companion object {
        fun fromRoute(route: String?): NectarScreen =
            when (route?.substringBefore("/")) {
                OnBoarding.name -> OnBoarding
                Splash.name -> Splash
                Start.name -> Start
                Login.name -> Login
                null -> OnBoarding
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}