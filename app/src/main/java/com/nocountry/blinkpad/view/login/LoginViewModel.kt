package com.nocountry.blinkpad.view.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseUser
import com.nocountry.blinkpad.domain.model.Response
import com.nocountry.blinkpad.domain.usecases.auth.AuthUseCases
import com.nocountry.blinkpad.view.utils.FacebookLoginHandler
import com.nocountry.blinkpad.view.utils.GoogleLoginHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val loginManager: LoginManager,
    private val callbackManager: CallbackManager
): ViewModel() {

    //####VARIABLES######

    var isLoading by mutableStateOf(false)
        private set

    val resultingActivityHandler = FacebookLoginHandler()
    val resultingGoogleLoginHandler = GoogleLoginHandler()

    //Variable que contiene los estados de los campos
    var state by mutableStateOf(LoginState())
        private set

    //Variable que verifica si el correo electronico es correcto
    var isEmailValid by mutableStateOf(false)
        private set

    //Variable que verifica si la contraseña es correcta
    var isPasswordValid by mutableStateOf(false)
        private set

    //Variable que maneja la respuesta de firebase y notificar a la vista
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
            private set


    //###FUNCIONES QUE MANEJAN EL INICIO DE SESIÓN
    private fun loginWithGoogle(idToken: String){
        viewModelScope.launch {
            loginResponse = Response.Loading
            val result = withContext(Dispatchers.IO){
                authUseCases.userAuthWithGoogle(idToken)
            }
            loginResponse = result
        }
    }
    //Funcion que llama al intent de Google y recupera el idToken para posterior mandarlo a firebase y hacer el login
    fun onGoogleLoginSelected(){
       viewModelScope.launch {
           val gsc = authUseCases.getGoogleClient()
           val result = resultingGoogleLoginHandler.getContent(
               gsc
           )
           loginWithGoogle(result!!)
       }
    }

    //Funcion que valida los campos
    private fun validFieldEmailAndPassword(){
        when{
            state.email.isEmpty() -> {
                isEmailValid = false
                insertTextTitleDialog("Error en el campo \nde correó electronico")
                insertTextDescriptionDialog("El campo email esta vacio")
            }
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() ->{
                isEmailValid = false
                insertTextTitleDialog("Error en el formato")
                insertTextDescriptionDialog("Por favor, ingresa una dirección de correo electrónico válida")
            }
            state.password.isEmpty() ->{
                insertTextTitleDialog("Error en el campo \nde contraseña")
                insertTextDescriptionDialog("El campo de contraseña esta vacio")
                isPasswordValid = false
            }
            else -> {
                isPasswordValid= true
                isEmailValid = true
                insertTextTitleDialog()
                insertTextDescriptionDialog()
            }
        }


    }


    //Funcion privada que inserta valor a la descripción al dialogo
    private fun insertTextDescriptionDialog(value:String = "" ){
        state = state.copy(
            errorDescriptionState = value
        )
    }

    //Funcion que inserta titulo al dialogo
    private fun insertTextTitleDialog(title: String = ""){
        state = state.copy(
            titleDialogState = title
        )
    }

    //Funcion que se ejecuta cuando se presiona el botom de inicio de sesión
    fun onLoginSelected(){
        validFieldEmailAndPassword()
        if (isEmailValid && isPasswordValid){
            login()
        }else{
            state = state.copy(
                stateDialog = true
            )
        }
    }

    //Funcion que hace el inicio de sesión con el correo electronico y password
    private fun login(){
        viewModelScope.launch {
            loginResponse = Response.Loading
            val result = withContext(Dispatchers.IO){
                authUseCases.userAuthWithEmailAndPassword(
                    state.email,
                    state.password
                )
            }
            loginResponse = result
        }
    }



    //Inició de sesión con facebookk
    private fun loginWithFacebook(accessToken: AccessToken){
        viewModelScope.launch {
            loginResponse = Response.Loading
            val result = withContext(Dispatchers.IO){
                authUseCases.userAuthWithFacebook(accessToken)
            }
            loginResponse = result
        }
    }

    //Funcion que llama al intent del activity y manda el token para el login
   fun onFacebookLoginSelected(){
       viewModelScope.launch {
           val accessToken = resultingActivityHandler.getContent(loginManager, callbackManager, listOf("email", "public_profile") )
           loginWithFacebook(accessToken!!)
       }
    }

    //###  FUNCIONES #######
    //Funcion que asinga un valor al email
    fun onChangeValueEmail(email: String) {
        state = state.copy(
            email = email
        )
    }
    //Funcion que asigna un valor al passwordd
    fun onChangeValuePassword(password: String) {
        state = state.copy(
            password = password
        )
    }


    //Funcion que carga el estado del progress bar cuando este se carga
    fun stateLoadingProgressBar() {
        isLoading = true
    }
    //Funcion que cambia el estado del progress bar cuando este falla
    fun stateFailureProgressBar() {
        isLoading = false
    }


    //Funcion que oculta el dialogo
    fun stateDialogValue() {
        state = state.copy(
            stateDialog = false
        )
    }
}