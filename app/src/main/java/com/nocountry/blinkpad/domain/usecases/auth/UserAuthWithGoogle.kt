package com.nocountry.blinkpad.domain.usecases.auth

import com.nocountry.blinkpad.domain.repository.AuthDataSource
import javax.inject.Inject

class UserAuthWithGoogle @Inject constructor(
    private val repository: AuthDataSource
) {
    suspend operator fun invoke(idToken: String) = repository.loginWithGoogle(idToken)
}