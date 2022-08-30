package com.kelvinbush.nectar.presentation.screens.bottom_nav_screens.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.navigation.Screen
import com.kelvinbush.nectar.presentation.components.CategoryComponent
import com.kelvinbush.nectar.presentation.components.SearchTextField
import com.kelvinbush.nectar.presentation.components.ShopHeaderComponent
import com.kelvinbush.nectar.presentation.screens.splash.SplashViewModel
import com.kelvinbush.nectar.util.Constants.DETAIL_ARGUMENT_KEY

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun ShopScreen(
    navController: NavHostController,
    viewModel: ShopViewModel = hiltViewModel(),
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )
    val user = splashViewModel.fUser.value?.user


    val state = viewModel.state.value
    val categories = ArrayList<String>()
    state.products.forEach { item ->
        categories.add(item.category.name)
    }
    var searchItem by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ShopHeaderComponent()
        SearchTextField(searchItem = searchItem, changeEvent = { searchItem = it })
        Box() {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
                    .fillMaxHeight(0.9f)
            ) {
                if (state.products.isNotEmpty()) {
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.carousel_1),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
                categories.toSet().forEach { category ->
                    val routeDetails = Screen.Detail.route + "/{$DETAIL_ARGUMENT_KEY}"
                    item {
                        CategoryComponent(
                            category = category,
                            products = state.products,
                            addItem = { id ->
                                viewModel.addToCart(id = id, quantity = 1)
                            },
                            navigateToDetail = {
                                navController.currentBackStackEntry?.arguments?.putParcelable(
                                    DETAIL_ARGUMENT_KEY, it)
                                navController.navigate(routeDetails) {
                                    launchSingleTop = true
                                }
                            })
                    }
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center)
                    .padding(32.dp))
            }
        }

    }
}