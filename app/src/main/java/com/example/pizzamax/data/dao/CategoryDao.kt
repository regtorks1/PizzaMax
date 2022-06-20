package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Appetizers
import com.example.pizzamax.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoom(category: Category)

    @Update
    suspend fun updateList(category: Category)

    @Query("SELECT * FROM category ORDER BY id ASC")
    fun getAllFromCategory() : Flow<List<Category>>

    @Query("DELETE FROM category")
    suspend fun deleteFromCategory()

    @Delete
    suspend fun deleteItem(category: Category)//single item
}