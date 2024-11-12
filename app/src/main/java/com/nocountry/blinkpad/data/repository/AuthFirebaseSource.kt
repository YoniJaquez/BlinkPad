package com.nocountry.blinkpad.data.repository

import android.content.Context
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.nocountry.blinkpad.R
import com.nocountry.blinkpad.domain.model.Response
import com.nocountry.blinkpad.domain.model.User
import com.nocountry.blinkpad.domain.repository.AuthDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthFirebaseSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @ApplicationContext private val context: Context
): AuthDataSource{
    //Variable que recupera la seccion o datos del usuario logeoado
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    //Metodo que logea al usuario con el email y password cuando ya esta registrado
    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Response<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Successs(result.user!!)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    //Funcion que maneja el login con la cuenta de Google
    override suspend fun loginWithGoogle(idToken: String): Response<FirebaseUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = completeRegisterWithCredential(credential)
            Response.Successs(result!!)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun loginWithFacebook(accessToken: AccessToken): Response<FirebaseUser> {
        return try {
            val credential = FacebookAuthProvider.getCredential(accessToken.token)
            val result = completeRegisterWithCredential(credential)
            Response.Successs(result!!)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    //Funcion que crea al usuario por medio de su correo y contraseña
    override suspend fun signUp(user: User): Response<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(
                user.email,
                user.password
            ).await()
            Response.Successs(result.user!!)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    //Funcion que recupera el cliente de google
    override fun getGoogleClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(context.getString(R.string.default_web_client_id)).requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    //Metodo que cierra la session del usuario
    override fun logout() {
        firebaseAuth.signOut()
    }

    //Funcion que completa el registro con la credencial de la cuenta
    private suspend fun completeRegisterWithCredential(credential: AuthCredential): FirebaseUser? {
        return suspendCancellableCoroutine { cancellableContinuation ->
            firebaseAuth.signInWithCredential(credential).addOnSuccessListener {
                cancellableContinuation.resume(it.user)
            }.addOnFailureListener {
                cancellableContinuation.resumeWithException(it)
            }
        }
    }


}