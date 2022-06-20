package com.example.pizzamax.data.repository

import com.example.pizzamax.data.dao.*
import com.example.pizzamax.model.*
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val categoriesDao: CategoriesDao,
    private val categoryListDao: CategoryListDao

) : CategoriesDao, CategoryListDao {
    override fun insertToCategories(categories: Categories) {
        return categoriesDao.insertToCategories(categories)
    }

    override fun updateCategories(categories: Categories) {
        categoriesDao.updateCategories(categories)
    }

    override fun getAllFromCategories(query: String): Flow<List<Categories>> {
     return  categoriesDao.getAllFromCategories(query)
    }

    override fun deleteAllFromCategories() {
        return categoriesDao.deleteAllFromCategories()
    }

    override fun deleteCategory(categories: Categories) {
        return categoriesDao.deleteCategory(categories)
    }

    override fun insertToCategoryList(categoriesList: CategoriesList) {
        return categoryListDao.insertToCategoryList(categoriesList)
    }

    override fun updateCategoryList(categoriesList: CategoriesList) {
       return categoryListDao.updateCategoryList(categoriesList)
    }

    override fun getAllFromCategoryList(): Flow<List<CategoriesList>> {
       return categoryListDao.getAllFromCategoryList()
    }

    override fun deleteAllFromCategoryList() {
        categoryListDao.deleteAllFromCategoryList()
    }

    override fun deleteCategoryList(categoriesList: CategoriesList) {
        categoryListDao.deleteCategoryList(categoriesList)
    }


}