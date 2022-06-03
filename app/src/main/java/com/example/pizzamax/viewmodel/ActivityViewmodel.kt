package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import com.example.pizzamax.data.repository.ValueDealsRepository
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.launch

class ActivityViewmodel(private val repository: ValueDealsRepository) : ViewModel() {
    private val _details: MutableLiveData<ValuesDeals> = MutableLiveData()
    val details: LiveData<ValuesDeals> get() = _details



    fun insertIntoRoom(valueDeals: ValuesDeals) = viewModelScope.launch {
        _details.postValue(valueDeals)
        repository.insertToRoom(valueDeals)
    }

    fun updateList(valueDeals: ValuesDeals) = viewModelScope.launch {
        _details.postValue(valueDeals)
        repository.updateList(valueDeals)
    }

    val getList: LiveData<List<ValuesDeals>> = repository.getAll().asLiveData()
}