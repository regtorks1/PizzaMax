package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Expenses
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertToRoom(expenses: Expenses)

    @Update
     fun updateList(expenses: MutableList<Expenses>)

    @Query("SELECT * FROM expenses ORDER BY id ASC")
    fun getAllExpenses(): Flow<List<Expenses>>

    @Query("DELETE FROM expenses")
     fun deleteFromExpenses()

    @Delete
    fun deleteItem(expenses: Expenses)//single item
}