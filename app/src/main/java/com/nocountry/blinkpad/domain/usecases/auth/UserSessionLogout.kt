package com.nocountry.blinkpad.domain.usecases.auth

import com.nocountry.blinkpad.domain.repository.AuthDataSource
import javax.inject.Inject

class UserSessionLogout @Inject constructor(
    private val repository: AuthDataSource
){
    operator fun invoke() = repository.logout()
}