package com.example.pizzamax.data.repository

import com.example.pizzamax.data.dao.CategoryDao
import com.example.pizzamax.data.dao.CategoryItemsDao
import com.example.pizzamax.model.Appetizers
import com.example.pizzamax.model.Category
import com.example.pizzamax.model.CategoryItems
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao,
                         private val categoryItemsDao: CategoryItemsDao):
                         CategoryDao, CategoryItemsDao {

    override suspend fun insertToRoom(category: Category) {
        categoryDao.insertToRoom(category)
    }

    override suspend fun updateList(category: Category) {
        return categoryDao.updateList(category)
    }

    override suspend fun insertToRoom(categoryItems: CategoryItems) {
        categoryItemsDao.insertToRoom(categoryItems)
    }

    override suspend fun updateList(categoryItems: CategoryItems) {
        return categoryItemsDao.insertToRoom(categoryItems)
    }

    override fun getAllFromCategoryItems(): Flow<List<CategoryItems>> {
        return categoryItemsDao.getAllFromCategoryItems()
    }

    override fun getAllFromCategory(): Flow<List<Category>> {
        return categoryDao.getAllFromCategory()
    }

    override suspend fun deleteFromCategoryItems() {
        return categoryItemsDao.deleteFromCategoryItems()
    }

    override suspend fun deleteItem(categoryItems: CategoryItems) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromCategory() {
        return categoryDao.deleteFromCategory()
    }

    override suspend fun deleteItem(category: Category) {
        TODO("Not yet implemented")
    }

}