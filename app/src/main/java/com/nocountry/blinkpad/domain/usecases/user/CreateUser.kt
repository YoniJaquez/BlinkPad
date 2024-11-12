package com.nocountry.blinkpad.domain.usecases.user

import com.nocountry.blinkpad.domain.model.User
import com.nocountry.blinkpad.domain.repository.UserDataSource
import javax.inject.Inject

class CreateUser @Inject constructor(
    private val repository: UserDataSource
) {
    suspend operator fun invoke(user: User) = repository.createUser(user)
}