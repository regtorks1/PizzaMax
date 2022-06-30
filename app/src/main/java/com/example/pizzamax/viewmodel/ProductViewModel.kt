package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import com.example.pizzamax.data.repository.ProductRepository
import com.example.pizzamax.model.Cart
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoryItems
import com.example.pizzamax.model.Favorites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Handler

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    private val _details: MutableLiveData<CategoryItems> = MutableLiveData()
    val details: LiveData<CategoryItems> get() = _details

    //CATEGORIES
    fun insertIntoCategories(categories: MutableList<Categories>) = viewModelScope.launch {
        repository.insertToCategories(categories)
    }

    fun updateCategories(categories: Categories) = viewModelScope.launch {
        repository.updateCategories(categories)
    }

    fun deleteAllCategories() = viewModelScope.launch {
        repository.deleteAllFromCategories()
    }

    fun getCategoriesList(query: String): LiveData<List<Categories>> =
        repository.getAllFromCategories(query).asLiveData()


    //CATEGORIES LIST
    fun insertIntoCategories(categories: CategoryItems) = viewModelScope.launch {
       // repository.insertToCategoryList(categories)
    }

    fun updateCategories(categories: CategoryItems) = viewModelScope.launch {
        repository.updateCategoryList(categories)
    }

    fun deleteAllList() = viewModelScope.launch {
        repository.deleteAllFromCategoryList()
    }

    val getCategoryList: LiveData<List<CategoryItems>> =
        repository.getAllFromCategoryList().asLiveData()


    //CART
    fun insertIntoCart(cart: List<Cart>) = viewModelScope.launch {
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


}