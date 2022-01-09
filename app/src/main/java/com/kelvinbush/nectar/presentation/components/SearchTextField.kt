package com.kelvinbush.nectar.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R

@Composable
fun SearchTextField(searchItem: String, changeEvent: (String) -> Unit) {
    TextField(
        value = searchItem,
        onValueChange = { changeEvent(it) },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 4.dp)
            )

        },
        placeholder = { Text(text = "Search store") },
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xfff2f3f2),
            textColor = Color(0xff7c7c7c),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color(0xff7c7c7c)
        ),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(50.dp)
    )
}