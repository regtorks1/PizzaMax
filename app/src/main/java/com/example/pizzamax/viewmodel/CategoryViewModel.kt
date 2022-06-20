package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import com.example.pizzamax.data.repository.CategoryRepository
import com.example.pizzamax.model.Category
import com.example.pizzamax.model.CategoryItems
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository
    ) : ViewModel() {
        private val _details: MutableLiveData<Category> = MutableLiveData()
    val details : LiveData<Category> get() = _details

    private val _secDetails : MutableLiveData<CategoryItems> = MutableLiveData()
    val secDetails : LiveData<CategoryItems> get() = _secDetails

    //Category
    fun insertIntoRoom(category: Category) = viewModelScope.launch {
        _details.postValue(category)
        repository.insertToRoom(category)
    }

    fun updateList(category: Category) = viewModelScope.launch {
        _details.postValue(category)
        repository.updateList(category)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteFromCategory()
    }

    val getList: LiveData<List<Category>> = repository.getAllFromCategory().asLiveData()


    //CategoryItems
    fun insertIntoRoom(categoryItems: CategoryItems) = viewModelScope.launch {
        _secDetails.postValue(categoryItems)
        repository.insertToRoom(categoryItems)
    }

    fun updateList(categoryItems: CategoryItems) = viewModelScope.launch {
        _secDetails.postValue(categoryItems)
        repository.updateList(categoryItems)
    }

    fun deleteAll1() = viewModelScope.launch {
        repository.getAllFromCategoryItems()
    }

    val getList1: LiveData<List<CategoryItems>> = repository.getAllFromCategoryItems().asLiveData()


}