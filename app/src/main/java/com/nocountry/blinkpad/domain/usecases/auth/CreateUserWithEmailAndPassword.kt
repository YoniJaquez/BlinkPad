package com.nocountry.blinkpad.domain.usecases.auth

import com.nocountry.blinkpad.domain.model.User
import com.nocountry.blinkpad.domain.repository.AuthDataSource
import javax.inject.Inject

class CreateUserWithEmailAndPassword @Inject constructor(
    private val repository: AuthDataSource
){
    suspend operator fun invoke(
        user: User
    ) = repository.signUp(user)
}