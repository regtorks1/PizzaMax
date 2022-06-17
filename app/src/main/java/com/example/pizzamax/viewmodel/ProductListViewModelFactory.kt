package com.example.pizzamax.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pizzamax.data.repository.ProductListRepository
import com.example.pizzamax.data.repository.ProductRepository

class ProductListViewModelFactory (private val repository: ProductListRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModelFactory Class")
    }

}