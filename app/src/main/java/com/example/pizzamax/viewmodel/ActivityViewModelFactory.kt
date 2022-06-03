package com.example.pizzamax.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pizzamax.data.repository.ValueDealsRepository

class ActivityViewModelFactory (private val repository: ValueDealsRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(ActivityViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ActivityViewmodel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModelFactory Class")
    }

}