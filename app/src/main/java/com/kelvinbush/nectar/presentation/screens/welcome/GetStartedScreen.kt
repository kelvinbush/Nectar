package com.kelvinbush.nectar.presentation.screens.welcome

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelvinbush.nectar.R
import com.kelvinbush.nectar.navigation.BottomNavScreen
import com.kelvinbush.nectar.presentation.components.Btn
import com.kelvinbush.nectar.presentation.components.Leading
import com.kelvinbush.nectar.presentation.components.fieldColors
import com.kelvinbush.nectar.presentation.screens.login.LoginScreenViewModel

private const val TAG = "GetStartedScreen"

@Composable
fun GetStartedScreen(
    navController: NavHostController,
    viewModel: LoginScreenViewModel = hiltViewModel(),
) {
    var phoneNumber by remember { mutableStateOf("") }
    val state by viewModel.loadingState.collectAsState()
    /*
    val idToken by viewModel.idToken.observeAsState()
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

                navController.popBackStack()
                navController.navigate(BottomNavScreen.Shop.route)

            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }*/

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )
    Column(
        modifier = Modifier
            .background(Color(0xfffcfcfc))
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.43f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_3),
                contentDescription = null,
                modifier = Modifier
                    .rotate(-141.29f)
                    .fillMaxSize()
                    .scale(1.8f)
                    .padding(end = 11.dp, top = 40.dp, start = 30.dp),
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.29f)
                    .fillMaxWidth(0.79f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_min_logo),
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.77f)
        ) {
            Text(
                text = "Get your groceries",
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
            )
            Text(
                text = "with fruity",
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                leadingIcon = { Leading() },
                textStyle = MaterialTheme.typography.h4,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth(0.85f),
                colors = fieldColors(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Or connect with social media", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.height(10.dp))
            Btn(
                id = R.drawable.ic_google,
                text = "Continue with Google",
                clicked = {
                   /* val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()

                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSignInClient.signInIntent)*/
                },
                color = Color(0xff5383ec)
            )
        }
    }
}
