package com.kelvinbush.nectar.presentation.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.navigation.Screen
import com.kelvinbush.nectar.presentation.common.ext.fieldModifier
import com.kelvinbush.nectar.presentation.components.EmailField
import com.kelvinbush.nectar.presentation.components.PasswordField
import com.kelvinbush.nectar.presentation.components.RepeatPasswordField

@Composable
fun SignupScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {
    val uiState by viewModel.uiState
    val fieldModifier = Modifier.fieldModifier()

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )

    if (uiState.hasError) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = uiState.error
            )
        }
    } else if (uiState.isAuthenticated) {
        LaunchedEffect(key1 = null) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "SignUp successful"
            )
            navController.navigate(Screen.Login.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xfffcfcfc)),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Image(painter = painterResource(id = R.drawable.ic_min_carrot),
                contentDescription = null)
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
            EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)
            PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)
            RepeatPasswordField(uiState.repeatPassword,
                viewModel::onRepeatPasswordChange,
                fieldModifier)
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
                onClick = {
                    viewModel.onSignUpClick()
                },
                modifier = Modifier
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
                    fontFamily = FontFamily(Font(R.font.gilroysemibold,
                        weight = FontWeight.SemiBold)),
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

        }
    }
}