package com.nocountry.blinkpad.domain.usecases.auth

import com.facebook.AccessToken
import com.nocountry.blinkpad.domain.repository.AuthDataSource
import javax.inject.Inject

class UserAuthWithFacebook @Inject constructor(
    private val repository: AuthDataSource
) {
    suspend operator fun invoke(accessToken: AccessToken) = repository.loginWithFacebook(accessToken)
}