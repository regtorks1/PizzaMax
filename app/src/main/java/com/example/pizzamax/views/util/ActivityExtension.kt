package com.example.pizzamax.views.util

import androidx.appcompat.app.AppCompatActivity

class ActivityExtension: AppCompatActivity() {

    fun String.toInteger(string: String) = Integer.parseInt(string)
}