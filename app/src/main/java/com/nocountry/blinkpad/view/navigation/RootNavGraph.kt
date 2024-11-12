package com.nocountry.blinkpad.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nocountry.blinkpad.view.home.HomeScreen

@Composable
fun RootNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController, route = Graph.ROOT, startDestination = Graph.AUTHENTICATION){
        authNavGraph(
            navController = navController
        )
        composable(route = Graph.HOME){
            HomeScreen()
        }

    }

}