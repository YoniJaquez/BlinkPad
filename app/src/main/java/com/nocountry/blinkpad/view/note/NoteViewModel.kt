package com.nocountry.blinkpad.view.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.nocountry.blinkpad.domain.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(

): ViewModel(){

    var state by mutableStateOf(NoteState())
        private set

    var responseNote by mutableStateOf<Response<FirebaseUser>?>(null)
        private set


    fun onChangeValueTitleNote(title: String) {
        state = state.copy(
            titleNote = title
        )
    }

    fun onChangeValueDescriptionNote(description: String) {
        state = state.copy(
            noteDescription = description
        )
    }

    fun saveNote() {

    }

    fun onChangeValueSearchText(textSearch: String) {
        state= state.copy(
            searchText = textSearch
        )
    }

    fun onValueStateExpanded( expandedTrue: Boolean) {
        state = state.copy(
            expandedState = expandedTrue
        )
    }

}