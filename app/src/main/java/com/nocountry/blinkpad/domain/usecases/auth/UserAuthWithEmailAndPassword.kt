package com.nocountry.blinkpad.domain.usecases.auth

import com.nocountry.blinkpad.domain.repository.AuthDataSource
import javax.inject.Inject

class UserAuthWithEmailAndPassword @Inject constructor(
    private val repository: AuthDataSource
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ) = repository.loginWithEmailAndPassword(email, password)
}