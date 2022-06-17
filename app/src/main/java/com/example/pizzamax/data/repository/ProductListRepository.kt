package com.example.pizzamax.data.repository

import com.example.pizzamax.data.dao.ProductListDao
import com.example.pizzamax.views.adapters.ProductRecyclerViewItem
import kotlinx.coroutines.flow.Flow

class ProductListRepository(
    private val bigBetterDoa: ProductListDao.BigBetterDoa,
    private val valueDealsDao: ProductListDao.ValueDealsDao,
    private val appetizersDao: ProductListDao.AppetizersDao,
    private val signatureDao: ProductListDao.SignatureDao,
    private val favoritesDao: ProductListDao.FavoritesDao,
    private val carDao: ProductListDao.CartDao,
    private val expensesDao: ProductListDao.ExpensesDao
) : ProductListDao.ValueDealsDao, ProductListDao.FavoritesDao, ProductListDao.AppetizersDao,
    ProductListDao.CartDao, ProductListDao.ExpensesDao, ProductListDao.BigBetterDoa, ProductListDao.SignatureDao {
    override fun insertToRoom(appetizers: ProductRecyclerViewItem.Appetizers) {
       appetizersDao.insertToRoom(appetizers)
    }

    override fun updateList(appetizers: ProductRecyclerViewItem.Appetizers) {
        return appetizersDao.updateList(appetizers)
    }

    override fun getAllFromAppetizers(): Flow<List<ProductRecyclerViewItem.Appetizers>> {
        return appetizersDao.getAllFromAppetizers()
    }

    override fun deleteAllFromAppetizers() {
     return appetizersDao.deleteAllFromAppetizers()
    }

    override fun deleteItem(appetizers: ProductRecyclerViewItem.Appetizers) {
        return appetizersDao.deleteItem(appetizers)
    }

    override fun insertToRoom(cart: List<ProductRecyclerViewItem.Cart>) {
        return carDao.insertToRoom(cart)
    }

    override fun updateList(cart: ProductRecyclerViewItem.Cart) {
       return carDao.updateList(cart)
    }

    override fun getAllFromCart(): Flow<List<ProductRecyclerViewItem.Cart>> {
     return carDao.getAllFromCart()
    }

    override fun deleteFromCart() {
       return carDao.deleteFromCart()
    }

    override fun deleteItem(cart: ProductRecyclerViewItem.Cart) {
        return carDao.deleteItem(cart)
    }

    override fun insertToRoom(expenses: ProductRecyclerViewItem.Expenses) {
        return expensesDao.insertToRoom(expenses)
    }

    override fun updateList(expenses: MutableList<ProductRecyclerViewItem.Expenses>) {
      return expensesDao.updateList(expenses)
    }

    override fun getAllExpenses(): Flow<List<ProductRecyclerViewItem.Expenses>> {
        return expensesDao.getAllExpenses()
    }

    override fun deleteFromExpenses() {
         return expensesDao.deleteFromExpenses()
    }

    override fun deleteItem(expenses: ProductRecyclerViewItem.Expenses) {
      return expensesDao.deleteItem(expenses)
    }



    override fun insertToFavorites(favorites: List<ProductRecyclerViewItem.Favorites>) {
         favoritesDao.insertToFavorites(favorites)
    }

    override fun updateList(favorites: ProductRecyclerViewItem.Favorites) {
     return favoritesDao.updateList(favorites)
    }

    override fun getAllFromFavorites(): Flow<List<ProductRecyclerViewItem.Favorites>> {
      return favoritesDao.getAllFromFavorites()
    }

    override fun deleteFromFavorites() {
       return favoritesDao.deleteFromFavorites()
    }

    override fun deleteItem(favorites: MutableList<ProductRecyclerViewItem.Favorites>) {
         return favoritesDao.deleteItem(favorites)
    }



    override fun insertToRoom(valueDeals: ProductRecyclerViewItem.ValuesDeals) {
       valueDealsDao.insertToRoom(valueDeals)
    }

    override fun updateList(valueDeals: ProductRecyclerViewItem.ValuesDeals) {
        return valueDealsDao.updateList(valueDeals)
    }

    override fun getAll(): Flow<List<ProductRecyclerViewItem.ValuesDeals>> {
         return valueDealsDao.getAll()
    }

    override fun deleteAll(): Int {
      return valueDealsDao.deleteAll()
    }

    override fun deleteItem(valueDeals: ProductRecyclerViewItem.ValuesDeals) {
      return valueDealsDao.deleteItem(valueDeals)
    }

    override fun insertToRoom(bigBetter: ProductRecyclerViewItem.BigBetter) {
        bigBetterDoa.insertToRoom(bigBetter)
    }

    override fun updateList(bigBetter: ProductRecyclerViewItem.BigBetter) {
       return bigBetterDoa.updateList(bigBetter)
    }

    override fun getAllFromBigBetter(): Flow<List<ProductRecyclerViewItem.BigBetter>> {
     return bigBetterDoa.getAllFromBigBetter()
    }

    override fun deleteFromBigBetter() {
          return bigBetterDoa.deleteFromBigBetter()
    }

    override fun deleteItem(bigBetter: ProductRecyclerViewItem.BigBetter) {
      return bigBetterDoa.deleteItem(bigBetter)
    }

    override fun insertToRoom(signaturePizza: ProductRecyclerViewItem.SignaturePizza) {
          signatureDao.insertToRoom(signaturePizza)
    }

    override fun updateList(signaturePizza: ProductRecyclerViewItem.SignaturePizza) {
       return signatureDao.updateList(signaturePizza)
    }

    override fun getAllFromSignature(): Flow<List<ProductRecyclerViewItem.SignaturePizza>> {
           return signatureDao.getAllFromSignature()
    }

    override fun deleteAllFromSignature() {
         return signatureDao.deleteAllFromSignature()
    }

    override fun deleteItem(signaturePizza: ProductRecyclerViewItem.SignaturePizza) {
       return signatureDao.deleteItem(signaturePizza)
    }
}