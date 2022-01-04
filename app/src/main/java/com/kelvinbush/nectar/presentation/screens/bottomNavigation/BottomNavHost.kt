package com.kelvinbush.nectar.presentation.screens.bottomNavigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.annotation.ExperimentalCoilApi
import com.kelvinbush.nectar.R.string.screen_label
import com.kelvinbush.nectar.navigation.SetUpNavGraph
import com.kelvinbush.nectar.viewmodel.LoginScreenViewModel
import androidx.compose.ui.res.stringResource as stringResource1

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavHost(
    viewModel: LoginScreenViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    LaunchedEffect(key1 = 1) {
        viewModel.getAllProducts()
    }
    Scaffold(
        bottomBar = { MyBottomNav(navController = navController) }
    ) {
        SetUpNavGraph(navController = navController)
    }
}

@Composable
fun MyBottomNav(navController: NavHostController) {
    val bottomItems = listOf(
        BottomNavScreen.Shop,
        BottomNavScreen.Explore,
        BottomNavScreen.Cart,
        BottomNavScreen.Favourite,
        BottomNavScreen.Account
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    if (currentDestination?.route == BottomNavScreen.Shop.route ||
        currentDestination?.route == BottomNavScreen.Explore.route ||
        currentDestination?.route == BottomNavScreen.Cart.route ||
        currentDestination?.route == BottomNavScreen.Favourite.route ||
        currentDestination?.route == BottomNavScreen.Account.route
    ) {
        BottomNavigation(
            backgroundColor = Color.White,
            elevation = 8.dp,
            modifier = Modifier.height(62.dp)
        ) {

            bottomItems.forEach { screen ->
                if (currentDestination != null) {
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
                                color = if (currentDestination.hierarchy.any {
                                        it.route == screen.route
                                    })
                                    MaterialTheme.colors.primary else Color.Black,
                                textAlign = TextAlign.Start)
                        },
                        selected = currentDestination.hierarchy.any { it.route == screen.route },
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
    } else {
        Spacer(modifier = Modifier.width(0.dp))
    }
}