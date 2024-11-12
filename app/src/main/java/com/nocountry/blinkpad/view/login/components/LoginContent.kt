package com.nocountry.blinkpad.view.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nocountry.blinkpad.R
import com.nocountry.blinkpad.view.login.LoginState
import com.nocountry.blinkpad.view.login.LoginViewModel
import com.nocountry.blinkpad.view.navigation.AuthScreen

@Composable
fun LoginContent(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    loginViewModel.resultingActivityHandler.handle()
    loginViewModel.resultingGoogleLoginHandler.handle()
    var state = loginViewModel.state
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background"
        )
    }
    Body(state, loginViewModel = loginViewModel, navController = navController)
}

@Composable
fun Body(state: LoginState, loginViewModel: LoginViewModel, navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .offset(y = 30.dp),
            fontSize = 56.sp,
            text = "BlinkPad",
            color = Color(0xFFEBEBEB),
            fontFamily = FontFamily(Font(R.font.righteous_regular))
        )
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.logo), contentDescription = "logo"
        )
        Spacer(modifier = Modifier.height(25.dp))
        Buttons(loginViewModel = loginViewModel)
        Spacer(modifier = Modifier.height(40.dp))
        User(state.email) {
            loginViewModel.onChangeValueEmail(it)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Password(state.password) {
            loginViewModel.onChangeValuePassword(it)
        }
        Spacer(modifier = Modifier.height(30.dp))
        LoginRegister(loginViewModel = loginViewModel, navController = navController)
        Spacer(modifier = Modifier.weight(1f))
        Info()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User(password: String, onTextChange: (String) -> Unit) {

    TextField(
        shape = RoundedCornerShape(20.dp),
        value = password,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .border(0.3.dp, Color(0x43F0F0F0), shape = RoundedCornerShape(24.dp))
           ,
        placeholder = { Text(text = "Correo electronico", color = Color(0xCCE0E0E0)) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color(0xCDFFFFFF),
            disabledTextColor = Color.White

        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {
    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(
        value = password,
        onValueChange = { onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, Color(0x43F0F0F0), shape = RoundedCornerShape(24.dp)),
        placeholder = { Text(text = "Contraseña", color = Color(0xCCE0E0E0)) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color(0xCDFFFFFF)
        ),
        trailingIcon = {
            val imagen = if (passwordVisibility) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            IconButton(
                modifier = Modifier.padding(end = 14.dp),
                onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = imagen,
                    contentDescription = "show password",
                    tint = Color(0xCCFFFFFF),
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun Buttons(
    loginViewModel: LoginViewModel
) {

    Column(modifier = Modifier) {

        Button(
            onClick = {
                loginViewModel.onGoogleLoginSelected()
            },
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF3FAFF),
                disabledContainerColor = Color(0xFF78C8F9),
                contentColor = Color(0xFF505050),
                disabledContentColor = Color.White,
            )
        ) {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.padding(end = 8.dp).size(24.dp),
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null
                )
                Text(text = "Continuar con Gmail",
                    Modifier.padding(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = {


                loginViewModel.onFacebookLoginSelected()
            },
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2C9BEC),
                disabledContainerColor = Color(0xFF78C8F9),
                contentColor = Color.White,
                disabledContentColor = Color.White,
            )
        ) {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                Icon(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp).size(24.dp),
                    tint = Color.White
                )
                Text(text = "Continuar con Facebook",
                    Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun LoginRegister(
    loginViewModel: LoginViewModel,
    navController: NavHostController
) {
    Button(
        onClick = {
            loginViewModel.onLoginSelected()
        },
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x20FFFFFF),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White,
        ),
    ) {
        if (loginViewModel.isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(30.dp))
        } else {
            Text(
                text = "Iniciar Sesión",
                color = Color.White,
                fontSize = 15.sp,
                lineHeight = 4.sp,
                modifier = Modifier
                    .height(30.dp)
                    .padding(5.dp)
            )
        }
    }

    Text(
        text = "or",
        color = Color(0xD0FFFFFF),
        modifier = Modifier
            .fillMaxWidth(),
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
    )

    Button(
        onClick = {
            navController.navigate(
                route = AuthScreen.SignUp.route
            )
        },
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x21FFFFFF),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White,
        )
    ) {
        Text(text = "Registrarse")
    }
}

@Composable
fun Info() {
    Text(
        text = "By registering, you agree to our Terms of Use. Learn how we collect, use and share your data",
        modifier = Modifier
            .fillMaxWidth(),
        fontSize = 14.sp,
        color = Color(0xD0FFFFFF),
        textAlign = TextAlign.Center,
    )
}