package com.nocountry.blinkpad.view.login

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nocountry.blinkpad.view.components.DialogErrorMessage
import com.nocountry.blinkpad.view.login.components.LoginContent
import com.nocountry.blinkpad.view.register.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold {
        LoginContent(navController = navController)
    }

    //Maneja el dialogo de error
    if (loginViewModel.state.stateDialog) {
        DialogErrorMessage(
            errorDescription = loginViewModel.state.errorDescriptionState,
            titleError = loginViewModel.state.titleDialogState,
            onDimissRequest = { loginViewModel.stateDialogValue()},
        )
    }
    //Majador del estado de la peticion de login
    LoginValidState(navController = navController)
}
