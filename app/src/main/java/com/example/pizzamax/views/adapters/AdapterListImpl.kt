package com.example.pizzamax.views.adapters

import com.example.pizzamax.model.*

interface AdapterListImpl {
    /**Add to cart from the following*/
    fun onAddCart(cart: ValuesDeals)
    fun onAddCart(cart: Favorites)
    fun onAddCart(cart: Appetizers)
    fun onAddCart(cart: BigBetter)
    fun onAddCart(cart: Category)
    fun onAddCart(cart: CategoryItems)

    /**Add to favorites from from the following*/
    fun addToFavorites(favorites: ValuesDeals)
    fun addToFavorites(favorites: BigBetter)
    fun addToFavorites(favorites: SignaturePizza)
    fun addToFavorites(favorites: Appetizers)
    fun addToFavorites(favorites: Category)
    fun addToFavorites(favorites: CategoryItems)


    fun onDetailsOnItemClicked(details: ValuesDeals)
    fun onDetailsOnItemClicked(details: BigBetter)
    fun onDetailsOnItemClicked(details: Appetizers)
    fun onDetailsOnItemClicked(details: SignaturePizza)
    fun onDetailsOnItemClicked(details: Category)
    fun onDetailsOnItemsClicked(details: CategoryItems)
}