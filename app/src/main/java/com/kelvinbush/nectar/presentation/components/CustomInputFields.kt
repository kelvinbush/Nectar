package com.kelvinbush.nectar.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelvinbush.nectar.R

@Composable
fun PasswordTrailingIcon(showPassword: Boolean, toggleShowPassword: (Boolean) -> Unit) {
    IconButton(onClick = { toggleShowPassword(!showPassword) }) {
        Image(
            painter = if (showPassword)
                painterResource(id = R.drawable.ic_visibility_24)
            else painterResource(id = R.drawable.ic_visibility_off_24),
            contentDescription = stringResource(R.string.password_visibility)
        )
    }
}

@Composable
fun PasswordInput(
    onInputChanged: (String) -> Unit,
    inputText: String,
    showPassword: Boolean,
    toggleShowPassword: (Boolean) -> Unit,
    name: String,
) {

    TextField(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(bottom = 10.dp),
        value = inputText,
        onValueChange = { onInputChanged(it) },
        textStyle = MaterialTheme.typography.h4,
        colors = fieldColors(),
        label = { TextFieldLabel(name = name) },
        singleLine = true,
        keyboardOptions = myKeyboardOptions,
        visualTransformation = if (showPassword) VisualTransformation.None
        else PasswordVisualTransformation(),
        trailingIcon = {
            PasswordTrailingIcon(
                showPassword = showPassword,
                toggleShowPassword = { toggleShowPassword(it) }
            )
        }
    )
}

@Composable
fun MyTextField(
    onInputChanged: (String) -> Unit,
    inputText: String,
    name: String,
) {
    TextField(
        value = inputText,
        onValueChange = { onInputChanged(it) },
        textStyle = MaterialTheme.typography.h4,
        modifier = Modifier
            .background(Color.Transparent),
        colors = fieldColors(),
        singleLine = true,
        keyboardOptions = myKeyboardOptions,
        label = { TextFieldLabel(name = name) })
}

@Composable
fun TextFieldLabel(name: String) {
    Text(
        text = name, style = MaterialTheme.typography.h3,
        color = Color(0xff727272),
        textAlign = TextAlign.Start,
        lineHeight = 29.sp)
}

val myKeyboardOptions = KeyboardOptions(
    keyboardType = KeyboardType.Email,
    imeAction = ImeAction.Done
)

@Composable
fun ButtonLoading(
    name: String,
    isLoading: Boolean,
    enabled: Boolean,
    onClicked: () -> Unit,
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        if (!isLoading) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                enabled = enabled,
                shape = RoundedCornerShape(30),
                onClick = {
                    onClicked()
                }
            ) {
                Text(text = name, style = MaterialTheme.typography.button)
            }
        } else {
            CircularProgressIndicator()
        }
    }
}