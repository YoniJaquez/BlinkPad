package com.nocountry.blinkpad.domain.usecases.auth

data class AuthUseCases(
    val userAuthWithEmailAndPassword: UserAuthWithEmailAndPassword,
    val userSessionLogout: UserSessionLogout,
    val userAuthWithGoogle: UserAuthWithGoogle,
    val getGoogleClient: GetGoogleClient,
    val createUserWithEmailAndPassword: CreateUserWithEmailAndPassword,
    val getCurrentUser: GetCurrentUser,
    val userAuthWithFacebook: UserAuthWithFacebook
)