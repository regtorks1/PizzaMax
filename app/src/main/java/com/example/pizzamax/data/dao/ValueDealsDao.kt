package com.example.pizzamax.data.dao

import androidx.room.*
import com.example.pizzamax.model.ValuesDeals
import kotlinx.coroutines.flow.Flow


@Dao
interface ValueDealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoom(valueDeals: ValuesDeals)

    @Update
    suspend fun updateList(valueDeals: ValuesDeals)

    @Query("SELECT * FROM deals_table ORDER BY id ASC")
    fun getAll(): Flow<List<ValuesDeals>>

    @Query("DELETE FROM deals_table")
    suspend fun deleteAll()

     @Delete
    suspend fun deleteItem(valueDeals: ValuesDeals)//single item
}