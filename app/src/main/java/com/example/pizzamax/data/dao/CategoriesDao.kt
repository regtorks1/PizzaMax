package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Categories
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToCategories(categories: Categories)

    @Update
    fun updateCategories(categories: Categories)

    @Query("SELECT * FROM categories ORDER BY id ASC")
    fun getAllFromCategories(): Flow<List<Categories>>

    @Query("DELETE FROM categories")
    fun deleteAllFromCategories()

    @Delete
    fun deleteCategory(categories: Categories)//single item
}