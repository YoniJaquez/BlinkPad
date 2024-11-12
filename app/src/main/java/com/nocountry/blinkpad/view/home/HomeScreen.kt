package com.nocountry.blinkpad.view.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nocountry.blinkpad.view.navigation.HomeBottomBarNavGraph
import com.nocountry.blinkpad.view.navigation.HomeBottomBarScreen

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Box(){
                FloatingActionButton(
                    onClick = { /* stub */ },
                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(70.dp)
                        .offset(y = 50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(45.dp)
                    )
                }
            }
        }
    ) {
        HomeBottomBarNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        HomeBottomBarScreen.UploadedSubjects
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any {
        it.route == currentDestination?.route
    }


    val customShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {

            val path = androidx.compose.ui.graphics.Path().apply {
                val cornerRadius = 70f
                // Top left arc
                arcTo(
                    rect = Rect(
                        left = -cornerRadius,
                        top = -cornerRadius,
                        right = cornerRadius,
                        bottom = cornerRadius
                    ),
                    startAngleDegrees = 90.0f,
                    sweepAngleDegrees = -90.0f,
                    forceMoveTo = false
                )
                lineTo(x = size.width - cornerRadius, y = 0f)
                // Top right arc
                arcTo(
                    rect = Rect(
                        left = size.width - cornerRadius,
                        top = -cornerRadius,
                        right = size.width + cornerRadius,
                        bottom = cornerRadius
                    ),
                    startAngleDegrees = 180.0f,
                    sweepAngleDegrees = -90.0f,
                    forceMoveTo = false
                )
                lineTo(x = size.width, y = size.height - cornerRadius)
                // Bottom right arc
                arcTo(
                    rect = Rect(
                        left = size.width - cornerRadius,
                        top = size.height - cornerRadius,
                        right = size.width + cornerRadius,
                        bottom = size.height + cornerRadius
                    ),
                    startAngleDegrees = 270.0f,
                    sweepAngleDegrees = -90.0f,
                    forceMoveTo = false
                )
                lineTo(x = cornerRadius, y = size.height)
                // Bottom left arc
                arcTo(
                    rect = Rect(
                        left = -cornerRadius,
                        top = size.height - cornerRadius,
                        right = cornerRadius,
                        bottom = size.height + cornerRadius
                    ),
                    startAngleDegrees = 0.0f,
                    sweepAngleDegrees = -90.0f,
                    forceMoveTo = false
                )
                lineTo(x = 0f, y = cornerRadius)

                close()
            }

            return Outline.Generic(path)
        }

    }

    if (bottomBarDestination) {

        Column(
            modifier = Modifier
                .height(90.dp)
                .focusable(false)
        ){
            Box(modifier = Modifier.padding(15.dp).background(Color(0xFF3E4455), shape = ShapeDefaults.Large)){
                Row() {
                    screens.forEach { screen ->
                        AddItem(
                            screen = screen,
                            currentDestination = currentDestination,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            Icon(
                imageVector = if (selected) screen.filledIcon else screen.outlinedIcon,
                contentDescription = "navigation icon",
                tint = Color(0xFFA1A1B4)
            )
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color(0xFF3E4455),
            selectedIconColor = Color.Black,
        ),
    )


}