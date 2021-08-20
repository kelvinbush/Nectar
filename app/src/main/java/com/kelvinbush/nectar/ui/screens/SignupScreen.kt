package com.kelvinbush.nectar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.SystemUiController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.ui.components.fieldColors

@Composable
fun SignupScreen(systemUiController: SystemUiController) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffcfcfc)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Image(painter = painterResource(id = R.drawable.ic_min_carrot), contentDescription = null)
        Spacer(modifier = Modifier.fillMaxHeight(0.13f))
        Text(
            text = "Sign Up", style = MaterialTheme.typography.h2, modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 6.dp)
        )
        Text(
            text = "Enter your credentials to continue",
            style = MaterialTheme.typography.h3,
            color = Color(0xff727272),
            textAlign = TextAlign.Start, modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 20.dp)
        )
        Text(
            text = "Username", style = MaterialTheme.typography.h3,
            color = Color(0xff727272),
            textAlign = TextAlign.Start,
            lineHeight = 29.sp, modifier = Modifier
                .fillMaxWidth(0.85f)
        )
        TextField(
            value = username, onValueChange = { username = it },
            textStyle = MaterialTheme.typography.h4,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth(0.85f),
            colors = fieldColors(), singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Text(
            text = "Email", style = MaterialTheme.typography.h3,
            color = Color(0xff727272),
            textAlign = TextAlign.Start,
            lineHeight = 29.sp, modifier = Modifier
                .fillMaxWidth(0.85f)
        )
        TextField(
            value = email, onValueChange = { email = it },
            textStyle = MaterialTheme.typography.h4,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth(0.85f),
            colors = fieldColors(), singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )
        Text(
            text = "Password", style = MaterialTheme.typography.h3,
            color = Color(0xff727272),
            textAlign = TextAlign.Start,
            lineHeight = 29.sp, modifier = Modifier
                .fillMaxWidth(0.85f)

        )
        TextField(
            value = password, onValueChange = { password = it },
            textStyle = MaterialTheme.typography.h4,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth(0.85f)
                .padding(bottom = 10.dp),
            colors = fieldColors(), singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (showPassword) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                Image(
                    painter = if (showPassword)
                        painterResource(id = R.drawable.ic_visibility_24)
                    else painterResource(id = R.drawable.ic_visibility_off_24),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        showPassword = !showPassword
                    }
                )
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(0.85f),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "By continuing you agree to our ", style = MaterialTheme.typography.h5)
            Text(
                text = "Terms of Service",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(0.85f),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "and ", style = MaterialTheme.typography.h5)
            Text(
                text = "Privacy Policy.",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(60.dp),
            shape = RoundedCornerShape(30)
        ) {
            Text(text = "Sign Up", style = MaterialTheme.typography.button)
        }
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.85f),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.h6,
                fontFamily = FontFamily(
                    Font(R.font.gilroysemibold, weight = FontWeight.SemiBold)
                ),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Sign in",
                style = MaterialTheme.typography.h6,
                fontFamily = FontFamily(Font(R.font.gilroysemibold, weight = FontWeight.SemiBold)),
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.primary,
            )
        }

    }
}