package com.kelvinbush.nectar.presentation.screens.bottomNavigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.kelvinbush.nectar.R.string.screen_label
import com.kelvinbush.nectar.presentation.screens.cart.MyCart
import com.kelvinbush.nectar.viewmodel.LoginScreenViewModel
import androidx.compose.ui.res.stringResource as stringResource1

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavHost(
    viewModel: LoginScreenViewModel,
    navController: NavHostController,
) {
    val bottomItems = listOf(
        BottomNavScreen.Shop,
        BottomNavScreen.Explore,
        BottomNavScreen.Cart,
        BottomNavScreen.Favourite,
        BottomNavScreen.Account
    )

    LaunchedEffect(key1 = 1) {
        viewModel.getAllProducts()
    }
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                elevation = 8.dp,
                modifier = Modifier.height(62.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(screen.drawableId),
                                contentDescription = stringResource1(screen_label)
                            )
                        },
                        label = {
                            Text(
                                stringResource1(screen.resourceId),
                                color = if (currentDestination?.hierarchy?.any {
                                        it.route == screen.route
                                    } == true)
                                    MaterialTheme.colors.primary else Color.Black,
                                textAlign = TextAlign.Start)
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
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
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavScreen.Shop.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavScreen.Shop.route) { ShopScreen(navController, viewModel) }
            composable(BottomNavScreen.Explore.route) { ExploreScreen() }
            composable(BottomNavScreen.Cart.route) { MyCart(navController) }
            composable(BottomNavScreen.Favourite.route) { FavouriteScreen() }
            composable(BottomNavScreen.Account.route) { AccountScreen(navController, viewModel) }
        }
    }
}