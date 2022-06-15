package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import com.example.pizzamax.data.repository.ProductRepository
import com.example.pizzamax.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

     //CART
    fun insertIntoCart(cart: List<Cart>) = viewModelScope.launch {
        //_details.postValue(cart)
        repository.insertToRoom(cart)
    }
    fun updateCart(cart: Cart) = viewModelScope.launch {
        repository.updateList(cart)
    }
    fun deleteAllFromCart() = viewModelScope.launch {
        repository.deleteFromCart()
    }
    val getAllFromCart: LiveData<List<Cart>> = repository.getAllFromCart().asLiveData()



    //FAVORITES
    fun insertIntoFavorites(favorites: List<Favorites>) = viewModelScope.launch {
        repository.insertToFavorites(favorites)
    }
    fun updateFavorites(favorites: Favorites) = viewModelScope.launch {
        repository.updateList(favorites)
    }
    fun deleteAllFromFavorites() = viewModelScope.launch {
        repository.deleteFromFavorites()
    }
    val getAllFavorites: LiveData<List<Favorites>> = repository.getAllFromFavorites().asLiveData()
    fun deleteFavorite(favorites: Favorites) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(favorites)
        }
    }


    //EXPENSES
        fun addToExpenses(expenses: Expenses) = viewModelScope.launch {
            repository.insertToRoom(expenses)
        }
     fun updateExpenses(expenses: MutableList<Expenses>) {
         viewModelScope.launch(Dispatchers.Main) {
             repository.updateList(expenses)
         }
     }

    val getFromExpenses: LiveData<List<Expenses>> = repository.getAllExpenses().asLiveData()

}