package com.example.pizzamax.views.adapters

import com.example.pizzamax.model.*

interface AdapterListImpl {
    fun onAddToCartListener(cart: Cart)
    fun onAddToFavoriteListener(favorites: CategoryItems)
    fun onViewDetailListener(categoryItems: CategoryItems)
}