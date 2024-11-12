package com.nocountry.blinkpad.di

import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.nocountry.blinkpad.core.Constants.NOTES
import com.nocountry.blinkpad.core.Constants.USERS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    //Funcion que provee la instancia de firebaseAuth
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    //Funcion que provee
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseStorange(): FirebaseStorage= FirebaseStorage.getInstance()


    @Provides
    @Singleton
    fun provideLoginManagerFacebook(): LoginManager = LoginManager.getInstance()

    @Provides
    @Singleton
    fun provideCallbackManager(): CallbackManager = CallbackManager.Factory.create()


    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)


    @Provides
    @Named(NOTES)
    fun provideStorageNotesRef(storage: FirebaseStorage): StorageReference = storage.reference.child(NOTES)

    @Provides
    @Named
    fun provideNotesRef(db: FirebaseFirestore): CollectionReference = db.collection(NOTES)

}