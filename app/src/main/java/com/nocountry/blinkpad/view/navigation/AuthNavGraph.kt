package com.nocountry.blinkpad.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nocountry.blinkpad.view.login.LoginScreen
import com.nocountry.blinkpad.view.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable( route = AuthScreen.Login.route){
            LoginScreen(navController = navController)
        }
        composable(route = AuthScreen.SignUp.route){
            RegisterScreen(navController = navController)
        }
    }
}

sealed class AuthScreen(
    val route: String
){
    object Login: AuthScreen("login")
    object SignUp: AuthScreen("signup")
}