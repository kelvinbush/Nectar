package com.kelvinbush.nectar.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.domain.model.LoginCredentials
import com.kelvinbush.nectar.navigation.Screen
import com.kelvinbush.nectar.presentation.components.Btn
import com.kelvinbush.nectar.presentation.components.ButtonLoading
import com.kelvinbush.nectar.presentation.components.MyTextField
import com.kelvinbush.nectar.presentation.components.PasswordInput

@Composable
fun LoginScreen(
    navController: NavController,
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel(),
) {
    val uiState by loginScreenViewModel.uiState.collectAsState()
    val loginCredentials = LoginCredentials(email = uiState.emailInput,
        password = uiState.passwordInput)

    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }


    LaunchedEffect(key1 = uiState.isLoading) {
        if (!uiState.isLoading && uiState.result.isNotEmpty() && uiState.errorMessage.isEmpty()) {
            Toast.makeText(context,
                "User ${uiState.result} login successful",
                Toast.LENGTH_SHORT).show()
            navController.popBackStack()
            navController.navigate(Screen.SignUp.route) { launchSingleTop = true }
        }
    }


    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .background(Color(0xfffcfcfc)),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_min_carrot),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.13f))
                Text(modifier = Modifier.fillMaxWidth(),
                    text = "Login", style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Start
                )

                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, top = 20.dp),
                    text = "Enter your email and password",
                    style = MaterialTheme.typography.h3,
                    color = Color(0xff727272),
                    textAlign = TextAlign.Start

                )
                MyTextField(
                    onInputChanged = loginScreenViewModel::onEmailInputChanged,
                    inputText = uiState.emailInput,
                    name = "Email")
                Spacer(modifier = Modifier.height(10.dp))
                PasswordInput(
                    onInputChanged = loginScreenViewModel::onPasswordInputChanged,
                    inputText = uiState.passwordInput,
                    showPassword = uiState.showPassword,
                    toggleShowPassword = loginScreenViewModel::toggleShowPassword,
                    name = "Password")
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = "Forgot Password?", style = MaterialTheme.typography.h6,
                )
                ButtonLoading(name = "Login",
                    isLoading = uiState.isLoading,
                    enabled = uiState.emailInput.isNotEmpty() && uiState.passwordInput.isNotEmpty(),
                    onClicked = { loginScreenViewModel.loginUser(loginCredentials) })
                Spacer(modifier = Modifier.height(12.dp))
                Btn(
                    id = R.drawable.ic_google,
                    text = "Continue with Google",
                    clicked = {
                        Toast.makeText(context, "Feature not added", Toast.LENGTH_SHORT).show()
                    },
                    color = Color(0xff5383ec)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don't have an account?",
                        style = MaterialTheme.typography.h6,
                        fontFamily = FontFamily(
                            Font(R.font.gilroysemibold, weight = FontWeight.SemiBold)
                        ),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "Signup",
                        style = MaterialTheme.typography.h6,
                        fontFamily = FontFamily(
                            Font(
                                R.font.gilroysemibold,
                                weight = FontWeight.SemiBold
                            )
                        ),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                    )
                }
            }
        }
    )
}




