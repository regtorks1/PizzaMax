package com.example.pizzamax.data.repository

import com.example.pizzamax.data.dao.*
import com.example.pizzamax.model.*
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val bigBetterDoa: BigBetterDoa,
    private val valueDealsDao: ValueDealsDao,
    private val appetizersDao: AppetizersDao,
    private val signatureDao: SignatureDao,
    private val favoritesDao: FavoritesDao,
    private val carDao: CartDao,
    private val expensesDao: ExpensesDao
) : ValueDealsDao, BigBetterDoa, AppetizersDao, SignatureDao, FavoritesDao, CartDao, ExpensesDao {

    //BIG BETTER REPOSITORY
    override  fun insertToRoom(bigBetter: BigBetter) {
        bigBetterDoa.insertToRoom(bigBetter)
    }

    override  fun updateList(bigBetter: BigBetter) {
        return bigBetterDoa.updateList(bigBetter)
    }

    override fun getAllFromBigBetter(): Flow<List<BigBetter>> {
        return bigBetterDoa.getAllFromBigBetter()
    }

    override  fun deleteFromBigBetter() {
        return bigBetterDoa.deleteFromBigBetter()
    }

    override  fun deleteItem(bigBetter: BigBetter) {
        TODO("Not yet implemented")
    }


    //VALUE DEALS REPOSITORY
    override  fun insertToRoom(valueDeals: ValuesDeals) {
        valueDealsDao.insertToRoom(valueDeals)
    }

    override  fun updateList(valueDeals: ValuesDeals) {
        return valueDealsDao.updateList(valueDeals)
    }

    override fun getAll(): Flow<List<ValuesDeals>> {
        return valueDealsDao.getAll()
    }

    override  fun deleteAll(): Int {
        return valueDealsDao.deleteAll()
    }

    override  fun deleteItem(valueDeals: ValuesDeals) {
        return valueDealsDao.deleteItem(valueDeals)
    }


    //APPETIZERS REPOSITORY
    override  fun insertToRoom(appetizers: Appetizers) {
        appetizersDao.insertToRoom(appetizers)
    }

    override  fun updateList(appetizers: Appetizers) {
        return appetizersDao.updateList(appetizers)
    }

    override fun getAllFromAppetizers(): Flow<List<Appetizers>> {
        return appetizersDao.getAllFromAppetizers()
    }

    override  fun deleteAllFromAppetizers() {
        return appetizersDao.deleteAllFromAppetizers()
    }

    override  fun deleteItem(appetizers: Appetizers) {
       return appetizersDao.deleteItem(appetizers)
    }


    //SIGNATURE REPOSITORY
    override  fun insertToRoom(signaturePizza: SignaturePizza) {
        signatureDao.insertToRoom(signaturePizza)
    }

    override  fun updateList(signaturePizza: SignaturePizza) {
        return signatureDao.updateList(signaturePizza)
    }

    override fun getAllFromSignature(): Flow<List<SignaturePizza>> {
        return signatureDao.getAllFromSignature()
    }

    override  fun deleteAllFromSignature() {
        return signatureDao.deleteAllFromSignature()
    }

    override  fun deleteItem(signaturePizza: SignaturePizza) {
        return signatureDao.deleteItem(signaturePizza)
    }


    //FAVORITES REPOSITORY
   override  fun insertToFavorites(favorites: List<Favorites>) {
        favoritesDao.insertToFavorites(favorites)
    }

    override  fun updateList(favorites: Favorites) {
        return favoritesDao.updateList(favorites)
    }

    override fun getAllFromFavorites(): Flow<List<Favorites>> {
        return favoritesDao.getAllFromFavorites()
    }

    override  fun deleteFromFavorites() {
        return favoritesDao.deleteFromFavorites()
    }

    override  fun deleteItem(favorites: MutableList<Favorites>) {
        return favoritesDao.deleteItem(favorites)
    }


    //CART REPOSITORY
    override  fun insertToRoom(cart: List<Cart>) {
        return carDao.insertToRoom(cart)
    }

    override  fun updateList(cart: Cart) {
        return carDao.updateList(cart)
    }

    override fun getAllFromCart(): Flow<List<Cart>> {
        return carDao.getAllFromCart()
    }

    override  fun deleteFromCart() {
        return carDao.deleteFromCart()
    }

    override  fun deleteItem(cart: Cart) {
        return carDao.deleteItem(cart)
    }


    override  fun insertToRoom(expenses: Expenses) {
        return expensesDao.insertToRoom(expenses)
    }

    override  fun updateList(expenses: MutableList<Expenses>) {
       return expensesDao.updateList(expenses)
    }

    override fun getAllExpenses(): Flow<List<Expenses>> {
        return expensesDao.getAllExpenses()
    }

    override  fun deleteFromExpenses() {
        return expensesDao.deleteFromExpenses()
    }

    override fun deleteItem(expenses: Expenses) {
        return expensesDao.deleteItem(expenses)
    }

}