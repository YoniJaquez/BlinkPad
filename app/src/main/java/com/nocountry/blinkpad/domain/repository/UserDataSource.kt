package com.nocountry.blinkpad.domain.repository

import com.nocountry.blinkpad.domain.model.Response
import com.nocountry.blinkpad.domain.model.User

interface UserDataSource {
    suspend fun createUser(user: User): Response<Boolean>
}