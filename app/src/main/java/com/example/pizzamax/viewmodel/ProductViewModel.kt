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
    private val _details: MutableLiveData<ValuesDeals> = MutableLiveData()
    val details: LiveData<ValuesDeals> get() = _details

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

    val getCategories: LiveData<List<Categories>> = repository.getAllFromCategories().asLiveData()


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

    fun  getCategoriesList(query: String): LiveData<List<CategoriesList>> = repository.getAllFromCategoryList(query).asLiveData()


}