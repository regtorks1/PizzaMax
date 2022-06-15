package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.Appetizers
import kotlinx.coroutines.flow.Flow


@Dao
interface AppetizersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToRoom(appetizers: Appetizers)

    @Update
     fun updateList(appetizers: Appetizers)

    @Query("SELECT * FROM appetizers ORDER BY id ASC")
    fun getAllFromAppetizers(): Flow<List<Appetizers>>

    @Query("DELETE FROM appetizers")
     fun deleteAllFromAppetizers()

    @Delete
     fun deleteItem(appetizers: Appetizers)//single item
}