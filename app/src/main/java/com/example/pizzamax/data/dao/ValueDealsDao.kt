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

    @Query("SELECT * FROM deal ORDER BY id ASC")
    fun getAll(): Flow<List<ValuesDeals>>

    @Query("DELETE FROM deal")
    suspend fun deleteAll()
}