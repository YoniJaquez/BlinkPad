package com.nocountry.blinkpad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.blinkpad.domain.usecases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel(){
    private val _loading = MutableStateFlow(true)
    val loading:StateFlow<Boolean>  = _loading

    //Funcion que guarda al usuario logeado
    val currentUser = authUseCases.getCurrentUser()
    init {
        viewModelScope.launch {
            delay(100)
            _loading.value = false
        }
    }
    //Funcion que valida si hay un usuario logeado

}