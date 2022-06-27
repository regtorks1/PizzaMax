package com.example.pizzamax.data.repository

import com.example.pizzamax.data.dao.CartDao
import com.example.pizzamax.data.dao.CategoriesDao
import com.example.pizzamax.data.dao.CategoryItemsDao
import com.example.pizzamax.data.dao.FavoritesDao
import com.example.pizzamax.model.Cart
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoryItems
import com.example.pizzamax.model.Favorites
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val categoriesDao: CategoriesDao,
    private val categoryItemsDao: CategoryItemsDao,
    private val cartDao: CartDao,
    private  val favoritesDao: FavoritesDao

) : CategoriesDao, CategoryItemsDao, CartDao, FavoritesDao {
    override fun insertToCategories(categories: MutableList<Categories>) {
        return categoriesDao.insertToCategories(categories)
    }

    override fun updateCategories(categories: Categories) {
        categoriesDao.updateCategories(categories)
    }

    override fun getAllFromCategories(query: String): Flow<List<Categories>> {
        return categoriesDao.getAllFromCategories(query)
    }

    override fun deleteAllFromCategories() {
        return categoriesDao.deleteAllFromCategories()
    }

    override fun deleteCategory(categories: Categories) {
        return categoriesDao.deleteCategory(categories)
    }

    override fun insertToCategoryList(categoryItems: CategoryItems) {
        return categoryItemsDao.insertToCategoryList(categoryItems)
    }

    override fun updateCategoryList(categoryItems: CategoryItems) {
        return categoryItemsDao.updateCategoryList(categoryItems)
    }

    override fun getAllFromCategoryList(): Flow<List<CategoryItems>> {
        return categoryItemsDao.getAllFromCategoryList()
    }

    override fun deleteAllFromCategoryList() {
        categoryItemsDao.deleteAllFromCategoryList()
    }

    override fun deleteCategoryList(categoryItems: CategoryItems) {
        categoryItemsDao.deleteCategoryList(categoryItems)
    }

    override fun insertToRoom(cart: List<Cart>) {
        return cartDao.insertToRoom(cart)
    }

    override fun updateList(cart: Cart) {
        return cartDao.updateList(cart)
    }

    override fun getAllFromCart(): Flow<List<Cart>> {
        return cartDao.getAllFromCart()
    }

    override fun deleteFromCart() {
        return cartDao.deleteFromCart()
    }

    override fun deleteItem(cart: Cart) {
        return cartDao.deleteItem(cart)
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

 override fun deleteItem(favorites: Favorites) {
     return favoritesDao.deleteItem(favorites)
 }


}