package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.CategoriesList
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToCategoryList(categoriesList: CategoriesList)

    @Update
    fun updateCategoryList(categoriesList: CategoriesList)

    @Query("SELECT * FROM category_list ORDER BY id ASC")
    fun getAllFromCategoryList(query: String): Flow<List<CategoriesList>>

    @Query("DELETE FROM category_list")
    fun deleteAllFromCategoryList()

    @Delete
    fun deleteCategoryList(categoriesList: CategoriesList)//single item

}