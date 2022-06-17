package com.example.pizzamax.viewmodel

import androidx.lifecycle.*
import com.example.pizzamax.data.repository.ProductListRepository
import com.example.pizzamax.views.adapters.ProductRecyclerViewItem
import com.example.pizzamax.views.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val repository: ProductListRepository
) : ViewModel() {
    private val _productItems: MutableLiveData<Resource<List<ProductRecyclerViewItem>>> =
        MutableLiveData()
    val productItems: LiveData<Resource<List<ProductRecyclerViewItem>>> get() = _productItems


     private val _productDeals: MutableLiveData<Resource<ProductRecyclerViewItem.ValuesDeals>> =
        MutableLiveData()
    val productDeals: LiveData<Resource<ProductRecyclerViewItem.ValuesDeals>> get() = _productDeals


    //VALUE DEALS

    fun addToDealTable(valueDeals: ProductRecyclerViewItem.ValuesDeals) = viewModelScope.launch {
        _productItems.postValue(Resource.Loading)
        val deferredDeal = async{ repository.insertToRoom(valueDeals) }
        if (deferredDeal.isCompleted){
            _productDeals.value = Resource.Success(valueDeals)
        }else{
            Resource.Failure(false, "Insert err")
        }

    }

    fun updateList(valueDeals: ProductRecyclerViewItem.ValuesDeals) = viewModelScope.launch {
        //_productItems.postValue(valueDeals)
        repository.updateList(valueDeals)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    val getList: LiveData<List<ProductRecyclerViewItem.ValuesDeals>> = repository.getAll().asLiveData()


    //BIG BETTER
    fun insertIntoRoom(bigBetter: ProductRecyclerViewItem.BigBetter) = viewModelScope.launch {
        repository.insertToRoom(bigBetter)
    }

    fun updateList(bigBetter: ProductRecyclerViewItem.BigBetter) = viewModelScope.launch {
        repository.updateList(bigBetter)
    }

    fun deleteAllFromBigBetter() = viewModelScope.launch {
        repository.deleteFromBigBetter()
    }

    val getAllFromBigBetter: LiveData<List<ProductRecyclerViewItem.BigBetter>> = repository.getAllFromBigBetter()
        .asLiveData()


    //APPETIZERS
    fun insertIntoRoom(appetizers: ProductRecyclerViewItem.Appetizers) = viewModelScope.launch {
        repository.insertToRoom(appetizers)
    }

    fun updateList(appetizers: ProductRecyclerViewItem.Appetizers) = viewModelScope.launch {
        repository.updateList(appetizers)
    }

    fun deleteAllFromAppetizers() = viewModelScope.launch {
        repository.deleteAllFromAppetizers()
    }

    val getAllFromAppetizers: LiveData<List<ProductRecyclerViewItem.Appetizers>> =
        repository.getAllFromAppetizers().asLiveData()


    //SIGNATURE PIZZA
    fun insertIntoRoom(signaturePizza: ProductRecyclerViewItem.SignaturePizza) = viewModelScope.launch {
        repository.insertToRoom(signaturePizza)
    }

    fun updateList(signaturePizza: ProductRecyclerViewItem.SignaturePizza) = viewModelScope.launch {
        repository.updateList(signaturePizza)
    }

    fun deleteAllFromPizza() = viewModelScope.launch {
        repository.deleteAllFromSignature()
    }

    val getAllFromSignature: LiveData<List<ProductRecyclerViewItem.SignaturePizza>> =
        repository.getAllFromSignature().asLiveData()

    //CART
    fun insertIntoCart(cart: List<ProductRecyclerViewItem.Cart>) = viewModelScope.launch {
        //_details.postValue(cart)
        repository.insertToRoom(cart)
    }

    fun updateCart(cart: ProductRecyclerViewItem.Cart) = viewModelScope.launch {
        repository.updateList(cart)
    }

    fun deleteAllFromCart() = viewModelScope.launch {
        repository.deleteFromCart()
    }

    val getAllFromCart: LiveData<List<ProductRecyclerViewItem.Cart>> = repository.getAllFromCart().asLiveData()


    //FAVORITES
    fun insertIntoFavorites(favorites: List<ProductRecyclerViewItem.Favorites>) = viewModelScope.launch {
        repository.insertToFavorites(favorites)
    }

    fun updateFavorites(favorites: ProductRecyclerViewItem.Favorites) = viewModelScope.launch {
        repository.updateList(favorites)
    }

    fun deleteAllFromFavorites() = viewModelScope.launch {
        repository.deleteFromFavorites()
    }

    val getAllFavorites: LiveData<List<ProductRecyclerViewItem.Favorites>> = repository.getAllFromFavorites().asLiveData()
    fun deleteFavorite(favorites: MutableList<ProductRecyclerViewItem.Favorites>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(favorites)
        }
    }


    //EXPENSES
    fun addToExpenses(expenses: ProductRecyclerViewItem.Expenses) = viewModelScope.launch {
        repository.insertToRoom(expenses)
    }

    fun updateExpenses(expenses: MutableList<ProductRecyclerViewItem.Expenses>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateList(expenses)
        }
    }

    val getFromExpenses: LiveData<List<ProductRecyclerViewItem.Expenses>> = repository.getAllExpenses().asLiveData()


    private fun callBack(callback: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        callback()
    }

}