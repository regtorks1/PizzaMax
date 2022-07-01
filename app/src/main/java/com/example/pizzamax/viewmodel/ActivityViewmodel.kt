package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import com.example.pizzamax.data.repository.ProductRepository
import com.example.pizzamax.model.Cart
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoryItems
import kotlinx.coroutines.launch

class ActivityViewmodel(private val repository: ProductRepository) : ViewModel() {
    private val _details: MutableLiveData<Cart> = MutableLiveData()
    val details: LiveData<Cart> get() = _details

    //CATEGORIES
    fun insertIntoCategories(categories: Categories) = viewModelScope.launch {
        repository.insertToCategories(categories)
    }

    fun updateCategories(categories: Categories) = viewModelScope.launch {
        repository.updateCategories(categories)
    }

    fun deleteAllCategories() = viewModelScope.launch {
        repository.deleteAllFromCategories()
    }

    fun getCategories(query: String): LiveData<List<Categories>> = repository.getAllFromCategories(query).asLiveData()


    //CATEGORIES LIST
    fun insertIntoCategoryList(categories: MutableList<CategoryItems>) = viewModelScope.launch {
        repository.insertToCategoryList(categories)
    }

    fun updateCategories(categories: CategoryItems) = viewModelScope.launch {
        repository.updateCategoryList(categories)
    }

    fun deleteAllList() = viewModelScope.launch {
        repository.deleteAllFromCategoryList()
    }

    val getCategoryItems: LiveData<List<CategoryItems>> =
        repository.getAllFromCategoryList().asLiveData()


}