package com.nocountry.blinkpad.view.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val errorDescriptionState: String = "",
    val titleDialogState: String = "",
    val stateDialog: Boolean = false
)