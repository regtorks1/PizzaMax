package com.example.pizzamax.views.util

sealed class Resource<out T>{

    data class Success<out T>(val value: T):Resource<T>()
    data class Failure(
        val networkStatus:Boolean,
        val errMessage: String
    ): Resource<Nothing>()

    object Loading:Resource<Nothing>()
}
