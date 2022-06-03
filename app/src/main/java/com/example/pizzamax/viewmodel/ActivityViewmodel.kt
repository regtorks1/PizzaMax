package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import com.example.pizzamax.data.repository.ValueDealsRepository
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.launch

class ActivityViewmodel(private val repository: ValueDealsRepository) : ViewModel() {
    private val _details: MutableLiveData<ValuesDeals> = MutableLiveData()
    val details: LiveData<ValuesDeals> get() = _details

    /*fun insertIntoDb(locationDetails: LocationDetails) = viewModelScope.launch {
        locationRepository.addLocation(_details)
    }*/


    fun insertIntoRoom(locationDetails: ValuesDeals) = viewModelScope.launch {
        repository.insertToRoom(locationDetails)
    }

    val getDetails: LiveData<List<ValuesDeals>> = repository.getAll().asLiveData()
}