package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Appetizers
import com.example.pizzamax.model.Category
import com.example.pizzamax.model.CategoryItems
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoom(categoryItems: CategoryItems)

    @Update
    suspend fun updateList(categoryItems: CategoryItems)

    @Query("SELECT * FROM category ORDER BY id ASC")
    fun getAllFromCategoryItems() : Flow<List<CategoryItems>>

    @Query("DELETE FROM category")
    suspend fun deleteFromCategoryItems()

    @Delete
    suspend fun deleteItem(categoryItems: CategoryItems)//single item
}