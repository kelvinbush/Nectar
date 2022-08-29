package com.kelvinbush.nectar.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kelvinbush.nectar.NectarScreen.Shop
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.navigation.BottomNavScreen
import com.kelvinbush.nectar.navigation.Screen
import com.kelvinbush.nectar.presentation.components.Btn
import com.kelvinbush.nectar.presentation.components.fieldColors
import com.kelvinbush.nectar.util.LoadingState

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    var showPassword by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val state by viewModel.loadingState.collectAsState()
    /*val idToken by viewModel.idToken.observeAsState()
    val context = LocalContext.current
    val currentUser = Firebase.auth.currentUser
    val fUser by viewModel.fUser.observeAsState()
    val token = "1094590299473-1edm1h1dmpo1cq64p95ne4lvre6jp2u7.apps.googleusercontent.com"

    // Equivalent of onActivityResult
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                viewModel.signWithCredential(credential)
                if (currentUser != null) {
                    if (fUser != null) {
                        navController.popBackStack()
                        navController.navigate(BottomNavScreen.Shop.route)
                    }
                }
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }*/


    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackBarHostState),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xfffcfcfc)),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_min_carrot),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.13f))
                Text(
                    text = "Login", style = MaterialTheme.typography.h2, modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 6.dp)
                )
                Text(
                    text = "Enter your email and password",
                    style = MaterialTheme.typography.h3,
                    color = Color(0xff727272),
                    textAlign = TextAlign.Start, modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 20.dp)
                )
                Text(
                    text = "Email", style = MaterialTheme.typography.h3,
                    color = Color(0xff727272),
                    textAlign = TextAlign.Start,
                    lineHeight = 29.sp, modifier = Modifier
                        .fillMaxWidth(0.85f)
                )
                TextField(
                    value = uiState.email, onValueChange = viewModel::onEmailChange,
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
                    value = uiState.password, onValueChange = viewModel::onPasswordChange,
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
                                painterResource(id = R.drawable.ic_visibility_on)
                            else painterResource(id = R.drawable.ic_visibility_off),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                showPassword = !showPassword
                            }
                        )
                    }
                )
                Text(
                    text = "Forgot Password?", style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 22.dp)
                )
                Button(
                    onClick = {
                        if (viewModel.onSignInClick()) {
                            navController.popBackStack()
                            navController.navigate(BottomNavScreen.Shop.route) { launchSingleTop = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(60.dp),
                    shape = RoundedCornerShape(30),
                    enabled = uiState.email.isNotEmpty() && uiState.password.isNotEmpty()
                ) {
                    Text(text = "Login", style = MaterialTheme.typography.button)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Btn(
                    id = R.drawable.ic_google,
                    text = "Continue with Google",
                    clicked = {
                        /*val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(token)
                            .requestEmail()
                            .build()

                        val googleSignInClient = GoogleSignIn.getClient(context, gso)
                        launcher.launch(googleSignInClient.signInIntent)*/
                    },
                    color = Color(0xff5383ec)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.85f),
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
                when (state.status) {
                    LoadingState.Status.SUCCESS -> {
                        Text(text = "Success")
                    }
                    LoadingState.Status.FAILED -> {
                        Text(text = state.msg ?: "Error")
                    }
                    else -> {
                    }
                }

            }
        }
    )
}