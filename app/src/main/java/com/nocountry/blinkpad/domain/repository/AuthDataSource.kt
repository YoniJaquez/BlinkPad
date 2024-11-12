package com.nocountry.blinkpad.domain.repository

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import com.nocountry.blinkpad.domain.model.Response
import com.nocountry.blinkpad.domain.model.User

interface AuthDataSource {

    val currentUser: FirebaseUser?
    suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Response<FirebaseUser>

    suspend fun loginWithGoogle(idToken: String): Response<FirebaseUser>

    suspend fun loginWithFacebook(accessToken: AccessToken): Response<FirebaseUser>

    suspend fun signUp(user: User): Response<FirebaseUser>
    fun getGoogleClient(): GoogleSignInClient
    fun logout()
}