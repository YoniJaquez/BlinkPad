package com.nocountry.blinkpad.view.inicio

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MateriasScreen(
    navController: NavController
){
    Scaffold {
        MateriasContent()
    }
}