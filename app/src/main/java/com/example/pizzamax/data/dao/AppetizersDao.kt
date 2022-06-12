package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Appetizers
import kotlinx.coroutines.flow.Flow


@Dao
interface AppetizersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoom(appetizers: Appetizers)

    @Update
    suspend fun updateList(appetizers: Appetizers)

    @Query("SELECT * FROM appetizers ORDER BY id ASC")
    fun getAllFromAppetizers(): Flow<List<Appetizers>>

    @Query("DELETE FROM appetizers")
    suspend fun deleteAllFromAppetizers()

    @Delete
    suspend fun deleteItem(appetizers: Appetizers)//single item
}