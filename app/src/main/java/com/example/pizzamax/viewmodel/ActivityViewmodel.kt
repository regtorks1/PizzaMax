package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import com.example.pizzamax.data.repository.ProductRepository
import com.example.pizzamax.model.Cart
import com.example.pizzamax.model.Favorites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityViewmodel(private val repository: ProductRepository) : ViewModel() {
    private val _details: MutableLiveData<Cart> = MutableLiveData()
    val details: LiveData<Cart> get() = _details

    //CART
    fun insertIntoCart(cart: List<Cart>) = viewModelScope.launch {
        //_details.postValue(cart)
        repository.insertToRoom(cart)
    }
    fun updateCart(cart: Cart) = viewModelScope.launch {
        _details.postValue(cart)
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

    fun deleteFavorite(favorites: MutableList<Favorites>){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(favorites)
        }
    }





}