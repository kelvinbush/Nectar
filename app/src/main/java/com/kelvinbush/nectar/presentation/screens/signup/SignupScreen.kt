package com.kelvinbush.nectar.presentation.screens.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.domain.model.SignupUser
import com.kelvinbush.nectar.navigation.Screen
import com.kelvinbush.nectar.presentation.components.fieldColors

@Composable
fun SignupScreen(
    navController: NavController,
    signupViewModel: SignupViewModel = hiltViewModel(),
) {
    val uiState by signupViewModel.uiState.collectAsState()

    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )

    LaunchedEffect(key1 = uiState.isLoading) {
        Log.d( "signupScreen: ", "triggered")
        Log.d( "signupScreen: ", "Result: ${uiState.result}")
        Log.d( "signupScreen: ", "Errors: ${uiState.errorMessage}")

        if (!uiState.isLoading && uiState.result.isNotEmpty() && uiState.errorMessage.isEmpty()) {
            Toast.makeText(context,
                "User ${uiState.result} signed up successfully",
                Toast.LENGTH_SHORT).show()
            navController.popBackStack()
            navController.navigate(Screen.Login.route) { launchSingleTop = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffcfcfc)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = R.drawable.ic_min_carrot), contentDescription = null)
        Spacer(modifier = Modifier.height(16.dp))
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
            value = uiState.nameInput, onValueChange = { signupViewModel.onNameInputChanged(it) },
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
            value = uiState.emailInput, onValueChange = { signupViewModel.onEmailInputChanged(it) },
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
            value = uiState.passwordInput,
            onValueChange = { signupViewModel.onPasswordInputChanged(it) },
            textStyle = MaterialTheme.typography.h4,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth(0.85f)
                .padding(bottom = 10.dp),
            colors = fieldColors(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (uiState.showPassword) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                Image(
                    painter = if (uiState.showPassword)
                        painterResource(id = R.drawable.ic_visibility_24)
                    else painterResource(id = R.drawable.ic_visibility_off_24),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        signupViewModel.toggleShowPassword(!uiState.showPassword)
                    }
                )
            }
        )
        Text(
            text = "Password Confirmation", style = MaterialTheme.typography.h3,
            color = Color(0xff727272),
            textAlign = TextAlign.Start,
            lineHeight = 29.sp, modifier = Modifier
                .fillMaxWidth(0.85f)

        )
        TextField(
            value = uiState.passwordConfirmationInput, onValueChange = {
                signupViewModel.onPasswordConfirmationInputChanged(it)
            },
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
            visualTransformation = if (uiState.showPassword) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                Image(
                    painter = if (uiState.showPassword)
                        painterResource(id = R.drawable.ic_visibility_24)
                    else painterResource(id = R.drawable.ic_visibility_off_24),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        signupViewModel.toggleShowPassword(!uiState.showPassword)
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
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            if (!uiState.isLoading) {
                Button(
                    onClick = {
                        val userSignup = SignupUser(name = uiState.nameInput,
                            passwordConfirmation = uiState.passwordConfirmationInput,
                            password = uiState.passwordInput,
                            email = uiState.emailInput)
                        signupViewModel.signup(userSignup)
                        Log.d("signupScreen: ", uiState.errorMessage)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(60.dp),
                    shape = RoundedCornerShape(30)
                ) {
                    Text(text = "Sign Up", style = MaterialTheme.typography.button)
                }
            } else {
                CircularProgressIndicator()
            }

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