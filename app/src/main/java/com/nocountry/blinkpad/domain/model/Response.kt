package com.nocountry.blinkpad.domain.model

//Clase sellada que maneja la respuesta de firebase
sealed class Response<out T> {
    object Loading: Response <Nothing>()
    data class Successs<out T>(val data: T): Response<T>()
    data class Failure<out T>(val exception: Exception): Response<T>()
}