package com.example.pizzamax.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoom(expenses: Expenses)

    @Update
    suspend fun updateList(expenses: MutableList<Expenses>)

    @Query("SELECT * FROM expenses ORDER BY id ASC")
    fun getAllExpenses(): Flow<List<Expenses>>

    @Query("DELETE FROM expenses")
    suspend fun deleteFromExpenses()

    @Delete
    suspend fun deleteItem(expenses: Expenses)//single item
}