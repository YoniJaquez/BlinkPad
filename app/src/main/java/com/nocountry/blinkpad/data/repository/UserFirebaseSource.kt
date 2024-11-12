package com.nocountry.blinkpad.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.nocountry.blinkpad.core.Constants.USERS
import com.nocountry.blinkpad.domain.model.Response
import com.nocountry.blinkpad.domain.model.User
import com.nocountry.blinkpad.domain.repository.UserDataSource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class UserFirebaseSource @Inject constructor(
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(USERS) private val storageUserRef: StorageReference
): UserDataSource{
    override suspend fun createUser(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Successs(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }
}