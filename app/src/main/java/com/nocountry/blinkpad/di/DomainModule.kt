package com.nocountry.blinkpad.di

import com.nocountry.blinkpad.data.repository.AuthFirebaseSource
import com.nocountry.blinkpad.data.repository.UserFirebaseSource
import com.nocountry.blinkpad.domain.repository.AuthDataSource
import com.nocountry.blinkpad.domain.repository.UserDataSource
import com.nocountry.blinkpad.domain.usecases.auth.AuthUseCases
import com.nocountry.blinkpad.domain.usecases.auth.CreateUserWithEmailAndPassword
import com.nocountry.blinkpad.domain.usecases.auth.GetCurrentUser
import com.nocountry.blinkpad.domain.usecases.auth.GetGoogleClient
import com.nocountry.blinkpad.domain.usecases.auth.UserAuthWithEmailAndPassword
import com.nocountry.blinkpad.domain.usecases.auth.UserAuthWithFacebook
import com.nocountry.blinkpad.domain.usecases.auth.UserAuthWithGoogle
import com.nocountry.blinkpad.domain.usecases.auth.UserSessionLogout
import com.nocountry.blinkpad.domain.usecases.user.CreateUser
import com.nocountry.blinkpad.domain.usecases.user.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    //Funcion que provee la implementacion de los metodos de la interfaz authdatasource para que domain no sepa donde se obtienen los datos
    @Singleton
    @Provides
    fun provideAuthDataSouce(authFirebaseSource: AuthFirebaseSource): AuthDataSource = authFirebaseSource



    //Funcion que provee el caso de uso de authenticacion
    @Singleton
    @Provides
    fun provideAuthUseCase(repository: AuthDataSource) = AuthUseCases(
        userAuthWithEmailAndPassword = UserAuthWithEmailAndPassword(repository),
        userSessionLogout = UserSessionLogout(repository),
        userAuthWithGoogle = UserAuthWithGoogle(repository),
        getGoogleClient = GetGoogleClient(repository),
        createUserWithEmailAndPassword = CreateUserWithEmailAndPassword(repository),
        getCurrentUser = GetCurrentUser(repository),
        userAuthWithFacebook = UserAuthWithFacebook(repository)
    )

    //Funcion que provee la implementacion de los metodos de la interfaz UserDataSource para que el domain no sepa donde se obtiene los datas
    @Singleton
    @Provides
    fun provideUserDataSource(userFirebaseSource: UserFirebaseSource): UserDataSource = userFirebaseSource

    //Funcion que provee el caso de uso de User
    @Singleton
    @Provides
    fun provideUserUseCase(repository: UserDataSource) = UserUseCases(
        createUser = CreateUser(repository)
    )
}