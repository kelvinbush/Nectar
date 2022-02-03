package com.kelvinbush.nectar.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kelvinbush.nectar.R

@Composable
fun Leading() {
    Row {
        Image(painterResource(id = R.drawable.flag), contentDescription = null)
        Text(text = "+254", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun Btn(id: Int, text: String, clicked: () -> Unit, color: Color) {
    Button(
        onClick = { clicked() },
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(70.dp)
            .padding(top = 22.dp),
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color
        )
    ) {
        Row {
            Image(
                painter = painterResource(id = id), contentDescription = null,
                modifier = Modifier.padding(start = 6.dp, end = 24.dp)
            )
            Text(text = text, style = MaterialTheme.typography.button)
        }
    }
}

@Composable
fun fieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = Color(0xfffcfcfc),
    cursorColor = Color(0xff7c7c7c),
    focusedIndicatorColor = Color(0xffe2e2e2),
    unfocusedIndicatorColor = Color(0xffe2e2e2)
)
