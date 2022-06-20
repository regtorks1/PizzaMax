package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import androidx.room.Query
import com.example.pizzamax.data.repository.ProductRepository
import com.example.pizzamax.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    private val _details: MutableLiveData<CategoriesList> = MutableLiveData()
    val details: LiveData<CategoriesList> get() = _details

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

     fun  getCategoriesList(query: String): LiveData<List<Categories>> = repository.getAllFromCategories(query).asLiveData()




     //CATEGORIES LIST
    fun insertIntoCategories(categories: CategoriesList) = viewModelScope.launch {
        repository.insertToCategoryList(categories)
    }

    fun updateCategories(categories: CategoriesList) = viewModelScope.launch {
        repository.updateCategoryList(categories)
    }

    fun deleteAllList() = viewModelScope.launch {
        repository.deleteAllFromCategoryList()
    }

    val getCategoryList: LiveData<List<CategoriesList>> = repository.getAllFromCategoryList().asLiveData()




}