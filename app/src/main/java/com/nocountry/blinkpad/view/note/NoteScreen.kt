package com.nocountry.blinkpad.view.note

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nocountry.blinkpad.view.note.components.NoteContent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    navController: NavHostController,
    noteViewModel: NoteViewModel = hiltViewModel()
){
    Scaffold {
        NoteContent(noteViewModel = noteViewModel)
    }
}