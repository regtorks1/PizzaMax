package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import com.example.pizzamax.data.repository.ProductRepository
import com.example.pizzamax.model.Appetizers
import com.example.pizzamax.model.BigBetter
import com.example.pizzamax.model.SignaturePizza
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    private val _details: MutableLiveData<ValuesDeals> = MutableLiveData()
    val details: LiveData<ValuesDeals> get() = _details

    //VALUE DEALS
    fun insertIntoRoom(valueDeals: ValuesDeals) = viewModelScope.launch {
        _details.postValue(valueDeals)
        repository.insertToRoom(valueDeals)
    }

    fun updateList(valueDeals: ValuesDeals) = viewModelScope.launch {
        _details.postValue(valueDeals)
        repository.updateList(valueDeals)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    val getList: LiveData<List<ValuesDeals>> = repository.getAll().asLiveData()


    //BIG BETTER
    fun insertIntoRoom(bigBetter: BigBetter) = viewModelScope.launch {
        repository.insertToRoom(bigBetter)
    }

    fun updateList(bigBetter: BigBetter) = viewModelScope.launch {
        repository.updateList(bigBetter)
    }

    fun deleteAllFromBigBetter() = viewModelScope.launch {
        repository.deleteFromBigBetter()
    }

    val getAllFromBigBetter: LiveData<List<BigBetter>> = repository.getAllFromBigBetter()
        .asLiveData()


    //APPETIZERS
    fun insertIntoRoom(appetizers: Appetizers) = viewModelScope.launch {
        repository.insertToRoom(appetizers)
    }

    fun updateList(appetizers: Appetizers) = viewModelScope.launch {
        repository.updateList(appetizers)
    }

    fun deleteAllFromAppetizers()= viewModelScope.launch {
        repository.deleteAllFromAppetizers()
    }

    val getAllFromAppetizers: LiveData<List<Appetizers>> = repository.getAllFromAppetizers().asLiveData()



     //SIGNATURE PIZZA
    fun insertIntoRoom(signaturePizza: SignaturePizza) = viewModelScope.launch {
        repository.insertToRoom(signaturePizza)
    }

    fun updateList(signaturePizza: SignaturePizza) = viewModelScope.launch {
        repository.updateList(signaturePizza)
    }

    fun deleteAllFromPizza()= viewModelScope.launch {
        repository.deleteAllFromSignature()
    }

    val getAllFromSignature: LiveData<List<SignaturePizza>> = repository.getAllFromSignature().asLiveData()
}