package com.nocountry.blinkpad.view.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.rounded.Book
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nocountry.blinkpad.view.inicio.MateriasScreen

@Composable
fun HomeBottomBarNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.UploadedSubjects.route
    ) {
        composable(route = HomeBottomBarScreen.UploadedSubjects.route) {
            MateriasScreen(navController = navController)
        }
    }
}

sealed class HomeBottomBarScreen(
    val route: String,
    var title: String,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector,
) {

    object UploadedSubjects : HomeBottomBarScreen(
        route = "posts",
        title = "Posts",
        filledIcon = Icons.Rounded.Book,
        outlinedIcon = Icons.Outlined.Book
    )

}