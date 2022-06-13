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
    override suspend fun insertToRoom(bigBetter: BigBetter) {
        bigBetterDoa.insertToRoom(bigBetter)
    }

    override suspend fun updateList(bigBetter: BigBetter) {
        return bigBetterDoa.updateList(bigBetter)
    }

    override fun getAllFromBigBetter(): Flow<List<BigBetter>> {
        return bigBetterDoa.getAllFromBigBetter()
    }

    override suspend fun deleteFromBigBetter() {
        return bigBetterDoa.deleteFromBigBetter()
    }

    override suspend fun deleteItem(bigBetter: BigBetter) {
        TODO("Not yet implemented")
    }


    //VALUE DEALS REPOSITORY
    override suspend fun insertToRoom(valueDeals: ValuesDeals) {
        valueDealsDao.insertToRoom(valueDeals)
    }

    override suspend fun updateList(valueDeals: ValuesDeals) {
        return valueDealsDao.updateList(valueDeals)
    }

    override fun getAll(): Flow<List<ValuesDeals>> {
        return valueDealsDao.getAll()
    }

    override suspend fun deleteAll() {
        return valueDealsDao.deleteAll()
    }

    override suspend fun deleteItem(valueDeals: ValuesDeals) {
        return valueDealsDao.deleteItem(valueDeals)
    }


    //APPETIZERS REPOSITORY
    override suspend fun insertToRoom(appetizers: Appetizers) {
        appetizersDao.insertToRoom(appetizers)
    }

    override suspend fun updateList(appetizers: Appetizers) {
        return appetizersDao.updateList(appetizers)
    }

    override fun getAllFromAppetizers(): Flow<List<Appetizers>> {
        return appetizersDao.getAllFromAppetizers()
    }

    override suspend fun deleteAllFromAppetizers() {
        return appetizersDao.deleteAllFromAppetizers()
    }

    override suspend fun deleteItem(appetizers: Appetizers) {
       return appetizersDao.deleteItem(appetizers)
    }


    //SIGNATURE REPOSITORY
    override suspend fun insertToRoom(signaturePizza: SignaturePizza) {
        signatureDao.insertToRoom(signaturePizza)
    }

    override suspend fun updateList(signaturePizza: SignaturePizza) {
        return signatureDao.updateList(signaturePizza)
    }

    override fun getAllFromSignature(): Flow<List<SignaturePizza>> {
        return signatureDao.getAllFromSignature()
    }

    override suspend fun deleteAllFromSignature() {
        return signatureDao.deleteAllFromSignature()
    }

    override suspend fun deleteItem(signaturePizza: SignaturePizza) {
        return signatureDao.deleteItem(signaturePizza)
    }


    //FAVORITES REPOSITORY
   override suspend fun insertToFavorites(favorites: List<Favorites>) {
        favoritesDao.insertToFavorites(favorites)
    }

    override suspend fun updateList(favorites: Favorites) {
        return favoritesDao.updateList(favorites)
    }

    override fun getAllFromFavorites(): Flow<List<Favorites>> {
        return favoritesDao.getAllFromFavorites()
    }

    override suspend fun deleteFromFavorites() {
        return favoritesDao.deleteFromFavorites()
    }

    override suspend fun deleteItem(favorites: MutableList<Favorites>) {
        return favoritesDao.deleteItem(favorites)
    }


    //CART REPOSITORY
    override suspend fun insertToRoom(cart: List<Cart>) {
        return carDao.insertToRoom(cart)
    }

    override suspend fun updateList(cart: Cart) {
        return carDao.updateList(cart)
    }

    override fun getAllFromCart(): Flow<List<Cart>> {
        return carDao.getAllFromCart()
    }

    override suspend fun deleteFromCart() {
        return carDao.deleteFromCart()
    }

    override suspend fun deleteItem(cart: Cart) {
        return carDao.deleteItem(cart)
    }

    override suspend fun insertToRoom(expenses: Expenses) {
        return expensesDao.insertToRoom(expenses)
    }

    override suspend fun updateList(expenses: MutableList<Expenses>) {
       return expensesDao.updateList(expenses)
    }

    override fun getAllExpenses(): Flow<List<Expenses>> {
        return expensesDao.getAllExpenses()
    }

    override suspend fun deleteFromExpenses() {
        return expensesDao.deleteFromExpenses()
    }

    override suspend fun deleteItem(expenses: Expenses) {
        return expensesDao.deleteItem(expenses)
    }

}