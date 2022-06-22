package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.CategoryItems
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToCategoryList(categoryItems: CategoryItems)

    @Update
    fun updateCategoryList(categoryItems: CategoryItems)

    @Query("SELECT * FROM category_list ORDER BY id ASC")
    fun getAllFromCategoryList(): Flow<List<CategoryItems>>

    @Query("DELETE FROM category_list")
    fun deleteAllFromCategoryList()

    @Delete
    fun deleteCategoryList(categoryItems: CategoryItems)//single item

}