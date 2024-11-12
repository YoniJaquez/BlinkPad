package com.nocountry.blinkpad.view.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nocountry.blinkpad.domain.model.Response
import com.nocountry.blinkpad.view.components.ProgressBar
import com.nocountry.blinkpad.view.navigation.Graph

@Composable
fun LoginValidState(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
){
    when(val loginResponse = loginViewModel.loginResponse){
        is Response.Failure -> {
            loginViewModel.stateFailureProgressBar()
            Toast.makeText(LocalContext.current, loginResponse.exception.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        Response.Loading -> {
            loginViewModel.stateLoadingProgressBar()
        }
        is Response.Successs -> {
            LaunchedEffect(Unit){
                navController.navigate(route = Graph.HOME){
                    popUpTo(Graph.AUTHENTICATION){inclusive = true}
                }
            }
        }
        null -> null
    }
}
