package com.kelvinbush.nectar.ui.screens.bottomNavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kelvinbush.nectar.ui.components.SearchTextField
import com.kelvinbush.nectar.ui.theme.headerTextStyle


@Composable
fun ExploreScreen() {
    var search by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.07f))
        Text(text = "Find Products", style = headerTextStyle)
        SearchTextField(searchItem = search, changeEvent = { search = it })
    }
}
